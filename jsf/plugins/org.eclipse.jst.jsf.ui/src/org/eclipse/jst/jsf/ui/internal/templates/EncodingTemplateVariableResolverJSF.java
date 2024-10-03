package org.eclipse.jst.jsf.ui.internal.templates;

import org.eclipse.jface.text.templates.SimpleTemplateVariableResolver;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.ui.internal.Messages;
import org.eclipse.wst.sse.core.internal.encoding.CommonEncodingPreferenceNames;

public class EncodingTemplateVariableResolverJSF extends SimpleTemplateVariableResolver {
	private static final String ENCODING_TYPE = getEncodingType();

	private static String getEncodingType() {
		return "encoding"; //$NON-NLS-1$
	}

	/**
	 * Creates a new encoding variable
	 */
	public EncodingTemplateVariableResolverJSF() {
		super(ENCODING_TYPE, Messages.Creating_files_encoding);
	}

	protected String resolve(TemplateContext context) {
		return JSFCorePlugin.getDefault().getPluginPreferences().getString(CommonEncodingPreferenceNames.OUTPUT_CODESET);
	}
}
