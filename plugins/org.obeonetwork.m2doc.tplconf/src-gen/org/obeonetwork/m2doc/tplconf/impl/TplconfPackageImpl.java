/**
 */
package org.obeonetwork.m2doc.tplconf.impl;

import java.util.Map;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.obeonetwork.m2doc.tplconf.EPackageMapping;
import org.obeonetwork.m2doc.tplconf.ScalarType;
import org.obeonetwork.m2doc.tplconf.StructuredType;
import org.obeonetwork.m2doc.tplconf.TemplateConfig;
import org.obeonetwork.m2doc.tplconf.TemplateType;
import org.obeonetwork.m2doc.tplconf.TemplateVariable;
import org.obeonetwork.m2doc.tplconf.TplconfFactory;
import org.obeonetwork.m2doc.tplconf.TplconfPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class TplconfPackageImpl extends EPackageImpl implements TplconfPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass ePackageMappingEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass templateVariableEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass templateTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass scalarTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass structuredTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass templateConfigEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass stringToTypeMapEntryEClass = null;

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
     * @see org.obeonetwork.m2doc.tplconf.TplconfPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private TplconfPackageImpl() {
        super(eNS_URI, TplconfFactory.eINSTANCE);
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
     * This method is used to initialize {@link TplconfPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static TplconfPackage init() {
        if (isInited)
            return (TplconfPackage) EPackage.Registry.INSTANCE.getEPackage(TplconfPackage.eNS_URI);

        // Obtain or create and register package
        TplconfPackageImpl theTplconfPackage = (TplconfPackageImpl) (EPackage.Registry.INSTANCE
                .get(eNS_URI) instanceof TplconfPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                        : new TplconfPackageImpl());

        isInited = true;

        // Create package meta-data objects
        theTplconfPackage.createPackageContents();

        // Initialize created meta-data
        theTplconfPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theTplconfPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(TplconfPackage.eNS_URI, theTplconfPackage);
        return theTplconfPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEPackageMapping() {
        return ePackageMappingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEPackageMapping_Name() {
        return (EAttribute) ePackageMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEPackageMapping_Uri() {
        return (EAttribute) ePackageMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEPackageMapping_EPackage() {
        return (EReference) ePackageMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTemplateVariable() {
        return templateVariableEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTemplateVariable_Name() {
        return (EAttribute) templateVariableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTemplateVariable_TypeName() {
        return (EAttribute) templateVariableEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTemplateVariable_Type() {
        return (EReference) templateVariableEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTemplateType() {
        return templateTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTemplateType_Name() {
        return (EAttribute) templateTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getScalarType() {
        return scalarTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getStructuredType() {
        return structuredTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getStructuredType_MappingName() {
        return (EAttribute) structuredTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getStructuredType_Mapping() {
        return (EReference) structuredTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getStructuredType_EClassifier() {
        return (EReference) structuredTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTemplateConfig() {
        return templateConfigEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTemplateConfig_Mappings() {
        return (EReference) templateConfigEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTemplateConfig_Variables() {
        return (EReference) templateConfigEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTemplateConfig_TypesByName() {
        return (EReference) templateConfigEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getStringToTypeMapEntry() {
        return stringToTypeMapEntryEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getStringToTypeMapEntry_Key() {
        return (EAttribute) stringToTypeMapEntryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getStringToTypeMapEntry_Value() {
        return (EReference) stringToTypeMapEntryEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public TplconfFactory getTplconfFactory() {
        return (TplconfFactory) getEFactoryInstance();
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
        templateConfigEClass = createEClass(TEMPLATE_CONFIG);
        createEReference(templateConfigEClass, TEMPLATE_CONFIG__MAPPINGS);
        createEReference(templateConfigEClass, TEMPLATE_CONFIG__VARIABLES);
        createEReference(templateConfigEClass, TEMPLATE_CONFIG__TYPES_BY_NAME);

        ePackageMappingEClass = createEClass(EPACKAGE_MAPPING);
        createEAttribute(ePackageMappingEClass, EPACKAGE_MAPPING__NAME);
        createEAttribute(ePackageMappingEClass, EPACKAGE_MAPPING__URI);
        createEReference(ePackageMappingEClass, EPACKAGE_MAPPING__EPACKAGE);

        templateVariableEClass = createEClass(TEMPLATE_VARIABLE);
        createEAttribute(templateVariableEClass, TEMPLATE_VARIABLE__NAME);
        createEAttribute(templateVariableEClass, TEMPLATE_VARIABLE__TYPE_NAME);
        createEReference(templateVariableEClass, TEMPLATE_VARIABLE__TYPE);

        templateTypeEClass = createEClass(TEMPLATE_TYPE);
        createEAttribute(templateTypeEClass, TEMPLATE_TYPE__NAME);

        scalarTypeEClass = createEClass(SCALAR_TYPE);

        structuredTypeEClass = createEClass(STRUCTURED_TYPE);
        createEAttribute(structuredTypeEClass, STRUCTURED_TYPE__MAPPING_NAME);
        createEReference(structuredTypeEClass, STRUCTURED_TYPE__MAPPING);
        createEReference(structuredTypeEClass, STRUCTURED_TYPE__ECLASSIFIER);

        stringToTypeMapEntryEClass = createEClass(STRING_TO_TYPE_MAP_ENTRY);
        createEAttribute(stringToTypeMapEntryEClass, STRING_TO_TYPE_MAP_ENTRY__KEY);
        createEReference(stringToTypeMapEntryEClass, STRING_TO_TYPE_MAP_ENTRY__VALUE);
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
        scalarTypeEClass.getESuperTypes().add(this.getTemplateType());
        structuredTypeEClass.getESuperTypes().add(this.getTemplateType());

        // Initialize classes, features, and operations; add parameters
        initEClass(templateConfigEClass, TemplateConfig.class, "TemplateConfig", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTemplateConfig_Mappings(), this.getEPackageMapping(), null, "mappings", null, 0, -1,
                TemplateConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTemplateConfig_Variables(), this.getTemplateVariable(), null, "variables", null, 0, -1,
                TemplateConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTemplateConfig_TypesByName(), this.getStringToTypeMapEntry(), null, "typesByName", null, 0,
                -1, TemplateConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(ePackageMappingEClass, EPackageMapping.class, "EPackageMapping", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getEPackageMapping_Name(), ecorePackage.getEString(), "name", null, 0, 1, EPackageMapping.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEPackageMapping_Uri(), ecorePackage.getEString(), "uri", null, 0, 1, EPackageMapping.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEPackageMapping_EPackage(), ecorePackage.getEObject(), null, "ePackage", null, 0, 1,
                EPackageMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(templateVariableEClass, TemplateVariable.class, "TemplateVariable", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTemplateVariable_Name(), ecorePackage.getEString(), "name", null, 0, 1,
                TemplateVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTemplateVariable_TypeName(), ecorePackage.getEString(), "typeName", null, 0, 1,
                TemplateVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getTemplateVariable_Type(), this.getTemplateType(), null, "type", null, 0, 1,
                TemplateVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(templateTypeEClass, TemplateType.class, "TemplateType", IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTemplateType_Name(), ecorePackage.getEString(), "name", null, 0, 1, TemplateType.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scalarTypeEClass, ScalarType.class, "ScalarType", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);

        initEClass(structuredTypeEClass, StructuredType.class, "StructuredType", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getStructuredType_MappingName(), ecorePackage.getEString(), "mappingName", null, 0, 1,
                StructuredType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getStructuredType_Mapping(), this.getEPackageMapping(), null, "mapping", null, 0, 1,
                StructuredType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getStructuredType_EClassifier(), ecorePackage.getEObject(), null, "eClassifier", null, 0, 1,
                StructuredType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(stringToTypeMapEntryEClass, Map.Entry.class, "StringToTypeMapEntry", !IS_ABSTRACT, !IS_INTERFACE,
                !IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getStringToTypeMapEntry_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getStringToTypeMapEntry_Value(), this.getTemplateType(), null, "value", null, 0, 1,
                Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} // TplconfPackageImpl
