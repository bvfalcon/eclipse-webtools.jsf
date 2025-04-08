package org.eclipse.jst.jsf.taglibprocessing.attributevalues;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.internal.IMetaDataQuery;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMAttr;
import org.w3c.dom.Node;

/**
 * Meta-data processing type representing a facet name type.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 */

public class FacetNameType extends StringType {
	/**
	 * Default name of property in annotation file to use when supplying facet name values from meta-data 
	 */
	public static final String VALID_FACET_NAMES = "valid-facets"; //$NON-NLS-1$

	private List<String> values;

	private List<String> getFacetNameCandidats() {
		synchronized (this) {
			if (values != null) {
				return values;
			}
			values = Collections.emptyList();
			if (getStructuredDocumentContext() == null) {
				return values;
			}
			IDOMContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE.getDOMContextResolver(getStructuredDocumentContext());
			if (resolver == null) {
				return values;
			}
			Node aNode = resolver.getNode();
			if (!(aNode instanceof IDOMAttr)) {
				return values;
			}
			Node parentNode = ((IDOMAttr)aNode).getOwnerElement().getParentNode();
			if (parentNode == null) {
				return values;
			}
			IMetaDataDomainContext context 	= MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(getProject2());
			IMetaDataQuery query = MetaDataQueryFactory.getInstance().createQuery(context);
			Entity parentEntity = query.getQueryHelper().getEntity(parentNode.getNamespaceURI(), parentNode.getLocalName());
			if (parentEntity == null) {
				return values;
			}
			EList<Trait> parentEntityTraits = parentEntity.getTraits();
			if (parentEntityTraits.isEmpty()) {
				return values;
			}
			Trait facetsTrait = parentEntityTraits.stream().filter(trait -> Objects.equals(trait.getId(), VALID_FACET_NAMES)).findFirst().orElse(null);
			if (facetsTrait != null) {
				values = TraitValueHelper.getValueAsListOfStrings(facetsTrait);
			}
			return values;
		}
	}

	@Override
	protected List getMDValidValues() {
		return getFacetNameCandidats();
	}

	@Override
	protected List getMDPossibleValues() {
		return getFacetNameCandidats();
	}
}
