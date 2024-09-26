/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.validation.internal.appconfig;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.ExceptionHandlerFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.ExternalContextFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FacesContextFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.PartialViewContextFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.TagHandlerDelegateFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.ViewDeclarationLanguageFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.VisitContextFactoryType;

/**
 * @author cbateman
 *
 */
public class FactoryValidationVisitor extends EObjectValidationVisitor 
{
    /**
     * @param version
     */
    public FactoryValidationVisitor(String version) {
        super(FacesConfigPackage.eINSTANCE.getFacesConfigType_Factory()
                ,version);
    }

    protected void doValidate(EObject object, List messages, IFile file) {
        // nothing to do except vaildate children
    }

    protected EObjectValidationVisitor[] getChildNodeValidators() 
    {
        return new EObjectValidationVisitor[]
        {
                new ApplicationFactoryValidationVisitor(getVersion())
                , new FacesContextFactoryValidationVisitor(getVersion())
                , new LifecycleFactoryValidationVisitor(getVersion())
                , new RenderkitFactoryValidationVisitor(getVersion())
                , new ExceptionHandlerFactoryValidationVisitor(getVersion())
                , new ExternalContextFactoryValidationVisitor(getVersion())
                , new PartialViewContextFactoryValidationVisitor(getVersion())
                , new ViewDeclarationLanguageFactoryValidationVisitor(getVersion())
                , new TagHandlerDelegateFactoryValidationVisitor(getVersion())
                , new VisitContextFactoryValidationVisitor(getVersion())
        };
    }

    private static class ApplicationFactoryValidationVisitor extends ClassNameEObjectValidationVisitor
    {
        ApplicationFactoryValidationVisitor(final String version)
        {
            super(FacesConfigPackage.eINSTANCE.getFactoryType_ApplicationFactory(),
                    version);
        }
        
        protected String getFullyQualifiedName(EObject eobj) 
        {
            return ((ApplicationFactoryType)eobj).getTextContent();
        }

        protected String getInstanceOf() {
            return IJSFCoreConstants.isJakartaNamespace(getVersion()) ?
                    "jakarta.faces.application.ApplicationFactory": //$NON-NLS-1$
                    "jakarta.faces.application.ApplicationFactory"; //$NON-NLS-1$
        }

        @Override
        protected boolean mustBeClass() {
            return true;
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            // none
            return NO_CHILDREN;
        }
        
    }
    
    private static class FacesContextFactoryValidationVisitor extends ClassNameEObjectValidationVisitor
    {
        FacesContextFactoryValidationVisitor(final String version)
        {
            super(FacesConfigPackage.eINSTANCE.getFactoryType_FacesContextFactory(),
                    version);
        }
        
        protected String getFullyQualifiedName(EObject eobj) 
        {
            return ((FacesContextFactoryType)eobj).getTextContent();
        }

        protected String getInstanceOf() {
            return IJSFCoreConstants.isJakartaNamespace(getVersion()) ?
                    "jakarta.faces.context.FacesContextFactory": //$NON-NLS-1$
                    "jakarta.faces.context.FacesContextFactory"; //$NON-NLS-1$
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            // none
            return NO_CHILDREN;
        }

        @Override
        protected boolean mustBeClass() 
        {
            // must be a class
            return true;
        }
        
    }

    private static class LifecycleFactoryValidationVisitor extends ClassNameEObjectValidationVisitor
    {
        LifecycleFactoryValidationVisitor(final String version)
        {
            super(FacesConfigPackage.eINSTANCE.getFactoryType_LifecycleFactory(),
                    version);
        }
        
        protected String getFullyQualifiedName(EObject eobj) 
        {
            return ((LifecycleFactoryType)eobj).getTextContent();
        }

        protected String getInstanceOf() {
            return IJSFCoreConstants.isJakartaNamespace(getVersion()) ?
                    "jakarta.faces.lifecycle.LifecycleFactory": //$NON-NLS-1$
                    "jakarta.faces.lifecycle.LifecycleFactory"; //$NON-NLS-1$
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            // none
            return NO_CHILDREN;
        }

        @Override
        protected boolean mustBeClass() {
            // must be a class
            return true;
        }
        
    }

    private static class RenderkitFactoryValidationVisitor extends ClassNameEObjectValidationVisitor
    {
        RenderkitFactoryValidationVisitor(final String version)
        {
            super(FacesConfigPackage.eINSTANCE.getFactoryType_RenderKitFactory(),
                    version);
        }
        
        protected String getFullyQualifiedName(EObject eobj) 
        {
            return ((RenderKitFactoryType)eobj).getTextContent();
        }

        protected String getInstanceOf() {
            return IJSFCoreConstants.isJakartaNamespace(getVersion()) ? 
                    "jakarta.faces.render.RenderKitFactory": //$NON-NLS-1$
                    "jakarta.faces.render.RenderKitFactory"; //$NON-NLS-1$
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            // none
            return NO_CHILDREN;
        }

        @Override
        protected boolean mustBeClass() {
            // must be a class
            return true;
        }
    }
    private static class ExceptionHandlerFactoryValidationVisitor extends ClassNameEObjectValidationVisitor
    {
    	ExceptionHandlerFactoryValidationVisitor(final String version)
        {
            super(FacesConfigPackage.eINSTANCE.getFactoryType_ExceptionHandlerFactory(),
                    version);            
        }
        
