/*******************************************************************************
 * Copyright (c) 2006, 2011 Sybase, Inc. and others.
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
package org.eclipse.jst.pagedesigner.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo;
import org.eclipse.jst.pagedesigner.dom.DOMUtil;
import org.eclipse.jst.pagedesigner.preview.PageExpressionContext;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.sse.core.internal.provisional.INodeAdapter;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * This is base class for all non-hidden tag converters.
 * 
 * @author mengbo
 * @version 1.5
 */
public abstract class AbstractTagConverter implements ITagConverter,
		ITagEditInfo, INodeAdapter, IDOMFactory {
	private IDOMDocument _targetDocument;

	private Element _hostElement;

	private Element _resultElement;

	private List _childNodes = Collections.EMPTY_LIST;

	private Map _childNodePositions = Collections.EMPTY_MAP;

	private int _mode;

	private int _minWidth;

	private int _minHeight;

	private boolean _needBorderDecorator;

	/**
	 * @param host 
	 * 
	 */
	public AbstractTagConverter(Element host) {
		_hostElement = host;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#setTargetDocument(org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument)
	 */
	public void setDestDocument(IDOMDocument document) {
		_targetDocument = document;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#convertRefresh(java.lang.Object)
	 */
	public final void convertRefresh(Object context) {
		_resultElement = null;
		_childNodes = new ArrayList();
		_childNodePositions = new HashMap();

		_resultElement = doConvertRefresh();
		if (_resultElement instanceof INodeNotifier) {
			((INodeNotifier) _resultElement).addAdapter(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.sse.core.internal.provisional.INodeAdapter#notifyChanged(org.eclipse.wst.sse.core.internal.provisional.INodeNotifier,
	 *      int, java.lang.Object, java.lang.Object, java.lang.Object, int)
	 */
	public void notifyChanged(INodeNotifier notifier, int eventType,
			Object changedFeature, Object oldValue, Object newValue, int pos) {
		// do nothing.
	}

	/**
	 * Child class should override this method. The child class should NEVER
	 * change the host DOM structure.
	 * 
	 * @return the convert result. Should be an HTML element.
	 */
	protected abstract Element doConvertRefresh();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#getHostElement()
	 */
	public final Element getHostElement() {
		return _hostElement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#getResultElement()
	 */
	public final Element getResultElement() {
		return _resultElement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#getChildModeList()
	 */
	public final List getChildModeList() {
		return _childNodes;
	}


	public List getNonVisualChildren() 
    {
	    // by default, no non-visual children
        return Collections.EMPTY_LIST;
    }

    /**
	 * child class should call this method.
	 * 
	 * @param childNode
	 *            the childNode of the hostElement that should be futher
	 *            converted.
	 * @param position
	 * 
	 */
	protected void addChild(Node childNode, ConvertPosition position) {
		_childNodes.add(childNode);
		_childNodePositions.put(childNode, position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#getChildVisualPosition(org.w3c.dom.Node)
	 */
	public final ConvertPosition getChildVisualPosition(Node childModel) {
		return (ConvertPosition) _childNodePositions.get(childModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#isVisualByHTML()
	 */
	public boolean isVisualByHTML() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#getVisualImage()
	 */
	public Image getVisualImage() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#dispose()
	 */
	public void dispose() {
        // do nothing; children may wish to sub-class
        // TODO: null shared references?
        // this doesn't seem to be called by anybody..
        // need to review this
	}

	/**
	 * @param node
	 * @return true if the node should be ignored for  conversion purposes
	 */
	protected boolean shouldIgnore(Node node) {
		int nodeType = node.getNodeType();
		switch (nodeType) {
		case Node.TEXT_NODE:
		case Node.CDATA_SECTION_NODE:
		case Node.ELEMENT_NODE:
			return false;
		default:
			return true;
		}

	}

	/**
	 * utility method for those converter that only converts the host tag's name
	 * and directly copy children.
	 * @param src 
	 * @param dest 
	 * 
	 */
	protected void copyChildren(Element src, Element dest) {
		Node node = src.getFirstChild();
		int index = 0;
		for (; node != null; node = node.getNextSibling()) {
			if (!shouldIgnore(node)) {
				addChild(node, new ConvertPosition(dest, index++));
			}
		}
	}

	/**
	 * utility method for those converter that directly copy children.
	 * @param src 
	 * @param dest 
	 * 
	 */
	protected void dumCopyChildren(Element src, Element dest) {
		Node node = src.getFirstChild();
		Document destDoc = dest.getOwnerDocument();
		for (; node != null; node = node.getNextSibling()) {
			if (!shouldIgnore(node)) {
				Node n = DOMUtil.cloneNodeDeepIgnoreError(destDoc, node);
				dest.appendChild(n);
			}
		}
	}

	/**
	 * In the future, the conversion result HTML DOM tree could be in another
	 * document.
	 * 
	 * @return the destination document
	 */
	public IDOMDocument getDestDocument() {
		if (this._targetDocument != null) {
			return this._targetDocument;
		}
        return (IDOMDocument) _hostElement.getOwnerDocument();
	}

	/**
	 * shortcut method. Child class should always use this method to create a
	 * result element.
	 * 
	 * @param tagName
	 * @return a new element named tagName
	 */
	public Element createElement(String tagName) {
		return getDestDocument().createElement(tagName);
	}

	/**
	 * shortcut method. Child class should always use this method to create a
	 * text node.
	 * 
	 * @param text
	 * @return a new text node using text as the value
	 */
	public Text createText(String text) {
		return getDestDocument().createTextNode(text);
	}

	/**
	 * @param original
	 * @return the mapped String  TODO: currently does nothing
	 */
	protected String mapURL(String original) {
		// TODO: how to map URL? such as original url look like:
		// getContext().getPath()+...
		return original;
	}

	// TODO: FIXME: XXX:
	// if the value is expression, we may want to do something here!!!
	/**
	 * @param value
	 * @return value mapped based on EL expression
	 */
	protected String mapValue(String value) {
		if (value == null) {
			return null;
		}
		if (isDesignerMode()) {
			// if there has jsf binding expressions
			int checkPos = value.indexOf("#{"); //$NON-NLS-1$
			if (checkPos != -1) {
				String mapValue = ""; //$NON-NLS-1$
				int preferType = PreferenceReader.getMapValueType();
				switch (preferType) {
				case PreferenceReader.FULL_EXPRESSION_TYPE:
					mapValue = value;
					break;
				case PreferenceReader.LAST_EXPRESSION_TYPE:
					String strBackup = value;
					StringBuffer sb = new StringBuffer();
					while (strBackup.indexOf("#{") != -1) { //$NON-NLS-1$
						int pos = strBackup.indexOf("#{"); //$NON-NLS-1$
						int endBracketPos = strBackup.indexOf("}", pos + 1); //$NON-NLS-1$
						if (endBracketPos != -1) {
							sb.append(strBackup.substring(0, pos + 2));
							String exp = strBackup.substring(pos + 2,
									endBracketPos);
							if (allowTrim(exp)) {
								int lastDotPos = exp.lastIndexOf("."); //$NON-NLS-1$
								if (lastDotPos != -1) {
									String convertedExp = exp
											.substring(lastDotPos + 1);
									sb.append(convertedExp);
								} else {
									sb.append(exp);
								}

							} else {
								sb.append(exp);
							}
							sb.append("}"); //$NON-NLS-1$
						} else {
							break;
						}
						if (strBackup.length() > endBracketPos + 1) {
							strBackup = strBackup.substring(endBracketPos + 1);
						} else {
							strBackup = ""; //$NON-NLS-1$
							break;
						}

					}
					sb.append(strBackup);
					mapValue = sb.toString();
					break;
				case PreferenceReader.REAL_VALUE_TYPE:
					// TODO calculate the expression value
				default:
					mapValue = value;
					break;
				}

				return mapValue;
			}
		} else {
			// preview mode. let's try to display the value.
			try {
				//Bug 319317 - Third-party plug-in providing jakarta.el version 2.1 or greater breaks WPE preview
				Map options = new HashMap();
				options.put("ELEMENT", _hostElement); //$NON-NLS-1$
				return (String) PageExpressionContext.getCurrent()
						.evaluateExpression(value, String.class, options);
			} catch (Exception ex) {
				// can't calculate the result. ignore.
				// ex.printStackTrace();
			}
		}
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#needBorderDecorator()
	 */
	public boolean needBorderDecorator() {
		return this._needBorderDecorator;
	}

	/**
	 * @param b
	 */
	public void setNeedBorderDecorator(boolean b) {
		this._needBorderDecorator = b;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#needTableDecorator()
	 */
	public boolean needTableDecorator() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.sse.core.internal.provisional.INodeAdapter#isAdapterForType(java.lang.Object)
	 */
	public boolean isAdapterForType(Object type) {
		if (type == ITagEditInfo.class) {
			return true;
		}
		return false;
	}

	/**
	 * @param mode
	 */
	public final void setMode(int mode) {
		this._mode = mode;
	}

	/**
	 * @return true if the converter mode is preview
	 */
	public final boolean isPreviewMode() {
		return this._mode == IConverterFactory.MODE_PREVIEW;
	}

	/**
	 * @return true if the converter mode is designer
	 */
	public final boolean isDesignerMode() {
		return this._mode == IConverterFactory.MODE_DESIGNER;
	}

	/**
	 * @return the converter mode
	 */
	public final int getMode() {
		return this._mode;
	}

	/**
	 * The method is used to judge whether the value binding and method binding
	 * expression is allowed to be trimmed.Currently only expression contains
	 * only letter,digit,and '.' is allowed to be trimmed.
	 * 
	 * @param expression
	 *            value binding or method binding expression
	 * @return
	 */
	private boolean allowTrim(String expression) {
		for (int i = 0, size = expression.length(); i < size; i++) {
			char ch = expression.charAt(i);
			if (!Character.isLetterOrDigit(ch) && (ch != '.') && (ch != '_')) {
				return false;
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#getMinWidth()
	 */
	public int getMinWidth() {
		return this._minWidth;
	}

	/**
	 * @param minWidth
	 */
	public void setMinWidth(int minWidth) {
		this._minWidth = minWidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#getMinHeight()
	 */
	public int getMinHeight() {
		return this._minHeight;
	}

	/**
	 * @param minHeight
	 */
	public void setMinHeight(int minHeight) {
		this._minHeight = minHeight;
	}

	/**
	 * @param element
	 * @param attrname
	 * @return the attribute on element with the name attrname
	 */
	public static boolean hasAttribute(Element element, String attrname) {
		return element.hasAttribute(attrname);
	}
}
