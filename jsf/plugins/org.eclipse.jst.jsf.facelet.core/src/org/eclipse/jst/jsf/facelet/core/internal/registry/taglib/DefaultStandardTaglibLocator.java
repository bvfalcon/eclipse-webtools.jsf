/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.facelet.core.internal.FaceletCorePlugin;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.IFaceletTagRecord.TagRecordDescriptor;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib;

/**
 * @author cbateman
 * 
 */
public class DefaultStandardTaglibLocator extends AbstractFaceletTaglibLocator
{
    private static final String TAGLIBS_ROOT = "/std-taglibs"; //$NON-NLS-1$
    private Set<MyTagRecordDescriptor> readStdTaglibs(JSFVersion jsfVersion)
    {
        if (jsfVersion == null || jsfVersion == JSFVersion.UNKNOWN)
        {
            jsfVersion = JSFVersion.values()[JSFVersion.values().length - 1];
        }
        Enumeration<String> taglibLocations = FaceletCorePlugin.getDefault().getBundle().getEntryPaths(TAGLIBS_ROOT + "/jsf-" + jsfVersion); //$NON-NLS-1$
        Set<MyTagRecordDescriptor> taglibs = new HashSet<MyTagRecordDescriptor>();
        while (taglibLocations.hasMoreElements())
        {
            String location = taglibLocations.nextElement();
            try
            {
                final URL url = FaceletCorePlugin.getDefault().getBundle().getEntry(location);
                final URL fileURL = FileLocator.toFileURL(url);
                File file = new File(fileURL.getPath());
                final InputStream openStream = fileURL.openStream();
                final TagModelLoader loader = new TagModelLoader(
                        file.getAbsolutePath());
                loader.loadFromInputStream(openStream);
                final FaceletTaglib taglib = loader.getTaglib();
                MyTagRecordDescriptor desc = new MyTagRecordDescriptor(new Path(fileURL.toString()), taglib);
                taglibs.add(desc);
            } catch (final Exception e)
            {
                FaceletCorePlugin.log(
                        "Trying to load default taglib for: " + location, e); //$NON-NLS-1$
            }
        }
        return Collections.unmodifiableSet(taglibs);
    }
    private HashMap<String, IFaceletTagRecord> _defaultRecords;

    /**
     * 
     */
    public DefaultStandardTaglibLocator()
    {
        super("", ""); //$NON-NLS-1$//$NON-NLS-2$
    }

    @Override
    public void start(IProject project)
    {
        _defaultRecords = new HashMap<String, IFaceletTagRecord>();
        final TagRecordFactory factory = new TagRecordFactory(project, false);
        JSFVersion jsfVersion = JSFVersion.valueOfProject(project);
        Set<MyTagRecordDescriptor> defaultTaglibs = readStdTaglibs(jsfVersion);
        for (final MyTagRecordDescriptor desc : defaultTaglibs)
        {
            final IFaceletTagRecord record = factory.createRecords(desc.getTaglib(),
                    desc);
            if (record != null)
            {
                _defaultRecords.put(record.getURI(), record);
            }
        }
        super.start(project);
    }

    @Override
    protected Map<String, ? extends IFaceletTagRecord> doLocate(IProject context)
    {
        return Collections.unmodifiableMap(_defaultRecords);
    }

    private static class MyTagRecordDescriptor extends TagRecordDescriptor
    {
        private final IPath _path;
        private final FaceletTaglib  _taglib;
        
        public MyTagRecordDescriptor(final IPath path, final FaceletTaglib taglib)
        {
            super(Source.JAR);
            _path = path;
            _taglib = taglib;
        }

        @Override
        public IResource getResource()
        {
            return null;
        }

        @Override
        public IPath getPath()
        {
            return _path;
        }

        public FaceletTaglib getTaglib()
        {
            return _taglib;
        }
    }
}
