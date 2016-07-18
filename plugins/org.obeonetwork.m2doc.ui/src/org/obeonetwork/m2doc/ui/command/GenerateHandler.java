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
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.InvalidAcceleoPackageException;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.obeonetwork.m2doc.genconf.Definition;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.ModelDefinition;
import org.obeonetwork.m2doc.genconf.StringDefinition;
import org.obeonetwork.m2doc.genconf.util.ConfigurationServices;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.generator.DocumentGenerator;
import org.obeonetwork.m2doc.parser.DocumentParser;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.services.ServiceRegistry;
import org.obeonetwork.m2doc.template.DocumentTemplate;
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
            EObject generation = null;
            if (selected instanceof Generation) {
                generation = (EObject) selected;
            } else if (selected instanceof IResource) {
                URI genConfURI = URI.createPlatformResourceURI(((IResource) selected).getFullPath().toString(), true);
                generation = ConfigurationServices.getGeneration(genConfURI);
            } else {
                MessageDialog.openError(shell, "Bad selection",
                        "Document generation action can only be triggered on Generation object.");
                return null;
            }

            if (generation != null) {
                Map<String, Object> definitions = createDefinitions((Generation) generation);
                try {
                    generate((Generation) generation, definitions, shell);
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
        } else

        {
            MessageDialog.openError(shell, "Bad selection",
                    "Document generation action can only be triggered on Generation object.");
        }
        return null;

    }

    /**
     * Launch the documentation generation.
     * 
     * @param generation
     *            the generation configuration object
     * @param definitions
     *            the set of definitions associated to the generation
     * @throws IOException
     *             if an I/O problem occurs
     * @throws DocumentParserException
     *             if the document coulnd'nt be parsed.
     * @throws DocumentGenerationException
     *             if the document couldn't be generated
     */
    private void generate(Generation generation, Map<String, Object> definitions, Shell shell)
            throws IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        registerServices(queryEnvironment);

        for (String nsURI : generation.getPackagesNSURI()) {
            EPackage p = EPackage.Registry.INSTANCE.getEPackage(nsURI);
            if (p == null) {
                Activator.getDefault().getLog().log(
                        new Status(Status.WARNING, Activator.PLUGIN_ID, "Couldn't find package with nsURI " + nsURI));
            } else {
                queryEnvironment.registerEPackage(p);
            }
        }
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        workspace.getRoot().getLocation();
        IContainer container = workspace.getRoot().findMember(generation.eResource().getURI().toPlatformString(true))
                .getParent();
        while (!(container instanceof IProject)) {
            container = container.getParent();
        }
        if (generation.getTemplateFileName() == null) {
            throw new DocumentGenerationException("Template file name must be filled.");
        }
        if (generation.getResultFileName() == null) {
            throw new DocumentGenerationException("Generated file name must be filled.");
        }
        IFile templateFile = container.getFile(new Path(generation.getTemplateFileName()));
        IFile generatedFile = container.getFile(new Path(generation.getResultFileName()));
        String projectRoot = container.getLocation().toString();
        if (!templateFile.exists()) {
            MessageDialog.openError(shell, "File not found", "Couldn't find file " + templateFile);
            return;
        }
        FileInputStream is = new FileInputStream(templateFile.getLocation().toFile());
        OPCPackage oPackage;
        try {
            oPackage = OPCPackage.open(is);
        } catch (InvalidFormatException e) {
            throw new IllegalArgumentException("Couldn't open template", e);
        }
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        DocumentGenerator generator = new DocumentGenerator(projectRoot,
                templateFile.getLocation().toFile().getAbsolutePath(),
                generatedFile.getLocation().toFile().getAbsolutePath(), template, definitions, queryEnvironment,
                generation);
        generator.generate();
        MessageDialog.openConfirm(shell, "M2Doc generation",
                "The document '" + generatedFile.getLocation().toString() + "' is generated.");
    }

    /**
     * Creates a definition map from a {@link Generation} instance.
     * 
     * @param generation
     *            the instance holding the definitions.
     * @return the created map.
     */
    private Map<String, Object> createDefinitions(Generation generation) {
        Map<String, Object> result = new HashMap<String, Object>();
        for (Definition def : generation.getDefinitions()) {
            if (def instanceof ModelDefinition) {
                result.put(((ModelDefinition) def).getKey(), ((ModelDefinition) def).getValue());
            } else if (def instanceof StringDefinition) {
                result.put(((StringDefinition) def).getKey(), ((StringDefinition) def).getValue());
            } else {
                throw new UnsupportedOperationException();
            }
        }
        return result;
    }

    /**
     * Registers services for use by the AQL evaluation engine.
     * 
     * @param env
     *            registers the services
     */
    private void registerServices(IQueryEnvironment env) {
        List<Class<?>> services = ServiceRegistry.INSTANCE.getServicePackages(ServiceRegistry.DEFAULT_TOKEN);
        for (Class<?> cls : services) {
            try {
                env.registerServicePackage(cls);
            } catch (InvalidAcceleoPackageException e) {
                Activator.getDefault().getLog()
                        .log(new Status(Status.ERROR, Activator.PLUGIN_ID, Status.ERROR,
                                "Invalid Service Pacakge registered under token " + ServiceRegistry.DEFAULT_TOKEN
                                    + " : " + e.getMessage(),
                                e));
            }
        }
    }

}
