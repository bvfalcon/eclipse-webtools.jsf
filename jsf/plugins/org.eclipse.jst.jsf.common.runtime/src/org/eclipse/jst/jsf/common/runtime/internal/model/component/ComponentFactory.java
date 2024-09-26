/*******************************************************************************
 * Copyright (c) 2001, 2020 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.common.runtime.internal.model.component;

import java.util.Map;

import org.eclipse.jst.jsf.common.runtime.internal.model.bean.DataModelInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.IActionSource2Info;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.IActionSourceInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.IEditableValueHolderInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.INamingContainerInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.IValueHolderInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.FacetDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.event.IActionListenerInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.event.IValueChangeListenerInfo;

/**
 * Factory for creating component related objects.
 * 
 * @author cbateman
 * 
 */
public class ComponentFactory
{

    /**
     * The key for the standard ValueHolder adapter interface
     */
    public final static Class VALUE_HOLDER = IValueHolderInfo.class;
    /**
     * The key for the standard EditableValueHolder adapter interface
     */
    public final static Class EDITABLE_VALUE_HOLDER = IEditableValueHolderInfo.class;
    /**
     * The key for the standard ActionSource adapter interface
     */
    public final static Class ACTION_SOURCE = IActionSourceInfo.class;
    /**
     * The key for the standard ActionSource2 adapter interface
     */
    public final static Class ACTION_SOURCE2 = IActionSource2Info.class;
    /**
     * The key for the standard NamingContainer adapter interface
     */
    public final static Class NAMING_CONTAINER = INamingContainerInfo.class;

    /**
     * The key for the standard Converter decorator
     */
    public final static Class CONVERTER = ConverterDecorator.class;
    /**
     * The key for the standard Facet decorator
     */
    public final static Class FACET = FacetDecorator.class;
    /**
     * The key for the standard Validator decorator
     */
    public final static Class VALIDATOR = ValidatorDecorator.class;
    /**
     * The key for the standard ValueChangeListener decorator
     */
    public final static Class VALUE_CHANGE_LISTENER = IValueChangeListenerInfo.class;
    /**
     * The key for the standard ActionListener decorator
     */
    public final static Class ACTION_LISTENER = IActionListenerInfo.class;

    /**
     * Base class name for UIInput's
     */
    public final static String BASE_CLASS_UIINPUT = "jakarta.faces.component.UIInput"; //$NON-NLS-1$
    /**
     * Base class name for UIOutput's
     */
    public final static String BASE_CLASS_UIOUTPUT = "jakarta.faces.component.UIOutput"; //$NON-NLS-1$
    /**
     * Base class name for UICommand's
     */
    public final static String BASE_CLASS_UICOMMAND = "jakarta.faces.component.UICommand"; //$NON-NLS-1$
    /**
     * Base class name for UIData's
     */
    public final static String BASE_CLASS_UIDATA = "jakarta.faces.component.UIData"; //$NON-NLS-1$
    /**
     * Base class name for UIForm's
     */
    public final static String BASE_CLASS_UIFORM = "jakarta.faces.component.UIForm"; //$NON-NLS-1$

    /**
     * Interface name for ValueHolder's
     */
    public final static String INTERFACE_VALUEHOLDER = "jakarta.faces.component.ValueHolder"; //$NON-NLS-1$
    /**
     * Interface name for EditableValueHolder's
     */
    public final static String INTERFACE_EDITABLEVALUEHOLDER = "jakarta.faces.component.EditableValueHolder"; //$NON-NLS-1$
    /**
     * Interface name for ActionSource's
     */
    public final static String INTERFACE_ACTIONSOURCE = "jakarta.faces.component.ActionSource"; //$NON-NLS-1$
    /**
     * Interface name for ActionSource2's
     */
    public final static String INTERFACE_ACTIONSOURCE2 = "jakarta.faces.component.ActionSource2"; //$NON-NLS-1$
    /**
     * Interface name for NamingContainer's
     */
    public final static String INTERFACE_NAMINGCONTAINER = "jakarta.faces.component.NamingContainer"; //$NON-NLS-1$

