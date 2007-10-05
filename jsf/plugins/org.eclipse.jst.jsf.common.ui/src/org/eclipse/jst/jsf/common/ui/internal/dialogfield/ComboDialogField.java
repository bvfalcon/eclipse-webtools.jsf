/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.ui.internal.dialogfield;

import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * Dialog field containing a label and a combo control.
 * 
 * If the combo control is editable, then when user type in the field, will only
 * fire dialogFieldChanged, without dialogFieldApplied just as text control. But
 * when user change selection using the drop down, will fire both
 * dialogFieldChanged and dialogFieldApplied
 * 
 * @author mengbo
 */
public class ComboDialogField extends DialogFieldBase implements
		ISupportTextValue {
	final static private int WIDTH_HINT = 10;

	private String _text;

	private int _selectionIndex;

	private String[] _items;

	private CCombo _comboControl;

	private ModifyListener _modifyListener;

	private int _flags;

	private boolean _pending = false;

	private Map _entryMap;

	/**
	 * @param flags
	 */
	public ComboDialogField(int flags) {
		super();
		_text = ""; //$NON-NLS-1$
		_items = new String[0];
		_flags = flags;
		_selectionIndex = -1;
	}

	// ------- layout helpers

	/*
	 * @see DialogField#doFillIntoGrid
	 */
	public Control[] doFillIntoGrid(FormToolkit toolkit, Composite parent,
			int nColumns) {
		assertEnoughColumns(nColumns);

		Control requiredLabel = getRequiredLabelControl(toolkit, parent);
		requiredLabel.setLayoutData(gridDataForLabel(1));

		Control label = getLabelControl(toolkit, parent);
		label.setLayoutData(gridDataForLabel(1));

		CCombo combo = getComboControl(toolkit, parent);
		combo.setLayoutData(gridDataForCombo(nColumns - 2));

		return new Control[] { requiredLabel, label, combo };
	}

	/*
	 * @see DialogField#getNumberOfControls
	 */
	public int getNumberOfControls() {
		return 3;
	}

	private static GridData gridDataForCombo(int span) {
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = false;
		gd.horizontalSpan = span;
		gd.widthHint = WIDTH_HINT;
		return gd;
	}

	// ------- focus methods

	/*
	 * @see DialogField#setFocus
	 */
	public boolean setFocus() {
		if (isOkToUse(_comboControl)) {
			_comboControl.setFocus();
		}
		return true;
	}

	// ------- ui creation

	/**
	 * Creates or returns the created combo control.
	 * @param toolkit 
	 * 
	 * @param parent
	 *            The parent composite or <code>null</code> when the widget
	 *            has already been created.
	 * @return the custom combo control
	 */
	public CCombo getComboControl(FormToolkit toolkit, Composite parent) {
		if (_comboControl == null) {
			assertCompositeNotNull(parent);
			_modifyListener = new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					doModifyText(e);
				}
			};
			SelectionListener selectionListener = new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					doSelectionChanged(e);
				}

				public void widgetDefaultSelected(SelectionEvent e) {
					handleDefaultSelection(e);
				}
			};

			if (toolkit != null) {
				_comboControl = new CCombo(parent, _flags);
				toolkit.adapt(_comboControl);
			} else {
				_comboControl = new CCombo(parent, _flags | SWT.BORDER);
				_comboControl.setBackground(Display.getCurrent()
						.getSystemColor(SWT.COLOR_LIST_BACKGROUND));
			}

			// moved up due to 1GEUNW2
			_comboControl.setItems(_items);
			if (_selectionIndex != -1) {
				_comboControl.select(_selectionIndex);
			} else {
				_comboControl.setText(_text);
			}
			_comboControl.setFont(parent.getFont());
			_comboControl.addModifyListener(_modifyListener);
			_comboControl.addSelectionListener(selectionListener);
			_comboControl.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent e) {
					doFocusLost(e);
				}
			});
			_comboControl.setEnabled(isEnabled());
			_comboControl.setToolTipText(getToolTip());
		}
		return _comboControl;
	}

	private void handleDefaultSelection(SelectionEvent e) {
		// commit value
		if (_pending) {
			_pending = false;
			dialogFieldApplied();
		}
	}

	private void doFocusLost(FocusEvent e) {
		if (_pending) {
			_pending = false;
			dialogFieldApplied();
		}
	}

	private void doModifyText(ModifyEvent e) {
		if (isOkToUse(_comboControl)) {
			_text = getEntryKey(_comboControl.getText());
			_selectionIndex = _comboControl.getSelectionIndex();
		}
		_pending = true;
		dialogFieldChanged();
	}

	private void doSelectionChanged(SelectionEvent e) {
		if (isOkToUse(_comboControl)) {
			_items = _comboControl.getItems();
			_text = getEntryKey(_comboControl.getText());
			_selectionIndex = _comboControl.getSelectionIndex();
		}
		_pending = false;
		dialogFieldChangedAndApplied();
	}

	// ------ enable / disable management

	/*
	 * @see DialogField#updateEnableState
	 */
	protected void updateEnableState() {
		super.updateEnableState();
		if (isOkToUse(_comboControl)) {
			_comboControl.setEnabled(isEnabled());
		}
	}

	// ------ text access


	/**
	 * Sets the combo items. Triggers a dialog-changed event.
	 * @param items 
	 */
	public void setItems(String[] items) {
		_items = items;
		if (isOkToUse(_comboControl)) {
			_comboControl.setItems(items);
		}
		_pending = false;
		// dialogFieldChangedAndApplied();
	}

	/**
	 * Gets the text.
	 */
	public String getText() {
		return _text;
	}

	/**
	 * Sets the text. Triggers a dialog-changed event.
	 */
	public void setText(String text) {
		setTextWithoutUpdate(text);
		_pending = false;
		dialogFieldChangedAndApplied();
	}

	/**
	 * Selects an item.
	 * @param index 
	 */
	public void selectItem(int index) {
		if (isOkToUse(_comboControl)) {
			_comboControl.select(index);
		} else {
			if (index >= 0 && index < _items.length) {
				_text = getEntryKey(_items[index]);
				_selectionIndex = index;
			}
		}
		dialogFieldChangedAndApplied();
	}

	/**
	 * Sets the text without triggering a dialog-changed event.
	 */
	public void setTextWithoutUpdate(String text) {
		_pending = false;
		if (text == null) {
			text = "";//$NON-NLS-1$
		}
		_text = text;
		if (isOkToUse(_comboControl)) {
			_comboControl.removeModifyListener(_modifyListener);
			_comboControl.setText(getEntryValue(text));
			_comboControl.addModifyListener(_modifyListener);
		}
	}

	private String getEntryValue(String key) {
		if (_entryMap == null || !_entryMap.containsKey(key)) {
			return key;
		}
		return _entryMap.get(key).toString();
	}

	private String getEntryKey(String value) {
		if (_entryMap == null || !_entryMap.containsValue(value)) {
			return value;
		}

		Entry[] entries = (Entry[]) _entryMap.entrySet().toArray(new Entry[0]);
		for (int i = 0; i < entries.length; i++) {
			if (entries[i].getValue() == value
					|| (value != null && value.equals(entries[i].getValue()))) {
				return entries[i].getKey().toString();
			}
		}
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField#handleGrabHorizontal()
	 */
	public void handleGrabHorizontal() {
		LayoutUtil.setGrabHorizontal(_comboControl, true);
	}
}
