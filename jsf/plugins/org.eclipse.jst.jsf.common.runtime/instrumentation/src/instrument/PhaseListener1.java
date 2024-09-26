/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package instrument;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import jakarta.faces.FactoryFinder;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIViewRoot;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.ResponseWriter;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
import jakarta.faces.render.RenderKit;
import jakarta.faces.render.RenderKitFactory;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import org.eclipse.jst.jsf.common.runtime.internal.debug.ComponentTreeMessage;
import org.eclipse.jst.jsf.common.runtime.internal.debug.JSFMonitorMessage;
import org.eclipse.jst.jsf.common.runtime.internal.debug.MonitorMessageFactory;
import org.eclipse.jst.jsf.common.runtime.internal.debug.RenderAttribute;
import org.eclipse.jst.jsf.common.runtime.internal.debug.RenderNode;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;

/**
 * @author cbateman
 *
 */
public class PhaseListener1 implements PhaseListener 
{
	SocketClient		_socketClient;
	ResponseWriter		_savedRW;
	LoggingResponseWriter  _injectRW;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6564874625827591775L;

	public PhaseListener1()
	{
		System.out.println("Initialized PhaseListener: "+this);
		_socketClient = new SocketClient(3702);
		Thread thread = new Thread(_socketClient);
		thread.start();
		
	}
	
	public void afterPhase(PhaseEvent event) {
		if(PhaseId.RENDER_RESPONSE.equals(event.getPhaseId()))
		{
			System.out.printf("afterPhase: %s, for view id: %s\n",event.getPhaseId(),event.getFacesContext().getViewRoot().getViewId());
			dumpComponentTree(event.getFacesContext());
			
			if (_savedRW != null)
			{
				event.getFacesContext().setResponseWriter(_savedRW);
			}
			
			if (_injectRW != null)
			{
				dumpNode(_injectRW._rootComponentNode, "");
			}
		}
	}

	private void dumpNode(RenderNode node, String prefix)
	{
		System.out.printf("%s%s for component %s\n", prefix, node.getRenderedNodeName(), node.getComponentId());

		for (final Iterator it = node.getChildren().iterator();it.hasNext();)
		{
			RenderNode child = (RenderNode) it.next();
			dumpNode(child, prefix+"\t");
		}
	}

