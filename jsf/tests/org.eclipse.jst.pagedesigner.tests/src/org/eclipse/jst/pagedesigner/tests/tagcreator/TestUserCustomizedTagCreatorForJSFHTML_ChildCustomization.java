package org.eclipse.jst.pagedesigner.tests.tagcreator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.CustomizationDataImpl;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.IWritableCustomizationData;
import org.eclipse.jst.pagedesigner.tests.tagcreatorPlugin.UserCustomizedElementEditFactory;

/**
 * Test class for child tag customization.
 * 
 * @author Debajit Adhikary
 *
 */
public class TestUserCustomizedTagCreatorForJSFHTML_ChildCustomization extends BaseUserCustomizedTagCreatorTestCase 
{
	/**
	 * Path to directory where the test-data files are located. The XML
	 * generated from this test case is compared against this test data.
	 * 
	 */
	private static final String TESTDATA_FILES_PATH = "/testdata/UserCustomizedTagCreator/ChildCustomization";
	
	
	public void testChildTagInsertion() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON, "jsp", "jsp", 358, false);
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON, "jspx", "jspx", 495, false);
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON, "xhtml", "xhtml", 350, false);
    }
	

    @Override
    protected IAdaptable getCustomizationData()
    {
    	// Setup tag
    	IWritableCustomizationData tagDropped = new CustomizationDataImpl(getTagIdentifier());
    	tagDropped.addAttribute("attr1", "value1");
    	tagDropped.addAttribute("attr2", "value2");
    	tagDropped.addAttribute("attr3", "value3");

        /*

          Add child tags
          We will create the tag hierarchy:
          
          f:view 
          |
          +----TagDropped  (attr1=value1, att2=value2, attr3=value3)
               |
               +----TAG1
               |    |
               |    +----TAG1 
               |
               +----TAG2  (tag2Attr1=tag2Attr1Value, tag2Attr2=tag2Attr2Value) 
               |    |
               |    +----TAG7
               |    |
               |    +----TAG6
               |
               |
               +----TAG3
                    |
                    +----TAG8
                         |
                         +----TAG9
                    
        */

    	
        IWritableCustomizationData tag11 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG1);
        IWritableCustomizationData tag12 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG1);
        tag11.addChildData(tag12);
    	tagDropped.addChildData(tag11);
    	
        IWritableCustomizationData tag7 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG7);
        IWritableCustomizationData tag6 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG6);
        IWritableCustomizationData tag2 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG2);
        tag2.addAttribute("tag2Attr1", "tag2Attr1Value");
        tag2.addAttribute("tag2Attr2", "tag2Attr2Value");
        tag2.addChildData(tag7);
        tag2.addChildData(tag6);
        tagDropped.addChildData(tag2);
        
        IWritableCustomizationData tag3 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG3);
        IWritableCustomizationData tag8 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG8);
        IWritableCustomizationData tag9 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG9);
        tag8.addChildData(tag9);
        tag3.addChildData(tag8);
        tagDropped.addChildData(tag3);
    	
        // Setup parent <f:view>. (This will not use the UserCustomizedTagCreator but
        // will use its own tag creator)
        IWritableCustomizationData view = new CustomizationDataImpl(IJSFConstants.TAG_IDENTIFIER_VIEW);
        tagDropped.addParentData(view);

    	return tagDropped;
    }

    
	@Override
    protected final String getExpectedResult(final String tagName, final String outExt) throws Exception
    {
        final String ext = outExt == null ? "" : "." + outExt;
        final String fileName = "expectedResult_"
                + tagName.replaceAll(":", "_") + ext + ".data";
        final String pathStr = TESTDATA_FILES_PATH + "/" + _compareDataSubDir
                + "/" + fileName;
        return getExpectedResult(pathStr);
    }
}
