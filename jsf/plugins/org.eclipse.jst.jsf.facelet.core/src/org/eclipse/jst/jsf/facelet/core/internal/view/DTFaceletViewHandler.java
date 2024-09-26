/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.view;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;
import org.eclipse.jst.jsf.common.internal.JSFUtil;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot;
import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot.VersionStamp;
import org.eclipse.jst.jsf.designtime.internal.view.DefaultDTViewHandler;
import org.eclipse.jst.jsf.designtime.internal.view.IViewDefnAdapterFactory;
import org.eclipse.jst.jsf.designtime.internal.view.XMLComponentTreeConstructionStrategy;
import org.eclipse.jst.jsf.designtime.internal.view.XMLViewDefnAdapter;

/**
 * The Facelet design time view handler implementation.
 * 
 * @author cbateman
 *
 */
public class DTFaceletViewHandler extends DefaultDTViewHandler
{

    private static final String ORG_ECLIPSE_WST_HTML_CORE_HTMLSOURCE = "org.eclipse.wst.html.core.htmlsource"; //$NON-NLS-1$
    private static final String JAVAX_FACES_VIEW_ROOT = "jakarta.faces.ViewRoot"; //$NON-NLS-1$

    @Override
    public String calculateLocale(DTFacesContext context)
            throws ViewHandlerException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IResource getActionDefinition(DTFacesContext context, String viewId)
            throws ViewHandlerException
    {
        // TODO: this seems like a bit of a cope out...
        return context.adaptContextObject();
    }

    @Override
    public IPath getActionURL(DTFacesContext context, IResource resource,
            IPath requestPath) throws ViewHandlerException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IPath getRelativeActionPath(DTFacesContext context,
            String relativeToViewId, String uri) throws ViewHandlerException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IViewDefnAdapterFactory getViewMetadataAdapterFactory(
            DTFacesContext context) throws ViewHandlerException
    {
        final IResource res = context.adaptContextObject();

        if (res instanceof IFile)
        {
            return new ViewDefnAdapterFactory(this, getDefaultViewMetadataAdapterFactory(context));
        }

        return null;
    }

    @Override
    protected DTUIViewRoot newView(DTFacesContext facesContext, String viewId)
    {
        return new FaceletUIViewRoot(facesContext);
    }

    @Override
    protected XMLComponentTreeConstructionStrategy createTreeConstructionStrategy(
            XMLViewDefnAdapter adapter, IProject project)
    {
        return new XMLComponentTreeConstructionStrategy(adapter, project)
        {
            @SuppressWarnings("unchecked")
            @Override
            protected void populateViewRoot(DTUIViewRoot viewRoot, List children)
            {
                // facelets effectively ignores view roots created by the view
                // defn.  So we simply need to loop through all of children
                // and add them to viewRoot unless they are view roots, in which
                // case we add their children
                for (final Iterator<?> it = children.iterator(); it.hasNext();)
                {
                    final ComponentInfo child = (ComponentInfo) it.next();
                    
                    if (child instanceof DTUIViewRoot ||
                            JAVAX_FACES_VIEW_ROOT.equals(child.getComponentTypeInfo().getComponentType()))
                    {
                        // add recursively
                        populateViewRoot(viewRoot, child.getChildren());
                    }
                    else
                    {
                        viewRoot.addChild(child);
                    }
                }
            }
        };
    }

    @Override
    public boolean supportsViewDefinition(final IFile file)
    {
        return JSFVersion.guessAtLeast(JSFVersion.V2_0, file.getProject()) &&
            (JSFUtil.isJSFContentType(file) || isHTMLContent(file));
    }

    boolean isHTMLContent(final IFile file)
    {
        final IContentTypeManager typeManager = Platform
                .getContentTypeManager();
        IContentType htmlContentType = typeManager
                .getContentType(ORG_ECLIPSE_WST_HTML_CORE_HTMLSOURCE);
        if (htmlContentType != null
                && htmlContentType.isAssociatedWith(file.getName()))
        {
            return true;
        }
        return false;
    }

    @Override
    protected VersionStamp createVersionStamp(DTFacesContext facesContext,
            String viewId)
    {
        return new TimeBasedVersionStamp();
    }
}
