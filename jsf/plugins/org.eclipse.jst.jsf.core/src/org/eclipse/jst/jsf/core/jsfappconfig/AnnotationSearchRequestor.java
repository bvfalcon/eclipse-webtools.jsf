/*******************************************************************************
 * Copyright (c) 2011, 2021 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *        Andrew McCulloch        - initial API and implementation
 *        Ian Trimble                 - maintenance
 *        Reto Weiss/Axon Ivy - Use cached JDTBeanIntrospector to resolve annotation types
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.jsfappconfig;

import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.APPLICATION_SCOPED_ANNOTATION_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.CDI_APPLICATION_SCOPED_ANNOTATION_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.CDI_APPLICATION_SCOPED_ANNOTATION_JAKARTA_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.CDI_CONVERSATION_SCOPED_ANNOTATION_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.CDI_CONVERSATION_SCOPED_ANNOTATION_JAKARTA_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.CDI_MODEL_BEAN_ANNOTATION_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.CDI_NAMED_BEAN_ANNOTATION_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.CDI_MODEL_BEAN_ANNOTATION_JAKARTA_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.CDI_NAMED_BEAN_ANNOTATION_JAKARTA_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.CDI_REQUEST_SCOPED_ANNOTATION_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.CDI_REQUEST_SCOPED_ANNOTATION_JAKARTA_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.CDI_SESSION_SCOPED_ANNOTATION_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.CDI_SESSION_SCOPED_ANNOTATION_JAKARTA_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.CUSTOM_SCOPED_ANNOTATION_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.FACES_COMPONENT_ANNOTATION_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.FACES_COMPONENT_ANNOTATION_JAKARTA_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.FACES_CONVERTER_ANNOTATION_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.FACES_CONVERTER_ANNOTATION_JAKARTA_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.FACES_RENDERER_ANNOTATION_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.FACES_RENDERER_ANNOTATION_JAKARTA_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.FACES_VALIDATOR_ANNOTATION_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.FACES_VALIDATOR_ANNOTATION_JAKARTA_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.MANAGED_BEAN_ANNOTATION_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.MANAGED_BEAN_ANNOTATION_JAKARTA_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.NONE_SCOPED_ANNOTATION_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.REFERENCED_BEAN_ANNOTATION_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.SESSION_SCOPED_ANNOTATION_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.VIEW_SCOPED_ANNOTATION_CLASS;
import static org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigProvider.VIEW_SCOPED_ANNOTATION_JAKARTA_CLASS;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.IMemberValuePair;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchRequestor;
import org.eclipse.jst.jsf.common.util.JDTBeanIntrospector;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentFamilyType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentTypeType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterForClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterIdType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanNameType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanScopeType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanNameType;
import org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitIdType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererClassType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererTypeType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorIdType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorType;

/**
 * SearchRequestor that looks at annotations for JSF configuration.
 * 
 * <p>
 * <b>Provisional API - subject to change</b>
 * </p>
 * 
 * @author Andrew McCulloch - Oracle
 */
public class AnnotationSearchRequestor extends SearchRequestor
{
    private final FacesConfigType facesConfig;
    
    private static final Set<String> ANNOTATION_CANDIDATES = ofAnnotation(
            MANAGED_BEAN_ANNOTATION_CLASS, 
            MANAGED_BEAN_ANNOTATION_JAKARTA_CLASS, 
            REFERENCED_BEAN_ANNOTATION_CLASS, 
            FACES_COMPONENT_ANNOTATION_CLASS, 
            FACES_COMPONENT_ANNOTATION_JAKARTA_CLASS, 
            FACES_CONVERTER_ANNOTATION_CLASS, 
            FACES_CONVERTER_ANNOTATION_JAKARTA_CLASS, 
            FACES_RENDERER_ANNOTATION_CLASS,
            FACES_RENDERER_ANNOTATION_JAKARTA_CLASS,
            FACES_VALIDATOR_ANNOTATION_CLASS, 
            FACES_VALIDATOR_ANNOTATION_JAKARTA_CLASS, 
            CDI_NAMED_BEAN_ANNOTATION_CLASS, 
            CDI_MODEL_BEAN_ANNOTATION_CLASS, 
            CDI_NAMED_BEAN_ANNOTATION_JAKARTA_CLASS, 
            CDI_MODEL_BEAN_ANNOTATION_JAKARTA_CLASS);

