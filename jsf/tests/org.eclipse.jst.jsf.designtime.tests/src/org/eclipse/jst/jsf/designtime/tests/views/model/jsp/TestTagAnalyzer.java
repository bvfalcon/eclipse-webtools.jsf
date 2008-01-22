package org.eclipse.jst.jsf.designtime.tests.views.model.jsp;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IComponentTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IConverterTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IHandlerTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IJSFTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IValidatorTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IHandlerTagElement.TagHandlerType;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IJSFTagElement.TagType;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.TLDTagElement;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.analyzer.TagAnalyzer;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;
import org.eclipse.jst.jsp.core.taglib.ITaglibRecord;

public class TestTagAnalyzer extends BaseTestClass
{
    private TLDElementDeclaration _inputTextTag;
    private TLDElementDeclaration _commandButtonTag;
    private TLDElementDeclaration _convertDateTimeTag;
    private TLDElementDeclaration _validateDoubleRangeTag;
    private TLDElementDeclaration _convertNumberTag;
    private TLDElementDeclaration _validateLengthTag;
    private TLDElementDeclaration _validateLongRangeTag;
    private TLDElementDeclaration _facetTag;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        final ITaglibRecord coreTags = _tagRecords
                .get(ITLDConstants.URI_JSF_CORE);
        final ITaglibRecord htmlTags = _tagRecords
                .get(ITLDConstants.URI_JSF_HTML);

        _convertDateTimeTag = TestUtil.getTag(coreTags, "convertDateTime");
        _validateDoubleRangeTag = TestUtil.getTag(coreTags,
                "validateDoubleRange");
        _convertNumberTag = TestUtil.getTag(coreTags, "convertNumber");
        _validateLengthTag = TestUtil.getTag(coreTags, "validateLength");
        _validateLongRangeTag = TestUtil.getTag(coreTags, "validateLongRange");
        _facetTag = TestUtil.getTag(coreTags, "facet");

        _inputTextTag = TestUtil.getTag(htmlTags, "inputText");
        _commandButtonTag = TestUtil.getTag(htmlTags, "commandButton");
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testFindComponentType()
    {
        final String componentType = TagAnalyzer.findComponentType(
                _inputTextTag, _webProjectTestEnv.getTestProject());
        assertEquals("javax.faces.HtmlInputText", componentType);
    }

    public void testFindConverterType()
    {
        final String converterType = TagAnalyzer.findConverterType(
                _convertDateTimeTag, _webProjectTestEnv.getTestProject());

        // TODO: this doesn't work and may never work for JSF 1.2 without
        // meta-data because of the *ELTag's.
        assertEquals("javax.faces.DateTime", converterType);
    }

    public void testFindValidatorType()
    {
        final String validatorType = TagAnalyzer.findValidatorType(
                _validateDoubleRangeTag, _webProjectTestEnv.getTestProject());

        // TODO: this doesn't work and may never work for JSF 1.2 without
        // meta-data because of the *ELTag's.
        assertEquals("javax.faces.DoubleRange", validatorType);
    }

    public void testCreateTLDTagElement()
    {
        final IProject project = _webProjectTestEnv.getTestProject();
        verifyCommandButton((IComponentTagElement) TagAnalyzer
                .createTLDTagElement(_commandButtonTag, project));

        verifyCommandButton((IComponentTagElement) TagAnalyzer
                .createTLDTagElement(_commandButtonTag, project));
        verifyNumberConverter(((IConverterTagElement) TagAnalyzer
                .createTLDTagElement(_convertNumberTag, project)));
        verifyLengthValidator(((IValidatorTagElement) TagAnalyzer
                .createTLDTagElement(_validateLengthTag, project)));
        verifyFacetHandler(((IHandlerTagElement) TagAnalyzer
                .createTLDTagElement(_facetTag, project)));
        // XXX: verify robustness when the TLDElementDeclaration has a
        // bad tagClass name.
    }

    public void testCreateComponentTagElement()
    {
        final TLDTagElement tagElement = TagAnalyzer.createComponentTagElement(
                _commandButtonTag, _webProjectTestEnv.getTestProject());
        assertNotNull(tagElement);
        assertTrue(tagElement instanceof IComponentTagElement);
        verifyCommandButton((IComponentTagElement) tagElement);
    }

    private void verifyCommandButton(
            final IComponentTagElement componentTagElement)
    {
        assertEquals(TagType.COMPONENT, componentTagElement.getType());
        final ComponentTypeInfo componentTypeInfo = componentTagElement
                .getComponent();
        assertEquals("javax.faces.component.html.HtmlCommandButton",
                componentTypeInfo.getClassName());
        assertEquals("javax.faces.HtmlCommandButton", componentTypeInfo
                .getComponentType());
        assertEquals("javax.faces.Command", componentTypeInfo
                .getComponentFamily());
        assertEquals("javax.faces.Button", componentTypeInfo.getRenderFamily());

    }