    /**
     * @param id
     * @param parent
     * @param typeInfo
     * @param isRendered
     * @return a new component info
     */
    public static ComponentInfo createComponentInfo(final String id,
            final ComponentInfo parent, final ComponentTypeInfo typeInfo,
            final boolean isRendered)
    {
        return new ComponentInfo(id, parent, typeInfo, isRendered);
    }

    /**
     * If the rendered attribute isn't set, defaults it.
     * 
     * @param attributes
     */
    public static void maybeDefaultRendered(final Map attributes)
    {
        if (!(attributes.get("rendered") instanceof Boolean)) //$NON-NLS-1$
        {
            attributes.put("rendered", Boolean.TRUE); //$NON-NLS-1$
        }
    }

    /**
     * @param parent
     * @param componentTypeInfo
     * @param attributes
     * @return a new component info
     */
    public static ComponentInfo createComponentInfo(final ComponentInfo parent,
            final ComponentTypeInfo componentTypeInfo, final Map attributes)
    {
        maybeDefaultRendered(attributes);
        return new ComponentInfo(parent, componentTypeInfo, attributes);
    }

    /**
     * @param id
     * @param parent
     * @param typeInfo
     * @param editableValueHolder
     * @param isRendered
     * @return a new UIInputInfo
     */
    public static UIInputInfo createUIInputInfo(final String id,
            final ComponentInfo parent, final ComponentTypeInfo typeInfo,
            final IEditableValueHolderInfo editableValueHolder,
            final boolean isRendered)
    {
        return new UIInputInfo(id, parent, typeInfo, editableValueHolder,
                isRendered);
    }

    /**
     * @param parent
     * @param typeInfo
     * @param attributes
     * @return a new UIInputInfo
     */
    public static UIInputInfo createUIInputInfo(final ComponentInfo parent,
            final ComponentTypeInfo typeInfo, final Map attributes)
    {
        maybeDefaultRendered(attributes);
        return new UIInputInfo(parent, typeInfo, attributes);
    }

    /**
     * @param id
     * @param parent
     * @param typeInfo
     * @param valueHolderInfo
     * @param isRendered
     * @return a new UIOutputInfo
     */
    public static UIOutputInfo createUIOutputInfo(final String id,
            final ComponentInfo parent, final ComponentTypeInfo typeInfo,
            final IValueHolderInfo valueHolderInfo, final boolean isRendered)
    {
        return new UIOutputInfo(id, parent, typeInfo, valueHolderInfo,
                isRendered);
    }

    /**
     * @param parent
     * @param typeInfo
     * @param attributes
     * @return a new UIOutputInfo
     */
    public static UIOutputInfo createUIOutputInfo(final ComponentInfo parent,
            final ComponentTypeInfo typeInfo, final Map attributes)
    {
        maybeDefaultRendered(attributes);
        return new UIOutputInfo(parent, typeInfo, attributes);
    }

    /**
     * @param id
     * @param parent
     * @param typeInfo
     * @param actionSourceInfo
     * @param isRendered
     * @return a new UICommandInfo
     */
    public static UICommandInfo createUICommandInfo(final String id,
            final ComponentInfo parent, final ComponentTypeInfo typeInfo,
            final IActionSourceInfo actionSourceInfo, final boolean isRendered)
    {
        return new UICommandInfo(id, parent, typeInfo, isRendered,
                actionSourceInfo);
    }

    /**
     * @param parent
     * @param typeInfo
     * @param attributes
     * @return a new UICommandInfo
     */
    public static UICommandInfo createUICommandInfo(final ComponentInfo parent,
            final ComponentTypeInfo typeInfo, final Map attributes)
    {
        maybeDefaultRendered(attributes);
        return new UICommandInfo(parent, typeInfo, attributes);
    }

    /**
     * @param id
     * @param parent
     * @param typeInfo
     * @param isRendered
     * @param prependId
     * @param submitted
     * @return a new UIFormInfo
     */
    public static UIFormInfo createUIFormInfo(final String id,
            final ComponentInfo parent, final ComponentTypeInfo typeInfo,
            final boolean isRendered, final boolean prependId,
            final boolean submitted)
    {
        return new UIFormInfo(id, parent, typeInfo, isRendered, prependId,
                submitted);
    }

