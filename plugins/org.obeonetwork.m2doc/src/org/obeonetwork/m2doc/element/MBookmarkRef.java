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
 * A bookmark reference.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public interface MBookmarkRef extends MIdentifiable {

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
