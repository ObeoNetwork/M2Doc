/**
 */
package org.obeonetwork.m2doc.genconf;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.genconf.Definition#getKey <em>Key</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getDefinition()
 * @model abstract="true"
 * @generated
 */
public interface Definition extends EObject {
    /**
     * Returns the value of the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Key</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Key</em>' attribute.
     * @see #setKey(String)
     * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getDefinition_Key()
     * @model
     * @generated
     */
    String getKey();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.genconf.Definition#getKey <em>Key</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Key</em>' attribute.
     * @see #getKey()
     * @generated
     */
    void setKey(String value);

} // Definition
