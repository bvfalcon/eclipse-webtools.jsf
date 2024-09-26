/*******************************************************************************
 * Copyright (c) 2010, 2016 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.test.util.mock;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;
import org.eclipse.core.runtime.content.IContentTypeMatcher;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;

public class MockContentTypeManager implements IContentTypeManager
{
    private final Map<String, IContentType> _idToContentType;
    private IScopeContext _context = new InstanceScope();

    public static Map<String, IContentType> createDefaultContentTypeMappings()
    {
        Map<String, IContentType> map = new HashMap<String, IContentType>();
        map.put("org.eclipse.wst.html.core.htmlsource", MockContentType
                .createContentType("org.eclipse.wst.html.core.htmlsource",
                        new String[]
                        { "xhtml" , "html", "htm"}, new String[0]));
        map.put("org.eclipse.jst.jsf.core.jsfsource", MockContentType
                .createContentType("org.eclipse.jst.jsf.core.jsfsource",
                        new String[]
                        { "xhtml" }, new String[0]));

        return map;
    }

    /**
     * Construct with a default set of extension to type mappings
     */
    public MockContentTypeManager()
    {
        this(Collections.unmodifiableMap(createDefaultContentTypeMappings()));
    }
    
    public MockContentTypeManager(
            final Map<String, IContentType> idToContentType)
    {
        _idToContentType = idToContentType;
    }

    public IContentType findContentTypeFor(final InputStream contents,
            final String fileName) throws IOException
    {
        throw new UnsupportedOperationException();
    }

    public IContentType findContentTypeFor(final String fileName)
    {

        throw new UnsupportedOperationException();
    }

    public IContentType[] findContentTypesFor(final InputStream contents,
            final String fileName) throws IOException
    {

        throw new UnsupportedOperationException();
    }

    public IContentType[] findContentTypesFor(final String fileName)
    {

        throw new UnsupportedOperationException();
    }

    public IContentDescription getDescriptionFor(final InputStream contents,
            final String fileName, final QualifiedName[] options)
            throws IOException
    {

        throw new UnsupportedOperationException();
    }

    public IContentDescription getDescriptionFor(final Reader contents,
            final String fileName, final QualifiedName[] options)
            throws IOException
    {

        throw new UnsupportedOperationException();
    }

    public void addContentTypeChangeListener(
            final IContentTypeChangeListener listener)
    {
        throw new UnsupportedOperationException();
    }

    public IContentType[] getAllContentTypes()
    {

        throw new UnsupportedOperationException();
    }

    public IScopeContext getContext()
    {
        return _context;
    }

    public IContentType getContentType(final String contentTypeIdentifier)
    {
        return _idToContentType.get(contentTypeIdentifier);
    }

    public IContentTypeMatcher getMatcher(final ISelectionPolicy customPolicy,
            final IScopeContext context)
    {

        throw new UnsupportedOperationException();
    }

    public void removeContentTypeChangeListener(
            final IContentTypeChangeListener listener)
    {
        throw new UnsupportedOperationException();
    }

	public IContentType addContentType(String contentTypeIdentifier, String name, IContentType baseType)
			throws CoreException
	{
		throw new UnsupportedOperationException();
	}

	public void removeContentType(String contentTypeIdentifier) throws CoreException
	{
		throw new UnsupportedOperationException();
	}

}