    private static final Set<String> SCOPED_ANNOTATION_CANDIDATES = ofAnnotation(
            NONE_SCOPED_ANNOTATION_CLASS,
            VIEW_SCOPED_ANNOTATION_CLASS,
            VIEW_SCOPED_ANNOTATION_JAKARTA_CLASS,
            SESSION_SCOPED_ANNOTATION_CLASS,
            APPLICATION_SCOPED_ANNOTATION_CLASS,
            CUSTOM_SCOPED_ANNOTATION_CLASS);

    private static final Set<String> CDI_SCOPED_ANNOTATION_CANDIDATES = ofAnnotation(
            CDI_REQUEST_SCOPED_ANNOTATION_CLASS,
            CDI_REQUEST_SCOPED_ANNOTATION_JAKARTA_CLASS,
            CDI_CONVERSATION_SCOPED_ANNOTATION_CLASS,
            CDI_CONVERSATION_SCOPED_ANNOTATION_JAKARTA_CLASS,
            CDI_SESSION_SCOPED_ANNOTATION_CLASS,
            CDI_SESSION_SCOPED_ANNOTATION_JAKARTA_CLASS,
            CDI_APPLICATION_SCOPED_ANNOTATION_CLASS,
            CDI_APPLICATION_SCOPED_ANNOTATION_JAKARTA_CLASS);
 
    private static Set<String> ofAnnotation(String... annotationClasses)
    {
        Set<String> candidates = new HashSet<String>();
        for (String annotationClass : annotationClasses)
        {
            candidates.add(annotationClass);
            candidates.add(Signature.getSimpleName(annotationClass));
        }
        return Collections.unmodifiableSet(candidates);
    }

    AnnotationSearchRequestor(final FacesConfigType facesConfig)
    {
        this.facesConfig = facesConfig;
    }

    @Override
    public void acceptSearchMatch(SearchMatch match) throws CoreException
    {
        Object element = match.getElement();
        if (!(element instanceof IType))
        {
            return;
        }
        IType type = (IType) element;
        JDTBeanIntrospector beanIntrospector = JDTBeanIntrospector.forType(type);
        IAnnotation[] annotations = type.getAnnotations();
        if (annotations == null)
        {
            return;
        }
        for (IAnnotation annotation : annotations)
        {
            processAnnotation(type, beanIntrospector, annotation);
        }
    }

