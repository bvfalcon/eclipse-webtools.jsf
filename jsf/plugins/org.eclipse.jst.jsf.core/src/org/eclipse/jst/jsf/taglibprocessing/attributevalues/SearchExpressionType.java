package org.eclipse.jst.jsf.taglibprocessing.attributevalues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFUtilFactory;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFUtils;
import org.eclipse.jst.jsf.metadataprocessors.features.PossibleValue;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;

/**
 * */
public class SearchExpressionType extends StringType {
	private static final String delimiter = " "; //$NON-NLS-1$
	private static final String[] specialCases = { "@all", "@none"}; //$NON-NLS-1$ //$NON-NLS-2$

	private Map<String, String> standardExpressions;

	@Override
	public List<PossibleValue> getPossibleValues() {
		String currentAttrValue = getCurrentAttrValue();
		if (containsSpecialCase(currentAttrValue)) {
			return Collections.emptyList();
		}
		if (currentAttrValue.length() > 0 && !currentAttrValue.endsWith(delimiter)) {
			currentAttrValue += delimiter;
		}
		final String currentValue = currentAttrValue;
		List<PossibleValue> possibleValues = super.getPossibleValues();
		possibleValues = removeAlreadySelected(possibleValues, currentAttrValue);
		possibleValues = setDefaultFirst(possibleValues);
		final List<String> specialCasesList = Arrays.asList(specialCases);
		return possibleValues.stream()
			.peek(val -> val.setValue(specialCasesList.indexOf(val.getValue()) == -1 ?
					currentValue + val.getValue() : val.getValue()))
			.collect(Collectors.toList());
	}

	private boolean containsSpecialCase(String value) {
		if (value == null) {
			return false;
		}
		for (String specialCase : specialCases) {
			if (value.indexOf(specialCase) != -1) {
				return true;
			}
		}
		return false;
	}

	private List<PossibleValue> removeAlreadySelected(List<PossibleValue> proposals, String selectionString) {
		if (selectionString == null || selectionString.trim().isEmpty()) {
			return proposals;
		}
		List<PossibleValue> result = new ArrayList<>(proposals); 
		Stream.of(selectionString.split(delimiter)).map(String::trim)
				.forEach(selected -> result.removeIf(proposal -> selected.equals(proposal.getValue())));
		return result;
	}
	
	private List<PossibleValue> setDefaultFirst(List<PossibleValue> proposals) {
		String defaultValue = super.getDefaultValue();
		if (defaultValue == null || defaultValue.trim().isEmpty()) {
			return proposals;
		}
		final String defValue = defaultValue.trim();
		PossibleValue defaultProposal = proposals.stream().filter(proposal -> defValue.equals(proposal.getValue())).findAny().orElse(null);
		if (defaultProposal == null) {
			return proposals;
		}
		proposals.remove(defaultProposal);
		proposals.add(0, defaultProposal);
		return proposals;
	}

	private String getCurrentAttrValue() {
		if (getStructuredDocumentContext() != null){
			IDOMContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE.getDOMContextResolver(getStructuredDocumentContext());
			if (resolver != null){
				return resolver.getNode().getNodeValue();
			}
		}
		return ""; //$NON-NLS-1$
	}

	@Override
	protected List<String> getMDPossibleValues() {
		List<String> result = new ArrayList<>();
		result.addAll(getStandardExpressions().keySet());
		result.addAll(getComponentIdsFromCurrentDocument());
		return result;
	}

	@Override
	protected List<String> getMDPossibleValuesForDisplay() {
		List<String> result = new ArrayList<>();
		result.addAll(getStandardExpressions().entrySet().stream()
					.map(entry -> entry.getKey() + " (" + entry.getValue() + ")") //$NON-NLS-1$ //$NON-NLS-2$
					.collect(Collectors.toList()));
		result.addAll(getComponentIdsFromCurrentDocument());
		return result;
	}

	private Map<String, String> getStandardExpressions() {
		if (this.standardExpressions == null) {
			Map<String, String> result = new LinkedHashMap<>();
			JSFUtils jsfUtil = new JSFUtilFactory().create(getProject2());
			if (jsfUtil != null) {
				result.putAll(jsfUtil.getSearchExpressions());
			}
			standardExpressions = result;
		}
		return standardExpressions;
	}

	private List<String> getComponentIdsFromCurrentDocument() {
		List<String> result = new ArrayList<>();
		if (getStructuredDocumentContext() == null) {
			return result;
		}
		IDOMContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE.getDOMContextResolver(getStructuredDocumentContext());
		if (resolver == null) {
			return result;
		}
		Node aNode = resolver.getNode();
		if (!(aNode instanceof Attr)) {
			return result;
		}
		Node previousTagNode = ((Attr) aNode).getOwnerElement();
		Node currentTagNode = ((Attr) aNode).getOwnerElement().getParentNode();
		while (currentTagNode != null) {
			for (int itemIndex = 0; itemIndex < currentTagNode.getChildNodes().getLength(); itemIndex++) {
				Node childNode = currentTagNode.getChildNodes().item(itemIndex);
				if (childNode == previousTagNode) {
					continue;
				}
				List<String> childNodeIds = getComponentIdsFromTagWithChildNodes(childNode, null);
				result.addAll(childNodeIds);
			}
			previousTagNode = currentTagNode;
			currentTagNode = currentTagNode.getParentNode();
		}
		return result;
	}

	private List<String> getComponentIdsFromTagWithChildNodes(Node tagNode, String path) {
		List<String> result = new ArrayList<>();
		Optional.of(tagNode).map(Node::getAttributes).map(o -> o.getNamedItem("id")).filter(o -> o instanceof Attr) //$NON-NLS-1$
			.map(o -> (Attr)o).map(Attr::getValue)
			.ifPresent(value -> result.add(path != null ? path + ":" + value.trim() : value.trim())); //$NON-NLS-1$
		String childNodePath = null;
		if (isNamingContainer(tagNode)) {
			String containerId = getNamingContainerName(tagNode);
			if (containerId != null) {
				childNodePath = ":" + getNamingContainerName(tagNode); //$NON-NLS-1$
			} else {
				JSFCorePlugin.log(IStatus.WARNING,
						String.format("Markup element %s should have id, but it hasn't", tagNode.getNodeName())); //$NON-NLS-1$
				childNodePath = ""; //$NON-NLS-1$
			}
		}
		for (int itemIndex = 0; itemIndex < tagNode.getChildNodes().getLength(); itemIndex++) {
			Node childNode = tagNode.getChildNodes().item(itemIndex);
			List<String> childNodeIds = getComponentIdsFromTagWithChildNodes(childNode, childNodePath);
			if (path != null) {
				childNodeIds = childNodeIds.stream().map(childNodeId -> path + ":" + childNodeId).toList(); //$NON-NLS-1$
			}
			result.addAll(childNodeIds);
		}
		return result;
	}

	private boolean isNamingContainer(Node tagNode) {
		return "form".equalsIgnoreCase(tagNode.getLocalName()); //$NON-NLS-1$
	}

	private String getNamingContainerName(Node tagNode) {
		return Optional.of(tagNode).map(Node::getAttributes).map(o -> o.getNamedItem("id")) //$NON-NLS-1$
				.filter(o -> o instanceof Attr).map(o -> (Attr)o).map(Attr::getValue).orElse(null);
	}
}
