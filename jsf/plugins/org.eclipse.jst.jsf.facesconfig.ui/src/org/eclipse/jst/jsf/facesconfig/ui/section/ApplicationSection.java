/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others.
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.ui.dialog.DialogUtil;
import org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage;
import org.eclipse.jst.jsf.facesconfig.ui.page.OthersPage;
import org.eclipse.jst.jsf.facesconfig.ui.util.ModelUtil;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * A common <code>IFacesConfigSection</code> implementation for the sections that editing
 * the child elements of <application>, including: <action-listener>,
 * <default-render-kit-id>, <message-bundle>, <navigation-handler>,
 * <view-handler>,<state-manager>, <property-resolver>, <variable-resolver> and
 * <locale-config>. This sections will use the EClass object of these elements
 * as the key.
 * 
 * @author Zhi-peng Zhang, sfshi
 * @version
 */
public class ApplicationSection extends OthersPageBaseSection {

	/** The Eclass object this Section operated */
	private EClass applicationChildClass;

	/**
	 * 
	 * @param applicationChildClass
	 * @param parent
	 * @param managedForm
	 * @param page
	 * @param toolkit
	 * @param helpContextId
	 * @param helpTooltip
	 */
	public ApplicationSection(EClass applicationChildClass, Composite parent,
			IManagedForm managedForm, IFacesConfigPage page,
			FormToolkit toolkit, String helpContextId, String helpTooltip) {
		super(parent, managedForm, page, toolkit, helpContextId, helpTooltip);
		this.applicationChildClass = applicationChildClass;
	}

	/**
	 * 
	 * @param componentClass
	 * @param parent
	 * @param managedForm
	 * @param page
	 * @param toolkit
	 */
	public ApplicationSection(EClass componentClass, Composite parent,
			IManagedForm managedForm, IFacesConfigPage page, FormToolkit toolkit) {
		this(componentClass, parent, managedForm, page, toolkit, null, null);
	}

	/**
	 * Create an object which is instance of <code>applicationChildClass</code>.
	 * 
	 * @return the child class object
	 */
	public EObject createApplicationChildObject() {
		String textValue = null;
		IProject project = (IProject) this.getPage().getEditor().getAdapter(
				IProject.class);
		FacesConfigType facesConfig = (FacesConfigType) this.getPage()
				.getInput();
		if (applicationChildClass == FacesConfigPackage.eINSTANCE
				.getDefaultRenderKitIdType()) {

			/** Open a dialog to select a render-kit of this faces config. */
			textValue = DialogUtil.openRenderKitDialog(getSection().getShell(),
					facesConfig);
		} else if (applicationChildClass == FacesConfigPackage.eINSTANCE
				.getMessageBundleType()) {
			/**
			 * Open a dialog the select the message bundles that in current
			 * project. If user select a bundle that already declared in this
			 * faces config, then shows a warning message.
			 */
			List bundles = new ArrayList();
			if (facesConfig.getApplication().size() > 0) {
				ApplicationType applicationType = (ApplicationType) facesConfig
						.getApplication().get(0);
				bundles = applicationType.getMessageBundle();
			}

			textValue = DialogUtil.openMessageBundleDialog(getSection()
					.getShell(), project, bundles);

		} else {
			/**
			 * For other types children, open a ClassDialog to select a class in
			 * current project.
			 */
			String superType = ModelUtil.getSuperType(applicationChildClass, JSFVersion.valueOfProject(project));
			textValue = DialogUtil.openClassDialog(getSection().getShell(),
					project, superType,
					IJavaElementSearchConstants.CONSIDER_ALL_TYPES);
		}
		if (textValue != null && textValue.length() > 0) {
			EObject component = FacesConfigFactory.eINSTANCE
					.create(applicationChildClass);
			// set text content.
			EStructuralFeature feature = applicationChildClass
					.getEStructuralFeatures().get(0);
			component.eSet(feature, textValue);
			return component;
		}
		return null;
	}

	/**
	 * Set the <application> element as the structuredViewer's input.
	 * 
	 * @param input
	 */
	protected void setViewerInput(Object input) {
		if (input instanceof ApplicationType) {
			tableViewer.setInput(input);
		} else
			tableViewer.setInput(null);
	}

	/**
	 * Add a filter on the table viewer. Only the elements that is a instance of
	 * <code>applicationChildClass</code> could be selected.
	 */
	protected void configTableViewer(TableViewer tableViewer1) {
		tableViewer1.addFilter(new ViewerFilter() {
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				return applicationChildClass.isInstance(element);
			}
		});
	}

	/**
	 * Perform some actions to create new child object and append it to the
	 * <application> element.
	 */
	void addButtonSelected(SelectionEvent e) {
		/** Create such a new object. */
		EObject obj = createApplicationChildObject();
		if (obj != null) {

			boolean needRefreshAll = false;
			Command command = null;
			if (getInput() instanceof ApplicationType) {
				/**
				 * The input is a <application> element and not null, append the
				 * new object to it.
				 */
				ApplicationType application = (ApplicationType) getInput();
				command = AddCommand.create(getEditingDomain(), application,
						null, obj);
			} else {
				/**
				 * The input is null, create a <application> element and append
				 * it to <faces-config>.
				 */
				needRefreshAll = true;

				List list = new ArrayList(2);
				ApplicationType application = FacesConfigFactory.eINSTANCE
						.createApplicationType();
				Command cmd1 = AddCommand.create(getEditingDomain(),
						application, null, obj);
				list.add(cmd1);
				Command cmd2 = AddCommand.create(getEditingDomain(), this
						.getPage().getInput(), null, application);
				list.add(cmd2);
				command = new CompoundCommand(list);
			}

			if (command.canExecute()) {
				getEditingDomain().getCommandStack().execute(command);
				if (needRefreshAll) {
					/** reset the application input. */
					((OthersPage) this.getPage()).resetApplicationInput();
				}
			}
		}

	}
}
