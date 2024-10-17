/*******************************************************************************
 *  Copyright (c) 2016, 2024 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.ide.ui.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.obeonetwork.m2doc.ide.ui.wizard.TemplateCustomPropertiesWizard;

/**
 * Edite template custom properties.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class EditTemplatePropertiesHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        final Shell shell = HandlerUtil.getActiveShell(event);
        final ISelection selection = HandlerUtil.getCurrentSelection(event);
        if (!selection.isEmpty() && selection instanceof IStructuredSelection) {
            final IFile templateFile = (IFile) ((IStructuredSelection) selection).getFirstElement();
            final URI templateURI = URI.createPlatformResourceURI(templateFile.getFullPath().toString(), true);

            WizardDialog dialog = new WizardDialog(shell, new TemplateCustomPropertiesWizard(templateURI)) {

                @Override
                public void create() {
                    super.create();
                    getShell().setText("Template properties");
                }

                @Override
                public void showPage(IWizardPage page) {
                    super.showPage(page);
                    getShell().setText("Template properties");
                }
            };
            dialog.open();
        }

        return null;
    }

}
