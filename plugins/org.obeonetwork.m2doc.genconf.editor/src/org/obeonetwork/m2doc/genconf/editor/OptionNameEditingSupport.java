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
package org.obeonetwork.m2doc.genconf.editor;

import java.util.List;

import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.Option;

/**
 * {@link Option#getName() Option name} editing support.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class OptionNameEditingSupport extends EditingSupport {

    /**
     * The Editor.
     */
    private final ComboBoxViewerCellEditor editor;

    /**
     * The {@link EditingDomain}.
     */
    private EditingDomain editingDomain;

    /**
     * Constructor.
     * 
     * @param viewer
     *            the {@link ColumnViewer}
     * @param editingDomain
     *            the {@link EditingDomain}
     */
    public OptionNameEditingSupport(ColumnViewer viewer, EditingDomain editingDomain) {
        super(viewer);
        this.editingDomain = editingDomain;
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
        final Option option = (Option) element;
        List<String> availableOptions = GenconfUtils.getAviliableOptionNames((Generation) option.eContainer());

        availableOptions.add(0, ((Option) element).getName());
        editor.setInput(availableOptions);

        return editor;
    }

    @Override
    protected boolean canEdit(Object element) {
        return true;
    }

    @Override
    protected Object getValue(Object element) {
        final Option option = (Option) element;

        return option.getName();
    }

    @Override
    protected void setValue(Object element, Object value) {
        final Option option = (Option) element;

        if ((option.getName() == null && value != null)
            || (option.getName() != null && !option.getName().equals(value))) {
            editingDomain.getCommandStack()
                    .execute(SetCommand.create(editingDomain, option, GenconfPackage.Literals.OPTION__NAME, value));
        }
    }

    /**
     * Sets the {@link EditingDomain}.
     * 
     * @param editingDomain
     *            the new {@link EditingDomain}
     */
    public void setEditingDomain(EditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

}
