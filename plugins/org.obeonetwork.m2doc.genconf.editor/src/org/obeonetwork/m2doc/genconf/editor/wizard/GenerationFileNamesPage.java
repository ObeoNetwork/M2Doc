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
package org.obeonetwork.m2doc.genconf.editor.wizard;

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.genconf.Definition;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.editor.GenerationListener;
import org.obeonetwork.m2doc.genconf.editor.ITemplateCustomPropertiesProvider;
import org.obeonetwork.m2doc.ide.ui.dialog.M2DocFileSelectionDialog;
import org.obeonetwork.m2doc.ide.ui.dialog.SelectRegistredTemplateDialog;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Files selection {@link WizardPage}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class GenerationFileNamesPage extends WizardPage implements ITemplateCustomPropertiesProvider {

    /**
     * The {@link GenconfPackage}.
     */
    private Generation generation;

    /**
     * The {@link GenerationListener}.
     */
    private final GenerationListener generationListener;

    /**
     * The genconf {@link URI} {@link Text}.
     */
    private Text genConfURIText;

    /**
     * The {@link Generation#getTemplateFileName() template URI} {@link Text}.
     */
    private Text templateURIText;

    /**
     * The {@link Generation#getValidationFileName() validation URI} {@link Text}.
     */
    private Text validationURIText;

    /**
     * The {@link Generation#getResultFileName() result URI} {@link Text}.
     */
    private Text resultURIText;

    /**
     * The {@link TemplateCustomProperties} if any, <code>null</code> otherwise.
     */
    private TemplateCustomProperties templateCustomProperties;

    /**
     * Tells if we can change the template file.
     */
    private boolean canChangeTemplateFile;

    /**
     * Constructor.
     * 
     * @param generation
     *            the {@link Generation}
     * @param generationListener
     *            the {@link GenerationListener}
     * @param canChangeTemplateFile
     *            <code>true</code> if change is possible, <code>false</code> otherwise
     */
    protected GenerationFileNamesPage(Generation generation, GenerationListener generationListener,
            boolean canChangeTemplateFile) {
        super("Select files");
        setTitle("Select files");
        this.generation = generation;
        this.generationListener = generationListener;
        this.canChangeTemplateFile = canChangeTemplateFile;
    }

    @Override
    public void createControl(Composite parent) {
        final Composite container = new Composite(parent, parent.getStyle());
        container.setLayout(new GridLayout(1, false));
        setControl(container);

        final EditingDomain editingDomain = TransactionUtil.getEditingDomain(generation);

        genConfURIText = createGenconfURIComposite(generation, container);

        final Group relativeToGenconfGroup = new Group(container, SWT.BORDER);
        relativeToGenconfGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        relativeToGenconfGroup.setLayout(new GridLayout(1, false));
        relativeToGenconfGroup.setText("Relative to generation file");

        templateURIText = createTemplateURIComposite(generation, relativeToGenconfGroup, canChangeTemplateFile);
        generationListener.setTemplateURIText(templateURIText);

        validationURIText = createURIComposite(generation, relativeToGenconfGroup, "Validation file:", new Listener() {

            @Override
            public void handleEvent(Event event) {
                final M2DocFileSelectionDialog dialog = new M2DocFileSelectionDialog(getShell(),
                        "Select validation file.",
                        getFileName(GenconfUtils.getResolvedURI(generation,
                                URI.createURI(generation.getValidationFileName()))),
                        M2DocUtils.DOCX_EXTENSION_FILE, false);
                final int dialogResult = dialog.open();
                if ((dialogResult == IDialogConstants.OK_ID) && !dialog.getFileName().isEmpty()) {
                    final URI validationURI = URI.createPlatformResourceURI(dialog.getFileName(), true);
                    final URI genconfURI = generation.eResource().getURI();
                    final String relativeDestinationPath = URI.decode(validationURI.deresolve(genconfURI).toString());
                    editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, generation,
                            GenconfPackage.Literals.GENERATION__VALIDATION_FILE_NAME, relativeDestinationPath));
                }
            }

        });
        generationListener.setValidationURIText(validationURIText);
        if (generation.getValidationFileName() != null) {
            validationURIText.setText(generation.getValidationFileName());
        }
        validationURIText.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (!validationURIText.getText().equals(generation.getValidationFileName())) {
                    editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, generation,
                            GenconfPackage.Literals.GENERATION__VALIDATION_FILE_NAME, validationURIText.getText()));
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // nothing to do here
            }
        });

        resultURIText = createURIComposite(generation, relativeToGenconfGroup, "Result file:", new Listener() {

            @Override
            public void handleEvent(Event event) {
                final M2DocFileSelectionDialog dialog = new M2DocFileSelectionDialog(getShell(), "Select result file.",
                        getFileName(
                                GenconfUtils.getResolvedURI(generation, URI.createURI(generation.getResultFileName()))),
                        M2DocUtils.DOCX_EXTENSION_FILE, false);
                final int dialogResult = dialog.open();
                if ((dialogResult == IDialogConstants.OK_ID) && !dialog.getFileName().isEmpty()) {
                    final URI validationURI = URI.createPlatformResourceURI(dialog.getFileName(), true);
                    final URI genconfURI = generation.eResource().getURI();
                    final String relativeDestinationPath = URI.decode(validationURI.deresolve(genconfURI).toString());
                    editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, generation,
                            GenconfPackage.Literals.GENERATION__RESULT_FILE_NAME, relativeDestinationPath));
                }
            }

        });
        generationListener.setDestinationURIText(resultURIText);
        if (generation.getResultFileName() != null) {
            resultURIText.setText(generation.getResultFileName());
        }
        resultURIText.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (!resultURIText.getText().equals(generation.getResultFileName())) {
                    editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, generation,
                            GenconfPackage.Literals.GENERATION__RESULT_FILE_NAME, resultURIText.getText()));
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // nothing to do here
            }
        });

        final URI templateURI = URI.createURI(getTemplateURIText().getText());
        if (templateURI != null) {
            validatePage(generation, templateURI);
        } else {
            validatePage(generation, URI.createURI(""));
        }
    }

    /**
     * Creates the {@link Generation#getDefinitions() destination URI} composite.
     * 
     * @param gen
     *            the {@link Generation}
     * @param composite
     *            the container {@link Composite}
     * @return the created {@link Text}
     */
    private Text createGenconfURIComposite(final Generation gen, final Composite composite) {
        final Composite uriComposite = new Composite(composite, composite.getStyle());
        uriComposite.setLayout(new GridLayout(3, false));
        uriComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        final Label uriLabel = new Label(uriComposite, composite.getStyle());
        uriLabel.setText("Generation file:");
        final Text uriText = new Text(uriComposite, composite.getStyle());
        uriText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        if (gen.eResource().getURI() != null) {
            uriText.setText(URI.decode(gen.eResource().getURI().toString()));
        }
        uriText.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                updateGenconfURI(gen, URI.createURI(uriText.getText(), true));
                validatePage(gen, GenconfUtils.getResolvedURI(gen, URI.createURI(gen.getTemplateFileName(), false)));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // nothing to do here
            }
        });
        Button uriButton = new Button(uriComposite, SWT.BORDER);
        uriButton.setText("Browse");
        uriButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event event) {
                final M2DocFileSelectionDialog dialog = new M2DocFileSelectionDialog(getShell(),
                        "Select generation file.", getFileName(gen.eResource().getURI()),
                        GenconfUtils.GENCONF_EXTENSION_FILE, false);
                final int dialogResult = dialog.open();
                if ((dialogResult == IDialogConstants.OK_ID) && !dialog.getFileName().isEmpty()) {
                    URI newGenconfURI = URI.createPlatformResourceURI(dialog.getFileName(), true);
                    updateGenconfURI(gen, newGenconfURI);
                    uriText.setText(newGenconfURI.toString());
                    validatePage(gen,
                            GenconfUtils.getResolvedURI(gen, URI.createURI(gen.getTemplateFileName(), false)));
                }
            }
        });

        return uriText;
    }

    /**
     * Updates the generation URI.
     * 
     * @param gen
     *            the {@link Generation}
     * @param newGenconfURI
     *            the new generation URI
     */
    private void updateGenconfURI(final Generation gen, final URI newGenconfURI) {
        final URI templateAbsolutURI;
        if (gen.getTemplateFileName() != null) {
            templateAbsolutURI = GenconfUtils.getResolvedURI(gen, URI.createURI(gen.getTemplateFileName(), false));
        } else {
            templateAbsolutURI = null;
        }
        final URI validationAbsolutURI;
        if (gen.getValidationFileName() != null) {
            validationAbsolutURI = GenconfUtils.getResolvedURI(gen, URI.createURI(gen.getValidationFileName(), false));
        } else {
            validationAbsolutURI = null;
        }
        final URI resultAbsolutURI;
        if (gen.getResultFileName() != null) {
            resultAbsolutURI = GenconfUtils.getResolvedURI(gen, URI.createURI(gen.getResultFileName()));
        } else {
            resultAbsolutURI = null;
        }

        gen.eResource().setURI(newGenconfURI);

        final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(gen);
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

            @Override
            protected void doExecute() {
                if (templateAbsolutURI != null) {
                    gen.setTemplateFileName(URI.decode(templateAbsolutURI.deresolve(newGenconfURI).toString()));
                }
                if (validationAbsolutURI != null) {
                    gen.setValidationFileName(URI.decode(validationAbsolutURI.deresolve(newGenconfURI).toString()));
                }
                if (resultAbsolutURI != null) {
                    gen.setResultFileName(URI.decode(resultAbsolutURI.deresolve(newGenconfURI).toString()));
                }
            }
        });
    }

    /**
     * Creates the template {@link URI} {@link Composite}.
     * 
     * @param gen
     *            the {@link Generation}
     * @param composite
     *            the container {@link Composite}
     * @param canChange
     *            <code>true</code> if change is possible, <code>false</code> otherwise
     * @return the template {@link URI} {@link Composite}
     */
    private Text createTemplateURIComposite(final Generation gen, Composite composite, boolean canChange) {
        final Composite uriComposite = new Composite(composite, composite.getStyle());
        uriComposite.setLayout(new GridLayout(4, false));
        uriComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
        final EditingDomain editingDomain = TransactionUtil.getEditingDomain(gen);
        final Label uriLabel = new Label(uriComposite, composite.getStyle());
        uriLabel.setText("Template File:");
        final Text uriText = new Text(uriComposite, composite.getStyle());
        uriText.setEnabled(canChange);
        uriText.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(ModifyEvent e) {
                templateCustomProperties = validatePage(gen,
                        GenconfUtils.getResolvedURI(gen, URI.createURI(gen.getTemplateFileName(), false)));
            }
        });
        if (gen.getTemplateFileName() != null) {
            uriText.setText(gen.getTemplateFileName());
            templateCustomProperties = validatePage(gen,
                    GenconfUtils.getResolvedURI(gen, URI.createURI(gen.getTemplateFileName(), false)));
        }
        uriText.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (!uriText.getText().equals(gen.getTemplateFileName())) {
                    editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, gen,
                            GenconfPackage.Literals.GENERATION__TEMPLATE_FILE_NAME, uriText.getText()));
                    templateCustomProperties = validatePage(gen,
                            GenconfUtils.getResolvedURI(gen, URI.createURI(gen.getTemplateFileName(), false)));
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // nothing to do here
            }
        });
        uriText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        uriText.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (!uriText.getText().equals(gen.eResource().getURI().toString())) {
                    updateTemplateURI(gen, URI.createURI(uriText.getText()));
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // nothing to do here
            }
        });

        final Button workspaceButton = new Button(uriComposite, SWT.BORDER);
        workspaceButton.setEnabled(canChange);
        workspaceButton.setText("Browse workspace");
        workspaceButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event event) {
                final M2DocFileSelectionDialog dialog = new M2DocFileSelectionDialog(getShell(),
                        "Select generation file.",
                        getFileName(GenconfUtils.getResolvedURI(gen, URI.createURI(gen.getTemplateFileName()))),
                        M2DocUtils.DOCX_EXTENSION_FILE, true);
                final int dialogResult = dialog.open();
                if ((dialogResult == IDialogConstants.OK_ID) && !dialog.getFileName().isEmpty()) {
                    final URI templateURI = URI.createPlatformResourceURI(dialog.getFileName(), true);
                    updateTemplateURI(gen, templateURI);
                }
            }

        });

        final Button registeryButton = new Button(uriComposite, SWT.BORDER);
        registeryButton.setEnabled(canChange);
        registeryButton.setText("Browse registery");
        registeryButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event event) {
                final SelectRegistredTemplateDialog dialog = new SelectRegistredTemplateDialog(getShell());

                final int dialogResult = dialog.open();
                if ((dialogResult == IDialogConstants.OK_ID) && dialog.getTemplateURI() != null) {
                    updateTemplateURI(gen, dialog.getTemplateURI());
                }
            }

        });

        return uriText;
    }

    /**
     * Updates the {@link Generation#getTemplateFileName() template URI}.
     * 
     * @param gen
     *            the {@link Generation}
     * @param templateURI
     *            the new {@link Generation#getTemplateFileName() template URI}
     */
    private void updateTemplateURI(Generation gen, final URI templateURI) {
        final URI genconfURI = gen.eResource().getURI();
        final String relativeTemplatePath;
        if (!templateURI.isPlatformPlugin()) {
            relativeTemplatePath = URI.decode(templateURI.deresolve(genconfURI).toString());
        } else {
            relativeTemplatePath = URI.decode(templateURI.toString());
        }
        final EditingDomain editingDomain = TransactionUtil.getEditingDomain(gen);
        templateCustomProperties = validatePage(gen,
                GenconfUtils.getResolvedURI(gen, URI.createURI(gen.getTemplateFileName(), false)));
        editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, gen,
                GenconfPackage.Literals.GENERATION__TEMPLATE_FILE_NAME, relativeTemplatePath));
        if (gen.getResultFileName() == null || gen.getResultFileName().isEmpty()) {
            editingDomain.getCommandStack()
                    .execute(SetCommand.create(editingDomain, gen, GenconfPackage.Literals.GENERATION__RESULT_FILE_NAME,
                            relativeTemplatePath.replace("." + M2DocUtils.DOCX_EXTENSION_FILE, "-generated.docx")));
        }
        if (gen.getValidationFileName() == null || gen.getValidationFileName().isEmpty()) {
            editingDomain.getCommandStack()
                    .execute(SetCommand.create(editingDomain, gen,
                            GenconfPackage.Literals.GENERATION__VALIDATION_FILE_NAME,
                            relativeTemplatePath.replace("." + M2DocUtils.DOCX_EXTENSION_FILE, "-validation.docx")));
        }
    }

    /**
     * Creates the {@link Generation#getDefinitions() destination URI} composite.
     * 
     * @param gen
     *            the {@link Generation}
     * @param composite
     *            the container {@link Composite}
     * @param label
     *            the label
     * @param buttonListener
     *            the {@link Button} {@link Listener}
     * @return the created {@link Text}
     */
    private Text createURIComposite(final Generation gen, final Composite composite, String label,
            Listener buttonListener) {
        final Composite uriComposite = new Composite(composite, composite.getStyle());
        uriComposite.setLayout(new GridLayout(3, false));
        uriComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
        final Label uriLabel = new Label(uriComposite, composite.getStyle());
        uriLabel.setText(label);
        final Text uriText = new Text(uriComposite, composite.getStyle());
        uriText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        Button uriButton = new Button(uriComposite, SWT.BORDER);
        uriButton.setText("Browse");
        uriButton.addListener(SWT.Selection, buttonListener);

        return uriText;
    }

    /**
     * Validates the page and gets the {@link TemplateCustomProperties} if any.
     * 
     * @param gen
     *            the {@link Generation}
     * @param templateURI
     *            the template {@link URI}
     * @return the {@link TemplateCustomProperties} if any, <code>null</code> otherwise
     */
    private TemplateCustomProperties validatePage(Generation gen, URI templateURI) {
        TemplateCustomProperties res;

        final EditingDomain editingDomain = TransactionUtil.getEditingDomain(gen);

        final URI absoluteURI = templateURI.resolve(gen.eResource().getURI());
        if (URIConverter.INSTANCE.exists(absoluteURI, null)) {
            try {
                res = POIServices.getInstance().getTemplateCustomProperties(URIConverter.INSTANCE, absoluteURI);
                final List<Definition> oldDefinitions = GenconfUtils.getOldDefinitions(gen, res);
                final Command removeCommand = RemoveCommand.create(editingDomain, gen,
                        GenconfPackage.GENERATION__DEFINITIONS, oldDefinitions);
                editingDomain.getCommandStack().execute(removeCommand);

                final List<Definition> newDefinitions = GenconfUtils.getNewDefinitions(gen, res);
                final Command addCommand = AddCommand.create(editingDomain, gen, GenconfPackage.GENERATION__DEFINITIONS,
                        newDefinitions);
                editingDomain.getCommandStack().execute(addCommand);
                // CHECKSTYLE:OFF
            } catch (Exception e) {
                // CHECKSTYLE:ON
                setErrorMessage("Invalid template: " + e.getMessage());
                res = null;
            }
        } else {
            res = null;
            setErrorMessage("Template " + absoluteURI + " doesn't exist.");
        }

        if (res != null) {
            setPageComplete(true);
            if (!M2DocUtils.VERSION.equals(res.getM2DocVersion())) {
                setMessage("M2Doc version mismatch: template version is " + res.getM2DocVersion()
                    + " and current M2Doc version is " + M2DocUtils.VERSION, IMessageProvider.WARNING);
            } else {
                setErrorMessage(null);
            }
        } else {
            setPageComplete(false);
        }

        return res;
    }

    /**
     * Gets the genconf {@link URI} {@link Text}.
     * 
     * @return the genconf {@link URI} {@link Text}
     */
    public Text getGenConfURIText() {
        return genConfURIText;
    }

    /**
     * Gets the {@link Generation#getTemplateFileName() template URI} {@link Text}.
     * 
     * @return the {@link Generation#getTemplateFileName() template URI} {@link Text}
     */
    public Text getTemplateURIText() {
        return templateURIText;
    }

    public Text getValidationURIText() {
        return validationURIText;
    }

    public Text getResultURIText() {
        return resultURIText;
    }

    @Override
    public TemplateCustomProperties getTemplateCustomProperties() {
        return templateCustomProperties;
    }

    /**
     * Gets the file name from the given {@link URI}.
     * 
     * @param uri
     *            the {@link URI}
     * @return the file name from the given {@link URI}
     */
    private String getFileName(URI uri) {
        final String res;

        if (uri.isPlatformResource()) {
            res = uri.toPlatformString(true);
        } else {
            res = "";
        }

        return res;
    }

}
