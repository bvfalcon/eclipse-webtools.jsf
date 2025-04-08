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
import org.eclipse.jst.jsf.facesconfig.emf.ComponentClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentTypeType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.ui.EditorMessages;
import org.eclipse.jst.jsf.facesconfig.ui.IFacesConfigConstants;
import org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage;
import org.eclipse.jst.jsf.facesconfig.ui.util.ModelUtil;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * The sections that used for displaying and editing the general information of
 * a component, the information includs display name, description, component
 * type and component class.
 * 
 * @author sfshi
 * 
 */
public class ComponentGeneralSection extends AbstractFacesConfigSection {

	private StringDialogField displayNameField;

	private StringDialogField descriptionField;

	private StringDialogField componentTypeField;

	private ClassButtonDialogField componentClassField;

	private ComponentGeneralAdapter componentGeneralAdapter;

	/**
	 * 
	 * @param parent
	 * @param managedForm
	 * @param page
	 * @param toolkit
	 */
	public ComponentGeneralSection(Composite parent, IManagedForm managedForm,
			IFacesConfigPage page, FormToolkit toolkit) {
		super(parent, managedForm, page, toolkit, null, null);
		getSection()
				.setText(EditorMessages.ComponentGeneralSection_Name);
		getSection().setDescription(
				EditorMessages.ComponentGeneralSection_Description);
	}

