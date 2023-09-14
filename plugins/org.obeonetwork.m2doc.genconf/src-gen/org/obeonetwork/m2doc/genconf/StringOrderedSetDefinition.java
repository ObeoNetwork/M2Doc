/**
 */
package org.obeonetwork.m2doc.genconf;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String Ordered Set Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.genconf.StringOrderedSetDefinition#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getStringOrderedSetDefinition()
 * @model
 * @generated
 */
public interface StringOrderedSetDefinition extends Definition {
    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Value</em>' attribute list.
     * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getStringOrderedSetDefinition_Value()
     * @model default=""
     * @generated
     */
    EList<String> getValue();

} // StringOrderedSetDefinition
