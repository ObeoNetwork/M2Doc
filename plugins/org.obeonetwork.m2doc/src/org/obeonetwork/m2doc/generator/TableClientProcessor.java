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
package org.obeonetwork.m2doc.generator;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.IRunBody;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.obeonetwork.m2doc.element.MStyle;
import org.obeonetwork.m2doc.element.MTable;
import org.obeonetwork.m2doc.element.MTable.MCell;
import org.obeonetwork.m2doc.element.MTable.MRow;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.provider.AbstractTableProvider;
import org.obeonetwork.m2doc.provider.ProviderException;
import org.obeonetwork.m2doc.util.M2DocUtils;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;

import static com.google.common.base.Preconditions.checkNotNull;

import static org.obeonetwork.m2doc.provider.ProviderConstants.HIDE_TITLE_KEY;

/**
 * Class that inserts a table in a document for a m:wtable tag.
 * 
 * @author ldelaigue
 */
public final class TableClientProcessor {
    /** The current generated document. */
    private final IBody body;
    /** Arguments of the m:wtable tag. */
    private final Map<String, Object> parameters;
    /** The table provider. */
    private final AbstractTableProvider provider;

    /**
     * The {@link ResourceSet} for model variables.
     */
    private final ResourceSet resourceSetForModels;

    /**
     * Constructor.
     * 
     * @param body
     *            The current generated document, must not be <code>null</code>
     * @param provider
     *            The table provider, must not be <code>null</code>
     * @param arguments
     *            The map of arguments, cannot be <code>null</code>
     * @param resourceSetForModels
     *            the resourceset keeping the model instances.
     */
    public TableClientProcessor(IBody body, AbstractTableProvider provider, Map<String, Object> arguments,
            ResourceSet resourceSetForModels) {
        this.body = checkNotNull(body);
        this.provider = checkNotNull(provider);
        this.parameters = checkNotNull(arguments);
        this.resourceSetForModels = resourceSetForModels;
    }

    /**
     * Generate the table(s) in the document.
     * 
     * @param run
     *            The run where the tables must be output
     * @throws ProviderException
     *             If the retrieval of the tables from the provider goes wrong.
     */
    public void generate(XWPFRun run) throws ProviderException {
        List<MTable> tables = provider.getTables(resourceSetForModels, parameters);
        boolean first = true;
        for (MTable mtable : tables) {
            XWPFTable table = createTable(run, first, mtable);
            if (table != null) {
                fillTable(table, mtable);
                first = false;
            }
        }
    }

    /**
     * Do we output the table title.
     * 
     * @return Whether the table must have a title or not.
     */
    private boolean showTitle() {
        if (parameters.containsKey(HIDE_TITLE_KEY)) {
            Object hide = parameters.get(HIDE_TITLE_KEY);
            if (hide instanceof String) {
                return !Boolean.valueOf((String) hide).booleanValue();
            }
        }
        return true;
    }

    /**
     * Create a table just after a given run.
     * 
     * @param tableRun
     *            The run
     * @param first
     *            Whether it's the 1st table to create or not
     * @param mtable
     *            The table description
     * @return The newly created table, can be <code>null</code> if the tag was used in an unsupported context.
     */
    private XWPFTable createTable(XWPFRun tableRun, boolean first, MTable mtable) {
        XWPFTable table = null;
        if (body instanceof XWPFDocument) {
            table = createTableInDocument(tableRun, first, mtable);
        } else if (body instanceof XWPFTableCell) {
            XWPFTableCell tcell = (XWPFTableCell) body;
            table = createTableInCell(tableRun, first, mtable, tcell);
        } else {
            M2DocUtils.appendMessageRun((XWPFParagraph) tableRun.getParent(), ValidationMessageLevel.ERROR,
                    "m:table can't be inserted here.");
        }
        return table;
    }

    /**
     * Create a table in a document.
     * 
     * @param tableRun
     *            the run after which the table must be created
     * @param first
     *            Whether it's the first table to insert or not
     * @param mtable
     *            The table description
     * @return The newly created table.
     */
    private XWPFTable createTableInDocument(XWPFRun tableRun, boolean first, MTable mtable) {
        XWPFTable table;
        if (!first) {
            ((XWPFDocument) body).createParagraph();
        }
        if (showTitle() && mtable.getLabel() != null) {
            XWPFRun captionRun;
            if (first) {
                captionRun = tableRun;
                IRunBody runBody = captionRun.getParent();
                if (runBody instanceof XWPFParagraph) {
                    ((XWPFParagraph) runBody).setSpacingAfter(0);
                }
            } else {
                XWPFParagraph captionParagraph = ((XWPFDocument) body).createParagraph();
                captionParagraph.setSpacingAfter(0);
                captionRun = captionParagraph.createRun();
            }
            captionRun.setText(mtable.getLabel());
            captionRun.setBold(true);
        }
        table = ((XWPFDocument) body).createTable();
        return table;
    }

