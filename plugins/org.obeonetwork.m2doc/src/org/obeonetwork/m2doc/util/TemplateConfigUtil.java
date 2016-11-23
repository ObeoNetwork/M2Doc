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
package org.obeonetwork.m2doc.util;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.POIXMLProperties.CustomProperties;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.util.EcoreValidator;
import org.obeonetwork.m2doc.api.POIServices;
import org.obeonetwork.m2doc.properties.M2DocCustomProperties;
import org.obeonetwork.m2doc.properties.TemplateInfo;
import org.obeonetwork.m2doc.tplconf.EPackageMapping;
import org.obeonetwork.m2doc.tplconf.ScalarType;
import org.obeonetwork.m2doc.tplconf.StructuredType;
import org.obeonetwork.m2doc.tplconf.TemplateConfig;
import org.obeonetwork.m2doc.tplconf.TemplateType;
import org.obeonetwork.m2doc.tplconf.TemplateVariable;
import org.obeonetwork.m2doc.tplconf.TplconfFactory;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty;

/**
 * Utility class to load an save {@link TemplateConfig} objects to/from docx template files.
 * 
 * @author ldelaigue
 */
public final class TemplateConfigUtil {

    /**
     * Separator used between the {@link EPackage} name and the {@link EClassifier} name when serializing metamodel type names.
     */
    public static final String METAMODEL_TYPE_SEPARATOR = "::";

    /**
     * Private constructor.
     */
    private TemplateConfigUtil() {
        // To prevent instantiation
    }

    /**
     * Get the typeName of the given classifier, as it should be used in docx properties.
     * 
     * @param eClassifier
     *            The classifier, must not be <code>null</code>
     * @return The type name to use in docx properties serialization, build by concatenating the {@link EPackage}'s name, the
     *         {@link TemplateConfigUtil#METAMODEL_TYPE_SEPARATOR}, and the {@link EClassifier}'s name.
     */
    public static String typeName(EClassifier eClassifier) {
        return typeName(eClassifier.getEPackage().getName(), eClassifier.getName());
    }

    /**
     * Get the typeName for the given package anem and classifier name.
     * 
     * @param packageName
     *            The package name, must not be <code>null</code>
     * @param classifierName
     *            The classifier name, must not be <code>null</code>
     * @return The type name to use in docx properties serialization.
     */
    public static String typeName(String packageName, String classifierName) {
        return packageName + METAMODEL_TYPE_SEPARATOR + classifierName;
    }

    /**
     * Load a template configuration (metamodel URIs, declared variables) from a given docx template file.
     * 
     * @param templateFile
     *            The docx template file, must not be <code>null</code> and must be a valid docx file
     * @return A new instance of {@link TemplateConfig} that contains the metamodel URIs and the declared variables extracted from the given
     *         docx file's custom properties.
     * @throws IOException
     *             If a I/O problem occurs while reading the given docx file.
     */
    public static TemplateConfig load(IFile templateFile) throws IOException {
        TemplateInfo info = POIServices.getInstance().getTemplateInformations(templateFile);
        return load(info);
    }

    /**
     * Load a template configuration from a given {@link TemplateInfo} object.
     * 
     * @param info
     *            The template info
     * @return A new instance of {@link TemplateConfig} that contains the relevant information.
     */
    public static TemplateConfig load(TemplateInfo info) {
        TemplateConfig config = TplconfFactory.eINSTANCE.createTemplateConfig();
        createSupportedScalarTypes(config);
        for (String uri : info.getPackagesURIs()) {
            EPackageMapping mm = TplconfFactory.eINSTANCE.createEPackageMapping();
            mm.setUri(uri);
            EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(uri);
            if (ePackage != null) {
                mm.setName(ePackage.getName());
                mm.setEPackage(ePackage);
            }
            config.getMappings().add(mm);
        }
        for (Map.Entry<String, String> entry : info.getVariables().entrySet()) {
            TemplateVariable var = TplconfFactory.eINSTANCE.createTemplateVariable();
            var.setName(entry.getKey());
            String typeName = entry.getValue();
            config.getVariables().add(var);
            if (typeName == null) {
                continue;
            }
            var.setTypeName(typeName);
            if (typeName.indexOf(METAMODEL_TYPE_SEPARATOR) <= 0) {
                // Scalar type
                TemplateType type = config.getTypesByName().get(typeName);
                if (type != null) {
                    var.setType(type);
                }
            } else if (typeName != null) {
                StructuredType type = (StructuredType) config.getTypesByName().get(typeName);
                if (type != null) {
                    var.setType(type);
                } else {
                    loadStructuredType(config, var, typeName);
                }
            }
        }
        return config;
    }

