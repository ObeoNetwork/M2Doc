/**
 */
package org.obeonetwork.m2doc.genconf;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.genconf.BooleanDefinition#isValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getBooleanDefinition()
 * @model
 * @generated
 */
public interface BooleanDefinition extends Definition {
    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(boolean)
     * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getBooleanDefinition_Value()
     * @model default="false"
     * @generated
     */
    boolean isValue();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.genconf.BooleanDefinition#isValue <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Value</em>' attribute.
     * @see #isValue()
     * @generated
     */
    void setValue(boolean value);

} // BooleanDefinition
