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
package org.eclipse.jst.jsf.core.tests.project.facet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.javaee.web.WebAppVersionType;
import org.eclipse.jst.javaee.web.WebFactory;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.internal.project.facet.IJSFFacetInstallDataModelProperties;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFUtilFactory;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFUtils;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.mock.MockBundle;
import org.eclipse.jst.jsf.test.util.mock.MockDataModel;
import org.eclipse.jst.jsf.test.util.mock.MockDataModel.MockPropertyHolder;
import org.eclipse.jst.jsf.test.util.mock.MockModelProvider;
import org.eclipse.jst.jsf.test.util.mock.MockResource;

//@Category(NoPluginEnvironment.class)
public abstract class TestJSFUtils extends TestCase
{
    private static final String PATTERN_SLASH_FACES_SLASH_ALL = "/faces/*";
    // private WebProjectTestEnvironment _webProjectTestEnv;
    protected TestableJSFUtils _jsfUtils;
    protected Object _modelObject;
    private WebAppAccessor _webAppAccessor;

//    @Before
    protected void setUp() throws Exception
    {
        // for these tests, it doesn't matter which version is created.
        _modelObject = new Object();
        _jsfUtils = new TestableJSFUtils(new JSFUtilFactory().create(
                getVersionToTestIn(), null),
                new MockModelProvider(_modelObject));
        assertEquals(getVersionToTestIn(), _jsfUtils.getVersion());
        _webAppAccessor = createWebAccessor();
    }

    protected void testRollbackWebApp(final String version)
    {
        // safe on an empty web app
        final Object webApp = _webAppAccessor.createWebApp(version);
        _jsfUtils.rollbackWebApp(webApp);

        // Add servlet
        final Object servlet = _webAppAccessor.createServlet("Foobar",
                JSFUtils.JSF_SERVLET_CLASS);
        _webAppAccessor.addServletToWebApp(webApp, servlet);
        _jsfUtils.rollbackWebApp(webApp);
        // servlet should be gone
        assertEquals(0, _webAppAccessor.getServlets(webApp).size());
        assertTrue(_webAppAccessor.getServlet(webApp, "Foobar") == null);

        // Add servlet and servlet mapping and context param
        _webAppAccessor.addServletToWebApp(webApp, servlet);
        Object mapping = _webAppAccessor.createServletMapping();
        _webAppAccessor.setMappingData(webApp, Collections
                .singletonList(".xhtml"), servlet, mapping);
        _webAppAccessor.addMappingToWebApp(webApp, mapping);
        Object contextParam = _webAppAccessor.createContextParam(JSFUtils.JSF_CONFIG_CONTEXT_PARAM, "/WEB-INF/faces-config.xml", version);
        _webAppAccessor.setContextParam(webApp, contextParam, version);
        assertEquals(1, _webAppAccessor.getServlets(webApp).size());
        assertTrue(_webAppAccessor.getServlet(webApp, "Foobar") == servlet);
        assertEquals(1, _webAppAccessor.getServletMappings_WebApp(webApp)
                .size());
        assertEquals(1, _webAppAccessor.getContextParams(webApp, version).size());
        _jsfUtils.rollbackWebApp(webApp);
        // servlet should be gone
        assertEquals(0, _webAppAccessor.getServlets(webApp).size());
        assertTrue(_webAppAccessor.getServlet(webApp, "Foobar") == null);
        // servlet mapping should be gone.
        assertEquals(0, _webAppAccessor.getServletMappings_WebApp(webApp)
                .size());
        // context param should be gone
        assertEquals(0, _webAppAccessor.getContextParams(webApp, version).size());

        // add two servlet and servlet mappings and verify it only removes
        // the JSF one
        // Add servlet and servlet mapping
        _webAppAccessor.addServletToWebApp(webApp, servlet);
        Object servlet2 = _webAppAccessor.createServlet("DonotRemove",
                "com.foo.SomeClass");
        _webAppAccessor.addServletToWebApp(webApp, servlet2);
        _webAppAccessor.addMappingToWebApp(webApp, mapping);
        Object mapping2 = _webAppAccessor.createServletMapping();
        _webAppAccessor.setMappingData(webApp, Collections
                .singletonList("blah"), servlet2, mapping2);
        _webAppAccessor.addMappingToWebApp(webApp, mapping2);
        assertEquals(2, _webAppAccessor.getServlets(webApp).size());
        assertTrue(_webAppAccessor.getServlet(webApp, "Foobar") == servlet);
        assertTrue(_webAppAccessor.getServlet(webApp, "DonotRemove") == servlet2);
        // servlet mapping should be gone.
        assertEquals(2, _webAppAccessor.getServletMappings_WebApp(webApp)
                .size());
        _jsfUtils.rollbackWebApp(webApp);
        // servlet should be gone, but servlet2 is still here
        assertEquals(1, _webAppAccessor.getServlets(webApp).size());
        assertSame(servlet2, _webAppAccessor.getServlet(webApp, "DonotRemove"));
        // servlet mapping1 should be gone, but mapping 2 still there.
        assertEquals(1, _webAppAccessor.getServletMappings_WebApp(webApp)
                .size());
    }

