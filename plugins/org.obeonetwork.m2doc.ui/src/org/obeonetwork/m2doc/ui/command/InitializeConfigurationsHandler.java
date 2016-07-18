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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.util.ConfigurationServices;
import org.obeonetwork.m2doc.properties.TemplateInfo;
import org.obeonetwork.m2doc.ui.Activator;

/**
 * Initialize configurations for documention generation.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class InitializeConfigurationsHandler extends AbstractHandler {
    /**
     * The constructor.
     */
    public InitializeConfigurationsHandler() {
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
            if (selected instanceof IFile) {
                // get properties from docx file
                FileInputStream is;
                try {
                    is = new FileInputStream(((IFile) selected).getLocation().toString());
                    OPCPackage oPackage = OPCPackage.open(is);
                    XWPFDocument document = new XWPFDocument(oPackage);
                    TemplateInfo templateInfo = new TemplateInfo(document);

                    // create genconf model
                    if (templateInfo != null) {
                        createConfigurationModel(templateInfo, ((IFile) selected));
                    }
                } catch (FileNotFoundException e) {
                    Activator.getDefault().getLog()
                            .log(new Status(Status.ERROR, Activator.PLUGIN_ID, Status.ERROR, e.getMessage(), e));
                    MessageDialog.openError(shell, "File not found, see the error log for details", e.getMessage());
                } catch (InvalidFormatException e) {
                    Activator.getDefault().getLog()
                            .log(new Status(Status.ERROR, Activator.PLUGIN_ID, Status.ERROR, e.getMessage(), e));
                    MessageDialog.openError(shell, "Invalid file format, see the error log for details",
                            e.getMessage());
                } catch (IOException e) {
                    Activator.getDefault().getLog()
                            .log(new Status(Status.ERROR, Activator.PLUGIN_ID, Status.ERROR, e.getMessage(), e));
                    MessageDialog.openError(shell, "I/O problem, see the error log for details", e.getMessage());
                }

            } else {
                MessageDialog.openError(shell, "Bad selection",
                        "Configuration action can only be triggered on docx files.");
            }

        } else {
            MessageDialog.openError(shell, "Bad selection",
                    "Document generation action can only be triggered on docx files.");
        }
        return null;

    }

    /**
     * Create genconf model from templateInfo information.
     * 
     * @param templateInfo
     *            TemplateInfo
     * @param templateFile
     *            IFile
     */
    private void createConfigurationModel(TemplateInfo templateInfo, final IFile templateFile) {
        // create genconf resource.
        URI templateURI = URI.createPlatformResourceURI(templateFile.getFullPath().toString(), true);
        URI genConfURI = templateURI.trimFileExtension().appendFileExtension(Activator.GENCONF_EXTENSION_FILE);
        Resource resource = createGenConfResource(templateFile, genConfURI);

        // create initial model content.
        ConfigurationServices configurationServices = new ConfigurationServices();
        Generation rootObject = configurationServices.createInitialModel(genConfURI.trimFileExtension().lastSegment(),
                templateFile.getProjectRelativePath().toString());
        // add docx properties
        configurationServices.addProperties(rootObject, templateInfo);
        if (rootObject != null) {
            resource.getContents().add(rootObject);
        }

        // Save the contents of the resource to the file system.
        saveResource(resource);
    }

    /**
     * Save the contents of the resource to the file system.
     * 
     * @param resource
     *            Resource
     * @throws IOException
     */
    private void saveResource(Resource resource) {
        try {
            Map<Object, Object> options = new HashMap<Object, Object>();
            options.put(XMLResource.OPTION_ENCODING, "UTF-8");
            resource.save(options);
        } catch (Exception exception) {
            Activator.getDefault().getLog().log(
                    new Status(Status.ERROR, Activator.PLUGIN_ID, Status.ERROR, exception.getMessage(), exception));
        }
    }

    /**
     * @param templateFile
     *            IFile
     * @param genConfURI
     *            URI
     * @return new genconf resource.
     */
    private Resource createGenConfResource(IFile templateFile, URI genConfURI) {
        // Create a resource set
        ResourceSet resourceSet = new ResourceSetImpl();
        // Create a resource for this file.
        Resource resource = resourceSet.createResource(genConfURI);
        return resource;
    }

}
