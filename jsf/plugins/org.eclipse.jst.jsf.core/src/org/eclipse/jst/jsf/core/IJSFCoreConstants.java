/*******************************************************************************
 * Copyright (c) 2001, 2018 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.core;

import org.eclipse.wst.common.project.facet.core.IProjectFacet;

/**
 * JSF Core framework constants
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author cbateman
 *
 */
public final class IJSFCoreConstants 
{
    /**
     * The global id for the JSF facet
     * TODO: align with extensioin point through plugin.properties
     */
    public static final String JSF_CORE_FACET_ID = "jst.jsf"; //$NON-NLS-1$
    /**
     * The facet version for a JSF 1.0 project
     * TODO: align with extensioin point through plugin.properties
     */
    public final static String                  FACET_VERSION_1_0 = "1.0"; //$NON-NLS-1$
    /**
     * The constant id for a JSF 1.0 project
     */
    public final static String                  JSF_VERSION_1_0 = FACET_VERSION_1_0;
    /**
     * The facet version for a JSF 1.1 project
     * TODO: align with extensioin point through plugin.properties
     */
    public final static String                  FACET_VERSION_1_1 = "1.1"; //$NON-NLS-1$
    /**
     * The constant id for a JSF 1.1 project
     */
    public final static String                  JSF_VERSION_1_1 = FACET_VERSION_1_1;
    /**
     * The facet version for a JSF 1.2 project
     * TODO: align with extensioin point through plugin.properties
     */
    public final static String                  FACET_VERSION_1_2 = "1.2";//$NON-NLS-1$
    /**
     * The constant id for a JSF 1.2 project
     */
    public final static String                  JSF_VERSION_1_2 = FACET_VERSION_1_2;

    /**
     * The facet version for a JSF 2.0 project
     */
    public static final String                  FACET_VERSION_2_0 = "2.0"; //$NON-NLS-1$
    /**
     * The version string for a JSF 2.0 project
     */
    public static final String JSF_VERSION_2_0 = FACET_VERSION_2_0;
    
    /**
     * The facet version for a JSF 2.1 project
     */
    public static final String                  FACET_VERSION_2_1 = "2.1"; //$NON-NLS-1$
    /**
     * The version string for a JSF 2.1 project
     */
    public static final String JSF_VERSION_2_1 = FACET_VERSION_2_1;
    
    /**
     * The facet version for a JSF 2.2 project
     */
    public static final String                  FACET_VERSION_2_2 = "2.2"; //$NON-NLS-1$
    /**
     * The version string for a JSF 2.2 project
     */
    public static final String JSF_VERSION_2_2 = FACET_VERSION_2_2;

	/**
	 * The facet version for a JSF 2.3 project
	 */
	public static final String FACET_VERSION_2_3 = "2.3"; //$NON-NLS-1$
	/**
	 * The version string for a JSF 2.3 project
	 */
	public static final String JSF_VERSION_2_3 = FACET_VERSION_2_3;

	/**
	 * The facet version for a Jakarta Faces 3.0 project
	 */
	public static final String FACET_VERSION_3_0 = "3.0"; //$NON-NLS-1$
	/**
	 * The version string for a Jakarta Faces 3.0 project
	 */
	public static final String JSF_VERSION_3_0 = FACET_VERSION_3_0;

	/**
	 * The facet version for a Jakarta Faces 4.0 project
	 */
	public static final String FACET_VERSION_4_0 = "4.0"; //$NON-NLS-1$
	/**
	 * The version string for a Jakarta Faces 4.0 project
	 */
	public static final String JSF_VERSION_4_0 = FACET_VERSION_4_0;

	/**
	 * The facet version for a Jakarta Faces 4.1 project
	 */
	public static final String FACET_VERSION_4_1 = "4.1"; //$NON-NLS-1$
	/**
	 * The version string for a Jakarta Faces 4.1 project
	 */
	public static final String JSF_VERSION_4_1 = FACET_VERSION_4_1;

    /**
     * @param facet
     * @return true if the facet is a jsf facet.
     * 
     */
    public static boolean isJSFFacet(final IProjectFacet facet)
    {
        return JSF_CORE_FACET_ID.equals(facet.getId());
    }
    private IJSFCoreConstants()
    {
        // no instantiation
    }
}
