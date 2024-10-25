/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
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

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.jdt.core.IType;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.hyperlink.AbstractHyperlinkDetector;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.context.symbol.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.IBeanMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.IBeanPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.IComponentSymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.core.internal.contentassist.el.SymbolInfo;

/**
 * This HyperlinkDetector creates hyperlinks for symbols in JSF EL expressions
 * inside jsp and facelet files.
 */
public abstract class AbstractELHyperlinkDetector extends AbstractHyperlinkDetector {

	/**
	 * Tests if this detector should return any hyperlinks for this context.
	 * @param context IStructuredDocumentContext instance to test enablement for.
	 * @return true if this detector should return any hyperlinks for this context, else false.
	 */
	protected abstract boolean isEnabled(final IStructuredDocumentContext context);

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.hyperlink.IHyperlinkDetector#detectHyperlinks(org.eclipse.jface.text.ITextViewer, org.eclipse.jface.text.IRegion, boolean)
	 */
	public final IHyperlink[] detectHyperlinks(
			final ITextViewer textViewer,
			final IRegion region,
			final boolean canShowMultipleHyperlinks) {
        final IStructuredDocumentContext context =
        	IStructuredDocumentContextFactory.INSTANCE.getContext(textViewer, region.getOffset());
        if (!isEnabled(context)) {
        	return null;
        }
        return detectHyperlinks(context, region);
	}

    /**
     * Broken out for testing.
     * @param context
     * @param region
     * @return the hyperlinks
     */
    protected IHyperlink[] detectHyperlinks(
            final IStructuredDocumentContext context, final IRegion region) {
        final Region elRegion = JSPSourceUtil.findELRegion(context);
        if (elRegion != null) {
            final SymbolInfo symbolInfo = JSPSourceUtil.determineSymbolInfo(
                    context, elRegion, region.getOffset());
            if (symbolInfo != null) {
                IHyperlink link = null;
                final Region linkRegion = new Region(symbolInfo
                        .getRelativeRegion().getOffset()
                        + elRegion.getOffset(), symbolInfo.getRelativeRegion()
                        .getLength());
                final ISymbol symbol = symbolInfo.getSymbol();
                if (symbol instanceof IBeanInstanceSymbol) {
                    link = createBeanInstanceLink(linkRegion,
                            (IBeanInstanceSymbol) symbol);
                } else if (symbol instanceof IBeanPropertySymbol) {
                    link = createBeanPropertyLink(linkRegion,
                            (IBeanPropertySymbol) symbol);
                } else if (symbol instanceof IBeanMethodSymbol) {
                    link = createMethodLink(linkRegion,
                            (IBeanMethodSymbol) symbol);
                } else if (symbol instanceof IComponentSymbol) {
                    link = createResourceBundleLink(linkRegion,
                            (IComponentSymbol) symbol);
                }
                if (link != null) {
                    return new IHyperlink[] { link };
                }
            }
        }
        return null;
    }

    private IHyperlink createBeanInstanceLink(
    		final Region region, final IBeanInstanceSymbol symbol) {
        if (symbol.isTypeResolved()) {
            final IType type = symbol.getJavaTypeDescriptor().getType();
            return new JavaElementHyperlink(region, type);
        }
        return null;
    }

    private IHyperlink createBeanPropertyLink(
            final Region region, final IBeanPropertySymbol symbol) {
        // defer calculation of access method until user click on link (takes
        // too long otherwise):
        return new BeanSuffixHyperlink(region, symbol);
    }

    private IHyperlink createMethodLink(
    		final Region region, final IBeanMethodSymbol symbol) {
        // defer calculation of access method until user click on link (takes
        // too long otherwise):
        return new BeanSuffixHyperlink(region, symbol);
    }

    private IHyperlink createResourceBundleLink(
            final Region region, final IComponentSymbol symbol) {
        return new PropertyKeyHyperlink(region, symbol);
    }

    /**
     * Tests if the specified file has the specified content type.
     * @param filename Name of file to test.
     * @param contentTypeId ID of content type to test.
     * @return true if the specified file has the specified content type, else false.
     */
	protected boolean hasContentType(String filename, String contentTypeId) {
		boolean hasContentType = false;
		final IContentType[] contentTypes =
			Platform.getContentTypeManager().findContentTypesFor(filename);
		for (final IContentType contentType: contentTypes) {
			if (contentTypeId.equals(contentType.getId())) {
				hasContentType = true;
				break;
			}
		}
		return hasContentType;
	}

}
