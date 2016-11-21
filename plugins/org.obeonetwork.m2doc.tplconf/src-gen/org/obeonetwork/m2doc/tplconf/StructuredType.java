/**
 */
package org.obeonetwork.m2doc.tplconf;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Structured Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.tplconf.StructuredType#getMappingName <em>Mapping Name</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.tplconf.StructuredType#getMapping <em>Mapping</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.tplconf.StructuredType#getEClassifier <em>EClassifier</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.tplconf.TplconfPackage#getStructuredType()
 * @model
 * @generated
 */
public interface StructuredType extends TemplateType {
    /**
     * Returns the value of the '<em><b>Mapping Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mapping Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Mapping Name</em>' attribute.
     * @see #setMappingName(String)
     * @see org.obeonetwork.m2doc.tplconf.TplconfPackage#getStructuredType_MappingName()
     * @model id="true"
     * @generated
     */
    String getMappingName();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.tplconf.StructuredType#getMappingName <em>Mapping Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Mapping Name</em>' attribute.
     * @see #getMappingName()
     * @generated
     */
    void setMappingName(String value);

    /**
     * Returns the value of the '<em><b>Mapping</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mapping</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Mapping</em>' reference.
     * @see #setMapping(EPackageMapping)
     * @see org.obeonetwork.m2doc.tplconf.TplconfPackage#getStructuredType_Mapping()
     * @model
     * @generated
     */
    EPackageMapping getMapping();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.tplconf.StructuredType#getMapping <em>Mapping</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Mapping</em>' reference.
     * @see #getMapping()
     * @generated
     */
    void setMapping(EPackageMapping value);

    /**
     * Returns the value of the '<em><b>EClassifier</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>EClassifier</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>EClassifier</em>' reference.
     * @see #setEClassifier(EObject)
     * @see org.obeonetwork.m2doc.tplconf.TplconfPackage#getStructuredType_EClassifier()
     * @model
     * @generated
     */
    EObject getEClassifier();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.tplconf.StructuredType#getEClassifier <em>EClassifier</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>EClassifier</em>' reference.
     * @see #getEClassifier()
     * @generated
     */
    void setEClassifier(EObject value);

} // StructuredType
