/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.internal.project.facet;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.jsf.core.JSFVersion;

/**
 * JSF Utils instance for JSF 2.0.
 * 
 * @author cbateman
 * 
 */
/* package: use JSFUtilFactory */class JSFUtils20 extends JSFUtils12
{
    private static final String DEFAULT_DEFAULT_MAPPING_SUFFIX = "xhtml"; //$NON-NLS-1$

    /**
     * @param modelProvider
     */
    protected JSFUtils20(final IModelProvider modelProvider)
    {
        super(JSFVersion.V2_0, modelProvider);
    }

    /**
     * @param jsfVersion
     * @param modelProvider 
     */
    protected JSFUtils20(final JSFVersion jsfVersion, final IModelProvider modelProvider)
    {
        super(jsfVersion, modelProvider);
        if (jsfVersion.compareTo(JSFVersion.V2_0) < 0)
        {
            throw new IllegalArgumentException(
                    "JSFVersion must be at least 2.0"); //$NON-NLS-1$
        }
    }

    @Override
    protected String getDefaultDefaultSuffix()
    {
        return DEFAULT_DEFAULT_MAPPING_SUFFIX;
    }

    private static final Map<String, String> SEARCH_EXPRESSIONS = new LinkedHashMap<>();
    static {
        SEARCH_EXPRESSIONS.put("@this", "current component"); //$NON-NLS-1$ //$NON-NLS-2$
        SEARCH_EXPRESSIONS.put("@all", "all component identifiers"); //$NON-NLS-1$ //$NON-NLS-2$
        SEARCH_EXPRESSIONS.put("@form", "closest ancestor form of current component"); //$NON-NLS-1$ //$NON-NLS-2$
        SEARCH_EXPRESSIONS.put("@none", "no identifiers"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Map<String, String> getSearchExpressions() {
        Map<String, String> result = new LinkedHashMap<>();
        result.putAll(super.getSearchExpressions());
        result.putAll(SEARCH_EXPRESSIONS);
        return result;
    }
}
