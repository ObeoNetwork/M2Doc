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
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.presentation.M2docconfEditorPlugin;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.parser.DocumentParserException;

/**
 * Initialize configurations for documention generation.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class ValidateHandler extends AbstractHandler {
    /**
     * The constructor.
     */
    public ValidateHandler() {
    }

    /**
     * The command has been executed, so extract extract the needed information
     * from the application context.
     * 
     * @param event
     *            the {@link ExecutionEvent}
     * @throws ExecutionException
     *             if something went wrong
     * @return <code>null</code>
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = HandlerUtil.getCurrentSelection(event);
        Shell shell = HandlerUtil.getActiveShell(event);
        if (selection instanceof IStructuredSelection) {
            Object selected = ((IStructuredSelection) selection).getFirstElement();
            Generation generation = null;
            if (selected instanceof Generation) {
                generation = (Generation) selected;
            } else {
                MessageDialog.openError(shell, "Bad selection",
                        "Document generation action can only be triggered on Generation object.");
                return null;
            }

            if (generation != null) {
                try {
                    boolean inError = GenconfUtils.validate(generation);
                    if (!inError) {
                        MessageDialog.openInformation(shell, "M2Doc validation",
                                "The template validation has been performed successfully.");
                    } else {
                        MessageDialog.openInformation(shell, "M2Doc validation",
                                "Error(s) detected during validation. A log file has been generated next to the template file.");
                    }
                } catch (FileNotFoundException e) {
                    M2docconfEditorPlugin.getPlugin().getLog().log(new Status(Status.ERROR,
                            M2docconfEditorPlugin.getPlugin().getSymbolicName(), Status.ERROR, e.getMessage(), e));
                    MessageDialog.openError(shell, "File not found, see the error log for details", e.getMessage());
                } catch (IOException e) {
                    M2docconfEditorPlugin.getPlugin().getLog().log(new Status(Status.ERROR,
                            M2docconfEditorPlugin.getPlugin().getSymbolicName(), Status.ERROR, e.getMessage(), e));
                    MessageDialog.openError(shell, "I/O problem, see the error log for details", e.getMessage());
                } catch (DocumentParserException e) {
                    M2docconfEditorPlugin.getPlugin().getLog().log(new Status(Status.ERROR,
                            M2docconfEditorPlugin.getPlugin().getSymbolicName(), Status.ERROR, e.getMessage(), e));
                    MessageDialog.openError(shell, "Template parsing problem, see the error log for details",
                            e.getMessage());
                } catch (DocumentGenerationException e) {
                    M2docconfEditorPlugin.getPlugin().getLog().log(new Status(Status.ERROR,
                            M2docconfEditorPlugin.getPlugin().getSymbolicName(), Status.ERROR, e.getMessage(), e));
                    MessageDialog.openError(shell, "Generation problem, see the error log for details", e.getMessage());
                }

            }

        } else {
            MessageDialog.openError(shell, "Bad selection",
                    "Document validation action can only be triggered on generation files.");
        }
        return null;

    }

}
