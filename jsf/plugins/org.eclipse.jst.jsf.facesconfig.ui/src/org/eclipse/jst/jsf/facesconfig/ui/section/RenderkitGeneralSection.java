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
package org.eclipse.jst.jsf.facesconfig.ui.section;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.ClassButtonDialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogFieldApplyListener;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.LayoutUtil;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.StringDialogField;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitClassType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitIdType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitType;
import org.eclipse.jst.jsf.facesconfig.ui.EditorMessages;
import org.eclipse.jst.jsf.facesconfig.ui.IFacesConfigConstants;
import org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage;
import org.eclipse.jst.jsf.facesconfig.ui.util.ModelUtil;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author Bryan Yang
 * 
 */
public class RenderkitGeneralSection extends AbstractFacesConfigSection {

	private StringDialogField displayNameField;

	private StringDialogField descriptionField;

	private StringDialogField renderkitIdField;

	private ClassButtonDialogField renderkitClassField;

	private RenderKitGeneralAdapter renderKitGeneralAdapter;

	/**
	 * 
	 * @param parent
	 * @param managedForm
	 * @param page
	 * @param toolkit
	 */
	public RenderkitGeneralSection(Composite parent, IManagedForm managedForm,
			IFacesConfigPage page, FormToolkit toolkit) {
		super(parent, managedForm, page, toolkit, null, null);
		getSection()
				.setText(EditorMessages.RenderKitGeneralSection_Name);
		getSection().setDescription(
				EditorMessages.RenderKitGeneralSection_Description);
	}

	/**
	 * 
	 */
	protected void createContents(Composite container, FormToolkit toolkit) {
		int numberOfColumns = 4;
		GridLayout layout = new GridLayout(numberOfColumns, false);
		container.setLayout(layout);
		GridData td = new GridData(GridData.FILL_HORIZONTAL);
		container.setLayoutData(td);

		toolkit.paintBordersFor(container);
		displayNameField = new StringDialogField();
		displayNameField
				.setLabelText(EditorMessages.RenderKitGeneralSection_Label_DisplayName);
		displayNameField.doFillIntoGrid(toolkit, container, numberOfColumns);

		displayNameField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {

					public void dialogFieldApplied(DialogField field) {
						String newDisplayNameValue = ((StringDialogField) field)
								.getText().trim();
						Command cmd = null;
						RenderKitType Renderkit = (RenderKitType) getInput();
						EditingDomain editingDomain = getEditingDomain();
						if (Renderkit.getDisplayName().size() > 0) {
							DisplayNameType displayName = (DisplayNameType) Renderkit
									.getDisplayName().get(0);
							cmd = SetCommand.create(editingDomain, displayName,
									FacesConfigPackage.eINSTANCE
											.getDisplayNameType_TextContent(),
									newDisplayNameValue);
						} else {
							DisplayNameType displayNameType = FacesConfigFactory.eINSTANCE
									.createDisplayNameType();
							displayNameType.setTextContent(newDisplayNameValue);
							displayNameType.eAdapters().add(
									getRenderKitGeneralAdapter());

							cmd = AddCommand.create(editingDomain, Renderkit,
									FacesConfigPackage.eINSTANCE
											.getRenderKitType_DisplayName(),
									displayNameType);
						}
						if (cmd.canExecute()) {
							editingDomain.getCommandStack().execute(cmd);
						}
					}

				});

		descriptionField = new StringDialogField(2);
		descriptionField
				.setLabelText(EditorMessages.RenderKitGeneralSection_Label_Description);
		descriptionField.doFillIntoGrid(toolkit, container, numberOfColumns);

