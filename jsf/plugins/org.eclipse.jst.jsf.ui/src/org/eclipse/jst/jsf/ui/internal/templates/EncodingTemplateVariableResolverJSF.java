/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.templates;

import org.eclipse.jface.text.templates.SimpleTemplateVariableResolver;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.ui.internal.Messages;
import org.eclipse.wst.sse.core.internal.encoding.CommonEncodingPreferenceNames;


public class EncodingTemplateVariableResolverJSF extends SimpleTemplateVariableResolver {
	private static final String ENCODING_TYPE = getEncodingType();

	private static String getEncodingType() {
		return "encoding"; //$NON-NLS-1$
	}

	/**
	 * Creates a new encoding variable
	 */
	public EncodingTemplateVariableResolverJSF() {
		super(ENCODING_TYPE, Messages.Creating_files_encoding);
	}

	protected String resolve(TemplateContext context) {
		return JSFCorePlugin.getDefault().getPluginPreferences().getString(CommonEncodingPreferenceNames.OUTPUT_CODESET);
	}
}
