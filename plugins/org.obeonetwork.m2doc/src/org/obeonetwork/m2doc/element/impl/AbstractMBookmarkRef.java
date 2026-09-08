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
package org.obeonetwork.m2doc.element.impl;

import org.obeonetwork.m2doc.element.MBookmarkRef;

/**
 * Abstract implementation of {@link MBookmarkRef}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class AbstractMBookmarkRef implements MBookmarkRef {

    /**
     * The bookmark ID.
     */
    private String id;

    /**
     * Tells if this reference can be omitted when the bookmark declaration doesn't exists.
     */
    private boolean optional;

    /**
     * Constructor.
     * 
     * @param id
     *            the bookmark id
     * @param optional
     *            <code>true</code> if this reference can be omitted when the bookmark declaration doesn't exists, <code>false</code>
     *            otherwise
     */
    public AbstractMBookmarkRef(String id, boolean optional) {
        this.id = id;
        this.optional = optional;
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
    public boolean isOptional() {
        return optional;
    }

    @Override
    public void setOptional(boolean optional) {
        this.optional = optional;
    }
}
