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
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.commands.DesignerCommand;
import org.eclipse.jst.pagedesigner.jsf.core.dom.JSFDOMUtil;
import org.eclipse.jst.pagedesigner.utils.JSFUtil;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Insert a column into dataTable.
 * 
 * @author mengbo
 * @version 1.5
 */
public class DataTableInsertColumnCommand extends DesignerCommand
{
    Element _dataTable;
    Element _child;
    int     _index;

    /**
     * 
     * @param viewer
     * @param dataTable
     * @param index 0 means before the first column.
     */
    public DataTableInsertColumnCommand(IHTMLGraphicalViewer viewer, Element dataTable, int index)
    {
        super(CommandResources.getString("DataTableInsertColumnCommand.Label.InsertColumn"), viewer); //$NON-NLS-1$
        this._dataTable = dataTable;
        this._index = index;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute()
    {
        if (this._index < 0)
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
        String prefix = JSFUtil.getOrCreatePrefix(getModel(), ITLDConstants.URI_JSF_HTML, "h"); //$NON-NLS-1$
        _child = _dataTable.getOwnerDocument().createElement(prefix + ":column"); //$NON-NLS-1$

        createHeaderFooter(_child);
        NodeList children = _dataTable.getChildNodes();
        int count = 0;
        for (int i = 0, size = children.getLength(); i < size; i++)
        {
            Node node = children.item(i);
            if (JSFDOMUtil.isHColumn(node))
            {
                if (_index == count)
                {
                    _dataTable.insertBefore(_child, node);
                    return;
                }
                count++;
            }
        }
        _dataTable.insertBefore(_child, null);
        formatNode(this._child);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#getAfterCommandDesignerSelection()
     */
    protected ISelection getAfterCommandDesignerSelection()
    {
        return toDesignSelection(_dataTable);
    }

    /**
     * check whether the datatable already have header and footer, if yes,
     * then also create for this column
     * 
     * @param column
     */
    private void createHeaderFooter(Element column)
    {
        boolean hasHeader = false;
        boolean hasFooter = false;
        NodeList children = _dataTable.getChildNodes();
        for (int i = 0, size = children.getLength(); i < size; i++)
        {
            Node node = children.item(i);
            if (JSFDOMUtil.isHColumn(node))
            {
                if (!hasHeader && JSFDOMUtil.findFacet((Element) node, "header") != null) //$NON-NLS-1$
                {
                    hasHeader = true;
                }
                if (!hasFooter && JSFDOMUtil.findFacet((Element) node, "footer") != null) //$NON-NLS-1$
                {
                    hasFooter = true;
                }
            }
        }
        if (hasHeader)
        {
            Element facet = createFacet();
            facet.setAttribute("name", "header"); //$NON-NLS-1$ //$NON-NLS-2$
            Element ele = createDefaultElement();
            facet.appendChild(ele);
            column.appendChild(facet);
        }
        if (hasFooter)
        {
            Element facet = createFacet();
            facet.setAttribute("name", "footer"); //$NON-NLS-1$ //$NON-NLS-2$
            Element ele = createDefaultElement();
            facet.appendChild(ele);
            column.appendChild(facet);
        }
    }

    /**
     * @return
     */
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
        ele.setAttribute(IJSFConstants.ATTR_VALUE, "Column"); //$NON-NLS-1$
        return ele;
    }

}
