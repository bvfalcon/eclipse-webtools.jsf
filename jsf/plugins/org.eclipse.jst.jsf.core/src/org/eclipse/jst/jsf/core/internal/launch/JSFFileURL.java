/*******************************************************************************
 * Copyright (c) 2005, 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Gerry Kessler - initial API and implementation
 *******************************************************************************/ 

package org.eclipse.jst.jsf.core.internal.launch;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.internal.web.jfaces.extension.FileURL;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFUtilFactory;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFUtils;

/**
 * Extends the FileURL extension-point so that a JSF JSP page
 * can have it's URL mapped to the Faces Servlet using the servlet-mapping 
 * specified in the web.xml file
 * 
 * @author Gerry Kessler - Oracle
 *
 */
public class JSFFileURL implements FileURL 
{

	/**
	 * If this is a JSP page, this will return a URL using the first valid servlet-mapping to the Faces Servlet
	 * if found.   If the faces servlet is not defined in the web.xml or there is no servlet-mapping, this will return null.
	 * 
	 * The resource's file extension is compared with the jakarta.faces.DEFAULT_SUFFIX context-param if set.   If the same, then 
	 * extension mapping will be used if found in the web.xml.  If not set, "jsp" default is assumed.   If resource does not have the default
	 * file extension, then it must have either: jsp,jspx, or jsf file extensions in order to be considered for launch.
	 * 
	 * This will not guarentee a page to be run.  The user will still need a properly configured server with valid URL mappings and
	 * context-params.
	 * 
	 * TODO: We could detect and insert or just warn for context-params and url mappings to run.  However, this would better detected during a JSF App validation step.
	 * 
	 * @see org.eclipse.jst.j2ee.internal.web.jfaces.extension.FileURL#getFileURL(org.eclipse.core.resources.IResource, org.eclipse.core.runtime.IPath)
	 */
	public IPath getFileURL(IResource resource, IPath existingURL) {
		
		//is this is a Faces Project with a Faces Servlet?
		//we will not check to see if facet is installed.  
		// check to see if this resource is a JSF page (currently always true) and then,
		//if servlet is present, we will change the url based on first mapping found 
		IModelProvider provider = ModelProviderManager.getModelProvider(resource.getProject());
		Object webAppObj = provider.getModelObject();
		final JSFUtils jsfUtil = new JSFUtilFactory().create(resource.getProject());
		if (webAppObj != null && jsfUtil != null){
			//methods below returning the path are identical except for the APIs required
		    return jsfUtil.getFileUrlPath(webAppObj, resource, existingURL);
		}
		return null;
	}
}
