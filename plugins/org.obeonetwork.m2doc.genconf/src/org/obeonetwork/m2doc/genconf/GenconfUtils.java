/*******************************************************************************
 *  Copyright (c) 2017, 2023 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.genconf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.acceleo.query.parser.AstValidator;
import org.eclipse.acceleo.query.runtime.CrossReferenceProvider;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IRootEObjectProvider;
import org.eclipse.acceleo.query.runtime.Query;
import org.eclipse.acceleo.query.runtime.impl.ValidationServices;
import org.eclipse.acceleo.query.validation.type.ClassType;
import org.eclipse.acceleo.query.validation.type.EClassifierType;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.ide.M2DocPlugin;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.util.IClassProvider;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Utility class for {@link Generation}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
@SuppressWarnings("restriction")
public final class GenconfUtils {

    static {
        // make sure org.obeonetwork.m2doc.ide is started
        M2DocPlugin.INSTANCE.getBaseURL();
    }

    /**
     * The genconf extension file.
     */
    public static final String GENCONF_EXTENSION_FILE = "genconf";

    /**
     * The genconf {@link URI} option.
     */
    public static final String GENCONF_URI_OPTION = "GenconfURI";

    /**
     * Constructor.
     */
    private GenconfUtils() {
    }

    /**
     * Gets the {@link Map} of options from the given {@link Generation}.
     * 
     * @param generation
     *            the {@link Generation}
     * @return the {@link Map} of options from the given {@link Generation}
     */
    public static Map<String, String> getOptions(Generation generation) {
        final Map<String, String> res = new LinkedHashMap<String, String>();

        final Resource eResource = generation.eResource();
        if (eResource != null && eResource.getURI() != null) {
            res.put(GENCONF_URI_OPTION, eResource.getURI().toString());
        }
        if (generation.getTemplateFileName() != null) {
            res.put(M2DocUtils.TEMPLATE_URI_OPTION,
                    getResolvedURI(generation, URI.createURI(generation.getTemplateFileName(), false)).toString());
        }
        if (generation.getResultFileName() != null) {
            res.put(M2DocUtils.RESULT_URI_OPTION,
                    getResolvedURI(generation, URI.createURI(generation.getResultFileName(), false)).toString());
        }
        if (generation.getValidationFileName() != null) {
            res.put(M2DocUtils.VALIDATION_URI_OPTION,
                    getResolvedURI(generation, URI.createURI(generation.getValidationFileName(), false)).toString());
        }
        for (Option option : generation.getOptions()) {
            res.put(option.getName(), option.getValue());
        }

        return res;
    }

    /**
     * Initializes options for the given {@link Generation}.
     * 
     * @param generation
     *            the {@link Generation}
     */
    public static void initializeOptions(Generation generation) {
        final Map<String, String> options = getOptions(generation);

        final Map<String, String> initializedOptions = M2DocUtils.getInitializedOptions(options);
        for (Option option : generation.getOptions()) {
            if (initializedOptions.containsKey(option.getName())) {
                final String initialValue = initializedOptions.remove(option.getName());
                if (option.getValue() == null) {
                    option.setValue(initialValue);
                }
            }
        }
        for (Entry<String, String> entry : initializedOptions.entrySet()) {
            final Option option = GenconfPackage.eINSTANCE.getGenconfFactory().createOption();
            option.setName(entry.getKey());
            option.setValue(entry.getValue());
            generation.getOptions().add(option);
        }
    }

    /**
     * Gets the initialized {@link IQueryEnvironment} for the given {@link Generation}.
     * 
     * @param resourceSetForModels
     *            the {@link ResourceSet} for model elements
     * @param generation
     *            the {@link Generation}
     * @return the initialized {@link IQueryEnvironment} for the given {@link Generation}
     */
    public static IQueryEnvironment getQueryEnvironment(ResourceSet resourceSetForModels, Generation generation) {
        final URI templateURI;
        templateURI = getResolvedURI(generation, URI.createURI(generation.getTemplateFileName(), false));

        final Map<String, String> options = getOptions(generation);
        final IQueryEnvironment queryEnvironment = M2DocUtils.getQueryEnvironment(resourceSetForModels, templateURI,
                options);

        return queryEnvironment;
    }

    /**
     * Gets the initialized {@link IQueryEnvironment} for the given {@link Generation}.
     * 
     * @param resourceSetForModels
     *            the {@link ResourceSet} for model elements
     * @param crossReferenceProvider
     *            the {@link CrossReferenceProvider} used for eInverse() service
     * @param rootProvider
     *            the {@link IRootEObjectProvider} used for the allInstances() service
     * @param generation
     *            the {@link Generation}
     * @return the initialized {@link IQueryEnvironment} for the given {@link Generation}
     */
    public static IQueryEnvironment getQueryEnvironment(ResourceSet resourceSetForModels,
            CrossReferenceProvider crossReferenceProvider, IRootEObjectProvider rootProvider, Generation generation) {
        final URI templateURI;
        templateURI = getResolvedURI(generation, URI.createURI(generation.getTemplateFileName(), false));

        final Map<String, String> options = getOptions(generation);
        final IQueryEnvironment queryEnvironment = M2DocUtils.getQueryEnvironment(resourceSetForModels,
                crossReferenceProvider, rootProvider, templateURI, options);

        return queryEnvironment;
    }

    /**
     * Gets the variables {@link Map} from the given {@link Generation} and {@link ResourceSet}.
     * 
     * @param generation
     *            the {@link Generation} holding the {@link Generation#getDefinitions() definitions}
     * @param resourceSetForModels
     *            the {@link ResourceSet} used to load the model instances
     * @return the created variables {@link Map} from the given {@link Generation} and {@link ResourceSet}
     */
    public static Map<String, Object> getVariables(Generation generation, ResourceSet resourceSetForModels) {
        Map<String, Object> result = new HashMap<String, Object>();
        for (Definition def : generation.getDefinitions()) {
            if (def instanceof ModelDefinition) {
                final EObject value = getEObjectValue(resourceSetForModels, ((ModelDefinition) def).getValue());
                result.put(def.getKey(), value);
            } else if (def instanceof ModelSequenceDefinition) {
                final List<EObject> value = new ArrayList<>();
                for (EObject eObj : ((ModelSequenceDefinition) def).getValue()) {
                    value.add(getEObjectValue(resourceSetForModels, eObj));
                }
            } else if (def instanceof ModelOrderedSetDefinition) {
                final List<EObject> value = new ArrayList<>();
                for (EObject eObj : ((ModelOrderedSetDefinition) def).getValue()) {
                    value.add(getEObjectValue(resourceSetForModels, eObj));
                }
            } else if (def instanceof StringDefinition) {
                result.put(def.getKey(), ((StringDefinition) def).getValue());
            } else if (def instanceof StringSequenceDefinition) {
                final List<String> value = new ArrayList<>(((StringSequenceDefinition) def).getValue());
                result.put(def.getKey(), value);
            } else if (def instanceof StringOrderedSetDefinition) {
                final Set<String> value = new LinkedHashSet<>(((StringOrderedSetDefinition) def).getValue());
                result.put(def.getKey(), value);
            } else if (def instanceof IntegerDefinition) {
                result.put(def.getKey(), ((IntegerDefinition) def).getValue());
            } else if (def instanceof IntegerSequenceDefinition) {
                final List<Integer> value = new ArrayList<>(((IntegerSequenceDefinition) def).getValue());
                result.put(def.getKey(), value);
            } else if (def instanceof IntegerOrderedSetDefinition) {
                final Set<Integer> value = new LinkedHashSet<>(((IntegerOrderedSetDefinition) def).getValue());
                result.put(def.getKey(), value);
            } else if (def instanceof RealDefinition) {
                result.put(def.getKey(), ((RealDefinition) def).getValue());
            } else if (def instanceof RealSequenceDefinition) {
                final List<Double> value = new ArrayList<>(((RealSequenceDefinition) def).getValue());
                result.put(def.getKey(), value);
            } else if (def instanceof RealOrderedSetDefinition) {
                final Set<Double> value = new LinkedHashSet<>(((RealOrderedSetDefinition) def).getValue());
                result.put(def.getKey(), value);
            } else if (def instanceof BooleanDefinition) {
                result.put(def.getKey(), ((BooleanDefinition) def).isValue());
            } else if (def instanceof BooleanSequenceDefinition) {
                final List<Boolean> value = new ArrayList<>(((BooleanSequenceDefinition) def).getValue());
                result.put(def.getKey(), value);
            } else if (def instanceof BooleanOrderedSetDefinition) {
                final Set<Boolean> value = new LinkedHashSet<>(((BooleanOrderedSetDefinition) def).getValue());
                result.put(def.getKey(), value);
            } else {
                throw new UnsupportedOperationException();
            }
        }
        return result;
    }

    /**
     * Gets the {@link EObject} value from the given {@link EObject} original value in the given {@link ResourceSet} for models.
     * 
     * @param resourceSetForModels
     *            the {@link ResourceSet}
     * @param originalValue
     *            the {@link EObject}
     * @return the {@link EObject} value from the given {@link EObject} original value in the given {@link ResourceSet} for models
     */
    private static EObject getEObjectValue(ResourceSet resourceSetForModels, EObject originalValue) {
        EObject val = null;
        if (originalValue != null) {
            URI uri = EcoreUtil.getURI(originalValue);
            try {
                val = resourceSetForModels.getEObject(uri, true);
            } catch (WrappedException e) {
                // The resource could not be loaded, in that case the value is reset to a proxy with the same uri.
                if (originalValue != null) {
                    InternalEObject eobj = (InternalEObject) EcoreUtil.create(originalValue.eClass());
                    eobj.eSetProxyURI(uri);
                    val = eobj;
                }
            }
        }
        return val;
    }

    /**
     * Gets the {@link List} of old {@link Definition} delta between the given {@link Generation} and the given
     * {@link TemplateCustomProperties}. {@link Definition} that can be removed from the {@link Generation}.
     * 
     * @param generation
     *            the {@link Generation}
     * @param templateCustomProperties
     *            the {@link TemplateCustomProperties}
     * @return the {@link List} of new {@link Definition}
     */
    public static List<Definition> getOldDefinitions(Generation generation,
            TemplateCustomProperties templateCustomProperties) {
        final List<Definition> oldDefinitions = new ArrayList<Definition>();

        final IQueryEnvironment queryEnvironment = Query.newEnvironment();
        queryEnvironment.registerEPackage(EcorePackage.eINSTANCE);
        queryEnvironment.registerCustomClassMapping(EcorePackage.eINSTANCE.getEStringToStringMapEntry(),
                EStringToStringMapEntryImpl.class);
        templateCustomProperties.configureQueryEnvironmentWithResult(queryEnvironment);
        final AstValidator validator = new AstValidator(new ValidationServices(queryEnvironment));
        final Map<String, Set<IType>> variablesTypes = templateCustomProperties.getVariableTypes(validator,
                queryEnvironment);
        for (Definition definition : generation.getDefinitions()) {
            if (!variablesTypes.containsKey(definition.getKey())
                || !isValidDefinitionForType(queryEnvironment, definition, variablesTypes.get(definition.getKey()))) {
                oldDefinitions.add(definition);
            }
        }

        return oldDefinitions;
    }

    /**
     * Gets the {@link List} of new {@link Definition} delta between the given {@link Generation} and the given
     * {@link TemplateCustomProperties}. {@link Definition} that can be added to the {@link Generation}.
     * 
     * @param generation
     *            the {@link Generation}
     * @param templateCustomProperties
     *            the {@link TemplateCustomProperties}
     * @return the {@link List} of new {@link Definition}
     */
    public static List<Definition> getNewDefinitions(Generation generation,
            TemplateCustomProperties templateCustomProperties) {
        final IQueryEnvironment queryEnvironment = Query.newEnvironment();
        queryEnvironment.registerEPackage(EcorePackage.eINSTANCE);
        queryEnvironment.registerCustomClassMapping(EcorePackage.eINSTANCE.getEStringToStringMapEntry(),
                EStringToStringMapEntryImpl.class);
        templateCustomProperties.configureQueryEnvironmentWithResult(queryEnvironment);
        final AstValidator validator = new AstValidator(new ValidationServices(queryEnvironment));
        final Map<String, Set<IType>> variablesTypes = templateCustomProperties.getVariableTypes(validator,
                queryEnvironment);
        final Set<String> existingVariables = new HashSet<String>();
        for (Definition definition : generation.getDefinitions()) {
            if (variablesTypes.containsKey(definition.getKey())
                && isValidDefinitionForType(queryEnvironment, definition, variablesTypes.get(definition.getKey()))) {
                existingVariables.add(definition.getKey());
            }
        }

        final List<Definition> newDefinitions = new ArrayList<Definition>();
        for (Entry<String, Set<IType>> entry : variablesTypes.entrySet()) {
            if (!existingVariables.contains(entry.getKey())) {
                final Definition newDefinition = createDefinition(queryEnvironment, entry.getKey(), entry.getValue());
                if (newDefinition != null) {
                    newDefinitions.add(newDefinition);
                }
            }
        }

        return newDefinitions;
    }

    /**
     * Creates the proper definition for the given {@link Set} of {@link IType}.
     * 
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param name
     *            the {@link Definition#getKey() definition name}
     * @param types
     *            the {@link Set} of {@link IType}
     * @return the proper definition for the given {@link Set} of {@link IType} if any created, <code>null</code> otherwise
     */
    private static Definition createDefinition(IReadOnlyQueryEnvironment queryEnvironment, String name,
            Set<IType> types) {
        Definition res = null;

        final IType eObjectType = new ClassType(queryEnvironment, EObject.class);
        final IType eObjectSequenceType = new ForkedSequenceType(queryEnvironment, eObjectType);
        final IType eObjectOrderedSetType = new ForkedSetType(queryEnvironment, eObjectType);
        final IType stringType = new ClassType(queryEnvironment, String.class);
        final IType stringSequenceType = new ForkedSequenceType(queryEnvironment, stringType);
        final IType stringOrderedSetType = new ForkedSetType(queryEnvironment, stringType);
        final IType integerType = new ClassType(queryEnvironment, Integer.class);
        final IType integerSequenceType = new ForkedSequenceType(queryEnvironment, integerType);
        final IType integerOrderedSetType = new ForkedSetType(queryEnvironment, integerType);
        final IType realType = new ClassType(queryEnvironment, Double.class);
        final IType realSequenceType = new ForkedSequenceType(queryEnvironment, realType);
        final IType realOrderedSetType = new ForkedSetType(queryEnvironment, realType);
        final IType booleanType = new ClassType(queryEnvironment, Boolean.class);
        final IType booleanSequenceType = new ForkedSequenceType(queryEnvironment, booleanType);
        final IType booleanOrderedSetType = new ForkedSetType(queryEnvironment, booleanType);
        for (IType type : types) {
            if (eObjectType.isAssignableFrom(type)) {
                res = GenconfPackage.eINSTANCE.getGenconfFactory().createModelDefinition();
                res.setKey(name);
                break;
            } else if (eObjectSequenceType.isAssignableFrom(type)) {
                res = GenconfPackage.eINSTANCE.getGenconfFactory().createModelOrderedSetDefinition();
                res.setKey(name);
                break;
            } else if (eObjectOrderedSetType.isAssignableFrom(type)) {
                res = GenconfPackage.eINSTANCE.getGenconfFactory().createModelOrderedSetDefinition();
                res.setKey(name);
                break;
            } else if (stringType.isAssignableFrom(type)) {
                res = GenconfPackage.eINSTANCE.getGenconfFactory().createStringDefinition();
                res.setKey(name);
                break;
            } else if (stringSequenceType.isAssignableFrom(type)) {
                res = GenconfPackage.eINSTANCE.getGenconfFactory().createStringSequenceDefinition();
                res.setKey(name);
                break;
            } else if (stringOrderedSetType.isAssignableFrom(type)) {
                res = GenconfPackage.eINSTANCE.getGenconfFactory().createStringOrderedSetDefinition();
                res.setKey(name);
                break;
            } else if (integerType.isAssignableFrom(type)) {
                res = GenconfPackage.eINSTANCE.getGenconfFactory().createIntegerDefinition();
                res.setKey(name);
                break;
            } else if (integerSequenceType.isAssignableFrom(type)) {
                res = GenconfPackage.eINSTANCE.getGenconfFactory().createIntegerSequenceDefinition();
                res.setKey(name);
                break;
            } else if (integerOrderedSetType.isAssignableFrom(type)) {
                res = GenconfPackage.eINSTANCE.getGenconfFactory().createIntegerOrderedSetDefinition();
                res.setKey(name);
                break;
            } else if (realType.isAssignableFrom(type)) {
                res = GenconfPackage.eINSTANCE.getGenconfFactory().createRealDefinition();
                res.setKey(name);
                break;
            } else if (realSequenceType.isAssignableFrom(type)) {
                res = GenconfPackage.eINSTANCE.getGenconfFactory().createRealSequenceDefinition();
                res.setKey(name);
                break;
            } else if (realOrderedSetType.isAssignableFrom(type)) {
                res = GenconfPackage.eINSTANCE.getGenconfFactory().createRealOrderedSetDefinition();
                res.setKey(name);
                break;
            } else if (booleanType.isAssignableFrom(type)) {
                res = GenconfPackage.eINSTANCE.getGenconfFactory().createBooleanDefinition();
                res.setKey(name);
                break;
            } else if (booleanSequenceType.isAssignableFrom(type)) {
                res = GenconfPackage.eINSTANCE.getGenconfFactory().createBooleanSequenceDefinition();
                res.setKey(name);
                break;
            } else if (booleanOrderedSetType.isAssignableFrom(type)) {
                res = GenconfPackage.eINSTANCE.getGenconfFactory().createBooleanOrderedSetDefinition();
                res.setKey(name);
                break;
            }
        }

        return res;

    }

    /**
     * Tells if the given {@link Definition} is valid for the given {@link Set} of {@link IType}.
     * resourceSetForModels
     * 
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param definition
     *            the {@link Definition} to check
     * @param types
     *            the {@link Set} of {@link IType}
     * @return <code>true</code> if the given {@link Definition} is valid for the given {@link Set} of {@link IType}, <code>false</code>
     *         otherwise
     */
    private static boolean isValidDefinitionForType(IReadOnlyQueryEnvironment queryEnvironment, Definition definition,
            Set<IType> types) {
        boolean res = false;

        final IType eObjectType = new ClassType(queryEnvironment, EObject.class);
        final IType eObjectSequenceType = new ForkedSequenceType(queryEnvironment, eObjectType);
        final IType eObjectOrderedSetType = new ForkedSetType(queryEnvironment, eObjectType);
        final IType stringType = new ClassType(queryEnvironment, String.class);
        final IType stringSequenceType = new ForkedSequenceType(queryEnvironment, stringType);
        final IType stringOrderedSetType = new ForkedSetType(queryEnvironment, stringType);
        final IType integerType = new ClassType(queryEnvironment, Integer.class);
        final IType integerSequenceType = new ForkedSequenceType(queryEnvironment, integerType);
        final IType integerOrderedSetType = new ForkedSetType(queryEnvironment, integerType);
        final IType realType = new ClassType(queryEnvironment, Double.class);
        final IType realSequenceType = new ForkedSequenceType(queryEnvironment, realType);
        final IType realOrderedSetType = new ForkedSetType(queryEnvironment, realType);
        final IType booleanType = new ClassType(queryEnvironment, Boolean.class);
        final IType booleanSequenceType = new ForkedSequenceType(queryEnvironment, booleanType);
        final IType booleanOrderedSetType = new ForkedSetType(queryEnvironment, booleanType);
        for (IType type : types) {
            if (eObjectType.isAssignableFrom(type) && definition instanceof ModelDefinition) {
                res = true;
                break;
            } else if (eObjectSequenceType.isAssignableFrom(type) && definition instanceof ModelSequenceDefinition) {
                res = true;
                break;
            } else
                if (eObjectOrderedSetType.isAssignableFrom(type) && definition instanceof ModelOrderedSetDefinition) {
                    res = true;
                    break;
                } else if (stringType.isAssignableFrom(type) && definition instanceof StringDefinition) {
                    res = true;
                    break;
                } else
                    if (stringSequenceType.isAssignableFrom(type) && definition instanceof StringSequenceDefinition) {
                        res = true;
                        break;
                    } else if (stringOrderedSetType.isAssignableFrom(type)
                        && definition instanceof StringOrderedSetDefinition) {
                            res = true;
                            break;
                        } else if (integerType.isAssignableFrom(type) && definition instanceof IntegerDefinition) {
                            res = true;
                            break;
                        } else if (integerSequenceType.isAssignableFrom(type)
                            && definition instanceof IntegerSequenceDefinition) {
                                res = true;
                                break;
                            } else if (integerOrderedSetType.isAssignableFrom(type)
                                && definition instanceof IntegerOrderedSetDefinition) {
                                    res = true;
                                    break;
                                } else if (realType.isAssignableFrom(type) && definition instanceof RealDefinition) {
                                    res = true;
                                    break;
                                } else if (realSequenceType.isAssignableFrom(type)
                                    && definition instanceof RealSequenceDefinition) {
                                        res = true;
                                        break;
                                    } else if (realOrderedSetType.isAssignableFrom(type)
                                        && definition instanceof RealOrderedSetDefinition) {
                                            res = true;
                                            break;
                                        } else if (booleanType.isAssignableFrom(type)
                                            && definition instanceof BooleanDefinition) {
                                                res = true;
                                                break;
                                            } else if (booleanSequenceType.isAssignableFrom(type)
                                                && definition instanceof BooleanSequenceDefinition) {
                                                    res = true;
                                                    break;
                                                } else if (booleanOrderedSetType.isAssignableFrom(type)
                                                    && definition instanceof BooleanOrderedSetDefinition) {
                                                        res = true;
                                                        break;
                                                    }
        }

        return res;
    }

    /**
     * Generate a document from the specified generation configuration.
     * 
     * @param generation
     *            the generation configuration
     * @param classProvider
     *            the {@link IClassProvider}
     * @param monitor
     *            used to track the progress will generating.
     * @return generated file
     * @throws DocumentGenerationException
     *             DocumentGenerationException
     * @throws DocumentParserException
     *             DocumentParserException
     * @throws IOException
     *             IOException
     */
    public static List<URI> generate(Generation generation, IClassProvider classProvider, Monitor monitor)
            throws DocumentGenerationException, IOException, DocumentParserException {
        if (generation == null) {
            throw new IllegalArgumentException("Null configuration object passed.");
        }
        String templateFilePath = generation.getTemplateFileName();
        if (templateFilePath == null) {
            throw new DocumentGenerationException("The template file path isn't set in the provided configuration");
        }

        String resultFilePath = generation.getResultFileName();
        if (resultFilePath == null) {
            throw new DocumentGenerationException("The result file path isn't set in the provided configuration");
        }

        final URI templateURI = getResolvedURI(generation, URI.createURI(generation.getTemplateFileName(), false));
        final URI generatedURI = getResolvedURI(generation, URI.createURI(generation.getResultFileName(), false));
        final URI validationURI;
        if (generation.getValidationFileName() != null && !generation.getValidationFileName().isEmpty()) {
            validationURI = getResolvedURI(generation, URI.createURI(generation.getValidationFileName(), false));
        } else {
            validationURI = null;
        }

        // generate result file and validation file.
        return generate(generation, classProvider, templateURI, generatedURI, validationURI, monitor);
    }

    /**
     * Gets the {@link URI#resolve(URI) resolved URI} from the given {@link Generation}.
     * 
     * @param generation
     *            the {@link Generation}
     * @param uri
     *            the {@link URI} to resolve
     * @return the {@link URI#resolve(URI) resolved URI} from the given {@link Generation}
     */
    public static URI getResolvedURI(Generation generation, URI uri) {
        final URI res;

        if (generation.eResource() != null && generation.eResource().getURI() != null) {
            res = uri.resolve(generation.eResource().getURI());
        } else {
            res = uri;
        }

        return res;
    }

    /**
     * Launch the documentation generation.
     * 
     * @param generation
     *            the generation configuration object
     * @param classProvider
     *            the {@link IClassProvider}
     * @param templateURI
     *            the template {@link URI}
     * @param generatedURI
     *            the generated docx {@link URI}
     * @param validationURI
     *            the validation docx {@link URI}
     * @param monitor
     *            used to track the progress will generating.
     * @return generated file and validation file if exists
     * @throws IOException
     *             if an I/O problem occurs
     * @throws DocumentParserException
     *             if the document coulnd'nt be parsed.
     * @throws DocumentGenerationException
     *             if the document couldn't be generated
     */
    private static List<URI> generate(Generation generation, IClassProvider classProvider, URI templateURI,
            URI generatedURI, URI validationURI, Monitor monitor)
            throws IOException, DocumentParserException, DocumentGenerationException {

        final List<Exception> exceptions = new ArrayList<Exception>();
        final Map<String, String> options = getOptions(generation);
        final ResourceSet resourceSetForModels = M2DocUtils.createResourceSetForModels(exceptions, generation,
                new ResourceSetImpl(), options);
        final URIConverter uriConverter = resourceSetForModels.getURIConverter();
        final IQueryEnvironment queryEnvironment = GenconfUtils.getQueryEnvironment(resourceSetForModels, generation);

        if (!uriConverter.exists(templateURI, Collections.EMPTY_MAP)) {
            throw new DocumentGenerationException("The template doest not exist " + templateURI);
        }

        // create generated file
        try (DocumentTemplate documentTemplate = M2DocUtils.parse(uriConverter, templateURI, queryEnvironment,
                classProvider, monitor)) {

            // create definitions
            Map<String, Object> definitions = GenconfUtils.getVariables(generation, resourceSetForModels);

            // validate template
            final boolean ignoreVersionCheck = Boolean.valueOf(options.get(M2DocUtils.IGNORE_VERSION_CHECK_OPTION));
            final URI resultValidationURI = validate(uriConverter, generatedURI, validationURI, documentTemplate,
                    queryEnvironment, ignoreVersionCheck, monitor);

            // launch generation
            final boolean updateFields = Boolean.valueOf(options.get(M2DocUtils.UPDATE_FIELDS_OPTION));
            M2DocUtils.generate(documentTemplate, queryEnvironment, definitions, resourceSetForModels, generatedURI,
                    updateFields, monitor);

            List<URI> generatedURIs = new ArrayList<URI>();
            generatedURIs.add(generatedURI);
            if (resultValidationURI != null) {
                generatedURIs.add(resultValidationURI);
            }

            M2DocUtils.cleanResourceSetForModels(generation, resourceSetForModels);

            return generatedURIs;
        }
    }

    /**
     * Validate templateInfo information.
     * 
     * @param generation
     *            Generation
     * @param classProvider
     *            the {@link IClassProvider}
     * @param monitor
     *            the {@link Monitor}
     * @return if template contains errors.
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     * @throws DocumentGenerationException
     *             DocumentGenerationException
     */
    public static boolean validate(Generation generation, IClassProvider classProvider, Monitor monitor)
            throws IOException, DocumentParserException, DocumentGenerationException {
        final boolean res;

        final List<Exception> exceptions = new ArrayList<Exception>();
        final ResourceSet resourceSetForModel = M2DocUtils.createResourceSetForModels(exceptions, generation,
                new ResourceSetImpl(), getOptions(generation));
        // get AQL environment
        IQueryEnvironment queryEnvironment = GenconfUtils.getQueryEnvironment(resourceSetForModel, generation);

        // get the template path
        final String templateFilePath = generation.getTemplateFileName();
        if (templateFilePath == null || templateFilePath.isEmpty()) {
            throw new DocumentGenerationException("The template file path isn't set in the provided configuration");
        }

        final URI templateURI = getResolvedURI(generation, URI.createURI(templateFilePath, false));
        if (!resourceSetForModel.getURIConverter().exists(templateURI, Collections.EMPTY_MAP)) {
            throw new DocumentGenerationException("The template file does not exist " + templateFilePath);
        }

        // get the validation path
        final String validationFilePath = generation.getValidationFileName();
        final URI validationURI;
        if (validationFilePath == null || validationFilePath.isEmpty()) {
            validationURI = null;
        } else {
            validationURI = getResolvedURI(generation, URI.createURI(validationFilePath, false));
        }

        // parse template
        try (DocumentTemplate documentTemplate = M2DocUtils.parse(resourceSetForModel.getURIConverter(), templateURI,
                queryEnvironment, classProvider, monitor)) {
            final XWPFRun run = documentTemplate.getDocument().getParagraphs().get(0).getRuns().get(0);
            for (Exception e : exceptions) {
                documentTemplate.getBody().getValidationMessages()
                        .add(new TemplateValidationMessage(ValidationMessageLevel.ERROR, e.getMessage(), run));
            }

            // validate template
            final Map<String, String> options = getOptions(generation);
            final boolean ignoreVersionCheck = Boolean.valueOf(options.get(M2DocUtils.IGNORE_VERSION_CHECK_OPTION));
            res = validate(resourceSetForModel.getURIConverter(), templateURI, validationURI, documentTemplate,
                    queryEnvironment, ignoreVersionCheck, monitor) != null;
        }

        // validate output path
        final String outputPath = generation.getResultFileName();
        if (outputPath == null || outputPath.isEmpty()) {
            throw new DocumentGenerationException("The output path isn't set in the provided configuration");
        } else {
            final URI outputURI = getResolvedURI(generation, URI.createURI(outputPath, false));
            if (resourceSetForModel.getURIConverter().exists(outputURI, Collections.EMPTY_MAP)) {
                final Map<Object, Object> options = new HashMap<Object, Object>();
                final Set<String> attributs = new LinkedHashSet<String>();
                attributs.add(URIConverter.ATTRIBUTE_DIRECTORY);
                attributs.add(URIConverter.ATTRIBUTE_READ_ONLY);
                options.put(URIConverter.OPTION_REQUESTED_ATTRIBUTES, attributs);
                Map<String, ?> attributeValues = resourceSetForModel.getURIConverter().getAttributes(outputURI,
                        options);
                if ((Boolean) attributeValues.get(URIConverter.ATTRIBUTE_DIRECTORY)) {
                    throw new DocumentGenerationException("The output path is a folder");
                } else if ((Boolean) attributeValues.get(URIConverter.ATTRIBUTE_READ_ONLY)) {
                    throw new DocumentGenerationException("The output path is read only");
                }
            }
        }

        M2DocUtils.cleanResourceSetForModels(generation, resourceSetForModel);

        return res;
    }

    /**
     * Validates template with templateInfo information.
     * 
     * @param uriConverter
     *            the {@link URIConverter}
     * @param generatedURI
     *            the generated {@link URI}
     * @param validationURI
     *            the validation {@link URI}
     * @param documentTemplate
     *            DocumentTemplate
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param ignoreVersionCheck
     *            ignore the {@link M2DocUtils#VERSION} check
     * @param monitor
     *            the {@link Monitor}
     * @return the validation {@link URI} if the validation isn't OK, <code>null</code> otherwise
     * @throws DocumentGenerationException
     *             DocumentGenerationException
     * @throws IOException
     *             IOException
     */
    private static URI validate(URIConverter uriConverter, URI generatedURI, URI validationURI,
            DocumentTemplate documentTemplate, IReadOnlyQueryEnvironment queryEnvironment, boolean ignoreVersionCheck,
            Monitor monitor) throws DocumentGenerationException, IOException {
        final URI res;

        final ValidationMessageLevel validationLevel = M2DocUtils.validate(documentTemplate, queryEnvironment,
                ignoreVersionCheck, monitor);
        if (validationLevel != ValidationMessageLevel.OK) {
            if (validationURI != null) {
                res = validationURI;
            } else {
                res = getValidationLogFile(generatedURI, validationLevel);
            }
            M2DocUtils.serializeValidatedDocumentTemplate(uriConverter, documentTemplate, res);
        } else {
            res = null;
        }

        return res;
    }

    /**
     * Gets the log {@link URI} for the given template {@link URI} and {@link ValidationMessageLevel}.
     * 
     * @param generatedURI
     *            the generated {@link URI}
     * @param level
     *            the {@link ValidationMessageLevel}
     * @return the log {@link URI} for the given template {@link URI} and {@link ValidationMessageLevel}
     */
    private static URI getValidationLogFile(URI generatedURI, ValidationMessageLevel level) {
        final URI res;

        String lastSegmentNoExtension = generatedURI.lastSegment().replaceFirst("[.][^.]+$", "");
        final URI uri = generatedURI.trimSegments(1)
                .appendSegment(lastSegmentNoExtension + "-" + level.name().toLowerCase());
        if (URI.validSegment(generatedURI.fileExtension())) {
            res = uri.appendFileExtension(generatedURI.fileExtension());
        } else {
            res = uri;
        }

        return res;
    }

    /**
     * Create genconf model from templateInfo information.
     * 
     * @param uriConverter
     *            the {@link URIConverter}
     * @param templateURI
     *            the template {@link URI}
     * @return the EMF {@link Resource} containing the {@link Generation} model. The {@link URI} of that resource is built based on
     *         {@code templateURI}.
     * @throws IOException
     *             IOException
     */
    public static Resource createConfigurationModel(URIConverter uriConverter, URI templateURI) throws IOException {
        Resource resource = null;
        TemplateCustomProperties templateProperties = POIServices.getInstance()
                .getTemplateCustomProperties(uriConverter, templateURI);

        // create genconf model
        if (templateProperties != null) {
            resource = createConfigurationModel(templateProperties, templateURI);
        }
        return resource;
    }

    /**
     * Create resource.
     * 
     * @param templateFile
     *            IFile
     * @param genConfURI
     *            URI
     * @return new resource.
     */
    private static Resource createResource(URI templateFile, URI genConfURI) {
        // Create a resource set
        ResourceSet resourceSet = new ResourceSetImpl();
        // Create a resource for this file.
        Resource resource = resourceSet.createResource(genConfURI);
        return resource;
    }

    /**
     * Create genconf model from templateInfo information.
     * 
     * @param templateProperties
     *            TemplateInfo
     * @param templateURI
     *            File
     * @return configuration model
     */
    private static Resource createConfigurationModel(TemplateCustomProperties templateProperties,
            final URI templateURI) {
        // create genconf resource.
        URI genConfURI = templateURI.trimFileExtension().appendFileExtension(GenconfUtils.GENCONF_EXTENSION_FILE);
        Resource resource = createResource(templateURI, genConfURI);

        // create initial model content.
        final Generation generation = createInitialModel(genConfURI.trimFileExtension().lastSegment(),
                templateURI.deresolve(genConfURI).toString());
        // add docx properties
        final List<Definition> definitions = GenconfUtils.getNewDefinitions(generation, templateProperties);
        generation.getDefinitions().addAll(definitions);
        if (generation != null) {
            resource.getContents().add(generation);
        }
        // Save the contents of the resource to the file system.
        try {
            saveResource(resource);
        } catch (IOException e) {
            GenconfPlugin.INSTANCE
                    .log(new Status(Status.ERROR, GenconfPlugin.PLUGIN_ID, Status.ERROR, e.getMessage(), e));
        }

        // Save the contents of the resource to the file system.
        try {
            saveResource(resource);
        } catch (IOException e) {
            GenconfPlugin.INSTANCE
                    .log(new Status(Status.ERROR, GenconfPlugin.PLUGIN_ID, Status.ERROR, e.getMessage(), e));
        }
        return resource;
    }

    /**
     * Create generation eObject.
     * 
     * @param name
     *            the name
     * @param templateFileName
     *            the template file name
     * @return the created {@link Generation}
     */
    private static Generation createInitialModel(String name, String templateFileName) {
        Generation generation = GenconfFactory.eINSTANCE.createGeneration();
        generation.setName(URI.decode(name));
        generation.setTemplateFileName(URI.decode(templateFileName));
        return generation;
    }

    /**
     * Save the contents of the resource to the file system.
     * 
     * @param resource
     *            Resource
     * @throws IOException
     *             if the resource can't be serialized
     */
    private static void saveResource(Resource resource) throws IOException {
        Map<Object, Object> options = new HashMap<>();
        options.put(XMLResource.OPTION_ENCODING, "UTF-8");
        resource.save(options);
    }

    /**
     * Gets or creates the {@link Option} with the given name.
     * 
     * @param generation
     *            the {@link Generation}
     * @param name
     *            the {@link Option#getName() option name}
     * @return the {@link Option} with the given name
     */
    public static Option getOrCreateOption(Generation generation, String name) {
        final Option res;

        Option foundOption = null;
        for (Option option : generation.getOptions()) {
            if (name.equals(option.getName())) {
                foundOption = option;
                break;
            }
        }

        if (foundOption != null) {
            res = foundOption;
        } else {
            res = GenconfPackage.eINSTANCE.getGenconfFactory().createOption();
            res.setName(name);
            generation.getOptions().add(res);
        }

        return res;
    }

    /**
     * Gets the {@link List} of available {@link Option#getName() option names} for the given {@link Generation}.
     * 
     * @param generation
     *            the {@link Generation}
     * @return the {@link List} of available {@link Option#getName() option names}
     */
    public static List<String> getAvailableOptionNames(Generation generation) {
        List<String> availableOptions = M2DocUtils.getPossibleOptionNames();

        for (Option option : generation.getOptions()) {
            availableOptions.remove(option.getName());
        }
        return availableOptions;
    }

    /**
     * Initializes the {@link Generation#getDefinitions() variable definitions} from the given {@link Generation} with the given
     * {@link ResourceSet}.
     * 
     * @param generation
     *            the {@link Generation} to initialize
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param properties
     *            the {@link TemplateCustomProperties}
     * @param resourceSetForModels
     *            the {@link ResourceSet} to get values from
     */
    public static void initializeVariableDefinition(Generation generation, IReadOnlyQueryEnvironment queryEnvironment,
            TemplateCustomProperties properties, ResourceSet resourceSetForModels) {
        final AstValidator validator = new AstValidator(new ValidationServices(queryEnvironment));
        final Map<ModelDefinition, Set<IType>> toInitialilize = new HashMap<ModelDefinition, Set<IType>>();
        for (Definition definition : generation.getDefinitions()) {
            if (definition instanceof ModelDefinition && ((ModelDefinition) definition).getValue() == null) {
                final ModelDefinition modelDefinition = (ModelDefinition) definition;
                final Set<IType> possibleTypes = properties.getVariableTypes(validator, queryEnvironment,
                        properties.getVariables().get(modelDefinition.getKey()));
                toInitialilize.put(modelDefinition, possibleTypes);
            }
        }

        final Iterator<Notifier> it = resourceSetForModels.getAllContents();
        while (!toInitialilize.isEmpty() && it.hasNext()) {
            final Notifier notifier = it.next();
            if (notifier instanceof EObject) {
                EObject element = (EObject) notifier;
                final EClassifierType elementType = new EClassifierType(queryEnvironment, element.eClass());
                ModelDefinition initialized = null;
                for (Entry<ModelDefinition, Set<IType>> entry : toInitialilize.entrySet()) {
                    for (IType definitionType : entry.getValue()) {
                        if (definitionType.isAssignableFrom(elementType)) {
                            initialized = entry.getKey();
                            initialized.setValue(element);
                        }
                    }
                }
                if (initialized != null) {
                    toInitialilize.remove(initialized);
                }
            }
        }
    }

    /**
     * Gets the Generation from the given {@link URI}.
     * 
     * @param uri
     *            the {@link URI}
     * @return the Generation from the given {@link URI}
     */
    public static Generation getGeneration(URI uri) {
        ResourceSet rs = new ResourceSetImpl();
        Resource modelResource = rs.getResource(uri, true);
        if (modelResource != null && !modelResource.getContents().isEmpty()) {
            EObject root = modelResource.getContents().get(0);
            if (root instanceof Generation) {
                return (Generation) root;
            }
        }
        return null;
    }

}
