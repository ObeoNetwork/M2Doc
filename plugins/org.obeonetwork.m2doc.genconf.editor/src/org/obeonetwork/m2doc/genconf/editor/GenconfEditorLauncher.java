/*******************************************************************************
 *  Copyright (c) 2018 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.genconf.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorLauncher;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.obeonetwork.m2doc.genconf.editor.wizard.NewGenerationWizard;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Opens the {@link NewGenerationWizard}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class GenconfEditorLauncher implements IEditorLauncher {

    /**
     * The height.
     */
    private static final int HEIGHT = 400;

    /**
     * The width.
     */
    private static final int WIDTH = 900;

    @Override
    public void open(IPath file) {
        final IWorkbench workbench = PlatformUI.getWorkbench();
        final Shell shell = workbench.getActiveWorkbenchWindow().getShell();
        final ISelection selection = workbench.getActiveWorkbenchWindow().getSelectionService().getSelection();

        if (selection instanceof IStructuredSelection) {
            openNewGenerationWizard(workbench, shell, (IStructuredSelection) selection);
        }
    }

    /**
     * Opens the {@link NewGenerationWizard}.
     * 
     * @param workbench
     *            the {@link IWorkbench}
     * @param shell
     *            the {@link Shell}
     * @param structuredSelection
     *            the {@link IStructuredSelection}
     */
    public static void openNewGenerationWizard(IWorkbench workbench, Shell shell,
            IStructuredSelection structuredSelection) {
        final NewGenerationWizard newWizard = new NewGenerationWizard();
        newWizard.setCanChangeTemplateFile(
                structuredSelection.getFirstElement() instanceof IFile && !M2DocUtils.DOCX_EXTENSION_FILE
                        .equals(((IFile) structuredSelection.getFirstElement()).getFullPath().getFileExtension()));
        newWizard.init(workbench, structuredSelection);
        WizardDialog dialog = new WizardDialog(shell, newWizard) {
            @Override
            public void create() {
                super.create();
                getShell().setText("Generation configuration");
                getShell().setMinimumSize(WIDTH, HEIGHT);
            }

            @Override
            public void showPage(IWizardPage page) {
                super.showPage(page);
                getShell().setText("Generation configuration");
            }
        };
        dialog.open();
    }

}
