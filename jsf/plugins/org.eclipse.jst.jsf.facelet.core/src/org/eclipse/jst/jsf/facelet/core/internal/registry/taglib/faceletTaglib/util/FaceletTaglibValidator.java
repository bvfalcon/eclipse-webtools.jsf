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
 * $Id: FaceletTaglibValidator.java,v 1.1 2010/03/18 06:24:40 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.util;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeValidator;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.*;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Description;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DisplayName;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DocumentRoot;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibCanonicalName;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibExtension;
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

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage
 * @generated
 */
public class FaceletTaglibValidator extends EObjectValidator
{
    /**
	 * The cached model package
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @SuppressWarnings("hiding")
    public static final FaceletTaglibValidator INSTANCE = new FaceletTaglibValidator();

    /**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
    @SuppressWarnings("hiding")
    public static final String DIAGNOSTIC_SOURCE = "org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib"; //$NON-NLS-1$

    /**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

    /**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

    /**
	 * The cached base package validator.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected XMLTypeValidator xmlTypeValidator;

    /**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglibValidator()
    {
		super();
		xmlTypeValidator = XMLTypeValidator.INSTANCE;
	}

    /**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EPackage getEPackage()
    {
	  return FaceletTaglibPackage.eINSTANCE;
	}

    /**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		switch (classifierID) {
			case FaceletTaglibPackage.DESCRIPTION:
				return validateDescription((Description)value, diagnostics, context);
			case FaceletTaglibPackage.DISPLAY_NAME:
				return validateDisplayName((DisplayName)value, diagnostics, context);
			case FaceletTaglibPackage.DOCUMENT_ROOT:
				return validateDocumentRoot((DocumentRoot)value, diagnostics, context);
			case FaceletTaglibPackage.FACELET_TAGLIB_CANONICAL_NAME:
				return validateFaceletTaglibCanonicalName((FaceletTaglibCanonicalName)value, diagnostics, context);
			case FaceletTaglibPackage.FACELET_TAGLIB_EXTENSION:
				return validateFaceletTaglibExtension((FaceletTaglibExtension)value, diagnostics, context);
			case FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION:
				return validateFaceletTaglibFunction((FaceletTaglibFunction)value, diagnostics, context);
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE:
				return validateFaceletTaglibTagAttribute((FaceletTaglibTagAttribute)value, diagnostics, context);
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR_EXTENSION:
				return validateFaceletTaglibTagBehaviorExtension((FaceletTaglibTagBehaviorExtension)value, diagnostics, context);
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR:
				return validateFaceletTaglibTagBehavior((FaceletTaglibTagBehavior)value, diagnostics, context);
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT_EXTENSION:
				return validateFaceletTaglibTagComponentExtension((FaceletTaglibTagComponentExtension)value, diagnostics, context);
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT:
				return validateFaceletTaglibTagComponent((FaceletTaglibTagComponent)value, diagnostics, context);
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER_EXTENSION:
				return validateFaceletTaglibTagConverterExtension((FaceletTaglibTagConverterExtension)value, diagnostics, context);
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER:
				return validateFaceletTaglibTagConverter((FaceletTaglibTagConverter)value, diagnostics, context);
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_EXTENSION:
				return validateFaceletTaglibTagExtension((FaceletTaglibTagExtension)value, diagnostics, context);
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG:
				return validateFaceletTaglibTag((FaceletTaglibTag)value, diagnostics, context);
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR_EXTENSION:
				return validateFaceletTaglibTagValidatorExtension((FaceletTaglibTagValidatorExtension)value, diagnostics, context);
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR:
				return validateFaceletTaglibTagValidator((FaceletTaglibTagValidator)value, diagnostics, context);
			case FaceletTaglibPackage.FACELET_TAGLIB:
				return validateFaceletTaglib((FaceletTaglib)value, diagnostics, context);
			case FaceletTaglibPackage.FULLY_QUALIFIED_CLASS:
				return validateFullyQualifiedClass((FullyQualifiedClass)value, diagnostics, context);
			case FaceletTaglibPackage.GENERIC_BOOLEAN:
				return validateGenericBoolean((GenericBoolean)value, diagnostics, context);
			case FaceletTaglibPackage.ICON:
				return validateIcon((Icon)value, diagnostics, context);
			case FaceletTaglibPackage.JAVA_IDENTIFIER:
				return validateJavaIdentifier((JavaIdentifier)value, diagnostics, context);
			case FaceletTaglibPackage.PATH:
				return validatePath((Path)value, diagnostics, context);
			case FaceletTaglibPackage.IDENTIFIABLE_STRING_VALUE:
				return validateIdentifiableStringValue((IdentifiableStringValue)value, diagnostics, context);
			case FaceletTaglibPackage.IDENTIFIABLE_LANG_STRING_VALUE:
				return validateIdentifiableLangStringValue((IdentifiableLangStringValue)value, diagnostics, context);
			case FaceletTaglibPackage.USER_VISIBLE_TAGLIB_OBJECT:
				return validateUserVisibleTaglibObject((UserVisibleTaglibObject)value, diagnostics, context);
			case FaceletTaglibPackage.FACELET_TAGLIB_VERSION:
				return validateFaceletTaglibVersion((FaceletTaglibVersion)value, diagnostics, context);
			case FaceletTaglibPackage.GENERIC_BOOLEAN_BASE:
				return validateGenericBooleanBase((GenericBooleanBase)value, diagnostics, context);
			case FaceletTaglibPackage.FACELET_TAGLIB_VERSION_TYPE_OBJECT:
				return validateFaceletTaglibVersionTypeObject((FaceletTaglibVersion)value, diagnostics, context);
			case FaceletTaglibPackage.FULLY_QUALIFIED_CLASS_BASE:
				return validateFullyQualifiedClassBase((String)value, diagnostics, context);
			case FaceletTaglibPackage.JAVA_IDENTIFIER_BASE:
				return validateJavaIdentifierBase((String)value, diagnostics, context);
			case FaceletTaglibPackage.PATH_TYPE_BASE:
				return validatePathTypeBase((String)value, diagnostics, context);
			default:
				return true;
		}
	}

    /**
	 * <!-- begin-user-doc -->
     * @param description 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateDescription(Description description, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(description, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param displayName 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateDisplayName(DisplayName displayName, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(displayName, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param documentRoot 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateDocumentRoot(DocumentRoot documentRoot, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(documentRoot, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param faceletTaglibCanonicalName 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateFaceletTaglibCanonicalName(FaceletTaglibCanonicalName faceletTaglibCanonicalName, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(faceletTaglibCanonicalName, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param faceletTaglibExtension 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateFaceletTaglibExtension(FaceletTaglibExtension faceletTaglibExtension, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(faceletTaglibExtension, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param faceletTaglibFunction 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateFaceletTaglibFunction(FaceletTaglibFunction faceletTaglibFunction, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(faceletTaglibFunction, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param faceletTaglibTagAttribute 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateFaceletTaglibTagAttribute(FaceletTaglibTagAttribute faceletTaglibTagAttribute, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(faceletTaglibTagAttribute, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param faceletTaglibTagBehaviorExtension 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateFaceletTaglibTagBehaviorExtension(FaceletTaglibTagBehaviorExtension faceletTaglibTagBehaviorExtension, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(faceletTaglibTagBehaviorExtension, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param faceletTaglibTagBehavior 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateFaceletTaglibTagBehavior(FaceletTaglibTagBehavior faceletTaglibTagBehavior, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(faceletTaglibTagBehavior, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param faceletTaglibTagComponentExtension 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateFaceletTaglibTagComponentExtension(FaceletTaglibTagComponentExtension faceletTaglibTagComponentExtension, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(faceletTaglibTagComponentExtension, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param faceletTaglibTagComponent 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateFaceletTaglibTagComponent(FaceletTaglibTagComponent faceletTaglibTagComponent, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(faceletTaglibTagComponent, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param faceletTaglibTagConverterExtension 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateFaceletTaglibTagConverterExtension(FaceletTaglibTagConverterExtension faceletTaglibTagConverterExtension, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(faceletTaglibTagConverterExtension, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param faceletTaglibTagConverter 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateFaceletTaglibTagConverter(FaceletTaglibTagConverter faceletTaglibTagConverter, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(faceletTaglibTagConverter, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param faceletTaglibTagExtension 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateFaceletTaglibTagExtension(FaceletTaglibTagExtension faceletTaglibTagExtension, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(faceletTaglibTagExtension, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param faceletTaglibTag 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateFaceletTaglibTag(FaceletTaglibTag faceletTaglibTag, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(faceletTaglibTag, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param faceletTaglibTagValidatorExtension 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateFaceletTaglibTagValidatorExtension(FaceletTaglibTagValidatorExtension faceletTaglibTagValidatorExtension, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(faceletTaglibTagValidatorExtension, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param faceletTaglibTagValidator 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateFaceletTaglibTagValidator(FaceletTaglibTagValidator faceletTaglibTagValidator, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(faceletTaglibTagValidator, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param faceletTaglib 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateFaceletTaglib(FaceletTaglib faceletTaglib, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(faceletTaglib, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param fullyQualifiedClass 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateFullyQualifiedClass(FullyQualifiedClass fullyQualifiedClass, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(fullyQualifiedClass, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param genericBoolean 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateGenericBoolean(GenericBoolean genericBoolean, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(genericBoolean, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param icon 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateIcon(Icon icon, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(icon, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param javaIdentifier 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateJavaIdentifier(JavaIdentifier javaIdentifier, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(javaIdentifier, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param path 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validatePath(Path path, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(path, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param identifiableStringValue 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateIdentifiableStringValue(IdentifiableStringValue identifiableStringValue, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(identifiableStringValue, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param identifiableLangStringValue 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateIdentifiableLangStringValue(IdentifiableLangStringValue identifiableLangStringValue, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(identifiableLangStringValue, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param userVisibleTaglibObject 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateUserVisibleTaglibObject(UserVisibleTaglibObject userVisibleTaglibObject, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validate_EveryDefaultConstraint(userVisibleTaglibObject, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param faceletTaglibVersion 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateFaceletTaglibVersion(FaceletTaglibVersion faceletTaglibVersion, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return true;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param genericBooleanBase 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateGenericBooleanBase(GenericBooleanBase genericBooleanBase, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return true;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param faceletTaglibVersionTypeObject 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateFaceletTaglibVersionTypeObject(FaceletTaglibVersion faceletTaglibVersionTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return true;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param fullyQualifiedClassBase 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateFullyQualifiedClassBase(String fullyQualifiedClassBase, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return true;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param javaIdentifierBase 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateJavaIdentifierBase(String javaIdentifierBase, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		boolean result = validateJavaIdentifierBase_Pattern(javaIdentifierBase, diagnostics, context);
		return result;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated NOT
	 * @see #validateJavaIdentifierBase_Pattern
	 */
    public static final  PatternMatcher [][] JAVA_IDENTIFIER_BASE__PATTERN__VALUES =
        new PatternMatcher [][] {
			new PatternMatcher [] {
				XMLTypeUtil.createPatternMatcher("($|_|\\p{L})(\\p{L}|\\p{Nd}|_|$)*") //$NON-NLS-1$
			}
		};

    /**
	 * Validates the Pattern constraint of '<em>Java Identifier Base</em>'.
	 * <!-- begin-user-doc -->
     * @param javaIdentifierBase 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validateJavaIdentifierBase_Pattern(String javaIdentifierBase, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return validatePattern(FaceletTaglibPackage.Literals.JAVA_IDENTIFIER_BASE, javaIdentifierBase, JAVA_IDENTIFIER_BASE__PATTERN__VALUES, diagnostics, context);
	}

    /**
	 * <!-- begin-user-doc -->
     * @param pathTypeBase 
     * @param diagnostics 
     * @param context 
     * @return 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean validatePathTypeBase(String pathTypeBase, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
		return true;
	}

    /**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public ResourceLocator getResourceLocator()
    {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //FaceletTaglibValidator
