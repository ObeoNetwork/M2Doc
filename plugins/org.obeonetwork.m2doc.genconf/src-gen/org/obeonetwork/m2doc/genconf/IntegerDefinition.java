/**
 */
package org.obeonetwork.m2doc.genconf;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Integer Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.genconf.IntegerDefinition#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getIntegerDefinition()
 * @model
 * @generated
 */
public interface IntegerDefinition extends Definition {
    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(int)
     * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getIntegerDefinition_Value()
     * @model
     * @generated
     */
    int getValue();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.genconf.IntegerDefinition#getValue <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
    void setValue(int value);

} // IntegerDefinition
