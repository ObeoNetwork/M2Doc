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
package org.obeonetwork.m2doc.ui.dialog;

import java.util.EventObject;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.presentation.EcoreActionBarContributor.ExtendedLoadResourceAction.RegisteredPackageDialog;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.obeonetwork.m2doc.genconf.emf.ui.celleditor.URIDialogCellEditor;
import org.obeonetwork.m2doc.tplconf.EPackageMapping;
import org.obeonetwork.m2doc.tplconf.StructuredType;
import org.obeonetwork.m2doc.tplconf.TemplateConfig;
import org.obeonetwork.m2doc.tplconf.TemplateType;
import org.obeonetwork.m2doc.tplconf.TemplateVariable;
import org.obeonetwork.m2doc.tplconf.TplconfFactory;
import org.obeonetwork.m2doc.tplconf.impl.TplconfFactoryImpl;
import org.obeonetwork.m2doc.tplconf.provider.spec.TplconfItemProviderAdapterFactorySpec;
import org.obeonetwork.m2doc.ui.Activator;
import org.obeonetwork.m2doc.util.TemplateConfigUtil;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Dialog to configuration M2Doc template properties (nsURI and variable declarations).
 * 
 * @author ldelaigue
 */
public class ConfigureTemplateDialog extends TitleAreaDialog {
    private final TemplateConfig config;
    private ComposedAdapterFactory adapterFactory;
    private Table mmTable;
    private Table varTable;
    private TableViewer varTableViewer;
    private TableViewer mmTableViewer;
    private TransactionalEditingDomain editingDomain;

    private Map<String, EPackageMapping> mappingsByUri = new LinkedHashMap<String, EPackageMapping>();

    /**
     * Create the dialog.
     * 
     * @param parentShell
     *            The shell
     * @param config
     *            The template config
     */
    public ConfigureTemplateDialog(Shell parentShell, TemplateConfig config) {
        super(parentShell);
        setShellStyle(SWT.CLOSE | SWT.RESIZE | SWT.TITLE | SWT.APPLICATION_MODAL);
        this.config = checkNotNull(config);
        for (EPackageMapping mapping : config.getMappings()) {
            if (mapping.getUri() != null) {
                mappingsByUri.put(mapping.getUri(), mapping);
            }
        }
    }

