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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.obeonetwork.m2doc.provider.AbstractTableProvider.MRow;
import org.obeonetwork.m2doc.provider.AbstractTableProvider.MStyle;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Implementation of {@link MRow} based on a {@link DLine}.
 * 
 * @author ldelaigue
 */
public class DMRow implements MRow {
    /** The wrapped line. */
    private final DLine line;
    /** The MTable this row belongs to. */
    private final DMTable table;
    /**
     * The row's style, possibly <code>null</code>. If it's not <code>null</code>, it will apply to cells of this row that don't
     * have a cell style applied. Cells of this row won't use their column style i this row has a non-<code>null</code> style.
     */
    private MStyle style;
    /** The list of non-null cells of this row. */
    private List<DMCell> cells;

    /**
     * Constructor.
     * 
     * @param line
     *            The sirius DLine, cannot be <code>null</code>
     * @param table
     *            The table that contains this MRow, cannot be <code>null</code>.
     */
    public DMRow(DLine line, DMTable table) {
        this.line = checkNotNull(line);
        this.table = checkNotNull(table);
        DTableElementStyle currentStyle = line.getCurrentStyle();
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
        return line.getLabel();
    }

    @Override
    public Iterable<DMCell> getCells() {
        if (cells == null) {
            cells = new ArrayList<DMCell>();
            for (DCell dcell : line.getCells()) {
                cells.add(new DMCell(dcell, table));
            }
        }
        return cells;
    }
}
