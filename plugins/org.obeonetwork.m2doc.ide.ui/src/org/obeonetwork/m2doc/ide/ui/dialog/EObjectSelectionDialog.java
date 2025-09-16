/*******************************************************************************
 *  Copyright (c) 2019, 2025 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.ide.ui.dialog;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.validation.type.EClassifierType;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.ui.action.LoadResourceAction.LoadResourceDialog;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * {@link EObject} selection dialog.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class EObjectSelectionDialog extends MessageDialog {

    /**
     * The table minimum height.
     */
    private static final int TABLE_MINIMUM_HEIGHT = 400;

    /**
     * The table minimum width.
     */
    private static final int TABLE_MINIMUM_WIDTH = 200;

    /**
     * The selected value.
     */
    protected Object value;

    /**
     * The {@link AdapterFactory}.
     */
    private AdapterFactory adapterFactory;

    /**
     * The {@link ResourceSet} for model.
     */
    private final ResourceSet resourceSet;

    /**
     * The {@link IReadOnlyQueryEnvironment}.
     */
    private IReadOnlyQueryEnvironment queryEnvironment;

    /**
     * The {@link Set} of accepted {@link EClass}.
     */
    private Set<EClass> acceptedEClasses;

    /**
     * Tells if we should allow multi selection.
     */
    private boolean multiSelection;

    /**
     * Constructor.
     * 
     * @param parentShell
     *            the parent {@link Shell}
     * @param adapterFactory
     *            the {@link AdapterFactory}
     * @param title
     *            the title of the dialog
     * @param message
     *            the message of the dialog
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param resourceSet
     *            the {@link ResourceSet} for model
     * @param acceptedEClasses
     *            the {@link Set} of accepted {@link EClass}
     */
    public EObjectSelectionDialog(Shell parentShell, AdapterFactory adapterFactory, String title, String message,
            IReadOnlyQueryEnvironment queryEnvironment, ResourceSet resourceSet, Set<EClass> acceptedEClasses) {
        super(parentShell, title, null, message, MessageDialog.QUESTION,
                new String[] {IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL }, 0);
        this.resourceSet = resourceSet;
        this.adapterFactory = adapterFactory;
        this.queryEnvironment = queryEnvironment;
        this.acceptedEClasses = acceptedEClasses;
    }

    /**
     * Sets the multi selection.
     * 
     * @param multiSelection
     *            the multiSelection to set
     */
    public void setMultiSelection(boolean multiSelection) {
        this.multiSelection = multiSelection;
    }

    @Override
    protected Control createCustomArea(Composite parent) {
        final Composite container = new Composite(parent, parent.getStyle());
        container.setLayout(new GridLayout(1, false));
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

        final Set<IType> acceptedTypes = new HashSet<>();
        for (EClass eCls : acceptedEClasses) {
            acceptedTypes.add(new EClassifierType(queryEnvironment, eCls));
        }
        createModelCustomArea(acceptedTypes, container);

        return container;
    }

    /**
     * Creates the {@link #createCustomArea(Composite) custom area} for the given {@link ModelDefinition}.
     * 
     * @param acceptedTypes
     *            the {@link Set} of accepted {@link IType}
     * @param container
     *            the container {@link Composite}
     */
    protected void createModelCustomArea(final Set<IType> acceptedTypes, Composite container) {
        final int style;
        if (multiSelection) {
            style = SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.CHECK;
        } else {
            style = SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER;
        }
        final FilteredTree filteredTree = new FilteredTree(container, style, new PatternFilter(), true, true);
        final TreeViewer treeViewer = filteredTree.getViewer();

        if (multiSelection) {
            treeViewer.getTree().addSelectionListener(new SelectionListener() {

                @Override
                public void widgetSelected(SelectionEvent event) {
                    handleEvent(acceptedTypes, event);
                }

                @Override
                public void widgetDefaultSelected(SelectionEvent event) {
                    // nothing to do here
                }
            });
        } else {
            treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

                @Override
                public void selectionChanged(SelectionChangedEvent event) {
                    final Object selected = ((IStructuredSelection) event.getSelection()).getFirstElement();
                    final boolean enableOKButton;
                    if (selected instanceof EObject) {
                        final EObject eObj = (EObject) selected;
                        if (isValidValue(acceptedTypes, eObj)) {
                            value = eObj;
                            enableOKButton = true;
                        } else {
                            enableOKButton = false;
                        }
                    } else {
                        enableOKButton = false;
                    }
                    final Button okButton = getButton(OK);
                    if (okButton != null) {
                        okButton.setEnabled(enableOKButton);
                    }
                }
            });
        }
        treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
        treeViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
        final GridData gdTable = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        gdTable.minimumWidth = TABLE_MINIMUM_WIDTH;
        gdTable.minimumHeight = TABLE_MINIMUM_HEIGHT;
        treeViewer.getTree().setLayoutData(gdTable);

        final Set<EStructuralFeature> containingFeatures = new LinkedHashSet<>();
        final Set<EClass> eClasses = new LinkedHashSet<>();
        for (IType type : acceptedTypes) {
            if (type.getType() instanceof EClass) {
                final EClass eCls = (EClass) type.getType();
                eClasses.add(eCls);
                Set<EStructuralFeature> features = queryEnvironment.getEPackageProvider()
                        .getAllContainingEStructuralFeatures(eCls);
                containingFeatures.addAll(features);
            }
        }

        final ViewerFilter filter = new EObjectViewerFilter(containingFeatures, eClasses);
        treeViewer.addFilter(filter);

        treeViewer.setInput(resourceSet);
        final Button okButton = getButton(OK);
        if (okButton != null) {
            okButton.setEnabled(multiSelection);
        }

        final Composite filterCheckboxComposite = new Composite(container, container.getStyle());
        filterCheckboxComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        filterCheckboxComposite.setLayout(new GridLayout(3, false));
        final Button filterCheckBox = new Button(filterCheckboxComposite, SWT.CHECK);
        filterCheckBox.setSelection(true);
        filterCheckBox.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                Button bouton = (Button) e.getSource();
                if (bouton.getSelection()) {
                    treeViewer.addFilter(filter);
                    if (treeViewer.getTree().getItemCount() == 0) {
                        messageLabel.setText(message
                            + " (the tree is empty, you can either load a resource from the previous dialog or deselect the type filter)");
                    }
                } else {
                    treeViewer.removeFilter(filter);
                    if (treeViewer.getTree().getItemCount() == 0) {
                        messageLabel.setText(
                                message + " (the tree is empty, you can load a resource from the previous dialog)");
                    }
                }
            }

        });
        if (treeViewer.getTree().getItemCount() == 0) {
            messageLabel.setText(message
                + " (the tree is empty, you can either load a resource from the previous dialog or deselect the type filter)");
        }

        final Label filterLabel = new Label(filterCheckboxComposite, container.getStyle());
        filterLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
        filterLabel.setText("Filter elements by type");

        createLoadResourceButton(filterCheckboxComposite);
    }

    /**
     * Handles multi selection tree events.
     * 
     * @param acceptedTypes
     *            the {@link Set} of accepted {@link IType}.
     * @param event
     *            the {@link SelectionEvent}
     */
    private void handleEvent(final Set<IType> acceptedTypes, SelectionEvent event) {
        if (event.detail == SWT.CHECK) {
            final TreeItem item = (TreeItem) event.item;
            final EObject eObj = (EObject) item.getData();
            if (item.getChecked()) {
                if (isValidValue(acceptedTypes, eObj)) {
                    ((Collection<Object>) value).add(eObj);
                } else {
                    item.setChecked(false);
                }
            } else {
                ((Collection<Object>) value).remove(eObj);
            }
        }
    }

    /**
     * Creates the load resource {@link Button}.
     * 
     * @param parent
     *            the parent {@link Composite}
     */
    protected void createLoadResourceButton(final Composite parent) {
        final Button loadResourceButton = new Button(parent, parent.getStyle());
        loadResourceButton.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, true, false, 1, 1));
        loadResourceButton.setText("Load resource");
        loadResourceButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(resourceSet);
                if (editingDomain == null) {
                    editingDomain = new TransactionalEditingDomainImpl(adapterFactory, resourceSet);
                }
                LoadResourceDialog dialog = new LoadResourceDialog(getShell(), editingDomain);
                dialog.open();
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                // nothing to do here
            }
        });
    }

    /**
     * Tells if the given {@link Set} of accepted {@link IType} and the given {@link EObject} are compatible.
     * 
     * @param acceptedTypes
     *            the {@link Set} of accepted {@link IType}
     * @param eObj
     *            the {@link EObject}
     * @return <code>true</code> if the given {@link ModelDefinition} and the given {@link EObject} are compatible, <code>false</code>
     *         otherwise
     */
    private boolean isValidValue(Set<IType> acceptedTypes, EObject eObj) {
        boolean res = false;

        final EClassifierType eObjType = new EClassifierType(queryEnvironment, eObj.eClass());

        for (IType definitionType : acceptedTypes) {
            if (definitionType.isAssignableFrom(eObjType)) {
                res = true;
            }
        }

        return res;
    }

    public Object getValue() {
        return value;
    }

}
