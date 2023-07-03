/*******************************************************************************
 *  Copyright (c) 2018, 2023 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.element;

/**
 * Interface that represents a paragraph that can be inserted in a word document.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public interface MParagraph extends MElementContainer, MElement {

    /**
     * The text direction.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    enum Dir {
        /** Left to right. */
        LTR,
        /** Right to left. */
        RTL;
    }

    /**
     * Gets the style name of the paragraph.
     * 
     * @return the style name of the paragraph if any, <code>null</code> otherwise
     */
    String getStyleName();

    /**
     * Sets the style name of the paragraph.
     * 
     * @param styleName
     *            the new style name of the paragraph, <code>null</code> for default
     */
    void setStyleName(String styleName);

    /**
     * Gets the numbering ID.
     * 
     * @return the numbering ID
     */
    Long getNumberingID();

    /**
     * Sets the numbering ID.
     * 
     * @param numberingID
     *            the numbering ID to set
     */
    void setNumberingID(Long numberingID);

    /**
     * Gets the numbering level.
     * 
     * @return the numbering level
     */
    Long getNumberingLevel();

    /**
     * Sets the numbering level.
     * 
     * @param numberingLevel
     *            the numbering level to set
     */
    void setNumberingLevel(Long numberingLevel);

    /**
     * Gets the ext direction.
     * 
     * @return the ext direction if nay, <code>null</code> if not specified
     */
    Dir getTextDirection();

    /**
     * Sets the paragraph text direction.
     * 
     * @param direction
     *            the text direction, <code>null</code> if not defined
     */
    void setTextDirection(Dir direction);

    /**
     * Gets the margin left in pixel.
     * 
     * @return the margin left in pixel
     */
    int getMarginLeft();

    /**
     * Sets the margin left in pixel.
     * 
     * @param value
     *            the margin left in pixel
     */
    void setMarginLeft(int value);

    /**
     * Gets the margin right in pixel.
     * 
     * @return the margin right in pixel
     */
    int getMarginRight();

    /**
     * Sets the margin right in pixel.
     * 
     * @param value
     *            the margin right in pixel
     */
    void setMarginRight(int value);

    /**
     * Gets the margin top in pixel.
     * 
     * @return the margin top in pixel
     */
    int getMarginTop();

    /**
     * Sets the margin top in pixel.
     * 
     * @param value
     *            the margin top in pixel
     */
    void setMarginTop(int value);

    /**
     * Gets the margin bottom in pixel.
     * 
     * @return the margin bottom in pixel
     */
    int getMarginBottom();

    /**
     * Sets the margin bottom in pixel.
     * 
     * @param value
     *            the margin bottom in pixel
     */
    void setMarginBottom(int value);

    /**
     * Gets the left {@link MBorder}.
     * 
     * @return the left {@link MBorder}
     */
    MBorder getLeftBorder();

    /**
     * Sets the left {@link MBorder}.
     * 
     * @param border
     *            the {@link MBorder}
     */
    void setLeftBorder(MBorder border);

    /**
     * Gets the right {@link MBorder}.
     * 
     * @return the right {@link MBorder}
     */
    MBorder getRightBorder();

    /**
     * Sets the right {@link MBorder}.
     * 
     * @param border
     *            the {@link MBorder}
     */
    void setRightBorder(MBorder border);

    /**
     * Gets the top {@link MBorder}.
     * 
     * @return the top {@link MBorder}
     */
    MBorder getTopBorder();

    /**
     * Sets the top {@link MBorder}.
     * 
     * @param border
     *            the {@link MBorder}
     */
    void setTopBorder(MBorder border);

    /**
     * Gets the bottom {@link MBorder}.
     * 
     * @return the bottom {@link MBorder}
     */
    MBorder getBottomBorder();

    /**
     * Sets the bottom {@link MBorder}.
     * 
     * @param border
     *            the {@link MBorder}
     */
    void setBottomBorder(MBorder border);

}
