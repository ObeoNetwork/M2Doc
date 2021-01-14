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

import org.obeonetwork.m2doc.element.MBookmark;

/**
 * An bookmark that can be returned by services.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MBookmarkImpl implements MBookmark {

    /**
     * The text to display.
     */
    private String text;

    /**
     * The bookmark ID.
     */
    private String id;

    /**
     * <code>true</code> if this is a reference to a {@link MBookmarkImpl}, <code>false</code> if it's a {@link MBookmarkImpl} declaration.
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
     *            <code>true</code> if this is a reference to a {@link MBookmarkImpl}, <code>false</code> if it's a {@link MBookmarkImpl}
     *            declaration
     */
    public MBookmarkImpl(String text, String id, boolean reference) {
        this.text = text;
        this.id = id;
        this.reference = reference;
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
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean isReference() {
        return reference;
    }

}
