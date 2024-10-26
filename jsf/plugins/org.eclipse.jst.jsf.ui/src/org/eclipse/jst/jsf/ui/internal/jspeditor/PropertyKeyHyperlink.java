package org.eclipse.jst.jsf.ui.internal.jspeditor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.jst.jsf.context.symbol.IComponentSymbol;
import org.eclipse.jst.jsf.context.symbol.IMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.IPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.internal.impl.ResourceBundleKeyMapSource;
import org.eclipse.jst.jsf.designtime.internal.symbols.ResourceBundleMapSource;
import org.eclipse.jst.jsf.ui.internal.JSFUiPlugin;
import org.eclipse.jst.jsf.ui.internal.Messages;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.texteditor.ITextEditor;

public class PropertyKeyHyperlink implements IHyperlink {
	private final IRegion _fRegion;
	private IComponentSymbol _rbSymbol;
	private IPropertySymbol _keySymbol;

    public PropertyKeyHyperlink(final IRegion region, final IComponentSymbol symbol) {
        _fRegion = region;
        _rbSymbol = symbol;
    }

    public PropertyKeyHyperlink(final IRegion region, final IPropertySymbol symbol) {
        _fRegion = region;
        _keySymbol = symbol;
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
			ResourceBundleMapSource resBundleMapSource = null;
			if (_rbSymbol != null) {
				resBundleMapSource = ((ResourceBundleMapSource)((IMapTypeDescriptor)_rbSymbol
						.getTypeDescriptor()).getMapSource());
			} else {
				ResourceBundleKeyMapSource keySymbolMapSource = (ResourceBundleKeyMapSource)
						((IMapTypeDescriptor)_keySymbol.getTypeDescriptor()).getMapSource();
				resBundleMapSource = (ResourceBundleMapSource) keySymbolMapSource.getOwner();
			}
			IFile rbBaseNameFile = resBundleMapSource.getBundleFile();
			IEditorPart editor = IDE.openEditor(page, rbBaseNameFile);
			if (_keySymbol != null) {
				byte[] content = rbBaseNameFile.readAllBytes();
				int offset = new String(content, rbBaseNameFile.getCharset()).indexOf(_keySymbol.getName());
				((ITextEditor) editor).selectAndReveal(offset, _fRegion.getLength());
			}
		} catch (Exception e) {
			JSFUiPlugin.log(IStatus.ERROR, "Error opening properties file: " + e.getMessage(), e); //$NON-NLS-1$
		}
	}
}
