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
 * $Id: FaceletTaglibFactoryImpl.java,v 1.2 2010/03/18 06:24:39 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.*;
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
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableStringValue;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.JavaIdentifier;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Path;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FaceletTaglibFactoryImpl extends EFactoryImpl implements FaceletTaglibFactory
{
    /**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static FaceletTaglibFactory init()
    {
		try {
			FaceletTaglibFactory theFaceletTaglibFactory = (FaceletTaglibFactory)EPackage.Registry.INSTANCE.getEFactory(FaceletTaglibPackage.eNS_URI);
			if (theFaceletTaglibFactory != null) {
				return theFaceletTaglibFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new FaceletTaglibFactoryImpl();
	}

    /**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglibFactoryImpl()
    {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public EObject create(EClass eClass)
    {
		switch (eClass.getClassifierID()) {
			case FaceletTaglibPackage.DESCRIPTION: return createDescription();
			case FaceletTaglibPackage.DISPLAY_NAME: return createDisplayName();
			case FaceletTaglibPackage.DOCUMENT_ROOT: return createDocumentRoot();
			case FaceletTaglibPackage.FACELET_TAGLIB_CANONICAL_NAME: return createFaceletTaglibCanonicalName();
			case FaceletTaglibPackage.FACELET_TAGLIB_EXTENSION: return createFaceletTaglibExtension();
			case FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION: return createFaceletTaglibFunction();
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE: return createFaceletTaglibTagAttribute();
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR_EXTENSION: return createFaceletTaglibTagBehaviorExtension();
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR: return createFaceletTaglibTagBehavior();
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT_EXTENSION: return createFaceletTaglibTagComponentExtension();
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT: return createFaceletTaglibTagComponent();
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER_EXTENSION: return createFaceletTaglibTagConverterExtension();
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER: return createFaceletTaglibTagConverter();
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_EXTENSION: return createFaceletTaglibTagExtension();
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG: return createFaceletTaglibTag();
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR_EXTENSION: return createFaceletTaglibTagValidatorExtension();
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR: return createFaceletTaglibTagValidator();
			case FaceletTaglibPackage.FACELET_TAGLIB: return createFaceletTaglib();
			case FaceletTaglibPackage.FULLY_QUALIFIED_CLASS: return createFullyQualifiedClass();
			case FaceletTaglibPackage.GENERIC_BOOLEAN: return createGenericBoolean();
			case FaceletTaglibPackage.ICON: return createIcon();
			case FaceletTaglibPackage.JAVA_IDENTIFIER: return createJavaIdentifier();
			case FaceletTaglibPackage.PATH: return createPath();
			case FaceletTaglibPackage.IDENTIFIABLE_STRING_VALUE: return createIdentifiableStringValue();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue)
    {
		switch (eDataType.getClassifierID()) {
			case FaceletTaglibPackage.FACELET_TAGLIB_VERSION:
				return createFaceletTaglibVersionFromString(eDataType, initialValue);
			case FaceletTaglibPackage.GENERIC_BOOLEAN_BASE:
				return createGenericBooleanBaseFromString(eDataType, initialValue);
			case FaceletTaglibPackage.FACELET_TAGLIB_VERSION_TYPE_OBJECT:
				return createFaceletTaglibVersionTypeObjectFromString(eDataType, initialValue);
			case FaceletTaglibPackage.FULLY_QUALIFIED_CLASS_BASE:
				return createFullyQualifiedClassBaseFromString(eDataType, initialValue);
			case FaceletTaglibPackage.JAVA_IDENTIFIER_BASE:
				return createJavaIdentifierBaseFromString(eDataType, initialValue);
			case FaceletTaglibPackage.PATH_TYPE_BASE:
				return createPathTypeBaseFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue)
    {
		switch (eDataType.getClassifierID()) {
			case FaceletTaglibPackage.FACELET_TAGLIB_VERSION:
				return convertFaceletTaglibVersionToString(eDataType, instanceValue);
			case FaceletTaglibPackage.GENERIC_BOOLEAN_BASE:
				return convertGenericBooleanBaseToString(eDataType, instanceValue);
			case FaceletTaglibPackage.FACELET_TAGLIB_VERSION_TYPE_OBJECT:
				return convertFaceletTaglibVersionTypeObjectToString(eDataType, instanceValue);
			case FaceletTaglibPackage.FULLY_QUALIFIED_CLASS_BASE:
				return convertFullyQualifiedClassBaseToString(eDataType, instanceValue);
			case FaceletTaglibPackage.JAVA_IDENTIFIER_BASE:
				return convertJavaIdentifierBaseToString(eDataType, instanceValue);
			case FaceletTaglibPackage.PATH_TYPE_BASE:
				return convertPathTypeBaseToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Description createDescription()
    {
		DescriptionImpl description = new DescriptionImpl();
		return description;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DisplayName createDisplayName()
    {
		DisplayNameImpl displayName = new DisplayNameImpl();
		return displayName;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DocumentRoot createDocumentRoot()
    {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglibCanonicalName createFaceletTaglibCanonicalName()
    {
		FaceletTaglibCanonicalNameImpl faceletTaglibCanonicalName = new FaceletTaglibCanonicalNameImpl();
		return faceletTaglibCanonicalName;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglibExtension createFaceletTaglibExtension()
    {
		FaceletTaglibExtensionImpl faceletTaglibExtension = new FaceletTaglibExtensionImpl();
		return faceletTaglibExtension;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglibFunction createFaceletTaglibFunction()
    {
		FaceletTaglibFunctionImpl faceletTaglibFunction = new FaceletTaglibFunctionImpl();
		return faceletTaglibFunction;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglibTagAttribute createFaceletTaglibTagAttribute()
    {
		FaceletTaglibTagAttributeImpl faceletTaglibTagAttribute = new FaceletTaglibTagAttributeImpl();
		return faceletTaglibTagAttribute;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglibTagBehaviorExtension createFaceletTaglibTagBehaviorExtension()
    {
		FaceletTaglibTagBehaviorExtensionImpl faceletTaglibTagBehaviorExtension = new FaceletTaglibTagBehaviorExtensionImpl();
		return faceletTaglibTagBehaviorExtension;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglibTagBehavior createFaceletTaglibTagBehavior()
    {
		FaceletTaglibTagBehaviorImpl faceletTaglibTagBehavior = new FaceletTaglibTagBehaviorImpl();
		return faceletTaglibTagBehavior;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglibTagComponentExtension createFaceletTaglibTagComponentExtension()
    {
		FaceletTaglibTagComponentExtensionImpl faceletTaglibTagComponentExtension = new FaceletTaglibTagComponentExtensionImpl();
		return faceletTaglibTagComponentExtension;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglibTagComponent createFaceletTaglibTagComponent()
    {
		FaceletTaglibTagComponentImpl faceletTaglibTagComponent = new FaceletTaglibTagComponentImpl();
		return faceletTaglibTagComponent;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglibTagConverterExtension createFaceletTaglibTagConverterExtension()
    {
		FaceletTaglibTagConverterExtensionImpl faceletTaglibTagConverterExtension = new FaceletTaglibTagConverterExtensionImpl();
		return faceletTaglibTagConverterExtension;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglibTagConverter createFaceletTaglibTagConverter()
    {
		FaceletTaglibTagConverterImpl faceletTaglibTagConverter = new FaceletTaglibTagConverterImpl();
		return faceletTaglibTagConverter;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglibTagExtension createFaceletTaglibTagExtension()
    {
		FaceletTaglibTagExtensionImpl faceletTaglibTagExtension = new FaceletTaglibTagExtensionImpl();
		return faceletTaglibTagExtension;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglibTag createFaceletTaglibTag()
    {
		FaceletTaglibTagImpl faceletTaglibTag = new FaceletTaglibTagImpl();
		return faceletTaglibTag;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglibTagValidatorExtension createFaceletTaglibTagValidatorExtension()
    {
		FaceletTaglibTagValidatorExtensionImpl faceletTaglibTagValidatorExtension = new FaceletTaglibTagValidatorExtensionImpl();
		return faceletTaglibTagValidatorExtension;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglibTagValidator createFaceletTaglibTagValidator()
    {
		FaceletTaglibTagValidatorImpl faceletTaglibTagValidator = new FaceletTaglibTagValidatorImpl();
		return faceletTaglibTagValidator;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglib createFaceletTaglib()
    {
		FaceletTaglibImpl faceletTaglib = new FaceletTaglibImpl();
		return faceletTaglib;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FullyQualifiedClass createFullyQualifiedClass()
    {
		FullyQualifiedClassImpl fullyQualifiedClass = new FullyQualifiedClassImpl();
		return fullyQualifiedClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public GenericBoolean createGenericBoolean()
    {
		GenericBooleanImpl genericBoolean = new GenericBooleanImpl();
		return genericBoolean;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Icon createIcon()
    {
		IconImpl icon = new IconImpl();
		return icon;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public JavaIdentifier createJavaIdentifier()
    {
		JavaIdentifierImpl javaIdentifier = new JavaIdentifierImpl();
		return javaIdentifier;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Path createPath()
    {
		PathImpl path = new PathImpl();
		return path;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public IdentifiableStringValue createIdentifiableStringValue()
    {
		IdentifiableStringValueImpl identifiableStringValue = new IdentifiableStringValueImpl();
		return identifiableStringValue;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param eDataType 
     * @param initialValue 
     * @return the version
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglibVersion createFaceletTaglibVersionFromString(EDataType eDataType, String initialValue)
    {
		FaceletTaglibVersion result = FaceletTaglibVersion.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param eDataType 
     * @param instanceValue 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertFaceletTaglibVersionToString(EDataType eDataType, Object instanceValue)
    {
		return instanceValue == null ? null : instanceValue.toString();
	}

    /**
	 * <!-- begin-user-doc -->
     * @param eDataType 
     * @param initialValue 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public GenericBooleanBase createGenericBooleanBaseFromString(EDataType eDataType, String initialValue)
    {
		GenericBooleanBase result = GenericBooleanBase.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param eDataType 
     * @param instanceValue 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertGenericBooleanBaseToString(EDataType eDataType, Object instanceValue)
    {
		return instanceValue == null ? null : instanceValue.toString();
	}

    /**
	 * <!-- begin-user-doc -->
     * @param eDataType 
     * @param initialValue 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglibVersion createFaceletTaglibVersionTypeObjectFromString(EDataType eDataType, String initialValue)
    {
		return createFaceletTaglibVersionFromString(FaceletTaglibPackage.Literals.FACELET_TAGLIB_VERSION, initialValue);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param eDataType 
     * @param instanceValue 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertFaceletTaglibVersionTypeObjectToString(EDataType eDataType, Object instanceValue)
    {
		return convertFaceletTaglibVersionToString(FaceletTaglibPackage.Literals.FACELET_TAGLIB_VERSION, instanceValue);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param eDataType 
     * @param initialValue 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String createFullyQualifiedClassBaseFromString(EDataType eDataType, String initialValue)
    {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.TOKEN, initialValue);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param eDataType 
     * @param instanceValue 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertFullyQualifiedClassBaseToString(EDataType eDataType, Object instanceValue)
    {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.TOKEN, instanceValue);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param eDataType 
     * @param initialValue 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String createJavaIdentifierBaseFromString(EDataType eDataType, String initialValue)
    {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.TOKEN, initialValue);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param eDataType 
     * @param instanceValue 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertJavaIdentifierBaseToString(EDataType eDataType, Object instanceValue)
    {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.TOKEN, instanceValue);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param eDataType 
     * @param initialValue 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String createPathTypeBaseFromString(EDataType eDataType, String initialValue)
    {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.TOKEN, initialValue);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param eDataType 
     * @param instanceValue 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertPathTypeBaseToString(EDataType eDataType, Object instanceValue)
    {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.TOKEN, instanceValue);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglibPackage getFaceletTaglibPackage()
    {
		return (FaceletTaglibPackage)getEPackage();
	}

    /**
	 * <!-- begin-user-doc -->
     * @return 
     * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
    @Deprecated
    public static FaceletTaglibPackage getPackage()
    {
		return FaceletTaglibPackage.eINSTANCE;
	}

} //FaceletTaglibFactoryImpl
