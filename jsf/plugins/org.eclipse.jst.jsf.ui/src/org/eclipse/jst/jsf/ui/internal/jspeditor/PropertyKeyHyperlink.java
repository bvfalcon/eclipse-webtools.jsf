package org.eclipse.jst.jsf.ui.internal.jspeditor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
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
				int offset = getOffset(rbBaseNameFile, _keySymbol.getName());
				((ITextEditor) editor).selectAndReveal(offset, _fRegion.getLength());
			}
		} catch (Exception e) {
			JSFUiPlugin.log(IStatus.ERROR, "Error opening properties file: " + e.getMessage(), e); //$NON-NLS-1$
		}
	}

	private int getOffset(IFile propFile, String key) throws IOException, CoreException {
		int eolLength = getLineEndingLength(propFile);
		InputStream in = null;
		try {
			in = propFile.getContents();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, propFile.getCharset()));
			int offset = 0;
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith(key) && line.substring(line.indexOf(key) + key.length()).trim().startsWith("=")) {
					return offset;
				}
				offset += (line.length() + eolLength);
			}
			return -1;
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	private int getLineEndingLength(IFile file) throws IOException, CoreException {
		final byte CR = 0x0D;
		final byte LF = 0x0A;
		InputStream in = null;
		try {
			in = file.getContents();
			byte[] bytes = new byte[256];
			int bytesRead = in.read(bytes);
			while (bytesRead != -1) {
				for (int i = 0; i < bytes.length-2; i++) {
					if (bytes[i] == CR && bytes[i+1] == LF) {
						return 2;
					}
					if (bytes[i] == CR || bytes[i] == LF) {
						return 1;
					}
				}
				bytesRead = in.read(bytes);
			}
			return System.lineSeparator().length();
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
}
