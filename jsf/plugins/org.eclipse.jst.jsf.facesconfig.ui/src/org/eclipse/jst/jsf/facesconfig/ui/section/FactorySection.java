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
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FactoryType;
import org.eclipse.jst.jsf.facesconfig.ui.dialog.DialogUtil;
import org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage;
import org.eclipse.jst.jsf.facesconfig.ui.page.OthersPage;
import org.eclipse.jst.jsf.facesconfig.ui.util.ModelUtil;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author Zhi-peng Zhang, sfshi
 * @version
 */
public class FactorySection extends OthersPageBaseSection {
	/** The Eclass object this Section operated */
	private EClass factoryChildClass;

	/**
	 * 
	 * @param factoryChildClass
	 * @param parent
	 * @param managedForm
	 * @param page
	 * @param toolkit
	 * @param helpContextId
	 * @param helpTooltip
	 */
	public FactorySection(EClass factoryChildClass, Composite parent,
			IManagedForm managedForm, IFacesConfigPage page,
			FormToolkit toolkit, String helpContextId, String helpTooltip) {
		super(parent, managedForm, page, toolkit, helpContextId, helpTooltip);
		this.factoryChildClass = factoryChildClass;
	}

	/**
	 * 
	 * @param factoryChildClass
	 * @param parent
	 * @param managedForm
	 * @param page
	 * @param toolkit
	 */
	public FactorySection(EClass factoryChildClass, Composite parent,
			IManagedForm managedForm, IFacesConfigPage page, FormToolkit toolkit) {
		this(factoryChildClass, parent, managedForm, page, toolkit, null, null);
	}

	/**
	 * @return an new object which is instance of <code>factoryChildClass</code>.
	 */
	public EObject createFactoryChildObject() {
		IProject project = (IProject) this.getPage().getEditor().getAdapter(IProject.class);
		String superType = ModelUtil.getSuperType(factoryChildClass, JSFVersion.valueOfProject(project));
		String result = DialogUtil.openClassDialog(getSection().getShell(),
				project, superType,
				IJavaElementSearchConstants.CONSIDER_ALL_TYPES);

		if (result != null) {
			EObject component = FacesConfigFactory.eINSTANCE
					.create(factoryChildClass);
			// set the text content value.
			EStructuralFeature feature = factoryChildClass
					.getEStructuralFeatures().get(0);
			component.eSet(feature, result);
			return component;
		}
		return null;
	}

	/**
	 * Set the <factory> element as the structuredViewer's input.
	 * 
	 * @param input
	 */
	protected void setViewerInput(Object input) {
		if (input instanceof FactoryType) {
			tableViewer.setInput(input);
		} else
			tableViewer.setInput(null);
	}

	/**
	 * Add a filter on the table viewer. Only the elements that is a instance of
	 * <code>factoryChildClass</code> could be selected.
	 */
	protected void configTableViewer(TableViewer tableViewer1) {
		tableViewer1.addFilter(new ViewerFilter() {
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				return factoryChildClass.isInstance(element);
			}
		});
	}

	/**
	 * Perform some actions to create new child object and append it to the
	 * <factory> element.
	 */
	void addButtonSelected(SelectionEvent e) {
		/** Create a new object which is instance of factoryChildClass. */
		EObject obj = createFactoryChildObject();
		if (obj != null) {
			boolean needRefreshAll = false;
			Command command = null;
			if (getInput() instanceof FactoryType) {
				/**
				 * The input is a <factory> element and not null, append the new
				 * object to it.
				 */
				FactoryType factory = (FactoryType) getInput();
				command = AddCommand.create(getEditingDomain(), factory, null,
						obj);
			} else {
				/**
				 * The input is null, create a <factory> element and append it
				 * to <faces-config>.
				 */
				needRefreshAll = true;
				List list = new ArrayList(2);
				FactoryType factory = FacesConfigFactory.eINSTANCE
						.createFactoryType();
				Command cmd1 = AddCommand.create(getEditingDomain(), factory,
						null, obj);
				list.add(cmd1);
				Command cmd2 = AddCommand.create(getEditingDomain(), this
						.getPage().getInput(), null, factory);
				list.add(cmd2);
				command = new CompoundCommand(list);
			}

			if (command.canExecute()) {
				getEditingDomain().getCommandStack().execute(command);
				if (needRefreshAll)
					((OthersPage) this.getPage()).resetFactoryInput();
			}
		}
	}

}
