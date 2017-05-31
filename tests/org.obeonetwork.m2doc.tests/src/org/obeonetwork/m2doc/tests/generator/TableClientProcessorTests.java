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
package org.obeonetwork.m2doc.tests.generator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Before;
import org.junit.Test;
import org.obeonetwork.m2doc.element.MStyle;
import org.obeonetwork.m2doc.element.MTable;
import org.obeonetwork.m2doc.element.MTable.MCell;
import org.obeonetwork.m2doc.element.MTable.MRow;
import org.obeonetwork.m2doc.element.impl.MTableImpl;
import org.obeonetwork.m2doc.generator.TableClientProcessor;
import org.obeonetwork.m2doc.provider.AbstractTableProvider;
import org.obeonetwork.m2doc.provider.OptionType;
import org.obeonetwork.m2doc.provider.ProviderException;
import org.obeonetwork.m2doc.provider.ProviderValidationMessage;

import static org.junit.Assert.assertEquals;

/**
 * Tests of {@link TableClientProcessor}.
 * 
 * @author ldelaigue
 */
// CHECKSTYLE:OFF
public class TableClientProcessorTests {

    protected TableClientProcessor processor;

    protected XWPFDocument doc;
    protected XWPFParagraph paragraph;
    protected XWPFRun run;
    protected Map<String, OptionType> options;
    protected AbstractTableProvider provider;

    @Test
    public void testWithoutAnyParameter() throws ProviderException {
        Map<String, Object> arguments = new HashMap<>();
        processor = new TableClientProcessor(doc, provider, arguments, new ResourceSetImpl());
        processor.generate(run);

        checkParagraph(paragraph, "Test Table");
        List<XWPFTable> tables = doc.getTables();
        assertEquals(1, tables.size());
        XWPFTable table = tables.get(0);
        checkTable(table);
    }

    @Test
    public void testWithParamHideTitleFalse() throws ProviderException {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("hideTitle", "false");
        processor = new TableClientProcessor(doc, provider, arguments, new ResourceSetImpl());
        processor.generate(run);

        checkParagraph(paragraph, "Test Table");
        List<XWPFTable> tables = doc.getTables();
        assertEquals(1, tables.size());
        XWPFTable table = tables.get(0);
        checkTable(table);
    }

    @Test
    public void testWithParamHideTitleTrue() throws ProviderException {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("hideTitle", "true");
        processor = new TableClientProcessor(doc, provider, arguments, new ResourceSetImpl());
        processor.generate(run);

        assertEquals("", paragraph.getText());
        List<XWPFTable> tables = doc.getTables();
        assertEquals(1, tables.size());
        XWPFTable table = tables.get(0);
        checkTable(table);
    }

    protected void checkParagraph(XWPFParagraph paragraph, String expectedTitle) {
        assertEquals(expectedTitle, paragraph.getText());
        assertEquals(0, paragraph.getSpacingAfter());
        List<XWPFRun> runs = paragraph.getRuns();
        assertEquals(1, runs.size());
    }

    protected void checkTable(XWPFTable table) {
        assertEquals(3, table.getNumberOfRows());
        List<XWPFTableRow> rows = table.getRows();
        XWPFTableRow row = rows.get(0);
        assertEquals("", row.getCell(0).getText());
        assertEquals("Col 1", row.getCell(1).getText());
        assertEquals("Col 2", row.getCell(2).getText());
        row = rows.get(1);
        assertEquals("Row 1", row.getCell(0).getText());
        assertEquals("Cell 1 1", row.getCell(1).getText());
        assertEquals("", row.getCell(2).getText());
        row = rows.get(2);
        assertEquals("Row 2", row.getCell(0).getText());
        assertEquals("", row.getCell(1).getText());
        assertEquals("Cell 2 2", row.getCell(2).getText());
    }

    @Before
    public void setUp() {
        doc = new XWPFDocument();
        paragraph = doc.createParagraph();
        run = paragraph.createRun();
        provider = new AbstractTableProvider() {
            @Override
            public Map<String, OptionType> getOptionTypes() {
                return options;
            }

            @Override
            public List<MTable> getTables(ResourceSet set, Map<String, Object> parameters) throws ProviderException {
                return getTestTables();
            }

            @Override
            public List<ProviderValidationMessage> validate(Map<String, Object> params) {
                return Collections.emptyList();
            }
        };
    }

