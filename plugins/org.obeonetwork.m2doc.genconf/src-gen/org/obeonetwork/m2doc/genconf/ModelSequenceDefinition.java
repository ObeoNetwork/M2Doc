/**
 */
package org.obeonetwork.m2doc.genconf;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Sequence Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.genconf.ModelSequenceDefinition#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getModelSequenceDefinition()
 * @model
 * @generated
 */
public interface ModelSequenceDefinition extends Definition {
    /**
     * Returns the value of the '<em><b>Value</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Value</em>' reference list.
     * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getModelSequenceDefinition_Value()
     * @model
     * @generated
     */
    EList<EObject> getValue();

} // ModelSequenceDefinition
