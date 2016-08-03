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
package org.obeonetwork.m2doc.api;

import org.obeonetwork.m2doc.genconf.Definition;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.util.ConfigurationServices;
import org.obeonetwork.m2doc.properties.M2DocCustomProperties;
import org.obeonetwork.m2doc.properties.TemplateInfo;

/**
 * Services for genconf metamodels.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public final class TemplateConfigurationServices {

    /**
     * Instance.
     */
    private static TemplateConfigurationServices eINSTANCE = new TemplateConfigurationServices();

    /**
     * Configuration services.
     */
    private ConfigurationServices configurationServices;

    /**
     * Constructor.
     */
    private TemplateConfigurationServices() {
        super();
    }

    /**
     * return the instance.
     * 
     * @return the instance
     */
    public static TemplateConfigurationServices getInstance() {
        return eINSTANCE;
    }

    /**
     * return ConfigurationServices.
     * 
     * @return ConfigurationServices
     */
    public ConfigurationServices getConfigurationServices() {
        if (configurationServices == null) {
            configurationServices = new ConfigurationServices();
        }
        return configurationServices;
    }

    /**
     * return generation eObject with definitions template information.
     * 
     * @param generation
     *            Generation
     * @param templateInfo
     *            TemplateInfo
     * @return generation eObject with definitions template information.
     */
    public Generation addProperties(Generation generation, TemplateInfo templateInfo) {
        for (String key : templateInfo.getVariables().keySet()) {
            String type = templateInfo.getVariables().get(key);
            Definition definition = null;
            if (M2DocCustomProperties.INT_TYPE.equalsIgnoreCase(type)
                || M2DocCustomProperties.REAL_TYPE.equalsIgnoreCase(type)
                || M2DocCustomProperties.BOOLEAN_TYPE.equalsIgnoreCase(type)
                || M2DocCustomProperties.DATE_TYPE.equalsIgnoreCase(type)
                || M2DocCustomProperties.STRING_TYPE.equalsIgnoreCase(type)) {
                definition = getConfigurationServices().createStringDefinition(generation);
            } else {
                definition = getConfigurationServices().createModelDefinition(generation);
                // TODO later get var type EClassifier from ?
                // EClassifier eClassifier = QueryServices.getInstance().getEClassifier(generation, type);
                // ((ModelDefinition) definition).setType(eClassifier);
            }
            if (definition != null) {
                definition.setKey(key);
            }
        }
        return generation;
    }

}