    /**
     * Provide the list of {@link MTable}s to use in this test class.
     * 
     * @return The list of tables to use in this test class. Can be overridden.
     */
    protected List<MTable> getTestTables() {
        List<MTable> result = new ArrayList<>();
        result.add(getTestTable());
        return result;
    }

    /**
     * Create a test table like this:
     * <table>
     * <thead>
     * <tr>
     * <th></th>
     * <th>"Col 1"</th>
     * <th>"Col 2"</th>
     * </thead>
     * <tbody>
     * <tr>
     * <td>"Row 1"</td>
     * <td>"Cell 1 1"</td>
     * <td></td>
     * </tr>
     * <tr>
     * <td>"Row 2"</td>
     * <td></td>
     * <td>"Cell 2 2"</td>
     * </tr>
     * </tbody>
     * </table>
     * 
     * @return
     */
    protected TestTable getTestTable() {
        TestTable table = new TestTable("Test Table");

        TestRow row0 = new TestRow();
        table.getRows().add(row0);
        TestRow row1 = new TestRow();
        table.getRows().add(row1);
        TestRow row2 = new TestRow();
        table.getRows().add(row2);

        // Null cell at first
        row0.getCells().add(null);
        TestCell col1 = new TestCell("Col 1");
        row0.getCells().add(col1);
        TestCell col2 = new TestCell("Col 2");
        row0.getCells().add(col2);

        TestCell cell10 = new TestCell("Row 1");
        row1.getCells().add(cell10);
        TestCell cell11 = new TestCell("Cell 1 1");
        row1.getCells().add(cell11);
        // Cell 1-2 not populated
        // TestCell cell12 = new TestCell("Cell 1 2");
        // row1.getCells().add(cell12);

        TestCell cell20 = new TestCell("Row 2");
        row2.getCells().add(cell20);
        TestCell cell21 = null;
        row2.getCells().add(cell21);
        TestCell cell22 = new TestCell("Cell 2 2");
        row2.getCells().add(cell22);
        return table;
    }

    public abstract static class TestLabeledElement {
        protected String label;

        public TestLabeledElement(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

    }

    public abstract static class TestStyledElement extends TestLabeledElement {
        protected MStyle style;

        public TestStyledElement(String label) {
            super(label);
        }

        public MStyle getStyle() {
            return style;
        }

        public void setStyle(MStyle style) {
            this.style = style;
        }

    }

    public static class TestTable extends MTableImpl {

        public TestTable(String label) {
            super();
            setLabel(label);
        }

    }

    public static class TestRow implements MRow {

        protected final List<MCell> cells = new ArrayList<>();

        public TestRow() {
            super();
        }

        @Override
        public List<MCell> getCells() {
            return cells;
        }

    }

    public static class TestCell extends TestStyledElement implements MCell {

        public TestCell(String label) {
            super(label);
        }

    }

    public static class TestStyle implements MStyle {
        private Color backgroundColor;
        private Color foregroundColor;
        private int fontModifiers;
        private int fontSize;

        public void setBackgroundColor(String color) {
            this.backgroundColor = new Color(Integer.parseInt(color, 16));
        }

        public void setForegroundColor(String color) {
            this.foregroundColor = new Color(Integer.parseInt(color, 16));
        }

        public void setBackgroundColor(int bg) {
            this.backgroundColor = new Color(bg);
        }

        public void setForegroundColor(int fg) {
            this.foregroundColor = new Color(fg);
        }

        public void setFontModifiers(int fontModifiers) {
            this.fontModifiers = fontModifiers;
        }

        @Override
        public void setFontSize(int fontSize) {
            this.fontSize = fontSize;
        }

        @Override
        public Color getBackgroundColor() {
            return backgroundColor;
        }

        @Override
        public Color getForegroundColor() {
            return foregroundColor;
        }

        @Override
        public int getFontModifiers() {
            return fontModifiers;
        }

        @Override
        public int getFontSize() {
            return fontSize;
        }

        @Override
        public void setForegroundColor(Color color) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setBackgroundColor(Color color) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setModifiers(int modifiers) {
            throw new UnsupportedOperationException();
        }
    }
}
// CHECKSTYLE:ON
