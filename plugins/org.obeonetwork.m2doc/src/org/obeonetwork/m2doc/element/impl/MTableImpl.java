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
package org.obeonetwork.m2doc.element.impl;

import java.util.ArrayList;
import java.util.List;

import org.obeonetwork.m2doc.element.MStyle;
import org.obeonetwork.m2doc.element.MTable;

/**
 * A table that can be inserted in a word document.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MTableImpl implements MTable {

    /**
     * An element with a label.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    public static class MLabeled {
        /**
         * The label.
         */
        private String label;

        /**
         * Gets the label.
         * 
         * @return the label
         */
        public String getLabel() {
            return label;
        }

        /**
         * Sets the label.
         * 
         * @param label
         *            the new label
         */
        public void setLabel(String label) {
            this.label = label;
        }
    }

    /**
     * An element with a style.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    public static class MStyled extends MLabeled {
        /**
         * The {@link MStyle}.
         */
        private MStyle style;

        /**
         * Gets the style.
         * 
         * @return the style
         */
        public MStyle getStyle() {
            return style;
        }

        /**
         * Sets the style.
         * 
         * @param style
         *            the new style
         */
        public void setStyle(MStyle style) {
            this.style = style;
        }
    }

    /**
     * A table column, whose label can be used as column header.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    public static class MColumnImpl extends MStyled implements MColumn {

    }

    /**
     * A table row, whose label can be used as row header.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    public static class MRowImpl extends MStyled implements MRow {

        /**
         * The {@link List} of {@link MCell}.
         */
        private final List<MCell> cells = new ArrayList<MTable.MCell>();

        @Override
        public List<MCell> getCells() {
            return cells;
        }
    }

    /**
     * A table cell.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    public static class MCellImpl extends MStyled implements MCell {

        /**
         * The {@link MColumn}.
         */
        private MColumn column;

        @Override
        public MColumn getColumn() {
            return column;
        }

        @Override
        public void setColumn(MColumn column) {
            this.column = column;
        }

    }

    /**
     * The {@link List} of {@link MColumn}.
     */
    private final List<MColumn> columns = new ArrayList<MColumn>();

    /**
     * The {@link List} of {@link MRow}.
     */
    private final List<MRow> rows = new ArrayList<MRow>();

    /**
     * The table label.
     */
    private String label;

    @Override
    public List<MColumn> getColumns() {
        return columns;
    }

    @Override
    public List<MRow> getRows() {
        return rows;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

}
