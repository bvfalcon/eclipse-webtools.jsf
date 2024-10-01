/*******************************************************************************
 * Copyright (c) 2009, 2019 IBM Corporation and others.
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
package org.eclipse.jst.pagedesigner.tests.tagcreator;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.pagedesigner.itemcreation.CreationData;
import org.eclipse.jst.pagedesigner.itemcreation.ITagCreator;
import org.eclipse.jst.pagedesigner.itemcreation.ITagDropOverrider;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.CustomizationDataImpl;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.ICustomizationData;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.IWritableCustomizationData;
import org.eclipse.jst.pagedesigner.tests.PageDesignerTestsPlugin;
import org.eclipse.wst.xml.core.internal.document.ElementImpl;
import org.w3c.dom.Element;

public class TestOverrideableCreationData extends
		BaseUserCustomizedTagCreatorTestCase {

	private final String OVERRIDDEN_TAGNAME = "fooTag";
	private final String OVERRIDDEN_TAGURI = "http://com.foo.bar/fooey";
	private final String OVERRIDDEN_DEFAULT_PREFIX = "fooey";

	private boolean DO_TAGNAME_OVERRIDE = true;
	private boolean DO_TAGURI_OVERRIDE = true;
	private boolean DO_DEFAULT_PREFIX_OVERRIDE = true;

	public void testOverriddenCreationData() throws Exception {
		Verification v = new Verification(OVERRIDDEN_TAGNAME,
				OVERRIDDEN_TAGURI, OVERRIDDEN_DEFAULT_PREFIX,
				OVERRIDDEN_TAGNAME, OVERRIDDEN_DEFAULT_PREFIX);
		
		reset();
		
		doCreateTestOverridden(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON,
				"jsp", "jsp", 358, false, getCustomizationData(), v);
		doCreateTestOverridden(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON,
				"jspx", "jspx", 495, false, getCustomizationData(), v);
		
		JSFCoreUtilHelper.injectTestTagRegistryFactoryProvider(JSFCoreUtilHelper.createSimpleRegistryFactory());
		doCreateTestOverridden(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON,
				"xhtml", "xhtml", 350, false, getCustomizationData(), v);
		JSFCoreUtilHelper.injectTestTagRegistryFactoryProvider(null);

	}
	
	public void testNullTagNameOverride() throws Exception {
		reset();
		DO_TAGNAME_OVERRIDE = false;
		
		Verification v = new Verification(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON.getTagName(),
				OVERRIDDEN_TAGURI, OVERRIDDEN_DEFAULT_PREFIX,
				IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON.getTagName(), OVERRIDDEN_DEFAULT_PREFIX);
		doCreateTestOverridden(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON,
				"jsp", "jsp", 358, false, getCustomizationData(), v);
		
	}
	
	public void testNullTagUriOverride() throws Exception {
		reset();
		DO_TAGURI_OVERRIDE = false;
		
		Verification v = new Verification(OVERRIDDEN_TAGNAME,
				IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON.getUri(), OVERRIDDEN_DEFAULT_PREFIX,
				OVERRIDDEN_TAGNAME, "h");//<-- changes are required to JSFUtil.getPrefix() before the DefPrefixOverride is picked up in all cases when URI is not overridden

		doCreateTestOverridden(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON,
				"jspx", "jspx", 495, false, getCustomizationData(), v);
		
	}

	public void testNullDefaultPrefixOverride() throws Exception {
		reset();
		DO_DEFAULT_PREFIX_OVERRIDE = false;
		
		Verification v = new Verification(OVERRIDDEN_TAGNAME,
				OVERRIDDEN_TAGURI, "h",
				OVERRIDDEN_TAGNAME, "h");
		
		JSFCoreUtilHelper.injectTestTagRegistryFactoryProvider(JSFCoreUtilHelper.createSimpleRegistryFactory());
		doCreateTestOverridden(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON,
				"xhtml", "xhtml", 350, false, getCustomizationData(), v);
		JSFCoreUtilHelper.injectTestTagRegistryFactoryProvider(null);
	}
	
	private void reset() {
		DO_TAGNAME_OVERRIDE = true;
		DO_TAGURI_OVERRIDE = true;
		DO_DEFAULT_PREFIX_OVERRIDE = true;
	}



	private void doCreateTestOverridden(final TagIdentifier tagId,
			final String inExt, final String outExt, int offset,
			boolean forceResultTagEmpty, ICustomizationData customizationData,
			Verification v) throws Exception {
		final String uri = tagId.getUri();
		final String tagName = tagId.getTagName();

		IFile file = (IFile) _webProjectTestEnv.loadResourceInWebRoot(
				PageDesignerTestsPlugin.getDefault().getBundle(),
				"/testdata/tagcreator/tagCreator." + inExt + ".data",
				"/tagCreator_" + tagName + "." + inExt);

		setTagIdentifier(tagId);

		ITagCreator tagCreator = getTagCreator(getTagIdentifier());

		CreationData creationData = getCreationData(uri, tagName,
				_defaultPrefix, file, offset, customizationData);
		Element element = tagCreator.createTag(creationData);

		// this is a hack that is required because we do a literal comparison
		// between the modified source file and test data file on a character
		// by character basis. However, the MyFaces and RI (and possibly other)
		// impls cause the tags to be generated differently (MyFaces with no
		// end tag because body-content = empty and RI with an end tag because
		// body-content = JSP), so if the caller sets forceResultTagEmpty, we
		// force
		// the tag to have no end tag. This should not invalidate the test as
		// long as
		// the caller doesn't set forceResultTagEmpty on a tag that may have
		// children.
		// the tag name and attributes should be cloned.
		if (forceResultTagEmpty && element instanceof ElementImpl) {
			forceTagEmpty((ElementImpl) element);
		}

//		 System.out.println(element.toString());

		v.verify(creationData, element);

	}

	protected ICustomizationData getCustomizationData() {

		// Setup tag
		IWritableCustomizationData data = new CustomizationDataImpl(
				getTagIdentifier()) {

			@SuppressWarnings("rawtypes")
            @Override
			public Object getAdapter(Class adapter) {
				if (ITagDropOverrider.class == adapter) {
					return getCustomizationAdapter();
				}
				return super.getAdapter(adapter);
			}

		};
		data.addAttribute("attr1", "value1");

		return data;
	}

	private ITagDropOverrider getCustomizationAdapter() {
		return new ITagDropOverrider() {

			public String getTagNameOverride() {
				if (DO_TAGNAME_OVERRIDE)
					return OVERRIDDEN_TAGNAME;
				return null;
			}

			public String getUriOverride() {
				if (DO_TAGURI_OVERRIDE)
					return OVERRIDDEN_TAGURI;
				return null;
			}

			public String getDefaultPrefixOverride() {
				if (DO_DEFAULT_PREFIX_OVERRIDE)
					return OVERRIDDEN_DEFAULT_PREFIX;
				return null;
			}
		};
	}

	private class Verification {

		private String _expectedTagName;
		private String _expectedTagUri;
		private String _expectedDefaultPrefix;
		private String _expectedElementLocalName;
		private String _expectedElementPrefix;

		/**
		 * @param expectedTagName
		 * @param expectedTagUri
		 * @param expectedDefaultPrefix
		 * @param expectedElementLocalName
		 * @param expectedElementPrefix
		 */
		public Verification(final String expectedTagName,
				final String expectedTagUri,
				final String expectedDefaultPrefix,
				final String expectedElementLocalName,
				final String expectedElementPrefix) {
			_expectedTagName = expectedTagName;
			_expectedTagUri = expectedTagUri;
			_expectedDefaultPrefix = expectedDefaultPrefix;
			_expectedElementLocalName = expectedElementLocalName;
			_expectedElementPrefix = expectedElementPrefix;
		}

		public void verify(CreationData creationData, Element element)
				throws Exception {
			assertEquals(_expectedTagName, creationData.getTagName());
			assertEquals(_expectedTagUri, creationData.getUri());
			assertEquals(_expectedDefaultPrefix, creationData
					.getDefaultPrefix());
			assertEquals(_expectedElementLocalName, element.getLocalName());
			assertEquals(_expectedElementPrefix, element.getPrefix());
		}
	}
}
