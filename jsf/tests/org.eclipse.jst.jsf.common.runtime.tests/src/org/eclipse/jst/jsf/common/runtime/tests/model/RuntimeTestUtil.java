/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.common.runtime.tests.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.eclipse.jst.jsf.common.runtime.internal.model.ViewObject;
import org.eclipse.jst.jsf.common.runtime.internal.model.bean.DataModelInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.IActionSource2Info;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.IActionSourceInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.IEditableValueHolderInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.IValueHolderInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.UICommandInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.UIDataInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.UIFormInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.UIInputInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.UIOutputInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ActionListenerDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ComponentDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.Decorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.FacetDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorTypeInfo;

public final class RuntimeTestUtil extends Assert
{
    // expected type info for jsf/core components
    public static final ComponentTypeInfo COMPINFO_PARAM = new ComponentTypeInfo(
            "jakarta.faces.Parameter", "jakarta.faces.component.UIParameter",
            new String[]
            { "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.StateHolder", }, "jakarta.faces.Parameter",
            null);

    public static final ComponentTypeInfo COMPINFO_SELECTITEM = new ComponentTypeInfo(
            "jakarta.faces.SelectItem", "jakarta.faces.component.UISelectItem",
            new String[]
            { "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.StateHolder", }, "jakarta.faces.SelectItem",
            null);

    public static final ComponentTypeInfo COMPINFO_SELECTITEMS = new ComponentTypeInfo(
            "jakarta.faces.SelectItems", "jakarta.faces.component.UISelectItems",
            new String[]
            { "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.StateHolder", },
            "jakarta.faces.SelectItems", null);

    public static final ComponentTypeInfo COMPINFO_SUBVIEW = new ComponentTypeInfo(
            "jakarta.faces.NamingContainer",
            "jakarta.faces.component.UINamingContainer", new String[]
            { "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.NamingContainer",
                    "jakarta.faces.component.StateHolder", },
            "jakarta.faces.NamingContainer", null);

    public static final ComponentTypeInfo COMPINFO_VERBATIM = new ComponentTypeInfo(
            "jakarta.faces.Output", "jakarta.faces.component.UIOutput",
            new String[]
            { "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.ValueHolder",
                    "jakarta.faces.component.StateHolder", },
            "jakarta.faces.Output", "jakarta.faces.Text");

    public static final ComponentTypeInfo COMPINFO_VIEW = new ComponentTypeInfo(
            "jakarta.faces.ViewRoot", "jakarta.faces.component.UIViewRoot",
            new String[]
            { "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.StateHolder", }, "jakarta.faces.ViewRoot",
            null);