    private void processAnnotation(IType type, JDTBeanIntrospector beanIntrospector, IAnnotation annotation) throws CoreException
    {
        if (!annotation.exists())
        {
            return;
        }
        String annotationType = annotation.getElementName();
        if (! isAnnotationCandidate(annotationType))
        {
            return;
        }
        String annotationClassName = beanIntrospector.resolveFullQualifiedTypeName(annotationType);
        if (annotationClassName != null)
        {
            if (MANAGED_BEAN_ANNOTATION_CLASS.equals(annotationClassName) || MANAGED_BEAN_ANNOTATION_JAKARTA_CLASS.equals(annotationClassName))
            {
                addManagedBean(annotation, type, beanIntrospector);
            }
            else if (REFERENCED_BEAN_ANNOTATION_CLASS.equals(annotationClassName))
            {
                addReferencedBean(annotation, type);
            }
            else if (FACES_COMPONENT_ANNOTATION_CLASS.equals(annotationClassName) || FACES_COMPONENT_ANNOTATION_JAKARTA_CLASS.equals(annotationClassName))
            {
                addComponent(annotation, type);
            }
            else if (FACES_CONVERTER_ANNOTATION_CLASS.equals(annotationClassName) || FACES_CONVERTER_ANNOTATION_JAKARTA_CLASS.equals(annotationClassName))
            {
                addConverter(annotation, type);
            }
            else if (FACES_RENDERER_ANNOTATION_CLASS.equals(annotationClassName) || FACES_RENDERER_ANNOTATION_JAKARTA_CLASS.equals(annotationClassName))
            {
                addRenderer(annotation, type);
            }
            else if (FACES_VALIDATOR_ANNOTATION_CLASS.equals(annotationClassName) || FACES_VALIDATOR_ANNOTATION_JAKARTA_CLASS.equals(annotationClassName))
            {
                addValidator(annotation, type);
            }
            else if (CDI_NAMED_BEAN_ANNOTATION_CLASS.equals(annotationClassName) || CDI_NAMED_BEAN_ANNOTATION_JAKARTA_CLASS.equals(annotationClassName))
            {
                addCDINamedBean(annotation, beanIntrospector, type);
            }
            else if (CDI_MODEL_BEAN_ANNOTATION_CLASS.equals(annotationClassName) || CDI_MODEL_BEAN_ANNOTATION_JAKARTA_CLASS.equals(annotationClassName))
            {
                addCDIModelBean(type);
            }
        }
    }

    private boolean isAnnotationCandidate(String annotationType)
    {
        return ANNOTATION_CANDIDATES.contains(annotationType);
    }

    private void addReferencedBean(IAnnotation referencedBeanAnnotation, IType beanType)
                    throws JavaModelException
    {
        IMemberValuePair[] pairs = referencedBeanAnnotation.getMemberValuePairs();
        String beanNameString = null;
        if (pairs != null)
        {
            for (IMemberValuePair pair : pairs)
            {
                if ("name".equals(pair.getMemberName()) && pair.getValueKind() == IMemberValuePair.K_STRING) //$NON-NLS-1$
                {
                    beanNameString = (String) pair.getValue();
                }
            }
        }
        if (beanNameString == null)
        {
            beanNameString = beanType.getElementName();
            if (beanNameString != null && beanNameString.length() > 0)
            {
                StringBuffer casedName = new StringBuffer(String.valueOf(beanNameString.charAt(0)).toUpperCase());
                beanNameString = casedName.append(beanNameString.substring(1)).toString();
            }
        }
        String beanClassName = beanType.getFullyQualifiedName();

        if (beanNameString != null && beanClassName != null)
        {
            ReferencedBeanType bean = FacesConfigFactory.eINSTANCE.createReferencedBeanType();
            ReferencedBeanNameType beanName = FacesConfigFactory.eINSTANCE.createReferencedBeanNameType();
            beanName.setTextContent(beanNameString);
            bean.setReferencedBeanName(beanName);
            ReferencedBeanClassType beanClass = FacesConfigFactory.eINSTANCE.createReferencedBeanClassType();
            beanClass.setTextContent(beanClassName);
            bean.setReferencedBeanClass(beanClass);
            facesConfig.getReferencedBean().add(bean);
        }
    }

