/*******************************************************************************
 * Copyright (c) 2006, 2013 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.internal.tld;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;

/**
 * Constants covering the JSF Core and JSF HTML tag and tag attribute names
 */
public interface IJSFConstants 
{

	/**
	 * actionListener tagname
	 */
	final static public String TAG_ACTIONLISTENER = "actionListener"; //$NON-NLS-1$

	/**
	 * attribute tagname
	 */
	final static public String TAG_ATTRIBUTE = "attribute"; //$NON-NLS-1$

	/**
	 * convertDateTime tagname
	 */
	final static public String TAG_CONVERTDATETIME = "convertDateTime"; //$NON-NLS-1$

	/**
	 * converter tagname
	 */
	final static public String TAG_CONVERTER = "converter"; //$NON-NLS-1$

	/**
	 * convertNumber tagname
	 */
	final static public String TAG_CONVERTNUMBER = "convertNumber"; //$NON-NLS-1$

	/**
	 * facet tagname
	 */
	final static public String TAG_FACET = "facet"; //$NON-NLS-1$

	/**
	 * loadBundle tagname
	 */
	final static public String TAG_LOADBUNDLE = "loadBundle"; //$NON-NLS-1$

	/**
	 * param tagname
	 */
	final static public String TAG_PARAM = "param"; //$NON-NLS-1$

    /**
     * phaseListener tagname
     */
	final static public String TAG_PHASELISTENER = "phaseListener"; //$NON-NLS-1$
	
	/**
	 * selectItem tagname
	 */
	final static public String TAG_SELECTITEM = "selectItem"; //$NON-NLS-1$

	/**
	 * selectItems tagname
	 */
	final static public String TAG_SELECTITEMS = "selectItems"; //$NON-NLS-1$

	/**
	 * setPropertyActionListener tagname
	 */
	final static public String TAG_SETPROPERTYACTIONLISTENER = "setPropertyActionListener"; //$NON-NLS-1$
	
	/**
	 * subview tagname
	 */
	final static public String TAG_SUBVIEW = "subview"; //$NON-NLS-1$

	/**
	 * validateBean tagname
	 */
	final static public String TAG_VALIDATEBEAN = "validateBean"; //$NON-NLS-1$

	/**
	 * validateDoubleRange tagname
	 */
	final static public String TAG_VALIDATEDOUBLERANGE = "validateDoubleRange"; //$NON-NLS-1$

	/**
	 * validateLength tagname
	 */
	final static public String TAG_VALIDATELENGTH = "validateLength"; //$NON-NLS-1$

	/**
	 * validateLongRange tagname
	 */
	final static public String TAG_VALIDATELONGRANGE = "validateLongRange"; //$NON-NLS-1$

	/**
	 * validateRegex tagname
	 */
	final static public String TAG_VALIDATEREGEX = "validateRegex"; //$NON-NLS-1$

	/**
	 * validateRequired tagname
	 */
	final static public String TAG_VALIDATEREQUIRED = "validateRequired"; //$NON-NLS-1$

	/**
	 * validator tagname
	 */
	final static public String TAG_VALIDATOR = "validator"; //$NON-NLS-1$

	/**
	 * valueChangeListener tagname
	 */
	final static public String TAG_VALUECHANGELISTENER = "valueChangeListener"; //$NON-NLS-1$

	/**
	 * verbatim tagname
	 */
	final static public String TAG_VERBATIM = "verbatim"; //$NON-NLS-1$

	/**
	 * view tagname
	 */
	final static public String TAG_VIEW = "view"; //$NON-NLS-1$

	/**
	 * viewParam tagname
	 */
	final static public String TAG_VIEWPARAM = "viewParam"; //$NON-NLS-1$

	/**
	 * column tagname
	 */
	final static public String TAG_COLUMN = "column"; //$NON-NLS-1$

	/**
	 * commandButton tagname
	 */
	final static public String TAG_COMMANDBUTTON = "commandButton"; //$NON-NLS-1$

	/**
	 * commandLink tagname
	 */
	final static public String TAG_COMMANDLINK = "commandLink"; //$NON-NLS-1$

	/**
	 * dataTable tagname
	 */
	final static public String TAG_DATATABLE = "dataTable"; //$NON-NLS-1$

	/**
	 * form tagname
	 */
	final static public String TAG_FORM = "form"; //$NON-NLS-1$

	/**
	 * graphicImage tagname
	 */
	final static public String TAG_GRAPHICIMAGE = "graphicImage"; //$NON-NLS-1$

	/**
	 * inputHidden tagname
	 */
	final static public String TAG_INPUTHIDDEN = "inputHidden"; //$NON-NLS-1$

	/**
	 * inputSecret tagname
	 */
	final static public String TAG_INPUTSECRET = "inputSecret"; //$NON-NLS-1$

	/**
	 * inputText tagname
	 */
	final static public String TAG_INPUTTEXT = "inputText"; //$NON-NLS-1$

	/**
	 * inputTextarea tagname
	 */
	final static public String TAG_INPUTTEXTAREA = "inputTextarea"; //$NON-NLS-1$

	/**
	 * message tagname
	 */
	final static public String TAG_MESSAGE = "message"; //$NON-NLS-1$

