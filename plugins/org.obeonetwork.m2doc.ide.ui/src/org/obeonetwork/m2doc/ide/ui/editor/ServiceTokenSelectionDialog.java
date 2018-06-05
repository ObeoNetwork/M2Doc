/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation and/or initial documentation
 *    ...
 *******************************************************************************/
package org.obeonetwork.m2doc.ide.ui.editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.obeonetwork.m2doc.ide.M2DocPlugin;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.services.TokenRegistry;

/**
 * Service tokens selection dialog.
 *
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ServiceTokenSelectionDialog extends Dialog {

    static {
        // make sure org.obeonetwork.m2doc.ide.ui is activated
        M2DocPlugin.getPlugin();
    }

    /**
     * The Y size.
     */
    private static final int Y = 300;

    /**
     * The X size.
     */
    private static final int X = 900;

    /**
     * The {@link TokenRegistry} providing values.
     */
    protected final TokenRegistry registry;

    /**
     * The {@link TemplateCustomProperties} to edit.
     */
    protected final TemplateCustomProperties properties;

    /**
     * Selected {@link List} of service tokens.
     */
    protected final List<String> selected = new ArrayList<>();

    /**
     * The {@link List} of added token names.
     */
    protected final List<String> added = new ArrayList<>();

    /**
     * The {@link List} of removed token names.
     */
    protected final List<String> removed = new ArrayList<>();

    /**
     * Create the dialog.
     * 
     * @param parentShell
     *            the parent {@link Shell}
     * @param registry
     *            the {@link TokenRegistry} providing values
     * @param properties
     *            the {@link TemplateCustomProperties} to edit
     */
    public ServiceTokenSelectionDialog(Shell parentShell, TokenRegistry registry, TemplateCustomProperties properties) {
        super(parentShell);
        this.registry = registry;
        this.properties = properties;

        for (String tokenName : registry.getRegisteredTokens()) {
            boolean isSelected = true;
            for (Entry<String, List<String>> entry : registry.getServices(tokenName).entrySet()) {
                final String bundleName = entry.getKey();
                for (String className : entry.getValue()) {
                    if (!bundleName.equals(properties.getServiceClasses().get(className))) {
                        isSelected = false;
                        break;
                    }
                }
            }
            final Set<String> packages = new HashSet<>(properties.getPackagesURIs());
            for (String pkg : registry.getPackages(tokenName)) {
                if (!packages.contains(pkg)) {
                    isSelected = false;
                    break;
                }
            }
            if (isSelected) {
                selected.add(tokenName);
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
        final Composite container = (Composite) super.createDialogArea(parent);

        CheckboxTableViewer tableViewer = new CheckboxTableViewer(
                new Table(container, SWT.BORDER | SWT.V_SCROLL | SWT.CHECK));
        tableViewer.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        tableViewer.setLabelProvider(new LabelProvider());
        tableViewer.setContentProvider(new IStructuredContentProvider() {

            @Override
            @SuppressWarnings("unchecked")
            public Object[] getElements(Object inputElement) {
                return ((java.util.List<Object>) inputElement).toArray();
            }

            /**
             * {@inheritDoc}
             *
             * @see org.eclipse.jface.viewers.IContentProvider#dispose()
             */
            @Override
            public void dispose() {
                // nothing to do here
            }

            @Override
            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
                // nothing to do here
            }

        });
        tableViewer.setCheckStateProvider(new ICheckStateProvider() {

            @Override
            public boolean isGrayed(Object element) {
                return false;
            }

            @Override
            public boolean isChecked(Object element) {
                return selected.contains(element);
            }
        });
        tableViewer.addCheckStateListener(new ICheckStateListener() {

            @Override
            public void checkStateChanged(CheckStateChangedEvent event) {
                if (event.getChecked()) {
                    final String tokenName = (String) event.getElement();
                    selected.add(tokenName);
                    added.add(tokenName);
                    removed.remove(tokenName);
                } else {
                    final String tokenName = (String) event.getElement();
                    selected.remove(tokenName);
                    removed.add(tokenName);
                    added.remove(tokenName);
                }
            }
        });
        tableViewer.setInput(getTokenNames());

        return container;
    }

    /**
     * Gets the {@link List} of possible token names.
     * 
     * @return the {@link List} of possible token names
     */
    protected List<String> getTokenNames() {
        final List<String> res;

        res = new ArrayList<>(registry.getRegisteredTokens());
        Collections.sort(res);

        return res;
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
        return new Point(X, Y);
    }

    @Override
    protected void okPressed() {
        super.okPressed();
        for (String tokenName : removed) {
            final Map<String, List<String>> map = registry.getServices(tokenName);
            for (Entry<String, List<String>> entry : map.entrySet()) {
                final String bundleName = entry.getKey();
                for (String className : entry.getValue()) {
                    if (bundleName.equals(properties.getServiceClasses().get(className))) {
                        properties.getServiceClasses().remove(className);
                    }
                }
            }
            properties.getPackagesURIs().removeAll(registry.getPackages(tokenName));
        }
        for (String tokenName : added) {
            final Map<String, List<String>> map = registry.getServices(tokenName);
            for (Entry<String, List<String>> entry : map.entrySet()) {
                final String bundleName = entry.getKey();
                for (String className : entry.getValue()) {
                    properties.getServiceClasses().put(className, bundleName);
                }
            }
            properties.getPackagesURIs().addAll(registry.getPackages(tokenName));
        }
    }

    @Override
    protected void cancelPressed() {
        super.cancelPressed();
        added.clear();
        removed.clear();
    }

    /**
     * Tells if the dialog made changes.
     * 
     * @return <code>true</code> if the dialog made changes, <code>false</code> otherwise
     */
    public boolean hasChanges() {
        return !added.isEmpty() || !removed.isEmpty();
    }

}
