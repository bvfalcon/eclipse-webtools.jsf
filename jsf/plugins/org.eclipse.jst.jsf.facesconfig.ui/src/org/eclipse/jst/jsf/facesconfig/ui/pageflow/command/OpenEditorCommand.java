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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.jst.jsf.facesconfig.common.logging.Logger;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.EditorResources;
import org.eclipse.jst.jsf.facesconfig.ui.FacesConfigEditor;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowNodeImpl;
import org.eclipse.jst.jsf.facesconfig.ui.util.WebrootUtil;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * 
 * This is the Command for opening a file in its default editor
 * 
 * @author Xiao-guang Zhang
 */
public class OpenEditorCommand extends org.eclipse.gef.commands.Command {
	/** The selected object */
	private PageflowNodeImpl child = null;

	/** The edit part */
	private EditPart part;

	/** log instance */
	private static final Logger log = EditorPlugin
			.getLogger(FacesConfigEditor.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#canExecute()
	 */
	public boolean canExecute() {
		return true;
	}

	/**
	 * The constructor
	 * 
	 * @param part -
	 *            the EditPart
	 */
	public OpenEditorCommand(EditPart part) {
		// Pageflow.Commands.OpenEditorCommand.Label = Open Editor
		super(EditorResources.getInstance().getString(
				"Pageflow.Commands.OpenEditorCommand.Label"));
		this.part = part;
	}

	/**
	 * Sets the selected object
	 * 
	 * @param child -
	 *            the PFPageImpl
	 */
	public void setChild(PageflowNodeImpl child) {
		this.child = child;
	}

	public PageflowNodeImpl getChild() {
		return child;
	}

	/**
	 * open existed JSF file or call the wizard to create a new one.
	 * 
	 * @param void
	 */
	private void executeOpenPFPage() {
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		String pagePath = ((PageflowPage) (part.getModel())).getPath();

		// Check the fileName is empty or not
		if (pagePath != null && pagePath.length() > 0) {
			String resourceName = WebrootUtil.getProjectPath((EObject) part
					.getModel(), pagePath);
			Path resourcePath = new Path(resourceName);
			if (resourcePath.getFileExtension() != null
					&& WebrootUtil.isValidWebFile(resourcePath)) {

				final IFile file = (IFile) workspaceRoot
						.findMember(resourcePath);
				openExistingJSFFile(file);
			} else {
				// Pageflow.PageflowEditor.Alert.errorOpenEditor4JSFTitle = Open
				// JSF File Error
				// Pageflow.PageflowEditor.Alert.errorOpenEditor4JSFFolderInfo =
				// This page is related with JSF folder. CAN'T be edited.
				EditorPlugin
						.getAlerts()
						.error(
								"Pageflow.PageflowEditor.Alert.errorOpenEditor4JSFTitle",
								"Pageflow.PageflowEditor.Alert.errorOpenEditor4JSFFolderInfo");
			}
		} else
		// if the fileName is empty, a new jsf file should be created!
		{
			// Pageflow.PageflowEditor.Alert.errorOpenEditor4JSFTitle = Open JSF
			// File Error
			// Pageflow.PageflowEditor.Alert.confirmCreateNewJSFFile = No jsp
			// file is related with this page.
			EditorPlugin.getAlerts().error(
					"Pageflow.PageflowEditor.Alert.errorOpenEditor4JSFTitle",
					"Pageflow.PageflowEditor.Alert.confirmCreateNewJSFFile");// )
			// {
			// return;
			// }

			// createNewJSFFile();
		}
	}

	/**
	 * open existing jsf file in a new editor.
	 * 
	 * @param file
	 */
	private void openExistingJSFFile(final IFile file) {
		// if the file is existed, open it.
		if (null != file && file.exists()) {
			Display display = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getShell().getDisplay();
			display.asyncExec(new Runnable() {
				public void run() {
					IWorkbenchPage page = PlatformUI.getWorkbench()
							.getActiveWorkbenchWindow().getActivePage();
					try {
						IDE.openEditor(page, file, true);
					} catch (PartInitException e) {
						// Pageflow.PageflowEditor.Error.CanNotOpenEditor4JSF =
						// The jsf file can not be opened in the editor.
						log
								.error(
										"Pageflow.PageflowEditor.Error.canNotOpenEditor4JSF",
										e);
						// Pageflow.PageflowEditor.Alert.errorOpenEditor4JSFTitle
						// = Open JSF File Error
						EditorPlugin
								.getAlerts()
								.error(
										"Pageflow.PageflowEditor.Alert.errorOpenEditor4JSFTitle",
										"Pageflow.PageflowEditor.Error.CanNotOpenEditor4JSF");
					}
				}
			});
		} else
		// otherwise, pop-up a error message box
		{
			String pagePath = ((PageflowPage) (part.getModel())).getPath();
			// Pageflow.PageflowEditor.Alert.errorOpenEditor4JSFTitle = Open JSF
			// File Error
			// Pageflow.PageflowEditor.Alert.errorOpenEditor4JSFInfo = The JSF
			// file is not existed.
			EditorPlugin.getAlerts().error(
					"Pageflow.PageflowEditor.Alert.errorOpenEditor4JSFTitle",
					"Pageflow.PageflowEditor.Alert.errorOpenEditor4JSFInfo",
					pagePath);
		}
	}

	/**
	 * create a new jsf file and open it in a new editor
	 * 
	 */
	// private void createNewJSFFile() {
	// IStructuredSelection selectionToPass = StructuredSelection.EMPTY;
	// // Create a new jsf Wizard
	// NewJSPWizard jsfWizard = new NewJSPWizard();
	// jsfWizard.init(EditorPlugin.getDefault().getWorkbench(),
	// selectionToPass);
	// // using a wizard dialog to display the new jsf wizard
	// CommonWizardDialog jsfSelectionWizardDialog = new CommonWizardDialog(
	// EditorPlugin.getDefault().getWorkbench()
	// .getActiveWorkbenchWindow().getShell(), jsfWizard);
	// jsfSelectionWizardDialog.open();
	// if (jsfSelectionWizardDialog.open() == Window.OK)
	// {
	// IFile jsfFile = (IFile) jsfWizard.getNewObject();
	// if (jsfFile != null)
	// {
	// //get the project path for the new created file, i.e.,
	// /project/webroot/*.jsp
	// String jsfSelection = jsfFile.getFullPath().toString();
	// //get the web path for the project path, i.e, /*.jsp
	// jsfSelection = WebrootUtil.getWebPath(jsfSelection);
	// if (jsfSelection != null && jsfSelection.length() > 0)
	// {
	// ((PFPage) (part.getModel())).setPath(jsfSelection);
	// }
	// }
	// }
	// }
	/**
	 * Executes the OpenEditorCommand and opens the editor
	 */
	public void execute() {
		if (part.getModel() instanceof PageflowPage) {
			executeOpenPFPage();
		}

	}
}
