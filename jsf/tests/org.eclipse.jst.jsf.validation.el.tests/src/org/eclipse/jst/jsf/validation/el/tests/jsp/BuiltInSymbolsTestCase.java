/*******************************************************************************
 * Copyright (c) 2001, 2014 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.validation.el.tests.jsp;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.jsf.validation.el.tests.base.ELAssert;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;

/**
 * Test cases for built-in (implicit) symbol resolution
 * 
 * @author cbateman
 *
 */
public class BuiltInSymbolsTestCase extends SingleJSPTestCase
{
    public BuiltInSymbolsTestCase()
    {
        super("/testdata/jsps/builtinSymbols.jsp.data", "/builtinSymbols.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        JSFCoreUtilHelper.injectTestTagRegistryFactoryProvider(JSFCoreUtilHelper.createSimpleRegistryFactory());
    }

    
    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
        JSFCoreUtilHelper.injectTestTagRegistryFactoryProvider(null);
    }

    @Override
    public void testSanity()
    {
        assertEquals("applicationScope", ELAssert.getELText(_structuredDocument,975));
        assertEquals("sessionScope", ELAssert.getELText(_structuredDocument,1024));
        assertEquals("requestScope", ELAssert.getELText(_structuredDocument,1069));
        assertEquals("cookie", ELAssert.getELText(_structuredDocument,1114));
        assertEquals("facesContext", ELAssert.getELText(_structuredDocument,1153));
        assertEquals("header", ELAssert.getELText(_structuredDocument,1198));
        assertEquals("headerValues", ELAssert.getELText(_structuredDocument,1237));
        assertEquals("initParam", ELAssert.getELText(_structuredDocument,1282));
        assertEquals("param", ELAssert.getELText(_structuredDocument,1324));
        assertEquals("paramValues", ELAssert.getELText(_structuredDocument,1362));
        assertEquals("view", ELAssert.getELText(_structuredDocument,1406));
        assertEquals("applicationScope.mapBean", ELAssert.getELText(_structuredDocument,1492));
        assertEquals("sessionScope.myBean", ELAssert.getELText(_structuredDocument,1596));
        assertEquals("sessionScope.mapBean1", ELAssert.getELText(_structuredDocument,1645));
        assertEquals("sessionScope.myBeanSettable", ELAssert.getELText(_structuredDocument,1696));
        assertEquals("requestScope.myBeanSubClass", ELAssert.getELText(_structuredDocument,1803));
        assertEquals("requestScope.hiddenBean", ELAssert.getELText(_structuredDocument,1860));
        assertEquals("requestScope.bundle", ELAssert.getELText(_structuredDocument,1913));
        assertEquals("requestScope.bundle2", ELAssert.getELText(_structuredDocument,1962));
        assertEquals("empty cookie", ELAssert.getELText(_structuredDocument,2096));
        assertEquals("empty header", ELAssert.getELText(_structuredDocument,2138));
        assertEquals("empty headerValues", ELAssert.getELText(_structuredDocument,2180));
        assertEquals("empty param", ELAssert.getELText(_structuredDocument,2228));
        assertEquals("empty paramValues", ELAssert.getELText(_structuredDocument,2269));
        assertEquals("facesContext.application", ELAssert.getELText(_structuredDocument,2367));
        assertEquals("facesContext.clientIdsWithMessages", ELAssert.getELText(_structuredDocument,2421));
        assertEquals("facesContext.externalContext", ELAssert.getELText(_structuredDocument,2485));
        assertEquals("facesContext.maximumSeverity", ELAssert.getELText(_structuredDocument,2543));
        assertEquals("facesContext.messages", ELAssert.getELText(_structuredDocument,2601));
        assertEquals("facesContext.renderKit", ELAssert.getELText(_structuredDocument,2652));
        assertEquals("facesContext.renderResponse", ELAssert.getELText(_structuredDocument,2704));
        assertEquals("facesContext.responseComplete", ELAssert.getELText(_structuredDocument,2761));
        assertEquals("facesContext.responseStream", ELAssert.getELText(_structuredDocument,2820));
        assertEquals("facesContext.responseWriter", ELAssert.getELText(_structuredDocument,2877));
        assertEquals("facesContext.viewRoot", ELAssert.getELText(_structuredDocument,2934));
        assertEquals("view.viewId", ELAssert.getELText(_structuredDocument,3030));
        assertEquals("view.family", ELAssert.getELText(_structuredDocument,3071));
        assertEquals("view.locale", ELAssert.getELText(_structuredDocument,3112));
        assertEquals("view.renderKitId", ELAssert.getELText(_structuredDocument,3153));
        assertEquals("view.viewId", ELAssert.getELText(_structuredDocument,3199));
        assertEquals("sessionScope.myBean.integerProperty", ELAssert.getELText(_structuredDocument,3295));
        assertEquals("requestScope.bundle.bundleProp2", ELAssert.getELText(_structuredDocument,3363));
        assertEquals("3 + sessionScope.myBean.integerProperty", ELAssert.getELText(_structuredDocument,3478));

        assertEquals("applicationScope.notAMember", ELAssert.getELText(_structuredDocument,3572));
        assertEquals("sessionScope.notAMember", ELAssert.getELText(_structuredDocument,3632));
        assertEquals("requestScope.notAMember", ELAssert.getELText(_structuredDocument,3688));
        assertEquals("cookie.notAMember", ELAssert.getELText(_structuredDocument,3744));
        assertEquals("facesContext.notAMember", ELAssert.getELText(_structuredDocument,3794));
        assertEquals("header.notAMember", ELAssert.getELText(_structuredDocument,3850));
        assertEquals("headerValues.notAMember", ELAssert.getELText(_structuredDocument,3900));
        assertEquals("initParam.notAMember", ELAssert.getELText(_structuredDocument,3956));
        assertEquals("param.notAMember", ELAssert.getELText(_structuredDocument,4009));
        assertEquals("paramValues.notAMember", ELAssert.getELText(_structuredDocument,4058));
        assertEquals("view.notAMember", ELAssert.getELText(_structuredDocument,4113));
        assertEquals("applicationScope.myBean_none", ELAssert.getELText(_structuredDocument,4209));
        assertEquals("sessionScope.myBean_none", ELAssert.getELText(_structuredDocument,4270));
        assertEquals("requestScope.myBean_none", ELAssert.getELText(_structuredDocument,4327));

        assertEquals("!initParam", ELAssert.getELText(_structuredDocument,4400));
    }

