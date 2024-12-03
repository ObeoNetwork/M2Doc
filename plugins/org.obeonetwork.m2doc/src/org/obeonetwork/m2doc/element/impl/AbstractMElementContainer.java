/*******************************************************************************
 *  Copyright (c) 2018, 2024 Obeo. 
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

import java.awt.Color;

import org.obeonetwork.m2doc.element.MElement;
import org.obeonetwork.m2doc.element.MElementContainer;

/**
 * Abstract implementation of {@link MElementContainer}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public abstract class AbstractMElementContainer implements MElementContainer {

    /**
     * The contents.
     */
    private MElement contents;

    /**
     * The horizontal alignemnt.
     */
    private HAlignment hAlign;

    /**
     * The background {@link Color}.
     */
    private Color backgroundColor;

    /**
     * Constructor.
     * 
     * @param contents
     *            the contained {@link MElement}
     */
    public AbstractMElementContainer(MElement contents) {
        this.contents = contents;
    }

    @Override
    public MElement getContents() {
        return contents;
    }

    @Override
    public void setContents(MElement contents) {
        this.contents = contents;
    }

    @Override
    public HAlignment getHAlignment() {
        return hAlign;
    }

    @Override
    public void setHAlignment(HAlignment alignement) {
        hAlign = alignement;
    }

    @Override
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public void setBackgroundColor(Color color) {
        backgroundColor = color;
    }

}
