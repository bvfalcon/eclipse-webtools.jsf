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
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitClassType;

/**
 * Validates the renderkit
 */
public class RenderKitValidationVisitor extends EObjectValidationVisitor 
{

    /**
     * @param version
     */
    public RenderKitValidationVisitor(String version) 
    {
        super(FacesConfigPackage.eINSTANCE.getFacesConfigType_RenderKit()
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
            new RenderKitClassValidationVisitor(getVersion()),
            new RendererValidationVisitor(getVersion()),
            new ClientBehaviorRendererValidationVisitor(getVersion())
        };
    }

    private static class RenderKitClassValidationVisitor extends ClassNameEObjectValidationVisitor
    {
        /**
         * @param version
         */
        public RenderKitClassValidationVisitor(String version) 
        {
            super(FacesConfigPackage.eINSTANCE.getRenderKitType_RenderKitClass()
                    , version);
        }

        protected String getFullyQualifiedName(EObject eobj) 
        {
            return ((RenderKitClassType)eobj).getTextContent();
        }

        protected String getInstanceOf() {
            return IJSFCoreConstants.isJakartaEE(getVersion()) ?
                    "jakarta.faces.render.RenderKit": //$NON-NLS-1$
                    "javax.faces.render.RenderKit"; //$NON-NLS-1$
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
