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
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.PluginProvidedJSFLibrary;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>JSF Library Registry</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl#getDefaultImplementationID <em>Default Implementation ID</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl#getJSFLibraries <em>JSF Libraries</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl#getPluginProvidedJSFLibraries <em>Plugin Provided JSF Libraries</em>}</li>
 * </ul>
 *
 * @generated
 */
public class JSFLibraryRegistryImpl extends EObjectImpl implements JSFLibraryRegistry {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * The default value of the '{@link #getDefaultImplementationID() <em>Default Implementation ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultImplementationID()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_IMPLEMENTATION_ID_EDEFAULT = ""; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getDefaultImplementationID() <em>Default Implementation ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultImplementationID()
	 * @generated
	 * @ordered
	 */
	protected String defaultImplementationID = DEFAULT_IMPLEMENTATION_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getJSFLibraries() <em>JSF Libraries</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJSFLibraries()
	 * @generated
	 * @ordered
	 */
	protected EList jsfLibraries;

	/**
	 * The cached value of the '{@link #getPluginProvidedJSFLibraries() <em>Plugin Provided JSF Libraries</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPluginProvidedJSFLibraries()
	 * @generated
	 * @ordered
	 */
	protected EList pluginProvidedJSFLibraries;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JSFLibraryRegistryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * @return the static eClass 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return JSFLibraryRegistryPackage.Literals.JSF_LIBRARY_REGISTRY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @return the default implementation id 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefaultImplementationID() {
		return defaultImplementationID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param newDefaultImplementationID 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultImplementationID(String newDefaultImplementationID) {
		String oldDefaultImplementationID = defaultImplementationID;
		defaultImplementationID = newDefaultImplementationID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JSFLibraryRegistryPackage.JSF_LIBRARY_REGISTRY__DEFAULT_IMPLEMENTATION_ID, oldDefaultImplementationID, defaultImplementationID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * @return the list of jsf libraries
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getJSFLibraries() {
		if (jsfLibraries == null) {
			jsfLibraries = new EObjectContainmentEList(JSFLibrary.class, this, JSFLibraryRegistryPackage.JSF_LIBRARY_REGISTRY__JSF_LIBRARIES);
		}
		return jsfLibraries;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @return the list of plugin provided JSF libraries 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPluginProvidedJSFLibraries() {
		if (pluginProvidedJSFLibraries == null) {
			pluginProvidedJSFLibraries = new EObjectContainmentEList(PluginProvidedJSFLibrary.class, this, JSFLibraryRegistryPackage.JSF_LIBRARY_REGISTRY__PLUGIN_PROVIDED_JSF_LIBRARIES);
		}
		return pluginProvidedJSFLibraries;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @return the default implemention JSF library 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public JSFLibrary getDefaultImplementation() {
		return getJSFLibraryByID(getDefaultImplementationID());
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param implementation 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setDefaultImplementation(JSFLibrary implementation) {
		if (implementation != null) {
			setDefaultImplementationID(implementation.getID());
		} else {
			setDefaultImplementationID(null);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JSFLibraryRegistryPackage.JSF_LIBRARY_REGISTRY__JSF_LIBRARIES:
				return ((InternalEList)getJSFLibraries()).basicRemove(otherEnd, msgs);
			case JSFLibraryRegistryPackage.JSF_LIBRARY_REGISTRY__PLUGIN_PROVIDED_JSF_LIBRARIES:
				return ((InternalEList)getPluginProvidedJSFLibraries()).basicRemove(otherEnd, msgs);
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
			case JSFLibraryRegistryPackage.JSF_LIBRARY_REGISTRY__DEFAULT_IMPLEMENTATION_ID:
				return getDefaultImplementationID();
			case JSFLibraryRegistryPackage.JSF_LIBRARY_REGISTRY__JSF_LIBRARIES:
				return getJSFLibraries();
			case JSFLibraryRegistryPackage.JSF_LIBRARY_REGISTRY__PLUGIN_PROVIDED_JSF_LIBRARIES:
				return getPluginProvidedJSFLibraries();
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
			case JSFLibraryRegistryPackage.JSF_LIBRARY_REGISTRY__DEFAULT_IMPLEMENTATION_ID:
				setDefaultImplementationID((String)newValue);
				return;
			case JSFLibraryRegistryPackage.JSF_LIBRARY_REGISTRY__JSF_LIBRARIES:
				getJSFLibraries().clear();
				getJSFLibraries().addAll((Collection)newValue);
				return;
			case JSFLibraryRegistryPackage.JSF_LIBRARY_REGISTRY__PLUGIN_PROVIDED_JSF_LIBRARIES:
				getPluginProvidedJSFLibraries().clear();
				getPluginProvidedJSFLibraries().addAll((Collection)newValue);
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
			case JSFLibraryRegistryPackage.JSF_LIBRARY_REGISTRY__DEFAULT_IMPLEMENTATION_ID:
				setDefaultImplementationID(DEFAULT_IMPLEMENTATION_ID_EDEFAULT);
				return;
			case JSFLibraryRegistryPackage.JSF_LIBRARY_REGISTRY__JSF_LIBRARIES:
				getJSFLibraries().clear();
				return;
			case JSFLibraryRegistryPackage.JSF_LIBRARY_REGISTRY__PLUGIN_PROVIDED_JSF_LIBRARIES:
				getPluginProvidedJSFLibraries().clear();
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
			case JSFLibraryRegistryPackage.JSF_LIBRARY_REGISTRY__DEFAULT_IMPLEMENTATION_ID:
				return DEFAULT_IMPLEMENTATION_ID_EDEFAULT == null ? defaultImplementationID != null : !DEFAULT_IMPLEMENTATION_ID_EDEFAULT.equals(defaultImplementationID);
			case JSFLibraryRegistryPackage.JSF_LIBRARY_REGISTRY__JSF_LIBRARIES:
				return jsfLibraries != null && !jsfLibraries.isEmpty();
			case JSFLibraryRegistryPackage.JSF_LIBRARY_REGISTRY__PLUGIN_PROVIDED_JSF_LIBRARIES:
				return pluginProvidedJSFLibraries != null && !pluginProvidedJSFLibraries.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param ID 
	 * @return the jsf library of ID or null if none 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public JSFLibrary getJSFLibraryByID(String ID) {
		JSFLibrary library = null;
		if (ID != null) {
			Iterator itLibs = getAllJSFLibraries().iterator();
			while (itLibs.hasNext()) {
				JSFLibrary curLib = (JSFLibrary)itLibs.next();
				if (ID.equals(curLib.getID())) {
					library = curLib;
					break;
				}
			}
		}
		return library;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param name 
	 * @return the list of libraries named 'name' 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getJSFLibrariesByName(String name) {
		EList libraries = new BasicEList();
		if (name != null) {
			Iterator itLibs = getAllJSFLibraries().iterator();
			while(itLibs.hasNext()) {
				JSFLibrary curLib = (JSFLibrary)itLibs.next();
				if (name.equals(curLib.getName())) {
					libraries.add(curLib);
				}
			}
		}
		return libraries;
	}

	/**
	 * <!-- begin-user-doc -->
	 * This is a convenience method to return an EList of JSFLibrary instances
	 * that are marked as JSF implementations; while all instances are valid
	 * references, the returned EList should not be used for additions and/or
	 * removals of instances (use the EList returned by getJSFLibraries()).
	 * @return the list of implemention jsf libraries 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getImplJSFLibraries() {
		EList implementations = new BasicEList();
		Iterator itLibs = getAllJSFLibraries().iterator();
		while (itLibs.hasNext()) {
			JSFLibrary lib = (JSFLibrary)itLibs.next();
			if (lib.isImplementation()) {
				implementations.add(lib);
			}
		}
		return implementations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * This is a convenience method to return an EList of JSFLibrary instances
	 * that are not marked as JSF implementations; while all instances are
	 * valid references, the returned EList should not be used for additions
	 * and/or removals of instances (use the EList returned by
	 * getJSFLibraries()).
	 * @return the non-implemention JSF libraries
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getNonImplJSFLibraries() {
		EList nonImplementations = new BasicEList();
		Iterator itLibs = getAllJSFLibraries().iterator();
		while (itLibs.hasNext()) {
			JSFLibrary lib = (JSFLibrary)itLibs.next();
			if (!lib.isImplementation()) {
				nonImplementations.add(lib);
			}
		}
		return nonImplementations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * This is a convenience method to return an EList of JSFLibrary instances
	 * and PluginProvidedJSFLibrary instances; while all instances are valid
	 * references, the returned EList should not be used for additions and/or
	 * removals of instances (use the EList returned by getJSFLibraries()).
	 * @return all JSF libraries 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getAllJSFLibraries() {
		EList allLibs = new BasicEList();
		allLibs.addAll(getJSFLibraries());
		allLibs.addAll(getPluginProvidedJSFLibraries());
		return allLibs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param library 
	 * @return true if library is successfully added 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean addJSFLibrary(JSFLibrary library) {
		boolean added = false;
		if (library instanceof PluginProvidedJSFLibrary) {
			added = getPluginProvidedJSFLibraries().add(library);
		} else {
			added = getJSFLibraries().add(library);
		}
		return added;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param library 
	 * @return true if library is successfully removed
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean removeJSFLibrary(JSFLibrary library) {
		boolean removed = false;
		if (library instanceof PluginProvidedJSFLibrary) {
			removed = getPluginProvidedJSFLibraries().remove(library);
		} else {
			removed = getJSFLibraries().remove(library);
		}
		return removed;
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
		result.append(" (DefaultImplementationID: ");
		result.append(defaultImplementationID);
		result.append(')');
		return result.toString();
	}
	

} //JSFLibraryRegistryImpl
