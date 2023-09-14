/**
 */
package org.obeonetwork.m2doc.genconf;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Integer Ordered Set Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.genconf.IntegerOrderedSetDefinition#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getIntegerOrderedSetDefinition()
 * @model
 * @generated
 */
public interface IntegerOrderedSetDefinition extends Definition {
    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute list.
     * The list contents are of type {@link java.lang.Integer}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Value</em>' attribute list.
     * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getIntegerOrderedSetDefinition_Value()
     * @model default="0"
     * @generated
     */
    EList<Integer> getValue();

} // IntegerOrderedSetDefinition
