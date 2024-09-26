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
package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.types.TypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IHandlerTagElement.TagHandlerType;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;

/**
 * Hard-coded TypeInfo for default core and html JSF tags.
 * 
 * @author cbateman
 * 
 */
public final class DefaultTagTypeInfo
{
    private final static Set<String> URI_IS_A_JSF_LIB;
    private final static Set<String> URI_IS_A_JSF_LIB_JAKARTA;

    static
    {
        final Set<String> libs = new HashSet<String>();
        libs.add(ITLDConstants.URI_JSF_CORE);
        libs.add(ITLDConstants.URI_JSF_HTML);
        URI_IS_A_JSF_LIB = Collections.unmodifiableSet(libs);

        final Set<String> libsJakarta = new HashSet<String>();
        libsJakarta.add(ITLDConstants.URI_JSF_CORE_JAKARTA);
        libsJakarta.add(ITLDConstants.URI_JSF_HTML_JAKARTA);
        URI_IS_A_JSF_LIB_JAKARTA = Collections.unmodifiableSet(libsJakarta);
}

    /**
     * @param tagId
     * @param jsfVersion
     * @return a type info for the tag id in jsf version or null if none.
     */
    public TypeInfo getTypeInfo(final TagIdentifier tagId,
            final JSFVersion jsfVersion)
    {

        switch (jsfVersion)
        {
            case V1_0:
            case V1_1:
                return JSF11_ELEMENTS.get(tagId);

            case V1_2:
                return JSF12_ELEMENTS.get(tagId);

            case V2_0:
            	return JSF20_ELEMENTS.get(tagId);

            case V3_0:
            	return JSF20_ELEMENTS.get(tagId);

            case V4_0:
            case V4_1:
            	return JSF40_ELEMENTS.get(tagId);

            default:
                return null;
        }
    }

    /**
     * @param uri
     * @return true if uri is covered by this class
     */
    public static boolean isDefaultLib(final String uri)
    {
        return URI_IS_A_JSF_LIB.contains(uri) || URI_IS_A_JSF_LIB_JAKARTA.contains(uri);
    }

