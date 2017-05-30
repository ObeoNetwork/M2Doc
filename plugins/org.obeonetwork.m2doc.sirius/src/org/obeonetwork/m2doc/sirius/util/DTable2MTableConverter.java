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
package org.obeonetwork.m2doc.sirius.util;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.obeonetwork.m2doc.element.MStyle;
import org.obeonetwork.m2doc.element.MTable;
import org.obeonetwork.m2doc.element.MTable.MCell;
import org.obeonetwork.m2doc.element.MTable.MRow;
import org.obeonetwork.m2doc.element.impl.MStyleImpl;
import org.obeonetwork.m2doc.element.impl.MTableImpl;
import org.obeonetwork.m2doc.element.impl.MTableImpl.MCellImpl;
import org.obeonetwork.m2doc.element.impl.MTableImpl.MRowImpl;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Implementation of {@link MTable} based on a {@link DTable}.
 * 
 * @author ldelaigue
 */
public final class DTable2MTableConverter {

    /** Header style. */
    public static final MStyle HEADER_STYLE = new MStyleImpl(12, Color.BLACK, Color.GRAY, MStyle.FONT_BOLD);

    /**
     * Default constructor.
     */
    private DTable2MTableConverter() {
    }

    /**
     * Converts a {@link DTable sirius table} to a {@link MTable m2doc table}.
     * 
     * @param table
     *            The sirius table, must not be <code>null</code>.
     * @return the converted table.
     */
    public static MTable convert(DTable table) {
        checkNotNull(table);

        /*
         * Creates the new table.
         */
        MTable mTable = new MTableImpl();
        mTable.setLabel(table.getName());

        /*
         * A header row is inserted with an empty cell
         */
        MRow mHeaderRow = new MRowImpl();
        mTable.getRows().add(mHeaderRow);
        // Empty cell at first position
        mHeaderRow.getCells().add(new MCellImpl());
        Map<Integer, MStyle> columnStyles = new HashMap<Integer, MStyle>();
        int colIdx = 0;
        for (DColumn column : table.getColumns()) {
            MCell mCell = new MCellImpl();
            mHeaderRow.getCells().add(mCell);
            mCell.setLabel(column.getLabel());
            mCell.setStyle(HEADER_STYLE);
            // Convert & keep the column style
            DTableElementStyle columnStyle = column.getCurrentStyle();
            columnStyles.put(colIdx, convert(columnStyle));
            colIdx++;
        }

        /*
         * Convert the rows.
         */
        for (DLine line : table.getLines()) {
            if (line.isVisible()) {
                MRow row = new MRowImpl();
                mTable.getRows().add(row);

                /*
                 * A header cell is inserted
                 */
                MCell mHeaderColumnCell = new MCellImpl();
                row.getCells().add(mHeaderColumnCell);
                mHeaderColumnCell.setLabel(line.getLabel());
                mHeaderColumnCell.setStyle(HEADER_STYLE);
                // Retrieve row style to apply to non styled cells
                DTableElementStyle lineStyle = line.getCurrentStyle();
                MStyle mRowStyle = convert(lineStyle);

                /*
                 * Initialize the row with default styles (row or column)
                 */
                for (colIdx = 0; colIdx < table.getColumns().size(); colIdx++) {
                    MCell mCell = new MCellImpl();
                    row.getCells().add(mCell);
                    if (mRowStyle != null) {
                        // 1st attempt : try row style
                        mCell.setStyle(mRowStyle);
                    } else {
                        // 2nd attempt : try column style
                        MStyle mColumnStyle = columnStyles.get(colIdx);
                        mCell.setStyle(mColumnStyle);
                    }
                }

                /*
                 * Converts the provided cells
                 */
                for (DCell dcell : line.getCells()) {
                    // The cell must be put at the right position in the index
                    colIdx = table.getColumns().indexOf(dcell.getColumn());
                    MCell mCell = row.getCells().get(colIdx + 1);
                    mCell.setLabel(dcell.getLabel());
                    // Override default style if required
                    DTableElementStyle style = dcell.getCurrentStyle();
                    if (style != null) {
                        mCell.setStyle(convert(style));
                    }
                }
            }
        }

        // Return the result
        return mTable;
    }

    /**
     * Converts a sirius style to an m2doc style.
     * 
     * @param dStyle
     *            the sirius style.
     * @return the converted style.
     */
    public static MStyle convert(DTableElementStyle dStyle) {
        MStyle mStyle = null;
        if (dStyle != null) {
            mStyle = new MStyleImpl();
            mStyle.setForegroundColor(convert(dStyle.getForegroundColor()));
            mStyle.setBackgroundColor(convert(dStyle.getBackgroundColor()));
            mStyle.setFontSize(dStyle.getLabelSize());
            mStyle.setModifiers(convert(dStyle.getLabelFormat()));
        }
        return mStyle;
    }

    /**
     * Converts a sirius font format collection to m2doc modifiers.
     * 
     * @param fontFormats
     *            the font formats.
     * @return the converted modifiers.
     */
    private static int convert(EList<FontFormat> fontFormats) {
        int result = 0;
        for (FontFormat format : fontFormats) {
            switch (format) {
                case BOLD_LITERAL:
                    result |= MStyle.FONT_BOLD;
                    break;
                case ITALIC_LITERAL:
                    result |= MStyle.FONT_ITALIC;
                    break;
                case STRIKE_THROUGH_LITERAL:
                    result |= MStyle.FONT_STRIKE_THROUGH;
                    break;
                case UNDERLINE_LITERAL:
                    result |= MStyle.FONT_UNDERLINE;
                    break;
                default:
                    // Nothing to do, if Sirius adds a format we won't deal with it
            }
        }
        return result;
    }

    /**
     * Converts a sirius RGB color to m2doc color.
     * 
     * @param rgb
     *            the color to convert.
     * @return the converted color.
     */
    private static Color convert(final RGBValues rgb) {
        if (rgb != null) {
            return new Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue());
        } else {
            return null;
        }
    }

}
