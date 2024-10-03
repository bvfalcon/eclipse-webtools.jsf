/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.jsf.common.internal.policy.OrderedListProvider;
import org.eclipse.jst.jsf.common.internal.policy.OrderedListProvider.OrderableObject;
import org.eclipse.jst.jsf.common.ui.internal.preferences.StrategyOrderingPanel;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry;
import org.eclipse.jst.jsf.designtime.internal.view.model.TagRegistryFactory;
import org.eclipse.jst.jsf.designtime.internal.view.model.TagRegistryFactory.TagRegistryFactoryException;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.registry.TLDRegistryManager;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.registry.TLDRegistryPreferences;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.registry.TLDRegistryPreferences.StrategyIdentifier;
import org.eclipse.jst.jsf.ui.internal.JSFUIPlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Preferences for the JSP tag registry.
 * 
 * @author cbateman
 * 
 */
public class JSPTagRegistryPreferencePage extends PreferencePage implements
        IWorkbenchPreferencePage
{
    private final TLDRegistryPreferences _tldRegistryPreferences;
    private StrategyOrderingPanel        _panel;
    private OrderedListProvider       _provider;

    /**
     * Default constructor: required by the preference page
     */
    public JSPTagRegistryPreferencePage()
    {
        _tldRegistryPreferences = new TLDRegistryPreferences(
                getPreferenceStore());
        _tldRegistryPreferences.load();
    }

    @Override
    protected Control createContents(final Composite parent)
    {
        _provider = _tldRegistryPreferences.getOrderedListProvider();
        _panel = new StrategyOrderingPanel(
                _provider,
                new MyLabelProvider(),
                Messages.JSPTagRegistryPreferencePage_PanelDescription);
        final Control contents = _panel.createContents(parent);
        _panel.refresh();
        return contents;
    }

    public void init(final IWorkbench workbench)
    {
        // do nothing
    }

    protected void performApply()
    {
        if (_tldRegistryPreferences.isDirty())
        {
            final MessageDialogWithToggle dialog = 
                MessageDialogWithToggle.openOkCancelConfirm(getShell(), 
                    Messages.JSPTagRegistryPreferencePage_0, 
                    Messages.JSPTagRegistryPreferencePage_1, 
                    Messages.JSPTagRegistryPreferencePage_2, false, null, null);
    
            if (dialog.getReturnCode() == Window.OK)
            {
                _tldRegistryPreferences.commit(getPreferenceStore());
                final TagRegistryFactory myRegistryFactory = new TLDRegistryManager.MyRegistryFactory();
                for (final IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects())
                {
                    if (project.isAccessible()
                            && myRegistryFactory.isInstance(project))
                    {
                        try
                        {
                            final ITagRegistry tagRegistry = 
                                myRegistryFactory.createTagRegistry(project);
                            tagRegistry.refresh(null, dialog.getToggleState());
                        } 
                        catch (TagRegistryFactoryException e)
                        {
                            JSFUIPlugin.log(IStatus.ERROR, "Error getting JSP tag registry on project: "+project.getName(), e); //$NON-NLS-1$
                        }
                    }
                }
            }
        }
    }

    protected void performDefaults()
    {
        _tldRegistryPreferences.setDefaults();
        //_provider.setStrategies(_tldRegistryPreferences.getStrategyIdOrdering());
        _panel.refresh();
        super.performDefaults();
    }

    public boolean performOk()
    {
        performApply();
        return true;
    }

    @Override
    protected IPreferenceStore doGetPreferenceStore()
    {
        return JSFCorePlugin.getDefault().getPreferenceStore();
    }


    private static class MyLabelProvider extends LabelProvider implements
            ITableLabelProvider
    {
        public Image getColumnImage(final Object element, final int columnIndex)
        {
            // no image.
            return null;
        }

        public String getColumnText(final Object element, final int columnIndex)
        {
            switch (columnIndex)
            {
                case 0:
                    if (element instanceof OrderableObject)
                    {
                        return ((StrategyIdentifier)(((OrderableObject) element).getObject())).getDisplayName();
                    }
                default:
                    Assert.isTrue(false);
                    return null;
            }
        }
    }
}
