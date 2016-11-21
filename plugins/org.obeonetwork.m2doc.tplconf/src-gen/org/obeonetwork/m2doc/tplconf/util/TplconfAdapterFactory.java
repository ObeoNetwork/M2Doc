/**
 */
package org.obeonetwork.m2doc.tplconf.util;

import java.util.Map;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.obeonetwork.m2doc.tplconf.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * 
 * @see org.obeonetwork.m2doc.tplconf.TplconfPackage
 * @generated
 */
public class TplconfAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static TplconfPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public TplconfAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = TplconfPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * 
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TplconfSwitch<Adapter> modelSwitch = new TplconfSwitch<Adapter>() {
        @Override
        public Adapter caseTemplateConfig(TemplateConfig object) {
            return createTemplateConfigAdapter();
        }

        @Override
        public Adapter caseEPackageMapping(EPackageMapping object) {
            return createEPackageMappingAdapter();
        }

        @Override
        public Adapter caseTemplateVariable(TemplateVariable object) {
            return createTemplateVariableAdapter();
        }

        @Override
        public Adapter caseTemplateType(TemplateType object) {
            return createTemplateTypeAdapter();
        }

        @Override
        public Adapter caseScalarType(ScalarType object) {
            return createScalarTypeAdapter();
        }

        @Override
        public Adapter caseStructuredType(StructuredType object) {
            return createStructuredTypeAdapter();
        }

        @Override
        public Adapter caseStringToTypeMapEntry(Map.Entry<String, TemplateType> object) {
            return createStringToTypeMapEntryAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param target
     *            the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.tplconf.EPackageMapping <em>EPackage Mapping</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.tplconf.EPackageMapping
     * @generated
     */
    public Adapter createEPackageMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.tplconf.TemplateVariable <em>Template Variable</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.tplconf.TemplateVariable
     * @generated
     */
    public Adapter createTemplateVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.tplconf.TemplateType <em>Template Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.tplconf.TemplateType
     * @generated
     */
    public Adapter createTemplateTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.tplconf.ScalarType <em>Scalar Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.tplconf.ScalarType
     * @generated
     */
    public Adapter createScalarTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.tplconf.StructuredType <em>Structured Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.tplconf.StructuredType
     * @generated
     */
    public Adapter createStructuredTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.tplconf.TemplateConfig <em>Template Config</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.tplconf.TemplateConfig
     * @generated
     */
    public Adapter createTemplateConfigAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>String To Type Map Entry</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see java.util.Map.Entry
     * @generated
     */
    public Adapter createStringToTypeMapEntryAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // TplconfAdapterFactory
