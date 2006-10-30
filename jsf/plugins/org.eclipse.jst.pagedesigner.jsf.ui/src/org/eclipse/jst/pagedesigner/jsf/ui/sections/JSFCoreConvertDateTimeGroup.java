/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http:// www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui.sections;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.IJSFConstants;
import org.eclipse.jst.pagedesigner.commands.single.ChangeAttributeCommand;
import org.eclipse.jst.pagedesigner.common.dialogfield.DialogField;
import org.eclipse.jst.pagedesigner.common.dialogfield.IDialogFieldApplyListener;
import org.eclipse.jst.pagedesigner.common.dialogfield.StyleComboDialogField;
import org.eclipse.jst.pagedesigner.meta.EditorCreator;
import org.eclipse.jst.pagedesigner.meta.IAttributeDescriptor;
import org.eclipse.jst.pagedesigner.properties.attrgroup.AttributeGroup;
import org.eclipse.jst.pagedesigner.ui.dialogfields.DialogFieldWrapper;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * This group could be used in both section and dialog.
 * 
 * @author mengbo
 * @version 1.5
 */
public class JSFCoreConvertDateTimeGroup extends AttributeGroup
{
    private StyleComboDialogField      _typeField;
    private StyleComboDialogField      _dateStyleField;
    private StyleComboDialogField      _timeStyleField;
    private StyleComboDialogField      _patternField;

    final private static String[] TYPES            = { "date", "time", "both" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                                                   };
    final private static String[] DATESTYLES       = { "default", "short", "medium", "long", "full", "custom" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
                                                   };
    final private static String[] TIMESTYLES       = { "default", "short", "medium", "long", "full", "custom" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
                                                   };
    final private static String[] DATEPATTERNS     = { "", "M/d/yy", "EEE, M/d/yy", "MM/dd/yyyy", "EEE, MM/dd/yyyy", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
            "MMM d, yyyy", "EEE, MMM d, yyyy", "MMMM d, yyyy", "EEEE, MMMM d, yyyy", "MMMM yyyy" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
                                                   };
    final private static String[] TIMEPATTERNS     = { "", "hh:mm", "hh:mm z", "HH:mm z", "HH:mm:ss z" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
                                                   };
    final private static String[] DATETIMEPATTERNS = {
            "", "M/d/yy hh:mm", "EEE, M/d/yy hh:mm", "MM/dd/yyyy HH:mm:ss z", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
            "EEE, MM/dd/yyyy HH:mm:ss z", "MMM d, yyyy HH:mm z", "EEE, MMM d, yyyy HH:mm z", "MMMM d, yyyy HH:mm z", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
            "EEEE, MMMM d, yyyy HH:mm z" //$NON-NLS-1$
                                                   };

    /**
     * @param uri
     * @param tagName
     * @param attrNames
     */
    public JSFCoreConvertDateTimeGroup()
    {
        super(IJMTConstants.URI_JSF_CORE, IJSFConstants.TAG_CONVERTDATETIME, new String[] { IJSFConstants.ATTR_TYPE,
                IJSFConstants.ATTR_DATESTYLE, IJSFConstants.ATTR_TIMESTYLE, IJSFConstants.ATTR_PATTERN});
    }

    protected DialogField createDialogField(String uri, String tag, IAttributeDescriptor attr)
    {
        EditorCreator creator = EditorCreator.getInstance();
        if (attr.getAttributeName().equals(IJSFConstants.ATTR_TYPE))
        {
            DialogFieldWrapper wrapper = (DialogFieldWrapper) creator
                    .createDialogFieldWithWrapper(uri, tag, attr, null);
            _typeField = (StyleComboDialogField) wrapper.getWrappedDialogField();
            return wrapper;
        }
        else if (attr.getAttributeName().equals(IJSFConstants.ATTR_DATESTYLE))
        {
            DialogFieldWrapper wrapper = (DialogFieldWrapper) creator
                    .createDialogFieldWithWrapper(uri, tag, attr, null);
            _dateStyleField = (StyleComboDialogField) wrapper.getWrappedDialogField();
            _dateStyleField.setItems(DATESTYLES);
            return wrapper;
        }
        else if (attr.getAttributeName().equals(IJSFConstants.ATTR_TIMESTYLE))
        {
            DialogFieldWrapper wrapper = (DialogFieldWrapper) creator
                    .createDialogFieldWithWrapper(uri, tag, attr, null);
            _timeStyleField = (StyleComboDialogField) wrapper.getWrappedDialogField();
            _timeStyleField.setItems(TIMESTYLES);
            return wrapper;
        }
        else if (attr.getAttributeName().equals(IJSFConstants.ATTR_PATTERN))
        {
            DialogFieldWrapper wrapper = (DialogFieldWrapper) creator
                    .createDialogFieldWithWrapper(uri, tag, attr, null);
            _patternField = (StyleComboDialogField) wrapper.getWrappedDialogField();
            return wrapper;
        }
        else
        {
            return null;
        }
    }

