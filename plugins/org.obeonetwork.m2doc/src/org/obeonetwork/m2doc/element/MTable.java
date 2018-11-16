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
import java.util.Collections;
import java.util.List;

/**
 * Interface that represents a table that can be inserted in a word document.
 * 
 * @author ldelaigue
 */
public interface MTable extends MElement {

    /**
     * Empty {@link MTable}.
     */
    // CHECKSTYLE:OFF
    MTable EMPTY = new MTable() {

        @Override
        public void setLabel(String label) {
            // nothing to do here
        }

        @Override
        public List<MRow> getRows() {
            return Collections.emptyList();
        }

        @Override
        public String getLabel() {
            return "";
        }

        @Override
        public int getColumnsCount() {
            return 0;
        }

        @Override
        public String getStyleID() {
            return null;
        }

        @Override
        public void setStyleID(String styleID) {
            // nothing to do here
        }

    };
    // CHECKSTYLE:ON

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
         * Vertical alignement.
         * 
         * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
         */
        enum VAlignment {

            /**
             * Both.
             */
            BOTH,

            /**
             * Bottom.
             */
            BOTTOM,

            /**
             * Center.
             */
            CENTER,

            /**
             * Top.
             */
            TOP

        }

        /**
         * Vertical alignement.
         * 
         * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
         */
        enum HAlignment {

            /**
             * Both.
             */
            BOTH,

            /**
             * Center.
             */
            CENTER,

            /**
             * Distribute.
             */
            DISTRIBUTE,

            /**
             * High kashida.
             */
            HIGH_KASHIDA,

            /**
             * Left.
             */
            LEFT,

            /**
             * Low kashida.
             */
            LOW_KASHIDA,

            /**
             * Medium kashida.
             */
            MEDIUM_KASHIDA,

            /**
             * Num tab.
             */
            NUM_TAB,

            /**
             * Right.
             */
            RIGHT,

            /**
             * Thai distribute.
             */
            THAI_DISTRIBUTE

        }

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

        /**
         * Gets the {@link VAlignment vertical alignment}.
         * 
         * @return the {@link VAlignment vertical alignment}
         */
        VAlignment getVAlignment();

        /**
         * Sets the {@link VAlignment vertical alignment}.
         * 
         * @param alignement
         *            the new {@link VAlignment vertical alignment}
         */
        void setVAlignment(VAlignment alignement);

        /**
         * Gets the {@link HAlignment horizontal alignment}.
         * 
         * @return the {@link HAlignment horizontal alignment}
         */
        HAlignment getHAlignment();

        /**
         * Sets the {@link HAlignment horizontal alignment}.
         * 
         * @param alignement
         *            the new {@link HAlignment horizontal alignment}
         */
        void setHAlignment(HAlignment alignement);

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

    /**
     * Gets the style ID. Note to be used the style ID should already be used in the document.
     * 
     * @return the style ID
     */
    String getStyleID();

    /**
     * Sets the style ID. Note to be used the style ID should already be used in the document.
     * 
     * @param styleID
     *            the new style ID
     */
    void setStyleID(String styleID);
}