    protected void testUpdateWebApp_NewServlet(final String version)
    {
        Object webApp = _webAppAccessor.createWebApp(version);

        final Map<String, MockPropertyHolder> configProps = new HashMap<String, MockPropertyHolder>();
        configProps.put(
                IJSFFacetInstallDataModelProperties.SERVLET_URL_PATTERNS,
                new MockPropertyHolder(new String[]
                { PATTERN_SLASH_FACES_SLASH_ALL }, null));
        configProps.put(IJSFFacetInstallDataModelProperties.CONFIG_PATH,
                new MockPropertyHolder("/WEB-INF/faces-config.xml", null));
        final MockDataModel config = new MockDataModel("testModel", configProps);
        _jsfUtils.updateWebApp(webApp, config);
        verifyServlet(webApp);

        // cover the cases where the CONFIG_PATH is not the default
        // case one: the config param is not already present
        webApp = _webAppAccessor.createWebApp(version);
        configProps.put(IJSFFacetInstallDataModelProperties.CONFIG_PATH,
                new MockPropertyHolder("/WEB-INF/app-config.xml", null));
        _jsfUtils.updateWebApp(webApp, config);
        verifyServlet(webApp);
        assertEquals(1, _webAppAccessor.getContextParams(webApp, version).size());
        assertEquals("/WEB-INF/app-config.xml", _webAppAccessor.getContextParamValue(_webAppAccessor.getContextParams(webApp, version).get(0),version));
        
        // case two: the config param is already present
        webApp = _webAppAccessor.createWebApp(version);
        configProps.put(IJSFFacetInstallDataModelProperties.CONFIG_PATH,
                new MockPropertyHolder("/WEB-INF/app-config.xml", null));
        Object param = _webAppAccessor.createContextParam(JSFUtils.JSF_CONFIG_CONTEXT_PARAM, "SomeOtherValue", version);
        _webAppAccessor.setContextParam(webApp, param, version);
        _jsfUtils.updateWebApp(webApp, config);
        verifyServlet(webApp);
        assertEquals(1, _webAppAccessor.getContextParams(webApp, version).size());
        
    }

    @SuppressWarnings("rawtypes")
    protected void verifyServlet(final Object webApp)
    {
        final Object defaultServlet = _webAppAccessor.getServlet(webApp,
                JSFUtils.JSF_DEFAULT_SERVLET_NAME);
        assertNotNull(defaultServlet);
        _webAppAccessor.verifyServlet(defaultServlet,
                JSFUtils.JSF_SERVLET_CLASS);
        _webAppAccessor.verifyLoadOnStartup(defaultServlet, Integer.valueOf(1));
        List mappings = _webAppAccessor.getServletMappings_Servlet(webApp,
                defaultServlet);
        assertEquals(1, mappings.size());
        Object mapping = mappings.get(0);
        assertNotNull(mapping);
        _webAppAccessor.verifyMapping(mapping,
                JSFUtils.JSF_DEFAULT_SERVLET_NAME,
                PATTERN_SLASH_FACES_SLASH_ALL);
    }

