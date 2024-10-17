/*******************************************************************************
 *  Copyright (c) 2018, 2024 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.ide.ui.wizard;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.obeonetwork.m2doc.ide.M2DocPlugin;
import org.obeonetwork.m2doc.ide.ui.M2DocUIPlugin;
import org.obeonetwork.m2doc.services.TemplateRegistry;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Import templates from {@link TemplateRegistry}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ImportTemplateWizard extends Wizard implements IImportWizard {

    /**
     * Open {@link MessageBox}.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private final class OpenMessageBoxRunnable implements Runnable {

        /**
         * The file name.
         */
        private final String fileName;

        /**
         * The result of the {@link MessageBox}.
         */
        private int result;

        /**
         * Constructor.
         * 
         * @param fileName
         *            the file name
         */
        private OpenMessageBoxRunnable(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            final MessageBox dialog = new MessageBox(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                    SWT.ICON_QUESTION | SWT.OK | SWT.CANCEL);
            dialog.setText(fileName + " already exists");
            dialog.setMessage("Do you want to overrite it?");
            result = dialog.open();
        }

        /**
         * Gets the result of the {@link MessageBox}.
         * 
         * @return the result of the {@link MessageBox}
         */
        public int getResult() {
            return result;
        }

    }

    /**
     * Import template job.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private final class ImportTemplateJob extends WorkspaceJob {

        /**
         * The container full path.
         */
        private IPath containerFullPath;

        /**
         * The file name.
         */
        private String fileName;

        /**
         * Constructor.
         * 
         * @param containerFullPath
         *            the container full path
         * @param fileName
         *            the file name
         */
        private ImportTemplateJob(IPath containerFullPath, String fileName) {
            super("Importing template " + URI.decode(importPage.getSelectedTemplateURI().toString()));
            this.containerFullPath = containerFullPath;
            this.fileName = fileName;
        }

        @Override
        public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
            Status status = new Status(IStatus.OK, M2DocUIPlugin.PLUGIN_ID, "Template imported succesfully");

            final IContainer container;
            if (containerFullPath.segmentCount() == 1) {
                container = ResourcesPlugin.getWorkspace().getRoot().getProject(containerFullPath.lastSegment());
            } else {
                container = ResourcesPlugin.getWorkspace().getRoot().getFolder(containerFullPath);
            }
            final URI templateURI = importPage.getSelectedTemplateURI();
            if (templateURI != null) {
                try (InputStream is = URIConverter.INSTANCE.createInputStream(templateURI)) {
                    final IFile file = container.getFile(new Path(fileName));
                    if (!file.exists()) {
                        file.create(is, true, new NullProgressMonitor());
                    } else if (openConfirmationDialog(file)) {
                        file.setContents(is, true, true, new NullProgressMonitor());
                    }
                } catch (IOException e) {
                    status = new Status(IStatus.ERROR, M2DocUIPlugin.PLUGIN_ID, "can't open input stream", e);
                    M2DocUIPlugin.getDefault().getLog().log(status);
                } catch (CoreException e) {
                    status = new Status(IStatus.ERROR, M2DocUIPlugin.PLUGIN_ID, "can't save file", e);
                    M2DocUIPlugin.getDefault().getLog().log(status);
                }
            } else {
                status = new Status(IStatus.ERROR, M2DocUIPlugin.PLUGIN_ID,
                        "Null template URI. Check your extension point.");
            }

            return status;
        }

        /**
         * Tells if the given {@link IFile} should be overritten.
         * 
         * @param file
         *            the {@link IFile} to prompt for
         * @return <code>true</code> if the given {@link IFile} should be overritten, <code>false</code> otherwise
         */
        private boolean openConfirmationDialog(IFile file) {
            final OpenMessageBoxRunnable openDialogRunnable = new OpenMessageBoxRunnable(file.getName());
            Display.getDefault().syncExec(openDialogRunnable);

            final int res = openDialogRunnable.getResult();
            return res == SWT.OK || res == SWT.YES;
        }

    }

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
        final Job job = new ImportTemplateJob(creationPage.getContainerFullPath(), creationPage.getFileName());
        job.setRule(ResourcesPlugin.getWorkspace().getRoot());
        job.schedule();

        return true;
    }

}
