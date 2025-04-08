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
 * $Id: FaceletTaglibAdapterFactory.java,v 1.2 2010/03/18 06:24:40 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage
 * @generated
 */
public class FaceletTaglibAdapterFactory extends AdapterFactoryImpl
{
    /**
	 * The cached model package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected static FaceletTaglibPackage modelPackage;

    /**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglibAdapterFactory()
    {
		if (modelPackage == null) {
			modelPackage = FaceletTaglibPackage.eINSTANCE;
		}
	}

    /**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
    @Override
    public boolean isFactoryForType(Object object)
    {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

    /**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected FaceletTaglibSwitch<Adapter> modelSwitch =
        new FaceletTaglibSwitch<Adapter>() {
			@Override
			public Adapter caseDescription(Description object) {
				return createDescriptionAdapter();
			}
			@Override
			public Adapter caseDisplayName(DisplayName object) {
				return createDisplayNameAdapter();
			}
			@Override
			public Adapter caseDocumentRoot(DocumentRoot object) {
				return createDocumentRootAdapter();
			}
			@Override
			public Adapter caseFaceletTaglibCanonicalName(FaceletTaglibCanonicalName object) {
				return createFaceletTaglibCanonicalNameAdapter();
			}
			@Override
			public Adapter caseFaceletTaglibExtension(FaceletTaglibExtension object) {
				return createFaceletTaglibExtensionAdapter();
			}
			@Override
			public Adapter caseFaceletTaglibFunction(FaceletTaglibFunction object) {
				return createFaceletTaglibFunctionAdapter();
			}
			@Override
			public Adapter caseFaceletTaglibTagAttribute(FaceletTaglibTagAttribute object) {
				return createFaceletTaglibTagAttributeAdapter();
			}
			@Override
			public Adapter caseFaceletTaglibTagBehaviorExtension(FaceletTaglibTagBehaviorExtension object) {
				return createFaceletTaglibTagBehaviorExtensionAdapter();
			}
			@Override
			public Adapter caseFaceletTaglibTagBehavior(FaceletTaglibTagBehavior object) {
				return createFaceletTaglibTagBehaviorAdapter();
			}
			@Override
			public Adapter caseFaceletTaglibTagComponentExtension(FaceletTaglibTagComponentExtension object) {
				return createFaceletTaglibTagComponentExtensionAdapter();
			}
			@Override
			public Adapter caseFaceletTaglibTagComponent(FaceletTaglibTagComponent object) {
				return createFaceletTaglibTagComponentAdapter();
			}
			@Override
			public Adapter caseFaceletTaglibTagConverterExtension(FaceletTaglibTagConverterExtension object) {
				return createFaceletTaglibTagConverterExtensionAdapter();
			}
			@Override
			public Adapter caseFaceletTaglibTagConverter(FaceletTaglibTagConverter object) {
				return createFaceletTaglibTagConverterAdapter();
			}
			@Override
			public Adapter caseFaceletTaglibTagExtension(FaceletTaglibTagExtension object) {
				return createFaceletTaglibTagExtensionAdapter();
			}
			@Override
			public Adapter caseFaceletTaglibTag(FaceletTaglibTag object) {
				return createFaceletTaglibTagAdapter();
			}
			@Override
			public Adapter caseFaceletTaglibTagValidatorExtension(FaceletTaglibTagValidatorExtension object) {
				return createFaceletTaglibTagValidatorExtensionAdapter();
			}
			@Override
			public Adapter caseFaceletTaglibTagValidator(FaceletTaglibTagValidator object) {
				return createFaceletTaglibTagValidatorAdapter();
			}
			@Override
			public Adapter caseFaceletTaglib(FaceletTaglib object) {
				return createFaceletTaglibAdapter();
			}
			@Override
			public Adapter caseFullyQualifiedClass(FullyQualifiedClass object) {
				return createFullyQualifiedClassAdapter();
			}
			@Override
			public Adapter caseGenericBoolean(GenericBoolean object) {
				return createGenericBooleanAdapter();
			}
			@Override
			public Adapter caseIcon(Icon object) {
				return createIconAdapter();
			}
			@Override
			public Adapter caseJavaIdentifier(JavaIdentifier object) {
				return createJavaIdentifierAdapter();
			}
			@Override
			public Adapter casePath(Path object) {
				return createPathAdapter();
			}
			@Override
			public Adapter caseIdentifiableStringValue(IdentifiableStringValue object) {
				return createIdentifiableStringValueAdapter();
			}
			@Override
			public Adapter caseIdentifiableLangStringValue(IdentifiableLangStringValue object) {
				return createIdentifiableLangStringValueAdapter();
			}
			@Override
			public Adapter caseUserVisibleTaglibObject(UserVisibleTaglibObject object) {
				return createUserVisibleTaglibObjectAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

    /**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
    @Override
    public Adapter createAdapter(Notifier target)
    {
		return modelSwitch.doSwitch((EObject)target);
	}


    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Description <em>Description</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Description
	 * @generated
	 */
    public Adapter createDescriptionAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DisplayName
	 * @generated
	 */
    public Adapter createDisplayNameAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DocumentRoot
	 * @generated
	 */
    public Adapter createDocumentRootAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibCanonicalName <em>Canonical Name</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibCanonicalName
	 * @generated
	 */
    public Adapter createFaceletTaglibCanonicalNameAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibExtension <em>Extension</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibExtension
	 * @generated
	 */
    public Adapter createFaceletTaglibExtensionAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibFunction <em>Function</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibFunction
	 * @generated
	 */
    public Adapter createFaceletTaglibFunctionAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute <em>Tag Attribute</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute
	 * @generated
	 */
    public Adapter createFaceletTaglibTagAttributeAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehaviorExtension <em>Tag Behavior Extension</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehaviorExtension
	 * @generated
	 */
    public Adapter createFaceletTaglibTagBehaviorExtensionAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehavior <em>Tag Behavior</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehavior
	 * @generated
	 */
    public Adapter createFaceletTaglibTagBehaviorAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponentExtension <em>Tag Component Extension</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponentExtension
	 * @generated
	 */
    public Adapter createFaceletTaglibTagComponentExtensionAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponent <em>Tag Component</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponent
	 * @generated
	 */
    public Adapter createFaceletTaglibTagComponentAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverterExtension <em>Tag Converter Extension</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverterExtension
	 * @generated
	 */
    public Adapter createFaceletTaglibTagConverterExtensionAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverter <em>Tag Converter</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverter
	 * @generated
	 */
    public Adapter createFaceletTaglibTagConverterAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagExtension <em>Tag Extension</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagExtension
	 * @generated
	 */
    public Adapter createFaceletTaglibTagExtensionAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag
	 * @generated
	 */
    public Adapter createFaceletTaglibTagAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagValidatorExtension <em>Tag Validator Extension</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagValidatorExtension
	 * @generated
	 */
    public Adapter createFaceletTaglibTagValidatorExtensionAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagValidator <em>Tag Validator</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagValidator
	 * @generated
	 */
    public Adapter createFaceletTaglibTagValidatorAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib <em>Facelet Taglib</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib
	 * @generated
	 */
    public Adapter createFaceletTaglibAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FullyQualifiedClass <em>Fully Qualified Class</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FullyQualifiedClass
	 * @generated
	 */
    public Adapter createFullyQualifiedClassAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.GenericBoolean <em>Generic Boolean</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.GenericBoolean
	 * @generated
	 */
    public Adapter createGenericBooleanAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon
	 * @generated
	 */
    public Adapter createIconAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.JavaIdentifier <em>Java Identifier</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.JavaIdentifier
	 * @generated
	 */
    public Adapter createJavaIdentifierAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Path <em>Path</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Path
	 * @generated
	 */
    public Adapter createPathAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableStringValue <em>Identifiable String Value</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableStringValue
	 * @generated
	 */
    public Adapter createIdentifiableStringValueAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableLangStringValue <em>Identifiable Lang String Value</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableLangStringValue
	 * @generated
	 */
    public Adapter createIdentifiableLangStringValueAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.UserVisibleTaglibObject <em>User Visible Taglib Object</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.UserVisibleTaglibObject
	 * @generated
	 */
    public Adapter createUserVisibleTaglibObjectAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
    public Adapter createEObjectAdapter()
    {
		return null;
	}

} //FaceletTaglibAdapterFactory