    protected void testUpdateWebApp_ExistingServlet(final String version)
    {
        final Object webApp = _webAppAccessor.createWebApp(version);
        final Object servlet = _webAppAccessor.createServlet("Foobar",
                JSFUtils.JSF_SERVLET_CLASS);
        _webAppAccessor.addServletToWebApp(webApp, servlet);
        final Map<String, MockPropertyHolder> configProps = new HashMap<String, MockPropertyHolder>();
        final MockDataModel config = new MockDataModel("testModel", configProps);

        _jsfUtils.updateWebApp(webApp, config);
        Object defaultServlet = _webAppAccessor.getServlet(webApp,
                JSFUtils.JSF_DEFAULT_SERVLET_NAME);
        assertNotNull(defaultServlet);
        _webAppAccessor.verifyServlet(defaultServlet,
                JSFUtils.JSF_SERVLET_CLASS);
        _webAppAccessor.verifyLoadOnStartup(defaultServlet, Integer.valueOf(1));

        // do again, but this time have an existing servlet mapping that matches
        // Foobar
        final Object mapping = _webAppAccessor.createServletMapping();
        _webAppAccessor.setMappingData(webApp, Collections
                .singletonList("/faces"), servlet, mapping);
        _webAppAccessor.addMappingToWebApp(webApp, mapping);
        configProps.put(IJSFFacetInstallDataModelProperties.SERVLET_URL_PATTERNS,
                new MockPropertyHolder(new String[]{"/faces"}, null));
        _jsfUtils.updateWebApp(webApp, config);
        defaultServlet = _webAppAccessor.getServlet(webApp,
                JSFUtils.JSF_DEFAULT_SERVLET_NAME);
        _webAppAccessor.verifyServlet(defaultServlet,
                JSFUtils.JSF_SERVLET_CLASS);
        _webAppAccessor.verifyMapping(mapping,
                JSFUtils.JSF_DEFAULT_SERVLET_NAME, "/faces");
        _webAppAccessor.verifyLoadOnStartup(defaultServlet, Integer.valueOf(1));

    }

    protected void testGetFileUrlPath_NonNullCases(final String version)
    {
        // a web app with valid servlet and extension mapping
        final Object webApp = _webAppAccessor.createWebApp(version);
        final Object servlet = _webAppAccessor.createServlet("JSF Servlet",
                JSFUtils.JSF_SERVLET_CLASS);
        _webAppAccessor.addServletToWebApp(webApp, servlet);
        final Object mapping = _webAppAccessor.createServletMapping();
        _webAppAccessor.setMappingData(webApp, Collections
                .singletonList(".faces"), servlet, mapping);
        _webAppAccessor.addMappingToWebApp(webApp, mapping);

        IPath fileUrlPath = _jsfUtils.getFileUrlPath(webApp, new MockResource(
                IResource.FILE, new Path("/WebContent/test.jsp")), new Path(
                "/test.jsp"));
        assertEquals("/test.faces", fileUrlPath.toString());

        // web app with valid servlet and non-default extension mapping
        fileUrlPath = _jsfUtils.getFileUrlPath(webApp, new MockResource(
                IResource.FILE, new Path("/WebContent/test.xhtml")), new Path(
                "/test.xhtml"));
        assertEquals("/test.faces", fileUrlPath.toString());

        // web app with valid servlet and path-based mapping
        _webAppAccessor.setUrlPattern(mapping, Collections
                .singletonList("/faces"));
        fileUrlPath = _jsfUtils.getFileUrlPath(webApp, new MockResource(
                IResource.FILE, new Path("/WebContent/test.jsp")), new Path(
                "/test.jsp"));
        assertEquals("/faces/test.jsp", fileUrlPath.toString());

        // web app with valid servlet and path-based mapping match *
        _webAppAccessor.setUrlPattern(mapping, Collections
                .singletonList(PATTERN_SLASH_FACES_SLASH_ALL));
        fileUrlPath = _jsfUtils.getFileUrlPath(webApp, new MockResource(
                IResource.FILE, new Path("/WebContent/test.jsp")), new Path(
                "/test.jsp"));
        assertEquals("/faces/test.jsp", fileUrlPath.toString());

        // web app with valid servlet and extension mapping based on a
        // non-default, default extension
        _webAppAccessor.setUrlPattern(mapping, Collections
                .singletonList(".jspx"));
        final Object param = _webAppAccessor.createContextParam(
                JSFUtils.JSF_DEFAULT_SUFFIX_CONTEXT_PARAM, ".xhtml", version);
        _webAppAccessor.setContextParam(webApp, param,version);
        fileUrlPath = _jsfUtils.getFileUrlPath(webApp, new MockResource(
                IResource.FILE, new Path("/WebContent/test.xhtml")), new Path(
                "/test.xhtml"));
        assertEquals("/test.jspx", fileUrlPath.toString());
    }

