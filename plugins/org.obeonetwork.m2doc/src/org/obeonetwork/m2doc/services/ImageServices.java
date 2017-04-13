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

import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.eclipse.emf.common.util.URI;
import org.obeonetwork.m2doc.api.Image;
import org.obeonetwork.m2doc.util.PictureType;

//@formatter:off
@ServiceProvider(
  value = "Services available for Images"
)
//@formatter:on
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype" })
public class ImageServices {

    /**
     * The template URI.
     */
    private final URI templateURI;

    /**
     * Constructor.
     * 
     * @param templateURI
     *            the template {@link URI}
     */
    public ImageServices(URI templateURI) {
        this.templateURI = templateURI;
    }

    // @formatter:off
    @Documentation(
        value = "Convert a String to an Image.",
        params = {
            @Param(name = "uri", value = "The Image uri, it can be relative to the template"),
        },
        result = "insert the image",
        examples = {
            @Example(expression = "'image.png'.asImage()", result = "insert the image 'image.png'"),
        }
    )
    // @formatter:on

    /**
     * Gets the {@link Image} corresponding to the given path.
     * <p>
     * Picture type is deducted from the file extension.
     * </p>
     * 
     * @param uriStr
     *            the uri.
     * @return the {@link Image} corresponding to the given path
     */
    public Image asImage(String uriStr) {
        final URI imageURI = URI.createURI(uriStr);
        return asImage(uriStr, PictureType.toType(imageURI));
    }

    /**
     * Gets the {@link Image} corresponding to the given path.
     * 
     * @param uriStr
     *            the uri.
     * @param type
     *            the picture {@link PictureType type}.
     * @return the {@link Image} corresponding to the given path
     */
    public Image asImage(String uriStr, String type) {
        return asImage(uriStr, PictureType.valueOf(type.toUpperCase()));
    }

    /**
     * Gets the {@link Image} corresponding to the given path.
     * 
     * @param uriStr
     *            the uri.
     * @param type
     *            the picture {@link PictureType type}.
     * @return the {@link Image} corresponding to the given path
     */
    private Image asImage(String uriStr, PictureType type) {
        final URI imageURI = URI.createURI(uriStr);

        URI uri = imageURI.resolve(templateURI);

        return new Image(uri, type);
    }

    // @formatter:off
    @Documentation(
        value = "Sets the width of the image.",
        params = {
            @Param(name = "image", value = "The Image"),
            @Param(name = "width", value = "The width"),
        },
        result = "sets the width of the image",
        examples = {
            @Example(expression = "myImage.setWidth(300)", result = "set the witdh to 300"),
        }
    )
    // @formatter:on
    /**
     * Sets the given width to the image.
     * 
     * @param image
     *            the {@link Image}
     * @param width
     *            the width
     * @return the given {@link Image} with the new width
     */
    public Image setWidth(Image image, Integer width) {
        image.setWidth(width);

        return image;
    }

    // @formatter:off
    @Documentation(
        value = "Sets the height of the image.",
        params = {
            @Param(name = "image", value = "The Image"),
            @Param(name = "height", value = "The height"),
        },
        result = "sets the height of the image",
        examples = {
            @Example(expression = "myImage.setHeight(300)", result = "set the height to 300"),
        }
    )
    // @formatter:on
    /**
     * Sets the given height to the image.
     * 
     * @param image
     *            the {@link Image}
     * @param height
     *            the height
     * @return the given {@link Image} with the new height
     */
    public Image setHeight(Image image, Integer height) {
        image.setHeight(height);

        return image;
    }

    // @formatter:off
    @Documentation(
        value = "Sets the conserve ratio of the image.",
        params = {
            @Param(name = "image", value = "The Image"),
            @Param(name = "conserve", value = "A Boolean"),
        },
        result = "sets the conserve ratio of the image",
        examples = {
            @Example(expression = "myImage.setConserveRatio(false)", result = "set the conserve ratio to false"),
        }
    )
    /**
     * Sets the conserve aspect ratio to the given {@link Image}.
     * 
     * @param image
     *            the {@link Image}
     * @param conserve
     *            <code>true</code> to conserve the aspect ratio, <code>false</code> otherwise
     * @return the given {@link Image} with the new conserve aspect ratio
     */
    public Image setConserveRatio(Image image, Boolean conserve) {
        image.setConserveRatio(conserve);

        return image;
    }

}
