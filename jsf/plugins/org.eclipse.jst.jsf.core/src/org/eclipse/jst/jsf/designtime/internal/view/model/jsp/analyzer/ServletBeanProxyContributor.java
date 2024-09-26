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
/**
 * 
 */
package org.eclipse.jst.jsf.designtime.internal.view.model.jsp.analyzer;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jem.internal.proxy.core.ConfigurationContributorAdapter;
import org.eclipse.jem.internal.proxy.core.IConfigurationContributionController;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.osgi.framework.Bundle;

class ServletBeanProxyContributor extends ConfigurationContributorAdapter
{
    private final JSFVersion _jsfVersion;

    public ServletBeanProxyContributor(final IProject project)
    {
        _jsfVersion = getProjectVersion(project);
        if (_jsfVersion == null)
        {
            throw new IllegalArgumentException("jsfVersion must not be null"); //$NON-NLS-1$
        }
    }

    @Override
    public void contributeClasspaths(
            final IConfigurationContributionController controller)
            throws CoreException
    {
        if (_jsfVersion != null && _jsfVersion.compareTo(JSFVersion.V1_2) < 0)
        {
            final Bundle servletBundle = Platform.getBundle("jakarta.servlet"); //$NON-NLS-1$
            controller.contributeClasspath(servletBundle, (IPath) null,
                    IConfigurationContributionController.APPEND_USER_CLASSPATH,
                    true);

            final Bundle jspBundle = Platform.getBundle("jakarta.servlet.jsp"); //$NON-NLS-1$
            controller.contributeClasspath(jspBundle, (IPath) null,
                    IConfigurationContributionController.APPEND_USER_CLASSPATH,
                    true);
        }
        else
        {
            final Bundle coreBundle = JSFCorePlugin.getDefault().getBundle();
            controller.contributeClasspath(coreBundle,
                    "/jars/fake_jsp_21.jar", //$NON-NLS-1$
                    IConfigurationContributionController.APPEND_USER_CLASSPATH,
                    false);
            controller.contributeClasspath(coreBundle,
                    "/jars/fake_el.jar", //$NON-NLS-1$
                    IConfigurationContributionController.APPEND_USER_CLASSPATH,
                    false);
        }
    }

    static JSFVersion getProjectVersion(final IProject project)
    {
        return JSFVersion.valueOfProject(project);
    }

}