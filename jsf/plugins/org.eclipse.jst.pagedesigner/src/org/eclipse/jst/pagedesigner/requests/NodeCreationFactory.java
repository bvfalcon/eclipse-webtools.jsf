/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.requests;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.editors.HTMLEditor;
import org.eclipse.jst.pagedesigner.utils.JSFUtil;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author mengbo
 */
public class NodeCreationFactory implements CreationFactory {
	private final String _tagName;

	private final String _uri;

	private final String _suggestedPrefix;

	private final Map _attributes;

	/**
	 * @param uri
	 * @param tagname
	 * @param suggestedPrefix
	 * @param attributes
	 */
	public NodeCreationFactory(String uri, String tagname,
			String suggestedPrefix, Map attributes) {
		_tagName = tagname;
		_uri = uri;
		_suggestedPrefix = suggestedPrefix;
		_attributes = attributes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.requests.CreationFactory#getNewObject()
	 */
	public Object getNewObject() {
		Document ownerdoc = getOwnerDocument();
		if (ownerdoc == null)
			return null;

		Element ele = ownerdoc.createElement(_tagName);
		if (ele instanceof IDOMNode) {
			String prefix = getPrefix(_uri, ((IDOMDocument) ownerdoc)
					.getModel(), _suggestedPrefix);
			if (prefix != null) {
				ele.setPrefix(prefix);
			}
		}
		if (_attributes != null) {
			for (Iterator iter = _attributes.keySet().iterator(); iter
					.hasNext();) {
				String key = (String) iter.next();
				String value = (String) _attributes.get(key);
				ele.setAttribute(key, value);
			}
		}
		return ele;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.requests.CreationFactory#getObjectType()
	 */
	public Object getObjectType() {
		return _tagName;
	}

	/**
	 * XXX: need some better way for owner document. Maybe pass in from
	 * constructor
	 * 
	 * @return the owner document
	 */
	protected Document getOwnerDocument() {
		IWorkbenchWindow active = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		if (active == null)
			return null;
		IWorkbenchPage page = active.getActivePage();
		if (page == null)
			return null;
		IEditorPart editor = page.getActiveEditor();
		if (editor instanceof HTMLEditor) {
			return ((HTMLEditor) editor).getDOMDocument();
		}
        return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.requests.NodeCreationFactory#getPrefix(int)
	 */
	private String getPrefix(String uri, IDOMModel model, String suggested) {
		if (ITLDConstants.URI_HTML.equals(uri)
				|| ITLDConstants.URI_JSP.equals(uri))
			return null;

		// now handles custom tag lib
		return JSFUtil.getOrCreatePrefix(model, uri, suggested);
	}
}
