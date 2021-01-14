/*******************************************************************************
 *  Copyright (c) 2017 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIHandler;

/**
 * A memory {@link URIHandler}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MemoryURIHandler implements URIHandler {

    /**
     * The protocol name.
     */
    public static final String PROTOCOL = "m2docmemory";

    /**
     * Resources.
     */
    private final Map<URI, ByteArrayOutputStream> resources = new HashMap<>();

    /**
     * Clears the memory.
     */
    public void clear() {
        resources.clear();
    }

    @Override
    public boolean canHandle(URI uri) {
        return PROTOCOL.equals(uri.scheme());
    }

    @Override
    public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
        final InputStream res;

        final ByteArrayOutputStream outputStream = resources.get(uri);
        if (outputStream != null) {
            res = new ByteArrayInputStream(outputStream.toByteArray());
        } else {
            throw new IOException("Resource " + uri + " doesn't exist in memory.");
        }

        return res;
    }

    @Override
    public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
        final ByteArrayOutputStream res = new ByteArrayOutputStream();

        resources.put(uri, res);

        return res;
    }

    @Override
    public void delete(URI uri, Map<?, ?> options) throws IOException {
        resources.remove(uri);
    }

    @Override
    public Map<String, ?> contentDescription(URI uri, Map<?, ?> options) throws IOException {
        return Collections.emptyMap();
    }

    @Override
    public boolean exists(URI uri, Map<?, ?> options) {
        return resources.containsKey(uri);
    }

    @Override
    public Map<String, ?> getAttributes(URI uri, Map<?, ?> options) {
        return Collections.emptyMap();
    }

    @Override
    public void setAttributes(URI uri, Map<String, ?> attributes, Map<?, ?> options) throws IOException {
        // nothing to do here
    }

}
