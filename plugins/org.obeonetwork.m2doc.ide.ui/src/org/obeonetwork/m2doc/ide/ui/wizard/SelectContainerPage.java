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
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 * A container selection wizard.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class SelectContainerPage extends WizardPage {

    /**
     * The selected {@link IContainer}.
     */
    private IContainer selectedContainer;

    /**
     * Constructor.
     */
    protected SelectContainerPage() {
        super("Select output folder");
        setTitle("Select the target container.");
    }

    @Override
    public void createControl(Composite parent) {
        final Composite container = new Composite(parent, SWT.NULL);
        setControl(container);
        container.setLayout(new FillLayout(SWT.HORIZONTAL | SWT.VERTICAL));

        final TreeViewer containerTreeViewer = new TreeViewer(container, SWT.BORDER);
        containerTreeViewer.setContentProvider(new WorkbenchContentProvider() {
            @Override
            public Object[] getChildren(Object element) {
                final List<Object> res = new ArrayList<>();
                for (Object obj : super.getChildren(element)) {
                    if (obj instanceof IContainer) {
                        res.add(obj);
                    }
                }
                return res.toArray();
            }
        });
        containerTreeViewer.setLabelProvider(new WorkbenchLabelProvider());
        containerTreeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                selectedContainer = (IContainer) ((IStructuredSelection) event.getSelection()).getFirstElement();
                setPageComplete(selectedContainer != null);
            }
        });
        containerTreeViewer.setInput(ResourcesPlugin.getWorkspace().getRoot());
        if (selectedContainer != null) {
            containerTreeViewer.setSelection(new StructuredSelection(selectedContainer));
        }
        setPageComplete(selectedContainer != null);
    }

    /**
     * Sets the selected {@link IContainer}.
     * 
     * @param selectedContainer
     *            the selected {@link IContainer}
     */
    public void setSelectedContainer(IContainer selectedContainer) {
        this.selectedContainer = selectedContainer;
    }

    /**
     * Gets the selected {@link IContainer}.
     * 
     * @return the selected {@link IContainer}
     */
    public IContainer getSelectedContainer() {
        return selectedContainer;
    }

}
