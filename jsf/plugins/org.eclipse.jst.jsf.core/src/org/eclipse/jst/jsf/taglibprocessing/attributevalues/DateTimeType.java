/*******************************************************************************
 * Copyright (c) 2007, 2024 Oracle Corporation., and others
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.metadataprocessors.AbstractRootTypeDescriptor;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.PossibleValue;

/**
 * Meta-data processing type representing a Date, Time and DateTime types.
 */

public class DateTimeType extends AbstractRootTypeDescriptor implements IPossibleValues {
	final private static List<String> OLD_TYPES     = Arrays.asList("date", "time", "both"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	  
	final private static List<String> JAVA8_TYPES = Arrays.asList(
		"localDate", "localDateTime", "localTime", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		"offsetTime", "offsetDateTime", //$NON-NLS-1$ //$NON-NLS-2$
		"zonedDateTime" //$NON-NLS-1$
	);

	public List getPossibleValues() {
		List<String> ret = new ArrayList();
		ret.addAll(OLD_TYPES);
		IProject project = this.getProject2();
		if (JSFVersion.guessAtLeast(JSFVersion.V2_3, project)) {
			ret.addAll(JAVA8_TYPES);
		}
		return ret.stream().map(item -> new PossibleValue(item)).toList();
	}
}
