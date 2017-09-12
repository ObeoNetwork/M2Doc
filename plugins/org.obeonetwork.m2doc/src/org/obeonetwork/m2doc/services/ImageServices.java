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
import org.eclipse.emf.ecore.resource.URIConverter;
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
     * The URI converter to use.
     */
    private URIConverter uriConverter;

    /**
     * The template URI.
     */
    private final URI templateURI;

    /**
     * Constructor.
     * 
     * @param uriConverter
     *            the {@link URIConverter uri converter} to use.
     * @param templateURI
     *            the template {@link URI}
     */
    public ImageServices(URIConverter uriConverter, URI templateURI) {
        this.uriConverter = uriConverter;
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

        return new MImageImpl(uriConverter, uri, type);
    }

    // @formatter:off
    @Documentation(
        value = "Gets the width of the image.",
        params = {
            @Param(name = "image", value = "The Image"),
        },
        result = "gets the width of the image",
        examples = {
            @Example(expression = "myImage.getWidth()", result = "300"),
        }
    )
    // @formatter:on
    public Integer getWidth(MImage image) {
        return image.getWidth();
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
        },
        result = "gets the height of the image",
        examples = {
            @Example(expression = "myImage.getHeight()", result = "300"),
        }
    )
    // @formatter:on
    public Integer getHeight(MImage image) {
        return image.getHeight();
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
    // @formatter:on
    public MImage setConserveRatio(MImage image, Boolean conserve) {
        image.setConserveRatio(conserve);

        return image;
    }

    // @formatter:off
    @Documentation(
        value = "Fits the Image in the given the given rectangle width and height.",
        params = {
            @Param(name = "image", value = "The Image"),
            @Param(name = "width", value = "The width to fit"),
            @Param(name = "height", value = "The height to fit"),
        },
        result = "sets the conserve ratio of the image",
        examples = {
            @Example(expression = "myImage.fit(200, 300)", result = "will fit the image in a rectangle (width=200, height=300)"),
        }
    )
    // @formatter:on
    public MImage fit(MImage image, Integer width, Integer height) {
        image.setWidth(width);
        if (!image.conserveRatio() || image.getHeight() > height) {
            image.setHeight(height);
        }

        return image;
    }

}
