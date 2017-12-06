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

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
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
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.obeonetwork.m2doc.POIServices;
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
import org.obeonetwork.m2doc.genconf.presentation.GenconfEditor;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.services.configurator.IServicesConfigurator;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Custom editor for genconf models.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class CustomGenconfEditor extends GenconfEditor {

    /**
     * Variable value {@link CellLabelProvider}.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private final class VariableValueCellLabelProvider extends CellLabelProvider {

        @Override
        public void update(ViewerCell cell) {
            final Definition definition = (Definition) cell.getElement();
            if (definition instanceof StringDefinition) {
                cell.setText(((StringDefinition) definition).getValue());
            } else if (definition instanceof IntegerDefinition) {
                cell.setText(String.valueOf(((IntegerDefinition) definition).getValue()));
            } else if (definition instanceof RealDefinition) {
                cell.setText(String.valueOf(((RealDefinition) definition).getValue()));
            } else if (definition instanceof BooleanDefinition) {
                cell.setText(String.valueOf(((BooleanDefinition) definition).isValue()));
            } else if (definition instanceof ModelDefinition) {
                final String text;
                final EObject eObj = ((ModelDefinition) definition).getValue();
                if (eObj != null) {
                    final IItemLabelProvider itemProvider = (IItemLabelProvider) adapterFactory.adapt(eObj,
                            IItemLabelProvider.class);

                    if (itemProvider == null) {
                        text = eObj.toString();
                    } else {
                        text = itemProvider.getText(eObj);
                    }
                    cell.setText(text);
                } else {
                    cell.setText("");
                }
            } else {
                cell.setText(DON_T_KNOW_WHAT_TO_DO_WITH + definition.getClass().getCanonicalName());
            }
        }
    }

    /**
     * {@link Definition#getKey() variable name} editing support.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private final class VariableNameEditingSupport extends EditingSupport {
        /**
         * The Editor.
         */
        private final ComboBoxViewerCellEditor editor;

        /**
         * Constructor.
         * 
         * @param viewer
         *            the {@link ColumnViewer}
         */
        private VariableNameEditingSupport(ColumnViewer viewer) {
            super(viewer);
            editor = new ComboBoxViewerCellEditor((Composite) viewer.getControl());
            editor.setContentProvider(new IStructuredContentProvider() {

                @Override
                public Object[] getElements(Object inputElement) {
                    return ((List<?>) inputElement).toArray();
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
        }

        @Override
        protected void setValue(Object element, Object value) {
            final Definition definition = (Definition) element;
            if (!definition.getKey().equals(value)) {
                editingDomain.getCommandStack().execute(
                        SetCommand.create(editingDomain, definition, GenconfPackage.Literals.DEFINITION__KEY, value));
            }
        }

        @Override
        protected Object getValue(Object element) {
            return ((Definition) element).getKey();
        }

        @Override
        protected CellEditor getCellEditor(Object element) {
            final List<String> variableNames = new ArrayList<String>();

            if (templateCustomProperties != null) {
                if (element instanceof StringDefinition) {
                    for (Entry<String, String> entry : templateCustomProperties.getVariables().entrySet()) {
                        if (TemplateCustomProperties.STRING_TYPE.equals(entry.getValue())) {
                            variableNames.add(entry.getKey());
                        }
                    }
                } else if (element instanceof IntegerDefinition) {
                    for (Entry<String, String> entry : templateCustomProperties.getVariables().entrySet()) {
                        if (TemplateCustomProperties.INTEGER_TYPE.equals(entry.getValue())) {
                            variableNames.add(entry.getKey());
                        }
                    }
                } else if (element instanceof RealDefinition) {
                    for (Entry<String, String> entry : templateCustomProperties.getVariables().entrySet()) {
                        if (TemplateCustomProperties.REAL_TYPE.equals(entry.getValue())) {
                            variableNames.add(entry.getKey());
                        }
                    }
                } else if (element instanceof BooleanDefinition) {
                    for (Entry<String, String> entry : templateCustomProperties.getVariables().entrySet()) {
                        if (TemplateCustomProperties.BOOLEAN_TYPE.equals(entry.getValue())) {
                            variableNames.add(entry.getKey());
                        }
                    }
                } else if (element instanceof ModelDefinition) {
                    for (Entry<String, String> entry : templateCustomProperties.getVariables().entrySet()) {
                        if (!TemplateCustomProperties.STRING_TYPE.equals(entry.getValue())
                            && !TemplateCustomProperties.INTEGER_TYPE.equals(entry.getValue())
                            && !TemplateCustomProperties.REAL_TYPE.equals(entry.getValue())
                            && !TemplateCustomProperties.BOOLEAN_TYPE.equals(entry.getValue())) {
                            variableNames.add(entry.getKey());
                        }
                    }
                } else {
                    throw new IllegalStateException(DON_T_KNOW_WHAT_TO_DO_WITH + element.getClass());
                }
            }

            editor.setInput(variableNames);
            return editor;
        }

        @Override
        protected boolean canEdit(Object element) {
            return templateCustomProperties != null;
        }
    }

    /**
     * {@link Definition#getKey() variable name} editing support.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private final class VariableValueEditingSupport extends EditingSupport {

        /**
         * Constructor.
         * 
         * @param viewer
         *            the {@link ColumnViewer}
         */
        private VariableValueEditingSupport(ColumnViewer viewer) {
            super(viewer);
        }

        @Override
        protected CellEditor getCellEditor(Object element) {
            final Definition definition = (Definition) element;
            final EStructuralFeature valueFeature = getValueFeature(definition);
            final AdapterFactoryContentProvider contentProvider = new AdapterFactoryContentProvider(adapterFactory);
            IPropertySource propertySource = contentProvider.getPropertySource(element);
            IPropertyDescriptor[] propertyDescriptors = propertySource.getPropertyDescriptors();
            for (IPropertyDescriptor propertyDescriptor : propertyDescriptors) {
                if (valueFeature.getName().equals(propertyDescriptor.getId())) {
                    return propertyDescriptor.createPropertyEditor((Composite) getViewer().getControl());
                }
            }
            return null;
        }

        @Override
        protected boolean canEdit(Object element) {
            return templateCustomProperties != null;
        }

        @Override
        protected Object getValue(Object element) {
            final Definition definition = (Definition) element;
            final EStructuralFeature valueFeature = getValueFeature(definition);

            return definition.eGet(valueFeature);
        }

        @Override
        protected void setValue(Object element, Object value) {
            final Definition definition = (Definition) element;
            final EStructuralFeature valueFeature = getValueFeature(definition);
            final Object currentValue = definition.eGet(valueFeature);
            if ((currentValue == null && value != null) || (currentValue != null && !currentValue.equals(value))) {
                editingDomain.getCommandStack()
                        .execute(SetCommand.create(editingDomain, definition, valueFeature, value));
            }
        }

        /**
         * Gets the {@link EStructuralFeature} containing the value of the {@link Definition}.
         * 
         * @param definition
         *            the {@link Definition}
         * @return the {@link EStructuralFeature} containing the value of the {@link Definition}.
         */
        EStructuralFeature getValueFeature(Definition definition) {
            final EStructuralFeature res;

            if (definition instanceof ModelDefinition) {
                res = GenconfPackage.Literals.MODEL_DEFINITION__VALUE;
            } else if (definition instanceof StringDefinition) {
                res = GenconfPackage.Literals.STRING_DEFINITION__VALUE;
            } else {
                throw new IllegalStateException(DON_T_KNOW_WHAT_TO_DO_WITH + definition.getClass().getCanonicalName());
            }

            return res;
        }

    }

    /**
     * {@link Option#getName() Option name} editing support.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private final class OptionNameEditingSupport extends EditingSupport {

        /**
         * The Editor.
         */
        private final ComboBoxViewerCellEditor editor;

        /**
         * Constructor.
         * 
         * @param viewer
         *            the {@link ColumnViewer}
         */
        private OptionNameEditingSupport(ColumnViewer viewer) {
            super(viewer);
            editor = new ComboBoxViewerCellEditor((Composite) viewer.getControl());
            editor.setContentProvider(new IStructuredContentProvider() {

                @Override
                public Object[] getElements(Object inputElement) {
                    return ((List<?>) inputElement).toArray();
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
        }

        @Override
        protected CellEditor getCellEditor(Object element) {
            List<String> availableOptions = getAviliableOptionNames();

            availableOptions.add(0, ((Option) element).getName());
            editor.setInput(availableOptions);

            return editor;
        }

        @Override
        protected boolean canEdit(Object element) {
            return true;
        }

        @Override
        protected Object getValue(Object element) {
            final Option option = (Option) element;

            return option.getName();
        }

        @Override
        protected void setValue(Object element, Object value) {
            final Option option = (Option) element;

            if ((option.getName() == null && value != null)
                || (option.getName() != null && !option.getName().equals(value))) {
                editingDomain.getCommandStack()
                        .execute(SetCommand.create(editingDomain, option, GenconfPackage.Literals.OPTION__NAME, value));
            }
        }

    }

    /**
     * {@link Option#getValue() Option value} editing support.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private final class OptionValueEditingSupport extends EditingSupport {

        /**
         * The Editor.
         */
        private final TextCellEditor editor;

        /**
         * Constructor.
         * 
         * @param viewer
         *            the {@link ColumnViewer}
         */
        private OptionValueEditingSupport(ColumnViewer viewer) {
            super(viewer);
            editor = new TextCellEditor((Composite) viewer.getControl());
        }

        @Override
        protected CellEditor getCellEditor(Object element) {
            return editor;
        }

        @Override
        protected boolean canEdit(Object element) {
            return true;
        }

        @Override
        protected Object getValue(Object element) {
            final Option option = (Option) element;

            return option.getValue();
        }

        @Override
        protected void setValue(Object element, Object value) {
            final Option option = (Option) element;

            if ((option.getValue() == null && value != null)
                || (option.getValue() != null && !option.getValue().equals(value))) {
                editingDomain.getCommandStack().execute(
                        SetCommand.create(editingDomain, option, GenconfPackage.Literals.OPTION__VALUE, value));
            }
        }

    }

    /**
     * Update GUI elements according to {@link Generation} changes.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private final class GenerationListener extends AdapterImpl {
        @SuppressWarnings("unchecked")
        @Override
        public void notifyChanged(Notification msg) {
            super.notifyChanged(msg);
            if (msg.getNotifier() instanceof Generation) {
                switch (msg.getFeatureID(Generation.class)) {
                    case GenconfPackage.GENERATION__TEMPLATE_FILE_NAME:
                        final String newTemplateURI = (String) msg.getNewValue();
                        if (!newTemplateURI.equals(templateURIText.getText())) {
                            templateURIText.setText(newTemplateURI);
                        }
                        break;
                    case GenconfPackage.GENERATION__RESULT_FILE_NAME:
                        final String newDestionationURI = (String) msg.getNewValue();
                        if (!newDestionationURI.equals(destinationURIText.getText())) {
                            destinationURIText.setText(newDestionationURI);
                        }
                        break;
                    case GenconfPackage.GENERATION__DEFINITIONS:
                        switch (msg.getEventType()) {
                            case Notification.ADD:
                                final Definition newDefinition = (Definition) msg.getNewValue();
                                newDefinition.eAdapters().add(this);
                                break;
                            case Notification.ADD_MANY:
                                for (Definition definition : (List<Definition>) msg.getOldValue()) {
                                    definition.eAdapters().add(this);
                                }
                                break;
                            case Notification.REMOVE:
                                final Definition oldDefinition = (Definition) msg.getOldValue();
                                oldDefinition.eAdapters().remove(this);
                                break;
                            case Notification.REMOVE_MANY:
                                for (Definition definition : (List<Definition>) msg.getOldValue()) {
                                    definition.eAdapters().remove(this);
                                }
                                break;
                            default:
                                break;
                        }
                        if (!variablesTable.getTable().isDisposed()) {
                            variablesTable.refresh();
                        }
                        break;
                    case GenconfPackage.GENERATION__OPTIONS:
                        switch (msg.getEventType()) {
                            case Notification.ADD:
                                final Option newOption = (Option) msg.getNewValue();
                                newOption.eAdapters().add(this);
                                break;
                            case Notification.ADD_MANY:
                                for (Option option : (List<Option>) msg.getOldValue()) {
                                    option.eAdapters().add(this);
                                }
                                break;
                            case Notification.REMOVE:
                                final Option oldoption = (Option) msg.getOldValue();
                                oldoption.eAdapters().remove(this);
                                break;
                            case Notification.REMOVE_MANY:
                                for (Option option : (List<Option>) msg.getOldValue()) {
                                    option.eAdapters().remove(this);
                                }
                                break;
                            default:
                                break;
                        }
                        if (!optionsTable.getTable().isDisposed()) {
                            optionsTable.refresh();
                        }
                        break;
                    default:
                        break;
                }
            } else if (msg.getNotifier() instanceof Definition) {
                if (!variablesTable.getTable().isDisposed()) {
                    variablesTable.refresh();
                }
            } else if (msg.getNotifier() instanceof Option) {
                if (!optionsTable.getTable().isDisposed()) {
                    optionsTable.refresh();
                }
            }
        }
    }

    /**
     * Illegal state message.
     */
    private static final String DON_T_KNOW_WHAT_TO_DO_WITH = "don't know what to do with ";

    /**
     * Default width.
     */
    private static final int WIDTH = 300;

    /**
     * The {@link Generation#getTemplateFileName() template URI} {@link Text}.
     */
    protected Text templateURIText;

    /**
     * The {@link Generation#getResultFileName() destination URI} {@link Text}.
     */
    protected Text destinationURIText;

    /**
     * The {@link Generation#getDefinitions() variables} {@link TableViewer}.
     */
    protected TableViewer variablesTable;

    /**
     * The {@link Generation#getOptions() options} {@link TableViewer}.
     */
    protected TableViewer optionsTable;

    /**
     * An {@link Adapter} listening to {@link #getGeneration() the edited generation}.
     */
    protected Adapter generationListener;

    /**
     * Current {@link TemplateCustomProperties}.
     */
    protected TemplateCustomProperties templateCustomProperties;

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
        installGenerationListener(generation, generationListener);
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
        nameColumn.setEditingSupport(new OptionNameEditingSupport(optionsTable));
        TableViewerColumn valueColumn = new TableViewerColumn(optionsTable, SWT.NONE);
        valueColumn.getColumn().setText("Option value");
        valueColumn.getColumn().setWidth(WIDTH);
        valueColumn.setEditingSupport(new OptionValueEditingSupport(optionsTable));
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
        nameColumn.setEditingSupport(new VariableNameEditingSupport(variablesTable));
        valueColumn.setLabelProvider(new VariableValueCellLabelProvider());
        valueColumn.setEditingSupport(new VariableValueEditingSupport(variablesTable));
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
                    updateTemplateCustomProperties(URI.createURI(templateURIText.getText()));
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
                    updateTemplateCustomProperties(URI.createURI(relativeTemplatePath));
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
        removeGenerationListener(getGeneration(), generationListener);
        super.dispose();
    }

    /**
     * Installs the listener.
     * 
     * @param generation
     *            the {@link #getGeneration() the edited generation}
     * @param listener
     *            the {@link Adapter}
     */
    protected void installGenerationListener(Generation generation, Adapter listener) {
        generation.eAdapters().add(listener);
        for (Definition definition : generation.getDefinitions()) {
            definition.eAdapters().add(listener);
        }
        for (Option option : generation.getOptions()) {
            option.eAdapters().add(listener);
        }
    }

    /**
     * Removes the listener.
     * 
     * @param generation
     *            the {@link #getGeneration() the edited generation}
     * @param listener
     *            the {@link Adapter}
     */
    private void removeGenerationListener(Generation generation, Adapter listener) {
        generation.eAdapters().remove(listener);
        for (Definition definition : generation.getDefinitions()) {
            definition.eAdapters().remove(listener);
        }
        for (Option option : generation.getOptions()) {
            option.eAdapters().remove(listener);
        }
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
     * Gets the {@link List} of availiable {@link Option#getName() option names}.
     * 
     * @return the {@link List} of availiable {@link Option#getName() option names}
     */
    protected List<String> getAviliableOptionNames() {
        List<String> availableOptions = new ArrayList<String>();

        for (IServicesConfigurator configurator : M2DocUtils.getConfigurators()) {
            availableOptions.addAll(configurator.getOptions());
        }
        for (Option option : getGeneration().getOptions()) {
            availableOptions.remove(option.getName());
        }
        return availableOptions;
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
        addOptionMenu.setEnabled(!getAviliableOptionNames().isEmpty());
        addOptionMenu.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                final Option option = GenconfPackage.eINSTANCE.getGenconfFactory().createOption();
                option.setName(getAviliableOptionNames().get(0));
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

}
