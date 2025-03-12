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
package org.eclipse.jst.jsf.facelet.core.internal.facet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.designtime.DesignTimeApplicationManager;
import org.eclipse.jst.jsf.facelet.core.internal.FaceletCoreTraceOptions;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.ViewHandlerType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * The facet installing delegate for Facelets.
 * 
 * @author cbateman
 * 
 */
public class FaceletInstallDelegate extends FaceletChangeDelegate
{

    @Override
    public void execute(final IProject project, final IProjectFacetVersion fv,
            final Object config, final IProgressMonitor monitor)
            throws CoreException
    {
        final FacetChangeModel model = (FacetChangeModel) config;
        if (FaceletCoreTraceOptions.TRACE_FACETINSTALLDELEGATE)
        {
            FaceletCoreTraceOptions.log("Installing facet on project: " //$NON-NLS-1$
                    + project.getName());

            FaceletCoreTraceOptions.log(String.format(
                    "FaceletInstallDelegate: Add default selection %b", //$NON-NLS-1$
                    Boolean.valueOf(model.isChgDefaultSuffix())));
            FaceletCoreTraceOptions
                    .log(String
                            .format(
                                    "FaceletInstallDelegate: Add view handler %b", Boolean.valueOf(model //$NON-NLS-1$
                                                    .isChgViewHandler())));
            FaceletCoreTraceOptions.log(String.format(
                    "FaceletInstallDelegate: Add configure listener %b", //$NON-NLS-1$
                    Boolean.valueOf(model.isChgConfigureListener())));
            FaceletCoreTraceOptions
                    .log(String
                            .format(
                                    "FaceletInstallDelegate: Add web app lifecycle listener %b", //$NON-NLS-1$
                                    Boolean.valueOf(model
                                            .isChgWebAppLifecycleListener())));
        }

        try
        {
            if (monitor != null)
            {
                monitor.beginTask("Installing facelet facet", 1); //$NON-NLS-1$
            }

            super.execute(project, fv, config, monitor);
        }
        finally
        {
            if (monitor != null)
            {
                monitor.done();
            }
        }
    }

    /**
     * Install the facelet view handler in the WEB-INF/faces-config file if not
     * already present.
     * 
     * @param project
     * @param monitor
     */
    @Override
    protected void maybeChangeFaceletViewHandler(final IProject project,
            final IProgressMonitor monitor)
    {
        if (FaceletCoreTraceOptions.TRACE_FACETINSTALLDELEGATE)
        {
            FaceletCoreTraceOptions
                    .log("FaceletInstallDelegate: Installing facelet view handler"); //$NON-NLS-1$
        }

        final FacesConfigArtifactEdit edit = FacesConfigArtifactEdit
                .getFacesConfigArtifactEditForWrite(project,
                        "WEB-INF/faces-config.xml"); //$NON-NLS-1$
        if (edit != null)
        {
            try
            {
                final FacesConfigType root = edit.getFacesConfig();
                if (!isViewHandlerPresent(root))
                {
                    if (FaceletCoreTraceOptions.TRACE_FACETINSTALLDELEGATE)
                    {
                        FaceletCoreTraceOptions
                                .log("FaceletInstallDelegate: View Handler not already found in faces-config"); //$NON-NLS-1$
                    }

                    PlatformUI.getWorkbench().getDisplay().syncExec(
                            new TempSafeUpdateFacesConfigOnUIThread(edit,
                                    monitor));

                    if (FaceletCoreTraceOptions.TRACE_FACETINSTALLDELEGATE)
                    {
                        FaceletCoreTraceOptions
                                .log("FaceletInstallDelegate: Saved changes for facelet view handler"); //$NON-NLS-1$
                    }
                }
                else
                {
                    if (FaceletCoreTraceOptions.TRACE_FACETINSTALLDELEGATE)
                    {
                        FaceletCoreTraceOptions
                                .log("FaceletInstallDelegate: View Handler NOT already found in faces-config; not adding"); //$NON-NLS-1$
                    }
                }
            }
            finally
            {
                edit.dispose();
            }
        }
    }

    private static class TempSafeUpdateFacesConfigOnUIThread implements
            Runnable
    {
        private final FacesConfigArtifactEdit _edit;
        private final IProgressMonitor        _monitor;

        public TempSafeUpdateFacesConfigOnUIThread(
                final FacesConfigArtifactEdit edit,
                final IProgressMonitor monitor)
        {
            _edit = edit;
            _monitor = monitor;
        }

        @SuppressWarnings("unchecked")
        public void run()
        {

            final FacesConfigType root = _edit.getFacesConfig();
            final EList applications = root.getApplication();
            ApplicationType application = null;
            if (applications.size() > 0)
            {
                application = (ApplicationType) applications.get(0);
            }
            else
            {
                application = FacesConfigFactory.eINSTANCE
                        .createApplicationType();
                applications.add(application);
            }
            final ViewHandlerType viewHandlerType = FacesConfigFactory.eINSTANCE
                    .createViewHandlerType();
            viewHandlerType.setTextContent(RUNTIME_VIEWHANDLER_CLASS_NAME);
            application.getViewHandler().add(viewHandlerType);
            _edit.save(_monitor);
        }
    }

    @Override
    protected void handleDesignTimeViewHandler(final IProject project)
    {
        final DesignTimeApplicationManager manager = DesignTimeApplicationManager
                .getInstance(project);

        manager.setViewHandlerId(FaceletFacet.VIEW_HANDLER_ID);
    }

    @Override
    protected String getDisplayName()
    {
        return Messages.FaceletInstallDelegate_FACET_INSTALLER_DELEGATE_DISPLAY_NAME;
    }

    @Override
    protected void maybeChangeDefaultSuffix(final FacetChangeModel model,
            final WebAppConfigurator configurator)
    {
        if (model.isChgDefaultSuffix())
        {
            if (FaceletCoreTraceOptions.TRACE_FACETINSTALLDELEGATE)
            {
                FaceletCoreTraceOptions
                        .log("FaceletInstallDelegate: Installing DEFAULT_SUFFIX"); //$NON-NLS-1$
            }
            configurator.addContextParam(
                    JSFVersion.guessAtLeast(JSFVersion.V3_0, configurator.getProject()) ?
                            FaceletFacet.JAKARTA_FACES_DEFAULT_SUFFIX : FaceletFacet.JAVAX_FACES_DEFAULT_SUFFIX,
                    FaceletFacet.XHTML, false);
        }
    }

    @Override
    protected void maybeChangeConfigureListener(final FacetChangeModel model,
            final WebAppConfigurator configurator)
    {
        if (model.isChgConfigureListener())
        {
            if (FaceletCoreTraceOptions.TRACE_FACETINSTALLDELEGATE)
            {
                FaceletCoreTraceOptions
                        .log("FaceletInstallDelegate: Install Configure Listener"); //$NON-NLS-1$
            }
            configurator.addListener(FaceletFacet.COM_SUN_FACES_CONFIG_CONFIGURE_LISTENER,
                    false);
        }
    }

    @Override
    protected void maybeChangeWebLifecycleListener(
            final FacetChangeModel model, final WebAppConfigurator configurator)
    {
        if (model.isChgWebAppLifecycleListener())
        {
            if (FaceletCoreTraceOptions.TRACE_FACETINSTALLDELEGATE)
            {
                FaceletCoreTraceOptions
                        .log("FaceletInstallDelegate: Install WebappLifecycleListener"); //$NON-NLS-1$
            }
            configurator.addListener(
                    FaceletFacet.COM_SUN_FACES_APPLICATION_WEBAPP_LIFECYCLE_LISTENER, false);
        }
    }
}
