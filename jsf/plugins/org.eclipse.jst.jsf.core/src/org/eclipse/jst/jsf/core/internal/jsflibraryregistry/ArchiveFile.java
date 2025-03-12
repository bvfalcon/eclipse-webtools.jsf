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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Archive File</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile#isRelativeToWorkspace <em>Relative To Workspace</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile#getSourceLocation <em>Source Location</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile#getRelativeDestLocation <em>Relative Dest Location</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile#getJSFLibrary <em>JSF Library</em>}</li>
 * </ul>
 *
 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage#getArchiveFile()
 * @model
 * @generated
 */
public interface ArchiveFile extends EObject{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2005 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Source Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Location</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Location</em>' attribute.
	 * @see #setSourceLocation(String)
	 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage#getArchiveFile_SourceLocation()
	 * @model required="true"
	 * @generated
	 */
	String getSourceLocation();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile#getSourceLocation <em>Source Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * If the value passed is found to be relative to the workspace, a
	 * workspace-relative location is stored; to prevent this behaviour, call
	 * isRelativeToWorkspace(false) before calling this method.
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Location</em>' attribute.
	 * @see #getSourceLocation()
	 * @generated
	 */
	void setSourceLocation(String value);

	/**
	 * Returns the value of the '<em><b>Relative To Workspace</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relative To Workspace</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relative To Workspace</em>' attribute.
	 * @see #setRelativeToWorkspace(boolean)
	 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage#getArchiveFile_RelativeToWorkspace()
	 * @model default="true" required="true"
	 * @generated
	 */
	boolean isRelativeToWorkspace();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile#isRelativeToWorkspace <em>Relative To Workspace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Relative To Workspace</em>' attribute.
	 * @see #isRelativeToWorkspace()
	 * @generated
	 */
	void setRelativeToWorkspace(boolean value);

	/**
	 * Returns the value of the '<em><b>Relative Dest Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relative Dest Location</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relative Dest Location</em>' attribute.
	 * @see #setRelativeDestLocation(String)
	 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage#getArchiveFile_RelativeDestLocation()
	 * @model required="true"
	 * @generated
	 */
	String getRelativeDestLocation();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile#getRelativeDestLocation <em>Relative Dest Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Relative Dest Location</em>' attribute.
	 * @see #getRelativeDestLocation()
	 * @generated
	 */
	void setRelativeDestLocation(String value);

	/**
	 * Returns the value of the '<em><b>JSF Library</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary#getArchiveFiles <em>Archive Files</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>JSF Library</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>JSF Library</em>' container reference.
	 * @see #setJSFLibrary(JSFLibrary)
	 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage#getArchiveFile_JSFLibrary()
	 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary#getArchiveFiles
	 * @model opposite="ArchiveFiles" required="true" transient="false"
	 * @generated
	 */
	JSFLibrary getJSFLibrary();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile#getJSFLibrary <em>JSF Library</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>JSF Library</em>' container reference.
	 * @see #getJSFLibrary()
	 * @generated
	 */
	void setJSFLibrary(JSFLibrary value);

	/**
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * @return the path
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	String getPath();

	/**
	 * <!-- begin-user-doc -->
	 * @return the name 
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	String getName();

	/**
	 * <!-- begin-user-doc -->
	 * @return true if exists 
	 * <!-- end-user-doc -->
	 * @model required="true"
	 * @generated
	 */
	boolean exists();

	/**
	 * <!-- begin-user-doc -->
	 * @param object 
	 * @return true if equal 
	 * <!-- end-user-doc -->
	 * @model required="true" objectRequired="true"
	 * @generated
	 */
	boolean equals(Object object);

	/**
	 * <!-- begin-user-doc -->
	 * @return the hashCode 
	 * <!-- end-user-doc -->
	 * @model required="true"
	 * @generated
	 */
	int hashCode();

	/**
	 * <!-- begin-user-doc -->
	 * @param baseDestLocation 
	 * @return true if copied succeeds 
	 * <!-- end-user-doc -->
	 * @model required="true" baseDestLocationRequired="true"
	 * @generated
	 */
	boolean copyTo(String baseDestLocation);

	/**
	 * <!-- begin-user-doc -->
	 * @return the resolved source location 
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	String getResolvedSourceLocation();

} // ArchiveFile