    // expected type info for jsf/html components
    public static final ComponentTypeInfo COMPINFO_COLUMN = new ComponentTypeInfo(
            "jakarta.faces.Column", "jakarta.faces.component.UIColumn",
            new String[]
            { "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object" },
            new String[]
            { "jakarta.faces.component.StateHolder" }, "jakarta.faces.Column", null);
    public static final ComponentTypeInfo COMPINFO_COMMAND = new ComponentTypeInfo(
            "jakarta.faces.HtmlCommandButton",
            "jakarta.faces.component.html.HtmlCommandButton", new String[]
            { "jakarta.faces.component.UICommand",
                    "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object" },
            new String[]
            { "jakarta.faces.component.ActionSource",
                    "jakarta.faces.component.StateHolder" },
            "jakarta.faces.Command", "jakarta.faces.Button");
    public static final ComponentTypeInfo COMPINFO_COMMANDLINK = new ComponentTypeInfo(
            "jakarta.faces.HtmlCommandLink",
            "jakarta.faces.component.html.HtmlCommandLink",
            new String[]
                       { "jakarta.faces.component.UICommand",
                               "jakarta.faces.component.UIComponentBase",
                               "jakarta.faces.component.UIComponent", "java.lang.Object" },
                       new String[]
                       { "jakarta.faces.component.ActionSource",
                               "jakarta.faces.component.StateHolder" },
                       "jakarta.faces.Command", "jakarta.faces.Link");
    public static final ComponentTypeInfo COMPINFO_DATATABLE = new ComponentTypeInfo(
            "jakarta.faces.HtmlDataTable",
            "jakarta.faces.component.html.HtmlDataTable", new String[]
            { "jakarta.faces.component.UIData",
                    "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object" },
            new String[]
            { "jakarta.faces.component.NamingContainer",
                    "jakarta.faces.component.StateHolder", }, "jakarta.faces.Data",
            "jakarta.faces.Table");
    public static final ComponentTypeInfo COMPINFO_FORM = new ComponentTypeInfo(
            "jakarta.faces.HtmlForm", "jakarta.faces.component.html.HtmlForm",
            new String[]
            { "jakarta.faces.component.UIForm",
                    "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object" },
            new String[]
            { "jakarta.faces.component.NamingContainer",
                    "jakarta.faces.component.StateHolder" }, "jakarta.faces.Form",
            "jakarta.faces.Form");
    public static final ComponentTypeInfo COMPINFO_GRAPHIC = new ComponentTypeInfo(
            "jakarta.faces.HtmlGraphicImage",
            "jakarta.faces.component.html.HtmlGraphicImage", new String[]
            { "jakarta.faces.component.UIGraphic",
                    "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.StateHolder", }, "jakarta.faces.Graphic",
            "jakarta.faces.Image");
    public static final ComponentTypeInfo COMPINFO_HIDDEN = new ComponentTypeInfo(
            "jakarta.faces.HtmlInputHidden",
            "jakarta.faces.component.html.HtmlInputHidden", new String[]
            { "jakarta.faces.component.UIInput",
                    "jakarta.faces.component.UIOutput",
                    "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.EditableValueHolder",
                    "jakarta.faces.component.ValueHolder",
                    "jakarta.faces.component.StateHolder", },
            "jakarta.faces.Input", "jakarta.faces.Hidden");
    public static final ComponentTypeInfo COMPINFO_SECRET = new ComponentTypeInfo(
            "jakarta.faces.HtmlInputSecret",
            "jakarta.faces.component.html.HtmlInputSecret", new String[]
            { "jakarta.faces.component.UIInput",
                    "jakarta.faces.component.UIOutput",
                    "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.EditableValueHolder",
                    "jakarta.faces.component.ValueHolder",
                    "jakarta.faces.component.StateHolder" }, "jakarta.faces.Input",
            "jakarta.faces.Secret");
    public static final ComponentTypeInfo COMPINFO_INPUTTEXT = new ComponentTypeInfo(
            "jakarta.faces.HtmlInputText",
            "jakarta.faces.component.html.HtmlInputText", new String[]
            { "jakarta.faces.component.UIInput",
                    "jakarta.faces.component.UIOutput",
                    "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object" },
            new String[]
            { "jakarta.faces.component.EditableValueHolder",
                    "jakarta.faces.component.ValueHolder",
                    "jakarta.faces.component.StateHolder" }, "jakarta.faces.Input",
            "jakarta.faces.Text");
    public static final ComponentTypeInfo COMPINFO_INPUTTEXTAREA = new ComponentTypeInfo(
            "jakarta.faces.HtmlInputTextarea",
            "jakarta.faces.component.html.HtmlInputTextarea", new String[]
            { "jakarta.faces.component.UIInput",
                    "jakarta.faces.component.UIOutput",
                    "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.EditableValueHolder",
                    "jakarta.faces.component.ValueHolder",
                    "jakarta.faces.component.StateHolder", },
            "jakarta.faces.Input", "jakarta.faces.Textarea");
    public static final ComponentTypeInfo COMPINFO_MESSAGE = new ComponentTypeInfo(
            "jakarta.faces.HtmlMessage",
            "jakarta.faces.component.html.HtmlMessage", new String[]
            { "jakarta.faces.component.UIMessage",
                    "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.StateHolder", }, "jakarta.faces.Message",
            "jakarta.faces.Message");
    public static final ComponentTypeInfo COMPINFO_MESSAGES = new ComponentTypeInfo(
            "jakarta.faces.HtmlMessages",
            "jakarta.faces.component.html.HtmlMessages", new String[]
            { "jakarta.faces.component.UIMessages",
                    "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.StateHolder", }, "jakarta.faces.Messages",
            "jakarta.faces.Messages");
    public static final ComponentTypeInfo COMPINFO_OUTPUTFORMAT = new ComponentTypeInfo(
            "jakarta.faces.HtmlOutputFormat",
            "jakarta.faces.component.html.HtmlOutputFormat", new String[]
            { "jakarta.faces.component.UIOutput",
                    "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.ValueHolder",
                    "jakarta.faces.component.StateHolder", },
            "jakarta.faces.Output", "jakarta.faces.Format");
    public static final ComponentTypeInfo COMPINFO_OUTPUTLABEL = new ComponentTypeInfo(
            "jakarta.faces.HtmlOutputLabel",
            "jakarta.faces.component.html.HtmlOutputLabel", new String[]
            { "jakarta.faces.component.UIOutput",
                    "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.ValueHolder",
                    "jakarta.faces.component.StateHolder", },
            "jakarta.faces.Output", "jakarta.faces.Label");
    public static final ComponentTypeInfo COMPINFO_OUTPUTLINK = new ComponentTypeInfo(
            "jakarta.faces.HtmlOutputLink",
            "jakarta.faces.component.html.HtmlOutputLink", new String[]
            { "jakarta.faces.component.UIOutput",
                    "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.ValueHolder",
                    "jakarta.faces.component.StateHolder", },
            "jakarta.faces.Output", "jakarta.faces.Link");
    public static final ComponentTypeInfo COMPINFO_OUTPUTTEXT = new ComponentTypeInfo(
            "jakarta.faces.HtmlOutputText",
            "jakarta.faces.component.html.HtmlOutputText", new String[]
            { "jakarta.faces.component.UIOutput",
                    "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.ValueHolder",
                    "jakarta.faces.component.StateHolder", },
            "jakarta.faces.Output", "jakarta.faces.Text");
    public static final ComponentTypeInfo COMPINFO_PANELGRID = new ComponentTypeInfo(
            "jakarta.faces.HtmlPanelGrid",
            "jakarta.faces.component.html.HtmlPanelGrid", new String[]
            { "jakarta.faces.component.UIPanel",
                    "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.StateHolder", }, "jakarta.faces.Panel",
            "jakarta.faces.Grid");
    public static final ComponentTypeInfo COMPINFO_PANELGROUP = new ComponentTypeInfo(
            "jakarta.faces.HtmlPanelGroup",
            "jakarta.faces.component.html.HtmlPanelGroup", new String[]
            { "jakarta.faces.component.UIPanel",
                    "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.StateHolder", }, "jakarta.faces.Panel",
            "jakarta.faces.Group");
    public static final ComponentTypeInfo COMPINFO_SELECTBOOLEANCHECKBOX = new ComponentTypeInfo(
            "jakarta.faces.HtmlSelectBooleanCheckbox",
            "jakarta.faces.component.html.HtmlSelectBooleanCheckbox",
            new String[]
            { "jakarta.faces.component.UISelectBoolean",
                    "jakarta.faces.component.UIInput",
                    "jakarta.faces.component.UIOutput",
                    "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.EditableValueHolder",
                    "jakarta.faces.component.ValueHolder",
                    "jakarta.faces.component.StateHolder", },
            "jakarta.faces.SelectBoolean", "jakarta.faces.Checkbox");
    public static final ComponentTypeInfo COMPINFO_SELECTMANYCHECKBOX = new ComponentTypeInfo(
            "jakarta.faces.HtmlSelectManyCheckbox",
            "jakarta.faces.component.html.HtmlSelectManyCheckbox", new String[]
            { "jakarta.faces.component.UISelectMany",
                    "jakarta.faces.component.UIInput",
                    "jakarta.faces.component.UIOutput",
                    "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.EditableValueHolder",
                    "jakarta.faces.component.ValueHolder",
                    "jakarta.faces.component.StateHolder", },
            "jakarta.faces.SelectMany", "jakarta.faces.Checkbox");
    public static final ComponentTypeInfo COMPINFO_SELECTMANYLISTBOX = new ComponentTypeInfo(
            "jakarta.faces.HtmlSelectManyListbox",
            "jakarta.faces.component.html.HtmlSelectManyListbox", new String[]
            { "jakarta.faces.component.UISelectMany",
                    "jakarta.faces.component.UIInput",
                    "jakarta.faces.component.UIOutput",
                    "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.EditableValueHolder",
                    "jakarta.faces.component.ValueHolder",
                    "jakarta.faces.component.StateHolder", },
            "jakarta.faces.SelectMany", "jakarta.faces.Listbox");
    public static final ComponentTypeInfo COMPINFO_SELECTMANYMENU = new ComponentTypeInfo(
            "jakarta.faces.HtmlSelectManyMenu",
            "jakarta.faces.component.html.HtmlSelectManyMenu", new String[]
            { "jakarta.faces.component.UISelectMany",
                    "jakarta.faces.component.UIInput",
                    "jakarta.faces.component.UIOutput",
                    "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.EditableValueHolder",
                    "jakarta.faces.component.ValueHolder",
                    "jakarta.faces.component.StateHolder", },
            "jakarta.faces.SelectMany", "jakarta.faces.Menu");
    public static final ComponentTypeInfo COMPINFO_SELECTONELISTBOX = new ComponentTypeInfo(
            "jakarta.faces.HtmlSelectOneListbox",
            "jakarta.faces.component.html.HtmlSelectOneListbox", new String[]
            { "jakarta.faces.component.UISelectOne",
                    "jakarta.faces.component.UIInput",
                    "jakarta.faces.component.UIOutput",
                    "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.EditableValueHolder",
                    "jakarta.faces.component.ValueHolder",
                    "jakarta.faces.component.StateHolder", },
            "jakarta.faces.SelectOne", "jakarta.faces.Listbox");
    public static final ComponentTypeInfo COMPINFO_SELECTONEMENU = new ComponentTypeInfo(
            "jakarta.faces.HtmlSelectOneMenu",
            "jakarta.faces.component.html.HtmlSelectOneMenu", new String[]
            { "jakarta.faces.component.UISelectOne",
                    "jakarta.faces.component.UIInput",
                    "jakarta.faces.component.UIOutput",
                    "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.EditableValueHolder",
                    "jakarta.faces.component.ValueHolder",
                    "jakarta.faces.component.StateHolder", },
            "jakarta.faces.SelectOne", "jakarta.faces.Menu");
    public static final ComponentTypeInfo COMPINFO_SELECTONERADIO = new ComponentTypeInfo(
            "jakarta.faces.HtmlSelectOneRadio",
            "jakarta.faces.component.html.HtmlSelectOneRadio", new String[]
            { "jakarta.faces.component.UISelectOne",
                    "jakarta.faces.component.UIInput",
                    "jakarta.faces.component.UIOutput",
                    "jakarta.faces.component.UIComponentBase",
                    "jakarta.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "jakarta.faces.component.EditableValueHolder",
                    "jakarta.faces.component.ValueHolder",
                    "jakarta.faces.component.StateHolder", },
            "jakarta.faces.SelectOne", "jakarta.faces.Radio");

