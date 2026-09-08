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

import org.obeonetwork.m2doc.element.MBookmarkTextRef;

/**
 * A bookmark text reference (insert the text of the referenced bookmark).
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MBookmarkTextRefImpl extends AbstractMBookmarkRef implements MBookmarkTextRef {

    /**
     * Constructor.
     * 
     * @param id
     *            the bookmark id
     * @param optional
     *            <code>true</code> if this reference can be omitted when the bookmark declaration doesn't exists, <code>false</code>
     *            otherwise
     */
    public MBookmarkTextRefImpl(String id, boolean optional) {
        super(id, optional);
    }

}
