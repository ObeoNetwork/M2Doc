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

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.presentation.M2docconfEditorPlugin;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.ide.M2DocPlugin;
import org.obeonetwork.m2doc.parser.DocumentParserException;

/**
 * Initialize configurations for documention generation.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class ValidateHandler extends AbstractGenerationHandler {

    @Override
    protected void execute(ExecutionEvent event, Generation generation) {
        final Shell shell = HandlerUtil.getActiveShell(event);
        try {
            boolean inError = GenconfUtils.validate(generation, M2DocPlugin.getClassProvider());
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
            MessageDialog.openError(shell, "Template parsing problem, see the error log for details", e.getMessage());
        } catch (DocumentGenerationException e) {
            M2docconfEditorPlugin.getPlugin().getLog().log(new Status(Status.ERROR,
                    M2docconfEditorPlugin.getPlugin().getSymbolicName(), Status.ERROR, e.getMessage(), e));
            MessageDialog.openError(shell, "Generation problem, see the error log for details", e.getMessage());
        }
    }

}