    private void addManagedBean(IAnnotation beanAnnotation, IType beanType, JDTBeanIntrospector beanIntrospector) throws JavaModelException
    {
        IMemberValuePair[] pairs = beanAnnotation.getMemberValuePairs();
        String beanNameString = null;
        Boolean isBeanEager = Boolean.FALSE;
        if (pairs != null)
        {
            for (IMemberValuePair pair : pairs)
            {
                if ("name".equals(pair.getMemberName()) && pair.getValueKind() == IMemberValuePair.K_STRING) //$NON-NLS-1$
                {
                    beanNameString = (String) pair.getValue();
                }
                else if ("eager".equals(pair.getMemberName()) && pair.getValueKind() == IMemberValuePair.K_BOOLEAN) //$NON-NLS-1$
                {
                    isBeanEager = (Boolean) pair.getValue();
                }
            }
        }
        if (beanNameString == null || beanNameString.length() < 1)
        {
            beanNameString = beanType.getElementName();
            if (beanNameString != null && beanNameString.length() > 0)
            {
                StringBuffer casedName = new StringBuffer(String.valueOf(beanNameString.charAt(0)).toLowerCase());
                beanNameString = casedName.append(beanNameString.substring(1)).toString();
            }
        }
        String beanClassName = beanType.getFullyQualifiedName();

        String beanScopeString = processScopeAnnotations(beanType, beanIntrospector);

        if (beanNameString != null && beanClassName != null)
        {
            ManagedBeanType bean = FacesConfigFactory.eINSTANCE.createManagedBeanType();
            ManagedBeanNameType beanName = FacesConfigFactory.eINSTANCE.createManagedBeanNameType();
            beanName.setTextContent(beanNameString);
            bean.setManagedBeanName(beanName);
            ManagedBeanClassType beanClass = FacesConfigFactory.eINSTANCE.createManagedBeanClassType();
            beanClass.setTextContent(beanClassName);
            bean.setManagedBeanClass(beanClass);
            ManagedBeanScopeType beanScope = FacesConfigFactory.eINSTANCE.createManagedBeanScopeType();
            beanScope.setTextContent(beanScopeString);
            bean.setManagedBeanScope(beanScope);
            bean.setEager(isBeanEager.booleanValue());
            JSFAppConfigUtils.setManagedBeanSource(bean, JSFAppConfigUtils.MANAGEDBEAN_SOURCE_JSF_ANNOTATION);
            facesConfig.getManagedBean().add(bean);
        }
    }

    private String processScopeAnnotations(IType beanType, JDTBeanIntrospector beanIntrospector) throws JavaModelException
    {
        String beanScopeString = "request"; //$NON-NLS-1$
        IAnnotation[] annotations = beanType.getAnnotations();
        if (annotations == null)
        {
            return beanScopeString;
        }
        for (IAnnotation annotation : annotations)
        {
            String scope = processScopeAnnotation(beanType, beanIntrospector, annotation);
            if (scope != null)
            {
                beanScopeString = scope;
            }
        }
        return beanScopeString;
    }

    private String processScopeAnnotation(IType beanType, JDTBeanIntrospector beanIntrospector, IAnnotation annotation) throws JavaModelException
    {
        if (! annotation.exists())
        {
            return null;
        }
        String annotationType = annotation.getElementName();
        if (! isScopeAnnotationCandidate(annotationType))
        {
            return null;
        }
        String annotationClassName = beanIntrospector.resolveFullQualifiedTypeName(annotationType);
        if (annotationClassName == null)
        {
            return null;
        }
        if (NONE_SCOPED_ANNOTATION_CLASS.equals(annotationClassName))
        {
            return "none"; //$NON-NLS-1$
        }
        else if (VIEW_SCOPED_ANNOTATION_CLASS.equals(annotationClassName) || VIEW_SCOPED_ANNOTATION_JAKARTA_CLASS.equals(annotationClassName))
        {
            return "view"; //$NON-NLS-1$
        }
        else if (SESSION_SCOPED_ANNOTATION_CLASS.equals(annotationClassName))
        {
            return "session"; //$NON-NLS-1$
        }
        else if (APPLICATION_SCOPED_ANNOTATION_CLASS.equals(annotationClassName))
        {
            return "application"; //$NON-NLS-1$
        }
        else if (CUSTOM_SCOPED_ANNOTATION_CLASS.equals(annotationClassName))
        {
            IMemberValuePair[] scopePairs = annotation.getMemberValuePairs();
            if (scopePairs != null && scopePairs.length == 1
                            && scopePairs[0].getValueKind() == IMemberValuePair.K_STRING)
            {
                return (String) scopePairs[0].getValue();
            }
        }
        return null;
    }

