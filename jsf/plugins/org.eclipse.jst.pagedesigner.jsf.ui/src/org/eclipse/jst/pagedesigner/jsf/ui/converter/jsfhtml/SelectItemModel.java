/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http:// https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui.converter.jsfhtml;

/**
 * This class emulate a jakarta.faces.model.SelectItem
 * 
 * @author mengbo
 * @version 1.5
 * @deprecated Use DTTagConverter meta-data instead
 */
public class SelectItemModel
{
    private String _description;
    private String _label;
    private String _itemValue;
    private String _value;
    private String _id;
    private boolean _disabled;
    
    /**
     * @return Returns the disabled.
     */
    public boolean isDisabled()
    {
        return _disabled;
    }
    
    /**
     * @param disabled The disabled to set.
     */
    public void setDisabled(boolean disabled)
    {
        this._disabled = disabled;
    }
    
    /**
     * Return a description of this item, for use in development tools.
     * @return the description
     */ 
    public String 	getDescription()
    {
        return _description;
    }

    /**
     * Return the label of this item, to be rendered visibly for the user.
     * @return the label for this item
     */
    public String 	getLabel()
    {
        return _label;
    }

    /**
	 * Set the description of this item, for use in development tools.
	 * @param description
	 */
    public void 	setDescription(java.lang.String description)
    {
        _description = description;
    }

    /**
	 * Set the label of this item, to be rendered visibly for the user.
	 * @param label
	 */
    public void 	setLabel(java.lang.String label)
    {
        this._label = label;
    }
    /**
     * @return the item value
     */
    public String getItemValue()
    {
        return _itemValue;
    }

    /**
     * @param itemValue
     */
    public void setItemValue(String itemValue)
    {
        this._itemValue = itemValue;
    }

    /**
     * @return the value
     */
    public String getValue()
    {
        return _value;
    }

    /**
     * @param value
     */
    public void setValue(String value)
    {
        this._value = value;
    }

    /**
     * @return the id
     */
    public String getId()
    {
        return _id;
    }

    /**
     * @param id
     */
    public void setId(String id)
    {
        _id = id;
    }

    /**
     * @return the display string
     */
    public String getDisplayString()
    {
        if (_label != null && _label.length() > 0)
        {
            return _label;
        }
        else if (_value != null && _value.length() > 0)
        {
            return _value;
        }
        else if (_description != null && _description.length() > 0)
        {
            return _description;
        }
        else if (_itemValue != null && _itemValue.length() > 0)
        {
            return _itemValue;
        }
        else
        {
            return "SelectItem"; //$NON-NLS-1$
        }
    }
}
