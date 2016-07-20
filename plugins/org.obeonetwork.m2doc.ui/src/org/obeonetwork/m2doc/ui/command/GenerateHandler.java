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
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.ServiceUtils;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.obeonetwork.m2doc.api.GenconfToDocumentGenerator;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.ui.Activator;

/**
 * Generate docx from docx template.
 * 
 * @author <a href="mailto:romain.guider@obeo.fr">Romain Guider</a>
 */
public class GenerateHandler extends AbstractHandler {
    /**
     * The constructor.
     */
    public GenerateHandler() {
    }

    /**
     * the command has been executed, so extract extract the needed information
     * from the application context.
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
                    GenconfToDocumentGenerator generator = new GenconfToDocumentGenerator();
                    List<IFile> generatedfiles = generator.generate(generation);
                    if (generatedfiles.size() == 1) {
                        MessageDialog.openConfirm(shell, "M2Doc generation",
                                "The document '" + generatedfiles.get(0).getLocation().toString() + "' is generated.");
                    } else if (generatedfiles.size() == 2) {
                        MessageDialog.openConfirm(shell, "M2Doc generation",
                                "The document '" + generatedfiles.get(0).getLocation().toString()
                                    + "' is generated. \n\n The template file contains validation errors, please read '"
                                    + generatedfiles.get(1).getLocation().toString() + "'.");
                    }

                } catch (IOException e) {
                    Activator.getDefault().getLog()
                            .log(new Status(Status.ERROR, Activator.PLUGIN_ID, Status.ERROR, e.getMessage(), e));
                    MessageDialog.openError(shell, "I/O problem, see the error log for details", e.getMessage());
                } catch (DocumentParserException e) {
                    Activator.getDefault().getLog()
                            .log(new Status(Status.ERROR, Activator.PLUGIN_ID, Status.ERROR, e.getMessage(), e));
                    MessageDialog.openError(shell, "Template parsing problem. See the error log for details",
                            e.getMessage());
                } catch (DocumentGenerationException e) {
                    Activator.getDefault().getLog()
                            .log(new Status(Status.ERROR, Activator.PLUGIN_ID, Status.ERROR, e.getMessage(), e));
                    MessageDialog.openError(shell, "Generation problem. See the error log for details", e.getMessage());
                } catch (RuntimeException e) {// do not let exception leak out.
                    String msg = e.getMessage();
                    Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, Status.ERROR,
                            "M2Doc : technical error" + (msg == null ? "." : " : " + msg), e));
                    MessageDialog.openError(shell, "Generation problem. See the error log for details",
                            "A technical error occured. Please log a bug (see the error log for details)");
                }
            }
        } else {
            MessageDialog.openError(shell, "Bad selection",
                    "Document generation action can only be triggered on Generation object.");
        }
        return null;

    }

}

