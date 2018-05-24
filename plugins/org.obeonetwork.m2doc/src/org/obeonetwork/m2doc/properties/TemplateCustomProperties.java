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

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenFactory;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.UnbufferedCharStream;
import org.antlr.v4.runtime.UnbufferedTokenStream;
import org.apache.poi.POIXMLProperties.CustomProperties;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.ast.AstPackage;
import org.eclipse.acceleo.query.ast.Binding;
import org.eclipse.acceleo.query.ast.ErrorTypeLiteral;
import org.eclipse.acceleo.query.ast.Lambda;
import org.eclipse.acceleo.query.ast.VarRef;
import org.eclipse.acceleo.query.ast.VariableDeclaration;
import org.eclipse.acceleo.query.parser.AstBuilderListener;
import org.eclipse.acceleo.query.parser.AstValidator;
import org.eclipse.acceleo.query.parser.QueryLexer;
import org.eclipse.acceleo.query.parser.QueryParser;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IValidationResult;
import org.eclipse.acceleo.query.runtime.Query;
import org.eclipse.acceleo.query.validation.type.EClassifierLiteralType;
import org.eclipse.acceleo.query.validation.type.EClassifierSetLiteralType;
import org.eclipse.acceleo.query.validation.type.EClassifierType;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.parser.M2DocParser;
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.Bookmark;
import org.obeonetwork.m2doc.template.Conditional;
import org.obeonetwork.m2doc.template.Let;
import org.obeonetwork.m2doc.template.Link;
import org.obeonetwork.m2doc.template.Repetition;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.util.AQL56Compatibility;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty;

/**
 * Template properties grouping the variable and service declaration in templates.
 * 
 * @author Romain Guider
 */
@SuppressWarnings("restriction")
public class TemplateCustomProperties {

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
     * Prefix of the service import custom properties.
     */
    public static final String SERVICE_IMPORT_PROPERTY_PREFIX = "m:import:";

    /**
     * Prefix of the service import custom properties length.
     */
    public static final int SERVICE_IMPORT_PROPERTY_PREFIX_LENGTH = SERVICE_IMPORT_PROPERTY_PREFIX.length();

    /**
     * A map that associates variables declared in the template with their intended type.
     */
    private final Map<String, String> variables = new LinkedHashMap<>();

    /**
     * The list of nsURIs declared in the template.
     */
    private final List<String> nsURIs = new ArrayList<>();

    /**
     * The {@link Map} of service {@link Class#getName() class names} to bundle name (needed for Eclipse workspace mode).
     */
    private final Map<String, String> serviceClasses = new LinkedHashMap<>();

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
                final String bundleName = property.getLpwstr();
                serviceClasses.put(serviceClasse, bundleName);
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
        Map<String, String> tmpServiceImports = new LinkedHashMap<>(serviceClasses);
        Map<String, String> tmpVars = new LinkedHashMap<>(variables);
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
                final String bundleName = tmpServiceImports.remove(serviceClasse);
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

