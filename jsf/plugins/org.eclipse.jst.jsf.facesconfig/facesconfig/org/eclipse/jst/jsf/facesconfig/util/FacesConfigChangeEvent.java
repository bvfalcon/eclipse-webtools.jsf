/***************************************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.util;

/**
 * This class should NOT be referenced or extended by clients.
 * 
 * @author xnjiang
 *
 */
final class FacesConfigChangeEvent implements IFacesConfigChangeEvent {

	private boolean bManagedBeandChanged = true;
	private boolean bNavigationRuleChanged = true;
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.facesconfig.IFacesConfigChangeEvent#isManagedBeanChanged()
	 */
	public boolean isManagedBeanChanged() {
		return bManagedBeandChanged;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.facesconfig.IFacesConfigChangeEvent#isNavigationRuleChanged()
	 */
	public boolean isNavigationRuleChanged() {
		return bNavigationRuleChanged;
	}
}