	public void beforePhase(PhaseEvent event) {
		if (PhaseId.RENDER_RESPONSE.equals(event.getPhaseId()))
		{
			FacesContext facesContext = event.getFacesContext();
            RenderKitFactory renderFactory = 
            	(RenderKitFactory)FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY);
            RenderKit renderKit = renderFactory.getRenderKit(facesContext,
                                                             facesContext.getViewRoot().getRenderKitId());

            _savedRW = facesContext.getResponseWriter();

            try
            {
	            Writer writer = ((ServletResponse) facesContext.getExternalContext().getResponse()).getWriter();
	            String charEncoding = ((ServletRequest)facesContext.getExternalContext().getRequest()).getCharacterEncoding();
	            ResponseWriter responseWriter = renderKit.createResponseWriter
	            	(writer,
	                                           null /*Default: get the allowed content-types from the accept-header*/,
	                                           charEncoding);
	            _injectRW = new LoggingResponseWriter(responseWriter);
	            facesContext.setResponseWriter(_injectRW);
            }
            catch (IOException ioe)
            {
            	ioe.printStackTrace();
            }
		}
//		System.out.printf("beforePhase: %s, for view id: %s\n",event.getPhaseId(),event.getFacesContext().getViewRoot().getViewId());
//		dumpComponentTree(event.getFacesContext());
	}

	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

	private void dumpComponentTree(final FacesContext facesContext)
	{
		final UIViewRoot root = facesContext.getViewRoot();
		
		if (root != null)
		{
			final String viewId = root.getViewId();
			final ComponentInfo rootComponent = MyMonitorMessageFactory.buildComponentTree(root,false);
			ComponentTreeMessage message = 
				MonitorMessageFactory.createTreeMessage(viewId, rootComponent, _injectRW._rootComponentNode);
			_socketClient.put(message);
		}
	}
	
	
	private static class SocketClient implements Runnable
	{
		private final int					_port;
		
		private LinkedBlockingQueue<JSFMonitorMessage>  _queue = 
			new LinkedBlockingQueue<JSFMonitorMessage>();
		
		SocketClient(final int port)
		{
			_port = port;
		}
		
		public void run()
		{
			JSFMonitorMessage buffer = null;
			
			try
			{
				while ((buffer = _queue.take()) != null)
				{
			        Socket socket = null;
			        
			        try
			        {
			        	socket = new Socket("127.0.0.1", _port);
			        	ObjectOutputStream  stream = new ObjectOutputStream(socket.getOutputStream());
			        	stream.writeObject(buffer);
//						PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
//						writer.println(buffer);
//						writer.flush();
			        } catch (UnknownHostException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
			        finally
			        {
			        	if (socket != null && !socket.isClosed())
			        	{
			        		try {
								socket.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
			        	}
			        }
				}
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		public void put(JSFMonitorMessage  message)
		{
			_queue.add(message);
		}
	}
	
	private static class LoggingResponseWriter extends ResponseWriter
	{
		private final ResponseWriter		_wrapWriter;
		private final Stack/*<RenderNode>*/	_parentStack;
		private RenderNode					_curNode;
		private RenderNode					_rootComponentNode;

		// map component id to the root rendernode rendered for it
		private Map							_componentToHtml;
		
		LoggingResponseWriter(ResponseWriter  wrapWriter)
		{
			_wrapWriter = wrapWriter;
			_parentStack = new Stack();
			
			_componentToHtml = new HashMap/*<String,RenderNode>*/();
		}
		
		public ResponseWriter cloneWithWriter(Writer writer) {
			return _wrapWriter.cloneWithWriter(writer);
		}

		public void endDocument() throws IOException {
			_wrapWriter.endDocument();
		}

		public void endElement(String element) throws IOException {
			_curNode = (RenderNode) _parentStack.pop();
			_wrapWriter.endElement(element);
		}

		public void flush() throws IOException {
			_wrapWriter.flush();
		}

		public String getCharacterEncoding() {
			return _wrapWriter.getCharacterEncoding();
		}

		public String getContentType() {
			return _wrapWriter.getContentType();
		}

		public void startDocument() throws IOException {
			_rootComponentNode = new RenderNode(null, null);
			_wrapWriter.startDocument();
		}

		public void startElement(String elementName, UIComponent component)
				throws IOException 
		{
			String componentId = null;
			
			if (component != null)
			{
				componentId = component.getId();
			}
			
			RenderNode oldNode = _curNode;
			// push the curNode.  Pushing null indicates the root of the document
			_parentStack.push(oldNode);

			_curNode = new RenderNode(componentId, elementName);
			
			// note that assumption here is that the first html element
			// we see corresponding to a component is the root of its
			// html tree.  This may not be true if a component renders
			// more than one root element or if the output is generated
			// any thing but pre-order (parent first) tranversal of the 
			// component tree.  TODO: this algorithm needs refining
			if (!_componentToHtml.containsKey(componentId))
			{
				_componentToHtml.put(componentId, _curNode);
			}
			
			// if the current node isn't null, then append this new element
			if (oldNode != null)
			{
				oldNode.getChildren().add(_curNode);
			}
			// otherwise, we are at the doc root, so append there instead
			else
			{
				_rootComponentNode.getChildren().add(_curNode);
			}

			
			// do the normal write
			_wrapWriter.startElement(elementName, component);
		}

		public void writeAttribute(String name, Object value, String componentPropertyName)
				throws IOException {
			
			System.out.printf("%s: [%s,%s]\n", name, value.toString(), componentPropertyName);
			final RenderAttribute  attribute = 
				new RenderAttribute(name, value.toString(), componentPropertyName);
			
			if (_curNode != null)
			{
				_curNode.getRenderedAttributes().add(attribute);
			}
			_wrapWriter.writeAttribute(name, value, componentPropertyName);
		}

		public void writeComment(Object obj) throws IOException {
			_wrapWriter.writeComment(obj);
		}

		public void writeText(Object arg0, String arg1) throws IOException {
			_wrapWriter.writeText(arg0, arg1);
		}

		public void writeText(char[] arg0, int arg1, int arg2)
				throws IOException {
			_wrapWriter.writeText(arg0, arg1, arg2);
		}

		public void writeURIAttribute(String arg0, Object arg1, String arg2)
				throws IOException {
			_wrapWriter.writeURIAttribute(arg0, arg1, arg2);
		}

		public void close() throws IOException {
			_wrapWriter.close();
		}

		public void write(char[] cbuf, int off, int len) throws IOException {
			_wrapWriter.write(cbuf, off, len);
		}

		public ResponseWriter getWrapWriter() {
			return _wrapWriter;
		}
	}
}
