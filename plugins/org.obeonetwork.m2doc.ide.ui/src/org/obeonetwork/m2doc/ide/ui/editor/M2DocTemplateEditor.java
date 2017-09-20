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
package org.obeonetwork.m2doc.ide.ui.editor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.ecore.presentation.EcoreActionBarContributor.ExtendedLoadResourceAction.RegisteredPackageDialog;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.emf.edit.ui.provider.UnwrappingSelectionProvider;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.internal.ui.dialogs.FilteredTypesSelectionDialog;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.ide.ui.Activator;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.services.ServiceRegistry;

/**
 * M2Doc template editor.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
@SuppressWarnings("restriction")
public class M2DocTemplateEditor extends EditorPart {

    /**
     * {@link TemplateCustomProperties#getVariables() variables} name editing support.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private final class VariableNameEditingSupport extends EditingSupport {

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
        private VariableNameEditingSupport(ColumnViewer viewer) {
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

        @SuppressWarnings("unchecked")
        @Override
        protected Object getValue(Object element) {
            final Entry<String, String> entry = (Entry<String, String>) element;
            return entry.getKey();
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void setValue(Object element, Object value) {
            final Entry<String, String> entry = (Entry<String, String>) element;
            if (templateCustomProperties.getVariables().containsKey(value)) {
                // TODO warn user
            } else {
                if (!entry.getKey().equals(value)) {
                    templateCustomProperties.getVariables().put((String) value,
                            templateCustomProperties.getVariables().remove(entry.getKey()));
                    setDirty(true);
                    variablesTable.refresh();
                }
            }
        }

    }

    /**
     * {@link TemplateCustomProperties#getVariables() variables} type editing support.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private final class VariableTypeEditingSupport extends EditingSupport {

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
        private VariableTypeEditingSupport(ColumnViewer viewer) {
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
            List<String> availableTypes = new ArrayList<String>();

            availableTypes.add(TemplateCustomProperties.STRING_TYPE);
            availableTypes.add(TemplateCustomProperties.INTEGER_TYPE);
            availableTypes.add(TemplateCustomProperties.REAL_TYPE);
            availableTypes.add(TemplateCustomProperties.BOOLEAN_TYPE);

            final List<String> availiablesTypes = new ArrayList<String>();
            availableTypes.addAll(getEClassifiers(EcorePackage.eINSTANCE));
            if (templateCustomProperties != null) {
                for (String nsURI : templateCustomProperties.getPackagesURIs()) {
                    final EPackage ePkg = EPackageRegistryImpl.INSTANCE.getEPackage(nsURI);
                    availableTypes.addAll(getEClassifiers(ePkg));
                }
            }
            Collections.sort(availiablesTypes);
            availableTypes.addAll(availiablesTypes);

            editor.setInput(availableTypes);

            return editor;
        }

        /**
         * Gets the {@link List} of all classifiers in the given {@link EPackage}.
         * 
         * @param ePkg
         *            the {@link EPackage}
         * @return the {@link List} of all classifiers in the given {@link EPackage}
         */
        private List<String> getEClassifiers(EPackage ePkg) {
            final List<String> res = new ArrayList<String>();

            for (EClassifier eClassifier : ePkg.getEClassifiers()) {
                res.add(ePkg.getName() + "::" + eClassifier.getName());
            }
            for (EPackage child : ePkg.getESubpackages()) {
                res.addAll(getEClassifiers(child));
            }

            return res;
        }

        @Override
        protected boolean canEdit(Object element) {
            return true;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected Object getValue(Object element) {
            final Entry<String, String> entry = (Entry<String, String>) element;

            return entry.getValue();
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void setValue(Object element, Object value) {
            final Entry<String, String> entry = (Entry<String, String>) element;
            if (!entry.getValue().equals(element)) {
                entry.setValue((String) value);
                setDirty(true);
                variablesTable.refresh();
            }
        }

    }

    /**
     * The Editor ID.
     */
    public static final String ID = "org.obeonetwork.m2doc.ide.ui.editor.M2DocTemplateEditor"; // $NON-NLS-0$

    /**
     * The "delete" string.
     */
    private static final String DELETE = "Delete";

    /**
     * Default width.
     */
    private static final int WIDTH = 300;

    /**
     * Tells if the editor is dirty.
     */
    private boolean dirty;

    /**
     * the template {@link URI}.
     */
    private URI templateURI;
    /**
     * The {@link XWPFDocument}.
     */
    private XWPFDocument document;

    /**
     * The Java project of the edited template.
     */
    private IJavaProject project;

    /**
     * The edited {@link TemplateCustomProperties}.
     */
    private TemplateCustomProperties templateCustomProperties;

    /**
     * The {@link TemplateCustomProperties#getVariables() variables} {@link TableViewer}.
     */
    private TableViewer variablesTable;

    /**
     * The {@link TemplateCustomProperties#getPackagesURIs() packages} {@link TableViewer}.
     */
    private TableViewer packagesTable;

    /**
     * The {@link TemplateCustomProperties#getServiceClasses() services} {@link TableViewer}.
     */
    private TableViewer servicesTable;

    /**
     * Constructor.
     */
    public M2DocTemplateEditor() {
    }

    /**
     * Create contents of the editor part.
     * 
     * @param parent
     */
    @Override
    public void createPartControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new GridLayout(1, false));

        SashForm sashForm = new SashForm(container, SWT.VERTICAL);
        sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

        createVariablesTable(sashForm);

        createPackagesTable(sashForm);

        createServicesTable(sashForm);

        sashForm.setWeights(new int[] {1, 1, 1 });

    }

    /**
     * Creates the {@link TemplateCustomProperties#getVariables() variables} {@link TableViewer}.
     * 
     * @param composite
     *            the parent {@link Composite}
     */
    protected void createServicesTable(Composite composite) {
        servicesTable = new TableViewer(composite, SWT.MULTI);
        servicesTable.getTable().setHeaderVisible(true);
        TableViewerColumn classNameColumn = new TableViewerColumn(servicesTable, SWT.NONE);
        classNameColumn.getColumn().setText("Service class");
        classNameColumn.getColumn().setWidth(WIDTH);
        classNameColumn.setLabelProvider(new CellLabelProvider() {

            @SuppressWarnings("unchecked")
            @Override
            public void update(ViewerCell cell) {
                cell.setText(((Entry<String, String>) cell.getElement()).getKey());
            }
        });
        TableViewerColumn bundleNameColumn = new TableViewerColumn(servicesTable, SWT.NONE);
        bundleNameColumn.getColumn().setText("Bundle");
        bundleNameColumn.getColumn().setWidth(WIDTH);
        bundleNameColumn.setLabelProvider(new CellLabelProvider() {

            @SuppressWarnings("unchecked")
            @Override
            public void update(ViewerCell cell) {
                cell.setText(((Entry<String, String>) cell.getElement()).getValue());
            }
        });
        servicesTable.setContentProvider(new IStructuredContentProvider() {

            @Override
            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
                // nothing to do here
            }

            @Override
            public void dispose() {
                // nothing to do here
            }

            @Override
            public Object[] getElements(Object inputElement) {
                return ((TemplateCustomProperties) inputElement).getServiceClasses().entrySet().toArray();
            }
        });
        createContextMenuFor(servicesTable);
        servicesTable.getControl().getMenu().addListener(SWT.Show, new Listener() {

            @Override
            public void handleEvent(Event event) {
                if (templateCustomProperties != null) {
                    contributeServiceMenus(templateCustomProperties);
                }
            }

        });
        servicesTable.setInput(templateCustomProperties);
    }

    /**
     * Creates the {@link TemplateCustomProperties#getPackagesURIs() packages} {@link TableViewer}.
     * 
     * @param composite
     *            the parent {@link Composite}
     */
    protected void createPackagesTable(Composite composite) {
        packagesTable = new TableViewer(composite, SWT.MULTI);
        packagesTable.getTable().setHeaderVisible(true);
        TableViewerColumn nsURIColumn = new TableViewerColumn(packagesTable, SWT.NONE);
        nsURIColumn.getColumn().setText("Package nsURI");
        nsURIColumn.getColumn().setWidth(WIDTH);
        nsURIColumn.setLabelProvider(new CellLabelProvider() {

            @Override
            public void update(ViewerCell cell) {
                cell.setText((String) cell.getElement());
            }
        });
        packagesTable.setContentProvider(new IStructuredContentProvider() {

            @Override
            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
                // nothing to do here
            }

            @Override
            public void dispose() {
                // nothing to do here
            }

            @Override
            public Object[] getElements(Object inputElement) {
                return ((TemplateCustomProperties) inputElement).getPackagesURIs().toArray();
            }
        });
        createContextMenuFor(packagesTable);
        packagesTable.getControl().getMenu().addListener(SWT.Show, new Listener() {

            @Override
            public void handleEvent(Event event) {
                if (templateCustomProperties != null) {
                    contributePackageMenus(templateCustomProperties);
                }
            }

        });
        packagesTable.setInput(templateCustomProperties);
    }

    /**
     * Creates the {@link TemplateCustomProperties#getVariables() variables} {@link TableViewer}.
     * 
     * @param composite
     *            the parent {@link Composite}
     */
    protected void createVariablesTable(Composite composite) {
        variablesTable = new TableViewer(composite, SWT.MULTI);
        variablesTable.getTable().setHeaderVisible(true);
        TableViewerColumn nameColumn = new TableViewerColumn(variablesTable, SWT.NONE);
        nameColumn.getColumn().setText("Variable name");
        nameColumn.getColumn().setWidth(WIDTH);
        nameColumn.setEditingSupport(new VariableNameEditingSupport(variablesTable));
        nameColumn.setLabelProvider(new CellLabelProvider() {

            @SuppressWarnings("unchecked")
            @Override
            public void update(ViewerCell cell) {
                cell.setText(((Entry<String, String>) cell.getElement()).getKey());
            }
        });
        TableViewerColumn typeColumn = new TableViewerColumn(variablesTable, SWT.NONE);
        typeColumn.getColumn().setText("Variable type");
        typeColumn.getColumn().setWidth(WIDTH);
        typeColumn.setEditingSupport(new VariableTypeEditingSupport(variablesTable));
        typeColumn.setLabelProvider(new CellLabelProvider() {

            @SuppressWarnings("unchecked")
            @Override
            public void update(ViewerCell cell) {
                cell.setText(((Entry<String, String>) cell.getElement()).getValue());
            }
        });
        variablesTable.setContentProvider(new IStructuredContentProvider() {

            @Override
            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
                // nothing to do here
            }

            @Override
            public void dispose() {
                // nothing to do here
            }

            @Override
            public Object[] getElements(Object inputElement) {
                return ((TemplateCustomProperties) inputElement).getVariables().entrySet().toArray();
            }
        });
        createContextMenuFor(variablesTable);
        variablesTable.getControl().getMenu().addListener(SWT.Show, new Listener() {

            @Override
            public void handleEvent(Event event) {
                if (templateCustomProperties != null) {
                    contributeVariablesMenus(templateCustomProperties);
                }
            }

        });
        variablesTable.setInput(templateCustomProperties);
    }

    @Override
    public void setFocus() {
        variablesTable.getTable().setFocus();
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        if (templateCustomProperties != null) {
            templateCustomProperties.save();
            try {
                POIServices.getInstance().saveFile(document, templateURI);
                setDirty(false);
            } catch (IOException e) {
                Activator.getDefault().getLog().log(
                        new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Editor is unable to save the template", e));
            }
        }
    }

    @Override
    public void doSaveAs() {
        // Do the Save As operation
    }

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        setPartName(input.getName());
        setSite(site);
        setInput(input);
        if (input instanceof FileEditorInput) {
            IFile file = ((FileEditorInput) input).getFile();
            project = JavaCore.create(file.getProject());
            try {
                if (project.exists()) {
                    project.open(new NullProgressMonitor());
                }
                templateURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
                document = POIServices.getInstance().getXWPFDocument(templateURI);
                templateCustomProperties = new TemplateCustomProperties(document);
            } catch (IOException e) {
                Activator.getDefault().getLog().log(
                        new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Editor is unable to open the template", e));
            } catch (JavaModelException e) {
                Activator.getDefault().getLog().log(
                        new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Editor is unable to open the template", e));
            }
        }
    }

    @Override
    public boolean isDirty() {
        return dirty;
    }

    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }

    @Override
    public void dispose() {
        if (document != null) {
            try {
                document.close();
            } catch (IOException e) {
                Activator.getDefault().getLog().log(
                        new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Editor is unable to close the template", e));
            }
        }
        super.dispose();
    }

    /**
     * Creates the generic menu for the given {@link StructuredViewer}.
     * 
     * @param viewer
     *            the {@link StructuredViewer}
     */
    protected void createContextMenuFor(StructuredViewer viewer) {
        MenuManager contextMenu = new MenuManager("#PopUp");
        contextMenu.add(new Separator("additions"));
        contextMenu.setRemoveAllWhenShown(true);
        Menu menu = contextMenu.createContextMenu(viewer.getControl());
        viewer.getControl().setMenu(menu);
        getSite().registerContextMenu(contextMenu, new UnwrappingSelectionProvider(viewer));

        int dndOperations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
        Transfer[] transfers = new Transfer[] {LocalTransfer.getInstance(), LocalSelectionTransfer.getTransfer(),
            FileTransfer.getInstance(), };
        viewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(viewer));
    }

    /**
     * Contribute {@link TemplateCustomProperties#getVariables() variables} menus.
     * 
     * @param customProperties
     *            the {@link TemplateCustomProperties}
     */
    protected void contributeVariablesMenus(final TemplateCustomProperties customProperties) {
        new MenuItem(variablesTable.getControl().getMenu(), SWT.SEPARATOR);
        final MenuItem addMissinfVariablesMenu = new MenuItem(variablesTable.getControl().getMenu(), SWT.PUSH);
        addMissinfVariablesMenu.setText("Add missing variables");
        final List<String> missingVariables = customProperties.getMissingVariables();
        addMissinfVariablesMenu.setEnabled(!missingVariables.isEmpty());
        addMissinfVariablesMenu.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                for (String missingVariable : missingVariables) {
                    customProperties.getVariables().put(missingVariable, "");
                    setDirty(true);
                    variablesTable.refresh();
                }
            }
        });

        final MenuItem addVariableMenu = new MenuItem(variablesTable.getControl().getMenu(), SWT.PUSH);
        addVariableMenu.setText("Add variable");
        addVariableMenu.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                customProperties.getVariables().put("", "");
                setDirty(true);
                variablesTable.refresh();
            }
        });

        final MenuItem deleteVariableMenu = new MenuItem(variablesTable.getControl().getMenu(), SWT.PUSH);
        deleteVariableMenu.setText(DELETE);
        deleteVariableMenu.addListener(SWT.Selection, new Listener() {
            @SuppressWarnings("unchecked")
            @Override
            public void handleEvent(Event event) {
                List<Entry<String, String>> selectedElements = ((IStructuredSelection) variablesTable.getSelection())
                        .toList();
                if (!selectedElements.isEmpty()) {
                    for (Entry<String, String> entry : selectedElements) {
                        customProperties.getVariables().remove(entry.getKey());
                    }
                    setDirty(true);
                    variablesTable.refresh();
                }
            }
        });
    }

    /**
     * Contribute {@link TemplateCustomProperties#getPackagesURIs() package} menus.
     * 
     * @param customProperties
     *            the {@link TemplateCustomProperties}
     */
    protected void contributePackageMenus(final TemplateCustomProperties customProperties) {
        new MenuItem(packagesTable.getControl().getMenu(), SWT.SEPARATOR);
        final MenuItem addPackageMenu = new MenuItem(packagesTable.getControl().getMenu(), SWT.PUSH);
        addPackageMenu.setText("Add");
        addPackageMenu.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                RegisteredPackageDialog dialog = new RegisteredPackageDialog(Display.getCurrent().getActiveShell());
                if (dialog.open() == Dialog.OK && dialog.getResult() != null) {
                    if (dialog.getResult().length != 0) {
                        for (Object object : dialog.getResult()) {
                            customProperties.getPackagesURIs().add((String) object);
                        }
                        setDirty(true);
                        packagesTable.refresh();
                    }
                }
            }
        });

        final MenuItem deletePackageMenu = new MenuItem(packagesTable.getControl().getMenu(), SWT.PUSH);
        deletePackageMenu.setText(DELETE);
        deletePackageMenu.addListener(SWT.Selection, new Listener() {

            @SuppressWarnings("unchecked")
            @Override
            public void handleEvent(Event event) {
                List<String> selectedElements = ((IStructuredSelection) packagesTable.getSelection()).toList();
                if (!selectedElements.isEmpty()) {
                    for (String selected : selectedElements) {
                        customProperties.getPackagesURIs().remove(selected);
                    }
                    setDirty(true);
                    packagesTable.refresh();
                }
            }
        });
    }

    /**
     * Contribute {@link TemplateCustomProperties#getServiceClasses() services} menus.
     * 
     * @param customProperties
     *            the {@link TemplateCustomProperties}
     */
    protected void contributeServiceMenus(final TemplateCustomProperties customProperties) {
        new MenuItem(servicesTable.getControl().getMenu(), SWT.SEPARATOR);
        final MenuItem addPackageMenu = new MenuItem(servicesTable.getControl().getMenu(), SWT.PUSH);
        addPackageMenu.setText("Add");
        addPackageMenu.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                FilteredTypesSelectionDialog dialog = new FilteredTypesSelectionDialog(
                        Display.getCurrent().getActiveShell(), true, PlatformUI.getWorkbench().getProgressService(),
                        SearchEngine.createJavaSearchScope(new IJavaElement[] {project, }), IJavaSearchConstants.CLASS);
                if (dialog.open() == Dialog.OK && dialog.getResult() != null) {
                    if (dialog.getResult().length != 0) {
                        for (Object object : dialog.getResult()) {
                            customProperties.getServiceClasses().put(((IType) object).getFullyQualifiedName(),
                                    ((IType) object).getJavaProject().getProject().getName());
                        }
                        setDirty(true);
                        servicesTable.refresh();
                    }
                }
            }
        });

        final MenuItem deletePackageMenu = new MenuItem(servicesTable.getControl().getMenu(), SWT.PUSH);
        deletePackageMenu.setText(DELETE);
        deletePackageMenu.addListener(SWT.Selection, new Listener() {

            @SuppressWarnings("unchecked")
            @Override
            public void handleEvent(Event event) {
                List<Entry<String, String>> selectedElements = ((IStructuredSelection) servicesTable.getSelection())
                        .toList();
                if (!selectedElements.isEmpty()) {
                    for (Entry<String, String> selected : selectedElements) {
                        customProperties.getServiceClasses().remove(selected.getKey());
                    }
                    setDirty(true);
                    servicesTable.refresh();
                }
            }
        });

        final MenuItem tokenPackageMenu = new MenuItem(servicesTable.getControl().getMenu(), SWT.PUSH);
        tokenPackageMenu.setText("Select tokens");
        tokenPackageMenu.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event event) {
                ServiceTokenSelectionDialog dialog = new ServiceTokenSelectionDialog(getSite().getShell(),
                        ServiceRegistry.INSTANCE, templateCustomProperties);
                dialog.open();
                if (dialog.hasChanges()) {
                    setDirty(true);
                    servicesTable.refresh();
                }
            }
        });
    }

    /**
     * Sets the editor {@link #isDirty() dirty}.
     * 
     * @param value
     *            the new dirty value
     */
    protected void setDirty(boolean value) {
        if (dirty != value) {
            dirty = value;
            firePropertyChange(PROP_DIRTY);
        }
    }

}
