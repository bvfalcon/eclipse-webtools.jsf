package org.eclipse.jst.jsf.core.internal.project.facet;

public class JakartaFacesFacetInstallDataModelProvider extends JSFFacetInstallDataModelProvider {

	protected String getServletClassName() {
		return JSFUtils30.JAKARTA_FACES_SERVLET_CLASS;
	}

	protected String getServletUrlMapping() {
		return JSFUtils30.JAKARTA_FACES_DEFAULT_URL_MAPPING;
	}
}
