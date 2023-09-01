/*******************************************************************************
 *  Copyright (c) 2018, 2023 Obeo. 
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

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.eclipse.emf.common.util.URI;
import org.obeonetwork.m2doc.element.MImage;
import org.obeonetwork.m2doc.element.PictureType;

/**
 * An AWT implementation for image transformation.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MImageAWTImpl implements MImage {

    /**
     * The {@link BufferedImage}.
     */
    private final BufferedImage image;

    /**
     * The {@link URI} to retrieve the content of the image.
     */
    private final URI uri;

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
     * The aspect ratio.
     */
    private double ratio;

    /**
     * Constructor with enforced image type.
     * 
     * @param image
     *            the {@link BufferedImage}
     * @param uri
     *            the {@link URI}
     */
    public MImageAWTImpl(BufferedImage image, URI uri) {
        this.image = image;
        this.uri = uri;
        width = image.getWidth();
        height = image.getHeight();
        conserveRatio = true;
        ratio = ((double) width) / ((double) height);
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
    public int getRelativeWidth() {
        return relativeWidth;
    }

    @Override
    public void setRelativeWidth(int relativeWidth) {
        this.relativeWidth = relativeWidth;
    }

    @Override
    public int getRelativeHeight() {
        return relativeHeight;
    }

    @Override
    public void setRelativeHeight(int relativeHeight) {
        this.relativeHeight = relativeHeight;
    }

    @Override
    public boolean conserveRatio() {
        return conserveRatio;
    }

    @Override
    public void setConserveRatio(boolean conserveRatio) {
        if (conserveRatio && ratio < 0) {
            throw new IllegalStateException("Can't conserve ratio: unknown ratio");
        }
        this.conserveRatio = conserveRatio;
    }

    @Override
    public double getRatio() {
        return ratio;
    }

    @Override
    public URI getURI() {
        return uri;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream();) {
            ImageIO.write(image, "png", output);
            return new ByteArrayInputStream(output.toByteArray());
        }
    }

    @Override
    public PictureType getType() {
        return PictureType.PNG;
    }

    @Override
    public String toString() {
        return "Image " + uri.toString();
    }

    /**
     * Gets the {@link BufferedImage} from the given {@link MImage}.
     * 
     * @param image
     *            the {@link MImage}
     * @return the {@link BufferedImage} from the given {@link MImage}
     * @throws IOException
     *             if the image can't be read
     */
    public static BufferedImage getBufferedImage(MImage image) throws IOException {
        final BufferedImage res;

        if (image instanceof MImageAWTImpl) {
            res = ((MImageAWTImpl) image).image;
        } else {
            try (InputStream input = image.getInputStream()) {
                res = ImageIO.read(input);
            }
        }

        return res;
    }

}
