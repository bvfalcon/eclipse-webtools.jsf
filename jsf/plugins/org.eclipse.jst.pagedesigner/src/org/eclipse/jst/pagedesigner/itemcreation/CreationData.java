/*******************************************************************************
 * Copyright (c) 2001, 2011 Oracle Corporation and others.
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
package org.eclipse.jst.pagedesigner.itemcreation;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainMetaDataQuery;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.editors.palette.ITagDropSourceData;
import org.eclipse.jst.pagedesigner.utils.JSFUtil;
import org.eclipse.wst.xml.core.internal.provisional.contentmodel.CMDocType;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;

/**
 * Value object that wraps creation data
 *  
 * <p><b>Provisional API - subject to change</b></p>
 *  
 * @author cbateman
 *
 */
public final class CreationData
{
    private final ITagDropSourceData  _creationProvider;
    private final String                _prefix; 
    private final IDOMPosition          _domPosition;
    private final IDOMModel             _model;
    private final IAdaptable            _customizationData;

    private TagIdentifier               _tagId; // = null; lazy init on creation 
    
    /**
     * The tag {@link Entity} being created
     */
    private Entity _tagEntity; // = null; lazy load because derived from
                               // potentially expensive meta-data search
    
    /**
     * The {@link IMetaDataModelContext} for the tag creation
     */
    private final IMetaDataModelContext _modelContext;
    private final ITaglibDomainMetaDataModelContext _taglibMetaDataContext;

    // mutable because it may be changed from the original _domPosition
    private IDOMPosition _adjustedPosition;

    private ITaglibDomainMetaDataQuery _query;
	
    /**
     * @param creationProvider 
     * @param model 
     * @param domPosition 
     * @param modelContext 
     * @param customizationData 
     */
    public CreationData(final ITagDropSourceData creationProvider,
            final IDOMModel model, final IDOMPosition domPosition,
            final IMetaDataModelContext modelContext,
            final IAdaptable customizationData)
    {
        super();
        this._creationProvider = creationProvider;
        this._modelContext = modelContext;
        this._taglibMetaDataContext = null;
        this._domPosition = domPosition;
        this._adjustedPosition = _domPosition;
        this._model = model;
        this._customizationData = customizationData; 
        this._prefix = getPrefix(getUri(), model, getDefaultPrefix());
        
    }

    /**
     * @param creationProvider 
     * @param model 
     * @param domPosition 
     * @param taglibMetaDataContext 
     * @param customizationData 
     * @deprecated - use the other constructor
     */
    public CreationData(final ITagDropSourceData creationProvider,
            final IDOMModel model, final IDOMPosition domPosition,
            final ITaglibDomainMetaDataModelContext taglibMetaDataContext,
            final IAdaptable customizationData)
    {
        super();
        this._creationProvider = creationProvider;
        this._taglibMetaDataContext = taglibMetaDataContext;
        this._modelContext = null;
        this._domPosition = domPosition;
        this._adjustedPosition = _domPosition;
        this._model = model;
        this._customizationData = customizationData; 
        this._prefix = getPrefix(getUri(), model, getDefaultPrefix());
    }
    /**
     * Returns the ns prefix for the tag and also creates taglib reference if necessary
     * @param uri
     * @param model
     * @param suggested
     * @return prefix to use
     */
    protected static String getPrefix(String uri, IDOMModel model,
            String suggested) {
        // TODO: this shouldn't really add to the document
        if (uri==null || ITLDConstants.URI_HTML.equalsIgnoreCase(uri)
                || ITLDConstants.URI_JSP.equalsIgnoreCase(uri)
                || CMDocType.JSP11_DOC_TYPE.equalsIgnoreCase(uri)) {
            return null;
        }

        // now handles custom tag lib
        return JSFUtil.getOrCreatePrefix(model, uri, suggested);
    }
    
    /**
     * @return the tag identifier uri
     */
    public String getUri() {
       	ITagDropOverrider overrider = getTagDropOverrider();
    	if (overrider != null && overrider.getUriOverride() != null)
    		 return overrider.getUriOverride();
    	
        return _creationProvider.getNamespace();
    }

    /**
     * @return the default prefix
     */
    public String getDefaultPrefix() {
    	ITagDropOverrider overrider = getTagDropOverrider();
    	if (overrider != null && overrider.getDefaultPrefixOverride() != null)
    		 return overrider.getDefaultPrefixOverride();
    	    	
        return _creationProvider.getDefaultPrefix();
    }

