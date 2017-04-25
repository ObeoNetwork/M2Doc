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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.POIXMLProperties.CustomProperties;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.emf.ecore.EPackage;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty;

/**
 * Template properties grouping the variable and service declaration in templates.
 * 
 * @author Romain Guider
 */
public class TemplateCustomProperties {

    /**
     * id of the integer type to use in variable declaration properties (not supported yet).
     */
    public static final String INT_TYPE = "Integer";

    /**
     * id of the real type to use in variable declaration properties (not supported yet).
     */
    public static final String REAL_TYPE = "Real";

    /**
     * id of the String type to use in variable declaration properties.
     */
    public static final String STRING_TYPE = "String";

    /**
     * id of the boolean type to use in variable declaration properties (not supported yet).
     */
    public static final String BOOLEAN_TYPE = "Boolean";

    /**
     * id of the date type to use in variable declaration properties (not supported yet).
     */
    public static final String DATE_TYPE = "Date";

    /**
     * id of the object type to use in variable declaration properties (not supported yet).
     */
    public static final String OBJECT_TYPE = "Object";

    /**
     * Prefix of the variable declaration custom properties.
     */
    public static final String VAR_PROPERTY_PREFIX = "m:var:";

    /**
     * Prefix of the variable declaration custom properties length.
     */
    public static final int VAR_PROPERTY_PREFIX_LENGTH = VAR_PROPERTY_PREFIX.length();

    /**
     * Prefix of the uri declaration custom properties.
     */
    public static final String URI_PROPERTY_PREFIX = "m:uri:";

    /**
     * Prefix of the uri declaration custom properties length.
     */
    public static final int URI_PROPERTY_PREFIX_LENGTH = URI_PROPERTY_PREFIX.length();

    /**
     * Prefix of the service declaration custom properties.
     */
    public static final String SERVICE_PROPERTY_PREFIX = "m:services:";

    /**
     * Prefix of the service declaration custom properties.
     */
    public static final int SERVICE_PROPERTY_PREFIX_LENGTH = SERVICE_PROPERTY_PREFIX.length();

    /**
     * Prefix of the service import custom properties.
     */
    public static final String SERVICE_IMPORT_PROPERTY_PREFIX = "m:import:";