    protected void testGetFileUrlPath_NullCases(final String version)
    {
        // a null webApp object produces a null path
        final Path existingURL = new Path("foobar");
        verifyNull(existingURL, null);

        // a non-null value that is not instanceof WebApp returns a null path
        verifyNull(existingURL, new Object());
        // a web app with no servlet produces a null path

        final Object webApp = _webAppAccessor.createWebApp("2.4");
        verifyNull(existingURL, webApp);

        // a web app with no meaningful servlet returns null
        Object servlet = _webAppAccessor.createServlet(null, null);
        _webAppAccessor.addServletToWebApp(webApp, servlet);
        verifyNull(existingURL, webApp);

        doPre25SpecificOrNoop(existingURL, webApp, servlet);

        // wrong servlet class name
        _webAppAccessor.setServletClass(servlet, "com.wrong.Servlet");
        verifyNull(existingURL, webApp);

        // multiple wrong class names
        servlet = _webAppAccessor.createServlet("JSF Servlet",
                "com.AnotherWrong.Servlet");
        _webAppAccessor.addServletToWebApp(webApp, servlet);

        assertEquals(2, _webAppAccessor.getServlets(webApp).size());
        verifyNull(existingURL, webApp);

        // valid servlet class, but no mappings
        _webAppAccessor.setServletClass(servlet, JSFUtils.JSF_SERVLET_CLASS);
        verifyNull(existingURL, webApp, new MockResource(IResource.FILE,
                new Path("/somepath")));
        verifyNull(existingURL, webApp, new MockResource(IResource.FILE,
                new Path("/somepath.xhtml")));

        final Object mapping = _webAppAccessor.createServletMapping();
        _webAppAccessor.addMappingToWebApp(webApp, mapping);
        assertEquals(1, _webAppAccessor.getServletMappings_WebApp(webApp)
                .size());
        assertEquals(0, _webAppAccessor.getServletMappings_Servlet(webApp,
                servlet).size());
        verifyNull(existingURL, webApp, new MockResource(IResource.FILE,
                new Path("/somepath.xhtml")));

        // empty mapping that matches to servlet
        _webAppAccessor.setServletOnMapping(mapping, servlet);
        assertEquals(1, _webAppAccessor.getServletMappings_WebApp(webApp)
                .size());
        // mapping should be matched
        assertEquals(1, _webAppAccessor.getServletMappings_Servlet(webApp,
                servlet).size());
        assertTrue(_webAppAccessor.getServletMappings_Servlet(webApp, servlet)
                .contains(mapping));
        verifyNull(existingURL, webApp, new MockResource(IResource.FILE,
                new Path("/somepath.xhtml")));
    }

    protected void doPre25SpecificOrNoop(final Path existingURL,
            final Object webApp, final Object servlet)
    {
        // this is a noop unless explicitly overridden
    }

//    @Test
    public void testFacesConfigCreation() throws IOException
    {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        _jsfUtils.printConfigFile_testable(os);
        assertEquals(getExpectedDefaultConfigFile().toString("ISO-8859-1")
                .replaceAll("\\r", ""), os.toString("ISO-8859-1"));
    }

