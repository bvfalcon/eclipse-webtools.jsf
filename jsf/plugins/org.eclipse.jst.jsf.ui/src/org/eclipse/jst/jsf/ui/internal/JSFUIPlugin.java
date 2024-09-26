/*******************************************************************************
 * Copyright (c) 2005, 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Gerry Kessler - initial API and implementation
 *    Ian Trimble - added logging methods
 *******************************************************************************/ 
package org.eclipse.jst.jsf.ui.internal;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.jst.jsf.ui.internal.templates.TemplateContextTypeIdsJSF;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.editors.text.templates.ContributionContextTypeRegistry;
import org.eclipse.ui.editors.text.templates.ContributionTemplateStore;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * JSF UI plugin.
 * 
 * @author Gerry Kessler - Oracle, Ian Trimble - Oracle
 */
public class JSFUIPlugin extends AbstractUIPlugin {

	/**
	 * The plugin id
	 */
	public static final String PLUGIN_ID = "org.eclipse.jst.jsf.ui"; //$NON-NLS-1$
	//The shared instance.
	private static JSFUIPlugin plugin;

	/**
	 * The template store for the jsf editor. 
	 */
	private TemplateStore fTemplateStore;
	
	/** 
	 * The template context type registry for the jsf editor. 
	 */
	private ContributionContextTypeRegistry fContextTypeRegistry;

	/**
	 * The constructor.
	 */
	public JSFUIPlugin() {
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 * @return the default plugin instance
	 */
	public static JSFUIPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path.
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		path = "icons/" + path; //$NON-NLS-1$
		return AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.jst.jsf.ui", path); //$NON-NLS-1$
	}

	   /**
     * Returns a shared image for the given name
     * <p>
     * Note: Images returned from this method will be automitically disposed of
     * when this plug-in shuts down. Callers must not dispose of these images
     * themselves.
     * </p>
     * 
     * @param name
     *            the image name found in /icons (with extension)
     * @return the image, null on error or not found.
     */
    public Image getImage(String name) {
        if (name == null) {
            return null;
        }

        ImageRegistry images = getImageRegistry();
        Image image = images.get(name);
        if (image == null) {
            try {
                final URL pluginBase= getBundle().getEntry("/");; //$NON-NLS-1$
                ImageDescriptor id = ImageDescriptor.createFromURL(new URL(
                        pluginBase, "icons/" + name)); //$NON-NLS-1$
                images.put(name, id);

                image = images.get(name);
            } catch (MalformedURLException ee) {
                // log.CommonPlugin.image.error=Image {0} not found.
                //.error("log.msg", "log.CommonPlugin.image.error", name, ee);
                log(IStatus.ERROR, "Loading image", ee); //$NON-NLS-1$
            }
        }
        return image;
    }
	/**
	 * @return the plugin id
	 */
	public String getPluginID() {
		return PLUGIN_ID;
	}

	/**
	 * Logs using the default ILog implementation provided by getLog().
	 * 
	 * @param severity Severity (IStatus constant) of log entry
	 * @param message Human-readable message describing log entry
	 * @param ex Throwable instance (can be null)
	 */
	public static void log(int severity, String message, Throwable ex) {
		getDefault().getLog().log(new Status(severity, PLUGIN_ID, IStatus.OK, message, ex));
	}

	/**
	 * Logs using the default ILog implementation provided by getLog().
	 * 
	 * @param severity Severity (IStatus constant) of log entry
	 * @param message Human-readable message describing log entry
	 */
	public static void log(int severity, String message) {
		log(severity, message, null);
	}

	/**
	 * Returns the template store for the jsf editor templates.
	 * 
	 * @return the template store for the jsf editor templates
	 */
	public TemplateStore getTemplateStore() {
		if (fTemplateStore == null) {
			fTemplateStore= new ContributionTemplateStore(getTemplateContextRegistry(), getPreferenceStore(), "org.eclipse.wst.sse.ui.custom_template"); //$NON-NLS-1$

			try {
				fTemplateStore.load();
			} catch (IOException e) {
				log(IStatus.ERROR, e.getMessage(), e);
			}
		}		
		return fTemplateStore;
	}

	/**
	 * Returns the template context type registry for the jsp plugin.
	 * 
	 * @return the template context type registry for the jsp plugin
	 */
	public ContributionContextTypeRegistry getTemplateContextRegistry() {
		if (fContextTypeRegistry == null) {
			ContributionContextTypeRegistry registry = new ContributionContextTypeRegistry();
			registry.addContextType(TemplateContextTypeIdsJSF.ALL);
			registry.addContextType(TemplateContextTypeIdsJSF.NEW);
			registry.addContextType(TemplateContextTypeIdsJSF.ATTRIBUTE);
			registry.addContextType(TemplateContextTypeIdsJSF.ATTRIBUTE_VALUE);
			
			fContextTypeRegistry= registry;
		}

		return fContextTypeRegistry;
	}
}
