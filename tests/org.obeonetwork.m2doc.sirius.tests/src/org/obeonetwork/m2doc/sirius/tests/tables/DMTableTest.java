package org.obeonetwork.m2doc.sirius.tests.tables;

import java.awt.Color;
import java.util.Iterator;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
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
import org.obeonetwork.m2doc.element.MImage;
import org.obeonetwork.m2doc.element.MList;
import org.obeonetwork.m2doc.element.MStyle;
import org.obeonetwork.m2doc.element.MTable;
import org.obeonetwork.m2doc.element.MTable.MCell;
import org.obeonetwork.m2doc.element.MTable.MRow;
import org.obeonetwork.m2doc.element.MText;
import org.obeonetwork.m2doc.sirius.util.DTable2MTableConverter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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

    /**
     * The {@link ComposedAdapterFactory}.
     */
    private final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
            ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

    /**
     * The {@link AdapterFactoryLabelProvider}.
     */
    private final AdapterFactoryLabelProvider adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(
            adapterFactory);

    /**
     * The {@link DTable2MTableConverter}.
     */
    private final DTable2MTableConverter tableConverter = new DTable2MTableConverter(adapterFactoryLabelProvider);

    @Test
    public void test() {
        assertEquals(3, table.getColumnsCount()); // Header column + 2 columns
        assertEquals(3, table.getRows().size()); // Header + 2 rows
        Iterator<? extends MRow> rowIt = table.getRows().iterator();

        // Header row
        MRow row = rowIt.next();
        MCell cell0 = row.getCells().get(0);
        assertNull(cell0.getContents());
        assertNull(cell0.getBackgroundColor());

        MCell cell1 = row.getCells().get(1);
        assertEquals("Col One", ((MText) cell1.getContents()).getText());
        assertEquals(DTable2MTableConverter.HEADER_BACKGROUND_COLOR, cell1.getBackgroundColor());
        assertStyleEqualsTo(DTable2MTableConverter.HEADER_STYLE, ((MText) cell1.getContents()).getStyle());

        MCell cell2 = row.getCells().get(2);
        assertEquals("Col Two", ((MText) cell2.getContents()).getText());
        assertEquals(DTable2MTableConverter.HEADER_BACKGROUND_COLOR, cell2.getBackgroundColor());
        assertStyleEqualsTo(DTable2MTableConverter.HEADER_STYLE, ((MText) cell2.getContents()).getStyle());

        // First row
        row = rowIt.next();
        assertEquals(3, row.getCells().size());
        MCell cell10 = row.getCells().get(0);
        assertTrue(((MList) cell10.getContents()).get(0) instanceof MImage);
        assertEquals("Row One", ((MText) ((MList) cell10.getContents()).get(1)).getText());
        assertStyleEqualsTo(DTable2MTableConverter.HEADER_STYLE,
                ((MText) ((MList) cell10.getContents()).get(1)).getStyle());

        MCell cell11 = row.getCells().get(1);
        MStyle style = ((MText) cell11.getContents()).getStyle();
        assertEquals("Cell One One", ((MText) cell11.getContents()).getText());
        assertEquals(6, style.getFontSize());
        assertEquals(new Color(GRAY), cell11.getBackgroundColor());
        assertEquals(new Color(BLACK), style.getForegroundColor());
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_BOLD);
        assertNotEquals(0, style.getFontModifiers() & MStyle.FONT_ITALIC);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_STRIKE_THROUGH);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_UNDERLINE);

        MCell cell12 = row.getCells().get(2);
        assertNull(cell12.getContents()); // No content
        assertNull(cell12.getBackgroundColor()); // No background color

        // Second row
        row = rowIt.next();
        assertEquals(3, row.getCells().size());
        MCell cell20 = row.getCells().get(0);
        assertTrue(((MList) cell20.getContents()).get(0) instanceof MImage);
        assertEquals("Row Two", ((MText) ((MList) cell20.getContents()).get(1)).getText());
        assertEquals(DTable2MTableConverter.HEADER_BACKGROUND_COLOR, cell20.getBackgroundColor());
        assertStyleEqualsTo(DTable2MTableConverter.HEADER_STYLE,
                ((MText) ((MList) cell20.getContents()).get(1)).getStyle());

        MCell cell21 = row.getCells().get(1);
        assertNull(cell21.getContents());
        assertEquals(new Color(WHITE), cell21.getBackgroundColor()); // no row style, but column style, so style == column style

        MCell cell22 = row.getCells().get(2);
        assertEquals("Cell Two Two", ((MText) cell22.getContents()).getText());
        style = ((MText) cell22.getContents()).getStyle();
        // CHECKSTYLE:OFF
        assertEquals(9, style.getFontSize());
        // CHECKSTYLE:ON
        assertEquals(new Color(WHITE), cell22.getBackgroundColor());
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
            assertEquals(left.getFontModifiers(), right.getFontModifiers());
            assertEquals(left.getFontSize(), right.getFontSize());
        }
    }

    @Before
    public void setUp() {
        dtable = TableFactory.eINSTANCE.createDTable();

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

        table = tableConverter.convertTable(dtable);
    }
}
