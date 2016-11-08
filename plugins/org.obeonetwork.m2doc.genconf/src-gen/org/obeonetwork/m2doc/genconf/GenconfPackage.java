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
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.obeonetwork.m2doc.genconf.GenconfFactory
 * @model kind="package"
 * @generated
 */
public interface GenconfPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "genconf";

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "http://www.obeonetwork.org/m2doc/genconf/1.0";

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "genconf";

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	GenconfPackage eINSTANCE = org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl.init();

	/**
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.GenerationImpl <em>Generation</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.genconf.impl.GenerationImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getGeneration()
     * @generated
     */
	int GENERATION = 0;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GENERATION__NAME = 0;

	/**
     * The feature id for the '<em><b>Template File Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GENERATION__TEMPLATE_FILE_NAME = 1;

	/**
     * The feature id for the '<em><b>Result File Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GENERATION__RESULT_FILE_NAME = 2;

	/**
     * The feature id for the '<em><b>Time Stamped</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GENERATION__TIME_STAMPED = 3;

	/**
     * The feature id for the '<em><b>Refresh Representations</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GENERATION__REFRESH_REPRESENTATIONS = 4;

    /**
     * The feature id for the '<em><b>Definitions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GENERATION__DEFINITIONS = 5;

	/**
     * The feature id for the '<em><b>Packages NSURI</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GENERATION__PACKAGES_NSURI = 6;

	/**
     * The feature id for the '<em><b>Services Tokens</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GENERATION__SERVICES_TOKENS = 7;

	/**
     * The number of structural features of the '<em>Generation</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GENERATION_FEATURE_COUNT = 8;

	/**
     * The number of operations of the '<em>Generation</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GENERATION_OPERATION_COUNT = 0;

	/**
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.DefinitionImpl <em>Definition</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.genconf.impl.DefinitionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getDefinition()
     * @generated
     */
	int DEFINITION = 1;

	/**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DEFINITION__KEY = 0;

	/**
     * The number of structural features of the '<em>Definition</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DEFINITION_FEATURE_COUNT = 1;

	/**
     * The number of operations of the '<em>Definition</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DEFINITION_OPERATION_COUNT = 0;

	/**
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.ModelDefinitionImpl <em>Model Definition</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.genconf.impl.ModelDefinitionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getModelDefinition()
     * @generated
     */
	int MODEL_DEFINITION = 2;

	/**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MODEL_DEFINITION__KEY = DEFINITION__KEY;

	/**
     * The feature id for the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MODEL_DEFINITION__VALUE = DEFINITION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MODEL_DEFINITION__TYPE = DEFINITION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Model Definition</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MODEL_DEFINITION_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 2;

	/**
     * The number of operations of the '<em>Model Definition</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MODEL_DEFINITION_OPERATION_COUNT = DEFINITION_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link org.obeonetwork.m2doc.genconf.impl.StringDefinitionImpl <em>String Definition</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.genconf.impl.StringDefinitionImpl
     * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getStringDefinition()
     * @generated
     */
	int STRING_DEFINITION = 3;

	/**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STRING_DEFINITION__KEY = DEFINITION__KEY;

	/**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STRING_DEFINITION__VALUE = DEFINITION_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>String Definition</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STRING_DEFINITION_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 1;

	/**
     * The number of operations of the '<em>String Definition</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STRING_DEFINITION_OPERATION_COUNT = DEFINITION_OPERATION_COUNT + 0;


	/**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.genconf.Generation <em>Generation</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Generation</em>'.
     * @see org.obeonetwork.m2doc.genconf.Generation
     * @generated
     */
	EClass getGeneration();

	/**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.genconf.Generation#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.obeonetwork.m2doc.genconf.Generation#getName()
     * @see #getGeneration()
     * @generated
     */
	EAttribute getGeneration_Name();

	/**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.genconf.Generation#getTemplateFileName <em>Template File Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
     * @return the meta object for the attribute '<em>Result File Name</em>'.
     * @see org.obeonetwork.m2doc.genconf.Generation#getResultFileName()
     * @see #getGeneration()
     * @generated
     */
	EAttribute getGeneration_ResultFileName();

	/**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.genconf.Generation#isTimeStamped <em>Time Stamped</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Time Stamped</em>'.
     * @see org.obeonetwork.m2doc.genconf.Generation#isTimeStamped()
     * @see #getGeneration()
     * @generated
     */
	EAttribute getGeneration_TimeStamped();

	/**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.genconf.Generation#isRefreshRepresentations <em>Refresh Representations</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Refresh Representations</em>'.
     * @see org.obeonetwork.m2doc.genconf.Generation#isRefreshRepresentations()
     * @see #getGeneration()
     * @generated
     */
    EAttribute getGeneration_RefreshRepresentations();

    /**
     * Returns the meta object for the containment reference list '{@link org.obeonetwork.m2doc.genconf.Generation#getDefinitions <em>Definitions</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Definitions</em>'.
     * @see org.obeonetwork.m2doc.genconf.Generation#getDefinitions()
     * @see #getGeneration()
     * @generated
     */
	EReference getGeneration_Definitions();

	/**
     * Returns the meta object for the attribute list '{@link org.obeonetwork.m2doc.genconf.Generation#getPackagesNSURI <em>Packages NSURI</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Packages NSURI</em>'.
     * @see org.obeonetwork.m2doc.genconf.Generation#getPackagesNSURI()
     * @see #getGeneration()
     * @generated
     */
	EAttribute getGeneration_PackagesNSURI();

	/**
     * Returns the meta object for the attribute list '{@link org.obeonetwork.m2doc.genconf.Generation#getServicesTokens <em>Services Tokens</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Services Tokens</em>'.
     * @see org.obeonetwork.m2doc.genconf.Generation#getServicesTokens()
     * @see #getGeneration()
     * @generated
     */
	EAttribute getGeneration_ServicesTokens();

	/**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.genconf.Definition <em>Definition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Definition</em>'.
     * @see org.obeonetwork.m2doc.genconf.Definition
     * @generated
     */
	EClass getDefinition();

	/**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.genconf.Definition#getKey <em>Key</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
     * @return the meta object for class '<em>Model Definition</em>'.
     * @see org.obeonetwork.m2doc.genconf.ModelDefinition
     * @generated
     */
	EClass getModelDefinition();

	/**
     * Returns the meta object for the reference '{@link org.obeonetwork.m2doc.genconf.ModelDefinition#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Value</em>'.
     * @see org.obeonetwork.m2doc.genconf.ModelDefinition#getValue()
     * @see #getModelDefinition()
     * @generated
     */
	EReference getModelDefinition_Value();

	/**
     * Returns the meta object for the reference '{@link org.obeonetwork.m2doc.genconf.ModelDefinition#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Type</em>'.
     * @see org.obeonetwork.m2doc.genconf.ModelDefinition#getType()
     * @see #getModelDefinition()
     * @generated
     */
    EReference getModelDefinition_Type();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.genconf.StringDefinition <em>String Definition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>String Definition</em>'.
     * @see org.obeonetwork.m2doc.genconf.StringDefinition
     * @generated
     */
	EClass getStringDefinition();

	/**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.genconf.StringDefinition#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.obeonetwork.m2doc.genconf.StringDefinition#getValue()
     * @see #getStringDefinition()
     * @generated
     */
	EAttribute getStringDefinition_Value();

	/**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	GenconfFactory getGenconfFactory();

	/**
     * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
     * @generated
     */
	interface Literals {
		/**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.GenerationImpl <em>Generation</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.genconf.impl.GenerationImpl
         * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getGeneration()
         * @generated
         */
		EClass GENERATION = eINSTANCE.getGeneration();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute GENERATION__NAME = eINSTANCE.getGeneration_Name();

		/**
         * The meta object literal for the '<em><b>Template File Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute GENERATION__TEMPLATE_FILE_NAME = eINSTANCE.getGeneration_TemplateFileName();

		/**
         * The meta object literal for the '<em><b>Result File Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute GENERATION__RESULT_FILE_NAME = eINSTANCE.getGeneration_ResultFileName();

		/**
         * The meta object literal for the '<em><b>Time Stamped</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute GENERATION__TIME_STAMPED = eINSTANCE.getGeneration_TimeStamped();

		/**
         * The meta object literal for the '<em><b>Refresh Representations</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute GENERATION__REFRESH_REPRESENTATIONS = eINSTANCE.getGeneration_RefreshRepresentations();

        /**
         * The meta object literal for the '<em><b>Definitions</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference GENERATION__DEFINITIONS = eINSTANCE.getGeneration_Definitions();

		/**
         * The meta object literal for the '<em><b>Packages NSURI</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute GENERATION__PACKAGES_NSURI = eINSTANCE.getGeneration_PackagesNSURI();

		/**
         * The meta object literal for the '<em><b>Services Tokens</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute GENERATION__SERVICES_TOKENS = eINSTANCE.getGeneration_ServicesTokens();

		/**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.DefinitionImpl <em>Definition</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.genconf.impl.DefinitionImpl
         * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getDefinition()
         * @generated
         */
		EClass DEFINITION = eINSTANCE.getDefinition();

		/**
         * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DEFINITION__KEY = eINSTANCE.getDefinition_Key();

		/**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.ModelDefinitionImpl <em>Model Definition</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.genconf.impl.ModelDefinitionImpl
         * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getModelDefinition()
         * @generated
         */
		EClass MODEL_DEFINITION = eINSTANCE.getModelDefinition();

		/**
         * The meta object literal for the '<em><b>Value</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MODEL_DEFINITION__VALUE = eINSTANCE.getModelDefinition_Value();

		/**
         * The meta object literal for the '<em><b>Type</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MODEL_DEFINITION__TYPE = eINSTANCE.getModelDefinition_Type();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.genconf.impl.StringDefinitionImpl <em>String Definition</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.genconf.impl.StringDefinitionImpl
         * @see org.obeonetwork.m2doc.genconf.impl.GenconfPackageImpl#getStringDefinition()
         * @generated
         */
		EClass STRING_DEFINITION = eINSTANCE.getStringDefinition();

		/**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute STRING_DEFINITION__VALUE = eINSTANCE.getStringDefinition_Value();

	}

} //GenconfPackage
