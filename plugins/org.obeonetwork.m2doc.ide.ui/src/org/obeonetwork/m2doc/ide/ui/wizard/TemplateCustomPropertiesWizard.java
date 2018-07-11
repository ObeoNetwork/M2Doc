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

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.ide.M2DocPlugin;
import org.obeonetwork.m2doc.ide.ui.Activator;
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
    private URI templateURI;

    /**
     * The {@link TemplateCustomProperties} to edit.
     */
    private TemplateCustomProperties properties;

    @Override
    public void addPages() {
        final IFile templateFile = (IFile) ((IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getSelectionService().getSelection()).getFirstElement();
        templateURI = URI.createPlatformResourceURI(templateFile.getFullPath().toString(), true);
        try {
            document = POIServices.getInstance().getXWPFDocument(templateURI);
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
            addPage(templateCustomPropertiesPage);
            addPage(templateVariablesProperties);
            // CHECKSTYLE:OFF
        } catch (final Exception e) {
            // CHECKSTYLE:ON
            addPage(new WizardPage("Invalid template") {

                @Override
                public void createControl(Composite parent) {
                    final Composite container = new Composite(parent, SWT.NULL);
                    setControl(container);
                    setErrorMessage("Template invalid: " + e.getMessage());
                    setPageComplete(false);
                }
            });
            Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e));
        }
    }

    @Override
    public boolean performFinish() {
        properties.save();
        try {
            POIServices.getInstance().saveFile(document, templateURI);
            document.close();
        } catch (IOException e) {
            Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e));
            return false;
        }
        return true;
    }

    @Override
    public boolean performCancel() {
        if (document != null) {
            try {
                document.close();
            } catch (IOException e) {
                Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e));
            }
        }
        return super.performCancel();
    }

}
