/*******************************************************************************
 * Copyright (c) 2006, 2011 Sybase, Inc. and others.
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
 *     Oracle - move to pluggable EL resolving
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.preview;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import jakarta.el.ELException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.jsf.common.ui.internal.utils.ResourceUtils;
import org.eclipse.jst.jsf.core.internal.tld.LoadBundleUtil;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.jsp.core.pagevar.IPageVariablesProvider;
import org.eclipse.jst.pagedesigner.jsp.core.pagevar.IVariableInfo;
import org.w3c.dom.Element;

/**
 * This is a static class. But it has "session" concept in it. To make it static
 * is only to simplify its use.
 * 
 * @author mengbo, ian.trimble@oracle.com
 */
public class PageExpressionContext {
	private static final Logger _log = PDPlugin.getLogger(PageExpressionContext.class);

	static PageExpressionContext _current;

	List _pageVarProviders = new ArrayList();

	private IProject _prj;

	/**
	 * @param prj
	 */
	public PageExpressionContext(IProject prj) {
		_prj = prj;
	}

	/**
	 * reset the context.
	 */
	public static void reset() {
		_current = null;
	}

	/**
	 * Initialize the current context
	 * @param prj
	 */
	public static void initialize(IProject prj) {
		_current = new PageExpressionContext(prj);
	}

	/**
	 * @return the current context
	 */
	public static PageExpressionContext getCurrent() {
		return _current;
	}

	/**
	 * @param provider
	 */
	public void pushPageVarProvider(IPageVariablesProvider provider) {
		_pageVarProviders.add(provider);
	}

	/**
	 * @param provider
	 */
	public void popPageVarProvider(IPageVariablesProvider provider) {
		try {
			_pageVarProviders.remove(_pageVarProviders.size() - 1);
		} catch (Exception ex) {
			_log.info("PageExpressionContext.Info.0", ex); //$NON-NLS-1$
		}
	}

	/**
	 * This method is for design time expression evaluation.
	 * 
	 * @param expression
	 * @param expectedClass 
	 * @param options Current Element is passed with a key of "ELEMENT"
	 * @return the result of evaluating the expression
	 * @throws ELException 
	 */
	public Object evaluateExpression(String expression, Class expectedClass, Map options)
			throws ELException {
		//Bug 319317 - Third-party plug-in providing jakarta.servlet.jsp.el version 2.1 or greater breaks WPE preview
		String ret = expression;
		if (options != null) {
			Object objElement = options.get("ELEMENT"); //$NON-NLS-1$
			if (objElement instanceof Element) {
				ret = ELValueResolver.resolve((Element)objElement, expression);
			}
		}
		return ret;
	}

	/**
	 * Gets an Object associated with a page variable.
	 * @param varName Page variable name.
	 * @return The Object associated with the named page variable, or null if the Object cannot be
	 * located.
	 */
	public Object getPageVariable(String varName) {
		Object ret = null;
		// reverse order.
		for (int k = _pageVarProviders.size() - 1; k >= 0; k--) {
			IPageVariablesProvider _pageVars = (IPageVariablesProvider) _pageVarProviders.get(k);
			if (_pageVars != null) {
				IVariableInfo[] vars = _pageVars.getBeanInfos();
				if (vars != null) {
					for (int i = 0; i < vars.length; i++) {
						if (varName.equals(vars[i].getName())) {
							if (vars[i].getMode() == IVariableInfo.RESOURCEBUNDLE) {
								String resourceName = vars[i].getTypeInfoString();
								IStorage storage = null;
								try {
									storage = LoadBundleUtil.getLoadBundleResource(_prj, resourceName);
								} catch (CoreException cex) {
									_log.info("PageExpressionContext.Info.0", cex); //$NON-NLS-1$
								}
								if (storage != null) {
									InputStream input = null;
									try {
										input = new BufferedInputStream(storage.getContents());
										Properties properties = new Properties();
										properties.load(input);
										ret = properties;
									} catch (Exception ignored) {
										//ignore - we'll return null
									} finally {
										ResourceUtils.ensureClosed(input);
									}
								}
							}
						}
					}
				}
			}
		}
		return ret;
	}



	static class ELValueResolver {

		static List<IELValueResolver> elValueResolvers;

		public static String resolve(final Element element, final String elExpression) {
			String value = elExpression;
			if (elValueResolvers == null) {
				readELValueResolvers();
			}
			for (IELValueResolver elValueResolver: elValueResolvers) {
				value = elValueResolver.resolve(element, value);
			}
			return value;
		}

		private static void readELValueResolvers() {
			elValueResolvers = new ArrayList<IELValueResolver>();
			final IExtensionPoint pdExtPt =
				Platform.getExtensionRegistry().getExtensionPoint(
						PDPlugin.getPluginId(), IJMTConstants.EXTENSION_POINT_PAGEDESIGNER);
			final IExtension[] extensions = pdExtPt.getExtensions();
			for (final IExtension extension: extensions) {
				final IConfigurationElement[] configElements = extension.getConfigurationElements();
				for (final IConfigurationElement configElement: configElements) {
					if (configElement.getName().equals("elValueResolver")) { //$NON-NLS-1$
						try {
							final Object objValueResolver = configElement.createExecutableExtension("class"); //$NON-NLS-1$
							if (objValueResolver instanceof IELValueResolver) {
								if (configElement.getContributor().getName().startsWith("org.eclipse.jst")) { //$NON-NLS-1$
									//add to end (give precedence to contributor-provided resolvers)
									elValueResolvers.add((IELValueResolver)objValueResolver);
								} else {
									//add at beginning (give precedence to contributor-provided resolvers)
									elValueResolvers.add(0, (IELValueResolver)objValueResolver);
								}
							}
						} catch(CoreException ce) {
							PDPlugin.log("Error reading extensions for: " + configElement.toString(), ce); //$NON-NLS-1$
						}
					}
				}
			}
		}

	}

}
