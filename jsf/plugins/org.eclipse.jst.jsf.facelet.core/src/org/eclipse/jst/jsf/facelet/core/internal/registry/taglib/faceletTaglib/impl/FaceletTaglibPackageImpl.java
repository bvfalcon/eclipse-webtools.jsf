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
 * $Id: FaceletTaglibPackageImpl.java,v 1.2 2010/03/18 06:24:39 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Description;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DisplayName;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DocumentRoot;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibCanonicalName;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibExtension;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibFactory;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibFunction;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehavior;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehaviorExtension;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponent;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponentExtension;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverter;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverterExtension;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagExtension;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagValidator;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagValidatorExtension;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibVersion;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FullyQualifiedClass;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.GenericBoolean;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.GenericBooleanBase;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableLangStringValue;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableStringValue;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.JavaIdentifier;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Path;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.UserVisibleTaglibObject;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.util.FaceletTaglibValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FaceletTaglibPackageImpl extends EPackageImpl implements FaceletTaglibPackage
{
    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass descriptionEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass displayNameEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass documentRootEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass faceletTaglibCanonicalNameEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass faceletTaglibExtensionEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass faceletTaglibFunctionEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass faceletTaglibTagAttributeEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass faceletTaglibTagBehaviorExtensionEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass faceletTaglibTagBehaviorEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass faceletTaglibTagComponentExtensionEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass faceletTaglibTagComponentEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass faceletTaglibTagConverterExtensionEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass faceletTaglibTagConverterEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass faceletTaglibTagExtensionEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass faceletTaglibTagEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass faceletTaglibTagValidatorExtensionEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass faceletTaglibTagValidatorEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass faceletTaglibEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass fullyQualifiedClassEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass genericBooleanEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass iconEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass javaIdentifierEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass pathEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass identifiableStringValueEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass identifiableLangStringValueEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass userVisibleTaglibObjectEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum faceletTaglibVersionEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum genericBooleanBaseEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EDataType faceletTaglibVersionTypeObjectEDataType = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EDataType fullyQualifiedClassBaseEDataType = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EDataType javaIdentifierBaseEDataType = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EDataType pathTypeBaseEDataType = null;

    /**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
    private FaceletTaglibPackageImpl()
    {
		super(eNS_URI, FaceletTaglibFactory.eINSTANCE);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static boolean isInited = false;

    /**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link FaceletTaglibPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
     * @return 
     * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
    public static FaceletTaglibPackage init()
    {
		if (isInited) return (FaceletTaglibPackage)EPackage.Registry.INSTANCE.getEPackage(FaceletTaglibPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredFaceletTaglibPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		FaceletTaglibPackageImpl theFaceletTaglibPackage = registeredFaceletTaglibPackage instanceof FaceletTaglibPackageImpl ? (FaceletTaglibPackageImpl)registeredFaceletTaglibPackage : new FaceletTaglibPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theFaceletTaglibPackage.createPackageContents();

		// Initialize created meta-data
		theFaceletTaglibPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theFaceletTaglibPackage,
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return FaceletTaglibValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theFaceletTaglibPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(FaceletTaglibPackage.eNS_URI, theFaceletTaglibPackage);
		return theFaceletTaglibPackage;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getDescription()
    {
		return descriptionEClass;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescription_Any() {
		return (EAttribute)descriptionEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescription_Id() {
		return (EAttribute)descriptionEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescription_Lang() {
		return (EAttribute)descriptionEClass.getEStructuralFeatures().get(2);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getDisplayName()
    {
		return displayNameEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getDocumentRoot()
    {
		return documentRootEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getDocumentRoot_Mixed()
    {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getDocumentRoot_XMLNSPrefixMap()
    {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getDocumentRoot_XSISchemaLocation()
    {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getDocumentRoot_FaceletTaglib()
    {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getFaceletTaglibCanonicalName()
    {
		return faceletTaglibCanonicalNameEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglibCanonicalName_Value()
    {
		return (EAttribute)faceletTaglibCanonicalNameEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglibCanonicalName_Id()
    {
		return (EAttribute)faceletTaglibCanonicalNameEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getFaceletTaglibExtension()
    {
		return faceletTaglibExtensionEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglibExtension_Any()
    {
		return (EAttribute)faceletTaglibExtensionEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglibExtension_Id()
    {
		return (EAttribute)faceletTaglibExtensionEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getFaceletTaglibFunction()
    {
		return faceletTaglibFunctionEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibFunction_FunctionName()
    {
		return (EReference)faceletTaglibFunctionEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibFunction_FunctionClass()
    {
		return (EReference)faceletTaglibFunctionEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibFunction_FunctionSignature()
    {
		return (EReference)faceletTaglibFunctionEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getFaceletTaglibTagAttribute()
    {
		return faceletTaglibTagAttributeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTagAttribute_NameElement()
    {
		return (EReference)faceletTaglibTagAttributeEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTagAttribute_RequiredElement()
    {
		return (EReference)faceletTaglibTagAttributeEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTagAttribute_TypeElement()
    {
		return (EReference)faceletTaglibTagAttributeEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTagAttribute_MethodSignatureElement()
    {
		return (EReference)faceletTaglibTagAttributeEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglibTagAttribute_Id()
    {
		return (EAttribute)faceletTaglibTagAttributeEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglibTagAttribute_Name()
    {
		return (EAttribute)faceletTaglibTagAttributeEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglibTagAttribute_Required()
    {
		return (EAttribute)faceletTaglibTagAttributeEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglibTagAttribute_Type()
    {
		return (EAttribute)faceletTaglibTagAttributeEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglibTagAttribute_MethodSignature()
    {
		return (EAttribute)faceletTaglibTagAttributeEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getFaceletTaglibTagBehaviorExtension()
    {
		return faceletTaglibTagBehaviorExtensionEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglibTagBehaviorExtension_Any()
    {
		return (EAttribute)faceletTaglibTagBehaviorExtensionEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglibTagBehaviorExtension_Id()
    {
		return (EAttribute)faceletTaglibTagBehaviorExtensionEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getFaceletTaglibTagBehavior()
    {
		return faceletTaglibTagBehaviorEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTagBehavior_BehaviorId()
    {
		return (EReference)faceletTaglibTagBehaviorEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTagBehavior_HandlerClass()
    {
		return (EReference)faceletTaglibTagBehaviorEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTagBehavior_BehaviorExtension()
    {
		return (EReference)faceletTaglibTagBehaviorEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getFaceletTaglibTagComponentExtension()
    {
		return faceletTaglibTagComponentExtensionEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglibTagComponentExtension_Any()
    {
		return (EAttribute)faceletTaglibTagComponentExtensionEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglibTagComponentExtension_Id()
    {
		return (EAttribute)faceletTaglibTagComponentExtensionEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getFaceletTaglibTagComponent()
    {
		return faceletTaglibTagComponentEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTagComponent_ComponentType()
    {
		return (EReference)faceletTaglibTagComponentEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTagComponent_RendererType()
    {
		return (EReference)faceletTaglibTagComponentEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTagComponent_HandlerClass()
    {
		return (EReference)faceletTaglibTagComponentEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTagComponent_ComponentExtension()
    {
		return (EReference)faceletTaglibTagComponentEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getFaceletTaglibTagConverterExtension()
    {
		return faceletTaglibTagConverterExtensionEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglibTagConverterExtension_Any()
    {
		return (EAttribute)faceletTaglibTagConverterExtensionEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglibTagConverterExtension_Id()
    {
		return (EAttribute)faceletTaglibTagConverterExtensionEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getFaceletTaglibTagConverter()
    {
		return faceletTaglibTagConverterEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTagConverter_ConverterId()
    {
		return (EReference)faceletTaglibTagConverterEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTagConverter_HandlerClass()
    {
		return (EReference)faceletTaglibTagConverterEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTagConverter_ConverterExtension()
    {
		return (EReference)faceletTaglibTagConverterEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getFaceletTaglibTagExtension()
    {
		return faceletTaglibTagExtensionEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglibTagExtension_Any()
    {
		return (EAttribute)faceletTaglibTagExtensionEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglibTagExtension_Id()
    {
		return (EAttribute)faceletTaglibTagExtensionEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getFaceletTaglibTag()
    {
		return faceletTaglibTagEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTag_TagNameElement()
    {
		return (EReference)faceletTaglibTagEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTag_HandlerClassElement()
    {
		return (EReference)faceletTaglibTagEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTag_Behavior()
    {
		return (EReference)faceletTaglibTagEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTag_Component()
    {
		return (EReference)faceletTaglibTagEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTag_Converter()
    {
		return (EReference)faceletTaglibTagEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTag_Validator()
    {
		return (EReference)faceletTaglibTagEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTag_Source()
    {
		return (EReference)faceletTaglibTagEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTag_Attribute()
    {
		return (EReference)faceletTaglibTagEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTag_TagExtension()
    {
		return (EReference)faceletTaglibTagEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglibTag_TagName()
    {
		return (EAttribute)faceletTaglibTagEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglibTag_HandlerClass()
    {
		return (EAttribute)faceletTaglibTagEClass.getEStructuralFeatures().get(10);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getFaceletTaglibTagValidatorExtension()
    {
		return faceletTaglibTagValidatorExtensionEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglibTagValidatorExtension_Any()
    {
		return (EAttribute)faceletTaglibTagValidatorExtensionEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglibTagValidatorExtension_Id()
    {
		return (EAttribute)faceletTaglibTagValidatorExtensionEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getFaceletTaglibTagValidator()
    {
		return faceletTaglibTagValidatorEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTagValidator_ValidatorId()
    {
		return (EReference)faceletTaglibTagValidatorEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTagValidator_HandlerClass()
    {
		return (EReference)faceletTaglibTagValidatorEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglibTagValidator_ValidatorExtension()
    {
		return (EReference)faceletTaglibTagValidatorEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getFaceletTaglib()
    {
		return faceletTaglibEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglib_LibraryClass()
    {
		return (EReference)faceletTaglibEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglib_Namespace()
    {
		return (EReference)faceletTaglibEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglib_CompositeLibraryName()
    {
		return (EReference)faceletTaglibEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglib_Group()
    {
		return (EAttribute)faceletTaglibEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglib_Tag()
    {
		return (EReference)faceletTaglibEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglib_Function()
    {
		return (EReference)faceletTaglibEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFaceletTaglib_TaglibExtension()
    {
		return (EReference)faceletTaglibEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglib_Id()
    {
		return (EAttribute)faceletTaglibEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglib_Version()
    {
		return (EAttribute)faceletTaglibEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFaceletTaglib_NamespaceUri()
    {
		return (EAttribute)faceletTaglibEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFaceletTaglib_ShortName() {
		return (EAttribute)faceletTaglibEClass.getEStructuralFeatures().get(10);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getFullyQualifiedClass()
    {
		return fullyQualifiedClassEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getGenericBoolean()
    {
		return genericBooleanEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getIcon()
    {
		return iconEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIcon_SmallIcon()
    {
		return (EReference)iconEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIcon_LargeIcon()
    {
		return (EReference)iconEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getIcon_Id()
    {
		return (EAttribute)iconEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getIcon_Lang()
    {
		return (EAttribute)iconEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getJavaIdentifier()
    {
		return javaIdentifierEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getPath()
    {
		return pathEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getIdentifiableStringValue()
    {
		return identifiableStringValueEClass;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIdentifiableStringValue_Value() {
		return (EAttribute)identifiableStringValueEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getIdentifiableStringValue_Id()
    {
		return (EAttribute)identifiableStringValueEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getIdentifiableLangStringValue()
    {
		return identifiableLangStringValueEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getIdentifiableLangStringValue_Lang()
    {
		return (EAttribute)identifiableLangStringValueEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getUserVisibleTaglibObject()
    {
		return userVisibleTaglibObjectEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getUserVisibleTaglibObject_Description()
    {
		return (EReference)userVisibleTaglibObjectEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getUserVisibleTaglibObject_DisplayName()
    {
		return (EReference)userVisibleTaglibObjectEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getUserVisibleTaglibObject_Icon()
    {
		return (EReference)userVisibleTaglibObjectEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getFaceletTaglibVersion()
    {
		return faceletTaglibVersionEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getGenericBooleanBase()
    {
		return genericBooleanBaseEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EDataType getFaceletTaglibVersionTypeObject()
    {
		return faceletTaglibVersionTypeObjectEDataType;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EDataType getFullyQualifiedClassBase()
    {
		return fullyQualifiedClassBaseEDataType;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EDataType getJavaIdentifierBase()
    {
		return javaIdentifierBaseEDataType;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EDataType getPathTypeBase()
    {
		return pathTypeBaseEDataType;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglibFactory getFaceletTaglibFactory()
    {
		return (FaceletTaglibFactory)getEFactoryInstance();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private boolean isCreated = false;

    /**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void createPackageContents()
    {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		descriptionEClass = createEClass(DESCRIPTION);
		createEAttribute(descriptionEClass, DESCRIPTION__ANY);
		createEAttribute(descriptionEClass, DESCRIPTION__ID);
		createEAttribute(descriptionEClass, DESCRIPTION__LANG);

		displayNameEClass = createEClass(DISPLAY_NAME);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__FACELET_TAGLIB);

		faceletTaglibCanonicalNameEClass = createEClass(FACELET_TAGLIB_CANONICAL_NAME);
		createEAttribute(faceletTaglibCanonicalNameEClass, FACELET_TAGLIB_CANONICAL_NAME__VALUE);
		createEAttribute(faceletTaglibCanonicalNameEClass, FACELET_TAGLIB_CANONICAL_NAME__ID);

		faceletTaglibExtensionEClass = createEClass(FACELET_TAGLIB_EXTENSION);
		createEAttribute(faceletTaglibExtensionEClass, FACELET_TAGLIB_EXTENSION__ANY);
		createEAttribute(faceletTaglibExtensionEClass, FACELET_TAGLIB_EXTENSION__ID);

		faceletTaglibFunctionEClass = createEClass(FACELET_TAGLIB_FUNCTION);
		createEReference(faceletTaglibFunctionEClass, FACELET_TAGLIB_FUNCTION__FUNCTION_NAME);
		createEReference(faceletTaglibFunctionEClass, FACELET_TAGLIB_FUNCTION__FUNCTION_CLASS);
		createEReference(faceletTaglibFunctionEClass, FACELET_TAGLIB_FUNCTION__FUNCTION_SIGNATURE);

		faceletTaglibTagAttributeEClass = createEClass(FACELET_TAGLIB_TAG_ATTRIBUTE);
		createEReference(faceletTaglibTagAttributeEClass, FACELET_TAGLIB_TAG_ATTRIBUTE__NAME_ELEMENT);
		createEReference(faceletTaglibTagAttributeEClass, FACELET_TAGLIB_TAG_ATTRIBUTE__REQUIRED_ELEMENT);
		createEReference(faceletTaglibTagAttributeEClass, FACELET_TAGLIB_TAG_ATTRIBUTE__TYPE_ELEMENT);
		createEReference(faceletTaglibTagAttributeEClass, FACELET_TAGLIB_TAG_ATTRIBUTE__METHOD_SIGNATURE_ELEMENT);
		createEAttribute(faceletTaglibTagAttributeEClass, FACELET_TAGLIB_TAG_ATTRIBUTE__ID);
		createEAttribute(faceletTaglibTagAttributeEClass, FACELET_TAGLIB_TAG_ATTRIBUTE__NAME);
		createEAttribute(faceletTaglibTagAttributeEClass, FACELET_TAGLIB_TAG_ATTRIBUTE__REQUIRED);
		createEAttribute(faceletTaglibTagAttributeEClass, FACELET_TAGLIB_TAG_ATTRIBUTE__TYPE);
		createEAttribute(faceletTaglibTagAttributeEClass, FACELET_TAGLIB_TAG_ATTRIBUTE__METHOD_SIGNATURE);

		faceletTaglibTagBehaviorExtensionEClass = createEClass(FACELET_TAGLIB_TAG_BEHAVIOR_EXTENSION);
		createEAttribute(faceletTaglibTagBehaviorExtensionEClass, FACELET_TAGLIB_TAG_BEHAVIOR_EXTENSION__ANY);
		createEAttribute(faceletTaglibTagBehaviorExtensionEClass, FACELET_TAGLIB_TAG_BEHAVIOR_EXTENSION__ID);

		faceletTaglibTagBehaviorEClass = createEClass(FACELET_TAGLIB_TAG_BEHAVIOR);
		createEReference(faceletTaglibTagBehaviorEClass, FACELET_TAGLIB_TAG_BEHAVIOR__BEHAVIOR_ID);
		createEReference(faceletTaglibTagBehaviorEClass, FACELET_TAGLIB_TAG_BEHAVIOR__HANDLER_CLASS);
		createEReference(faceletTaglibTagBehaviorEClass, FACELET_TAGLIB_TAG_BEHAVIOR__BEHAVIOR_EXTENSION);

		faceletTaglibTagComponentExtensionEClass = createEClass(FACELET_TAGLIB_TAG_COMPONENT_EXTENSION);
		createEAttribute(faceletTaglibTagComponentExtensionEClass, FACELET_TAGLIB_TAG_COMPONENT_EXTENSION__ANY);
		createEAttribute(faceletTaglibTagComponentExtensionEClass, FACELET_TAGLIB_TAG_COMPONENT_EXTENSION__ID);

		faceletTaglibTagComponentEClass = createEClass(FACELET_TAGLIB_TAG_COMPONENT);
		createEReference(faceletTaglibTagComponentEClass, FACELET_TAGLIB_TAG_COMPONENT__COMPONENT_TYPE);
		createEReference(faceletTaglibTagComponentEClass, FACELET_TAGLIB_TAG_COMPONENT__RENDERER_TYPE);
		createEReference(faceletTaglibTagComponentEClass, FACELET_TAGLIB_TAG_COMPONENT__HANDLER_CLASS);
		createEReference(faceletTaglibTagComponentEClass, FACELET_TAGLIB_TAG_COMPONENT__COMPONENT_EXTENSION);

		faceletTaglibTagConverterExtensionEClass = createEClass(FACELET_TAGLIB_TAG_CONVERTER_EXTENSION);
		createEAttribute(faceletTaglibTagConverterExtensionEClass, FACELET_TAGLIB_TAG_CONVERTER_EXTENSION__ANY);
		createEAttribute(faceletTaglibTagConverterExtensionEClass, FACELET_TAGLIB_TAG_CONVERTER_EXTENSION__ID);

		faceletTaglibTagConverterEClass = createEClass(FACELET_TAGLIB_TAG_CONVERTER);
		createEReference(faceletTaglibTagConverterEClass, FACELET_TAGLIB_TAG_CONVERTER__CONVERTER_ID);
		createEReference(faceletTaglibTagConverterEClass, FACELET_TAGLIB_TAG_CONVERTER__HANDLER_CLASS);
		createEReference(faceletTaglibTagConverterEClass, FACELET_TAGLIB_TAG_CONVERTER__CONVERTER_EXTENSION);

		faceletTaglibTagExtensionEClass = createEClass(FACELET_TAGLIB_TAG_EXTENSION);
		createEAttribute(faceletTaglibTagExtensionEClass, FACELET_TAGLIB_TAG_EXTENSION__ANY);
		createEAttribute(faceletTaglibTagExtensionEClass, FACELET_TAGLIB_TAG_EXTENSION__ID);

		faceletTaglibTagEClass = createEClass(FACELET_TAGLIB_TAG);
		createEReference(faceletTaglibTagEClass, FACELET_TAGLIB_TAG__TAG_NAME_ELEMENT);
		createEReference(faceletTaglibTagEClass, FACELET_TAGLIB_TAG__HANDLER_CLASS_ELEMENT);
		createEReference(faceletTaglibTagEClass, FACELET_TAGLIB_TAG__BEHAVIOR);
		createEReference(faceletTaglibTagEClass, FACELET_TAGLIB_TAG__COMPONENT);
		createEReference(faceletTaglibTagEClass, FACELET_TAGLIB_TAG__CONVERTER);
		createEReference(faceletTaglibTagEClass, FACELET_TAGLIB_TAG__VALIDATOR);
		createEReference(faceletTaglibTagEClass, FACELET_TAGLIB_TAG__SOURCE);
		createEReference(faceletTaglibTagEClass, FACELET_TAGLIB_TAG__ATTRIBUTE);
		createEReference(faceletTaglibTagEClass, FACELET_TAGLIB_TAG__TAG_EXTENSION);
		createEAttribute(faceletTaglibTagEClass, FACELET_TAGLIB_TAG__TAG_NAME);
		createEAttribute(faceletTaglibTagEClass, FACELET_TAGLIB_TAG__HANDLER_CLASS);

		faceletTaglibTagValidatorExtensionEClass = createEClass(FACELET_TAGLIB_TAG_VALIDATOR_EXTENSION);
		createEAttribute(faceletTaglibTagValidatorExtensionEClass, FACELET_TAGLIB_TAG_VALIDATOR_EXTENSION__ANY);
		createEAttribute(faceletTaglibTagValidatorExtensionEClass, FACELET_TAGLIB_TAG_VALIDATOR_EXTENSION__ID);

		faceletTaglibTagValidatorEClass = createEClass(FACELET_TAGLIB_TAG_VALIDATOR);
		createEReference(faceletTaglibTagValidatorEClass, FACELET_TAGLIB_TAG_VALIDATOR__VALIDATOR_ID);
		createEReference(faceletTaglibTagValidatorEClass, FACELET_TAGLIB_TAG_VALIDATOR__HANDLER_CLASS);
		createEReference(faceletTaglibTagValidatorEClass, FACELET_TAGLIB_TAG_VALIDATOR__VALIDATOR_EXTENSION);

		faceletTaglibEClass = createEClass(FACELET_TAGLIB);
		createEReference(faceletTaglibEClass, FACELET_TAGLIB__LIBRARY_CLASS);
		createEReference(faceletTaglibEClass, FACELET_TAGLIB__NAMESPACE);
		createEReference(faceletTaglibEClass, FACELET_TAGLIB__COMPOSITE_LIBRARY_NAME);
		createEAttribute(faceletTaglibEClass, FACELET_TAGLIB__GROUP);
		createEReference(faceletTaglibEClass, FACELET_TAGLIB__TAG);
		createEReference(faceletTaglibEClass, FACELET_TAGLIB__FUNCTION);
		createEReference(faceletTaglibEClass, FACELET_TAGLIB__TAGLIB_EXTENSION);
		createEAttribute(faceletTaglibEClass, FACELET_TAGLIB__ID);
		createEAttribute(faceletTaglibEClass, FACELET_TAGLIB__VERSION);
		createEAttribute(faceletTaglibEClass, FACELET_TAGLIB__NAMESPACE_URI);
		createEAttribute(faceletTaglibEClass, FACELET_TAGLIB__SHORT_NAME);

		fullyQualifiedClassEClass = createEClass(FULLY_QUALIFIED_CLASS);

		genericBooleanEClass = createEClass(GENERIC_BOOLEAN);

		iconEClass = createEClass(ICON);
		createEReference(iconEClass, ICON__SMALL_ICON);
		createEReference(iconEClass, ICON__LARGE_ICON);
		createEAttribute(iconEClass, ICON__ID);
		createEAttribute(iconEClass, ICON__LANG);

		javaIdentifierEClass = createEClass(JAVA_IDENTIFIER);

		pathEClass = createEClass(PATH);

		identifiableStringValueEClass = createEClass(IDENTIFIABLE_STRING_VALUE);
		createEAttribute(identifiableStringValueEClass, IDENTIFIABLE_STRING_VALUE__VALUE);
		createEAttribute(identifiableStringValueEClass, IDENTIFIABLE_STRING_VALUE__ID);

		identifiableLangStringValueEClass = createEClass(IDENTIFIABLE_LANG_STRING_VALUE);
		createEAttribute(identifiableLangStringValueEClass, IDENTIFIABLE_LANG_STRING_VALUE__LANG);

		userVisibleTaglibObjectEClass = createEClass(USER_VISIBLE_TAGLIB_OBJECT);
		createEReference(userVisibleTaglibObjectEClass, USER_VISIBLE_TAGLIB_OBJECT__DESCRIPTION);
		createEReference(userVisibleTaglibObjectEClass, USER_VISIBLE_TAGLIB_OBJECT__DISPLAY_NAME);
		createEReference(userVisibleTaglibObjectEClass, USER_VISIBLE_TAGLIB_OBJECT__ICON);

		// Create enums
		faceletTaglibVersionEEnum = createEEnum(FACELET_TAGLIB_VERSION);
		genericBooleanBaseEEnum = createEEnum(GENERIC_BOOLEAN_BASE);

		// Create data types
		faceletTaglibVersionTypeObjectEDataType = createEDataType(FACELET_TAGLIB_VERSION_TYPE_OBJECT);
		fullyQualifiedClassBaseEDataType = createEDataType(FULLY_QUALIFIED_CLASS_BASE);
		javaIdentifierBaseEDataType = createEDataType(JAVA_IDENTIFIER_BASE);
		pathTypeBaseEDataType = createEDataType(PATH_TYPE_BASE);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private boolean isInitialized = false;

    /**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void initializePackageContents()
    {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		displayNameEClass.getESuperTypes().add(this.getIdentifiableLangStringValue());
		faceletTaglibFunctionEClass.getESuperTypes().add(this.getUserVisibleTaglibObject());
		faceletTaglibTagAttributeEClass.getESuperTypes().add(this.getUserVisibleTaglibObject());
		faceletTaglibTagBehaviorEClass.getESuperTypes().add(this.getUserVisibleTaglibObject());
		faceletTaglibTagComponentEClass.getESuperTypes().add(this.getUserVisibleTaglibObject());
		faceletTaglibTagConverterEClass.getESuperTypes().add(this.getUserVisibleTaglibObject());
		faceletTaglibTagEClass.getESuperTypes().add(this.getUserVisibleTaglibObject());
		faceletTaglibTagValidatorEClass.getESuperTypes().add(this.getUserVisibleTaglibObject());
		faceletTaglibEClass.getESuperTypes().add(this.getUserVisibleTaglibObject());
		fullyQualifiedClassEClass.getESuperTypes().add(this.getIdentifiableStringValue());
		genericBooleanEClass.getESuperTypes().add(this.getIdentifiableStringValue());
		javaIdentifierEClass.getESuperTypes().add(this.getIdentifiableStringValue());
		pathEClass.getESuperTypes().add(this.getIdentifiableStringValue());
		identifiableLangStringValueEClass.getESuperTypes().add(this.getIdentifiableStringValue());

		// Initialize classes and features; add operations and parameters
		initEClass(descriptionEClass, Description.class, "Description", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDescription_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, Description.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDescription_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, Description.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDescription_Lang(), ecorePackage.getEString(), "lang", null, 0, 1, Description.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(displayNameEClass, DisplayName.class, "DisplayName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDocumentRoot_FaceletTaglib(), this.getFaceletTaglib(), null, "faceletTaglib", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(faceletTaglibCanonicalNameEClass, FaceletTaglibCanonicalName.class, "FaceletTaglibCanonicalName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getFaceletTaglibCanonicalName_Value(), theXMLTypePackage.getNCName(), "value", null, 0, 1, FaceletTaglibCanonicalName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFaceletTaglibCanonicalName_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, FaceletTaglibCanonicalName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(faceletTaglibExtensionEClass, FaceletTaglibExtension.class, "FaceletTaglibExtension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getFaceletTaglibExtension_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, FaceletTaglibExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFaceletTaglibExtension_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, FaceletTaglibExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(faceletTaglibFunctionEClass, FaceletTaglibFunction.class, "FaceletTaglibFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getFaceletTaglibFunction_FunctionName(), this.getIdentifiableStringValue(), null, "functionName", null, 1, 1, FaceletTaglibFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglibFunction_FunctionClass(), this.getFullyQualifiedClass(), null, "functionClass", null, 1, 1, FaceletTaglibFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglibFunction_FunctionSignature(), this.getIdentifiableStringValue(), null, "functionSignature", null, 1, 1, FaceletTaglibFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(faceletTaglibTagAttributeEClass, FaceletTaglibTagAttribute.class, "FaceletTaglibTagAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getFaceletTaglibTagAttribute_NameElement(), this.getFaceletTaglibCanonicalName(), null, "nameElement", null, 1, 1, FaceletTaglibTagAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglibTagAttribute_RequiredElement(), this.getGenericBoolean(), null, "requiredElement", null, 0, 1, FaceletTaglibTagAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglibTagAttribute_TypeElement(), this.getFullyQualifiedClass(), null, "typeElement", null, 0, 1, FaceletTaglibTagAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglibTagAttribute_MethodSignatureElement(), this.getIdentifiableStringValue(), null, "methodSignatureElement", null, 0, 1, FaceletTaglibTagAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFaceletTaglibTagAttribute_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, FaceletTaglibTagAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFaceletTaglibTagAttribute_Name(), ecorePackage.getEString(), "name", null, 0, 1, FaceletTaglibTagAttribute.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFaceletTaglibTagAttribute_Required(), ecorePackage.getEBoolean(), "required", null, 0, 1, FaceletTaglibTagAttribute.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFaceletTaglibTagAttribute_Type(), ecorePackage.getEString(), "type", null, 0, 1, FaceletTaglibTagAttribute.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFaceletTaglibTagAttribute_MethodSignature(), ecorePackage.getEString(), "methodSignature", null, 0, 1, FaceletTaglibTagAttribute.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(faceletTaglibTagBehaviorExtensionEClass, FaceletTaglibTagBehaviorExtension.class, "FaceletTaglibTagBehaviorExtension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getFaceletTaglibTagBehaviorExtension_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, FaceletTaglibTagBehaviorExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFaceletTaglibTagBehaviorExtension_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, FaceletTaglibTagBehaviorExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(faceletTaglibTagBehaviorEClass, FaceletTaglibTagBehavior.class, "FaceletTaglibTagBehavior", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getFaceletTaglibTagBehavior_BehaviorId(), this.getIdentifiableStringValue(), null, "behaviorId", null, 1, 1, FaceletTaglibTagBehavior.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglibTagBehavior_HandlerClass(), this.getFullyQualifiedClass(), null, "handlerClass", null, 0, 1, FaceletTaglibTagBehavior.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglibTagBehavior_BehaviorExtension(), this.getFaceletTaglibTagBehaviorExtension(), null, "behaviorExtension", null, 0, -1, FaceletTaglibTagBehavior.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(faceletTaglibTagComponentExtensionEClass, FaceletTaglibTagComponentExtension.class, "FaceletTaglibTagComponentExtension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getFaceletTaglibTagComponentExtension_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, FaceletTaglibTagComponentExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFaceletTaglibTagComponentExtension_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, FaceletTaglibTagComponentExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(faceletTaglibTagComponentEClass, FaceletTaglibTagComponent.class, "FaceletTaglibTagComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getFaceletTaglibTagComponent_ComponentType(), this.getIdentifiableStringValue(), null, "componentType", null, 1, 1, FaceletTaglibTagComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglibTagComponent_RendererType(), this.getIdentifiableStringValue(), null, "rendererType", null, 0, 1, FaceletTaglibTagComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglibTagComponent_HandlerClass(), this.getFullyQualifiedClass(), null, "handlerClass", null, 0, 1, FaceletTaglibTagComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglibTagComponent_ComponentExtension(), this.getFaceletTaglibTagComponentExtension(), null, "componentExtension", null, 0, -1, FaceletTaglibTagComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(faceletTaglibTagConverterExtensionEClass, FaceletTaglibTagConverterExtension.class, "FaceletTaglibTagConverterExtension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getFaceletTaglibTagConverterExtension_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, FaceletTaglibTagConverterExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFaceletTaglibTagConverterExtension_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, FaceletTaglibTagConverterExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(faceletTaglibTagConverterEClass, FaceletTaglibTagConverter.class, "FaceletTaglibTagConverter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getFaceletTaglibTagConverter_ConverterId(), this.getIdentifiableStringValue(), null, "converterId", null, 1, 1, FaceletTaglibTagConverter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglibTagConverter_HandlerClass(), this.getFullyQualifiedClass(), null, "handlerClass", null, 0, 1, FaceletTaglibTagConverter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglibTagConverter_ConverterExtension(), this.getFaceletTaglibTagConverterExtension(), null, "converterExtension", null, 0, -1, FaceletTaglibTagConverter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(faceletTaglibTagExtensionEClass, FaceletTaglibTagExtension.class, "FaceletTaglibTagExtension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getFaceletTaglibTagExtension_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, FaceletTaglibTagExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFaceletTaglibTagExtension_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, FaceletTaglibTagExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(faceletTaglibTagEClass, FaceletTaglibTag.class, "FaceletTaglibTag", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getFaceletTaglibTag_TagNameElement(), this.getFaceletTaglibCanonicalName(), null, "tagNameElement", null, 1, 1, FaceletTaglibTag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglibTag_HandlerClassElement(), this.getFullyQualifiedClass(), null, "handlerClassElement", null, 0, 1, FaceletTaglibTag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglibTag_Behavior(), this.getFaceletTaglibTagBehavior(), null, "behavior", null, 0, 1, FaceletTaglibTag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglibTag_Component(), this.getFaceletTaglibTagComponent(), null, "component", null, 0, 1, FaceletTaglibTag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglibTag_Converter(), this.getFaceletTaglibTagConverter(), null, "converter", null, 0, 1, FaceletTaglibTag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglibTag_Validator(), this.getFaceletTaglibTagValidator(), null, "validator", null, 0, 1, FaceletTaglibTag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglibTag_Source(), this.getIdentifiableStringValue(), null, "source", null, 0, 1, FaceletTaglibTag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglibTag_Attribute(), this.getFaceletTaglibTagAttribute(), null, "attribute", null, 0, -1, FaceletTaglibTag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglibTag_TagExtension(), this.getFaceletTaglibTagExtension(), null, "tagExtension", null, 0, -1, FaceletTaglibTag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFaceletTaglibTag_TagName(), ecorePackage.getEString(), "tagName", null, 0, 1, FaceletTaglibTag.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFaceletTaglibTag_HandlerClass(), ecorePackage.getEString(), "handlerClass", null, 0, 1, FaceletTaglibTag.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(faceletTaglibTagValidatorExtensionEClass, FaceletTaglibTagValidatorExtension.class, "FaceletTaglibTagValidatorExtension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getFaceletTaglibTagValidatorExtension_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, FaceletTaglibTagValidatorExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFaceletTaglibTagValidatorExtension_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, FaceletTaglibTagValidatorExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(faceletTaglibTagValidatorEClass, FaceletTaglibTagValidator.class, "FaceletTaglibTagValidator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getFaceletTaglibTagValidator_ValidatorId(), this.getIdentifiableStringValue(), null, "validatorId", null, 1, 1, FaceletTaglibTagValidator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglibTagValidator_HandlerClass(), this.getFullyQualifiedClass(), null, "handlerClass", null, 0, 1, FaceletTaglibTagValidator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglibTagValidator_ValidatorExtension(), this.getFaceletTaglibTagValidatorExtension(), null, "validatorExtension", null, 0, -1, FaceletTaglibTagValidator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(faceletTaglibEClass, FaceletTaglib.class, "FaceletTaglib", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getFaceletTaglib_LibraryClass(), this.getFullyQualifiedClass(), null, "libraryClass", null, 0, 1, FaceletTaglib.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglib_Namespace(), this.getIdentifiableStringValue(), null, "namespace", null, 0, 1, FaceletTaglib.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglib_CompositeLibraryName(), this.getFullyQualifiedClass(), null, "compositeLibraryName", null, 0, 1, FaceletTaglib.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFaceletTaglib_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, FaceletTaglib.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglib_Tag(), this.getFaceletTaglibTag(), null, "tag", null, 0, -1, FaceletTaglib.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglib_Function(), this.getFaceletTaglibFunction(), null, "function", null, 0, -1, FaceletTaglib.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getFaceletTaglib_TaglibExtension(), this.getFaceletTaglibExtension(), null, "taglibExtension", null, 0, -1, FaceletTaglib.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFaceletTaglib_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, FaceletTaglib.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFaceletTaglib_Version(), this.getFaceletTaglibVersion(), "version", null, 1, 1, FaceletTaglib.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFaceletTaglib_NamespaceUri(), theXMLTypePackage.getToken(), "namespaceUri", null, 0, 1, FaceletTaglib.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFaceletTaglib_ShortName(), ecorePackage.getEString(), "shortName", null, 0, 1, FaceletTaglib.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(fullyQualifiedClassEClass, FullyQualifiedClass.class, "FullyQualifiedClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(genericBooleanEClass, GenericBoolean.class, "GenericBoolean", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(iconEClass, Icon.class, "Icon", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getIcon_SmallIcon(), this.getPath(), null, "smallIcon", null, 0, 1, Icon.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getIcon_LargeIcon(), this.getPath(), null, "largeIcon", null, 0, 1, Icon.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getIcon_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, Icon.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getIcon_Lang(), ecorePackage.getEString(), "lang", null, 0, 1, Icon.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(javaIdentifierEClass, JavaIdentifier.class, "JavaIdentifier", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(pathEClass, Path.class, "Path", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(identifiableStringValueEClass, IdentifiableStringValue.class, "IdentifiableStringValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getIdentifiableStringValue_Value(), theXMLTypePackage.getToken(), "value", null, 0, 1, IdentifiableStringValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getIdentifiableStringValue_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, IdentifiableStringValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(identifiableLangStringValueEClass, IdentifiableLangStringValue.class, "IdentifiableLangStringValue", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getIdentifiableLangStringValue_Lang(), ecorePackage.getEString(), "lang", null, 0, 1, IdentifiableLangStringValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(userVisibleTaglibObjectEClass, UserVisibleTaglibObject.class, "UserVisibleTaglibObject", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getUserVisibleTaglibObject_Description(), this.getDescription(), null, "description", null, 0, -1, UserVisibleTaglibObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getUserVisibleTaglibObject_DisplayName(), this.getDisplayName(), null, "displayName", null, 0, -1, UserVisibleTaglibObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getUserVisibleTaglibObject_Icon(), this.getIcon(), null, "icon", null, 0, -1, UserVisibleTaglibObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		EOperation op = addEOperation(userVisibleTaglibObjectEClass, ecorePackage.getEString(), "getDescription", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEString(), "language", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEString(), "separationString", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = addEOperation(userVisibleTaglibObjectEClass, ecorePackage.getEString(), "getDefaultDescription", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEString(), "separationString", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = addEOperation(userVisibleTaglibObjectEClass, ecorePackage.getEString(), "getDisplayName", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEString(), "language", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEString(), "separationString", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = addEOperation(userVisibleTaglibObjectEClass, ecorePackage.getEString(), "getDefaultDisplayName", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEString(), "separationString", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		// Initialize enums and add enum literals
		initEEnum(faceletTaglibVersionEEnum, FaceletTaglibVersion.class, "FaceletTaglibVersion"); //$NON-NLS-1$
		addEEnumLiteral(faceletTaglibVersionEEnum, FaceletTaglibVersion._20);
		addEEnumLiteral(faceletTaglibVersionEEnum, FaceletTaglibVersion._21);
		addEEnumLiteral(faceletTaglibVersionEEnum, FaceletTaglibVersion._22);
		addEEnumLiteral(faceletTaglibVersionEEnum, FaceletTaglibVersion._23);
		addEEnumLiteral(faceletTaglibVersionEEnum, FaceletTaglibVersion._30);
		addEEnumLiteral(faceletTaglibVersionEEnum, FaceletTaglibVersion._40);
		addEEnumLiteral(faceletTaglibVersionEEnum, FaceletTaglibVersion._41);

		initEEnum(genericBooleanBaseEEnum, GenericBooleanBase.class, "GenericBooleanBase"); //$NON-NLS-1$
		addEEnumLiteral(genericBooleanBaseEEnum, GenericBooleanBase.TRUE);
		addEEnumLiteral(genericBooleanBaseEEnum, GenericBooleanBase.FALSE);
		addEEnumLiteral(genericBooleanBaseEEnum, GenericBooleanBase.YES);
		addEEnumLiteral(genericBooleanBaseEEnum, GenericBooleanBase.NO);

		// Initialize data types
		initEDataType(faceletTaglibVersionTypeObjectEDataType, FaceletTaglibVersion.class, "FaceletTaglibVersionTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(fullyQualifiedClassBaseEDataType, String.class, "FullyQualifiedClassBase", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(javaIdentifierBaseEDataType, String.class, "JavaIdentifierBase", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(pathTypeBaseEDataType, String.class, "PathTypeBase", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

    /**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void createExtendedMetaDataAnnotations()
    {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData"; //$NON-NLS-1$
		addAnnotation
		  (descriptionEClass,
		   source,
		   new String[] {
			   "name", "descriptionType", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getDescription_Any(),
		   source,
		   new String[] {
			   "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
			   "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getDescription_Id(),
		   source,
		   new String[] {
			   "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getDescription_Lang(),
		   source,
		   new String[] {
			   "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "lang", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "http://www.w3.org/XML/1998/namespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (displayNameEClass,
		   source,
		   new String[] {
			   "name", "display-nameType", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (documentRootEClass,
		   source,
		   new String[] {
			   "name", "", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "mixed" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getDocumentRoot_Mixed(),
		   source,
		   new String[] {
			   "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", ":mixed" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getDocumentRoot_XMLNSPrefixMap(),
		   source,
		   new String[] {
			   "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "xmlns:prefix" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getDocumentRoot_XSISchemaLocation(),
		   source,
		   new String[] {
			   "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "xsi:schemaLocation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getDocumentRoot_FaceletTaglib(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "facelet-taglib", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (faceletTaglibCanonicalNameEClass,
		   source,
		   new String[] {
			   "name", "facelet-taglib-canonical-nameType", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibCanonicalName_Value(),
		   source,
		   new String[] {
			   "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibCanonicalName_Id(),
		   source,
		   new String[] {
			   "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (faceletTaglibExtensionEClass,
		   source,
		   new String[] {
			   "name", "facelet-taglib-extensionType", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibExtension_Any(),
		   source,
		   new String[] {
			   "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
			   "wildcards", "##any", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
			   "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibExtension_Id(),
		   source,
		   new String[] {
			   "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (faceletTaglibFunctionEClass,
		   source,
		   new String[] {
			   "name", "facelet-taglib-functionType", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibFunction_FunctionName(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "function-name", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibFunction_FunctionClass(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "function-class", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibFunction_FunctionSignature(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "function-signature", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (faceletTaglibTagAttributeEClass,
		   source,
		   new String[] {
			   "name", "facelet-taglib-tag-attributeType", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagAttribute_NameElement(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "name", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagAttribute_RequiredElement(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "required", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagAttribute_TypeElement(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "type", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagAttribute_MethodSignatureElement(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "method-signature", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagAttribute_Id(),
		   source,
		   new String[] {
			   "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (faceletTaglibTagBehaviorExtensionEClass,
		   source,
		   new String[] {
			   "name", "facelet-taglib-tag-behavior-extensionType", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagBehaviorExtension_Any(),
		   source,
		   new String[] {
			   "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
			   "wildcards", "##any", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
			   "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagBehaviorExtension_Id(),
		   source,
		   new String[] {
			   "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (faceletTaglibTagBehaviorEClass,
		   source,
		   new String[] {
			   "name", "facelet-taglib-tag-behaviorType", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagBehavior_BehaviorId(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "behavior-id", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagBehavior_HandlerClass(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "handler-class", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagBehavior_BehaviorExtension(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "behavior-extension", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (faceletTaglibTagComponentExtensionEClass,
		   source,
		   new String[] {
			   "name", "facelet-taglib-tag-component-extensionType", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagComponentExtension_Any(),
		   source,
		   new String[] {
			   "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
			   "wildcards", "##any", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
			   "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagComponentExtension_Id(),
		   source,
		   new String[] {
			   "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (faceletTaglibTagComponentEClass,
		   source,
		   new String[] {
			   "name", "facelet-taglib-tag-componentType", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagComponent_ComponentType(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "component-type", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagComponent_RendererType(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "renderer-type", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagComponent_HandlerClass(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "handler-class", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagComponent_ComponentExtension(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "component-extension", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (faceletTaglibTagConverterExtensionEClass,
		   source,
		   new String[] {
			   "name", "facelet-taglib-tag-converter-extensionType", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagConverterExtension_Any(),
		   source,
		   new String[] {
			   "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
			   "wildcards", "##any", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
			   "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagConverterExtension_Id(),
		   source,
		   new String[] {
			   "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (faceletTaglibTagConverterEClass,
		   source,
		   new String[] {
			   "name", "facelet-taglib-tag-converterType", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagConverter_ConverterId(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "converter-id", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagConverter_HandlerClass(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "handler-class", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagConverter_ConverterExtension(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "converter-extension", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (faceletTaglibTagExtensionEClass,
		   source,
		   new String[] {
			   "name", "facelet-taglib-tag-extensionType", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagExtension_Any(),
		   source,
		   new String[] {
			   "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
			   "wildcards", "##any", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
			   "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagExtension_Id(),
		   source,
		   new String[] {
			   "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (faceletTaglibTagEClass,
		   source,
		   new String[] {
			   "name", "facelet-taglib-tagType", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTag_TagNameElement(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "tag-name", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTag_HandlerClassElement(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "handler-class", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTag_Behavior(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "behavior", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTag_Component(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "component", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTag_Converter(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "converter", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTag_Validator(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "validator", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTag_Source(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "source", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTag_Attribute(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTag_TagExtension(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "tag-extension", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (faceletTaglibTagValidatorExtensionEClass,
		   source,
		   new String[] {
			   "name", "facelet-taglib-tag-validator-extensionType", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagValidatorExtension_Any(),
		   source,
		   new String[] {
			   "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
			   "wildcards", "##any", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
			   "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagValidatorExtension_Id(),
		   source,
		   new String[] {
			   "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (faceletTaglibTagValidatorEClass,
		   source,
		   new String[] {
			   "name", "facelet-taglib-tag-validatorType", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (faceletTaglibTagValidatorEClass,
		   new boolean[] { true },
		   "http:///org/eclipse/emf/ecore/util/ExtendedMetaData", //$NON-NLS-1$
		   new String[] {
			   "name", "facelet-taglib-tag-validatorType", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagValidator_ValidatorId(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "validator-id", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagValidator_HandlerClass(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "handler-class", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglibTagValidator_ValidatorExtension(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "validator-extension", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (faceletTaglibEClass,
		   source,
		   new String[] {
			   "name", "facelet-taglibType", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglib_LibraryClass(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "library-class", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglib_Namespace(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "namespace", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglib_CompositeLibraryName(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "composite-library-name", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglib_Group(),
		   source,
		   new String[] {
			   "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "group:6" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglib_Tag(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "tag", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			   "group", "#group:6" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglib_Function(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "function", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			   "group", "#group:6" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglib_TaglibExtension(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "taglib-extension", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglib_Id(),
		   source,
		   new String[] {
			   "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglib_Version(),
		   source,
		   new String[] {
			   "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "version" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getFaceletTaglib_ShortName(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "short-name", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (faceletTaglibVersionEEnum,
		   source,
		   new String[] {
			   "name", "facelet-taglib-versionType" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (faceletTaglibVersionTypeObjectEDataType,
		   source,
		   new String[] {
			   "name", "facelet-taglib-versionType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			   "baseType", "facelet-taglib-versionType" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (fullyQualifiedClassEClass,
		   source,
		   new String[] {
			   "name", "fully-qualified-classType", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (fullyQualifiedClassBaseEDataType,
		   source,
		   new String[] {
			   "name", "fully-qualified-classType_._base", //$NON-NLS-1$ //$NON-NLS-2$
			   "baseType", "http://www.eclipse.org/emf/2003/XMLType#token" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (genericBooleanEClass,
		   source,
		   new String[] {
			   "name", "generic-booleanType", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (genericBooleanBaseEEnum,
		   source,
		   new String[] {
			   "name", "generic-booleanType_._base" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (iconEClass,
		   source,
		   new String[] {
			   "name", "iconType", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getIcon_SmallIcon(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "small-icon", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getIcon_LargeIcon(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "large-icon", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getIcon_Id(),
		   source,
		   new String[] {
			   "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getIcon_Lang(),
		   source,
		   new String[] {
			   "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "lang", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "http://www.w3.org/XML/1998/namespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (javaIdentifierEClass,
		   source,
		   new String[] {
			   "name", "java-identifierType", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (javaIdentifierBaseEDataType,
		   source,
		   new String[] {
			   "name", "java-identifierType_._base", //$NON-NLS-1$ //$NON-NLS-2$
			   "baseType", "http://www.eclipse.org/emf/2003/XMLType#token", //$NON-NLS-1$ //$NON-NLS-2$
			   "pattern", "($|_|\\p{L})(\\p{L}|\\p{Nd}|_|$)*" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (pathEClass,
		   source,
		   new String[] {
			   "name", "pathType", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (pathTypeBaseEDataType,
		   source,
		   new String[] {
			   "name", "pathType_._base", //$NON-NLS-1$ //$NON-NLS-2$
			   "baseType", "http://www.eclipse.org/emf/2003/XMLType#token" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (identifiableStringValueEClass,
		   source,
		   new String[] {
			   "name", "string", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getIdentifiableStringValue_Value(),
		   source,
		   new String[] {
			   "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
			   "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getIdentifiableStringValue_Id(),
		   source,
		   new String[] {
			   "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getIdentifiableLangStringValue_Lang(),
		   source,
		   new String[] {
			   "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "lang", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "http://www.w3.org/XML/1998/namespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getUserVisibleTaglibObject_Description(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getUserVisibleTaglibObject_DisplayName(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getUserVisibleTaglibObject_Icon(),
		   source,
		   new String[] {
			   "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			   "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
			   "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });
	}

} //FaceletTaglibPackageImpl
