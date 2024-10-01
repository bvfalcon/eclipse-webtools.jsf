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
import java.util.Comparator;

import javax.xml.namespace.QName;

import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.adapters.IBodyInfo;
import org.eclipse.jst.pagedesigner.adapters.internal.BodyInfo;
import org.eclipse.jst.pagedesigner.dom.DOMPosition;
import org.eclipse.jst.pagedesigner.dom.DOMRefPosition;
import org.eclipse.jst.pagedesigner.dom.DOMRefPosition2;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMText;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class helps location insertion position to inside correct body or doc
 * prefix. NOTE: this class only doing limited support on doc level position
 * validation. Element specific position validation will be done in other
 * places.
 * 
 * @author mengbo
 */
public class BodyHelper {
	// bit flags used for child skipping.
	/**
	 * Bit flag for empty text node
	 */
	public static final int EMPTY_TEXT = 1;

	/**
	 * Bit flag for comment node
	 */
	public static final int COMMENT = 2;

	/**
	 * Bit flag for HEAD node
	 */
	public static final int HEADER = 3;

	/**
	 * 
	 * @param child
	 * @return boolean
	 */
	private static boolean isSkippableChild(Node parent, Node child, int flag) {
		if ((flag & COMMENT) != 0 && child.getNodeType() == Node.COMMENT_NODE)
			return true;
		if ((flag & EMPTY_TEXT) != 0 && child instanceof IDOMText
				&& ((IDOMText) child).isElementContentWhitespace())
			return true;

		if ((flag & HEADER) != 0 && child.getNodeType() == Node.ELEMENT_NODE) {
			String uri = CMUtil.getElementNamespaceURI((Element) child);
			IBodyInfo parentInfo = getBodyInfo((IDOMNode) parent);
			if (parentInfo != null
					&& parentInfo.isBodyHeader((IDOMNode) parent, uri,
							((Element) child).getLocalName()))
				return true;
		}
		return false;
	}

	/**
	 * check whether uri/tag should be header of any body container that is
	 * ancester of the start node.
	 * 
	 * @param start
	 * @param uri
	 * @param tag
	 * @return IDOMNode
	 */
	public static IDOMNode findHeaderContainer(IDOMNode start, String uri,
			String tag) {
		while (start != null) {
			IBodyInfo designInfo = getBodyInfo(start);
			if (designInfo != null && designInfo.isBodyContainer(start)) {
				if (designInfo.isBodyHeader(start, uri, tag))
					return start;
			}
			start = (IDOMNode) start.getParentNode();
		}
		return null;
	}

	/**
	 * find the closest body insertion point, to make it as deep as possible.
	 * (Move into as more body as possible)
	 * @param position 
	 * @return IDOMPosition
	 */
	public static IDOMPosition findBodyInsertLocation(IDOMPosition position) {
		// forward first.
		Node reference = position.getNextSiblingNode();
		Node container = position.getContainerNode();
		while (reference != null) {
			IBodyInfo info = getBodyInfo((IDOMNode) reference);
			if (info != null && info.isBodyContainer((IDOMNode) reference)) {
				// good, we find a body!
				position = new DOMPosition(reference, 0);
				return findBodyInsertLocation(position);
			}
			if (isSkippableChild(container, reference, EMPTY_TEXT | COMMENT
					| HEADER)) {
				reference = reference.getNextSibling();
				continue;
			}
            break;
		}

		// backward
		reference = position.getPreviousSiblingNode();
		while (reference != null) {
			IBodyInfo info = getBodyInfo((IDOMNode) reference);
			if (info != null && info.isBodyContainer((IDOMNode) reference)) {
				// good, we find a body!
				position = new DOMPosition(reference, reference.getChildNodes()
						.getLength());
				return findBodyInsertLocation(position);
			}
			// XXX: not skip header here. So if there is some header with wrong
			// location, we will respect user.
			if (isSkippableChild(container, reference, EMPTY_TEXT | COMMENT)) {
				reference = reference.getPreviousSibling();
				continue;
			}
            break;
		}

		// not find any body at same level as the insertion point.
		return position;
	}

