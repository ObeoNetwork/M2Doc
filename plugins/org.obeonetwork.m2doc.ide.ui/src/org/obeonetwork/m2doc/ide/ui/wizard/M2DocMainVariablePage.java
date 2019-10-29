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
package org.obeonetwork.m2doc.ide.ui.wizard;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.action.LoadResourceAction.LoadResourceDialog;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;

/**
 * Template file name page.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class M2DocMainVariablePage extends WizardPage {

    /**
     * The variable name.
     */
    private String variableName;

    /**
     * The variable value.
     */
    private EObject variableValue;

    /**
     * The {@link AdapterFactory}.
     */
    private final ComposedAdapterFactory adapterFactory;

    /**
     * The {@link ResourceSet}.
     */
    private final ResourceSet resourceSet = new ResourceSetImpl();

    /**
     * The {@link TransactionalEditingDomain} for our {@link ResourceSet}.
     */
    private final TransactionalEditingDomain editingDomain;

    /**
     * Constructor.
     */
    public M2DocMainVariablePage() {
        super("Set the main variable value and name for your template.");
        adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
        adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
        editingDomain = new TransactionalEditingDomainImpl(adapterFactory, resourceSet);
    }

    @Override
    public void createControl(Composite parent) {
        final Composite pageComposite = new Composite(parent, SWT.NONE);
        pageComposite.setLayout(new GridLayout(1, false));

        final TreeViewer treeViewer = createVariableValueComposite(pageComposite);
        final Text variableNameText = createVariableNameComposite(pageComposite);

        treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                final Object selected = ((IStructuredSelection) event.getSelection()).getFirstElement();
                if (selected instanceof EObject) {
                    final boolean wasDefaultName = variableValue == null
                        || ("my" + variableValue.eClass().getName()).equals(variableNameText.getText());
                    variableValue = (EObject) selected;
                    if (wasDefaultName) {
                        variableNameText.setText("my" + variableValue.eClass().getName());
                        variableName = variableNameText.getText();
                    }
                }
                validatePage();
            }
        });

        validatePage();

        setControl(pageComposite);
    }

    /**
     * Creates the variable name {@link Composite}.
     * 
     * @param pageComposite
     *            the page {@link Composite}
     * @return the variable name {@link Text}
     */
    private Text createVariableNameComposite(Composite pageComposite) {
        final Composite variableNameComposite = new Composite(pageComposite, SWT.NONE);
        variableNameComposite.setLayout(new GridLayout(2, false));
        variableNameComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
        final Label variableNameLabel = new Label(variableNameComposite, SWT.None);
        variableNameLabel.setText("Variable name:");
        final Text variableNameText = new Text(variableNameComposite, SWT.NONE);
        variableNameText.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(ModifyEvent e) {
                variableName = variableNameText.getText();
                validatePage();
            }
        });

        variableNameText.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                variableName = variableNameText.getText();
                validatePage();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // nothing to do here
            }
        });
        variableNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        return variableNameText;
    }

    /**
     * Creates the variable value {@link Composite}.
     * 
     * @param pageComposite
     *            the page {@link Composite}
     * @return the created {@link TreeViewer}
     */
    private TreeViewer createVariableValueComposite(Composite pageComposite) {
        final Composite valueComposite = new Composite(pageComposite, SWT.NONE);
        valueComposite.setLayout(new GridLayout(2, false));
        valueComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        final int style = SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER;
        final FilteredTree filteredTree = new FilteredTree(valueComposite, style, new PatternFilter(), true);
        filteredTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        final TreeViewer treeViewer = filteredTree.getViewer();
        treeViewer.getTree().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
        treeViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));

        final Button loadResourceButton = new Button(valueComposite, SWT.NONE);
        loadResourceButton.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
        loadResourceButton.setText("Load resource");
        loadResourceButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                LoadResourceDialog dialog = new LoadResourceDialog(getShell(), editingDomain);
                dialog.open();
                treeViewer.refresh();
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                // nothing to do here
            }
        });

        treeViewer.setInput(resourceSet);

        return treeViewer;
    }

    /**
     * Validates the page with the given template name.
     */
    private void validatePage() {
        if (variableValue instanceof EObject) {
            if (variableName != null && TemplateCustomProperties.isValidVariableName(variableName)) {
                setErrorMessage(null);
                setPageComplete(true);
            } else {
                setErrorMessage("Invalid variable name.");
                setPageComplete(false);
            }
        } else {
            setErrorMessage("The variable value should be a model element.");
            setPageComplete(false);
        }
    }

    /**
     * Gets the variable name.
     * 
     * @return the variable name
     */
    public String getVariableName() {
        return variableName;
    }

    /**
     * Gets the variable value.
     * 
     * @return the variable value
     */
    public EObject getVariableValue() {
        return variableValue;
    }

}
