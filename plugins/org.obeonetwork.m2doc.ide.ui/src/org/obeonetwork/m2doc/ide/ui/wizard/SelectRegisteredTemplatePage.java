/*******************************************************************************
 *  Copyright (c) 2018 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.ide.ui.wizard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
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
public class SelectRegisteredTemplatePage extends WizardPage {

    /**
     * Template {@link URI} can be setted.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    public interface TemplateURISettable {

        /**
         * Sets the template {@link URI}.
         * 
         * @param templateURI
         *            the new template {@link URI}
         */
        void setTemplateURI(URI templateURI);

    }

    /**
     * {@link Collection} {@link ITreeContentProvider}.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    public static class CollectionContentProvider extends ArrayContentProvider implements ITreeContentProvider {

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
     * The selected template {@link URI}.
     */
    private URI selectedTemplateURI;

    /**
     * Constructor.
     */
    protected SelectRegisteredTemplatePage() {
        super("Select a registered template");
        setTitle("Select templates to import in the workspace.");
    }

    @Override
    public void createControl(Composite parent) {
        final Composite container = new Composite(parent, parent.getStyle());
        setControl(container);
        container.setLayout(new FillLayout(SWT.HORIZONTAL | SWT.VERTICAL));

        final TreeViewer templatesTreeViewer = new TreeViewer(container, SWT.BORDER);
        templatesTreeViewer.setContentProvider(new CollectionContentProvider());
        templatesTreeViewer.setLabelProvider(new ColumnLabelProvider());
        final List<String> registeredTemplates = new ArrayList<>(TemplateRegistry.INSTANCE.getTemplates().keySet());
        Collections.sort(registeredTemplates);
        templatesTreeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                setSelectedTemplateURI(TemplateRegistry.INSTANCE.getTemplates()
                        .get(((IStructuredSelection) event.getSelection()).getFirstElement()));
                setPageComplete(selectedTemplateURI != null);
            }
        });
        templatesTreeViewer.setInput(registeredTemplates);
        setPageComplete(selectedTemplateURI != null);
    }

    /**
     * Gets the selected template {@link URI}.
     * 
     * @return the selected template {@link URI}
     */
    public URI getSelectedTemplateURI() {
        return selectedTemplateURI;
    }

    /**
     * Sets the selected template {@link URI}.
     * 
     * @param selectedTemplateURI
     *            the selected template {@link URI} to set
     */
    protected void setSelectedTemplateURI(URI selectedTemplateURI) {
        this.selectedTemplateURI = selectedTemplateURI;
    }

}
