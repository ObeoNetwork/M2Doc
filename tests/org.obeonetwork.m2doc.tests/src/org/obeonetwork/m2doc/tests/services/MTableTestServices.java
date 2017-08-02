/*******************************************************************************
 *  Copyright (c) 2017 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.tests.services;

import java.awt.Color;

import org.obeonetwork.m2doc.element.MStyle;
import org.obeonetwork.m2doc.element.MTable;
import org.obeonetwork.m2doc.element.MTable.MCell;
import org.obeonetwork.m2doc.element.MTable.MRow;
import org.obeonetwork.m2doc.element.impl.MStyleImpl;
import org.obeonetwork.m2doc.element.impl.MTableImpl;
import org.obeonetwork.m2doc.element.impl.MTableImpl.MCellImpl;
import org.obeonetwork.m2doc.element.impl.MTableImpl.MRowImpl;

/**
 * Test {@link MTable} insertion.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MTableTestServices {

    /**
     * Gets an empty {@link MTable} with the given label.
     * 
     * @param label
     *            the {@link MTable#getLabel() label}
     * @return the empty {@link MTable} with the given label
     */
    public MTable emptyTable(String label) {
        final MTable table = new MTableImpl();
        table.setLabel(label);

        return table;
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
        headerRow.getCells().add(new MCellImpl());
        for (int xStyle = 0; xStyle < 4; xStyle++) {
            final MCell headerCell = new MCellImpl();
            headerRow.getCells().add(headerCell);
            headerCell.setLabel(getLabelStyle(xStyle));
            headerCell.setStyle(getStyle(1 << xStyle, 10, null, null));
        }
        for (int yStyle = 0; yStyle < 4; yStyle++) {
            final MRow row = new MRowImpl();
            table.getRows().add(row);
            final MCell headerCell = new MCellImpl();
            row.getCells().add(headerCell);
            headerCell.setLabel(getLabelStyle(yStyle));
            headerCell.setStyle(getStyle(1 << yStyle, 10, null, null));
            for (int xStyle = 0; xStyle < 4; xStyle++) {
                final MCell cell = new MCellImpl();
                row.getCells().add(cell);
                cell.setLabel(Integer.toString(index++));
                final Color foregroundColor = new Color(colors[(index + 1) % 3], colors[(index + 2) % 3],
                        colors[(index + 3) % 3]);
                final Color backgroundColor = new Color(colors[(index + 2) % 3], colors[(index + 3) % 3],
                        colors[(index + 1) % 3]);
                cell.setStyle(getStyle(1 << xStyle | 1 << yStyle, index + 4, foregroundColor, backgroundColor));
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
     * @param backgroundColor
     *            the foreground {@link Color}
     * @param foregroundColor
     *            the background {@link Color}
     * @return the {@link MStyle} for the given modifiers and {@link Color}
     */
    private MStyle getStyle(int modifiers, int fontSize, Color foregroundColor, Color backgroundColor) {
        final MStyle res = new MStyleImpl();

        res.setModifiers(modifiers);
        res.setFontSize(fontSize);
        res.setForegroundColor(foregroundColor);
        res.setBackgroundColor(backgroundColor);

        return res;
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

}
