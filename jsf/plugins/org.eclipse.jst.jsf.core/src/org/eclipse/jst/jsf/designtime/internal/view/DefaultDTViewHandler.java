/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
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

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jst.jsf.common.internal.JSFUtil;
import org.eclipse.jst.jsf.common.internal.resource.EventResult;
import org.eclipse.jst.jsf.common.internal.resource.IResourceLifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.ImmutableLifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.ReasonType;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot.StalenessAdvisor;
import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot.StalenessEvent;
import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot.StalenessEvent.ChangeType;
import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot.StalenessListener;
import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot.VersionStamp;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry;
import org.w3c.dom.Node;

/**
 * A default implementation of the design time view handler meant to parallel
 * the default runtime ViewHandler required by the JSF spec.
 * 
 */
public class DefaultDTViewHandler extends AbstractDTViewHandler
{
    private final MyLifecycleManager _lifecycleManager = new MyLifecycleManager();

    @Override
    public String calculateLocale(final DTFacesContext context)
    throws ViewHandlerException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IResource getActionDefinition(final DTFacesContext context,
            final String viewId) throws ViewHandlerException
            {
        // TODO: this seems like a bit of a cop out...
        return context.adaptContextObject();
            }

    @Override
    public IPath getActionURL(final DTFacesContext context,
            final IResource resource, final IPath requestPath)
    throws ViewHandlerException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IPath getRelativeActionPath(final DTFacesContext context,
            final String relativeToViewId, final String uri)
    throws ViewHandlerException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IViewDefnAdapterFactory getViewMetadataAdapterFactory(
            final DTFacesContext context) throws ViewHandlerException
    {
    	return internalGetViewMetadataAdapterFactory(context);
    }
    
    /**
     * @param context
     * @return the DefaultViewDefnAdapterFactory
     * 			
     */
    protected IViewDefnAdapterFactory getDefaultViewMetadataAdapterFactory(
    		final DTFacesContext context) 
    {
    	return internalGetViewMetadataAdapterFactory(context);
    }

    private IViewDefnAdapterFactory internalGetViewMetadataAdapterFactory (final DTFacesContext context) {
    	final IResource res = context.adaptContextObject();

        if (res instanceof IFile)
        {
            return new DefaultViewDefnAdapterFactory(this);
        }

        return null;
    }
    
    @Override
    protected VersionStamp createVersionStamp(
            final DTFacesContext facesContext, final String viewId)
    {
        return new TimeBasedVersionStamp();
    }

    static class DefaultViewDefnAdapterFactory extends
    AbstractViewDefnAdapterFactory
    {
        private final DefaultDTViewHandler _myViewHandler;

        DefaultViewDefnAdapterFactory(final DefaultDTViewHandler viewHandler)
        {
            _myViewHandler = viewHandler;
        }

        @Override
        public IViewDefnAdapter<Node, IDocument> createAdapter(
                final DTFacesContext context, final String viewId)
                {
            try
            {
                final IResource res = _myViewHandler.getActionDefinition(
                        context, viewId);

                if (res instanceof IFile)
                {
                    final IFile srcFile = (IFile) res;
                    final ITagRegistry registry = findTagRegistry(srcFile);
                    if (JSFUtil.isJSFContentType(srcFile) && registry != null)
                    {
                        // if we have a jsp file, then return the default
                        // adapter
                        return new JSPViewDefnAdapter(registry);
                    }
                }
            }
            catch (final ViewHandlerException vhe)
            {
                JSFCorePlugin.log(vhe, "While acquiring view adapter"); //$NON-NLS-1$
            }

            // not found or failed
            return null;
                }
    }

