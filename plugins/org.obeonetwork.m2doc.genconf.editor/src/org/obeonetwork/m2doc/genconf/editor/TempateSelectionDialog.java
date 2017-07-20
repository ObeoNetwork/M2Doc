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
package org.obeonetwork.m2doc.genconf.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 * A template selection dialog.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class TempateSelectionDialog extends Dialog {

    /**
     * The dialog height.
     */
    private static final int HEIGHT = 300;

    /**
     * The dialog width.
     */
    private static final int WIDTH = 450;

    /**
     * The selected template {@link IFile}.
     */
    protected IFile result;

    /**
     * The {@link Shell}.
     */
    protected Shell shell;

    /**
     * The {@link IContentProvider}.
     */
    protected IContentProvider contentProvider;

    /**
     * Tells if the dialog has been canceled.
     */
    protected boolean canceled;

    /**
     * Create the dialog.
     * 
     * @param parent
     *            the parent {@link Shell}
     * @param style
     *            the SWT style
     */
    public TempateSelectionDialog(Shell parent, int style) {
        super(parent, style);
        setText("Select a template");
    }

    /**
     * Tells if the dialog has been canceled.
     * 
     * @return <code>true</code> if the dialog has been canceled, <code>false</code> otherwise
     */
    public boolean isCanceled() {
        return canceled;
    }

    /**
     * Open the dialog.
     * 
     * @return the selected {@link IFile}
     */
    public IFile open() {
        createContents();
        shell.open();
        shell.layout();
        Display display = getParent().getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        contentProvider.dispose();
        return result;
    }

    /**
     * Create contents of the dialog.
     */
    private void createContents() {
        shell = new Shell(getParent(), getStyle());
        shell.setSize(WIDTH, HEIGHT);
        shell.setText(getText());
        shell.setLayout(new GridLayout(1, false));
        contentProvider = new WorkbenchContentProvider() {
            @Override
            public Object[] getChildren(Object element) {
                final List<Object> res = new ArrayList<Object>();

                final Object[] children = super.getChildren(element);
                for (Object child : children) {
                    if (child instanceof IFile) {
                        if (child instanceof IFile && "docx".equals(((IFile) child).getLocation().getFileExtension())) {
                            res.add(child);
                        } else {
                            // nothing to do here
                        }
                    } else {
                        res.add(child);
                    }
                }

                return res.toArray();
            }
        };

        final Composite composite = new Composite(shell, SWT.NONE);
        composite.setLayout(new GridLayout(1, false));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

        final FilteredTree treeViewer = new FilteredTree(composite, SWT.BORDER, new PatternFilter(), true);
        treeViewer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        final TreeViewer viewer = treeViewer.getViewer();
        viewer.setLabelProvider(WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider());
        viewer.setContentProvider(contentProvider);
        viewer.setInput(ResourcesPlugin.getWorkspace().getRoot());
        Tree tree = viewer.getTree();
        tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

        final Composite buttonComposite = new Composite(composite, SWT.NONE);
        buttonComposite.setLayout(new GridLayout(2, false));
        buttonComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

        final Button cancelButton = new Button(buttonComposite, SWT.NONE);
        cancelButton.setText("Cancel");
        cancelButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event event) {
                canceled = true;
                dispose();
            }
        });

        final Button okButton = new Button(buttonComposite, SWT.NONE);
        okButton.setText("OK");
        okButton.setEnabled(false);
        okButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event event) {
                canceled = false;
                dispose();
            }
        });

        viewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                Object selected = ((IStructuredSelection) event.getSelection()).getFirstElement();
                if (selected instanceof IFile) {
                    result = (IFile) selected;
                    okButton.setEnabled(true);
                } else {
                    okButton.setEnabled(false);
                }
            }
        });
    }

    /**
     * Dispose.
     */
    public void dispose() {
        shell.dispose();
        contentProvider.dispose();
    }

}
