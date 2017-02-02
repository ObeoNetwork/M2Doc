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

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.obeonetwork.m2doc.genconf.Definition;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.ModelDefinition;
import org.obeonetwork.m2doc.genconf.StringDefinition;
import org.obeonetwork.m2doc.genconf.util.ConfigurationServices;
import org.obeonetwork.m2doc.properties.M2DocCustomProperties;
import org.obeonetwork.m2doc.properties.TemplateInfo;
import org.obeonetwork.m2doc.util.TemplateConfigUtil;

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
        Multimap<String, EPackage> packagesByName = ArrayListMultimap.create();
        for (String uri : templateInfo.getPackagesURIs()) {
            if (EPackage.Registry.INSTANCE.containsKey(uri)) {
                EPackage p = EPackage.Registry.INSTANCE.getEPackage(uri);
                if (p != null && p.getName() != null) {
                    packagesByName.put(p.getName(), p);
                }
            }
        }
        for (String key : templateInfo.getVariables().keySet()) {
            String typeName = templateInfo.getVariables().get(key);
            Definition definition = null;
            // The only currently supported scalar type is 'string'
            if (M2DocCustomProperties.STRING_TYPE.equals(typeName)) {
                StringDefinition sdefinition = getConfigurationServices().createStringDefinition(generation);
                sdefinition.setValue(typeName);
                definition = sdefinition;
            } else if (TemplateConfigUtil.isValidClassifierTypeName(typeName)) {
                int index = typeName.indexOf(TemplateConfigUtil.METAMODEL_TYPE_SEPARATOR);
                String packageName = typeName.substring(0, index);
                String classifierName = typeName.substring(index + 2);
                ModelDefinition mdefinition = getConfigurationServices().createModelDefinition(generation);
                if (packagesByName.containsKey(packageName)) {
                    Collection<EPackage> packages = packagesByName.get(packageName);
                    // We'll use the first matching classifier we find with that name among the packages with this name
                    for (EPackage pack : packages) {
                        EClassifier eClassifier = pack.getEClassifier(classifierName);
                        if (eClassifier != null) {
                            mdefinition.setType(eClassifier);
                            break;
                        }
                    }
                }
                definition = mdefinition;
            } else {
                // Create untyped definition
                definition = getConfigurationServices().createModelDefinition(generation);
            }
            if (definition != null) {
                definition.setKey(key);
            }
        }
        return generation;
    }

}
