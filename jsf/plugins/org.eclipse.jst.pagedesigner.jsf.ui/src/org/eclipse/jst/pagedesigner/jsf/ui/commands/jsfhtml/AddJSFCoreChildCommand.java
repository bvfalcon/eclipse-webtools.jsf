/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
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
package org.eclipse.jst.pagedesigner.jsf.ui.commands.jsfhtml;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Element;

import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.commands.DesignerCommand;
import org.eclipse.jst.pagedesigner.utils.JSFUtil;

/**
 * @author mengbo
 * @version 1.5
 */
public class AddJSFCoreChildCommand extends DesignerCommand
{
    // either use single name/value pair
    private String     _attrName;
    private String     _attrValue;
    // or use a map.
    private Map        _attrMap;

    private String     _coreChildLocalName;
    private IDOMElement _parent;

    /**
     * @param parent 
     * @param coreChildLocalName 
     * @param attributes 
     */
    public AddJSFCoreChildCommand(IDOMElement parent, String coreChildLocalName, Map attributes)
    {
        super("", parent); //$NON-NLS-1$
        StringBuffer buffer = new StringBuffer(CommandResources.getString("AddJSFCoreChildCommand.Label.Add")); //$NON-NLS-1$
        if(coreChildLocalName != null && coreChildLocalName.length() > 0)
        {
            buffer.append(coreChildLocalName.substring(0,1).toUpperCase());
            buffer.append(coreChildLocalName.substring(1));
        }
        setLabel(buffer.toString());
        this._parent = parent;
        this._coreChildLocalName = coreChildLocalName;
        this._attrMap = attributes;
    }

    /**
     * @param parent 
     * @param coreChildLocalName 
     * @param aname 
     * @param avalue 
     */
    public AddJSFCoreChildCommand(IDOMElement parent, String coreChildLocalName, String aname, String avalue)
    {
        super("", parent); //$NON-NLS-1$
        StringBuffer buffer = new StringBuffer(CommandResources.getString("AddJSFCoreChildCommand.Label.Add")); //$NON-NLS-1$
        if(coreChildLocalName != null && coreChildLocalName.length() > 0)
        {
            buffer.append(coreChildLocalName.substring(0,1).toUpperCase());
            buffer.append(coreChildLocalName.substring(1));
        }
        setLabel(buffer.toString());
        this._parent = parent;
        this._coreChildLocalName = coreChildLocalName;
        this._attrName = aname;
        this._attrValue = avalue;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
     */
    protected void doExecute()
    {
        String prefix = JSFUtil.getOrCreatePrefix(this.getModel(), ITLDConstants.URI_JSF_CORE, "f"); //$NON-NLS-1$
        String tag = _coreChildLocalName;
        Element child = _parent.getOwnerDocument().createElement(tag);
        child.setPrefix(prefix);
        if (_attrMap != null)
        {
            for (Iterator iter = _attrMap.keySet().iterator(); iter.hasNext();)
            {
                String key = (String) iter.next();
                String value = (String) _attrMap.get(key);

                child.setAttribute(key, value);
            }
        }
        if (_attrName != null)
        {
            child.setAttribute(_attrName, _attrValue);
        }

        _parent.appendChild(child);
        formatNode(child);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#getAfterCommandDesignerSelection()
     */
    protected ISelection getAfterCommandDesignerSelection()
    {
        return toDesignSelection(_parent);
    }
}
