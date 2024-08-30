/*******************************************************************************
 *  Copyright (c) 2017, 2024 Obeo. 
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
 * An bookmark that can be returned by services.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public interface MBookmark extends MElement {

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

    /**
     * Gets the bookmark ID.
     * 
     * @return the bookmark ID
     */
    String getId();

    /**
     * Sets the bookmark ID.
     * 
     * @param id
     *            the new bookmark ID
     */
    void setId(String id);

    /**
     * Tells if this is a reference to a {@link MBookmark} or a {@link MBookmark} declaration.
     * 
     * @return <code>true</code> if this is a reference to a {@link MBookmark}, <code>false</code> if it's a {@link MBookmark}
     *         declaration
     */
    boolean isReference();

    /**
     * Tells if this reference can be omitted when the bookmark declaration doesn't exists.
     * 
     * @return <code>true</code> if this reference can be omitted when the bookmark declaration doesn't exists, <code>false</code> if an
     *         error
     *         should be raised.
     */
    boolean isOptional();

    /**
     * Sets if this reference can be omitted when the bookmark declaration doesn't exists.
     * 
     * @param optional
     *            <code>true</code> if this reference can be omitted when the bookmark declaration doesn't exists, <code>false</code> if an
     *            error
     */
    void setOptional(boolean optional);

}
