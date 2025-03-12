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
 * $Id: FaceletTaglibSwitch.java,v 1.2 2010/03/18 06:24:41 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
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
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FullyQualifiedClass;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.GenericBoolean;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableLangStringValue;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableStringValue;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.JavaIdentifier;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Path;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.UserVisibleTaglibObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * @param <T> 
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage
 * @generated
 */
public class FaceletTaglibSwitch<T> extends Switch<T>
{
    /**
	 * The cached model package
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected static FaceletTaglibPackage modelPackage;

    /**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglibSwitch()
    {
		if (modelPackage == null) {
			modelPackage = FaceletTaglibPackage.eINSTANCE;
		}
	}

    /**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

				/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
     * @param classifierID 
     * @param theEObject 
     * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
    @Override
				protected T doSwitch(int classifierID, EObject theEObject)
    {
		switch (classifierID) {
			case FaceletTaglibPackage.DESCRIPTION: {
				Description description = (Description)theEObject;
				T result = caseDescription(description);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.DISPLAY_NAME: {
				DisplayName displayName = (DisplayName)theEObject;
				T result = caseDisplayName(displayName);
				if (result == null) result = caseIdentifiableLangStringValue(displayName);
				if (result == null) result = caseIdentifiableStringValue(displayName);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.DOCUMENT_ROOT: {
				DocumentRoot documentRoot = (DocumentRoot)theEObject;
				T result = caseDocumentRoot(documentRoot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.FACELET_TAGLIB_CANONICAL_NAME: {
				FaceletTaglibCanonicalName faceletTaglibCanonicalName = (FaceletTaglibCanonicalName)theEObject;
				T result = caseFaceletTaglibCanonicalName(faceletTaglibCanonicalName);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.FACELET_TAGLIB_EXTENSION: {
				FaceletTaglibExtension faceletTaglibExtension = (FaceletTaglibExtension)theEObject;
				T result = caseFaceletTaglibExtension(faceletTaglibExtension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION: {
				FaceletTaglibFunction faceletTaglibFunction = (FaceletTaglibFunction)theEObject;
				T result = caseFaceletTaglibFunction(faceletTaglibFunction);
				if (result == null) result = caseUserVisibleTaglibObject(faceletTaglibFunction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE: {
				FaceletTaglibTagAttribute faceletTaglibTagAttribute = (FaceletTaglibTagAttribute)theEObject;
				T result = caseFaceletTaglibTagAttribute(faceletTaglibTagAttribute);
				if (result == null) result = caseUserVisibleTaglibObject(faceletTaglibTagAttribute);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR_EXTENSION: {
				FaceletTaglibTagBehaviorExtension faceletTaglibTagBehaviorExtension = (FaceletTaglibTagBehaviorExtension)theEObject;
				T result = caseFaceletTaglibTagBehaviorExtension(faceletTaglibTagBehaviorExtension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR: {
				FaceletTaglibTagBehavior faceletTaglibTagBehavior = (FaceletTaglibTagBehavior)theEObject;
				T result = caseFaceletTaglibTagBehavior(faceletTaglibTagBehavior);
				if (result == null) result = caseUserVisibleTaglibObject(faceletTaglibTagBehavior);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT_EXTENSION: {
				FaceletTaglibTagComponentExtension faceletTaglibTagComponentExtension = (FaceletTaglibTagComponentExtension)theEObject;
				T result = caseFaceletTaglibTagComponentExtension(faceletTaglibTagComponentExtension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT: {
				FaceletTaglibTagComponent faceletTaglibTagComponent = (FaceletTaglibTagComponent)theEObject;
				T result = caseFaceletTaglibTagComponent(faceletTaglibTagComponent);
				if (result == null) result = caseUserVisibleTaglibObject(faceletTaglibTagComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER_EXTENSION: {
				FaceletTaglibTagConverterExtension faceletTaglibTagConverterExtension = (FaceletTaglibTagConverterExtension)theEObject;
				T result = caseFaceletTaglibTagConverterExtension(faceletTaglibTagConverterExtension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER: {
				FaceletTaglibTagConverter faceletTaglibTagConverter = (FaceletTaglibTagConverter)theEObject;
				T result = caseFaceletTaglibTagConverter(faceletTaglibTagConverter);
				if (result == null) result = caseUserVisibleTaglibObject(faceletTaglibTagConverter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_EXTENSION: {
				FaceletTaglibTagExtension faceletTaglibTagExtension = (FaceletTaglibTagExtension)theEObject;
				T result = caseFaceletTaglibTagExtension(faceletTaglibTagExtension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG: {
				FaceletTaglibTag faceletTaglibTag = (FaceletTaglibTag)theEObject;
				T result = caseFaceletTaglibTag(faceletTaglibTag);
				if (result == null) result = caseUserVisibleTaglibObject(faceletTaglibTag);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR_EXTENSION: {
				FaceletTaglibTagValidatorExtension faceletTaglibTagValidatorExtension = (FaceletTaglibTagValidatorExtension)theEObject;
				T result = caseFaceletTaglibTagValidatorExtension(faceletTaglibTagValidatorExtension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR: {
				FaceletTaglibTagValidator faceletTaglibTagValidator = (FaceletTaglibTagValidator)theEObject;
				T result = caseFaceletTaglibTagValidator(faceletTaglibTagValidator);
				if (result == null) result = caseUserVisibleTaglibObject(faceletTaglibTagValidator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.FACELET_TAGLIB: {
				FaceletTaglib faceletTaglib = (FaceletTaglib)theEObject;
				T result = caseFaceletTaglib(faceletTaglib);
				if (result == null) result = caseUserVisibleTaglibObject(faceletTaglib);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.FULLY_QUALIFIED_CLASS: {
				FullyQualifiedClass fullyQualifiedClass = (FullyQualifiedClass)theEObject;
				T result = caseFullyQualifiedClass(fullyQualifiedClass);
				if (result == null) result = caseIdentifiableStringValue(fullyQualifiedClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.GENERIC_BOOLEAN: {
				GenericBoolean genericBoolean = (GenericBoolean)theEObject;
				T result = caseGenericBoolean(genericBoolean);
				if (result == null) result = caseIdentifiableStringValue(genericBoolean);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.ICON: {
				Icon icon = (Icon)theEObject;
				T result = caseIcon(icon);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.JAVA_IDENTIFIER: {
				JavaIdentifier javaIdentifier = (JavaIdentifier)theEObject;
				T result = caseJavaIdentifier(javaIdentifier);
				if (result == null) result = caseIdentifiableStringValue(javaIdentifier);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.PATH: {
				Path path = (Path)theEObject;
				T result = casePath(path);
				if (result == null) result = caseIdentifiableStringValue(path);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.IDENTIFIABLE_STRING_VALUE: {
				IdentifiableStringValue identifiableStringValue = (IdentifiableStringValue)theEObject;
				T result = caseIdentifiableStringValue(identifiableStringValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.IDENTIFIABLE_LANG_STRING_VALUE: {
				IdentifiableLangStringValue identifiableLangStringValue = (IdentifiableLangStringValue)theEObject;
				T result = caseIdentifiableLangStringValue(identifiableLangStringValue);
				if (result == null) result = caseIdentifiableStringValue(identifiableLangStringValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FaceletTaglibPackage.USER_VISIBLE_TAGLIB_OBJECT: {
				UserVisibleTaglibObject userVisibleTaglibObject = (UserVisibleTaglibObject)theEObject;
				T result = caseUserVisibleTaglibObject(userVisibleTaglibObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Description</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Description</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseDescription(Description object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Display Name</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Display Name</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseDisplayName(DisplayName object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseDocumentRoot(DocumentRoot object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Canonical Name</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Canonical Name</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseFaceletTaglibCanonicalName(FaceletTaglibCanonicalName object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Extension</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseFaceletTaglibExtension(FaceletTaglibExtension object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Function</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseFaceletTaglibFunction(FaceletTaglibFunction object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Tag Attribute</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tag Attribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseFaceletTaglibTagAttribute(FaceletTaglibTagAttribute object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Tag Behavior Extension</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tag Behavior Extension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseFaceletTaglibTagBehaviorExtension(FaceletTaglibTagBehaviorExtension object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Tag Behavior</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tag Behavior</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseFaceletTaglibTagBehavior(FaceletTaglibTagBehavior object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Tag Component Extension</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tag Component Extension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseFaceletTaglibTagComponentExtension(FaceletTaglibTagComponentExtension object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Tag Component</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tag Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseFaceletTaglibTagComponent(FaceletTaglibTagComponent object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Tag Converter Extension</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tag Converter Extension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseFaceletTaglibTagConverterExtension(FaceletTaglibTagConverterExtension object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Tag Converter</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tag Converter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseFaceletTaglibTagConverter(FaceletTaglibTagConverter object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Tag Extension</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tag Extension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseFaceletTaglibTagExtension(FaceletTaglibTagExtension object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Tag</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tag</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseFaceletTaglibTag(FaceletTaglibTag object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Tag Validator Extension</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tag Validator Extension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseFaceletTaglibTagValidatorExtension(FaceletTaglibTagValidatorExtension object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Tag Validator</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tag Validator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseFaceletTaglibTagValidator(FaceletTaglibTagValidator object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Facelet Taglib</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Facelet Taglib</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseFaceletTaglib(FaceletTaglib object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Fully Qualified Class</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fully Qualified Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseFullyQualifiedClass(FullyQualifiedClass object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Generic Boolean</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic Boolean</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseGenericBoolean(GenericBoolean object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Icon</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Icon</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseIcon(Icon object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Java Identifier</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Java Identifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseJavaIdentifier(JavaIdentifier object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Path</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Path</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T casePath(Path object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Identifiable String Value</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Identifiable String Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseIdentifiableStringValue(IdentifiableStringValue object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Identifiable Lang String Value</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Identifiable Lang String Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseIdentifiableLangStringValue(IdentifiableLangStringValue object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>User Visible Taglib Object</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Visible Taglib Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseUserVisibleTaglibObject(UserVisibleTaglibObject object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
    @Override
				public T defaultCase(EObject object)
    {
		return null;
	}

} //FaceletTaglibSwitch
