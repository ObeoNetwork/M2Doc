/*******************************************************************************
 *  Copyright (c) 2018 Obeo. 
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
 * A container for {@link MElement}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public interface MElementContainer {

    /**
     * Vertical alignement.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    enum HAlignment {

        /**
         * Both.
         */
        BOTH,

        /**
         * Center.
         */
        CENTER,

        /**
         * Distribute.
         */
        DISTRIBUTE,

        /**
         * High kashida.
         */
        HIGH_KASHIDA,

        /**
         * Left.
         */
        LEFT,

        /**
         * Low kashida.
         */
        LOW_KASHIDA,

        /**
         * Medium kashida.
         */
        MEDIUM_KASHIDA,

        /**
         * Num tab.
         */
        NUM_TAB,

        /**
         * Right.
         */
        RIGHT,

        /**
         * Thai distribute.
         */
        THAI_DISTRIBUTE

    }

    /**
     * Gets the cell contents.
     * 
     * @return the cell contents if any, <code>null</code> otherwise
     */
    MElement getContents();

    /**
     * Sets the cell contents.
     * 
     * @param contents
     *            the new cell contents
     */
    void setContents(MElement contents);

    /**
     * Gets the {@link HAlignment horizontal alignment}.
     * 
     * @return the {@link HAlignment horizontal alignment}
     */
    HAlignment getHAlignment();

    /**
     * Sets the {@link HAlignment horizontal alignment}.
     * 
     * @param alignment
     *            the new {@link HAlignment horizontal alignment}
     */
    void setHAlignment(HAlignment alignment);

}
