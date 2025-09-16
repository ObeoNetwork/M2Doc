/*******************************************************************************
 *  Copyright (c) 2024, 2025 Obeo. 
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.acceleo.query.AQLUtils;
import org.eclipse.acceleo.query.ide.QueryPlugin;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.Query;
import org.eclipse.acceleo.query.runtime.impl.namespace.JavaLoader;
import org.eclipse.acceleo.query.runtime.namespace.ILoader;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameResolver;
import org.eclipse.acceleo.query.validation.type.EClassifierType;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.ModelDefinition;
import org.obeonetwork.m2doc.genconf.editor.wizard.GenerationWithTemplateLibraryWizard;
import org.obeonetwork.m2doc.genconf.impl.GenconfFactoryImpl;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.generator.M2DocEvaluationEnvironment;
import org.obeonetwork.m2doc.ide.ui.M2DocUIPlugin;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.services.namespace.M2DocDocumentTemplateLoader;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Generates from template library.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class GenerateWithTemplateLibrary extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        final Shell shell = HandlerUtil.getActiveShell(event);
        final ISelection selection = HandlerUtil.getCurrentSelection(event);
        if (!selection.isEmpty() && selection instanceof IStructuredSelection) {
            final EObject eObj = (EObject) ((IStructuredSelection) selection).getFirstElement();

            generate(shell, eObj);
        }

        return null;
    }

    /**
     * Generates.
     * 
     * @param shell
     *            the {@link Shell}
     * @param eObj
     *            the {@link EObject}
     */
    protected void generate(Shell shell, EObject eObj) {
        final IQueryEnvironment queryEnvironment = Query.newEnvironment();
        queryEnvironment.registerEPackage(eObj.eClass().getEPackage());
        for (EClass superECls : eObj.eClass().getEAllSuperTypes()) {
            queryEnvironment.registerEPackage(superECls.getEPackage());
        }

        final Set<IType> variableTypes = new HashSet<>();
        variableTypes.add(new EClassifierType(queryEnvironment, eObj.eClass()));

        final Map<File, TemplateCustomProperties> compatibleTemplates = M2DocUIPlugin.getDefault()
                .getCompatibleTemplatesFromLibraries(queryEnvironment, variableTypes);
        final GenerationWithTemplateLibraryWizard genWiz = new GenerationWithTemplateLibraryWizard(
                new ArrayList<File>(compatibleTemplates.keySet()));
        final WizardDialog dialog = new WizardDialog(shell, genWiz) {
            @Override
            public void create() {
                super.create();
                getShell().setText("Generation Configuration");
            }
        };

        int result = dialog.open();

        if (result == Dialog.OK) {
            final URI templateFile = URI.createFileURI(genWiz.getSelectedTemplate().getAbsolutePath());
            final URI resultFile = URI.createFileURI(genWiz.getOutputFile().getAbsolutePath());
            final TemplateCustomProperties templateCustomProperties = compatibleTemplates
                    .get(genWiz.getSelectedTemplate());
            String variableName = templateCustomProperties.getVariables().keySet().iterator().next();

            // creating the genconf in memory
            final Generation generation = GenconfFactoryImpl.eINSTANCE.createGeneration();
            final Resource resource = new XMIResourceImpl();
            resource.getContents().add(generation);
            resource.setURI(URI.createURI("inMemory.genconf").resolve(eObj.eResource().getURI()));
            generation.setTemplateFileName(templateFile.toString());
            generation.setResultFileName(resultFile.toString());
            // bind the variable value
            ModelDefinition modelDef = GenconfFactoryImpl.eINSTANCE.createModelDefinition();
            modelDef.setKey(variableName);
            modelDef.setValue(eObj);
            generation.getDefinitions().add(modelDef);
            // initialize options
            GenconfUtils.initializeOptions(generation, eObj);

            // launch the generation
            final List<Exception> exceptions = new ArrayList<Exception>();
            final Map<String, String> options = GenconfUtils.getOptions(generation);
            final ResourceSet resourceSetForModel = AQLUtils.createResourceSetForModels(exceptions, generation,
                    new ResourceSetImpl(), options);
            boolean error = false;
            try {
                final IQualifiedNameResolver resolver = QueryPlugin.getPlugin().createResolver(templateFile,
                        this.getClass().getClassLoader(), resourceSetForModel.getPackageRegistry(),
                        M2DocUtils.QUALIFIER_SEPARATOR, false);
                final M2DocEvaluationEnvironment m2docEnv = GenconfUtils.createM2DocEvaluationEnvironment(generation,
                        resolver, resourceSetForModel);

                resolver.addLoader(
                        new M2DocDocumentTemplateLoader(m2docEnv, new BasicMonitor(), M2DocUtils.QUALIFIER_SEPARATOR));
                final ILoader javaLoader = new JavaLoader(M2DocUtils.QUALIFIER_SEPARATOR, false);
                resolver.addLoader(javaLoader);

                GenconfUtils.generate(generation, m2docEnv, options, new BasicMonitor());
            } catch (DocumentGenerationException e) {
                e.printStackTrace();
                error = true;
            } catch (IOException e) {
                e.printStackTrace();
                error = true;
            } catch (DocumentParserException e) {
                e.printStackTrace();
                error = true;
            } finally {
                AQLUtils.cleanResourceSetForModels(generation, resourceSetForModel);
            }
            if (error) {
                MessageDialog.openError(shell, "Error", "Generation ended with an error");
            } else {
                MessageDialog.openInformation(shell, "Complete", "Generation completed");
            }
        }
    }

}
