/*******************************************************************************
 *  Copyright (c) 2018 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.ide.ui.wizard;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.ide.ui.Activator;
import org.obeonetwork.m2doc.util.M2DocUtils;
import org.obeonetwork.m2doc.util.MemoryURIHandler;

/**
 * The new template wizard.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class M2DocNewTemplateWizard extends Wizard implements INewWizard {

    /**
     * The ok status message.
     */
    private static final String OK_MESSAGE = "M2Doc template %s created.";

    /**
     * The error status message.
     */
    private static final String ERROR_MESSAGE = "M2Doc template %s can't be created.";

    /**
     * Creates the template.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    public class FinishJob extends Job {

        /**
         * The template name.
         */
        private String templateName;

        /**
         * The variable value.
         */
        private EObject variableValue;

        /**
         * The variable name.
         */
        private String variableName;

        /**
         * Constructor.
         * 
         * @param templateName
         *            the template name
         * @param variableValue
         *            the variable value
         * @param variableName
         *            the variable name
         */
        public FinishJob(String templateName, EObject variableValue, String variableName) {
            super("Creating M2Doc template: " + templateName);
            this.templateName = templateName;
            this.variableValue = variableValue;
            this.variableName = variableName;
        }

        @Override
        protected IStatus run(IProgressMonitor monitor) {
            IStatus res = new Status(IStatus.OK, Activator.PLUGIN_ID, String.format(OK_MESSAGE, templateName));

            final URIConverter uriConverter = new ExtensibleURIConverterImpl();
            final MemoryURIHandler handler = new MemoryURIHandler();
            uriConverter.getURIHandlers().add(0, handler);
            try (XWPFDocument sampleTemplate = M2DocUtils.createSampleTemplate(variableName, variableValue.eClass());) {
                final URI memoryURI = URI
                        .createURI(MemoryURIHandler.PROTOCOL + "://resources/temp." + M2DocUtils.DOCX_EXTENSION_FILE);
                POIServices.getInstance().saveFile(uriConverter, sampleTemplate, memoryURI);

                try (InputStream source = uriConverter.createInputStream(memoryURI)) {
                    final IFile templateFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(templateName));
                    templateFile.create(source, true, monitor);
                } catch (CoreException e) {
                    res = new Status(IStatus.OK, Activator.PLUGIN_ID, String.format(ERROR_MESSAGE, templateName), e);
                }
            } catch (InvalidFormatException e) {
                res = new Status(IStatus.OK, Activator.PLUGIN_ID, String.format(ERROR_MESSAGE, templateName), e);
            } catch (IOException e) {
                res = new Status(IStatus.OK, Activator.PLUGIN_ID, String.format(ERROR_MESSAGE, templateName), e);
            }

            return res;
        }

    }

    /**
     * The {@link M2DocNewTemplatePage}.
     */
    private M2DocNewTemplatePage newTemplatePage;

    /**
     * The {@link M2DocMainVariablePage}.
     */
    private M2DocMainVariablePage variablePage;

    /**
     * The default template name.
     */
    private String defaultTempateName;

    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        if (selection.getFirstElement() instanceof IFile) {
            defaultTempateName = ((IFile) selection.getFirstElement()).getParent().getFullPath() + "/template.docx";
        } else if (selection.getFirstElement() instanceof IContainer) {
            defaultTempateName = ((IContainer) selection.getFirstElement()).getFullPath() + "/template.docx";
        }
    }

    @Override
    public void addPages() {
        newTemplatePage = new M2DocNewTemplatePage(defaultTempateName);
        addPage(newTemplatePage);
        variablePage = new M2DocMainVariablePage();
        addPage(variablePage);
    }

    @Override
    public boolean performFinish() {
        final String templateName = newTemplatePage.getTemplateName();
        final String variableName = variablePage.getVariableName();
        final EObject variableValue = variablePage.getVariableValue();

        final Job job = new FinishJob(templateName, variableValue, variableName);
        job.setRule(ResourcesPlugin.getWorkspace().getRoot());
        job.schedule();

        return true;
    }

}
