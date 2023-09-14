/**
 */
package org.obeonetwork.m2doc.genconf;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Ordered Set Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.genconf.ModelOrderedSetDefinition#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getModelOrderedSetDefinition()
 * @model
 * @generated
 */
public interface ModelOrderedSetDefinition extends Definition {
    /**
     * Returns the value of the '<em><b>Value</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Value</em>' reference list.
     * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getModelOrderedSetDefinition_Value()
     * @model
     * @generated
     */
    EList<EObject> getValue();

} // ModelOrderedSetDefinition
