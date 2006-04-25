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

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.command;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jst.jsf.facesconfig.ui.EditorResources;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLinkBendpoint;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowFactory;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;

/**
 * Move bend point command
 */
public class MoveBendpointCommand extends BendpointCommand {
	/**
	 * old bend point of the link
	 */
	private PFLinkBendpoint oldBendpoint;

	public MoveBendpointCommand() {
		// Pageflow.Commands.MoveBendpointCommand.Label = Move Bendpoint
		super(EditorResources.getInstance().getString(
				"Pageflow.Commands.MoveBendpointCommand.Label"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#execute()
	 */
	public void execute() {
		Map registry = EPackage.Registry.INSTANCE;
		String pageflowURI = PageflowPackage.eNS_URI;
		PageflowPackage pageflowPackage = (PageflowPackage) registry
				.get(pageflowURI);
		PageflowFactory factory = pageflowPackage.getPageflowFactory();
		PFLinkBendpoint _newBendpoint = factory.createPFLinkBendpoint();

		_newBendpoint.setRelativeDimensions(getFirstRelativeDimension(),
				getSecondRelativeDimension());
		setOldBendpoint((PFLinkBendpoint) getPFLink().getBendPoints().get(
				getIndex()));

		getPFLink().setBendpoint(getIndex(), _newBendpoint);
		super.execute();
	}

	/**
	 * get the old bend point
	 * 
	 * @return - old bend point
	 */
	protected PFLinkBendpoint getOldBendpoint() {
		return oldBendpoint;
	}

	/**
	 * set the old bend point
	 * 
	 * @param bp -
	 *            old bend point
	 */
	public void setOldBendpoint(PFLinkBendpoint bp) {
		oldBendpoint = bp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#undo()
	 */
	public void undo() {
		super.undo();
		getPFLink().setBendpoint(getIndex(), getOldBendpoint());
	}

}
