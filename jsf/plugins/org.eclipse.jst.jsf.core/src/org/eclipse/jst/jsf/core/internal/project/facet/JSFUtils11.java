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

package org.eclipse.jst.jsf.core.internal.project.facet;

import java.io.PrintWriter;
import java.util.List;

import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * Utility file for JSF model v1.1
 * 
 * @author Gerry Kessler - Oracle
 */
/*package use JSFUtilFactory*/ class JSFUtils11 extends JSFUtils {	
	
	/**
	 * @param modelProvider 
	 */
	protected JSFUtils11(final IModelProvider modelProvider)
    {
        super(JSFVersion.V1_1, modelProvider);
    }


    /**
     * Creates a stubbed JSF configuration file for specified JSF version and
     * path
     */
    @Override
    public void doVersionSpecificConfigFile(final PrintWriter pw)
    {
        final String QUOTE = new String(new char[]
        { '"' });
        pw.write("<?xml version=" + QUOTE + "1.0" + QUOTE + " encoding=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + QUOTE + "UTF-8" + QUOTE + "?>\n"); //$NON-NLS-1$ //$NON-NLS-2$

        pw.write("<!DOCTYPE faces-config PUBLIC\n"); //$NON-NLS-1$
        pw.write("    " //$NON-NLS-1$
                + QUOTE
                + "-//Sun Microsystems, Inc.//DTD JavaServer Faces Config 1.1//EN" //$NON-NLS-1$
                + QUOTE + "\n"); //$NON-NLS-1$
        pw.write("    " + QUOTE //$NON-NLS-1$
                + "http://java.sun.com/dtd/web-facesconfig_1_1.dtd" //$NON-NLS-1$
                + QUOTE + ">\n"); //$NON-NLS-1$

        pw.write("<faces-config>\n\n"); //$NON-NLS-1$
        pw.write("</faces-config>\n"); //$NON-NLS-1$
    }

    @Override
    public void updateWebApp(Object webApp, IDataModel config)
    {
        // create or update servlet ref
        Object servlet = findJSFServlet(webApp);// check to see
                                                            // if already
                                                            // present
        
        
        servlet = createOrUpdateServletRef(webApp, config, servlet);

        // init mappings
        final List listOfMappings = getServletMappings(config);
        setUpURLMappings(webApp, listOfMappings, servlet);

        // setup context params
        setupContextParams(webApp, config);
    }

    @Override
    public void rollbackWebApp(Object webApp)
    {
        Object servlet = findJSFServlet(webApp);
        if (servlet == null)
        {
            return;
        }
        // remove faces url mappings
        removeURLMappings(webApp, servlet);
        // remove context params
        removeJSFContextParams(webApp);
        // remove servlet
        removeJSFServlet(webApp, servlet);
    }

 
}
