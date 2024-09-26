/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
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
package instrument;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Iterator;

import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.component.UIOutput;

import org.eclipse.jst.jsf.common.runtime.internal.debug.JSFMonitorMessage;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentFactory;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.FacetInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.UIInputInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.UIOutputInfo;

public class MyMonitorMessageFactory 
{
    public static JSFMonitorMessage  createJSFMonitorMessage(InputStream inStream) throws IOException, ClassCastException, ClassNotFoundException
    {
        ObjectInputStream objectStream = new ObjectInputStream(inStream);
        Object nextObject = objectStream.readObject();
        return (JSFMonitorMessage) nextObject;
    }
   
    static ComponentInfo buildComponentTree(UIComponent component, boolean isFacet)
    {
        final ComponentInfo componentInfo = getComponentData(component, isFacet);
        
        for (Iterator it = component.getChildren().iterator(); it.hasNext();)
        {
            UIComponent child = (UIComponent) it.next();
            componentInfo.getChildren().add(buildComponentTree(child, false));
        }

        for (Iterator it = component.getFacets().values().iterator(); it.hasNext();)
        {
            UIComponent facet = (UIComponent) it.next();
            componentInfo.getChildren().add(buildComponentTree(facet, true));
        }
        
        return componentInfo;
    }
    
    private static ComponentInfo getComponentData(final UIComponent component, boolean isFacet)
    {
        if (isFacet)
        {
            return calculateFacetInfo(component);
        }
        else if (component instanceof UIInput)
        {
            return calculateUIInput((UIInput)component);
        }
        else if (component instanceof UIOutput)
        {
            return calculateUIOutput((UIOutput)component);
        }
        
        // default; just make a component
        return calculateComponentInfo(component);
    }

    private static ComponentInfo calculateComponentInfo(UIComponent component)
    {
        final String id = component.getId();
        final String parentId = component.getParent() == null ? null : component.getParent().getId();
        final String componentFamily = component.getFamily();
        final String renderFamily = component.getRendererType();
        final String componentType = null;
        final String componentClass = component.getClass().getCanonicalName();
        
        return ComponentFactory.createComponentInfo
            (id, parentId, componentFamily, renderFamily, componentType, componentClass);
    }
    
    private static FacetInfo calculateFacetInfo(UIComponent component)
    {
        final String id = component.getId();
        final String parentId = component.getParent() == null ? null : component.getParent().getId();
        final String componentFamily = component.getFamily();
        final String renderFamily = component.getRendererType();
        final String componentType = null;
        final String componentClass = component.getClass().getCanonicalName();

        return ComponentFactory.createFacetInfo
            (id, parentId, componentFamily, renderFamily, componentType, componentClass);
    }
    
    private static UIInputInfo calculateUIInput(UIInput  uiInput)
    {
        final String id = uiInput.getId();
        final String parentId = uiInput.getParent() == null ? null : uiInput.getParent().getId();
        final String componentFamily = uiInput.getFamily();
        final String renderFamily = uiInput.getRendererType();
        final String componentType = null;
        final String componentClass = uiInput.getClass().getCanonicalName();
        final String isValid = Boolean.toString(uiInput.isValid());
        final String isImmediate = Boolean.toString(uiInput.isImmediate());
        final String isRequired = Boolean.toString(uiInput.isRequired());
        final String isRendered = Boolean.toString(uiInput.isRendered());

        return ComponentFactory.createUIInputInfo
            (id, parentId, componentFamily, renderFamily, isValid, isImmediate, isRequired, isRendered, componentType, componentClass);
    }

    private static UIOutputInfo calculateUIOutput(UIOutput uiOutput)
    {
        final String id = uiOutput.getId();
        final String parentId = uiOutput.getParent() == null ? null : uiOutput.getParent().getId();
        final String componentFamily = uiOutput.getFamily();
        final String renderFamily = uiOutput.getRendererType();
        final String componentType = null;
        final String componentClass = uiOutput.getClass().getCanonicalName();
        final String isRendered = Boolean.toString(uiOutput.isRendered());

        return ComponentFactory.createUIOutputInfo
            (id, parentId, componentFamily, renderFamily, isRendered, componentType, componentClass);
    }
}
