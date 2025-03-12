/*******************************************************************************
 * Copyright (c) 2001, 2013 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.core.internal.contentassist.el;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.jsf.common.internal.types.CompositeType;
import org.eclipse.jst.jsf.common.internal.types.IAssignable;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.ITaglibContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.symbol.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.IComponentSymbol;
import org.eclipse.jst.jsf.context.symbol.IObjectSymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.designtime.resolver.ISymbolContextResolver;
import org.eclipse.jst.jsf.designtime.resolver.StructuredDocumentSymbolResolverFactory;
import org.eclipse.jst.jsf.metadataprocessors.MetaDataEnabledProcessingFactory;
import org.eclipse.jst.jsf.metadataprocessors.features.ELIsNotValidException;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Utility class for resolving symbols for a IStructuredDocumentContext.
 */
public class SymbolResolveUtil {

	private SymbolResolveUtil() {
		// utility class; not instantiable
	}

	/**
	 * Get symbol for a variable (managed bean name, bundle name)
	 * 
	 * @param context
	 * @param name
	 * @return ISymbol
	 */
	public static ISymbol getSymbolForVariable(
			final IStructuredDocumentContext context, final String name) {
		final ISymbolContextResolver symbolResolver = StructuredDocumentSymbolResolverFactory
				.getInstance().getSymbolContextResolver(context);

		return symbolResolver.getVariable(name);
	}

	/**
	 * Get symbol for a variable suffix (e. g. bean property/method, bundle
	 * property). Takes into account whether method bindings are expected for
	 * the given context.
	 * 
	 * @param context -
	 *            the IStructuredDocumentContext
	 * @param fullName -
	 *            full name of the suffix (e. g. bean.property1.property2)
	 * @param isLastSuffix -
	 *            set true if there follows no other suffix. Method names will
	 *            only be considered if true
	 * @return ISymbol. May be null.
	 */
	public static ISymbol getSymbolForVariableSuffixExpr(
			final IStructuredDocumentContext context, final String fullName,
			final boolean isLastSuffix) {
	    if (fullName == null)
	    {
	        return null;
	    }
		String[] ids = fullName.split("\\."); //$NON-NLS-1$

		// if no suffixes, only one id
		if (ids.length < 1) {
			ids = new String[] { fullName };
		}

		final ISymbolContextResolver symbolResolver = StructuredDocumentSymbolResolverFactory
				.getInstance().getSymbolContextResolver(context);
		if (symbolResolver != null) {
			ISymbol symbol = symbolResolver.getVariable(ids[0]);
			if (symbol instanceof IBeanInstanceSymbol
					&& ((IBeanInstanceSymbol) symbol).isTypeResolved()) {
				for (int curSuffixIdx = 1; curSuffixIdx < ids.length; curSuffixIdx++) {
					if (isLastSuffix && curSuffixIdx == ids.length - 1
							&& isMethodBindingExpected(context)) {
						/*
						 * TODO Take into acount required method signature,
						 * since there may be different methods with the same
						 * name
						 */
						return symbolResolver.getMethod((IObjectSymbol) symbol,
								ids[curSuffixIdx]);
					}

					final ISymbol property = symbolResolver.getProperty(symbol,
							ids[curSuffixIdx]);

					if (property == null) {
						return null;
					}
					symbol = property;
				}
				return symbol;
			} else if (symbol instanceof IComponentSymbol
					&& ((IComponentSymbol) symbol).isTypeResolved()) {
				for (int curSuffixIdx = 1; curSuffixIdx < ids.length; curSuffixIdx++) {
					final ISymbol property = symbolResolver.getProperty(symbol,
							ids[curSuffixIdx]);
	
					if (property == null) {
						return null;
					}
					symbol = property;
				}
			return symbol;
			}
		}
		return null;
	}

	/**
	 * Tells whether method bindings are expected for the given context.
	 * 
	 * @param context -
	 *            the IStructuredDocumentContext
	 * @return true, if method bindings expected
	 */
	public static boolean isMethodBindingExpected(
			final IStructuredDocumentContext context) {
		return isMethodBindingExpected(context, null);
	}

	/**
	 * Tells whether method bindings are expected for the given context. Will
	 * add signatures of expected method bindings to a given list.
	 * 
	 * @param context -
	 *            the IStructuredDocumentContext
	 * @param expectedBindings -
	 *            a list. If not null, signatures of expected method bindings
	 *            will be appended to this list.
	 * @return true, if method bindings expected
	 */
	public static boolean isMethodBindingExpected(
			final IStructuredDocumentContext context,
			final List expectedBindings) {
		final IDOMContextResolver domResolver = IStructuredDocumentContextResolverFactory.INSTANCE
				.getDOMContextResolver(context);

		final Node curNode = domResolver.getNode();

		if (curNode instanceof Attr) {
			final Attr attr = (Attr) curNode;
			final Element element = attr.getOwnerElement();

			final ITaglibContextResolver taglibResolver = IStructuredDocumentContextResolverFactory.INSTANCE
					.getTaglibContextResolver(context);

			final String uri = taglibResolver.getTagURIForNodeName(element);

			final List elVals = MetaDataEnabledProcessingFactory.getInstance()
					.getAttributeValueRuntimeTypeFeatureProcessors(
							IValidELValues.class, context, uri,
							element.getLocalName(), attr.getLocalName());

			boolean methodBindingExpected = false;
			for (final Iterator it = elVals.iterator(); it.hasNext();) {
				final IValidELValues validValues = (IValidELValues) it.next();

				try {
					final CompositeType type = validValues
							.getExpectedRuntimeType();
					if (type != null
							&& type.getAssignmentTypeMask() == IAssignable.ASSIGNMENT_TYPE_NONE) {
						methodBindingExpected = true;
						if (expectedBindings != null) {
							expectedBindings.addAll(Arrays.asList(validValues
									.getExpectedRuntimeType().getSignatures()));
						} else {
							// if we don't need the method signatures, *one*
							// expected method binding is sufficient.
							return true;
						}
					}
				} catch (final ELIsNotValidException e) {
					// do nothing
				}
			}
			return methodBindingExpected;
		}
		// default condition is no method binding
		return false;
	}

}
