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
package org.obeonetwork.m2doc.api;

/**
 * An bookmark that can be returned by services.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class Bookmark {

    /**
     * The text to display.
     */
    private String text;

    /**
     * The bookmark ID.
     */
    private String id;

    /**
     * <code>true</code> if this is a reference to a {@link Bookmark}, <code>false</code> if it's a {@link Bookmark} declaration.
     */
    private final boolean reference;

    /**
     * Constructor.
     * 
     * @param text
     *            the text to display
     * @param id
     *            the id
     * @param reference
     *            <code>true</code> if this is a reference to a {@link Bookmark}, <code>false</code> if it's a {@link Bookmark} declaration
     */
    public Bookmark(String text, String id, boolean reference) {
        this.text = text;
        this.id = id;
        this.reference = reference;
    }

    /**
     * Gets the text to display.
     * 
     * @return the text to display
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the bookmark ID.
     * 
     * @return the bookmark ID
     */
    public String getId() {
        return id;
    }

    /**
     * Tells if this is a reference to a {@link Bookmark} or a {@link Bookmark} declaration.
     * 
     * @return <code>true</code> if this is a reference to a {@link Bookmark}, <code>false</code> if it's a {@link Bookmark} declaration
     */
    public boolean isReference() {
        return reference;
    }

}
