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
public interface MTable extends MElement {

    /**
     * Interface that represents a table row, whose label can be used as row header.
     * 
     * @author ldelaigue
     */
    public interface MRow {
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

    }

    /**
     * Tha table rows.
     * 
     * @return All the table rows.
     */
    List<MRow> getRows();

    /**
     * Returns the columns count.
     * 
     * @return the columns count.
     */
    int getColumnsCount();

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
