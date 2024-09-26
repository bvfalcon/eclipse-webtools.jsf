package org.eclipse.jst.jsf.core.internal.project.facet;

public class JSFFacetJakartaInstallDataModelProvider extends JSFFacetInstallDataModelProvider {

	protected String getServletClassName() {
		return JSFUtils.JSF_SERVLET_CLASS_JAKARTA;
	}

	protected String getServletUrlMapping() {
		return JSFUtils.JSF_DEFAULT_URL_MAPPING_JAKARTA;
	}
}
