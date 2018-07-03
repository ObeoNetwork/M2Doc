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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.obeonetwork.m2doc.ide.ui.wizard.SelectRegisteredTemplatePage.CollectionContentProvider;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;

/**
 * File selection dialog.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class M2DocTypeSelectionDialog extends MessageDialog {

    /**
     * The table minimum height.
     */
    private static final int TABLE_MINIMUM_HEIGHT = 400;

    /**
     * The table minimum width.
     */
    private static final int TABLE_MINIMUM_WIDTH = 200;

    /**
     * The variable name.
     */
    private String variableName;

    /**
     * The {@link TemplateCustomProperties}.
     */
    private TemplateCustomProperties properties;

    /**
     * The selected type.
     */
    private String selectedType;

    /**
     * Constructor.
     * 
     * @param parentShell
     *            the parent {@link Shell}
     * @param variableName
     *            the variable name
     * @param properties
     *            the {@link TemplateCustomProperties}
     */
    public M2DocTypeSelectionDialog(Shell parentShell, String variableName, TemplateCustomProperties properties) {
        super(parentShell, "Select a variable type for " + variableName, null, "Select a type.", MessageDialog.QUESTION,
                new String[] {IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL }, 0);
        this.variableName = variableName;
        this.properties = properties;
    }

    @Override
    protected Control createCustomArea(Composite parent) {
        final Composite container = new Composite(parent, SWT.NULL);
        container.setLayout(new GridLayout(1, false));
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

        final FilteredTree filteredTree = new FilteredTree(container, SWT.BORDER, new PatternFilter(), true);
        final TreeViewer treeViewer = filteredTree.getViewer();
        final Tree tree = treeViewer.getTree();
        final GridData gdTable = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        gdTable.minimumWidth = TABLE_MINIMUM_WIDTH;
        gdTable.minimumHeight = TABLE_MINIMUM_HEIGHT;
        tree.setLayoutData(gdTable);
        treeViewer.setContentProvider(new CollectionContentProvider());
        treeViewer.setLabelProvider(new LabelProvider());
        treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                final Object selected = ((IStructuredSelection) event.getSelection()).getFirstElement();
                selectedType = (String) selected;
            }
        });
        treeViewer.setInput(getAvailableTypes(properties));
        final String defaultType = properties.getVariables().get(variableName);
        if (defaultType != null) {
            treeViewer.setSelection(new StructuredSelection(defaultType));
        }

        return container;
    }

    /**
     * Gets the {@link List} of available types for the given {@link TemplateCustomProperties}.
     * 
     * @param customProperties
     *            the {@link TemplateCustomProperties}
     * @return the {@link List} of available types for the given {@link TemplateCustomProperties}
     */
    List<String> getAvailableTypes(TemplateCustomProperties customProperties) {
        final Set<String> types = new HashSet<>();

        types.add(TemplateCustomProperties.STRING_TYPE);
        types.add(TemplateCustomProperties.INTEGER_TYPE);
        types.add(TemplateCustomProperties.REAL_TYPE);
        types.add(TemplateCustomProperties.BOOLEAN_TYPE);

        types.addAll(getEClassifiers(EcorePackage.eINSTANCE));
        if (customProperties != null) {
            for (String nsURI : customProperties.getPackagesURIs()) {
                final EPackage ePkg = EPackageRegistryImpl.INSTANCE.getEPackage(nsURI);
                types.addAll(getEClassifiers(ePkg));
            }
        }

        final List<String> res = new ArrayList<>(types);
        Collections.sort(res);

        return res;
    }

    /**
     * Gets the {@link List} of all classifiers in the given {@link EPackage}.
     * 
     * @param ePkg
     *            the {@link EPackage}
     * @return the {@link List} of all classifiers in the given {@link EPackage}
     */
    private List<String> getEClassifiers(EPackage ePkg) {
        final List<String> res = new ArrayList<>();

        for (EClassifier eClassifier : ePkg.getEClassifiers()) {
            res.add(ePkg.getName() + "::" + eClassifier.getName());
        }
        for (EPackage child : ePkg.getESubpackages()) {
            res.addAll(getEClassifiers(child));
        }

        return res;
    }

    /**
     * Gets the selected type.
     * 
     * @return the selected type
     */
    public String getSelectedType() {
        return selectedType;
    }

}
