/**
 */
package org.obeonetwork.m2doc.genconf;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Real Ordered Set Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.genconf.RealOrderedSetDefinition#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getRealOrderedSetDefinition()
 * @model
 * @generated
 */
public interface RealOrderedSetDefinition extends Definition {
    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute list.
     * The list contents are of type {@link java.lang.Double}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Value</em>' attribute list.
     * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getRealOrderedSetDefinition_Value()
     * @model default="0"
     * @generated
     */
    EList<Double> getValue();

} // RealOrderedSetDefinition
