/*******************************************************************************
 * Copyright (c) 2005, 2013 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.jsfappconfig;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.webapplication.ContextParam;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.project.facet.core.FacetedProjectFramework;

/**
 * ContextParamSpecifiedJSFAppConfigLocater attempts to locate application
 * configuration files specified by the JSF CONFIG_FILES context parameter.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Ian Trimble - Oracle
 * TODO:cleanup once JavaEE API's stabilize
 */
public class ContextParamSpecifiedJSFAppConfigLocater extends WebContentRelativeJSFAppConfigLocater {

	/**
	 * Cached instance of ContextParamAdapter.
	 */
	protected ContextParamAdapter 	contextParamAdapter = null;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.jsfappconfig.AbstractJSFAppConfigLocater#startLocating()
	 */
	public void startLocating() {
		locateProviders();
		Object webAppObj = getModelObject();
		if (webAppObj != null){
			contextParamAdapter = new ContextParamAdapter();
			if (webAppObj instanceof WebApp) 
				startLocatingJ2EEConfigs((WebApp)webAppObj);
			else if (webAppObj instanceof org.eclipse.jst.javaee.web.WebApp)
				startLocatingJEEConfigs((org.eclipse.jst.javaee.web.WebApp)webAppObj);
		} else {
			//TODO should never get here.  Log err?
		}
	}

