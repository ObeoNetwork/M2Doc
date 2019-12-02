package org.obeonetwork.m2doc.sirius.tests.tables;

import java.awt.Color;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.table.metamodel.table.TableFactory;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.junit.Before;
import org.junit.Test;
import org.obeonetwork.m2doc.element.MStyle;
import org.obeonetwork.m2doc.sirius.util.DTable2MTableConverter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DMStyleTest {

    private DTableElementStyle dstyle;

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
    public void testStyle() {
        MStyle style = tableConverter.convertStyle(dstyle);
        assertEquals(6, style.getFontSize());
        // CHECKSTYLE:OFF
        assertEquals(new Color(0 << 16 | 128 << 8 | 255), tableConverter.convertColor(dstyle.getBackgroundColor()));
        assertEquals(new Color(255 << 16 | 128 << 8 | 0), style.getForegroundColor());
        // CHECKSTYLE:ON
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_BOLD);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_ITALIC);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_STRIKE_THROUGH);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_UNDERLINE);
    }

    @Test
    public void testStyleFontModifiers() {
        dstyle.getLabelFormat().add(FontFormat.BOLD_LITERAL);
        MStyle style = tableConverter.convertStyle(dstyle);
        assertNotEquals(0, style.getFontModifiers() & MStyle.FONT_BOLD);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_ITALIC);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_STRIKE_THROUGH);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_UNDERLINE);

        dstyle.getLabelFormat().clear();
        dstyle.getLabelFormat().add(FontFormat.ITALIC_LITERAL);
        style = tableConverter.convertStyle(dstyle);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_BOLD);
        assertNotEquals(0, style.getFontModifiers() & MStyle.FONT_ITALIC);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_STRIKE_THROUGH);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_UNDERLINE);

        dstyle.getLabelFormat().clear();
        dstyle.getLabelFormat().add(FontFormat.STRIKE_THROUGH_LITERAL);
        style = tableConverter.convertStyle(dstyle);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_BOLD);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_ITALIC);
        assertNotEquals(0, style.getFontModifiers() & MStyle.FONT_STRIKE_THROUGH);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_UNDERLINE);

        dstyle.getLabelFormat().clear();
        dstyle.getLabelFormat().add(FontFormat.UNDERLINE_LITERAL);
        style = tableConverter.convertStyle(dstyle);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_BOLD);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_ITALIC);
        assertEquals(0, style.getFontModifiers() & MStyle.FONT_STRIKE_THROUGH);
        assertNotEquals(0, style.getFontModifiers() & MStyle.FONT_UNDERLINE);

        dstyle.getLabelFormat().clear();
        dstyle.getLabelFormat().add(FontFormat.BOLD_LITERAL);
        dstyle.getLabelFormat().add(FontFormat.ITALIC_LITERAL);
        dstyle.getLabelFormat().add(FontFormat.STRIKE_THROUGH_LITERAL);
        dstyle.getLabelFormat().add(FontFormat.UNDERLINE_LITERAL);
        style = tableConverter.convertStyle(dstyle);
        assertNotEquals(0, style.getFontModifiers() & MStyle.FONT_BOLD);
        assertNotEquals(0, style.getFontModifiers() & MStyle.FONT_ITALIC);
        assertNotEquals(0, style.getFontModifiers() & MStyle.FONT_STRIKE_THROUGH);
        assertNotEquals(0, style.getFontModifiers() & MStyle.FONT_UNDERLINE);
    }

    @Before
    public void setUp() {
        dstyle = TableFactory.eINSTANCE.createDTableElementStyle();
        dstyle.setLabelSize(6);
        // CHECKSTYLE:OFF
        dstyle.setBackgroundColor(RGBValues.create(0, 128, 255));
        dstyle.setForegroundColor(RGBValues.create(255, 128, 0));
        // CHECKSTYLE:ON
    }
}
