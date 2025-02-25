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
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Migrates template.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MigrateTemplateHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        final ISelection selection = HandlerUtil.getCurrentSelection(event);
        if (!selection.isEmpty() && selection instanceof IStructuredSelection) {
            for (Object selected : ((IStructuredSelection) selection).toList()) {
                IFile templateFile = (IFile) selected;
                final URI templateURI = URI.createPlatformResourceURI(templateFile.getFullPath().toString(), true);

                final Job job = new Job("Migrate " + templateURI) {

                    @Override
                    protected IStatus run(IProgressMonitor monitor) {
                        IStatus status;

                        try {
                            final URI targetURI = URI
                                    .createURI(templateFile.getName().replace("." + M2DocUtils.DOCX_EXTENSION_FILE,
                                            "-migrated" + "." + M2DocUtils.DOCX_EXTENSION_FILE))
                                    .resolve(templateURI);
                            M2DocUtils.migrate(URIConverter.INSTANCE, templateURI, targetURI,
                                    BasicMonitor.toMonitor(monitor));
                            templateFile.getParent().refreshLocal(IResource.DEPTH_ONE, monitor);
                            status = new Status(IStatus.OK, getClass(), templateURI + " migrated.");
                        } catch (DocumentParserException | CoreException e) {
                            status = new Status(IStatus.ERROR, getClass(), "Can't migrate " + templateURI, e);
                        }

                        return status;
                    }

                };

                job.setRule(ResourcesPlugin.getWorkspace().getRoot());
                job.schedule();
            }
        }

        return null;
    }

}
