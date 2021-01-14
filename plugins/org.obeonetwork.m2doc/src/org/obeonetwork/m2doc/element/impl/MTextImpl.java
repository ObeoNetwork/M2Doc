/*******************************************************************************
 *  Copyright (c) 2017 Obeo. 
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

import org.obeonetwork.m2doc.element.MStyle;
import org.obeonetwork.m2doc.element.MText;

/**
 * Styled text.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MTextImpl implements MText {

    /**
     * The text.
     */
    private String text;

    /**
     * The {@link MStyle}.
     */
    private MStyle style;

    /**
     * Constructor.
     * 
     * @param text
     *            the text
     * @param style
     *            the {@link MStyle}
     */
    public MTextImpl(String text, MStyle style) {
        this.text = text;
        this.style = style;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public MStyle getStyle() {
        return style;
    }

    @Override
    public void setStyle(MStyle style) {
        this.style = style;
    }

}
