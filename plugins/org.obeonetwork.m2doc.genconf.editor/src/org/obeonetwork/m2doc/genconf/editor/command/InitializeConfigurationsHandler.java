/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.genconf.editor.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.obeonetwork.m2doc.genconf.editor.wizard.NewGenerationWizard;

/**
 * Initialize configurations for documention generation.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class InitializeConfigurationsHandler extends AbstractHandler {

    /**
     * The height.
     */
    private static final int HEIGHT = 400;

    /**
     * The width.
     */
    private static final int WIDTH = 900;

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        final Shell shell = HandlerUtil.getActiveShell(event);

        final NewGenerationWizard newWizard = new NewGenerationWizard();
        final ISelection selection = HandlerUtil.getCurrentSelection(event);
        final IWorkbenchWindow workbenchWindow = HandlerUtil.getActiveWorkbenchWindow(event);
        final IWorkbench workbench;
        if (workbenchWindow != null) {
            workbench = workbenchWindow.getWorkbench();
        } else {
            workbench = null;
        }
        newWizard.init(workbench, (IStructuredSelection) selection);
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

        return null;
    }

}
