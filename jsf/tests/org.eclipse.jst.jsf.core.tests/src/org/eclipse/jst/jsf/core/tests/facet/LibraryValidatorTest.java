/*******************************************************************************
 * Copyright (c) 2001, 2012 Oracle Corporation and others.
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


package org.eclipse.jst.jsf.core.tests.facet;

import junit.framework.TestCase;

import java.util.function.Predicate;

import org.eclipse.core.runtime.IStatus;


/**
 * @author Debajit Adhikary
 *
 */
public abstract class LibraryValidatorTest extends TestCase
{
    private Predicate<String> classNameIdentifyingJarToUsePredicate;
    /*
    private String jarPath;
    private String jarPathWithoutImplementationVersionEntry;
    private String jarPathWithNonstandardImplementationVersionEntry;
    private String expectedLibraryVersion;
    */
    private UserLibraryVersionValidatorProxy validator;


    /**
     * @param name
     */
    public LibraryValidatorTest (final String name)
    {
        super(name);
    }


    public LibraryValidatorTest (final String name,
                                 final Predicate<String> classNameIdentifyingJarToUsePredicate
                                 /*
                                 final String jarPath,
                                 final String jarPathWithoutImplementationVersionEntry,
                                 final String jarPathWithNonstandardImplementationVersionEntry,
                                 final String expectedLibraryVersion*/
                                 )
    {
        super(name);

        this.classNameIdentifyingJarToUsePredicate = classNameIdentifyingJarToUsePredicate;
        /*
        this.jarPath = jarPath;
        this.jarPathWithoutImplementationVersionEntry = jarPathWithoutImplementationVersionEntry;
        this.jarPathWithNonstandardImplementationVersionEntry = jarPathWithNonstandardImplementationVersionEntry;
        this.expectedLibraryVersion = expectedLibraryVersion;
        */

        this.validator = new UserLibraryVersionValidatorProxy(this.classNameIdentifyingJarToUsePredicate);
    }


    public void testVersionStringSuffixMatch ()
    {
        assertNotNull(validator);

        final IStatus status = validator.validateVersionStrings("1.2", "1.1.2");
        assertEquals(IStatus.WARNING, status.getSeverity());
    }


    public void testVersionStringPrefixMatch ()
    {
        assertNotNull(validator);

        final IStatus status = validator.validateVersionStrings("1.2", "1.2.11");
        assertEquals(IStatus.OK, status.getSeverity());
    }


    public void testNullLibraryVersionString ()
    {
        assertNotNull(validator);

        final IStatus status = validator.validateVersionStrings("1.2", null);
        assertEquals(IStatus.WARNING, status.getSeverity());
    }


    public void testNullFacetVersionString ()
    {
        assertNotNull(validator);

        try
        {
            validator.validateVersionStrings(null, "1.0.1.2.11"); // Fails
        }
        catch (final IllegalArgumentException e)
        {
            assertEquals("Cannot read facet version", e.getLocalizedMessage());
            return;
        }

        fail();
    }

    /*
    protected File getFileFromPlugin (final String relativePathToFile,
                                      final Plugin plugin)
    throws IOException, URISyntaxException
    {
        final Bundle bundle = TestsPlugin.getDefault().getBundle();

        final URL bundleUrl = bundle.getEntry(relativePathToFile);
        assertNotNull(bundleUrl);

        final URL fileUrl = FileLocator.toFileURL(bundleUrl);
        final File file = new File(fileUrl.getPath());
        assertTrue(file.exists());
        return file;
    }
    */

    /*
    public void testReadLibraryVersionFromJarWithManifestEntry ()
    throws IOException, URISyntaxException
    {
        final JarFile jarFile = new JarFile(getFileFromPlugin(jarPath, TestsPlugin.getDefault()));
        assertEquals(expectedLibraryVersion, validator.getLibraryVersion(jarFile)); 
    }
	*/

    /*
    public void testReadLibraryVersionFromJarWithoutManifestEntry ()
    throws IOException, URISyntaxException
    {
        final JarFile jarFile = new JarFile(getFileFromPlugin(jarPathWithoutImplementationVersionEntry, TestsPlugin.getDefault()));
        assertNull("Was expecting library-version string to be null", validator.getLibraryVersion(jarFile)); //$NON-NLS-1$
    }
    */

    /**
     * Regression test-case. This would fail earlier without the patch in
     *
     * "JSF Facet version validator fails to validate some non-standard jars"
     * https://bugs.eclipse.org/bugs/show_bug.cgi?id=286351 
     *
     * @throws IOException
     * @throws URISyntaxException
     *
     */
    /*
    public void testReadLibraryVersionFromJarWithNonstandardImplementationVersion()
    throws IOException, URISyntaxException
    {
        final JarFile jarFile = new JarFile(getFileFromPlugin(jarPathWithNonstandardImplementationVersionEntry, TestsPlugin.getDefault()));
        assertNotNull("Was expecting library-version string to be non-null", validator.getLibraryVersion(jarFile)); //$NON-NLS-1$
        assertEquals(expectedLibraryVersion, validator.getLibraryVersion(jarFile));
    }
    */
}
