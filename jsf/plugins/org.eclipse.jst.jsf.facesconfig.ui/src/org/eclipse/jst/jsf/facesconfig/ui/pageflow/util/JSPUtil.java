/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
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
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * utility class for JSP related information.
 * 
 * @author Yang Liu
 * @deprecated
 * @see JSFUtil
 */
public class JSPUtil {

	/**
	 * get the action list in the jsp file
	 * @param jspFileName 
	 * 
	 * @return - action list
	 */
	public static List getActionListInJSPFile(String jspFileName) {
		/** jsp dom adapter */

        List actions = new ArrayList();

        // convert the relative directory to project directory, e.g., /a.jsp to
        // /testproject/webroot/a.sjp
        String physicalJspPath = jspFileName;
        if (physicalJspPath != null && physicalJspPath.length() > 0)
        {
            IPath jspPath = new Path(physicalJspPath);
            IFile jspFile = ResourcesPlugin.getWorkspace().getRoot().getFile(
                    jspPath);

            if (jspFile != null && jspFile.exists())
            {
                JSPDomAdapter jspAdapter = null;
                try
                {
                    jspAdapter = new JSPDomAdapter();
                    // initialize the adapter to initialize the model of jsp
                    if (jspAdapter.initialize(jspFile))
                    {
                        // the prefix of JSF HTML TagLib
                        String prefix = jspAdapter
                                .getTagLibPrefix(JSPDomAdapter.JSF_HTML_TAGLIB);

                        // get the command butonns
                        List buttonActions = jspAdapter.getElementsByTagNameNS(
                                prefix, "commandButton");//$NON-NLS-1$
                        if (buttonActions != null)
                            actions.addAll(buttonActions);

                        // get the command links
                        List linkActions = jspAdapter.getElementsByTagNameNS(
                                prefix, "commandLink");//$NON-NLS-1$
                        if (linkActions != null)
                            actions.addAll(linkActions);
                    }
                }
                finally
                {
                    if (jspAdapter != null)
                    {
                        jspAdapter.releaseModel();
                    }
                }
            }
        }
        return actions;
	}
}