    public IDialogFieldApplyListener getDialogFieldApplyListener(String uri, String tag, IAttributeDescriptor attr)
    {
        String attribute = attr.getAttributeName();
        if (attribute.equals(IJSFConstants.ATTR_TYPE) || attribute.equals(IJSFConstants.ATTR_DATESTYLE)
                || attribute.equals(IJSFConstants.ATTR_TIMESTYLE))
        {
            IDialogFieldApplyListener listener = new IDialogFieldApplyListener()
            {
                public void dialogFieldApplied(DialogField field)
                {
                    updatePatternItems();
                    updateFieldStatus();
                    updateFieldData();
                }
            };
            return listener;
        }
        else if (attribute.equals(IJSFConstants.ATTR_PATTERN))
        {
            IDialogFieldApplyListener listener = new IDialogFieldApplyListener()
            {
                public void dialogFieldApplied(DialogField field)
                {
                    updateFieldStatus();
                    updateFieldData();
                }
            };
            return listener;
        }
        else
        {
            return null;
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.properties.attrgroup.AttributeGroup#refreshData()
     */

    public void refreshData()
    {
        IDOMElement element = getElement();

        String type = element.getAttribute(IJSFConstants.ATTR_TYPE);
        _typeField.setTextWithoutUpdate(type);

        String dateStyle = element.getAttribute(IJSFConstants.ATTR_DATESTYLE);
        if (!_dateStyleField.getText().equalsIgnoreCase(DATESTYLES[5]))
        {
            _dateStyleField.setTextWithoutUpdate(dateStyle);
        }

        String timeStyle = element.getAttribute(IJSFConstants.ATTR_TIMESTYLE);
        if (!_timeStyleField.getText().equalsIgnoreCase(TIMESTYLES[5]))
        {
            _timeStyleField.setTextWithoutUpdate(timeStyle);
        }

        String pattern = element.getAttribute(IJSFConstants.ATTR_PATTERN);
        _patternField.setTextWithoutUpdate(pattern);

        updateFieldStatus();
    }

    /**
     * 
     */
    private void updateFieldStatus()
    {
        String type = _typeField.getText();
        String dateStyle = _dateStyleField.getText();
        String timeStyle = _timeStyleField.getText();

        if (type.equalsIgnoreCase(TYPES[0]))
        {
            _dateStyleField.setEnabled(true);
            _timeStyleField.setEnabled(false);
            if (dateStyle.equalsIgnoreCase(DATESTYLES[5]))
            {
                _patternField.setEnabled(true);
            }
            else
            {
                _patternField.setEnabled(false);
            }
        }
        else if (type.equalsIgnoreCase(TYPES[1]))
        {
            _dateStyleField.setEnabled(false);
            _timeStyleField.setEnabled(true);
            if (timeStyle.equalsIgnoreCase(TIMESTYLES[5]))
            {
                _patternField.setEnabled(true);
            }
            else
            {
                _patternField.setEnabled(false);
            }
        }
        else if (type.equalsIgnoreCase(TYPES[2]))
        {
            _dateStyleField.setEnabled(true);
            _timeStyleField.setEnabled(true);
            if (dateStyle.equalsIgnoreCase(DATESTYLES[5]) || timeStyle.equalsIgnoreCase(TIMESTYLES[5]))
            {
                _patternField.setEnabled(true);
            }
            else
            {
                _patternField.setEnabled(false);
            }
        }
    }

    private void updatePatternItems()
    {
        String type = _typeField.getText();
        String dateStyle = _dateStyleField.getText();
        String timeStyle = _timeStyleField.getText();

        if (type.equalsIgnoreCase(TYPES[0]) && dateStyle.equalsIgnoreCase(DATESTYLES[5]))
        {
            _patternField.getComboControl(null, null).removeAll();
            _patternField.getComboControl(null, null).setItems(DATEPATTERNS);
        }

        if (type.equalsIgnoreCase(TYPES[1]) && timeStyle.equalsIgnoreCase(TIMESTYLES[5]))
        {
            _patternField.getComboControl(null, null).removeAll();
            _patternField.getComboControl(null, null).setItems(TIMEPATTERNS);
        }

        if (type.equalsIgnoreCase(TYPES[2])
                && (dateStyle.equalsIgnoreCase(DATESTYLES[5]) || timeStyle.equalsIgnoreCase(TIMESTYLES[5])))
        {
            _patternField.getComboControl(null, null).removeAll();
            _patternField.getComboControl(null, null).setItems(DATETIMEPATTERNS);
        }
    }

    private void updateFieldData()
    {
        String type = _typeField.getText();
        String dateStyle = _dateStyleField.getText();
        String timeStyle = _timeStyleField.getText();
        String pattern = _patternField.getText();

        // update the model
        if (!_dateStyleField.isEnabled() || dateStyle.equalsIgnoreCase(DATESTYLES[5]))
        {
            dateStyle = "";
        }

        if (!_timeStyleField.isEnabled() || timeStyle.equalsIgnoreCase(TIMESTYLES[5]))
        {
            timeStyle = "";
        }

        if (!_patternField.isEnabled())
        {
            pattern = "";

        }

        Map map = new HashMap();
        map.put(IJSFConstants.ATTR_TYPE, type);
        map.put(IJSFConstants.ATTR_DATESTYLE, dateStyle);
        map.put(IJSFConstants.ATTR_TIMESTYLE, timeStyle);
        map.put(IJSFConstants.ATTR_PATTERN, pattern);

        ChangeAttributeCommand c = new ChangeAttributeCommand(SectionResources
                .getString("JSFCoreConvertDateTimeSection.ChangeAttribute"), getElement(), map);
        c.execute();
    }
}
