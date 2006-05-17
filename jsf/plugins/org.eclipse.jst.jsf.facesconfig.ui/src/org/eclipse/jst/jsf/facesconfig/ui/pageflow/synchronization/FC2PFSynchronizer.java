/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.synchronization;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jface.util.Assert;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FromViewIdType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.emf.ToViewIdType;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;

/**
 * The adapter that listens to modification of faces-config and updates
 * pageflow as needed.
 * 
 * @author hmeng
 * 
 */

public class FC2PFSynchronizer extends AdapterImpl {
	private final boolean DEBUG = false;

	FC2PFTransformer transformer;

	public FC2PFSynchronizer(FC2PFTransformer transformer) {
		this.transformer = transformer;
	}

	public Notifier getTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setTarget(Notifier newTarget) {
	}

	public boolean isAdapterForType(Object type) {
		return type == FC2PFSynchronizer.class;
	}

	public void notifyChanged(Notification notification) {
		if (!transformer.isListenToNotify()
				|| !(notification.getNotifier() instanceof EObject)) {
			return;
		}
		transformer.setInEvent(true);
		int type = notification.getEventType();
		switch (type) {
		case Notification.ADD: {
			processAdd(notification);
			notifyPageflow(notification);
			break;
		}
		case Notification.SET: {
			processSet(notification);
			notifyPageflow(notification);
			break;
		}
		case Notification.REMOVE:
			processRemove(notification);
			notifyPageflow(notification);
			break;
		}
		transformer.refreshFCAdapter((EObject) notification.getNotifier());
		transformer.refreshPFAdapter(transformer.getPageflow());

		transformer.setInEvent(false);
	}

	private void processAdd(Notification notification) {
		Object feature = notification.getFeature();
		if (feature == FacesConfigPackage.eINSTANCE
				.getNavigationRuleType_NavigationCase()) {
			if (DEBUG)
				System.out.println("New navigation case");
			NavigationCaseType newCase = (NavigationCaseType) notification
					.getNewValue();
			transformer.updatePageflowElements(transformer.getPageflow(),
					newCase);
		} else if (feature == FacesConfigPackage.eINSTANCE
				.getFacesConfigType_NavigationRule()) {
			NavigationRuleType newRule = (NavigationRuleType) notification
					.getNewValue();
			if (newRule.getNavigationCase().size() > 0) {
				for (int i = 0; i < newRule.getNavigationCase().size(); i++) {
					transformer.updatePageflowElements(transformer
							.getPageflow(), (NavigationCaseType) newRule
							.getNavigationCase().get(i));
				}
			}
			if (DEBUG)
				System.out.println("New navigation rule");
		} else if (DEBUG)
			System.out.println("Something is added");
	}

	/**
	 * Update pageflow model for the change, the editor will update nodes'
	 * layout.
	 * 
	 * @param notification
	 */
	private void notifyPageflow(Notification notification) {
		Assert.isTrue(notification.getNotifier() instanceof InternalEObject);
		transformer.getPageflow().notifyModelChanged(
				new ENotificationImpl((InternalEObject) notification
						.getNotifier(), Notification.SET,
						PageflowPackage.PAGEFLOW, null, null));
	}

	private void processRemove(Notification notification) {
		if (notification.getFeature() == FacesConfigPackage.eINSTANCE
				.getNavigationRuleType_NavigationCase()) {
			if (notification.getOldValue() instanceof NavigationCaseType) {
				NavigationCaseType caseType = (NavigationCaseType) notification
						.getOldValue();
				transformer.removeLink(caseType);
			}
			if (DEBUG)
				System.out.println("Navigation case");
		} else if (notification.getFeature() == FacesConfigPackage.eINSTANCE
				.getNavigationCaseType()) {
			if (notification.getOldValue() instanceof NavigationRuleType) {
				NavigationRuleType rule = (NavigationRuleType) notification
						.getOldValue();
				for (int i = 0; i < rule.getNavigationCase().size(); i++) {
					NavigationCaseType caseType = (NavigationCaseType) rule
							.getNavigationCase().get(i);
					transformer.refreshLink(caseType);
				}
			}
			if (DEBUG)
				System.out.println("navigation rule");
		} else if (notification.getFeature() == FacesConfigPackage.eINSTANCE
				.getFacesConfigType_NavigationRule()) {
			if (notification.getOldValue() instanceof NavigationRuleType) {
				NavigationRuleType rule = (NavigationRuleType) notification
						.getOldValue();
				for (int i = 0; i < rule.getNavigationCase().size(); i++) {
					NavigationCaseType caseType = (NavigationCaseType) rule
							.getNavigationCase().get(i);
					transformer.refreshLink(caseType);
				}
			}
		}
		if (DEBUG)
			System.out.println("Something is removed");
	}

	private void processSet(Notification notification) {
		Object feature = notification.getFeature();
		if (feature == FacesConfigPackage.eINSTANCE
				.getFromViewIdType_TextContent()
				|| feature == FacesConfigPackage.eINSTANCE
						.getNavigationRuleType_FromViewId()) {
			fromViewIdChanged(notification);
		} else if (feature == FacesConfigPackage.eINSTANCE
				.getToViewIdType_TextContent()
				|| feature == FacesConfigPackage.eINSTANCE
						.getNavigationCaseType_ToViewId()) {
			toViewIdChanged(notification);
		}
		if (DEBUG)
			System.out.println("Something is set");
	}

	private void fromViewIdChanged(Notification notification) {
		// remove
		Object feature = notification.getFeature();
		if (feature == FacesConfigPackage.eINSTANCE
				.getNavigationRuleType_FromViewId()
				&& notification.getNewValue() == null
				&& notification.getOldValue() instanceof FromViewIdType) {
			NavigationRuleType rule = (NavigationRuleType) notification
					.getNotifier();
			for (int i = 0; i < rule.getNavigationCase().size(); i++) {
				NavigationCaseType caseType = (NavigationCaseType) rule
						.getNavigationCase().get(i);
				transformer.refreshLink(caseType);
			}
		}
		// add or change
		else if (feature == FacesConfigPackage.eINSTANCE
				.getFromViewIdType_TextContent()) {
			NavigationRuleType rule = (NavigationRuleType) ((EObject) notification
					.getNotifier()).eContainer();
			for (int i = 0; i < rule.getNavigationCase().size(); i++) {
				NavigationCaseType caseType = (NavigationCaseType) rule
						.getNavigationCase().get(i);
				transformer.refreshLink(caseType);
			}
		}
	}

	private void toViewIdChanged(Notification notification) {
		// remove
		Object feature = notification.getFeature();
		if (feature == FacesConfigPackage.eINSTANCE
				.getNavigationCaseType_ToViewId()
				&& notification.getNewValue() == null
				&& notification.getOldValue() instanceof ToViewIdType) {
			NavigationCaseType caseType = (NavigationCaseType) notification
					.getNotifier();
			transformer.refreshLink(caseType);
		}
		// add or change
		else if (feature == FacesConfigPackage.eINSTANCE
				.getToViewIdType_TextContent()) {
			NavigationCaseType caseType = (NavigationCaseType) ((EObject) notification
					.getNotifier()).eContainer();
			transformer.refreshLink(caseType);
		}
	}

	public void dispose() {

	}
}
