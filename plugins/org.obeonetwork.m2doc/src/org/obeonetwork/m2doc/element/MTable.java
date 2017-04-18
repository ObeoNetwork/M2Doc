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
package org.obeonetwork.m2doc.element;

import java.util.List;

/**
 * Interface that represents a table that can be inserted in a word document.
 * 
 * @author ldelaigue
 */
public interface MTable extends MElemement {

    /**
     * Interface that represents a table column, whose label can be used as column header.
     * 
     * @author ldelaigue
     */
    public interface MColumn {
        /**
         * The column header {@link MStyle}.
         * 
         * @return the {@link MStyle} to use for the column header
         */
        MStyle getStyle();

        /**
         * Sets the column header {@link MStyle}.
         * 
         * @param style
         *            the new column header {@link MStyle}
         */
        void setStyle(MStyle style);

        /**
         * The column label.
         * 
         * @return the column label
         */
        String getLabel();

        /**
         * Sets the column label.
         * 
         * @param label
         *            the new column label
         */
        void setLabel(String label);
    }

    /**
     * Interface that represents a table row, whose label can be used as row header.
     * 
     * @author ldelaigue
     */
    public interface MRow {
        /**
         * The row header {@link MStyle}.
         * 
         * @return the {@link MStyle} to use for the row header
         */
        MStyle getStyle();

        /**
         * Sets the row header style.
         * 
         * @param style
         *            the new row header {@link MStyle}
         */
        void setStyle(MStyle style);

        /**
         * The row label.
         * 
         * @return The row label.
         */
        String getLabel();

        /**
         * Sets the row label.
         * 
         * @param label
         *            the new row label
         */
        void setLabel(String label);

        /**
         * The row's defined cells (i.e. non-empty cells). A row may have no cell at all, or may not have a cell for each column of its
         * table.
         * 
         * @return The row's defined cells.
         */
        List<MCell> getCells();
    }

    /**
     * Interface that represents a table cell.
     * 
     * @author ldelaigue
     */
    public interface MCell {
        /**
         * The cell style.
         * 
         * @return The style to use for the cell.
         */
        MStyle getStyle();

        /**
         * Sets the cell style.
         * 
         * @param style
         *            the new cell {@link MStyle}
         */
        void setStyle(MStyle style);

        /**
         * The cell label, i.e. its textual content.
         * 
         * @return The cell text.
         */
        String getLabel();

        /**
         * Sets the cell label.
         * 
         * @param label
         *            the new cell label
         */
        void setLabel(String label);

        /**
         * The cell's column.
         * 
         * @return The cell's column, that can be used to locate the cell in the table.
         */
        MColumn getColumn();

        /**
         * Sets the cell's {@link MColumn}.
         * 
         * @param column
         *            the new cell {@link MColumn}
         */
        void setColumn(MColumn column);
    }

    /**
     * The table columns.
     * 
     * @return All the table columns. An implicit untitled column is added to hold the row headers, hence this method must not return a
     *         Column for the row headers.
     */
    List<MColumn> getColumns();

    /**
     * Tha table rows.
     * 
     * @return All the table rows.
     */
    List<MRow> getRows();

    /**
     * The table label (caption).
     * 
     * @return The table label, <code>null</code> if the table has no caption.
     */
    String getLabel();

    /**
     * Sets the table label.
     * 
     * @param label
     *            the new table label
     */
    void setLabel(String label);
}
