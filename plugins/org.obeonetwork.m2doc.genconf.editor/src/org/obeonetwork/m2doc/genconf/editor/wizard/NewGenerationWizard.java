package org.obeonetwork.m2doc.genconf.editor.wizard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.Query;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.editor.GenerationListener;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * New generation wizard.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class NewGenerationWizard extends Wizard implements INewWizard {

    /**
     * Default genconf name.
     */
    public static final String DEFAULT_GENCONF_FILE_NAME = "MyGeneration.genconf";

    /**
     * The initial {@link IStructuredSelection}.
     */
    private IStructuredSelection selection = StructuredSelection.EMPTY;

    /**
     * The {@link Generation}.
     */
    private final Generation generation;

    /**
     * The {@link FileNamesPage}.
     */
    private FileNamesPage fileNamesPage;
    /**
     * The {@link VariableAndOptionPage}.
     */
    private VariableAndOptionPage optionPage;

    /**
     * The {@link GenerationListener}.
     */
    private GenerationListener generationListener;

    /**
     * Constructor.
     */
    public NewGenerationWizard() {
        generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();
        final Resource resource = new XMIResourceImpl();
        final ResourceSet rs = new ResourceSetImpl();
        rs.getResources().add(resource);
        resource.getContents().add(generation);
        TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(rs);
    }

    @Override
    public void init(IWorkbench workbench, IStructuredSelection selected) {
        selection = selected;
    }

    @Override
    public void addPages() {
        super.addPages();

        final Generation loadedGeneration = getGeneration(selection);
        if (loadedGeneration == null) {
            final URI genconfURI = getGenconfURI(selection);
            if (genconfURI != null) {
                generation.eResource().setURI(genconfURI);
                final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(generation);
                editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

                    @Override
                    protected void doExecute() {
                        generation.setTemplateFileName(URI.decode(getTemplateFileName(genconfURI).toString()));
                        generation.setValidationFileName(getValidationFileName(genconfURI));
                        generation.setResultFileName(getResultFileName(genconfURI));
                        GenconfUtils.initializeOptions(generation);

                        initializeVariableDefinition(generation);
                    }

                });
            }
        } else {
            generation.eResource().setURI(loadedGeneration.eResource().getURI());
            final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(generation);
            editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

                @Override
                protected void doExecute() {
                    generation.setName(loadedGeneration.getName());
                    generation.setTemplateFileName(loadedGeneration.getTemplateFileName());
                    generation.setValidationFileName(loadedGeneration.getValidationFileName());
                    generation.setResultFileName(loadedGeneration.getResultFileName());
                    generation.getDefinitions().addAll(loadedGeneration.getDefinitions());
                    generation.getOptions().addAll(loadedGeneration.getOptions());
                    GenconfUtils.initializeOptions(generation);

                    initializeVariableDefinition(generation);
                }

            });
        }

        generationListener = new GenerationListener();
        generationListener.installGenerationListener(generation);

        fileNamesPage = new FileNamesPage(generation, generationListener);
        addPage(fileNamesPage);
        optionPage = new VariableAndOptionPage(generation, generationListener, fileNamesPage);
        addPage(optionPage);
    }

    /**
     * Gets the {@link Generation} from the given {@link IStructuredSelection}.
     * 
     * @param selected
     *            the {@link IStructuredSelection}
     * @return the {@link Generation} from the given {@link IStructuredSelection} if any, <code>null</code> otherwise
     */
    private Generation getGeneration(IStructuredSelection selected) {
        final Generation res;

        final ResourceSet rs = new ResourceSetImpl();
        final URI genconfURI = URI
                .createPlatformResourceURI(((IFile) selection.getFirstElement()).getFullPath().toString(), true);
        final Resource resource = rs.getResource(genconfURI, true);
        if (!resource.getContents().isEmpty() && resource.getContents().get(0) instanceof Generation) {
            res = (Generation) resource.getContents().get(0);
        } else {
            res = null;
        }

        return res;
    }

    /**
     * Initializes the {@link Generation#getDefinitions() variable definition} for the given {@link Generation}.
     * 
     * @param gen
     *            the {@link Generation}
     */
    private void initializeVariableDefinition(Generation gen) {
        IQueryEnvironment queryEnvironment = Query.newEnvironment();
        TemplateCustomProperties properties;
        try {
            properties = POIServices.getInstance().getTemplateCustomProperties(
                    URI.createURI(gen.getTemplateFileName()).deresolve(gen.eResource().getURI()));
            ((IQueryEnvironment) queryEnvironment).registerEPackage(EcorePackage.eINSTANCE);
            ((IQueryEnvironment) queryEnvironment).registerCustomClassMapping(
                    EcorePackage.eINSTANCE.getEStringToStringMapEntry(), EStringToStringMapEntryImpl.class);
            properties.configureQueryEnvironmentWithResult((IQueryEnvironment) queryEnvironment);
            final ResourceSetImpl defaultResourceSet = new ResourceSetImpl();
            defaultResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*",
                    new XMIResourceFactoryImpl());
            final ResourceSet resourceSetForModel = M2DocUtils.createResourceSetForModels(new ArrayList<Exception>(),
                    queryEnvironment, defaultResourceSet, GenconfUtils.getOptions(gen));
            GenconfUtils.initializeVariableDefinition(gen, queryEnvironment, properties, resourceSetForModel);
        } catch (IOException e) {
            // no initialization if it fails no big deal
        }
    }

    /**
     * Gets the template file name from the given genconf {@link URI}.
     * 
     * @param genconfURI
     *            the template {@link URI}
     * @return the validation file name from the given genconf {@link URI}
     */
    private String getTemplateFileName(URI genconfURI) {
        final String lastSegment = genconfURI.lastSegment();
        return URI.decode(lastSegment.substring(0, lastSegment.length() - GenconfUtils.GENCONF_EXTENSION_FILE.length())
            + M2DocUtils.DOCX_EXTENSION_FILE);
    }

    /**
     * Gets the validation file name from the given genconf {@link URI}.
     * 
     * @param genconfURI
     *            the genconf {@link URI}
     * @return the validation file name from the given genconf {@link URI}
     */
    private String getValidationFileName(URI genconfURI) {
        final String lastSegment = genconfURI.lastSegment();
        return URI.decode(lastSegment.substring(0, lastSegment.length() - GenconfUtils.GENCONF_EXTENSION_FILE.length())
            + "validation." + M2DocUtils.DOCX_EXTENSION_FILE);
    }

    /**
     * Gets the result file name from the given genconf {@link URI}.
     * 
     * @param genconfURI
     *            the genconf {@link URI}
     * @return the result file name from the given genconf {@link URI}
     */
    private String getResultFileName(URI genconfURI) {
        final String lastSegment = genconfURI.lastSegment();
        return URI.decode(lastSegment.substring(0, lastSegment.length() - GenconfUtils.GENCONF_EXTENSION_FILE.length())
            + "generated." + M2DocUtils.DOCX_EXTENSION_FILE);
    }

    @Override
    public void dispose() {
        super.dispose();
        generationListener.removeGenerationListener(generation);
        TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(generation);
        if (editingDomain != null) {
            editingDomain.dispose();
        }
    }

    /**
     * Gets the genconf {@link URI} from the given selection.
     * 
     * @param selected
     *            the {@link IStructuredSelection}.
     * @return the genconf {@link URI} from the given selection if any, <code>null</code> otherwise
     */
    private URI getGenconfURI(IStructuredSelection selected) {
        final URI res;

        if (selected.getFirstElement() instanceof IFile) {
            final IFile file = (IFile) selected.getFirstElement();
            if (M2DocUtils.DOCX_EXTENSION_FILE.equals(file.getFileExtension())) {
                final String fullPathString = file.getFullPath().toString();
                String genconfFileString = fullPathString.substring(0,
                        fullPathString.length() - M2DocUtils.DOCX_EXTENSION_FILE.length())
                    + GenconfUtils.GENCONF_EXTENSION_FILE;
                res = URI.createPlatformResourceURI(genconfFileString, true);
            } else {
                final String fullPathString = file.getParent().getFullPath().toString();
                res = URI.createPlatformResourceURI(fullPathString + "/" + DEFAULT_GENCONF_FILE_NAME, true);
            }
        } else if (selected.getFirstElement() instanceof IContainer) {
            final IContainer container = (IContainer) selected.getFirstElement();
            final String fullPathString = container.getFullPath().toString();
            res = URI.createPlatformResourceURI(fullPathString + "/" + DEFAULT_GENCONF_FILE_NAME, true);
        } else {
            res = null;
        }

        return res;
    }

    @Override
    public boolean performFinish() {
        boolean res = true;
        try {
            generation.eResource().save(Collections.emptyMap());
        } catch (IOException e) {
            res = false;
            e.printStackTrace();

        }
        return res;
    }

}
