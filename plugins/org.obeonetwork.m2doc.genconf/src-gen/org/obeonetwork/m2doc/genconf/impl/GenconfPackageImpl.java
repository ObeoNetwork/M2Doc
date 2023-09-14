/**
 */
package org.obeonetwork.m2doc.genconf.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.obeonetwork.m2doc.genconf.BooleanDefinition;
import org.obeonetwork.m2doc.genconf.BooleanOrderedSetDefinition;
import org.obeonetwork.m2doc.genconf.BooleanSequenceDefinition;
import org.obeonetwork.m2doc.genconf.Definition;
import org.obeonetwork.m2doc.genconf.GenconfFactory;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.IntegerDefinition;
import org.obeonetwork.m2doc.genconf.IntegerOrderedSetDefinition;
import org.obeonetwork.m2doc.genconf.IntegerSequenceDefinition;
import org.obeonetwork.m2doc.genconf.ModelDefinition;
import org.obeonetwork.m2doc.genconf.ModelOrderedSetDefinition;
import org.obeonetwork.m2doc.genconf.ModelSequenceDefinition;
import org.obeonetwork.m2doc.genconf.Option;
import org.obeonetwork.m2doc.genconf.RealDefinition;
import org.obeonetwork.m2doc.genconf.RealOrderedSetDefinition;
import org.obeonetwork.m2doc.genconf.RealSequenceDefinition;
import org.obeonetwork.m2doc.genconf.StringDefinition;
import org.obeonetwork.m2doc.genconf.StringOrderedSetDefinition;
import org.obeonetwork.m2doc.genconf.StringSequenceDefinition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class GenconfPackageImpl extends EPackageImpl implements GenconfPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass generationEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass definitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass modelDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass modelSequenceDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass modelOrderedSetDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass stringDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass stringSequenceDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass stringOrderedSetDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass integerDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass integerSequenceDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass integerOrderedSetDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass realDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass realSequenceDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass realOrderedSetDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass booleanDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass booleanSequenceDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass booleanOrderedSetDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass optionEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>
     * Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.obeonetwork.m2doc.genconf.GenconfPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private GenconfPackageImpl() {
        super(eNS_URI, GenconfFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * <p>
     * This method is used to initialize {@link GenconfPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static GenconfPackage init() {
        if (isInited)
            return (GenconfPackage) EPackage.Registry.INSTANCE.getEPackage(GenconfPackage.eNS_URI);

        // Obtain or create and register package
        Object registeredGenconfPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        GenconfPackageImpl theGenconfPackage = registeredGenconfPackage instanceof GenconfPackageImpl
                ? (GenconfPackageImpl) registeredGenconfPackage
                : new GenconfPackageImpl();

        isInited = true;

        // Create package meta-data objects
        theGenconfPackage.createPackageContents();

        // Initialize created meta-data
        theGenconfPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theGenconfPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(GenconfPackage.eNS_URI, theGenconfPackage);
        return theGenconfPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getGeneration() {
        return generationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getGeneration_Name() {
        return (EAttribute) generationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getGeneration_TemplateFileName() {
        return (EAttribute) generationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getGeneration_ResultFileName() {
        return (EAttribute) generationEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getGeneration_ValidationFileName() {
        return (EAttribute) generationEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getGeneration_Definitions() {
        return (EReference) generationEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getGeneration_Options() {
        return (EReference) generationEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getDefinition() {
        return definitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getDefinition_Key() {
        return (EAttribute) definitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getModelDefinition() {
        return modelDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getModelDefinition_Value() {
        return (EReference) modelDefinitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getModelSequenceDefinition() {
        return modelSequenceDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getModelSequenceDefinition_Value() {
        return (EReference) modelSequenceDefinitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getModelOrderedSetDefinition() {
        return modelOrderedSetDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getModelOrderedSetDefinition_Value() {
        return (EReference) modelOrderedSetDefinitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getStringDefinition() {
        return stringDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getStringDefinition_Value() {
        return (EAttribute) stringDefinitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getStringSequenceDefinition() {
        return stringSequenceDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getStringSequenceDefinition_Value() {
        return (EAttribute) stringSequenceDefinitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getStringOrderedSetDefinition() {
        return stringOrderedSetDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getStringOrderedSetDefinition_Value() {
        return (EAttribute) stringOrderedSetDefinitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getIntegerDefinition() {
        return integerDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getIntegerDefinition_Value() {
        return (EAttribute) integerDefinitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getIntegerSequenceDefinition() {
        return integerSequenceDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getIntegerSequenceDefinition_Value() {
        return (EAttribute) integerSequenceDefinitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getIntegerOrderedSetDefinition() {
        return integerOrderedSetDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getIntegerOrderedSetDefinition_Value() {
        return (EAttribute) integerOrderedSetDefinitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getRealDefinition() {
        return realDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getRealDefinition_Value() {
        return (EAttribute) realDefinitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getRealSequenceDefinition() {
        return realSequenceDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getRealSequenceDefinition_Value() {
        return (EAttribute) realSequenceDefinitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getRealOrderedSetDefinition() {
        return realOrderedSetDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getRealOrderedSetDefinition_Value() {
        return (EAttribute) realOrderedSetDefinitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getBooleanDefinition() {
        return booleanDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getBooleanDefinition_Value() {
        return (EAttribute) booleanDefinitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getBooleanSequenceDefinition() {
        return booleanSequenceDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getBooleanSequenceDefinition_Value() {
        return (EAttribute) booleanSequenceDefinitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getBooleanOrderedSetDefinition() {
        return booleanOrderedSetDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getBooleanOrderedSetDefinition_Value() {
        return (EAttribute) booleanOrderedSetDefinitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getOption() {
        return optionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getOption_Name() {
        return (EAttribute) optionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getOption_Value() {
        return (EAttribute) optionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public GenconfFactory getGenconfFactory() {
        return (GenconfFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void createPackageContents() {
        if (isCreated)
            return;
        isCreated = true;

        // Create classes and their features
        generationEClass = createEClass(GENERATION);
        createEAttribute(generationEClass, GENERATION__NAME);
        createEAttribute(generationEClass, GENERATION__TEMPLATE_FILE_NAME);
        createEAttribute(generationEClass, GENERATION__RESULT_FILE_NAME);
        createEAttribute(generationEClass, GENERATION__VALIDATION_FILE_NAME);
        createEReference(generationEClass, GENERATION__DEFINITIONS);
        createEReference(generationEClass, GENERATION__OPTIONS);

        definitionEClass = createEClass(DEFINITION);
        createEAttribute(definitionEClass, DEFINITION__KEY);

        modelDefinitionEClass = createEClass(MODEL_DEFINITION);
        createEReference(modelDefinitionEClass, MODEL_DEFINITION__VALUE);

        modelSequenceDefinitionEClass = createEClass(MODEL_SEQUENCE_DEFINITION);
        createEReference(modelSequenceDefinitionEClass, MODEL_SEQUENCE_DEFINITION__VALUE);

        modelOrderedSetDefinitionEClass = createEClass(MODEL_ORDERED_SET_DEFINITION);
        createEReference(modelOrderedSetDefinitionEClass, MODEL_ORDERED_SET_DEFINITION__VALUE);

        stringDefinitionEClass = createEClass(STRING_DEFINITION);
        createEAttribute(stringDefinitionEClass, STRING_DEFINITION__VALUE);

        stringSequenceDefinitionEClass = createEClass(STRING_SEQUENCE_DEFINITION);
        createEAttribute(stringSequenceDefinitionEClass, STRING_SEQUENCE_DEFINITION__VALUE);

        stringOrderedSetDefinitionEClass = createEClass(STRING_ORDERED_SET_DEFINITION);
        createEAttribute(stringOrderedSetDefinitionEClass, STRING_ORDERED_SET_DEFINITION__VALUE);

        integerDefinitionEClass = createEClass(INTEGER_DEFINITION);
        createEAttribute(integerDefinitionEClass, INTEGER_DEFINITION__VALUE);

        integerSequenceDefinitionEClass = createEClass(INTEGER_SEQUENCE_DEFINITION);
        createEAttribute(integerSequenceDefinitionEClass, INTEGER_SEQUENCE_DEFINITION__VALUE);

        integerOrderedSetDefinitionEClass = createEClass(INTEGER_ORDERED_SET_DEFINITION);
        createEAttribute(integerOrderedSetDefinitionEClass, INTEGER_ORDERED_SET_DEFINITION__VALUE);

        realDefinitionEClass = createEClass(REAL_DEFINITION);
        createEAttribute(realDefinitionEClass, REAL_DEFINITION__VALUE);

        realSequenceDefinitionEClass = createEClass(REAL_SEQUENCE_DEFINITION);
        createEAttribute(realSequenceDefinitionEClass, REAL_SEQUENCE_DEFINITION__VALUE);

        realOrderedSetDefinitionEClass = createEClass(REAL_ORDERED_SET_DEFINITION);
        createEAttribute(realOrderedSetDefinitionEClass, REAL_ORDERED_SET_DEFINITION__VALUE);

        booleanDefinitionEClass = createEClass(BOOLEAN_DEFINITION);
        createEAttribute(booleanDefinitionEClass, BOOLEAN_DEFINITION__VALUE);

        booleanSequenceDefinitionEClass = createEClass(BOOLEAN_SEQUENCE_DEFINITION);
        createEAttribute(booleanSequenceDefinitionEClass, BOOLEAN_SEQUENCE_DEFINITION__VALUE);

        booleanOrderedSetDefinitionEClass = createEClass(BOOLEAN_ORDERED_SET_DEFINITION);
        createEAttribute(booleanOrderedSetDefinitionEClass, BOOLEAN_ORDERED_SET_DEFINITION__VALUE);

        optionEClass = createEClass(OPTION);
        createEAttribute(optionEClass, OPTION__NAME);
        createEAttribute(optionEClass, OPTION__VALUE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized)
            return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        modelDefinitionEClass.getESuperTypes().add(this.getDefinition());
        modelSequenceDefinitionEClass.getESuperTypes().add(this.getDefinition());
        modelOrderedSetDefinitionEClass.getESuperTypes().add(this.getDefinition());
        stringDefinitionEClass.getESuperTypes().add(this.getDefinition());
        stringSequenceDefinitionEClass.getESuperTypes().add(this.getDefinition());
        stringOrderedSetDefinitionEClass.getESuperTypes().add(this.getDefinition());
        integerDefinitionEClass.getESuperTypes().add(this.getDefinition());
        integerSequenceDefinitionEClass.getESuperTypes().add(this.getDefinition());
        integerOrderedSetDefinitionEClass.getESuperTypes().add(this.getDefinition());
        realDefinitionEClass.getESuperTypes().add(this.getDefinition());
        realSequenceDefinitionEClass.getESuperTypes().add(this.getDefinition());
        realOrderedSetDefinitionEClass.getESuperTypes().add(this.getDefinition());
        booleanDefinitionEClass.getESuperTypes().add(this.getDefinition());
        booleanSequenceDefinitionEClass.getESuperTypes().add(this.getDefinition());
        booleanOrderedSetDefinitionEClass.getESuperTypes().add(this.getDefinition());

        // Initialize classes, features, and operations; add parameters
        initEClass(generationEClass, Generation.class, "Generation", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getGeneration_Name(), ecorePackage.getEString(), "name", null, 0, 1, Generation.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGeneration_TemplateFileName(), ecorePackage.getEString(), "templateFileName", null, 0, 1,
                Generation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGeneration_ResultFileName(), ecorePackage.getEString(), "resultFileName", null, 0, 1,
                Generation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGeneration_ValidationFileName(), ecorePackage.getEString(), "validationFileName", null, 0, 1,
                Generation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getGeneration_Definitions(), this.getDefinition(), null, "definitions", null, 0, -1,
                Generation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGeneration_Options(), this.getOption(), null, "options", null, 0, -1, Generation.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(definitionEClass, Definition.class, "Definition", IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDefinition_Key(), ecorePackage.getEString(), "key", null, 0, 1, Definition.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(modelDefinitionEClass, ModelDefinition.class, "ModelDefinition", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getModelDefinition_Value(), ecorePackage.getEObject(), null, "value", null, 0, 1,
                ModelDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(modelSequenceDefinitionEClass, ModelSequenceDefinition.class, "ModelSequenceDefinition",
                !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getModelSequenceDefinition_Value(), ecorePackage.getEObject(), null, "value", null, 0, -1,
                ModelSequenceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(modelOrderedSetDefinitionEClass, ModelOrderedSetDefinition.class, "ModelOrderedSetDefinition",
                !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getModelOrderedSetDefinition_Value(), ecorePackage.getEObject(), null, "value", null, 0, -1,
                ModelOrderedSetDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(stringDefinitionEClass, StringDefinition.class, "StringDefinition", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getStringDefinition_Value(), ecorePackage.getEString(), "value", "", 0, 1,
                StringDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(stringSequenceDefinitionEClass, StringSequenceDefinition.class, "StringSequenceDefinition",
                !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getStringSequenceDefinition_Value(), ecorePackage.getEString(), "value", "", 0, -1,
                StringSequenceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(stringOrderedSetDefinitionEClass, StringOrderedSetDefinition.class, "StringOrderedSetDefinition",
                !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getStringOrderedSetDefinition_Value(), ecorePackage.getEString(), "value", "", 0, -1,
                StringOrderedSetDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(integerDefinitionEClass, IntegerDefinition.class, "IntegerDefinition", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIntegerDefinition_Value(), ecorePackage.getEInt(), "value", "0", 0, 1,
                IntegerDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(integerSequenceDefinitionEClass, IntegerSequenceDefinition.class, "IntegerSequenceDefinition",
                !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIntegerSequenceDefinition_Value(), ecorePackage.getEInt(), "value", "0", 0, -1,
                IntegerSequenceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(integerOrderedSetDefinitionEClass, IntegerOrderedSetDefinition.class, "IntegerOrderedSetDefinition",
                !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIntegerOrderedSetDefinition_Value(), ecorePackage.getEInt(), "value", "0", 0, -1,
                IntegerOrderedSetDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(realDefinitionEClass, RealDefinition.class, "RealDefinition", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRealDefinition_Value(), ecorePackage.getEDouble(), "value", "0", 0, 1, RealDefinition.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(realSequenceDefinitionEClass, RealSequenceDefinition.class, "RealSequenceDefinition", !IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRealSequenceDefinition_Value(), ecorePackage.getEDouble(), "value", "0", 0, -1,
                RealSequenceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(realOrderedSetDefinitionEClass, RealOrderedSetDefinition.class, "RealOrderedSetDefinition",
                !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRealOrderedSetDefinition_Value(), ecorePackage.getEDouble(), "value", "0", 0, -1,
                RealOrderedSetDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(booleanDefinitionEClass, BooleanDefinition.class, "BooleanDefinition", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBooleanDefinition_Value(), ecorePackage.getEBoolean(), "value", "false", 0, 1,
                BooleanDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(booleanSequenceDefinitionEClass, BooleanSequenceDefinition.class, "BooleanSequenceDefinition",
                !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBooleanSequenceDefinition_Value(), ecorePackage.getEBoolean(), "value", "false", 0, -1,
                BooleanSequenceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(booleanOrderedSetDefinitionEClass, BooleanOrderedSetDefinition.class, "BooleanOrderedSetDefinition",
                !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBooleanOrderedSetDefinition_Value(), ecorePackage.getEBoolean(), "value", "false", 0, -1,
                BooleanOrderedSetDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(optionEClass, Option.class, "Option", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getOption_Name(), ecorePackage.getEString(), "name", "", 1, 1, Option.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getOption_Value(), ecorePackage.getEString(), "value", "", 1, 1, Option.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} // GenconfPackageImpl
