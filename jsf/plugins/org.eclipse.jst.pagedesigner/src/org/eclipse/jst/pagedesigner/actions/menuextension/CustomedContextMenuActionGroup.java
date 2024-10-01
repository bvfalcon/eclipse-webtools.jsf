/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
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
package org.eclipse.jst.pagedesigner.actions.menuextension;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jst.pagedesigner.extensionpoint.IContextMenuItemContributor;
import org.eclipse.jst.pagedesigner.utils.JSFUtil;
import org.eclipse.jst.pagedesigner.utils.StructuredModelUtil;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;

/**
 * @author mengbo
 * @version 1.5
 */
public class CustomedContextMenuActionGroup extends ActionGroup {
	private final static String POPUPMENU_EXTENSION_ID = "org.eclipse.jst.pagedesigner.popupMenuContributor"; //$NON-NLS-1$

	private List _contributedMenuListener;

	private Control _parentControl;

	private IStructuredModel _model;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void fillContextMenu(IMenuManager menu) {
		populateContributedMenu(menu);
	}

	private List getListeners() {
		if (_contributedMenuListener == null) {
			_contributedMenuListener = computeContributedMenuListener();
		}
		return _contributedMenuListener;
	}

	private List computeContributedMenuListener() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = registry
				.getExtensionPoint(POPUPMENU_EXTENSION_ID);
		IExtension[] extensions = extensionPoint.getExtensions();
		List results = new ArrayList();
		for (int i = 0; i < extensions.length; i++) {
			IConfigurationElement[] elements = extensions[i]
					.getConfigurationElements();
			for (int j = 0; j < elements.length; j++) {
				try {
					Object listener = elements[j]
							.createExecutableExtension("class");//$NON-NLS-1$

					if (listener instanceof IContextMenuItemContributor) {
						results.add(listener);
						((IContextMenuItemContributor) listener)
								.setURI(elements[j].getAttribute("URI"));//$NON-NLS-1$
					}
				} catch (CoreException e) {
					// ignore
				}
			}
		}
		return results;
	}

	private void populateContributedMenu(IMenuManager menuMgr) {
		List list = getListeners();
		for (int i = 0, n = list.size(); i < n; i++) {
			IContextMenuItemContributor contributor = (IContextMenuItemContributor) list
					.get(i);
			IFile file = StructuredModelUtil.getFileFor(_model);
			if (file != null && contributor.getURI() != null) {
				if (JSFUtil.supportTaglib(contributor.getURI(), file)) {
					contributor.fillContextMenu(menuMgr, getContext()
							.getSelection(), _model, _parentControl);
				}
			}
		}
	}

	/**
	 * @return Returns the model.
	 */
	public IStructuredModel getModel() {
		return _model;
	}

	/**
	 * @param model
	 *            The model to set.
	 */
	public void setModel(IStructuredModel model) {
		this._model = model;
	}

	/**
	 * @return Returns the parentControl.
	 */
	public Control getParentControl() {
		return _parentControl;
	}

	/**
	 * @param parentControl
	 *            The parentControl to set.
	 */
	public void setParentControl(Control parentControl) {
		this._parentControl = parentControl;
	}
}
