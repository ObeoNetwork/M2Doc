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
package org.obeonetwork.m2doc.element;

/**
 * An bookmark that can be returned by services.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public interface MBookmark extends MElemement {

    /**
     * Gets the text to display.
     * 
     * @return the text to display
     */
    String getText();

    /**
     * Gets the bookmark ID.
     * 
     * @return the bookmark ID
     */
    String getId();

    /**
     * Tells if this is a reference to a {@link MBookmark} or a {@link MBookmark} declaration.
     * 
     * @return <code>true</code> if this is a reference to a {@link MBookmark}, <code>false</code> if it's a {@link MBookmark}
     *         declaration
     */
    boolean isReference();

}