    /**
     * Set the relevant type to the given variable, given an EClassifier type name.
     * 
     * @param config
     *            The config
     * @param var
     *            The variable whose type to set
     * @param typeName
     *            The type name
     */
    private static void loadStructuredType(TemplateConfig config, TemplateVariable var, String typeName) {
        int index = typeName.indexOf(METAMODEL_TYPE_SEPARATOR);
        StructuredType type;
        String mappingName = typeName.substring(0, index);
        String classifierName = typeName.substring(index + 2);
        EPackageMapping mapping = null;
        EClassifier classifier = null;
        for (EPackageMapping candidateMapping : config.getMappings()) {
            if (mappingName.equals(candidateMapping.getName())) {
                classifier = ((EPackage) candidateMapping.getEPackage()).getEClassifier(classifierName);
                if (classifier != null) {
                    // Found the right mapping
                    mapping = candidateMapping;
                    break;
                }
            }
        }
        if (mapping == null) {
            // Metamodel not registered yet => is it registered in the current eclipse version?
            Set<String> uris = new LinkedHashSet<String>(Registry.INSTANCE.keySet()); // Avoid concurrent modification
            for (String nsURI : uris) {
                EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
                if (ePackage != null && mappingName.equals(ePackage.getName())) {
                    classifier = ePackage.getEClassifier(classifierName);
                    if (classifier != null) {
                        // Found it!
                        mapping = TplconfFactory.eINSTANCE.createEPackageMapping();
                        mapping.setName(mappingName);
                        mapping.setUri(nsURI);
                        mapping.setEPackage(ePackage);
                        config.getMappings().add(mapping);
                        break;
                    }
                }
            }
        }
        type = TplconfFactory.eINSTANCE.createStructuredType();
        type.setName(classifierName);
        type.setMappingName(mappingName);
        config.getTypesByName().put(typeName, type);
        var.setType(type);
        // Metamodel found for this prefix
        if (mapping != null) {
            EPackage ePackage = (EPackage) mapping.getEPackage();
            if (ePackage != null) {
                type.setMapping(mapping);
                EClassifier eClassifier = ePackage.getEClassifier(classifierName);
                type.setEClassifier(eClassifier); // May be null
            } else {
                // A metamodel registered in 'metamodelsByName' must have a known EPackage
                throw new IllegalStateException();
            }
        }
    }

    /**
     * Create the list of supported scalar types, currently only "string".
     * 
     * @param config
     *            The config
     */
    private static void createSupportedScalarTypes(TemplateConfig config) {
        // The only type actually supported is 'string'
        addScalarType(config, M2DocCustomProperties.STRING_TYPE);
    }

    /**
     * Add a new scalar type with the given name into the given configuration.
     * 
     * @param config
     *            The config
     * @param name
     *            The name of the new type to create
     * @return The created scalar type
     */
    private static ScalarType addScalarType(TemplateConfig config, String name) {
        ScalarType type = TplconfFactory.eINSTANCE.createScalarType();
        type.setName(name);
        config.getTypesByName().put(name, type);
        return type;
    }

    /**
     * Save the given configuration in the custom properties of the given docx file.
     * 
     * @param config
     *            The configuration to save, must not be <code>null</code>
     * @param templateFile
     *            The docx template file, must be a valid docx file
     * @throws IOException
     *             If an I/O problem occurs while reading or writing the docx file.
     */
    public static void save(TemplateConfig config, IFile templateFile) throws IOException {
        XWPFDocument xwpfDocument = POIServices.getInstance().getXWPFDocument(templateFile);

        store(config, xwpfDocument);

        POIServices.getInstance().saveFile(xwpfDocument, templateFile.getRawLocation().toOSString());
    }

