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
    private final List<String> serviceTokens = Lists.newArrayList();

    /**
     * A map that associates variables declared in the template with their intended type.
     */
    private final Map<String, String> variables = Maps.newHashMap();

    /**
     * The list of nsURIs declared in the template.
     */
    private final List<String> packageURIs = Lists.newArrayList();

    /**
     * The {@link List} of service {@link Class#getName() class names}.
     */
    private final List<String> serviceClasses = Lists.newArrayList();

    /**
     * Constructor.
     * 
     * @param document
     *            XWPFDocument
     */
    public TemplateInfo(XWPFDocument document) {
        extractMetaData(document);
    }

    /**
     * Extracts meta data.
     * 
     * @param document
     *            XWPFDocument
     */
    private void extractMetaData(XWPFDocument document) {
        final CustomProperties props = document.getProperties().getCustomProperties();
        final List<CTProperty> properties = props.getUnderlyingProperties().getPropertyList();
        final int variablePrefixLength = M2DocCustomProperties.VAR_PROPERTY_PREFIX.length();
        for (CTProperty property : properties) {
            String name = property.getName();
            if (name == null) {
                continue;
            }
            name = name.trim();
            if (M2DocCustomProperties.SERVICE_PROPERTY_PREFIX.equals(name)) {
                String serviceTokenList = property.getLpwstr();
                if (serviceTokenList != null) {
                    String[] tokens = serviceTokenList.trim().split(M2DocCustomProperties.SERVICETOKEN_SEPARATOR);
                    serviceTokens.addAll(Arrays.asList(tokens));
                }
            } else if (name.startsWith(M2DocCustomProperties.VAR_PROPERTY_PREFIX)
                && name.length() > variablePrefixLength) {
                String variableName = name.substring(variablePrefixLength);
                String type = property.getLpwstr();
                if (isValidVariableName(variableName)) {
                    variables.put(variableName, type);
                }
            } else if (M2DocCustomProperties.URI_PROPERTY_PREFIX.equals(name)) {
                extractEPackages(property);
            } else if (M2DocCustomProperties.SERVICE_IMPORT_PROPERTY_PREFIX.equals(name)) {
                extractServiceImports(property);
            }
        }
    }

    /**
     * Extracts {@link org.eclipse.emf.ecore.EPackage EPackage} imports.
     * 
     * @param property
     *            the property to parse
     */
    private void extractEPackages(CTProperty property) {
        String uriList = property.getLpwstr();
        if (uriList != null) {
            String[] uris = uriList.trim().split(M2DocCustomProperties.SERVICETOKEN_SEPARATOR);
            for (String uri : uris) {
                if (!uri.trim().isEmpty()) {
                    packageURIs.add(uri.trim());
                }
            }
        }
    }

    /**
     * Extracts service imports.
     * 
     * @param property
     *            the property to parse
     */
    private void extractServiceImports(CTProperty property) {
        String uriList = property.getLpwstr();
        if (uriList != null) {
            String[] uris = uriList.trim().split(M2DocCustomProperties.SERVICETOKEN_SEPARATOR);
            for (String uri : uris) {
                if (!uri.trim().isEmpty()) {
                    serviceClasses.add(uri.trim());
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
     * Returns an unmodifiable copy of the variable type map.
     * 
     * @return the variable type map.
     */
    public Map<String, String> getVariables() {
        return Collections.unmodifiableMap(variables);
    }

    /**
     * Gets the {@link List} of service {@link Class#getName() class names}.
     * 
     * @return the {@link List} of service {@link Class#getName() class names}
     */
    public List<String> getServiceClasses() {
        return serviceClasses;
    }

    /**
     * Check that a given name is a valid M2DOC (I.e. AQL) variable name.
     * 
     * @param name
     *            The variable name to check
     * @return <code>true</code> if the given name matches "[a-zA-Z_][a-zA-Z0-9_]*".
     */
    public static boolean isValidVariableName(String name) {
        return name != null && name.matches("[a-zA-Z_][a-zA-Z0-9_]*");
    }
}