    /**
     * Create contents of the dialog.
     * 
     * @param parent
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        setTitle("Configure Template");
        setMessage("Configure the meta-models and variables used by your template.");
        adapterFactory = createAdapterfactory();
        editingDomain = new TransactionalEditingDomainImpl(adapterFactory);
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
            @Override
            protected void doExecute() {
                Resource r = new ResourceImpl();
                editingDomain.getResourceSet().getResources().add(r);
                r.getContents().add(config);
            }
        });
        editingDomain.getCommandStack().flush();
        Composite area = (Composite) super.createDialogArea(parent);
        Composite container = new Composite(area, SWT.NONE);
        FillLayout fl_container = new FillLayout();
        fl_container.type = SWT.VERTICAL;
        container.setLayout(fl_container);
        container.setLayoutData(new GridData(GridData.FILL_BOTH));

        CTabFolder tabFolder = new CTabFolder(container, SWT.BORDER);
        tabFolder.setSelectionBackground(
                Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
        tabFolder.setVisible(true);

        CTabItem tbtmMetamodels = new CTabItem(tabFolder, SWT.NONE);
        tbtmMetamodels.setText("Meta-Models");
        ScrolledComposite mmScrolledComposite = createScrolledComposite(tabFolder, tbtmMetamodels);

        createMetamodelsTable(mmScrolledComposite);

        CTabItem tbtmVariables = new CTabItem(tabFolder, SWT.NONE);
        tbtmVariables.setText("Variables");
        ScrolledComposite varScrolledComposite = createScrolledComposite(tabFolder, tbtmVariables);

        createVariablesTable(varScrolledComposite);

        editingDomain.getCommandStack().addCommandStackListener(new CommandStackListener() {
            @Override
            public void commandStackChanged(EventObject event) {
                Command mostRecentCommand = ((CommandStack) event.getSource()).getMostRecentCommand();
                if (!(mostRecentCommand instanceof AbstractCommand.NonDirtying)) {
                    varTableViewer.refresh();
                    mmTableViewer.refresh();
                    validate();
                }
            }
        });

        return area;
    }

    protected ScrolledComposite createScrolledComposite(CTabFolder tabFolder, CTabItem tabItem) {
        ScrolledComposite mmScrolledComposite = new ScrolledComposite(tabFolder,
                SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        mmScrolledComposite.setExpandHorizontal(true);
        mmScrolledComposite.setExpandVertical(true);
        tabItem.setControl(mmScrolledComposite);
        return mmScrolledComposite;
    }

    protected void createMetamodelsTable(ScrolledComposite mmScrolledComposite) {
        Composite mmComposite = new Composite(mmScrolledComposite, SWT.NONE);
        mmComposite.setLayout(new GridLayout(2, false));

        mmTableViewer = new TableViewer(mmComposite, SWT.BORDER | SWT.FULL_SELECTION);
        mmTable = mmTableViewer.getTable();
        mmTable.setLinesVisible(true);
        mmTable.setHeaderVisible(true);
        mmTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

        TableViewerColumn mmNameTVColumn = new TableViewerColumn(mmTableViewer, SWT.NONE);
        TableColumn mmNameColumn = mmNameTVColumn.getColumn();
        mmNameColumn.setWidth(110);
        mmNameColumn.setText("Name");
        mmNameTVColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof EPackageMapping) {
                    return ((EPackageMapping) element).getName();
                }
                return super.getText(element);
            }
        });
        mmNameTVColumn.setEditingSupport(new EditingSupport(mmTableViewer) {

            @Override
            protected void setValue(Object element, Object value) {
                if (element instanceof EPackageMapping && value instanceof EPackage) {
                    EPackage ePackage = (EPackage) value;
                    EPackageMapping toUpdate = (EPackageMapping) element;
                    if (!ePackage.getNsURI().equals(toUpdate.getUri())) {
                        mappingsByUri.remove(toUpdate.getUri());
                        mappingsByUri.put(ePackage.getNsURI(), toUpdate);
                    }
                    toUpdate.setName(ePackage.getName());
                    toUpdate.setEPackage(ePackage);
                    toUpdate.setUri(ePackage.getNsURI());
                }
            }

            @Override
            protected Object getValue(Object element) {
                if (element instanceof EPackageMapping) {
                    return ((EPackageMapping) element).getName();
                }
                return null;
            }

            @Override
            protected CellEditor getCellEditor(Object element) {
                return new URIDialogCellEditor(mmTable);
            }

            @Override
            protected boolean canEdit(Object element) {
                return true;
            }
        });

        TableViewerColumn mmURITVColumn = new TableViewerColumn(mmTableViewer, SWT.NONE);
        TableColumn mmURIColumn = mmURITVColumn.getColumn();
        mmURIColumn.setWidth(250);
        mmURIColumn.setText("nsURI");
        mmURITVColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof EPackageMapping) {
                    return ((EPackageMapping) element).getUri();
                }
                return super.getText(element);
            }
        });
        mmURITVColumn.setEditingSupport(new EditingSupport(mmTableViewer) {
            @Override
            protected void setValue(final Object element, final Object value) {
                if (element instanceof EPackageMapping && value instanceof String) {
                    editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
                        @Override
                        protected void doExecute() {
                            String uri = (String) value;
                            EPackageMapping mm = (EPackageMapping) element;
                            EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(uri);
                            if (uri != null && !uri.equals(mm.getUri())) {
                                mappingsByUri.remove(mm.getUri());
                                mappingsByUri.put(uri, mm);
                            }
                            if (ePackage == null) {
                                mm.setName(null);
                                mm.setEPackage(null);
                            } else {
                                mm.setName(ePackage.getName());
                                mm.setEPackage(ePackage);
                            }
                            mm.setUri(uri);
                        }
                    });
                }
            }

            @Override
            protected Object getValue(Object element) {
                if (element instanceof EPackageMapping) {
                    return ((EPackageMapping) element).getUri();
                }
                return null;
            }

            @Override
            protected CellEditor getCellEditor(Object element) {
                return new TextCellEditor(mmTable);
            }

            @Override
            protected boolean canEdit(Object element) {
                return true;
            }
        });

        mmTableViewer.setContentProvider(new IStructuredContentProvider() {
            @Override
            public Object[] getElements(Object inputElement) {
                if (inputElement instanceof TemplateConfig) {
                    TemplateConfig cfg = (TemplateConfig) inputElement;
                    return cfg.getMappings().toArray(new Object[cfg.getMappings().size()]);
                }
                return new Object[0];
            }

            @Override
            public void dispose() {
                // Nothing
            }

            @Override
            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
                // Nothing
            }
        });
        mmTableViewer.setInput(config);

        Composite mmButtonsComp = new Composite(mmComposite, SWT.NONE);
        RowLayout rl_mmButtonsComp = new RowLayout(SWT.VERTICAL);
        rl_mmButtonsComp.center = true;
        rl_mmButtonsComp.justify = true;
        rl_mmButtonsComp.fill = true;
        mmButtonsComp.setLayout(rl_mmButtonsComp);
        mmButtonsComp.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false, 1, 1));

        Button btnAddURI = new Button(mmButtonsComp, SWT.NONE);
        btnAddURI.setImage(Activator.getDefault().getImageRegistry().get(Activator.ADD_IMG_KEY));
        btnAddURI.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                RegisteredPackageDialog dialog = new RegisteredPackageDialog(Display.getCurrent().getActiveShell());
                dialog.setMultipleSelection(false);
                dialog.open();
                if (dialog.getReturnCode() == Dialog.OK) {
                    Object firstResult = dialog.getFirstResult();
                    if (firstResult instanceof String) {
                        String uri = (String) firstResult;
                        final EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(uri);
                        if (ePackage != null) {
                            editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
                                @Override
                                protected void doExecute() {
                                    // Check whether this mapping is already here...
                                    if (mappingsByUri.containsKey(ePackage.getNsURI())) {
                                        // Just update the existing mapping
                                        EPackageMapping mapping = mappingsByUri.get(ePackage.getNsURI());
                                        mapping.setEPackage(ePackage);
                                        mapping.setName(ePackage.getName());
                                        mapping.setUri(ePackage.getNsURI());
                                    } else {
                                        EPackageMapping newMM = TplconfFactory.eINSTANCE.createEPackageMapping();
                                        newMM.setName(ePackage.getName());
                                        newMM.setUri(ePackage.getNsURI());
                                        newMM.setEPackage(ePackage);
                                        config.getMappings().add(newMM);
                                        mappingsByUri.put(ePackage.getNsURI(), newMM);
                                    }
                                }
                            });
                        }
                    }
                }
            }
        });

        final Button btnDelURI = new Button(mmButtonsComp, SWT.NONE);
        btnDelURI.setImage(Activator.getDefault().getImageRegistry().get(Activator.DELETE_IMG_KEY));
        mmScrolledComposite.setContent(mmComposite);
        mmScrolledComposite.setMinSize(mmComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

        mmTableViewer.addPostSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                if (event.getSelection().isEmpty()) {
                    btnDelURI.setEnabled(false);
                } else {
                    btnDelURI.setEnabled(true);
                }
            }
        });

        btnDelURI.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                IStructuredSelection selection = (IStructuredSelection) mmTableViewer.getSelection();
                if (!selection.isEmpty() && (selection.getFirstElement() instanceof EPackageMapping)) {
                    final EPackageMapping mm = (EPackageMapping) selection.getFirstElement();
                    editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
                        @Override
                        protected void doExecute() {
                            mappingsByUri.remove(mm.getUri());
                            config.getMappings().remove(mm);
                        }
                    });
                }
            }
        });
    }

    protected void createVariablesTable(ScrolledComposite varScrolledComposite) {
        Composite varComposite = new Composite(varScrolledComposite, SWT.NONE);
        varComposite.setLayout(new GridLayout(2, false));

        varTableViewer = new TableViewer(varComposite, SWT.BORDER | SWT.FULL_SELECTION);
        varTable = varTableViewer.getTable();
        varTable.setLinesVisible(true);
        varTable.setHeaderVisible(true);
        varTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

        TableViewerColumn varNameTVColumn = new TableViewerColumn(varTableViewer, SWT.NONE);
        TableColumn varNameColumn = varNameTVColumn.getColumn();
        varNameColumn.setWidth(110);
        varNameColumn.setText("Name");
        varNameTVColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof TemplateVariable) {
                    return ((TemplateVariable) element).getName();
                }
                return super.getText(element);
            }
        });
        varNameTVColumn.setEditingSupport(new EditingSupport(varTableViewer) {
            @Override
            protected void setValue(final Object element, final Object value) {
                if (element instanceof TemplateVariable && value instanceof String) {
                    editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
                        @Override
                        protected void doExecute() {
                            ((TemplateVariable) element).setName((String) value);
                        }
                    });
                }
            }

            @Override
            protected Object getValue(Object element) {
                if (element instanceof TemplateVariable) {
                    return ((TemplateVariable) element).getName();
                }
                return null;
            }

            @Override
            protected CellEditor getCellEditor(Object element) {
                TextCellEditor editor = new TextCellEditor(varTable);
                editor.setValidator(new ICellEditorValidator() {

                    @Override
                    public String isValid(Object value) {
                        if (value instanceof String) {
                            String s = (String) value;
                            if (TemplateConfigUtil.isValidVariableName(s)) {
                                return null;
                            }
                            return "Variable name is not valid.";
                        }
                        return "Value must be a String.";
                    }
                });
                return editor;
            }

            @Override
            protected boolean canEdit(Object element) {
                return true;
            }
        });

        TableViewerColumn varTypeTVColumn = new TableViewerColumn(varTableViewer, SWT.NONE);
        TableColumn varTypeColumn = varTypeTVColumn.getColumn();
        varTypeColumn.setWidth(250);
        varTypeColumn.setText("Type");
        varTypeTVColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof TemplateVariable) {
                    return ((TemplateVariable) element).getTypeName();
                }
                return super.getText(element);
            }
        });
        varTypeTVColumn.setEditingSupport(new EditingSupport(varTableViewer) {
            @Override
            protected void setValue(final Object element, final Object value) {
                if (element instanceof TemplateVariable && value instanceof TemplateVariable) {
                    editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
                        @Override
                        protected void doExecute() {
                            TemplateVariable toUpdate = (TemplateVariable) element;
                            TemplateVariable copy = (TemplateVariable) value;
                            String oldName = toUpdate.getName();
                            if (copy.getName() != null && !copy.getName().equals(oldName)) {
                                toUpdate.setName(copy.getName());
                            }
                            toUpdate.setTypeName(copy.getTypeName());
                            toUpdate.setType(copy.getType());
                            postVariableChange(toUpdate);
                        }
                    });
                }
            }

            @Override
            protected Object getValue(Object element) {
                if (element instanceof TemplateVariable) {
                    return ((TemplateVariable) element).getTypeName();
                }
                return null;
            }

            @Override
            protected CellEditor getCellEditor(Object element) {
                if (element instanceof TemplateVariable) {
                    TemplateVariable var = (TemplateVariable) element;
                    final TemplateVariable copy = TplconfFactoryImpl.eINSTANCE.createTemplateVariable();
                    copy.setName(var.getName());
                    copy.setTypeName(var.getTypeName());
                    copy.setType(var.getType());
                    return new DialogCellEditor(varTable, SWT.NONE) {
                        @Override
                        protected Object openDialogBox(Control cellEditorWindow) {
                            VariableEditDialog dlg = new VariableEditDialog(getParentShell(), copy, config,
                                    adapterFactory);
                            if (dlg.open() == Dialog.OK) {
                                return copy;
                            }
                            return null;
                        }
                    };
                }
                return null;
            }

            @Override
            protected boolean canEdit(Object element) {
                return true;
            }
        });

        varTableViewer.setContentProvider(new IStructuredContentProvider() {
            @Override
            public Object[] getElements(Object inputElement) {
                if (inputElement instanceof TemplateConfig) {
                    TemplateConfig cfg = (TemplateConfig) inputElement;
                    List<TemplateVariable> result = cfg.getVariables();
                    return result.toArray(new Object[result.size()]);
                }
                return new Object[0];
            }

            @Override
            public void dispose() {
                // Nothing
            }

            @Override
            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
                // Nothing
            }
        });
        varTableViewer.setInput(config);

        Composite varButtonsComp = new Composite(varComposite, SWT.NONE);
        varButtonsComp.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false, 1, 1));
        RowLayout rl_varButtonsComp = new RowLayout(SWT.VERTICAL);
        rl_varButtonsComp.justify = true;
        rl_varButtonsComp.fill = true;
        rl_varButtonsComp.center = true;
        varButtonsComp.setLayout(rl_varButtonsComp);

        Button btnAddVar = new Button(varButtonsComp, SWT.NONE);
        btnAddVar.setImage(Activator.getDefault().getImageRegistry().get(Activator.ADD_IMG_KEY));
        btnAddVar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                final TemplateVariable newVar = TplconfFactory.eINSTANCE.createTemplateVariable();
                newVar.setName("newVar");
                VariableEditDialog dlg = new VariableEditDialog(getParentShell(), newVar, config, adapterFactory);
                if (dlg.open() == Dialog.OK) {
                    editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
                        @Override
                        protected void doExecute() {
                            config.getVariables().add(newVar);
                            postVariableChange(newVar);
                        }
                    });
                }
            }
        });

        final Button btnDelVar = new Button(varButtonsComp, SWT.NONE);
        btnDelVar.setImage(Activator.getDefault().getImageRegistry().get(Activator.DELETE_IMG_KEY));
        varScrolledComposite.setContent(varComposite);
        varScrolledComposite.setMinSize(varComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

        varTableViewer.addPostSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                if (event.getSelection().isEmpty()) {
                    btnDelVar.setEnabled(false);
                } else {
                    btnDelVar.setEnabled(true);
                }
            }
        });

        btnDelVar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                final IStructuredSelection selection = (IStructuredSelection) varTableViewer.getSelection();
                if (!selection.isEmpty() && (selection.getFirstElement() instanceof TemplateVariable)) {
                    editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
                        @Override
                        protected void doExecute() {
                            config.getVariables().remove(selection.getFirstElement());
                        }
                    });
                }
            }
        });
    }

    private void postVariableChange(TemplateVariable variable) {
        TemplateType type = variable.getType();
        if (type != null && !config.getTypesByName().values().contains(type)) {
            config.getTypesByName().put(type.getName(), type);
            if (type instanceof StructuredType) {
                EPackageMapping mapping = ((StructuredType) type).getMapping();
                if (mapping != null && !mappingsByUri.containsValue(mapping)) {
                    mappingsByUri.put(mapping.getUri(), mapping);
                }
            }
        }
    }

    protected ComposedAdapterFactory createAdapterfactory() {
        ComposedAdapterFactory af = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        af.addAdapterFactory(new TplconfItemProviderAdapterFactorySpec());
        af.addAdapterFactory(new EcoreAdapterFactory());
        af.addAdapterFactory(new ResourceItemProviderAdapterFactory());
        af.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
        return af;
    }

    protected void validate() {
        boolean valid = true;
        for (TemplateVariable var : config.getVariables()) {
            if (!TemplateConfigUtil.isValidVariableName(var.getName())
                || !TemplateConfigUtil.isValidTypeName(var.getTypeName())) {
                valid = false;
            }
        }
        if (valid) {
            getButton(IDialogConstants.OK_ID).setEnabled(true);
            setErrorMessage(null);
        } else {
            getButton(IDialogConstants.OK_ID).setEnabled(false);
            setErrorMessage("The configuration is not valid, check the variables.");
        }
    }

    /**
     * Create contents of the button bar.
     * 
     * @param parent
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }

    /**
     * Return the initial size of the dialog.
     */
    @Override
    protected Point getInitialSize() {
        return new Point(470, 400);
    }
}
