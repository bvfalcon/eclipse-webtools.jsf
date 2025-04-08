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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>JSF Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary#getID <em>ID</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary#getJSFVersion <em>JSF Version</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary#isDeployed <em>Deployed</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary#isImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary#getArchiveFiles <em>Archive Files</em>}</li>
 * </ul>
 *
 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage#getJSFLibrary()
 * @model
 * @generated
 */
public interface JSFLibrary extends EObject{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2005 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>ID</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ID</em>' attribute.
	 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage#getJSFLibrary_ID()
	 * @model default="" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	String getID();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage#getJSFLibrary_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>JSF Version</b></em>' attribute.
	 * The default value is <code>"UNKNOWN"</code>.
	 * The literals are from the enumeration {@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFVersion}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>JSF Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>JSF Version</em>' attribute.
	 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFVersion
	 * @see #setJSFVersion(JSFVersion)
	 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage#getJSFLibrary_JSFVersion()
	 * @model default="UNKNOWN" required="true"
	 * @generated
	 */
	JSFVersion getJSFVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary#getJSFVersion <em>JSF Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>JSF Version</em>' attribute.
	 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFVersion
	 * @see #getJSFVersion()
	 * @generated
	 */
	void setJSFVersion(JSFVersion value);

	/**
	 * Returns the value of the '<em><b>Deployed</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deployed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deployed</em>' attribute.
	 * @see #setDeployed(boolean)
	 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage#getJSFLibrary_Deployed()
	 * @model default="true" required="true"
	 * @generated
	 */
	boolean isDeployed();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary#isDeployed <em>Deployed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deployed</em>' attribute.
	 * @see #isDeployed()
	 * @generated
	 */
	void setDeployed(boolean value);

	/**
	 * Returns the value of the '<em><b>Implementation</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implementation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implementation</em>' attribute.
	 * @see #setImplementation(boolean)
	 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage#getJSFLibrary_Implementation()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isImplementation();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary#isImplementation <em>Implementation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation</em>' attribute.
	 * @see #isImplementation()
	 * @generated
	 */
	void setImplementation(boolean value);

	/**
	 * Returns the value of the '<em><b>Archive Files</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile#getJSFLibrary <em>JSF Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Archive Files</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Archive Files</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage#getJSFLibrary_ArchiveFiles()
	 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile#getJSFLibrary
	 * @model type="org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile" opposite="JSFLibrary" containment="true"
	 * @generated
	 */
	EList getArchiveFiles();

	/**
	 * <!-- begin-user-doc -->
	 * @param fullPath 
	 * @return true if fullPath contains archive file 
	 * <!-- end-user-doc -->
	 * @model required="true" fullPathRequired="true"
	 * @generated
	 */
	boolean containsArchiveFile(String fullPath);

	/**
	 * <!-- begin-user-doc -->
	 * @return the working copy 
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	JSFLibrary getWorkingCopy();

	/**
	 * <!-- begin-user-doc -->
	 * @param otherLibrary 
	 * <!-- end-user-doc -->
	 * @model otherLibraryRequired="true"
	 * @generated
	 */
	void updateValues(JSFLibrary otherLibrary);

	/**
	 * <!-- begin-user-doc -->
	 * @param baseDestLocation 
	 * @return true if copy succeeds 
	 * <!-- end-user-doc -->
	 * @model required="true" baseDestLocationRequired="true"
	 * @generated
	 */
	boolean copyTo(String baseDestLocation);

	/**
	 * <!-- begin-user-doc -->
	 * @return label
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	String getLabel();

} // JSFLibrary
