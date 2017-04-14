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

import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.obeonetwork.m2doc.genconf.Definition;
import org.obeonetwork.m2doc.genconf.GenconfFactory;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.ModelDefinition;
import org.obeonetwork.m2doc.genconf.Option;
import org.obeonetwork.m2doc.genconf.StringDefinition;
import org.obeonetwork.m2doc.provider.ProviderConstants;
import org.obeonetwork.m2doc.util.M2DocUtils;

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
     * The genconf {@link URI} option.
     */
    public static final String GENCONF_URI_OPTION = "GenconfURI";

    /**
     * Init acceleo environment.
     * 
     * @param generation
     *            Generation
     * @return IQueryEnvironment
     */
    public IQueryEnvironment initAcceleoEnvironment(Generation generation) {
        URI templateURI = URI.createFileURI(generation.getTemplateFileName());
        if (generation.eResource() != null) {
            templateURI = templateURI.resolve(generation.eResource().getURI());
        }

        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);

        final Map<String, String> options = getOptions(generation);
        M2DocUtils.prepareEnvironmentServices(queryEnvironment, URIConverter.INSTANCE, templateURI, options);

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
     * @param resourcesetForModels
     *            the resourceset used to load the model instances
     * @return the created map.
     */
    public Map<String, Object> createDefinitions(Generation generation, ResourceSet resourcesetForModels) {
        Map<String, Object> result = new HashMap<String, Object>();
        for (Definition def : generation.getDefinitions()) {
            if (def instanceof ModelDefinition) {
                URI uri = EcoreUtil.getURI(((ModelDefinition) def).getValue());
                EObject val = null;
                try {
                    val = resourcesetForModels.getEObject(uri, true);
                } catch (WrappedException e) {
                    /*
                     * The resource could not be loaded, in that case the value is reset to a proxy with the same uri.
                     */
                    if (((ModelDefinition) def).getValue() != null) {
                        InternalEObject eobj = (InternalEObject) EcoreUtil
                                .create(((ModelDefinition) def).getValue().eClass());
                        eobj.eSetProxyURI(uri);
                        val = eobj;
                    }
                }
                result.put(((ModelDefinition) def).getKey(), val);
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

    /**
     * Gets the {@link Map} of options from the given {@link Generation}.
     * 
     * @param generation
     *            the {@link Generation}
     * @return the {@link Map} of options from the given {@link Generation}
     */
    public Map<String, String> getOptions(Generation generation) {
        final Map<String, String> res = new LinkedHashMap<String, String>();

        final Resource eResource = generation.eResource();
        if (eResource != null) {
            res.put(GENCONF_URI_OPTION, eResource.getURI().toString());
        }
        for (Option option : generation.getOptions()) {
            res.put(option.getName(), option.getValue());
        }

        return res;
    }

}
