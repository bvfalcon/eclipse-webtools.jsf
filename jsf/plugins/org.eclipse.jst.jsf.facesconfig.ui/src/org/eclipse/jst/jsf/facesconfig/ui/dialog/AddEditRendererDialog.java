/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.dialog;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.ClassButtonDialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.IStringButtonAdapter;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.LayoutUtil;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.StringButtonDialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.StringDialogField;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.facesconfig.ui.EditorMessages;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.IFacesConfigConstants;
import org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

/**
 * @author Bryan Yang
 * 
 */
public class AddEditRendererDialog extends Dialog {

	private static final int MIN_DIALOG_WIDTH = 300;

	private StringDialogField rendererNameField;

	private ClassButtonDialogField rendererClassField;

	private StringButtonDialogField componentFamilyField;

	private StringDialogField renderTypeField;

	private IProject project;

	private String rendererName;

	private String rendererClass;

	private String componentFamily;

	private String rendererType;

	private boolean isNew;

	private IFacesConfigPage page;

	/**
	 * @param parentShell
	 * @param page
	 * @param isNew
	 */
	public AddEditRendererDialog(Shell parentShell, IFacesConfigPage page,
			boolean isNew) {
		super(parentShell);
		this.page = page;
		this.isNew = isNew;
	}

	/**
	 * 
	 * @param parentShell
	 * @param rendererName 
	 * @param rendererClass 
	 * @param componentFamily 
	 * @param renderType 
	 */
	public AddEditRendererDialog(Shell parentShell, String rendererName,
			String rendererClass, String componentFamily, String renderType) {
		super(parentShell);
		this.rendererName = rendererName;
		this.rendererClass = rendererClass;
		this.componentFamily = componentFamily;
		this.rendererType = renderType;
		this.isNew = false;
	}

	/*
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		if (isNew)
			newShell.setText(EditorMessages.RendererSection_Dialog_Title_Add);
		else
			newShell.setText(EditorMessages.RendererSection_Dialog_Title_Edit);
	}

	/*
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		Composite container = new Composite(parent, SWT.FILL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = layout.marginHeight = 5;
		container.setLayout(layout);
		GridData gd = new GridData(GridData.FILL_BOTH);
		container.setLayoutData(gd);

		TabFolder tabFolder = new TabFolder(container, SWT.FILL);
		tabFolder.setLayoutData(gd);
		TabItem generalTabItem = new TabItem(tabFolder, SWT.NONE);
		generalTabItem
				.setText(EditorMessages.RendererSection_Dialog_Tab_General);
		Control control = createGeneralTabControl(generalTabItem.getParent());
		generalTabItem.setControl(control);
		return container;
	}

	/**
	 * @param parent
	 * @return
	 */
	private Control createGeneralTabControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 5;
		composite.setLayout(layout);
		GridData gd = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(gd);
		composite.setFont(parent.getFont());

		rendererNameField = new StringDialogField();
		rendererNameField
				.setLabelText(EditorMessages.RendererSection_Dialog_DisplayName);

		rendererClassField = new ClassButtonDialogField(getProject());
//		rendererClassField.setRequired(true);
		rendererClassField
				.setLabelText(EditorMessages.RendererSection_Dialog_RendererClass);
		rendererClassField.setProject(getProject());
		rendererClassField.setSuperClassName(
				JSFVersion.guessAtLeast(JSFVersion.V3_0, getProject()) ?
						IFacesConfigConstants.RENDERER_SUPER_CLASS_JAKARTA : 
							IFacesConfigConstants.RENDERER_SUPER_CLASS);
		rendererClassField.setAutoOpenResource(false);

		componentFamilyField = new StringButtonDialogField(
				new IStringButtonAdapter() {
					public void changeControlPressed(DialogField field) {
						Shell shell = EditorPlugin.getActiveShell();
						ComponentListDialog dialog = new ComponentListDialog(
								shell,
								page,
								page.getInput(),
								EditorMessages.RendererSection_Dialog_ComponentFamilyCaption,
								EditorMessages.RendererSection_Dialog_ComponentFamilyLabel);
						if (dialog.open() == Dialog.OK) {
							componentFamilyField.setText(dialog.getValue());
						}
					}
				});
		componentFamilyField
				.setLabelText(EditorMessages.RendererSection_Dialog_ComponentFamilyValue);
//		componentFamilyField.setRequired(true);

		renderTypeField = new StringDialogField();
//		renderTypeField.setRequired(true);
		renderTypeField
				.setLabelText(EditorMessages.RendererSection_Dialog_TypeValue);

		GridData data = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(data);

		int numberOfColumns = 4;
		GridLayout gl = new GridLayout(numberOfColumns, false);
		composite.setLayout(gl);

		rendererNameField.doFillIntoGrid(null, composite, numberOfColumns);

		componentFamilyField.doFillIntoGrid(null, composite, numberOfColumns);

		renderTypeField.doFillIntoGrid(null, composite, numberOfColumns);

		rendererClassField.doFillIntoGrid(null, composite, numberOfColumns);

		LayoutUtil.setHorizontalGrabbing(rendererClassField.getTextControl(
				null, composite));

		// set the initial value for these fields.
		rendererNameField.setText(rendererName);
		rendererClassField.setText(rendererClass);
		componentFamilyField.setText(componentFamily);
		renderTypeField.setText(rendererType);

		return composite;
	}

	/*
	 * @see org.eclipse.jface.window.Window#getInitialSize()
	 */
	protected Point getInitialSize() {
		Point shellSize = super.getInitialSize();
		return new Point(Math.max(
				convertHorizontalDLUsToPixels(MIN_DIALOG_WIDTH), shellSize.x),
				shellSize.y);
	}

	/**
	 * @return the associated project
	 */
	public IProject getProject() {
		return project;
	}

	/**
	 * 
	 */
	protected void okPressed() {
		rendererName = rendererNameField.getText();
		rendererClass = rendererClassField.getText();
		componentFamily = componentFamilyField.getText();
		rendererType = renderTypeField.getText();

		super.okPressed();
	}

	/**
	 * @return the renderer clas
	 */
	public String getRendererClass() {
		return rendererClass;
	}

	/**
	 * @param rendererClass
	 */
	public void setRendererClass(String rendererClass) {
		this.rendererClass = rendererClass;
	}

	/**
	 * @return the renderer name
	 */
	public String getRendererName() {
		return rendererName;
	}

	/**
	 * @param rendererName
	 */
	public void setRendererName(String rendererName) {
		this.rendererName = rendererName;
	}

	/**
	 * @return the component family
	 */
	public String getComponentFamily() {
		return componentFamily;
	}

	/**
	 * @param componentFamily
	 */
	public void setComponentFamily(String componentFamily) {
		this.componentFamily = componentFamily;
	}

	/**
	 * @return the renderer type
	 */
	public String getRendererType() {
		return rendererType;
	}

	/**
	 * @param rendererType
	 */
	public void setRendererType(String rendererType) {
		this.rendererType = rendererType;
	}

	/**
	 * @param project
	 */
	public void setProject(IProject project) {
		this.project = project;
	}

}
