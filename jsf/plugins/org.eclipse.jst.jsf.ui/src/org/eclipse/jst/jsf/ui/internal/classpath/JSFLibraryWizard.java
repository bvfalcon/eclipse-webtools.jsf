/*******************************************************************************
 * Copyright (c) 2005, 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Gerry Kessler - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.ui.internal.classpath;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryRegistryUtil;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryFactory;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFVersion;
import org.eclipse.jst.jsf.ui.internal.JSFUIPlugin;
import org.eclipse.jst.jsf.ui.internal.Messages;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * Dialog for creating or editing a JSF Library or Implementation.
 * <br>
 * If the selection passed in init is not null then the item will be edit mode.
 * 
 * @author Gerry Kessler - Oracle
 * @deprecated
 */
public class JSFLibraryWizard extends Wizard implements INewWizard {

	private JSFLibraryEditControl jsfLibraryEditControl;
	
	private boolean isNew = false;
	private boolean modified = false;

	private JSFLibrary curLibrary;
	private JSFLibrary workingCopyLibrary;

	private JSFLibraryWizardPage page;

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) 
	{
		if (selection != null
				&& selection.getFirstElement() instanceof JSFLibrary) {
			curLibrary = (JSFLibrary) selection.getFirstElement();
			workingCopyLibrary = curLibrary.getWorkingCopy();
		} else {
			isNew = true;
			workingCopyLibrary = JSFLibraryRegistryFactory.eINSTANCE.createJSFLibrary();
		}
		setWindowTitle(isNew ? Messages.JSFLibraryWizard_CreateJSFLibrary : Messages.JSFLibraryWizard_EditJSFLibrary);
	}

	/**
	 * Updates the JSF Library instance with the values from the working copy and 
	 * persists the registry.
	 * 
	 * If editing a library reference, referencing java models will be updated.
	 *  
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean performFinish() 
	{
		final String name = jsfLibraryEditControl.getJSFLibraryName();
		final boolean isDeployed = jsfLibraryEditControl.getIsDeployed();
		final JSFVersion version = jsfLibraryEditControl.getJSFVersion();
		
		workingCopyLibrary.setName(name);
		workingCopyLibrary.setDeployed(isDeployed);
		workingCopyLibrary.setJSFVersion(version);
		
		final String originalID = curLibrary != null ? curLibrary.getID() :workingCopyLibrary.getID();
		
		if (isNew){
			JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry().addJSFLibrary(workingCopyLibrary);
		}
		else {
			curLibrary.updateValues(workingCopyLibrary);
			try {
				JSFLibraryRegistryUtil.rebindClasspathContainerEntries(originalID, workingCopyLibrary.getID(), null);
			} catch (JavaModelException e) {
				JSFUIPlugin.log(IStatus.ERROR, "Exception while updating JSF Library containers", e); //$NON-NLS-1$
			}
		}
		JSFLibraryRegistryUtil.getInstance().saveJSFLibraryRegistry();
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void addPages() {
		page = new JSFLibraryWizardPage(Messages.JSFLibraryWizard_JSFLibrary);
		super.addPage(page);
		page.setWizard(this);
	}

	/**
	 * @return the JSFLibrary being modified by this wizard
	 */
	public JSFLibrary getJSFLibrary() {
		return workingCopyLibrary;
	}

	private class JSFLibraryWizardPage extends WizardPage {

		/**
		 * @param pageName
		 */
		protected JSFLibraryWizardPage(String pageName) {
			super(pageName);
			setDescription(Messages.JSFLibraryWizard_DESCRIPTION);
			setTitle(Messages.JSFLibraryWizard_JSFLibrary);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
		 */
		public boolean isPageComplete() {
			if (modified == false) {
				return false;
			}
			return super.isPageComplete();
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
		 */
		public void createControl(Composite parent) {
			initializeDialogUnits(parent);

			jsfLibraryEditControl = new JSFLibraryEditControl(workingCopyLibrary, parent);
			jsfLibraryEditControl.setLayout(new GridLayout(2, false));
			jsfLibraryEditControl.setLayoutData(new GridData(GridData.FILL_BOTH));

			jsfLibraryEditControl.addValidationListener(new JSFLibraryValidationListener(){
				public void notifyValidation(JSFLibraryValidationEvent e) {
					setErrorMessage(e.getMessage());
					modified = true;
					setPageComplete(getErrorMessage()==null);
				}				
			});

			setControl(jsfLibraryEditControl);
			setPageComplete(false);
		}

	}

}
