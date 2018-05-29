/**
 */
package org.obeonetwork.m2doc.genconf;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.obeonetwork.m2doc.genconf.GenconfFactory
 * @model kind="package"
 * @generated
 */
public interface GenconfPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "genconf";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.obeonetwork.org/m2doc/genconf/1.0";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "genconf";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    GenconfPackage eINSTANCE = org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl.init();

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.GenerationImpl <em>Generation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.genconf.impl.GenerationImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getGeneration()
     * @generated
     */
    int GENERATION = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GENERATION__NAME = 0;

    /**
     * The feature id for the '<em><b>Template File Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GENERATION__TEMPLATE_FILE_NAME = 1;

    /**
     * The feature id for the '<em><b>Result File Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GENERATION__RESULT_FILE_NAME = 2;

    /**
     * The feature id for the '<em><b>Validation File Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GENERATION__VALIDATION_FILE_NAME = 3;

    /**
     * The feature id for the '<em><b>Definitions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GENERATION__DEFINITIONS = 4;

    /**
     * The feature id for the '<em><b>Options</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GENERATION__OPTIONS = 5;

    /**
     * The number of structural features of the '<em>Generation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GENERATION_FEATURE_COUNT = 6;

    /**
     * The number of operations of the '<em>Generation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GENERATION_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.DefinitionImpl <em>Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.genconf.impl.DefinitionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getDefinition()
     * @generated
     */
    int DEFINITION = 1;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEFINITION__KEY = 0;

    /**
     * The number of structural features of the '<em>Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEFINITION_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEFINITION_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.ModelDefinitionImpl <em>Model Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.genconf.impl.ModelDefinitionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getModelDefinition()
     * @generated
     */
    int MODEL_DEFINITION = 2;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_DEFINITION__KEY = DEFINITION__KEY;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_DEFINITION__VALUE = DEFINITION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Model Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_DEFINITION_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Model Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_DEFINITION_OPERATION_COUNT = DEFINITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.StringDefinitionImpl <em>String Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.genconf.impl.StringDefinitionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getStringDefinition()
     * @generated
     */
    int STRING_DEFINITION = 3;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STRING_DEFINITION__KEY = DEFINITION__KEY;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STRING_DEFINITION__VALUE = DEFINITION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>String Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STRING_DEFINITION_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>String Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STRING_DEFINITION_OPERATION_COUNT = DEFINITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.IntegerDefinitionImpl <em>Integer Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.genconf.impl.IntegerDefinitionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getIntegerDefinition()
     * @generated
     */
    int INTEGER_DEFINITION = 4;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTEGER_DEFINITION__KEY = DEFINITION__KEY;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTEGER_DEFINITION__VALUE = DEFINITION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Integer Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTEGER_DEFINITION_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Integer Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTEGER_DEFINITION_OPERATION_COUNT = DEFINITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.RealDefinitionImpl <em>Real Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.genconf.impl.RealDefinitionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getRealDefinition()
     * @generated
     */
    int REAL_DEFINITION = 5;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REAL_DEFINITION__KEY = DEFINITION__KEY;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REAL_DEFINITION__VALUE = DEFINITION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Real Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REAL_DEFINITION_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Real Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REAL_DEFINITION_OPERATION_COUNT = DEFINITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.BooleanDefinitionImpl <em>Boolean Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.genconf.impl.BooleanDefinitionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getBooleanDefinition()
     * @generated
     */
    int BOOLEAN_DEFINITION = 6;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOOLEAN_DEFINITION__KEY = DEFINITION__KEY;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOOLEAN_DEFINITION__VALUE = DEFINITION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Boolean Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOOLEAN_DEFINITION_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Boolean Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOOLEAN_DEFINITION_OPERATION_COUNT = DEFINITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.OptionImpl <em>Option</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.genconf.impl.OptionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getOption()
     * @generated
     */
    int OPTION = 7;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPTION__NAME = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPTION__VALUE = 1;

    /**
     * The number of structural features of the '<em>Option</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPTION_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Option</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPTION_OPERATION_COUNT = 0;

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.genconf.Generation <em>Generation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Generation</em>'.
     * @see org.obeonetwork.m2doc.genconf.Generation
     * @generated
     */
    EClass getGeneration();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.genconf.Generation#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.obeonetwork.m2doc.genconf.Generation#getName()
     * @see #getGeneration()
     * @generated
     */
    EAttribute getGeneration_Name();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.genconf.Generation#getTemplateFileName <em>Template File
     * Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Template File Name</em>'.
     * @see org.obeonetwork.m2doc.genconf.Generation#getTemplateFileName()
     * @see #getGeneration()
     * @generated
     */
    EAttribute getGeneration_TemplateFileName();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.genconf.Generation#getResultFileName <em>Result File Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Result File Name</em>'.
     * @see org.obeonetwork.m2doc.genconf.Generation#getResultFileName()
     * @see #getGeneration()
     * @generated
     */
    EAttribute getGeneration_ResultFileName();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.genconf.Generation#getValidationFileName <em>Validation File
     * Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Validation File Name</em>'.
     * @see org.obeonetwork.m2doc.genconf.Generation#getValidationFileName()
     * @see #getGeneration()
     * @generated
     */
    EAttribute getGeneration_ValidationFileName();

    /**
     * Returns the meta object for the containment reference list '{@link org.obeonetwork.m2doc.genconf.Generation#getDefinitions
     * <em>Definitions</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '<em>Definitions</em>'.
     * @see org.obeonetwork.m2doc.genconf.Generation#getDefinitions()
     * @see #getGeneration()
     * @generated
     */
    EReference getGeneration_Definitions();

    /**
     * Returns the meta object for the containment reference list '{@link org.obeonetwork.m2doc.genconf.Generation#getOptions
     * <em>Options</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '<em>Options</em>'.
     * @see org.obeonetwork.m2doc.genconf.Generation#getOptions()
     * @see #getGeneration()
     * @generated
     */
    EReference getGeneration_Options();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.genconf.Definition <em>Definition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Definition</em>'.
     * @see org.obeonetwork.m2doc.genconf.Definition
     * @generated
     */
    EClass getDefinition();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.genconf.Definition#getKey <em>Key</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Key</em>'.
     * @see org.obeonetwork.m2doc.genconf.Definition#getKey()
     * @see #getDefinition()
     * @generated
     */
    EAttribute getDefinition_Key();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.genconf.ModelDefinition <em>Model Definition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Model Definition</em>'.
     * @see org.obeonetwork.m2doc.genconf.ModelDefinition
     * @generated
     */
    EClass getModelDefinition();

    /**
     * Returns the meta object for the reference '{@link org.obeonetwork.m2doc.genconf.ModelDefinition#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Value</em>'.
     * @see org.obeonetwork.m2doc.genconf.ModelDefinition#getValue()
     * @see #getModelDefinition()
     * @generated
     */
    EReference getModelDefinition_Value();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.genconf.StringDefinition <em>String Definition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>String Definition</em>'.
     * @see org.obeonetwork.m2doc.genconf.StringDefinition
     * @generated
     */
    EClass getStringDefinition();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.genconf.StringDefinition#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.obeonetwork.m2doc.genconf.StringDefinition#getValue()
     * @see #getStringDefinition()
     * @generated
     */
    EAttribute getStringDefinition_Value();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.genconf.IntegerDefinition <em>Integer Definition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Integer Definition</em>'.
     * @see org.obeonetwork.m2doc.genconf.IntegerDefinition
     * @generated
     */
    EClass getIntegerDefinition();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.genconf.IntegerDefinition#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.obeonetwork.m2doc.genconf.IntegerDefinition#getValue()
     * @see #getIntegerDefinition()
     * @generated
     */
    EAttribute getIntegerDefinition_Value();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.genconf.RealDefinition <em>Real Definition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Real Definition</em>'.
     * @see org.obeonetwork.m2doc.genconf.RealDefinition
     * @generated
     */
    EClass getRealDefinition();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.genconf.RealDefinition#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.obeonetwork.m2doc.genconf.RealDefinition#getValue()
     * @see #getRealDefinition()
     * @generated
     */
    EAttribute getRealDefinition_Value();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.genconf.BooleanDefinition <em>Boolean Definition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Boolean Definition</em>'.
     * @see org.obeonetwork.m2doc.genconf.BooleanDefinition
     * @generated
     */
    EClass getBooleanDefinition();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.genconf.BooleanDefinition#isValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.obeonetwork.m2doc.genconf.BooleanDefinition#isValue()
     * @see #getBooleanDefinition()
     * @generated
     */
    EAttribute getBooleanDefinition_Value();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.genconf.Option <em>Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Option</em>'.
     * @see org.obeonetwork.m2doc.genconf.Option
     * @generated
     */
    EClass getOption();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.genconf.Option#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.obeonetwork.m2doc.genconf.Option#getName()
     * @see #getOption()
     * @generated
     */
    EAttribute getOption_Name();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.genconf.Option#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.obeonetwork.m2doc.genconf.Option#getValue()
     * @see #getOption()
     * @generated
     */
    EAttribute getOption_Value();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    GenconfFactory getGenconfFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each operation of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.GenerationImpl <em>Generation</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.genconf.impl.GenerationImpl
         * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getGeneration()
         * @generated
         */
        EClass GENERATION = eINSTANCE.getGeneration();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GENERATION__NAME = eINSTANCE.getGeneration_Name();

        /**
         * The meta object literal for the '<em><b>Template File Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GENERATION__TEMPLATE_FILE_NAME = eINSTANCE.getGeneration_TemplateFileName();

        /**
         * The meta object literal for the '<em><b>Result File Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GENERATION__RESULT_FILE_NAME = eINSTANCE.getGeneration_ResultFileName();

        /**
         * The meta object literal for the '<em><b>Validation File Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GENERATION__VALIDATION_FILE_NAME = eINSTANCE.getGeneration_ValidationFileName();

        /**
         * The meta object literal for the '<em><b>Definitions</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference GENERATION__DEFINITIONS = eINSTANCE.getGeneration_Definitions();

        /**
         * The meta object literal for the '<em><b>Options</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference GENERATION__OPTIONS = eINSTANCE.getGeneration_Options();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.DefinitionImpl <em>Definition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.genconf.impl.DefinitionImpl
         * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getDefinition()
         * @generated
         */
        EClass DEFINITION = eINSTANCE.getDefinition();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DEFINITION__KEY = eINSTANCE.getDefinition_Key();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.ModelDefinitionImpl <em>Model Definition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.genconf.impl.ModelDefinitionImpl
         * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getModelDefinition()
         * @generated
         */
        EClass MODEL_DEFINITION = eINSTANCE.getModelDefinition();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference MODEL_DEFINITION__VALUE = eINSTANCE.getModelDefinition_Value();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.StringDefinitionImpl <em>String Definition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.genconf.impl.StringDefinitionImpl
         * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getStringDefinition()
         * @generated
         */
        EClass STRING_DEFINITION = eINSTANCE.getStringDefinition();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute STRING_DEFINITION__VALUE = eINSTANCE.getStringDefinition_Value();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.IntegerDefinitionImpl <em>Integer Definition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.genconf.impl.IntegerDefinitionImpl
         * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getIntegerDefinition()
         * @generated
         */
        EClass INTEGER_DEFINITION = eINSTANCE.getIntegerDefinition();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute INTEGER_DEFINITION__VALUE = eINSTANCE.getIntegerDefinition_Value();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.RealDefinitionImpl <em>Real Definition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.genconf.impl.RealDefinitionImpl
         * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getRealDefinition()
         * @generated
         */
        EClass REAL_DEFINITION = eINSTANCE.getRealDefinition();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REAL_DEFINITION__VALUE = eINSTANCE.getRealDefinition_Value();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.BooleanDefinitionImpl <em>Boolean Definition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.genconf.impl.BooleanDefinitionImpl
         * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getBooleanDefinition()
         * @generated
         */
        EClass BOOLEAN_DEFINITION = eINSTANCE.getBooleanDefinition();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BOOLEAN_DEFINITION__VALUE = eINSTANCE.getBooleanDefinition_Value();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.OptionImpl <em>Option</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.genconf.impl.OptionImpl
         * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getOption()
         * @generated
         */
        EClass OPTION = eINSTANCE.getOption();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute OPTION__NAME = eINSTANCE.getOption_Name();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute OPTION__VALUE = eINSTANCE.getOption_Value();

    }

} // GenconfPackage
