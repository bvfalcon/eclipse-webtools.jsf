/*******************************************************************************
 * Copyright (c) 2005, 2008 IBM Corporation and others.
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

/**
 * */
public class TemplateContextTypeIdsJSF {
	public static final String ALL = getAll();

	public static final String ATTRIBUTE = getAttribute();

	public static final String ATTRIBUTE_VALUE = getAttributeValue();

	public static final String NEW = getNew();

	private static String getAll() {
		return getPrefix() + "_all"; //$NON-NLS-1$
	}

	private static String getAttribute() {
		return getPrefix() + "_attribute"; //$NON-NLS-1$
	}

	private static String getAttributeValue() {
		return getPrefix() + "_attribute_value"; //$NON-NLS-1$
	}

	private static String getNew() {
		return getPrefix() + "_new"; //$NON-NLS-1$
	}

	private static String getPrefix() {
		return "jsf"; //$NON-NLS-1$
	}
}
