package test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.obeonetwork.m2doc.element.MElement;
import org.obeonetwork.m2doc.element.MElementContainer.HAlignment;
import org.obeonetwork.m2doc.element.MList;
import org.obeonetwork.m2doc.element.MPagination;
import org.obeonetwork.m2doc.element.MParagraph;
import org.obeonetwork.m2doc.element.MStyle;
import org.obeonetwork.m2doc.element.MTable;
import org.obeonetwork.m2doc.element.MTable.MCell;
import org.obeonetwork.m2doc.element.MTable.MCell.VAlignment;
import org.obeonetwork.m2doc.element.MTable.MRow;
import org.obeonetwork.m2doc.element.MText;
import org.obeonetwork.m2doc.element.impl.MListImpl;
import org.obeonetwork.m2doc.element.impl.MParagraphImpl;
import org.obeonetwork.m2doc.element.impl.MStyleImpl;
import org.obeonetwork.m2doc.element.impl.MTableImpl;
import org.obeonetwork.m2doc.element.impl.MTableImpl.MCellImpl;
import org.obeonetwork.m2doc.element.impl.MTableImpl.MRowImpl;
import org.obeonetwork.m2doc.element.impl.MTextImpl;

public class UMLServices {

	public String getServiceString(Object obj) {
		return "some service string.";
	}

    /**
     * Gets a sample {@link MTable} with the given label.
     * 
     * @param label
     *            the {@link MTable#getLabel() label}
     * @return the sample {@link MTable} with the given label
     */
    public MTable sampleTable(String label) {
        final MTable table = new MTableImpl();
        table.setLabel(label);

        final int[] colors = new int[] {0, 127, 255 };
        int index = 0;
        final MRow headerRow = new MRowImpl();
        table.getRows().add(headerRow);
        // Empty cell at first
        headerRow.getCells().add(new MCellImpl(null, null));
        for (int xStyle = 0; xStyle < 4; xStyle++) {
            final MText headerText = new MTextImpl(getLabelStyle(xStyle), getStyle(1 << xStyle, 10, null, null));
            final MCell headerCell = new MCellImpl(headerText, null);
            headerRow.getCells().add(headerCell);
        }
        for (int yStyle = 0; yStyle < 4; yStyle++) {
            final MRow row = new MRowImpl();
            table.getRows().add(row);
            final MText headerText = new MTextImpl(getLabelStyle(yStyle), getStyle(1 << yStyle, 10, null, null));
            final MCell headerCell = new MCellImpl(headerText, null);
            row.getCells().add(headerCell);
            for (int xStyle = 0; xStyle < 4; xStyle++) {
                final String text = Integer.toString(index++);
                final Color foregroundColor = new Color(colors[(index + 1) % 3], colors[(index + 2) % 3],
                        colors[(index + 3) % 3]);
                final Color backgroundColor = new Color(colors[(index + 2) % 3], colors[(index + 3) % 3],
                        colors[(index + 1) % 3]);
                final MText mText = new MTextImpl(text,
                        getStyle(1 << xStyle | 1 << yStyle, index + 4, foregroundColor, null));
                final MCell cell = new MCellImpl(mText, backgroundColor);
                row.getCells().add(cell);
            }
        }

        return table;
    }


    /**
     * Gets the {@link MStyle}.
     * 
     * @param modifiers
     *            the modifiers
     * @param fontSize
     *            the font size
     *            ]
     * @param foregroundColor
     *            the foreground {@link Color}
     * @param backgroundColor
     *            the background {@link Color}
     * @return the {@link MStyle} for the given modifiers and {@link Color}
     */
    private MStyle getStyle(int modifiers, int fontSize, Color foregroundColor, Color backgroundColor) {
        return new MStyleImpl(null, fontSize, foregroundColor, backgroundColor, modifiers);
    }

    /**
     * Gets the style name from its given index.
     * 
     * @param index
     *            the style index
     * @return the style name from its given index
     */
    private String getLabelStyle(int index) {
        final String res;
        switch (index) {
            case 0:
                res = "Bold";
                break;
            case 1:
                res = "Italic";
                break;
            case 2:
                res = "Underline";
                break;
            case 3:
                res = "Strike through";
                break;
            default:
                res = "";
                break;
        }

        return res;
    }

    public List<MElement> alignmentTables(String label) {
        final List<MElement> res = new ArrayList<>();

        for (HAlignment hAlign : MCell.HAlignment.values()) {
            final MTable table = new MTableImpl();
            res.add(table);
            res.add(MPagination.newParagraph);
            final MRow titleRow = new MRowImpl();
            table.getRows().add(titleRow);
            titleRow.getCells().add(new MCellImpl(null, null));
            titleRow.getCells().add(new MCellImpl(new MTextImpl(hAlign.name(), null), null));
            titleRow.getCells().add(new MCellImpl(null, null));
            for (VAlignment vAlign : MCell.VAlignment.values()) {
                final MRow row = new MRowImpl();
                table.getRows().add(row);
                row.getCells().add(new MCellImpl(new MTextImpl(vAlign.name(), null), null));
                final MCellImpl cell = new MCellImpl(new MTextImpl(
                        "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...",
                        null), null);
                cell.setVAlignment(vAlign);
                cell.setHAlignment(hAlign);
                row.getCells().add(cell);
                row.getCells().add(new MCellImpl(new MTextImpl("\n\n\n\n\n\n", null), null));
            }
        }

        return res;
    }
    
    /**
     * Generates a {@link MList} of {@link MParagraph} with all {@link HAlignment}.
     * 
     * @param text
     *            the text to set for each {@link MParagraph}
     * @return a {@link MList} of {@link MParagraph} with all {@link HAlignment}
     */
    public MList getAlignments(String text) {
        final MList result = new MListImpl();

        for (HAlignment alignment : HAlignment.values()) {
            final MParagraph paragraph = new MParagraphImpl(new MTextImpl(text, null), null);
            paragraph.setHAlignment(alignment);
            result.add(paragraph);
        }

        return result;
    }

}
