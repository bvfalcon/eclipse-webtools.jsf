/*******************************************************************************
 * Copyright (c) 2006, 2013 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.metadataprocessors;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IImageDescriptorProvider;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.internal.IMetaDataQuery;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.IStructuredDocumentContextResolverFactory2;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.internal.IMetaDataEnabledFeature2;

/**
 * Simple abstract class that implementers of {@link IMetaDataEnabledFeature} can subclass in the <b>TagLibDomain</b> of metadata
 * <p><b>Provisional API - subject to change</b></p>*
 */
public abstract class AbstractMetaDataEnabledFeature implements IMetaDataEnabledFeature, IMetaDataEnabledFeature2{
	
	private MetaDataContext mdContext;
	private IModelContext sdContext;
	private IProject 					_project;
	private IFile _file;

	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature#setMetaDataContext(org.eclipse.jst.jsf.metadataprocessors.MetaDataContext)
	 */
	public void setMetaDataContext(final MetaDataContext context) {
		this.mdContext = context;		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature#getMetaDataContext()
	 */
	public MetaDataContext getMetaDataContext() {
		return mdContext;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature#setStructuredDocumentContext(org.eclipse.wtp.jsf.context.structureddocument.IStructuredDocumentContext)
	 */
	public void setStructuredDocumentContext(final IStructuredDocumentContext context) {
		this.sdContext = context;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature#getStructuredDocumentContext()
	 */
	public IStructuredDocumentContext getStructuredDocumentContext() {
	    if (sdContext instanceof IStructuredDocumentContext)
	    {
	        return (IStructuredDocumentContext) sdContext;
	    }
	    return null;
	}
	
	/**
	 * @return the model context
	 */
	public IModelContext getModelContext() {
	    return this.sdContext;
	}
	
	/**
	 * @param modelContext
	 */
	public void setModelContext(IModelContext modelContext)
	{
	    this.sdContext = modelContext;
	}

    /**
     * @return the project
     */
    protected IProject getProject2()
    {
        if (_project == null)
        {
            _project = IStructuredDocumentContextResolverFactory2.INSTANCE.getWorkspaceContextResolver2(sdContext)
                    .getProject();
        }
        return _project;
    }

    /**
     * @return the file
     */
    protected IFile getFile2()
    {
        if (_file == null)
        {
            IResource res = IStructuredDocumentContextResolverFactory2.INSTANCE.getWorkspaceContextResolver2(sdContext)
                    .getResource();
            if (res instanceof IFile) _file = (IFile) res;
        }
        return _file;
    }

	//common metadata accessors
	/**
	 * Return the single expected String value for a given property.
	 * 
	 * @param traitName property name
	 * @return String value
	 */
	protected String getTraitValueAsString(final String traitName){	
		Trait t = getTraitForEntityUsingContext(traitName);
		if (t != null){
			return TraitValueHelper.getValueAsString(t);
		}
				
		return null;

	}
	
	/**
	 * Return the List of values for a given property.
	 * 
	 * @param traitName trait name
	 * @return List of String values
	 */
	protected List getTraitValueAsListOfStrings(final String traitName){
		Trait t = getTraitForEntityUsingContext(traitName);
		if (t != null){
			return TraitValueHelper.getValueAsListOfStrings(t);
		}
			
		return Collections.EMPTY_LIST;
	}

	/**
	 * Return a boolean value for the given named trait .
	 * 
	 * @param traitName property name
	 * @return boolean value.  Returns false if trait was not located.
	 */
	protected boolean getTraitValueAsBoolean(final String traitName){	
		Trait t = getTraitForEntityUsingContext(traitName);
		if (t != null){
			return TraitValueHelper.getValueAsBoolean(t);
		}
				
		return false;

	}
	private Trait getTraitForEntityUsingContext(final String traitName) {
		//look for trait on given entity
		final Entity entity = getMetaDataContext().getEntity();		
		final IMetaDataDomainContext modelContext = getMetaDataDomainContext();
		final IMetaDataQuery query = MetaDataQueryFactory.getInstance().createQuery(modelContext);
		return query.getQueryHelper().getTrait(entity, traitName);
	}

	protected IMetaDataDomainContext getMetaDataDomainContext() {
		final IFile file = getFile2();
		if (file != null)
		{
			return MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(file);
		}
		
		IProject project = getProject2();
		if (project != null)
		{
		    return MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(project);
		}
		return null;
	}

	/**
	 * @return small-icon name 
	 */
	protected String getSmallIcon() {
		return getTraitValueAsString(IPossibleValues.POSSIBLE_VALUES_SMALL_ICON_PROP_NAME);
	}
	
	/**
	 * @return ImageDescriptor from the small-icon property for annotation from the same
	 * source model provider as the trait
	 */
	protected ImageDescriptor getImage() {
		final String smallIcon = getSmallIcon();
		if (smallIcon == null)
			return null;
		
		try {
			Trait t = getMetaDataContext().getTrait();
			IMetaDataSourceModelProvider provider = t.getSourceModelProvider();
			IImageDescriptorProvider ip = (IImageDescriptorProvider)provider.getAdapter(IImageDescriptorProvider.class);
			if (ip != null){
				return ip.getImageDescriptor(smallIcon);
			}
		} catch (RuntimeException e) {		
			//TODO: log error?

		}
		return null;
	}
}