    protected TestFileResource getExpectedDefaultConfigFile()
            throws IOException
    {
        String replaceAll = getVersionToTestIn().toString().replaceAll("\\.",
                "_");
        final String defaultFileName = String.format(
                "/testfiles/facet/expected-faces-config-%s.xml", replaceAll);
        TestFileResource testFileResource = new TestFileResource();
        testFileResource.load(new MockBundle(new File(".").getCanonicalPath()),
                defaultFileName);
        return testFileResource;
    }

//    @Test
    public void testIsJavaEE()
    {
        final org.eclipse.jst.javaee.web.WebApp javaEEWebApp = WebFactory.eINSTANCE
                .createWebApp();
        assertEquals(WebAppVersionType._40, javaEEWebApp.getVersion());
        assertTrue(_jsfUtils.isJavaEE(javaEEWebApp));
        javaEEWebApp.setVersion(WebAppVersionType._25_LITERAL);
        assertTrue(_jsfUtils.isJavaEE(javaEEWebApp));

        final org.eclipse.jst.j2ee.webapplication.WebApp J2EEWebApp = org.eclipse.jst.j2ee.webapplication.WebapplicationFactory.eINSTANCE
                .createWebApp();
        assertFalse(_jsfUtils.isJavaEE(J2EEWebApp));
    }

//    @Test
    public void testGetModelProvider()
    {
        final IModelProvider modelProvider = _jsfUtils.getModelProvider();
        assertNotNull(modelProvider);
        assertEquals(_modelObject, modelProvider.getModelObject());

        _jsfUtils = new TestableJSFUtils(new JSFUtilFactory().create(
                JSFVersion.V2_0, null), new MockModelProvider(null));
        // returns null when the model provider has a null model object.
        assertNull(_jsfUtils.getModelProvider());
    }

//    @Test
    public void testGetDisplayName()
    {
        final Map<String, MockPropertyHolder> propsMap = new HashMap<String, MockPropertyHolder>();
        final MockDataModel model = new MockDataModel("TestID", propsMap);
        // when the property is not present, use the default.
        assertEquals(JSFUtils.JSF_DEFAULT_SERVLET_NAME, _jsfUtils
                .getDisplayName_testable(model));

        // if property is present but empty or only whitespace, we also use
        // the default.
        model.setProperty(IJSFFacetInstallDataModelProperties.SERVLET_NAME, "");
        assertEquals(JSFUtils.JSF_DEFAULT_SERVLET_NAME, _jsfUtils
                .getDisplayName_testable(model));
        model.setProperty(IJSFFacetInstallDataModelProperties.SERVLET_NAME, "");
        assertEquals(JSFUtils.JSF_DEFAULT_SERVLET_NAME, _jsfUtils
                .getDisplayName_testable(model));

        // otherwise, we should get back the value
        model.setProperty(IJSFFacetInstallDataModelProperties.SERVLET_NAME,
                "foobar");
        assertEquals("foobar", _jsfUtils.getDisplayName_testable(model));
    }

//    @Test
    public void testGetServletClassName()
    {
        final Map<String, MockPropertyHolder> propsMap = new HashMap<String, MockPropertyHolder>();
        final MockDataModel model = new MockDataModel("TestID", propsMap);
        final Object webApp = _webAppAccessor.createWebApp("2.4");
        // when the property is not present, use the default.
        assertEquals(JSFUtils.JSF_SERVLET_CLASS, _jsfUtils
                .getServletClassname_testable(webApp, model));

        // if property is present but empty or only whitespace, we also use
        // the default.
        model.setProperty(
                IJSFFacetInstallDataModelProperties.SERVLET_CLASSNAME, "");
        assertEquals(JSFUtils.JSF_SERVLET_CLASS, _jsfUtils
                .getServletClassname_testable(webApp, model));
        model.setProperty(
                IJSFFacetInstallDataModelProperties.SERVLET_CLASSNAME, "");
        assertEquals(JSFUtils.JSF_SERVLET_CLASS, _jsfUtils
                .getServletClassname_testable(webApp, model));

        // otherwise, we should get back the value
        model.setProperty(
                IJSFFacetInstallDataModelProperties.SERVLET_CLASSNAME, "foobar");
        assertEquals("foobar", _jsfUtils.getServletClassname_testable(webApp, model));
    }

//    @Test
    public void testGetServletMappings()
    {
        final Map<String, MockPropertyHolder> propsMap = new HashMap<String, MockPropertyHolder>();
        final MockDataModel model = new MockDataModel("TestID", propsMap);

        // empty array should beget empty list
        model.setProperty(
                IJSFFacetInstallDataModelProperties.SERVLET_URL_PATTERNS,
                new String[]
                {});
        assertTrue(_jsfUtils.getServletMappings_testable(model).isEmpty());

        // non-empty list should beget the same list back
        final String[] values1 = new String[]
        { "foo" };
        final String[] valuesMany = new String[]
        { "foo", "bar", "foobar" };

        model.setProperty(
                IJSFFacetInstallDataModelProperties.SERVLET_URL_PATTERNS,
                values1);
        assertEquals(Arrays.asList(values1), _jsfUtils
                .getServletMappings_testable(model));

        model.setProperty(
                IJSFFacetInstallDataModelProperties.SERVLET_URL_PATTERNS,
                valuesMany);
        assertEquals(Arrays.asList(valuesMany), _jsfUtils
                .getServletMappings_testable(model));
    }

//    @Test
    public void testIsJSFPage()
    {
        assertFalse(_jsfUtils.isJSFPage_testable(null));
    }

//    @Test
    public void testIsValidKnownExtension()
    {
        assertTrue(_jsfUtils.isValidKnownExtension_testable("jsp"));
        assertTrue(_jsfUtils.isValidKnownExtension_testable("jspx")); //$NON-NLS-1$
        assertTrue(_jsfUtils.isValidKnownExtension_testable("jsf")); //$NON-NLS-1$
        assertTrue(_jsfUtils.isValidKnownExtension_testable("jspf")); //$NON-NLS-1$)
        assertTrue(_jsfUtils.isValidKnownExtension_testable("xhtml")); //$NON-NLS-1$

        assertFalse(_jsfUtils.isValidKnownExtension_testable("html")); //$NON-NLS-1$)
        assertFalse(_jsfUtils.isValidKnownExtension_testable("dtd")); //$NON-NLS-1$)
        assertFalse(_jsfUtils.isValidKnownExtension_testable("php")); //$NON-NLS-1$)

        assertFalse(_jsfUtils.isValidKnownExtension_testable("")); //$NON-NLS-1$)
        assertFalse(_jsfUtils.isValidKnownExtension_testable(null)); // )
    }

