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
import org.eclipse.jst.jsf.facesconfig.emf.BehaviorClassType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;

/**
 * Validates the behavior
 */
public class BehaviorValidationVisitor extends EObjectValidationVisitor 
{

    /**
     * @param version
     */
    public BehaviorValidationVisitor(String version) 
    {
        super(FacesConfigPackage.eINSTANCE.getFacesConfigType_Behavior()
                ,version);
    }

    protected void doValidate(EObject object, List messages, IFile file) 
    {
        // nothing to do 
    }

    protected EObjectValidationVisitor[] getChildNodeValidators()
    {
        return new EObjectValidationVisitor[]
        {
            new BehaviorClassValidationVisitor(getVersion()),
            new AttributeValidationVisitor(FacesConfigPackage.eINSTANCE.getBehaviorType_Attribute(), getVersion()),
            new PropertyValidationVisitor
                (FacesConfigPackage.eINSTANCE.getBehaviorType_Property()
                ,FacesConfigPackage.eINSTANCE.getBehaviorType_BehaviorClass()
                ,getVersion())
        };
    }

    private static class BehaviorClassValidationVisitor extends ClassNameEObjectValidationVisitor
    {
        /**
         * @param version
         */
        public BehaviorClassValidationVisitor(String version) 
        {
            super(FacesConfigPackage.eINSTANCE.getBehaviorType_BehaviorClass()
                    , version);
        }

        protected String getFullyQualifiedName(EObject eobj) 
        {
            return ((BehaviorClassType)eobj).getTextContent();
        }

        protected String getInstanceOf() {
            return IJSFCoreConstants.isJakartaNamespace(getVersion()) ?
                    "jakarta.faces.component.behavior.Behavior": //$NON-NLS-1$
                    "jakarta.faces.component.behavior.Behavior"; //$NON-NLS-1$
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            return NO_CHILDREN;
        }

        @Override
        protected boolean mustBeClass() {
            // must be a class
            return true;
        }
    }
}
