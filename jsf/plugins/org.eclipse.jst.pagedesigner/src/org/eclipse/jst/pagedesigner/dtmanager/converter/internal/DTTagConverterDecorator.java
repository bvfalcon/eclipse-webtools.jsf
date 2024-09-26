/*******************************************************************************
 * Copyright (c) 2005, 2011 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.dtmanager.converter.internal;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import jakarta.el.ELException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IImageDescriptorProvider;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;
import org.eclipse.jst.jsf.common.metadata.internal.IResourceURLProvider;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.converter.IConverterFactory;
import org.eclipse.jst.pagedesigner.converter.ITagConverter;
import org.eclipse.jst.pagedesigner.dtmanager.DTManager;
import org.eclipse.jst.pagedesigner.dtmanager.IDTInfo;
import org.eclipse.jst.pagedesigner.dtmanager.converter.ITagConverterDecorator;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.ResolveAttributeValue;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo;
import org.eclipse.jst.pagedesigner.preview.PageExpressionContext;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * ITagConverterDecorator implementation for DTTagConverter.
 * 
 * @author Ian Trimble - Oracle
 */
public class DTTagConverterDecorator implements ITagConverterDecorator
{

    private static final String DECORATE_INFO_ID_DESIGN = "vpd-decorate-design"; //$NON-NLS-1$
    private static final String DECORATE_INFO_ID_PREVIEW = "vpd-decorate-preview"; //$NON-NLS-1$
    private static final String MD_PLUGIN_LOCATION = "$metadata-plugin-location$"; //$NON-NLS-1$

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jst.pagedesigner.dtmanager.converter.ITagConverterDecorator
     * #decorate(org.eclipse.jst.pagedesigner.converter.ITagConverter)
     */
    public void decorate(ITagConverter tagConverter)
    {
        if (!(tagConverter instanceof DTTagConverter))
        {
            throw new IllegalArgumentException(
                    "ITagConverter argument must be an instance of DTTagConverter"); //$NON-NLS-1$
        }
        DTTagConverter dtTagConverter = (DTTagConverter) tagConverter;

        if (dtTagConverter.getMode() == IConverterFactory.MODE_DESIGNER)
        {
            decorateFromDTInfo(dtTagConverter, DECORATE_INFO_ID_DESIGN);
        } else if (dtTagConverter.getMode() == IConverterFactory.MODE_PREVIEW)
        {
            decorateFromDTInfo(dtTagConverter, DECORATE_INFO_ID_PREVIEW);
        }

        if (tagConverter.getResultElement() == null
                && tagConverter.isVisualByHTML())
        {
            createUnknownTagRepresentation(dtTagConverter);
        }
    }

    /**
     * Performs decoration of the specified DTTagConverter instance from IDTInfo
     * (metadata) for the specified (by ID) TagDecorateInfo.
     * 
     * @param dtTagConverter
     *            DTTagConverter instance.
     * @param tagDecorateInfoID
     *            ID of the TagDecorateInfo to be located in metadata.
     */
    protected void decorateFromDTInfo(DTTagConverter dtTagConverter,
            String tagDecorateInfoID)
    {
        Element srcElement = dtTagConverter.getHostElement();
        DTManager dtManager = DTManager.getInstance();
        IDTInfo dtInfo = dtManager.getDTInfo(srcElement);
        if (dtInfo != null)
        {
            TagDecorateInfo tdInfo = dtInfo
                    .getTagDecorateInfo(tagDecorateInfoID);
            if (tdInfo != null)
            {
                dtTagConverter.setMultiLevel(tdInfo.isMultiLevel());
                dtTagConverter.setNeedBorderDecorator(tdInfo
                        .isNeedBorderDecorator());
                dtTagConverter.setNeedTableDecorator(tdInfo
                        .isNeedTableDecorator());
                if (tdInfo.isNonVisual())
                {
                    setNonVisual(dtTagConverter, dtInfo, tdInfo
                            .getNonVisualImagePath());
                }
                if (tdInfo.isResolveChildText())
                {
                    resolveChildText(dtTagConverter.getResultElement(), dtInfo);
                }
                if (tdInfo.isSetNonVisualChildElements())
                {
                    setNonVisualChildElements(dtTagConverter, srcElement);
                }
                dtTagConverter.setWidget(tdInfo.isWidget());
                dtTagConverter.setMinHeight(tdInfo.getMinHeight());
                dtTagConverter.setMinWidth(tdInfo.getMinWidth());
                ResolveAttributeValue resAttrValue = tdInfo
                        .getResolveAttributeValue();
                if (resAttrValue != null)
                {
                    String attributeName = resAttrValue.getAttributeName();
                    if (attributeName != null && attributeName.length() > 0)
                    {
                        resolveAttributeValue(dtTagConverter.getHostElement(),
                                dtTagConverter.getResultElement(),
                                attributeName, dtInfo, tagDecorateInfoID);
                    }
                }
            }
        }
    }

