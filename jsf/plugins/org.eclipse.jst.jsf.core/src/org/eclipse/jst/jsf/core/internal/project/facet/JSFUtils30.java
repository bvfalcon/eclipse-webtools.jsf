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

/**
 * JSF Utils instance for JSF 3.0.
 * 
 */
class JSFUtils30 extends JSFUtils23 {

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