	/**
	 * Create the UI fields.
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
				.setLabelText(EditorMessages.ComponentGeneralSection_Label_DisplayName);
		displayNameField.doFillIntoGrid(toolkit, container, numberOfColumns);

		displayNameField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {

					public void dialogFieldApplied(DialogField field) {
						String newDisplayNameValue = ((StringDialogField) field)
								.getText().trim();
						Command cmd = null;
						ComponentType component = (ComponentType) getInput();
						EditingDomain editingDomain = getEditingDomain();
						if (component.getDisplayName().size() > 0) {
							DisplayNameType displayName = (DisplayNameType) component
									.getDisplayName().get(0);
							cmd = SetCommand.create(editingDomain, displayName,
									FacesConfigPackage.eINSTANCE
											.getDisplayNameType_TextContent(),
									newDisplayNameValue);
						} else {
							DisplayNameType displayNameType = FacesConfigFactory.eINSTANCE
									.createDisplayNameType();
							displayNameType.setTextContent(newDisplayNameValue);

							/** For the new displayname object, add the adapter. */
							displayNameType.eAdapters().add(
									getComponentGeneralAdapter());
							cmd = AddCommand.create(editingDomain, component,
									FacesConfigPackage.eINSTANCE
											.getComponentType_DisplayName(),
									displayNameType);
						}
						if (cmd.canExecute()) {
							editingDomain.getCommandStack().execute(cmd);
						}
					}

				});

		descriptionField = new StringDialogField(2);
		descriptionField
				.setLabelText(EditorMessages.ComponentGeneralSection_Label_Description);
		descriptionField.doFillIntoGrid(toolkit, container, numberOfColumns);

		descriptionField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {
						String newDescriptionValue = ((StringDialogField) field)
								.getText().trim();

						newDescriptionValue = ModelUtil
								.escapeEntities(newDescriptionValue);
						Command cmd = null;
						ComponentType component = (ComponentType) getInput();
						EditingDomain editingDomain = getEditingDomain();
						if (component.getDescription().size() > 0) {
							DescriptionType description = (DescriptionType) component
									.getDescription().get(0);
							cmd = SetCommand.create(editingDomain, description,
									FacesConfigPackage.eINSTANCE
											.getDescriptionType_TextContent(),
									newDescriptionValue);
						} else {
							DescriptionType description = FacesConfigFactory.eINSTANCE
									.createDescriptionType();
							description.setTextContent(newDescriptionValue);
							/** For the new description object, add the adapter. */
							description.eAdapters().add(
									getComponentGeneralAdapter());

							cmd = AddCommand.create(editingDomain, component,
									FacesConfigPackage.eINSTANCE
											.getComponentType_Description(),
									description);
						}
						if (cmd.canExecute()) {
							editingDomain.getCommandStack().execute(cmd);
						}
					}
				});

		componentTypeField = new StringDialogField();
		componentTypeField
				.setLabelText(EditorMessages.ComponentGeneralSection_Label_ComponentType);
		componentTypeField.doFillIntoGrid(toolkit, container, numberOfColumns);
		componentTypeField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {
						ComponentTypeType componentType = FacesConfigFactory.eINSTANCE
								.createComponentTypeType();
						componentType
								.setTextContent(((StringDialogField) field)
										.getText());

						EditingDomain editingDomain = getEditingDomain();
						if (editingDomain != null) {
							Command command = SetCommand.create(editingDomain,
									getInput(), FacesConfigPackage.eINSTANCE
											.getComponentType_ComponentType(),
									componentType);
							if (command.canExecute()) {
								editingDomain.getCommandStack()
										.execute(command);
							}
						}
					}
				});

		componentClassField = new ClassButtonDialogField(null);
		componentClassField
				.setLabelText(EditorMessages.ComponentGeneralSection_Label_ComponentClass);
		componentClassField.doFillIntoGrid(toolkit, container, numberOfColumns);
		LayoutUtil.setHorizontalGrabbing(componentClassField.getTextControl(
				toolkit, container));
		IProject project = (IProject) getPage().getEditor().getAdapter(IProject.class);
		componentClassField.setProject(project);
		componentClassField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {

						ComponentClassType componentClass = FacesConfigFactory.eINSTANCE
								.createComponentClassType();
						componentClass
								.setTextContent(((StringDialogField) field)
										.getText());
						EditingDomain editingDomain = getEditingDomain();
						if (editingDomain != null) {
							Command command = SetCommand.create(editingDomain,
									getInput(), FacesConfigPackage.eINSTANCE
											.getComponentType_ComponentClass(),
									componentClass);
							if (command.canExecute()) {
								editingDomain.getCommandStack()
										.execute(command);
							}
						}

					}
				});
		componentClassField
				.setSuperClassName(JSFVersion.guessAtLeast(JSFVersion.V3_0, project) ?
						IFacesConfigConstants.COMPONENT_SUPER_CLASS_JAKARTA :
							IFacesConfigConstants.COMPONENT_SUPER_CLASS);
	}

	/**
	 * 
	 */
	public void refreshAll() {
		refresh();
	}

	/**
	 * Refresh the content on this section.
	 */
	public void refresh() {
		super.refresh();
		Object input = this.getInput();
		if (input instanceof ComponentType) {			
			final ComponentType component = (ComponentType) input;
			refreshControls(component);
		}
	}

	private void refreshControls(ComponentType component) {
		if (component.getDisplayName().size() > 0) {
			DisplayNameType displayName = (DisplayNameType) component
					.getDisplayName().get(0);
			displayNameField.setTextWithoutUpdate(displayName
					.getTextContent());
		} else {
			displayNameField.setTextWithoutUpdate(""); //$NON-NLS-1$
		}

		if (component.getDescription().size() > 0) {
			DescriptionType description = (DescriptionType) component
					.getDescription().get(0);
			String descriptionString = description.getTextContent();
			descriptionString = ModelUtil.unEscapeEntities(descriptionString);
			descriptionField.setTextWithoutUpdate(descriptionString);
		} else {
			descriptionField.setTextWithoutUpdate(""); //$NON-NLS-1$
		}

		if (component.getComponentType() != null) {
			componentTypeField.setTextWithoutUpdate(component
					.getComponentType().getTextContent());
		} else {
			componentTypeField.setTextWithoutUpdate(""); //$NON-NLS-1$
		}

		if (component.getComponentClass() != null) {
			componentClassField.setTextWithoutUpdate(component
					.getComponentClass().getTextContent());
		} else {
			componentClassField.setTextWithoutUpdate(""); //$NON-NLS-1$
		}
	}

	/**
	 * Add ComponentGeneralAdapter onto <component> and the first <display-name>
	 * and <description> elements.
	 */
	protected void addAdaptersOntoInput(Object newInput) {
		ComponentType component = (ComponentType) newInput;
		if (EcoreUtil.getExistingAdapter(component,
				ComponentGeneralSection.class) == null) {

			component.eAdapters().add(getComponentGeneralAdapter());
		}

		if (component.getDisplayName().size() > 0) {
			DisplayNameType displayName = (DisplayNameType) component
					.getDisplayName().get(0);
			if (EcoreUtil.getExistingAdapter(displayName,
					ComponentGeneralSection.class) == null) {

				displayName.eAdapters().add(getComponentGeneralAdapter());
			}
		}

		if (component.getDescription().size() > 0) {
			DescriptionType description = (DescriptionType) component
					.getDescription().get(0);
			if (EcoreUtil.getExistingAdapter(description,
					ComponentGeneralSection.class) == null) {

				description.eAdapters().add(getComponentGeneralAdapter());
			}
		}
	}

	/**
	 * Remove ComponentGeneralAdapter from <component> and the first
	 * <display-name> and <description> elements.
	 */
	protected void removeAdaptersFromInput(Object oldInput) {
		ComponentType component = (ComponentType) oldInput;
		if (EcoreUtil.getExistingAdapter(component,
				ComponentGeneralSection.class) != null) {
			component.eAdapters().remove(getComponentGeneralAdapter());
		}
		if (component.getDisplayName().size() > 0) {
			DisplayNameType displayName = (DisplayNameType) component
					.getDisplayName().get(0);
			if (EcoreUtil.getExistingAdapter(displayName,
					ComponentGeneralSection.class) != null) {

				displayName.eAdapters().remove(getComponentGeneralAdapter());
			}
		}

		if (component.getDescription().size() > 0) {
			DescriptionType description = (DescriptionType) component
					.getDescription().get(0);
			if (EcoreUtil.getExistingAdapter(description,
					ComponentGeneralSection.class) != null) {

				description.eAdapters().remove(getComponentGeneralAdapter());
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	private Adapter getComponentGeneralAdapter() {

		if (componentGeneralAdapter == null) {
			componentGeneralAdapter = new ComponentGeneralAdapter();
		}
		return componentGeneralAdapter;
	}

	/**
	 * The adapter that will be added onto <component> element, to listen the
	 * events of the children that are displayed in this section, notify the
	 * section to refresh.
	 * 
	 * @author sfshi
	 * 
	 */
	class ComponentGeneralAdapter extends AdapterImpl {

		public boolean isAdapterForType(Object type) {
			if (type == ComponentGeneralSection.class)
				return true;
			return false;
		}

		public void notifyChanged(Notification msg) {

			if (msg.getEventType() == Notification.ADD
					|| msg.getEventType() == Notification.REMOVE
					|| msg.getEventType() == Notification.SET) {
				if (msg.getFeature() == FacesConfigPackage.eINSTANCE
						.getComponentType_ComponentClass()
						|| msg.getFeature() == FacesConfigPackage.eINSTANCE
								.getComponentType_ComponentType()
						|| msg.getFeature() == FacesConfigPackage.eINSTANCE
								.getComponentType_DisplayName()
						|| msg.getFeature() == FacesConfigPackage.eINSTANCE
								.getComponentType_Description()) {
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
