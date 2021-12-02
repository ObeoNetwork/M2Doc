/*******************************************************************************
 *  Copyright (c) 2018 Obeo. 
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

}
