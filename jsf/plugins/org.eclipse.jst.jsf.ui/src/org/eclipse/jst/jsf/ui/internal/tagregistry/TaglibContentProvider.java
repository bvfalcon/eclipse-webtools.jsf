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
package org.eclipse.jst.jsf.ui.internal.tagregistry;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagAttribute;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.core.internal.CompositeTagRegistryFactory;
import org.eclipse.jst.jsf.core.internal.ITagRegistryFactoryInfo;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry.TagRegistryChangeEvent;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry.TagRegistryChangeEvent.EventType;
import org.eclipse.jst.jsf.designtime.internal.view.model.TagRegistryFactory;
import org.eclipse.jst.jsf.designtime.internal.view.model.TagRegistryFactory.TagRegistryFactoryException;
import org.eclipse.jst.jsf.ui.internal.JSFUIPlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * Structured content provider for tag libraries.
 * 
 * @author cbateman
 * 
 */
public class TaglibContentProvider implements IStructuredContentProvider,
        ITreeContentProvider, ITagRegistry.ITagRegistryListener
{
    private final static Object[]                                     NO_CHILDREN       = new Object[0];
    private IProject                                                  _curInput;
    private Map<ITagRegistry, TagRegistryInstance>                    _curTagRegistries = 
        new HashMap<ITagRegistry, TagRegistryInstance>();
    private Viewer                                                    _curViewer;
    private final AtomicLong                                          _changeStamp      = new AtomicLong(
                                                                                                0);

    public Object[] getElements(final Object inputElement)
    {

        if (inputElement instanceof IProject)
        {
            return _curTagRegistries.values().toArray();
            // return _rootNamespaces.values().toArray();
        }

        return NO_CHILDREN;
    }

    public void dispose()
    {
        // nothing to do
    }

    public void inputChanged(final Viewer viewer, final Object oldInput,
            final Object newInput)
    {
        // update our change stamp to invalid outstanding update tasks
        _changeStamp.incrementAndGet();
        _curViewer = viewer;

        if (oldInput instanceof IProject)
        {
            for (final TagRegistryInstance tagRegistry : _curTagRegistries.values())
            {
                tagRegistry.getRegistry().removeListener(this);
            }
        }

        if (newInput instanceof IProject)
        {
            _curInput = (IProject) newInput;

            final Set<ITagRegistryFactoryInfo> factories = CompositeTagRegistryFactory
                    .getInstance().getAllTagRegistryFactories();

            _curTagRegistries.clear();

            for (ITagRegistryFactoryInfo factoryInfo : factories)
            {
                TagRegistryFactory factory = factoryInfo
                        .getTagRegistryFactory();
                ITagRegistry registry;
                try
                {
                    registry = factory.createTagRegistry(_curInput);
                    if (registry != null)
                    {
                        final TagRegistryInstance registryInstance =
                            new TagRegistryInstance(factoryInfo, registry);
                        _curTagRegistries.put(registry, registryInstance);
                        registry.addListener(this);
                        
                        new UpdateNamespacesListJob(_curInput, _changeStamp.get(), 
                                registryInstance).schedule();
                    }
                }
                catch (TagRegistryFactoryException e)
                {
                    JSFUIPlugin.log(IStatus.ERROR,
                            "Problem getting tag registry", e); //$NON-NLS-1$
                }
            }
        }
        else
        {
            _curInput = null;
            _curTagRegistries.clear();
        }
    }

    public Object[] getChildren(final Object parentElement)
    {
        if (parentElement instanceof IProject)
        {
            return _curTagRegistries.values().toArray();
        }
        else if (parentElement instanceof TagRegistryInstance)
        {
            final TagRegistryInstance regInstance = (TagRegistryInstance) parentElement;
            
            if (!regInstance.isUpToDate())
            {
                return new Object[] {new TreePlaceholder(Messages.TaglibContentProvider_Calculating, null)};
            }
            return regInstance.getNamespaces().values().toArray();
        }
        else if (parentElement instanceof Namespace)
        {
            final Namespace ns = (Namespace) parentElement;

            // this could be very expensive if not initialized
            if (ns.isInitialized())
            {
                return ((Namespace) parentElement).getViewElements().toArray();
            }

            // fire up a job that ensures the namespace is initialized
            // and then fires refresh again on this element
            final Job updateNamespaceJob = new Job(Messages.TaglibContentProvider_JobDesc)
            {
                @Override
                protected IStatus run(final IProgressMonitor monitor)
                {
                    ns.getViewElements();
                    PlatformUI.getWorkbench().getDisplay().asyncExec(
                            new Runnable()
                            {
                                public void run()
                                {
                                    // avoid infinite recursion
                                    if (ns.isInitialized())
                                    {
                                        TaglibContentProvider.this
                                                .viewerRefresh(ns);
                                    }
                                    else
                                    {
                                        MessageDialog
                                                .openError(
                                                        Display
                                                                .getCurrent()
                                                                .getActiveShell(),
                                                        Messages.TaglibContentProvider_NamespaceErrorTitle,
                                                        Messages.TaglibContentProvider_NamespaceErrorDescription);
                                    }
                                }
                            });
                    return Status.OK_STATUS;
                }
            };

            updateNamespaceJob.schedule();

            return new Object[]
            { new TreePlaceholder(Messages.TaglibContentProvider_TagCalculatingWaitMessage, null) };
        }
        else if (parentElement instanceof ITagElement)
        {
            final Map<String, ? extends ITagAttribute> attributes = ((ITagElement)parentElement).getAttributes();
            if (attributes != null)
            {
                return attributes.values().toArray();
            }
        }
        return NO_CHILDREN;
    }

    public Object getParent(final Object element)
    {
        // no support for parent traversal right now
        return null;
    }

    public boolean hasChildren(final Object element)
    {
        // avoid an infinite refresh loop on the namespaces in the tag registry
        if (element instanceof TagRegistryInstance)
        {
            return true;
        }
        // finding all children of a namespace can be expensive
        else if (element instanceof Namespace)
        {
            return ((Namespace) element).hasViewElements();
        }
        return getChildren(element).length > 0;
    }

    public void registryChanged(final TagRegistryChangeEvent changeEvent)
    {
        if (_curViewer != null)
        {
            TagRegistryInstance registryInstance =
                _curTagRegistries.get(changeEvent.getSource());
            
            if (registryInstance != null)
            {
                _curViewer.getControl().getDisplay().asyncExec(
                        new RegistryChangeTask(changeEvent.getType(), changeEvent
                                .getAffectedObjects(), _changeStamp.get(),registryInstance));
            }
        }
    }

    private final class RegistryChangeTask implements Runnable
    {
        private final EventType                 _eventType;
        private final long                      _timestamp;
        private final List<? extends Namespace> _affectedObjects;
        private final TagRegistryInstance       _registryInstance;

        RegistryChangeTask(final TagRegistryChangeEvent.EventType eventType,
                final List<? extends Namespace> affectedObjects,
                final long timestamp, final TagRegistryInstance registryInstance)
        {
            _eventType = eventType;
            _timestamp = timestamp;
            _affectedObjects = affectedObjects;
            _registryInstance = registryInstance;
        }

        public void run()
        {
            // if changes have been made since this task was queued, then abort
            // since we don't know if our data is still valid
            if (_timestamp != TaglibContentProvider.this._changeStamp.get())
            {
                return;
            }

            switch (_eventType)
            {
                case ADDED_NAMESPACE:
                case CHANGED_NAMESPACE:
                {
                    for (final Namespace ns : _affectedObjects)
                    {
                        _registryInstance.getNamespaces().put(ns.getNSUri(), ns);
                    }

                    viewerRefresh(_curInput);
                }
                break;

                case REMOVED_NAMESPACE:
                {
                    for (final Namespace ns : _affectedObjects)
                    {
                        _registryInstance.getNamespaces().remove(ns.getNSUri());
                    }
                    viewerRefresh(_curInput);
                }
                break;

                case REGISTRY_DISPOSED:
                {
                    _registryInstance.getRegistry().removeListener(TaglibContentProvider.this);
                    _curTagRegistries.remove(_registryInstance);
                    viewerRefresh(_curInput);
                }
            }
        }
    }

    private void viewerRefresh(final Object parentElement)
    {
        if (_curViewer instanceof StructuredViewer)
        {
            final StructuredViewer viewer = (StructuredViewer) _curViewer;
            viewer.refresh(parentElement);
        }
        else
        {
            _curViewer.refresh();
        }
    }

    private class UpdateNamespacesListJob extends Job
    {

        private final long                _timestamp;
        private final IProject            _project;
        private final TagRegistryInstance _registry;

        public UpdateNamespacesListJob(final IProject project,
                final long timestamp, final TagRegistryInstance registry)
        {
            super("Updating available namespaces for project " //$NON-NLS-1$
                    + project.getName());
            _project = project;
            _timestamp = timestamp;
            _registry = registry;
        }

        @Override
        protected IStatus run(final IProgressMonitor monitor)
        {
            if (!_project.isAccessible()
                    || _registry.isUpToDate())
            {
                return new Status(IStatus.CANCEL, JSFUIPlugin.PLUGIN_ID, ""); //$NON-NLS-1$
            }

            final Collection<? extends Namespace> libs = _registry.getRegistry()
                    .getAllTagLibraries();
            _registry.getNamespaces().clear();

            for (Namespace ns : libs)
            {
                if (ns.getNSUri() != null)
                {
                    _registry.getNamespaces().put(ns.getNSUri(), ns);

                }
            }

            _registry.setUpToDate(true);
            PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable()
            {
                public void run()
                {
                    // only bother if the provider hasn't changed asynchronously
                    if (_timestamp == TaglibContentProvider.this._changeStamp
                            .get())
                    {
                        viewerRefresh(_curInput);
                    }
                }
            });

            return Status.OK_STATUS;
        }
    }

    static class TagRegistryInstance
    {
        private final ITagRegistryFactoryInfo        _info;
        private final ITagRegistry                  _registry;
        private final Map<String, Namespace>        _namespaces;
        private boolean                             _isUpToDate;

        public TagRegistryInstance(final ITagRegistryFactoryInfo info,
                ITagRegistry registry)
        {
            _info = info;
            _registry = registry;
            _namespaces = new ConcurrentHashMap<String, Namespace>();
        }

        public ITagRegistryFactoryInfo getInfo()
        {
            return _info;
        }

        public ITagRegistry getRegistry()
        {
            return _registry;
        }

        public Map<String, Namespace> getNamespaces()
        {
            return _namespaces;
        }

        public synchronized boolean isUpToDate()
        {
            return _isUpToDate;
        }

        public synchronized void setUpToDate(boolean isUpToDate)
        {
            _isUpToDate = isUpToDate;
        }
    }

    /**
     * Takes the place of a real tree model object while the real object is
     * being retrieved.
     * 
     */
    public static class TreePlaceholder
    {
        private final String _text;
        private final Image  _image;

        TreePlaceholder(final String text, final Image image)
        {
            _text = text;
            _image = image;
        }

        /**
         * @return the placeholder text or null if none
         */
        public String getText()
        {
            return _text;
        }

        /**
         * @return the image or null if none
         */
        public Image getImage()
        {
            return _image;
        }

    }
}
