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
import org.obeonetwork.m2doc.element.MImage;
import org.obeonetwork.m2doc.element.impl.MImageImpl;
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
        value = "Convert a String representing an URI to an Image.",
        params = {
            @Param(name = "uri", value = "The Image uri, it can be relative to the template"),
        },
        result = "insert the image",
        examples = {
            @Example(expression = "'image.png'.asImage()", result = "insert the image 'image.png'"),
        }
    )
    // @formatter:on
    public MImage asImage(String uriStr) {
        final URI imageURI = URI.createURI(uriStr);
        return asImage(uriStr, PictureType.toType(imageURI));
    }

    // @formatter:off
    @Documentation(
        value = "Convert a String representing an URI to an Image and serialize it in the given format.",
        params = {
            @Param(name = "uri", value = "The Image uri, it can be relative to the template"),
        },
        result = "insert the image",
        examples = {
            @Example(expression = "'image.png'.asImage('jpg')", result = "insert the image 'image.jpg'"),
        }
    )
    // @formatter:on
    public MImage asImage(String uriStr, String type) {
        return asImage(uriStr, PictureType.valueOf(type.toUpperCase()));
    }

    /**
     * Gets the {@link MImage} corresponding to the given path.
     * 
     * @param uriStr
     *            the uri.
     * @param type
     *            the picture {@link PictureType type}.
     * @return the {@link MImage} corresponding to the given path
     */
    private MImage asImage(String uriStr, PictureType type) {
        final URI imageURI = URI.createURI(uriStr);

        URI uri = imageURI.resolve(templateURI);

        return new MImageImpl(uri, type);
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
    public MImage setWidth(MImage image, Integer width) {
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
    public MImage setHeight(MImage image, Integer height) {
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
    public MImage setConserveRatio(MImage image, Boolean conserve) {
        image.setConserveRatio(conserve);

        return image;
    }

}