    private static final ComponentTypeInfo      COMPINFO_PARAM                 = new ComponentTypeInfo(
                                                                                       "javax.faces.Parameter", //$NON-NLS-1$
                                                                                       "javax.faces.component.UIParameter", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "javax.faces.Parameter", //$NON-NLS-1$
                                                                                       null);
    private static final ComponentTypeInfo      COMPINFO_PARAM_JAKARTA         = new ComponentTypeInfo(
                                                                                       "jakarta.faces.Parameter", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.UIParameter", //$NON-NLS-1$
                                                                                        new String[]
                                                                                        {
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                        new String[]
                                                                                        { "jakarta.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                        "jakarta.faces.Parameter", //$NON-NLS-1$
                                                                                        null);

    private static final ComponentTypeInfo      COMPINFO_SELECTITEM            = new ComponentTypeInfo(
                                                                                       "javax.faces.SelectItem", //$NON-NLS-1$
                                                                                       "javax.faces.component.UISelectItem", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "javax.faces.SelectItem", //$NON-NLS-1$
                                                                                       null);
    private static final ComponentTypeInfo      COMPINFO_SELECTITEM_JAKARTA            = new ComponentTypeInfo(
                                                                                       "jakarta.faces.SelectItem", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.UISelectItem", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "jakarta.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "jakarta.faces.SelectItem", //$NON-NLS-1$
                                                                                       null);

    private static final ComponentTypeInfo      COMPINFO_SELECTITEMS           = new ComponentTypeInfo(
                                                                                       "javax.faces.SelectItems", //$NON-NLS-1$
                                                                                       "javax.faces.component.UISelectItems", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "javax.faces.SelectItems", //$NON-NLS-1$
                                                                                       null);

    private static final ComponentTypeInfo      COMPINFO_SELECTITEMS_JAKARTA           = new ComponentTypeInfo(
                                                                                       "jakarta.faces.SelectItems", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.UISelectItems", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "jakarta.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "jakarta.faces.SelectItems", //$NON-NLS-1$
                                                                                       null);

    private static final ComponentTypeInfo      COMPINFO_SUBVIEW               = new ComponentTypeInfo(
                                                                                       "javax.faces.NamingContainer", //$NON-NLS-1$
                                                                                       "javax.faces.component.UINamingContainer", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.NamingContainer", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.NamingContainer", //$NON-NLS-1$
                                                                                       null);

    private static final ComponentTypeInfo      COMPINFO_VERBATIM              = new ComponentTypeInfo(
                                                                                       "javax.faces.Output", //$NON-NLS-1$
                                                                                       "javax.faces.component.UIOutput", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.Output", //$NON-NLS-1$
                                                                                       "javax.faces.Text"); //$NON-NLS-1$

    private static final ComponentTypeInfo      COMPINFO_VIEW                  = new ComponentTypeInfo(
                                                                                       "javax.faces.ViewRoot", //$NON-NLS-1$
                                                                                       "javax.faces.component.UIViewRoot", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "javax.faces.ViewRoot", //$NON-NLS-1$
                                                                                       null);

    private static final ComponentTypeInfo      COMPINFO_VIEW_JAKARTA                  = new ComponentTypeInfo(
                                                                                       "jakarta.faces.ViewRoot", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.UIViewRoot", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "jakarta.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "jakarta.faces.ViewRoot", //$NON-NLS-1$
                                                                                       null);

    private static final ComponentTypeInfo      COMPINFO_VIEWPARAM            = new ComponentTypeInfo(
    		                                                                          "javax.faces.ViewParameter", //$NON-NLS-1$
    		                                                                          "javax.faces.component.UIViewParameter", //$NON-NLS-1$
                                                                                      new String[]
                                                                                      {
           "javax.faces.component.UIInput", "javax.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
           "javax.faces.component.UIComponentBase", //$NON-NLS-1$
           "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                      new String[]
                                                                                      {
           "javax.faces.component.EditableValueHolder", //$NON-NLS-1$
           "javax.faces.component.ValueHolder", //$NON-NLS-1$
           "javax.faces.component.StateHolder"                                       }, //$NON-NLS-1$
    		                                                                          "javax.faces.ViewParameter", //$NON-NLS-1$
    		                                                                          null);

    private static final ComponentTypeInfo      COMPINFO_VIEWPARAM_JAKARTA            = new ComponentTypeInfo(
    		                                                                          "jakarta.faces.ViewParameter", //$NON-NLS-1$
    		                                                                          "jakarta.faces.component.UIViewParameter", //$NON-NLS-1$
                                                                                      new String[]
                                                                                      {
           "jakarta.faces.component.UIInput", "jakarta.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
           "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
           "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                      new String[]
                                                                                      {
           "jakarta.faces.component.EditableValueHolder", //$NON-NLS-1$
           "jakarta.faces.component.ValueHolder", //$NON-NLS-1$
           "jakarta.faces.component.StateHolder"                                       }, //$NON-NLS-1$
    		                                                                          "jakarta.faces.ViewParameter", //$NON-NLS-1$
    		                                                                          null);

    // expected type info for jsf/html components
    private static final ComponentTypeInfo      COMPINFO_COLUMN                = new ComponentTypeInfo(
                                                                                       "javax.faces.Column", //$NON-NLS-1$
                                                                                       "javax.faces.component.UIColumn", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object"                   }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder" }, //$NON-NLS-1$
                                                                                       "javax.faces.Column", //$NON-NLS-1$
                                                                                       null);
    private static final ComponentTypeInfo      COMPINFO_COLUMN_JAKARTA                = new ComponentTypeInfo(
                                                                                       "jakarta.faces.Column", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.UIColumn", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object"                   }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "jakarta.faces.component.StateHolder" }, //$NON-NLS-1$
                                                                                       "jakarta.faces.Column", //$NON-NLS-1$
                                                                                       null);
    private static final ComponentTypeInfo      COMPINFO_COMMAND               = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlCommandButton", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlCommandButton", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UICommand", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object"                   }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.ActionSource", //$NON-NLS-1$
            "javax.faces.component.StateHolder"                                       }, //$NON-NLS-1$
                                                                                       "javax.faces.Command", //$NON-NLS-1$
                                                                                       "javax.faces.Button"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_COMMAND_JAKARTA               = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlCommandButton", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlCommandButton", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UICommand", //$NON-NLS-1$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object"                   }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.ActionSource", //$NON-NLS-1$
            "jakarta.faces.component.StateHolder"                                       }, //$NON-NLS-1$
                                                                                       "jakarta.faces.Command", //$NON-NLS-1$
                                                                                       "jakarta.faces.Button"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_COMMANDLINK           = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlCommandLink", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlCommandLink", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UICommand", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object"                   }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.ActionSource", //$NON-NLS-1$
            "javax.faces.component.StateHolder"                                       }, //$NON-NLS-1$
                                                                                       "javax.faces.Command", //$NON-NLS-1$
                                                                                       "javax.faces.Link"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_COMMANDLINK_JAKARTA           = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlCommandLink", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlCommandLink", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UICommand", //$NON-NLS-1$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object"                   }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.ActionSource", //$NON-NLS-1$
            "jakarta.faces.component.StateHolder"                                       }, //$NON-NLS-1$
                                                                                       "jakarta.faces.Command", //$NON-NLS-1$
                                                                                       "jakarta.faces.Link"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_DATATABLE             = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlDataTable", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlDataTable", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIData", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object"                   }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.NamingContainer", //$NON-NLS-1$
            "javax.faces.component.StateHolder"                                       }, //$NON-NLS-1$
                                                                                       "javax.faces.Data", //$NON-NLS-1$
                                                                                       "javax.faces.Table"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_DATATABLE_JAKARTA             = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlDataTable", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlDataTable", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UIData", //$NON-NLS-1$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object"                   }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.NamingContainer", //$NON-NLS-1$
            "jakarta.faces.component.StateHolder"                                       }, //$NON-NLS-1$
                                                                                       "jakarta.faces.Data", //$NON-NLS-1$
                                                                                       "jakarta.faces.Table"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_FORM                  = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlForm", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlForm", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIForm", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object"                   }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.NamingContainer", //$NON-NLS-1$
            "javax.faces.component.StateHolder"                                       }, //$NON-NLS-1$
                                                                                       "javax.faces.Form", //$NON-NLS-1$
                                                                                       "javax.faces.Form"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_FORM_JAKARTA                  = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlForm", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlForm", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UIForm", //$NON-NLS-1$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object"                   }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.NamingContainer", //$NON-NLS-1$
            "jakarta.faces.component.StateHolder"                                       }, //$NON-NLS-1$
                                                                                       "jakarta.faces.Form", //$NON-NLS-1$
                                                                                       "jakarta.faces.Form"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_GRAPHIC               = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlGraphicImage", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlGraphicImage", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIGraphic", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "javax.faces.Graphic", //$NON-NLS-1$
                                                                                       "javax.faces.Image"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_GRAPHIC_JAKARTA               = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlGraphicImage", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlGraphicImage", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UIGraphic", //$NON-NLS-1$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "jakarta.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "jakarta.faces.Graphic", //$NON-NLS-1$
                                                                                       "jakarta.faces.Image"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_HIDDEN                = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlInputHidden", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlInputHidden", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder", //$NON-NLS-1$
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.Input", //$NON-NLS-1$
                                                                                       "javax.faces.Hidden"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_HIDDEN_JAKARTA                = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlInputHidden", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlInputHidden", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UIInput", "jakarta.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.EditableValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.ValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "jakarta.faces.Input", //$NON-NLS-1$
                                                                                       "jakarta.faces.Hidden"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SECRET                = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlInputSecret", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlInputSecret", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder", //$NON-NLS-1$
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder"                                       }, //$NON-NLS-1$
                                                                                       "javax.faces.Input", //$NON-NLS-1$
                                                                                       "javax.faces.Secret"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SECRET_JAKARTA                = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlInputSecret", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlInputSecret", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UIInput", "jakarta.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.EditableValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.ValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.StateHolder"                                       }, //$NON-NLS-1$
                                                                                       "jakarta.faces.Input", //$NON-NLS-1$
                                                                                       "jakarta.faces.Secret"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_INPUTTEXT             = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlInputText", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlInputText", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object"                   }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder", //$NON-NLS-1$
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder"                                       }, //$NON-NLS-1$
                                                                                       "javax.faces.Input", //$NON-NLS-1$
                                                                                       "javax.faces.Text"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_INPUTTEXT_JAKARTA             = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlInputText", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlInputText", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UIInput", "jakarta.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object"                   }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.EditableValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.ValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.StateHolder"                                       }, //$NON-NLS-1$
                                                                                       "jakarta.faces.Input", //$NON-NLS-1$
                                                                                       "jakarta.faces.Text"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_INPUTTEXTAREA         = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlInputTextarea", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlInputTextarea", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder", //$NON-NLS-1$
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.Input", //$NON-NLS-1$
                                                                                       "javax.faces.Textarea"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_INPUTTEXTAREA_JAKARTA         = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlInputTextarea", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlInputTextarea", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UIInput", "jakarta.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.EditableValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.ValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "jakarta.faces.Input", //$NON-NLS-1$
                                                                                       "jakarta.faces.Textarea"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_MESSAGE               = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlMessage", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlMessage", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIMessage", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "javax.faces.Message", //$NON-NLS-1$
                                                                                       "javax.faces.Message"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_MESSAGE_JAKARTA               = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlMessage", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlMessage", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UIMessage", //$NON-NLS-1$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "jakarta.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "jakarta.faces.Message", //$NON-NLS-1$
                                                                                       "jakarta.faces.Message"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_MESSAGES              = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlMessages", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlMessages", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIMessages", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "javax.faces.Messages", //$NON-NLS-1$
                                                                                       "javax.faces.Messages"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_MESSAGES_JAKARTA              = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlMessages", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlMessages", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UIMessages", //$NON-NLS-1$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "jakarta.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "jakarta.faces.Messages", //$NON-NLS-1$
                                                                                       "jakarta.faces.Messages"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_OUTPUTFORMAT          = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlOutputFormat", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlOutputFormat", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIOutput", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.Output", //$NON-NLS-1$
                                                                                       "javax.faces.Format"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_OUTPUTFORMAT_JAKARTA          = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlOutputFormat", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlOutputFormat", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UIOutput", //$NON-NLS-1$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.ValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "jakarta.faces.Output", //$NON-NLS-1$
                                                                                       "jakarta.faces.Format"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_OUTPUTLABEL           = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlOutputLabel", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlOutputLabel", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIOutput", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.Output", //$NON-NLS-1$
                                                                                       "javax.faces.Label"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_OUTPUTLABEL_JAKARTA           = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlOutputLabel", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlOutputLabel", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UIOutput", //$NON-NLS-1$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.ValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "jakarta.faces.Output", //$NON-NLS-1$
                                                                                       "jakarta.faces.Label"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_OUTPUTLINK            = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlOutputLink", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlOutputLink", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIOutput", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.Output", //$NON-NLS-1$
                                                                                       "javax.faces.Link"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_OUTPUTLINK_JAKARTA            = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlOutputLink", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlOutputLink", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UIOutput", //$NON-NLS-1$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.ValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "jakarta.faces.Output", //$NON-NLS-1$
                                                                                       "jakarta.faces.Link"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_OUTPUTTEXT            = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlOutputText", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlOutputText", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIOutput", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.Output", //$NON-NLS-1$
                                                                                       "javax.faces.Text"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_OUTPUTTEXT_JAKARTA            = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlOutputText", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlOutputText", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UIOutput", //$NON-NLS-1$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.ValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "jakarta.faces.Output", //$NON-NLS-1$
                                                                                       "jakarta.faces.Text"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_PANELGRID             = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlPanelGrid", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlPanelGrid", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIPanel", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "javax.faces.Panel", //$NON-NLS-1$
                                                                                       "javax.faces.Grid"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_PANELGRID_JAKARTA             = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlPanelGrid", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlPanelGrid", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UIPanel", //$NON-NLS-1$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "jakarta.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "jakarta.faces.Panel", //$NON-NLS-1$
                                                                                       "jakarta.faces.Grid"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_PANELGROUP            = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlPanelGroup", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlPanelGroup", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIPanel", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "javax.faces.Panel", //$NON-NLS-1$
                                                                                       "javax.faces.Group"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_PANELGROUP_JAKARTA            = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlPanelGroup", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlPanelGroup", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UIPanel", //$NON-NLS-1$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "jakarta.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "jakarta.faces.Panel", //$NON-NLS-1$
                                                                                       "jakarta.faces.Group"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SELECTBOOLEANCHECKBOX = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlSelectBooleanCheckbox", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlSelectBooleanCheckbox", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UISelectBoolean", //$NON-NLS-1$
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder", //$NON-NLS-1$
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.SelectBoolean", //$NON-NLS-1$
                                                                                       "javax.faces.Checkbox"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SELECTBOOLEANCHECKBOX_JAKARTA = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlSelectBooleanCheckbox", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlSelectBooleanCheckbox", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UISelectBoolean", //$NON-NLS-1$
            "jakarta.faces.component.UIInput", "jakarta.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.EditableValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.ValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "jakarta.faces.SelectBoolean", //$NON-NLS-1$
                                                                                       "jakarta.faces.Checkbox"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SELECTMANYCHECKBOX    = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlSelectManyCheckbox", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlSelectManyCheckbox", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UISelectMany", //$NON-NLS-1$
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder", //$NON-NLS-1$
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.SelectMany", //$NON-NLS-1$
                                                                                       "javax.faces.Checkbox"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SELECTMANYCHECKBOX_JAKARTA    = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlSelectManyCheckbox", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlSelectManyCheckbox", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UISelectMany", //$NON-NLS-1$
            "jakarta.faces.component.UIInput", "jakarta.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.EditableValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.ValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "jakarta.faces.SelectMany", //$NON-NLS-1$
                                                                                       "jakarta.faces.Checkbox"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SELECTMANYLISTBOX     = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlSelectManyListbox", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlSelectManyListbox", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UISelectMany", //$NON-NLS-1$
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder", //$NON-NLS-1$
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.SelectMany", //$NON-NLS-1$
                                                                                       "javax.faces.Listbox"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SELECTMANYLISTBOX_JAKARTA     = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlSelectManyListbox", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlSelectManyListbox", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UISelectMany", //$NON-NLS-1$
            "jakarta.faces.component.UIInput", "jakarta.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.EditableValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.ValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "jakarta.faces.SelectMany", //$NON-NLS-1$
                                                                                       "jakarta.faces.Listbox"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SELECTMANYMENU        = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlSelectManyMenu", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlSelectManyMenu", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UISelectMany", //$NON-NLS-1$
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder", //$NON-NLS-1$
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.SelectMany", //$NON-NLS-1$
                                                                                       "javax.faces.Menu"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SELECTMANYMENU_JAKARTA        = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlSelectManyMenu", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlSelectManyMenu", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UISelectMany", //$NON-NLS-1$
            "jakarta.faces.component.UIInput", "jakarta.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.EditableValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.ValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "jakarta.faces.SelectMany", //$NON-NLS-1$
                                                                                       "jakarta.faces.Menu"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SELECTONELISTBOX      = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlSelectOneListbox", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlSelectOneListbox", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UISelectOne", //$NON-NLS-1$
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder", //$NON-NLS-1$
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.SelectOne", //$NON-NLS-1$
                                                                                       "javax.faces.Listbox"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SELECTONELISTBOX_JAKARTA      = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlSelectOneListbox", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlSelectOneListbox", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UISelectOne", //$NON-NLS-1$
            "jakarta.faces.component.UIInput", "jakarta.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.EditableValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.ValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "jakarta.faces.SelectOne", //$NON-NLS-1$
                                                                                       "jakarta.faces.Listbox"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SELECTONEMENU         = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlSelectOneMenu", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlSelectOneMenu", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UISelectOne", //$NON-NLS-1$
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder", //$NON-NLS-1$
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.SelectOne", //$NON-NLS-1$
                                                                                       "javax.faces.Menu"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SELECTONEMENU_JAKARTA         = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlSelectOneMenu", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlSelectOneMenu", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UISelectOne", //$NON-NLS-1$
            "jakarta.faces.component.UIInput", "jakarta.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.EditableValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.ValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "jakarta.faces.SelectOne", //$NON-NLS-1$
                                                                                       "jakarta.faces.Menu"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SELECTONERADIO        = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlSelectOneRadio", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlSelectOneRadio", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UISelectOne", //$NON-NLS-1$
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder", //$NON-NLS-1$
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.SelectOne", //$NON-NLS-1$
                                                                                       "javax.faces.Radio"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SELECTONERADIO_JAKARTA        = new ComponentTypeInfo(
                                                                                       "jakarta.faces.HtmlSelectOneRadio", //$NON-NLS-1$
                                                                                       "jakarta.faces.component.html.HtmlSelectOneRadio", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.UISelectOne", //$NON-NLS-1$
            "jakarta.faces.component.UIInput", "jakarta.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "jakarta.faces.component.UIComponentBase", //$NON-NLS-1$
            "jakarta.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "jakarta.faces.component.EditableValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.ValueHolder", //$NON-NLS-1$
            "jakarta.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "jakarta.faces.SelectOne", //$NON-NLS-1$
                                                                                       "jakarta.faces.Radio"); //$NON-NLS-1$

    // default converters
    private static final ConverterTypeInfo      CONVERTERINFO_DATETIME         = new ConverterTypeInfo(
                                                                                       "javax.faces.convert.DateTimeConverter", //$NON-NLS-1$
                                                                                       "javax.faces.DateTime"); //$NON-NLS-1$
    private static final ConverterTypeInfo      CONVERTERINFO_DATETIME_JAKARTA         = new ConverterTypeInfo(
                                                                                       "jakarta.faces.convert.DateTimeConverter", //$NON-NLS-1$
                                                                                       "jakarta.faces.DateTime"); //$NON-NLS-1$

    private static final ConverterTypeInfo      CONVERTERINFO_NUMBER           = new ConverterTypeInfo(
                                                                                       "javax.faces.convert.NumberConverter", //$NON-NLS-1$
                                                                                       "javax.faces.Number"); //$NON-NLS-1$
    private static final ConverterTypeInfo      CONVERTERINFO_NUMBER_JAKARTA           = new ConverterTypeInfo(
                                                                                       "jakarta.faces.convert.NumberConverter", //$NON-NLS-1$
                                                                                       "jakarta.faces.Number"); //$NON-NLS-1$

    // default validators
    private static final ValidatorTypeInfo      VALIDATORINFO_BEAN             = new ValidatorTypeInfo(
    		                                                                           "javax.faces.validator.BeanValidator", //$NON-NLS-1$
    		                                                                           "javax.faces.Bean"); //$NON-NLS-1$
    private static final ValidatorTypeInfo      VALIDATORINFO_BEAN_JAKARTA             = new ValidatorTypeInfo(
    		                                                                           "jakarta.faces.validator.BeanValidator", //$NON-NLS-1$
    		                                                                           "jakarta.faces.Bean"); //$NON-NLS-1$

    private static final ValidatorTypeInfo      VALIDATORINFO_DOUBLERANGE      = new ValidatorTypeInfo(
                                                                                       "javax.faces.validator.DoubleRangeValidator", //$NON-NLS-1$
                                                                                       "javax.faces.DoubleRange"); //$NON-NLS-1$
    private static final ValidatorTypeInfo      VALIDATORINFO_DOUBLERANGE_JAKARTA      = new ValidatorTypeInfo(
                                                                                       "jakarta.faces.validator.DoubleRangeValidator", //$NON-NLS-1$
                                                                                       "jakarta.faces.DoubleRange"); //$NON-NLS-1$

    private static final ValidatorTypeInfo      VALIDATORINFO_LENGTH           = new ValidatorTypeInfo(
                                                                                       "javax.faces.validator.LengthValidator", //$NON-NLS-1$
                                                                                       "javax.faces.Length"); //$NON-NLS-1$
    private static final ValidatorTypeInfo      VALIDATORINFO_LENGTH_JAKARTA           = new ValidatorTypeInfo(
                                                                                       "jakarta.faces.validator.LengthValidator", //$NON-NLS-1$
                                                                                       "jakarta.faces.Length"); //$NON-NLS-1$

    private static final ValidatorTypeInfo      VALIDATORINFO_LONGRANGE        = new ValidatorTypeInfo(
                                                                                       "javax.faces.validator.LongRangeValidator", //$NON-NLS-1$
                                                                                       "javax.faces.LongRange"); //$NON-NLS-1$
    private static final ValidatorTypeInfo      VALIDATORINFO_LONGRANGE_JAKARTA        = new ValidatorTypeInfo(
                                                                                       "jakarta.faces.validator.LongRangeValidator", //$NON-NLS-1$
                                                                                       "jakarta.faces.LongRange"); //$NON-NLS-1$

    private static final ValidatorTypeInfo      VALIDATORINFO_REGEX            = new ValidatorTypeInfo(
    		                                                                           "javax.faces.validator.RegexValidator", //$NON-NLS-1$
                                                                                       "javax.faces.RegularExpression"); //$NON-NLS-1$
    private static final ValidatorTypeInfo      VALIDATORINFO_REGEX_JAKARTA            = new ValidatorTypeInfo(
    		                                                                           "jakarta.faces.validator.RegexValidator", //$NON-NLS-1$
                                                                                       "jakarta.faces.RegularExpression"); //$NON-NLS-1$

    private static final ValidatorTypeInfo      VALIDATORINFO_REQUIRED         = new ValidatorTypeInfo(
                                                                                       "javax.faces.validator.RequiredValidator", //$NON-NLS-1$
                                                                                       "javax.faces.Required"); //$NON-NLS-1$
    private static final ValidatorTypeInfo      VALIDATORINFO_REQUIRED_JAKARTA         = new ValidatorTypeInfo(
                                                                                       "jakarta.faces.validator.RequiredValidator", //$NON-NLS-1$
                                                                                       "jakarta.faces.Required"); //$NON-NLS-1$

    private static Map<TagIdentifier, TypeInfo> JSF11_ELEMENTS;
    private static Map<TagIdentifier, TypeInfo> JSF12_ELEMENTS;
    private static Map<TagIdentifier, TypeInfo> JSF20_ELEMENTS;
    private static Map<TagIdentifier, TypeInfo> JSF40_ELEMENTS;

    static
    {
        final Map<TagIdentifier, TypeInfo> commonElements = new HashMap<TagIdentifier, TypeInfo>();
        // IJSFConstants.TAG_IDENTIFIER_PHASELISTENER);
        // IJSFConstants.TAG_IDENTIFIER_SETPROPERTYACTIONLISTENER);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_ACTIONLISTENER,
                TagHandlerType.ACTIONLISTENER);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_ATTRIBUTE,
                TagHandlerType.ATTRIBUTE);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_CONVERTDATETIME,
                CONVERTERINFO_DATETIME);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_CONVERTER,
                ConverterTypeInfo.UNKNOWN);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_CONVERTNUMBER,
                CONVERTERINFO_NUMBER);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_FACET, TagHandlerType.FACET);
        // elements.put(IJSFConstants.TAG_IDENTIFIER_LOADBUNDLE,
        // TagHandlerType.);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_PARAM, COMPINFO_PARAM);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_SELECTITEM,
                COMPINFO_SELECTITEM);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_SELECTITEMS,
                COMPINFO_SELECTITEMS);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_SUBVIEW, COMPINFO_SUBVIEW);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_VALIDATEDOUBLERANGE,
                VALIDATORINFO_DOUBLERANGE);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_VALIDATELENGTH,
                VALIDATORINFO_LENGTH);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_VALIDATELONGRANGE,
                VALIDATORINFO_LONGRANGE);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_VALIDATOR,
                ValidatorTypeInfo.UNKNOWN);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_VALUECHANGELISTENER,
                TagHandlerType.VALUECHANGELISTENER);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_VERBATIM, COMPINFO_VERBATIM);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_VIEW, COMPINFO_VIEW);

        // JSF 1.2 core elements
        // none currently...

        // JSF 1.1/1.2 HTML tags
        // html verifier
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_COLUMN, COMPINFO_COLUMN);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON,
                COMPINFO_COMMAND);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_COMMANDLINK,
                COMPINFO_COMMANDLINK);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_DATA_TABLE,
                COMPINFO_DATATABLE);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_FORM, COMPINFO_FORM);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_GRAPHICIMAGE,
                COMPINFO_GRAPHIC);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_INPUTHIDDEN, COMPINFO_HIDDEN);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_INPUTSECRET, COMPINFO_SECRET);

        commonElements
                .put(IJSFConstants.TAG_IDENTIFIER_INPUTTEXT, COMPINFO_INPUTTEXT);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_INPUTTEXTAREA,
                COMPINFO_INPUTTEXTAREA);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_MESSAGE, COMPINFO_MESSAGE);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_MESSAGES, COMPINFO_MESSAGES);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_OUTPUTFORMAT,
                COMPINFO_OUTPUTFORMAT);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_OUTPUTLABEL,
                COMPINFO_OUTPUTLABEL);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_OUTPUTLINK,
                COMPINFO_OUTPUTLINK);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_OUTPUTTEXT,
                COMPINFO_OUTPUTTEXT);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_PANEL_GRID,
                COMPINFO_PANELGRID);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_PANEL_GROUP,
                COMPINFO_PANELGROUP);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_SELECTBOOLEANCHECKBOX,
                COMPINFO_SELECTBOOLEANCHECKBOX);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_SELECTMANYCHECKBOX,
                COMPINFO_SELECTMANYCHECKBOX);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_SELECTMANYLISTBOX,
                COMPINFO_SELECTMANYLISTBOX);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_SELECTMANYMENU,
                COMPINFO_SELECTMANYMENU);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_SELECTONELISTBOX,
                COMPINFO_SELECTONELISTBOX);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_SELECTONEMENU,
                COMPINFO_SELECTONEMENU);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_SELECTONERADIO,
                COMPINFO_SELECTONERADIO);

        JSF11_ELEMENTS = Collections.unmodifiableMap(commonElements);

        JSF12_ELEMENTS = Collections
                .unmodifiableMap(new HashMap<TagIdentifier, TypeInfo>(commonElements));

        //JSF 2.0
        Map<TagIdentifier, TypeInfo> jsf20Elements = new HashMap<TagIdentifier, TypeInfo>(commonElements);
        jsf20Elements.put(IJSFConstants.TAG_IDENTIFIER_VALIDATEBEAN, VALIDATORINFO_BEAN);
        jsf20Elements.put(IJSFConstants.TAG_IDENTIFIER_VALIDATEREGEX, VALIDATORINFO_REGEX);
        jsf20Elements.put(IJSFConstants.TAG_IDENTIFIER_VALIDATEREQUIRED, VALIDATORINFO_REQUIRED);
        jsf20Elements.put(IJSFConstants.TAG_IDENTIFIER_VIEWPARAM, COMPINFO_VIEWPARAM);
        JSF20_ELEMENTS = Collections.unmodifiableMap(jsf20Elements);

        //Jakarta Faces 4.0
        final Map<TagIdentifier, TypeInfo> commonElementsJakarta = new HashMap<TagIdentifier, TypeInfo>();

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_ACTIONLISTENER_JAKARTA,
                TagHandlerType.ACTIONLISTENER);
        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_ATTRIBUTE_JAKARTA,
                TagHandlerType.ATTRIBUTE);
        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_CONVERTDATETIME_JAKARTA,
                CONVERTERINFO_DATETIME_JAKARTA);
        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_CONVERTER_JAKARTA,
                ConverterTypeInfo.UNKNOWN);
        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_CONVERTNUMBER_JAKARTA,
                CONVERTERINFO_NUMBER_JAKARTA);
        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_FACET_JAKARTA, TagHandlerType.FACET);
        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_PARAM_JAKARTA, COMPINFO_PARAM_JAKARTA);
        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_SELECTITEM_JAKARTA,
                COMPINFO_SELECTITEM_JAKARTA);
        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_SELECTITEMS_JAKARTA,
                COMPINFO_SELECTITEMS_JAKARTA);
        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_VALIDATEDOUBLERANGE_JAKARTA,
                VALIDATORINFO_DOUBLERANGE_JAKARTA);
        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_VALIDATELENGTH_JAKARTA,
                VALIDATORINFO_LENGTH_JAKARTA);
        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_VALIDATELONGRANGE_JAKARTA,
                VALIDATORINFO_LONGRANGE_JAKARTA);
        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_VALIDATOR_JAKARTA,
                ValidatorTypeInfo.UNKNOWN);
        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_VALUECHANGELISTENER_JAKARTA,
                TagHandlerType.VALUECHANGELISTENER);
        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_VIEW_JAKARTA, COMPINFO_VIEW_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_COLUMN_JAKARTA, COMPINFO_COLUMN_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON_JAKARTA,
                COMPINFO_COMMAND_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_COMMANDLINK_JAKARTA,
                COMPINFO_COMMANDLINK_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_DATA_TABLE_JAKARTA,
                COMPINFO_DATATABLE_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_FORM_JAKARTA, COMPINFO_FORM_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_GRAPHICIMAGE_JAKARTA,
                COMPINFO_GRAPHIC_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_INPUTHIDDEN_JAKARTA, COMPINFO_HIDDEN_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_INPUTSECRET_JAKARTA, COMPINFO_SECRET_JAKARTA);

        commonElementsJakarta
                .put(IJSFConstants.TAG_IDENTIFIER_INPUTTEXT_JAKARTA, COMPINFO_INPUTTEXT_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_INPUTTEXTAREA_JAKARTA,
                COMPINFO_INPUTTEXTAREA_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_MESSAGE_JAKARTA, COMPINFO_MESSAGE_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_MESSAGES_JAKARTA, COMPINFO_MESSAGES_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_OUTPUTFORMAT_JAKARTA,
                COMPINFO_OUTPUTFORMAT_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_OUTPUTLABEL_JAKARTA,
                COMPINFO_OUTPUTLABEL_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_OUTPUTLINK_JAKARTA,
                COMPINFO_OUTPUTLINK_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_OUTPUTTEXT_JAKARTA,
                COMPINFO_OUTPUTTEXT_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_PANEL_GRID_JAKARTA,
                COMPINFO_PANELGRID_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_PANEL_GROUP_JAKARTA,
                COMPINFO_PANELGROUP_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_SELECTBOOLEANCHECKBOX_JAKARTA,
                COMPINFO_SELECTBOOLEANCHECKBOX_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_SELECTMANYCHECKBOX_JAKARTA,
                COMPINFO_SELECTMANYCHECKBOX_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_SELECTMANYLISTBOX_JAKARTA,
                COMPINFO_SELECTMANYLISTBOX_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_SELECTMANYMENU_JAKARTA,
                COMPINFO_SELECTMANYMENU_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_SELECTONELISTBOX_JAKARTA,
                COMPINFO_SELECTONELISTBOX_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_SELECTONEMENU_JAKARTA,
                COMPINFO_SELECTONEMENU_JAKARTA);

        commonElementsJakarta.put(IJSFConstants.TAG_IDENTIFIER_SELECTONERADIO_JAKARTA,
                COMPINFO_SELECTONERADIO_JAKARTA);

        Map<TagIdentifier, TypeInfo> jsf40Elements = new HashMap<TagIdentifier, TypeInfo>(commonElementsJakarta);
        jsf40Elements.put(IJSFConstants.TAG_IDENTIFIER_VALIDATEBEAN_JAKARTA, VALIDATORINFO_BEAN_JAKARTA);
        jsf40Elements.put(IJSFConstants.TAG_IDENTIFIER_VALIDATEREGEX_JAKARTA, VALIDATORINFO_REGEX_JAKARTA);
        jsf40Elements.put(IJSFConstants.TAG_IDENTIFIER_VALIDATEREQUIRED_JAKARTA, VALIDATORINFO_REQUIRED_JAKARTA);
        jsf40Elements.put(IJSFConstants.TAG_IDENTIFIER_VIEWPARAM_JAKARTA, COMPINFO_VIEWPARAM_JAKARTA);
        JSF40_ELEMENTS = Collections.unmodifiableMap(jsf40Elements);

    }

}
