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
package org.obeonetwork.m2doc.ui.popup.actions;

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
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.generator.DocumentGenerator;
import org.obeonetwork.m2doc.parser.DocumentParser;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.services.ServiceRegistry;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.ui.Activator;
import org.obeonetwork.m2doc.ui.genconf.Definition;
import org.obeonetwork.m2doc.ui.genconf.Generation;
import org.obeonetwork.m2doc.ui.genconf.ModelDefinition;
import org.obeonetwork.m2doc.ui.genconf.StringDefinition;

/**
 * Handlers for the document generation.
 * 
 * @author Romain Guider
 */
public class GenerateDocumentation implements IObjectActionDelegate {
    /**
     * The shell.
     */
    private Shell shell;
    /**
     * The current selection.
     */
    private ISelection selection;

    /**
     * Constructor for Action1.
     */
    public GenerateDocumentation() {
        super();
    }

    /*
     * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
     */
    @Override
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        shell = targetPart.getSite().getShell();
    }

    /*
     * @see IActionDelegate#run(IAction)
     */
    @Override
    public void run(IAction action) {
        if (selection instanceof IStructuredSelection) {
            Object selected = ((IStructuredSelection) selection).getFirstElement();
            if (selected instanceof Generation) {
                Map<String, Object> definitions = createDefinitions((Generation) selected);
                try {
                    generate((Generation) selected, definitions);
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
            } else {
                MessageDialog.openError(shell, "Bad selection",
                        "Document generation action can only be triggered on Generation object.");
            }
        } else {
            MessageDialog.openError(shell, "Bad selection",
                    "Document generation action can only be triggered on Generation object.");
        }
    }

    /**
     * Returns the file that corresponds to the specified ressource.
     * 
     * @param resource
     *            the resource
     * @return the file
     */
    protected IFile getFile(Resource resource) {
        URI uri = resource.getURI();
        String fileString = URI.decode(uri.path());
        IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(fileString));
        return file;
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
    private void generate(Generation generation, Map<String, Object> definitions)
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
                generatedFile.getLocation().toFile().getAbsolutePath(), template, definitions, queryEnvironment,generation);
        generator.generate();
        MessageDialog.openConfirm(shell, "M2Doc generation",
                "document " + generatedFile.getLocation().toString() + " generated");
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

    /*
     * @see IActionDelegate#selectionChanged(IAction, ISelection)
     */
    @Override
    public void selectionChanged(IAction action, ISelection newSelection) {
        this.selection = newSelection;
    }

}
