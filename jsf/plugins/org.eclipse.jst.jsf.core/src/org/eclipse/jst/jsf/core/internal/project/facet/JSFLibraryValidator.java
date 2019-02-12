/*******************************************************************************
 * Copyright (c) 2001, 2009 Oracle Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors: Oracle Corporation - initial API and implementation
 *******************************************************************************/


package org.eclipse.jst.jsf.core.internal.project.facet;

import org.eclipse.jst.jsf.common.facet.libraryprovider.UserLibraryVersionValidator;


/**
 * Checks that the JSF user library is version-compatible with the JSF facet.
 * 
 * @author Debajit Adhikary
 * 
 */
public class JSFLibraryValidator extends UserLibraryVersionValidator
{
    private static final String CLASS_NAME_IDENTIFYING_IMPLEMENTATION_JAR = "javax/faces/render/RenderKit.class"; //$NON-NLS-1$


    /**
     * Construct a validator for the JSF facet.
     */
    public JSFLibraryValidator ()
    {
        super(CLASS_NAME_IDENTIFYING_IMPLEMENTATION_JAR);
    }
}
