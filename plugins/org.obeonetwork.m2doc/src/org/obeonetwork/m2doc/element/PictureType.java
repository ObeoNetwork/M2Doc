/*******************************************************************************
 *  Copyright (c) 2017, 2026 Obeo. 
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

import org.apache.poi.xwpf.usermodel.Document;
import org.eclipse.emf.common.util.URI;

/**
 * Picture types.
 * 
 * @author <a href="mailto:jean-francois.brazeau@obeo.fr">Jean-Francois Brazeau</a>
 */
public enum PictureType {

    /*** Bitmap format. */
    BMP(org.apache.poi.common.usermodel.PictureType.BMP),

    /*** DIB format. */
    DIB(org.apache.poi.common.usermodel.PictureType.DIB),

    /*** EMF format. */
    EMF(org.apache.poi.common.usermodel.PictureType.EMF),

    /*** EPS format. */
    EPS(org.apache.poi.common.usermodel.PictureType.EPS),

    /*** GIF format. */
    GIF(org.apache.poi.common.usermodel.PictureType.GIF),

    /*** JPEG format. */
    JPG(org.apache.poi.common.usermodel.PictureType.JPEG),

    /*** JPG format. */
    JPEG(org.apache.poi.common.usermodel.PictureType.JPEG),

    /*** PICT format. */
    PICT(org.apache.poi.common.usermodel.PictureType.PICT),

    /*** PNG format. */
    PNG(org.apache.poi.common.usermodel.PictureType.PNG),

    /*** PNG format. */
    SVG(org.apache.poi.common.usermodel.PictureType.PNG),

    /*** TIFF format. */
    TIFF(org.apache.poi.common.usermodel.PictureType.TIFF),

    /*** WMF format. */
    WMF(org.apache.poi.common.usermodel.PictureType.WMF),

    /*** WPG format. */
    WPG(org.apache.poi.common.usermodel.PictureType.WPG);

    /**
     * Data scheme.
     */
    private static final String DATA = "data";

    /**
     * Image mime type.
     */
    private static final String MIME_IMAGE_TYPE = "image/";

    /**
     * A semi colon.
     */
    private static final String SEMI_COLON = ";";

    /**
     * The picture type according to POI.
     */
    private org.apache.poi.common.usermodel.PictureType poiType;

    /**
     * Default constructor.
     * 
     * @param poiType
     *            the POI {@link org.apache.poi.common.usermodel.PictureType}
     */
    PictureType(org.apache.poi.common.usermodel.PictureType poiType) {
        this.poiType = poiType;
    }

    /**
     * Return the picture type value according to POI.
     * 
     * @return the poiType the POI picture type.
     * @see Document
     */
    public int getPoiType() {
        return poiType.ooxmlId;
    }

    /**
     * Return the {@link org.apache.poi.common.usermodel.PictureType}.
     * 
     * @return the {@link org.apache.poi.common.usermodel.PictureType}
     */
    public org.apache.poi.common.usermodel.PictureType getPoiPictureType() {
        return poiType;
    }

    /**
     * Returns the image type based on the filename's extension.
     * 
     * @param pictureURI
     *            the picture {@link URI}.
     * @return the picture type.
     */
    public static PictureType toType(URI pictureURI) {
        PictureType res = null;

        if (pictureURI.fileExtension() != null) {
            String extension = pictureURI.fileExtension();
            try {
                res = valueOf(extension.toUpperCase());
            } catch (IllegalArgumentException ignored) {
                // Simply ignore this exception. It means that the
                // given extension is unknown.
                // JPG type will be returned by default.
            }
        } else if (DATA.equalsIgnoreCase(pictureURI.scheme())) {
            final int semiColonIndex = pictureURI.opaquePart().indexOf(SEMI_COLON);
            if (semiColonIndex > 0) {
                final String type = pictureURI.opaquePart().substring(0, semiColonIndex).toLowerCase();
                if (type.startsWith(MIME_IMAGE_TYPE)) {
                    try {
                        res = valueOf(type.substring(MIME_IMAGE_TYPE.length()).toUpperCase());
                    } catch (IllegalArgumentException ignored) {
                        // Simply ignore this exception. It means that the
                        // given extension is unknown.
                        // JPG type will be returned by default.
                    }
                }
            }
        }

        // By default, fallback to JPG format
        if (res == null) {
            res = JPG;
        }

        return res;
    }

}
