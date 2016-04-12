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
/**
 */
package org.obeonetwork.m2doc.ui.genconf.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.obeonetwork.m2doc.ui.genconf.Definition;
import org.obeonetwork.m2doc.ui.genconf.GenconfFactory;
import org.obeonetwork.m2doc.ui.genconf.GenconfPackage;
import org.obeonetwork.m2doc.ui.genconf.Generation;
import org.obeonetwork.m2doc.ui.genconf.ModelDefinition;
import org.obeonetwork.m2doc.ui.genconf.StringDefinition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GenconfPackageImpl extends EPackageImpl implements GenconfPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass generationEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass definitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass modelDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass stringDefinitionEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.obeonetwork.m2doc.ui.genconf.GenconfPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private GenconfPackageImpl() {
        super(eNS_URI, GenconfFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link GenconfPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static GenconfPackage init() {
        if (isInited) return (GenconfPackage)EPackage.Registry.INSTANCE.getEPackage(GenconfPackage.eNS_URI);

        // Obtain or create and register package
        GenconfPackageImpl theGenconfPackage = (GenconfPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof GenconfPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new GenconfPackageImpl());

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
     * @generated
     */
    public EClass getGeneration() {
        return generationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGeneration_Name() {
        return (EAttribute)generationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGeneration_TemplateFileName() {
        return (EAttribute)generationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGeneration_ResultFileName() {
        return (EAttribute)generationEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGeneration_TimeStamped() {
        return (EAttribute)generationEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getGeneration_Definitions() {
        return (EReference)generationEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGeneration_PackagesNSURI() {
        return (EAttribute)generationEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGeneration_ServicesTokens() {
        return (EAttribute)generationEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDefinition() {
        return definitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDefinition_Key() {
        return (EAttribute)definitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getModelDefinition() {
        return modelDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getModelDefinition_Value() {
        return (EReference)modelDefinitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getStringDefinition() {
        return stringDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getStringDefinition_Value() {
        return (EAttribute)stringDefinitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GenconfFactory getGenconfFactory() {
        return (GenconfFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        generationEClass = createEClass(GENERATION);
        createEAttribute(generationEClass, GENERATION__NAME);
        createEAttribute(generationEClass, GENERATION__TEMPLATE_FILE_NAME);
        createEAttribute(generationEClass, GENERATION__RESULT_FILE_NAME);
        createEAttribute(generationEClass, GENERATION__TIME_STAMPED);
        createEReference(generationEClass, GENERATION__DEFINITIONS);
        createEAttribute(generationEClass, GENERATION__PACKAGES_NSURI);
        createEAttribute(generationEClass, GENERATION__SERVICES_TOKENS);

        definitionEClass = createEClass(DEFINITION);
        createEAttribute(definitionEClass, DEFINITION__KEY);

        modelDefinitionEClass = createEClass(MODEL_DEFINITION);
        createEReference(modelDefinitionEClass, MODEL_DEFINITION__VALUE);

        stringDefinitionEClass = createEClass(STRING_DEFINITION);
        createEAttribute(stringDefinitionEClass, STRING_DEFINITION__VALUE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        modelDefinitionEClass.getESuperTypes().add(this.getDefinition());
        stringDefinitionEClass.getESuperTypes().add(this.getDefinition());

        // Initialize classes, features, and operations; add parameters
        initEClass(generationEClass, Generation.class, "Generation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getGeneration_Name(), ecorePackage.getEString(), "name", null, 0, 1, Generation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGeneration_TemplateFileName(), ecorePackage.getEString(), "templateFileName", null, 0, 1, Generation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGeneration_ResultFileName(), ecorePackage.getEString(), "resultFileName", null, 0, 1, Generation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGeneration_TimeStamped(), ecorePackage.getEBoolean(), "timeStamped", "true", 0, 1, Generation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGeneration_Definitions(), this.getDefinition(), null, "definitions", null, 0, -1, Generation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGeneration_PackagesNSURI(), ecorePackage.getEString(), "packagesNSURI", null, 0, -1, Generation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGeneration_ServicesTokens(), ecorePackage.getEString(), "servicesTokens", null, 0, -1, Generation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(definitionEClass, Definition.class, "Definition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDefinition_Key(), ecorePackage.getEString(), "key", null, 0, 1, Definition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(modelDefinitionEClass, ModelDefinition.class, "ModelDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getModelDefinition_Value(), ecorePackage.getEObject(), null, "value", null, 0, 1, ModelDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(stringDefinitionEClass, StringDefinition.class, "StringDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getStringDefinition_Value(), ecorePackage.getEString(), "value", null, 0, 1, StringDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} // GenconfPackageImpl
