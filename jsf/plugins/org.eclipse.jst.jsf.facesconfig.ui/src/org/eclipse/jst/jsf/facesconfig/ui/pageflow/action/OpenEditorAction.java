/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.action;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jst.jsf.facesconfig.ui.EditorResources;
import org.eclipse.jst.jsf.facesconfig.ui.FacesConfigEditor;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFPage;
import org.eclipse.ui.IWorkbenchPart;

/**
 * 
 * This is the Action for opening a JSP page in the default JSP Editor from a
 * Pageflow Diagram
 * 
 */
public class OpenEditorAction extends SelectionAction {
	/** The name of the request */
	public static final String OPEN_EDITOR_REQUEST = EditorResources
			.getInstance().getString(
					"Pageflow.Commands.OpenEditorCommand.Label");

	/** The request */
	Request request;

	/**
	 * The constructor
	 * 
	 * @param part -
	 *            the IWorkbenchPart
	 */
	public OpenEditorAction(IWorkbenchPart part) {
		super(part);
		request = new Request(OPEN_EDITOR_REQUEST);
		// Pageflow.Action.OpenEditor.Label = Edit Page
		setText(EditorResources.getInstance().getString(
				"Pageflow.Action.OpenEditor.Label"));
		setId(FacesConfigEditor.EDITOR_ID);
		// Pageflow.Action.OpenEditor.ToolTip = Edit this page
		setToolTipText(EditorResources.getInstance().getString(
				"Pageflow.Action.OpenEditor.ToolTip"));
	}

	/**
	 * Determines if the action can be enabled
	 * 
	 * @return boolean - the enabled state
	 */
	protected boolean calculateEnabled() {
		return canPerformAction();
	}

	/**
	 * Determines if the action can be performed
	 * 
	 * @return boolean - the perform state
	 */
	private boolean canPerformAction() {
		if (getSelectedObjects().isEmpty()) {
			return false;
		}
		List parts = getSelectedObjects();
		for (int i = 0; i < parts.size(); i++) {
			Object o = parts.get(i);
			if (!(o instanceof EditPart)) {
				return false;
			}
			EditPart part = (EditPart) o;
			if (!(part.getModel() instanceof PFPage)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns the command for the action
	 * 
	 * @return Command - the action command
	 */
	private Command getCommand() {
		List editparts = getSelectedObjects();
		CompoundCommand cc = new CompoundCommand();
		cc.setDebugLabel(OPEN_EDITOR_REQUEST);
		for (int i = 0; i < editparts.size(); i++) {
			EditPart part = (EditPart) editparts.get(i);
			cc.add(part.getCommand(request));
		}
		return cc;
	}

	/**
	 * Runs the command
	 */
	public void run() {
		execute(getCommand());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#execute(org.eclipse.gef.commands.Command)
	 */
	protected void execute(Command command) {
		if (command == null || !command.canExecute()) {
			return;
		}
		command.execute();
	}
}