    // default converters
    public static final ConverterTypeInfo CONVERTERINFO_DATETIME = new ConverterTypeInfo(
            "jakarta.faces.convert.DateTimeConverter", "jakarta.faces.DateTime");

    public static final ConverterTypeInfo CONVERTERINFO_NUMBER = new ConverterTypeInfo(
            "jakarta.faces.convert.NumberConverter", "jakarta.faces.Number");

    // default validators
    public static final ValidatorTypeInfo VALIDATORINFO_DOUBLERANGE = new ValidatorTypeInfo(
            "jakarta.faces.validator.DoubleRangeValidator",
            "jakarta.faces.DoubleRange");

    public static final ValidatorTypeInfo VALIDATORINFO_LENGTH = new ValidatorTypeInfo(
            "jakarta.faces.validator.LengthValidator", "jakarta.faces.Length");

    public static final ValidatorTypeInfo VALIDATORINFO_LONGRANGE = new ValidatorTypeInfo(
            "jakarta.faces.validator.LongRangeValidator", "jakarta.faces.LongRange");

    // public static final ValidatorTypeInfo VALIDATORINFO_METHODEXPRESSION =
    // new ValidatorTypeInfo(
    // "jakarta.faces.validator.MethodExpressionValidator ",
    // "jakarta.faces.LongRange");

