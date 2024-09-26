/*******************************************************************************
 * Copyright (c) 2006, 2011 Sybase, Inc. and others.
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
package org.eclipse.jst.jsf.common.ui.internal.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;
import org.eclipse.jst.jsf.common.ui.IFileFolderConstants;
import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * Web app utility methods
 */
public class WebrootUtil {

	/**
	 * get the webpath for the project path. The project path is something like
	 * "/projectname/webroot/filename.jsp", or "/projectname/webroot/folder".
	 * The project information should be removed from project path, e.g,
	 * "/filename.jsp" or "/folder/*";
	 * 
	 * @param path
	 * @return the web path
	 */
	public static String getWebPath(IPath path) {
		String strWebrootPath = ""; //$NON-NLS-1$
		IProject project = WorkspaceUtil.getProjectFor(path);
		IPath webContentPath = getWebContentPath(project);
		if (webContentPath != null && webContentPath.isPrefixOf(path)) {
			int start = path.matchingFirstSegments(webContentPath);
			String[] segments = path.segments();
			for (int i = start, n = path.segmentCount(); i < n; i++) {
				strWebrootPath = strWebrootPath
						+ IFileFolderConstants.PATH_SEPARATOR + segments[i];
			}
		}
		return strWebrootPath;
	}

	/**
	 * To see if a resource is under the webcontent folder.
	 * 
	 * @param resource
	 * @return true if resource is within the web content folder hierarchy
	 */
	public static boolean isUnderWebContentFolder(IResource resource) {
		IPath webContentPath = getWebContentPath(resource.getProject());
		if (webContentPath != null) {
			return webContentPath.isPrefixOf(resource.getFullPath());
		}
		return true;
	}

	/**
	 * @param project
	 * @return full path to web content folder
	 */
	public static IPath getWebContentPath(IProject project) {
		if (project != null) {
			IVirtualComponent component = ComponentCore.createComponent(project);
			if (component != null) {
				IVirtualFolder rootFolder = component.getRootFolder();
				if (rootFolder != null) {
					IContainer underlyingFolder = rootFolder.getUnderlyingFolder();
					if (underlyingFolder != null) {
						return underlyingFolder.getFullPath();
					}
				}
			}
		}
		return null;
	}

	/**
	 * Return the name of the web content folder. i.e, "WebContent"
	 * 
	 * @param project
	 * @return the web content folder name
	 */
	public static String getWebContentFolderName(IProject project) {
		IPath webContentPath = getWebContentPath(project);
		if (webContentPath != null)
			return webContentPath.lastSegment();
		return null;
	}

	/**
	 * Gets the "web content" folder for the given project - this method should no longer be used
	 * since it cannot handle the case where the project root is also the "web content" folder
	 * (use {@link #getWebContentContainer(IProject)} instead).
	 * @param project The IProject instance for which to get the "web content" folder.
	 * @return The "web content" folder for the given project.
	 * @deprecated
	 * @see #getWebContentContainer(IProject)
	 */
	public static IFolder getWebContentFolder(IProject project) {
		IPath webContentPath = getWebContentPath(project);
		IFolder folder = null;
		if (webContentPath != null) {
			folder = project.getFolder(webContentPath.removeFirstSegments(1));
		}
		return folder;
	}

	/**
	 * Gets the primary "web content" container for the given project - this method is considered
	 * preferable to using {@link #getWebContentFolder(IProject)}, since it correctly handles the
	 * case where the project root is also the primary "web content" container.
	 * @param project The IProject instance for which to get the primary "web content" container.
	 * @return The primary "web content" container for the given project.
	 * @see #getWebContentContainers(IProject)
	 */
	public static IContainer getWebContentContainer(IProject project) {
		IContainer container = null;
		IPath webContentPath = getWebContentPath(project);
		if (webContentPath != null) {
			if (webContentPath.segmentCount() > 1) {
				container = project.getFolder(webContentPath.removeFirstSegments(1));
			} else {
				container = project;
			}
		}
		return container;
	}

	/**
	 * Gets all "web content" containers for the given project (there can be multiple mappings to
	 * the same runtime path).
	 * @param project The IProject instance for which to get all "web content" containers.
	 * @return All "web content" containers for the given project, or <code>null</code> if none are
	 * found.
	 * @see #getWebContentContainer(IProject)
	 */
	public static IContainer[] getWebContentContainers(IProject project) {
		IContainer[] containers = null;
		if (project != null) {
			IVirtualComponent component = ComponentCore.createComponent(project);
			if (component != null) {
				IVirtualFolder rootFolder = component.getRootFolder();
				if (rootFolder != null) {
					containers = rootFolder.getUnderlyingFolders();
				}
			}
		}
		return containers;
	}

