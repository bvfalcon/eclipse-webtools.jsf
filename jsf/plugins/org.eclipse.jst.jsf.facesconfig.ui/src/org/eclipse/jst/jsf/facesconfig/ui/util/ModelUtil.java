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
package org.eclipse.jst.jsf.facesconfig.ui.util;

import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.ui.IFacesConfigConstants;

/**
 * @author Zhi-peng Zhang
 * @version
 */
public class ModelUtil {

	private static final String ENTITY_NEWLINE = "&#xA;"; //$NON-NLS-1$
	private static final String ENTITY_TAB = "&#x9;"; //$NON-NLS-1$
	private static final String ENTITY_CARRIAGE_RETURN = "&#xD;"; //$NON-NLS-1$
	private static final String ENTITY_AMPERSAND = "&amp;"; //$NON-NLS-1$
	private static final String ENTITY_DOUBLE_QUOTE = "&quot;"; //$NON-NLS-1$
	private static final String ENTITY_SINGLE_QUOTE = "&apos;"; //$NON-NLS-1$
	private static final String ENTITY_GREATER_THAN = "&gt;"; //$NON-NLS-1$
	private static final String ENTITY_LESS_THAN = "&lt;"; //$NON-NLS-1$

	/**
	 * Get the super class's name of certain element. For example, a class for
	 * <action-listener> should implement interface
	 * "jakarta.faces.event.ActionListener".
	 * 
	 * 
	 * 
	 * @param key
	 *            the EClass object of the application child.
	 * @return the expected super type for a particular element
	 * TODO: could make common with the faces-config validator
	 */
	public static String getSuperType(Object key) {
		if (key == FacesConfigPackage.eINSTANCE.getActionListenerType()) {
			return IFacesConfigConstants.ACTION_LISTENER_INTERFACE;
		}
		if (key == FacesConfigPackage.eINSTANCE.getNavigationHandlerType()) {
			return IFacesConfigConstants.NAVIGATION_HANDLE_SUPER_CLASS;
		}
		if (key == FacesConfigPackage.eINSTANCE.getPropertyResolverType()) {
			return IFacesConfigConstants.PROPERTY_RESOLVER_SUPER_CLASS;
		}
		if (key == FacesConfigPackage.eINSTANCE.getStateManagerType()) {
			return IFacesConfigConstants.STATE_MANAGER_SUPER_CLASS;
		}
		if (key == FacesConfigPackage.eINSTANCE.getVariableResolverType()) {
			return IFacesConfigConstants.VARIABLE_RESOLVER_SUPER_CLASS;
		}
		if (key == FacesConfigPackage.eINSTANCE.getViewHandlerType()) {
			return IFacesConfigConstants.VIEW_HANDLER_SUPER_CLASS;
		}

		if (key == FacesConfigPackage.eINSTANCE.getApplicationFactoryType()) {
			return IFacesConfigConstants.APPLICATION_FACTORY_SUPER_CLASS;
		}
		if (key == FacesConfigPackage.eINSTANCE.getFacesContextFactoryType()) {
			return IFacesConfigConstants.FACES_CONTEXT_FACTORY_SUPER_CLASS;
		}
		if (key == FacesConfigPackage.eINSTANCE.getLifecycleFactoryType()) {
			return IFacesConfigConstants.LIFECYCLE_FACTORY_SUPER_CLASS;
		}
		if (key == FacesConfigPackage.eINSTANCE.getRenderKitFactoryType()) {
			return IFacesConfigConstants.RENDER_KIT_FACTORY_SUPER_CLASS;
		}

		if (key == FacesConfigPackage.eINSTANCE.getPhaseListenerType()) {
			return IFacesConfigConstants.PHASE_LISTENER_INTERFACE;
		}

		return null;
	}
	

	/**
	 * This will take the three pre-defined entities in XML 1.0 (used
	 * specifically in XML elements) and convert their character representation
	 * to the appropriate entity reference, suitable for XML element content.
	 * 
	 * @param str
	 *            <code>String</code> input to escape.
	 * @return <code>String</code> with escaped content.
	 */
	public static String escapeEntities(String str) {
		if (isEmptyString(str)) {
			return ""; //$NON-NLS-1$
		}

		StringBuffer buffer;
		char ch;
		String entity;

		buffer = null;
		for (int i = 0, n = str.length(); i < n; i++) {
			ch = str.charAt(i);
			switch (ch) {
			case '<':
				entity = ENTITY_LESS_THAN;
				break;
			case '>':
				entity = ENTITY_GREATER_THAN;
				break;
			case '\'':
				entity = ENTITY_SINGLE_QUOTE;
				break;
			case '\"':
				entity = ENTITY_DOUBLE_QUOTE;
				break;
			case '&':
				entity = ENTITY_AMPERSAND;
				break;
			case '\r':
				entity = ENTITY_CARRIAGE_RETURN;
				break;
			case '\t':
				entity = ENTITY_TAB;
				break;
			case '\n':
				entity = ENTITY_NEWLINE;
				break;
			default:
				entity = null;
				break;
			}
			if (buffer == null) {
				if (entity != null) {
					// An entity occurred, so we'll have to use StringBuffer
					// (allocate room for it plus a few more entities).
					buffer = new StringBuffer(str.length() + 20);
					// Copy previous skipped characters and fall through
					// to pickup current character
					buffer.append(str.substring(0, i));
					buffer.append(entity);
				}
			} else {
				if (entity == null) {
					buffer.append(ch);
				} else {
					buffer.append(entity);
				}
			}
		}

		// If there were any entities, return the escaped characters
		// that we put in the StringBuffer. Otherwise, just return
		// the unmodified input string.
		return (buffer == null) ? str : buffer.toString();
	}

	/**
	 * @param str
	 * @return removes XML/HTML escaped characters from str.
	 */
	public static String unEscapeEntities(String str) {
		if (isEmptyString(str)) {
			return ""; //$NON-NLS-1$
		}

		String result = new String(str);

		result = result.replaceAll(ENTITY_AMPERSAND, "&"); //$NON-NLS-1$
		result = result.replaceAll(ENTITY_LESS_THAN, "<"); //$NON-NLS-1$
		result = result.replaceAll(ENTITY_GREATER_THAN, ">"); //$NON-NLS-1$
		result = result.replaceAll(ENTITY_SINGLE_QUOTE, "\'"); //$NON-NLS-1$
		result = result.replaceAll(ENTITY_DOUBLE_QUOTE, "\""); //$NON-NLS-1$
		result = result.replaceAll(ENTITY_CARRIAGE_RETURN, "\r"); //$NON-NLS-1$
		result = result.replaceAll(ENTITY_TAB, "\t"); //$NON-NLS-1$
		result = result.replaceAll(ENTITY_NEWLINE, "\n"); //$NON-NLS-1$

		return result;
	}

	private static boolean isEmptyString(String value) {
		if (value == null || value.length() == 0) {
			return true;
		}
		return false;
	}
}