    @SuppressWarnings("unchecked")
    public static <COMPONENT_T> COMPONENT_T serializeDeserialize(
            final COMPONENT_T object) throws IOException,
            ClassNotFoundException
    {
        final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        final ObjectOutputStream outStream = new ObjectOutputStream(byteStream);

        outStream.writeObject(object);

        final ByteArrayInputStream byteArray = new ByteArrayInputStream(
                byteStream.toByteArray());
        final ObjectInputStream inStream = new ObjectInputStream(byteArray);

        return (COMPONENT_T) inStream.readObject();
    }

    public static void verifyImplicitAdapter(final ViewObject check,
            final Class<?> adapterType, final Object explicitAdapter)
    {
        assertEquals(check, check.getAdapter(adapterType));
        // cannot add explicit adapter if check is already that type
        boolean caughtException = false;
        
        try
        {
            check.addAdapter(adapterType, explicitAdapter);
        }
        catch (IllegalArgumentException iae)
        {
            caughtException = true;
        }
        
        assertTrue(caughtException);

        // should be unaffected by the attempt
        assertEquals(check, check.getAdapter(adapterType));
    }

    public static void verifySame(final ComponentTypeInfo truth,
            final ComponentTypeInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        assertEquals(truth.getClassName(), check.getClassName());
        assertEquals(truth.getComponentFamily(), check.getComponentFamily());
        assertEquals(truth.getComponentType(), check.getComponentType());
        assertEquals(truth.getRenderFamily(), check.getRenderFamily());
        
        verifyArraysSame(truth.getInterfaces(), check.getInterfaces());
        verifyArraysSame(truth.getSuperClasses(), check.getSuperClasses());
    }

