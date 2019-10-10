/*******************************************************************************
 *  Copyright (c) 2019 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.generator;

import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;

/**
 * Evaluation environemnt for M2Doc.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class M2DocEvaluationEnvironment {

    /**
     * The {@link BookmarkManager}.
     */
    private final BookmarkManager bookmarkManager;

    /**
     * The {@link UserContentManager}.
     */
    private final UserContentManager userContentManager;

    /**
     * The {@link RawCopier}.
     */
    private final RawCopier copier;

    /**
     * The {@link IReadOnlyQueryEnvironment}.
     */
    private final IReadOnlyQueryEnvironment queryEnvironment;

    /**
     * Constructor.
     * 
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param uriConverter
     *            the {@link URIConverter}
     * @param sourceURI
     *            the source {@link URI}
     * @param destinationURI
     *            the destination {@link URI}
     */
    public M2DocEvaluationEnvironment(IReadOnlyQueryEnvironment queryEnvironment, URIConverter uriConverter,
            URI sourceURI, URI destinationURI) {
        this.bookmarkManager = new BookmarkManager();
        this.userContentManager = new UserContentManager(uriConverter, sourceURI, destinationURI);
        this.copier = new RawCopier();
        this.queryEnvironment = queryEnvironment;
    }

    /**
     * Gets the {@link BookmarkManager}.
     * 
     * @return the {@link BookmarkManager}
     */
    public BookmarkManager getBookmarkManager() {
        return bookmarkManager;
    }

    /**
     * Gets the {@link RawCopier}.
     * 
     * @return the {@link RawCopier}
     */
    public RawCopier getCopier() {
        return copier;
    }

    /**
     * get the {@link IReadOnlyQueryEnvironment}.
     * 
     * @return the {@link IReadOnlyQueryEnvironment}
     */
    public IReadOnlyQueryEnvironment getQueryEnvironment() {
        return queryEnvironment;
    }

    /**
     * Gets the {@link UserContentManager}.
     * 
     * @return the {@link UserContentManager}
     */
    public UserContentManager getUserContentManager() {
        return userContentManager;
    }

}
