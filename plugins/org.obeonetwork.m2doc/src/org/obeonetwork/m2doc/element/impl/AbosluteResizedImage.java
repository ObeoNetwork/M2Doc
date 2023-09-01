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

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.emf.common.util.URI;
import org.obeonetwork.m2doc.element.MImage;
import org.obeonetwork.m2doc.element.PictureType;

/**
 * Wrap an {@link MImage} to allow changing its size to absolute without changing the original {@link MImage}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class AbosluteResizedImage implements MImage {

    /**
     * The image width.
     */
    private int width;

    /**
     * The image height.
     */
    private int height;

    /**
     * The relative width.
     */
    private int relativeWidth = -1;

    /**
     * The relative height.
     */
    private int relativeHeight = -1;

    /**
     * Tells if we keep the aspect ratio.
     */
    private boolean conserveRatio;

    /**
     * The original {@link MImage}.
     */
    private final MImage original;

    /**
     * Constructor.
     * 
     * @param original
     *            the original {@link MImage}
     */
    public AbosluteResizedImage(MImage original) {
        this.original = original;
        this.width = original.getWidth();
        this.height = original.getHeight();
        this.conserveRatio = original.conserveRatio();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
        if (conserveRatio) {
            height = (int) (1 / getRatio() * width);
        }
    }

    @Override
    public int getRelativeWidth() {
        return relativeWidth;
    }

    @Override
    public void setRelativeWidth(int relativeWidth) {
        throw new UnsupportedOperationException("Absolute size only.");
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
        if (conserveRatio) {
            width = (int) (getRatio() * height);
        }
    }

    @Override
    public int getRelativeHeight() {
        return relativeHeight;
    }

    @Override
    public void setRelativeHeight(int relativeHeight) {
        throw new UnsupportedOperationException("Absolute size only.");
    }

    @Override
    public boolean conserveRatio() {
        return conserveRatio;
    }

    @Override
    public void setConserveRatio(boolean conserveRatio) {
        this.conserveRatio = conserveRatio;
    }

    @Override
    public double getRatio() {
        return original.getRatio();
    }

    @Override
    public URI getURI() {
        return original.getURI();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return original.getInputStream();
    }

    @Override
    public PictureType getType() {
        return original.getType();
    }

}
