/**
 */
package org.obeonetwork.m2doc.tplconf;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Template Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.tplconf.TemplateVariable#getName <em>Name</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.tplconf.TemplateVariable#getTypeName <em>Type Name</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.tplconf.TemplateVariable#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.tplconf.TplconfPackage#getTemplateVariable()
 * @model
 * @generated
 */
public interface TemplateVariable extends EObject {
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
     * @see org.obeonetwork.m2doc.tplconf.TplconfPackage#getTemplateVariable_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.tplconf.TemplateVariable#getName <em>Name</em>}' attribute.
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
     * Returns the value of the '<em><b>Type Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Type Name</em>' attribute.
     * @see #setTypeName(String)
     * @see org.obeonetwork.m2doc.tplconf.TplconfPackage#getTemplateVariable_TypeName()
     * @model
     * @generated
     */
    String getTypeName();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.tplconf.TemplateVariable#getTypeName <em>Type Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Type Name</em>' attribute.
     * @see #getTypeName()
     * @generated
     */
    void setTypeName(String value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Type</em>' reference.
     * @see #setType(TemplateType)
     * @see org.obeonetwork.m2doc.tplconf.TplconfPackage#getTemplateVariable_Type()
     * @model
     * @generated
     */
    TemplateType getType();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.tplconf.TemplateVariable#getType <em>Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Type</em>' reference.
     * @see #getType()
     * @generated
     */
    void setType(TemplateType value);

} // TemplateVariable
