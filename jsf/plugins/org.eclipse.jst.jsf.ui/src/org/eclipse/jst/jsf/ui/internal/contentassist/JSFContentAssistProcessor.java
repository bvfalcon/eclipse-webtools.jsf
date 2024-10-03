/*******************************************************************************
 * Copyright (c) 2006, 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.ui.internal.contentassist;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.ITaglibContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.IStructuredDocumentContextResolverFactory2;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.metadataprocessors.MetaDataEnabledProcessingFactory;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValue;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.ui.internal.JSFUIPlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.sse.ui.internal.contentassist.CustomCompletionProposal;
import org.eclipse.wst.xml.core.internal.contentmodel.CMAttributeDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNamedNodeMap;
import org.eclipse.wst.xml.core.internal.contentmodel.modelquery.ModelQuery;
import org.eclipse.wst.xml.core.internal.modelquery.ModelQueryUtil;
import org.eclipse.wst.xml.core.internal.regions.DOMRegionContext;
import org.eclipse.wst.xml.ui.internal.contentassist.XMLRelevanceConstants;
import org.eclipse.wst.xml.ui.internal.taginfo.MarkupTagInfoProvider;
import org.osgi.framework.Bundle;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * The content assist processor for non-EL attribute values.
 * 
 * @author Gerry Kessler - Oracle
 * 
 */
public class JSFContentAssistProcessor implements IContentAssistProcessor {
	private ITextRegionContextResolver resolver;
	private ITaglibContextResolver tlResolver;
	private String defaultAdditionalInfo;

	private String defaultIconPath = "/icons/attr_val.gif"; //$NON-NLS-1$
	
	private ImageDescriptor defaultAttrValImgDesc;
	
	private MarkupTagInfoProvider fInfoProvider;