    private boolean isScopeAnnotationCandidate(String annotationType)
    {
        return SCOPED_ANNOTATION_CANDIDATES.contains(annotationType);
    }

    private void addValidator(IAnnotation validatorAnnotation, IType validatorType) throws JavaModelException
    {
        String validatorClassName = validatorType.getFullyQualifiedName();
        IMemberValuePair[] pairs = validatorAnnotation.getMemberValuePairs();
        String validatorIDString = null;
        // Boolean isDefaultBoolean = null;
        if (pairs != null)
        {
            for (IMemberValuePair pair : pairs)
            {
                if ("value".equals(pair.getMemberName()) && pair.getValueKind() == IMemberValuePair.K_STRING) //$NON-NLS-1$
                {
                    validatorIDString = (String) pair.getValue();
                    // isDefault not used in emf model
                    // } else if ("isDefault".equals(pair.getMemberName()) &&
                    // pair.getValueKind() == IMemberValuePair.K_BOOLEAN) { //$NON-NLS-1$
                    // isDefaultBoolean = (Boolean)pair.getValue();
                }
            }
        }

        if (validatorClassName != null && validatorIDString != null)
        {
            ValidatorType validator = FacesConfigFactory.eINSTANCE.createValidatorType();
            ValidatorClassType validatorClass = FacesConfigFactory.eINSTANCE.createValidatorClassType();
            validatorClass.setTextContent(validatorClassName);
            validator.setValidatorClass(validatorClass);

            ValidatorIdType validatorID = FacesConfigFactory.eINSTANCE.createValidatorIdType();
            validatorID.setTextContent(validatorIDString);
            validator.setValidatorId(validatorID);

            // if (isDefaultBoolean == null) {
            // isDefaultBoolean = Boolean.FALSE;
            // }

            facesConfig.getValidator().add(validator);
        }
    }

    private void addRenderer(IAnnotation rendererAnnotation, IType rendererType) throws JavaModelException
    {
        String rendererClassName = rendererType.getFullyQualifiedName();
        IMemberValuePair[] pairs = rendererAnnotation.getMemberValuePairs();
        String rendererTypeString = null;
        String componentFamilyString = null;
        String renderKitIDString = null;
        if (pairs != null)
        {
            for (IMemberValuePair pair : pairs)
            {
                if ("rendererType".equals(pair.getMemberName()) && pair.getValueKind() == IMemberValuePair.K_STRING) //$NON-NLS-1$
                {
                    rendererTypeString = (String) pair.getValue();
                }
                else if ("componentFamily".equals(pair.getMemberName()) //$NON-NLS-1$
                                && pair.getValueKind() == IMemberValuePair.K_STRING)
                {
                    componentFamilyString = (String) pair.getValue();
                }
                else if ("renderKitId".equals(pair.getMemberName()) //$NON-NLS-1$
                                && pair.getValueKind() == IMemberValuePair.K_STRING)
                {
                    renderKitIDString = (String) pair.getValue();
                }
            }
        }

        if (rendererClassName != null && rendererTypeString != null && componentFamilyString != null)
        {
            RendererType renderer = FacesConfigFactory.eINSTANCE.createRendererType();
            RendererClassType rendererClass = FacesConfigFactory.eINSTANCE.createRendererClassType();
            rendererClass.setTextContent(rendererClassName);
            renderer.setRendererClass(rendererClass);

            RendererTypeType rendererTypeType = FacesConfigFactory.eINSTANCE.createRendererTypeType();
            rendererTypeType.setTextContent(rendererTypeString);
            renderer.setRendererType(rendererTypeType);

            ComponentFamilyType componentFamily = FacesConfigFactory.eINSTANCE.createComponentFamilyType();
            componentFamily.setTextContent(componentFamilyString);
            renderer.setComponentFamily(componentFamily);

            if (renderKitIDString == null)
            {
                // use the default
                renderKitIDString = "HTML_BASIC"; //$NON-NLS-1$
            }
            EList renderKits = facesConfig.getRenderKit();
            if (renderKits != null)
            {
                RenderKitType renderKit = null;
                for (int i = 0, k = renderKits.size(); i < k; i++)
                {
                    if (((RenderKitType) renderKits.get(i)).getRenderKitId() != null && renderKitIDString
                                    .equals(((RenderKitType) renderKits.get(i)).getRenderKitId().getTextContent()))
                    {
                        renderKit = (RenderKitType) (renderKits.get(i));
                    }
                }
                if (renderKit == null)
                {
                    renderKit = FacesConfigFactory.eINSTANCE.createRenderKitType();
                    RenderKitIdType renderKitID = FacesConfigFactory.eINSTANCE.createRenderKitIdType();
                    renderKitID.setTextContent(renderKitIDString);
                    renderKit.setRenderKitId(renderKitID);
                    renderKits.add(renderKit);
                }
                renderKit.getRenderer().add(renderer);
            }
        }
    }

