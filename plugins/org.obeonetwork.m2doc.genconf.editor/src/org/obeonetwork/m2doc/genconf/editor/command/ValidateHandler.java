/*******************************************************************************
 *  Copyright (c) 2016, 2025 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.genconf.editor.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.acceleo.query.AQLUtils;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.presentation.M2docconfEditorPlugin;
import org.obeonetwork.m2doc.ide.M2DocPlugin;

/**
 * Initialize configurations for documention generation.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class ValidateHandler extends AbstractGenerationHandler {

    /**
     * Validate job.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private final class ValidateJob extends WorkspaceJob {

        /**
         * The validation dialog title.
         */
        private static final String M2_DOC_VALIDATION = "M2Doc validation";

        /**
         * The {@link Generation} to validate.
         */
        private final Generation generation;

        /**
         * The {@link Shell} for error reporting.
         */
        private final Shell shell;

        /**
         * Consturctor.
         * 
         * @param generation
         *            the {@link Generation} to validate
         * @param shell
         *            the {@link Shell} for error reporting
         */
        private ValidateJob(Generation generation, Shell shell) {
            super("Validating " + URI.decode(generation.eResource().getURI().toString()));
            this.generation = generation;
            this.shell = shell;
        }

        @Override
        public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
            Status status = new Status(IStatus.OK, M2docconfEditorPlugin.getPlugin().getSymbolicName(),
                    "Generation configuration succesfully saved");

            try {
                final boolean validate = checkM2DocVersion(shell, M2_DOC_VALIDATION, generation);
                if (validate) {
                    final List<Exception> exceptions = new ArrayList<Exception>();
                    final Map<String, String> options = GenconfUtils.getOptions(generation);
                    final ResourceSet resourceSetForModel = AQLUtils.createResourceSetForModels(exceptions, generation,
                            new ResourceSetImpl(), options);
                    try {
                        boolean inError = GenconfUtils.validate(generation, resourceSetForModel, options, exceptions,
                                M2DocPlugin.getClassProvider(), BasicMonitor.toMonitor(monitor));
                        if (!inError) {
                            Display.getDefault().asyncExec(new Runnable() {
                                @Override
                                public void run() {
                                    MessageDialog.openInformation(shell, M2_DOC_VALIDATION,
                                            "The template validation has been performed successfully.");
                                }
                            });
                        } else {
                            Display.getDefault().asyncExec(new Runnable() {
                                @Override
                                public void run() {
                                    MessageDialog.openInformation(shell, M2_DOC_VALIDATION,
                                            "Error(s) detected during validation. A log file has been generated next to the template file.");
                                }
                            });
                        }
                    } finally {
                        AQLUtils.cleanResourceSetForModels(generation, resourceSetForModel);
                    }
                }
                // CHECKSTYLE:OFF
            } catch (Exception e) {
                // CHECKSTYLE:ON
                M2docconfEditorPlugin.getPlugin().getLog().log(new Status(Status.ERROR,
                        M2docconfEditorPlugin.getPlugin().getSymbolicName(), Status.ERROR, e.getMessage(), e));
                Display.getDefault().asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        MessageDialog.openError(shell, "Can't save validation file.",
                                "Can't save validation file, see the error log for details and check if the file is not opened elsewhere.");
                    }
                });
            }

            return status;
        }
    }

    @Override
    protected void execute(ExecutionEvent event, final Generation generation) {
        final Shell shell = HandlerUtil.getActiveShell(event);

        final Job job = new ValidateJob(generation, shell);
        job.setRule(ResourcesPlugin.getWorkspace().getRoot());
        job.schedule();

    }

}
