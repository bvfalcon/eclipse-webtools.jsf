/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.designtime.internal.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.source.ISymbolSourceProvider;
import org.eclipse.jst.jsf.context.symbol.source.ISymbolSourceProviderFactory;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;

/**
 * The default view root implementation. Assumes an XML view definition.
 * 
 * @author cbateman
 * 
 */
public class DefaultDTUIViewRoot extends DTUIViewRoot
{
    /**
     * serializable
     */
    private static final long                    serialVersionUID = -6948413077931237435L;
    private final DefaultServices                _defaultServices;
	private DTFacesContext _facesContext;

    /**
     * @param facesContext 
     */
    public DefaultDTUIViewRoot(final DTFacesContext facesContext)
    {
        // TODO: refactor constants
        super(null, null, new ComponentTypeInfo("jakarta.faces.ViewRoot", //$NON-NLS-1$
                "jakarta.faces.component.UIViewRoot", "jakarta.faces.ViewRoot", //$NON-NLS-1$ //$NON-NLS-2$
                null));
        _defaultServices = new DefaultServices();
    	_facesContext = facesContext;
    }

    @Override
    public IAdaptable getServices()
    {
        return _defaultServices;
    }

    private class DefaultServices implements IAdaptable, Serializable
    {
        /**
         * 
         */
        private static final long serialVersionUID = -5220371377352799212L;
        private final XMLViewObjectMappingService   _xmlViewObjectMappingService;

        private DefaultServices()
        {
            _xmlViewObjectMappingService = new XMLViewObjectMappingService();
        }

        public Object getAdapter(final Class adapter)
        {
            if (adapter.equals(XMLViewObjectMappingService.class))
            {
                return _xmlViewObjectMappingService;
            }
            return null;
        }
    }

	@Override
	protected Map<String, ISymbol> doGetMapForScope(final int scopeMask) {
		final Map<String, ISymbol> map = new HashMap<String, ISymbol>();
		
		IProject project = _facesContext.adaptContextObject().getProject();
		for (final Iterator it = JSFCommonPlugin.getSymbolSourceProviders().iterator(); it.hasNext();)
        {
		    final ISymbolSourceProviderFactory  factory = (ISymbolSourceProviderFactory) it.next();
		    final ISymbolSourceProvider provider = factory.createInstance(project);
		    
		    final ISymbol[] symbols = provider.getSymbols(_facesContext.adaptContextObject(), scopeMask);
		    
		    for (int i = 0; i < symbols.length; i++)
		    {
		    	map.put(symbols[i].getName(), symbols[i]);
		    }
        }

		return map;
	}
}
