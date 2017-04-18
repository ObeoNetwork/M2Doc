/*******************************************************************************
 * Copyright (c) 2016 Obeo. 
 *    All rights reserved. This program and the accompanying materials
 *    are made available under the terms of the Eclipse Public License v1.0
 *    which accompanies this distribution, and is available at
 *    http://www.eclipse.org/legal/epl-v10.html
 *     
 *     Contributors:
 *         Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.m2doc.sirius.providers.tables;

import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.obeonetwork.m2doc.element.MStyle;
import org.obeonetwork.m2doc.element.MTable.MCell;
import org.obeonetwork.m2doc.element.MTable.MColumn;

/**
 * Implementation of {@link MCell} based on a {@link DCell}.
 * 
 * @author ldelaigue
 */
public class DMCell implements MCell {
    /** The wrapped sirius cell. */
    private final DCell cell;
    /** The table this cell belongs to. */
    private final DMTable table;
    /**
     * The cell's style, possibly <code>null</code>. If <code>null</code>, the cell will use its row's style, and if its row's style is
     * <code>null</code>, it will use its column style.
     */
    private MStyle style;

    /**
     * Constructor.
     * 
     * @param cell
     *            The cell to wrap, cannot be <code>null</code>
     * @param table
     *            The parent table, cannot be <code>null</code>
     */
    public DMCell(DCell cell, DMTable table) {
        this.cell = cell;
        this.table = table;
        DTableElementStyle currentStyle = cell.getCurrentStyle();
        if (currentStyle != null) {
            style = new DMStyle(currentStyle);
        }
    }

    @Override
    public MStyle getStyle() {
        return style;
    }

    @Override
    public String getLabel() {
        return cell.getLabel();
    }

    @Override
    public MColumn getColumn() {
        return table.getColumn(cell.getColumn());
    }

    @Override
    public void setStyle(MStyle style) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLabel(String label) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setColumn(MColumn column) {
        throw new UnsupportedOperationException();
    }
}
