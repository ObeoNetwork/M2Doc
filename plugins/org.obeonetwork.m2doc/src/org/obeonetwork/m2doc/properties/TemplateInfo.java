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
package org.obeonetwork.m2doc.properties;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.poi.POIXMLProperties.CustomProperties;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty;

/**
 * Template information grouping the variable and service declaration in templates.
 * 
 * @author Romain Guider
 */
public class TemplateInfo {
    /**
     * The list of service tokens declared in the template.
     */
    private List<String> serviceTokens;
    /**
     * A map that associates variables declared in the template with their intended type.
     */
    private Map<String, String> variables;
    /**
     * The list of nsURIs declared in the template.
     */
    private List<String> packageURIs;

    /**
     * Constructor.
     * 
     * @param document
     *            XWPFDocument
     */
    public TemplateInfo(XWPFDocument document) {
        this.serviceTokens = Lists.newArrayList();
        this.variables = Maps.newHashMap();
        this.packageURIs = Lists.newArrayList();
        extractMetaData(document);
    }

    /**
     * Extract meta datas.
     * 
     * @param document
     *            XWPFDocument
     */
    private void extractMetaData(XWPFDocument document) {
        CustomProperties props = document.getProperties().getCustomProperties();
        List<CTProperty> properties = props.getUnderlyingProperties().getPropertyList();
        for (CTProperty property : properties) {
            String name = property.getName();
            int variablePrefixLength = M2DocCustomProperties.VAR_PROPERTY_PREFIX.length();
            if (name != null) {
                if (name.startsWith(M2DocCustomProperties.SERVICE_PROPERTY_PREFIX)) {
                    String[] tokens = property.getLpwstr().trim().split(M2DocCustomProperties.SERVICETOKEN_SEPARATOR);
                    serviceTokens.addAll(Arrays.asList(tokens));
                } else if (name.startsWith(M2DocCustomProperties.VAR_PROPERTY_PREFIX)
                    && name.length() > variablePrefixLength) {
                    String variableName = name.substring(variablePrefixLength + 1);
                    String type = property.getLpwstr();
                    if (!Strings.isNullOrEmpty(variableName)) {
                        variables.put(variableName, type);
                    }
                } else if (name.startsWith(M2DocCustomProperties.URI_PROPERTY_PREFIX)) {
                    String[] uris = property.getLpwstr().trim().split(M2DocCustomProperties.SERVICETOKEN_SEPARATOR);
                    packageURIs.addAll(Arrays.asList(uris));
                }
            }
        }
    }

    /**
     * Returns a non modifiable copy of the service tokens.
     * 
     * @return the list of service tokens.
     */
    public List<String> getServiceTokens() {
        return Collections.unmodifiableList(serviceTokens);
    }

    /**
     * Returns a non modifiable copy of the uris.
     * 
     * @return the list of service tokens.
     */
    public List<String> getPackagesURIs() {
        return Collections.unmodifiableList(packageURIs);
    }

    /**
     * REturns an unmodifiable copy of the variable type map.
     * 
     * @return the variable type map.
     */
    public Map<String, String> getVariables() {
        return Collections.unmodifiableMap(variables);
    }
}
