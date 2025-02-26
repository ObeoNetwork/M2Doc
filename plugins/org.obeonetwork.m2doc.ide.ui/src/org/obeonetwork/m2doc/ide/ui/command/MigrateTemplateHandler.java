/*******************************************************************************
 *  Copyright (c) 2025 Obeo. 
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

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Migrates template.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MigrateTemplateHandler extends AbstractHandler {

    /**
     * A dot.
     */
    private static final String DOT = ".";

    /**
     * The migration {@link Job}.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private final class MigrationJob extends Job {

        /**
         * The template {@link URI}.
         */
        private final URI templateURI;

        /**
         * the template {@link IFile}.
         */
        private final IFile templateFile;

        /**
         * Constructor.
         * 
         * @param templateFile
         *            the template {@link IFile}
         */
        private MigrationJob(IFile templateFile) {
            super("Migrate " + templateFile.getFullPath().toString());
            templateURI = URI.createPlatformResourceURI(templateFile.getFullPath().toString(), true);
            this.templateFile = templateFile;
        }

        @Override
        protected IStatus run(IProgressMonitor monitor) {
            IStatus status;

            try {
                final URI targetURI = URI.createURI(templateFile.getName().replace(DOT + M2DocUtils.DOCX_EXTENSION_FILE,
                        "-migrated" + DOT + M2DocUtils.DOCX_EXTENSION_FILE)).resolve(templateURI);
                final URI targetErrorURI = URI
                        .createURI(templateFile.getName().replace(DOT + M2DocUtils.DOCX_EXTENSION_FILE,
                                "-migrated-errors" + DOT + M2DocUtils.DOCX_EXTENSION_FILE))
                        .resolve(templateURI);
                final List<TemplateValidationMessage> messages = M2DocUtils.migrate(URIConverter.INSTANCE, templateURI,
                        targetURI, targetErrorURI, BasicMonitor.toMonitor(monitor));

                if (!messages.isEmpty()) {
                    status = new Status(IStatus.ERROR, getClass(),
                            "Errors while migrating " + templateURI + " see " + targetErrorURI + ".");
                } else {
                    status = new Status(IStatus.OK, getClass(), templateURI + " migrated.");
                }

                templateFile.getParent().refreshLocal(IResource.DEPTH_ONE, monitor);
            } catch (DocumentParserException | CoreException e) {
                status = new Status(IStatus.ERROR, getClass(), "Can't migrate " + templateURI, e);
            }

            return status;
        }
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        final ISelection selection = HandlerUtil.getCurrentSelection(event);
        if (!selection.isEmpty() && selection instanceof IStructuredSelection) {
            for (Object selected : ((IStructuredSelection) selection).toList()) {
                IFile templateFile = (IFile) selected;

                final Job job = new MigrationJob(templateFile);

                job.setRule(ResourcesPlugin.getWorkspace().getRoot());
                job.schedule();
            }
        }

        return null;
    }

}