    protected final void verifyNull(final Path existingURL,
            final Object webApp, final IResource res)
    {
        assertNull(_jsfUtils.getFileUrlPath(webApp, res, null));
        assertNull(_jsfUtils.getFileUrlPath(webApp, res, existingURL));
    }

    protected final void verifyNull(final Path existingURL, final Object webApp)
    {
        verifyNull(existingURL, webApp, null);
    }

    protected abstract JSFVersion getVersionToTestIn();

    protected abstract WebAppAccessor createWebAccessor();

    /**
     * Concrete implementations are used to abstract test cases away from
     * whether they are using the J2EE or JavaEE version of the WebApp objects.
     * 
     * @author cbateman
     * 
     */
    @SuppressWarnings("rawtypes")
    public static abstract class WebAppAccessor
    {
        protected abstract Object createWebApp(final String version);

        protected abstract String getContextParamValue(Object param, String version);

        protected abstract List getContextParams(Object webApp, String version);

        protected abstract void verifyMapping(Object mapping,
                String servletName, String servletClass);

        protected abstract Object createServlet(final String servletName,
                final String servletClass);

        protected abstract Object createServletMapping();

        protected abstract void setServletClass(Object servlet, String name);

        protected abstract List getServlets(final Object webApp);

        protected abstract void addMappingToWebApp(final Object webApp,
                final Object mapping);

        protected abstract void addServletToWebApp(final Object webApp,
                final Object servlet);

        protected abstract List getServletMappings_WebApp(final Object webApp);

        protected abstract List getServletMappings_Servlet(final Object webApp,
                final Object servlet);

        protected abstract void setMappingData(final Object webApp,
                final List<String> urlPatterns, final Object servlet,
                final Object mapping);

        /**
         * Clears any existing patterns and replaces them with patterns
         * 
         * @param mapping
         * @param patterns
         */
        protected abstract void setUrlPattern(final Object mapping,
                final List<String> patterns);

        protected abstract Object createContextParam(final String name,
                final String value, final String version);

        protected abstract void setContextParam(final Object webApp,
                final Object param, final String version);

        protected abstract Object getServlet(final Object webApp,
                final String servletName);

        protected abstract void verifyServlet(final Object defaultServlet,
                final String className);

        protected abstract void verifyLoadOnStartup(
                final Object defaultServlet, final Integer expectedValue);

        protected abstract void setServletOnMapping(Object mapping,
                Object servlet);
    }

    // @Override
    // protected void tearDown() throws Exception {
    // super.tearDown();
    // _webProjectTestEnv.getTestProject().close(null);
    // }

