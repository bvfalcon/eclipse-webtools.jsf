/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.metadata.tests.taglibprocessing;

import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.MetaDataModelContextImpl;
import org.eclipse.jst.jsf.common.metadata.query.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.MetaDataQueryHelper;
import org.eclipse.jst.jsf.common.metadata.tests.AbstractBaseMetaDataTestCase;
import org.eclipse.jst.jsf.metadata.tests.MetadataTestsPlugin;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfo;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfos;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.TagCreationInfo;

public class JSFHTMLTestCase extends AbstractBaseMetaDataTestCase{
	private String _uri;
	private IMetaDataModelContext _context;
	
	public void setUp() throws Exception{
		super.setUp();
	    
	    projectTestEnvironment.loadResourceInWebRoot(MetadataTestsPlugin.getDefault().getBundle(),
	            "/testfiles/html_basic.tld",
	            "/WEB-INF/lib/html_basic.tld");
	    
		_uri = "http://java.sun.com/jsf/html";
		_context = new MetaDataModelContextImpl( project, MetaDataQueryHelper.TAGLIB_DOMAIN, _uri );
	}
	
	public void testPaletteInfos(){
		Model model = MetaDataQueryHelper.getModel(_context);
		Trait trait = MetaDataQueryHelper.getTrait(model , PaletteInfos.TRAIT_ID);
		assertNotNull(trait);
		assertTrue(trait.getValue() instanceof PaletteInfos);
		PaletteInfos pis = (PaletteInfos)trait.getValue();
		PaletteInfo cmdBtn = pis.findPaletteInfoById("commandButton");
		assertNotNull(cmdBtn);
		assertEquals("Command Button", cmdBtn.getDisplayLabel());
		
		PaletteInfo info = pis.findPaletteInfoById("inputText");
		assertNotNull(info);
		//required jsf html tld to work!
		assertNotNull(info.getDisplayLabel());
		assertNotNull(info.getDescription());
		assertNull(info.getExpert());
		assertNull(info.getHidden());	
		assertFalse(info.isExpert());
		assertFalse(info.isHidden());	
		assertNotNull(info.getSmallIcon());
		assertNotNull(info.getLargeIcon());
	}
	
	public void testCreateInfos(){
		Trait trait = MetaDataQueryHelper.getTrait(_context , "dataTable", TagCreationInfo.TRAIT_ID);
		assertNotNull(trait);
		assertTrue(trait.getValue() instanceof TagCreationInfo);
		TagCreationInfo tci = (TagCreationInfo)trait.getValue();
		
		Object template = tci.getTemplate();
		assertNotNull(template);
		assertTrue(template instanceof String);

		trait = MetaDataQueryHelper.getTrait(_context , "commandLink", TagCreationInfo.TRAIT_ID);
		assertNotNull(trait);
		assertTrue(trait.getValue() instanceof TagCreationInfo);
		tci = (TagCreationInfo)trait.getValue();
		assertNotNull(tci.getAttributes());
		assertNotNull(tci.getTemplate());
		assertEquals(0, tci.getAttributes().size());

	}
}
