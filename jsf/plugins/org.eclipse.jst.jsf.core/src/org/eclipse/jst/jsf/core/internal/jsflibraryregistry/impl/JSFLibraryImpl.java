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
package org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryFactory;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFVersion;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.adapter.MaintainDefaultImplementationAdapter;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>JSF Library</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryImpl#getID <em>ID</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryImpl#getJSFVersion <em>JSF Version</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryImpl#isDeployed <em>Deployed</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryImpl#isImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryImpl#getArchiveFiles <em>Archive Files</em>}</li>
 * </ul>
 *
 * @generated
 */
public class JSFLibraryImpl extends EObjectImpl implements JSFLibrary {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * The default value of the '{@link #getID() <em>ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getID()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = ""; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getID() <em>ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getID()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getJSFVersion() <em>JSF Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJSFVersion()
	 * @generated
	 * @ordered
	 */
	protected static final JSFVersion JSF_VERSION_EDEFAULT = JSFVersion.UNKNOWN_LITERAL;

	/**
	 * The cached value of the '{@link #getJSFVersion() <em>JSF Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJSFVersion()
	 * @generated
	 * @ordered
	 */
	protected JSFVersion jsfVersion = JSF_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #isDeployed() <em>Deployed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeployed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DEPLOYED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isDeployed() <em>Deployed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeployed()
	 * @generated
	 * @ordered
	 */
	protected boolean deployed = DEPLOYED_EDEFAULT;

	/**
	 * The default value of the '{@link #isImplementation() <em>Implementation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isImplementation()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IMPLEMENTATION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isImplementation() <em>Implementation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isImplementation()
	 * @generated
	 * @ordered
	 */
	protected boolean implementation = IMPLEMENTATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getArchiveFiles() <em>Archive Files</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArchiveFiles()
	 * @generated
	 * @ordered
	 */
	protected EList archiveFiles;