        protected String getFullyQualifiedName(EObject eobj) 
        {
            return ((ExceptionHandlerFactoryType)eobj).getTextContent();
        }

        protected String getInstanceOf() {
            return IJSFCoreConstants.isJakartaNamespace(getVersion()) ?
                    "jakarta.faces.context.ExceptionHandlerFactory" : //$NON-NLS-1$
                    "jakarta.faces.context.ExceptionHandlerFactory"; //$NON-NLS-1$
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            // none
            return NO_CHILDREN;
        }

        @Override
        protected boolean mustBeClass() {
            // must be a class
            return true;
        }
    }
    private static class ExternalContextFactoryValidationVisitor extends ClassNameEObjectValidationVisitor
    {
    	ExternalContextFactoryValidationVisitor(final String version)
        {
            super(FacesConfigPackage.eINSTANCE.getFactoryType_ExternalContextFactory(),
                    version);
        }
        
        protected String getFullyQualifiedName(EObject eobj) 
        {
            return ((ExternalContextFactoryType)eobj).getTextContent();
        }

        protected String getInstanceOf() {
            return IJSFCoreConstants.isJakartaNamespace(getVersion()) ?
                    "jakarta.faces.context.ExternalContextFactory": //$NON-NLS-1$
                    "jakarta.faces.context.ExternalContextFactory"; //$NON-NLS-1$
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            // none
            return NO_CHILDREN;
        }

        @Override
        protected boolean mustBeClass() {
            // must be a class
            return true;
        }
    }

    private static class ViewDeclarationLanguageFactoryValidationVisitor extends ClassNameEObjectValidationVisitor
    {
    	ViewDeclarationLanguageFactoryValidationVisitor(final String version)
        {
            super(FacesConfigPackage.eINSTANCE.getFactoryType_ViewDeclarationLanguageFactory(),
                    version);
        }
        
        protected String getFullyQualifiedName(EObject eobj) 
        {
            return ((ViewDeclarationLanguageFactoryType)eobj).getTextContent();
        }

        protected String getInstanceOf() {
            return IJSFCoreConstants.isJakartaNamespace(getVersion()) ?
                    "jakarta.faces.view.ViewDeclarationLanguageFactory": //$NON-NLS-1$
                    "jakarta.faces.view.ViewDeclarationLanguageFactory"; //$NON-NLS-1$
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            // none
            return NO_CHILDREN;
        }

        @Override
        protected boolean mustBeClass() {
            // must be a class
            return true;
        }
    }
    private static class PartialViewContextFactoryValidationVisitor extends ClassNameEObjectValidationVisitor
    {
    	PartialViewContextFactoryValidationVisitor(final String version)
        {
            super(FacesConfigPackage.eINSTANCE.getFactoryType_PartialViewContextFactory(),
                    version);
        }
        
        protected String getFullyQualifiedName(EObject eobj) 
        {
            return ((PartialViewContextFactoryType)eobj).getTextContent();
        }

        protected String getInstanceOf() {
            return IJSFCoreConstants.isJakartaNamespace(getVersion()) ?
                    "jakarta.faces.context.PartialViewContextFactory": //$NON-NLS-1$
                    "jakarta.faces.context.PartialViewContextFactory"; //$NON-NLS-1$
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            // none
            return NO_CHILDREN;
        }

        @Override
        protected boolean mustBeClass() {
            // must be a class
            return true;
        }
    }
    private static class TagHandlerDelegateFactoryValidationVisitor extends ClassNameEObjectValidationVisitor
    {
    	TagHandlerDelegateFactoryValidationVisitor(final String version)
        {
            super(FacesConfigPackage.eINSTANCE.getFactoryType_TagHandlerDelegateFactory(),
                    version);
        }
        
        protected String getFullyQualifiedName(EObject eobj) 
        {
            return ((TagHandlerDelegateFactoryType)eobj).getTextContent();
        }

        protected String getInstanceOf() {
            return IJSFCoreConstants.isJakartaNamespace(getVersion()) ?
                    "jakarta.faces.view.facelets.TagHandlerDelegateFactory": //$NON-NLS-1$
                    "jakarta.faces.view.facelets.TagHandlerDelegateFactory"; //$NON-NLS-1$
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            // none
            return NO_CHILDREN;
        }

        @Override
        protected boolean mustBeClass() {
            // must be a class
            return true;
        }
    }
    private static class VisitContextFactoryValidationVisitor extends ClassNameEObjectValidationVisitor
    {
    	VisitContextFactoryValidationVisitor(final String version)
        {
            super(FacesConfigPackage.eINSTANCE.getFactoryType_VisitContextFactory(),
                    version);
        }
        
        protected String getFullyQualifiedName(EObject eobj) 
        {
            return ((VisitContextFactoryType)eobj).getTextContent();
        }

        protected String getInstanceOf() {
            return IJSFCoreConstants.isJakartaNamespace(getVersion()) ?
                    "jakarta.faces.component.visit.VisitContextFactory": //$NON-NLS-1$
                    "jakarta.faces.component.visit.VisitContextFactory"; //$NON-NLS-1$
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            // none
            return NO_CHILDREN;
        }

        @Override
        protected boolean mustBeClass() {
            // must be a class
            return true;
        }
    }    
}
