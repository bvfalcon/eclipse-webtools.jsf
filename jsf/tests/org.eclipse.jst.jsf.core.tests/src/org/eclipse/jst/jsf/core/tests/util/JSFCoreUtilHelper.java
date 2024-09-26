/*******************************************************************************
 * Copyright (c) 2005, 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.tests.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebFacetProjectCreationDataModelProvider;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.internal.AbstractTagRegistryFactoryProvider;
import org.eclipse.jst.jsf.core.internal.CompositeTagRegistryFactory;
import org.eclipse.jst.jsf.core.internal.ITagRegistryFactoryInfo;
import org.eclipse.jst.jsf.core.internal.ITagRegistryFactoryProvider;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryRegistryUtil;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryFactory;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.core.tests.mock.MockJSPTagRegistry;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry;
import org.eclipse.jst.jsf.designtime.internal.view.model.TagRegistryFactory;
import org.eclipse.jst.jsf.designtime.resolver.ISymbolContextResolver;
import org.eclipse.jst.jsf.designtime.resolver.StructuredDocumentSymbolResolverFactory;
import org.eclipse.jst.jsp.core.internal.domdocument.DOMModelForJSP;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;

@SuppressWarnings("deprecation")
public final class JSFCoreUtilHelper
{
    private static final String JSFRUNTIMEJARSDIR = "jsfRuntimeJarsDirectoryV";
    private static String JSF11Path = "";
    private static String JSF12Path = "";
    private static String JSF20Path = "";

	/**
	 * Constructs jsfLib using this plugin's install path and "testfiles" subdirectory
	 * @param id
	 * @param name
	 * @param archivefiles
	 * @param bImpl
	 * @return
	 */
	public static JSFLibrary constructJSFLib(
			final String id,
			final String name,
			final String[] archivefiles,
			final boolean bImpl) {

		final String pathTestFiles = TestsPlugin.getInstallLocation().getPath() + "testfiles/";

		return constructJSFLib(id, name, pathTestFiles, archivefiles, bImpl);
	}

	/**
	 * @param id
	 * @param name
	 * @param pluginRelativePathToArchiveFiles
	 * @param archivefiles
	 * @param bImpl
	 * @return JSF Library
	 */
	public static JSFLibrary constructJSFLib(
			final String id,
			final String name,
			final String basePathToArchiveFiles,
			final String[] archivefiles,
			final boolean bImpl) {

		ArchiveFile archiveFile = null;
		String testData;
		final JSFLibrary jsfLib = JSFLibraryRegistryFactory.eINSTANCE.createJSFLibrary();
//		jsfLib.setID(id);
		jsfLib.setName(name);
		jsfLib.setImplementation(bImpl);

		for (final String archivefile2 : archivefiles) {
			testData = basePathToArchiveFiles + archivefile2;
			archiveFile = JSFLibraryRegistryFactory.eINSTANCE.createArchiveFile();
			archiveFile.setRelativeToWorkspace(false);
			archiveFile.setSourceLocation(testData);
			archiveFile.setJSFLibrary(jsfLib);
		}

		return jsfLib;
	}

	/**
	 * Create a Dynamic Web application with given name using default operation.
	 * If project with given name already exists, then it returns that project.
	 *
	 * @param aProjectName Project name.
	 * @return IProject instance.
	 * @throws Exception on error.
	 */
	public static IProject createWebProject(final String aProjectName) throws Exception {
		final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(aProjectName);
		if (!project.exists()) {
			final IDataModel dataModel = DataModelFactory.createDataModel(new WebFacetProjectCreationDataModelProvider());
			dataModel.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, aProjectName);
			dataModel.getDefaultOperation().execute(new NullProgressMonitor(), null);
		}
		return ResourcesPlugin.getWorkspace().getRoot().getProject(aProjectName);
	}

	/**
	 * If the JSF Library registry is empty, it will seed it with an Impl and non-Impl library
	 */
	public static void createJSFLibraryRegistry(){
		if (JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry().getAllJSFLibraries().size() < 2){
			//create Impl
			JSFLibrary lib = constructJSFLib("AN-IMPL-LIB", "/testfiles/JSFLib/", true, false);
			JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry().addJSFLibrary(lib);
			//create non-Impl.   Uses same jars but declares it to be non implementation
			lib = constructJSFLib("A-NON-IMPL-LIB", "/testfiles/JSFLib/", false, false);
			JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry().addJSFLibrary(lib);
			//create plugin impl
			lib = constructJSFLib("AN-IMPL-LIB-PP", "/testfiles/JSFLib/", true, true);
			JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry().addJSFLibrary(lib);
			//create plugin-non impl

		}
	}

	/**
	 * Convenience method to get new JSFLibraryRegistry instance from factory.
	 * Note that the returned instance is not associated with a resource and so
	 * cannot be loaded or saved unless a resource is created and the returned
	 * instance is set in the resource contents.
	 *
	 * @return JSFLibraryRegistry instance.
	 */
	public static JSFLibraryRegistry getNewJSFLibraryRegistry() {
		return JSFLibraryRegistryFactory.eINSTANCE.createJSFLibraryRegistry();
	}

	/**
	 * Convenience method to get the JSFLibraryRegistry instance
	 * (which will cause the loadJSFLibraryRegistry() and
	 * loadJSFLibraryExtensions() methods to be called and will
	 * subsequently allow saveJSFLibraryRegistry() to be called,
	 * if desired).
	 *
	 * @return JSFLibraryRegistry instance from JSFCorePlugin.
	 */
	public static JSFLibraryRegistry getJSFLibraryRegistryFromJSFLibraryHelper() {
		return JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry();
	}

	/**
	 * Creates a JSF Library from all the jars and zips found at the relative path from this plugin
	 *
	 * @param name
	 * @param relPathToArchives
	 * @param isImpl
	 * @param isFakedPlugginLib
	 * @return JSFLibrary
	 */
	public static JSFLibrary constructJSFLib(
			final String name,
			final String relPathToArchives,
			final boolean isImpl,
			final boolean isFakedPlugginLib) {

		JSFLibrary jsfLib = null;
		if (isFakedPlugginLib){
			jsfLib = JSFLibraryRegistryFactory.eINSTANCE.createPluginProvidedJSFLibrary();
//			jsfLib.setID("fakePluginLib_"+name);
		} else {
			jsfLib = JSFLibraryRegistryFactory.eINSTANCE.createJSFLibrary();
		}

		jsfLib.setName(name);
		jsfLib.setImplementation(isImpl);

		final File pathTestFiles = new File(TestsPlugin.getInstallLocation().getPath()
								+ File.separator + relPathToArchives);
		final FilenameFilter jars = new FilenameFilter(){
			public boolean accept(File dir, String name_) {
				if (name_.length() >=5){
					String lastChars = name_.toLowerCase().substring(name_.length() - 4);
					if (lastChars.equals(".jar") || lastChars.equals(".zip")) {
						return true;
					}
				}
				return false;
			}
		};

		final String[] fileNames = pathTestFiles.list(jars);
		for (final String fileName2 : fileNames) {
			final String fileName = pathTestFiles.getAbsolutePath().concat(File.separator).concat(fileName2);
			final ArchiveFile archiveFile = JSFLibraryRegistryFactory.eINSTANCE.createArchiveFile();
			archiveFile.setRelativeToWorkspace(false);
			archiveFile.setSourceLocation(fileName);
			archiveFile.setJSFLibrary(jsfLib);
		}

		return jsfLib;
	}

    /**
     * @param jsfVersion
     * @return Directory name for jsf runtime jars.
     * <br>Will be null if not set in JSFRUNTIMEJARSDIRV<b>X.X</b> system property
     */
    public static String getJSFRuntimeJarsDirectory(final JSFVersion jsfVersion) {
        final String propertyName = JSFRUNTIMEJARSDIR+jsfVersion.toString();
        String res = System.getProperty(propertyName);
        if (res == null) {
            //check env var also
            res = System.getenv(propertyName);
        }

        // do some tracing the first time a new value is returned for 1.1 or 1.2
        if (jsfVersion == JSFVersion.V1_1
               && JSF11Path != res)
        {
            System.out.printf("Using jsf runtime path %s for propertyName %s\n", res, propertyName);
            JSF11Path = res;
        }
        else if (jsfVersion == JSFVersion.V1_2
                   && JSF12Path != res)
        {
              System.out.printf("Using jsf runtime path %s for propertyName %s\n", res, propertyName);
              JSF12Path = res;
        }
        else if (jsfVersion == JSFVersion.V2_0
        		&& JSF20Path != res) {
            System.out.printf("Using jsf runtime path %s for propertyName %s\n", res, propertyName);
            JSF20Path = res;        	
        }
        return res;
    }

    /**
     * Returns true if the environment property holding the name of the directory that points at the
     * runtime jars exist.
     * <p>
     * The expected property name is jsfRuntimeJarsDirectoryVXX where XX is the
     * JSFVersion.  <br>i.e "jsfRuntimeJarsDirectoryV1.1", or "jsfRuntimeJarsDirectoryV1.2"
     * <p>
     * It <b>does</b> check for the existence of the directory.<br>
     * It <b>does not</b> check for any jars within that directory.
     *
     * @param jsfVersion as String.  ie. "1.1", or "1.2"
     * @return true if the property is set
     */
    public static boolean isJSFRuntimeJarsDirectoryPropertySet(final JSFVersion jsfVersion) {
        final String dirName = getJSFRuntimeJarsDirectory(jsfVersion);
        if (dirName != null && dirName.trim().length() != 0)
        {
            final File dir = new File(dirName);

            if (dir.exists() && dir.isDirectory())
            {
                return true;
            }
            else
            {
                System.err.printf("Dir: %s either doesn't exists or is not a directory\n",dirName);
            }
        }
        else
        {
            System.err.println("dirName is null");
        }

        return false;
    }

    public static boolean createRegistryAndAddReferences(final JSFFacetedTestEnvironment jsfFacedEnv, final String[] archiveFiles, final JSFVersion jsfVersion) throws CoreException {
        final JSFLibraryRegistry jsfLibRegistry =
            JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry();

       if (archiveFiles != null)
        {
            final String libIDandName = "_internalJSFRuntimeLibraryV" + jsfVersion + "_";
            final JSFLibrary jsfImpl = JSFCoreUtilHelper.
                constructJSFLib(libIDandName, libIDandName,"", archiveFiles, true);
            jsfLibRegistry.addJSFLibrary(jsfImpl);
            JSFLibraryRegistryUtil.getInstance().saveJSFLibraryRegistry();
            jsfFacedEnv.addJSFLibraryReference(jsfImpl, true);
            return true;
        }

       return false;
    }

    public static String[] getJSFRuntimeJarNames(final JSFVersion version) throws IOException
    {
        String[] jarNames = null;
        final String dirName = getJSFRuntimeJarsDirectory(version);
        System.out.printf("Using dirName: %s\n", dirName);
        if (dirName != null) {
            final File dir = new File(dirName);
            if (dir.exists() && dir.isDirectory()) {
                final File[] jars = dir.listFiles();
                if (jars != null && jars.length > 0) {
                    jarNames = new String[jars.length];
                    for (int i = 0; i < jars.length; i++) {
                        jarNames[i] = jars[i].getAbsolutePath();
                    }
                }
            }
            else
            {
                throw new FileNotFoundException(dirName+" is either does not exist or is not a directory");
            }
        }
        else
        {
            throw new FileNotFoundException("dirName is null");
        }
        return jarNames;
    }

    public static boolean isJSFRuntimeJarsDirectoryValid(final JSFVersion jsfVersion)
    {
        try
        {
            return getJSFRuntimeJarNames(jsfVersion) != null;
        }
        catch (final IOException e)
        {
            return false;
        }
    }

    /**
     * Attempts to create and initialize a JSF library for the jar files stored at the location
     * pointed to by the standard env variable (@see getJSFRuntimeJarsDirectory)
     * @param jsfVersion
     * @param jsfFacetedTestEnv
     * @return true if successful
     * @throws Exception
     */
    public static boolean addJSFRuntimeJarsToClasspath(final JSFVersion jsfVersion, final JSFFacetedTestEnvironment jsfFacetedTestEnv) throws Exception
    {
        final String[] jarNames = getJSFRuntimeJarNames(jsfVersion);
        return createRegistryAndAddReferences(jsfFacetedTestEnv, jarNames, jsfVersion);
    }

    /**
     * @param testClass
     * @param jsfVersion
     * @return a message informing a test suite runner that the environment variable
     * isn't correct for finding JSF library jar files
     */
    public static String getTestRequiresJSFRuntimeMessage(final Class<?> testClass, final JSFVersion jsfVersion) {
        final StringBuffer sb = new StringBuffer();
        sb.append("Unable to run test suite \"");
        sb.append(testClass.getName());
        sb.append("\"; JSF runtime (v");
        sb.append(jsfVersion.toString());
        sb.append(") is required but not present.\nSystem property or environment variable \"jsfRuntimeJarsDirectoryV");
        sb.append(jsfVersion.toString());
        sb.append("\" must be set to point to a directory containing JSF runtime (v");
        sb.append(jsfVersion.toString());
        sb.append(") JARs.");
        return sb.toString();
    }
