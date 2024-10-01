/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.common.internal;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;

/**
 * Utility JSF methods
 * @author cbateman
 *
 */
public final class JSFUtil 
{
    private final static String  CTYPE_JSFSOURCE = 
        "org.eclipse.jst.jsf.core.jsfsource"; //$NON-NLS-1$
    private final static String  CTYPE_JSFFRAGMENTSOURCE = 
        "org.eclipse.jst.jsf.core.jsffragmentsource"; //$NON-NLS-1$
    /**
     * @param contentType
     * @return true if contentType is one of the content types registered
     * for JSP files
     */
    public static boolean isJSFContentType(final String contentType)
    {
        return CTYPE_JSFSOURCE.equals(contentType)
                    || CTYPE_JSFFRAGMENTSOURCE.equals(contentType);
    }
    
    /**
     * @param file
     * @return true if file is associated with a JSP or JSP fragment content type
     */
    public static boolean isJSFContentType(final IFile file)
    {
    	final boolean isJSPSource = isJSFSource(file);
        if (isJSPSource)
        {
            return true;
        }

        final boolean isJSPFragment = isJSFFragment(file);
        
        if  (isJSPFragment)
        {
        	return true;
        }
        
        return false;
    }
    
    private JSFUtil()
    {
        // no instantiation
    }

	/**
	 * @param file
	 * @return true if file is associated with the JSP source content type
	 * (returns if JSP fragment)
	 */
	public static boolean isJSFSource(IFile file) {
		return isAssociatedWithContentType(file, CTYPE_JSFSOURCE);
	}

	/**
	 * @param file
	 * @return true if the file is associated with the JSP fragment content type
	 */
	public static boolean isJSFFragment(IFile file) {
		return isAssociatedWithContentType(file, CTYPE_JSFFRAGMENTSOURCE);
	}
	
	private static boolean isAssociatedWithContentType(final IFile file, final String contentType)
	{
        final IContentTypeManager typeManager = Platform.getContentTypeManager();
        IContentType jspContentType = 
            typeManager.getContentType(contentType);
        if (jspContentType != null
                && jspContentType.isAssociatedWith(file.getName()))
        {
            return true;
        }
      
        return false;
	}
}
