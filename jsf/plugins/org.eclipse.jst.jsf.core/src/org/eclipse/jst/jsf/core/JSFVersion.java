/*******************************************************************************
 * Copyright (c) 2001, 2018 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.core;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.wst.common.project.facet.core.FacetedProjectFramework;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/** 
 * @author gekessle
 *
 */
public enum JSFVersion {
	
	/**
	 * Unknown version support
	 */
	UNKNOWN,
	/**
	 * Supports JSF Version 1.0
	 */
	V1_0,
	/**
	 * Supports JSF Version 1.1
	 */
	V1_1,
	/**
	 * Supports JSF Version 1.1
	 */
	V1_2,
	/**
	 * Supports JSF Version 2.0
	 */
	V2_0,
	/**
	 * Supports JSF Version 2.1
	 */
	V2_1,
	/**
	 * Supports JSF Version 2.2
	 */
	V2_2,
	/**
	 * Supports JSF Version 2.3
	 */
	V2_3;
	
	// WARNING: you MUST add newer versions to the end; the ordinal value of
	// of the version is used in compareTo.
    @Override
    public String toString() {
        switch(this)
        {
            case V1_0:
                return IJSFCoreConstants.JSF_VERSION_1_0;
            case V1_1:
                return IJSFCoreConstants.JSF_VERSION_1_1;
            case V1_2:
                return IJSFCoreConstants.JSF_VERSION_1_2;
            case V2_0:
                return IJSFCoreConstants.JSF_VERSION_2_0;
            case V2_1:
            	return IJSFCoreConstants.JSF_VERSION_2_1;
            case V2_2:
            	return IJSFCoreConstants.JSF_VERSION_2_2;
            case V2_3:
            	return IJSFCoreConstants.JSF_VERSION_2_3;
            case UNKNOWN:
                return "unknown"; //$NON-NLS-1$
            default:
                throw new IllegalStateException("Missing string conversion: "+this.name()); //$NON-NLS-1$
        }
    }

    /**
     * @param valueAsString
     * @return the reverse mapping to an enum from toString() returns
     */
    public static JSFVersion valueOfString(String  valueAsString)
    {
        if (IJSFCoreConstants.JSF_VERSION_1_0.equals(valueAsString))
        {
            return V1_0;
        }
        else if (IJSFCoreConstants.JSF_VERSION_1_1.equals(valueAsString))
        {
            return V1_1;
        }
        else if (IJSFCoreConstants.JSF_VERSION_1_2.equals(valueAsString))
        {
            return V1_2;
        }
        else if (IJSFCoreConstants.JSF_VERSION_2_0.equals(valueAsString))
        {
            return V2_0;
        }
        else if (IJSFCoreConstants.JSF_VERSION_2_1.equals(valueAsString))
        {
        	return V2_1;
        }
        else if (IJSFCoreConstants.JSF_VERSION_2_2.equals(valueAsString))
        {
        	return V2_2;
        }
        else if (IJSFCoreConstants.JSF_VERSION_2_3.equals(valueAsString))
        {
        	return V2_3;
        }
        else if ("unknown".equals(valueAsString)) //$NON-NLS-1$
        {
            return UNKNOWN;
        }
        else
        {
            return null;
        }
    }
    
    /**
     * @param facetVersion
     * @return the jsf version for the facet version
     * @throw IllegalArgumentException if the underlying facet is not a JSF facet.
     */
    public static JSFVersion valueOfFacetVersion(final IProjectFacetVersion facetVersion)
    {
        if (!IJSFCoreConstants.isJSFFacet(facetVersion.getProjectFacet()))
        {
            throw new IllegalArgumentException("Not a JSF facet: "+facetVersion.getProjectFacet().toString()); //$NON-NLS-1$
        }
        
        String versionString = facetVersion.getVersionString();
        if (versionString != null)
        {
            return valueOfString(versionString);
        }
        return null;
    }
    
    /**
     * @param project
     * @return the project version of the project.
     */
    public static JSFVersion valueOfProject(final IProject project)
    {
        try
        {
            if (project != null && FacetedProjectFramework.isFacetedProject(project))
            {
                IFacetedProject fProj = ProjectFacetsManager.create(project);
                if (fProj != null)
                {
                    IProjectFacetVersion projectFacetVersion = fProj.getProjectFacetVersion(
                            ProjectFacetsManager.getProjectFacet(IJSFCoreConstants.JSF_CORE_FACET_ID));
                    if (projectFacetVersion != null)
                    {
                        return valueOfFacetVersion(projectFacetVersion);
                    }
                }
            }
        }
        catch(final CoreException ce)
        {
            // ignore and fall-through
            // TODO: is this worth logging?
        }
        return null;
    }
    
    
    /**
     * @param project
     * @return the best guess at what JSF version the project is or null if can't determine.
     */
    public static JSFVersion guessJSFVersion(final IProject project) {
        JSFVersion jsfVersion = JSFVersion.valueOfProject(project);
        if (jsfVersion == null) {
            try
            {
                IJavaProject javaProj = JavaCore.create(project);
                if (javaProj != null && javaProj.exists())
                {
                    if (javaProj.findType("jakarta.faces.component.html.HtmlBody") != null) //$NON-NLS-1$
                    {
                        // at least 2.0 inside here
                        jsfVersion = JSFVersion.V2_0;
                        if (javaProj.findType("jakarta.faces.view.facelets.FaceletCacheFactory") != null) //$NON-NLS-1$
                        {
                            // add in 2.1
                            jsfVersion = JSFVersion.V2_1;
                        }
                    }
                }
            }
            catch (JavaModelException jme)
            {
                JSFCorePlugin.log(jme, "Trying to guess jsf version"); //$NON-NLS-1$
            }
        }
        return jsfVersion;
    }
    
    /**
     * @param atLeastThisVersion
     * @param project
     * @return true if the project has at least JSFVersion passed based guessJSFVersion
     */
    public static boolean guessAtLeast(final JSFVersion atLeastThisVersion, final IProject project)
    {
        if (project != null && project.isAccessible())
        {
            JSFVersion guessJSFVersion = guessJSFVersion(project);
            return guessJSFVersion != null && guessJSFVersion.compareTo(atLeastThisVersion) >= 0;
        }
        return false;
    }
}