    /**
     * Create a table in a table cell.
     * 
     * @param tableRun
     *            The table run
     * @param first
     *            whether it's the 1st table created in this cell
     * @param mtable
     *            The table description
     * @param tcell
     *            The cell in which to create a new table
     * @return The newly creatted table, located in the given cell.
     */
    private XWPFTable createTableInCell(XWPFRun tableRun, boolean first, MTable mtable, XWPFTableCell tcell) {
        XWPFTable table;
        if (showTitle() && mtable.getLabel() != null) {
            XWPFRun captionRun;
            if (first) {
                captionRun = tableRun;
                IRunBody runBody = captionRun.getParent();
                if (runBody instanceof XWPFParagraph) {
                    ((XWPFParagraph) runBody).setSpacingAfter(0);
                }
            } else {
                XWPFParagraph captionParagraph = tcell.addParagraph();
                captionParagraph.setSpacingAfter(0);
                captionRun = captionParagraph.createRun();
            }
            captionRun.setText(mtable.getLabel());
            captionRun.setBold(true);
        }
        CTTbl ctTbl = tcell.getCTTc().addNewTbl();
        table = new XWPFTable(ctTbl, tcell);
        int tableRank = tcell.getTables().size();
        tcell.insertTable(tableRank, table);
        // A paragraph is mandatory at the end of a cell, so let's always add one.
        tcell.addParagraph();
        return table;
    }

    /**
     * Fill a newly created word table with the data from an MTable.
     * 
     * @param table
     *            The newly created word table
     * @param mtable
     *            The MTable that describes the data and styles to insert
     */
    private void fillTable(XWPFTable table, MTable mtable) {
        List<MRow> rows = mtable.getRows();
        // Iterate over the rows
        for (int rowIdx = 0; rowIdx < rows.size(); rowIdx++) {
            MRow mRow = rows.get(rowIdx);

            // Get or create XWPF row
            XWPFTableRow xwpfRow;
            if (table.getNumberOfRows() > rowIdx) {
                xwpfRow = table.getRow(rowIdx);
            } else {
                xwpfRow = table.createRow();
            }

            // Iterate over the columns
            for (int colIdx = 0; colIdx < mtable.getColumnsCount(); colIdx++) {
                // Get or create XWPF cell
                XWPFTableCell xwpfCell;
                if (xwpfRow.getTableCells().size() > colIdx) {
                    xwpfCell = xwpfRow.getCell(colIdx);
                } else {
                    xwpfCell = xwpfRow.createCell();
                }

                // Populate cell
                XWPFParagraph xwpfCellParagraph = xwpfCell.getParagraphs().get(0);
                xwpfCellParagraph.setSpacingBefore(0);
                xwpfCellParagraph.setSpacingAfter(0);
                MCell mCell = null;
                if (colIdx < mRow.getCells().size()) {
                    mCell = mRow.getCells().get(colIdx);
                }
                setCellContent(xwpfCell, mCell);
            }
        }
    }

    /**
     * Create a new run in the cell's paragraph and set this run's text, and apply the given style to the cell and its paragraph.
     * 
     * @param cell
     *            Cell to fill in
     * @param mCell
     *            the cell to read data from
     */
    private void setCellContent(XWPFTableCell cell, MCell mCell) {
        XWPFParagraph cellParagraph = cell.getParagraphs().get(0);
        XWPFRun cellRun = cellParagraph.createRun();
        if (mCell != null) {
            String label = mCell.getLabel();
            if (label != null) {
                cellRun.setText(label);
            }
            MStyle style = mCell.getStyle();
            if (style != null) {
                if (style.getBackgroundColor() != null) {
                    cell.setColor(hexColor(style.getBackgroundColor()));
                }
                applyTableClientStyle(cellRun, style);
            }
        }
    }

    /**
     * Apply the given style to the given run. Background color is not taken into account here since it does not apply to runs.
     * 
     * @param run
     *            The run to style
     * @param style
     *            The style to apply, can be <code>null</code>
     */
    private void applyTableClientStyle(XWPFRun run, MStyle style) {
        run.setFontSize(style.getFontSize());
        run.setBold((style.getFontModifiers() & MStyle.FONT_BOLD) != 0);
        run.setItalic((style.getFontModifiers() & MStyle.FONT_ITALIC) != 0);
        if ((style.getFontModifiers() & MStyle.FONT_UNDERLINE) != 0) {
            run.setUnderline(UnderlinePatterns.SINGLE);
        }
        run.setStrikeThrough((style.getFontModifiers() & MStyle.FONT_STRIKE_THROUGH) != 0);
        run.setColor(hexColor(style.getForegroundColor()));
    }

    /**
     * Translate a {@link Color} to the word format.
     * 
     * @param color
     *            the {@link Color}
     * @return The color as a 6-digits string.
     */
    private static String hexColor(Color color) {
        return String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
}
