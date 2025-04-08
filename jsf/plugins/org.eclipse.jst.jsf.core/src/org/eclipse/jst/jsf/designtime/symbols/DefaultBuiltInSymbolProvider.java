/*******************************************************************************
 * Copyright (c) 2006, 2013 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 * 
 ********************************************************************************/

package org.eclipse.jst.jsf.designtime.symbols;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.context.symbol.ERuntimeSource;
import org.eclipse.jst.jsf.context.symbol.IInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.IMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.source.ISymbolConstants;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.designtime.DesignTimeApplicationManager;
import org.eclipse.jst.jsf.designtime.context.IDTExternalContext;
import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot;

/**
 * Provides the default built-in JSF symbols
 * 
 * Clients may sub-class
 * 
 * @author cbateman
 * 
 */
public class DefaultBuiltInSymbolProvider
{
    private static DefaultBuiltInSymbolProvider INSTANCE;
    private static final JSFSymbolFactory    _symbolFactory;

    /**
     * @return the singleton instance
     */
    public synchronized static DefaultBuiltInSymbolProvider getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new DefaultBuiltInSymbolProvider();
        }

        return INSTANCE;
    }

    private static final String APPLICATION_SCOPE                   = "applicationScope";                //$NON-NLS-1$
    private static final String SESSION_SCOPE                       = "sessionScope";                    //$NON-NLS-1$
    private static final String REQUEST_SCOPE                       = "requestScope";                    //$NON-NLS-1$
    private static final String COOKIE_IMPLICIT_OBJ                 = "cookie";                          //$NON-NLS-1$
    private static final String FACES_CONTEXT_IMPLICIT_OBJ          = "facesContext";                    //$NON-NLS-1$
    private static final String HEADER_IMPLICIT_OBJ                 = "header";                          //$NON-NLS-1$
    private static final String HEADER_VALUES_IMPLICIT_OBJ          = "headerValues";                    //$NON-NLS-1$
    private static final String INIT_PARAM_IMPLICIT_OBJ             = "initParam";                       //$NON-NLS-1$
    private static final String PARAM_IMPLICIT_OBJ                  = "param";                           //$NON-NLS-1$
    private static final String PARAM_VALUES_IMPLICIT_OBJ           = "paramValues";                     //$NON-NLS-1$
    private static final String VIEW_IMPLICIT_OBJ                   = "view";                            //$NON-NLS-1$

    private static final String FACES_CONTEXT_FULLY_QUALIFIED_CLASS 		= "javax.faces.context.FacesContext"; //$NON-NLS-1$
    private static final String FACES_CONTEXT_JAKARTA_FULLY_QUALIFIED_CLASS = "jakarta.faces.context.FacesContext"; //$NON-NLS-1$
    private static final String VIEW_FULLY_QUALIFIED_CLASS          = "javax.faces.component.UIViewRoot"; //$NON-NLS-1$
    private static final String VIEW_JAKARTA_FULLY_QUALIFIED_CLASS          = "jakarta.faces.component.UIViewRoot"; //$NON-NLS-1$
    
    private static final ISymbol SYMBOL_COOKIE_IMPLICIT_OBJ;
    private static final ISymbol SYMBOL_HEADER_IMPLICIT_OBJ;
    private static final ISymbol SYMBOL_HEADER_VALUES_IMPLICIT_OBJ;
    private static final ISymbol SYMBOL_PARAM_IMPLICIT_OBJ;
    private static final ISymbol SYMBOL_PARAM_VALUES_IMPLICIT_OBJ;
    private static final ISymbol SYMBOL_INIT_PARAM_IMPLICIT_OBJ;
    
    //JSF2.0
    private static final String VIEW_SCOPE                          = "viewScope";                      //$NON-NLS-1$
    private static final String FLASH_SCOPE                         = "flash";                          //$NON-NLS-1$
    private static final String CC_IMPLICIT_OBJ                     = "cc";                             //$NON-NLS-1$
    private static final String COMPONENT_IMPLICIT_OBJ              = "component";                      //$NON-NLS-1$
    private static final String RESOURCE_IMPLICIT_OBJ               = "resource";                       //$NON-NLS-1$
    
    private static final String UICOMPONENT_FULLY_QUALIFIED_CLASS               = "javax.faces.component.UIComponent";//$NON-NLS-1$
    private static final String UICOMPONENT_JAKARTA_FULLY_QUALIFIED_CLASS       = "jakarta.faces.component.UIComponent";//$NON-NLS-1$
    
    static
    {
        _symbolFactory = new JSFSymbolFactory();
        // invariant request scope variables
        SYMBOL_COOKIE_IMPLICIT_OBJ = _symbolFactory.createUnknownInstanceSymbol(COOKIE_IMPLICIT_OBJ, ERuntimeSource.BUILT_IN_SYMBOL_LITERAL);
        SYMBOL_HEADER_IMPLICIT_OBJ = _symbolFactory.createUnknownInstanceSymbol(HEADER_IMPLICIT_OBJ, ERuntimeSource.BUILT_IN_SYMBOL_LITERAL);
        SYMBOL_HEADER_VALUES_IMPLICIT_OBJ = _symbolFactory.createUnknownInstanceSymbol(HEADER_VALUES_IMPLICIT_OBJ, ERuntimeSource.BUILT_IN_SYMBOL_LITERAL);
        SYMBOL_PARAM_IMPLICIT_OBJ = _symbolFactory.createUnknownInstanceSymbol(PARAM_IMPLICIT_OBJ, ERuntimeSource.BUILT_IN_SYMBOL_LITERAL);
        SYMBOL_PARAM_VALUES_IMPLICIT_OBJ = _symbolFactory.createUnknownInstanceSymbol(PARAM_VALUES_IMPLICIT_OBJ, ERuntimeSource.BUILT_IN_SYMBOL_LITERAL); 
        
        // invariant application scope variables
        SYMBOL_INIT_PARAM_IMPLICIT_OBJ = _symbolFactory.createUnknownInstanceSymbol(INIT_PARAM_IMPLICIT_OBJ, ERuntimeSource.BUILT_IN_SYMBOL_LITERAL);
    }
    

    /**
     * No direct instantiation -- use getInstance
     * 
     * Made protected to allow sub-classing
     */
    protected DefaultBuiltInSymbolProvider()
    {
        // nothing to do.
    }

    /**
     * @param context
     * @param symbolScopeMask
     * @return all symbols for context in scopes matching symbolScopeMask
     */
    public ISymbol[] getSymbols(final IAdaptable context,
            final int symbolScopeMask)
    {
        final IFile fileContext = FileContextUtil
                .deriveIFileFromContext(context);
        return (ISymbol[]) getSymbolsForScope(fileContext, symbolScopeMask)
                .toArray(ISymbol.EMPTY_SYMBOL_ARRAY);
    }

    /**
     * @param name
     * @param context
     * @param symbolScopeMask
     * @return the symbol in context matching name or null if not found
     */
    public ISymbol getSymbol(final String name, final IAdaptable context,
            final int symbolScopeMask)
    {
        final IFile file = FileContextUtil.deriveIFileFromContext(context);

        ISymbol symbol = null;
        if ((symbolScopeMask & ISymbolConstants.SYMBOL_SCOPE_REQUEST) != 0)
        {
            symbol = getRequestScopeSymbols(file).get(name);
        }
        if ((symbolScopeMask & ISymbolConstants.SYMBOL_SCOPE_SESSION) != 0
                && symbol == null)
        {
            symbol = getSessionScopeSymbols(file).get(name);
        }
        if ((symbolScopeMask & ISymbolConstants.SYMBOL_SCOPE_APPLICATION) != 0
                && symbol == null)
        {
            symbol = getApplicationScopeSymbols(file).get(name);
        }
        if ((symbolScopeMask & ISymbolConstants.SYMBOL_SCOPE_VIEW) != 0
                && symbol == null)
        {
            symbol = getViewScopeSymbols(file).get(name);
        }
        if ((symbolScopeMask & ISymbolConstants.SYMBOL_SCOPE_FLASH) != 0
                && symbol == null)
        {
            symbol = getFlashScopeSymbols(file).get(name);
        }
        

        return symbol;
    }

    /**
     * @param prefix
     * @param context
     * @param symbolScopeMask
     * @return all implicit symbols for context starting with prefix in scopes
     *         matching symbolScopeMask
     */
    public ISymbol[] getSymbols(final String prefix, final IAdaptable context,
            final int symbolScopeMask)
    {
        final IFile file = FileContextUtil.deriveIFileFromContext(context);

        final List<ISymbol> symbols = new ArrayList<ISymbol>();
        if ((symbolScopeMask & ISymbolConstants.SYMBOL_SCOPE_REQUEST) != 0)
        {
            symbols.addAll(getRequestScopeSymbols(file).values());
        }
        if ((symbolScopeMask & ISymbolConstants.SYMBOL_SCOPE_SESSION) != 0)
        {
            symbols.addAll(getSessionScopeSymbols(file).values());
        }
        if ((symbolScopeMask & ISymbolConstants.SYMBOL_SCOPE_APPLICATION) != 0)
        {
            symbols.addAll(getApplicationScopeSymbols(file).values());
        }
        if ((symbolScopeMask & ISymbolConstants.SYMBOL_SCOPE_VIEW) != 0)
        {
             symbols.addAll(getViewScopeSymbols(file).values());
        }     
        if ((symbolScopeMask & ISymbolConstants.SYMBOL_SCOPE_FLASH) != 0)
        {
             symbols.addAll(getFlashScopeSymbols(file).values());
        }         
        return symbols.toArray(ISymbol.EMPTY_SYMBOL_ARRAY);
    }

    private List getSymbolsForScope(final IFile file, final int symbolScopeMask)
    {
        final List symbols = new ArrayList();
        if ((symbolScopeMask & ISymbolConstants.SYMBOL_SCOPE_REQUEST) != 0)
        {
            symbols.addAll(getRequestScopeSymbols(file).values());
        }
        if ((symbolScopeMask & ISymbolConstants.SYMBOL_SCOPE_SESSION) != 0)
        {
            symbols.addAll(getSessionScopeSymbols(file).values());
        }
        if ((symbolScopeMask & ISymbolConstants.SYMBOL_SCOPE_APPLICATION) != 0)
        {
            symbols.addAll(getApplicationScopeSymbols(file).values());
        }
        if ((symbolScopeMask & ISymbolConstants.SYMBOL_SCOPE_VIEW) != 0)
        {
            symbols.addAll(getViewScopeSymbols(file).values());
        }
        if ((symbolScopeMask & ISymbolConstants.SYMBOL_SCOPE_FLASH) != 0)
        {
            symbols.addAll(getFlashScopeSymbols(file).values());
        }

        return symbols;
    }

    private Map<String, ISymbol> getRequestScopeSymbols(final IFile file)
    {
        final Map<String, ISymbol> requestSymbols = new HashMap<String, ISymbol>();

        ISymbol symbol = createScopeSymbol(file,
                ISymbolConstants.SYMBOL_SCOPE_REQUEST, REQUEST_SCOPE);
        requestSymbols.put(symbol.getName(), symbol);
        
        requestSymbols.put(SYMBOL_COOKIE_IMPLICIT_OBJ.getName(), SYMBOL_COOKIE_IMPLICIT_OBJ);
        requestSymbols.put(SYMBOL_HEADER_IMPLICIT_OBJ.getName(), SYMBOL_HEADER_IMPLICIT_OBJ);
        requestSymbols.put(SYMBOL_HEADER_VALUES_IMPLICIT_OBJ.getName(), SYMBOL_HEADER_VALUES_IMPLICIT_OBJ);
        requestSymbols.put(SYMBOL_PARAM_IMPLICIT_OBJ.getName(), SYMBOL_PARAM_IMPLICIT_OBJ);
        requestSymbols.put(SYMBOL_PARAM_VALUES_IMPLICIT_OBJ.getName(), SYMBOL_PARAM_VALUES_IMPLICIT_OBJ);

        IProject project = file.getProject();
        boolean isJakartaEE = JSFVersion.guessAtLeast(JSFVersion.V3_0, project);
        // TODO: these aren't maps; need to find way to handle
        symbol = _symbolFactory.createBeanOrUnknownInstanceSymbol(project,
                isJakartaEE ? FACES_CONTEXT_JAKARTA_FULLY_QUALIFIED_CLASS : FACES_CONTEXT_FULLY_QUALIFIED_CLASS,
                FACES_CONTEXT_IMPLICIT_OBJ, ERuntimeSource.BUILT_IN_SYMBOL_LITERAL);
        requestSymbols.put(symbol.getName(), symbol);

        symbol = _symbolFactory.createBeanOrUnknownInstanceSymbol(project,
                isJakartaEE ? VIEW_JAKARTA_FULLY_QUALIFIED_CLASS : VIEW_FULLY_QUALIFIED_CLASS,
                VIEW_IMPLICIT_OBJ, ERuntimeSource.BUILT_IN_SYMBOL_LITERAL);
        requestSymbols.put(symbol.getName(), symbol);
        
        //add jsf2.0 implicits
        if (JSFVersion.guessAtLeast(JSFVersion.V2_0, file.getProject()) ) {
            symbol = _symbolFactory.createBeanOrUnknownInstanceSymbol(project,
                    isJakartaEE ? UICOMPONENT_JAKARTA_FULLY_QUALIFIED_CLASS : UICOMPONENT_FULLY_QUALIFIED_CLASS,
                    CC_IMPLICIT_OBJ, ERuntimeSource.BUILT_IN_SYMBOL_LITERAL);
            requestSymbols.put(symbol.getName(), symbol);
            

//            _symbolFactory.createJavaComponentSymbol(CC_IMPLICIT_OBJ, typeDesc, ""); //$NON-NLS-1$
            symbol = _symbolFactory.createBeanOrUnknownInstanceSymbol(project,
                    isJakartaEE ? UICOMPONENT_JAKARTA_FULLY_QUALIFIED_CLASS : UICOMPONENT_FULLY_QUALIFIED_CLASS,
                    COMPONENT_IMPLICIT_OBJ, ERuntimeSource.BUILT_IN_SYMBOL_LITERAL);
            requestSymbols.put(symbol.getName(), symbol);
                       
        }
        
        return Collections.unmodifiableMap(requestSymbols);
    }

    private Map<String,ISymbol> getSessionScopeSymbols(final IFile file)
    {
        ISymbol symbol = createScopeSymbol(file,
                ISymbolConstants.SYMBOL_SCOPE_SESSION, SESSION_SCOPE);

        return Collections.unmodifiableMap
            (Collections.singletonMap(symbol.getName(), symbol));
    }

    private Map<String,ISymbol> getApplicationScopeSymbols(final IFile file)
    {
        final Map<String,ISymbol> symbols = new HashMap<String, ISymbol>();

        // TODO: may be able to resolve this one based on web.xml
        symbols.put(SYMBOL_INIT_PARAM_IMPLICIT_OBJ.getName(), SYMBOL_INIT_PARAM_IMPLICIT_OBJ);
        
        ISymbol symbol = createScopeSymbol(file,
                ISymbolConstants.SYMBOL_SCOPE_APPLICATION, APPLICATION_SCOPE);
        symbols.put(symbol.getName(), symbol);
        
        //add jsf2.0 implicits
        if (JSFVersion.guessAtLeast(JSFVersion.V2_0, file.getProject()) ) {
            symbol = _symbolFactory.createUnknownInstanceSymbol(
                    RESOURCE_IMPLICIT_OBJ,
                    ERuntimeSource.BUILT_IN_SYMBOL_LITERAL);
            symbols.put(symbol.getName(), symbol);
        }
        
        return Collections.unmodifiableMap(symbols);
    }
    
    private Map<String,ISymbol> getViewScopeSymbols(final IFile file)
    {
        if (JSFVersion.guessAtLeast(JSFVersion.V2_0, file.getProject()) ) {
            ISymbol symbol = createScopeSymbol(file,
                    ISymbolConstants.SYMBOL_SCOPE_VIEW, VIEW_SCOPE);
    
            return Collections.unmodifiableMap
                (Collections.singletonMap(symbol.getName(), symbol));
        }
        return Collections.emptyMap();
    }

    private Map<String,ISymbol> getFlashScopeSymbols(final IFile file)
    {
        if (JSFVersion.guessAtLeast(JSFVersion.V2_0, file.getProject()) ) {
            ISymbol symbol = createScopeSymbol(file,
                    ISymbolConstants.SYMBOL_SCOPE_FLASH, FLASH_SCOPE);
    
            return Collections.unmodifiableMap
                (Collections.singletonMap(symbol.getName(), symbol));
        }
        return Collections.emptyMap();
    }
    
    private ISymbol createScopeSymbol(final IFile file, final int scopeMask,
            final String name)
    {
        final Map mapSource = new ScopeMap(file, scopeMask);
        final IMapTypeDescriptor typeDesc = SymbolFactory.eINSTANCE
                .createIBoundedMapTypeDescriptor();
        typeDesc.setMapSource(mapSource);
        typeDesc.setImmutable(false); // scope maps are mutable
        final IInstanceSymbol symbol = SymbolFactory.eINSTANCE
                .createIInstanceSymbol();
        symbol.setName(name);
        symbol.setRuntimeSource(ERuntimeSource.BUILT_IN_SYMBOL_LITERAL);
        symbol.setTypeDescriptor(typeDesc);
        // TODO:symbol.setDetailedDescription("A Map of the application scope
        // attribute values, keyed by attribute name");

        return symbol;
    }

    private static class ScopeMap extends AbstractMap
    {
        private final IFile _externalContextKey;
        private final int   _scopeMask;

        ScopeMap(final IFile externalContextKey, final int scopeMask)
        {
            _externalContextKey = externalContextKey;
            _scopeMask = scopeMask;
        }

        @Override
        public Set entrySet()
        {
            final Map scopeMap = new HashMap();
            // do beans first so in case of name collision, beans are hidden
            final DefaultBeanSymbolSourceProvider beanProvider = DefaultBeanSymbolSourceProvider
                    .getInstance();

            final ISymbol beanSymbols[] = beanProvider.getSymbols(
                    _externalContextKey, _scopeMask);

            for (final ISymbol beanSymbol : beanSymbols)
            {
                scopeMap.put(beanSymbol.getName(), beanSymbol);
            }

            final DesignTimeApplicationManager manager = DesignTimeApplicationManager
                    .getInstance(_externalContextKey.getProject());

            if (manager != null)
            {

                final IDTExternalContext externalContext = manager
                        .getFacesContext(_externalContextKey)
                        .getDTExternalContext(_externalContextKey);

                scopeMap.putAll(externalContext.getMapForScope(_scopeMask));
                
                DTUIViewRoot viewRoot = manager
                    .getFacesContext(_externalContextKey)
                    .getViewRootHandle().getCachedViewRoot();

                if (viewRoot == null) {
                    viewRoot = manager
                    .getFacesContext(_externalContextKey)
                    .getViewRootHandle().updateViewRoot();
                }
                scopeMap.putAll(viewRoot.getViewMap());
            }

            return scopeMap.entrySet();

        }
    }
}