		descriptionField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {
						String newDescriptionValue = ((StringDialogField) field)
								.getText().trim();
						newDescriptionValue = ModelUtil
								.escapeEntities(newDescriptionValue);
						Command cmd = null;
						RenderKitType Renderkit = (RenderKitType) getInput();
						EditingDomain editingDomain = getEditingDomain();
						if (Renderkit.getDescription().size() > 0) {
							DescriptionType description = (DescriptionType) Renderkit
									.getDescription().get(0);
							cmd = SetCommand.create(editingDomain, description,
									FacesConfigPackage.eINSTANCE
											.getDescriptionType_TextContent(),
									newDescriptionValue);
						} else {
							DescriptionType description = FacesConfigFactory.eINSTANCE
									.createDescriptionType();
							description.setTextContent(newDescriptionValue);
							description.eAdapters().add(
									getRenderKitGeneralAdapter());

							cmd = AddCommand.create(editingDomain, Renderkit,
									FacesConfigPackage.eINSTANCE
											.getRenderKitType_Description(),
									description);
						}
						if (cmd.canExecute()) {
							editingDomain.getCommandStack().execute(cmd);
						}
					}
				});

		renderkitIdField = new StringDialogField();
		// renderkitIdField.setRequired(true);
		renderkitIdField
				.setLabelText(EditorMessages.RenderKitGeneralSection_Label_RenderKitID);
		renderkitIdField.doFillIntoGrid(toolkit, container, numberOfColumns);
		renderkitIdField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {
						RenderKitIdType RenderkitId = FacesConfigFactory.eINSTANCE
								.createRenderKitIdType();
						RenderkitId.setTextContent(((StringDialogField) field)
								.getText());

						EditingDomain editingDomain = getEditingDomain();
						if (editingDomain != null) {
							Command command = SetCommand.create(editingDomain,
									getInput(), FacesConfigPackage.eINSTANCE
											.getRenderKitType_RenderKitId(),
									RenderkitId);
							if (command.canExecute()) {
								editingDomain.getCommandStack()
										.execute(command);
							}
						}
					}
				});

		renderkitClassField = new ClassButtonDialogField(null);
		// renderkitClassField.setRequired(true);
		renderkitClassField
				.setLabelText(EditorMessages.RenderKitGeneralSection_Label_RenderKitClass);
		renderkitClassField.doFillIntoGrid(toolkit, container, numberOfColumns);
		IProject project = getPage().getEditor().getAdapter(IProject.class);
		renderkitClassField.setProject(project);
		LayoutUtil.setHorizontalGrabbing(renderkitClassField.getTextControl(
				toolkit, container));

		renderkitClassField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {

						RenderKitClassType RenderkitClass = FacesConfigFactory.eINSTANCE
								.createRenderKitClassType();
						RenderkitClass
								.setTextContent(((StringDialogField) field)
										.getText());
						EditingDomain editingDomain = getEditingDomain();
						if (editingDomain != null) {
							Command command = SetCommand.create(editingDomain,
									getInput(), FacesConfigPackage.eINSTANCE
											.getRenderKitType_RenderKitClass(),
									RenderkitClass);
							if (command.canExecute()) {
								editingDomain.getCommandStack()
										.execute(command);
							}
						}

					}
				});
		renderkitClassField
				.setSuperClassName(
						JSFVersion.guessAtLeast(JSFVersion.V3_0, project) ?
								IFacesConfigConstants.RENDER_KIT_SUPER_CLASS_JAKARTA :
									IFacesConfigConstants.RENDER_KIT_SUPER_CLASS);
	}

	/**
	 * 
	 */
	public void refreshAll() {
		refresh();
	}

	/**
	 * 
	 */
	public void refresh() {
		super.refresh();
		Object input = this.getInput();
		if (input instanceof RenderKitType) {
			final RenderKitType renderkit = (RenderKitType) input;
			refreshControls(renderkit);
		}
	}

	private void refreshControls(RenderKitType renderkit) {
		if (renderkit.getDisplayName().size() > 0) {
			DisplayNameType displayName = (DisplayNameType) renderkit
					.getDisplayName().get(0);
			displayNameField.setTextWithoutUpdate(displayName
					.getTextContent());
		} else {
			displayNameField.setTextWithoutUpdate(""); //$NON-NLS-1$
		}

		if (renderkit.getDescription().size() > 0) {
			DescriptionType description = (DescriptionType) renderkit
					.getDescription().get(0);
			String descriptionString = description.getTextContent();
			descriptionString = ModelUtil
					.unEscapeEntities(descriptionString);
			descriptionField.setTextWithoutUpdate(descriptionString);
		} else {
			descriptionField.setTextWithoutUpdate(""); //$NON-NLS-1$
		}

		if (renderkit.getRenderKitId() != null) {
			renderkitIdField.setTextWithoutUpdate(renderkit
					.getRenderKitId().getTextContent());
		} else {
			renderkitIdField.setTextWithoutUpdate(""); //$NON-NLS-1$
		}

		if (renderkit.getRenderKitClass() != null) {
			renderkitClassField.setTextWithoutUpdate(renderkit
					.getRenderKitClass().getTextContent());
		} else {
			renderkitClassField.setTextWithoutUpdate(""); //$NON-NLS-1$
		}

	}
	/**
	 * Add RenderKitGeneralAdapter onto <render-kit> and the first
	 * <display-name> and <description> elements.
	 */
	protected void addAdaptersOntoInput(Object newInput) {
		RenderKitType renderkit = (RenderKitType) newInput;
		if (EcoreUtil.getExistingAdapter(renderkit,
				RenderkitGeneralSection.class) == null) {

			renderkit.eAdapters().add(getRenderKitGeneralAdapter());
		}

		if (renderkit.getDisplayName().size() > 0) {
			DisplayNameType displayName = (DisplayNameType) renderkit
					.getDisplayName().get(0);
			if (EcoreUtil.getExistingAdapter(displayName,
					RenderkitGeneralSection.class) == null) {

				displayName.eAdapters().add(getRenderKitGeneralAdapter());
			}
		}

		if (renderkit.getDescription().size() > 0) {
			DescriptionType description = (DescriptionType) renderkit
					.getDescription().get(0);
			if (EcoreUtil.getExistingAdapter(description,
					RenderkitGeneralSection.class) == null) {

				description.eAdapters().add(getRenderKitGeneralAdapter());
			}
		}
	}

	/**
	 * Remove RenderKitGeneralAdapter from <render-kit> and the first
	 * <display-name> and <description> elements.
	 */
	protected void removeAdaptersFromInput(Object oldInput) {
		RenderKitType renderkit = (RenderKitType) oldInput;
		if (EcoreUtil.getExistingAdapter(renderkit,
				RenderkitGeneralSection.class) != null) {
			renderkit.eAdapters().remove(getRenderKitGeneralAdapter());
		}
		if (renderkit.getDisplayName().size() > 0) {
			DisplayNameType displayName = (DisplayNameType) renderkit
					.getDisplayName().get(0);
			if (EcoreUtil.getExistingAdapter(displayName,
					RenderkitGeneralSection.class) != null) {

				displayName.eAdapters().remove(getRenderKitGeneralAdapter());
			}
		}

		if (renderkit.getDescription().size() > 0) {
			DescriptionType description = (DescriptionType) renderkit
					.getDescription().get(0);
			if (EcoreUtil.getExistingAdapter(description,
					RenderkitGeneralSection.class) != null) {

				description.eAdapters().remove(getRenderKitGeneralAdapter());
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	private Adapter getRenderKitGeneralAdapter() {

		if (renderKitGeneralAdapter == null) {
			renderKitGeneralAdapter = new RenderKitGeneralAdapter();
		}
		return renderKitGeneralAdapter;
	}

	/**
	 * The adapter that will be added onto <render-kit> element, to listen the
	 * events of the children that are displayed in this section, notify the
	 * section to refresh.
	 * 
	 * @author sfshi
	 * 
	 */
	class RenderKitGeneralAdapter extends AdapterImpl {

		public boolean isAdapterForType(Object type) {
			if (type == RenderkitGeneralSection.class)
				return true;
			return false;
		}

		public void notifyChanged(Notification msg) {

			if (msg.getEventType() == Notification.ADD
					|| msg.getEventType() == Notification.REMOVE
					|| msg.getEventType() == Notification.SET) {
				if (msg.getFeature() == FacesConfigPackage.eINSTANCE
						.getRenderKitType_RenderKitId()
						|| msg.getFeature() == FacesConfigPackage.eINSTANCE
								.getRenderKitType_RenderKitClass()
						|| msg.getFeature() == FacesConfigPackage.eINSTANCE
								.getRenderKitType_Description()
						|| msg.getFeature() == FacesConfigPackage.eINSTANCE
								.getRenderKitType_DisplayName()) {
					refreshInUIThread();
				} else if (msg.getFeature() == FacesConfigPackage.eINSTANCE
						.getDisplayNameType_TextContent()
						|| msg.getFeature() == FacesConfigPackage.eINSTANCE
								.getDescriptionType_TextContent()) {
					refreshInUIThread();
				}
			}
		}

		private void refreshInUIThread() {
			if (Thread.currentThread() == PlatformUI.getWorkbench().getDisplay().getThread()) {
				refresh();
			} else {				
				PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
					public void run() {
						refresh();
					}
				});
			}		
		}
	}
	
}
