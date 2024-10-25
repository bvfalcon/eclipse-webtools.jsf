package org.eclipse.jst.jsf.ui.internal.jspeditor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.jst.jsf.context.symbol.IComponentSymbol;
import org.eclipse.jst.jsf.context.symbol.IMapTypeDescriptor;
import org.eclipse.jst.jsf.designtime.internal.symbols.ResourceBundleMapSource;
import org.eclipse.jst.jsf.ui.internal.Messages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public class PropertyKeyHyperlink implements IHyperlink {
	private final IRegion _fRegion;
	private final IComponentSymbol _symbol;

	public PropertyKeyHyperlink(final IRegion region, final IComponentSymbol symbol) {
        _fRegion = region;
        _symbol = symbol;
    }

	@Override
	public IRegion getHyperlinkRegion() {
		return _fRegion;
	}

	@Override
	public String getHyperlinkText() {
		return Messages.Hyperlink_Open_PropertiesFile;
	}

	@Override
	public String getTypeLabel() {
		return null;
	}

	@Override
	public void open() {
		try {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IFile file = ((ResourceBundleMapSource)((IMapTypeDescriptor)_symbol.getTypeDescriptor()).getMapSource()).getBundleFile();
			IDE.openEditor(page, file);
		} catch (Exception e) {
			Platform.getLog(getClass()).error("Error opening properties file: " + e.getMessage(), e); //$NON-NLS-1$
		}
	}
}
