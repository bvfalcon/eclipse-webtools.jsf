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
package org.eclipse.jst.jsf.designtime.internal.view;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler.ViewHandlerException;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler.ViewHandlerException.Cause;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry;
import org.eclipse.jst.jsp.core.internal.contentmodel.TaglibController;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.TLDCMDocumentManager;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.TaglibTracker;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;
import org.eclipse.jst.jsp.core.internal.domdocument.DOMModelForJSP;
import org.eclipse.jst.jsp.core.internal.regions.DOMJSPRegionContexts;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.regions.DOMRegionContext;
import org.w3c.dom.Element;

/**
 * <p>
 * A view definition adapter that adapts JSF view definitions based on the
 * standard JSP view description language.
 * </p>
 * 
 * @author cbateman
 * 
 */
public class JSPViewDefnAdapter extends TaglibBasedViewDefnAdapter
{
    JSPViewDefnAdapter(final ITagRegistry tldTagRegistry)
    {
        super(tldTagRegistry);
    }

    @Override
    public IDocument getContainer(final DTFacesContext context,
            final String viewId)
    {
        final IResource viewDefn = context.adaptContextObject();

        if (viewDefn instanceof IFile)
        {
            final IFile viewDefnFile = (IFile) viewDefn;
            IStructuredModel model = null;
            try
            {
                model =
                    StructuredModelManager.getModelManager()
                    .getModelForRead(viewDefnFile);

                if (model instanceof DOMModelForJSP)
                {
                    return model.getStructuredDocument();
                }
            }
            catch (final IOException e)
            {
                JSFCorePlugin.log(e, "Acquiring model for view root"); //$NON-NLS-1$
            }
            catch (final CoreException e)
            {
                JSFCorePlugin.log(e, "Acquiring model for view root"); //$NON-NLS-1$
            }
            finally
            {
                if (model != null)
                {
                    model.releaseFromRead();
                }
            }
        }
        return null;
    }

    @Override
    public final String getNamespace(final Element element,
            final IDocument doc)
    {
        final String prefix = element.getPrefix();

        // TODO: merge back with JSFUtil.findUri()
        final TLDCMDocumentManager m =
            TaglibController.getTLDCMDocumentManager(doc);

        if (prefix == null || m == null)
        {
            return null;
        }
        final List<?> trackers = m.getTaglibTrackers();
        for (final Object name : trackers)
        {
            final TaglibTracker tracker = (TaglibTracker) name;
            if (prefix.equals(tracker.getPrefix()))
            {
                return tracker.getURI();
            }
        }
        return null;
    }


    @Override
    public DTELExpression getELExpression(final IModelContext genericContext)
    throws ViewHandlerException
    {
        final IStructuredDocumentContext context =
            (IStructuredDocumentContext) genericContext
            .getAdapter(IStructuredDocumentContext.class);

        if (context == null)
        {
            throw new ViewHandlerException(Cause.EL_NOT_FOUND);
        }

        ITextRegionContextResolver resolver =
            IStructuredDocumentContextResolverFactory.INSTANCE
            .getTextRegionResolver(context);

        String elText = null;

        if (resolver != null)
        {
            final String regionType = resolver.getRegionType();

            if (regionType != null && resolver.matchesRelative(new String[]
                                                                          { DOMRegionContext.XML_TAG_ATTRIBUTE_VALUE }))
            {
                // if we are in the EL content, then get the current region
                // text
                if (DOMJSPRegionContexts.JSP_VBL_CONTENT.equals(regionType))
                {
                    elText = resolver.getRegionText();
                }
                // otherwise, we may be at the end of a content region but
                // at
                // the beginning of a closing brace so check to see if the
                // previous
                // region was a VBL_CONTENT
                else if (regionType.equals(DOMJSPRegionContexts.JSP_VBL_CLOSE))
                {
                    final IStructuredDocumentContext previousContext =
                        resolver.getPreviousContext();

                    final ITextRegionContextResolver prevResolver =
                        IStructuredDocumentContextResolverFactory.INSTANCE
                        .getTextRegionResolver(previousContext);

                    if (prevResolver != null)
                    {
                        if (DOMJSPRegionContexts.JSP_VBL_CONTENT
                                .equals(prevResolver.getRegionType()))
                        {
                            resolver = prevResolver;
                            elText = prevResolver.getRegionText();
                        }
                        else if (DOMJSPRegionContexts.JSP_VBL_OPEN
                                .equals(prevResolver.getRegionType()))
                        {
                            elText = ""; //$NON-NLS-1$
                        }
                    }
                }
            }
        }

        if (elText != null)
        {
            final IStructuredDocumentContext elContext =
                IStructuredDocumentContextFactory.INSTANCE.getContext(
                        context.getStructuredDocument(), resolver
                        .getStartOffset());
            return new DTELExpression(elContext, elText);
        }
        return null;
    }

    @Override
    public String getGeneratedIdPrefix()
    {
        return "_idJsp"; //$NON-NLS-1$
    }

    @Override
    public String getPrefix(String namespace, IDocument doc)
    {
        TLDCMDocumentManager m = TaglibController.getTLDCMDocumentManager(doc);
        if (m == null)
            return null;
        List trackers = m.getTaglibTrackers();
        for (Iterator iter = trackers.iterator(); iter.hasNext();) {
            TaglibTracker tracker = (TaglibTracker) iter.next();
            if (namespace.equals(tracker.getURI())) {
                return tracker.getPrefix();
            }
            
            CMDocument cmdoc = tracker.getDocument();
            if (cmdoc instanceof TLDDocument
                    && namespace.equals(((TLDDocument) cmdoc).getUri())) {
                return tracker.getPrefix();
            }
        }
        return null;

    }
}
