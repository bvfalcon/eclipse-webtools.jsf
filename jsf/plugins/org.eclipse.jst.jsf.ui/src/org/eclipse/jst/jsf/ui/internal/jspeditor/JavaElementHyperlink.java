/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.ui.internal.jspeditor;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.jst.jsf.ui.internal.JSFUIPlugin;
import org.eclipse.jst.jsf.ui.internal.Messages;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorPart;

/**
 * Hyperlink for a IJavaElement to the corresponding java source
 */
class JavaElementHyperlink implements IHyperlink, ITestHyperlink {
	private final IRegion fRegion;
	private final IJavaElement fElement;

	/**Creates a new hyperlink for a given IJavaElement
	 * @param region - region of the hyperlink
	 * @param element - hyperlink links to the source of <code>element</code>
	 */
	public JavaElementHyperlink(final IRegion region, final IJavaElement element) {
		fRegion = region;
		fElement = element;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.jface.text.hyperlink.IHyperlink#getHyperlinkRegion()
	 */
	public IRegion getHyperlinkRegion() {
		return fRegion;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.jface.text.hyperlink.IHyperlink#getTypeLabel()
	 */
	public String getTypeLabel() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.jface.text.hyperlink.IHyperlink#getHyperlinkText()
	 */
	public String getHyperlinkText() {
		//Bug 254452 - JSF hyperlink provider shows "unknown hyperlink" when in list
	    final IJavaElement element = determineJavaElement();
	    if (element != null) {
	    	final ICompilationUnit compilationUnit = (ICompilationUnit)element.getAncestor(IJavaElement.COMPILATION_UNIT);
	    	if (compilationUnit != null) {
    			return NLS.bind(Messages.Hyperlink_Open_JavaType, compilationUnit.getElementName());
	    	}
	    	return Messages.Hyperlink_Open_JavaFile;
	    }
		return Messages.Hyperlink_Open_JavaElement;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.jface.text.hyperlink.IHyperlink#open()
	 */
	public void open() {
		try {
			final IEditorPart editor = JavaUI.openInEditor(fElement);
			if (editor != null) {
				JavaUI.revealInEditor(editor, fElement);
			}
		}
		catch (final Exception e) {
		    JSFUIPlugin.log(IStatus.WARNING, e.getMessage(), e);
		}
	}

	public IJavaElement determineJavaElement() {
		return fElement;
	}


}
