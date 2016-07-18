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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.obeonetwork.m2doc.genconf.Definition;
import org.obeonetwork.m2doc.genconf.GenconfFactory;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.properties.M2DocCustomProperties;
import org.obeonetwork.m2doc.properties.TemplateInfo;

/**
 * Services for genconf metamodels.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class ConfigurationServices {

    /**
     * @param generation
     *            Generation
     * @param templateInfo
     *            TemplateInfo
     * @return generation eObject with definitions template information.
     */
    public Generation addProperties(Generation generation, TemplateInfo templateInfo) {
        for (String key : templateInfo.getVariables().keySet()) {
            String value = templateInfo.getVariables().get(key);
            Definition definition = null;
            if (M2DocCustomProperties.INT_TYPE.equals(value) || M2DocCustomProperties.REAL_TYPE.equals(value)
                || M2DocCustomProperties.STRING_TYPE.equals(value)) {
                definition = GenconfFactory.eINSTANCE.createStringDefinition();
            } else {
                definition = GenconfFactory.eINSTANCE.createModelDefinition();
            }
            if (definition != null) {
                definition.setKey(key);

            }
            generation.getDefinitions().add(definition);
        }
        return generation;
    }

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
}
