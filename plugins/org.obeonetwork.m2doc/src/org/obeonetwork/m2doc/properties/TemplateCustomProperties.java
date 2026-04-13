/*******************************************************************************
 *  Copyright (c) 2016, 2026 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.properties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.poi.ooxml.POIXMLProperties.CustomProperties;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.eclipse.acceleo.query.AQLUtils;
import org.eclipse.acceleo.query.ast.Binding;
import org.eclipse.acceleo.query.ast.Lambda;
import org.eclipse.acceleo.query.ast.VarRef;
import org.eclipse.acceleo.query.ast.VariableDeclaration;
import org.eclipse.acceleo.query.parser.AstResult;
import org.eclipse.acceleo.query.parser.AstValidator;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IValidationResult;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.parser.M2DocParser;
import org.obeonetwork.m2doc.parser.TokenType;
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.Bookmark;
import org.obeonetwork.m2doc.template.Conditional;
import org.obeonetwork.m2doc.template.Let;
import org.obeonetwork.m2doc.template.Link;
import org.obeonetwork.m2doc.template.Repetition;
import org.obeonetwork.m2doc.template.Template;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty;

/**
 * Template properties grouping the variable and service declaration in templates.
 * 
 * @author Romain Guider
 */
public class TemplateCustomProperties {

    /**
     * An {@link Entry} to store the extended qualified name and its bundle name.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private static class ExtendsEntry implements Entry<String, String> {

        /**
         * The extended qualified name.
         */
        private String qualifiedName;

        /**
         * The bundle name.
         */
        private String bundleName;

        /**
         * Constructor.
         * 
         * @param qualifiedName
         *            the qualified name
         * @param bundleName
         *            the bundle name
         */
        ExtendsEntry(String qualifiedName, String bundleName) {
            this.qualifiedName = qualifiedName;
            this.bundleName = bundleName;
        }

        @Override
        public String getKey() {
            return qualifiedName;
        }

        /**
         * Sets the qualified name.
         * 
         * @param key
         *            the qualified name
         */
        public void setKey(String key) {
            qualifiedName = key;
        }

        @Override
        public String getValue() {
            return bundleName;
        }

