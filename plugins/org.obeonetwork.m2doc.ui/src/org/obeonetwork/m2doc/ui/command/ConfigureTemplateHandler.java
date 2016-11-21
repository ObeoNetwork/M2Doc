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
package org.obeonetwork.m2doc.ui.command;

import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.obeonetwork.m2doc.M2DocPlugin;
import org.obeonetwork.m2doc.tplconf.TemplateConfig;
import org.obeonetwork.m2doc.ui.Activator;
import org.obeonetwork.m2doc.ui.dialog.ConfigureTemplateDialog;
import org.obeonetwork.m2doc.util.TemplateConfigUtil;

/**
 * Handler that helps users configure an existing M2Doc template by declaring the template meta-model URIs and variables.
 * These meta-data are stored in the template's properties.
 * 
 * @author ldelaigue
 */
public class ConfigureTemplateHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = HandlerUtil.getCurrentSelection(event);
        if (selection instanceof IStructuredSelection) {
            Object selected = ((IStructuredSelection) selection).getFirstElement();
            if (selected instanceof IFile
                && M2DocPlugin.DOCX_EXTENSION_FILE.equals(((IFile) selected).getFileExtension())) {
                try {
                    final IFile templateFile = (IFile) selected;
                    final TemplateConfig config = TemplateConfigUtil.load(templateFile);
                    Shell shell = HandlerUtil.getActiveShell(event);
                    ConfigureTemplateDialog dlg = new ConfigureTemplateDialog(shell, config);
                    if (dlg.open() == Dialog.OK) {
                        WorkspaceJob job = new WorkspaceJob("Update template") {
                            @Override
                            public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
                                saveTemplate(config, templateFile);
                                templateFile.refreshLocal(IResource.DEPTH_ONE, monitor);
                                return Status.OK_STATUS;
                            }
                        };
                        job.schedule();
                    }
                } catch (IOException e) {
                    Shell shell = HandlerUtil.getActiveShell(event);
                    MessageDialog.openError(shell, "I/O Exception", e.getMessage());
                }
            }
        }
        return null;
    }

    protected void saveTemplate(TemplateConfig config, IFile templateFile) {
        try {
            TemplateConfigUtil.save(config, templateFile);
        } catch (IOException e) {
            Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e));
        }
    }

}
