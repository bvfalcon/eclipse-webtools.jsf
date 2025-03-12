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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.jsf.core.internal.Messages;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>JSF Library Registry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry#getDefaultImplementationID <em>Default Implementation ID</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry#getJSFLibraries <em>JSF Libraries</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry#getPluginProvidedJSFLibraries <em>Plugin Provided JSF Libraries</em>}</li>
 * </ul>
 *
 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage#getJSFLibraryRegistry()
 * @model
 * @generated
 */
public interface JSFLibraryRegistry extends EObject{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2005 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Default Implementation ID</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Implementation ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Implementation ID</em>' attribute.
	 * @see #setDefaultImplementationID(String)
	 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage#getJSFLibraryRegistry_DefaultImplementationID()
	 * @model default=""
	 * @generated
	 */
	String getDefaultImplementationID();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry#getDefaultImplementationID <em>Default Implementation ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Implementation ID</em>' attribute.
	 * @see #getDefaultImplementationID()
	 * @generated
	 */
	void setDefaultImplementationID(String value);

	/**
	 *The default implementation message string
	 */
	public static final String DEFAULT_IMPL_LABEL = Messages.JSFLibraryRegistry_DEFAULT_IMPL_LABEL;

	/**
	 * Returns the value of the '<em><b>JSF Libraries</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>JSF Libraries</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>JSF Libraries</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage#getJSFLibraryRegistry_JSFLibraries()
	 * @model type="org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary" containment="true"
	 * @generated
	 */
	EList getJSFLibraries();

	/**
	 * Returns the value of the '<em><b>Plugin Provided JSF Libraries</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.PluginProvidedJSFLibrary}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Plugin Provided JSF Libraries</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plugin Provided JSF Libraries</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage#getJSFLibraryRegistry_PluginProvidedJSFLibraries()
	 * @model type="org.eclipse.jst.jsf.core.internal.jsflibraryregistry.PluginProvidedJSFLibrary" containment="true" transient="true"
	 * @generated
	 */
	EList getPluginProvidedJSFLibraries();

	/**
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Implementation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * @return the default implementation 
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	JSFLibrary getDefaultImplementation();

	/**
	 * <!-- begin-user-doc -->
	 * @param implementation 
	 * <!-- end-user-doc -->
	 * @model implementationRequired="true"
	 * @generated
	 */
	void setDefaultImplementation(JSFLibrary implementation);

	/**
	 * <!-- begin-user-doc -->
	 * @param ID 
	 * @return the jsf library 
	 * <!-- end-user-doc -->
	 * @model required="true" IDRequired="true"
	 * @generated
	 */
	JSFLibrary getJSFLibraryByID(String ID);

	/**
	 * <!-- begin-user-doc -->
	 * @param name 
	 * @return the jsf libraries matching name 
	 * <!-- end-user-doc -->
	 * @model required="true" many="false" nameRequired="true"
	 * @generated
	 */
	EList getJSFLibrariesByName(String name);

	/**
	 * <!-- begin-user-doc -->
	 * @return the implementation JSF libraries 
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true" many="false"
	 * @generated
	 */
	EList getImplJSFLibraries();

	/**
	 * <!-- begin-user-doc -->
	 * @return the non-implementation JSF libraries 
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true" many="false"
	 * @generated
	 */
	EList getNonImplJSFLibraries();

	/**
	 * <!-- begin-user-doc -->
	 * @return all the JSF libraries 
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true" many="false"
	 * @generated
	 */
	EList getAllJSFLibraries();

	/**
	 * <!-- begin-user-doc -->
	 * @param library 
	 * @return true if add succeeds 
	 * <!-- end-user-doc -->
	 * @model required="true" libraryRequired="true"
	 * @generated
	 */
	boolean addJSFLibrary(JSFLibrary library);

	/**
	 * <!-- begin-user-doc -->
	 * @param library 
	 * @return true if remove succeeds 
	 * <!-- end-user-doc -->
	 * @model required="true" libraryRequired="true"
	 * @generated
	 */
	boolean removeJSFLibrary(JSFLibrary library);

} // JSFLibraryRegistry
