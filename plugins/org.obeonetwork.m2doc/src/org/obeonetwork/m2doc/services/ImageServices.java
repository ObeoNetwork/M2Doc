/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.services;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

import org.eclipse.emf.common.util.URI;
import org.obeonetwork.m2doc.api.Image;

//@formatter:off
//@ServiceProvider(
//  value = "Services available for Images"
//)
//@formatter:on
// TODO activate documentation when changing AQL dependency
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype" })
public class ImageServices {

    /**
     * The template URI.
     */
    private final URI templateURI;

    /**
     * Cosntructor.
     * 
     * @param templateURI
     *            the template {@link URI}
     */
    public ImageServices(URI templateURI) {
        this.templateURI = templateURI;
    }

    // @formatter:off
//    @Documentation(
//        value = "Conert a String to an Image.",
//        params = {
//            @Param(name = "path", value = "The Image path"),
//        },
//        result = "insert the image",
//        examples = {
//            @Example(expression = "'image.png'.asImage()", result = "insert the image 'image.png'"),
//        }
//    )
    // @formatter:on
    // TODO activate documentation when changing AQL dependency
    /**
     * Gets the {@link Image} corresponding to the given path.
     * 
     * @param path
     *            the path
     * @return the {@link Image} corresponding to the given path
     */
    public Image asImage(String path) {
        final Image res;

        final URI imageURI = URI.createURI(path);

        URI uri;
        if (!imageURI.hasAbsolutePath()) {
            /*
             * it is expected that we have an EResource and URI for the current template to resolve relative URIs from it.
             */
            uri = templateURI.trimSegments(1);
            for (String s : Splitter.on(CharMatcher.anyOf("/\\")).split(path)) {
                uri = uri.appendSegment(s);
            }
        } else {
            uri = imageURI;
        }
        res = new Image(uri);

        return res;
    }

    /**
     * Sets the given width to the image.
     * 
     * @param image
     *            the {@link Image}
     * @param width
     *            the width
     * @return the given {@link Image} with the new width
     */
    // TODO activate documentation when changing AQL dependency
    public Image setWidth(Image image, Integer width) {
        image.setWidth(width);

        return image;
    }

    /**
     * Sets the given height to the image.
     * 
     * @param image
     *            the {@link Image}
     * @param height
     *            the height
     * @return the given {@link Image} with the new height
     */
    // TODO activate documentation when changing AQL dependency
    public Image setHeight(Image image, Integer height) {
        image.setHeight(height);

        return image;
    }

    /**
     * Sets the conserve aspect ratio to the given {@link Image}.
     * 
     * @param image
     *            the {@link Image}
     * @param conserve
     *            <code>true</code> to conserve the aspect ratio, <code>false</code> otherwise
     * @return the given {@link Image} with the new conserve aspect ratio
     */
    // TODO activate documentation when changing AQL dependency
    public Image setConserveRatio(Image image, Boolean conserve) {
        image.setConserveRatio(conserve);

        return image;
    }

}
