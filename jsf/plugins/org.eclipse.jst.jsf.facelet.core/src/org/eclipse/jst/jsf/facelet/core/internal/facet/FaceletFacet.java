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

import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * Facelet facet constants and utilities
 * @author cbateman
 *
 */
public final class FaceletFacet
{
    /**
     * Facelet facet id
     */
    public final static String FACET_ID        = "jsf.facelet"; //$NON-NLS-1$
    /**
     * The id of the Facelet HTML view handler
     */
    public final static String VIEW_HANDLER_ID = "org.eclipse.jst.jsf.facelet.core.html.viewhandler"; //$NON-NLS-1$
    static final String COM_SUN_FACES_APPLICATION_WEBAPP_LIFECYCLE_LISTENER = "com.sun.faces.application.WebappLifecycleListener"; //$NON-NLS-1$
    static final String COM_SUN_FACES_CONFIG_CONFIGURE_LISTENER             = "com.sun.faces.config.ConfigureListener";           //$NON-NLS-1$
    static final String XHTML                                               = ".xhtml";                                           //$NON-NLS-1$
    static final String JAVAX_FACES_DEFAULT_SUFFIX                          = "javax.faces.DEFAULT_SUFFIX";                       //$NON-NLS-1$
    static final String JAKARTA_FACES_DEFAULT_SUFFIX                        = "jakarta.faces.DEFAULT_SUFFIX";                       //$NON-NLS-1$

    private FaceletFacet()
    {
        // no external instantiation
    }

    /**
     * @param project
     * @return true if projec has the facelet facet installed.
     */
    public static boolean hasFacet(final IProject project)
    {
        final IProjectFacetVersion facetVersion = getProjectFacet(project);

        if (facetVersion != null)
        {
            return true;
        }
        return false;
    }

    /**
     * Get the facet version for the project
     * 
     * @param project
     * @return the project facet version or null if could not be found or if
     *  project is not accessible
     */
    @SuppressWarnings("unchecked")
    public static IProjectFacetVersion getProjectFacet(final IProject project)
    {
        // check for null or inaccessible project
        if (project != null && project.isAccessible())
        {
            // check for Facelet facet on project
            try
            {
                final IFacetedProject facetedProject = ProjectFacetsManager
                        .create(project);
                if (facetedProject != null)
                {
                    final Set projectFacets = facetedProject.getProjectFacets();
                    final Iterator itProjectFacets = projectFacets.iterator();
                    while (itProjectFacets.hasNext())
                    {
                        final IProjectFacetVersion projectFacetVersion = (IProjectFacetVersion) itProjectFacets
                                .next();
                        if (FACET_ID.equals(projectFacetVersion
                                .getProjectFacet().getId()))
                        {
                            return projectFacetVersion;
                        }
                    }
                }
            }
            catch (final CoreException ce)
            {
                // log error
                JSFCorePlugin.log(IStatus.ERROR, ce.getLocalizedMessage(), ce);
            }
        }
        return null;
    }
}
