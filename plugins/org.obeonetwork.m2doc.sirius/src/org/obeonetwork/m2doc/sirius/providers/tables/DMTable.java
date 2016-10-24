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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.obeonetwork.m2doc.provider.AbstractTableProvider.MColumn;
import org.obeonetwork.m2doc.provider.AbstractTableProvider.MRow;
import org.obeonetwork.m2doc.provider.AbstractTableProvider.MTable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Implementation of {@link MTable} based on a {@link DTable}.
 * 
 * @author ldelaigue
 */
public class DMTable implements MTable {
    /** The wrapped sirius table. */
    private final DTable table;
    /** Map of MColumns indeed by DColumns. */
    private Map<DColumn, DMColumn> columnsByDColumns;

    /**
     * Constructor.
     * 
     * @param table
     *            The sirius table, must not be <code>null</code>.
     */
    public DMTable(DTable table) {
        this.table = checkNotNull(table);
        columnsByDColumns = new LinkedHashMap<DColumn, DMColumn>();
        for (DColumn col : table.getColumns()) {
            if (col.isVisible()) {
                DMColumn dmColumn = new DMColumn(col);
                columnsByDColumns.put(col, dmColumn);
            }
        }
    }

    @Override
    public String getLabel() {
        return table.getName();
    }

    @Override
    public Iterable<? extends MColumn> getColumns() {
        return columnsByDColumns.values();
    }

    @Override
    public Iterable<? extends MRow> getRows() {
        List<MRow> result = new ArrayList<MRow>();
        for (DLine line : table.getLines()) {
            if (line.isVisible()) {
                result.add(new DMRow(line, this));
            }
        }
        return result;
    }

    /**
     * Get the MColumn that wraps a given DColumn in this table.
     * 
     * @param dcol
     *            The DColumn
     * @return The MColumn that wraps the given DColumn, or <code>null</code> if the given DColumn is not part of this table.
     */
    protected DMColumn getColumn(DColumn dcol) {
        return columnsByDColumns.get(dcol);
    }
}
