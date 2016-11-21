/**
 */
package org.obeonetwork.m2doc.tplconf;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * 
 * @see org.obeonetwork.m2doc.tplconf.TplconfPackage
 * @generated
 */
public interface TplconfFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    TplconfFactory eINSTANCE = org.obeonetwork.m2doc.tplconf.impl.TplconfFactoryImpl.init();

    /**
     * Returns a new object of class '<em>EPackage Mapping</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>EPackage Mapping</em>'.
     * @generated
     */
    EPackageMapping createEPackageMapping();

    /**
     * Returns a new object of class '<em>Template Variable</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Template Variable</em>'.
     * @generated
     */
    TemplateVariable createTemplateVariable();

    /**
     * Returns a new object of class '<em>Scalar Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Scalar Type</em>'.
     * @generated
     */
    ScalarType createScalarType();

    /**
     * Returns a new object of class '<em>Structured Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Structured Type</em>'.
     * @generated
     */
    StructuredType createStructuredType();

    /**
     * Returns a new object of class '<em>Template Config</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Template Config</em>'.
     * @generated
     */
    TemplateConfig createTemplateConfig();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the package supported by this factory.
     * @generated
     */
    TplconfPackage getTplconfPackage();

} // TplconfFactory
