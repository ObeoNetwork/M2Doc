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
package org.obeonetwork.m2doc.genconf.provider;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.template.DocumentTemplate;

/**
 * Provider for pre and post configuration operations.
 * <ul>
 * <li>configuration model creation</li>
 * <li>template validation</li>
 * <li>template generation</li>
 * </ul>
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public interface IConfigurationProvider {

    /**
     * Post operation after configuration model creation.
     * 
     * @param templateProperties
     *            the {@link TemplateCustomProperties}
     * @param templateURI
     *            the {@link URI}
     * @param generation
     *            the {@link Generation}
     */
    void postCreateConfigurationModel(TemplateCustomProperties templateProperties, URI templateURI,
            Generation generation);

    /**
     * Pre operation before configuration model creation.
     * 
     * @param templateProperties
     *            the {@link TemplateCustomProperties}
     * @param templateURI
     *            the template {@link URI}
     */
    void preCreateConfigurationModel(TemplateCustomProperties templateProperties, URI templateURI);

    /**
     * Post operation after template validation.
     * 
     * @param templateURI
     *            the template {@link URI}
     * @param template
     *            the {@link DocumentTemplate}
     * @param generation
     *            the {@link Generation}
     * @return validation result, must return true by default.
     */
    boolean postValidateTemplate(URI templateURI, DocumentTemplate template, Generation generation);

    /**
     * Pre operation before template validation.
     * 
     * @param templateURI
     *            the template {@link URI}
     * @param template
     *            the {@link DocumentTemplate}
     * @param generation
     *            the {@link Generation}
     */
    void preValidateTemplate(URI templateURI, DocumentTemplate template, Generation generation);

    /**
     * Pre operation before generation.
     * 
     * @param generation
     *            the {@link Generation}
     * @param templateURI
     *            the template {@link URI}
     * @param generatedURI
     *            the generated {@link URI}
     */
    void preGenerate(Generation generation, URI templateURI, URI generatedURI);

    /**
     * Post operation after generation.
     * 
     * @param generation
     *            the {@link Generation}
     * @param templateURI
     *            the template {@link URI}
     * @param generatedURI
     *            the generated {@link URI}
     * @param template
     *            the {@link DocumentTemplate}
     * @return URI list to return after the generation. Generation result and validation log are already in there.
     */
    List<URI> postGenerate(Generation generation, URI templateURI, URI generatedURI, DocumentTemplate template);

    /**
     * Create a new resourceSet which would need specific initialization for loading the models specified in the Generation objects.
     * 
     * @param generation
     *            the generation object containing all the parameters of the document generation.
     * @return null if no specific resourceset has to be created for this genconf, a ResourceSet instance already configured and ready to
     *         load models if some specific setup needed to be done.
     */
    ResourceSet createResourceSetForModels(Generation generation);
}