    @Override
    protected DTUIViewRoot internalCreateView(
            final DTFacesContext facesContext, final String viewId)
    {
        IViewDefnAdapterFactory factory;
        try
        {
            factory = getViewMetadataAdapterFactory(facesContext);
            if (factory != null)
            {
                final IViewDefnAdapter<?, ?> adapter = factory.createAdapter(
                        facesContext, viewId);
                if (adapter instanceof XMLViewDefnAdapter)
                {
                    final IResource res = facesContext.adaptContextObject();
                    final DTUIViewRoot root = newView(facesContext, viewId);
                    final XMLComponentTreeConstructionStrategy constructionStrategy = createTreeConstructionStrategy(
                            (XMLViewDefnAdapter) adapter, res.getProject());

                    constructionStrategy
                    .createComponentTree(facesContext, root);
                    return root;
                }
                JSFCorePlugin
                .log(
                        IStatus.WARNING,
                        String
                        .format(
                                "Could not get view adapter to construct design time view root for %s", //$NON-NLS-1$
                                viewId));
            }
            else
            {
                JSFCorePlugin
                .log(
                        IStatus.WARNING,
                        String
                        .format(
                                "Could not get view adapter factory toconstruct design time view root for %s", //$NON-NLS-1$
                                viewId));
            }
        }
        catch (final ViewHandlerException e)
        {
            JSFCorePlugin.log(e, "While acquiring view defn adapter factory"); //$NON-NLS-1$
            // fall-through
        }

        return new NullViewRoot();
    }

    /**
     * By default, returns DefaultDTUIViewRoot.
     * 
     * @param facesContext
     * @param viewId
     * @return a new instance of a view. Override to change the implementation
     *         or configuration of DTUIViewRoot that is used by
     *         internalCreateView.
     */
    protected DTUIViewRoot newView(final DTFacesContext facesContext,
            final String viewId)
    {
        return new DefaultDTUIViewRoot(facesContext);
    }

    @Override
    protected void registerView(final DTUIViewRoot viewRoot,
            final DTFacesContext facesContext, final String viewId)
    {
        final IResource res = facesContext.adaptContextObject();
        _lifecycleManager.addViewInfo(viewId, res);
    }

    /**
     * Sub-classes may override to provide a different strategy.
     * 
     * @param adapter
     * @param project
     * @return the construction strategy used to create this view's component
     *         tree
     */
    protected XMLComponentTreeConstructionStrategy createTreeConstructionStrategy(
            final XMLViewDefnAdapter adapter, final IProject project)
    {
        return new XMLComponentTreeConstructionStrategy(adapter, project);
    }

    @Override
    public boolean supportsViewDefinition(final IFile file)
    {
        // currently only JSP content type is supported
        return (JSFUtil.isJSFContentType(file));
    }

    @Override
    protected StalenessAdvisor createStalenessAdvisor(
            final DTUIViewRoot viewRoot, final DTFacesContext facesContext,
            final String viewId)
    {
        final IResource res = facesContext.adaptContextObject();
        // if the view root is null or the res is null fall through
        // and use the null staleness advisor
        if (!(viewRoot instanceof NullViewRoot) && res != null)
        {
            return new ResourceModStampStalenessAdvisor(viewRoot, res, viewId);
        }
        return new NullStalenessAdvisor();
    }

