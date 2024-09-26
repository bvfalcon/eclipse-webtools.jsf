/*******************************************************************************
 * Copyright (c) 2006, 2010 Sybase, Inc. and others.
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

import java.util.Iterator;
import java.util.Map;

import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.utils.JSFUtil;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 * @version 1.5
 */
public class AddSubNodeCommand extends SingleNodeCommand {
	private final IDOMElement _parent;
	private IDOMElement _child;

	private final String _tagName;

	private final String _url;

	private final Map _attributes;

	/**
	 * @param label
	 * @param node
	 * @param name 
	 * @param url 
	 * @param attributs 
	 */
	public AddSubNodeCommand(String label, IDOMElement node, String name,
			String url, Map attributs) {
		super(label, node);
		this._parent = node;
		this._tagName = name;
		this._url = url;
		this._attributes = attributs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
	 */
	protected void doExecute() {
		String nodeName = null;
		if (ITLDConstants.URI_HTML.equals(_url)) {
			nodeName = _tagName;
		} else {
			String prefix = JSFUtil.getOrCreatePrefix(_parent.getModel(), _url, null);
			nodeName = prefix + ":" + _tagName; //$NON-NLS-1$
		}

		_child = (IDOMElement) _parent.getOwnerDocument().createElement(nodeName);

		if (_child == null) {
			return;
		}

		for (Iterator iterator = _attributes.keySet().iterator(); iterator
				.hasNext();) {
			String key = (String) iterator.next();
			String value = (String) _attributes.get(key);
			_child.setAttribute(key, value);
		}

		_parent.appendChild(_child);
	}

	/**
	 * @return the child node
	 */
	public IDOMElement getChildNode() {
		return _child;
	}
}
