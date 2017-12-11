/**
 */
package org.obeonetwork.m2doc.genconf;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Option</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.genconf.Option#getName <em>Name</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.genconf.Option#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getOption()
 * @model
 * @generated
 */
public interface Option extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getOption_Name()
     * @model default="" required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.genconf.Option#getName <em>Name</em>}' attribute.
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
     * Returns the value of the '<em><b>Value</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(String)
     * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getOption_Value()
     * @model default="" required="true"
     * @generated
     */
    String getValue();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.genconf.Option#getValue <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
    void setValue(String value);

} // Option
