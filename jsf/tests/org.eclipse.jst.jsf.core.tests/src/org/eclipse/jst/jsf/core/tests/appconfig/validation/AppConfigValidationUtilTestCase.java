/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.core.tests.appconfig.validation;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.ProjectTestEnvironment;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.jst.jsf.validation.internal.appconfig.AppConfigValidationUtil;
import org.eclipse.jst.jsf.validation.internal.appconfig.DiagnosticFactory;
import org.eclipse.jst.jsf.validation.internal.appconfig.ILocalizedMessage;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * Unit tests for AppConfigValidationUtil
 * 
 * @author cbateman
 *
 */
public class AppConfigValidationUtilTestCase extends TestCase 
{
    private ProjectTestEnvironment      _testEnvironment;
    private JDTTestEnvironment          _jdtTestEnvironment;
    
    @Override
    protected void setUp() throws Exception 
    {
        super.setUp();
        
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.uk.oracle.com", "80");
        
        _testEnvironment =  
            new WebProjectTestEnvironment("AppConfigValidationUtilTestCase_"+getName());
        _testEnvironment.createProject(true);
        
        _jdtTestEnvironment = new JDTTestEnvironment(_testEnvironment);
        
        TestFileResource resource = new TestFileResource();
        resource.load(TestsPlugin.getDefault().getBundle(), 
                      "/testfiles/TestEnum1.java.data");

        _jdtTestEnvironment.addSourceFile("src", "org.eclipse.jst.jsf.core.tests.util", "TestEnum1", resource.toString());
        
        resource = new TestFileResource();
        resource.load(TestsPlugin.getDefault().getBundle(), 
                        "/testfiles/TestBean1.java.data");
        _jdtTestEnvironment.addSourceFile("src", "com.test", "TestBean1", resource.toString());

        resource = new TestFileResource();
        resource.load(TestsPlugin.getDefault().getBundle(), 
                        "/testfiles/TestBean1Subclass.java.data");
        _jdtTestEnvironment.addSourceFile("src", "com.test", "TestBean1Subclass", resource.toString());
    }

    /**
     * test validateELExpression method
     */
    public void testValidateELExpression()
    {
        final String validELString1 = "#{x.y}";
        final String validELString2 = "#{a.b.c}";
        final String inValidEmptyExpr = "#{}";
        final String inValidNoBraces = "x.y";
        final String inValidSyntaxInEL = "#{x.[}";
        
        assertNull(AppConfigValidationUtil.validateELExpression(validELString1));
        assertNull(AppConfigValidationUtil.validateELExpression(validELString2));
        
        IMessage message = AppConfigValidationUtil.validateELExpression(inValidEmptyExpr);
        assertNotNull(message);
        assertEquals(DiagnosticFactory.SYNTAX_ERROR_IN_EL_ID, 
                ((ILocalizedMessage)message).getErrorCode());
        
        message = AppConfigValidationUtil.validateELExpression(inValidNoBraces);
        assertNotNull(message);
        assertEquals(DiagnosticFactory.EL_EXPR_MUST_BE_IN_HASH_BRACES_ID, 
                ((ILocalizedMessage)message).getErrorCode());

        message = AppConfigValidationUtil.validateELExpression(inValidSyntaxInEL);
        assertNotNull(message);
        assertEquals(DiagnosticFactory.SYNTAX_ERROR_IN_EL_ID, 
                ((ILocalizedMessage)message).getErrorCode());
    }
    
    public void testValidateClassName_NoError() throws Exception
    {
        final IProject project = _testEnvironment.getTestProject();
        
        
        // null indicates no problem
        assertNull(AppConfigValidationUtil.validateClassName
            ("com.test.TestBean1", null, true, project));
        // TestBean1 is a class, so mustBeClass should have no effect on outcome
        assertNull(AppConfigValidationUtil.validateClassName
            ("com.test.TestBean1", null, false, project));
        
        // should react the same as TestBean1 since is a class
        assertNull(AppConfigValidationUtil.validateClassName
                ("com.test.TestBean1Subclass", null, true, project));
        assertNull(AppConfigValidationUtil.validateClassName
                ("com.test.TestBean1Subclass", null, false, project));
        // TestBean1Subclass is-a TestBean1
        assertNull(AppConfigValidationUtil.validateClassName
                ("com.test.TestBean1Subclass", "com.test.TestBean1", true, project));
        assertNull(AppConfigValidationUtil.validateClassName
                ("com.test.TestBean1Subclass", "com.test.TestBean1", false, project));
        
        // will succeed for enums if mustBeClass == false
        assertNull(AppConfigValidationUtil.validateClassName
                ("org.eclipse.jst.jsf.core.tests.util.TestEnum1", null, false, project));
        // the enum is-a Enum
        assertNull(AppConfigValidationUtil.validateClassName
                ("org.eclipse.jst.jsf.core.tests.util.TestEnum1", "java.lang.Enum", false, project));
    }
    
    public void testValidateClassName_Errors() throws Exception
    {
        final IProject project = _testEnvironment.getTestProject();

        // invalid name
        IMessage error = AppConfigValidationUtil.validateClassName
            ("com.test.NoTaVAliDClAss", null, false, project);
        assertNotNull(error);
        assertEquals(DiagnosticFactory.CANNOT_FIND_CLASS_NAME_ID, ((ILocalizedMessage)error).getErrorCode());
        
        // must be a class but is an enum
        error = AppConfigValidationUtil.validateClassName
            ("org.eclipse.jst.jsf.core.tests.util.TestEnum1", null, true, project);
        assertNotNull(error);
        assertEquals(DiagnosticFactory.FULLY_QUALIFIED_NAME_MUST_BE_A_CLASS_ID, ((ILocalizedMessage)error).getErrorCode());
        
        // must be an instanceof jakarta.faces.Converter but is not
        error = AppConfigValidationUtil.validateClassName
            ("com.test.TestBean1", "jakarta.faces.Converter", true, project);
        assertNotNull(error);
        assertEquals(DiagnosticFactory.CLASS_MUST_BE_INSTANCE_OF_ID, ((ILocalizedMessage)error).getErrorCode());
    }
}