	/**
	 * The element type identified by "uri" and "tag" is going to be inserted
	 * into the document. This method is used to adjust the insert position so
	 * it can be put into correct body or header section.
	 * @param uri 
	 * @param tag 
	 * @param position 
	 * @return IDOMPosition
	 * 
	 */
	public static IDOMPosition adjustInsertPosition(String uri, String tag,
			IDOMPosition position) {
		IDOMNode parent = (IDOMNode) position.getContainerNode();
		IBodyInfo designInfo = getBodyInfo(parent);
		if (designInfo == null) {
			return position; // should not happen.
		}

		IDOMNode headerContainer = findHeaderContainer(parent, uri, tag);

		if (headerContainer == null) {
			// the new node is not header.
			if (shouldIgnoreAdjust(uri, tag)) {
				return position;
			}

			// new node is not body header. So should place inside the inner most
			// body.
			if (!designInfo.isBodyContainer(parent)) {
				return position; // it's parent is not body, so we suggest
				// it's parent already correctly located, and respect user's
				// choice.
			}

			// ok, we are inside some body, but we don't know whether we are in
			// the inner most body.
			// try to find a body container at same level and see whether we can
			// move into that body.
			return findBodyInsertLocation(position);
		}
        // good, we find a body container and the new node should be header
        // of it.
        Node child = headerContainer.getFirstChild();

        // if parent is different from headerContainer, then
        // child!=referenceHolder[0] will always be true
        while (child != null) // && child != refNode)
        {
        	Comparator comp = NodeLocationComparator.getInstance();
        	// Currently the comparator deels with tags like taglib and
        	// loadbundle particularly, comparasion result 0
        	// means it didn't compare the tags.
        	if (comp.compare(child, tag) < 0
        			|| (comp.compare(child, tag) == 0 && isSkippableChild(
        					headerContainer, child, COMMENT | EMPTY_TEXT
        							| HEADER))) {
        		child = child.getNextSibling();
        	} else {
        		break;
        	}
        }
        if (child != null) {
        	return new DOMRefPosition(child, false);
        }
        return new DOMPosition(parent, parent.getChildNodes()
        		.getLength());
	}

	/**
	 * Find the position to insert a header element into the specified parent.
	 * 
	 * @param uri
	 * @param tag
	 * @param parent
	 * @param ref 
	 */
	public static void findHeaderInsertPosition(String uri, String tag,
			Node parent, Node[] ref) {
		Node child = parent.getFirstChild();
		while (child != null) {
			Comparator comp = NodeLocationComparator.getInstance();
			if (comp.compare(child, tag) < 0
					|| (comp.compare(child, tag) == 0 && isSkippableChild(
							parent, child, COMMENT | EMPTY_TEXT | HEADER))) {
				child = child.getNextSibling();
			} else {
				break;
			}
		}
		ref[0] = child;
		return;
	}

	/**
	 * @param position
	 * @param body
	 * @param defaultPrefix
	 * @return the new dom position based on the insert.  May return null if
	 * insert fails.
	 */
	public static IDOMPosition insertBody(IDOMPosition position, QName body,
			String defaultPrefix) {
		IBodyInfo bodyInfo = getBodyInfo((IDOMNode) position.getContainerNode());

		Node node = position.getContainerNode();
		final Node originalContainer = node;
		final Node nextSibling = position.getNextSiblingNode();

		// create the body element first.
		Document ownerDoc;
		if (node instanceof Document) {
			ownerDoc = (Document) node;
		} else {
			ownerDoc = node.getOwnerDocument();
		}
		if (ownerDoc == null) {
			return null; // should not happen
		}

		final String prefix = JSFUtil.getOrCreatePrefix(((IDOMNode) node).getModel(),
				body.getNamespaceURI(), defaultPrefix);
		final Element ele = ownerDoc.createElement((prefix == null ? "" //$NON-NLS-1$
				: (prefix + ":")) //$NON-NLS-1$
				+ body.getLocalPart());

		// need to find out the insertion point
		while (node instanceof IDOMNode) {
			if (bodyInfo.isBodyContainer((IDOMNode) node)) {
				// ok, node is a body container.
				// we could create the new node as child of node and move all
				// node's none header children
				// as children of the new node.

				NodeList nl = node.getChildNodes();
				ArrayList list = new ArrayList();
				for (int i = 0; i < nl.getLength(); i++) {
					Node child = nl.item(i);
					if (isSkippableChild(node, child, HEADER | COMMENT
							| EMPTY_TEXT)) {
						continue;
					}
					list.add(nl.item(i));
				}
				for (int i = 0; i < list.size(); i++) {
					ele.appendChild((Node) list.get(i));
				}
				node.appendChild(ele);

				if (node == originalContainer) {
					if (nextSibling == null) {
						return new DOMRefPosition2(ele, true);
					} else if (nextSibling.getParentNode() == ele) {
						// next sibling is not in header part
						return new DOMRefPosition(nextSibling, false);
					} else {
						return new DOMPosition(ele, 0);
					}
				}
                return position;
			}
			node = node.getParentNode();
		}
		// should not happen, because document and documentfragment node will
		// always be body node
		// so if reach here, means the position is not in document.
		return null;
	}

	/**
	 * For certain special tags, do not following the "header"/"body" separation
	 * and can't fit into the relocation process.
	 * 
	 * @param uri
	 * @param tag
	 * @return true if tag is an element that should be moved in response to 
	 * body insert.
	 */
	public static boolean shouldIgnoreAdjust(String uri, String tag) {
		// FIXME:
		return (ITLDConstants.URI_HTML.equalsIgnoreCase(uri) && "script" //$NON-NLS-1$
				.equalsIgnoreCase(tag))
				|| (ITLDConstants.URI_JSP.equals(uri));
	}

	/**
	 * @param node
	 * @return the body info corresponding to node (should we use a node adapter?)
	 */
	public static IBodyInfo getBodyInfo(IDOMNode node) {
		// TODO: in the future, when bodyinfo is no longer singleton, we'll use
		// adapter mechanism.
		return BodyInfo.getInstance();
	}
}
