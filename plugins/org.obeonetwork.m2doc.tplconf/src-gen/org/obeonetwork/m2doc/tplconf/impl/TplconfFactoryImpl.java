/**
 */
package org.obeonetwork.m2doc.tplconf.impl;

import java.util.Map;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.obeonetwork.m2doc.tplconf.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class TplconfFactoryImpl extends EFactoryImpl implements TplconfFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public static TplconfFactory init() {
        try {
            TplconfFactory theTplconfFactory = (TplconfFactory) EPackage.Registry.INSTANCE
                    .getEFactory(TplconfPackage.eNS_URI);
            if (theTplconfFactory != null) {
                return theTplconfFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new TplconfFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public TplconfFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case TplconfPackage.TEMPLATE_CONFIG:
                return createTemplateConfig();
            case TplconfPackage.EPACKAGE_MAPPING:
                return createEPackageMapping();
            case TplconfPackage.TEMPLATE_VARIABLE:
                return createTemplateVariable();
            case TplconfPackage.SCALAR_TYPE:
                return createScalarType();
            case TplconfPackage.STRUCTURED_TYPE:
                return createStructuredType();
            case TplconfPackage.STRING_TO_TYPE_MAP_ENTRY:
                return (EObject) createStringToTypeMapEntry();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EPackageMapping createEPackageMapping() {
        EPackageMappingImpl ePackageMapping = new EPackageMappingImpl();
        return ePackageMapping;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public TemplateVariable createTemplateVariable() {
        TemplateVariableImpl templateVariable = new TemplateVariableImpl();
        return templateVariable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public ScalarType createScalarType() {
        ScalarTypeImpl scalarType = new ScalarTypeImpl();
        return scalarType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public StructuredType createStructuredType() {
        StructuredTypeImpl structuredType = new StructuredTypeImpl();
        return structuredType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public TemplateConfig createTemplateConfig() {
        TemplateConfigImpl templateConfig = new TemplateConfigImpl();
        return templateConfig;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public Map.Entry<String, TemplateType> createStringToTypeMapEntry() {
        StringToTypeMapEntryImpl stringToTypeMapEntry = new StringToTypeMapEntryImpl();
        return stringToTypeMapEntry;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public TplconfPackage getTplconfPackage() {
        return (TplconfPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static TplconfPackage getPackage() {
        return TplconfPackage.eINSTANCE;
    }

} // TplconfFactoryImpl
