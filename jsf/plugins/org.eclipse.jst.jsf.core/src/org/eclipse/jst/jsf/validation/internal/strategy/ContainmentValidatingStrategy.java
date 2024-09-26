/*******************************************************************************
 * Copyright (c) 2001, 2011 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.validation.internal.strategy;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.common.dom.DOMAdapter;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.common.internal.JSFUtil;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainMetaDataQuery;
import org.eclipse.jst.jsf.common.sets.AxiomaticSet;
import org.eclipse.jst.jsf.common.sets.ConcreteAxiomaticSet;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.region.Region2ElementAdapter;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.jsf.core.set.constraint.MemberConstraint;
import org.eclipse.jst.jsf.core.set.mapping.ElementToTagIdentifierMapping;
import org.eclipse.jst.jsf.core.tagmatcher.EvaluationException;
import org.eclipse.jst.jsf.core.tagmatcher.InvalidExpressionException;
import org.eclipse.jst.jsf.core.tagmatcher.XPathMatchingAlgorithm;
import org.eclipse.jst.jsf.validation.internal.AbstractXMLViewValidationStrategy;
import org.eclipse.jst.jsf.validation.internal.JSFValidationContext;
import org.eclipse.jst.jsf.validation.internal.constraints.ContainsTagConstraint;
import org.eclipse.jst.jsf.validation.internal.constraints.TagId;
import org.eclipse.jst.jsf.validation.internal.constraints.TagSet;
import org.w3c.dom.Node;

/**
 * @author cbateman
 * 
 */
