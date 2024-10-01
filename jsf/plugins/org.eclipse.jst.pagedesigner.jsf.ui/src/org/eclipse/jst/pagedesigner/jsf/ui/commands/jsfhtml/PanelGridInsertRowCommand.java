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

import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.commands.DesignerCommand;
import org.eclipse.jst.pagedesigner.jsf.core.dom.JSFDOMUtil;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.eclipse.jst.pagedesigner.utils.JSFUtil;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;

/**
 * @author mengbo
 * @version 1.5
 */
public class PanelGridInsertRowCommand extends DesignerCommand
{
    private int     _rowIndex;
    private Element _panelGridEle;

    /**
     * consturctor
     * @param viewer
     * @param panelGrid
     * @param rowIndex
     */
    public PanelGridInsertRowCommand(IHTMLGraphicalViewer viewer, Element panelGrid, int rowIndex)
    {
        super(CommandResources.getString("PanelGridInsertRowCommand.Label.InsertRow"), viewer); //$NON-NLS-1$
        this._panelGridEle = panelGrid;
        this._rowIndex = rowIndex;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute()
    {
        if (this._rowIndex < 0)
        {
            return false;
        }
        boolean hasHeaderRow = (JSFDOMUtil.findFacet(_panelGridEle, "header") != null); //$NON-NLS-1$
        //forbid insert row before header
        if (hasHeaderRow && _rowIndex == 0)
        {
            return false;
        }

        int offIndex = this._rowIndex;
        if (hasHeaderRow)
        {
            offIndex--;
        }

        int columns = DOMUtil.getIntAttributeIgnoreCase(_panelGridEle, IJSFConstants.ATTR_COLUMNS, 1);
        if (columns < 1)
        {
            columns = 1;
        }
        List children = JSFDOMUtil.getUIComponentChildren(_panelGridEle);
        int numRows = (children.size() + columns - 1) / columns;
        //        //if the last element row is not full,then forbid insert row after the row
        //        if ((children.size() % columns != 0) && (offIndex == numRows))
        //        {
        //            return false;
        //        }
        //forbid insert row after the footer row
        if (offIndex > numRows)
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
        boolean hasHeaderRow = (JSFDOMUtil.findFacet(_panelGridEle, "header") != null); //$NON-NLS-1$

        int columns = DOMUtil.getIntAttributeIgnoreCase(_panelGridEle, IJSFConstants.ATTR_COLUMNS, 1);
        if (columns < 1)
        {
            columns = 1;
        }

        List children = JSFDOMUtil.getUIComponentChildren(_panelGridEle);
        int numRows = (children.size() + columns - 1) / columns;

        int insertRow = _rowIndex;

        if (hasHeaderRow)
        {
            if (insertRow == 0)
            {
                return;
            }
            insertRow--;
        }
        //if (insertRow < numRows || ((insertRow == numRows) && (children.size() % columns == 0)))

        {
            int insertPoint = insertRow * columns;
            Node node = null;
            if (insertPoint < children.size())
            {
                node = (Node) children.get(insertPoint);
            }

            int adds = 0;
            //if insert after the last ui row
            if (insertRow >= numRows)
            {
                adds = columns * (numRows + 1) - children.size();
            }
            else
            {
                adds = columns;
            }

            for (int i = 0; i < adds; i++)
            {
                Node child = createDefaultNode();
                _panelGridEle.insertBefore(child, node);
            }
            formatNode(_panelGridEle);
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#getAfterCommandDesignerSelection()
     */
    protected ISelection getAfterCommandDesignerSelection()
    {
        return toDesignSelection(this._panelGridEle);
    }
    
    private Node createDefaultNode()
    {
        String prefix = JSFUtil.getOrCreatePrefix(getModel(), ITLDConstants.URI_JSF_HTML, "h"); //$NON-NLS-1$
        Element child = _panelGridEle.getOwnerDocument().createElement(IJSFConstants.TAG_OUTPUTTEXT);
        child.setPrefix(prefix);
        return child;
    }
}
