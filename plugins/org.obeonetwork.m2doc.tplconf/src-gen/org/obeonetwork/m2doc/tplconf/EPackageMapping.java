/**
 */
package org.obeonetwork.m2doc.tplconf;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EPackage Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.tplconf.EPackageMapping#getName <em>Name</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.tplconf.EPackageMapping#getUri <em>Uri</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.tplconf.EPackageMapping#getEPackage <em>EPackage</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.tplconf.TplconfPackage#getEPackageMapping()
 * @model
 * @generated
 */
public interface EPackageMapping extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.obeonetwork.m2doc.tplconf.TplconfPackage#getEPackageMapping_Name()
     * @model id="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.tplconf.EPackageMapping#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Uri</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Uri</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Uri</em>' attribute.
     * @see #setUri(String)
     * @see org.obeonetwork.m2doc.tplconf.TplconfPackage#getEPackageMapping_Uri()
     * @model
     * @generated
     */
    String getUri();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.tplconf.EPackageMapping#getUri <em>Uri</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Uri</em>' attribute.
     * @see #getUri()
     * @generated
     */
    void setUri(String value);

    /**
     * Returns the value of the '<em><b>EPackage</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>EPackage</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>EPackage</em>' reference.
     * @see #setEPackage(EObject)
     * @see org.obeonetwork.m2doc.tplconf.TplconfPackage#getEPackageMapping_EPackage()
     * @model
     * @generated
     */
    EObject getEPackage();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.tplconf.EPackageMapping#getEPackage <em>EPackage</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>EPackage</em>' reference.
     * @see #getEPackage()
     * @generated
     */
    void setEPackage(EObject value);

} // EPackageMapping