	/**
	 * <!-- begin-user-doc -->
	 * Enhanced to not only create an instance but also to set an initial ID
	 * (which can be reset later) and to add the
	 * MaintainDefaultImplementationAdapter to the list of adapters.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected JSFLibraryImpl() {
		super();
//		//set initial ID; will be overwritten from XML if already persisted
//		setID(String.valueOf(System.currentTimeMillis()));
		//add adapter to maintain a default implementation
		eAdapters().add(MaintainDefaultImplementationAdapter.getInstance());
	}

	/**
	 * <!-- begin-user-doc -->
	 * @return the static eClass 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return JSFLibraryRegistryPackage.Literals.JSF_LIBRARY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getID() {
		return getName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * @return the name 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param newName 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JSFLibraryRegistryPackage.JSF_LIBRARY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * @return the jsf version 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JSFVersion getJSFVersion() {
		return jsfVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param newJSFVersion 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJSFVersion(JSFVersion newJSFVersion) {
		JSFVersion oldJSFVersion = jsfVersion;
		jsfVersion = newJSFVersion == null ? JSF_VERSION_EDEFAULT : newJSFVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JSFLibraryRegistryPackage.JSF_LIBRARY__JSF_VERSION, oldJSFVersion, jsfVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * @return true if library is to be deployed 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDeployed() {
		return deployed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param newDeployed 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeployed(boolean newDeployed) {
		boolean oldDeployed = deployed;
		deployed = newDeployed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JSFLibraryRegistryPackage.JSF_LIBRARY__DEPLOYED, oldDeployed, deployed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * @return true if implementation 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isImplementation() {
		return implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param newImplementation 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplementation(boolean newImplementation) {
		boolean oldImplementation = implementation;
		implementation = newImplementation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JSFLibraryRegistryPackage.JSF_LIBRARY__IMPLEMENTATION, oldImplementation, implementation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * @return the list of archive files 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getArchiveFiles() {
		if (archiveFiles == null) {
			archiveFiles = new EObjectContainmentWithInverseEList(ArchiveFile.class, this, JSFLibraryRegistryPackage.JSF_LIBRARY__ARCHIVE_FILES, JSFLibraryRegistryPackage.ARCHIVE_FILE__JSF_LIBRARY);
		}
		return archiveFiles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param fullPath 
	 * @return the true if the fullPath contains an archive file 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean containsArchiveFile(String fullPath) {
		boolean contains = false;
		if (fullPath != null) {
			Iterator itArchiveFiles = getArchiveFiles().iterator();
			while (itArchiveFiles.hasNext()) {
				ArchiveFile archiveFile = (ArchiveFile)itArchiveFiles.next();
				if (fullPath.equals(archiveFile.getResolvedSourceLocation())) {
					contains = true;
					break;
				}
			}
		}
		return contains;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @return the working copy 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public JSFLibrary getWorkingCopy() {
		JSFLibrary workingCopyLib = JSFLibraryRegistryFactory.eINSTANCE.createJSFLibrary();
//		workingCopyLib.setID(getID());
		workingCopyLib.setName(getName());
		workingCopyLib.setJSFVersion(getJSFVersion());
		workingCopyLib.setDeployed(isDeployed());
		workingCopyLib.setImplementation(isImplementation());
		Iterator itArchiveFiles = getArchiveFiles().iterator();
		while (itArchiveFiles.hasNext()) {
			ArchiveFile srcArchiveFile = (ArchiveFile)itArchiveFiles.next();
			ArchiveFile destArchiveFile = JSFLibraryRegistryFactory.eINSTANCE.createArchiveFile();
			destArchiveFile.setRelativeToWorkspace(srcArchiveFile.isRelativeToWorkspace());
			destArchiveFile.setSourceLocation(srcArchiveFile.getSourceLocation());
			destArchiveFile.setRelativeDestLocation(srcArchiveFile.getRelativeDestLocation());
			workingCopyLib.getArchiveFiles().add(destArchiveFile);
		}
		return workingCopyLib;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param otherLibrary 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void updateValues(JSFLibrary otherLibrary) {
		if (otherLibrary != null) {
//			setID(otherLibrary.getID());
			setName(otherLibrary.getName());
			setJSFVersion(otherLibrary.getJSFVersion());
			setDeployed(otherLibrary.isDeployed());
			setImplementation(otherLibrary.isImplementation());
			Iterator itArchiveFiles = otherLibrary.getArchiveFiles().iterator();
			getArchiveFiles().clear();
			while (itArchiveFiles.hasNext()) {
				ArchiveFile srcArchiveFile = (ArchiveFile)itArchiveFiles.next();
				ArchiveFile destArchiveFile = JSFLibraryRegistryFactory.eINSTANCE.createArchiveFile();
				destArchiveFile.setRelativeToWorkspace(srcArchiveFile.isRelativeToWorkspace());
				destArchiveFile.setSourceLocation(srcArchiveFile.getSourceLocation());
				destArchiveFile.setRelativeDestLocation(srcArchiveFile.getRelativeDestLocation());
				getArchiveFiles().add(destArchiveFile);
			}
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param baseDestLocation 
	 * @return the base destination location 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean copyTo(String baseDestLocation) {
		boolean allCopied = true;
		Iterator itFiles = getArchiveFiles().iterator();
		while (itFiles.hasNext()) {
			ArchiveFile archiveFile = (ArchiveFile)itFiles.next();
			boolean copied = archiveFile.copyTo(baseDestLocation);
			allCopied = allCopied && copied;
		}
		return allCopied;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getLabel() {
		return getName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JSFLibraryRegistryPackage.JSF_LIBRARY__ARCHIVE_FILES:
				return ((InternalEList)getArchiveFiles()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JSFLibraryRegistryPackage.JSF_LIBRARY__ARCHIVE_FILES:
				return ((InternalEList)getArchiveFiles()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JSFLibraryRegistryPackage.JSF_LIBRARY__ID:
				return getID();
			case JSFLibraryRegistryPackage.JSF_LIBRARY__NAME:
				return getName();
			case JSFLibraryRegistryPackage.JSF_LIBRARY__JSF_VERSION:
				return getJSFVersion();
			case JSFLibraryRegistryPackage.JSF_LIBRARY__DEPLOYED:
				return isDeployed() ? Boolean.TRUE : Boolean.FALSE;
			case JSFLibraryRegistryPackage.JSF_LIBRARY__IMPLEMENTATION:
				return isImplementation() ? Boolean.TRUE : Boolean.FALSE;
			case JSFLibraryRegistryPackage.JSF_LIBRARY__ARCHIVE_FILES:
				return getArchiveFiles();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case JSFLibraryRegistryPackage.JSF_LIBRARY__NAME:
				setName((String)newValue);
				return;
			case JSFLibraryRegistryPackage.JSF_LIBRARY__JSF_VERSION:
				setJSFVersion((JSFVersion)newValue);
				return;
			case JSFLibraryRegistryPackage.JSF_LIBRARY__DEPLOYED:
				setDeployed(((Boolean)newValue).booleanValue());
				return;
			case JSFLibraryRegistryPackage.JSF_LIBRARY__IMPLEMENTATION:
				setImplementation(((Boolean)newValue).booleanValue());
				return;
			case JSFLibraryRegistryPackage.JSF_LIBRARY__ARCHIVE_FILES:
				getArchiveFiles().clear();
				getArchiveFiles().addAll((Collection)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case JSFLibraryRegistryPackage.JSF_LIBRARY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case JSFLibraryRegistryPackage.JSF_LIBRARY__JSF_VERSION:
				setJSFVersion(JSF_VERSION_EDEFAULT);
				return;
			case JSFLibraryRegistryPackage.JSF_LIBRARY__DEPLOYED:
				setDeployed(DEPLOYED_EDEFAULT);
				return;
			case JSFLibraryRegistryPackage.JSF_LIBRARY__IMPLEMENTATION:
				setImplementation(IMPLEMENTATION_EDEFAULT);
				return;
			case JSFLibraryRegistryPackage.JSF_LIBRARY__ARCHIVE_FILES:
				getArchiveFiles().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case JSFLibraryRegistryPackage.JSF_LIBRARY__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case JSFLibraryRegistryPackage.JSF_LIBRARY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case JSFLibraryRegistryPackage.JSF_LIBRARY__JSF_VERSION:
				return jsfVersion != JSF_VERSION_EDEFAULT;
			case JSFLibraryRegistryPackage.JSF_LIBRARY__DEPLOYED:
				return deployed != DEPLOYED_EDEFAULT;
			case JSFLibraryRegistryPackage.JSF_LIBRARY__IMPLEMENTATION:
				return implementation != IMPLEMENTATION_EDEFAULT;
			case JSFLibraryRegistryPackage.JSF_LIBRARY__ARCHIVE_FILES:
				return archiveFiles != null && !archiveFiles.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @return the string representation 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (ID: ");
		result.append(id);
		result.append(", Name: ");
		result.append(name);
		result.append(", JSFVersion: ");
		result.append(jsfVersion);
		result.append(", Deployed: ");
		result.append(deployed);
		result.append(", Implementation: ");
		result.append(implementation);
		result.append(')');
		return result.toString();
	}

} //JSFLibraryImpl
