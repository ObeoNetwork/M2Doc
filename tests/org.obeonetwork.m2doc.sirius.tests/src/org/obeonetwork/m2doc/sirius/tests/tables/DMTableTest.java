package org.obeonetwork.m2doc.sirius.tests.tables;

import com.google.common.collect.Iterables;

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
import org.obeonetwork.m2doc.provider.AbstractTableProvider.MCell;
import org.obeonetwork.m2doc.provider.AbstractTableProvider.MColumn;
import org.obeonetwork.m2doc.provider.AbstractTableProvider.MRow;
import org.obeonetwork.m2doc.provider.AbstractTableProvider.MStyle;
import org.obeonetwork.m2doc.sirius.providers.tables.DMTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class DMTableTest {

    private DTable dtable;

    private DTableElementStyle styleRow;
    private DTableElementStyle styleCol;
    private DCellStyle styleCell;

    private DMTable table;

    @Test
    public void test() {
        assertEquals("Test Table", table.getLabel());
        assertEquals(2, Iterables.size(table.getColumns()));
        assertEquals(2, Iterables.size(table.getRows()));

        Iterator<? extends MColumn> colIt = table.getColumns().iterator();
        MColumn col1 = colIt.next();
        assertEquals("Col One", col1.getLabel());
        MStyle style = col1.getStyle();
        assertEquals(6, style.getFontSize());
        assertEquals(0x404040, style.getBackgroundColor());
        assertEquals(0x000000, style.getForegroundColor());
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_BOLD);
        assertNotEquals(0, style.getFontModifiers() & MStyle.FONT_ITALIC);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_STRIKE_THROUGH);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_UNDERLINE);

        MColumn col2 = colIt.next();
        assertEquals("Col Two", col2.getLabel());
        assertNull(col2.getStyle());

        Iterator<? extends MRow> rowIt = table.getRows().iterator();
        // First row
        MRow row = rowIt.next();
        assertEquals("Row One", row.getLabel());
        assertNull(row.getStyle());

        assertEquals(1, Iterables.size(row.getCells()));
        MCell cell11 = row.getCells().iterator().next();
        assertSame(col1, cell11.getColumn());
        assertEquals("Cell One One", cell11.getLabel());
        assertNull(cell11.getStyle());

        // Second row
        row = rowIt.next();
        assertEquals("Row Two", row.getLabel());
        style = row.getStyle();
        assertEquals(3, style.getFontSize());
        assertEquals(0xffffff, style.getBackgroundColor());
        assertEquals(0x000000, style.getForegroundColor());
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_BOLD);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_ITALIC);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_STRIKE_THROUGH);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_UNDERLINE);

        assertEquals(1, Iterables.size(row.getCells()));
        MCell cell22 = row.getCells().iterator().next();
        assertSame(col2, cell22.getColumn());
        assertEquals("Cell Two Two", cell22.getLabel());
        style = cell22.getStyle();
        assertEquals(9, style.getFontSize());
        assertEquals(0xffffff, style.getBackgroundColor());
        assertEquals(0x804000, style.getForegroundColor());
        assertNotEquals(0, style.getFontModifiers() & MStyle.FONT_BOLD);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_ITALIC);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_STRIKE_THROUGH);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_UNDERLINE);
    }

    @Before
    public void setUp() {
        dtable = TableFactory.eINSTANCE.createDTable();
        dtable.setName("Test Table");

        styleRow = TableFactory.eINSTANCE.createDTableElementStyle();
        styleRow.setLabelSize(3);

        styleCol = TableFactory.eINSTANCE.createDTableElementStyle();
        styleCol.setLabelSize(6);
        styleCol.setBackgroundColor(RGBValues.create(0x40, 0x40, 0x40));
        styleCol.getLabelFormat().add(FontFormat.ITALIC_LITERAL);

        styleCell = TableFactory.eINSTANCE.createDCellStyle();
        styleCell.setLabelSize(9);
        styleCell.setForegroundColor(RGBValues.create(0x80, 0x40, 0));
        styleCell.getLabelFormat().add(FontFormat.BOLD_LITERAL);

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

        table = new DMTable(dtable);
    }
}
