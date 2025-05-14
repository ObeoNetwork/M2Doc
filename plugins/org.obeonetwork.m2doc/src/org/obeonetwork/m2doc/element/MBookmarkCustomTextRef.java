/*******************************************************************************
 *  Copyright (c) 2025 Obeo. 
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
 * A bookmark with a custom text reference (insert the custom text and reference the bookmark).
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public interface MBookmarkCustomTextRef extends MBookmarkRef {

    /**
     * Gets the text to display.
     * 
     * @return the text to display
     */
    String getText();

    /**
     * Sets the text to display.
     * 
     * @param text
     *            the new text to display
     */
    void setText(String text);

}
