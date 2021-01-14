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
package org.obeonetwork.m2doc.element;

/**
 * Styled text.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public interface MText extends MElement {

    /**
     * Gets the text.
     * 
     * @return the text
     */
    String getText();

    /**
     * Sets the text.
     * 
     * @param text
     *            the new text
     */
    void setText(String text);

    /**
     * Gets the {@link MStyle}.
     * 
     * @return the {@link MStyle}
     */
    MStyle getStyle();

    /**
     * Set the {@link MStyle}.
     * 
     * @param style
     *            the new {@link MStyle}
     */
    void setStyle(MStyle style);

}
