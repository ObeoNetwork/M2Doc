/*******************************************************************************
 *  Copyright (c) 2018, 2024 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.ide.ui.wizard;

import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.ide.M2DocPlugin;
import org.obeonetwork.m2doc.ide.ui.M2DocUIPlugin;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.services.TokenRegistry;

/**
 * {@link TemplateCustomProperties} {@link Wizard}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class TemplateCustomPropertiesWizard extends Wizard {

    {
        // make sure org.obeonetwork.m2doc.ide is activated
        M2DocPlugin.getDefault();
    }

    /**
     * The properties page.
     */
    TemplateCustomPropertiesPage templateCustomPropertiesPage;

    /**
     * The variables page.
     */
    TemplateVariablesPage templateVariablesProperties;

    /**
     * The selected {@link XWPFDocument}.
     */
    XWPFDocument document;

    /**
     * The template {@link URI}.
     */
    private final URI templateURI;

    /**
     * The {@link TemplateCustomProperties} to edit.
     */
    private TemplateCustomProperties properties;

    /**
     * Constructor.
     * 
     * @param templateURI
     *            the template {@link URI}
     */
    public TemplateCustomPropertiesWizard(URI templateURI) {
        this.templateURI = templateURI;
    }

    @Override
    public void addPages() {
        try {
            document = POIServices.getInstance().getXWPFDocument(URIConverter.INSTANCE, templateURI);
            properties = new TemplateCustomProperties(document);
            for (String missingVariable : properties.getMissingVariables()) {
                properties.getVariables().put(missingVariable, "");
            }
            for (String unusedVariable : properties.getUnusedDeclarations()) {
                properties.getVariables().remove(unusedVariable);
            }

            templateVariablesProperties = new TemplateVariablesPage(properties);
            templateCustomPropertiesPage = new TemplateCustomPropertiesPage(TokenRegistry.INSTANCE, properties,
                    templateVariablesProperties);
            templateVariablesProperties.setWizardPage(templateCustomPropertiesPage);
            addPage(templateCustomPropertiesPage);
            addPage(templateVariablesProperties);
            // CHECKSTYLE:OFF
        } catch (final Exception e) {
            // CHECKSTYLE:ON
            addPage(new WizardPage("Invalid template") {

                @Override
                public void createControl(Composite parent) {
                    final Composite container = new Composite(parent, parent.getStyle());
                    setControl(container);
                    setErrorMessage("Template invalid: " + e.getMessage());
                    setPageComplete(false);
                }
            });
            M2DocUIPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, M2DocUIPlugin.PLUGIN_ID, e.getMessage(), e));
        }
    }

    @Override
    public boolean performFinish() {

        final Job job = new WorkspaceJob("Saving template") {

            @Override
            public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
                Status status = new Status(IStatus.OK, M2DocUIPlugin.PLUGIN_ID, "Template saved succesfully");

                properties.save();
                try {
                    POIServices.getInstance().saveFile(URIConverter.INSTANCE, document, templateURI);
                    document.close();
                } catch (IOException e) {
                    status = new Status(IStatus.ERROR, M2DocUIPlugin.PLUGIN_ID, e.getMessage(), e);
                    M2DocUIPlugin.getDefault().getLog().log(status);
                }
                return status;
            }
        };
        job.setRule(ResourcesPlugin.getWorkspace().getRoot());
        job.schedule();

        return true;
    }

    @Override
    public boolean performCancel() {
        if (document != null) {
            try {
                document.close();
            } catch (IOException e) {
                M2DocUIPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, M2DocUIPlugin.PLUGIN_ID, e.getMessage(), e));
            }
        }
        return super.performCancel();
    }

}
