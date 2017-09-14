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

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.obeonetwork.m2doc.element.MElement;
import org.obeonetwork.m2doc.element.MTable;

/**
 * A table that can be inserted in a word document.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MTableImpl implements MTable {

    /**
     * A table row, whose label can be used as row header.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    public static class MRowImpl implements MRow {

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
    public static class MCellImpl implements MCell {

        /**
         * The contents.
         */
        private MElement contents;

        /**
         * The background {@link Color}.
         */
        private Color backgroundColor;

        /**
         * Constructor.
         * 
         * @param contents
         *            the contents
         * @param backgroundColor
         *            the background {@link Color}
         */
        public MCellImpl(MElement contents, Color backgroundColor) {
            this.contents = contents;
            this.backgroundColor = backgroundColor;
        }

        @Override
        public MElement getContents() {
            return contents;
        }

        @Override
        public void setContents(MElement contents) {
            this.contents = contents;
        }

        @Override
        public Color getBackgroundColor() {
            return backgroundColor;
        }

        @Override
        public void setBackgroundColor(Color backgroundColor) {
            this.backgroundColor = backgroundColor;
        }

    }

    /**
     * The {@link List} of {@link MRow}.
     */
    private final List<MRow> rows = new ArrayList<MRow>();

    /**
     * The table label.
     */
    private String label;

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

    @Override
    public int getColumnsCount() {
        int result = 0;
        for (MRow mRow : rows) {
            int rowWidth = mRow.getCells().size();
            if (rowWidth > result) {
                result = rowWidth;
            }
        }
        return result;
    }
}
