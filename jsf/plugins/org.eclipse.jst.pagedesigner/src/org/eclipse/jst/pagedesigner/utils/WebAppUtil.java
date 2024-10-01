/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
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
package org.eclipse.jst.pagedesigner.utils;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.j2ee.internal.web.deployables.WebDeployableArtifactUtil;
import org.eclipse.jst.jsf.common.ui.IFileFolderConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;

/**
 * @author mengbo
 * @version 1.5
 */
public class WebAppUtil {
	private final static String FACES_SERVLET_NAME = "jakarta.faces.webapp.FacesServlet"; //$NON-NLS-1$

	/**
	 * @param url
	 * @param openedFile
	 * @return the transformed url
	 */
	public static String transformJSPURL(String url, IFile openedFile) {
		boolean canSupportJSF = JSFUtil.supportTaglib(
				ITLDConstants.URI_JSF_HTML, openedFile);
		if (canSupportJSF
				&& url != null
				&& url.endsWith(IFileFolderConstants.DOT
						+ IFileFolderConstants.EXT_JSF)) {
			String urlPattern = ""; //$NON-NLS-1$
			IVirtualResource[] resources = ComponentCore
					.createResources(openedFile);
			IVirtualComponent component = null;
			if (resources[0] != null) {
				component = resources[0].getComponent();
			}
			if (component != null) {
				urlPattern = WebDeployableArtifactUtil.getServletMapping(
						openedFile.getProject(), true, FACES_SERVLET_NAME,
						component.getName());
			}
			if (urlPattern.lastIndexOf(IFileFolderConstants.DOT) != -1) {
				String extension = urlPattern.substring(urlPattern
						.lastIndexOf(IFileFolderConstants.DOT));
				url = url.substring(0, url.lastIndexOf(IFileFolderConstants.DOT
						+ IFileFolderConstants.EXT_JSF))
						+ extension;
			}
		}
		return url;
	}

}
