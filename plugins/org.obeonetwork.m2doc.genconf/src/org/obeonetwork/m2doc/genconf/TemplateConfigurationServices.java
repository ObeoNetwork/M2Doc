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
package org.obeonetwork.m2doc.genconf;

import org.obeonetwork.m2doc.genconf.util.ConfigurationServices;
import org.obeonetwork.m2doc.properties.M2DocCustomProperties;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;

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
     * @param templateProperties
     *            TemplateInfo
     * @return generation eObject with definitions template information.
     */
    public Generation addProperties(Generation generation, TemplateCustomProperties templateProperties) {
        for (String key : templateProperties.getVariables().keySet()) {
            String typeName = templateProperties.getVariables().get(key);
            Definition definition = null;
            // The only currently supported scalar type is 'string'
            if (M2DocCustomProperties.STRING_TYPE.equals(typeName)) {
                StringDefinition sdefinition = getConfigurationServices().createStringDefinition(generation);
                sdefinition.setValue(typeName);
                definition = sdefinition;
            } else {
                definition = getConfigurationServices().createModelDefinition(generation);
            }
            if (definition != null) {
                definition.setKey(key);
            }
        }
        return generation;
    }

}
