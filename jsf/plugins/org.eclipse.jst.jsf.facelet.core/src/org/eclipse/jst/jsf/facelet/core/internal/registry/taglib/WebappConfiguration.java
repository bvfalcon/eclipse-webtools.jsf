/*******************************************************************************
 * Copyright (c) 2008, 2013 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.javaee.core.ParamValue;
import org.eclipse.jst.jsf.common.internal.componentcore.AbstractJEEModelProviderQuery;
import org.eclipse.jst.jsf.common.internal.componentcore.AbstractVirtualComponentQuery;
import org.eclipse.jst.jsf.common.internal.resource.EventResult;
import org.eclipse.jst.jsf.common.internal.resource.IResourceLifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.LifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.EventType;
import org.eclipse.jst.jsf.common.internal.resource.WorkspaceMediator;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.WebappConfiguration.WebappListener.WebappChangeEvent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

/**
 * Manages the web.xml elements of interest to Facelet tag libraries
 * 
 * @author cbateman
 * 
 */
public class WebappConfiguration
{
    /**
     * The param key for Facelet 1.x libraries declared in web.xml
     */
    public static final String FACELET_10_LIBRARIES_CONTEXT_PARAM_NAME = "facelets.LIBRARIES"; //$NON-NLS-1$
    /**
     * The param key for Facelet 2.0 libraries declared in web.xml
     */
    public static final String JSF20_FACELET_LIBRARIES_CONTEXT_PARAM_NAME = "jakarta.faces.FACELETS_LIBRARIES"; //$NON-NLS-1$
    private final IProject _project;
    /**
     * Cached instance of ContextParamAdapter.
     */
    private final ContextParamAdapter _contextParamAdapter;
    private List<IFile> _cachedFiles;
    private final AbstractJEEModelProviderQuery _modelProvider;
    private final AbstractVirtualComponentQuery _vcQuery;
    private final LifecycleListener _lifecycleListener;
    private final WorkspaceMediator _wsMediator;

    /**
     * @param project
     * @param webAppProvider
     * @param vcQuery
     * @param wsMediator
     */
    public WebappConfiguration(final IProject project,
            final AbstractJEEModelProviderQuery webAppProvider,
            final AbstractVirtualComponentQuery vcQuery,
            final WorkspaceMediator wsMediator)
    {
        _project = project;
        _vcQuery = vcQuery;
        _lifecycleListener = new LifecycleListener(getWebXmlFile(project),
                project.getWorkspace());
        _contextParamAdapter = new ContextParamAdapter();
        _modelProvider = webAppProvider;
        _wsMediator = wsMediator;
    }

    /**
     * @param listener
     */
    public void addListener(final WebappListener listener)
    {
        _contextParamAdapter.addListener(listener);
    }

    /**
     * @param listener
     */
    public void removeListener(final WebappListener listener)
    {
        _contextParamAdapter.removeListener(listener);
    }

    /**
     * @return the list of IFile's
     */
    public List<IFile> getFiles()
    {
        final IVirtualFolder folder = _vcQuery.getWebContentFolder(_project);
        if (folder == null)
        {
            return Collections.emptyList();
        }
        final IContainer underlyingContainer = folder.getUnderlyingFolder();
        if (underlyingContainer == null)
        {
            return Collections.emptyList();
        }

        final List<String> filenames = getConfigFilesFromContextParam(_project,
                _modelProvider);
        final List<IFile> files = new ArrayList<IFile>();

        for (final String filename : filenames)
        {
            final IFile vfile = underlyingContainer.getFile(new Path(filename));
            if (vfile != null && vfile.isAccessible())
            {
                files.add(vfile);
            }
        }
        _cachedFiles = files;
        return Collections.unmodifiableList(_cachedFiles);
    }

    private IFile getWebXmlFile(final IProject project)
    {
        final IVirtualFolder webContentFolder = _vcQuery
                .getWebContentFolder(project);
        final IContainer folder = webContentFolder.getUnderlyingFolder();
        return folder.getFile(new Path("WEB-INF/web.xml")); //$NON-NLS-1$
    }

    /**
     * 
     */
    public void start()
    {
        _lifecycleListener.addListener(_contextParamAdapter);
    }

    /**
     * 
     */
    public void stop()
    {
        _lifecycleListener.removeListener(_contextParamAdapter);
    }

    /**
     * 
     */
    public void dispose()
    {
        _lifecycleListener.dispose();
    }

    /**
     * Gets list of application configuration file names as listed in the JSF
     * CONFIG_FILES context parameter ("jakarta.faces.CONFIG_FILES"). Will return
     * an empty list if WebArtifactEdit is null, if WebApp is null, if context
     * parameter does not exist, or if trimmed context parameter's value is an
     * empty String.
     * 
     * @param project
     *            IProject instance for which to get the context parameter's
     *            value.
     * @param modelProvider
     * @return List of application configuration file names as listed in the JSF
     *         CONFIG_FILES context parameter ("jakarta.faces.CONFIG_FILES"); list
     *         may be empty.
     */
    public static List<String> getConfigFilesFromContextParam(
            final IProject project, final AbstractJEEModelProviderQuery modelProvider)
    {
        List<String> filesList = new ArrayList<String>(5);
        {
            List<ParamValue> paramValues = modelProvider.getWebAppParamValues();
            for (final ParamValue paramValue : paramValues){
                if (paramValue.getParamName().equals(
                        FACELET_10_LIBRARIES_CONTEXT_PARAM_NAME)
                        || paramValue.getParamName().equals(
                                JSF20_FACELET_LIBRARIES_CONTEXT_PARAM_NAME))
                {
                    String filesString = paramValue.getParamValue();
                    filesList.addAll(parseFilesString(filesString));
                }
            }
        }
        return filesList.isEmpty() ? Collections.EMPTY_LIST : filesList;
    }

