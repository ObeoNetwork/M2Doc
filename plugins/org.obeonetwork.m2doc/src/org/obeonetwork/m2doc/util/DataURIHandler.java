/*******************************************************************************
 *  Copyright (c) 2020 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIHandler;

/**
 * Handles <a href="https://tools.ietf.org/html/rfc2397">The "data" URL scheme</a>.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class DataURIHandler implements URIHandler {

    /**
     * Not implemented message.
     */
    private static final String NOT_IMPLEMENTED = "Not implemented.";

    /**
     * Data scheme.
     */
    private static final String DATA = "data";

    /**
     * A semi colon.
     */
    private static final String SEMI_COLON = ";";

    /**
     * A coma.
     */
    private static final String COMA = ",";

    /**
     * The base64 string.
     */
    private static final String BASE64 = "base64";

    @Override
    public boolean canHandle(URI uri) {
        return DATA.equals(uri.scheme().toLowerCase());
    }

    @Override
    public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
        final byte[] res;

        final int semiColonIndex = uri.opaquePart().indexOf(SEMI_COLON);
        final int commaIndex = uri.opaquePart().indexOf(COMA);
        if (commaIndex >= 0) {
            final String uriData = uri.opaquePart().substring(commaIndex + 1);
            if (semiColonIndex >= 0
                && uri.opaquePart().substring(semiColonIndex + 1, commaIndex).equalsIgnoreCase(BASE64)) {
                res = Base64.getDecoder().decode(uriData);
            } else {
                res = URI.decode(uriData).getBytes();
            }
        } else {
            res = new byte[] {};
        }

        return new ByteArrayInputStream(res);
    }

    @Override
    public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    @Override
    public void delete(URI uri, Map<?, ?> options) throws IOException {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    @Override
    public Map<String, ?> contentDescription(URI uri, Map<?, ?> options) throws IOException {
        return null;
    }

    @Override
    public boolean exists(URI uri, Map<?, ?> options) {
        return true;
    }

    @Override
    public Map<String, ?> getAttributes(URI uri, Map<?, ?> options) {
        return null;
    }

    @Override
    public void setAttributes(URI uri, Map<String, ?> attributes, Map<?, ?> options) throws IOException {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

}
