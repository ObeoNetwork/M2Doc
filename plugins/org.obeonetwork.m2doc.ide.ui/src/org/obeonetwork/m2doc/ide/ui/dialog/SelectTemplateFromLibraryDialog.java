/*******************************************************************************
 *  Copyright (c) 2024, 2025 Obeo. 
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

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.obeonetwork.m2doc.ide.M2DocPlugin;
import org.obeonetwork.m2doc.ide.ui.M2DocUIPlugin;
import org.obeonetwork.m2doc.ide.ui.wizard.SelectRegisteredTemplatePage.CollectionContentProvider;

/**
 * Template from libraries selection {@link Dialog}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class SelectTemplateFromLibraryDialog extends MessageDialog {

    /**
     * The table minimum height.
     */
    private static final int TABLE_MINIMUM_HEIGHT = 400;

    /**
     * The table minimum width.
     */
    private static final int TABLE_MINIMUM_WIDTH = 200;

    {
        // make sure org.obeonetwork.m2doc.ide is started
        M2DocPlugin.getPlugin();
    }

    /**
     * The template {@link URI}.
     */
    private URI templateURI;

    /**
     * Consturctor.
     * 
     * @param parentShell
     *            the parent {@link Shell}
     */
    public SelectTemplateFromLibraryDialog(Shell parentShell) {
        super(parentShell, "Select registered template.", null,
                "Select a template in the following registered templates.", MessageDialog.QUESTION,
                new String[] {IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL }, 0);
    }

    @Override
    protected Control createCustomArea(Composite parent) {
        final Composite container = new Composite(parent, parent.getStyle());
        container.setLayout(new GridLayout(1, false));
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

        final TableViewer templatesTreeViewer = new TableViewer(container, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        Table table = templatesTreeViewer.getTable();
        final GridData gdTable = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        gdTable.minimumWidth = TABLE_MINIMUM_WIDTH;
        gdTable.minimumHeight = TABLE_MINIMUM_HEIGHT;
        table.setLayoutData(gdTable);
        templatesTreeViewer.setContentProvider(new CollectionContentProvider());
        templatesTreeViewer.setLabelProvider(new ColumnLabelProvider());
        final List<String> templatesFromLibraries = new ArrayList<>(getTemplateFromLibraries());
        Collections.sort(templatesFromLibraries);
        templatesTreeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                final String selected = (String) ((IStructuredSelection) event.getSelection()).getFirstElement();
                templateURI = URI.createFileURI(selected);
            }
        });
        templatesTreeViewer.addDoubleClickListener(new IDoubleClickListener() {

            @Override
            public void doubleClick(DoubleClickEvent event) {
                if (getButton(OK).isEnabled()) {
                    buttonPressed(IDialogConstants.OK_ID);
                }
            }
        });
        templatesTreeViewer.setInput(templatesFromLibraries);

        return container;
    }

    /**
     * Gets the {@link List} of template from libraries absolute path.
     * 
     * @return the {@link List} of template from libraries absolute path
     */
    private List<String> getTemplateFromLibraries() {
        final List<String> res = new ArrayList<>();

        for (File template : M2DocUIPlugin.getDefault().getAllTemplatesFromLibraries()) {
            res.add(template.getAbsolutePath());
        }

        return res;
    }

    @Override
    protected boolean isResizable() {
        return true;
    }

    /**
     * Gets the template {@link URI}.
     * 
     * @return the template {@link URI}
     */
    public URI getTemplateURI() {
        return templateURI;
    }

}
