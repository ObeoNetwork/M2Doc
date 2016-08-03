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
package org.obeonetwork.m2doc.genconf.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.obeonetwork.m2doc.genconf.Definition;
import org.obeonetwork.m2doc.genconf.GenconfFactory;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.ModelDefinition;
import org.obeonetwork.m2doc.genconf.StringDefinition;

/**
 * Services for genconf metamodels.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class ConfigurationServices {

    public static final String GENCONF_EXTENSION_FILE = "genconf";

    /**
     * Create generation eObject.
     * 
     * @param name
     * @param templateFileName
     * @return Generation
     */
    public Generation createInitialModel(String name, String templateFileName) {
        Generation generation = GenconfFactory.eINSTANCE.createGeneration();
        generation.setName(name);
        generation.setTemplateFileName(templateFileName);
        return generation;
    }

    /**
     * @param uri
     *            URI
     * @return generation element from URI.
     */
    public static Generation getGeneration(URI uri) {
        ResourceSet rs = new ResourceSetImpl();
        Resource modelResource = rs.getResource(uri, true);
        if (modelResource != null && !modelResource.getContents().isEmpty()) {
            EObject root = modelResource.getContents().get(0);
            if (root instanceof Generation) {
                return (Generation) root;
            }
        }
        return null;
    }

    /**
     * Creates a definition map from a {@link Generation} instance.
     * 
     * @param generation
     *            the instance holding the definitions.
     * @return the created map.
     */
    public Map<String, Object> createDefinitions(Generation generation) {
        Map<String, Object> result = new HashMap<String, Object>();
        for (Definition def : generation.getDefinitions()) {
            if (def instanceof ModelDefinition) {
                result.put(((ModelDefinition) def).getKey(), ((ModelDefinition) def).getValue());
            } else if (def instanceof StringDefinition) {
                result.put(((StringDefinition) def).getKey(), ((StringDefinition) def).getValue());
            } else {
                throw new UnsupportedOperationException();
            }
        }
        return result;
    }

    /**
     * Create ModelDefinition.
     * 
     * @param generation
     *            Generation
     * @return ModelDefinition
     */
    public ModelDefinition createModelDefinition(Generation generation) {
        ModelDefinition modelDefinition = GenconfFactory.eINSTANCE.createModelDefinition();
        generation.getDefinitions().add(modelDefinition);
        return modelDefinition;
    }

    /**
     * Create StringDefinition.
     * 
     * @param generation
     *            Generation
     * @return StringDefinition
     */
    public StringDefinition createStringDefinition(Generation generation) {
        StringDefinition stringDefinition = GenconfFactory.eINSTANCE.createStringDefinition();
        generation.getDefinitions().add(stringDefinition);
        return stringDefinition;
    }
}
