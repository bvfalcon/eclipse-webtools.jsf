/*******************************************************************************
 * Copyright (c) 2007, 2011 Oracle Corporation and others.
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

public class FacesConfigConverterIDTypeTests extends SingleJSPTestCase {

	public FacesConfigConverterIDTypeTests() {
		super(	"/testfiles/jsps/facesConfigConverterIDType.jsp.data/", 
				"/facesConfigConverterIDType.jsp", 
				JSFVersion.V1_1,
				"/testfiles/web/faces-config_1_1.xml.data");
	
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		addJavaFile("MyConverter");
	}

//	public void testSanity() {
//        final IJavaProject javaProject = _jdtTestEnv.getJavaProject(); 
//        assertNotNull(javaProject);
//        assertNotNull(_structuredDocument);
//	}
	
	public void testPossibleValues() {
		IPossibleValues pv = (IPossibleValues)getProcessor(IPossibleValues.class, JSF_HTML_URI, "inputText", "converter");		
		assertNotNull(pv);
		
		assertEquals(13, pv.getPossibleValues().size());

		assertPossibleValues(pv.getPossibleValues(),
			new String[]{
				"com.foo.myconverter",
				"jakarta.faces.BigDecimal", 
				"jakarta.faces.BigInteger",
				"jakarta.faces.Byte",
				"jakarta.faces.Boolean",
				"jakarta.faces.Character",
				"jakarta.faces.DateTime",
				"jakarta.faces.Double",
				"jakarta.faces.Float",
				"jakarta.faces.Integer",
				"jakarta.faces.Number",
				"jakarta.faces.Long",
				"jakarta.faces.Short"} );

	}
	public void testGetExpectedRuntimeValue() {
		IValidELValues vv = (IValidELValues)getProcessor(IValidELValues.class, JSF_HTML_URI, "inputText", "converter");		
		assertNotNull(vv);
		
		try {
			assertNotNull(vv.getExpectedRuntimeType());
			assertTrue(vv.getExpectedRuntimeType() instanceof CompositeType);
		} catch (Exception e) {
		}	
	}
	
	public void testValidValues() {
		IValidValues vv = (IValidValues)getProcessor(IValidValues.class, JSF_HTML_URI, "inputText", "converter");		
		assertNotNull(vv);
		
		assertTrue(vv.isValidValue("com.foo.myconverter"));
		assertTrue(vv.isValidValue("jakarta.faces.BigDecimal"));
		assertTrue(vv.isValidValue("jakarta.faces.BigInteger"));
		assertTrue(vv.isValidValue("jakarta.faces.Byte"));
		assertTrue(vv.isValidValue("jakarta.faces.Boolean"));
		assertTrue(vv.isValidValue("jakarta.faces.Character"));
		assertTrue(vv.isValidValue("jakarta.faces.DateTime"));
		assertTrue(vv.isValidValue("jakarta.faces.Double"));
		assertTrue(vv.isValidValue("jakarta.faces.Float"));
		assertTrue(vv.isValidValue("jakarta.faces.Integer"));
		assertTrue(vv.isValidValue("jakarta.faces.Number"));
		assertTrue(vv.isValidValue("jakarta.faces.Long"));
		assertTrue(vv.isValidValue("jakarta.faces.Short"));
		
		assertFalse(vv.isValidValue("com.foo.myconvertersubclass1"));
	}
}
