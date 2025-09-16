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
import org.eclipse.acceleo.query.ide.QueryPlugin;
import org.eclipse.acceleo.query.runtime.impl.namespace.JavaLoader;
import org.eclipse.acceleo.query.runtime.namespace.ILoader;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameResolver;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
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
import org.obeonetwork.m2doc.generator.M2DocEvaluationEnvironment;
import org.obeonetwork.m2doc.services.namespace.M2DocDocumentTemplateLoader;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Generate docx from {@link Generation}.
 * 
 * @author <a href="mailto:romain.guider@obeo.fr">Romain Guider</a>
 */
public class GenerateHandler extends AbstractGenerationHandler {

    /**
     * Run the generation.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    public static final class GenerateJob extends WorkspaceJob {

        /**
         * The generation dialog title.
         */
        private static final String M2_DOC_GENERATION = "M2Doc generation";

        /**
         * The {@link Generation}.
         */
        private final Generation generation;

        /**
         * The {@link Shell}.
         */
        private final Shell shell;

        /**
         * Constructor.
         * 
         * @param generation
         *            the {@link Generation} to generate
         * @param shell
         *            the {@link Shell} to display errors
         */
        public GenerateJob(Generation generation, Shell shell) {
            super("Generating: " + URI.decode(generation.eResource().getURI().toString()));
            this.generation = generation;
            this.shell = shell;
        }

        @Override
        public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
            Status status = new Status(IStatus.OK, M2docconfEditorPlugin.getPlugin().getSymbolicName(),
                    "Generation succesfull");

            final List<URI> generatedfiles = new ArrayList<URI>();
            final List<Exception> exceptions = new ArrayList<Exception>();
            final Map<String, String> options = GenconfUtils.getOptions(generation);
            final ResourceSet resourceSetForModels = AQLUtils.createResourceSetForModels(exceptions, generation,
                    new ResourceSetImpl(), options);
            try {
                final boolean generate = checkM2DocVersion(shell, M2_DOC_GENERATION, generation);
                if (generate) {
                    final URI templateURI = GenconfUtils.getResolvedURI(generation,
                            URI.createURI(generation.getTemplateFileName(), false));
                    final IQualifiedNameResolver resolver = QueryPlugin.getPlugin().createResolver(templateURI,
                            this.getClass().getClassLoader(), resourceSetForModels.getPackageRegistry(),
                            M2DocUtils.QUALIFIER_SEPARATOR, false);
                    M2DocEvaluationEnvironment m2docEnv = GenconfUtils.createM2DocEvaluationEnvironment(generation,
                            resolver, resourceSetForModels);

                    resolver.addLoader(new M2DocDocumentTemplateLoader(m2docEnv, new BasicMonitor(),
                            M2DocUtils.QUALIFIER_SEPARATOR));
                    final ILoader javaLoader = new JavaLoader(M2DocUtils.QUALIFIER_SEPARATOR, false);
                    resolver.addLoader(javaLoader);

                    generatedfiles.addAll(
                            GenconfUtils.generate(generation, m2docEnv, options, BasicMonitor.toMonitor(monitor)));
                }
                // CHECKSTYLE:OFF any error should be reported back.
            } catch (final Exception e) {// do not let exception leak out.
                // CHECKSTYLE:ON
                final String msg = e.getMessage();
                status = new Status(Status.ERROR, M2docconfEditorPlugin.getPlugin().getSymbolicName(), Status.ERROR,
                        "M2Doc : technical error" + (msg == null ? "." : " : " + msg), e);
                M2docconfEditorPlugin.getPlugin().getLog().log(status);
                Display.getDefault().asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        MessageDialog.openError(shell, "Generation problem. See the error log for details", msg);
                    }
                });
            } finally {
                AQLUtils.cleanResourceSetForModels(generation, resourceSetForModels);
            }

            if (generatedfiles.size() == 1) {
                Display.getDefault().asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        MessageDialog.openInformation(shell, M2_DOC_GENERATION,
                                "The document '" + URI.decode(generatedfiles.get(0).toString()) + "' is generated.");
                    }
                });
            } else if (generatedfiles.size() == 2) {
                Display.getDefault().asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        MessageDialog.openInformation(shell, M2_DOC_GENERATION,
                                "The document '" + URI.decode(generatedfiles.get(0).toString())
                                    + "' is generated. \n\n The template file contains validation errors, please read '"
                                    + URI.decode(generatedfiles.get(1).toString()) + "'.");
                    }
                });
            }

            return status;
        }

    }

    @Override
    protected void execute(ExecutionEvent event, final Generation generation) {
        final Shell shell = HandlerUtil.getActiveShell(event);

        final GenerateJob job = new GenerateJob(generation, shell);
        job.setRule(ResourcesPlugin.getWorkspace().getRoot());
        job.setUser(true);
        job.schedule();
    }

}
