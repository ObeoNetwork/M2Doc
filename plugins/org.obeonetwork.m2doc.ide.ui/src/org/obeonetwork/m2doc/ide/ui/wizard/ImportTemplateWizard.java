package org.obeonetwork.m2doc.ide.ui.wizard;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.obeonetwork.m2doc.ide.M2DocPlugin;
import org.obeonetwork.m2doc.ide.ui.Activator;
import org.obeonetwork.m2doc.services.TemplateRegistry;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Import templates from {@link TemplateRegistry}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ImportTemplateWizard extends Wizard implements IImportWizard {

    {
        // make sure org.obeonetwork.m2doc.ide is started
        M2DocPlugin.getDefault();
    }

    /**
     * The {@link SelectRegisteredTemplatePage}.
     */
    private SelectRegisteredTemplatePage importPage;

    /**
     * The {@link SelectContainerPage}.
     */
    private WizardNewFileCreationPage creationPage;

    /**
     * The {@link IStructuredSelection}.
     */
    private IStructuredSelection structuredSelection;

    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        setWindowTitle("Template import");
        this.structuredSelection = selection;
    }

    @Override
    public void addPages() {
        super.addPages();
        creationPage = new WizardNewFileCreationPage("Select destination Template file", structuredSelection);
        creationPage.setAllowExistingResources(true);
        creationPage.setFileExtension(M2DocUtils.DOCX_EXTENSION_FILE);

        importPage = new SelectRegisteredTemplatePage() {

            @Override
            protected void setSelectedTemplateURI(URI selectedTemplateURI) {
                super.setSelectedTemplateURI(selectedTemplateURI);
                creationPage.setFileName(selectedTemplateURI.lastSegment());
            }

        };

        addPage(importPage);
        addPage(creationPage);
    }

    @Override
    public boolean performFinish() {
        boolean res;
        final IContainer container;
        if (creationPage.getContainerFullPath().segmentCount() == 1) {
            container = ResourcesPlugin.getWorkspace().getRoot()
                    .getProject(creationPage.getContainerFullPath().lastSegment());
        } else {
            container = ResourcesPlugin.getWorkspace().getRoot().getFolder(creationPage.getContainerFullPath());
        }
        final URI templateURI = importPage.getSelectedTemplateURI();
        if (templateURI != null) {
            try (InputStream is = URIConverter.INSTANCE.createInputStream(templateURI)) {
                final IFile file = container.getFile(new Path(creationPage.getFileName()));
                if (!file.exists()) {
                    file.create(is, true, new NullProgressMonitor());
                } else if (openConfirmationDialog(file)) {
                    file.setContents(is, true, true, new NullProgressMonitor());
                }
                res = true;
            } catch (IOException e) {
                Activator.getDefault().getLog()
                        .log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "can't open input stream", e));
                res = false;
            } catch (CoreException e) {
                Activator.getDefault().getLog()
                        .log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "can't save file", e));
                res = false;
            }
        } else {
            res = false;
        }

        return res;
    }

    /**
     * Tells if the given {@link IFile} should be overritten.
     * 
     * @param file
     *            the {@link IFile} to prompt for
     * @return <code>true</code> if the given {@link IFile} should be overritten, <code>false</code> otherwise
     */
    private boolean openConfirmationDialog(IFile file) {
        MessageBox dialog = new MessageBox(getShell(), SWT.ICON_QUESTION | SWT.OK | SWT.CANCEL);
        dialog.setText(file.getName() + " already exists");
        dialog.setMessage("Do you want to overrite it?");

        final int res = dialog.open();

        return res == SWT.OK || res == SWT.YES;
    }

}