public class ContainmentValidatingStrategy extends
        AbstractXMLViewValidationStrategy
{
    /**
     * identifier
     */
    public final static String                         ID = 
        "org.eclipse.jst.jsf.validation.strategy.ElementValidatingStrategy"; //$NON-NLS-1$
    private final static String                        DISPLAY_NAME =
        Messages.ContainmentValidatingStrategy_DisplayName;
    private final static ElementToTagIdentifierMapping elem2TagIdMapper = 
        new ElementToTagIdentifierMapping();
    
    private static final  String  ENABLE_CONTAINMENT_VALIDATION_KEY  = "jsfCoreEnableContainmentValidation"; //$NON-NLS-1$
    
    private int                                        _containmentValidationCount;  // = 0;
    private final JSFValidationContext                 _jsfValidationContext;
    private boolean 								   _enabled;						

    /**
     * @param jsfValidationContext
     */
    public ContainmentValidatingStrategy(
            final JSFValidationContext jsfValidationContext)
    {
        super(ID, DISPLAY_NAME);
        _jsfValidationContext = jsfValidationContext;
        _enabled = isEnabled();
    }

	@Override
    public boolean isInteresting(DOMAdapter domAdapter)
    {
        return domAdapter instanceof Region2ElementAdapter;
    }

    @Override
    public void validate(DOMAdapter domAdapter)
    {
        if (_enabled 
        		&& domAdapter instanceof Region2ElementAdapter)
        {
            final Region2ElementAdapter elementAdapter = 
                (Region2ElementAdapter) domAdapter;
            validateContainment(elementAdapter, _jsfValidationContext);
        }
    }

    /**
     * @return true if the containment validation strategy is enabled
     */
    public static boolean isEnabled() {
		 String res = System.getProperty(ENABLE_CONTAINMENT_VALIDATION_KEY);
		 if (res == null) {
		     //check env var also
		     res = System.getenv(ENABLE_CONTAINMENT_VALIDATION_KEY);
		 }
		 return res != null;		
	}
    
    private void validateContainment(
            final Region2ElementAdapter elementAdapter,
            final JSFValidationContext jsfValidationContext)
    {
        // don't validate JSP fragments since the necessary containment may
        // existing
        // in the JSP files that include them
        // also only validate the first instance of containment violation in a
        // file
        if (JSFUtil.isJSFFragment(jsfValidationContext.getFile())
                || _containmentValidationCount > 0)
        {
            return;
        }

        final IStructuredDocumentContext context = elementAdapter
                .getDocumentContext();
        final IDOMContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE
                .getDOMContextResolver(context);
        final Node node = resolver.getNode();

        final String uri = elementAdapter.getNamespace();
        final String tagName = elementAdapter.getLocalName();
        // final Element node = elementAdapter.
        
		final IMetaDataDomainContext mdcontext = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(jsfValidationContext.getFile());
		final ITaglibDomainMetaDataQuery query = MetaDataQueryFactory.getInstance().createQuery(mdcontext);
//        final ITaglibDomainMetaDataModelContext modelContext = TaglibDomainMetaDataQueryHelper
//                .createMetaDataModelContext(jsfValidationContext.getFile()
//                        .getProject(), uri);
        final Entity entity = query.getQueryHelper().getEntity(
                uri, tagName);
        if (entity != null)
        {
            final Trait trait = query.findTrait(
                    entity, "containment-constraint"); //$NON-NLS-1$

            if (trait != null)
            {
                final ContainsTagConstraint tagConstraint = (ContainsTagConstraint) trait
                        .getValue();

                final String algorithm = tagConstraint.getSetGenerator()
                        .getAlgorithm();

                // TODO: need generalized factory mechanism for registering and
                // constructing algorithms.
                if (!"xpath".equals(algorithm)) //$NON-NLS-1$
                {
                    return;
                }

                final String expr = tagConstraint.getSetGenerator()
                        .getExpression();

                // TODO: optimize on the expression and cache for reuse
                final XPathMatchingAlgorithm xpathAlg = new XPathMatchingAlgorithm(
                        expr);

                AxiomaticSet set = null;

                try
                {
                    set = xpathAlg.evaluate(node);
                    // map dom nodes to tag identifiers
                    set = elem2TagIdMapper.map(set);
                }
                catch (final InvalidExpressionException e)
                {
                    JSFCorePlugin.log(e, "Problem with expression: " + expr //$NON-NLS-1$
                            + " on node " + node); //$NON-NLS-1$
                    return;
                }
                catch (final EvaluationException e)
                {
                    JSFCorePlugin.log(e, "Problem evaluating expression: " //$NON-NLS-1$
                            + expr + " on node " + node); //$NON-NLS-1$
                    return;
                }

                final TagSet constraintData = tagConstraint.getSatisfiesSet();
                final AxiomaticSet constraintSet = new ConcreteAxiomaticSet();
                for (final Iterator it = constraintData.getTags().iterator(); it
                        .hasNext();)
                {
                    final TagId tagId = (TagId) it.next();
                    constraintSet.add(TagIdentifierFactory.createJSPTagWrapper(
                            tagId.getUri(), tagId.getName()));
                }
                final MemberConstraint memberConstraint = new MemberConstraint(
                        constraintSet);
                final Diagnostic diag = memberConstraint.isSatisfied(set);

                if (diag.getSeverity() != Diagnostic.OK)
                {
                    _containmentValidationCount++; // found a violation

                    final List data = diag.getData();

                    for (final Iterator it = data.iterator(); it.hasNext();)
                    {
                        final TagIdentifier missingParent = (TagIdentifier) it
                                .next();

                        reportContainmentProblem(context, node, missingParent);
                    }
                }
            }
        }
    }

    private void reportContainmentProblem(
            final IStructuredDocumentContext context,
            final Node node,
            final TagIdentifier missingParent)
    {

    	Diagnostic diagnostic = null;
    	if (missingParent.equals(IJSFConstants.TAG_IDENTIFIER_VIEW)) {
    		diagnostic = DiagnosticFactory.create_CONTAINMENT_ERROR_MISSING_VIEW(node.getNodeName());
    	}
    	else if (missingParent.equals(IJSFConstants.TAG_IDENTIFIER_FORM)) {
    		diagnostic = DiagnosticFactory.create_CONTAINMENT_ERROR_MISSING_FORM(node.getNodeName());
    	}
    	else {
    		diagnostic = DiagnosticFactory.create_CONTAINMENT_ERROR_MISSING_ANCESTOR(node.getNodeName(), missingParent);
    	} 

        // add one so that the start offset is at the node name, rather
        // than the opening brace.
        final int start = context.getDocumentPosition()+1;
        final int length = node.getNodeName().length();

       _jsfValidationContext.getReporter().report(diagnostic, start, length);
    }
    
}
