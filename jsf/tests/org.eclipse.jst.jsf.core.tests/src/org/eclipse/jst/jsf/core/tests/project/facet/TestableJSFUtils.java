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
package org.eclipse.jst.jsf.core.tests.project.facet;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import junit.framework.Assert;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFUtils;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class TestableJSFUtils extends JSFUtils
{
    private final JSFUtils _delegate;

    public TestableJSFUtils(final JSFUtils jsfUtils,
            final IModelProvider modelProvider)
    {
        super(jsfUtils.getVersion(), modelProvider);
        Assert.assertNotNull(jsfUtils);
        Assert.assertNotNull(modelProvider);
        _delegate = jsfUtils;
    }

    public String getDisplayName_testable(final IDataModel config)
    {
        return super.getDisplayName(config);
    }

    public String getServletClassname_testable(final Object webApp, final IDataModel config)
    {
        return super.getServletClassname(webApp, config);
    }

    public List<String> getServletMappings_testable(IDataModel config)
    {
        return super.getServletMappings(config);
    }

    public boolean isJSFPage_testable(final IResource resource)
    {
        return super.isJSFPage(resource);
    }

    public boolean isValidKnownExtension_testable(String fileExtension)
    {
        return super.isValidKnownExtension(fileExtension);
    }

    public void printConfigFile_testable(final OutputStream os)
    {
        super.printConfigFile(os);
    }

    @Override
    public void doVersionSpecificConfigFile(PrintWriter pw)
    {
        _delegate.doVersionSpecificConfigFile(pw);
    }

    @Override
    public void updateWebApp(Object webApp, IDataModel config)
    {
        _delegate.updateWebApp(webApp, config);
    }

    @Override
    public void rollbackWebApp(Object webApp)
    {
        _delegate.rollbackWebApp(webApp);
    }

    @Override
    public IPath getFileUrlPath(Object webAppObj, IResource resource,
            IPath existingURL)
    {
        return _delegate.getFileUrlPath(webAppObj, resource, existingURL);
    }
}
