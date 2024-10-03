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
 * Utility file for JSF v1.2 model
 * 
 * @author Gerry Kessler - Oracle
 */
/*package: use JSFUtilFactory*/ class JSFUtils12 extends JSFUtils {

	/**
	 * @param modelProvider 
	 * 
	 */
	protected JSFUtils12(final IModelProvider modelProvider)
    {
        this(JSFVersion.V1_2, modelProvider);
    }

    /**
     * @param jsfVersion
     * @param modelProvider 
     */
    protected JSFUtils12(final JSFVersion jsfVersion, final IModelProvider modelProvider)
    {
        super(jsfVersion, modelProvider);
        if (jsfVersion.compareTo(JSFVersion.V1_2) < 0)
        {
            throw new IllegalArgumentException(
                    "JsfVersion must be at least 1.2"); //$NON-NLS-1$
        }
    }

    @Override
    public void doVersionSpecificConfigFile(PrintWriter pw)
    {
        final String QUOTE = new String(new char[]
        { '"' });
        final String schemaVersionString = getVersion().toString().replaceAll("\\.", "_");  //$NON-NLS-1$//$NON-NLS-2$
        pw.write("<?xml version=" + QUOTE + "1.0" + QUOTE + " encoding=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + QUOTE + "UTF-8" + QUOTE + "?>\n"); //$NON-NLS-1$ //$NON-NLS-2$
        pw.write("<faces-config\n"); //$NON-NLS-1$
        pw.write("    " + "xmlns=" + QUOTE //$NON-NLS-1$ //$NON-NLS-2$
                + "http://java.sun.com/xml/ns/javaee" + QUOTE + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
        pw.write("    " + "xmlns:xsi=" + QUOTE //$NON-NLS-1$ //$NON-NLS-2$
                + "http://www.w3.org/2001/XMLSchema-instance" + QUOTE //$NON-NLS-1$
                + "\n"); //$NON-NLS-1$
        pw.write("    " //$NON-NLS-1$
                + "xsi:schemaLocation=" //$NON-NLS-1$
                + QUOTE
                + String.format("http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-facesconfig_%s.xsd", schemaVersionString) //$NON-NLS-1$
                + QUOTE + "\n"); //$NON-NLS-1$
        pw.write("    " + "version=" + QUOTE + getVersion().toString() + QUOTE + ">\n\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        pw.write("</faces-config>\n"); //$NON-NLS-1$
    }
	
    @Override
    public void updateWebApp(Object webApp, IDataModel config)
    {
        // create or update servlet ref
        Object servlet = findJSFServlet(webApp);// check to see
                                                            // if already
        
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