    /**
     * Store the given template configuration into the given document.
     * 
     * @param config
     *            The configuration to store
     * @param xwpfDocument
     *            The document the custom properties of which will be used to store the configuration
     */
    public static void store(TemplateConfig config, XWPFDocument xwpfDocument) {
        // Metamodel URIs
        StringBuilder b = new StringBuilder(1000);
        for (EPackageMapping mm : config.getMappings()) {
            b.append(M2DocCustomProperties.SERVICETOKEN_SEPARATOR).append(mm.getUri());
        }
        b.deleteCharAt(0);
        CustomProperties customProperties = xwpfDocument.getProperties().getCustomProperties();
        if (customProperties.contains(M2DocCustomProperties.URI_PROPERTY_PREFIX)) {
            CTProperty uriProp = customProperties.getProperty(M2DocCustomProperties.URI_PROPERTY_PREFIX);
            uriProp.setLpwstr(b.toString());
        } else {
            customProperties.addProperty(M2DocCustomProperties.URI_PROPERTY_PREFIX, b.toString());
        }

        List<Integer> indicesToRemove = new ArrayList<Integer>();
        // Delete former variables
        List<CTProperty> propertyList = customProperties.getUnderlyingProperties().getPropertyList();
        for (int i = 0; i < propertyList.size(); i++) {
            CTProperty prop = propertyList.get(i);
            if (prop.getName() != null && prop.getName().startsWith(M2DocCustomProperties.VAR_PROPERTY_PREFIX + ":")) {
                indicesToRemove.add(i);
            }
        }
        for (Integer indexToRemove : Lists.reverse(indicesToRemove)) {
            customProperties.getUnderlyingProperties().removeProperty(indexToRemove.intValue());
        }

        // Variables
        for (TemplateVariable var : config.getVariables()) {
            String name = var.getName();
            String typeName = var.getTypeName();
            String key = M2DocCustomProperties.VAR_PROPERTY_PREFIX + ":" + name;
            if (customProperties.contains(key)) {
                customProperties.getProperty(key).setLpwstr(typeName);
            } else {
                customProperties.addProperty(key, typeName);
            }
        }
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

    /**
     * Check that a given name is a valid type name, i.e. either "string" or a complex name made of a prefix that represents a package name
     * and a suffix that represents a classifier name, separated by "::". For example, "ecore::EClass" is valid, and so is
     * "çà:StràngeLéttèrs", but "e-core::EClass", "ecore::", "ecore:", "ecore::E-Class" are not.
     * 
     * @param typeName
     *            The type name to check
     * @return <code>true</code> if the typeName is not <code>null</code>, and is either "string" or a valid type name, made of two valid
     *         (as EMF means it) ENamedElement
     *         names separated by "::".
     */
    // CHECKSTYLE:OFF
    public static boolean isValidTypeName(String typeName) {
        if (typeName == null) {
            return false;
        }
        if ("string".equals(typeName)) {
            return true;
        }
        int index = typeName.indexOf(METAMODEL_TYPE_SEPARATOR);
        if (index <= 0) {
            return false;
        }
        String packageName = typeName.substring(0, index);
        EcoreValidator v = new EcoreValidator();
        EClass placeHolder = EcoreFactory.eINSTANCE.createEClass();
        placeHolder.setName(packageName);
        if (!v.validateENamedElement_WellFormedName(placeHolder, null, null)) {
            return false;
        }
        String className = typeName.substring(index + 2);
        placeHolder.setName(className);
        if (className.length() == 0 || !v.validateENamedElement_WellFormedName(placeHolder, null, null)) {
            return false;
        }
        return true;
    }
    // @CHECKSTYLE:ON

    /**
     * Check that the given type name is a (syntactically) valid classifier type name, i.e with a prfix followed by "::" followed by a
     * suffix.
     * 
     * @param typeName
     *            The type name
     * @return <code>true</code> if the given type name is a syntactically valid classifier type name. This does NOT mean that the
     *         classifier is actually recognized!
     */
    public static boolean isValidClassifierTypeName(String typeName) {
        return isValidTypeName(typeName) && typeName.indexOf(METAMODEL_TYPE_SEPARATOR) > 0;
    }
}
