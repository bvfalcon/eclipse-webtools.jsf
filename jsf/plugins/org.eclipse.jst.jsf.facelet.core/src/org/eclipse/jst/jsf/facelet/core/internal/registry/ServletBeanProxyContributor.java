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
/**
 * 
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jem.internal.proxy.core.ConfigurationContributorAdapter;
import org.eclipse.jem.internal.proxy.core.IConfigurationContributionController;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.osgi.framework.Bundle;

/**
 * Contributes dummy jsp servlet api to smooth the JEM instantiation for introspection.
 * 
 * @author cbateman
 *
 */
public class ServletBeanProxyContributor extends ConfigurationContributorAdapter
{
    private static final String JAVAX_SERVLET_JSP = "jakarta.servlet.jsp"; //$NON-NLS-1$
    private static final String JAVAX_SERVLET = "jakarta.servlet"; //$NON-NLS-1$
    private final JSFVersion _jsfVersion;

    /**
     * @param jsfVersion
     */
    public ServletBeanProxyContributor(final JSFVersion jsfVersion)
    {
        if (jsfVersion == null)
        {
            throw new IllegalArgumentException("jsfVersion must not be null"); //$NON-NLS-1$
        }
        
        _jsfVersion = jsfVersion;
    }

    @Override
    public void contributeClasspaths(
            final IConfigurationContributionController controller)
            throws CoreException
    {
        if (_jsfVersion != JSFVersion.V1_2)
        {
            final Bundle servletBundle = Platform.getBundle(JAVAX_SERVLET);
            controller.contributeClasspath(servletBundle, (IPath) null,
                    IConfigurationContributionController.APPEND_USER_CLASSPATH,
                    true);

            final Bundle jspBundle = Platform.getBundle(JAVAX_SERVLET_JSP);
            controller.contributeClasspath(jspBundle, (IPath) null,
                    IConfigurationContributionController.APPEND_USER_CLASSPATH,
                    true);
        }
        
    }
}