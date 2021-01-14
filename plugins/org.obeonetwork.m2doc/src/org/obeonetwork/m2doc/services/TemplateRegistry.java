/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;

/**
 * {@link TemplateRegistry} is used to register M2Doc templates.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public final class TemplateRegistry {

    /**
     * Single instance of {@link TemplateRegistry}.
     */
    public static final TemplateRegistry INSTANCE = new TemplateRegistry();

    /**
     * Mapping from template name to its {@link URI}.
     */
    private Map<String, URI> templatesRegistry = new LinkedHashMap<>();

    /**
     * Registers a template.
     * 
     * @param templateName
     *            the template name
     * @param templateURI
     *            the template {@link URI}
     */
    public void registerTemplate(String templateName, URI templateURI) {
        templatesRegistry.put(templateName, templateURI);
    }

    /**
     * Removes the given {@link List} of service class names from the given token name.
     * 
     * @param templateName
     *            the template name
     */
    public void unregisterTemplate(String templateName) {
        templatesRegistry.remove(templateName);
    }

    /**
     * Gets the template name to template {@link URI} mapping.
     * 
     * @return the template name to template {@link URI} mapping
     */
    public Map<String, URI> getTemplates() {
        return templatesRegistry;
    }

}