	private Object getModelObject() {
        Object modelObject = null;
        IProject project = getJSFAppConfigManager().getProject();
        boolean facetedProject = false;
        try
        {
            facetedProject = FacetedProjectFramework.isFacetedProject(project);
        }
        catch (CoreException ce)
        {
            // ignore
        }
        if (facetedProject) {
            IModelProvider provider = ModelProviderManager.getModelProvider(project);
            if (provider != null) {
                // we can't get the model if the workspace tree is currently locked.
                // to avoid the logged message, check the workspace tree here.
                if (!project.getWorkspace().isTreeLocked()) {
                    modelObject = provider.getModelObject();
                }
            }
        }

        return modelObject;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.jsfappconfig.AbstractJSFAppConfigLocater#stopLocating()
	 */
	public void stopLocating() {
		if (contextParamAdapter != null) {
			Object webAppObj = getModelObject();
			if (webAppObj != null){
				if (webAppObj instanceof WebApp) 
					stopLocatingJ2EEConfigs((WebApp)webAppObj);
				else if (webAppObj instanceof org.eclipse.jst.javaee.web.WebApp)
					stopLocatingJEEConfigs((org.eclipse.jst.javaee.web.WebApp)webAppObj);
			} else {
				//TODO should never get here.  Log err?
			}
			contextParamAdapter = null;
		}
	}

	private void startLocatingJ2EEConfigs(WebApp webApp){
		webApp.eAdapters().add(contextParamAdapter);
		EList contexts = webApp.getContexts();
		if (contexts != null) {
			Iterator itContexts = contexts.iterator();
			while (itContexts.hasNext()) {
				ContextParam contextParam = (ContextParam)itContexts.next();
				contextParam.eAdapters().add(contextParamAdapter);
			}
		}
		EList contextParams = webApp.getContextParams();
		if (contextParams != null) {
			Iterator itContextParams = contextParams.iterator();
			while (itContextParams.hasNext()) {
				ParamValue paramValue = (ParamValue)itContextParams.next();
				paramValue.eAdapters().add(contextParamAdapter);
			}
		}
	}

	private void startLocatingJEEConfigs(org.eclipse.jst.javaee.web.WebApp webApp) {
		((EObject)webApp).eAdapters().add(contextParamAdapter);
		List params = webApp.getContextParams();
		if (params != null) {
			Iterator itContexts = params.iterator();
			while (itContexts.hasNext()) {
				EObject contextParam = (EObject)itContexts.next();
				contextParam.eAdapters().add(contextParamAdapter);
			}
		}
	}

	private void stopLocatingJ2EEConfigs(WebApp webApp) {
		webApp.eAdapters().remove(contextParamAdapter);
		EList contexts = webApp.getContexts();
		if (contexts != null) {
			Iterator itContexts = contexts.iterator();
			while (itContexts.hasNext()) {
				ContextParam contextParam = (ContextParam)itContexts.next();
				contextParam.eAdapters().remove(contextParamAdapter);
			}
		}
		EList contextParams = webApp.getContextParams();
		if (contextParams != null) {
			Iterator itContextParams = contextParams.iterator();
			while (itContextParams.hasNext()) {
				ParamValue paramValue = (ParamValue)itContextParams.next();
				paramValue.eAdapters().remove(contextParamAdapter);
			}
		}
	}
	
	private void stopLocatingJEEConfigs(org.eclipse.jst.javaee.web.WebApp webApp) {
		((EObject)webApp).eAdapters().remove(contextParamAdapter);
		List contextParams = webApp.getContextParams();
		if (contextParams != null) {
			Iterator itContextParams = contextParams.iterator();
			while (itContextParams.hasNext()) {
				EObject paramValue = (EObject)itContextParams.next();
				paramValue.eAdapters().remove(contextParamAdapter);
			}
		}
	}
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.jsfappconfig.WebContentRelativeJSFAppConfigLocater#getFilenames()
	 */
	protected List getFilenames() {
		return JSFAppConfigUtils.getConfigFilesFromContextParam(getJSFAppConfigManager().getProject());
	}

	/**
	 * Adapter implementation used to monitor addition/removal of context-param
	 * nodes and change in name of existing nodes in order to respond to
	 * changes to the JSF CONFIG_FILES context-param.
	 * 
	 * @author Ian Trimble - Oracle
	 */
	class ContextParamAdapter extends AdapterImpl {

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
		 */
		public void notifyChanged(Notification notification) {
			Object objNotifier = notification.getNotifier();
//			System.out.println(objNotifier.toString());
			if (objNotifier instanceof WebApp ||
					objNotifier instanceof org.eclipse.jst.javaee.web.WebApp) {
				int eventType = notification.getEventType();
				switch (eventType) {
				case Notification.ADD:
					Object objNewValue = notification.getNewValue();
					if (objNewValue instanceof ContextParam ||
							objNewValue instanceof org.eclipse.jst.javaee.core.ParamValue) {
						contextParamAdded((EObject)objNewValue);
					} else if (objNewValue instanceof ParamValue ) {		
						paramValueAdded((EObject)objNewValue);
					}
					break;
				case Notification.REMOVE:
					Object objOldValue = notification.getOldValue();
					if (objOldValue instanceof ContextParam ||
							objOldValue instanceof org.eclipse.jst.javaee.core.ParamValue) {
						contextParamRemoved((EObject)objOldValue);
					} else if (objOldValue instanceof ParamValue) {
						paramValueRemoved((EObject)objOldValue);
					}
					break;
				}
			} else if (objNotifier instanceof ContextParam ||
					objNotifier instanceof org.eclipse.jst.javaee.core.ParamValue) {
				if (notification.getEventType() != Notification.REMOVING_ADAPTER) {
					locateProviders();
				}
			} else if (objNotifier instanceof ParamValue) {
				if (notification.getEventType() != Notification.REMOVING_ADAPTER) {
					locateProviders();
				}
			}
		}

		/**
		 * Called when a new ContextParam instance is added.
		 * 
		 * @param contextParam ContextParam instance.
		 */
		protected void contextParamAdded(EObject contextParam) {
			if (isConfigFilesContextParam(contextParam)) {
				locateProviders();
			}
			contextParam.eAdapters().add(this);
		}

		/**
		 * Called when a new ParamValue instance is added.
		 * 
		 * @param paramValue ParamValue instance.
		 */
		protected void paramValueAdded(EObject paramValue) {
			if (isConfigFilesParamValue(paramValue)) {
				locateProviders();
			}
			paramValue.eAdapters().add(this);
		}

		/**
		 * Called when a ContextParam instance is removed.
		 * 
		 * @param contextParam ContextParam instance.
		 */
		protected void contextParamRemoved(EObject contextParam) {
			if (isConfigFilesContextParam(contextParam)) {
				locateProviders();
			}
			contextParam.eAdapters().remove(this);
		}

		/**
		 * Called when a ParamValue instance is removed.
		 * 
		 * @param paramValue ParamValue instance.
		 */
		protected void paramValueRemoved(EObject paramValue) {
			if (isConfigFilesParamValue(paramValue)) {
				locateProviders();
			}
			paramValue.eAdapters().remove(this);
		}

		/**
		 * Tests if the passed ContextParam instance is the JSF CONFIG_FILES
		 * context parameter.
		 * 
		 * @param contextParam ContextParam instance.
		 * @return true if the passed ContextParam instance is the JSF
		 * CONFIG_FILES context parameter, else false
		 */
		protected boolean isConfigFilesContextParam(EObject contextParam) {
			boolean isConfigFiles = false;
			if (contextParam != null) {
				String name = null;
				if (contextParam instanceof ContextParam)
					name = ((ContextParam)contextParam).getParamName();
				else if (contextParam instanceof org.eclipse.jst.javaee.core.ParamValue)
					name = ((org.eclipse.jst.javaee.core.ParamValue)contextParam).getParamName();

				if (name != null &&
						(name.equals(JSFAppConfigUtils.CONFIG_FILES_CONTEXT_PARAM_NAME)
								|| name.equals(JSFAppConfigUtils.CONFIG_FILES_CONTEXT_PARAM_NAME_JAKARTA))) {
					isConfigFiles = true;
				}
			}
			return isConfigFiles;
		}

		/**
		 * Tests if the passed ParamValue instance is the JSF CONFIG_FILES
		 * context parameter.
		 * 
		 * @param paramVal as EObject ParamValue instance.
		 * @return true if the passed ParamValue instance is the JSF
		 * CONFIG_FILES context parameter, else false
		 */
		protected boolean isConfigFilesParamValue(EObject paramVal) {
			boolean isConfigFiles = false;
			if (paramVal != null) {
				String name = null;
				if (paramVal instanceof ParamValue)
					name = ((ParamValue)paramVal).getName();
				else if (paramVal instanceof org.eclipse.jst.javaee.core.ParamValue)
					name = ((org.eclipse.jst.javaee.core.ParamValue)paramVal).getParamName();
				
				if (name != null &&
						(name.equals(JSFAppConfigUtils.CONFIG_FILES_CONTEXT_PARAM_NAME)
								|| name.equals(JSFAppConfigUtils.CONFIG_FILES_CONTEXT_PARAM_NAME_JAKARTA))) {
					isConfigFiles = true;
				}
			}
			return isConfigFiles;
		}

	}
}
