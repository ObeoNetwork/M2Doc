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
