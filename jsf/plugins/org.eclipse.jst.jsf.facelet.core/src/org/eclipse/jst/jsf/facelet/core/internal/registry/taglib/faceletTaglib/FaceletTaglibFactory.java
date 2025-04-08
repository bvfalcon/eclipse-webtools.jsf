/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
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
/**
 * <copyright>
 * </copyright>
 *
 * $Id: FaceletTaglibFactory.java,v 1.2 2010/03/18 06:24:28 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage
 * @generated
 */
public interface FaceletTaglibFactory extends EFactory
{
    /**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    FaceletTaglibFactory eINSTANCE = org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibFactoryImpl.init();

    /**
	 * Returns a new object of class '<em>Description</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Description</em>'.
	 * @generated
	 */
    Description createDescription();

    /**
	 * Returns a new object of class '<em>Display Name</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Display Name</em>'.
	 * @generated
	 */
    DisplayName createDisplayName();

    /**
	 * Returns a new object of class '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Document Root</em>'.
	 * @generated
	 */
    DocumentRoot createDocumentRoot();

    /**
	 * Returns a new object of class '<em>Canonical Name</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Canonical Name</em>'.
	 * @generated
	 */
    FaceletTaglibCanonicalName createFaceletTaglibCanonicalName();

    /**
	 * Returns a new object of class '<em>Extension</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Extension</em>'.
	 * @generated
	 */
    FaceletTaglibExtension createFaceletTaglibExtension();

    /**
	 * Returns a new object of class '<em>Function</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Function</em>'.
	 * @generated
	 */
    FaceletTaglibFunction createFaceletTaglibFunction();

    /**
	 * Returns a new object of class '<em>Tag Attribute</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag Attribute</em>'.
	 * @generated
	 */
    FaceletTaglibTagAttribute createFaceletTaglibTagAttribute();

    /**
	 * Returns a new object of class '<em>Tag Behavior Extension</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag Behavior Extension</em>'.
	 * @generated
	 */
    FaceletTaglibTagBehaviorExtension createFaceletTaglibTagBehaviorExtension();

    /**
	 * Returns a new object of class '<em>Tag Behavior</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag Behavior</em>'.
	 * @generated
	 */
    FaceletTaglibTagBehavior createFaceletTaglibTagBehavior();

    /**
	 * Returns a new object of class '<em>Tag Component Extension</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag Component Extension</em>'.
	 * @generated
	 */
    FaceletTaglibTagComponentExtension createFaceletTaglibTagComponentExtension();

    /**
	 * Returns a new object of class '<em>Tag Component</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag Component</em>'.
	 * @generated
	 */
    FaceletTaglibTagComponent createFaceletTaglibTagComponent();

    /**
	 * Returns a new object of class '<em>Tag Converter Extension</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag Converter Extension</em>'.
	 * @generated
	 */
    FaceletTaglibTagConverterExtension createFaceletTaglibTagConverterExtension();

    /**
	 * Returns a new object of class '<em>Tag Converter</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag Converter</em>'.
	 * @generated
	 */
    FaceletTaglibTagConverter createFaceletTaglibTagConverter();

    /**
	 * Returns a new object of class '<em>Tag Extension</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag Extension</em>'.
	 * @generated
	 */
    FaceletTaglibTagExtension createFaceletTaglibTagExtension();

    /**
	 * Returns a new object of class '<em>Tag</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag</em>'.
	 * @generated
	 */
    FaceletTaglibTag createFaceletTaglibTag();

    /**
	 * Returns a new object of class '<em>Tag Validator Extension</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag Validator Extension</em>'.
	 * @generated
	 */
    FaceletTaglibTagValidatorExtension createFaceletTaglibTagValidatorExtension();

    /**
	 * Returns a new object of class '<em>Tag Validator</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag Validator</em>'.
	 * @generated
	 */
    FaceletTaglibTagValidator createFaceletTaglibTagValidator();

    /**
	 * Returns a new object of class '<em>Facelet Taglib</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Facelet Taglib</em>'.
	 * @generated
	 */
    FaceletTaglib createFaceletTaglib();

    /**
	 * Returns a new object of class '<em>Fully Qualified Class</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Fully Qualified Class</em>'.
	 * @generated
	 */
    FullyQualifiedClass createFullyQualifiedClass();

    /**
	 * Returns a new object of class '<em>Generic Boolean</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Generic Boolean</em>'.
	 * @generated
	 */
    GenericBoolean createGenericBoolean();

    /**
	 * Returns a new object of class '<em>Icon</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Icon</em>'.
	 * @generated
	 */
    Icon createIcon();

    /**
	 * Returns a new object of class '<em>Java Identifier</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Java Identifier</em>'.
	 * @generated
	 */
    JavaIdentifier createJavaIdentifier();

    /**
	 * Returns a new object of class '<em>Path</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Path</em>'.
	 * @generated
	 */
    Path createPath();

    /**
	 * Returns a new object of class '<em>Identifiable String Value</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Identifiable String Value</em>'.
	 * @generated
	 */
    IdentifiableStringValue createIdentifiableStringValue();

    /**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
    FaceletTaglibPackage getFaceletTaglibPackage();

} //FaceletTaglibFactory
