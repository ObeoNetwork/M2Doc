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
package org.obeonetwork.m2doc.element.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.obeonetwork.m2doc.element.MImage;
import org.obeonetwork.m2doc.util.PictureType;

/**
 * An image that can be returned by services.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MImageImpl implements MImage {

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
     * The type.
     */
    private PictureType type;

    /**
     * The URI converter to use.
     */
    private URIConverter uriConverter;

    /**
     * Default constructor.
     * <p>
     * The image type is deducted from the image {@link URI} file extension. If the extension is unknown, jpeg format will be selected by
     * default.
     * </p>
     * 
     * @param uri
     *            the {@link URI}
     */
    public MImageImpl(URI uri) {
        this(URIConverter.INSTANCE, uri, PictureType.toType(uri));
    }

    /**
     * Constructor.
     * <p>
     * The image type is deducted from the image {@link URI} file extension. If the extension is unknown, jpeg format will be selected by
     * default.
     * </p>
     * 
     * @param uriConverter
     *            the {@link URIConverter uri converter} to use.
     * @param uri
     *            the {@link URI}
     */
    public MImageImpl(URIConverter uriConverter, URI uri) {
        this(uriConverter, uri, PictureType.toType(uri));
    }

    /**
     * Constructor with enforced image type.
     * 
     * @param uriConverter
     *            the {@link URIConverter uri converter} to use.
     * @param uri
     *            the {@link URI}
     * @param type
     *            the picture {@link PictureType type}.
     */
    public MImageImpl(URIConverter uriConverter, URI uri, PictureType type) {
        this.uriConverter = uriConverter;
        this.uri = uri;
        this.type = type;
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

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
        if (conserveRatio) {
            height = (int) (1 / ratio * width);
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
            width = (int) (ratio * height);
        }
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

    /**
     * Gets the aspect ratio.
     * 
     * @return the aspect ratio
     */
    protected double getRatio() {
        return ratio;
    }

    @Override
    public URI getURI() {
        return uri;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return uriConverter.createInputStream(uri);
    }

    @Override
    public PictureType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Image " + uri.toString();
    }

}