//	/**
//	 * Creates a JSF Library from all the jars and zips found at the relative
//	 * path from this plugin.
//	 *
//	 * @param ID
//	 * @param name
//	 * @param relPathToArchives
//	 * @param isImpl
//	 * @param isFakedPlugginLib
//	 * @return JSFLibrary
//	 */
//	public static JSFLibrary constructJSFLib(
//			String ID,
//			String name,
//			String relPathToArchives,
//			boolean isImpl,
//			boolean isFakedPlugginLib) {
//
//		JSFLibrary jsfLib = constructJSFLib(name, relPathToArchives, isImpl, isFakedPlugginLib);
////		jsfLib.setID(ID);
//		return jsfLib;
//	}

	/**
	 * @param jspFile
	 * @param offset
	 * @return the context wrapper. Caller must ensure dispose is called so
	 *         model is released when finished.
	 * @throws Exception
	 */
	public static void assertELSanity(final IFile jspFile, final int offset,
			final String attrName, final String elExpr) throws Exception {
		ContextWrapper wrapper = null;
		try {
			wrapper = JSFCoreUtilHelper.getDocumentContext(jspFile, offset);
			final IStructuredDocumentContext context = wrapper.getContext();
			final IDOMContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE
					.getDOMContextResolver(context);
			final Node node = resolver.getNode();
			// bJSFTestUtil.getIndexedRegion((IStructuredDocument)
			// context.getStructuredDocument(), 589);
			Assert.assertTrue(node instanceof Attr);
			Assert.assertEquals(attrName, ((Attr) node).getNodeName());
			Assert.assertEquals(elExpr, ((Attr) node).getNodeValue());
		} finally {
			if (wrapper != null) {
				wrapper.dispose();
			}
		}
	}

	public static void assertELVariableSanity(final IFile jspFile,
			final String varName) throws Exception {
		ContextWrapper contextWrapper = null;

		try {
			contextWrapper = JSFCoreUtilHelper.getDocumentContext(jspFile, -1);
			final ISymbolContextResolver symbolResolver = StructuredDocumentSymbolResolverFactory
					.getInstance().getSymbolContextResolver(
							contextWrapper.getContext());
			final ISymbol bundleVar = symbolResolver.getVariable(varName);
			Assert.assertNotNull(bundleVar);
		} finally {
			if (contextWrapper != null) {
				contextWrapper.dispose();
			}
		}
	}


	public static ContextWrapper getDocumentContext(final IFile jspFile,
			final int offset) throws Exception {
		final IModelManager modelManager = StructuredModelManager
				.getModelManager();

		IStructuredModel model = null;

		model = modelManager.getModelForRead(jspFile);
		Assert.assertTrue(model instanceof DOMModelForJSP);
		final IStructuredDocumentContext context = IStructuredDocumentContextFactory.INSTANCE
				.getContext(model.getStructuredDocument(), offset);
		return new ContextWrapper(context, model);
	}

    public static class ContextWrapper {
		private final IStructuredDocumentContext _context;
		private final IStructuredModel _model;

		public ContextWrapper(final IStructuredDocumentContext context,
				final IStructuredModel model) {
			super();
			_context = context;
			_model = model;
		}

		public IStructuredDocumentContext getContext() {
			return _context;
		}

		public IStructuredModel getModel() {
			return _model;
		}

		public void dispose() {
			_model.releaseFromRead();
		}
	}

    public static void injectTestTagRegistryFactoryProvider(
            ITagRegistryFactoryProvider abstractTagRegistryFactoryProvider)
    {
        CompositeTagRegistryFactory.getInstance().setTestInjectedProvider(abstractTagRegistryFactoryProvider);
    }
    
    public static ITagRegistryFactoryProvider createSimpleRegistryFactory()
    {
        return new JSPandFaceletTagRegistryFactoryProvider();
    }
        
    private static class JSPandFaceletTagRegistryFactoryProvider extends AbstractTagRegistryFactoryProvider
    {
        @Override
        public Set<ITagRegistryFactoryInfo> getTagRegistryFactories()
        {
            Set<IContentType> contentTypes = new HashSet<IContentType>();
            final IContentTypeManager typeManager = Platform.getContentTypeManager();
            
            IContentType jspContentType = 
                typeManager.getContentType("org.eclipse.jst.jsf.core.jsfsource");
            contentTypes.add(jspContentType);
            Set<ITagRegistryFactoryInfo>  infos = new HashSet<ITagRegistryFactoryInfo>();
            infos.add(new MyTagRegistryFactoryInfo(contentTypes, new MyTagRegistryFactory(), "JSP Test Factory", "Test ONLY!!! USER SHOULD NEVER SEE THIS"));
            
            IContentType htmlContentType = 
                typeManager.getContentType("org.eclipse.wst.html.core.htmlsource");
            contentTypes.add(htmlContentType);            
            infos.add(new MyTagRegistryFactoryInfo(contentTypes, new MyTagRegistryFactory(), "Facelet Test Factory", "Test ONLY!!! USER SHOULD NEVER SEE THIS"));


            return infos;
        }
    }

    private static class MyTagRegistryFactory extends TagRegistryFactory
    {
        private final String _displayName;

        MyTagRegistryFactory()
        {
            _displayName = "TEST ONLY!!! YOU SHOULD NOT SEE THIS!!";
        }

        public String getDisplayName()
        {
            return _displayName;
        }

        @Override
        public ITagRegistry createTagRegistry(IProject project)
                throws TagRegistryFactoryException
        {
            return new MockJSPTagRegistry();
        }

        @Override
        public boolean isInstance(IProject project)
        {
            // always create a new one.
            return false;
        }

        @Override
        public boolean projectIsValid(IProject project) {
            if (project == null)
            {
                return false;
            }

            return true;
        }
        
    }

    private static class MyTagRegistryFactoryInfo implements ITagRegistryFactoryInfo
    {
        private final Set<IContentType> _contentTypes;
        private final TagRegistryFactory _factory;
        private final String _id;
        private final String _description;

        /**
         * @param contentTypes
         * @param factory
         * @param id
         * @param description
         */
        public MyTagRegistryFactoryInfo(final Set<IContentType> contentTypes,
                TagRegistryFactory factory, String id, String description)
        {
            _contentTypes = contentTypes;
            _factory = factory;
            _id = id;
            _description = description;
        }
        
        public Set<IContentType> getContentTypes()
        {
            return _contentTypes;
        }

        public TagRegistryFactory getTagRegistryFactory()
        {
            return _factory;
        }

        public String getId()
        {
            return _id;
        }

        public String getDescription()
        {
            return _description;
        }
        
    }
}