/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author mengbo
 */
public class SelectManyHelper {

	private final static Set<String> JSF_SELECT_TAGS, HTML_SELECT_TAGS;


	static {
		Set<String> tempSet = new HashSet<String>(4);
		tempSet.add(IHTMLConstants.TAG_SELECT);
		tempSet.add(IHTMLConstants.TAG_OPTGROUP);
		HTML_SELECT_TAGS = Collections.unmodifiableSet(tempSet);
		
		tempSet = new HashSet(8);
		tempSet.add(IJSFConstants.TAG_SELECTONELISTBOX);
		tempSet.add(IJSFConstants.TAG_SELECTONEMENU);
		tempSet.add(IJSFConstants.TAG_SELECTMANYLISTBOX);//
		tempSet.add(IJSFConstants.TAG_SELECTMANYMENU);//
		tempSet.add(IJSFConstants.TAG_SELECTMANYCHECKBOX);
		JSF_SELECT_TAGS = Collections.unmodifiableSet(tempSet);
	}

	/**
	 * @param node
	 * @return the select option children of node
	 */
	public static Object[] getSelectOptions(Element node) {
		if (node == null) {
			return null;
		}
		IDOMModel xmlModel = null;
		if (node instanceof IDOMNode) {
			xmlModel = ((IDOMNode) node).getModel();
		}
		if (xmlModel == null) {
			return null;
		}
		// TODO: this query is not cached.
		String prefixNode = JSFUtil.getPrefix(xmlModel,
				ITLDConstants.URI_JSF_HTML);
		if (prefixNode != null && node.getPrefix() != null
				&& node.getPrefix().equals(prefixNode)) {
			if (JSF_SELECT_TAGS.contains(node.getLocalName())) {
				String prefixItem = JSFUtil.getPrefix(xmlModel,
						ITLDConstants.URI_JSF_CORE);
				List result = new ArrayList();
				NodeList items = node.getElementsByTagName(prefixItem
						+ ":" + IJSFConstants.TAG_SELECTITEM); //$NON-NLS-1$
				for (int i = 0, n = items.getLength(); i < n; i++) {
					result.add(items.item(i));
				}
				items = node.getElementsByTagName(prefixItem
						+ ":" + IJSFConstants.TAG_SELECTITEMS); //$NON-NLS-1$
				for (int i = 0, n = items.getLength(); i < n; i++) {
					result.add(items.item(i));
				}
				return result.size() > 0 ? result.toArray(new Node[result
						.size()]) : null;
			}
		}
		if (node.getPrefix() == null) {
			if (HTML_SELECT_TAGS.contains(node.getNodeName().toLowerCase())) {
				List result = new ArrayList();
				NodeList options = node
						.getElementsByTagName(IHTMLConstants.TAG_OPTION);
				NodeList optionGroups = node
						.getElementsByTagName(IHTMLConstants.TAG_OPTGROUP);
				for (int i = 0, n = options.getLength(); i < n; i++) {
					result.add(options.item(i));
				}
				for (int i = 0, n = optionGroups.getLength(); i < n; i++) {
					result.add(optionGroups.item(i));
				}
				return result.toArray(new Node[result.size()]);
			}
		}
		return null;
	}

	/**
	 * @param node
	 * @return the selection option children of node as strings
	 */
	public static String[] getSelectOptionsString(Element node) {
		if (node == null) {
			return null;
		}
		Object[] options = getSelectOptions(node);
		if (null == options || options[0] == null) {
			return null;
		}
		List result = new ArrayList();
		for (int i = 0, n = options.length; i < n; i++) {
			if (options[i] instanceof Element) {
				Element element = (Element) options[i];
				Node value = null;
				if (element.getNodeName()
						.indexOf(IJSFConstants.TAG_SELECTITEMS) >= 0) {
					value = element.getAttributeNode(IJSFConstants.ATTR_VALUE);
				} else if (element.getNodeName().indexOf(
						IJSFConstants.TAG_SELECTITEM) >= 0) {
					value = element
							.getAttributeNode(ICSSPropertyID.ATTR_ITEMLABEL);
				}
				if (value != null) {
					result.add(value.getNodeValue());
				}
			}
		}
		return (String[]) result.toArray(new String[] {});
	}

	/**
	 * @param node
	 * @return true if node has select option children
	 */
	public static boolean hasSelectOptions(Element node) {
		if (node == null) {
			return false;
		}
		String uri = CMUtil.getElementNamespaceURI(node);
		if (ITLDConstants.URI_JSF_HTML.equals(uri)) {

			if (JSF_SELECT_TAGS.contains(node.getLocalName())) {
				IDOMModel model = ((IDOMElement) node).getModel();
				String jsfcorePrefix = JSFUtil.getPrefix(model,
						ITLDConstants.URI_JSF_CORE);
				if (jsfcorePrefix != null) {
					NodeList nl = node.getElementsByTagName(jsfcorePrefix + ":" //$NON-NLS-1$
							+ IJSFConstants.TAG_SELECTITEM);
					NodeList nl1 = node.getElementsByTagName(jsfcorePrefix
							+ ":" + IJSFConstants.TAG_SELECTITEMS); //$NON-NLS-1$
					return nl.getLength() > 0 || nl1.getLength() > 0;
				}
                return false;
			}
		}
		if (ITLDConstants.URI_HTML.equals(uri)) {
			if (HTML_SELECT_TAGS.contains(node.getNodeName().toLowerCase())) {
				NodeList nl = node
						.getElementsByTagName(IHTMLConstants.TAG_OPTION);
				NodeList nl1 = node
						.getElementsByTagName(IHTMLConstants.TAG_OPTGROUP);
				return nl.getLength() > 0 || nl1.getLength() > 0;
			}
		}
		return false;
	}

	/**
	 * @param node
	 * @return true if node is a core or HTML select tag
	 */
	public static boolean supportSections(Element node) {
		String uri = CMUtil.getElementNamespaceURI(node);
		if (ITLDConstants.URI_JSF_HTML.equals(uri)) {
			return JSF_SELECT_TAGS.contains(node.getLocalName());
		}
		if (ITLDConstants.URI_HTML.equals(uri)) {
			return HTML_SELECT_TAGS.contains(node.getNodeName().toLowerCase());
		}
		return false;
	}
}
