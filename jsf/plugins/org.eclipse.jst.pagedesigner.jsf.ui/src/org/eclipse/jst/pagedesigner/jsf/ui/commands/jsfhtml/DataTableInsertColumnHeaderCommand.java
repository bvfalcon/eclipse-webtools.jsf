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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.commands.DesignerCommand;
import org.eclipse.jst.pagedesigner.jsf.core.dom.JSFDOMUtil;
import org.eclipse.jst.pagedesigner.utils.JSFUtil;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;

/**
 * @author mengbo
 * @version 1.5
 */
public class DataTableInsertColumnHeaderCommand extends DesignerCommand
{
    boolean         _header;   // true means header, false means footer.
    private Element _dataTable;

    /**
     * @param viewer
     * @param dataTable 
     * @param header 
     */
    public DataTableInsertColumnHeaderCommand(IHTMLGraphicalViewer viewer, Element dataTable, boolean header)
    {
        super(header ? CommandResources.getString("DataTableInsertColumnHeaderCommand.Label.InsertHeader") : CommandResources.getString("DataTableInsertColumnHeaderCommand.Label.InsertFooter"), viewer); //$NON-NLS-1$ //$NON-NLS-2$
        this._header = header;
        this._dataTable = dataTable;
    }

    public boolean canExecute()
    {
        int num = 0;
        NodeList children = _dataTable.getChildNodes();
        for (int i = 0, size = children.getLength(); i < size; i++)
        {
            Node node = children.item(i);
            if (JSFDOMUtil.isHColumn(node))
            {
                Element column = (Element) node;
                if (!hasHeader(column))
                {
                    num++;
                    break;
                }
            }
        }
        if (num == 0)
        {
            return false;
        }
        return super.canExecute();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
     */
    protected void doExecute()
    {
        String type = (this._header) ? "header" : "footer"; //$NON-NLS-1$ //$NON-NLS-2$

        NodeList children = _dataTable.getChildNodes();
        for (int i = 0, size = children.getLength(); i < size; i++)
        {
            Node node = children.item(i);
            if (JSFDOMUtil.isHColumn(node))
            {
                Element column = (Element) node;
                if (!hasHeader(column))
                {
                    Element headerOrFooter = createHeaderOrFooter(type);
                    column.appendChild(headerOrFooter);
                }
            }
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

    /**
     * @param column
     * @param _header2
     * @return
     */
    private boolean hasHeader(Element column)
    {
        return JSFDOMUtil.findFacet(column, _header ? "header" : "footer") != null; //$NON-NLS-1$ //$NON-NLS-2$
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
            ele.setAttribute(IJSFConstants.ATTR_VALUE, "Column Header"); //$NON-NLS-1$
        }
        else
        {
            ele.setAttribute(IJSFConstants.ATTR_VALUE, "Column Footer"); //$NON-NLS-1$
        }

        return ele;
    }
}
