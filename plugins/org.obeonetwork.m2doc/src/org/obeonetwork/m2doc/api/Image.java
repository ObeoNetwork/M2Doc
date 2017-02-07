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
package org.obeonetwork.m2doc.api;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;

/**
 * An image that can be returned by a service.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class Image {

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
     * Tells if we keep the aspect ratio.
     */
    private boolean conserveRatio;

    /**
     * The aspect ratio.
     */
    private double ratio;

    /**
     * Constructor.
     * 
     * @param uri
     *            the {@link URI}
     */
    public Image(URI uri) {
        this.uri = uri;
        try {
            final BufferedImage image = ImageIO.read(getInputStream());
            if (image != null) {
                width = image.getWidth();
                height = image.getHeight();
                conserveRatio = true;
                ratio = ((double) width) / ((double) height);
            } else {
                conserveRatio = false;
                ratio = -1;
            }
        } catch (IOException e) {
            // will continue with out ratio and width x height preset
            ratio = -1;
        }
    }

    /**
     * Gets the image width.
     * 
     * @return the image width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the image width.
     * 
     * @param width
     *            the image width
     */
    public void setWidth(int width) {
        this.width = width;
        if (conserveRatio) {
            height = (int) (1 / ratio * width);
        }
    }

    /**
     * Gets the image height.
     * 
     * @return the image height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the image height.
     * 
     * @param height
     *            the image height
     */
    public void setHeight(int height) {
        this.height = height;
        if (conserveRatio) {
            width = (int) (ratio * height);
        }
    }

    /**
     * Tells if we conserve aspect ratio.
     * 
     * @return <code>true</code> if we conserve aspect ratio, <code>false</code> otherwise
     */
    public boolean conserveRatio() {
        return conserveRatio;
    }

    /**
     * Sets the aspect ratio.
     * 
     * @param conserveRatio
     *            <code>true</code> to conserve the {@link #getRatio() aspect ration}, <code>false</code> otherwise
     */
    public void setConserveRatio(boolean conserveRatio) {
        if (conserveRatio && ratio < 0) {
            throw new IllegalStateException("Can't conserve ratio: unknown ratio");
        }
        this.conserveRatio = conserveRatio;
    }

    /**
     * Gets the aspect ratio.
     * 
     * @return the aspect ratio
     */
    public double getRatio() {
        return ratio;
    }

    /**
     * Gets the {@link URI}.
     * 
     * @return the {@link URI}
     */
    public URI getURI() {
        return uri;
    }

    /**
     * Gets the {@link InputStream}.
     * 
     * @return the {@link InputStream}
     * @throws IOException
     *             if the {@link InputStream} can't be created.
     */
    public InputStream getInputStream() throws IOException {
        return URIConverter.INSTANCE.createInputStream(uri);
    }

    @Override
    public String toString() {
        return "Image " + uri.toString();
    }

}
