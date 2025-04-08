/*******************************************************************************
 * Copyright (c) 2007, 2008 Oracle Corporation., and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.metadataprocessors.AbstractRootTypeDescriptor;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.PossibleValue;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMAttr;
import org.w3c.dom.Node;

/**
 * Meta-data processing type representing a Date, Time and DateTime Pattern types.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 */

public class DateTimePatternType extends AbstractRootTypeDescriptor implements IPossibleValues{
	//should we ever validate the patterns, extend EnumerationType
	final private static String[] DATEPATTERNS = {"dd.MM.yyyy", "M/d/yy", "EEE, M/d/yy", "MM/dd/yyyy", "EEE, MM/dd/yyyy", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
        "MMM d, yyyy", "EEE, MMM d, yyyy", "MMMM d, yyyy", "EEEE, MMMM d, yyyy", "MMMM yyyy" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ };
	};
	
	final private static String[] TIMEPATTERNS     = {"hh:mm", "hh:mm z", "HH:mm z", "HH:mm:ss z" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	};
	  
	final private static String[] DATETIMEPATTERNS = {"dd.MM.yyyy HH:mm", "M/d/yy hh:mm", "EEE, M/d/yy hh:mm", "MM/dd/yyyy HH:mm:ss z", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		"EEE, MM/dd/yyyy HH:mm:ss z", "MMM d, yyyy HH:mm z", "EEE, MMM d, yyyy HH:mm z", "MMMM d, yyyy HH:mm z", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		"EEEE, MMMM d, yyyy HH:mm z" //$NON-NLS-1$
	};

	public List getPossibleValues() {
		List<String> ret = new ArrayList();
		String typeVal = getTypeValue();
		if ("date".equalsIgnoreCase(typeVal) || "localDate".equalsIgnoreCase(typeVal)) { //$NON-NLS-1$ //$NON-NLS-2$
			for (int i=0;i<DATEPATTERNS.length;i++){
				ret.add(DATEPATTERNS[i]);
			}
		}
		else if ("time".equalsIgnoreCase(typeVal) || "localTime".equalsIgnoreCase(typeVal) || //$NON-NLS-1$ //$NON-NLS-2$
				"offsetTime".equalsIgnoreCase(typeVal)) { //$NON-NLS-1$
			for (int i=0;i<TIMEPATTERNS.length;i++){
				ret.add(TIMEPATTERNS[i]);
			}
		}
		else {	
			for (int i=0;i<DATETIMEPATTERNS.length;i++){
				ret.add(DATETIMEPATTERNS[i]);
			}
		}
		List<PossibleValue> result = new ArrayList<>();
		result.addAll(ret.stream().map(item -> toPossibleValue(item, false)).collect(Collectors.toList()));
		String currentValue = getCurrentAttrValue();
		if (currentValue != null && !currentValue.trim().isEmpty() && !ret.contains(currentValue.trim())) {
			result.add(0, toPossibleValue(currentValue, true));
		}
		return result;
	}
	
	private static List<ZonedDateTime> dates;
	private static List<ZonedDateTime> getExampleDates() {
		if (dates == null) {
			dates = Arrays.asList(
					ZonedDateTime.now(),
					ZonedDateTime.now().plus(13, ChronoUnit.YEARS).plus(6, ChronoUnit.MONTHS).plus(12, ChronoUnit.HOURS).plus(34, ChronoUnit.MINUTES),
					ZonedDateTime.of(1903, 12, 28, 19, 26, 15, 123456789, ZoneId.systemDefault()),
					ZonedDateTime.of(1600, 2, 17, 9, 5, 37, 123456789, ZoneId.systemDefault()),
					ZonedDateTime.of(-4, 7, 15, 7, 42, 5, 123456789, ZoneId.systemDefault())
				);
		}
		return dates;
	}
	
	private PossibleValue toPossibleValue(String value, boolean showError) {
		PossibleValue pValue = new PossibleValue(value);
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(value);
			StringBuilder sb = new StringBuilder("Examples:<br>"); //$NON-NLS-1$
			sb.append(getExampleDates().stream().map(item -> " - " + item.format(dtf)) //$NON-NLS-1$
					.collect(Collectors.joining("<br>"))); //$NON-NLS-1$
			pValue.setAdditionalInformation(sb.toString());
		} catch (Throwable e) {
			if (showError) {
				String additionalInfo = String.format("Pattern is invalid: %s", e.getMessage()); //$NON-NLS-1$
				pValue.setAdditionalInformation(additionalInfo);
			} else {
				JSFCorePlugin.log(e.getMessage(), e);
			}
		}
		return pValue;
	}
	
	private String getTypeValue() {
		if (getStructuredDocumentContext() != null){
			IDOMContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE.getDOMContextResolver(getStructuredDocumentContext());
			if (resolver != null){
				Node aNode = resolver.getNode();
				if (aNode instanceof IDOMAttr) {
					Node tagNode = ((IDOMAttr)aNode).getOwnerElement();
					IDOMAttr typeNode = (IDOMAttr) tagNode.getAttributes().getNamedItem("type"); //$NON-NLS-1$
					if (typeNode != null)
						return typeNode.getValue();
				}				
			}
		}
		return ""; //$NON-NLS-1$
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
}