    public void testCreateConverterTagElement()
    {
        final TLDTagElement tagElement = TagAnalyzer.createConverterTagElement(
                _convertNumberTag, _webProjectTestEnv.getTestProject());
        assertNotNull(tagElement);
        assertTrue(tagElement instanceof IConverterTagElement);
        verifyNumberConverter((IConverterTagElement) tagElement);
    }

    private void verifyNumberConverter(
            final IConverterTagElement converterTagElement)
    {
        assertEquals(TagType.CONVERTER, converterTagElement.getType());
        final ConverterTypeInfo converterTypeInfo = converterTagElement
                .getConverter();
        assertEquals("javax.faces.convert.NumberConverter", converterTypeInfo
                .getClassName());
        assertEquals("javax.faces.Number", converterTypeInfo.getConverterId());
    }

    public void testCreateValidatorTagElement()
    {
        final TLDTagElement tagElement = TagAnalyzer.createValidatorTagElement(
                _validateLengthTag, _webProjectTestEnv.getTestProject());
        assertNotNull(tagElement);
        assertTrue(tagElement instanceof IValidatorTagElement);
        verifyLengthValidator((IValidatorTagElement) tagElement);
    }

    private void verifyLengthValidator(
            final IValidatorTagElement validatorTagElement)
    {
        assertEquals(TagType.VALIDATOR, validatorTagElement.getType());
        final ValidatorTypeInfo converterTypeInfo = validatorTagElement
                .getValidator();
        assertEquals("javax.faces.validator.LengthValidator", converterTypeInfo
                .getClassName());
        assertEquals("javax.faces.Length", converterTypeInfo.getValidatorId());
    }

    public void testCreateHandlerTagElement() throws Exception
    {
        final IType type = JavaCore.create(_webProjectTestEnv.getTestProject())
                .findType(_facetTag.getTagclass());
        final TLDTagElement tagElement = TagAnalyzer.createHandlerTagElement(
                _facetTag, type);
        assertNotNull(tagElement);
        assertTrue(tagElement instanceof IHandlerTagElement);
        verifyFacetHandler((IHandlerTagElement) tagElement);
    }

    private void verifyFacetHandler(IHandlerTagElement tagElement)
    {
        assertEquals(TagHandlerType.FACET, tagElement.getTagHandlerType());
    }

    public void testGetJSFComponentTagType() throws Exception
    {
        { // inputText is a component
            final String inputTextTagClass = _inputTextTag.getTagclass();
            final IType tagClassType = JavaCore.create(
                    _webProjectTestEnv.getTestProject()).findType(
                    inputTextTagClass);
            assertEquals(IJSFTagElement.TagType.COMPONENT, TagAnalyzer
                    .getJSFComponentTagType(tagClassType, _webProjectTestEnv
                            .getTestProject()));
        }

        { // validateLongRange is a validator
            final String validateLongRangeTagClass = _validateLongRangeTag
                    .getTagclass();
            final IType tagClassType = JavaCore.create(
                    _webProjectTestEnv.getTestProject()).findType(
                    validateLongRangeTagClass);
            assertEquals(IJSFTagElement.TagType.VALIDATOR, TagAnalyzer
                    .getJSFComponentTagType(tagClassType, _webProjectTestEnv
                            .getTestProject()));
        }

        { // convertDateTime
            final String converterDateTimeTagClass = _convertDateTimeTag
                    .getTagclass();
            final IType tagClassType = JavaCore.create(
                    _webProjectTestEnv.getTestProject()).findType(
                    converterDateTimeTagClass);
            assertEquals(IJSFTagElement.TagType.CONVERTER, TagAnalyzer
                    .getJSFComponentTagType(tagClassType, _webProjectTestEnv
                            .getTestProject()));

        }

        { // loadBundle is not a component tag

            final ITaglibRecord coreTags = _tagRecords
                    .get(ITLDConstants.URI_JSF_CORE);

            final TLDElementDeclaration loadBundleTag = TestUtil.getTag(
                    coreTags, "loadBundle");

            assertNotNull(loadBundleTag);
            final String loadBundleTagClass = loadBundleTag.getTagclass();
            final IType tagClassType = JavaCore.create(
                    _webProjectTestEnv.getTestProject()).findType(
                    loadBundleTagClass);
            assertNull(TagAnalyzer.getJSFComponentTagType(tagClassType,
                    _webProjectTestEnv.getTestProject()));
        }
    }
}
