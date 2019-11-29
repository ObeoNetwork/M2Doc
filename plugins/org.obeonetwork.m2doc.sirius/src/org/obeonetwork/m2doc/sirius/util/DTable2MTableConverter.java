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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.obeonetwork.m2doc.element.MList;
import org.obeonetwork.m2doc.element.MStyle;
import org.obeonetwork.m2doc.element.MTable;
import org.obeonetwork.m2doc.element.MTable.MCell;
import org.obeonetwork.m2doc.element.MTable.MRow;
import org.obeonetwork.m2doc.element.MText;
import org.obeonetwork.m2doc.element.impl.MListImpl;
import org.obeonetwork.m2doc.element.impl.MStyleImpl;
import org.obeonetwork.m2doc.element.impl.MTableImpl;
import org.obeonetwork.m2doc.element.impl.MTableImpl.MCellImpl;
import org.obeonetwork.m2doc.element.impl.MTableImpl.MRowImpl;
import org.obeonetwork.m2doc.element.impl.MTextImpl;

/**
 * Implementation of {@link MTable} based on a {@link DTable}.
 * 
 * @author ldelaigue
 */
public final class DTable2MTableConverter {

    /** Header style. */
    public static final MStyle HEADER_STYLE = new MStyleImpl(null, 12, Color.BLACK, null, MStyle.FONT_BOLD);

    /** Header background color. */
    public static final Color HEADER_BACKGROUND_COLOR = Color.GRAY;

    /**
     * Default constructor.
     */
    private DTable2MTableConverter() {
    }

    /**
     * Converts a {@link DTable Sirius table} to a {@link MTable M2Doc table}.
     * 
     * @param table
     *            the Sirius table, must not be <code>null</code>
     * @return the converted table
     */
    public static MTable convertTable(DTable table) {
        final MTable mTable = new MTableImpl();
        mTable.setLabel(table.getName());

        // A header row is inserted with an empty cell
        final MRow mHeaderRow = new MRowImpl();
        mTable.getRows().add(mHeaderRow);
        // Empty cell at first position
        mHeaderRow.getCells().add(new MCellImpl(null, null));
        final Map<Integer, DTableElementStyle> columnStyles = new HashMap<>();
        int colIdx = 0;
        for (DColumn column : table.getColumns()) {
            final MText mText = new MTextImpl(column.getLabel(), HEADER_STYLE);
            final MCell mCell = new MCellImpl(mText, HEADER_BACKGROUND_COLOR);
            mHeaderRow.getCells().add(mCell);
            // Keep the column style
            columnStyles.put(colIdx, column.getCurrentStyle());
            colIdx++;
        }

        // Convert the rows.
        for (DLine line : table.getLines()) {
            final List<MRow> mRows = convertRow(table, columnStyles, line, 0);
            mTable.getRows().addAll(mRows);
        }

        // Return the result
        return mTable;
    }

    /**
     * Converts the given {@link DLine} to a {@link List} of {@link MRow}.
     * 
     * @param table
     *            the {@link DTable} to convert
     * @param columnStyles
     *            the style mapping
     * @param line
     *            the {@link DLine} to convert
     * @param depth
     *            the current depth of the table line
     * @return the {@link List} of converted {@link MRow}
     */
    private static List<MRow> convertRow(DTable table, final Map<Integer, DTableElementStyle> columnStyles, DLine line,
            int depth) {
        final List<MRow> res = new ArrayList<>();

        if (line.isVisible()) {
            final MRow row = new MRowImpl();
            res.add(row);

            // A header cell is inserted
            final MList headerCellContent = new MListImpl();
            if (depth > 0) {
                final MText intentation = new MTextImpl(getIntentation(depth), HEADER_STYLE);
                headerCellContent.add(intentation);
            }
            final MText mHeaderText = new MTextImpl(line.getLabel(), HEADER_STYLE);
            headerCellContent.add(mHeaderText);
            final MCell mHeaderColumnCell = new MCellImpl(headerCellContent, HEADER_BACKGROUND_COLOR);
            row.getCells().add(mHeaderColumnCell);
            // Retrieve row style to apply to non styled cells
            final DTableElementStyle rowStyle = line.getCurrentStyle();

            // Initialize the row cells
            for (int colIdx = 0; colIdx < table.getColumns().size(); colIdx++) {
                final DTableElementStyle style;
                if (rowStyle != null) {
                    style = rowStyle;
                } else {
                    style = columnStyles.get(colIdx);
                }
                if (style != null) {
                    row.getCells().add(new MCellImpl(null, convertColor(style.getBackgroundColor())));
                } else {
                    row.getCells().add(new MCellImpl(null, null));
                }
            }

            for (DCell dcell : line.getCells()) {
                setCellContent(table, columnStyles, row, rowStyle, dcell);
            }
            for (DLine child : line.getLines()) {
                res.addAll(convertRow(table, columnStyles, child, depth + 1));
            }
        }

        return res;
    }

    /**
     * Gets the intentation for the given depth.
     * 
     * @param depth
     *            the depth
     * @return the intentation for the given depth
     */
    private static String getIntentation(int depth) {
        final StringBuilder res = new StringBuilder();

        for (int i = 0; i < depth; i++) {
            res.append("  ");
        }

        return res.toString();
    }

    /**
     * Sets the converted {@link MCell} contents of the given {@link DCell}.
     * 
     * @param table
     *            the {@link DTable} to convert
     * @param columnStyles
     *            the column style mapping
     * @param row
     *            the converted {@link MRow}
     * @param rowStyle
     *            the row style
     * @param dcell
     *            the {@link DCell} to convert
     */
    private static void setCellContent(DTable table, final Map<Integer, DTableElementStyle> columnStyles,
            final MRow row, final DTableElementStyle rowStyle, DCell dcell) {
        // The cell must be put at the right position in the index
        final int colIdx = table.getColumns().indexOf(dcell.getColumn());

        final DTableElementStyle style;
        if (dcell.getCurrentStyle() != null) {
            style = dcell.getCurrentStyle();
        } else if (rowStyle != null) {
            style = rowStyle;
        } else {
            style = columnStyles.get(colIdx);
        }

        final MCell mCell = row.getCells().get(colIdx + 1);
        if (style != null) {
            final MText mText = new MTextImpl(dcell.getLabel(), convertStyle(style));
            mCell.setContents(mText);
            mCell.setBackgroundColor(convertColor(style.getBackgroundColor()));
        } else {
            final MText mText = new MTextImpl(dcell.getLabel(), null);
            mCell.setContents(mText);
        }
    }

    /**
     * Converts a Sirius style to an m2doc style.
     * 
     * @param dStyle
     *            the Sirius style.
     * @return the converted style.
     */
    public static MStyle convertStyle(DTableElementStyle dStyle) {
        MStyle mStyle = null;
        if (dStyle != null) {
            mStyle = new MStyleImpl(null, dStyle.getLabelSize(), convertColor(dStyle.getForegroundColor()),
                    convertColor(dStyle.getBackgroundColor()), convertFontFormat(dStyle.getLabelFormat()));
        }
        return mStyle;
    }

    /**
     * Converts a Sirius font format collection to m2doc modifiers.
     * 
     * @param fontFormats
     *            the font formats.
     * @return the converted modifiers.
     */
    private static int convertFontFormat(EList<FontFormat> fontFormats) {
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
     * Converts a Sirius RGB color to m2doc color.
     * 
     * @param rgb
     *            the color to convert.
     * @return the converted color.
     */
    public static Color convertColor(final RGBValues rgb) {
        if (rgb != null) {
            return new Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue());
        } else {
            return null;
        }
    }

}
