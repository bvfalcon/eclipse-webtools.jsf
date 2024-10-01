/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.test;

import java.util.List;

import org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.JSFUtil;
import org.eclipse.jst.jsf.facesconfig.ui.test.util.TestUtil;

/**
 * @author sfshi
 *
 */
public class JSPUtilTest extends FacesConfigEditorTest {

	/**
	 * Test method for {@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.JSfUtil#getActionListInJSFFile(java.lang.String)}.
	 * @throws Exception 
	 */
	public void testGetActionListInJSPFile() throws Exception {
		TestUtil.copyFile(project, "WebContent", "page1.jsp");
		
		List<?> actionNodes = JSFUtil.getActionListInJSFFile("/emptyjsfproject/WebContent/page1.jsp");
		assertEquals(3, actionNodes.size());
	}

}