    @Override
    public void testNoErrorExprs()
    {
        assertNoError(975, TypeConstants.TYPE_MAP);
        assertNoError(1024, TypeConstants.TYPE_MAP);
        assertNoError(1069, TypeConstants.TYPE_MAP);
        assertNoError(1114, TypeConstants.TYPE_MAP);
        assertNoError(1153, "Ljakarta.faces.context.FacesContext;");
        assertNoError(1198, TypeConstants.TYPE_MAP);
        assertNoError(1237, TypeConstants.TYPE_MAP);
        assertNoError(1282, TypeConstants.TYPE_MAP);
        assertNoError(1324, TypeConstants.TYPE_MAP);
        assertNoError(1362, TypeConstants.TYPE_MAP);
        assertNoError(1406, "Ljakarta.faces.component.UIViewRoot;");
        assertNoError(1492, "Lbeans.MapBean;");
        assertNoError(1596, "Lbeans.MyBean;");
        assertNoError(1645, "Lbeans.MapBean;");
        assertNoError(1696, "Lbeans.MyBeanSettable;");
        assertNoError(1803, "Lbeans.MyBeanSubClass;");
        assertNoError(1860, "Lbeans.MyBean;");

        assertNoError(1913, TypeConstants.TYPE_MAP);
        assertNoError(1962, TypeConstants.TYPE_MAP);

        assertNoError(2096, Signature.SIG_BOOLEAN);
        assertNoError(2138, Signature.SIG_BOOLEAN);
        assertNoError(2180, Signature.SIG_BOOLEAN);
        assertNoError(2228, Signature.SIG_BOOLEAN);
        assertNoError(2269, Signature.SIG_BOOLEAN);
        // TODO: can't check these until we can import the required API jars
        // into the project
        //        assertEquals("facesContext.application", getELText(_structuredDocument,2367));
        //        assertEquals("facesContext.clientIdsWithMessages", getELText(_structuredDocument,2421));
        //        assertEquals("facesContext.externalContext", getELText(_structuredDocument,2485));
        //        assertEquals("facesContext.maximumSeverity", getELText(_structuredDocument,2543));
        //        assertEquals("facesContext.messages", getELText(_structuredDocument,2601));
        //        assertEquals("facesContext.renderKit", getELText(_structuredDocument,2652));
        //        assertEquals("facesContext.renderResponse", getELText(_structuredDocument,2704));
        //        assertEquals("facesContext.responseComplete", getELText(_structuredDocument,2761));
        //        assertEquals("facesContext.responseStream", getELText(_structuredDocument,2820));
        //        assertEquals("facesContext.responseWriter", getELText(_structuredDocument,2877));
        //        assertEquals("facesContext.viewRoot", getELText(_structuredDocument,2934));
        //        assertEquals("view.viewId", getELText(_structuredDocument,3030));
        //        assertEquals("view.family", getELText(_structuredDocument,3071));
        //        assertEquals("view.locale", getELText(_structuredDocument,3112));
        //        assertEquals("view.renderKitId", getELText(_structuredDocument,3153));
        //        assertEquals("view.viewId", getELText(_structuredDocument,3199));
        assertNoError(3295, Signature.SIG_INT);
        assertNoError(3363, TypeConstants.TYPE_STRING);
        assertNoError(3478, Signature.SIG_LONG);
        
        // can't know what's actually in the scope map.  so anything unresolved is just
        // no error Java object.
        assertNoError(3572, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(3632,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(3688,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(3744,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(3794,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(3850,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(3900,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(3956,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(4009,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(4058,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(4113,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(4209,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(4270,TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(4327,TypeConstants.TYPE_JAVAOBJECT);
    }
    @Override
    public void testWarningExprs()
    {
        // no warnings.  The issues marked warnings in the jsp test data
        // are no longer, since we realized that maps can contain anything...
        // the  only exceptino is FacesContext which is resovled to a map 
        // at test time since the  API  is not in the classpath.  This
        // should be changed if start using jsf api/impl in these tests TODO
    }

    @Override
    public void testErrorExprs()
    {
    	/* reduced to warning for bug 243674
        final List<ReportedProblem> list = assertSemanticError(4400,null,1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);
        */
    }
}
