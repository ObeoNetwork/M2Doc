/*******************************************************************************
 *  Copyright (c) 2018 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.services;

import java.awt.Color;
import java.io.IOException;
import java.util.Locale;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.obeonetwork.m2doc.element.MElement;
import org.obeonetwork.m2doc.element.MStyle;
import org.obeonetwork.m2doc.element.MTable;
import org.obeonetwork.m2doc.element.MTable.MCell;
import org.obeonetwork.m2doc.element.MTable.MRow;
import org.obeonetwork.m2doc.element.impl.MStyleImpl;
import org.obeonetwork.m2doc.element.impl.MTableImpl;
import org.obeonetwork.m2doc.element.impl.MTableImpl.MCellImpl;
import org.obeonetwork.m2doc.element.impl.MTableImpl.MRowImpl;
import org.obeonetwork.m2doc.element.impl.MTextImpl;

//@formatter:off
@ServiceProvider(
value = "Services available for Excel"
)
//@formatter:on
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype" })
public class ExcelServices {

    /**
     * The mask for unsigned values.
     */
    private static final int MASK = 0xFF;

    /**
     * The URI converter to use.
     */
    private URIConverter uriConverter;

    /**
     * The template URI.
     */
    private final URI templateURI;

    /**
     * Constructor.
     * 
     * @param uriConverter
     *            the {@link URIConverter uri converter} to use.
     * @param templateURI
     *            the template {@link URI}
     */
    public ExcelServices(URIConverter uriConverter, URI templateURI) {
        this.uriConverter = uriConverter;
        this.templateURI = templateURI;
    }

    // @formatter:off
    @Documentation(
        value = "Insert a table from an Excel .xlsx file.",
        params = {
            @Param(name = "uri", value = "The Excel .xlsx file uri, it can be relative to the template"),
            @Param(name = "sheetName", value = "The sheet name"),
            @Param(name = "topLeftCellAdress", value = "The top left cell address"),
            @Param(name = "bottomRightCellAdress", value = "The bottom right cell address"),
        },
        result = "insert the table",
        examples = {
            @Example(expression = "'excel.xlsx'.asTable('Feuil1', 'C3', 'F7')", result = "insert the table from 'excel.xlsx'"),
        }
    )
    // @formatter:on
    public MTable asTable(String uriStr, String sheetName, String topLeftCellAdress, String bottomRightCellAdress)
            throws IOException {
        return asTable(uriStr, sheetName, topLeftCellAdress, bottomRightCellAdress, null);
    }

    // @formatter:off
    @Documentation(
        value = "Insert a table from an Excel .xlsx file.",
        params = {
            @Param(name = "uri", value = "The Excel .xlsx file uri, it can be relative to the template"),
            @Param(name = "sheetName", value = "The sheet name"),
            @Param(name = "topLeftCellAdress", value = "The top left cell address"),
            @Param(name = "bottomRightCellAdress", value = "The bottom right cell address"),
            @Param(name = "languageTag", value = "The language tag for the locale"),
        },
        result = "insert the table",
        examples = {
            @Example(expression = "'excel.xlsx'.asTable('Feuil1', 'C3', 'F7', 'fr-FR')", result = "insert the table from 'excel.xlsx'"),
        }
    )
    // @formatter:on
    public MTable asTable(String uriStr, String sheetName, String topLeftCellAdress, String bottomRightCellAdress,
            String languageTag) throws IOException {
        final MTable res = new MTableImpl();

        final URI xlsxURI = URI.createURI(uriStr, false);
        final URI uri = xlsxURI.resolve(templateURI);

        try (XSSFWorkbook workbook = new XSSFWorkbook(uriConverter.createInputStream(uri));) {
            final FormulaEvaluator evaluator = new XSSFFormulaEvaluator(workbook);
            final XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException(String.format("The sheet %s doesn't exists in %s.", sheetName, uri));
            } else {
                final Locale locale;
                if (languageTag != null) {
                    locale = Locale.forLanguageTag(languageTag);
                } else {
                    locale = Locale.getDefault();
                }
                final DataFormatter dataFormatter = new DataFormatter(locale);
                final CellAddress start = new CellAddress(topLeftCellAdress);
                final CellAddress end = new CellAddress(bottomRightCellAdress);
                int rowIndex = start.getRow();
                while (rowIndex <= end.getRow()) {
                    final XSSFRow row = sheet.getRow(rowIndex++);
                    if (row != null) {
                        final MRow mRow = new MRowImpl();
                        int cellIndex = start.getColumn();
                        while (cellIndex <= end.getColumn()) {
                            final XSSFCell cell = row.getCell(cellIndex++);
                            if (cell != null) {
                                final MStyle style = getStyle(cell);
                                final MElement text = new MTextImpl(dataFormatter.formatCellValue(cell, evaluator),
                                        style);
                                final Color background = getColor(cell.getCellStyle().getFillForegroundColorColor());
                                final MCell mCell = new MCellImpl(text, background);
                                mRow.getCells().add(mCell);
                            } else {
                                mRow.getCells().add(createEmptyCell());
                            }
                        }
                        res.getRows().add(mRow);
                    } else {
                        final int length = end.getColumn() - start.getColumn() + 1;
                        res.getRows().add(createEmptyRow(length));
                    }
                }

            }
        }

        return res;
    }

    /**
     * Creates an {@link MRow} of the given length populated with {@link #createEmptyCell() empty cells}.
     * 
     * @param lenght
     *            the length of the {@link MRow}
     * @return the created {@link MRow}
     */
    private MRow createEmptyRow(int length) {
        final MRow res = new MRowImpl();

        for (int i = 0; i < length; i++) {
            res.getCells().add(createEmptyCell());
        }

        return res;
    }

    private MCellImpl createEmptyCell() {
        return new MCellImpl(new MTextImpl("", null), null);
    }

    /**
     * Gets the {@link MStyle} of the given {@link XSSFCell}.
     * 
     * @param cellthe
     *            {@link XSSFCell}
     * @return the {@link MStyle} of the given {@link XSSFCell}
     */
    private MStyle getStyle(XSSFCell cell) {
        final XSSFCellStyle style = cell.getCellStyle();
        final XSSFFont font = style.getFont();
        int modifiers = 0;
        if (font.getBold()) {
            modifiers |= MStyle.FONT_BOLD;
        }
        if (font.getItalic()) {
            modifiers |= MStyle.FONT_ITALIC;
        }
        if (font.getStrikeout()) {
            modifiers |= MStyle.FONT_STRIKE_THROUGH;
        }
        if (font.getUnderline() != 0) {
            modifiers |= MStyle.FONT_UNDERLINE;
        }

        return new MStyleImpl(font.getFontHeightInPoints(), getColor(font.getXSSFColor()), modifiers);
    }

    /**
     * Gets the {@link Color} form the given {@link XSSFColor}.
     * 
     * @param color
     *            the {@link XSSFColor}
     * @return the {@link Color} form the given {@link XSSFColor}
     */
    private Color getColor(XSSFColor color) {
        final Color res;

        if (color != null && color.getRGB() != null) {
            final byte[] rgb = color.getRGB();
            final int r = rgb[0] & MASK;
            final int g = rgb[1] & MASK;
            final int b = rgb[2] & MASK;
            res = new Color(r, g, b);
        } else {
            res = null;
        }

        return res;
    }

}
