/*******************************************************************************
 * Copyright (c) 2006, 2021 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    Andreas Rusch/Axon Ivy - Lazy compute additional proposal info (javadoc) 
 *
 ********************************************************************************/
package org.eclipse.jst.jsf.core.internal.contentassist.el;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.symbol.IMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.IObjectSymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.provider.IContentProposalProvider;
import org.eclipse.jst.jsf.context.symbol.provider.IContentProposalProvider.IProposalCreationFactory;
import org.eclipse.jst.jsf.context.symbol.provider.ProposalCreationFactoryAdapter;
import org.eclipse.jst.jsf.designtime.resolver.ISymbolContextResolver;
import org.eclipse.jst.jsf.designtime.resolver.StructuredDocumentSymbolResolverFactory;
import org.eclipse.jst.jsp.core.internal.regions.DOMJSPRegionContexts;
import org.eclipse.swt.graphics.Image;

/**
 * A completion strategy for function completions like:
 *
 * v a r .
 *        ^
 *
 * @author cbateman
 *
 */
public class FunctionCompletionStrategy extends ContentAssistStrategy
{
    /**
     * @param value
     * @param proposalStart
     */
    public FunctionCompletionStrategy(final String value, final String proposalStart)
    {
        super(ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION, value, proposalStart);
    }

    @Override
	public List<ICompletionProposal> getProposals(final IStructuredDocumentContext context)
    {
        List<ICompletionProposal> completionList = Collections.EMPTY_LIST;
        final ISymbolContextResolver  symbolResolver =
            StructuredDocumentSymbolResolverFactory.getInstance().
                getSymbolContextResolver(context);

        String text = getValue();
        if (text != null)
        {
            final ISymbol symbol = SymbolResolveUtil.getSymbolForVariableSuffixExpr(context, getValue());
    
            // if we get a completion symbol, get it's proposals
            if (symbol instanceof IObjectSymbol)
            {
                final List expectedMethodBindings = new ArrayList();
                final ISymbol[] suffixes = getSymbols((IObjectSymbol) symbol,
                                                 context,
                                                 symbolResolver,
                                                 expectedMethodBindings);
    
                final ComposedAdapterFactory factory =
                    new ComposedAdapterFactory(
                           ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
                final IProposalCreationFactory  creationInfo =
                    new MyProposalFactory(context, getProposalStart().length(),
                                            expectedMethodBindings);
                
                completionList = new ArrayList<ICompletionProposal>();
                for (final ISymbol propSymbol : suffixes) {
                    final Object  provider =
                      factory.adapt(propSymbol, IContentProposalProvider.class);
    
                    if (provider instanceof IContentProposalProvider)
                    {
                        final ICompletionProposal[] proposal  =
                            ((IContentProposalProvider) provider).
                                getProposals(propSymbol, creationInfo);
                        if (proposal != null)
                        {
                        	addProposalsMatchingProposalStart(completionList,
    								proposal);
                        }
                    }
                }
            }
        }    
        return Collections.unmodifiableList(completionList);
    }

	private ISymbol[] getSymbols(final IObjectSymbol symbol,
                                 final IStructuredDocumentContext context,
                                 final ISymbolContextResolver  symbolResolver,
                                 final List   expectedMethodBindings)
    {
        final List symbols = new ArrayList();

        if (SymbolResolveUtil.isMethodBindingExpected(context, expectedMethodBindings))
        {
            symbols.addAll(Arrays.asList(
                          symbolResolver.getMethods(symbol)));
        }

        symbols.addAll(Arrays.asList(symbolResolver.getProperties(symbol)));

        return (ISymbol[]) symbols.toArray(ISymbol.EMPTY_SYMBOL_ARRAY);
    }

    private static class MyProposalFactory extends ProposalCreationFactoryAdapter
    {
        private final static int   DEFAULT_RELEVANCE = 1;

        private final static int   HIGH_RELEVANCE = 2;
        private final static int   NORMAL_RELEVANCE = 1;
        private final static int   LOW_RELEVANCE = 0;

        private final List                         _expectedMethodBindings;
        private final IStructuredDocumentContext   _context;

        /**
         * @param context
         * @param replacementLength
         * @param expectedMethodBindings
         */
        public MyProposalFactory(final IStructuredDocumentContext context, final int replacementLength,
                                    final List expectedMethodBindings) {
            super(context.getDocumentPosition() - replacementLength, replacementLength);
            _context = context;
            _expectedMethodBindings = expectedMethodBindings;
        }

        @Override
		public ICompletionProposal createProposal(final String replacementText,
                                                  final String displayText,
                                                  final Image displayImage,
                                                  final Object target)
        {
            int replacementOffset = _replacementOffset;
            int replacementLength = _replacementLength;

            // TODO: I regard this as a bit of hack, but until we write our
            // proposal implementation, it's basically the only way I can
            // see to do this
            // if it's an array, we must check if we need to replace a
            // preceding '.'
            if (replacementText.startsWith("[")) //$NON-NLS-1$
            {
                ITextRegionContextResolver textResolver =
                    IStructuredDocumentContextResolverFactory.INSTANCE.getTextRegionResolver(_context);

                if (textResolver.getRegionType().equals(DOMJSPRegionContexts.JSP_VBL_CLOSE))
                {
                    textResolver =
                        IStructuredDocumentContextResolverFactory.
                            INSTANCE.getTextRegionResolver(textResolver.getPreviousContext());
                }

                final String regionText = textResolver.getRegionText();
                final int regionStart = textResolver.getStartOffset();

                if (DOMJSPRegionContexts.JSP_VBL_CONTENT.equals(textResolver.getRegionType())
                        && regionText != null
                        && regionStart != -1
                        && regionStart < _context.getDocumentPosition())
                {
                    final int  relativeOffset = _context.getDocumentPosition() - regionStart - 1;

                    if (regionText.charAt(relativeOffset) == '.')
                    {
                        // we must replace a length of 1 (the dot)
                        // at an offset on prior
                        replacementOffset--;
                        replacementLength = 1;
                    }
                }
            }

            return createDefaultProposal(replacementText,
                                         replacementOffset,
                                         replacementLength,
                                         replacementText.length(),
                                         displayImage,
                                         displayText,
                                         null,
                                         getRelevance(target, DEFAULT_RELEVANCE),
                                         target);
        }

        private int getRelevance(final Object target, final int defaultRelevance)
        {
            // if method bindings are expected, then list exact signature
            // matches top most.  Still list non-matching methods, but put
            // them at the bottom
            if (_expectedMethodBindings.size() > 0)
            {
                if (target instanceof IMethodSymbol)
                {
                    final IMethodSymbol methodSymbol = (IMethodSymbol) target;

                    for (final Iterator it = _expectedMethodBindings.iterator();
                            it.hasNext();)
                    {
                        final String methodType = (String) it.next();

                        // we have a match, so push to the top
                        if (methodType.equals(methodSymbol.getSignature()))
                        {
                            return HIGH_RELEVANCE;
                        }
                    }

                    // if we get out of the loop, then this method doesn't
                    // match the expected signature
                    return LOW_RELEVANCE;
                }

                // non-method targets have normal relevance when mb expected
                return NORMAL_RELEVANCE;
            }

            // otherwise, simply return the default for all
            return defaultRelevance;
        }
    }
}