    /**
     * Measures the staleness of a view by comparing the modification stamp on
     * the resource at construction with the current value when isStale is
     * called.
     * 
     * @author cbateman
     * 
     */
    protected final class ResourceModStampStalenessAdvisor extends
    StalenessAdvisor implements Serializable
    {
        /**
         * version id
         */
        private static final long                                 serialVersionUID = -4982206388722638735L;
        private final long                                        _modificationStamp;

        private transient final IResource                         _res;
        private transient final AtomicBoolean                     _forcedStale;
        private transient final StalenessListener                 _myListener;

        /**
         * @param viewRoot
         * @param file
         * @param viewId 
         */
        public ResourceModStampStalenessAdvisor(final DTUIViewRoot viewRoot,
                final IResource file, final String viewId)
        {
            _res = file;
            _modificationStamp = file.getModificationStamp();
            _forcedStale = new AtomicBoolean(false);
            _myListener = new StalenessListener()
            {
                @Override
                protected void stalenessChanged(final StalenessEvent event)
                {
                    if (event.getChangeType() == ChangeType.PROJECT_CLEANED)
                    {
                        if (_forcedStale.compareAndSet(false, true))
                        {
                            _lifecycleManager.removeListener(_res, _myListener);
                        }
                    }
                }
            };
            _lifecycleManager.addViewInfo(viewId, _res);
            _lifecycleManager.addListener(_res, _myListener);
        }

        @Override
        public boolean isStale()
        {
            if (!_forcedStale.get())
            {
                final long curStamp = _res.getModificationStamp();
                return curStamp != _modificationStamp;
            }
            // else forced stale
            return true;
        }

        @Override
        public void addListener(final StalenessListener listener)
        {
            _lifecycleManager.addListener(_res, listener);
        }

        @Override
        public void removeListener(final StalenessListener listener)
        {
            _lifecycleManager.removeListener(_res, listener);
        }

        @Override
        public boolean isAccessible()
        {
            return _res.isAccessible();
        }
    }

    @Override
    public final void setLifecycleListener(final ImmutableLifecycleListener listener)
    {
        _lifecycleManager.update(listener);
    }

    @Override
    protected final void doDispose()
    {
        _lifecycleManager.dispose();
    }

    private class MyLifecycleManager implements IResourceLifecycleListener
    {
        private ImmutableLifecycleListener     _listener;
        private final Map<IResource, ViewInfo> _stalenessListeners;
        private final IResourceChangeListener  _buildListener;

        public MyLifecycleManager()
        {
            _stalenessListeners = new HashMap<IResource, ViewInfo>();
            _buildListener = new IResourceChangeListener()
            {
                // on a clean build request, fire staleness for all project-related
                // resources.
                public void resourceChanged(final IResourceChangeEvent event)
                {
                    if (event.getType() == IResourceChangeEvent.PRE_BUILD)
                    {
                        if (event.getBuildKind() == IncrementalProjectBuilder.CLEAN_BUILD)
                        {
                            if (event.getSource() instanceof IProject)
                            {
                                cleanProject((IProject) event.getSource());
                            }
                            else if (event.getSource() instanceof IWorkspace)
                            {
                                cleanAll();
                            }
                        }
                    }
                }
            };
            ResourcesPlugin.getWorkspace().addResourceChangeListener(_buildListener,
                    IResourceChangeEvent.PRE_BUILD);
        }

        public void addListener(final IResource res, final StalenessListener listener)
        {
            final ViewInfo viewInfo = getViewInfo(res);
            viewInfo.getListeners().addIfAbsent(listener);
        }

        public void removeListener(final IResource res, final StalenessListener listener)
        {
            final ViewInfo viewInfo = getViewInfo(res);
            viewInfo.getListeners().remove(listener);
        }

        public EventResult acceptEvent(final ResourceLifecycleEvent event)
        {
            switch (event.getEventType())
            {
                case RESOURCE_CHANGED:
                {
                    return handleContentChangeEvent(event);
                }

                case RESOURCE_INACCESSIBLE:
                {
                    return handleInaccessibleChangeEvent(event);
                }

                default:
                    // do nothing with other types
            }

            return EventResult.getDefaultEventResult();
        }

        private EventResult handleContentChangeEvent(
                final ResourceLifecycleEvent event)
        {
            if (event.getReasonType() != ReasonType.RESOURCE_CHANGED_CONTENTS)
            {
                return EventResult.getDefaultEventResult();
            }

            final IResource res = event.getAffectedResource();
            final List<StalenessListener> stalenessListeners = getListeners(res);

            if (stalenessListeners != null)
            {
                for (final StalenessListener listener : stalenessListeners)
                {
                    listener.stalenessChanged(new StalenessEvent(
                            ChangeType.VIEW_DEFN_CHANGED));
                }
            }
            return EventResult.getDefaultEventResult();
        }

