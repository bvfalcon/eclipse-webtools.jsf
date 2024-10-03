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
import org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererClassType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;

/**
 * ClientBehaviorRenderer validation
 *
 */
public class ClientBehaviorRendererValidationVisitor extends EObjectValidationVisitor {

    /**
     * @param version
     */
    public ClientBehaviorRendererValidationVisitor(String version)
    {
        super(FacesConfigPackage.eINSTANCE.getRenderKitType_ClientBehaviorRenderer(),
                version);
    }

    protected void doValidate(EObject object, List messages, IFile file) {
        // nothing to do
    }

    protected EObjectValidationVisitor[] getChildNodeValidators() {
        return new EObjectValidationVisitor[]
        {
            new ClientBehaviorRendererClassValidationVisitor(getVersion())
        };
    }

    private static class ClientBehaviorRendererClassValidationVisitor extends ClassNameEObjectValidationVisitor
    {
        /**
         * @param version
         */
        public ClientBehaviorRendererClassValidationVisitor(String version) 
        {
            super(FacesConfigPackage.eINSTANCE.getClientBehaviorRendererType_ClientBehaviorRendererClass()
                    , version);
        }

        protected String getFullyQualifiedName(EObject eobj) 
        {
            return ((ClientBehaviorRendererClassType)eobj).getTextContent();
        }

        protected String getInstanceOf() {
            return IJSFCoreConstants.isJakartaEE(getVersion()) ?
                    "jakarta.faces.render.ClientBehaviorRenderer": //$NON-NLS-1$
                    "javax.faces.render.ClientBehaviorRenderer"; //$NON-NLS-1$
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
