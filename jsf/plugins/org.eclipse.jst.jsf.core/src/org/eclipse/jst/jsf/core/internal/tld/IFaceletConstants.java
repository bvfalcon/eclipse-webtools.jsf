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
package org.eclipse.jst.jsf.core.internal.tld;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Adds facelet-specific constants.*
 * 
 * @author cbateman
 * 
 */
public final class IFaceletConstants implements ITLDConstants
{
    /**
     * The set of all standard Facelet tag libraries.
     */
    public static final Set<String> ALL_FACELET_TAGLIBS;
    /**
     * The uri for the composite taglib (Facelets only)
     */
    public static final String URI_JSF_COMPOSITE = "http://java.sun.com/jsf/composite"; //$NON-NLS-1$
    /**
     * The uri for the composite taglib (Facelets only) (Jakarta EE)
     */
    public static final String URI_JSF_COMPOSITE_JAKARTA = "jakarta.faces.composite"; //$NON-NLS-1$
    /**
     * The uri for the composite taglib (Facelets only) (JCP)
     */
    public static final String URI_JSF_COMPOSITE_JCP = "http://xmlns.jcp.org/jsf/composite"; //$NON-NLS-1$
    /**
     * The uri for the ui taglib (Facelets only)
     */
    public static final String URI_JSF_UI = "http://java.sun.com/jsf/facelets"; //$NON-NLS-1$
    /**
     * The uri for the ui taglib (Facelets only) (Jakarta EE)
     */
    public static final String URI_JSF_UI_JAKARTA = "jakarta.faces.facelets"; //$NON-NLS-1$
    /**
     * The uri for the ui taglib (Facelets only) (JCP)
     */
    public static final String URI_JSF_UI_JCP = "http://xmlns.jcp.org/jsf/facelets"; //$NON-NLS-1$
    /**
     * The uri for the jsf implementation of JSTL core tags (Facelets only)
     */
    public static final String URI_JSF_JSTL_CORE = "http://java.sun.com/jsp/jstl/core"; //$NON-NLS-1$
    /**
     * The uri for the jsf implementation of JSTL core tags (Facelets only) (Jakarta EE)
     */
    public static final String URI_JSF_JSTL_CORE_JAKARTA = "jakarta.tags.core"; //$NON-NLS-1$
    /**
     * The uri for the jsf implementation of JSTL function tags (Facelets only)
     */
    public static final String URI_JSF_JSTL_FUNCTIONS = "http://java.sun.com/jsp/jstl/functions"; //$NON-NLS-1$
    /**
     * The uri for the jsf implementation of JSTL function tags (Facelets only) (Jakarta EE)
     */
    public static final String URI_JSF_JSTL_FUNCTIONS_JAKARTA = "jakarta.tags.functions"; //$NON-NLS-1$
    
    static
    {
        Set<String>  taglibs = new HashSet<String>();
        taglibs.add(URI_JSF_HTML);
        taglibs.add(URI_JSF_HTML_JAKARTA);
        taglibs.add(URI_JSF_HTML_JCP);
        taglibs.add(URI_JSF_CORE);
        taglibs.add(URI_JSF_CORE_JAKARTA);
        taglibs.add(URI_JSF_CORE_JCP);
        taglibs.add(URI_JSF_COMPOSITE);
        taglibs.add(URI_JSF_COMPOSITE_JAKARTA);
        taglibs.add(URI_JSF_COMPOSITE_JCP);
        taglibs.add(URI_JSF_UI);
        taglibs.add(URI_JSF_UI_JAKARTA);
        taglibs.add(URI_JSF_UI_JCP);
        taglibs.add(URI_JSF_JSTL_CORE);
        taglibs.add(URI_JSF_JSTL_CORE_JAKARTA);
        taglibs.add(URI_JSF_JSTL_FUNCTIONS);
        taglibs.add(URI_JSF_JSTL_FUNCTIONS_JAKARTA);
        
        ALL_FACELET_TAGLIBS = Collections.unmodifiableSet(taglibs);
    }

}
