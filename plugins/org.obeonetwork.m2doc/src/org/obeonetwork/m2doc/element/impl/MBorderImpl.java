/*******************************************************************************
 *  Copyright (c) 2023 Obeo. 
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

import org.obeonetwork.m2doc.element.MBorder;

/**
 * Border definition.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MBorderImpl implements MBorder {

    /**
     * The {@link Type}.
     */
    private Type type = Type.NONE;

    /**
     * The {@link Color}.
     */
    private Color color;

    /**
     * The size.
     */
    private int size = -1;

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        if (type != null) {
            this.type = type;
        } else {
            this.type = Type.NONE;
        }
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

}
