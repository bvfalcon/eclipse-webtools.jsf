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

import org.eclipse.jface.viewers.ISelection;
import org.w3c.dom.Element;

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.commands.DesignerCommand;
import org.eclipse.jst.pagedesigner.jsf.core.dom.JSFDOMUtil;
import org.eclipse.jst.pagedesigner.utils.JSFUtil;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;

/**
 * This command is used to insert a header row or footer row.
 * 
 * @author mengbo
 * @version 1.5
 */
public class DataTableInsertHeaderCommand extends DesignerCommand
{
    boolean         _header;   // true means header, false means footer.
    private Element _dataTable;

    /**
     * @param viewer
     * @param dataTable 
     * @param header 
     */
    public DataTableInsertHeaderCommand(IHTMLGraphicalViewer viewer, Element dataTable, boolean header)
    {
        super(header ? CommandResources.getString("DataTableInsertHeaderCommand.Label.InsertHeader") : CommandResources.getString("DataTableInsertHeaderCommand.Label.InsertFooter"), viewer); //$NON-NLS-1$ //$NON-NLS-2$
        this._header = header;
        this._dataTable = dataTable;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute()
    {
        if (this._header)
        {
            boolean hasHeader = (JSFDOMUtil.findFacet(this._dataTable, "header") != null); //$NON-NLS-1$
            if (hasHeader)
            {
                return false;
            }
        }
        else
        {
            boolean hasFooter = (JSFDOMUtil.findFacet(this._dataTable, "footer") != null); //$NON-NLS-1$
            if (hasFooter)
            {
                return false;
            }
        }
        return super.canExecute();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
     */
    protected void doExecute()
    {
        String type = (this._header) ? "header" : "footer"; //$NON-NLS-1$ //$NON-NLS-2$
        Element headerOrFooter = createHeaderOrFooter(type);
        if (this._header)
        {
            this._dataTable.insertBefore(headerOrFooter, this._dataTable.getFirstChild());
        }
        else
        {
            this._dataTable.appendChild(headerOrFooter);
        }
        formatNode(this._dataTable);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#getAfterCommandDesignerSelection()
     */
    protected ISelection getAfterCommandDesignerSelection()
    {
        return toDesignSelection(_dataTable);
    }

    private Element createHeaderOrFooter(String type)
    {
        Element facet = createFacet();
        Element def = createDefaultElement();
        facet.appendChild(def);
        facet.setAttribute(IJSFConstants.ATTR_NAME, type);
        return facet;
    }

    private Element createFacet()
    {
        String prefix = JSFUtil.getOrCreatePrefix(getModel(), ITLDConstants.URI_JSF_CORE, "f"); //$NON-NLS-1$
        Element ele = _dataTable.getOwnerDocument().createElement(IJSFConstants.TAG_FACET);
        ele.setPrefix(prefix);
        return ele;
    }

    private Element createDefaultElement()
    {
        String prefix = JSFUtil.getOrCreatePrefix(getModel(), ITLDConstants.URI_JSF_HTML, "h"); //$NON-NLS-1$
        Element ele = _dataTable.getOwnerDocument().createElement(IJSFConstants.TAG_OUTPUTTEXT);
        ele.setPrefix(prefix);
        if (this._header)
        {
            ele.setAttribute(IJSFConstants.ATTR_VALUE, "Table Header"); //$NON-NLS-1$
        }
        else
        {
            ele.setAttribute(IJSFConstants.ATTR_VALUE, "Table Footer"); //$NON-NLS-1$
        }
        return ele;
    }
}
