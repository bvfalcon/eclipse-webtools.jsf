/*******************************************************************************
 * Copyright (c) 2018, 2019 IBM Corporation and others.
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
package org.eclipse.jst.jsf.core.internal.project.facet;

import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.jsf.core.JSFVersion;

/**
 * JSF Utils instance for JSF 4.0.
 * 
 */
class JSFUtils40 extends JSFUtils30 {

	/**
	 * @param modelProvider
	 */
	protected JSFUtils40(final IModelProvider modelProvider) {
		super(JSFVersion.V4_0, modelProvider);
	}

	/**
	 * @param jsfVersion
	 * @param modelProvider
	 */
	protected JSFUtils40(final JSFVersion jsfVersion, final IModelProvider modelProvider) {
		super(jsfVersion, modelProvider);
		if (jsfVersion.compareTo(JSFVersion.V4_0) < 0) {
			throw new IllegalArgumentException("JSF Version must be at least 4.0"); //$NON-NLS-1$
		}
	}
}
