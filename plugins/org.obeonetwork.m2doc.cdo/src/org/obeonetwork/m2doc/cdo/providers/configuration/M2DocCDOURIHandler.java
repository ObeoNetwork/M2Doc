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
package org.obeonetwork.m2doc.cdo.providers.configuration;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Map;

import org.apache.xmlbeans.impl.common.ReaderInputStream;
import org.eclipse.emf.cdo.common.lob.CDOBlob;
import org.eclipse.emf.cdo.common.lob.CDOClob;
import org.eclipse.emf.cdo.eresource.CDOBinaryResource;
import org.eclipse.emf.cdo.eresource.CDOResourceNode;
import org.eclipse.emf.cdo.eresource.CDOTextResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.internal.cdo.view.CDOURIHandler;
import org.eclipse.emf.spi.cdo.InternalCDOTransaction;
import org.eclipse.emf.spi.cdo.InternalCDOView;

/**
 * A {@link CDOURIHandler} that support {@link CDOBinaryResource} CDO.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
@SuppressWarnings("restriction")
public class M2DocCDOURIHandler extends CDOURIHandler {

    /**
     * An {@link OutputStream} that buffer writes and feed them as the content of a {@link CDOResourceNode}.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private static final class CDONodeOutputStream extends OutputStream {

        /**
         * The {@link CDOResourceNode} to set contents to.
         */
        private final CDOResourceNode node;

        /**
         * The buffer.
         */
        private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        /**
         * Constructor.
         * 
         * @param node
         *            the {@link CDOResourceNode} to set contents to
         */
        private CDONodeOutputStream(CDOResourceNode node) {
            this.node = node;
        }

        @Override
        public void write(int b) throws IOException {
            buffer.write(b);
        }

        @Override
        public void flush() throws IOException {
            buffer.flush();
        }

        @Override
        public void close() throws IOException {
            if (node instanceof CDOBinaryResource) {
                ((CDOBinaryResource) node).setContents(new CDOBlob(new ByteArrayInputStream(buffer.toByteArray())));
            } else if (node instanceof CDOTextResource) {
                ((CDOTextResource) node).setContents(new CDOClob(new StringReader(buffer.toString("UTF-8"))));
            } else {
                throw new IllegalStateException("CDOResourceNode type not supported.");
            }
            buffer.close();
        }

    }

    /**
     * Constructor.
     * 
     * @param view
     *            the {@link InternalCDOView}
     */
    public M2DocCDOURIHandler(InternalCDOView view) {
        super(view);
    }

    @Override
    public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
        final InputStream res;

        final InternalCDOView view = getView();
        final CDOResourceNode node = view.getResourceNode(uri.path());
        if (node instanceof CDOBinaryResource) {
            res = ((CDOBinaryResource) node).getContents().getContents();
        } else if (node instanceof CDOTextResource) {
            res = new ReaderInputStream(((CDOTextResource) node).getContents().getContents(), "UTF-8");
        } else {
            res = super.createInputStream(uri, options);
        }

        return res;
    }

    @Override
    public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
        final OutputStream res;

        final InternalCDOView view = getView();
        if (view instanceof InternalCDOTransaction) {
            final InternalCDOTransaction transaction = (InternalCDOTransaction) view;

            final CDOResourceNode node;
            // TODO no binary resource
            if (uri.path().lastIndexOf('/') == 0) {
                node = transaction.getOrCreateBinaryResource(uri.path().substring(1));
            } else {
                node = transaction.getOrCreateBinaryResource(uri.path());
            }
            res = new CDONodeOutputStream(node);
        } else {
            res = super.createOutputStream(uri, options);
        }

        return res;
    }

}
