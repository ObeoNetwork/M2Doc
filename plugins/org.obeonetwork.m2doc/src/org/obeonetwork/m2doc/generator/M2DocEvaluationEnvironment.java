/*******************************************************************************
 *  Copyright (c) 2019, 2025 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.generator;

import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameResolver;
import org.eclipse.acceleo.query.services.ResourceServices;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;

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
     * The {@link ResourceSet} for models.
     */
    private final ResourceSet resourceSetForModels;
    /**
     * The {@link UserContentManager}.
     */
    private final UserContentManager userContentManager;

    /**
     * The {@link RawCopier}.
     */
    private final RawCopier copier;

    /**
     * The {@link IQualifiedNameResolver}.
     */
    private final IQualifiedNameResolver resolver;

    /**
     * The template {@link URI}.
     */
    private URI templateURI;

    /**
     * The destination {@link URI}.
     */
    private URI destinationURI;

    /**
     * Constructor.
     * 
     * @param resolver
     *            the {@link IQualifiedNameResolver}
     * @param resourceSetForModels
     *            the {@link ResourceSet}
     * @param templateURI
     *            the template {@link URI}
     * @param destinationURI
     *            the destination {@link URI}
     */
    public M2DocEvaluationEnvironment(IQualifiedNameResolver resolver, ResourceSet resourceSetForModels,
            URI templateURI, URI destinationURI) {
        this.bookmarkManager = new BookmarkManager();
        this.resourceSetForModels = resourceSetForModels;
        this.userContentManager = new UserContentManager(resourceSetForModels.getURIConverter(), templateURI,
                destinationURI);
        this.copier = new RawCopier();
        this.resolver = resolver;
        this.templateURI = templateURI;
        this.destinationURI = destinationURI;
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
     * get the {@link IQualifiedNameResolver}.
     * 
     * @return the {@link IQualifiedNameResolver}
     */
    public IQualifiedNameResolver getResolver() {
        return resolver;
    }

    /**
     * Gets the {@link UserContentManager}.
     * 
     * @return the {@link UserContentManager}
     */
    public UserContentManager getUserContentManager() {
        return userContentManager;
    }

    /**
     * Gets the {@link ResourceServices} for models.
     * 
     * @return the {@link ResourceServices} for models
     */
    public ResourceSet getResourceSetForModels() {
        return resourceSetForModels;
    }

    /**
     * Gets the template {@link URI}.
     * 
     * @return the template {@link URI}
     */
    public URI getTemplateURI() {
        return templateURI;
    }

    /**
     * Gets the destination {@link URI}.
     * 
     * @return the destination {@link URI}
     */
    public URI getDestinationURI() {
        return destinationURI;
    }

}
