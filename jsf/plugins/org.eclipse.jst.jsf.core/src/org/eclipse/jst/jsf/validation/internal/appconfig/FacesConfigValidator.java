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
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.PhaseListenerType;

/**
 * Top-level validator for the faces-config model
 * 
 * @author cbateman
 *
 */
public class FacesConfigValidator extends EObjectValidationVisitor
{
    /**
     * constructor
     * @param version 
     */
    public FacesConfigValidator(String version) 
    {
        super(version);
    }

    protected EObjectValidationVisitor[] getChildNodeValidators() 
    {
        return new EObjectValidationVisitor[]
        {
            new NavigationRuleValidator(getVersion())
            , new ApplicationValidationVisitor(getVersion())
            , new LifecycleValidationVisitor(getVersion())
            , new FactoryValidationVisitor(getVersion())
            , new ComponentValidatorVisitor(getVersion())
            , new ValidatorTypeValidationVisitor(getVersion())
            , new ConverterValidationVisitor(getVersion())
            , new ManagedBeanValidationVisitor(getVersion())
            , new RenderKitValidationVisitor(getVersion())
            , new BehaviorValidationVisitor(getVersion())
        };
    }

    protected void doValidate(EObject object, List messages, IFile file) 
    {
        // nothing to do
    }
    
    private static class LifecycleValidationVisitor extends EObjectValidationVisitor
    {
        LifecycleValidationVisitor(final String version)
        {
            super(FacesConfigPackage.eINSTANCE.getFacesConfigType_Lifecycle(),
                  version);
        }
        
        protected void doValidate(EObject object, List messages, IFile file) {
            // nothing to validate except in children
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            return new EObjectValidationVisitor[] {
                 new PhaseListenerValidation(getVersion())
            };
        }
        
        private static class PhaseListenerValidation extends ClassNameEObjectValidationVisitor
        {
            PhaseListenerValidation(final String version)
            {
                super(FacesConfigPackage.eINSTANCE.getLifecycleType_PhaseListener(),
                        version);
            }
            protected String getFullyQualifiedName(EObject eobj) {
                return ((PhaseListenerType)eobj).getTextContent();
            }

            protected String getInstanceOf() {
                return IJSFCoreConstants.isJakartaEE(getVersion()) ?
                        "jakarta.faces.event.PhaseListener": //$NON-NLS-1$
                        "javax.faces.event.PhaseListener"; //$NON-NLS-1$
            }

            protected EObjectValidationVisitor[] getChildNodeValidators() {
                // no children
                return NO_CHILDREN;
            }
            @Override
            protected boolean mustBeClass() {
                // must be a class
                return true;
            }
        }
    }
}
