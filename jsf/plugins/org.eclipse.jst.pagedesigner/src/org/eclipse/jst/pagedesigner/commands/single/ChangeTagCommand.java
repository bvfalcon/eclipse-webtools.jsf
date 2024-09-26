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
package org.eclipse.jst.pagedesigner.commands.single;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.utils.JSFUtil;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This is for command that changes an element's tagname and some attributes.
 * 
 * @author mengbo
 */
public class ChangeTagCommand extends SingleNodeCommand {
    private final IDOMElement _element;

    private final Map _attributes;

    private final boolean _moveContent;

    private String _uri;

    private String _localTag;

    private  String _totalTag;

	/**
	 * @param label
	 * @param node
	 * @param totaltag
	 * @param attributes
	 * @param movecontent
	 */
	public ChangeTagCommand(String label, IDOMElement node, String totaltag,
			Map attributes, boolean movecontent) {
		super(label, node);
		this._element = node;
		this._totalTag = totaltag;
		this._attributes = attributes;
		this._moveContent = movecontent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
	 */
	protected void doExecute() {
		String tag = null;

		if (_totalTag != null) {
			tag = _totalTag;
		} else {
			if (ITLDConstants.URI_HTML.equals(_uri)) {
				tag = _localTag;
			} else if (ITLDConstants.URI_JSP.equals(_uri)) {
				tag = "jsp:" + _localTag; //$NON-NLS-1$
			} else {
				tag = JSFUtil.getOrCreatePrefix(getModel(), _uri, null)
						+ _localTag;
			}
		}
		// we need to remove the old element and create a new one with the new
		// tag.
		IDOMElement replacement = (IDOMElement) _element.getOwnerDocument()
				.createElement(tag);
		NamedNodeMap attrs = _element.getAttributes();
		for (int i = 0, n = attrs.getLength(); i < n; i++) {
			Attr a = (Attr) attrs.item(i);
			replacement.setAttribute(a.getName(), a.getValue());
		}
		if (_attributes != null) {
			for (Iterator iter = _attributes.keySet().iterator(); iter
					.hasNext();) {
				String name = (String) iter.next();
				String value = (String) _attributes.get(name);
				replacement.setAttribute(name, value);
			}
		}
		if (_moveContent) {
			NodeList nl = _element.getChildNodes();
			ArrayList list = new ArrayList();
			for (int i = 0, n = nl.getLength(); i < n; i++) {
				list.add(nl.item(i));

			}
			for (int i = 0, n = list.size(); i < n; i++) {
				replacement.appendChild((Node) list.get(i));
			}
		}
		_element.getParentNode().replaceChild(replacement, _element);
		setReplacedElement(replacement);
	}

	/**
	 * @return the replacemd element
	 */
	public IDOMElement getNewElement() {
		return getReplacedElment();
	}
}
