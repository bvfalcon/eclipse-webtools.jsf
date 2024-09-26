/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.jsf.common.ui.internal.dialogs.CommonResourceDialog;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.PageflowMessages;
import org.eclipse.swt.widgets.Shell;

/**
 * @author hmeng
 */

public class ProjectWebPageSelectionDialog extends CommonResourceDialog {

	/**
	 * @param parentShell
	 * @param project
	 * @param style
	 */
	public ProjectWebPageSelectionDialog(Shell parentShell, IProject project,
			int style) {
		super(parentShell, project, style);
		init();
	}

	/**
	 * @param parentShell
	 * @param project
	 */
	public ProjectWebPageSelectionDialog(Shell parentShell, IProject project) {
		super(parentShell, project);
		init();
	}

	private void init() {
		setResourceDescription(PageflowMessages.ProjectWebPageSelectionDialog_Description);
		setSuffixs(new String[] { "jsp", "jspx", "jsf", "html", "htm", "xhtml" }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
	}

	/**
	 * @return the rseult file path
	 */
	public String getResultFilePath() {
		String result = ""; //$NON-NLS-1$
		IFile selectedFile = (IFile) getResult()[0];
		IPath path = selectedFile.getProjectRelativePath();
		path = path.removeFirstSegments(1);
		result = "/" + path.toString(); //$NON-NLS-1$
		return result;
	}
}