    /**
     * Prefix of the service import custom properties length.
     */
    public static final int SERVICE_IMPORT_PROPERTY_PREFIX_LENGTH = SERVICE_IMPORT_PROPERTY_PREFIX.length();

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
    private final List<String> nsURIs = Lists.newArrayList();

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
    public TemplateCustomProperties(XWPFDocument document) {
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
        for (CTProperty property : properties) {
            String propertyName = property.getName();
            if (propertyName == null) {
                continue;
            }
            propertyName = propertyName.trim();
            final String nsURI = getNsURI(propertyName);
            if (nsURI != null) {
                nsURIs.add(nsURI);
                continue;
            }

            final String serviceClasse = getServiceImport(propertyName);
            if (serviceClasse != null) {
                serviceClasses.add(serviceClasse);
                continue;
            }

            final String serviceToken = getServiceToken(propertyName);
            if (serviceToken != null) {
                serviceTokens.add(serviceToken);
                continue;
            }

            final String variableName = getVariableName(propertyName);
            if (variableName != null && isValidVariableName(variableName)) {
                variables.put(variableName, property.getLpwstr());
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
        List<String> tmpNsURI = new ArrayList<String>(nsURIs);
        List<String> tmpServiceImports = new ArrayList<String>(serviceClasses);
        List<String> tmpServiceTokens = new ArrayList<String>(serviceTokens);
        Map<String, String> tmpVars = new LinkedHashMap<String, String>(variables);
        for (CTProperty property : properties) {
            final String propertyName = property.getName();
            final String nsURI = getNsURI(propertyName);
            if (nsURI != null) {
                if (!tmpNsURI.remove(nsURI)) {
                    indexToDelete.add(currentIndex);
                }
                currentIndex++;
                continue;
            }

            final String serviceClasse = getServiceImport(propertyName);
            if (serviceClasse != null) {
                if (!tmpServiceImports.remove(serviceClasse)) {
                    indexToDelete.add(currentIndex);
                }
                currentIndex++;
                continue;
            }

            final String serviceToken = getServiceToken(propertyName);
            if (serviceToken != null) {
                if (!tmpServiceTokens.remove(serviceToken)) {
                    indexToDelete.add(currentIndex);
                }
                currentIndex++;
                continue;
            }

            final String variableName = getVariableName(propertyName);
            if (variableName != null) {
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

        for (String nsURI : tmpNsURI) {
            props.addProperty(URI_PROPERTY_PREFIX + nsURI, "");
        }
        for (String serviceClass : tmpServiceImports) {
            props.addProperty(SERVICE_IMPORT_PROPERTY_PREFIX + serviceClass, "");
        }
        for (String serviceToken : tmpServiceTokens) {
            props.addProperty(SERVICE_PROPERTY_PREFIX + serviceToken, "");
        }
        for (Entry<String, String> entry : tmpVars.entrySet()) {
            props.addProperty(VAR_PROPERTY_PREFIX + entry.getKey(), entry.getValue());
        }
    }

    /**
     * Gets the {@link EPackage#getNsURI() nsURI} from the given {@link CTProperty#getName() property name}.
     * 
     * @param propertyName
     *            the {@link CTProperty#getName() property name}
     * @return the {@link EPackage#getNsURI() nsURI} from the given {@link CTProperty#getName() property name} if any, <code>null</code>
     *         otherwise
     */
    private String getNsURI(String propertyName) {
        final String res;

        if (propertyName.startsWith(URI_PROPERTY_PREFIX) && propertyName.length() > URI_PROPERTY_PREFIX_LENGTH) {
            res = propertyName.substring(URI_PROPERTY_PREFIX_LENGTH);
        } else {
            res = null;
        }

        return res;
    }

    /**
     * Gets the {@link Class#getName() class name} from the given {@link CTProperty#getName() property name}.
     * 
     * @param propertyName
     *            the {@link CTProperty#getName() property name}
     * @return the {@link Class#getName() class name} from the given {@link CTProperty#getName() property name} if any, <code>null</code>
     *         otherwise
     */
    private String getServiceImport(String propertyName) {
        final String res;

        if (propertyName.startsWith(SERVICE_IMPORT_PROPERTY_PREFIX)
            && propertyName.length() > SERVICE_IMPORT_PROPERTY_PREFIX_LENGTH) {
            res = propertyName.substring(SERVICE_IMPORT_PROPERTY_PREFIX_LENGTH);
        } else {
            res = null;
        }

        return res;
    }

    /**
     * Gets the service token from the given {@link CTProperty#getName() property name}.
     * 
     * @param propertyName
     *            the {@link CTProperty#getName() property name}
     * @return the service token from the given {@link CTProperty#getName() property name} if any, <code>null</code>
     *         otherwise
     */
    private String getServiceToken(String propertyName) {
        final String res;

        if (propertyName.startsWith(SERVICE_PROPERTY_PREFIX)
            && propertyName.length() > SERVICE_PROPERTY_PREFIX_LENGTH) {
            res = propertyName.substring(SERVICE_PROPERTY_PREFIX_LENGTH);
        } else {
            res = null;
        }

        return res;
    }

    /**
     * Gets the variable name from the given {@link CTProperty#getName() property name}.
     * 
     * @param propertyName
     *            the {@link CTProperty#getName() property name}
     * @return the variable name from the given {@link CTProperty#getName() property name} if any, <code>null</code>
     *         otherwise
     */
    private String getVariableName(String propertyName) {
        final String res;

        if (propertyName.startsWith(VAR_PROPERTY_PREFIX) && propertyName.length() > VAR_PROPERTY_PREFIX_LENGTH) {
            res = propertyName.substring(VAR_PROPERTY_PREFIX_LENGTH);
        } else {
            res = null;
        }

        return res;
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
        return nsURIs;
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