	/**
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeCompletionProposals(org.eclipse.jface.text.ITextViewer,
	 *      int)
	 */
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int documentPosition) {
		List proposals = new ArrayList();
		IStructuredDocumentContext context = IStructuredDocumentContextFactory.INSTANCE
				.getContext(viewer, documentPosition);

		if (context != null) {
			resolver = IStructuredDocumentContextResolverFactory.INSTANCE
					.getTextRegionResolver(context);

			if (resolver != null) {
				final String regionType = resolver.getRegionType();

				if (regionType != null
						&& regionType.equals(DOMRegionContext.XML_TAG_ATTRIBUTE_VALUE)) {
					
					tlResolver = IStructuredDocumentContextResolverFactory2.INSTANCE
							.getTaglibContextResolverFromDelegates(context);

					if (tlResolver != null) {
						
						Attr attr = getAttribute(context);
						if (attr != null) {
							Node tagElement = attr.getOwnerElement();
							if (tagElement != null) {
								String uri = tlResolver.getTagURIForNodeName(tagElement);
								if (uri != null) {
									proposals = createProposals(context, uri, tagElement, attr);
								}
							}
						}
					}
				}
			}
		}

		return (ICompletionProposal[]) proposals
				.toArray(new ICompletionProposal[0]);
	}

	private String getDefaultAdditionalInfo(Node tagElement, Attr attr) {
		if (defaultAdditionalInfo == null){
			CMElementDeclaration elementNode = getCMElementDeclaration(tagElement);
			if (elementNode != null){
				CMAttributeDeclaration attrNode = getCMAttributeDeclaration(elementNode, attr);
				if (attrNode != null)
					defaultAdditionalInfo = getInfoProvider().getInfo(attrNode);
			}
		}
		return defaultAdditionalInfo;
	}
	
	private CMElementDeclaration getCMElementDeclaration(Node node) {
		CMElementDeclaration result = null;
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			ModelQuery modelQuery = ModelQueryUtil.getModelQuery(node.getOwnerDocument());
			if (modelQuery != null)
				result = modelQuery.getCMElementDeclaration((Element) node);
		}
		return result;
	}

	private CMAttributeDeclaration getCMAttributeDeclaration(CMElementDeclaration tagElement, Attr attr) {
		CMNamedNodeMap attrs = tagElement.getAttributes();
		for (Iterator it = attrs.iterator();it.hasNext();){
			CMAttributeDeclaration CMAttr = (CMAttributeDeclaration)it.next();
			if (CMAttr.getAttrName().equals(attr.getName()))
				return CMAttr;
		}
		return null;
	}
	
	private MarkupTagInfoProvider getInfoProvider() {
		if (fInfoProvider == null) {
			fInfoProvider = new MarkupTagInfoProvider();
		}
		return fInfoProvider;
	}
	
	private List createProposals(IStructuredDocumentContext context, String uri, Node tagElement, Attr attr) {
		List ret = new ArrayList();
		List processors = MetaDataEnabledProcessingFactory.getInstance()
						.getAttributeValueRuntimeTypeFeatureProcessors(
								IPossibleValues.class, context, uri,
								tagElement.getLocalName(), attr.getLocalName());
		
		if (processors != null) {
			for (int i = 0; i < processors.size(); i++) {
				IPossibleValues p = (IPossibleValues) processors.get(i);
				ret.addAll(createProposals(p, tagElement, attr));
			}
		}
		return ret;
	}

	private List createProposals(IPossibleValues p, Node tagElement, Attr attr) {
		List ret = new ArrayList();
		defaultAdditionalInfo = null;
		Iterator it = p.getPossibleValues().iterator();
		while (it.hasNext()) {
			IPossibleValue val = (IPossibleValue) it.next();
			if (val != null){ //just in case...
				ICompletionProposal proposal = new CustomCompletionProposal(
						val.getValue(), 		//replacement text
						getReplacementOffset(), //replacementOffset
						getReplacementLength(), //replacementLength
						getCursorPosition(val), //cursor pos
						getImage(val), 			//image
						val.getDisplayValue(), 	//display value
						null, 					//IContextInformation
						getAdditionalInfo(val,tagElement,attr), //addditional info
						XMLRelevanceConstants.R_JSP_ATTRIBUTE_VALUE,	//relevance
						true);					//updateReplace
	
				ret.add(proposal);
			}
		}
		return ret;
	}


	private String getAdditionalInfo(IPossibleValue val, Node tagElement, Attr attr) {
		if (val.getAdditionalInformation() != null)
			return val.getAdditionalInformation();
		
		return 	getDefaultAdditionalInfo(tagElement, attr);
	}

	private int getReplacementLength() {
		return resolver.getRegionText().length() - 2;
	}

	private int getCursorPosition(IPossibleValue val) {
		//Something changed in 1.5RC2 CustomCompletionProposal 
		//it appears that the cursor position is now relative to where it is currently
		//rather than relative to document
		
		//return getReplacementOffset() + val.getValue().length();
		return val.getValue().length();
	}

	private int getReplacementOffset() {
		return resolver.getStartOffset() + 1;
	}

	private Image getImage(IPossibleValue val) {
		ImageDescriptor icon = val.getIcon();		
		if (icon == null || icon.equals("")){ //$NON-NLS-1$
			return getDefaultAttributeValueImage();
		}
		
		return getOrCreateImage(icon);		
	}

	private Image getOrCreateImage(ImageDescriptor icon) {
		if (icon == null)
			return null;
			
		Image img = JSFUIPlugin.getDefault().getImageRegistry().get(icon.toString());
		if (img == null){
			try {
				img = icon.createImage();
				JSFUIPlugin.getDefault().getImageRegistry().put(icon.toString(), img);
			} catch (RuntimeException e) {
                // empty block; C.B: handle exception?
			}
		}
		return img;
	}

	private Image getDefaultAttributeValueImage() {
		if (defaultAttrValImgDesc == null){
			Bundle bundle = Platform.getBundle(JSFUIPlugin.PLUGIN_ID);
			URL url= FileLocator.find(bundle,new Path(defaultIconPath ), null);
			defaultAttrValImgDesc = ImageDescriptor.createFromURL(url);
		}
		return getOrCreateImage(defaultAttrValImgDesc);
	}

	private Attr getAttribute(IStructuredDocumentContext context) {
		final IDOMContextResolver domResolver = IStructuredDocumentContextResolverFactory.INSTANCE
				.getDOMContextResolver(context);

		if (domResolver != null) {
			final Node curNode = domResolver.getNode();

			if (curNode instanceof Attr) {
				return (Attr) curNode;
			}
		}
		return null;

	}

	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset) {
		// no context info
		return null;
	}

	public char[] getCompletionProposalAutoActivationCharacters() {
		// auto activate when user hits a '.'
		return new char[] { '.' };
	}

	public char[] getContextInformationAutoActivationCharacters() {
		// no auto-activation for context info
		return null;
	}

	public String getErrorMessage() {
		// don't flag errors
		return null;
	}

	public IContextInformationValidator getContextInformationValidator() {
		// don't validate context information
		return null;
	}

}