    private void addConverter(IAnnotation converterAnnotation, IType converterType) throws JavaModelException
    {
        String converterClassName = converterType.getFullyQualifiedName();
        IMemberValuePair[] pairs = converterAnnotation.getMemberValuePairs();
        String converterIDString = null;
        String converterForClassString = null;
        if (pairs != null)
        {
            for (IMemberValuePair pair : pairs)
            {
                if ("value".equals(pair.getMemberName()) && pair.getValueKind() == IMemberValuePair.K_STRING) //$NON-NLS-1$
                {
                    converterIDString = (String) pair.getValue();
                }
                else if ("forClass".equals(pair.getMemberName()) && pair.getValueKind() == IMemberValuePair.K_CLASS) //$NON-NLS-1$
                {
                    converterForClassString = (String) pair.getValue();
                }
            }
        }
        if (converterClassName != null)
        {
            ConverterType converter = FacesConfigFactory.eINSTANCE.createConverterType();
            ConverterClassType converterClass = FacesConfigFactory.eINSTANCE.createConverterClassType();
            converterClass.setTextContent(converterClassName);
            converter.setConverterClass(converterClass);

            if (converterIDString != null)
            {
                ConverterIdType converterID = FacesConfigFactory.eINSTANCE.createConverterIdType();
                converterID.setTextContent(converterIDString);
                converter.setConverterId(converterID);
            }

            if (converterForClassString == null)
            {
                // use the default
                converterForClassString = "java.lang.Object"; //$NON-NLS-1$
            }
            ConverterForClassType converterForClass = FacesConfigFactory.eINSTANCE.createConverterForClassType();
            converterForClass.setTextContent(converterForClassString);
            converter.setConverterForClass(converterForClass);
            facesConfig.getConverter().add(converter);
        }
    }

    private void addComponent(IAnnotation componentAnnotation, IType componentType) throws JavaModelException
    {
        String componentClassName = componentType.getFullyQualifiedName();
        IMemberValuePair[] pairs = componentAnnotation.getMemberValuePairs();
        String componentTypeString = null;
        if (pairs != null)
        {
            for (IMemberValuePair pair : pairs)
            {
                if ("value".equals(pair.getMemberName()) && pair.getValueKind() == IMemberValuePair.K_STRING) //$NON-NLS-1$
                {
                    componentTypeString = (String) pair.getValue();
                }
            }
        }
        if (componentTypeString != null && componentClassName != null)
        {
            ComponentType component = FacesConfigFactory.eINSTANCE.createComponentType();
            ComponentClassType componentClass = FacesConfigFactory.eINSTANCE.createComponentClassType();
            componentClass.setTextContent(componentClassName);
            component.setComponentClass(componentClass);

            ComponentTypeType componentTypeType = FacesConfigFactory.eINSTANCE.createComponentTypeType();
            componentTypeType.setTextContent(componentTypeString);
            component.setComponentType(componentTypeType);

            facesConfig.getComponent().add(component);
        }
    }