    public static <T> void verifyArraysSame(T[]  expected, T[] check)
    {
        assertEquals("Arrays must be same size", expected.length, check.length);
        
        Set<T>  expectedValues = new HashSet<T>();
        
        for (final T e : expected)
        {
            expectedValues.add(e);
        }
        
        for (final T e : check)
        {
            assertTrue("Must contain "+e,expectedValues.contains(e));
        }
    }
    
    public static void verifySame(final DataModelInfo truth,
            final DataModelInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        assertEquals(truth.getRowCount(), check.getRowCount());
        assertEquals(truth.getRowIndex(), check.getRowIndex());

        // assertEquals(truth.getRowData(), check.getRowData());
        // assertEquals(truth.getWrappedData(), check.getWrappedData());
    }

    public static void verifySame(final ViewObject truth, final ViewObject check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        assertEquals(truth.getAllDecorators().size(), check.getAllDecorators()
                .size());

        for (int i = 0; i < check.getAllDecorators().size(); i++)
        {
            verifySame((Decorator) truth.getAllDecorators().get(i),
                    (Decorator) check.getAllDecorators().get(i));
        }

        // TODO: hard to check adapters since they are arbitrary
        assertEquals(truth.getAllAdapters().size(), truth.getAllAdapters()
                .size());
        // can at least check that the keys match
        assertEquals(truth.getAllAdapters().keySet(), check.getAllAdapters()
                .keySet());
    }

