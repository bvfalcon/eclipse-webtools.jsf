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
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.section;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.DialogFieldGroup;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.StringDialogField;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.PropertyResources;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author jchoi
 * @version
 */
public abstract class AbstractEditPartGroup extends DialogFieldGroup {
	protected static PropertyResources resource = PropertyResources
			.getInstance();

	private StringDialogField descField;

	private StringDialogField displayNameField;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.common.dialogfield.DialogFieldGroup#initialize()
	 */
	public void initialize() {
		displayNameField = new StringDialogField();
		displayNameField.setLabelText(resource
				.getString("property.displayName"));
		displayNameField
				.setDialogFieldChangeListener(getDefaultChangeListener());
		descField = new StringDialogField();
		;
		descField.setLabelText(resource.getString("property.description"));
		descField.setDialogFieldChangeListener(getDefaultChangeListener());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.common.dialogfield.DialogFieldGroup#layoutDialogFields(org.eclipse.ui.forms.widgets.FormToolkit,
	 *      org.eclipse.swt.widgets.Composite)
	 */
	public void layoutDialogFields(FormToolkit toolkit, Composite parent,
			int col) {
		displayNameField.doFillIntoGrid(toolkit, parent, col);
		descField.doFillIntoGrid(toolkit, parent, col);

	}

	protected void refreshData(PageflowElement elem) {
		descField.setTextWithoutUpdate(elem.getComment());
		displayNameField.setTextWithoutUpdate(elem.getName());
	}

	public StringDialogField getDescField() {
		return descField;
	}

	public StringDialogField getDisplayNameField() {
		return displayNameField;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.common.dialogfield.DialogFieldGroup#initialize()
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.common.dialogfield.DialogFieldGroup#validateDialogFields()
	 */
	public IStatus[] validateDialogFields() {

		return null;
	}

	protected String assertString(String value) {
		return value == null ? "" : value;
	}
}
