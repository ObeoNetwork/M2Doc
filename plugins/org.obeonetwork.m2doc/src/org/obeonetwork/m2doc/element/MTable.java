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

import java.awt.Color;
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
         * Gets the cell contents.
         * 
         * @return the cell contents if any, <code>null</code> otherwise
         */
        MElement getContents();

        /**
         * Sets the cell contents.
         * 
         * @param contents
         *            the new cell contents
         */
        void setContents(MElement contents);

        /**
         * Gets the background {@link Color}.
         * 
         * @return the background {@link Color} if any, <code>null</code> otherwise
         */
        Color getBackgroundColor();

        /**
         * Sets the background {@link Color}.
         * 
         * @param color
         *            the new background {@link Color}
         */
        void setBackgroundColor(Color color);

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
     * Sets the table label (caption).
     * 
     * @param label
     *            the new table label (caption)
     */
    void setLabel(String label);
}