    private void addCDINamedBean(IAnnotation beanAnnotation, JDTBeanIntrospector beanIntrospector, IType beanType) throws JavaModelException
    {
        IMemberValuePair[] pairs = beanAnnotation.getMemberValuePairs();

        String beanNameString = null;
        if (pairs != null)
        {
            for (IMemberValuePair pair : pairs)
            {
                if ("value".equals(pair.getMemberName()) && pair.getValueKind() == IMemberValuePair.K_STRING) //$NON-NLS-1$
                {
                    beanNameString = (String) pair.getValue();
                }
            }
        }
        if (beanNameString == null || beanNameString.length() < 1)
        {
            beanNameString = beanType.getElementName();
            if (beanNameString != null && beanNameString.length() > 0)
            {
                StringBuffer casedName = new StringBuffer(String.valueOf(beanNameString.charAt(0)).toLowerCase());
                beanNameString = casedName.append(beanNameString.substring(1)).toString();
            }
        }

        String beanClassName = beanType.getFullyQualifiedName();

        String beanScopeString = processCDIScopeAnnotations(beanType, beanIntrospector);

        if (beanNameString != null && beanClassName != null)
        {
            ManagedBeanType bean = FacesConfigFactory.eINSTANCE.createManagedBeanType();
            ManagedBeanNameType beanName = FacesConfigFactory.eINSTANCE.createManagedBeanNameType();
            beanName.setTextContent(beanNameString);
            bean.setManagedBeanName(beanName);
            ManagedBeanClassType beanClass = FacesConfigFactory.eINSTANCE.createManagedBeanClassType();
            beanClass.setTextContent(beanClassName);
            bean.setManagedBeanClass(beanClass);
            ManagedBeanScopeType beanScope = FacesConfigFactory.eINSTANCE.createManagedBeanScopeType();
            beanScope.setTextContent(beanScopeString);
            bean.setManagedBeanScope(beanScope);
            bean.setEager(false);
            JSFAppConfigUtils.setManagedBeanSource(bean, JSFAppConfigUtils.MANAGEDBEAN_SOURCE_CDI_ANNOTATION);
            facesConfig.getManagedBean().add(bean);
        }
    }

    private String processCDIScopeAnnotations(IType beanType, JDTBeanIntrospector beanIntrospector) throws JavaModelException
    {
        String beanScopeString = "dependent"; //$NON-NLS-1$
        IAnnotation[] annotations = beanType.getAnnotations();
        if (annotations == null)
        {
            return beanScopeString;
        }
        for (IAnnotation annotation : annotations)
        {
            String scope = processCDIScopeAnnotation(beanType, beanIntrospector, annotation);
            if (scope != null)
            {
                beanScopeString = scope;
            }
        }
        return beanScopeString;
    }
    
    private String processCDIScopeAnnotation(IType beanType, JDTBeanIntrospector beanIntrospector, IAnnotation annotation)
    {
        if (!annotation.exists())
        {
            return null;
        }
        String annotationType = annotation.getElementName();
        if (! isCDIScopeAnnotationCandidate(annotationType))
        {
            return null;
        }
        String annotationClassName = beanIntrospector.resolveFullQualifiedTypeName(annotationType);
        if (annotationClassName == null)
        {
            return null;
        }
        if (CDI_REQUEST_SCOPED_ANNOTATION_CLASS.equals(annotationClassName) || CDI_REQUEST_SCOPED_ANNOTATION_JAKARTA_CLASS.equals(annotationClassName))
        {
            return "request"; //$NON-NLS-1$
        }
        else if (CDI_CONVERSATION_SCOPED_ANNOTATION_CLASS.equals(annotationClassName) || CDI_CONVERSATION_SCOPED_ANNOTATION_JAKARTA_CLASS.equals(annotationClassName))
        {
            return "conversation"; //$NON-NLS-1$
        }
        else if (CDI_SESSION_SCOPED_ANNOTATION_CLASS.equals(annotationClassName) || CDI_SESSION_SCOPED_ANNOTATION_JAKARTA_CLASS.equals(annotationClassName))
        {
            return "session"; //$NON-NLS-1$
        }
        else if (CDI_APPLICATION_SCOPED_ANNOTATION_CLASS.equals(annotationClassName) || CDI_APPLICATION_SCOPED_ANNOTATION_JAKARTA_CLASS.equals(annotationClassName))
        {
            return "application"; //$NON-NLS-1$
        }
        return null;
    }

