/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.designtime.internal.view.model;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.strategy.IIdentifiableStrategy;

/**
 * Creates a tag registry for the given project.  The factory may use 
 * whatever mechanism it chooses and the tag registry may or may not be a
 * single instance for project.
 * 
 * @author cbateman
 *
 */
public abstract class TagRegistryFactory implements IIdentifiableStrategy<IProject, ITagRegistry, String>
{
    private static final ITagRegistry NO_RESULT = null;

    private static final String ID = "org.eclipse.jst.jsf.designtime.view.model.TagRegistryFactory";

    /**
     * @param project
     * @return a tag registry for project.  This instance may be a common
     * shared per-project singleton.  The factory must ensure that the registry
     * is cleaned up if the associated project becomes inaccessible (see IProject.isAccessible).
     * Return null if none.
     * 
     * @throws TagRegistryFactoryException 
     */
    public abstract ITagRegistry createTagRegistry(final IProject project) throws TagRegistryFactoryException;

    /**
     * Allow 
     * 
     * @param project
     * @return true if an instance of ITagRegistry already exists for project
     */
    public abstract boolean isInstance(final IProject project);

    public final ITagRegistry getNoResult()
    {
        return NO_RESULT;
    }

    public final ITagRegistry perform(IProject project) throws Exception
    {
        return createTagRegistry(project);
    }

    public String getId()
    {
        return ID;
    }

    /**
     * Wraps exceptions generated by trying to create a tag registry
     */
    public static class TagRegistryFactoryException extends Exception
    {
        /**
         * serializable id
         */
        private static final long serialVersionUID = 1361229535611361339L;

        /**
         * @param cause
         */
        public TagRegistryFactoryException(Throwable cause)
        {
            super("Problem during tag registry construction", cause);
        }
        
    }
}
