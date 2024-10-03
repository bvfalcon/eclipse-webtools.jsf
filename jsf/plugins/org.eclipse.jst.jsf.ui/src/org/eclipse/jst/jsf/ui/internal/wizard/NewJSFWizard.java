package org.eclipse.jst.jsf.ui.internal.wizard;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.ui.internal.JSFUIPlugin;
import org.eclipse.jst.jsf.ui.internal.Messages;
import org.eclipse.jst.jsp.ui.internal.editor.JSPEditorPluginImageHelper;
import org.eclipse.jst.jsp.ui.internal.editor.JSPEditorPluginImages;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.wst.sse.core.internal.encoding.CommonEncodingPreferenceNames;
import org.eclipse.wst.sse.core.utils.StringUtils;

public class NewJSFWizard extends Wizard implements INewWizard {
	private NewJSFFileWizardPage fNewFilePage;
	private NewJSFTemplatesWizardPage fNewFileTemplatesPage;
	private IStructuredSelection fSelection;
	private Display fDisplay;

	private boolean fShouldOpenEditorOnFinish = true;
	
	public void createPageControls(Composite pageContainer) {
		fDisplay = pageContainer.getDisplay();
		super.createPageControls(pageContainer);
	}

	// https://bugs.eclipse.org/bugs/show_bug.cgi?id=248424
	public void setOpenEditorOnFinish(boolean openEditor) {
		this.fShouldOpenEditorOnFinish = openEditor;
	}
	
	public void addPages() {
		fNewFilePage = new NewJSFFileWizardPage("JSFWizardNewFileCreationPage", new StructuredSelection(IDE.computeSelectedResources(fSelection))); //$NON-NLS-1$ 
		fNewFilePage.setTitle(Messages._UI_WIZARD_NEW_HEADING);
		fNewFilePage.setDescription(Messages._UI_WIZARD_NEW_DESCRIPTION);
		addPage(fNewFilePage);

		fNewFileTemplatesPage = new NewJSFTemplatesWizardPage();
		addPage(fNewFileTemplatesPage);
	}

	private String applyLineDelimiter(IFile file, String text) {
		String lineDelimiter = Platform.getPreferencesService().getString(Platform.PI_RUNTIME, Platform.PREF_LINE_SEPARATOR, System.getProperty("line.separator"), new IScopeContext[] {new ProjectScope(file.getProject()), new InstanceScope() });//$NON-NLS-1$
		String convertedText = StringUtils.replace(text, "\r\n", "\n"); //$NON-NLS-1$ //$NON-NLS-2$
		convertedText = StringUtils.replace(convertedText, "\r", "\n"); //$NON-NLS-1$ //$NON-NLS-2$
		convertedText = StringUtils.replace(convertedText, "\n", lineDelimiter); //$NON-NLS-1$
		return convertedText;
	}

	public void init(IWorkbench aWorkbench, IStructuredSelection aSelection) {
		fSelection = aSelection;
		setWindowTitle(Messages._UI_WIZARD_NEW_TITLE);

		ImageDescriptor descriptor = JSPEditorPluginImageHelper.getInstance().getImageDescriptor(JSPEditorPluginImages.IMG_OBJ_WIZBAN_NEWJSPFILE);
		setDefaultPageImageDescriptor(descriptor);
	}

	private void openEditor(final IFile file) {
		if (file != null) {
			fDisplay.asyncExec(new Runnable() {
				public void run() {
					if (!PlatformUI.isWorkbenchRunning())
						return;
					try {
						IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
						IDE.openEditor(page, file, true);
					}
					catch (PartInitException e) {
						JSFUIPlugin.log(IStatus.WARNING, e.getMessage(), e);
					}
				}
			});
		}
	}

	public boolean performFinish() {
		boolean performedOK = false;

		// save user options for next use
		fNewFileTemplatesPage.saveLastSavedPreferences();

		// no file extension specified so add default extension
		String fileName = fNewFilePage.getFileName();
		if (fileName.lastIndexOf('.') == -1) {
			String newFileName = fNewFilePage.addDefaultExtension(fileName);
			fNewFilePage.setFileName(newFileName);
		}

		// create a new empty file
		IFile file = fNewFilePage.createNewFile();

		// if there was problem with creating file, it will be null, so make
		// sure to check
		if (file != null) {
			if (!file.isLinked()) {
				// put template contents into file
				String templateString = fNewFileTemplatesPage.getTemplateString();
				if (templateString != null) {
					templateString = applyLineDelimiter(file, templateString);
					// determine the encoding for the new file
					Preferences preference = JSFCorePlugin.getDefault().getPluginPreferences();
					String charSet = preference.getString(CommonEncodingPreferenceNames.OUTPUT_CODESET);
	
					try {
						ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
						OutputStreamWriter outputStreamWriter = null;
						if (charSet == null || charSet.trim().equals("")) { //$NON-NLS-1$
							// just use default encoding
							outputStreamWriter = new OutputStreamWriter(outputStream);
						}
						else {
							outputStreamWriter = new OutputStreamWriter(outputStream, charSet);
						}
						outputStreamWriter.write(templateString);
						outputStreamWriter.flush();
						outputStreamWriter.close();
						ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
						file.setContents(inputStream, true, false, null);
						inputStream.close();
					}
					catch (Exception e) {
						JSFUIPlugin.log(IStatus.WARNING, "Could not create contents for new JSP file", e); //$NON-NLS-1$
					}
				}
			}
			// open the file in editor
			if (fShouldOpenEditorOnFinish)
				openEditor(file);

			// everything's fine
			performedOK = true;
		}
		return performedOK;
	}
}