    private boolean isCDIScopeAnnotationCandidate(String annotationType)
    {
        return CDI_SCOPED_ANNOTATION_CANDIDATES.contains(annotationType);
    }

    private void addCDIModelBean(IType beanType) throws JavaModelException
    {
        String beanNameString = beanType.getElementName();
        if (beanNameString != null && beanNameString.length() > 0)
        {
            StringBuffer casedName = new StringBuffer(String.valueOf(beanNameString.charAt(0)).toLowerCase());
            beanNameString = casedName.append(beanNameString.substring(1)).toString();
        }

        String beanClassName = beanType.getFullyQualifiedName();

        String beanScopeString = "request"; //$NON-NLS-1$
        IAnnotation[] annotations = beanType.getAnnotations();
        if (annotations != null)
        {
            for (int i = 0, k = annotations.length; i < k; i++)
            {
                if (annotations[i].exists())
                {
                    String annotationType = annotations[i].getElementName();
                    String[][] resolvedAnnotationTypes = beanType.resolveType(annotationType);
                    if (resolvedAnnotationTypes != null)
                    {
                        String resolvedAnnotationClassName = new StringBuffer(resolvedAnnotationTypes[0][0]).append('.')
                                        .append(resolvedAnnotationTypes[0][1]).toString();
                        if (CDI_CONVERSATION_SCOPED_ANNOTATION_CLASS.equals(resolvedAnnotationClassName) || CDI_CONVERSATION_SCOPED_ANNOTATION_JAKARTA_CLASS.equals(resolvedAnnotationClassName))
                        {
                            beanScopeString = "conversation"; //$NON-NLS-1$
                        }
                        else if (CDI_SESSION_SCOPED_ANNOTATION_CLASS.equals(resolvedAnnotationClassName) || CDI_SESSION_SCOPED_ANNOTATION_JAKARTA_CLASS.equals(resolvedAnnotationClassName))
                        {
                            beanScopeString = "session"; //$NON-NLS-1$
                        }
                        else if (CDI_APPLICATION_SCOPED_ANNOTATION_CLASS.equals(resolvedAnnotationClassName) || CDI_APPLICATION_SCOPED_ANNOTATION_JAKARTA_CLASS.equals(resolvedAnnotationClassName))
                        {
                            beanScopeString = "application"; //$NON-NLS-1$
                        }
                    }
                }
            }
        }

        if (beanNameString != null && beanClassName != null)
        {
            ManagedBeanType bean = FacesConfigFactory.eINSTANCE.createManagedBeanType();
            ManagedBeanNameType beanName = FacesConfigFactory.eINSTANCE.createManagedBeanNameType();
            beanName.setTextContent(beanNameString);
            bean.setManagedBeanName(beanName);
            ManagedBeanClassType beanClass = FacesConfigFactory.eINSTANCE.createManagedBeanClassType();
            beanClass.setTextContent(beanClassName);
            bean.setManagedBeanClass(beanClass);
            ManagedBeanScopeType beanScope = FacesConfigFactory.eINSTANCE.createManagedBeanScopeType();
            beanScope.setTextContent(beanScopeString);
            bean.setManagedBeanScope(beanScope);
            bean.setEager(false);
            JSFAppConfigUtils.setManagedBeanSource(bean, JSFAppConfigUtils.MANAGEDBEAN_SOURCE_CDI_ANNOTATION);
            facesConfig.getManagedBean().add(bean);
        }
    }

}