    /**
     * Creates a visual representation result Element for an unknown tag.
     * 
     * @param dtTagConverter
     *            DTTagConverter instance.
     */
    protected void createUnknownTagRepresentation(DTTagConverter dtTagConverter)
    {
        Element element = dtTagConverter.createElement("span"); //$NON-NLS-1$
        element.setAttribute("style", "color:red;font-weight:bold;"); //$NON-NLS-1$ //$NON-NLS-2$
        Text text = dtTagConverter
                .createText("<" + dtTagConverter.getHostElement().getTagName() + "/>"); //$NON-NLS-1$ //$NON-NLS-2$
        element.appendChild(text);
        dtTagConverter.setResultElement(element);
        dtTagConverter.setWidget(true);
    }

    /**
     * Adds child Elements of the specified source Element to the specified
     * DTTagConverter instance's collection of non-visual children.
     * 
     * @param dtTagConverter
     *            DTTagConverter instance.
     * @param srcElement
     *            Source Element for which child Elements are to be added.
     */
    protected void setNonVisualChildElements(DTTagConverter dtTagConverter,
            Element srcElement)
    {
        NodeList childNodes = srcElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++)
        {
            Node curNode = childNodes.item(i);
            if (curNode.getNodeType() == Node.ELEMENT_NODE)
            {
                dtTagConverter.addNonVisualChildElement((Element) curNode);
            }
        }
    }

    /**
     * Performs simple EL resolution for the child Text Node of the specified
     * source Element instance.
     * 
     * @param srcElement
     *            Source Element for which child Text Node EL resolution is to
     *            be performed.
     * @param dtInfo
     *            IDTInfo instance.
     */
    protected void resolveChildText(Element srcElement, IDTInfo dtInfo)
    {
        if (srcElement != null)
        {
            NodeList childNodes = srcElement.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++)
            {
                Node childNode = childNodes.item(i);
                if (childNode.getNodeType() == Node.TEXT_NODE)
                {
                    Text textNode = (Text) childNode;
                    String textNodeValue = textNode.getNodeValue();
                    try
                    {
                        String newTextNodeValue;
                        if (textNodeValue.contains(MD_PLUGIN_LOCATION))
                        {
                            newTextNodeValue = resolveMDPluginLocation(
                                    textNodeValue, dtInfo);
                        } else
                        {
                        	//Bug 319317 - Third-party plug-in providing javax.servlet.jsp.el version 2.1 or greater breaks WPE preview
                        	Map options = new HashMap();
                        	options.put("ELEMENT", srcElement); //$NON-NLS-1$
                            newTextNodeValue = (String) PageExpressionContext
                                    .getCurrent().evaluateExpression(
                                            textNodeValue, String.class, options);
                        }
                        if (newTextNodeValue != null
                                && !textNodeValue.equals(newTextNodeValue))
                        {
                            textNode.setNodeValue(newTextNodeValue);
                        }
                    } catch (Exception ex)
                    {
                        // ignore - could not resolve, do not change existing
                        // value
                    }
                }
            }
        }
    }

    /**
     * Performs simple EL resolution for the value of the specified attribute of
     * the specified Element.
     * 
     * @param srcElement
     * 
     * @param targetElement
     *            Source Element instance.
     * @param attributeName
     *            Name of attribute for which the value should be resolved.
     * @param dtInfo
     *            IDTInfo instance.
     * @param tagDecorateInfoID 
     */
    protected void resolveAttributeValue(Element srcElement,
            Element targetElement, String attributeName, IDTInfo dtInfo, 
            String tagDecorateInfoID)
    {
        if (targetElement != null)
        {
            String oldAttributeValue = null;
            String targetAttributeName = attributeName;
            // determine if attributeName is XPath and re-target as appropriate
            if (attributeName.contains("/")) { //$NON-NLS-1$
                int lastSlashPos = attributeName.lastIndexOf("/"); //$NON-NLS-1$
                String xPathExpression = attributeName.substring(0,
                        lastSlashPos);
                XPath xPath = XPathFactory.newInstance().newXPath();
                try
                {
                    Object resultObject = xPath.evaluate(xPathExpression,
                            targetElement, XPathConstants.NODE);
                    if (resultObject instanceof Element)
                    {
                        targetElement = (Element) resultObject;
                        targetAttributeName = attributeName
                                .substring(lastSlashPos + 1);
                    }
                    else if (resultObject instanceof Text)
                    {
                        Node parentNode = ((Text)resultObject).getParentNode();
                        if (parentNode instanceof Element)
                        {
                            parentNode.normalize();
                            targetAttributeName = IAttributeValueResolver.TEXT_NODE_KEY;
                            oldAttributeValue = ((Text)resultObject).getNodeValue();
                        }
                    }
                }
                catch (XPathExpressionException xee)
                {
                    // could not evaluate - leave targetElement and
                    // targetAttributeName unchanged
                }
            }

            
            if (!IAttributeValueResolver.TEXT_NODE_KEY.equals(targetAttributeName))
            {
                oldAttributeValue = targetElement.getAttribute(targetAttributeName);
            }

            if (oldAttributeValue != null && oldAttributeValue.length() > 0)
            {
                String newAttributeValue;
                if (oldAttributeValue.contains(MD_PLUGIN_LOCATION))
                {
                    newAttributeValue = resolveMDPluginLocation(
                            oldAttributeValue, dtInfo);
                }
                else
                {
                    newAttributeValue = resolveAttributeValue(srcElement,
                            targetElement, targetAttributeName,
                            oldAttributeValue, tagDecorateInfoID);
                }
                if (newAttributeValue != null
                        && !oldAttributeValue.equals(newAttributeValue))
                {
                    if (IAttributeValueResolver.TEXT_NODE_KEY
                            .equals(targetAttributeName))
                    {
                        for (int i = targetElement.getChildNodes().getLength()-1; i >= 0; i--)
                        {
                            Node childNode = targetElement.getChildNodes().item(i);
                            if (childNode.getNodeType() == Node.TEXT_NODE)
                            {
                                targetElement.removeChild(childNode);
                            }
                        }
                        targetElement.appendChild(targetElement.getOwnerDocument().createTextNode(newAttributeValue));
                    }
                    else
                    {
                        targetElement.setAttribute(targetAttributeName,
                                newAttributeValue);
                    }
                }
            }
        }
    }

    private String resolveAttributeValue(final Element originalElement,
            final Element convertedElement,
            final String convertedAttributeName, final String oldAttributeValue,
            final String tagDecorateInfoID)
    {
        String newValue = null;
        boolean valueResolved = false;
        final String[] result = new String[1];

        for (final IAttributeValueResolver resolver : AttributeValueResolverRegistryReader
                .getInstance().getExtensions())
        {
            SafeRunner.run(new ISafeRunnable()
            {
                public void handleException(Throwable exception)
                {
                    PDPlugin
                            .log(
                                    "While resolving attribute in converter decorator", exception); //$NON-NLS-1$
                }

                public void run() throws Exception
                {
                    if (resolver.canResolve(originalElement,
                            convertedElement, convertedAttributeName, oldAttributeValue))
                    {
                        result[0] = resolver.resolveAttribute(
                                originalElement, convertedElement,
                                convertedAttributeName, oldAttributeValue);
                    }
                }
            });
            if (result[0] != null)
            {
                newValue = result[0];
                valueResolved = true;
                break;
            }
        }

        if (!valueResolved &&
                // maintain backward compatibility: only do this default
                // behaviour for the preview
                tagDecorateInfoID.equals(DECORATE_INFO_ID_PREVIEW))
        {
            // fall- through to default case.
            try
            {
                
                PageExpressionContext current = PageExpressionContext.getCurrent();
                if (current != null)
                {
                	//Bug 319317 - Third-party plug-in providing javax.servlet.jsp.el version 2.1 or greater breaks WPE preview
                	Map options = new HashMap();
                	options.put("ELEMENT", originalElement); //$NON-NLS-1$
                    return (String) current
                            .evaluateExpression(oldAttributeValue, String.class, options);
                }
            } catch (ELException e)
            {
                // ignore. we will just return null since couldn't resolve
            }
        }
        return newValue;
    }

    /**
     * Resolves any instance of MD_PLUGIN_LOCATION in input String.
     * 
     * @param input
     *            Input String.
     * @param dtInfo
     *            IDTInfo instance.
     * @return Input String with any instance of MD_PLUGIN_LOCATION resolved.
     */
    protected String resolveMDPluginLocation(String input, IDTInfo dtInfo)
    {
        String output = input;
        if (input != null && input.contains(MD_PLUGIN_LOCATION))
        {
            int tokenStart = input.indexOf(MD_PLUGIN_LOCATION);
            int tokenEnd = tokenStart + MD_PLUGIN_LOCATION.length();
            String prefix = input.substring(0, tokenStart);
            String suffix = input.substring(tokenEnd);
            Trait trait = dtInfo.getTrait();
            IMetaDataSourceModelProvider mdSourceModelProvider = trait
                    .getSourceModelProvider();
            IResourceURLProvider resourceURLProvider = (IResourceURLProvider) mdSourceModelProvider
                    .getAdapter(IResourceURLProvider.class);
            URL url = resourceURLProvider.getResourceURL("/META-INF/"); //$NON-NLS-1$
            String resolvedToken = url.toExternalForm();
            resolvedToken = resolvedToken.substring(0,
                    resolvedToken.length() - 10);
            output = prefix + resolvedToken + suffix;
        }
        return output;
    }

    /**
     * Sets DTTagConverter instance as non-visual as HTML and sets the
     * ImageDescriptor instance that DTTagConverter will use to return an Image
     * for rendering.
     * 
     * @param dtTagConverter
     *            DTTagConverter instance.
     * @param dtInfo
     *            IDTInfo instance.
     * @param imagePath
     *            Image path, relative to declaring plug-in.
     */
    protected void setNonVisual(DTTagConverter dtTagConverter, IDTInfo dtInfo,
            String imagePath)
    {
        dtTagConverter.setVisualByHTML(false);
        if (imagePath != null && imagePath.length() > 0)
        {
            Trait trait = dtInfo.getTrait();
            IImageDescriptorProvider imgDescProvider = (IImageDescriptorProvider) trait
                    .getSourceModelProvider().getAdapter(
                            IImageDescriptorProvider.class);
            if (imgDescProvider != null)
            {
                ImageDescriptor imageDescriptor = imgDescProvider
                        .getImageDescriptor(imagePath);
                dtTagConverter.setVisualImageDescriptor(imageDescriptor);
            }
        }
    }

}