	/**
	 * messages tagname
	 */
	final static public String TAG_MESSAGES = "messages"; //$NON-NLS-1$

	/**
	 * outputFormat tagname
	 */
	final static public String TAG_OUTPUTFORMAT = "outputFormat"; //$NON-NLS-1$

	/**
	 * outputLabel tagname
	 */
	final static public String TAG_OUTPUTLABEL = "outputLabel"; //$NON-NLS-1$

	/**
	 * outputLink tagname
	 */
	final static public String TAG_OUTPUTLINK = "outputLink"; //$NON-NLS-1$

	/**
	 * outputText tagname
	 */
	final static public String TAG_OUTPUTTEXT = "outputText"; //$NON-NLS-1$

	/**
	 * panelGrid tagname
	 */
	final static public String TAG_PANELGRID = "panelGrid"; //$NON-NLS-1$

	/**
	 * panelGroup tagname
	 */
	final static public String TAG_PANELGROUP = "panelGroup"; //$NON-NLS-1$

	/**
	 * selectBooleanCheckbox tagname
	 */
	final static public String TAG_SELECTBOOLEANCHECKBOX = "selectBooleanCheckbox"; //$NON-NLS-1$

	/**
	 * selectManyCheckbox tagname
	 */
	final static public String TAG_SELECTMANYCHECKBOX = "selectManyCheckbox"; //$NON-NLS-1$

	/**
	 * selectManyListbox tagname
	 */
	final static public String TAG_SELECTMANYLISTBOX = "selectManyListbox"; //$NON-NLS-1$

	/**
	 * selectManyMenu tagname
	 */
	final static public String TAG_SELECTMANYMENU = "selectManyMenu"; //$NON-NLS-1$

	/**
	 * selectOneListbox tagname
	 */
	final static public String TAG_SELECTONELISTBOX = "selectOneListbox"; //$NON-NLS-1$

	/**
	 * selectOneMenu tagname
	 */
	final static public String TAG_SELECTONEMENU = "selectOneMenu"; //$NON-NLS-1$

	/**
	 * selectOneMenu tagname
	 */
	final static public String TAG_SELECTONERADIO = "selectOneRadio"; //$NON-NLS-1$

