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
package org.eclipse.jst.jsf.ui.internal.jspeditor;

import org.eclipse.jface.text.Region;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.core.internal.contentassist.el.ContentAssistParser;
import org.eclipse.jst.jsf.core.internal.contentassist.el.SymbolInfo;
import org.eclipse.jst.jsf.designtime.DTAppManagerUtil;
import org.eclipse.jst.jsf.designtime.internal.view.XMLViewDefnAdapter;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler.ViewHandlerException;
import org.eclipse.jst.jsf.designtime.internal.view.XMLViewDefnAdapter.DTELExpression;
import org.eclipse.wst.xml.core.internal.regions.DOMRegionContext;

/**
 * Utility class to access EL symbols in jsp file.
 */
public final class JSPSourceUtil
{

    private JSPSourceUtil()
    {
        // utility class, no instances.
    }

    /**
     * Find the Region
     * 
     * @param context -
     *            the IStructuredDocumentContext
     * @return region of el expression, null if context doesn't point to an el
     *         expression
     */
    public static Region findELRegion(final IStructuredDocumentContext context)
    {
        if (context != null)
        {
            final DTELExpression expression = getELExpression(context);
            if (expression != null)
            {
                final ITextRegionContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE
                        .getTextRegionResolver(expression.getDocumentContext());

                if (resolver != null)
                {
                    final String regionType = resolver.getRegionType();

                    if (regionType != null)
                    {

                        if (regionType == DOMRegionContext.XML_TAG_ATTRIBUTE_VALUE)
                        {
                            return new Region(expression.getDocumentContext()
                                    .getDocumentPosition(), expression
                                    .getText().length());
                        }
                        else if (resolver.matchesRelative(new String[]
                        { DOMRegionContext.XML_TAG_ATTRIBUTE_VALUE }))
                        {
                            return new Region(resolver.getStartOffset(),
                                    resolver.getLength());
                        }
                        else if (regionType == DOMRegionContext.XML_CONTENT)
                        {
                            return new Region(expression.getDocumentContext()
                                    .getDocumentPosition(), expression
                                    .getText().length());
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Determines symbol and symbol region at a given document position
     * 
     * @param context -
     *            the IStructuredDocumentContext
     * @param elRegion -
     *            the region of the el expression to consider
     * @param documentPosition -
     *            the document position to get the symbol for
     * @return SymbolInfo
     */
    public static SymbolInfo determineSymbolInfo(
            final IStructuredDocumentContext context, final Region elRegion,
            final int documentPosition)
    {
        if (context != null && elRegion != null)
        {
            final DTELExpression elExpression = getELExpression(context);
            
            final String elText = elExpression.getText().trim();
//                        context.getStructuredDocument().get(
//                                elRegion.getOffset(), elRegion.getLength());
            final SymbolInfo symbolInfo =
                    ContentAssistParser.getSymbolInfo(context,
                            documentPosition - elRegion.getOffset() + 1,
                            elText);
            return symbolInfo;
        }
        return null;
    }
    
    private static DTELExpression getELExpression(
            final IStructuredDocumentContext context)
    {
        final XMLViewDefnAdapter adapter = DTAppManagerUtil
                .getXMLViewDefnAdapter(context);
        DTELExpression expression = null;
        if (adapter != null)
        {
            try
            {
                expression = adapter.getELExpression(context);
            }
            catch (ViewHandlerException e)
            {
                expression = null;
            }
        }
        return expression;
    }
}
