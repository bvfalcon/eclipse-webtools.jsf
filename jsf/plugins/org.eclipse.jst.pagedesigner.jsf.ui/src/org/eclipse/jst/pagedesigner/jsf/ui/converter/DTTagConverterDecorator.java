/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.pagedesigner.jsf.ui.converter;

import org.eclipse.jst.pagedesigner.IJSFConstants;
import org.eclipse.jst.pagedesigner.converter.ITagConverter;
import org.eclipse.jst.pagedesigner.dom.TagIdentifier;
import org.eclipse.jst.pagedesigner.dom.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.jsf.ui.util.JSFUIPluginResourcesUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class DTTagConverterDecorator implements ITagConverterDecorator {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.ITagConverterDecorator#decorate(org.eclipse.jst.pagedesigner.converter.ITagConverter)
	 */
	public void decorate(ITagConverter tagConverter) {
		if (!(tagConverter instanceof DTTagConverter)) {
			throw new IllegalArgumentException(JSFUIPluginResourcesUtil.getInstance().getString("Error.DTTagConverterDecorator.NotDTTagConverterInstance"));
		}
		DTTagConverter dtTagConverter = (DTTagConverter)tagConverter;
		if (tagConverter.getResultElement() == null) {
			createUnknownTagRepresentation(dtTagConverter);
		}
		Element srcElement = tagConverter.getHostElement();

		TagIdentifier srcTagIdentifier =
			TagIdentifierFactory.createDocumentTagWrapper(srcElement);

		if (IJSFConstants.TAG_IDENTIFIER_VIEW.isSameTagType(srcTagIdentifier)) 
        {
			dtTagConverter.setNeedBorderDecorator(true);
		}
		else if (IJSFConstants.TAG_IDENTIFIER_FACET.isSameTagType(srcTagIdentifier))
		{
			dtTagConverter.setMinWidth(10);
			dtTagConverter.setMinHeight(10);
			dtTagConverter.setMultiLevel(true);
			dtTagConverter.setNeedBorderDecorator(true);
		}
		else if (IJSFConstants.TAG_IDENTIFIER_FORM.isSameTagType(srcTagIdentifier)) 
        {
			dtTagConverter.setNeedBorderDecorator(true);
        }
        else if (IJSFConstants.TAG_IDENTIFIER_INPUTTEXT.isSameTagType(srcTagIdentifier))
        {
			dtTagConverter.setMultiLevel(true);
			dtTagConverter.setWidget(true);
			setNonVisualChildElements(dtTagConverter, srcElement);
        }
        else if (IJSFConstants.TAG_IDENTIFIER_OUTPUTTEXT.isSameTagType(srcTagIdentifier)) 
        {
			dtTagConverter.setNeedBorderDecorator(true);
			dtTagConverter.setMultiLevel(true);
			dtTagConverter.setWidget(true);
			setNonVisualChildElements(dtTagConverter, srcElement);
        }
		else if (IJSFConstants.TAG_IDENTIFIER_OUTPUTLABEL.isSameTagType(srcTagIdentifier)) 
        {
			dtTagConverter.setNeedBorderDecorator(true);
			dtTagConverter.setMultiLevel(true);
			dtTagConverter.setWidget(true);
			setNonVisualChildElements(dtTagConverter, srcElement);
		}
		else if (IJSFConstants.TAG_IDENTIFIER_PANEL_GRID.isSameTagType(srcTagIdentifier))
		{
			dtTagConverter.setMultiLevel(true);
			dtTagConverter.setNeedBorderDecorator(true);
			dtTagConverter.setNeedTableDecorator(true);
		}
	}

	protected void createUnknownTagRepresentation(DTTagConverter dtTagConverter) {
		Element element = dtTagConverter.createElement("span");
		element.setAttribute("style", "color:red;font-weight:bold;");
		Text text = dtTagConverter.createText("<" + dtTagConverter.getHostElement().getTagName() + "/>");
		element.appendChild(text);
		dtTagConverter.setResultElement(element);
		dtTagConverter.setWidget(true);
	}

	protected void setNonVisualChildElements(DTTagConverter dtTagConverter, Element srcElement) {
		NodeList childNodes = srcElement.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node curNode = childNodes.item(i);
			if (curNode.getNodeType() == Node.ELEMENT_NODE) {
				dtTagConverter.addNonVisualChildElement((Element)curNode);
			}
		}
	}

}