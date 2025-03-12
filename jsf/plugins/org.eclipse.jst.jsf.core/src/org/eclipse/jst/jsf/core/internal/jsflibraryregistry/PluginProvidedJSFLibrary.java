/*******************************************************************************
 * Copyright (c) 2005, 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.internal.jsflibraryregistry;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Plugin Provided JSF Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.PluginProvidedJSFLibrary#getPluginID <em>Plugin ID</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.PluginProvidedJSFLibrary#getLabel <em>Label</em>}</li>
 * </ul>
 *
 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage#getPluginProvidedJSFLibrary()
 * @model
 * @generated
 */
public interface PluginProvidedJSFLibrary extends JSFLibrary{
	/**
	 * Separator between plugin id and JSF Library name
	 */
	public static final String ID_SEPARATOR = "$$"; //$NON-NLS-1$
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2005 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Plugin ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Plugin ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plugin ID</em>' attribute.
	 * @see #setPluginID(String)
	 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage#getPluginProvidedJSFLibrary_PluginID()
	 * @model required="true"
	 * @generated
	 */
	String getPluginID();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.PluginProvidedJSFLibrary#getPluginID <em>Plugin ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Plugin ID</em>' attribute.
	 * @see #getPluginID()
	 * @generated
	 */
	void setPluginID(String value);

	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage#getPluginProvidedJSFLibrary_Label()
	 * @model required="true"
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.PluginProvidedJSFLibrary#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

} // PluginProvidedJSFLibrary
