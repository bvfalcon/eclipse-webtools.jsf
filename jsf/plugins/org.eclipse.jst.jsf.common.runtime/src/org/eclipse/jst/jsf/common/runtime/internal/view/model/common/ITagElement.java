/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.common.runtime.internal.view.model.common;

import java.io.Serializable;
import java.util.Map;

/**
 * Super-interface of all JSF tag elements.
 * 
 * @author cbateman
 *
 */
public interface ITagElement extends Serializable
{

    /**
     * @return the name of the tag
     */
    public abstract String getName();

    /**
     * @return the namespace uri for this tag
     */
    public abstract String getUri();
    
    /**
     * @return the fully qualified class name in dot separated format
     * (i.e. javax.faces.webapp.ConverterTag or jakarta.faces.webapp.ConverterTag)
     */
    public abstract String getTagHandlerClassName();
    
    /**
     * @return an unmodifiable map, indexed by name, of all the attribute
     * handlers available for this tag element.  Each value in the Map
     * must be a ITagAttributeHandler.
     */
    public abstract Map    getAttributeHandlers();

    /**
     * @return the attributes for the tag.
     */
    public abstract Map<String, ? extends ITagAttribute> getAttributes();
    /**
     * Signals that the tag element should  make any mutable data immutable
     * and throw exceptions if attempts are made to implement.  Flag must
     * latch and become irrevocable.
     */
//    public abstract void setLocked();
    
    /**
     * @return true if setLocked has been called.
     */
//    public abstract boolean isLocked();
}