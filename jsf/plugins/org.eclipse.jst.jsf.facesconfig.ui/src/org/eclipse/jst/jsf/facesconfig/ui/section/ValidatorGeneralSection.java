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
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.ClassButtonDialogField;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.DialogField;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.IDialogFieldApplyListener;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.LayoutUtil;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.StringDialogField;
import org.eclipse.jst.jsf.facesconfig.ui.IFacesConfigConstants;
import org.eclipse.jst.jsf.facesconfig.ui.NewEditorResourcesNLS;
import org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage;
import org.eclipse.jst.jsf.facesconfig.ui.util.ModelUtil;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wtp.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.wtp.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.wtp.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.wtp.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.wtp.jsf.facesconfig.emf.ValidatorClassType;
import org.eclipse.wtp.jsf.facesconfig.emf.ValidatorIdType;
import org.eclipse.wtp.jsf.facesconfig.emf.ValidatorType;

/**
 * @author Bryan Yang
 * 
 */
public class ValidatorGeneralSection extends AbstractFacesConfigSection {

	private StringDialogField displayNameField;

	private StringDialogField descriptionField;

	private StringDialogField validatorIDField;

	private ClassButtonDialogField validatorClassField;

	private ValidatorGeneralAdapter validatorGeneralAdapter;

	/**
	 * 
	 * @param parent
	 * @param managedForm
	 * @param page
	 * @param toolkit
	 */
	public ValidatorGeneralSection(Composite parent, IManagedForm managedForm,
			IFacesConfigPage page, FormToolkit toolkit) {
		super(parent, managedForm, page, toolkit, null, null);
		getSection()
				.setText(NewEditorResourcesNLS.ValidatorGeneralSection_Name);
		getSection().setDescription(
				NewEditorResourcesNLS.ValidatorGeneralSection_Description);
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
				.setLabelText(NewEditorResourcesNLS.ValidatorGeneralSection_Label_DisplayName);
		displayNameField.doFillIntoGrid(toolkit, container, numberOfColumns);

		displayNameField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {

					public void dialogFieldApplied(DialogField field) {
						String newDisplayNameValue = ((StringDialogField) field)
								.getText().trim();
						Command cmd = null;
						ValidatorType Validator = (ValidatorType) getInput();
						EditingDomain editingDomain = getEditingDomain();
						if (Validator.getDisplayName().size() > 0) {
							DisplayNameType displayName = (DisplayNameType) Validator
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
									getValidatorGeneralAdapter());

							cmd = AddCommand.create(editingDomain, Validator,
									FacesConfigPackage.eINSTANCE
											.getValidatorType_DisplayName(),
									displayNameType);
						}
						if (cmd.canExecute()) {
							editingDomain.getCommandStack().execute(cmd);
						}
					}

				});

		descriptionField = new StringDialogField(2);
		descriptionField
				.setLabelText(NewEditorResourcesNLS.ValidatorGeneralSection_Label_Description);
		descriptionField.doFillIntoGrid(toolkit, container, numberOfColumns);

		descriptionField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {
						String newDescriptionValue = ((StringDialogField) field)
								.getText().trim();
						newDescriptionValue = ModelUtil
								.escapeEntities(newDescriptionValue);
						Command cmd = null;
						ValidatorType Validator = (ValidatorType) getInput();
						EditingDomain editingDomain = getEditingDomain();
						if (Validator.getDescription().size() > 0) {
							DescriptionType description = (DescriptionType) Validator
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
									getValidatorGeneralAdapter());

							cmd = AddCommand.create(editingDomain, Validator,
									FacesConfigPackage.eINSTANCE
											.getValidatorType_Description(),
									description);
						}
						if (cmd.canExecute()) {
							editingDomain.getCommandStack().execute(cmd);
						}
					}
				});

		validatorIDField = new StringDialogField();
		// validatorIDField.setRequired(true);
		validatorIDField
				.setLabelText(NewEditorResourcesNLS.ValidatorGeneralSection_Label_ValidatorID);
		validatorIDField.doFillIntoGrid(toolkit, container, numberOfColumns);
		validatorIDField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {
						ValidatorIdType ValidatorType = FacesConfigFactory.eINSTANCE
								.createValidatorIdType();
						ValidatorType
								.setTextContent(((StringDialogField) field)
										.getText());

						EditingDomain editingDomain = getEditingDomain();
						if (editingDomain != null) {
							Command command = SetCommand.create(editingDomain,
									getInput(), FacesConfigPackage.eINSTANCE
											.getValidatorType_ValidatorId(),
									ValidatorType);
							if (command.canExecute()) {
								editingDomain.getCommandStack()
										.execute(command);
							}
						}
					}
				});

		validatorClassField = new ClassButtonDialogField(null);
		// validatorClassField.setRequired(true);
		validatorClassField
				.setLabelText(NewEditorResourcesNLS.ValidatorGeneralSection_Label_ValidatorClass);
		validatorClassField.setProject((IProject) getPage().getEditor()
				.getAdapter(IProject.class));
		validatorClassField.doFillIntoGrid(toolkit, container, numberOfColumns);
		LayoutUtil.setHorizontalGrabbing(validatorClassField.getTextControl(
				toolkit, container));

		validatorClassField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {

						ValidatorClassType ValidatorClass = FacesConfigFactory.eINSTANCE
								.createValidatorClassType();
						ValidatorClass
								.setTextContent(((StringDialogField) field)
										.getText());
						EditingDomain editingDomain = getEditingDomain();
						if (editingDomain != null) {
							Command command = SetCommand.create(editingDomain,
									getInput(), FacesConfigPackage.eINSTANCE
											.getValidatorType_ValidatorClass(),
									ValidatorClass);
							if (command.canExecute()) {
								editingDomain.getCommandStack()
										.execute(command);
							}
						}

					}
				});
		validatorClassField
				.setInterface(IFacesConfigConstants.VALIDATOR_INTERFACE);
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
		if (input instanceof ValidatorType) {
			ValidatorType Validator = (ValidatorType) input;

			if (Validator.getDisplayName().size() > 0) {
				DisplayNameType displayName = (DisplayNameType) Validator
						.getDisplayName().get(0);
				displayNameField.setTextWithoutUpdate(displayName
						.getTextContent());
			} else {
				displayNameField.setTextWithoutUpdate("");
			}

			if (Validator.getDescription().size() > 0) {
				DescriptionType description = (DescriptionType) Validator
						.getDescription().get(0);
				String descriptionString = description.getTextContent();
				descriptionString = ModelUtil
						.unEscapeEntities(descriptionString);
				descriptionField.setTextWithoutUpdate(descriptionString);
			} else {
				descriptionField.setTextWithoutUpdate("");
			}

			if (Validator.getValidatorId() != null) {
				validatorIDField.setTextWithoutUpdate(Validator
						.getValidatorId().getTextContent());
			} else {
				validatorIDField.setTextWithoutUpdate("");
			}

			if (Validator.getValidatorClass() != null) {
				validatorClassField.setTextWithoutUpdate(Validator
						.getValidatorClass().getTextContent());
			} else {
				validatorClassField.setTextWithoutUpdate("");
			}
		}
	}

	/**
	 * 
	 */
	public void clearAll() {
		// TODO Auto-generated method stub

	}

	/**
	 * Add ValidatorGeneralAdapter onto <validator> and the first <display-name>
	 * and <description> elements.
	 */
	protected void addAdaptersOntoInput(Object newInput) {
		ValidatorType validator = (ValidatorType) newInput;
		if (EcoreUtil.getExistingAdapter(validator,
				ValidatorGeneralSection.class) == null) {

			validator.eAdapters().add(getValidatorGeneralAdapter());
		}

		if (validator.getDisplayName().size() > 0) {
			DisplayNameType displayName = (DisplayNameType) validator
					.getDisplayName().get(0);
			if (EcoreUtil.getExistingAdapter(displayName,
					ValidatorGeneralSection.class) == null) {

				displayName.eAdapters().add(getValidatorGeneralAdapter());
			}
		}

		if (validator.getDescription().size() > 0) {
			DescriptionType description = (DescriptionType) validator
					.getDescription().get(0);
			if (EcoreUtil.getExistingAdapter(description,
					ValidatorGeneralSection.class) == null) {

				description.eAdapters().add(getValidatorGeneralAdapter());
			}
		}
	}

	/**
	 * Remove ValidatorGeneralAdapter from <validator> and the first
	 * <display-name> and <description> elements.
	 */
	protected void removeAdaptersFromInput(Object oldInput) {
		ValidatorType validator = (ValidatorType) oldInput;
		if (EcoreUtil.getExistingAdapter(validator,
				ValidatorGeneralSection.class) != null) {
			validator.eAdapters().remove(getValidatorGeneralAdapter());
		}
		if (validator.getDisplayName().size() > 0) {
			DisplayNameType displayName = (DisplayNameType) validator
					.getDisplayName().get(0);
			if (EcoreUtil.getExistingAdapter(displayName,
					ValidatorGeneralSection.class) != null) {

				displayName.eAdapters().remove(getValidatorGeneralAdapter());
			}
		}

		if (validator.getDescription().size() > 0) {
			DescriptionType description = (DescriptionType) validator
					.getDescription().get(0);
			if (EcoreUtil.getExistingAdapter(description,
					ValidatorGeneralSection.class) != null) {

				description.eAdapters().remove(getValidatorGeneralAdapter());
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	private Adapter getValidatorGeneralAdapter() {

		if (validatorGeneralAdapter == null) {
			validatorGeneralAdapter = new ValidatorGeneralAdapter();
		}
		return validatorGeneralAdapter;
	}

	/**
	 * The adapter that will be added onto <validator> element, to listen the
	 * events of the children that are displayed in this section, notify the
	 * section to refresh.
	 * 
	 * @author sfshi
	 * 
	 */
	class ValidatorGeneralAdapter extends AdapterImpl {

		public boolean isAdapterForType(Object type) {
			if (type == ValidatorGeneralSection.class)
				return true;
			return false;
		}

		public void notifyChanged(Notification msg) {

			if (msg.getEventType() == Notification.ADD
					|| msg.getEventType() == Notification.REMOVE
					|| msg.getEventType() == Notification.SET) {
				if (msg.getFeature() == FacesConfigPackage.eINSTANCE
						.getValidatorType_ValidatorId()
						|| msg.getFeature() == FacesConfigPackage.eINSTANCE
								.getValidatorType_ValidatorClass()
						|| msg.getFeature() == FacesConfigPackage.eINSTANCE
								.getValidatorType_Description()
						|| msg.getFeature() == FacesConfigPackage.eINSTANCE
								.getValidatorType_DisplayName()) {
					refresh();
				} else if (msg.getFeature() == FacesConfigPackage.eINSTANCE
						.getDisplayNameType_TextContent()
						|| msg.getFeature() == FacesConfigPackage.eINSTANCE
								.getDescriptionType_TextContent()) {
					refresh();
				}
			}
		}
	}
}
