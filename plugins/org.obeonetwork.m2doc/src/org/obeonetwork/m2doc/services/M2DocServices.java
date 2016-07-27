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
package org.obeonetwork.m2doc.services;

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
public class M2DocServices {

    /**
     * Constants.
     */
    public static final String DOCX_EXTENSION_FILE = "docx";

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
            String value = templateInfo.getVariables().get(key);
            Definition definition = null;
            if (M2DocCustomProperties.INT_TYPE.equalsIgnoreCase(value)
                || M2DocCustomProperties.REAL_TYPE.equalsIgnoreCase(value)
                || M2DocCustomProperties.STRING_TYPE.equalsIgnoreCase(value)) {
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

}
