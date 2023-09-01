/*******************************************************************************
 *  Copyright (c) 2017, 2023 Obeo. 
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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.emf.common.util.URI;

/**
 * An image that can be returned by services.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public interface MImage extends MElement {

    /**
     * An empty {@link MImage}.
     */
    // CHECKSTYLE:OFF
    MImage EMPTY = new MImage() {

        /**
         * The empty {@link URI}.
         */
        private final URI uri = URI.createURI("");

        @Override
        public void setWidth(int width) {
            // nothing to do here
        }

        @Override
        public void setHeight(int height) {
            // nothing to do here
        }

        @Override
        public void setConserveRatio(boolean conserveRatio) {
            // nothing to do here
        }

        @Override
        public int getWidth() {
            return 0;
        }

        @Override
        public URI getURI() {
            return uri;
        }

        @Override
        public PictureType getType() {
            return PictureType.JPG;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(new byte[] {});
        }

        @Override
        public int getHeight() {
            return 0;
        }

        @Override
        public boolean conserveRatio() {
            return false;
        }

        @Override
        public double getRatio() {
            return 1;
        }

        @Override
        public int getRelativeHeight() {
            return -1;
        }

        @Override
        public int getRelativeWidth() {
            return -1;
        }

        @Override
        public void setRelativeHeight(int relativeHeight) {
            // nothing to do here
        }

        @Override
        public void setRelativeWidth(int relativeWidth) {
            // nothing to do here
        }
    };
    // CHECKSTYLE:ON

    /**
     * Gets the image width.
     * 
     * @return the image width
     */
    int getWidth();

    /**
     * Sets the image width.
     * 
     * @param width
     *            the image width
     */
    void setWidth(int width);

    /**
     * Gets the relative image width from its future container (0..100).
     * 
     * @return the relative image width from its future container if any, <code>-1</code> otherwise
     */
    int getRelativeWidth();

    /**
     * Sets the relative image width from its future container (0..100) or <code>-1</code> to unset.
     * 
     * @param relativeWidth
     *            the relative image width from its future container
     */
    void setRelativeWidth(int relativeWidth);

    /**
     * Gets the image height.
     * 
     * @return the image height
     */
    int getHeight();

    /**
     * Sets the image height.
     * 
     * @param height
     *            the image height
     */
    void setHeight(int height);

    /**
     * Gets the relative image height from its future container (0..100).
     * 
     * @return the relative image height from its future container if any, <code>-1</code> otherwise
     */
    int getRelativeHeight();

    /**
     * Sets the relative image height from its future container (0..100) or <code>-1</code> to unset.
     * 
     * @param relativeHeight
     *            the relative image height from its future container
     */
    void setRelativeHeight(int relativeHeight);

    /**
     * Tells if we conserve aspect ratio.
     * 
     * @return <code>true</code> if we conserve aspect ratio, <code>false</code> otherwise
     */
    boolean conserveRatio();

    /**
     * Sets the aspect ratio.
     * 
     * @param conserveRatio
     *            <code>true</code> to conserve the {@link #getRatio() aspect ration}, <code>false</code> otherwise
     */
    void setConserveRatio(boolean conserveRatio);

    /**
     * Gets the aspect ratio.
     * 
     * @return the aspect ratio
     */
    double getRatio();

    /**
     * Gets the {@link URI}.
     * 
     * @return the {@link URI}
     */
    URI getURI();

    /**
     * Gets the {@link InputStream}.
     * 
     * @return the {@link InputStream}
     * @throws IOException
     *             if the {@link InputStream} can't be created.
     */
    InputStream getInputStream() throws IOException;

    /**
     * Gets the {@link PictureType}.
     * 
     * @return the type the {@link PictureType}.
     */
    PictureType getType();

}
