/**
 */
package org.obeonetwork.m2doc.genconf;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Integer Sequence Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.genconf.IntegerSequenceDefinition#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getIntegerSequenceDefinition()
 * @model
 * @generated
 */
public interface IntegerSequenceDefinition extends Definition {
    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute list.
     * The list contents are of type {@link java.lang.Integer}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Value</em>' attribute list.
     * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getIntegerSequenceDefinition_Value()
     * @model default="0" unique="false"
     * @generated
     */
    EList<Integer> getValue();

} // IntegerSequenceDefinition