    private static List<String> parseFilesString(final String filesString)
    {
        final List<String> filesList = new ArrayList<String>();
        if (filesString != null && filesString.trim().length() > 0)
        {
            final StringTokenizer stFilesString = new StringTokenizer(
                    filesString, ";"); //$NON-NLS-1$
            while (stFilesString.hasMoreTokens())
            {
                final String configFile = stFilesString.nextToken().trim();
                filesList.add(configFile);
            }
        }
        return filesList;
    }

    /**
     * Adapter implementation used to monitor addition/removal of context-param
     * nodes and change in name of existing nodes in order to respond to changes
     * to the JSF CONFIG_FILES context-param.
     * 
     * @author Ian Trimble - Oracle
     */
    private class ContextParamAdapter implements IResourceLifecycleListener
    {
        private final CopyOnWriteArrayList<WebappListener> _listeners = new CopyOnWriteArrayList<WebappListener>();

        public void addListener(final WebappListener listener)
        {
            _listeners.addIfAbsent(listener);
        }

        public void removeListener(final WebappListener listener)
        {
            _listeners.remove(listener);
        }

        private void fireEvent(final WebappChangeEvent event)
        {
            for (final WebappListener listener : _listeners)
            {
                listener.webappChanged(event);
            }
        }

        private void checkAndFireFileChanges()
        {
            final List<IFile> oldFiles = _cachedFiles == null ? Collections.EMPTY_LIST
                    : _cachedFiles;
            final List<IFile> newFiles = getFiles();

            final List<IFile> filesAdded = new ArrayList<IFile>();
            final List<IFile> filesRemoved = new ArrayList<IFile>();

            for (final IFile oldFile : oldFiles)
            {
                if (!newFiles.contains(oldFile))
                {
                    filesRemoved.add(oldFile);
                }
            }

            for (final IFile newFile : newFiles)
            {
                if (!oldFiles.contains(newFile))
                {
                    filesAdded.add(newFile);
                }
            }

            if (filesAdded.size() > 0 || filesRemoved.size() > 0)
            {
                fireEvent(new WebappChangeEvent(filesRemoved, filesAdded));
            }
        }

        /**
         * Called when a ContextParam instance is removed.
         * 
         * @param contextParam
         *            ContextParam instance.
         */
        protected void processParamValue(
                final org.eclipse.jst.javaee.core.ParamValue contextParam)
        {
            checkAndFireFileChanges();
        }

//        /**
//         * Tests if the passed ContextParam instance is the JSF CONFIG_FILES
//         * context parameter.
//         * 
//         * @param contextParam
//         *            ContextParam instance.
//         * @return true if the passed ContextParam instance is the JSF
//         *         CONFIG_FILES context parameter, else false
//         */
//        protected boolean isConfigFilesContextParam(
//                final org.eclipse.jst.javaee.core.ParamValue contextParam)
//        {
//            boolean isConfigFiles = false;
//            if (contextParam != null)
//            {
//                final String name = contextParam.getParamName();
//                if (FACELET_10_LIBRARIES_CONTEXT_PARAM_NAME.equals(name)
//                        || JSF20_FACELET_LIBRARIES_CONTEXT_PARAM_NAME
//                                .equals(name))
//                {
//                    isConfigFiles = true;
//                }
//            }
//            return isConfigFiles;
//        }

        public EventResult acceptEvent(final ResourceLifecycleEvent event)
        {
            // the event is only interesting if it is the web.xml
            if (event.getAffectedResource() instanceof IFile
                    && "web.xml".equals(event.getAffectedResource().getProjectRelativePath().lastSegment())) //$NON-NLS-1$
            {
                if (event.getEventType() == EventType.RESOURCE_CHANGED)
                {
                    handleChange();
                }
            }
            return EventResult.getDefaultEventResult();
        }

        private void handleChange()
        {
            final IWorkspaceRunnable runnable = new IWorkspaceRunnable()
            {
                public void run(final IProgressMonitor monitor) throws CoreException
                {
                    List<ParamValue> webAppParamValues = _modelProvider.getWebAppParamValues();
                    for (final org.eclipse.jst.javaee.core.ParamValue paramValue : webAppParamValues)
                    {
                        processParamValue(paramValue);
                    }
                }
            };
            _wsMediator.runInWorkspaceJob(runnable, "Update web xml"); //$NON-NLS-1$
        }
    }

    abstract static class WebappListener
    {
        public static class WebappChangeEvent
        {
            private final List<IFile> _removed;
            private final List<IFile> _added;

            WebappChangeEvent(final List<IFile> removed, final List<IFile> added)
            {
                _removed = Collections.unmodifiableList(removed);
                _added = Collections.unmodifiableList(added);
            }

            public final List<IFile> getRemoved()
            {
                return _removed;
            }

            public final List<IFile> getAdded()
            {
                return _added;
            }
        }

        public abstract void webappChanged(final WebappChangeEvent event);
    }
}
