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

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
    private final Map<String, String> variables = Maps.newLinkedHashMap();

    /**
     * The list of nsURIs declared in the template.
     */
    private final List<String> packageURIs = Lists.newArrayList();

    /**
     * The {@link List} of service {@link Class#getName() class names}.
     */
    private final List<String> serviceClasses = Lists.newArrayList();

    /**
     * The {@link XWPFDocument}.
     */
    private final XWPFDocument document;

    /**
     * Constructor.
     * 
     * @param document
     *            the {@link XWPFDocument}
     */
    public TemplateInfo(XWPFDocument document) {
        this.document = document;
        parseProperties(document);
    }

    /**
     * Parses {@link CustomProperties}.
     * 
     * @param doc
     *            the {@link XWPFDocument}
     */
    private void parseProperties(XWPFDocument doc) {
        final CustomProperties props = doc.getProperties().getCustomProperties();
        final List<CTProperty> properties = props.getUnderlyingProperties().getPropertyList();
        final int variablePrefixLength = M2DocCustomProperties.VAR_PROPERTY_PREFIX.length();
        for (CTProperty property : properties) {
            String propertyName = property.getName();
            if (propertyName == null) {
                continue;
            }
            propertyName = propertyName.trim();
            if (M2DocCustomProperties.SERVICE_PROPERTY_PREFIX.equals(propertyName)) {
                String serviceTokenList = property.getLpwstr();
                if (serviceTokenList != null) {
                    String[] tokens = serviceTokenList.trim().split(M2DocCustomProperties.SEPARATOR);
                    serviceTokens.addAll(Arrays.asList(tokens));
                }
            } else if (propertyName.startsWith(M2DocCustomProperties.VAR_PROPERTY_PREFIX)
                && propertyName.length() > variablePrefixLength) {
                final String variableName = propertyName.substring(variablePrefixLength);
                final String type = property.getLpwstr();
                if (isValidVariableName(variableName)) {
                    variables.put(variableName, type);
                }
            } else if (M2DocCustomProperties.URI_PROPERTY_PREFIX.equals(propertyName)) {
                parseEPackages(property);
            } else if (M2DocCustomProperties.SERVICE_IMPORT_PROPERTY_PREFIX.equals(propertyName)) {
                parseServiceImports(property);
            }
        }
    }

    /**
     * Parses {@link org.eclipse.emf.ecore.EPackage EPackage} imports.
     * 
     * @param property
     *            the property to parse
     */
    private void parseEPackages(CTProperty property) {
        String uriList = property.getLpwstr();
        if (uriList != null) {
            String[] uris = uriList.trim().split(M2DocCustomProperties.SEPARATOR);
            for (String uri : uris) {
                if (!uri.trim().isEmpty()) {
                    packageURIs.add(uri.trim());
                }
            }
        }
    }

    /**
     * Parses service imports.
     * 
     * @param property
     *            the property to parse
     */
    private void parseServiceImports(CTProperty property) {
        String uriList = property.getLpwstr();
        if (uriList != null) {
            String[] uris = uriList.trim().split(M2DocCustomProperties.SEPARATOR);
            for (String uri : uris) {
                if (!uri.trim().isEmpty()) {
                    serviceClasses.add(uri.trim());
                }
            }
        }
    }

    /**
     * Saves the properties to the document.
     */
    public void save() {
        final CustomProperties props = document.getProperties().getCustomProperties();
        final List<CTProperty> properties = props.getUnderlyingProperties().getPropertyList();
        final List<Integer> indexToDelete = new ArrayList<Integer>();
        int currentIndex = 0;
        final int variablePrefixLength = M2DocCustomProperties.VAR_PROPERTY_PREFIX.length();
        boolean uriInsertionDone = false;
        boolean serviceImportsInsertionDone = false;
        boolean serviceTokenInsertionDone = false;
        Map<String, String> tmpVars = new LinkedHashMap<String, String>(variables);
        for (CTProperty property : properties) {
            final String propertyName = property.getName();
            if (M2DocCustomProperties.URI_PROPERTY_PREFIX.equals(propertyName)) {
                if (!packageURIs.isEmpty()) {
                    property.setLpwstr(Joiner.on(M2DocCustomProperties.SEPARATOR).join(packageURIs));
                    uriInsertionDone = true;
                } else {
                    indexToDelete.add(currentIndex);
                }
            } else if (M2DocCustomProperties.SERVICE_IMPORT_PROPERTY_PREFIX.equals(propertyName)) {
                if (!serviceClasses.isEmpty()) {
                    property.setLpwstr(Joiner.on(M2DocCustomProperties.SEPARATOR).join(serviceClasses));
                    serviceImportsInsertionDone = true;
                } else {
                    indexToDelete.add(currentIndex);
                }
            } else if (M2DocCustomProperties.SERVICE_PROPERTY_PREFIX.equals(propertyName)) {
                if (!serviceTokens.isEmpty()) {
                    property.setLpwstr(Joiner.on(M2DocCustomProperties.SEPARATOR).join(serviceTokens));
                    serviceTokenInsertionDone = true;
                } else {
                    indexToDelete.add(currentIndex);
                }
            } else if (propertyName.startsWith(M2DocCustomProperties.VAR_PROPERTY_PREFIX)
                && propertyName.length() > variablePrefixLength) {
                final String variableName = propertyName.substring(variablePrefixLength);
                final String type = tmpVars.remove(variableName);
                if (type != null) {
                    property.setLpwstr(type);
                } else {
                    indexToDelete.add(currentIndex);
                }
            }
            currentIndex++;
        }

        for (int i = indexToDelete.size() - 1; i > -1; i--) {
            props.getUnderlyingProperties().removeProperty(indexToDelete.get(i));
        }

        if (!uriInsertionDone && !packageURIs.isEmpty()) {
            props.addProperty(M2DocCustomProperties.URI_PROPERTY_PREFIX,
                    Joiner.on(M2DocCustomProperties.SEPARATOR).join(packageURIs));
        }
        if (!serviceImportsInsertionDone && !serviceClasses.isEmpty()) {
            props.addProperty(M2DocCustomProperties.SERVICE_IMPORT_PROPERTY_PREFIX,
                    Joiner.on(M2DocCustomProperties.SEPARATOR).join(serviceClasses));
        }
        if (!serviceTokenInsertionDone && !serviceTokens.isEmpty()) {
            props.addProperty(M2DocCustomProperties.SERVICE_PROPERTY_PREFIX,
                    Joiner.on(M2DocCustomProperties.SEPARATOR).join(serviceTokens));
        }
        if (!tmpVars.isEmpty()) {
            for (Entry<String, String> entry : tmpVars.entrySet()) {
                props.addProperty(M2DocCustomProperties.VAR_PROPERTY_PREFIX + entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Returns a non modifiable copy of the service tokens.
     * 
     * @return the list of service tokens.
     */
    public List<String> getServiceTokens() {
        return serviceTokens;
    }

    /**
     * Returns a non modifiable copy of the uris.
     * 
     * @return the list of service tokens.
     */
    public List<String> getPackagesURIs() {
        return packageURIs;
    }

    /**
     * Returns an unmodifiable copy of the variable type map.
     * 
     * @return the variable type map.
     */
    public Map<String, String> getVariables() {
        return variables;
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