        private EventResult handleInaccessibleChangeEvent(
                final ResourceLifecycleEvent event)
        {
            final IResource res = event.getAffectedResource();
            final ReasonType reasonType = event.getReasonType();
            ChangeType changeType = null;
            if (reasonType == ReasonType.RESOURCE_PROJECT_CLOSED)
            {
                changeType = ChangeType.VIEW_DEFN_PROJECT_CLOSED;
            }
            else if (reasonType == ReasonType.RESOURCE_DELETED)
            {
                changeType = ChangeType.VIEW_DEFN_DELETED;
            }
            else
            {
                return EventResult.getDefaultEventResult();
            }

            final List<StalenessListener>  listeners = getListeners(res);

            if (listeners != null)
            {
                for (final StalenessListener listener : listeners)
                {
                    listener.stalenessChanged(new StalenessEvent(
                            changeType));
                }
            }
            return EventResult.getDefaultEventResult();
        }

        private void cleanAll()
        {
            final StalenessEvent event = new StalenessEvent(ChangeType.PROJECT_CLEANED);
            for (final Map.Entry<IResource, ViewInfo> entry : _stalenessListeners.entrySet())
            {
                final ViewInfo info = entry.getValue();
                for (final StalenessListener listener : info.getListeners())
                {
                    listener.stalenessChanged(event);
                }
            }
        }

        private void cleanProject(final IProject project)
        {
            final StalenessEvent event = new StalenessEvent(ChangeType.PROJECT_CLEANED);
            for (final Map.Entry<IResource, ViewInfo> entry : _stalenessListeners.entrySet())
            {
                final IResource res = entry.getKey();
                
                if (res.getProject().equals(project))
                {
                    final ViewInfo info = entry.getValue();
                    for (final StalenessListener listener : info.getListeners())
                    {
                        listener.stalenessChanged(event);
                    }
                }
            }
        }

        private List<StalenessListener> getListeners(final IResource res)
        {
            List<StalenessListener> stalenessListeners = null;

            synchronized (this)
            {
                final ViewInfo viewInfo = _stalenessListeners.get(res);

                if (viewInfo != null)
                {
                    stalenessListeners = viewInfo.getListeners();
                }
            }
            return stalenessListeners;
        }

        public synchronized void addViewInfo(final String viewId,
                final IResource res)
        {
            ViewInfo viewInfo = _stalenessListeners.get(res);

            if (viewInfo == null)
            {
                viewInfo = new ViewInfo(viewId);
                _stalenessListeners.put(res, viewInfo);
            }
        }

        public synchronized ViewInfo getViewInfo(final IResource res)
        {
            return _stalenessListeners.get(res);
        }

        public synchronized void dispose()
        {
            // updating with null effectively deregisters any existing listener
            // and doesn't register a new one.
            ResourcesPlugin.getWorkspace().removeResourceChangeListener(_buildListener);
            update(null);
        }

        public synchronized void update(
                final ImmutableLifecycleListener listener)
        {
            if (listener == _listener)
            {
                return;
            }

            final ImmutableLifecycleListener oldListener = _listener;

            if (oldListener != null)
            {
                oldListener.removeListener(this);
            }

            _listener = listener;

            if (_listener != null)
            {
                _listener.addListener(this);
            }
        }
    }

    private static class ViewInfo
    {
        private final CopyOnWriteArrayList<StalenessListener> _listeners;
//        private final String                  _viewId;

        /**
         * @param listeners
         * @param res
         */
        private ViewInfo(final String viewId)
        {
            super();
            _listeners = new CopyOnWriteArrayList<StalenessListener>();
//            _viewId = viewId;
        }

        protected final CopyOnWriteArrayList<StalenessListener> getListeners()
        {
            return _listeners;
        }

//        protected final String getViewId()
//        {
//            return _viewId;
//        }
    }
}
