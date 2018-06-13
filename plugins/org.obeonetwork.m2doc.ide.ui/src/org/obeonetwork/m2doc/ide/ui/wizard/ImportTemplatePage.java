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
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.obeonetwork.m2doc.services.TemplateRegistry;

/**
 * Import template {@link WizardPage}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ImportTemplatePage extends WizardPage {

    /**
     * {@link Collection} {@link ITreeContentProvider}.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private static class CollectionContentProvider extends ArrayContentProvider implements ITreeContentProvider {

        @Override
        public Object[] getChildren(Object parentElement) {
            return null;
        }

        @Override
        public Object getParent(Object element) {
            return null;
        }

        @Override
        public boolean hasChildren(Object element) {
            return false;
        }

    }

    /**
     * The {@link Set} of selected template {@link URI}.
     */
    private final Set<URI> selectedTemplateURIs = new HashSet<>();

    /**
     * Constructor.
     */
    protected ImportTemplatePage() {
        super("Select a registered template");
        setTitle("Select templates to import in the workspace.");
    }

    @Override
    public void createControl(Composite parent) {
        final Composite container = new Composite(parent, SWT.NULL);
        setControl(container);
        container.setLayout(new FillLayout(SWT.HORIZONTAL | SWT.VERTICAL));

        final CheckboxTreeViewer templatesTreeViewer = new CheckboxTreeViewer(container, SWT.BORDER);
        templatesTreeViewer.setContentProvider(new CollectionContentProvider());
        templatesTreeViewer.setLabelProvider(new ColumnLabelProvider());
        final List<String> registeredTemplates = new ArrayList<>(TemplateRegistry.INSTANCE.getTemplates().keySet());
        Collections.sort(registeredTemplates);
        templatesTreeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                selectedTemplateURIs.clear();
                for (Object templateName : templatesTreeViewer.getCheckedElements()) {
                    selectedTemplateURIs.add(TemplateRegistry.INSTANCE.getTemplates().get(templateName));
                }
                setPageComplete(!selectedTemplateURIs.isEmpty());
            }
        });
        templatesTreeViewer.setInput(registeredTemplates);
        setPageComplete(!selectedTemplateURIs.isEmpty());
    }

    /**
     * Gets the selected template {@link URI}.
     * 
     * @return the selected template {@link URI}
     */
    public Set<URI> getSelectedTemplateURIs() {
        return selectedTemplateURIs;
    }

}
