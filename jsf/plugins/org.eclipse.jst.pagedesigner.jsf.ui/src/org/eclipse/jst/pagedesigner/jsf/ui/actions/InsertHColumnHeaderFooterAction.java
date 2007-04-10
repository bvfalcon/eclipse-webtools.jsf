/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http:// www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui.actions;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.jst.pagedesigner.jsf.ui.elementedit.request.InsertHColumnHeaderFooterRequest;


/**
 * @author mengbo
 * @version 1.5
 */
public class InsertHColumnHeaderFooterAction extends Action
{
    private Command _command;

    /**
     * @param text
     * @param editPart 
     * @param isHeader 
     */
    public InsertHColumnHeaderFooterAction(String text, EditPart editPart, boolean isHeader)
    {
        super(text);

        InsertHColumnHeaderFooterRequest req = new InsertHColumnHeaderFooterRequest(text, isHeader);
        this._command = editPart.getCommand(req);
        this.setEnabled(this._command != null && this._command.canExecute());
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run()
    {
        _command.execute();
    }
}
