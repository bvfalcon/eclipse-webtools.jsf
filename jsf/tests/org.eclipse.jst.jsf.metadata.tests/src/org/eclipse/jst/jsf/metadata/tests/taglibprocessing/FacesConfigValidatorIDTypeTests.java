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
package org.eclipse.jst.jsf.metadata.tests.taglibprocessing;

import org.eclipse.jst.jsf.common.internal.types.CompositeType;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.metadata.tests.util.SingleJSPTestCase;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;

public class FacesConfigValidatorIDTypeTests extends SingleJSPTestCase {
	private final String tagName = "validator";
	private final String attrName = "validatorId";
	
	public FacesConfigValidatorIDTypeTests() {
		super(	"/testfiles/jsps/facesConfigValidatorIDType.jsp.data/", 
				"/facesConfigValidatorIDType.jsp", 
				JSFVersion.V1_1,
				"/testfiles/web/faces-config_1_1.xml.data");
	
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		addJavaFile("MyValidator");
	}

//	public void testSanity() {
//        final IJavaProject javaProject = _jdtTestEnv.getJavaProject(); 
//        assertNotNull(javaProject);
//        assertNotNull(_structuredDocument);
//	}
	
	public void testPossibleValues() {
		IPossibleValues pv = (IPossibleValues)getProcessor(IPossibleValues.class, JSF_CORE_URI, tagName, attrName);		
		assertNotNull(pv);
		
		assertEquals(4, pv.getPossibleValues().size());

		assertPossibleValues(pv.getPossibleValues(),
			new String[]{
				"com.foo.myvalidator",
				"jakarta.faces.DoubleRange", 
				"jakarta.faces.Length",
				"jakarta.faces.LongRange"} );

	}
	
	public void testGetExpectedRuntimeValue() {
		IValidELValues vv = (IValidELValues)getProcessor(IValidELValues.class, JSF_CORE_URI, tagName, attrName);	
		assertNotNull(vv);
		
		try {
			assertNotNull(vv.getExpectedRuntimeType());
			assertTrue(vv.getExpectedRuntimeType() instanceof CompositeType);
		} catch (Exception e) {
		}	
	}
	
	public void testValidValues() {
		IValidValues vv = (IValidValues)getProcessor(IValidValues.class, JSF_CORE_URI, tagName, attrName);		
		assertNotNull(vv);
		
		assertTrue(vv.isValidValue("com.foo.myvalidator"));
		assertTrue(vv.isValidValue("jakarta.faces.DoubleRange"));
		assertTrue(vv.isValidValue("jakarta.faces.Length"));
		assertTrue(vv.isValidValue("jakarta.faces.LongRange"));
		
		assertFalse(vv.isValidValue("com.foo.myconverter"));
	}
}
