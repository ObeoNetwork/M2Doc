/**
 */
package org.obeonetwork.m2doc.tplconf;

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
 * @see org.obeonetwork.m2doc.tplconf.TplconfFactory
 * @model kind="package"
 * @generated
 */
public interface TplconfPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "tplconf";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.obeonetwork.org/m2doc/template/config/1.0";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "tplconf";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    TplconfPackage eINSTANCE = org.obeonetwork.m2doc.tplconf.impl.TplconfPackageImpl.init();

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.tplconf.impl.EPackageMappingImpl <em>EPackage Mapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.tplconf.impl.EPackageMappingImpl
     * @see org.obeonetwork.m2doc.tplconf.impl.TplconfPackageImpl#getEPackageMapping()
     * @generated
     */
    int EPACKAGE_MAPPING = 1;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.tplconf.impl.TemplateVariableImpl <em>Template Variable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.tplconf.impl.TemplateVariableImpl
     * @see org.obeonetwork.m2doc.tplconf.impl.TplconfPackageImpl#getTemplateVariable()
     * @generated
     */
    int TEMPLATE_VARIABLE = 2;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.tplconf.impl.TemplateTypeImpl <em>Template Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.tplconf.impl.TemplateTypeImpl
     * @see org.obeonetwork.m2doc.tplconf.impl.TplconfPackageImpl#getTemplateType()
     * @generated
     */
    int TEMPLATE_TYPE = 3;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.tplconf.impl.ScalarTypeImpl <em>Scalar Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.tplconf.impl.ScalarTypeImpl
     * @see org.obeonetwork.m2doc.tplconf.impl.TplconfPackageImpl#getScalarType()
     * @generated
     */
    int SCALAR_TYPE = 4;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.tplconf.impl.StructuredTypeImpl <em>Structured Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.tplconf.impl.StructuredTypeImpl
     * @see org.obeonetwork.m2doc.tplconf.impl.TplconfPackageImpl#getStructuredType()
     * @generated
     */
    int STRUCTURED_TYPE = 5;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.tplconf.impl.TemplateConfigImpl <em>Template Config</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.tplconf.impl.TemplateConfigImpl
     * @see org.obeonetwork.m2doc.tplconf.impl.TplconfPackageImpl#getTemplateConfig()
     * @generated
     */
    int TEMPLATE_CONFIG = 0;

    /**
     * The feature id for the '<em><b>Mappings</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE_CONFIG__MAPPINGS = 0;

    /**
     * The feature id for the '<em><b>Variables</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE_CONFIG__VARIABLES = 1;

    /**
     * The feature id for the '<em><b>Types By Name</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE_CONFIG__TYPES_BY_NAME = 2;

    /**
     * The number of structural features of the '<em>Template Config</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE_CONFIG_FEATURE_COUNT = 3;

    /**
     * The number of operations of the '<em>Template Config</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE_CONFIG_OPERATION_COUNT = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EPACKAGE_MAPPING__NAME = 0;

    /**
     * The feature id for the '<em><b>Uri</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EPACKAGE_MAPPING__URI = 1;

    /**
     * The feature id for the '<em><b>EPackage</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EPACKAGE_MAPPING__EPACKAGE = 2;

    /**
     * The number of structural features of the '<em>EPackage Mapping</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EPACKAGE_MAPPING_FEATURE_COUNT = 3;

    /**
     * The number of operations of the '<em>EPackage Mapping</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EPACKAGE_MAPPING_OPERATION_COUNT = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE_VARIABLE__NAME = 0;

    /**
     * The feature id for the '<em><b>Type Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE_VARIABLE__TYPE_NAME = 1;

    /**
     * The feature id for the '<em><b>Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE_VARIABLE__TYPE = 2;

    /**
     * The number of structural features of the '<em>Template Variable</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE_VARIABLE_FEATURE_COUNT = 3;

    /**
     * The number of operations of the '<em>Template Variable</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE_VARIABLE_OPERATION_COUNT = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE_TYPE__NAME = 0;

    /**
     * The number of structural features of the '<em>Template Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE_TYPE_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Template Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE_TYPE_OPERATION_COUNT = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SCALAR_TYPE__NAME = TEMPLATE_TYPE__NAME;

    /**
     * The number of structural features of the '<em>Scalar Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SCALAR_TYPE_FEATURE_COUNT = TEMPLATE_TYPE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Scalar Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SCALAR_TYPE_OPERATION_COUNT = TEMPLATE_TYPE_OPERATION_COUNT + 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STRUCTURED_TYPE__NAME = TEMPLATE_TYPE__NAME;

    /**
     * The feature id for the '<em><b>Mapping Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STRUCTURED_TYPE__MAPPING_NAME = TEMPLATE_TYPE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Mapping</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STRUCTURED_TYPE__MAPPING = TEMPLATE_TYPE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>EClassifier</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STRUCTURED_TYPE__ECLASSIFIER = TEMPLATE_TYPE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Structured Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STRUCTURED_TYPE_FEATURE_COUNT = TEMPLATE_TYPE_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>Structured Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STRUCTURED_TYPE_OPERATION_COUNT = TEMPLATE_TYPE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.tplconf.impl.StringToTypeMapEntryImpl <em>String To Type Map Entry</em>}'
     * class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.tplconf.impl.StringToTypeMapEntryImpl
     * @see org.obeonetwork.m2doc.tplconf.impl.TplconfPackageImpl#getStringToTypeMapEntry()
     * @generated
     */
    int STRING_TO_TYPE_MAP_ENTRY = 6;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STRING_TO_TYPE_MAP_ENTRY__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STRING_TO_TYPE_MAP_ENTRY__VALUE = 1;

    /**
     * The number of structural features of the '<em>String To Type Map Entry</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STRING_TO_TYPE_MAP_ENTRY_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>String To Type Map Entry</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STRING_TO_TYPE_MAP_ENTRY_OPERATION_COUNT = 0;

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.tplconf.EPackageMapping <em>EPackage Mapping</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>EPackage Mapping</em>'.
     * @see org.obeonetwork.m2doc.tplconf.EPackageMapping
     * @generated
     */
    EClass getEPackageMapping();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.tplconf.EPackageMapping#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.obeonetwork.m2doc.tplconf.EPackageMapping#getName()
     * @see #getEPackageMapping()
     * @generated
     */
    EAttribute getEPackageMapping_Name();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.tplconf.EPackageMapping#getUri <em>Uri</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Uri</em>'.
     * @see org.obeonetwork.m2doc.tplconf.EPackageMapping#getUri()
     * @see #getEPackageMapping()
     * @generated
     */
    EAttribute getEPackageMapping_Uri();

    /**
     * Returns the meta object for the reference '{@link org.obeonetwork.m2doc.tplconf.EPackageMapping#getEPackage <em>EPackage</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>EPackage</em>'.
     * @see org.obeonetwork.m2doc.tplconf.EPackageMapping#getEPackage()
     * @see #getEPackageMapping()
     * @generated
     */
    EReference getEPackageMapping_EPackage();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.tplconf.TemplateVariable <em>Template Variable</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Template Variable</em>'.
     * @see org.obeonetwork.m2doc.tplconf.TemplateVariable
     * @generated
     */
    EClass getTemplateVariable();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.tplconf.TemplateVariable#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.obeonetwork.m2doc.tplconf.TemplateVariable#getName()
     * @see #getTemplateVariable()
     * @generated
     */
    EAttribute getTemplateVariable_Name();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.tplconf.TemplateVariable#getTypeName <em>Type Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Type Name</em>'.
     * @see org.obeonetwork.m2doc.tplconf.TemplateVariable#getTypeName()
     * @see #getTemplateVariable()
     * @generated
     */
    EAttribute getTemplateVariable_TypeName();

    /**
     * Returns the meta object for the reference '{@link org.obeonetwork.m2doc.tplconf.TemplateVariable#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Type</em>'.
     * @see org.obeonetwork.m2doc.tplconf.TemplateVariable#getType()
     * @see #getTemplateVariable()
     * @generated
     */
    EReference getTemplateVariable_Type();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.tplconf.TemplateType <em>Template Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Template Type</em>'.
     * @see org.obeonetwork.m2doc.tplconf.TemplateType
     * @generated
     */
    EClass getTemplateType();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.tplconf.TemplateType#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.obeonetwork.m2doc.tplconf.TemplateType#getName()
     * @see #getTemplateType()
     * @generated
     */
    EAttribute getTemplateType_Name();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.tplconf.ScalarType <em>Scalar Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Scalar Type</em>'.
     * @see org.obeonetwork.m2doc.tplconf.ScalarType
     * @generated
     */
    EClass getScalarType();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.tplconf.StructuredType <em>Structured Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Structured Type</em>'.
     * @see org.obeonetwork.m2doc.tplconf.StructuredType
     * @generated
     */
    EClass getStructuredType();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.tplconf.StructuredType#getMappingName <em>Mapping
     * Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Mapping Name</em>'.
     * @see org.obeonetwork.m2doc.tplconf.StructuredType#getMappingName()
     * @see #getStructuredType()
     * @generated
     */
    EAttribute getStructuredType_MappingName();

    /**
     * Returns the meta object for the reference '{@link org.obeonetwork.m2doc.tplconf.StructuredType#getMapping <em>Mapping</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Mapping</em>'.
     * @see org.obeonetwork.m2doc.tplconf.StructuredType#getMapping()
     * @see #getStructuredType()
     * @generated
     */
    EReference getStructuredType_Mapping();

    /**
     * Returns the meta object for the reference '{@link org.obeonetwork.m2doc.tplconf.StructuredType#getEClassifier <em>EClassifier</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>EClassifier</em>'.
     * @see org.obeonetwork.m2doc.tplconf.StructuredType#getEClassifier()
     * @see #getStructuredType()
     * @generated
     */
    EReference getStructuredType_EClassifier();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.tplconf.TemplateConfig <em>Template Config</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Template Config</em>'.
     * @see org.obeonetwork.m2doc.tplconf.TemplateConfig
     * @generated
     */
    EClass getTemplateConfig();

    /**
     * Returns the meta object for the containment reference list '{@link org.obeonetwork.m2doc.tplconf.TemplateConfig#getMappings
     * <em>Mappings</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '<em>Mappings</em>'.
     * @see org.obeonetwork.m2doc.tplconf.TemplateConfig#getMappings()
     * @see #getTemplateConfig()
     * @generated
     */
    EReference getTemplateConfig_Mappings();

    /**
     * Returns the meta object for the containment reference list '{@link org.obeonetwork.m2doc.tplconf.TemplateConfig#getVariables
     * <em>Variables</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '<em>Variables</em>'.
     * @see org.obeonetwork.m2doc.tplconf.TemplateConfig#getVariables()
     * @see #getTemplateConfig()
     * @generated
     */
    EReference getTemplateConfig_Variables();

    /**
     * Returns the meta object for the map '{@link org.obeonetwork.m2doc.tplconf.TemplateConfig#getTypesByName <em>Types By Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the map '<em>Types By Name</em>'.
     * @see org.obeonetwork.m2doc.tplconf.TemplateConfig#getTypesByName()
     * @see #getTemplateConfig()
     * @generated
     */
    EReference getTemplateConfig_TypesByName();

    /**
     * Returns the meta object for class '{@link java.util.Map.Entry <em>String To Type Map Entry</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>String To Type Map Entry</em>'.
     * @see java.util.Map.Entry
     * @model keyDataType="org.eclipse.emf.ecore.EString"
     *        valueType="org.obeonetwork.m2doc.tplconf.TemplateType" valueContainment="true"
     * @generated
     */
    EClass getStringToTypeMapEntry();

    /**
     * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Key</em>'.
     * @see java.util.Map.Entry
     * @see #getStringToTypeMapEntry()
     * @generated
     */
    EAttribute getStringToTypeMapEntry_Key();

    /**
     * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Value</em>'.
     * @see java.util.Map.Entry
     * @see #getStringToTypeMapEntry()
     * @generated
     */
    EReference getStringToTypeMapEntry_Value();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    TplconfFactory getTplconfFactory();

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
         * The meta object literal for the '{@link org.obeonetwork.m2doc.tplconf.impl.EPackageMappingImpl <em>EPackage Mapping</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.tplconf.impl.EPackageMappingImpl
         * @see org.obeonetwork.m2doc.tplconf.impl.TplconfPackageImpl#getEPackageMapping()
         * @generated
         */
        EClass EPACKAGE_MAPPING = eINSTANCE.getEPackageMapping();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EPACKAGE_MAPPING__NAME = eINSTANCE.getEPackageMapping_Name();

        /**
         * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EPACKAGE_MAPPING__URI = eINSTANCE.getEPackageMapping_Uri();

        /**
         * The meta object literal for the '<em><b>EPackage</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EPACKAGE_MAPPING__EPACKAGE = eINSTANCE.getEPackageMapping_EPackage();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.tplconf.impl.TemplateVariableImpl <em>Template Variable</em>}'
         * class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.tplconf.impl.TemplateVariableImpl
         * @see org.obeonetwork.m2doc.tplconf.impl.TplconfPackageImpl#getTemplateVariable()
         * @generated
         */
        EClass TEMPLATE_VARIABLE = eINSTANCE.getTemplateVariable();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TEMPLATE_VARIABLE__NAME = eINSTANCE.getTemplateVariable_Name();

        /**
         * The meta object literal for the '<em><b>Type Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TEMPLATE_VARIABLE__TYPE_NAME = eINSTANCE.getTemplateVariable_TypeName();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TEMPLATE_VARIABLE__TYPE = eINSTANCE.getTemplateVariable_Type();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.tplconf.impl.TemplateTypeImpl <em>Template Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.tplconf.impl.TemplateTypeImpl
         * @see org.obeonetwork.m2doc.tplconf.impl.TplconfPackageImpl#getTemplateType()
         * @generated
         */
        EClass TEMPLATE_TYPE = eINSTANCE.getTemplateType();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TEMPLATE_TYPE__NAME = eINSTANCE.getTemplateType_Name();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.tplconf.impl.ScalarTypeImpl <em>Scalar Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.tplconf.impl.ScalarTypeImpl
         * @see org.obeonetwork.m2doc.tplconf.impl.TplconfPackageImpl#getScalarType()
         * @generated
         */
        EClass SCALAR_TYPE = eINSTANCE.getScalarType();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.tplconf.impl.StructuredTypeImpl <em>Structured Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.tplconf.impl.StructuredTypeImpl
         * @see org.obeonetwork.m2doc.tplconf.impl.TplconfPackageImpl#getStructuredType()
         * @generated
         */
        EClass STRUCTURED_TYPE = eINSTANCE.getStructuredType();

        /**
         * The meta object literal for the '<em><b>Mapping Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute STRUCTURED_TYPE__MAPPING_NAME = eINSTANCE.getStructuredType_MappingName();

        /**
         * The meta object literal for the '<em><b>Mapping</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference STRUCTURED_TYPE__MAPPING = eINSTANCE.getStructuredType_Mapping();

        /**
         * The meta object literal for the '<em><b>EClassifier</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference STRUCTURED_TYPE__ECLASSIFIER = eINSTANCE.getStructuredType_EClassifier();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.tplconf.impl.TemplateConfigImpl <em>Template Config</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.tplconf.impl.TemplateConfigImpl
         * @see org.obeonetwork.m2doc.tplconf.impl.TplconfPackageImpl#getTemplateConfig()
         * @generated
         */
        EClass TEMPLATE_CONFIG = eINSTANCE.getTemplateConfig();

        /**
         * The meta object literal for the '<em><b>Mappings</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TEMPLATE_CONFIG__MAPPINGS = eINSTANCE.getTemplateConfig_Mappings();

        /**
         * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TEMPLATE_CONFIG__VARIABLES = eINSTANCE.getTemplateConfig_Variables();

        /**
         * The meta object literal for the '<em><b>Types By Name</b></em>' map feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TEMPLATE_CONFIG__TYPES_BY_NAME = eINSTANCE.getTemplateConfig_TypesByName();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.tplconf.impl.StringToTypeMapEntryImpl <em>String To Type Map
         * Entry</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.tplconf.impl.StringToTypeMapEntryImpl
         * @see org.obeonetwork.m2doc.tplconf.impl.TplconfPackageImpl#getStringToTypeMapEntry()
         * @generated
         */
        EClass STRING_TO_TYPE_MAP_ENTRY = eINSTANCE.getStringToTypeMapEntry();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute STRING_TO_TYPE_MAP_ENTRY__KEY = eINSTANCE.getStringToTypeMapEntry_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference STRING_TO_TYPE_MAP_ENTRY__VALUE = eINSTANCE.getStringToTypeMapEntry_Value();

    }

} // TplconfPackage