    public static void verifySame(final ComponentInfo truth,
            final ComponentInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        verifySame((ViewObject) truth, (ViewObject) check);
        verifySame(truth.getComponentTypeInfo(), check.getComponentTypeInfo());

        assertEquals(truth.getId(), check.getId());
        assertEquals(truth.isRendered(), check.isRendered());
//        assertEquals(truth.getMostSpecificComponentName(), check
//                .getMostSpecificComponentName());
        // assertEquals(truth.getParent(), t);
        // TestRenderNode.verifySame(truth.getRenderNode(),
        // check.getRenderNode());

        assertEquals(truth.getChildren().size(), check.getChildren().size());

        for (int i = 0; i < check.getChildren().size(); i++)
        {
            final ComponentInfo checkChild = (ComponentInfo) check
                    .getChildren().get(i);
            verifySame((ComponentInfo) truth.getChildren().get(i), checkChild);
        }

        final List<?> truthGetAllDecorators = truth.getAllDecorators();
        final List<?> checkGetAllDecorators = check.getAllDecorators();

        for (int i = 0; i < checkGetAllDecorators.size(); i++)
        {
            final Decorator checkDecorator = (Decorator) checkGetAllDecorators
                    .get(i);

            verifySame((Decorator) truthGetAllDecorators.get(i), checkDecorator);
        }
    }

    public static void verifySame(final UIOutputInfo truth,
            final UIOutputInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        verifySame((ComponentInfo) truth, (ComponentInfo) check);
        verifySame((IValueHolderInfo) truth, (IValueHolderInfo) check);
    }

    public static void verifySame(final UIInputInfo truth,
            final UIInputInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        verifySame((UIOutputInfo) truth, (UIOutputInfo) check);
        verifySame((IEditableValueHolderInfo) truth,
                (IEditableValueHolderInfo) check);
    }

    public static void verifySame(final UICommandInfo truth,
            final UICommandInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        verifySame((ComponentInfo) truth, (ComponentInfo) check);
        verifySame((IActionSourceInfo) truth, (IActionSourceInfo) check);
    }

    public static void verifySame(final UIFormInfo truth, final UIFormInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        verifySame((ComponentInfo) truth, (ComponentInfo) check);
        assertEquals(truth.isPrependId(), check.isPrependId());
        assertEquals(truth.isSubmitted(), check.isSubmitted());
    }

    public static void verifySame(final UIDataInfo truth, final UIDataInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        verifySame((ComponentInfo) truth, (ComponentInfo) check);

        assertEquals(truth.getFirst(), check.getFirst());
        assertEquals(truth.getRowCount(), check.getRowCount());
        assertEquals(truth.isRowAvailable(), check.isRowAvailable());
        assertEquals(truth.getRowIndex(), check.getRowIndex());
        assertEquals(truth.getRows(), check.getRows());
        assertEquals(truth.getVar(), check.getVar());
    }

    public static void verifySame(final Decorator truth, final Decorator check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        assertEquals(truth.getClass(), check.getClass());

        verifySame((ViewObject) truth, (ViewObject) check);

        if (truth instanceof FacetDecorator)
        {
            verifySame((FacetDecorator) truth, (FacetDecorator) check);
        }
        else if (truth instanceof ActionListenerDecorator)
        {
            verifySame((ActionListenerDecorator) truth,
                    (ActionListenerDecorator) check);
        }
        else if (truth instanceof ValidatorDecorator)
        {
            verifySame((ValidatorDecorator) truth, (ValidatorDecorator) check);
        }
        else if (truth instanceof ConverterDecorator)
        {
            verifySame((ConverterDecorator) truth, (ConverterDecorator) check);
        }
        else if (truth instanceof ComponentDecorator)
        {
            verifySame((ComponentDecorator) truth, (ComponentDecorator) check);
        }
    }

    private static void verifySame(final ComponentDecorator truth,
            final ComponentDecorator check)
    {
        verifySame(truth.getDecorates(), check.getDecorates());
    }

