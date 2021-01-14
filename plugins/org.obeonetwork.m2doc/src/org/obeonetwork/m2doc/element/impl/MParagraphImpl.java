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
package org.obeonetwork.m2doc.element.impl;

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
    public Long getNumberingID() {
        return numberingID;
    }

    /**
     * Sets the numbering ID.
     * 
     * @param numberingID
     *            the numbering ID to set
     */
    public void setNumberingID(Long numberingID) {
        this.numberingID = numberingID;
    }

    /**
     * Gets the numbering level.
     * 
     * @return the numbering level
     */
    public Long getNumberingLevel() {
        return numberingLevel;
    }

    /**
     * Sets the numbering level.
     * 
     * @param numberingLevel
     *            the numbering level to set
     */
    public void setNumberingLevel(Long numberingLevel) {
        this.numberingLevel = numberingLevel;
    }

}
