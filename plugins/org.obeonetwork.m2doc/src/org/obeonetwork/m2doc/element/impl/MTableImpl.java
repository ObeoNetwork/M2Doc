/*******************************************************************************
 *  Copyright (c) 2017, 2024 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
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
         * The height.
         */
        private int height = -1;

        /**
         * The {@link HeightRule}.
         */
        private HeightRule heightRule;

        /**
         * The {@link List} of {@link MCell}.
         */
        private final List<MCell> cells = new ArrayList<>();

        @Override
        public List<MCell> getCells() {
            return cells;
        }

        @Override
        public int getHeight() {
            return height;
        }

        @Override
        public void setHeight(int height) {
            this.height = height;

        }

        @Override
        public HeightRule getHeightRule() {
            return heightRule;
        }

        @Override
        public void setHeightRule(HeightRule rule) {
            this.heightRule = rule;
        }
    }

    /**
     * A table cell.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    public static class MCellImpl extends AbstractMElementContainer implements MCell {

        /**
         * The background {@link Color}.
         */
        private Color backgroundColor;

        /**
         * The vertical alignment.
         */
        private VAlignment vAlign;

        /**
         * The vertical {@link Merge}.
         */
        private Merge verticallyMerge;

        /**
         * The horizontal {@link Merge}.
         */
        private Merge horizontallyMerge;

        /**
         * The width.
         */
        private int width = -1;

        /**
         * The {@link WidthType}.
         */
        private WidthType widthType;

        /**
         * Constructor.
         * 
         * @param contents
         *            the contents
         * @param backgroundColor
         *            the background {@link Color}
         */
        public MCellImpl(MElement contents, Color backgroundColor) {
            super(contents);
            this.backgroundColor = backgroundColor;
        }

        @Override
        public Color getBackgroundColor() {
            return backgroundColor;
        }

        @Override
        public void setBackgroundColor(Color backgroundColor) {
            this.backgroundColor = backgroundColor;
        }

        @Override
        public VAlignment getVAlignment() {
            return vAlign;
        }

        @Override
        public void setVAlignment(VAlignment alignement) {
            vAlign = alignement;
        }

        @Override
        public Merge getVMerge() {
            return verticallyMerge;
        }

        @Override
        public void setVMerge(Merge merge) {
            verticallyMerge = merge;
        }

        @Override
        public Merge getHMerge() {
            return horizontallyMerge;
        }

        @Override
        public void setHMerge(Merge merge) {
            horizontallyMerge = merge;
        }

        @Override
        public int getWitdh() {
            return width;
        }

        @Override
        public void setWidth(int width) {
            this.width = width;
        }

        @Override
        public WidthType getWidthType() {
            return widthType;
        }

        @Override
        public void setWidthType(WidthType type) {
            this.widthType = type;
        }
    }

    /**
     * The {@link List} of {@link MRow}.
     */
    private final List<MRow> rows = new ArrayList<>();

    /**
     * The table label.
     */
    private String label;

    /**
     * The style ID of this table.
     */
    private String styleID;

    /**
     * The {@link MTableAlign}.
     */
    private MTableAlign tableAlign;

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
            final int rowWidth = mRow.getCells().size();
            if (rowWidth > result) {
                result = rowWidth;
            }
        }
        return result;
    }

    @Override
    public String getStyleID() {
        return styleID;
    }

    @Override
    public void setStyleID(String styleID) {
        this.styleID = styleID;
    }

    @Override
    public MTableAlign getTableAlign() {
        return tableAlign;
    }

    @Override
    public void setTableAlign(MTableAlign tableAlign) {
        this.tableAlign = tableAlign;
    }

}
