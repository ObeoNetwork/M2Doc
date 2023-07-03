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
package org.obeonetwork.m2doc.element.impl;

import org.obeonetwork.m2doc.element.MBorder;
import org.obeonetwork.m2doc.element.MElement;
import org.obeonetwork.m2doc.element.MParagraph;

/**
 * A paragraph that can be inserted in a word document.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MParagraphImpl extends AbstractMElementContainer implements MParagraph {

    /**
     * The style name.
     */
    private String styleName;

    /**
     * The numbering ID.
     */
    private Long numberingID;

    /**
     * The numbering level.
     */
    private Long numberingLevel;

    /**
     * The text direction, <code>null</code> if not defined.
     */
    private Dir textDirection;

    /**
     * The left margin in pixels.
     */
    private int marginLeft = -1;

    /**
     * The right margin in pixels.
     */
    private int marginRight = -1;

    /**
     * The top margin in pixels.
     */
    private int marginTop = -1;

    /**
     * The bottom margin in pixels.
     */
    private int marginBottom = -1;

    /**
     * The left {@link MBorder}.
     */
    private MBorder leftBorder;

    /**
     * The right {@link MBorder}.
     */
    private MBorder rightBorder;

    /**
     * The top {@link MBorder}.
     */
    private MBorder topBorder;

    /**
     * The bottom {@link MBorder}.
     */
    private MBorder bottomBorder;

    /**
     * Constructor.
     * 
     * @param contents
     *            the contents
     * @param styleName
     *            the style name
     */
    public MParagraphImpl(MElement contents, String styleName) {
        super(contents);
        this.styleName = styleName;
    }

    @Override
    public String getStyleName() {
        return styleName;
    }

    @Override
    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    /**
     * Gets the numbering ID.
     * 
     * @return the numbering ID
     */
    @Override
    public Long getNumberingID() {
        return numberingID;
    }

    /**
     * Sets the numbering ID.
     * 
     * @param numberingID
     *            the numbering ID to set
     */
    @Override
    public void setNumberingID(Long numberingID) {
        this.numberingID = numberingID;
    }

    /**
     * Gets the numbering level.
     * 
     * @return the numbering level
     */
    @Override
    public Long getNumberingLevel() {
        return numberingLevel;
    }

    /**
     * Sets the numbering level.
     * 
     * @param numberingLevel
     *            the numbering level to set
     */
    @Override
    public void setNumberingLevel(Long numberingLevel) {
        this.numberingLevel = numberingLevel;
    }

    @Override
    public Dir getTextDirection() {
        return textDirection;
    }

    @Override
    public void setTextDirection(Dir direction) {
        this.textDirection = direction;
    }

    @Override
    public int getMarginLeft() {
        return marginLeft;
    }

    @Override
    public void setMarginLeft(int value) {
        marginLeft = value;
    }

    @Override
    public int getMarginRight() {
        return marginRight;
    }

    @Override
    public void setMarginRight(int value) {
        marginRight = value;
    }

    @Override
    public int getMarginTop() {
        return marginTop;
    }

    @Override
    public void setMarginTop(int value) {
        marginTop = value;
    }

    @Override
    public int getMarginBottom() {
        return marginBottom;
    }

    @Override
    public void setMarginBottom(int value) {
        marginBottom = value;
    }

    @Override
    public MBorder getLeftBorder() {
        return leftBorder;
    }

    @Override
    public void setLeftBorder(MBorder border) {
        this.leftBorder = border;
    }

    @Override
    public MBorder getRightBorder() {
        return rightBorder;
    }

    @Override
    public void setRightBorder(MBorder border) {
        this.rightBorder = border;
    }

    @Override
    public MBorder getTopBorder() {
        return topBorder;
    }

    @Override
    public void setTopBorder(MBorder border) {
        this.topBorder = border;
    }

    @Override
    public MBorder getBottomBorder() {
        return bottomBorder;
    }

    @Override
    public void setBottomBorder(MBorder border) {
        this.bottomBorder = border;
    }

}
