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
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.jsf.core.JSFVersion;

/**
 * JSF Utils instance for JSF 2.3.
 * 
 */
class JSFUtils23 extends JSFUtils22 {

	/**
	 * @param modelProvider
	 */
	protected JSFUtils23(final IModelProvider modelProvider) {
		super(JSFVersion.V2_3, modelProvider);
	}

	/**
	 * @param jsfVersion
	 * @param modelProvider
	 */
	protected JSFUtils23(final JSFVersion jsfVersion, final IModelProvider modelProvider) {
		super(jsfVersion, modelProvider);
		if (jsfVersion.compareTo(JSFVersion.V2_3) < 0) {
			throw new IllegalArgumentException("JSF Version must be at least 2.3"); //$NON-NLS-1$
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
				QUOTE + "http://xmlns.jcp.org/xml/ns/javaee" + QUOTE + //$NON-NLS-1$
				"\n"); //$NON-NLS-1$
		pw.write("    xmlns:xsi=" + //$NON-NLS-1$
				QUOTE + "http://www.w3.org/2001/XMLSchema-instance" + QUOTE + //$NON-NLS-1$
				"\n"); //$NON-NLS-1$
		pw.write("    xsi:schemaLocation=" + //$NON-NLS-1$
				QUOTE
				+ String.format(
						"http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-facesconfig_%s.xsd", //$NON-NLS-1$
						schemaVersionString)
				+ QUOTE + "\n"); //$NON-NLS-1$
		pw.write("    version=" + //$NON-NLS-1$
				QUOTE + getVersion().toString() + QUOTE + ">\n\n"); //$NON-NLS-1$
		pw.write("</faces-config>\n"); //$NON-NLS-1$
	}

    private static final Map<String, String> SEARCH_EXPRESSIONS = new LinkedHashMap<>();
    static {
        SEARCH_EXPRESSIONS.put("@composite", "closest composite component ancestor"); //$NON-NLS-1$ //$NON-NLS-2$
        SEARCH_EXPRESSIONS.put("@namingcontainer", "closest ancestor naming container of current component"); //$NON-NLS-1$ //$NON-NLS-2$
        SEARCH_EXPRESSIONS.put("@parent", "parent of the current component"); //$NON-NLS-1$ //$NON-NLS-2$
        SEARCH_EXPRESSIONS.put("@previous", "previous sibling component"); //$NON-NLS-1$ //$NON-NLS-2$
        SEARCH_EXPRESSIONS.put("@next", "next sibling component"); //$NON-NLS-1$ //$NON-NLS-2$
        SEARCH_EXPRESSIONS.put("@root", "UIViewRoot instance of the view"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Map<String, String> getSearchExpressions() {
        Map<String, String> result = new LinkedHashMap<>();
        result.putAll(super.getSearchExpressions());
        result.putAll(SEARCH_EXPRESSIONS);
        return result;
    }
}
