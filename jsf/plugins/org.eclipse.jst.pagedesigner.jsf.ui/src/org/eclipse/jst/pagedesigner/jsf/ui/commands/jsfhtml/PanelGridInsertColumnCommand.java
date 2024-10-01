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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.commands.DesignerCommand;
import org.eclipse.jst.pagedesigner.jsf.core.dom.JSFDOMUtil;
import org.eclipse.jst.pagedesigner.jsf.ui.elementedit.util.PanelGridUtil;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.eclipse.jst.pagedesigner.utils.JSFUtil;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;

/**
 * @author mengbo
 * @version 1.5
 */
public class PanelGridInsertColumnCommand extends DesignerCommand
{
    private int     _columnIndex;
    private Element _panelGridEle;

    /**
     * construction
     * @param viewer
     * @param panelGrid
     * @param columnIndex
     */
    public PanelGridInsertColumnCommand(IHTMLGraphicalViewer viewer, Element panelGrid, int columnIndex)
    {
        super(CommandResources.getString("PanelGridInsertColumnCommand.Label.InsertColumn"), viewer); //$NON-NLS-1$
        this._panelGridEle = panelGrid;
        this._columnIndex = columnIndex;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute()
    {
        if (this._columnIndex < 0)
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
        int columns = DOMUtil.getIntAttributeIgnoreCase(_panelGridEle, IJSFConstants.ATTR_COLUMNS, 1);
        if (columns < 1)
        {
            columns = 1;
        }

        int startPoint = 0;
        if (_columnIndex > columns)
        {
            return;
        }
        else if (_columnIndex == columns)
        {
            startPoint = _columnIndex - 1;
        }
        else
        {
            startPoint = _columnIndex;
        }

        List children = JSFDOMUtil.getUIComponentChildren(_panelGridEle);

        List toMove = new ArrayList();
        for (int i = startPoint; i < children.size(); i += columns)
        {
            if (_columnIndex < columns)
            {
                toMove.add(children.get(i));
            }
            else
            {
                int tmp = i + 1;
                if (tmp == children.size())
                {
                    toMove.add(null);
                }
                else
                {
                    toMove.add(children.get(i + 1));
                }
            }
        }
        //        for (int i = 0, size = toMove.size(); i < size; i++)
        int lastRowCells = (children.size() % columns == 0) ? columns : (children.size() % columns);
        PanelGridUtil util = new PanelGridUtil(this._panelGridEle);
        int uiRows = util.getUIRowCount();
        int adds = uiRows;
        if (this._columnIndex > lastRowCells)
        {
            adds += this._columnIndex - lastRowCells;
        }
        for (int i = 0, size = adds; i < size; i++)
        {
            Node child = createDefaultNode();
            int toMoveSize = toMove.size();
            Node node = null;
            if (i < toMoveSize)
            {
                node = (Node) toMove.get(i);
            }
            _panelGridEle.insertBefore(child, node);
        }

        _panelGridEle.setAttribute(IJSFConstants.ATTR_COLUMNS, String.valueOf(columns + 1));
        formatNode(_panelGridEle);
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
