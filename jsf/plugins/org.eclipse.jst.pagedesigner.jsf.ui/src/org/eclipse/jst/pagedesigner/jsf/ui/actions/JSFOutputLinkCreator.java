/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http:// https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui.actions;

import org.eclipse.gef.EditPart;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.actions.link.AbstractLinkCreator;
import org.eclipse.jst.pagedesigner.actions.link.LinkUtil;
import org.eclipse.jst.pagedesigner.parts.TextEditPart;
import org.eclipse.jst.pagedesigner.utils.JSFUtil;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * @author mengbo
 * @version 1.5
 */
public class JSFOutputLinkCreator extends AbstractLinkCreator
{
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.actions.link.ILinkCreator#makeLinkElement(org.eclipse.gef.EditPart, org.eclipse.jst.pagedesigner.viewer.DesignRange)
     */
    public Element makeLinkElement(EditPart part, DesignRange range)
    {
        if (part instanceof TextEditPart)
        {
            Text middleNode = LinkUtil.splitDomText(part, range);
            EditPart parent = part.getParent();
            Node parentNode = (Node) parent.getModel();
            Document doc = (parentNode instanceof Document) ? (Document) parentNode : (parentNode.getOwnerDocument());

            IDOMModel model = ((IDOMNode) parentNode).getModel();
            String prefix = JSFUtil.getOrCreatePrefix(model, ITLDConstants.URI_JSF_HTML, "h"); //$NON-NLS-1$
            String fPrefix = JSFUtil.getOrCreatePrefix(model, ITLDConstants.URI_JSF_CORE, "f"); //$NON-NLS-1$

            Element outputLink = doc.createElement(IJSFConstants.TAG_OUTPUTLINK);
            outputLink.setPrefix(prefix);
            Element verbatim = doc.createElement(IJSFConstants.TAG_VERBATIM);
            verbatim.setPrefix(fPrefix);
            Text value = doc.createTextNode(middleNode.getNodeValue());
            verbatim.appendChild(value);
            outputLink.appendChild(verbatim);

            parentNode.replaceChild(outputLink, middleNode);
            return outputLink;
        }

        return null;

    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.actions.link.AbstractLinkCreator#canExecute(org.eclipse.jst.pagedesigner.viewer.DesignRange)
     */
    public boolean canExecute(DesignRange range)
    {
        DesignPosition startPosition = range.getStartPosition();
        EditPart part = startPosition.getContainerPart();
        IDOMNode node = (IDOMNode) part.getModel();
        IDOMModel model = node.getModel();
        return model.getDocument().getElementsByTagNameNS(ITLDConstants.URI_JSF_HTML, "view") != null; //$NON-NLS-1$
    
    }

    public String getSourcePreview(EditPart part, DesignRange range)
    {
        if (part instanceof TextEditPart)
        {
            TextEditPart textPart = (TextEditPart) part;
            int[] offsets = textPart.getSelectedRange();
            String displayData = textPart.getTextData();
            String linkExp = displayData.substring(offsets[0], offsets[1]);

            StringBuffer sb = new StringBuffer();
            sb.append("<h:outputLink>\n<f:verbatim>"); //$NON-NLS-1$
            sb.append(linkExp);
            sb.append("</f:verbatim>\n</h:outputLink>"); //$NON-NLS-1$
            return sb.toString();
        }
        return null;
    }
}