    /**
     * @param parent
     * @param typeInfo
     * @param attributes
     * @return a new UIFormInfo
     */
    public static UIFormInfo createUIFormInfo(final ComponentInfo parent,
            final ComponentTypeInfo typeInfo, final Map attributes)
    {
        maybeDefaultRendered(attributes);
        maybeDefaultPrependId(attributes);
        maybeDefaultSubmitted(attributes);
        return new UIFormInfo(parent, typeInfo, attributes);
    }

    private static void maybeDefaultSubmitted(Map attributes)
    {
        if (!(attributes.get("submitted") instanceof Boolean)) //$NON-NLS-1$
        {
            attributes.put("submitted", Boolean.FALSE); //$NON-NLS-1$
        }
    }

    private static void maybeDefaultPrependId(Map attributes)
    {
        if (!(attributes.get("prependId") instanceof Boolean)) //$NON-NLS-1$
        {
            attributes.put("prependId", Boolean.FALSE); //$NON-NLS-1$
        }
    }

    /**
     * @param id
     * @param parent
     * @param typeInfo
     * @param isRendered
     * @param dataModel
     * @param first
     * @param footer
     * @param header
     * @param rowCount
     * @param rowAvailable
     * @param rowData
     * @param rowIndex
     * @param rows
     * @param value
     * @param var
     * @return a new UIDataInfo
     */
    public static UIDataInfo createUIDataInfo(final String id,
            final ComponentInfo parent, final ComponentTypeInfo typeInfo,
            final boolean isRendered, final DataModelInfo dataModel,
            final int first, final ComponentInfo footer,
            final ComponentInfo header, final int rowCount,
            final boolean rowAvailable, final Object rowData,
            final int rowIndex, final int rows, final Object value,
            final String var)
    {
        return new UIDataInfo(id, parent, typeInfo, isRendered, dataModel,
                first, footer, header, rowCount, rowAvailable, rowData,
                rowIndex, rows, value, var);
    }

    /**
     * @param parent
     * @param typeInfo
     * @param attributes
     * @return the UIDataInfo
     */
    public static UIDataInfo createUIDataInfo(final ComponentInfo parent,
            final ComponentTypeInfo typeInfo, final Map attributes)
    {
        maybeDefaultRendered(attributes);
        maybeDefaultFirst(attributes);
        maybeDefaultRowCount(attributes);
        maybeDefaultRowAvailable(attributes);
        maybeDefaultRowIndex(attributes);
        maybeDefaultRows(attributes);
        maybeDefaultVar(attributes);
        return new UIDataInfo(parent, typeInfo, attributes);
    }


    private static final Integer ZERO = Integer.valueOf(0);
    private static final Integer MINUS_ONE = Integer.valueOf(-1);

    private static void maybeDefaultFirst(Map attributes)
    {
        if (!(attributes.get("first") instanceof Integer)) //$NON-NLS-1$
        {
            attributes.put("first", ZERO); //$NON-NLS-1$
        }
    }

    private static void maybeDefaultRowCount(Map attributes)
    {
        if (!(attributes.get("rowCount") instanceof Integer)) //$NON-NLS-1$
        {
            attributes.put("rowCount", MINUS_ONE); //$NON-NLS-1$
        }
    }

    private static void maybeDefaultRowAvailable(Map attributes)
    {
        if (! (attributes.get("rowAvailable") instanceof Boolean)) //$NON-NLS-1$
        {
            attributes.put("rowAvailable", Boolean.FALSE); //$NON-NLS-1$
        }
    }

    private static void maybeDefaultRowIndex(Map attributes)
    {
        if (! (attributes.get("rowIndex") instanceof Integer)) //$NON-NLS-1$
        {
            attributes.put("rowIndex", MINUS_ONE); //$NON-NLS-1$
        }
    }

    private static void maybeDefaultRows(Map attributes)
    {
        if (! (attributes.get("rows") instanceof Integer)) //$NON-NLS-1$
        {
            attributes.put("rows", ZERO); //$NON-NLS-1$
        }
    }

    private static void maybeDefaultVar(Map attributes)
    {
        if (! (attributes.get("var") instanceof String)) //$NON-NLS-1$
        {
            attributes.put("var", "** default variable **"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }
}
