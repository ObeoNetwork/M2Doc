/**
 */
package org.obeonetwork.m2doc.genconf;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean Ordered Set Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.genconf.BooleanOrderedSetDefinition#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getBooleanOrderedSetDefinition()
 * @model
 * @generated
 */
public interface BooleanOrderedSetDefinition extends Definition {
    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute list.
     * The list contents are of type {@link java.lang.Boolean}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Value</em>' attribute list.
     * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getBooleanOrderedSetDefinition_Value()
     * @model default="false"
     * @generated
     */
    EList<Boolean> getValue();

} // BooleanOrderedSetDefinition
