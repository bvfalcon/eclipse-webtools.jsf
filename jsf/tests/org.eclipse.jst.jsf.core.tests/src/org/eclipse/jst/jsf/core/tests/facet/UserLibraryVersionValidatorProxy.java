/*******************************************************************************
 * Copyright (c) 2001, 2009 Oracle Corporation and others.
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


package org.eclipse.jst.jsf.core.tests.facet;

import java.io.IOException;
import java.util.function.Predicate;
import java.util.jar.JarFile;


import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.jsf.common.facet.libraryprovider.UserLibraryVersionValidator;


/**
 * "Proxy" class for the actual UserLibraryVersionValidator. This is only used so
 * that the protected methods in UserLibraryVersionValidator can be tested.
 *
 * The test-cases have package-level access to these protected methods.
 *
 * @author Debajit Adhikary
 *
 */
public class UserLibraryVersionValidatorProxy extends UserLibraryVersionValidator
{
    public UserLibraryVersionValidatorProxy (final Predicate<String> classNameIdentifyingJarPredicate)
    {
        super(classNameIdentifyingJarPredicate);
    }


    @Override
    protected IStatus validateVersionStrings (final String facetVersion, final String libraryVersion)
    {
        return super.validateVersionStrings(facetVersion, libraryVersion);
    }


    @Override
    protected String getLibraryVersion (final JarFile jarFile) throws IOException
    {
        return super.getLibraryVersion(jarFile);
    }
}
