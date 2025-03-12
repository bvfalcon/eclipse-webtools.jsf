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

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.command.Command;
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
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleType;
import org.eclipse.jst.jsf.facesconfig.ui.dialog.DialogUtil;
import org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage;
import org.eclipse.jst.jsf.facesconfig.ui.page.OthersPage;
import org.eclipse.jst.jsf.facesconfig.ui.util.ModelUtil;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author Zhi-peng Zhang
 * @version
 */
public class LifecycleSection extends OthersPageBaseSection {

	/**
	 * The Eclass object this Section operated. Since lifecycle only has one
	 * type child: phase-listener, so this object should always equals
	 * <code>FacesConfigPackage.eINSTANCE
	 .getPhaseListenerType()</code>.
	 */
	private EClass lifecycleChildClass;

	/**
	 * 
	 * @param lifecycleChildClass
	 * @param parent
	 * @param managedForm
	 * @param page
	 * @param toolkit
	 * @param helpContextId
	 * @param helpTooltip
	 */
	public LifecycleSection(EClass lifecycleChildClass, Composite parent,
			IManagedForm managedForm, IFacesConfigPage page,
			FormToolkit toolkit, String helpContextId, String helpTooltip) {
		super(parent, managedForm, page, toolkit, helpContextId, helpTooltip);
		this.lifecycleChildClass = lifecycleChildClass;
	}

	/**
	 * 
	 * @param lifecycleChildClass
	 * @param parent
	 * @param managedForm
	 * @param page
	 * @param toolkit
	 */
	public LifecycleSection(EClass lifecycleChildClass, Composite parent,
			IManagedForm managedForm, IFacesConfigPage page, FormToolkit toolkit) {
		this(lifecycleChildClass, parent, managedForm, page, toolkit, null,
				null);
	}

	/**
	 * @return the lifecycle child
	 */
	public EObject createLifecycleChildObject() {
		IProject project = (IProject) this.getPage().getEditor().getAdapter(IProject.class);
		String superType = ModelUtil.getSuperType(lifecycleChildClass, JSFVersion.valueOfProject(project));
		String result = DialogUtil.openClassDialog(getSection().getShell(),
				project, superType,
				IJavaElementSearchConstants.CONSIDER_ALL_TYPES);

		if (result != null) {
			EObject component = FacesConfigFactory.eINSTANCE
					.create(lifecycleChildClass);
			EStructuralFeature feature = lifecycleChildClass
					.getEStructuralFeatures().get(0);
			component.eSet(feature, result);
			return component;
		}
		return null;
	}

	/**
	 * Set the <lifecycle> element as the structuredViewer's input.
	 * 
	 * @param input
	 */
	protected void setViewerInput(Object input) {
		if (input instanceof LifecycleType) {
			tableViewer.setInput(input);
		} else
			tableViewer.setInput(null);
	}

	/**
	 * Add a filter on the table viewer. Only the elements that is a instance of
	 * <code>lifecycleChildClass</code> could be selected.
	 */
	protected void configTableViewer(TableViewer tableViewer1) {
		tableViewer1.addFilter(new ViewerFilter() {
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				return lifecycleChildClass.isInstance(element);
			}
		});
	}

	/**
	 * Perform some actions to create new child object and append it to the
	 * <lifecycle> element.
	 */
	void addButtonSelected(SelectionEvent e) {
		EObject obj = createLifecycleChildObject();
		if (obj != null) {

			boolean needRefreshAll = false;
			Command command = null;
			if (getInput() instanceof LifecycleType) {
				LifecycleType lifeCycle = (LifecycleType) getInput();
				command = AddCommand.create(getEditingDomain(), lifeCycle,
						null, obj);
			} else {
				needRefreshAll = true;
				LifecycleType lifeCycle = FacesConfigFactory.eINSTANCE
						.createLifecycleType();
				lifeCycle.getPhaseListener().add(obj);
				command = AddCommand.create(getEditingDomain(), this.getPage()
						.getInput(), null, lifeCycle);
			}

			if (command.canExecute()) {
				getEditingDomain().getCommandStack().execute(command);
				if (needRefreshAll)
					((OthersPage) this.getPage()).resetLifecycleInput();
			}
		}

	}

}
