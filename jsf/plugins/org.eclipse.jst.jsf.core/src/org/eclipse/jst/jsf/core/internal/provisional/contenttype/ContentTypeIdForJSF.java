/*******************************************************************************
 * Copyright (c) 2004, 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     
 *******************************************************************************/
package org.eclipse.jst.jsf.core.internal.provisional.contenttype;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager.ContentTypeChangeEvent;
import org.eclipse.core.runtime.content.IContentTypeManager.IContentTypeChangeListener;

/**
 * This class, with its one field, is a convience to provide compile-time
 * safety when refering to a contentType ID. The value of the contenttype id
 * field must match what is specified in plugin.xml file.
 */
public class ContentTypeIdForJSF {
	/**
	 * The value of the contenttype id field must match what is specified in
	 * plugin.xml file. Note: this value is intentially set with default
	 * protected method so it will not be inlined.
	 */
	public final static String ContentTypeID_JSF = getConstantString();
	/**
	 * The value of the contenttype id field must match what is specified in
	 * plugin.xml file. Note: this value is intentially set with default
	 * protected method so it will not be inlined.
	 */
	public final static String ContentTypeID_JSFFRAGMENT = getFragmentConstantString();

	static char[][] JSF_EXTENSIONS;
	static char[][] JSF_FILENAMES;
	private static String XHTML = "xhtml"; //$NON-NLS-1$

	private static ContentTypeChangeListener typeChangeListener = new ContentTypeChangeListener();

	/**
	 * Don't allow instantiation.
	 */
	private ContentTypeIdForJSF() {
		super();
	}

	static String getConstantString() {
		return "org.eclipse.jst.jsf.core.jsfsource"; //$NON-NLS-1$
	}
	
	static String getFragmentConstantString() {
		return "org.eclipse.jst.jsf.core.jsffragmentsource"; //$NON-NLS-1$
	}
	
	static String getTagConstantString() {
		return "org.eclipse.jst.jsf.core.tagsource"; //$NON-NLS-1$
	}

	public static IContentTypeChangeListener getTypeChangeListener() {
		return typeChangeListener;
	}

	/**
	 * @param fileName
	 * @return the first index within an array of filename extensions that
	 *         denote the JSF content type or a subtype and match the
	 *         extension of the given filename, or the index within an
	 *         array of explicitly defined filenames that matches.
	 */
	public static int indexOfJSFExtension(String fileName) {
		char[] name = fileName.toCharArray();
		int fileNameLength = fileName.length();
		char[][] jsfExtensions = getJSFExtensions();
		extensions: for (int i = 0, length = jsfExtensions.length; i < length; i++) {
			char[] extension = jsfExtensions[i];
			int extensionLength = extension.length;
			int extensionStart = fileNameLength - extensionLength;
			int dotIndex = extensionStart - 1;
			if (dotIndex < 0) continue;
			if (name[dotIndex] != '.') continue;
			for (int j = 0; j < extensionLength; j++) {
				if (name[extensionStart + j] != extension[j])
					continue extensions;
			}
			return dotIndex;
		}
		char[][] filenames = JSF_FILENAMES;
		if (filenames != null) {
			for (int i = 0; i < filenames.length; i++) {
				if (Arrays.equals(name, filenames[i]))
					return i;
			}
		}
		return -1;
	}
	
	/**
	 * @return an array of all filename extensions that are of the JSF content
	 *         type or one of its subtypes
	 */
	public static char[][] getJSFExtensions() {
		if (JSF_EXTENSIONS == null) {
			IContentType jspContentType = Platform.getContentTypeManager().getContentType(getConstantString());
			Set<String> fileExtensions = new HashSet<>();
			Set<String> fileNames = new HashSet<>();
			// content types derived from JSP content type should be included (https://bugs.eclipse.org/bugs/show_bug.cgi?id=121715)
			IContentType[] contentTypes = Platform.getContentTypeManager().getAllContentTypes();
			for (int i = 0, length = contentTypes.length; i < length; i++) {
				if (contentTypes[i].isKindOf(jspContentType)) { // note that jspContentType.isKindOf(jspContentType) == true
					String[] fileExtension = contentTypes[i].getFileSpecs(IContentType.FILE_EXTENSION_SPEC);
					for (int j = 0; j < fileExtension.length; j++) {
						fileExtensions.add(fileExtension[j]);
					}
					String[] names = contentTypes[i].getFileSpecs(IContentType.FILE_NAME_SPEC);
					for (int j = 0; j < names.length; j++) {
						fileNames.add(names[j]);
					}
				}
			}
			int length = fileExtensions.size();
			// note that file extensions contains "xhtml"
			char[][] extensions = new char[length][];
			extensions[0] = XHTML.toCharArray(); // ensure that "jsp" is first
			int index = 1;
			Iterator<String> iterator = fileExtensions.iterator();
			while (iterator.hasNext()) {
				String fileExtension = iterator.next();
				if (XHTML.equalsIgnoreCase(fileExtension))
					continue;
				extensions[index++] = fileExtension.toCharArray();
			}
			JSF_EXTENSIONS = extensions;

			char[][] names = new char[fileNames.size()][];
			iterator = fileNames.iterator();
			int i = 0;
			while (iterator.hasNext()) {
				names[i++] = iterator.next().toCharArray();
			}
			JSF_FILENAMES = names;
		}
		return JSF_EXTENSIONS;
	}

	static class ContentTypeChangeListener implements IContentTypeChangeListener {
		@Override
		public void contentTypeChanged(ContentTypeChangeEvent event) {
			String id = event.getContentType().getId();
			if (getConstantString().equals(id) || getFragmentConstantString().equals(id) || getTagConstantString().equals(id)) {
				JSF_EXTENSIONS = null;
			}
		}
	}
}
