/*******************************************************************************
 * Copyright (c) 2006, 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.context.resolver.structureddocument.internal.impl;

import java.util.Iterator;

import org.eclipse.jst.jsf.common.internal.JSFUtil;
import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.context.resolver.structureddocument.ITaglibContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsp.core.internal.contentmodel.TaglibController;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.TLDCMDocumentManager;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.TaglibTracker;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * TODO: this resolver only applies to structured documents that can have
 * taglibs (I think only JSPs).  Should we move a different package?
 * Default taglib context resolver
 * 
 * @author cbateman
 * @deprecated Use ViewBasedTaglibResolver instead.  This impl will be removed
 * post-Helios.
 */
class TaglibContextResolver implements ITaglibContextResolver 
{
	private final IStructuredDocumentContext		_context;
	
	TaglibContextResolver(IStructuredDocumentContext context)
	{
		_context = context;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.context.resolver.structureddocument.ITaglibContextResolver#getTagPrefixForURI(java.lang.String)
	 */
	public String getTagPrefixForURI(String uri) 
	{
		return null;
	}

    public boolean hasTag(final Node node)
    {
        Node checkNode = node;
        
        if (node instanceof Attr)
        {
            checkNode = ((Attr)node).getOwnerElement();
        }
        
        return getTracker(checkNode) != null;
    }
    
    private TaglibTracker getTracker(Node node)
    {
        final String prefix = node.getPrefix();

        final TLDCMDocumentManager docMgr = 
            TaglibController.getTLDCMDocumentManager(_context.getStructuredDocument());

        // if there's no prefix, there's no way to id the tag library
        // TODO: is this always true?  need to consult spec
        // similar problem if couldn't load docMgr
        if (prefix == null
                || docMgr == null)
        {
            return null;
        }
        
        for (final Iterator it = docMgr.getTaglibTrackers().iterator(); it.hasNext();)
        {
            final TaglibTracker tracker = (TaglibTracker) it.next();
            
            if (prefix.equals(tracker.getPrefix()))
            {
                return tracker;
            }
        }
        
        return null;
    }
    
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.context.resolver.structureddocument.ITaglibContextResolver#getTagURIForNodeName(org.w3c.dom.Node)
	 */
	public String getTagURIForNodeName(final Node node) 
    {
        Node checkNode = node;
        
        if (node instanceof Attr)
        {
            checkNode = ((Attr)node).getOwnerElement();
        }
        
        final TaglibTracker tracker = getTracker(checkNode);
        
        if (tracker != null)
        {
            return tracker.getURI();
        }
        
        
        return null;
    }



	/* (non-Javadoc)
     * @see org.eclipse.jst.jsf.context.resolver.structureddocument.ITaglibContextResolver#getTagsByNamespaceURI(java.lang.String, java.lang.String)
     */
    public NodeList getTagsByNamespaceURI(String uri, String tagName) 
	{
		//Document domDoc = new DOMContextResolver(_context).getDOMDocument();
		return null;
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.context.resolver.IContextResolver#canResolveContext(org.eclipse.jst.jsf.context.IModelContext)
	 */
	public boolean canResolveContext(IModelContext modelContext) 
	{
        // must be a JSP page
		Object adapter = modelContext.getAdapter(IStructuredDocumentContext.class);
        
        if (adapter instanceof IStructuredDocumentContext)
        {
            IStructuredDocumentContext context =  
                (IStructuredDocumentContext) adapter;
            IStructuredModel smodel = null;
            
            try
            {
                smodel = StructuredModelManager.getModelManager().getModelForRead((IStructuredDocument)context.getStructuredDocument());
                return JSFUtil.isJSFContentType(smodel.getContentTypeIdentifier());
            }
            finally
            {
                if (smodel != null)
                {
                    smodel.releaseFromRead();
                }
            }
        }

        return false;
	}
}
