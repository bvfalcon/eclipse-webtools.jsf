/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests.read;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.WizardUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test the render which is one of 
 * many items inside the root elemnt faces-config in the configuration
 * information hierarchy of the faces-config.xml file 
 *
 */
public class ReadRenderKitTestCase extends TestCase {
	IProject project = null;

	public ReadRenderKitTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}

	/*
	 *Test to see if there is at least one render-kit.
	 *This should be specified in the file for reading (faces-config)
	 */
	public void testSingleRenderKit() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			assertNotNull(edit.getFacesConfig());
            
            assertNotNull(getRenderKit1(edit.getFacesConfig()));
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

    private RenderKitType getRenderKit1(FacesConfigType facesConfig)
    {
        return (RenderKitType) FacesConfigModelUtil
            .findEObjectElementById(facesConfig.getRenderKit(), "renderKit1");
    }
    
	// Test for the Descirption
	public void testDescriptionGroup() 
    {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			assertNotNull(edit.getFacesConfig());
            
            RenderKitType renderKit = getRenderKit1(edit.getFacesConfig());
            assertNotNull(renderKit);
            
            DescriptionType descType =
                (DescriptionType) FacesConfigModelUtil
                    .findEObjectElementById(renderKit.getDescription()
                                        , "renderKit1Description");
            assertNotNull(descType);
            assertEquals("Render kit Desc", descType.getTextContent());
            
            DisplayNameType displayName =
                (DisplayNameType) FacesConfigModelUtil
                .findEObjectElementById(renderKit.getDisplayName()
                                        , "renderKit1DisplayName");
            assertNotNull(displayName);
            assertEquals("Render kit disp name"
                         , displayName.getTextContent());
            
            IconType iconType =
                (IconType) FacesConfigModelUtil
                    .findEObjectElementById(renderKit.getIcon()
                                        , "renderKit1Icon");
            assertNotNull(iconType);
            assertEquals("small-renderkit-icon"
                         , iconType.getSmallIcon().getTextContent());
            assertEquals("large-renderkit-icon"
                         , iconType.getLargeIcon().getTextContent());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

    /*
	 * chech for hte render-kit-id  element
	 */

	public void testSingleValuedProperties() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			assertNotNull(edit.getFacesConfig());
            
            RenderKitType renderKit = getRenderKit1(edit.getFacesConfig());
            assertNotNull(renderKit);

            assertEquals("render-kit-Id"
                         ,renderKit.getRenderKitId().getTextContent());
            assertEquals("render-kit-class"
                        ,renderKit.getRenderKitClass().getTextContent());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

    /**
	 * Checks to see if there is at least one renderer
	 * Note: testing of the renderer is done in ReadRendererTestCase
	 */
	public void testRenderer() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			assertNotNull(edit.getFacesConfig());
            RenderKitType renderKit = getRenderKit1(edit.getFacesConfig());
            assertNotNull(renderKit);

            assertEquals(1, renderKit.getRenderer().size());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}

	}

}