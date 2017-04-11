/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.provider;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Abstract super-implementation of all table providers.
 * 
 * @author ldelaigue
 */
public abstract class AbstractTableProvider implements IProvider {

    /**
     * Provide the tables to insert in the word document.
     * 
     * @param parameters
     *            Map of arguments
     * @param resourceSetForModels
     *            the resourceset used for loading the models.
     * @return The list of table to insert, may be empty but should not be <code>null</code>.
     * @throws ProviderException
     *             If something goes wrong during the computation of the tables.
     */
    public abstract List<MTable> getTables(ResourceSet resourceSetForModels, Map<String, Object> parameters)
            throws ProviderException;

    /**
     * Interface that represents a table that can be inserted in a word document.
     * 
     * @author ldelaigue
     */
    public interface MTable {
        /**
         * The table columns.
         * 
         * @return All the table columns. An implicit untitled column is added to hold the row headers, hence this method must not return a
         *         Column for the row headers.
         */
        Iterable<? extends MColumn> getColumns();

        /**
         * Tha table rows.
         * 
         * @return All the table rows.
         */
        Iterable<? extends MRow> getRows();

        /**
         * The table label (caption).
         * 
         * @return The table label, <code>null</code> if the table has no caption.
         */
        String getLabel();
    }

    /**
     * Interface that represents a table column, whose label can be used as column header.
     * 
     * @author ldelaigue
     */
    public interface MColumn {
        /**
         * The column header style.
         * 
         * @return The style to use for the column header.
         */
        MStyle getStyle();

        /**
         * The column label.
         * 
         * @return The column label.
         */
        String getLabel();
    }

    /**
     * Interface that represents a table row, whose label can be used as row header.
     * 
     * @author ldelaigue
     */
    public interface MRow {
        /**
         * The row header style.
         * 
         * @return The style to use for the row header.
         */
        MStyle getStyle();

        /**
         * The row label.
         * 
         * @return The row label.
         */
        String getLabel();

        /**
         * The row's defined cells (i.e. non-empty cells). A row may have no cell at all, or may not have a cell for each column of its
         * table.
         * 
         * @return The row's defined cells.
         */
        Iterable<? extends MCell> getCells();
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
         * The cell label, i.e. its textual content.
         * 
         * @return The cell text.
         */
        String getLabel();

        /**
         * The cell's column.
         * 
         * @return The cell's column, that can be used to locate the cell in the table.
         */
        MColumn getColumn();
    }

    /**
     * Describes the style of an element.
     * 
     * @author ldelaigue
     */
    public interface MStyle {
        /** Modifier flag for BOLD font, to be checked by and-ing it with the value returned by getFontModidiers(). */
        int FONT_BOLD = 1;
        /** Modifier flag for ITALIC font, to be checked by and-ing it with the value returned by getFontModidiers(). */
        int FONT_ITALIC = 1 << 1;
        /** Modifier flag for UNDERLINE font, to be checked by and-ing it with the value returned by getFontModidiers(). */
        int FONT_UNDERLINE = 1 << 2;
        /** Modifier flag for STRIKE-THROUGH font, to be checked by and-ing it with the value returned by getFontModidiers(). */
        int FONT_STRIKE_THROUGH = 1 << 3;

        /**
         * The font size.
         * 
         * @return The font size in points.
         */
        int getFontSize();

        /**
         * The foreground color, as an integer where red is left-shifted by 16, green is left-shifted by 8 and blue is
         * right-aligned.
         * 
         * @return The foreground color.
         */
        int getForegroundColor();

        /**
         * The background color, as an integer where red is left-shifted by 16, green is left-shifted by 8 and blue is
         * right-aligned.
         * 
         * @return The background color.
         */
        int getBackgroundColor();

        /**
         * The font modifiers. To know whether the font is bold, use:
         * <code>getFontModifiers() & MStyle.FONT_BOLD != 0</code>
         * 
         * @return An integer that aggregates the font modifiers.
         */
        int getFontModifiers();
    }
}