	/**
	 * return the depth of webcontent folder. For example, if the webcontent
	 * folder path is /projectname/webContent, then return 2, if it's
	 * /projectname/a/webContent, then return 3.
	 * 
	 * @param project
	 * @return the depth of webcontent folder
	 */
	public static int getWebContentFolderDepth(IProject project) {
		if (project != null) {
			IPath webContentPath = getWebContentPath(project);
			if (webContentPath != null) {
				return webContentPath.segmentCount();
			}
		}
		// default to 2
		return 2;
	}

	/**
	 * determine the path of web file is valid or not
	 * 
	 * @param path -
	 *            the path of web file
	 * @return - true - valid web file
	 */
	public static boolean isValidWebFile(IPath path) {
		String[] jspExtensions = getJSPFileExtensions();

		String extension = path.getFileExtension();
		if (extension != null
				&& Arrays.asList(jspExtensions).contains(extension))
		{
				return true;
		}

		return false;
	}

	/**
	 * get the webpath for the project path. The project path is something like
	 * "/projectname/webroot/filename.jsp", or "/projectname/webroot/folder".
	 * The project information should be removed from project path, e.g,
	 * "/filename.jsp" or "/folder/*";
	 * 
	 * @param strPath -
	 *            the project path
	 * @return - web path remove from "/projectname/webroot"
	 * @deprecated use getWebPath(IPath path) instead.
	 */
	public static String getWebPath(String strPath) {
		String strWebrootPath = ""; //$NON-NLS-1$
		if (strPath != null) {
			IPath path = new Path(strPath);
			return getWebPath(path);
		}
		return strWebrootPath;
	}

	/**
	 * @param strWebPath
	 * @return the page name
	 */
	public static String getPageNameFromWebPath(String strWebPath) {
		String pageName = strWebPath;

		if (pageName.startsWith(IFileFolderConstants.PATH_SEPARATOR)) {
			pageName = pageName.substring(1);
		}

		String[] jspExtensions = getJSPFileExtensions();
		for (int i = 0, n = jspExtensions.length; i < n; i++) {
			String extension = IFileFolderConstants.DOT + jspExtensions[i];
			if (pageName.endsWith(extension)) {
			pageName = pageName.substring(0, pageName.length()
						- extension.length());
				break;
		}
		}

		return pageName;
	}
	/**
	 * Get the JSP file extension from Eclipse preference
	 * Windows->Preferences->General->Content Types
	 * 
	 * @return String Array for JSP file extensions
	 */
	public static String[] getJSPFileExtensions() {
		IContentTypeManager typeManager = Platform.getContentTypeManager();
		IContentType jspContentType = typeManager
				.getContentType("org.eclipse.jst.jsf.core.jsfsource"); //$NON-NLS-1$
		if (jspContentType != null) {
			return jspContentType
					.getFileSpecs(IContentType.FILE_EXTENSION_SPEC);
		}
		return null;
	}

	/**
	 * Tests if the passed IProject instance is a valid JSF project in the
	 * following ways:
	 * <ul>
	 * <li>project is not null and is accessible, </li>
	 * <li>project has the "jst.web" facet set on it.</li> 
	 * </ul>
	 * 
	 * @param project
	 *            IProject instance to be tested.
	 * @return true if the IProject instance is a valid JSF project, else false.
	 */
	public static boolean isValidWebProject(IProject project) {
		boolean isValid = false;
		// check for null or inaccessible project
		if (project != null && project.isAccessible()) {
            // TODO: this was jst.jsf before, but we are checking for jst.web
            // the javadoc seems out of sync with the method name
			// check for "jst.web" facet on project
			try {
				IFacetedProject facetedProject = ProjectFacetsManager
						.create(project);
				if (facetedProject != null) {
					Set projectFacets = facetedProject.getProjectFacets();
					Iterator itProjectFacets = projectFacets.iterator();
					while (itProjectFacets.hasNext()) {
						IProjectFacetVersion projectFacetVersion = (IProjectFacetVersion) itProjectFacets
								.next();
						IProjectFacet projectFacet = projectFacetVersion
								.getProjectFacet();
						if ("jst.web".equals(projectFacet.getId())) { //$NON-NLS-1$
							isValid = true;
							break;
						}
					}
				}
			} catch (CoreException ce) {
                JSFUICommonPlugin.getLogger(WebrootUtil.class).error("checking web project", ce); //$NON-NLS-1$
			}
		}
		return isValid;
	}
}
