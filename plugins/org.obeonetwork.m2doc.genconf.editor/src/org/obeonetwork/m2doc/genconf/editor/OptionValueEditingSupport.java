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

import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.Option;

/**
 * {@link Option#getValue() Option value} editing support.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public final class OptionValueEditingSupport extends EditingSupport {

    /**
     * The Editor.
     */
    private final TextCellEditor editor;

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
    public OptionValueEditingSupport(ColumnViewer viewer, EditingDomain editingDomain) {
        super(viewer);
        this.editingDomain = editingDomain;
        editor = new TextCellEditor((Composite) viewer.getControl());
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
        return editor;
    }

    @Override
    protected boolean canEdit(Object element) {
        return true;
    }

    @Override
    protected Object getValue(Object element) {
        final Option option = (Option) element;

        return option.getValue();
    }

    @Override
    protected void setValue(Object element, Object value) {
        final Option option = (Option) element;

        if ((option.getValue() == null && value != null)
            || (option.getValue() != null && !option.getValue().equals(value))) {
            editingDomain.getCommandStack()
                    .execute(SetCommand.create(editingDomain, option, GenconfPackage.Literals.OPTION__VALUE, value));
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