        for (String nsURI : tmpNsURI) {
            props.addProperty(URI_PROPERTY_PREFIX + nsURI, "");
        }
        for (Entry<String, String> entry : tmpServiceImports.entrySet()) {
            props.addProperty(SERVICE_IMPORT_PROPERTY_PREFIX + entry.getKey(), entry.getValue());
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
     * Gets the {@link Map} of service {@link Class#getName() class names} to bundle name (needed for Eclipse workspace mode).
     * 
     * @return the {@link Map} of service {@link Class#getName() class names} to bundle name (needed for Eclipse workspace mode).
     */
    public Map<String, String> getServiceClasses() {
        return serviceClasses;
    }

    /**
     * Configures the given {@link IQueryEnvironment} with {@link #getPackagesURIs() declared EPackages}.
     * 
     * @param queryEnvironment
     *            the {@link IQueryEnvironment} to configure
     * @return the {@link List} of nsURI with no {@link EPackage.Registry#put(String, Object) registered} {@link EPackage}
     */
    public List<String> configureQueryEnvironmentWithResult(IQueryEnvironment queryEnvironment) {
        final List<String> res = new ArrayList<>();

        for (String nsURI : getPackagesURIs()) {
            final EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
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

        final AstResult astResult = parseWhileAqlTypeLiteral(queryEnvironment, type);
        final IValidationResult validationResult = validator.validate(Collections.<String, Set<IType>> emptyMap(),
                astResult);
        // TODO replace with AstValidator.getDeclarationTypes()
        final Set<IType> variableTypes = validationResult.getPossibleTypes(astResult.getAst());
        for (IType iType : variableTypes) {
            if (iType instanceof EClassifierLiteralType) {
                res.add(new EClassifierType(queryEnvironment, ((EClassifierLiteralType) iType).getType()));
            } else if (iType instanceof EClassifierSetLiteralType) {
                for (EClassifier eClassifier : ((EClassifierSetLiteralType) iType).getEClassifiers()) {
                    res.add(new EClassifierType(queryEnvironment, eClassifier));
                }
            } else {
                res.add(iType);
            }
        }

        return res;
    }

    /**
     * Parses while matching an AQL expression.
     * 
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param type
     *            the type to parse
     * @return the corresponding {@link AstResult}
     */
    private AstResult parseWhileAqlTypeLiteral(IReadOnlyQueryEnvironment queryEnvironment, String type) {
        final IQueryBuilderEngine.AstResult result;

        if (type != null && type.length() > 0) {
            AstBuilderListener astBuilder = AQL56Compatibility
                    .createAstBuilderListener((IQueryEnvironment) queryEnvironment);
            CharStream input = new UnbufferedCharStream(new StringReader(type), type.length());
            QueryLexer lexer = new QueryLexer(input);
            lexer.setTokenFactory(new CommonTokenFactory(true));
            lexer.removeErrorListeners();
            lexer.addErrorListener(astBuilder.getErrorListener());
            TokenStream tokens = new UnbufferedTokenStream<CommonToken>(lexer);
            QueryParser parser = new QueryParser(tokens);
            parser.addParseListener(astBuilder);
            parser.removeErrorListeners();
            parser.addErrorListener(astBuilder.getErrorListener());
            // parser.setTrace(true);
            parser.typeLiteral();
            result = astBuilder.getAstResult();
        } else {
            ErrorTypeLiteral errorTypeLiteral = (ErrorTypeLiteral) EcoreUtil
                    .create(AstPackage.eINSTANCE.getErrorTypeLiteral());
            List<org.eclipse.acceleo.query.ast.Error> errors = new ArrayList<>(1);
            errors.add(errorTypeLiteral);
            final Map<Object, Integer> positions = new HashMap<>();
            if (type != null) {
                positions.put(errorTypeLiteral, Integer.valueOf(0));
            }
            final BasicDiagnostic diagnostic = new BasicDiagnostic();
            diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, AstBuilderListener.PLUGIN_ID, 0, "null or empty type.",
                    new Object[] {errorTypeLiteral }));
            result = new AstResult(errorTypeLiteral, positions, positions, errors, diagnostic);
        }

        return result;
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
     * Gets the {@link List} of missing variable names.
     * 
     * @return the {@link List} of missing variable names
     */
    public List<String> getMissingVariables() {
        final List<String> res = new ArrayList<>();

        final M2DocParser parser = new M2DocParser(document, Query.newEnvironment());
        try {
            final List<Template> templates = new ArrayList<Template>();
            final Block block = parser.parseBlock(templates);
            final Set<String> missing = new LinkedHashSet<>();
            walkForNeededVariables(new ArrayList<>(getVariables().keySet()), missing, block);
            res.addAll(missing);
        } catch (DocumentParserException e) {
            // can't parse then nothing is missing
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
     * @param eObj
     *            the current {@link EObject} to walk
     */
    private void walkForNeededVariables(List<String> declarations, Set<String> missing, EObject eObj) {
        if (eObj instanceof VarRef) {
            if (!declarations.contains(((VarRef) eObj).getVariableName())) {
                missing.add(((VarRef) eObj).getVariableName());
            }
        } else if (eObj instanceof Repetition) {
            walkForNeededVariables(declarations, missing, ((Repetition) eObj).getQuery().getAst());
            declarations.add(((Repetition) eObj).getIterationVar());
            walkForNeededVariables(declarations, missing, ((Repetition) eObj).getBody());
            declarations.remove(((Repetition) eObj).getIterationVar());
        } else if (eObj instanceof Let) {
            walkForNeededVariables(declarations, missing, ((Let) eObj).getValue().getAst());
            declarations.add(((Let) eObj).getName());
            walkForNeededVariables(declarations, missing, ((Let) eObj).getBody());
            declarations.remove(((Let) eObj).getName());
        } else if (eObj instanceof Conditional) {
            walkForNeededVariables(declarations, missing, ((Conditional) eObj).getCondition().getAst());
            walkForNeededVariables(declarations, missing, ((Conditional) eObj).getThen());
            if (((Conditional) eObj).getElse() != null) {
                walkForNeededVariables(declarations, missing, ((Conditional) eObj).getElse());
            }
        } else if (eObj instanceof Link) {
            walkForNeededVariables(declarations, missing, ((Link) eObj).getName().getAst());
            walkForNeededVariables(declarations, missing, ((Link) eObj).getText().getAst());
        } else if (eObj instanceof Bookmark) {
            walkForNeededVariables(declarations, missing, ((Bookmark) eObj).getName().getAst());
            walkForNeededVariables(declarations, missing, ((Bookmark) eObj).getBody());
        } else if (eObj instanceof org.obeonetwork.m2doc.template.Query) {
            walkForNeededVariables(declarations, missing,
                    ((org.obeonetwork.m2doc.template.Query) eObj).getQuery().getAst());
        } else if (eObj instanceof Lambda) {
            final List<String> lambdaDeclartations = new ArrayList<>();
            for (VariableDeclaration parameter : ((Lambda) eObj).getParameters()) {
                lambdaDeclartations.add(parameter.getName());
            }
            declarations.addAll(lambdaDeclartations);
            walkForNeededVariables(declarations, missing, ((Lambda) eObj).getExpression());
            declarations.removeAll(lambdaDeclartations);
        } else if (eObj instanceof org.eclipse.acceleo.query.ast.Let) {
            final List<String> letDeclartations = new ArrayList<>();
            for (Binding binding : ((org.eclipse.acceleo.query.ast.Let) eObj).getBindings()) {
                letDeclartations.add(binding.getName());
                walkForNeededVariables(declarations, missing, binding.getValue());
            }
            declarations.addAll(letDeclartations);
            walkForNeededVariables(declarations, missing, ((org.eclipse.acceleo.query.ast.Let) eObj).getBody());
            declarations.removeAll(letDeclartations);
        } else {
            for (EObject child : eObj.eContents()) {
                walkForNeededVariables(declarations, missing, child);
            }
        }
    }

}
