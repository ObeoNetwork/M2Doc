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

import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.presentation.M2docconfEditorPlugin;

/**
 * Initialize configurations for documention generation.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class InitializeConfigurationsHandler extends AbstractHandler {
    /**
     * The constructor.
     */
    public InitializeConfigurationsHandler() {
    }

    /**
     * the command has been executed, so extract extract the needed information
     * from the application context.
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = HandlerUtil.getCurrentSelection(event);
        Shell shell = HandlerUtil.getActiveShell(event);
        if (selection instanceof IStructuredSelection) {
            Object selected = ((IStructuredSelection) selection).getFirstElement();
            if (selected instanceof IFile) {
                try {
                    Resource configurationModel = GenconfUtils.createConfigurationModel(
                            URI.createPlatformResourceURI(((IFile) selected).getFullPath().toString(), true));
                    MessageDialog.openInformation(shell, "M2Doc generation", "The configuration file '"
                        + configurationModel.getURI().toPlatformString(true) + "' is created.");
                } catch (FileNotFoundException e) {
                    M2docconfEditorPlugin.getPlugin().getLog().log(new Status(Status.ERROR,
                            M2docconfEditorPlugin.getPlugin().getSymbolicName(), Status.ERROR, e.getMessage(), e));
                    MessageDialog.openError(shell, "File not found, see the error log for details", e.getMessage());
                } catch (IOException e) {
                    M2docconfEditorPlugin.getPlugin().getLog().log(new Status(Status.ERROR,
                            M2docconfEditorPlugin.getPlugin().getSymbolicName(), Status.ERROR, e.getMessage(), e));
                    MessageDialog.openError(shell, "I/O problem, see the error log for details", e.getMessage());
                }

            } else {
                MessageDialog.openError(shell, "Bad selection",
                        "Configuration action can only be triggered on docx files.");
            }

        } else {
            MessageDialog.openError(shell, "Bad selection",
                    "Document generation action can only be triggered on docx files.");
        }
        return null;

    }

}
