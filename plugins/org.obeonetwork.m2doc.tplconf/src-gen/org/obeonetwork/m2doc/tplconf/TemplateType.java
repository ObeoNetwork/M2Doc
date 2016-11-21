/**
 */
package org.obeonetwork.m2doc.tplconf;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Template Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.tplconf.TemplateType#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.tplconf.TplconfPackage#getTemplateType()
 * @model abstract="true"
 * @generated
 */
public interface TemplateType extends EObject {
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
     * @see org.obeonetwork.m2doc.tplconf.TplconfPackage#getTemplateType_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.tplconf.TemplateType#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // TemplateType
