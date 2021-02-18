/*******************************************************************************
 *  Copyright (c) 2017 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
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
    public interface MCell extends MElementContainer {

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
         * Vertical and Horizontal cell merge.
         * 
         * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
         */
        enum Merge {

            /**
             * (Re)starts a merge.
             */
            RESTART,

            /**
             * Continue a merge.
             */
            CONTINUE

        }

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
         * Gets the vertical {@link Merge}.
         * 
         * @return the vertical {@link Merge}
         */
        Merge getVMerge();

        /**
         * Sets the vertical {@link Merge}.
         * 
         * @param merge
         *            the vertical {@link Merge}
         */
        void setVMerge(Merge merge);

        /**
         * Gets the horizontal {@link Merge}.
         * 
         * @return the horizontal {@link Merge}.
         */
        Merge getHMerge();

        /**
         * Sets the horizontal {@link Merge}.
         * 
         * @param merge
         *            the horizontal {@link Merge}
         */
        void setHMerge(Merge merge);
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