    private static void verifySame(final ActionListenerDecorator truth,
            final ActionListenerDecorator check)
    {
        verifySame((ComponentDecorator) truth, (ComponentDecorator) check);
    }

    private static void verifySame(final FacetDecorator truth,
            final FacetDecorator check)
    {
        verifySame((ComponentDecorator) truth, (ComponentDecorator) check);
        assertEquals(truth.getName(), check.getName());
    }

    private static void verifySame(final ConverterDecorator truth,
            final ConverterDecorator check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        verifySame((ComponentDecorator) truth, (ComponentDecorator) check);
    }

    public static void verifySame(final ConverterTypeInfo truth,
            final ConverterTypeInfo check)
    {
        if (truth == check)
        {
            return;
        }
        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        assertEquals(truth.getClassName(), check.getClassName());
        assertEquals(truth.getConverterId(), check.getConverterId());
    }

    private static void verifySame(final ValidatorDecorator truth,
            final ValidatorDecorator check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        verifySame((ComponentDecorator) truth, (ComponentDecorator) check);
    }

    public static void verifySame(final ValidatorTypeInfo truth,
            final ValidatorTypeInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        assertEquals(truth.getClassName(), check.getClassName());
        assertEquals(truth.getValidatorId(), check.getValidatorId());
    }

    public static ComponentTypeInfo createComponentTypeInfo()
    {
        return new ComponentTypeInfo("org.eclipse.jst.jsf.test",
                "org.eclipse.jst.jsf.test.ComponentClass",
                "org.eclipse.jst.jsf.test.ComponentFamily",
                "org.eclipse.jst.jsf.test.RenderFamily");
    }

    public static void verifySame(final IValueHolderInfo truth,
            final IValueHolderInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        assertEquals(truth.getLocalValue(), check.getLocalValue());
        assertEquals(truth.getValue(), check.getValue());
        verifySame(truth.getConverter(), check.getConverter());
    }

    public static void verifySame(final IEditableValueHolderInfo truth,
            final IEditableValueHolderInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        verifySame((IValueHolderInfo) truth, (IValueHolderInfo) check);

        assertEquals(truth.isImmediate(), truth.isImmediate());
        assertEquals(truth.isLocalSetValue(), truth.isLocalSetValue());
        assertEquals(truth.isRequired(), truth.isRequired());
        assertEquals(truth.isValid(), truth.isValid());

        assertEquals(truth.getSubmittedValue(), check.getSubmittedValue());
        assertEquals(truth.getValidator(), check.getValidator());
        assertEquals(truth.getValueChangeListener(), truth
                .getValueChangeListener());

        // check validators
        assertEquals(truth.getValidators().size(), check.getValidators().size());

        for (int i = 0; i < check.getValidators().size(); i++)
        {
            verifySame((Decorator) truth.getValidators().get(i),
                    (Decorator) check.getValidators().get(i));
        }

        for (int i = 0; i < check.getValueChangeListeners().size(); i++)
        {
            verifySame((Decorator) truth.getValidators().get(i),
                    (Decorator) check.getValidators().get(i));
        }
    }

    public static void verifySame(final IActionSourceInfo truth,
            final IActionSourceInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        assertEquals(truth.getAction(), check.getAction());
        assertEquals(truth.getActionListener(), check.getActionListener());
        assertEquals(truth.isImmediate(), check.isImmediate());

        if (truth instanceof IActionSource2Info)
        {
            assertTrue(check instanceof IActionSource2Info);
            assertEquals(((IActionSource2Info) truth).getActionExpression(),
                    ((IActionSource2Info) check).getActionExpression());
        }

        // check action listener
        assertEquals(truth.getActionListeners().size(), check
                .getActionListeners().size());

        for (int i = 0; i < check.getActionListeners().size(); i++)
        {
            verifySame((Decorator) truth.getActionListeners().get(i),
                    (Decorator) check.getActionListeners().get(i));
        }
    }
}
