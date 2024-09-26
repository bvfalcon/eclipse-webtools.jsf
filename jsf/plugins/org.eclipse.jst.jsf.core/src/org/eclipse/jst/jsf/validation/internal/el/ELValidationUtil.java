/*******************************************************************************
 * Copyright (c) 2001, 2011 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.validation.internal.el;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;

/**
 * Utility methods used during EL validation.
 * 
 * @author ian.trimble@oracle.com
 */
public class ELValidationUtil {

	/**
	 * Tests if the passed IResource instance's project has (at least) EL 2.2 on its runtime
	 * classpath.
	 * 
	 * @param resource IResource instance to test.
	 * @return <code>true</code> if the project has (at least) EL 2.2 on it's runtime classpath,
	 * else <code>false</code>.
	 */
	public static boolean isProjectEL22(final IResource resource) {
		boolean isEL22 = false;
		if (resource != null) {
			final IProject project = resource.getProject();
			if (project != null) {
				final IJavaProject javaProject = JavaCore.create(project);
				if (javaProject != null) {
					try {
						final IType type = javaProject.findType("jakarta.el.ValueReference"); //$NON-NLS-1$
						isEL22 = (type != null); 
					} catch (JavaModelException ignored) {
						//ignore; isEL22 will remain false
					}
				}
			}
		}
		return isEL22;
	}

	/**
	 * Tests if the project associated with the passed context has (at least) EL 2.2 on its runtime
	 * classpath.
	 * 
	 * @param context IStructuredDocumentContext instance to test.
	 * @return <code>true</code> if the project has (at least) EL 2.2 on it's runtime classpath,
	 * else <code>false</code>.
	 */
	public static boolean isProjectEL22(final IStructuredDocumentContext context) {
		boolean isEL22 = false;
		if (context != null) {
			final IWorkspaceContextResolver resolver =
					IStructuredDocumentContextResolverFactory.INSTANCE.getWorkspaceContextResolver(context);
			if (resolver != null) {
				final IResource resource = resolver.getResource();
				if (resource != null) {
					isEL22 = isProjectEL22(resource);
				}
			}
		}
        return isEL22;
	}

}