    private ITagDropOverrider getTagDropOverrider() {
    	if (getDropCustomizationData() != null
    			&& getDropCustomizationData()
    				.getAdapter(ITagDropOverrider.class) != null) {
    		
    		 return (ITagDropOverrider)getDropCustomizationData()
    		 		.getAdapter(ITagDropOverrider.class);
    	}
    	return null;
    }
    /**
     * @return the local prefix for the  tag
     */
    public String getPrefix() {
        return _prefix;
    }

    /**
     * @return the tag name
     */
    public String getTagName() {
    	ITagDropOverrider overrider = getTagDropOverrider();
    	if (overrider != null && overrider.getTagNameOverride() != null)
    		 return overrider.getTagNameOverride();
    	
        return _creationProvider.getTagName();
    }

    /**
     * @return the creation provider
     */
    public ITagDropSourceData getTagCreationProvider()
    {
        return _creationProvider;
    }

    private  ITaglibDomainMetaDataQuery getQuery() {
    	if (_query == null)
    		_query = MetaDataQueryFactory.getInstance().createQuery(_modelContext);
    	
    	return _query;
    }
    /**
     * @return the {@link Entity} for this tag element being created
     */
    public Entity getTagEntity() {
        if (_tagEntity == null){
        	if (_modelContext != null)
        		_tagEntity = getQuery().getQueryHelper().getEntity(_modelContext.getModelIdentifier(), getTagName());
        	else
        		_tagEntity = TaglibDomainMetaDataQueryHelper.getEntity(_taglibMetaDataContext, getTagName());
            
        }
        return _tagEntity;
    }
    
    /**
     * @return flag indicating that html form container ancestor is required
     */
    public boolean isHTMLFormRequired() {
    	Trait t = null;
    	if (_modelContext != null)
    		t = getQuery().getQueryHelper().getTrait(getTagEntity(), "requires-html-form"); //$NON-NLS-1$
    	else 
    		t = TaglibDomainMetaDataQueryHelper.getTrait(getTagEntity(), "requires-html-form"); //$NON-NLS-1$
    	
        if (t != null)
            return TraitValueHelper.getValueAsBoolean(t);
        
        return false;
    }
    
    /**
     * @return flag indicating that jsf component
     */
    public boolean isJSFComponent() {      
    	Model model = null;
    	Trait t = null;
    	if (_modelContext != null) {
    		model = getQuery().findTagLibraryModel(_modelContext.getModelIdentifier());
        	t = getQuery().findTrait(model, "is-jsf-component-library"); //$NON-NLS-1$ 
    	} else {
            model = TaglibDomainMetaDataQueryHelper.getModel(_taglibMetaDataContext);
            t = TaglibDomainMetaDataQueryHelper.getTrait(model, "is-jsf-component-library"); //$NON-NLS-1$
    	}
        if (t != null)
            return TraitValueHelper.getValueAsBoolean(t);
        
        return false;
    }

    /**
     * @return flag indicating that jsf view container ancestor is required
     */
    public boolean isJSFViewTagRequired() {
        return JSFUtil.isJSFModel(_model);
    }

    /**
     * @return the metadata context for the tag
     */
    public IMetaDataModelContext getMetaDataContext() {
        return _modelContext;
    }
    
    /**
     * @return the (deprecated) metadata context for the tag
     * @deprecated - use {@link IMetaDataModelContext}
     */
    public ITaglibDomainMetaDataModelContext getTaglibMetaDataContext() {
        return _taglibMetaDataContext;
    }

    /**
     * @return the original dom position of the tag creation
     */
    public IDOMPosition getDomPosition() {
        return _domPosition;
    }

    /**
     * @return the dom model
     */
    public IDOMModel getModel() 
    {
        return _model;
    }

    /**
     * @return the adjusted position (calculated to account for containers etc.)
     */
    public IDOMPosition getAdjustedPosition() {
        return _adjustedPosition;
    }

    /**
     * @param adjustedPosition
     */
    /*package*/ void setAdjustedPosition(IDOMPosition adjustedPosition) {
        _adjustedPosition = adjustedPosition;
    }

    /**
     * @return the TagIdentifer for the tag to be created
     */
    public TagIdentifier getTagId() 
    {
        if (_tagId == null)
        {
            _tagId = TagIdentifierFactory.createJSPTagWrapper(getUri(), getTagName());
        }
        return _tagId;
    }

    /**
     * @return the customization data passed in from the tool.  May be null.
     */
    public IAdaptable getDropCustomizationData() {
        return _customizationData;
    }
}