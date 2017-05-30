package org.obeonetwork.m2doc.sirius.tests.tables;

import com.google.common.collect.Iterables;

import java.awt.Color;
import java.util.Iterator;

import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DCellStyle;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.table.metamodel.table.TableFactory;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.junit.Before;
import org.junit.Test;
import org.obeonetwork.m2doc.element.MStyle;
import org.obeonetwork.m2doc.element.MTable;
import org.obeonetwork.m2doc.element.MTable.MCell;
import org.obeonetwork.m2doc.element.MTable.MRow;
import org.obeonetwork.m2doc.sirius.util.DTable2MTableConverter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class DMTableTest {

    private static final int BROWN = 0x804000;

    private static final int WHITE = 0xffffff;

    private static final int GRAY = 0x404040;

    private static final int BLACK = 0x000000;

    private DTable dtable;

    private DTableElementStyle styleRow;
    private DTableElementStyle styleCol;
    private DCellStyle styleCell;

    private MTable table;

    @Test
    public void test() {
        assertEquals("Test Table", table.getLabel());
        assertEquals(3, table.getColumnsCount()); // Header column + 2 columns
        assertEquals(3, Iterables.size(table.getRows())); // Header + 2 rows
        Iterator<? extends MRow> rowIt = table.getRows().iterator();

        // Header row
        MRow row = rowIt.next();
        MCell col0 = row.getCells().get(0);
        assertNull(col0.getLabel());
        assertNull(col0.getStyle());

        MCell col1 = row.getCells().get(1);
        assertEquals("Col One", col1.getLabel());
        assertStyleEqualsTo(DTable2MTableConverter.HEADER_STYLE, col1.getStyle());

        MCell col2 = row.getCells().get(2);
        assertEquals("Col Two", col2.getLabel());
        assertStyleEqualsTo(DTable2MTableConverter.HEADER_STYLE, col2.getStyle());

        // First row
        row = rowIt.next();
        assertEquals(3, Iterables.size(row.getCells()));
        MCell cell10 = row.getCells().get(0);
        assertEquals("Row One", cell10.getLabel());
        assertStyleEqualsTo(DTable2MTableConverter.HEADER_STYLE, cell10.getStyle());

        MCell cell11 = row.getCells().get(1);
        MStyle style = cell11.getStyle();
        assertEquals("Cell One One", cell11.getLabel());
        assertEquals(6, style.getFontSize());
        assertEquals(new Color(GRAY), style.getBackgroundColor());
        assertEquals(new Color(BLACK), style.getForegroundColor());
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_BOLD);
        assertNotEquals(0, style.getFontModifiers() & MStyle.FONT_ITALIC);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_STRIKE_THROUGH);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_UNDERLINE);

        MCell cell12 = row.getCells().get(2);
        assertNull(cell12.getLabel()); // No label
        assertNull(cell12.getStyle()); // No column nor row style, so style == null

        // Second row
        row = rowIt.next();
        assertEquals(3, Iterables.size(row.getCells()));
        MCell cell20 = row.getCells().get(0);
        assertEquals("Row Two", cell20.getLabel());
        assertStyleEqualsTo(DTable2MTableConverter.HEADER_STYLE, cell20.getStyle());

        MCell cell21 = row.getCells().get(1);
        assertNull(cell21.getLabel());
        style = cell21.getStyle(); // no row style, but column style, so style == column style
        assertEquals(3, style.getFontSize());
        assertEquals(new Color(WHITE), style.getBackgroundColor());
        assertEquals(new Color(BLACK), style.getForegroundColor());
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_BOLD);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_ITALIC);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_STRIKE_THROUGH);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_UNDERLINE);

        MCell cell22 = row.getCells().get(2);
        assertEquals("Cell Two Two", cell22.getLabel());
        style = cell22.getStyle();
        // CHECKSTYLE:OFF
        assertEquals(9, style.getFontSize());
        // CHECKSTYLE:ON
        assertEquals(new Color(WHITE), style.getBackgroundColor());
        assertEquals(new Color(BROWN), style.getForegroundColor());
        assertNotEquals(0, style.getFontModifiers() & MStyle.FONT_BOLD);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_ITALIC);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_STRIKE_THROUGH);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_UNDERLINE);
    }

    private void assertStyleEqualsTo(MStyle left, MStyle right) {
        if (left == null) {
            assertNull(right);
        } else {
            assertEquals(left.getForegroundColor(), right.getForegroundColor());
            assertEquals(left.getBackgroundColor(), right.getBackgroundColor());
            assertEquals(left.getFontModifiers(), right.getFontModifiers());
            assertEquals(left.getFontSize(), right.getFontSize());
        }
    }

    @Before
    public void setUp() {
        dtable = TableFactory.eINSTANCE.createDTable();
        dtable.setName("Test Table");

        styleRow = TableFactory.eINSTANCE.createDTableElementStyle();
        styleRow.setLabelSize(3);

        styleCol = TableFactory.eINSTANCE.createDTableElementStyle();
        styleCol.setLabelSize(6);
        // CHECKSTYLE:OFF
        styleCol.setBackgroundColor(RGBValues.create(0x40, 0x40, 0x40));
        styleCol.getLabelFormat().add(FontFormat.ITALIC_LITERAL);

        styleCell = TableFactory.eINSTANCE.createDCellStyle();
        styleCell.setLabelSize(9);
        styleCell.setForegroundColor(RGBValues.create(0x80, 0x40, 0));
        styleCell.getLabelFormat().add(FontFormat.BOLD_LITERAL);
        // CHECKSTYLE:ON
        DColumn col1 = TableFactory.eINSTANCE.createDFeatureColumn();
        col1.setLabel("Col One");
        col1.setCurrentStyle(styleCol);
        col1.setVisible(true);
        col1.setTable(dtable);

        DColumn col2 = TableFactory.eINSTANCE.createDTargetColumn();
        col2.setLabel("Col Two");
        col2.setVisible(true);
        col2.setTable(dtable);

        DLine line1 = TableFactory.eINSTANCE.createDLine();
        line1.setLabel("Row One");
        line1.setVisible(true);
        dtable.getLines().add(line1);

        DCell cell11 = TableFactory.eINSTANCE.createDCell();
        cell11.setLabel("Cell One One");
        cell11.setColumn(col1);
        line1.getCells().add(cell11);

        DLine line2 = TableFactory.eINSTANCE.createDLine();
        line2.setLabel("Row Two");
        line2.setCurrentStyle(styleRow);
        dtable.getLines().add(line2);

        DCell cell22 = TableFactory.eINSTANCE.createDCell();
        cell22.setLabel("Cell Two Two");
        cell22.setColumn(col2);
        cell22.setCurrentStyle(styleCell);
        line2.getCells().add(cell22);

        table = DTable2MTableConverter.convert(dtable);
    }
}
