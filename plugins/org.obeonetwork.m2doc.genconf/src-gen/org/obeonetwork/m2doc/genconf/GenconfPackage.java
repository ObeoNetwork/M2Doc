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
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.ModelSequenceDefinitionImpl <em>Model Sequence
     * Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.genconf.impl.ModelSequenceDefinitionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getModelSequenceDefinition()
     * @generated
     */
    int MODEL_SEQUENCE_DEFINITION = 3;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_SEQUENCE_DEFINITION__KEY = DEFINITION__KEY;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_SEQUENCE_DEFINITION__VALUE = DEFINITION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Model Sequence Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_SEQUENCE_DEFINITION_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Model Sequence Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_SEQUENCE_DEFINITION_OPERATION_COUNT = DEFINITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.ModelOrderedSetDefinitionImpl <em>Model Ordered Set
     * Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.genconf.impl.ModelOrderedSetDefinitionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getModelOrderedSetDefinition()
     * @generated
     */
    int MODEL_ORDERED_SET_DEFINITION = 4;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_ORDERED_SET_DEFINITION__KEY = DEFINITION__KEY;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_ORDERED_SET_DEFINITION__VALUE = DEFINITION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Model Ordered Set Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_ORDERED_SET_DEFINITION_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Model Ordered Set Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_ORDERED_SET_DEFINITION_OPERATION_COUNT = DEFINITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.StringDefinitionImpl <em>String Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.genconf.impl.StringDefinitionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getStringDefinition()
     * @generated
     */
    int STRING_DEFINITION = 5;

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
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.StringSequenceDefinitionImpl <em>String Sequence
     * Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.genconf.impl.StringSequenceDefinitionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getStringSequenceDefinition()
     * @generated
     */
    int STRING_SEQUENCE_DEFINITION = 6;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STRING_SEQUENCE_DEFINITION__KEY = DEFINITION__KEY;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STRING_SEQUENCE_DEFINITION__VALUE = DEFINITION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>String Sequence Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STRING_SEQUENCE_DEFINITION_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>String Sequence Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STRING_SEQUENCE_DEFINITION_OPERATION_COUNT = DEFINITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.StringOrderedSetDefinitionImpl <em>String Ordered Set
     * Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.genconf.impl.StringOrderedSetDefinitionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getStringOrderedSetDefinition()
     * @generated
     */
    int STRING_ORDERED_SET_DEFINITION = 7;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STRING_ORDERED_SET_DEFINITION__KEY = DEFINITION__KEY;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STRING_ORDERED_SET_DEFINITION__VALUE = DEFINITION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>String Ordered Set Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STRING_ORDERED_SET_DEFINITION_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>String Ordered Set Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STRING_ORDERED_SET_DEFINITION_OPERATION_COUNT = DEFINITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.IntegerDefinitionImpl <em>Integer Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.genconf.impl.IntegerDefinitionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getIntegerDefinition()
     * @generated
     */
    int INTEGER_DEFINITION = 8;

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
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.IntegerSequenceDefinitionImpl <em>Integer Sequence
     * Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.genconf.impl.IntegerSequenceDefinitionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getIntegerSequenceDefinition()
     * @generated
     */
    int INTEGER_SEQUENCE_DEFINITION = 9;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTEGER_SEQUENCE_DEFINITION__KEY = DEFINITION__KEY;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTEGER_SEQUENCE_DEFINITION__VALUE = DEFINITION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Integer Sequence Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTEGER_SEQUENCE_DEFINITION_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Integer Sequence Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTEGER_SEQUENCE_DEFINITION_OPERATION_COUNT = DEFINITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.IntegerOrderedSetDefinitionImpl <em>Integer Ordered Set
     * Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.genconf.impl.IntegerOrderedSetDefinitionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getIntegerOrderedSetDefinition()
     * @generated
     */
    int INTEGER_ORDERED_SET_DEFINITION = 10;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTEGER_ORDERED_SET_DEFINITION__KEY = DEFINITION__KEY;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTEGER_ORDERED_SET_DEFINITION__VALUE = DEFINITION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Integer Ordered Set Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTEGER_ORDERED_SET_DEFINITION_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Integer Ordered Set Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTEGER_ORDERED_SET_DEFINITION_OPERATION_COUNT = DEFINITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.RealDefinitionImpl <em>Real Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.genconf.impl.RealDefinitionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getRealDefinition()
     * @generated
     */
    int REAL_DEFINITION = 11;

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
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.RealSequenceDefinitionImpl <em>Real Sequence Definition</em>}'
     * class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.genconf.impl.RealSequenceDefinitionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getRealSequenceDefinition()
     * @generated
     */
    int REAL_SEQUENCE_DEFINITION = 12;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REAL_SEQUENCE_DEFINITION__KEY = DEFINITION__KEY;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REAL_SEQUENCE_DEFINITION__VALUE = DEFINITION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Real Sequence Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REAL_SEQUENCE_DEFINITION_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Real Sequence Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REAL_SEQUENCE_DEFINITION_OPERATION_COUNT = DEFINITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.RealOrderedSetDefinitionImpl <em>Real Ordered Set
     * Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.genconf.impl.RealOrderedSetDefinitionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getRealOrderedSetDefinition()
     * @generated
     */
    int REAL_ORDERED_SET_DEFINITION = 13;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REAL_ORDERED_SET_DEFINITION__KEY = DEFINITION__KEY;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REAL_ORDERED_SET_DEFINITION__VALUE = DEFINITION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Real Ordered Set Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REAL_ORDERED_SET_DEFINITION_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Real Ordered Set Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REAL_ORDERED_SET_DEFINITION_OPERATION_COUNT = DEFINITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.BooleanDefinitionImpl <em>Boolean Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.genconf.impl.BooleanDefinitionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getBooleanDefinition()
     * @generated
     */
    int BOOLEAN_DEFINITION = 14;

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
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.BooleanSequenceDefinitionImpl <em>Boolean Sequence
     * Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.genconf.impl.BooleanSequenceDefinitionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getBooleanSequenceDefinition()
     * @generated
     */
    int BOOLEAN_SEQUENCE_DEFINITION = 15;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOOLEAN_SEQUENCE_DEFINITION__KEY = DEFINITION__KEY;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOOLEAN_SEQUENCE_DEFINITION__VALUE = DEFINITION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Boolean Sequence Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOOLEAN_SEQUENCE_DEFINITION_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Boolean Sequence Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOOLEAN_SEQUENCE_DEFINITION_OPERATION_COUNT = DEFINITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.BooleanOrderedSetDefinitionImpl <em>Boolean Ordered Set
     * Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.genconf.impl.BooleanOrderedSetDefinitionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getBooleanOrderedSetDefinition()
     * @generated
     */
    int BOOLEAN_ORDERED_SET_DEFINITION = 16;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOOLEAN_ORDERED_SET_DEFINITION__KEY = DEFINITION__KEY;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOOLEAN_ORDERED_SET_DEFINITION__VALUE = DEFINITION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Boolean Ordered Set Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOOLEAN_ORDERED_SET_DEFINITION_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Boolean Ordered Set Definition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOOLEAN_ORDERED_SET_DEFINITION_OPERATION_COUNT = DEFINITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.OptionImpl <em>Option</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.genconf.impl.OptionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getOption()
     * @generated
     */
    int OPTION = 17;

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
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.genconf.Generation#getResultFileName <em>Result File
     * Name</em>}'.
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
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.genconf.ModelSequenceDefinition <em>Model Sequence Definition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Model Sequence Definition</em>'.
     * @see org.obeonetwork.m2doc.genconf.ModelSequenceDefinition
     * @generated
     */
    EClass getModelSequenceDefinition();

    /**
     * Returns the meta object for the reference list '{@link org.obeonetwork.m2doc.genconf.ModelSequenceDefinition#getValue
     * <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Value</em>'.
     * @see org.obeonetwork.m2doc.genconf.ModelSequenceDefinition#getValue()
     * @see #getModelSequenceDefinition()
     * @generated
     */
    EReference getModelSequenceDefinition_Value();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.genconf.ModelOrderedSetDefinition <em>Model Ordered Set
     * Definition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Model Ordered Set Definition</em>'.
     * @see org.obeonetwork.m2doc.genconf.ModelOrderedSetDefinition
     * @generated
     */
    EClass getModelOrderedSetDefinition();

    /**
     * Returns the meta object for the reference list '{@link org.obeonetwork.m2doc.genconf.ModelOrderedSetDefinition#getValue
     * <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Value</em>'.
     * @see org.obeonetwork.m2doc.genconf.ModelOrderedSetDefinition#getValue()
     * @see #getModelOrderedSetDefinition()
     * @generated
     */
    EReference getModelOrderedSetDefinition_Value();

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
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.genconf.StringSequenceDefinition <em>String Sequence
     * Definition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>String Sequence Definition</em>'.
     * @see org.obeonetwork.m2doc.genconf.StringSequenceDefinition
     * @generated
     */
    EClass getStringSequenceDefinition();

    /**
     * Returns the meta object for the attribute list '{@link org.obeonetwork.m2doc.genconf.StringSequenceDefinition#getValue
     * <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Value</em>'.
     * @see org.obeonetwork.m2doc.genconf.StringSequenceDefinition#getValue()
     * @see #getStringSequenceDefinition()
     * @generated
     */
    EAttribute getStringSequenceDefinition_Value();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.genconf.StringOrderedSetDefinition <em>String Ordered Set
     * Definition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>String Ordered Set Definition</em>'.
     * @see org.obeonetwork.m2doc.genconf.StringOrderedSetDefinition
     * @generated
     */
    EClass getStringOrderedSetDefinition();

    /**
     * Returns the meta object for the attribute list '{@link org.obeonetwork.m2doc.genconf.StringOrderedSetDefinition#getValue
     * <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Value</em>'.
     * @see org.obeonetwork.m2doc.genconf.StringOrderedSetDefinition#getValue()
     * @see #getStringOrderedSetDefinition()
     * @generated
     */
    EAttribute getStringOrderedSetDefinition_Value();

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
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.genconf.IntegerSequenceDefinition <em>Integer Sequence
     * Definition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Integer Sequence Definition</em>'.
     * @see org.obeonetwork.m2doc.genconf.IntegerSequenceDefinition
     * @generated
     */
    EClass getIntegerSequenceDefinition();

    /**
     * Returns the meta object for the attribute list '{@link org.obeonetwork.m2doc.genconf.IntegerSequenceDefinition#getValue
     * <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Value</em>'.
     * @see org.obeonetwork.m2doc.genconf.IntegerSequenceDefinition#getValue()
     * @see #getIntegerSequenceDefinition()
     * @generated
     */
    EAttribute getIntegerSequenceDefinition_Value();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.genconf.IntegerOrderedSetDefinition <em>Integer Ordered Set
     * Definition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Integer Ordered Set Definition</em>'.
     * @see org.obeonetwork.m2doc.genconf.IntegerOrderedSetDefinition
     * @generated
     */
    EClass getIntegerOrderedSetDefinition();

    /**
     * Returns the meta object for the attribute list '{@link org.obeonetwork.m2doc.genconf.IntegerOrderedSetDefinition#getValue
     * <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Value</em>'.
     * @see org.obeonetwork.m2doc.genconf.IntegerOrderedSetDefinition#getValue()
     * @see #getIntegerOrderedSetDefinition()
     * @generated
     */
    EAttribute getIntegerOrderedSetDefinition_Value();

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
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.genconf.RealSequenceDefinition <em>Real Sequence Definition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Real Sequence Definition</em>'.
     * @see org.obeonetwork.m2doc.genconf.RealSequenceDefinition
     * @generated
     */
    EClass getRealSequenceDefinition();

    /**
     * Returns the meta object for the attribute list '{@link org.obeonetwork.m2doc.genconf.RealSequenceDefinition#getValue
     * <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Value</em>'.
     * @see org.obeonetwork.m2doc.genconf.RealSequenceDefinition#getValue()
     * @see #getRealSequenceDefinition()
     * @generated
     */
    EAttribute getRealSequenceDefinition_Value();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.genconf.RealOrderedSetDefinition <em>Real Ordered Set
     * Definition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Real Ordered Set Definition</em>'.
     * @see org.obeonetwork.m2doc.genconf.RealOrderedSetDefinition
     * @generated
     */
    EClass getRealOrderedSetDefinition();

    /**
     * Returns the meta object for the attribute list '{@link org.obeonetwork.m2doc.genconf.RealOrderedSetDefinition#getValue
     * <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Value</em>'.
     * @see org.obeonetwork.m2doc.genconf.RealOrderedSetDefinition#getValue()
     * @see #getRealOrderedSetDefinition()
     * @generated
     */
    EAttribute getRealOrderedSetDefinition_Value();

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
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.genconf.BooleanSequenceDefinition <em>Boolean Sequence
     * Definition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Boolean Sequence Definition</em>'.
     * @see org.obeonetwork.m2doc.genconf.BooleanSequenceDefinition
     * @generated
     */
    EClass getBooleanSequenceDefinition();

    /**
     * Returns the meta object for the attribute list '{@link org.obeonetwork.m2doc.genconf.BooleanSequenceDefinition#getValue
     * <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Value</em>'.
     * @see org.obeonetwork.m2doc.genconf.BooleanSequenceDefinition#getValue()
     * @see #getBooleanSequenceDefinition()
     * @generated
     */
    EAttribute getBooleanSequenceDefinition_Value();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.genconf.BooleanOrderedSetDefinition <em>Boolean Ordered Set
     * Definition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Boolean Ordered Set Definition</em>'.
     * @see org.obeonetwork.m2doc.genconf.BooleanOrderedSetDefinition
     * @generated
     */
    EClass getBooleanOrderedSetDefinition();

    /**
     * Returns the meta object for the attribute list '{@link org.obeonetwork.m2doc.genconf.BooleanOrderedSetDefinition#getValue
     * <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Value</em>'.
     * @see org.obeonetwork.m2doc.genconf.BooleanOrderedSetDefinition#getValue()
     * @see #getBooleanOrderedSetDefinition()
     * @generated
     */
    EAttribute getBooleanOrderedSetDefinition_Value();

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
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.ModelSequenceDefinitionImpl <em>Model Sequence
         * Definition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.genconf.impl.ModelSequenceDefinitionImpl
         * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getModelSequenceDefinition()
         * @generated
         */
        EClass MODEL_SEQUENCE_DEFINITION = eINSTANCE.getModelSequenceDefinition();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference MODEL_SEQUENCE_DEFINITION__VALUE = eINSTANCE.getModelSequenceDefinition_Value();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.ModelOrderedSetDefinitionImpl <em>Model Ordered Set
         * Definition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.genconf.impl.ModelOrderedSetDefinitionImpl
         * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getModelOrderedSetDefinition()
         * @generated
         */
        EClass MODEL_ORDERED_SET_DEFINITION = eINSTANCE.getModelOrderedSetDefinition();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference MODEL_ORDERED_SET_DEFINITION__VALUE = eINSTANCE.getModelOrderedSetDefinition_Value();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.StringDefinitionImpl <em>String Definition</em>}'
         * class.
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
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.StringSequenceDefinitionImpl <em>String Sequence
         * Definition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.genconf.impl.StringSequenceDefinitionImpl
         * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getStringSequenceDefinition()
         * @generated
         */
        EClass STRING_SEQUENCE_DEFINITION = eINSTANCE.getStringSequenceDefinition();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute STRING_SEQUENCE_DEFINITION__VALUE = eINSTANCE.getStringSequenceDefinition_Value();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.StringOrderedSetDefinitionImpl <em>String Ordered Set
         * Definition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.genconf.impl.StringOrderedSetDefinitionImpl
         * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getStringOrderedSetDefinition()
         * @generated
         */
        EClass STRING_ORDERED_SET_DEFINITION = eINSTANCE.getStringOrderedSetDefinition();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute STRING_ORDERED_SET_DEFINITION__VALUE = eINSTANCE.getStringOrderedSetDefinition_Value();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.IntegerDefinitionImpl <em>Integer Definition</em>}'
         * class.
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
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.IntegerSequenceDefinitionImpl <em>Integer Sequence
         * Definition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.genconf.impl.IntegerSequenceDefinitionImpl
         * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getIntegerSequenceDefinition()
         * @generated
         */
        EClass INTEGER_SEQUENCE_DEFINITION = eINSTANCE.getIntegerSequenceDefinition();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute INTEGER_SEQUENCE_DEFINITION__VALUE = eINSTANCE.getIntegerSequenceDefinition_Value();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.IntegerOrderedSetDefinitionImpl <em>Integer Ordered
         * Set Definition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.genconf.impl.IntegerOrderedSetDefinitionImpl
         * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getIntegerOrderedSetDefinition()
         * @generated
         */
        EClass INTEGER_ORDERED_SET_DEFINITION = eINSTANCE.getIntegerOrderedSetDefinition();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute INTEGER_ORDERED_SET_DEFINITION__VALUE = eINSTANCE.getIntegerOrderedSetDefinition_Value();

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
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.RealSequenceDefinitionImpl <em>Real Sequence
         * Definition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.genconf.impl.RealSequenceDefinitionImpl
         * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getRealSequenceDefinition()
         * @generated
         */
        EClass REAL_SEQUENCE_DEFINITION = eINSTANCE.getRealSequenceDefinition();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REAL_SEQUENCE_DEFINITION__VALUE = eINSTANCE.getRealSequenceDefinition_Value();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.RealOrderedSetDefinitionImpl <em>Real Ordered Set
         * Definition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.genconf.impl.RealOrderedSetDefinitionImpl
         * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getRealOrderedSetDefinition()
         * @generated
         */
        EClass REAL_ORDERED_SET_DEFINITION = eINSTANCE.getRealOrderedSetDefinition();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REAL_ORDERED_SET_DEFINITION__VALUE = eINSTANCE.getRealOrderedSetDefinition_Value();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.BooleanDefinitionImpl <em>Boolean Definition</em>}'
         * class.
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
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.BooleanSequenceDefinitionImpl <em>Boolean Sequence
         * Definition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.genconf.impl.BooleanSequenceDefinitionImpl
         * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getBooleanSequenceDefinition()
         * @generated
         */
        EClass BOOLEAN_SEQUENCE_DEFINITION = eINSTANCE.getBooleanSequenceDefinition();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BOOLEAN_SEQUENCE_DEFINITION__VALUE = eINSTANCE.getBooleanSequenceDefinition_Value();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.BooleanOrderedSetDefinitionImpl <em>Boolean Ordered
         * Set Definition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.genconf.impl.BooleanOrderedSetDefinitionImpl
         * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getBooleanOrderedSetDefinition()
         * @generated
         */
        EClass BOOLEAN_ORDERED_SET_DEFINITION = eINSTANCE.getBooleanOrderedSetDefinition();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BOOLEAN_ORDERED_SET_DEFINITION__VALUE = eINSTANCE.getBooleanOrderedSetDefinition_Value();

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