    // public void testSearchServletMappings_NoPrefixMappings() {
    // final List<String> mappings = new ArrayList<String>();
    // // prefix mappings must end in "*"
    // mappings.add("/notaprefixmapping/");
    // mappings.add("*.faces");
    // mappings.add("*.html");
    // mappings.add("*.jsp");
    //
    // // if no preference, then the first one should be found
    // MappingSearchResult result = searchServletMappings(mappings,
    // null, null);
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertTrue(result.getExtensionMapping().equals("*.faces"));
    // assertNull(result.getPrefixMapping());
    //
    // // selecting the first one as the preferred should yield the same result
    // result = searchServletMappings(mappings, "*.faces", null);
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getExtensionMapping(), "*.faces");
    // assertNull(result.getPrefixMapping());
    //
    // result = searchServletMappings(mappings, "*.html", null);
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getExtensionMapping(), "*.html");
    // assertNull(result.getPrefixMapping());
    //
    // result = searchServletMappings(mappings, "*.jsp", null);
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getExtensionMapping(), "*.jsp");
    // assertNull(result.getPrefixMapping());
    //
    // // an extension that doesn't exist
    // result = searchServletMappings(mappings, "*.xhtml", null);
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // // if preferred not present, then should be the first one
    // assertEquals(result.getExtensionMapping(), "*.faces");
    // assertNull(result.getPrefixMapping());
    // }
    //
    // public void testSearchServletMappings_NoExtensionMappings() {
    // final List<String> mappings = new ArrayList<String>();
    // // prefix mappings must end in "*"
    // mappings.add("/notaprefixMapping/");
    // mappings.add("/faces/*");
    // mappings.add("/foo/*");
    // mappings.add("/bar/*");
    //
    // // if no preference, then the first one should be found
    // MappingSearchResult result = searchServletMappings(mappings,
    // null, null);
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/faces/*");
    // assertNull(result.getExtensionMapping());
    //
    // result = searchServletMappings(mappings, null, "/faces/*");
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/faces/*");
    // assertNull(result.getExtensionMapping());
    //
    // result = searchServletMappings(mappings, null, "/foo/*");
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/foo/*");
    // assertNull(result.getExtensionMapping());
    //
    // result = searchServletMappings(mappings, null, "/bar/*");
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/bar/*");
    // assertNull(result.getExtensionMapping());
    //
    // // if a preference is given that is not present, then first one
    // // one should be picked
    // result = searchServletMappings(mappings, null, "/notfound/*");
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/faces/*");
    // assertNull(result.getExtensionMapping());
    // }
    //
    // public void testSearchServletMappings_BothKindsOfMappings() {
    // final List<String> mappings = new ArrayList<String>();
    // // prefix mappings must end in "*"
    // mappings.add("/notaprefixmapping/");
    // mappings.add("*.faces");
    // mappings.add("/faces/*");
    // mappings.add("*.html");
    // mappings.add("/foo/*");
    // mappings.add("*.jsp");
    // mappings.add("/bar/*");
    //
    // // if no preference, then the first one should be found
    // MappingSearchResult result = searchServletMappings(mappings,
    // null, null);
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/faces/*");
    // assertEquals(result.getExtensionMapping(), "*.faces");
    //
    // result = searchServletMappings(mappings, "*.faces", "/faces/*");
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/faces/*");
    // assertEquals(result.getExtensionMapping(), "*.faces");
    //
    // result = searchServletMappings(mappings, "*.html", "/foo/*");
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/foo/*");
    // assertEquals(result.getExtensionMapping(), "*.html");
    //
    // result = searchServletMappings(mappings, "*.jsp", "/bar/*");
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/bar/*");
    // assertEquals(result.getExtensionMapping(), "*.jsp");
    //
    // // one pref found, the other not: the first in is picked
    // result = searchServletMappings(mappings, "*.jsp", "/bar2/*");
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/faces/*");
    // assertEquals(result.getExtensionMapping(), "*.jsp");
    //
    // // one pref found, the other not: the first in is picked
    // result = searchServletMappings(mappings, "*.jspx", "/bar/*");
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/bar/*");
    // assertEquals(result.getExtensionMapping(), "*.faces");
    //
    // // neither found, so first of each
    // result = searchServletMappings(mappings, "*.jspx", "/bar2/*");
    // assertNotNull(result);
    // assertTrue(result.isResult());
    // assertEquals(result.getPrefixMapping(), "/faces/*");
    // assertEquals(result.getExtensionMapping(), "*.faces");
    // }
    //
    // public void testSearchServletMappings_NoMatches() {
    // final List<String> mappings = new ArrayList<String>();
    // // none of these are either prefix or extension mappings
    // mappings.add("/notaprefixmapping/");
    // mappings.add("/alsoNotAMatch/");
    // mappings.add("/");
    // mappings.add("file.jsp");
    // mappings.add("test.html");
    // mappings.add("foo.jspx");
    //
    // // should not find any matches
    // MappingSearchResult result = searchServletMappings(mappings,
    // null, null);
    // assertNotNull(result);
    // assertFalse(result.isResult());
    // assertNull(result.getPrefixMapping());
    // assertNull(result.getExtensionMapping());
    //
    // result = searchServletMappings(mappings, "*.faces", null);
    // assertNotNull(result);
    // assertFalse(result.isResult());
    // assertNull(result.getPrefixMapping());
    // assertNull(result.getExtensionMapping());
    //
    // result = searchServletMappings(mappings, null, "/faces/*");
    // assertNotNull(result);
    // assertFalse(result.isResult());
    // assertNull(result.getPrefixMapping());
    // assertNull(result.getExtensionMapping());
    //
    // result = searchServletMappings(mappings, "*.faces", "/faces/*");
    // assertNotNull(result);
    // assertFalse(result.isResult());
    // assertNull(result.getPrefixMapping());
    // assertNull(result.getExtensionMapping());
    // }
    //
    // /**
    // * Search the list of servlet-mappings for the first extension and prefix
    // mappings. The contents
    // * of mappings is assumed to be all url-pattern's.
    // *
    // * If prefExtMapping is not null, it is an extension mapping and
    // * it is in mappings, then it is returned. Otherwise, the first extension
    // * mapping in mappings is returned. Returns null if mappings does not
    // * contain an extension mapping. The same algorithm holds for
    // prefPrefixMapping and
    // * corresponding prefix mapping.
    // *
    // * See isExtensionMapping and isPrefixMapping for more information on url
    // patterns.
    // *
    // * @param mappings
    // * @param prefExtMapping
    // * @param prefPrefixMapping
    // * @return the result
    // */
    // public final MappingSearchResult searchServletMappings(
    // final List<String> mappings, String prefExtMapping,
    // String prefPrefixMapping) {
    // String firstExtFound = null;
    // String firstPrefixFound = null;
    // boolean foundExtMapping = false;
    // boolean foundPrefixMapping = false;
    //
    // // if the caller has no preferredMapping, then
    // // set it to something guaranteed to be non-null
    // // and which is guaranteed not to match anything
    // // that pass isExtensionMapping
    // if (prefExtMapping == null) {
    //            prefExtMapping = "NOTANEXTENSIONMAPPING"; //$NON-NLS-1$
    // }
    //
    // // similarly, guarantee that if the caller has no
    // // preferred prefix mapping, that we set a non-null
    // // comp mapping
    // if (prefPrefixMapping == null) {
    //            prefPrefixMapping = "NOTAPREFIXMAPPING"; //$NON-NLS-1$
    // }
    //
    // SEARCH_LOOP: for (String mapping : mappings) {
    // if (isExtensionMapping(mapping)) {
    // // can assum that mapping is non-null since
    // // it is an ext mapping
    // if (prefExtMapping.equals(mapping.trim())) {
    // firstExtFound = prefExtMapping;
    // continue;
    // }
    //
    // if (firstExtFound == null) {
    // firstExtFound = mapping.trim();
    // }
    // } else if (isPrefixMapping(mapping)) {
    // if (prefPrefixMapping.equals(mapping.trim())) {
    // firstPrefixFound = prefPrefixMapping;
    // continue;
    // }
    //
    // if (firstPrefixFound == null) {
    // firstPrefixFound = mapping.trim();
    // }
    // }
    //
    // if (foundExtMapping && foundPrefixMapping) {
    // break SEARCH_LOOP;
    // }
    // }
    //
    // return new MappingSearchResult(firstExtFound, firstPrefixFound);
    // }
    //
    // /**
    // * Servlet 2.3_SRV.11.2: a string that begins with a "/" and ends
    // * with "/*" is a prefix mapping
    // *
    // * @param mapping
    // * @return true if the mapping string represents a prefix mapping
    // */
    // public final boolean isPrefixMapping(final String mapping)
    // {
    // if (mapping == null || mapping.length() < 4)
    // {
    // return false;
    // }
    //
    //        return mapping.charAt(0) == '/' && mapping.endsWith("/*"); //$NON-NLS-1$
    // }
    //
    // /**
    // * Servlet 2.3_SRV.11.2: a string that begins with "*."
    // * is an extension mapping
    // *
    // * @param mapping
    // * @return true if mapping is an extension mapping
    // */
    // public final boolean isExtensionMapping(final String mapping)
    // {
    // if (mapping == null)
    // {
    // return false;
    // }
    //
    //        return mapping.startsWith("*."); //$NON-NLS-1$
    // }
}
