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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.Query;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.action.LoadResourceAction.LoadResourceDialog;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.obeonetwork.m2doc.genconf.BooleanDefinition;
import org.obeonetwork.m2doc.genconf.Definition;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.IntegerDefinition;
import org.obeonetwork.m2doc.genconf.ModelDefinition;
import org.obeonetwork.m2doc.genconf.Option;
import org.obeonetwork.m2doc.genconf.RealDefinition;
import org.obeonetwork.m2doc.genconf.StringDefinition;
import org.obeonetwork.m2doc.genconf.editor.GenerationListener;
import org.obeonetwork.m2doc.genconf.editor.ITemplateCustomPropertiesProvider;
import org.obeonetwork.m2doc.genconf.editor.VariableValueCellLabelProvider;
import org.obeonetwork.m2doc.genconf.editor.dialog.DefinitionValueDialog;
import org.obeonetwork.m2doc.genconf.editor.dialog.M2DocOptionDialog;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Option selection page.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class VariableAndOptionPage extends WizardPage {

    /**
     * The edit {@link Button} {@link SelectionListener}.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private final class EditDefinitionSelectionListener implements SelectionListener {

        /**
         * The variable {@link Viewer}.
         */
        private final Viewer variablesViewer;

        /**
         * The {@link Generation}.
         */
        private final Generation gen;

        /**
         * Constructor.
         * 
         * @param variablesViewer
         *            the variable {@link Viewer}
         * @param gen
         *            the {@link Generation}
         */
        private EditDefinitionSelectionListener(Viewer variablesViewer, Generation gen) {
            this.variablesViewer = variablesViewer;
            this.gen = gen;
        }

        @Override
        public void widgetSelected(SelectionEvent e) {
            final Definition def = (Definition) ((IStructuredSelection) variablesViewer.getSelection())
                    .getFirstElement();
            final DefinitionValueDialog dialog = new DefinitionValueDialog(getShell(), adapterFactory, def,
                    queryEnvironment, templateCustomPropertiesProvider.getTemplateCustomProperties(),
                    getEditingDomain(gen).getResourceSet());
            final int dialogResult = dialog.open();
            if (dialogResult == IDialogConstants.OK_ID) {
                final TransactionalEditingDomain generationDomain = TransactionUtil.getEditingDomain(gen);
                generationDomain.getCommandStack().execute(new RecordingCommand(generationDomain) {

                    @Override
                    protected void doExecute() {
                        if (def instanceof BooleanDefinition) {
                            ((BooleanDefinition) def).setValue((boolean) dialog.getValue());
                        } else if (def instanceof IntegerDefinition) {
                            ((IntegerDefinition) def).setValue((int) dialog.getValue());
                        } else if (def instanceof ModelDefinition) {
                            ((ModelDefinition) def).setValue((EObject) dialog.getValue());
                        } else if (def instanceof RealDefinition) {
                            ((RealDefinition) def).setValue((double) dialog.getValue());
                        } else if (def instanceof StringDefinition) {
                            ((StringDefinition) def).setValue((String) dialog.getValue());
                        } else {
                            throw new IllegalStateException("don't know what to do with " + def);
                        }
                    }
                });
            }
        }

        @Override
        public void widgetDefaultSelected(SelectionEvent e) {
            // nothing to do here
        }
    }

    /**
     * Default width.
     */
    private static final int WIDTH = 300;

    /**
     * The {@link Generation}.
     */
    private final Generation generation;

    /**
     * The {@link GenerationListener}.
     */
    private final GenerationListener generationListener;

    /**
     * The {@link Generation#getDefinitions() variables} {@link TableViewer}.
     */
    private TableViewer variablesTable;

    /**
     * The {@link Generation#getOptions() options} {@link TableViewer}.
     */
    private TableViewer optionsTable;

    /**
     * The {@link ITemplateCustomPropertiesProvider}.
     */
    private ITemplateCustomPropertiesProvider templateCustomPropertiesProvider;

    /**
     * The {@link AdapterFactory}.
     */
    private final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
            ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

    /**
     * The variable {@link ResourceSet}.
     */
    private TransactionalEditingDomain editingDomain;

    /**
     * A {@link IReadOnlyQueryEnvironment} for {@link ResourceSet} initialization.
     */
    private final IReadOnlyQueryEnvironment queryEnvironment = Query.newEnvironment();

    /**
     * Tells if the {@link #editingDomain} has been created by ourself or not.
     */
    private boolean createdEditingDomaine;

    /**
     * Constructor.
     * 
     * @param generation
     *            the {@link Generation}
     * @param generationListener
     *            the {@link GenerationListener}
     * @param provider
     *            the {@link ITemplateCustomPropertiesProvider}
     */
    public VariableAndOptionPage(Generation generation, GenerationListener generationListener,
            ITemplateCustomPropertiesProvider provider) {
        super("Set Variables and Options");
        setTitle("Set Variables and Options");
        this.generation = generation;
        this.generationListener = generationListener;
        this.templateCustomPropertiesProvider = provider;
    }

    @Override
    public void createControl(Composite parent) {

        final Composite container = new Composite(parent, SWT.NULL);
        setControl(container);
        container.setLayout(new GridLayout(1, false));

        final TabFolder tabFolder = new TabFolder(container, SWT.BORDER);
        tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

        final TabItem variableTabItem = new TabItem(tabFolder, SWT.NULL);
        variableTabItem.setText("Variables");
        final Composite variableContainer = new Composite(tabFolder, SWT.NULL);
        variableTabItem.setControl(variableContainer);
        variablesTable = createVariablesTable(generation, variableContainer, adapterFactory,
                templateCustomPropertiesProvider);
        generationListener.setVariablesViewer(variablesTable);
        createVariablesButonComposite(generation, variableContainer, variablesTable);

        final TabItem optionTabItem = new TabItem(tabFolder, SWT.NULL);
        optionTabItem.setText("Options (expert)");
        final Composite optionContainer = new Composite(tabFolder, SWT.NULL);
        optionTabItem.setControl(optionContainer);
        optionContainer.setLayout(new GridLayout(2, false));
        optionsTable = createOptionsTable(generation, optionContainer);
        generationListener.setOptionsViewer(optionsTable);
        createOptionsButonComposite(generation, optionContainer, optionsTable);
        initializeGenerationVariableDefinition(generation);
    }

    /**
     * Creates the {@link Generation#getOptions() options} {@link TableViewer}.
     * 
     * @param gen
     *            the {@link Generation}
     * @param composite
     *            the container {@link Composite}
     * @param factory
     *            the {@link AdapterFactory}
     * @param provider
     *            the {@link ITemplateCustomPropertiesProvider}
     * @return the created {@link TableViewer}
     */
    private TableViewer createVariablesTable(final Generation gen, Composite composite, AdapterFactory factory,
            ITemplateCustomPropertiesProvider provider) {
        composite.setLayout(new GridLayout(2, false));
        TableViewer res = new TableViewer(composite, SWT.MULTI);
        Table table = res.getTable();
        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        res.getTable().setHeaderVisible(true);
        TableViewerColumn nameColumn = new TableViewerColumn(res, SWT.NONE);
        nameColumn.getColumn().setText("Variable name");
        nameColumn.getColumn().setWidth(WIDTH);
        TableViewerColumn valueColumn = new TableViewerColumn(res, SWT.NONE);
        valueColumn.getColumn().setText("Variable value");
        valueColumn.getColumn().setWidth(WIDTH);
        res.setContentProvider(new IStructuredContentProvider() {

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
        valueColumn.setLabelProvider(new VariableValueCellLabelProvider(factory));

        res.setInput(gen);

        return res;
    }

    /**
     * Creates the {@link Button} {@link Composite}.
     * 
     * @param gen
     *            the {@link Generation}
     * @param composite
     *            the {@link Composite}
     * @param variablesViewer
     *            the variable {@link Viewer}
     */
    private void createVariablesButonComposite(final Generation gen, Composite composite,
            final Viewer variablesViewer) {
        final Composite container = new Composite(composite, SWT.NULL);
        container.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, true, 1, 1));
        container.setLayout(new GridLayout(1, false));

        final Button loadResourceButton = new Button(container, SWT.NONE);
        loadResourceButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
        loadResourceButton.setText("Load resource");
        loadResourceButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                LoadResourceDialog dialog = new LoadResourceDialog(getShell(), getEditingDomain(gen));
                dialog.open();
                initializeGenerationVariableDefinition(gen);
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                // nothing to do here
            }
        });

        final Button editButton = new Button(container, SWT.NONE);
        editButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
        editButton.setText("Edit");
        editButton.setEnabled(((IStructuredSelection) variablesViewer.getSelection()).getFirstElement() != null);
        variablesViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                editButton.setEnabled(
                        ((IStructuredSelection) event.getSelection()).getFirstElement() instanceof Definition);
            }
        });
        editButton.addSelectionListener(new EditDefinitionSelectionListener(variablesViewer, gen));
    }

    /**
     * Creates the {@link Generation#getOptions() options} {@link TableViewer}.
     * 
     * @param gen
     *            the {@link Generation}
     * @param composite
     *            the container {@link Composite}
     * @return the created {@link TableViewer}
     */
    private TableViewer createOptionsTable(final Generation gen, Composite composite) {
        TableViewer res = new TableViewer(composite, SWT.MULTI | SWT.BORDER) {
            @Override
            public void refresh() {
                updateEditingDomain(gen);
                initializeGenerationVariableDefinition(gen);
                super.refresh();
            }

        };
        Table table = res.getTable();
        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        res.getTable().setHeaderVisible(true);
        TableViewerColumn nameColumn = new TableViewerColumn(res, SWT.NONE);
        nameColumn.getColumn().setText("Option name");
        nameColumn.getColumn().setWidth(WIDTH);
        TableViewerColumn valueColumn = new TableViewerColumn(res, SWT.NONE);
        valueColumn.getColumn().setText("Option value");
        valueColumn.getColumn().setWidth(WIDTH);
        res.setContentProvider(new IStructuredContentProvider() {

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

        res.setInput(gen);

        return res;
    }

    /**
     * Creates the {@link Button} {@link Composite}.
     * 
     * @param gen
     *            the {@link Generation}
     * @param composite
     *            the {@link Composite}
     * @param optionsViewer
     *            the option {@link Viewer}
     */
    private void createOptionsButonComposite(final Generation gen, Composite composite, final Viewer optionsViewer) {
        final Composite container = new Composite(composite, SWT.NULL);
        container.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, true, 1, 1));
        container.setLayout(new GridLayout(1, false));

        final TransactionalEditingDomain genEditingDomain = TransactionUtil.getEditingDomain(gen);

        final Button addButton = new Button(container, SWT.NONE);
        addButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
        addButton.setText("Add");
        addButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                final Option option = GenconfPackage.eINSTANCE.getGenconfFactory().createOption();
                final List<String> availableOptionNames = GenconfUtils.getAvailableOptionNames(gen);
                if (!availableOptionNames.isEmpty()) {
                    option.setName(availableOptionNames.get(0));
                    genEditingDomain.getCommandStack().execute(
                            AddCommand.create(genEditingDomain, gen, GenconfPackage.GENERATION__OPTIONS, option));
                    editOption(genEditingDomain, gen, option);
                    addButton.setEnabled(availableOptionNames.size() > 1);
                }
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                // nothing to do here
            }
        });

        final Button editButton = new Button(container, SWT.NONE);
        editButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
        editButton.setText("Edit");
        editButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                final Option option = (Option) ((IStructuredSelection) optionsViewer.getSelection()).getFirstElement();
                editOption(genEditingDomain, gen, option);
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                // nothing to do here
            }
        });
        editButton.setEnabled(!optionsViewer.getSelection().isEmpty());
        optionsViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                editButton.setEnabled(!event.getSelection().isEmpty());
            }
        });

        final Button removeButton = new Button(container, SWT.NONE);
        removeButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
        removeButton.setText("Remove");
        removeButton.setEnabled(!optionsViewer.getSelection().isEmpty());
        removeButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                final List<?> selected = ((IStructuredSelection) optionsViewer.getSelection()).toList();
                genEditingDomain.getCommandStack().execute(
                        RemoveCommand.create(genEditingDomain, gen, GenconfPackage.GENERATION__OPTIONS, selected));
                addButton.setEnabled(true);
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                // nothing to do here
            }
        });
        optionsViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                removeButton.setEnabled(!event.getSelection().isEmpty());
            }
        });
    }

    /**
     * Edits the given {@link Option}.
     * 
     * @param genEditingDomain
     *            the {@link TransactionalEditingDomain}
     * @param gen
     *            the {@link Generation}
     * @param option
     *            the {@link Option} to edit
     */
    private void editOption(final TransactionalEditingDomain genEditingDomain, final Generation gen,
            final Option option) {
        final M2DocOptionDialog dialog = new M2DocOptionDialog(getShell(), gen, option);
        final int dialogResult = dialog.open();
        if (dialogResult == IDialogConstants.OK_ID) {
            genEditingDomain.getCommandStack().execute(new RecordingCommand(genEditingDomain) {

                @Override
                protected void doExecute() {
                    option.setName(dialog.getOptionName());
                    option.setValue(dialog.getOptionValue());
                }
            });
        }
    }

    /**
     * Gets the variable {@link TableViewer}.
     * 
     * @return the variable {@link TableViewer}
     */
    public TableViewer getVariablesTable() {
        return variablesTable;
    }

    /**
     * Gets the option {@link TableViewer}.
     * 
     * @return the option {@link TableViewer}
     */
    public TableViewer getOptionsTable() {
        return optionsTable;
    }

    @Override
    public void dispose() {
        super.dispose();
        if (editingDomain != null) {
            M2DocUtils.cleanResourceSetForModels(queryEnvironment, editingDomain.getResourceSet());
        }
        if (createdEditingDomaine) {
            editingDomain.dispose();
            editingDomain = null;
        }
        adapterFactory.dispose();
    }

    /**
     * Updates the {@link EditingDomain} for the given {@link Generation}.
     * 
     * @param gen
     *            the {@link Generation}
     */
    private void updateEditingDomain(Generation gen) {
        final ResourceSetImpl defaultResourceSet = new ResourceSetImpl();
        defaultResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*",
                new XMIResourceFactoryImpl());

        if (editingDomain != null) {
            M2DocUtils.cleanResourceSetForModels(queryEnvironment, editingDomain.getResourceSet());
        }
        if (createdEditingDomaine) {
            editingDomain.dispose();
            editingDomain = null;
        }

        final ResourceSet modelResourceSet = M2DocUtils.createResourceSetForModels(new ArrayList<Exception>(),
                queryEnvironment, defaultResourceSet, GenconfUtils.getOptions(gen));
        final TransactionalEditingDomain modelEditingDomain = TransactionUtil.getEditingDomain(modelResourceSet);
        if (modelEditingDomain == null) {
            editingDomain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(modelResourceSet);
            createdEditingDomaine = true;
        } else {
            editingDomain = modelEditingDomain;
            createdEditingDomaine = false;
        }
    }

    /**
     * Initializes {@link Generation#getDefinitions() variable definition} for the given {@link Generation}.
     * 
     * @param gen
     *            the {@link Generation}
     */
    private void initializeGenerationVariableDefinition(final Generation gen) {
        final TemplateCustomProperties properties = templateCustomPropertiesProvider.getTemplateCustomProperties();
        ((IQueryEnvironment) queryEnvironment).registerEPackage(EcorePackage.eINSTANCE);
        ((IQueryEnvironment) queryEnvironment).registerCustomClassMapping(
                EcorePackage.eINSTANCE.getEStringToStringMapEntry(), EStringToStringMapEntryImpl.class);
        if (properties != null) {
            properties.configureQueryEnvironmentWithResult((IQueryEnvironment) queryEnvironment);
        }

        final TransactionalEditingDomain generationDomain = TransactionUtil.getEditingDomain(gen);
        generationDomain.getCommandStack().execute(new RecordingCommand(generationDomain) {

            @Override
            protected void doExecute() {
                GenconfUtils.initializeVariableDefinition(gen, queryEnvironment, properties,
                        getEditingDomain(gen).getResourceSet());
            }
        });
    }

    /**
     * Gets the {@link EditingDomain} for the given {@link Generation}.
     * 
     * @param gen
     *            the {@link Generation}
     * @return the {@link EditingDomain} for the given {@link Generation}
     */
    private TransactionalEditingDomain getEditingDomain(Generation gen) {
        if (editingDomain == null) {
            updateEditingDomain(gen);
        }
        return editingDomain;
    }

}
