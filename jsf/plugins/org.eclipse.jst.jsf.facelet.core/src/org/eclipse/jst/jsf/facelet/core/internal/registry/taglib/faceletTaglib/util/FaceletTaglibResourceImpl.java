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
/**
 * <copyright>
 * </copyright>
 *
 * $Id: FaceletTaglibResourceImpl.java,v 1.1 2010/03/18 06:24:41 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.impl.SAXXMLHandler;
import org.eclipse.emf.ecore.xmi.impl.XMLLoadImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage;
import org.xml.sax.helpers.DefaultHandler;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.util.FaceletTaglibResourceFactoryImpl
 * @generated
 */
public class FaceletTaglibResourceImpl extends XMLResourceImpl
{
    /**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated
	 */
    public FaceletTaglibResourceImpl(URI uri)
    {
		super(uri);
	}

    @Override
    protected XMLLoad createXMLLoad()
    {
        return new XMLLoadImpl(createXMLHelper())
        {

            @Override
            protected DefaultHandler makeDefaultHandler()
            {
                return new SAXXMLHandler(resource, helper, options)
                {

                    @Override
                    protected void handleXMLNSAttribute(final String attrib,
                            final String value)
                    {
                        String useValue = value;
                        if ("xmlns".equals(attrib)) //$NON-NLS-1$
                        {
                            useValue = FaceletTaglibPackage.eNS_URI;
                        }
                        super.handleXMLNSAttribute(attrib, useValue);
                    }
                    
                };
            }
            
        };
    }

} //FaceletTaglibResourceImpl
