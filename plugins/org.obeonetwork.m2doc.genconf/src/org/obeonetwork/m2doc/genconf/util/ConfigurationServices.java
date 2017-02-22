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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.obeonetwork.m2doc.api.QueryServices;
import org.obeonetwork.m2doc.genconf.Definition;
import org.obeonetwork.m2doc.genconf.GenconfFactory;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.ModelDefinition;
import org.obeonetwork.m2doc.genconf.StringDefinition;
import org.obeonetwork.m2doc.provider.ProviderConstants;

/**
 * Services for genconf metamodels.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class ConfigurationServices {

    /**
     * The genconf extension file.
     */
    public static final String GENCONF_EXTENSION_FILE = "genconf";

    /**
     * Init acceleo environment.
     * 
     * @param generation
     *            Generation
     * @return IQueryEnvironment
     */
    public IQueryEnvironment initAcceleoEnvironment(Generation generation) {
        final URI templateURI = URI.createFileURI(generation.getTemplateFileName());
        final IQueryEnvironment queryEnvironment = QueryServices.getInstance().getEnvironment(templateURI);

        return queryEnvironment;
    }

    /**
     * Gets the provider variables for the given {@link Generation}.
     * 
     * @param generation
     *            the {@link Generation}
     * @return the provider variables for the given {@link Generation}
     */
    public Map<String, Object> getProviderVariables(Generation generation) {
        final Map<String, Object> res = new LinkedHashMap<String, Object>();

        res.put(ProviderConstants.CONF_ROOT_OBJECT_KEY, generation);
        res.put(ProviderConstants.REFRESH_REPRESENTATIONS_KEY, generation.isRefreshRepresentations());

        return res;
    }

    /**
     * Gets the {@link IType} from variables.
     * 
     * @param generation
     *            Generation
     * @return the {@link IType} from variables
     */
    public Map<String, Set<IType>> getTypes(Generation generation) {
        final URI teamplateURI = URI.createFileURI(generation.getTemplateFileName());
        return getTypes(QueryServices.getInstance().getEnvironment(teamplateURI), generation);
    }

    /**
     * Gets the {@link IType} from variables.
     * 
     * @param environment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param generation
     *            Generation
     * @return the {@link IType} from variables
     */
    public Map<String, Set<IType>> getTypes(IReadOnlyQueryEnvironment environment, Generation generation) {
        final Map<String, Set<IType>> res = new HashMap<>();

        // create definitions
        ConfigurationServices configurationServices = new ConfigurationServices();
        Map<String, Object> definitions = configurationServices.createDefinitions(generation);

        // get types.
        for (Entry<String, Object> entry : definitions.entrySet()) {
            final Object value = entry.getValue();
            final Set<IType> types = QueryServices.getInstance().getTypes(environment, value);
            res.put(entry.getKey(), types);
        }

        return res;
    }

    /**
     * Create generation eObject.
     * 
     * @param name
     *            the name
     * @param templateFileName
     *            the template file name
     * @return the created {@link Generation}
     */
    public Generation createInitialModel(String name, String templateFileName) {
        Generation generation = GenconfFactory.eINSTANCE.createGeneration();
        generation.setName(name);
        generation.setTemplateFileName(templateFileName);
        return generation;
    }

    /**
     * Gets the Generation from the given {@link URI}.
     * 
     * @param uri
     *            the {@link URI}
     * @return the Generation from the given {@link URI}
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
