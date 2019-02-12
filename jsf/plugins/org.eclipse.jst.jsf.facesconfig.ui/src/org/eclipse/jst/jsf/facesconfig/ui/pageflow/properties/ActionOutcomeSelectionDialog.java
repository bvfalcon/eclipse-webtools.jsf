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

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.Separator;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.PageflowMessages;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.JSPUtil;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

/**
 * This dialog is used to edit outcome property of link component of Pageflow
 * Designer.
 * 
 * @author Xiao-guang Zhang
 */
public class ActionOutcomeSelectionDialog extends Dialog {

	/** property's name text control */
	private Text actionText;

	/** linked source jsp file name */
	private String jspName;

	/** the current outcome */
	private String outcome;

	/** action table control */
	private Table actionTable;

	/** Column width of component name and action */
	private static final int COMPONENT_COL_WIDTH = 160;

	private static final int ACTION_COL_WIDTH = 160;

	/** Dialog default height and width */
	private static final int DIALOG_DEFAULT_WIDTH = 400;

	private static final int DIALOG_DEFAULT_HEIGHT = 300;

	private static String JSF_EL_LEFT_BRACE = "#{"; //$NON-NLS-1$

	private static String JSF_EL_RIGHT_BRACE = "}"; //$NON-NLS-1$

	/** the listener for the text modification */
	private ModifyListener modifyListener;

	/**
	 * @param parentShell
	 * @param outcome
	 * @param jspName
	 */
	public ActionOutcomeSelectionDialog(Shell parentShell, String outcome,
			String jspName) {
		super(parentShell);
		this.outcome = outcome;
		this.jspName = jspName;

		modifyListener = new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				verifyComplete();
			}
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Dialog#createDialogArea(Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		GridLayout gridLayout;

		Composite container = (Composite) super.createDialogArea(parent);
		gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		gridLayout.marginWidth = 10;
		gridLayout.marginHeight = 10;
		container.setLayout(gridLayout);
		GridData gd = new GridData(GridData.FILL_BOTH);
		// gd.widthHint = 300;
		gd.heightHint = DIALOG_DEFAULT_HEIGHT;
		gd.widthHint = DIALOG_DEFAULT_WIDTH;
		container.setLayoutData(gd);

		Label choiceLabel = new Label(container, SWT.LEFT);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		// gd.widthHint = 100;
		choiceLabel.setLayoutData(gd);
		// Pageflow.Property.Action.OutcomeSelectionDialog.Choice = Enter the
		// outcome or select one from action list below:
		choiceLabel
				.setText(PageflowMessages.Pageflow_Property_Action_OutcomeSelectionDialog_Choice);

		createOutcomeSection(container);

		createActionListSection(container);

