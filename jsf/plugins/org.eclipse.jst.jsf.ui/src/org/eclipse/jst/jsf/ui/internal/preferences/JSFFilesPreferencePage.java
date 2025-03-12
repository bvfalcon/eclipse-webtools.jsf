/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Jens Lukowski/Innoopract - initial renaming/restructuring
 *     Oracle - adapted for JSF tooling
 *     
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.preferences;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.provisional.contenttype.ContentTypeIdForJSF;
import org.eclipse.jst.jsf.ui.internal.JSFUiPlugin;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.wst.xml.ui.internal.preferences.XMLFilesPreferencePage;

/***/
public class JSFFilesPreferencePage extends XMLFilesPreferencePage {

    protected Control createContents(Composite parent) {
        Composite composite = createScrolledComposite(parent);
        createContentsForCreatingGroup(composite);

        setSize(composite);
        loadPreferences();
        return composite;
    }

    /**
     * @see org.eclipse.jface.preference.PreferencePage#doGetPreferenceStore()
     */
    protected IPreferenceStore doGetPreferenceStore() {
        return JSFUiPlugin.getDefault().getPreferenceStore();
    }

    /**
     * @see org.eclipse.wst.xml.ui.internal.preferences.XMLFilesPreferencePage#getContentType()
     */
    protected IContentType getContentType() {
        return Platform.getContentTypeManager().getContentType(ContentTypeIdForJSF.ContentTypeID_JSF);
    }

    /**
     * @see org.eclipse.wst.xml.ui.internal.preferences.XMLFilesPreferencePage#doSavePreferenceStore()
     */
    protected void doSavePreferenceStore() {
        JSFCorePlugin.getDefault().savePluginPreferences(); // model
    }

    /**
     * @see org.eclipse.wst.xml.ui.internal.preferences.XMLFilesPreferencePage#getModelPreferences()
     */
    protected Preferences getModelPreferences() {
        return JSFCorePlugin.getDefault().getPluginPreferences();
    }
}
