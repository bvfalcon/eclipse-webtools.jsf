/*******************************************************************************
 * Copyright (c) 2018, 2019 IBM Corporation and others.
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
package org.eclipse.jst.jsf.core.internal.project.facet;

import java.io.PrintWriter;

import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * JSF Utils instance for JSF 3.0.
 * 
 */
class JSFUtils30 extends JSFUtils23 {

	/**
	 * The default name of the Faces servlet class for Jakarta EE
	 */
	public static final String JAKARTA_FACES_SERVLET_CLASS = "jakarta.faces.webapp.FacesServlet"; //$NON-NLS-1$
	/**
	 * Default URL mapping to faces servlet (for Jakarta EE)
	 */
	public static final String JAKARTA_FACES_DEFAULT_URL_MAPPING = "*.xhtml"; //$NON-NLS-1$
	/**
	 * The name of the context parameter used for JSF configuration files (for Jakarta EE)
	 */
	public static final String JAKARTA_FACES_CONFIG_CONTEXT_PARAM = "jakarta.faces.CONFIG_FILES"; //$NON-NLS-1$
	/**
	 * The name of the context parameter used for defining the default JSF file extension (for Jakarta EE)
	 */
	public static final String JAKARTA_FACES_DEFAULT_SUFFIX_CONTEXT_PARAM = "jakarta.faces.DEFAULT_SUFFIX"; //$NON-NLS-1$
	/**
	 * @param modelProvider
	 */
	protected JSFUtils30(final IModelProvider modelProvider) {
		super(JSFVersion.V3_0, modelProvider);
	}

	/**
	 * @param jsfVersion
	 * @param modelProvider
	 */
	protected JSFUtils30(final JSFVersion jsfVersion, final IModelProvider modelProvider) {
		super(jsfVersion, modelProvider);
		if (jsfVersion.compareTo(JSFVersion.V3_0) < 0) {
			throw new IllegalArgumentException("JSF Version must be at least 3.0"); //$NON-NLS-1$
		}
	}

	@Override
	protected String getServletClassname(IDataModel config) {
		String className = config.getStringProperty(IJSFFacetInstallDataModelProperties.SERVLET_CLASSNAME);
		if (className == null || className.trim().equals("")) //$NON-NLS-1$
			className = JAKARTA_FACES_SERVLET_CLASS;
		return className.trim();
	}

    protected void removeJSFContextParams(final Object webApp) {
    	if (isJavaEE(webApp)) {
    		JEEUtils.removeContextParam((org.eclipse.jst.javaee.web.WebApp) webApp, JAKARTA_FACES_CONFIG_CONTEXT_PARAM);
    	} else {
    		J2EEUtils.removeContextParam((org.eclipse.jst.j2ee.webapplication.WebApp) webApp, JSF_CONFIG_CONTEXT_PARAM);
    	}
    }

    /**
     * @return default url mapping
     * */
    protected String getDefaultUrlMapping() {
        return JAKARTA_FACES_DEFAULT_URL_MAPPING;
    }

    /**
     * @param webApp
     * @return the default file extension from the context param. Default is
     *         "xhtml" if no context param.
     */
    protected String getDefaultSuffix(Object webApp) {
    	String contextParam = null;
    	if (webApp != null) {
	    	if (isJavaEE(webApp)) {
	    		contextParam = JEEUtils.getContextParam((org.eclipse.jst.javaee.web.WebApp) webApp, JAKARTA_FACES_DEFAULT_SUFFIX_CONTEXT_PARAM);
	    	} else {
	    		contextParam = J2EEUtils.getContextParam((org.eclipse.jst.j2ee.webapplication.WebApp) webApp, JSF_DEFAULT_SUFFIX_CONTEXT_PARAM);
	    	}
    	}
    	if (contextParam == null) {
    		return getDefaultDefaultSuffix();
    	}
   		return normalizeSuffix(contextParam);
    }

    /**
	 * Finds and returns a JSF Servlet definition, or null if servlet is not defined.
	 * 
	 * @param webApp
	 * @return Servlet or null
	 */    
    protected Object findJSFServlet(Object webApp) {
        if(isJavaEE(webApp)) {
            return JEEUtils.findServlet((org.eclipse.jst.javaee.web.WebApp) webApp, JAKARTA_FACES_SERVLET_CLASS);
        }
   	    return J2EEUtils.findServlet((org.eclipse.jst.j2ee.webapplication.WebApp) webApp, JSF_SERVLET_CLASS);
    }

    /**
	 * Creates or updates context-params
	 * @param webApp
	 * @param config
	 */
	protected void setupContextParams(final Object webApp, final IDataModel config) {
        final String paramValue = config.getStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH);
        if (paramValue != null && !paramValue.equals(JSF_DEFAULT_CONFIG_PATH)) {
            if (isJavaEE(webApp)) {
                JEEUtils.setupContextParam((org.eclipse.jst.javaee.web.WebApp) webApp, JAKARTA_FACES_CONFIG_CONTEXT_PARAM, paramValue);
            } else {
                J2EEUtils.setupContextParam((org.eclipse.jst.j2ee.webapplication.WebApp) webApp, JSF_CONFIG_CONTEXT_PARAM, paramValue);
            }
        }
	}

	@Override
	public void doVersionSpecificConfigFile(PrintWriter pw) {
		final String QUOTE = new String(new char[] { '"' });
		final String schemaVersionString = getVersion().toString().replaceAll("\\.", "_"); //$NON-NLS-1$//$NON-NLS-2$
		pw.write("<?xml version=" + //$NON-NLS-1$
				QUOTE + "1.0" + QUOTE + //$NON-NLS-1$
				" encoding=" + //$NON-NLS-1$
				QUOTE + "UTF-8" + QUOTE + //$NON-NLS-1$
				"?>\n"); //$NON-NLS-1$
		pw.write("<faces-config\n"); //$NON-NLS-1$
		pw.write("    xmlns=" + //$NON-NLS-1$
				QUOTE + "https://jakarta.ee/xml/ns/jakartaee" + QUOTE + //$NON-NLS-1$
				"\n"); //$NON-NLS-1$
		pw.write("    xmlns:xsi=" + //$NON-NLS-1$
				QUOTE + "http://www.w3.org/2001/XMLSchema-instance" + QUOTE + //$NON-NLS-1$
				"\n"); //$NON-NLS-1$
		pw.write("    xsi:schemaLocation=" + //$NON-NLS-1$
				QUOTE
				+ String.format(
						"https://jakarta.ee/xml/ns/jakartaee https://jakarta.ee/xml/ns/jakartaee/web-facesconfig_%s.xsd", //$NON-NLS-1$
						schemaVersionString)
				+ QUOTE + "\n"); //$NON-NLS-1$
		pw.write("    version=" + //$NON-NLS-1$
				QUOTE + getVersion().toString() + QUOTE + ">\n\n"); //$NON-NLS-1$
		pw.write("</faces-config>\n"); //$NON-NLS-1$
	}
}