		return container;
	}

	/**
	 * create a Outcome input section
	 * 
	 * @param container
	 */
	protected void createOutcomeSection(Composite container) {
		Composite outcomeSection = new Composite(container, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.numColumns = 1;
		gl.marginWidth = 0;
		outcomeSection.setLayout(gl);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		outcomeSection.setLayoutData(gd);

		actionText = new Text(outcomeSection, SWT.BORDER);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.FILL_HORIZONTAL);
		actionText.setLayoutData(gd);

		actionText.addModifyListener(modifyListener);

		if (outcome != null && outcome.length() > 0) {
			actionText.setText(outcome);
		}
	}

	/**
	 * create action list table section
	 * 
	 * @param container
	 */
	protected void createActionListSection(Composite container) {
		final Group actionSection = new Group(container, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		actionSection.setLayout(gridLayout);
		GridData gd = new GridData(GridData.FILL_BOTH);
		actionSection.setLayoutData(gd);

		String actionListTitle = null;
		if (jspName != null && jspName.length() > 0) {
			actionListTitle = NLS.bind(PageflowMessages.Pageflow_Property_Action_OutcomeSelectionDialog_ActionListTableWithJSP_Title, jspName);			
		} else {
			actionListTitle = PageflowMessages.Pageflow_Property_Action_OutcomeSelectionDialog_ActionListTable_Title;
		}

		// Pageflow.Property.Action.OutcomeSelectionDialog.ActionListTable.Title
		// = Actions
		actionSection.setText(actionListTitle);

		actionTable = new Table(actionSection, SWT.BORDER | SWT.FULL_SELECTION);
		actionTable.setLayoutData(new GridData(GridData.FILL_BOTH));
		actionTable.setHeaderVisible(true);
		actionTable.setLinesVisible(true);
		TableLayout layout = new TableLayout();
		actionTable.setLayout(layout);

		layout.addColumnData(new ColumnPixelData(COMPONENT_COL_WIDTH));
		layout.addColumnData(new ColumnPixelData(ACTION_COL_WIDTH));
		TableColumn componentCol = new TableColumn(actionTable, SWT.NONE);
		// Pageflow.Property.Action.OutcomeSelectionDialog.ActionListTable.Component
		// = Component ID
		componentCol
				.setText(PageflowMessages.Pageflow_Property_Action_OutcomeSelectionDialog_ActionListTable_Component);
		TableColumn actionCol = new TableColumn(actionTable, SWT.NONE);

		// Pageflow.Property.Action.OutcomeSelectionDialog.ActionListTable.Action
		// = Action
		actionCol
				.setText(PageflowMessages.Pageflow_Property_Action_OutcomeSelectionDialog_ActionListTable_Action);

		actionTable.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				TableItem selItem = (TableItem) e.item;
				String action = selItem.getText(1);
				if (action != null && action.length() > 0)
					actionText.setText(action);
			}
		});

		addActionsInJSP();

	}

	/**
	 * Creates a separator line. Expects a <code>GridLayout</code> with at
	 * least 1 column.
	 * 
	 * @param composite
	 *            the parent composite
	 * @param nColumns
	 *            number of columns to span
	 */
	protected void createSeparator(Composite composite, int nColumns) {
		(new Separator(SWT.SEPARATOR | SWT.HORIZONTAL)).doFillIntoGrid(null,
				composite, nColumns, convertHeightInCharsToPixels(1));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Dialog#createButtonsForButtonBar(Composite)
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);

		verifyComplete();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Dialog#configureShell(Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);

		// Pageflow.Property.Action.OutcomeSelectionDialog.Title = Outcome
		// Selection
		newShell
				.setText(PageflowMessages.Pageflow_Property_Action_OutcomeSelectionDialog_Title);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Dialog#buttonPressed(int)
	 */
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.CANCEL_ID) {
			setReturnCode(CANCEL);
			close();
			return;
		} else if (buttonId == IDialogConstants.OK_ID) {
			setReturnCode(OK);

			outcome = actionText.getText();

			close();
			return;
		}
		super.buttonPressed(buttonId);
	}

	/**
	 * 
	 * check the dialog inputs are complete or not, and set the OK button enable
	 * or not.
	 */
	private void verifyComplete() {
		if (getButton(IDialogConstants.OK_ID) != null) {
			getButton(IDialogConstants.OK_ID).setEnabled(isDialogComplete());
		}
	}

	/**
	 * check the dialog inputs are complete or not.
	 * 
	 * @return
	 */
	private boolean isDialogComplete() {
		return isValidName();
	}

	/**
	 * the input name is valid or not
	 * 
	 * @return
	 */
	private boolean isValidName() {
		return actionText.getText().length() > 0;
	}

	/**
	 * get the selected action in the action table
	 * 
	 * @return - selected action
	 */
	public String getSelectedAction() {
		return outcome;
	}

	/**
	 * add actions in the source jsp file to the action list table control
	 * 
	 */
	private void addActionsInJSP() {
		List actionNodes = JSPUtil.getActionListInJSPFile(jspName);

		if (actionNodes != null) {
			for (int i = 0, n = actionNodes.size(); i < n; i++) {
				Element node = (Element) actionNodes.get(i);
				StringBuffer componentName = new StringBuffer();

				Attr idAttr = node.getAttributeNode("id"); //$NON-NLS-1$
				if (idAttr != null)
					componentName.append(idAttr.getNodeValue());

				componentName.append("(").append(node.getTagName()).append(")"); //$NON-NLS-1$ //$NON-NLS-2$

				Attr actionAttr = node.getAttributeNode("action"); //$NON-NLS-1$
				if (actionAttr != null) {
					String action = actionAttr.getValue();
					if (isValidEL(action))
						addActionTableItem(componentName.toString(), action);
				}
			}
		}
	}

	/**
	 * @param expressionString
	 * @return true if expressionString is valid EL
	 */
	public static boolean isValidEL(String expressionString) {
		if (expressionString == null || expressionString.length() == 0)
			return true;

		return (expressionString.startsWith(JSF_EL_LEFT_BRACE) && expressionString
				.endsWith(JSF_EL_RIGHT_BRACE))
				|| (!expressionString.startsWith(JSF_EL_LEFT_BRACE) && !expressionString
						.endsWith(JSF_EL_RIGHT_BRACE));
	}

	/**
	 * add a new action item in the previous jsp page.
	 * 
	 * @param componentName -
	 *            JSF component element
	 * @param action -
	 *            value of action's attribute
	 */
	private void addActionTableItem(String componentName, String action) {
		if (componentName != null && componentName.length() > 0
				&& action != null && action.length() > 0) {
			TableItem item = new TableItem(actionTable, SWT.NONE);
			item.setText(0, componentName);
			item.setText(1, action);
		}
	}
}
