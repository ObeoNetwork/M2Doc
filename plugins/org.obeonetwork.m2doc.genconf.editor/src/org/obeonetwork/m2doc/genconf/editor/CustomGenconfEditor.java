/*******************************************************************************
 *  Copyright (c) 2017 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.genconf.editor;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.genconf.Definition;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.Option;
import org.obeonetwork.m2doc.genconf.presentation.GenconfEditor;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Custom editor for genconf models.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class CustomGenconfEditor extends GenconfEditor implements ITemplateCustomPropertiesProvider {

    /**
     * Illegal state message.
     */
    static final String DON_T_KNOW_WHAT_TO_DO_WITH = "don't know what to do with ";

    /**
     * Default width.
     */
    private static final int WIDTH = 300;

    /**
     * The {@link Generation#getTemplateFileName() template URI} {@link Text}.
     */
    private Text templateURIText;

    /**
     * The {@link Generation#getResultFileName() destination URI} {@link Text}.
     */
    private Text destinationURIText;
    /**
     * The {@link Generation#getµValidationFileName() validation URI} {@link Text}.
     */
    private Text validationURIText;

    /**
     * The {@link Generation#getDefinitions() variables} {@link TableViewer}.
     */
    private TableViewer variablesTable;

    /**
     * The {@link Generation#getOptions() options} {@link TableViewer}.
     */
    private TableViewer optionsTable;

    /**
     * The {@link GenerationListener}.
     */
    private GenerationListener generationListener;

    /**
     * Current {@link TemplateCustomProperties}.
     */
    private TemplateCustomProperties templateCustomProperties;

    @Override
    public void createPages() {
        super.createPages();

        getContainer().setLayout(new GridLayout(1, false));

        final Generation generation = getGeneration();
        final Composite composite = new Composite(getContainer(), SWT.NONE);

        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        composite.setLayout(new GridLayout(1, false));

        createTemplateURIComposite(generation, composite);

        createDestinationURIComposite(generation, composite);

        createValidationURIComposite(generation, composite);

        final Composite tablesComposite = new Composite(composite, SWT.NONE);
        tablesComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        tablesComposite.setLayout(new FillLayout(SWT.HORIZONTAL));

        SashForm tablesSashForm = new SashForm(tablesComposite, SWT.VERTICAL);

        createVariablesTable(generation, tablesSashForm);

        createOptionsTable(generation, tablesSashForm);

        tablesSashForm.setWeights(new int[] {1, 1});

        final int pageIndex = 0;
        addPage(pageIndex, composite);
        setPageText(pageIndex, "Overview");

        generationListener = new GenerationListener();
        generationListener.installGenerationListener(generation);
        generationListener.setTemplateURIText(templateURIText);
        generationListener.setDestinationURIText(destinationURIText);
        generationListener.setValidationURIText(validationURIText);
        generationListener.setVariablesViewer(variablesTable);
        generationListener.setOptionsViewer(optionsTable);
    }

    /**
     * Creates the {@link Generation#getOptions() options} table.
     * 
     * @param generation
     *            the {@link Generation}
     * @param composite
     *            the container {@link Composite}
     */
    private void createOptionsTable(final Generation generation, Composite composite) {
        optionsTable = new TableViewer(composite, SWT.MULTI);
        optionsTable.getTable().setHeaderVisible(true);
        TableViewerColumn nameColumn = new TableViewerColumn(optionsTable, SWT.NONE);
        nameColumn.getColumn().setText("Option name");
        nameColumn.getColumn().setWidth(WIDTH);
        nameColumn.setEditingSupport(new OptionNameEditingSupport(optionsTable, editingDomain));
        TableViewerColumn valueColumn = new TableViewerColumn(optionsTable, SWT.NONE);
        valueColumn.getColumn().setText("Option value");
        valueColumn.getColumn().setWidth(WIDTH);
        valueColumn.setEditingSupport(new OptionValueEditingSupport(optionsTable, editingDomain));
        optionsTable.setContentProvider(new IStructuredContentProvider() {

            @Override
            public Object[] getElements(Object inputElement) {
                return ((Generation) inputElement).getOptions().toArray();
            }

            @Override
            public void dispose() {
                // nothing to do here
            }

            @Override
            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
                // nothing to do here
            }

        });
        nameColumn.setLabelProvider(new CellLabelProvider() {
            @Override
            public void update(ViewerCell cell) {
                final Option option = (Option) cell.getElement();
                cell.setText(option.getName());
            }
        });
        valueColumn.setLabelProvider(new CellLabelProvider() {
            @Override
            public void update(ViewerCell cell) {
                final Option option = (Option) cell.getElement();
                cell.setText(option.getValue());
            }
        });
        createContextMenuFor(optionsTable);
        optionsTable.getControl().getMenu().addListener(SWT.Show, new Listener() {

            @Override
            public void handleEvent(Event event) {
                contributeOptionMenus(generation);
            }

        });

        optionsTable.setInput(generation);
    }

    /**
     * Creates the {@link Generation#getDefinitions() variable} table.
     * 
     * @param generation
     *            the {@link Generation}
     * @param composite
     *            the container {@link Composite}
     */
    private void createVariablesTable(final Generation generation, Composite composite) {
        variablesTable = new TableViewer(composite, SWT.MULTI);
        variablesTable.getTable().setHeaderVisible(true);
        TableViewerColumn nameColumn = new TableViewerColumn(variablesTable, SWT.NONE);
        nameColumn.getColumn().setText("Variable name");
        nameColumn.getColumn().setWidth(WIDTH);
        TableViewerColumn valueColumn = new TableViewerColumn(variablesTable, SWT.NONE);
        valueColumn.getColumn().setText("Variable value");
        valueColumn.getColumn().setWidth(WIDTH);
        variablesTable.setContentProvider(new IStructuredContentProvider() {

            @Override
            public Object[] getElements(Object inputElement) {
                return ((Generation) inputElement).getDefinitions().toArray();
            }

            @Override
            public void dispose() {
                // nothing to do here
            }

            @Override
            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
                // nothing to do here
            }

        });
        nameColumn.setLabelProvider(new CellLabelProvider() {
            @Override
            public void update(ViewerCell cell) {
                final Definition definition = (Definition) cell.getElement();
                cell.setText(definition.getKey());
            }
        });
        valueColumn.setLabelProvider(new VariableValueCellLabelProvider(adapterFactory));
        valueColumn.setEditingSupport(
                new VariableValueEditingSupport(variablesTable, editingDomain, adapterFactory, this));
        createContextMenuFor(variablesTable);
        variablesTable.setInput(generation);
        variablesTable.getControl().getMenu().addListener(SWT.Show, new Listener() {

            @Override
            public void handleEvent(Event event) {
                contributeDefinitionMenus(generation);
            }

        });
    }

    /**
     * Creates the {@link Generation#getDefinitions() destination URI} composite.
     * 
     * @param generation
     *            the {@link Generation}
     * @param composite
     *            the container {@link Composite}
     */
    private void createDestinationURIComposite(final Generation generation, final Composite composite) {
        final Composite destinationURIComposite = new Composite(composite, SWT.NONE);
        destinationURIComposite.setLayout(new GridLayout(3, false));
        destinationURIComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        final Label destinationURILabel = new Label(destinationURIComposite, SWT.None);
        destinationURILabel.setText("Destination URI:");
        destinationURIText = new Text(destinationURIComposite, SWT.NONE);
        if (generation.getResultFileName() != null) {
            destinationURIText.setText(generation.getResultFileName());
        }
        destinationURIText.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (!destinationURIText.getText().equals(generation.getResultFileName())) {
                    editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, generation,
                            GenconfPackage.Literals.GENERATION__RESULT_FILE_NAME, destinationURIText.getText()));
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // nothing to do here
            }
        });
        destinationURIText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        Button destinationURIButton = new Button(destinationURIComposite, SWT.BORDER);
        destinationURIButton.setText("Browse");
        destinationURIButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event event) {
                final TempateSelectionDialog dialog = new TempateSelectionDialog(getSite().getShell(), SWT.NONE);

                final IFile selected = dialog.open();
                if (!dialog.isCanceled()) {
                    final URI destinationURI = URI.createPlatformResourceURI(selected.getFullPath().toString(), true);
                    final URI genconfURI = getGenconfResource().getURI();
                    final String relativeDestinationPath = URI.decode(destinationURI.deresolve(genconfURI).toString());
                    destinationURIText.setText(relativeDestinationPath);
                    editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, generation,
                            GenconfPackage.Literals.GENERATION__RESULT_FILE_NAME, relativeDestinationPath));
                }
            }

        });
    }

    /**
     * Creates the {@link Generation#getDefinitions() destination URI} composite.
     * 
     * @param generation
     *            the {@link Generation}
     * @param composite
     *            the container {@link Composite}
     */
    private void createValidationURIComposite(final Generation generation, final Composite composite) {
        final Composite validationURIComposite = new Composite(composite, SWT.NONE);
        validationURIComposite.setLayout(new GridLayout(3, false));
        validationURIComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        final Label validationURILabel = new Label(validationURIComposite, SWT.None);
        validationURILabel.setText("Validation URI:");
        validationURIText = new Text(validationURIComposite, SWT.NONE);
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
        validationURIText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        Button validationURIButton = new Button(validationURIComposite, SWT.BORDER);
        validationURIButton.setText("Browse");
        validationURIButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event event) {
                final TempateSelectionDialog dialog = new TempateSelectionDialog(getSite().getShell(), SWT.NONE);

                final IFile selected = dialog.open();
                if (!dialog.isCanceled()) {
                    final URI validationURI = URI.createPlatformResourceURI(selected.getFullPath().toString(), true);
                    final URI genconfURI = getGenconfResource().getURI();
                    final String relativeDestinationPath = URI.decode(validationURI.deresolve(genconfURI).toString());
                    validationURIText.setText(relativeDestinationPath);
                    editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, generation,
                            GenconfPackage.Literals.GENERATION__VALIDATION_FILE_NAME, relativeDestinationPath));
                }
            }

        });
    }

    /**
     * Creates the {@link Generation#getTemplateFileName() template URI} composite.
     * 
     * @param generation
     *            the {@link Generation}
     * @param composite
     *            the container {@link Composite}
     */
    private void createTemplateURIComposite(final Generation generation, final Composite composite) {
        final Composite templateURIComposite = new Composite(composite, SWT.NONE);
        templateURIComposite.setLayout(new GridLayout(3, false));
        templateURIComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        final Label templateURILabel = new Label(templateURIComposite, SWT.None);
        templateURILabel.setText("Template URI:");
        templateURIText = new Text(templateURIComposite, SWT.NONE);
        if (generation.getTemplateFileName() != null) {
            templateURIText.setText(generation.getTemplateFileName());
            updateTemplateCustomProperties(URI.createURI(generation.getTemplateFileName()));
        }
        templateURIText.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (!templateURIText.getText().equals(generation.getTemplateFileName())) {
                    editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, generation,
                            GenconfPackage.Literals.GENERATION__TEMPLATE_FILE_NAME, templateURIText.getText()));
                    updateTemplateCustomProperties(URI.createURI(templateURIText.getText(), false));
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // nothing to do here
            }
        });
        templateURIText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        Button templateURIButton = new Button(templateURIComposite, SWT.BORDER);
        templateURIButton.setText("Browse");
        templateURIButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event event) {
                final TempateSelectionDialog dialog = new TempateSelectionDialog(getSite().getShell(), SWT.NONE);

                final IFile selected = dialog.open();
                if (!dialog.isCanceled()) {
                    final URI templateURI = URI.createPlatformResourceURI(selected.getFullPath().toString(), true);
                    final URI genconfURI = getGenconfResource().getURI();
                    final String relativeTemplatePath = URI.decode(templateURI.deresolve(genconfURI).toString());
                    templateURIText.setText(relativeTemplatePath);
                    updateTemplateCustomProperties(URI.createURI(relativeTemplatePath, false));
                    editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, generation,
                            GenconfPackage.Literals.GENERATION__TEMPLATE_FILE_NAME, relativeTemplatePath));
                    if (generation.getResultFileName() == null || generation.getResultFileName().isEmpty()) {
                        editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, generation,
                                GenconfPackage.Literals.GENERATION__RESULT_FILE_NAME,
                                relativeTemplatePath.replace("." + M2DocUtils.DOCX_EXTENSION_FILE, "-generated.docx")));
                    }
                }
            }

        });
    }

    /**
     * Gets the {@link Resource} containing the {@link Generation}.
     * 
     * @return the {@link Resource} containing the {@link Generation}
     */
    protected Resource getGenconfResource() {
        // we suppose the first resource is the genconf model...
        return editingDomain.getResourceSet().getResources().get(0);
    }

    @Override
    public void dispose() {
        generationListener.removeGenerationListener(getGeneration());
        super.dispose();
    }

    /**
     * Updates {@link TemplateCustomProperties}.
     * 
     * @param uri
     *            the template {@link URI}
     */
    private void updateTemplateCustomProperties(URI uri) {
        final URI absoluteURI = uri.resolve(getGenconfResource().getURI());
        if (URIConverter.INSTANCE.exists(absoluteURI, null)) {
            try {
                templateCustomProperties = POIServices.getInstance().getTemplateCustomProperties(absoluteURI);
                final Generation generation = getGeneration();
                final List<Definition> newDefinitions = GenconfUtils.getNewDefinitions(generation,
                        templateCustomProperties);
                editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, generation,
                        GenconfPackage.GENERATION__DEFINITIONS, newDefinitions));
                // CHECKSTYLE:OFF
            } catch (Exception e) {
                // CHECKSTYLE:ON
                templateCustomProperties = null;
            }
        }
    }

    /**
     * Contribute {@link Option} menus.
     * 
     * @param generation
     *            the {@link Generation}
     */
    protected void contributeOptionMenus(final Generation generation) {
        new MenuItem(optionsTable.getControl().getMenu(), SWT.SEPARATOR);
        final MenuItem addOptionMenu = new MenuItem(optionsTable.getControl().getMenu(), SWT.PUSH);
        addOptionMenu.setText("Add option");
        final List<String> aviliableOptionNames = GenconfUtils.getAviliableOptionNames(generation);
        addOptionMenu.setEnabled(!aviliableOptionNames.isEmpty());
        addOptionMenu.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                final Option option = GenconfPackage.eINSTANCE.getGenconfFactory().createOption();
                option.setName(aviliableOptionNames.get(0));
                editingDomain.getCommandStack().execute(
                        AddCommand.create(editingDomain, generation, GenconfPackage.GENERATION__OPTIONS, option));
            }
        });

        final MenuItem initializeOptionMenu = new MenuItem(optionsTable.getControl().getMenu(), SWT.PUSH);
        initializeOptionMenu.setText("Initialize option");
        initializeOptionMenu.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                final Generation generation = getGeneration();
                editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

                    @Override
                    protected void doExecute() {
                        GenconfUtils.initializeOptions(generation);
                    }

                });
            }
        });

        final MenuItem removeOptionMenu = new MenuItem(optionsTable.getControl().getMenu(), SWT.PUSH);
        removeOptionMenu.setText("Remove option");
        removeOptionMenu.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                List<?> selectedElements = ((IStructuredSelection) optionsTable.getSelection()).toList();
                if (!selectedElements.isEmpty()) {
                    editingDomain.getCommandStack().execute(RemoveCommand.create(editingDomain, generation,
                            GenconfPackage.GENERATION__OPTIONS, selectedElements));
                }
            }
        });
    }

    /**
     * Contribute {@link Definition} menus.
     * 
     * @param generation
     *            the {@link Generation}
     */
    protected void contributeDefinitionMenus(final Generation generation) {
        new MenuItem(variablesTable.getControl().getMenu(), SWT.SEPARATOR);
        final MenuItem addDefinitionsMenu = new MenuItem(variablesTable.getControl().getMenu(), SWT.PUSH);
        addDefinitionsMenu.setText("Add variables");
        if (templateCustomProperties != null) {
            final List<Definition> newDefinitions = GenconfUtils.getNewDefinitions(generation,
                    templateCustomProperties);
            addDefinitionsMenu.setEnabled(!newDefinitions.isEmpty());
            addDefinitionsMenu.addListener(SWT.Selection, new Listener() {
                @Override
                public void handleEvent(Event event) {
                    editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, generation,
                            GenconfPackage.GENERATION__OPTIONS, newDefinitions));
                }
            });
        } else {
            addDefinitionsMenu.setEnabled(false);
        }

        final MenuItem removeDefinitionMenu = new MenuItem(variablesTable.getControl().getMenu(), SWT.PUSH);
        removeDefinitionMenu.setText("Remove definition");
        removeDefinitionMenu.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                List<?> selectedElements = ((IStructuredSelection) variablesTable.getSelection()).toList();
                if (!selectedElements.isEmpty()) {
                    editingDomain.getCommandStack().execute(RemoveCommand.create(editingDomain, generation,
                            GenconfPackage.GENERATION__DEFINITIONS, selectedElements));
                }
            }
        });
    }

    @Override
    public Diagnostic analyzeResourceProblems(Resource resource, Exception exception) {
        final Diagnostic res;

        if (resource != null) {
            res = super.analyzeResourceProblems(resource, exception);
        } else {
            res = Diagnostic.OK_INSTANCE;
        }

        return res;
    }

    @Override
    public TemplateCustomProperties getTemplateCustomProperties() {
        return templateCustomProperties;
    }

}