        @Override
        public String setValue(String value) {
            final String oldBundleName = bundleName;

            bundleName = value;

            return oldBundleName;
        }

    }

    /**
     * id of the String type to use in variable declaration properties.
     */
    public static final String STRING_TYPE = "String";

    /**
     * id of the integer type to use in variable declaration properties.
     */
    public static final String INTEGER_TYPE = "Integer";

    /**
     * id of the real type to use in variable declaration properties.
     */
    public static final String REAL_TYPE = "Real";

    /**
     * id of the boolean type to use in variable declaration properties.
     */
    public static final String BOOLEAN_TYPE = "Boolean";

    /**
     * M2Doc version custom properties.
     */
    public static final String M2DOC_VERSION_PROPERTY = "m:M2DocVersion";

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
     * Prefix of the import custom properties.
     */
    public static final String IMPORT_PROPERTY_PREFIX = "m:import:";

    /**
     * Prefix of the import custom properties length.
     */
    public static final int IMPORT_PROPERTY_PREFIX_LENGTH = IMPORT_PROPERTY_PREFIX.length();

    /**
     * The extend property.
     */
    public static final String EXTEND_PROPERTY_PREFIX = "m:extend:";

    /**
     * Prefix of the extend custom properties length.
     */
    public static final int EXTEND_PROPERTY_PREFIX_LENGTH = EXTEND_PROPERTY_PREFIX.length();

    /**
     * The validation name {@link Pattern}.
     */
    private static final Pattern VARIABLE_NAME_PATTERN = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*");

    /**
     * The M2Doc version.
     */
    private String m2DocVersion;

    /**
     * A map that associates variables declared in the template with their intended type.
     */
    private final Map<String, String> variables = new LinkedHashMap<>();

    /**
     * The {@link Set} of {@link EPackage#getNsURI() nsURI} imported in the template.
     */
    private final Set<String> nsURIs = new LinkedHashSet<>();

    /**
     * The {@link Map} of service qualified name to bundle name (needed for Eclipse workspace mode).
     */
    private final Map<String, String> imports = new LinkedHashMap<>();

    /**
     * The extended qualified name and its bundle name if any, <code>null</code> otherwise.
     */
    private ExtendsEntry extend;

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

            if (M2DOC_VERSION_PROPERTY.equals(propertyName) && property.getLpwstr() != null) {
                m2DocVersion = property.getLpwstr().trim();
                continue;
            }

            final String extendQualifiedName = getExtendQualifiedName(propertyName);
            if (extendQualifiedName != null) {
                final String bundleName = property.getLpwstr();
                if (!bundleName.isEmpty()) {
                    extend = new ExtendsEntry(extendQualifiedName, bundleName);
                } else {
                    extend = new ExtendsEntry(extendQualifiedName, null);
                }
                continue;
            }

            final String nsURI = getNsURI(propertyName);
            if (nsURI != null) {
                nsURIs.add(nsURI);
                continue;
            }

            final String importQualifiedName = getImportQualifiedName(propertyName);
            if (importQualifiedName != null) {
                final String bundleName = property.getLpwstr();
                imports.put(importQualifiedName, bundleName);
                continue;
            }

            final String variableName = getVariableName(propertyName);
            if (variableName != null && isValidVariableName(variableName)) {
                final String type = property.getLpwstr();
                variables.put(variableName, type);
                continue;
            }
        }
    }

    /**
     * Saves the properties to the document.
     */
    public void save() {
        final CustomProperties props = document.getProperties().getCustomProperties();
        final List<CTProperty> properties = props.getUnderlyingProperties().getPropertyList();
        final List<Integer> indexToDelete = new ArrayList<>();
        int currentIndex = 0;
        List<String> tmpNsURI = new ArrayList<>(nsURIs);
        Map<String, String> tmpServiceImports = new LinkedHashMap<>(imports);
        Map<String, String> tmpVars = new LinkedHashMap<>(variables);
        boolean versionAdded = false;
        boolean extendAdded = false;
        for (CTProperty property : properties) {
            final String propertyName = property.getName();

            if (M2DOC_VERSION_PROPERTY.equals(propertyName)) {
                property.setLpwstr(m2DocVersion);
                versionAdded = true;
            }

            final String extendQualifiedName = getExtendQualifiedName(propertyName);
            if (extendQualifiedName != null) {

                if (extend != null) {
                    property.setName(EXTEND_PROPERTY_PREFIX + extend.getKey());
                    if (extend.getValue() != null) {
                        property.setLpwstr(extend.getValue());
                    } else {
                        property.setLpwstr("");
                    }
                } else {
                    indexToDelete.add(currentIndex);
                }
                currentIndex++;
                extendAdded = true;
                continue;
            }

            final String nsURI = getNsURI(propertyName);
            if (nsURI != null) {
                if (!tmpNsURI.remove(nsURI)) {
                    indexToDelete.add(currentIndex);
                }
                currentIndex++;
                continue;
            }

            final String importQualifiedName = getImportQualifiedName(propertyName);
            if (importQualifiedName != null) {
                final String bundleName = tmpServiceImports.remove(importQualifiedName);
                if (bundleName != null) {
                    property.setLpwstr(bundleName);
                } else {
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

        if (!versionAdded) {
            props.addProperty(M2DOC_VERSION_PROPERTY, m2DocVersion);
        }

        if (!extendAdded && extend != null) {
            if (extend.getValue() != null) {
                props.addProperty(EXTEND_PROPERTY_PREFIX + extend.getKey(), extend.getValue());
            } else {
                props.addProperty(EXTEND_PROPERTY_PREFIX + extend.getKey(), "");
            }
        }

        for (String nsURI : tmpNsURI) {
            props.addProperty(URI_PROPERTY_PREFIX + nsURI, "");
        }
        for (Entry<String, String> entry : tmpServiceImports.entrySet()) {
            props.addProperty(IMPORT_PROPERTY_PREFIX + entry.getKey(), entry.getValue());
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
            res = propertyName.substring(URI_PROPERTY_PREFIX_LENGTH).trim();
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
    private String getImportQualifiedName(String propertyName) {
        final String res;

        if (propertyName.startsWith(IMPORT_PROPERTY_PREFIX) && propertyName.length() > IMPORT_PROPERTY_PREFIX_LENGTH) {
            res = propertyName.substring(IMPORT_PROPERTY_PREFIX_LENGTH).trim();
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
    private String getExtendQualifiedName(String propertyName) {
        final String res;

        if (propertyName.startsWith(EXTEND_PROPERTY_PREFIX) && propertyName.length() > EXTEND_PROPERTY_PREFIX_LENGTH) {
            res = propertyName.substring(EXTEND_PROPERTY_PREFIX_LENGTH).trim();
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
            res = propertyName.substring(VAR_PROPERTY_PREFIX_LENGTH).trim();
        } else {
            res = null;
        }

        return res;
    }

    /**
     * Gets the {@link Set} of imported {@link EPackage#getNsURI() nsURI}.
     * 
     * @return the {@link Set} of imported {@link EPackage#getNsURI() nsURI}
     */
    public Set<String> getPackagesURIs() {
        return nsURIs;
    }

    /**
     * Gets the {@link Map} of declared variable name to variable type.
     * 
     * @return the {@link Map} of declared variable name to variable type.
     */
    public Map<String, String> getVariables() {
        return variables;
    }

    /**
     * Gets the {@link Map} of service qualified name to bundle name (needed for Eclipse workspace mode).
     * 
     * @return the {@link Map} of service qualified name to bundle name (needed for Eclipse workspace mode).
     */
    public Map<String, String> getImports() {
        return imports;
    }

    /**
     * Gets the extended qualified name.
     * 
     * @return the extended qualified name and its bundle name if any, <code>null</code> otherwise
     */
    public Entry<String, String> getExtend() {
        return extend;
    }

    /**
     * Sets extend qualified name.
     * 
     * @param qualifiedName
     *            the new extend qualified name
     * @param bundleName
     *            the new bundle name
     */
    public void setExtend(String qualifiedName, String bundleName) {
        if (qualifiedName != null) {
            extend = new ExtendsEntry(qualifiedName, bundleName);
        } else {
            extend = null;
        }
    }

    /**
     * Configures the given {@link IQueryEnvironment} with {@link #getPackagesURIs() declared EPackages}.
     * 
     * @param queryEnvironment
     *            the {@link IQueryEnvironment} to configure
     * @param ePackageRegistry
     *            the {@link EPackage.Registry} to use to resolve {@link EPackage#getNsURI() nsURI}
     * @return the {@link List} of nsURI with no {@link EPackage.Registry#put(String, Object) registered} {@link EPackage}
     */
    public List<String> configureQueryEnvironmentWithResult(IQueryEnvironment queryEnvironment,
            EPackage.Registry ePackageRegistry) {
        final List<String> res = new ArrayList<>();

        for (String nsURI : getPackagesURIs()) {
            final EPackage ePackage = ePackageRegistry.getEPackage(nsURI);
            if (ePackage != null) {
                queryEnvironment.registerEPackage(ePackage);
            } else {
                res.add(nsURI);
            }
        }

        return res;
    }

    /**
     * Gets the {@link Set} of {@link IType} for each variable declaration.
     * 
     * @param validator
     *            the {@link AstValidator}
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @return the {@link Set} of variable declaration {@link IType}
     */
    public Map<String, Set<IType>> getVariableTypes(AstValidator validator,
            IReadOnlyQueryEnvironment queryEnvironment) {
        final Map<String, Set<IType>> res = new LinkedHashMap<>();

        for (Entry<String, String> entry : getVariables().entrySet()) {
            final Set<IType> types = getVariableTypes(validator, queryEnvironment, entry.getValue());
            res.put(entry.getKey(), types);
        }

        return res;
    }

    /**
     * Gets the {@link Set} of variable declaration {@link IType}.
     * 
     * @param validator
     *            the {@link AstValidator}
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param type
     *            the {@link String} representation of a type
     * @return the {@link Set} of variable declaration {@link IType}
     */
    public Set<IType> getVariableTypes(AstValidator validator, IReadOnlyQueryEnvironment queryEnvironment,
            String type) {
        final Set<IType> res = new LinkedHashSet<>();

        final AstResult astResult = AQLUtils.parseWhileAqlTypeLiteral(type);
        final IValidationResult validationResult = validator.validate(Collections.<String, Set<IType>> emptyMap(),
                astResult);
        final Set<IType> variableTypes = validationResult.getPossibleTypes(astResult.getAst());
        res.addAll(validator.getDeclarationTypes(queryEnvironment, variableTypes));

        return res;
    }

    /**
     * Check that a given name is a valid M2DOC (I.e. AQL) variable name.
     * 
     * @param name
     *            The variable name to check
     * @return <code>true</code> if the given name matches "[a-zA-Z_][a-zA-Z0-9_]*".
     */
    public static boolean isValidVariableName(String name) {
        return name != null && VARIABLE_NAME_PATTERN.matcher(name).matches();
    }

    /**
     * Gets the {@link List} of missing variable names.
     * 
     * @return the {@link List} of missing variable names
     */
    public List<String> getMissingVariables() {
        final List<String> res = new ArrayList<>();

        final M2DocParser parser = new M2DocParser(document);
        try {
            final List<Template> templates = new ArrayList<>();
            final Block block = parser.parseBlock(templates);
            final Set<String> missing = new LinkedHashSet<>();
            final Set<String> used = new LinkedHashSet<>();
            walkForNeededVariables(new ArrayList<>(getVariables().keySet()), missing, used, block);
            for (XWPFFooter footer : document.getFooterList()) {
                final M2DocParser footerParser = new M2DocParser(footer);
                final Block footerBlock = footerParser.parseBlock(null, TokenType.EOF);
                walkForNeededVariables(new ArrayList<>(getVariables().keySet()), missing, used, footerBlock);
            }
            for (XWPFHeader header : document.getHeaderList()) {
                final M2DocParser headerParser = new M2DocParser(header);
                final Block headerBlock = headerParser.parseBlock(null, TokenType.EOF);
                walkForNeededVariables(new ArrayList<>(getVariables().keySet()), missing, used, headerBlock);
            }
            res.addAll(missing);
        } catch (DocumentParserException e) {
            // can't parse then nothing is missing
        }

        return res;
    }

    /**
     * Gets the {@link List} of unused variable names.
     * 
     * @return the {@link List} of unused variable names
     */
    public List<String> getUnusedDeclarations() {
        final List<String> res = new ArrayList<>(getVariables().keySet());

        final M2DocParser parser = new M2DocParser(document);
        try {
            final List<Template> templates = new ArrayList<>();
            final Block block = parser.parseBlock(templates);
            final Set<String> missing = new LinkedHashSet<>();
            final Set<String> used = new LinkedHashSet<>();
            walkForNeededVariables(new ArrayList<>(getVariables().keySet()), missing, used, block);
            for (XWPFFooter footer : document.getFooterList()) {
                final M2DocParser footerParser = new M2DocParser(footer);
                final Block footerBlock = footerParser.parseBlock(null, TokenType.EOF);
                walkForNeededVariables(new ArrayList<>(getVariables().keySet()), missing, used, footerBlock);
            }
            for (XWPFHeader header : document.getHeaderList()) {
                final M2DocParser headerParser = new M2DocParser(header);
                final Block headerBlock = headerParser.parseBlock(null, TokenType.EOF);
                walkForNeededVariables(new ArrayList<>(getVariables().keySet()), missing, used, headerBlock);
            }
            res.removeAll(used);
        } catch (DocumentParserException e) {
            // can't parse then nothing is used
        }

        return res;
    }

    /**
     * Walks the given {@link EObject} for missing variables.
     * 
     * @param declarations
     *            the known declarations
     * @param missing
     *            the missing declarations
     * @param used
     *            the used declarations
     * @param eObj
     *            the current {@link EObject} to walk
     */
    private void walkForNeededVariables(List<String> declarations, Set<String> missing, Set<String> used,
            EObject eObj) {
        if (eObj instanceof VarRef) {
            final String variableName = ((VarRef) eObj).getVariableName();
            used.add(variableName);
            if (!declarations.contains(variableName)) {
                missing.add(variableName);
            }
        } else if (eObj instanceof Repetition) {
            walkForNeededVariables(declarations, missing, used, ((Repetition) eObj).getQuery().getAst());
            declarations.add(((Repetition) eObj).getIterationVar());
            walkForNeededVariables(declarations, missing, used, ((Repetition) eObj).getBody());
            declarations.remove(((Repetition) eObj).getIterationVar());
        } else if (eObj instanceof Let) {
            walkForNeededVariables(declarations, missing, used, ((Let) eObj).getValue().getAst());
            declarations.add(((Let) eObj).getName());
            walkForNeededVariables(declarations, missing, used, ((Let) eObj).getBody());
            declarations.remove(((Let) eObj).getName());
        } else if (eObj instanceof Conditional) {
            walkForNeededVariables(declarations, missing, used, ((Conditional) eObj).getCondition().getAst());
            walkForNeededVariables(declarations, missing, used, ((Conditional) eObj).getThen());
            if (((Conditional) eObj).getElse() != null) {
                walkForNeededVariables(declarations, missing, used, ((Conditional) eObj).getElse());
            }
        } else if (eObj instanceof Link) {
            walkForNeededVariables(declarations, missing, used, ((Link) eObj).getName().getAst());
            walkForNeededVariables(declarations, missing, used, ((Link) eObj).getText().getAst());
        } else if (eObj instanceof Bookmark) {
            walkForNeededVariables(declarations, missing, used, ((Bookmark) eObj).getName().getAst());
            walkForNeededVariables(declarations, missing, used, ((Bookmark) eObj).getBody());
        } else if (eObj instanceof org.obeonetwork.m2doc.template.Query) {
            walkForNeededVariables(declarations, missing, used,
                    ((org.obeonetwork.m2doc.template.Query) eObj).getQuery().getAst());
        } else if (eObj instanceof Lambda) {
            final List<String> lambdaDeclartations = new ArrayList<>();
            for (VariableDeclaration parameter : ((Lambda) eObj).getParameters()) {
                lambdaDeclartations.add(parameter.getName());
            }
            declarations.addAll(lambdaDeclartations);
            walkForNeededVariables(declarations, missing, used, ((Lambda) eObj).getExpression());
            declarations.removeAll(lambdaDeclartations);
        } else if (eObj instanceof org.eclipse.acceleo.query.ast.Let) {
            final List<String> letDeclartations = new ArrayList<>();
            for (Binding binding : ((org.eclipse.acceleo.query.ast.Let) eObj).getBindings()) {
                letDeclartations.add(binding.getName());
                walkForNeededVariables(declarations, missing, used, binding.getValue());
            }
            declarations.addAll(letDeclartations);
            walkForNeededVariables(declarations, missing, used, ((org.eclipse.acceleo.query.ast.Let) eObj).getBody());
            declarations.removeAll(letDeclartations);
        } else {
            for (EObject child : eObj.eContents()) {
                walkForNeededVariables(declarations, missing, used, child);
            }
        }
    }

    /**
     * Sets the M2Doc version.
     * 
     * @param m2DocVersion
     *            the M2Doc version to set
     */
    public void setM2DocVersion(String m2DocVersion) {
        this.m2DocVersion = m2DocVersion;
    }

    /**
     * Gets the M2Doc version.
     * 
     * @return the M2Doc version
     */
    public String getM2DocVersion() {
        return m2DocVersion;
    }

}