    // tag identifiers
    /**
     * TagIdentifier for TAG_VIEW
     */
    final static TagIdentifier TAG_IDENTIFIER_VIEW =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_VIEW);
    /**
     * Jakarta EE version of TagIdentifier for TAG_VIEW
     */
    final static TagIdentifier TAG_IDENTIFIER_VIEW_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JAKARTA, TAG_VIEW);

    /**
     * TagIdentifier for TAG_VIEWPARAM
     */
    final static TagIdentifier TAG_IDENTIFIER_VIEWPARAM =
    	TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_VIEWPARAM);
    /**
     * Jakarta EE version of TagIdentifier for TAG_VIEWPARAM
     */
    final static TagIdentifier TAG_IDENTIFIER_VIEWPARAM_JAKARTA =
    	TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JAKARTA, TAG_VIEWPARAM);

    /**
     * TagIdentifier for TAG_LOADBUNDLE
     */
    final static TagIdentifier TAG_IDENTIFIER_LOADBUNDLE =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_LOADBUNDLE);
    /**
     * Jakarta EE version of TagIdentifier for TAG_LOADBUNDLE
     */
    final static TagIdentifier TAG_IDENTIFIER_LOADBUNDLE_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JAKARTA, TAG_LOADBUNDLE);
    /**
     * JCP version of TagIdentifier for TAG_LOADBUNDLE
     */
    final static TagIdentifier TAG_IDENTIFIER_LOADBUNDLE_JCP =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JCP, TAG_LOADBUNDLE);

    /**
     * TagIdentifier for TAG_FACET
     */
    final static TagIdentifier TAG_IDENTIFIER_FACET =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_FACET);
    /**
     * Jakarta EE version of TagIdentifier for TAG_FACET
     */
    final static TagIdentifier TAG_IDENTIFIER_FACET_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JAKARTA, TAG_FACET);
    /**
     * JCP version of TagIdentifier for TAG_FACET
     */
    final static TagIdentifier TAG_IDENTIFIER_FACET_JCP =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JCP, TAG_FACET);
    
    /**
     * TagIdentifier for TAG_VERBATIM
     */
    final static TagIdentifier TAG_IDENTIFIER_VERBATIM =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_VERBATIM);
    
    /**
     * TagIdentifier for TAG_DATATABLE
     */
    final static TagIdentifier TAG_IDENTIFIER_DATA_TABLE = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_DATATABLE);
    /**
     * Jakarta EE version of TagIdentifier for TAG_DATATABLE
     */
    final static TagIdentifier TAG_IDENTIFIER_DATA_TABLE_JAKARTA = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_DATATABLE);
    /**
     * JCP version of TagIdentifier for TAG_DATATABLE
     */
    final static TagIdentifier TAG_IDENTIFIER_DATA_TABLE_JCP = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JCP, TAG_DATATABLE);

    /**
     * TagIdentifier for TAG_PANELGRID
     */
    final static TagIdentifier TAG_IDENTIFIER_PANEL_GRID =
       TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_PANELGRID);
    /**
     * Jakarta EE version of TagIdentifier for TAG_PANELGRID
     */
    final static TagIdentifier TAG_IDENTIFIER_PANEL_GRID_JAKARTA =
       TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_PANELGRID);
    /**
     * JCP version of TagIdentifier for TAG_PANELGRID
     */
    final static TagIdentifier TAG_IDENTIFIER_PANEL_GRID_JCP =
       TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JCP, TAG_PANELGRID);

    /**
     * TagIdentifier for TAG_COLUMN
     */
    final static TagIdentifier TAG_IDENTIFIER_COLUMN =
       TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_COLUMN);
    /**
     * Jakarta EE version of TagIdentifier for TAG_COLUMN
     */
    final static TagIdentifier TAG_IDENTIFIER_COLUMN_JAKARTA =
       TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_COLUMN);
    /**
     * JCP version of TagIdentifier for TAG_COLUMN
     */
    final static TagIdentifier TAG_IDENTIFIER_COLUMN_JCP =
       TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JCP, TAG_COLUMN);

    /**
     * TagIdentifier for TAG_FORM
     */
    final static TagIdentifier TAG_IDENTIFIER_FORM =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_FORM);
    /**
     * Jakarta EE version of TagIdentifier for TAG_FORM
     */
    final static TagIdentifier TAG_IDENTIFIER_FORM_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_FORM);

    /**
     * TagIdentifier for TAG_INPUTTEXT
     */
    final static TagIdentifier TAG_IDENTIFIER_INPUTTEXT =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_INPUTTEXT);
    /**
     * Jakarta EE version of TagIdentifier for TAG_INPUTTEXT
     */
    final static TagIdentifier TAG_IDENTIFIER_INPUTTEXT_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_INPUTTEXT);
    
    /**
     * TagIdentifier for TAG_INPUTSECRET
     */
    final static TagIdentifier TAG_IDENTIFIER_INPUTSECRET =
    	TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_INPUTSECRET);
    /**
     * Jakarta EE version of TagIdentifier for TAG_INPUTSECRET
     */
    final static TagIdentifier TAG_IDENTIFIER_INPUTSECRET_JAKARTA =
    	TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_INPUTSECRET);

    /**
     * TagIdentifier for TAG_INPUTTEXTAREA
     */
    final static TagIdentifier TAG_IDENTIFIER_INPUTTEXTAREA =
    	TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_INPUTTEXTAREA);
    /**
     * Jakarta EE version of TagIdentifier for TAG_INPUTTEXTAREA
     */
    final static TagIdentifier TAG_IDENTIFIER_INPUTTEXTAREA_JAKARTA =
    	TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_INPUTTEXTAREA);

    /**
     * TagIdentifier for TAG_OUTPUTTEXT
     */
    final static TagIdentifier TAG_IDENTIFIER_OUTPUTTEXT =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_OUTPUTTEXT);
    /**
     * Jakarta EE version of TagIdentifier for TAG_OUTPUTTEXT
     */
    final static TagIdentifier TAG_IDENTIFIER_OUTPUTTEXT_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_OUTPUTTEXT);
    
    /**
     * TagIdentifier for TAG_OUTPUTLABEL
     */
    final static TagIdentifier TAG_IDENTIFIER_OUTPUTLABEL =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_OUTPUTLABEL);
    /**
     * Jakarta EE version of TagIdentifier for TAG_OUTPUTLABEL
     */
    final static TagIdentifier TAG_IDENTIFIER_OUTPUTLABEL_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_OUTPUTLABEL);
    
    /**
     * TagIdentifier for TAG_GRAPHICIMAGE
     */
    final static TagIdentifier TAG_IDENTIFIER_GRAPHICIMAGE =
    	TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_GRAPHICIMAGE);
    /**
     * Jakarta EE version of TagIdentifier for TAG_GRAPHICIMAGE
     */
    final static TagIdentifier TAG_IDENTIFIER_GRAPHICIMAGE_JAKARTA =
    	TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_GRAPHICIMAGE);

    /**
     * TagIdentifier for TAG_COMMANDBUTTON
     */
    final static TagIdentifier TAG_IDENTIFIER_COMMANDBUTTON =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_COMMANDBUTTON);
    /**
     * Jakarta EE version of TagIdentifier for TAG_COMMANDBUTTON
     */
    final static TagIdentifier TAG_IDENTIFIER_COMMANDBUTTON_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_COMMANDBUTTON);

    /**
     * TagIdentifier for TAG_COMMANDLINK
     */
    final static TagIdentifier TAG_IDENTIFIER_COMMANDLINK =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_COMMANDLINK);
    /**
     * Jakarta EE version of TagIdentifier for TAG_COMMANDLINK
     */
    final static TagIdentifier TAG_IDENTIFIER_COMMANDLINK_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_COMMANDLINK);

    /**
     * TagIdentifier for TAG_INPUTHIDDEN
     */
    final static TagIdentifier TAG_IDENTIFIER_INPUTHIDDEN =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_INPUTHIDDEN);
    /**
     * Jakarta EE version of TagIdentifier for TAG_INPUTHIDDEN
     */
    final static TagIdentifier TAG_IDENTIFIER_INPUTHIDDEN_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_INPUTHIDDEN);

    /**
     * TagIdentifier for TAG_MESSAGE
     */
    final static TagIdentifier TAG_IDENTIFIER_MESSAGE =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_MESSAGE);
    /**
     * Jakarta EE version of TagIdentifier for TAG_MESSAGE
     */
    final static TagIdentifier TAG_IDENTIFIER_MESSAGE_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_MESSAGE);

    /**
     * TagIdentifier for TAG_MESSAGES
     */
    final static  TagIdentifier TAG_IDENTIFIER_MESSAGES =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_MESSAGES);
    /**
     * Jakarta EE version of TagIdentifier for TAG_MESSAGES
     */
    final static  TagIdentifier TAG_IDENTIFIER_MESSAGES_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_MESSAGES);

    /**
     * TagIdentifier for TAG_OUTPUTFORMAT
     */
    final static TagIdentifier TAG_IDENTIFIER_OUTPUTFORMAT =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_OUTPUTFORMAT);
    /**
     * Jakarta EE version of TagIdentifier for TAG_OUTPUTFORMAT
     */
    final static TagIdentifier TAG_IDENTIFIER_OUTPUTFORMAT_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_OUTPUTFORMAT);

    /**
     * TagIdentifier for TAG_OUTPUTLINK
     */
    final static TagIdentifier TAG_IDENTIFIER_OUTPUTLINK =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_OUTPUTLINK);
    /**
     * Jakarta EE version of TagIdentifier for TAG_OUTPUTLINK
     */
    final static TagIdentifier TAG_IDENTIFIER_OUTPUTLINK_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_OUTPUTLINK);

    /**
     * TagIdentifier for TAG_PANELGROUP
     */
    final static TagIdentifier TAG_IDENTIFIER_PANEL_GROUP =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_PANELGROUP);
    /**
     * Jakarta EE version of TagIdentifier for TAG_PANELGROUP
     */
    final static TagIdentifier TAG_IDENTIFIER_PANEL_GROUP_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_PANELGROUP);

    /**
     * TagIdentifier for TAG_SELECTBOOLEANCHECKBOX
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTBOOLEANCHECKBOX = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_SELECTBOOLEANCHECKBOX);
    /**
     * Jakarta EE version of TagIdentifier for TAG_SELECTBOOLEANCHECKBOX
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTBOOLEANCHECKBOX_JAKARTA = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_SELECTBOOLEANCHECKBOX);

    /**
     * TagIdentifier for TAG_SELECTMANYCHECKBOX
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTMANYCHECKBOX = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_SELECTMANYCHECKBOX);
    /**
     * Jakarta EE version of TagIdentifier for TAG_SELECTMANYCHECKBOX
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTMANYCHECKBOX_JAKARTA = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_SELECTMANYCHECKBOX);

    /**
     * TagIdentifier for TAG_SELECTMANYLISTBOX
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTMANYLISTBOX = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_SELECTMANYLISTBOX);
    /**
     * Jakarta EE version of TagIdentifier for TAG_SELECTMANYLISTBOX
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTMANYLISTBOX_JAKARTA = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_SELECTMANYLISTBOX);

    /**
     * TagIdentifier for TAG_SELECTMANYMENU
     */
    final static  TagIdentifier TAG_IDENTIFIER_SELECTMANYMENU =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_SELECTMANYMENU);
    /**
     * Jakarta EE version of TagIdentifier for TAG_SELECTMANYMENU
     */
    final static  TagIdentifier TAG_IDENTIFIER_SELECTMANYMENU_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_SELECTMANYMENU);

    /**
     * TagIdentifier for TAG_SELECTONELISTBOX
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTONELISTBOX =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_SELECTONELISTBOX);
    /**
     * Jakarta EE version of TagIdentifier for TAG_SELECTONELISTBOX
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTONELISTBOX_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_SELECTONELISTBOX);
    
    /**
     * TagIdentifier for TAG_SELECTONEMENU
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTONEMENU =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_SELECTONEMENU);
    /**
     * Jakarta EE version of TagIdentifier for TAG_SELECTONEMENU
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTONEMENU_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_SELECTONEMENU);

    /**
     * TagIdentifier for TAG_SELECTONERADIO
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTONERADIO =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_SELECTONERADIO);
    /**
     * Jakarta EE version of TagIdentifier for TAG_SELECTONERADIO
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTONERADIO_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML_JAKARTA, TAG_SELECTONERADIO);

    /**
     * TagIdentifier for TAG_ACTIONLISTENER
     */
    final static TagIdentifier TAG_IDENTIFIER_ACTIONLISTENER = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_ACTIONLISTENER);
    /**
     * Jakarta EE version of TagIdentifier for TAG_ACTIONLISTENER
     */
    final static TagIdentifier TAG_IDENTIFIER_ACTIONLISTENER_JAKARTA = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JAKARTA, TAG_ACTIONLISTENER);

    /**
     * TagIdentifier for TAG_ATTRIBUTE
     */
    final static TagIdentifier TAG_IDENTIFIER_ATTRIBUTE = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_ATTRIBUTE);
    /**
     * Jakarta EE version of TagIdentifier for TAG_ATTRIBUTE
     */
    final static TagIdentifier TAG_IDENTIFIER_ATTRIBUTE_JAKARTA = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JAKARTA, TAG_ATTRIBUTE);

    /**
     * TagIdentifier for TAG_CONVERTDATETIME
     */
    final static TagIdentifier TAG_IDENTIFIER_CONVERTDATETIME = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_CONVERTDATETIME);
    /**
     * Jakarta EE version of TagIdentifier for TAG_CONVERTDATETIME
     */
    final static TagIdentifier TAG_IDENTIFIER_CONVERTDATETIME_JAKARTA = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JAKARTA, TAG_CONVERTDATETIME);

    /**
     * TagIdentifier for TAG_CONVERTNUMBER
     */
    final static TagIdentifier TAG_IDENTIFIER_CONVERTNUMBER = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_CONVERTNUMBER);
    /**
     * Jakarta EE version of TagIdentifier for TAG_CONVERTNUMBER
     */
    final static TagIdentifier TAG_IDENTIFIER_CONVERTNUMBER_JAKARTA = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JAKARTA, TAG_CONVERTNUMBER);

    /**
     * TagIdentifier for TAG_CONVERTER
     */
    final static TagIdentifier TAG_IDENTIFIER_CONVERTER = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_CONVERTER);
    /**
     * Jakarta EE version of TagIdentifier for TAG_CONVERTER
     */
    final static TagIdentifier TAG_IDENTIFIER_CONVERTER_JAKARTA = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JAKARTA, TAG_CONVERTER);

    /**
     * TagIdentifier for TAG_PARAM
     */
    final static TagIdentifier TAG_IDENTIFIER_PARAM =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_PARAM);
    /**
     * Jakarta EE version of TagIdentifier for TAG_PARAM
     */
    final static TagIdentifier TAG_IDENTIFIER_PARAM_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JAKARTA, TAG_PARAM);

    /**
     * TagIdentifier for TAG_PHASELISTENER
     */
    final static TagIdentifier TAG_IDENTIFIER_PHASELISTENER =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_PHASELISTENER);
    /**
     * Jakarta EE version of TagIdentifier for TAG_PHASELISTENER
     */
    final static TagIdentifier TAG_IDENTIFIER_PHASELISTENER_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JAKARTA, TAG_PHASELISTENER);

    /**
     * TagIdentifier for TAG_SELECTITEM
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTITEM = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_SELECTITEM);
    /**
     * Jakarta EE version of TagIdentifier for TAG_SELECTITEM
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTITEM_JAKARTA = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JAKARTA, TAG_SELECTITEM);

    /**
     * TagIdentifier for TAG_SELECTITEMS
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTITEMS =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_SELECTITEMS);
    /**
     * Jakarta EE version of TagIdentifier for TAG_SELECTITEMS
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTITEMS_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JAKARTA, TAG_SELECTITEMS);

    /**
     * TagIdentifier for TAG_SETPROPERTYACTIONLISTENER
     */
    final static TagIdentifier TAG_IDENTIFIER_SETPROPERTYACTIONLISTENER =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_SETPROPERTYACTIONLISTENER);
    /**
     * Jakarta EE version of TagIdentifier for TAG_SETPROPERTYACTIONLISTENER
     */
    final static TagIdentifier TAG_IDENTIFIER_SETPROPERTYACTIONLISTENER_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JAKARTA, TAG_SETPROPERTYACTIONLISTENER);

    /**
     * TagIdentifier for TAG_SUBVIEW
     */
    final static TagIdentifier TAG_IDENTIFIER_SUBVIEW =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_SUBVIEW);

    /**
     * TagIdentifier for TAG_VALIDATEBEAN
     */
    final static TagIdentifier TAG_IDENTIFIER_VALIDATEBEAN =
    	TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_VALIDATEBEAN);
    /**
     * Jakarta EE version of TagIdentifier for TAG_VALIDATEBEAN
     */
    final static TagIdentifier TAG_IDENTIFIER_VALIDATEBEAN_JAKARTA =
    	TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JAKARTA, TAG_VALIDATEBEAN);

    /**
     * TagIdentifier for TAG_VALIDATEDOUBLERANGE
     */
    final static TagIdentifier TAG_IDENTIFIER_VALIDATEDOUBLERANGE =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_VALIDATEDOUBLERANGE);
    /**
     * Jakarta EE version of TagIdentifier for TAG_VALIDATEDOUBLERANGE
     */
    final static TagIdentifier TAG_IDENTIFIER_VALIDATEDOUBLERANGE_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JAKARTA, TAG_VALIDATEDOUBLERANGE);

    /**
     * TagIdentifier for TAG_VALIDATELENGTH
     */
    final static TagIdentifier TAG_IDENTIFIER_VALIDATELENGTH =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_VALIDATELENGTH);
    /**
     * Jakarta EE version of TagIdentifier for TAG_VALIDATELENGTH
     */
    final static TagIdentifier TAG_IDENTIFIER_VALIDATELENGTH_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JAKARTA, TAG_VALIDATELENGTH);

    /**
     * TagIdentifier for TAG_VALIDATELONGRANGE
     */
    final static TagIdentifier TAG_IDENTIFIER_VALIDATELONGRANGE =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_VALIDATELONGRANGE);
    /**
     * Jakarta EE version of TagIdentifier for TAG_VALIDATELONGRANGE
     */
    final static TagIdentifier TAG_IDENTIFIER_VALIDATELONGRANGE_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JAKARTA, TAG_VALIDATELONGRANGE);

    /**
     * TagIdentifier for TAG_VALIDATEREGEX
     */
    final static TagIdentifier TAG_IDENTIFIER_VALIDATEREGEX =
    	TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_VALIDATEREGEX);
    /**
     * Jakarta EE version of TagIdentifier for TAG_VALIDATEREGEX
     */
    final static TagIdentifier TAG_IDENTIFIER_VALIDATEREGEX_JAKARTA =
    	TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JAKARTA, TAG_VALIDATEREGEX);

    /**
     * TagIdentifier for TAG_VALIDATEREQUIRED
     */
    final static TagIdentifier TAG_IDENTIFIER_VALIDATEREQUIRED =
    	TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_VALIDATEREQUIRED);
    /**
     * Jakarta EE version of TagIdentifier for TAG_VALIDATEREQUIRED
     */
    final static TagIdentifier TAG_IDENTIFIER_VALIDATEREQUIRED_JAKARTA =
    	TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JAKARTA, TAG_VALIDATEREQUIRED);

    /**
     * TagIdentifier for TAG_VALIDATOR
     */
    final static TagIdentifier TAG_IDENTIFIER_VALIDATOR =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_VALIDATOR);
    /**
     * Jakarta EE version of TagIdentifier for TAG_VALIDATOR
     */
    final static TagIdentifier TAG_IDENTIFIER_VALIDATOR_JAKARTA =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JAKARTA, TAG_VALIDATOR);

    /**
     * TagIdentifier for TAG_VALUECHANGELISTENER
     */
    final static TagIdentifier TAG_IDENTIFIER_VALUECHANGELISTENER = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_VALUECHANGELISTENER);
    /**
     * Jakarta EE version of TagIdentifier for TAG_VALUECHANGELISTENER
     */
    final static TagIdentifier TAG_IDENTIFIER_VALUECHANGELISTENER_JAKARTA = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE_JAKARTA, TAG_VALUECHANGELISTENER);

    // attribute names
	/**
	 * accept tag attribute name
	 */
	final static public String ATTR_ACCEPT = "accept"; //$NON-NLS-1$

	/**
	 * acceptcharset tag attribute name
	 */
	final static public String ATTR_ACCEPTCHARSET = "acceptcharset"; //$NON-NLS-1$

	/**
	 * accesskey tag attribute name
	 */
	final static public String ATTR_ACCESSKEY = "accesskey"; //$NON-NLS-1$

	/**
	 * action tag attribute name
	 */
	final static public String ATTR_ACTION = "action"; //$NON-NLS-1$

	/**
	 * actionListener tag attribute name
	 */
	final static public String ATTR_ACTIONLISTENER = "actionListener"; //$NON-NLS-1$

	/**
	 * alt tag attribute name
	 */
	final static public String ATTR_ALT = "alt"; //$NON-NLS-1$

	/**
	 * basename tag attribute name
	 */
	final static public String ATTR_BASENAME = "basename"; //$NON-NLS-1$

	/**
	 * bgcolor tag attribute name
	 */
	final static public String ATTR_BGCOLOR = "bgcolor"; //$NON-NLS-1$

	/**
	 * binding tag attribute name
	 */
	final static public String ATTR_BINDING = "binding"; //$NON-NLS-1$

	/**
	 * border tag attribute name
	 */
	final static public String ATTR_BORDER = "border"; //$NON-NLS-1$

	/**
	 * cellpadding tag attribute name
	 */
	final static public String ATTR_CELLPADDING = "cellpadding"; //$NON-NLS-1$

	/**
	 * cellspacing tag attribute name
	 */
	final static public String ATTR_CELLSPACING = "cellspacing"; //$NON-NLS-1$

	/**
	 * charset tag attribute name
	 */
	final static public String ATTR_CHARSET = "charset"; //$NON-NLS-1$

	/**
	 * cols tag attribute name
	 */
	final static public String ATTR_COLS = "cols"; //$NON-NLS-1$

	/**
	 * columnClasses tag attribute name
	 */
	final static public String ATTR_COLUMNCLASSES = "columnClasses"; //$NON-NLS-1$

	/**
	 * columns tag attribute name
	 */
	final static public String ATTR_COLUMNS = "columns"; //$NON-NLS-1$

	/**
	 * converterId tag attribute name
	 */
	final static public String ATTR_CONVERTERID = "converterId"; //$NON-NLS-1$

	/**
	 * coords tag attribute name
	 */
	final static public String ATTR_COORDS = "coords"; //$NON-NLS-1$

	/**
	 * currencyCode tag attribute name
	 */
	final static public String ATTR_CURRENCYCODE = "currencyCode"; //$NON-NLS-1$

	/**
	 * currencySymbol tag attribute name
	 */
	final static public String ATTR_CURRENCYSYMBOL = "currencySymbol"; //$NON-NLS-1$

	/**
	 * dateStyle tag attribute name
	 */
	final static public String ATTR_DATESTYLE = "dateStyle"; //$NON-NLS-1$

	/**
	 * dir tag attribute name
	 */
	final static public String ATTR_DIR = "dir"; //$NON-NLS-1$

	/**
	 * disabled tag attribute name
	 */
	final static public String ATTR_DISABLED = "disabled"; //$NON-NLS-1$

	/**
	 * errorClass tag attribute name
	 */
	final static public String ATTR_ERRORCLASS = "errorClass"; //$NON-NLS-1$

	/**
	 * errorStyle tag attribute name
	 */
	final static public String ATTR_ERRORSTYLE = "errorStyle"; //$NON-NLS-1$

	/**
	 * escape tag attribute name
	 */
	final static public String ATTR_ESCAPE = "escape"; //$NON-NLS-1$

	/**
	 * fatalClass tag attribute name
	 */
	final static public String ATTR_FATALCLASS = "fatalClass"; //$NON-NLS-1$

	/**
	 * fatalStyle tag attribute name
	 */
	final static public String ATTR_FATALSTYLE = "fatalStyle"; //$NON-NLS-1$

	/**
	 * first tag attribute name
	 */
	final static public String ATTR_FIRST = "first"; //$NON-NLS-1$

	/**
	 * footerClass tag attribute name
	 */
	final static public String ATTR_FOOTERCLASS = "footerClass"; //$NON-NLS-1$

	/**
	 * for tag attribute name
	 */
	final static public String ATTR_FOR = "for"; //$NON-NLS-1$

	/**
	 * frame tag attribute name
	 */
	final static public String ATTR_FRAME = "frame"; //$NON-NLS-1$

	/**
	 * globalOnly tag attribute name
	 */
	final static public String ATTR_GLOBEONLY = "globalOnly"; //$NON-NLS-1$

	/**
	 * headerClass tag attribute name
	 */
	final static public String ATTR_HEADERCLASS = "headerClass"; //$NON-NLS-1$

	/**
	 * hreflang tag attribute name
	 */
	final static public String ATTR_HREFLANG = "hreflang"; //$NON-NLS-1$

	/**
	 * id tag attribute name
	 */
	final static public String ATTR_ID = "id"; //$NON-NLS-1$

	/**
	 * image tag attribute name
	 */
	final static public String ATTR_IMAGE = "image"; //$NON-NLS-1$

	/**
	 * immediate tag attribute name
	 */
	final static public String ATTR_IMMEDIATE = "immediate"; //$NON-NLS-1$

	/**
	 * infoClass tag attribute name
	 */
	final static public String ATTR_INFOCLASS = "infoClass"; //$NON-NLS-1$

	/**
	 * infoStyle tag attribute name
	 */
	final static public String ATTR_INFOSTYLE = "infoStyle"; //$NON-NLS-1$

	/**
	 * itemDescription tag attribute name
	 */
	final static public String ATTR_ITEMDESCRIPTION = "itemDescription"; //$NON-NLS-1$

	/**
	 * itemDisabled tag attribute name
	 */
	final static public String ATTR_ITEMDISABLED = "itemDisabled"; //$NON-NLS-1$

	/**
	 * itemLabel tag attribute name
	 */
	final static public String ATTR_ITEMLABEL = "itemLabel"; //$NON-NLS-1$

	/**
	 * itemValue tag attribute name
	 */
	final static public String ATTR_ITEMVALUE = "itemValue"; //$NON-NLS-1$

	/**
	 * lang tag attribute name
	 */
	final static public String ATTR_LANG = "lang"; //$NON-NLS-1$

	/**
	 * layout tag attribute name
	 */
	final static public String ATTR_LAYOUT = "layout"; //$NON-NLS-1$

	/**
	 * locale tag attribute name
	 */
	final static public String ATTR_LOCALE = "locale"; //$NON-NLS-1$

	/**
	 * maximum tag attribute name
	 */
	final static public String ATTR_MAXIMUM = "maximum"; //$NON-NLS-1$

	/**
	 * minimum tag attribute name
	 */
	final static public String ATTR_MINIMUM = "minimum"; //$NON-NLS-1$

	/**
	 * name tag attribute name
	 */
	final static public String ATTR_NAME = "name"; //$NON-NLS-1$

	/**
	 * onblur tag attribute name
	 */
	final static public String ATTR_ONBLUR = "onblur"; //$NON-NLS-1$

	/**
	 * onchange tag attribute name
	 */
	final static public String ATTR_ONCHANGE = "onchange"; //$NON-NLS-1$

	/**
	 * onclick tag attribute name
	 */
	final static public String ATTR_ONCLICK = "onclick"; //$NON-NLS-1$

	/**
	 * ondblclick tag attribute name
	 */
	final static public String ATTR_ONDBLCLICK = "ondblclick"; //$NON-NLS-1$

	/**
	 * onfocus tag attribute name
	 */
	final static public String ATTR_ONFOCUS = "onfocus"; //$NON-NLS-1$

	/**
	 * onkeydown tag attribute name
	 */
	final static public String ATTR_ONKEYDOWN = "onkeydown"; //$NON-NLS-1$

	/**
	 * onkeypress tag attribute name
	 */
	final static public String ATTR_ONKEYPRESS = "onkeypress"; //$NON-NLS-1$

	/**
	 * onkeyup tag attribute name
	 */
	final static public String ATTR_ONKEYUP = "onkeyup"; //$NON-NLS-1$

	/**
	 * onmousedown tag attribute name
	 */
	final static public String ATTR_ONMOUSEDOWN = "onmousedown"; //$NON-NLS-1$

	/**
	 * onmousemove tag attribute name
	 */
	final static public String ATTR_ONMOUSEMOVE = "onmousemove"; //$NON-NLS-1$

	/**
	 * onmouseout tag attribute name
	 */
	final static public String ATTR_ONMOUSEOUT = "onmouseout"; //$NON-NLS-1$

	/**
	 * onmouseover tag attribute name
	 */
	final static public String ATTR_ONMOUSEOVER = "onmouseover"; //$NON-NLS-1$

	/**
	 * onmouseup tag attribute name
	 */
	final static public String ATTR_ONMOUSEUP = "onmouseup"; //$NON-NLS-1$

	/**
	 * onselect tag attribute name
	 */
	final static public String ATTR_ONSELECT = "onselect"; //$NON-NLS-1$

	/**
	 * pattern tag attribute name
	 */
	final static public String ATTR_PATTERN = "pattern"; //$NON-NLS-1$

	/**
	 * readonly tag attribute name
	 */
	final static public String ATTR_READONLY = "readonly"; //$NON-NLS-1$

	/**
	 * rel tag attribute name
	 */
	final static public String ATTR_REL = "rel"; //$NON-NLS-1$

	/**
	 * rendered tag attribute name
	 */
	final static public String ATTR_RENDERED = "rendered"; //$NON-NLS-1$

	/**
	 * rev tag attribute name
	 */
	final static public String ATTR_REV = "rev"; //$NON-NLS-1$

	/**
	 * rowClasses tag attribute name
	 */
	final static public String ATTR_ROWCLASSES = "rowClasses"; //$NON-NLS-1$

	/**
	 * rows tag attribute name
	 */
	final static public String ATTR_ROWS = "rows"; //$NON-NLS-1$

	/**
	 * rules tag attribute name
	 */
	final static public String ATTR_RULES = "rules"; //$NON-NLS-1$

	/**
	 * shape tag attribute name
	 */
	final static public String ATTR_SHAPE = "shape"; //$NON-NLS-1$

	/**
	 *  showDetail tag attribute name
	 */
	final static public String ATTR_SHOWDETAIL = "showDetail"; //$NON-NLS-1$

	/**
	 * showSummary tag attribute name
	 */
	final static public String ATTR_SHOWSUMMARY = "showSummary"; //$NON-NLS-1$

	/**
	 * size tag attribute name
	 */
	final static public String ATTR_SIZE = "size"; //$NON-NLS-1$

	/**
	 * style tag attribute name
	 */
	final static public String ATTR_STYLE = "style"; //$NON-NLS-1$

	/**
	 * styleClass tag attribute name
	 */
	final static public String ATTR_STYLECLASS = "styleClass"; //$NON-NLS-1$

	/**
	 * summary tag attribute name
	 */
	final static public String ATTR_SUMMARY = "summary"; //$NON-NLS-1$

	/**
	 * tabindex tag attribute name
	 */
	final static public String ATTR_TABINDEX = "tabindex"; //$NON-NLS-1$

	/**
	 * target tag attribute name
	 */
	final static public String ATTR_TARGET = "target"; //$NON-NLS-1$

	/**
	 * timeStyle tag attribute name
	 */
	final static public String ATTR_TIMESTYLE = "timeStyle"; //$NON-NLS-1$

	/**
	 * title tag attribute name
	 */
	final static public String ATTR_TITLE = "title"; //$NON-NLS-1$

	/**
	 * tooltip tag attribute name
	 */
	final static public String ATTR_TOOLTIP = "tooltip"; //$NON-NLS-1$

	/**
	 * type tag attribute name
	 */
	final static public String ATTR_TYPE = "type"; //$NON-NLS-1$

	/**
	 * url tag attribute name
	 */
	final static public String ATTR_URL = "url"; //$NON-NLS-1$

	/**
	 * validatorId tag attribute name
	 */
	final static public String ATTR_VALIDATORID = "validatorId"; //$NON-NLS-1$

	/**
	 * value tag attribute name
	 */
	final static public String ATTR_VALUE = "value"; //$NON-NLS-1$

	/**
	 * var tag attribute name
	 */
	final static public String ATTR_VAR = "var"; //$NON-NLS-1$

	/**
	 * warnClass tag attribute name
	 */
	final static public String ATTR_WARNCLASS = "warnClass"; //$NON-NLS-1$

	/**
	 * warnStyle tag attribute name
	 */
	final static public String ATTR_WARNSTYLE = "warnStyle"; //$NON-NLS-1$

	/**
	 * width tag attribute name
	 */
	final static public String ATTR_WIDTH = "width"; //$NON-NLS-1$

}
