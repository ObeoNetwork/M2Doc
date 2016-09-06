/**
 */
package org.obeonetwork.m2doc.genconf;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.m2doc.genconf.ModelDefinition#getValue <em>Value</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.genconf.ModelDefinition#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getModelDefinition()
 * @model
 * @generated
 */
public interface ModelDefinition extends Definition {
	/**
     * Returns the value of the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Value</em>' reference.
     * @see #setValue(EObject)
     * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getModelDefinition_Value()
     * @model
     * @generated
     */
	EObject getValue();

	/**
     * Sets the value of the '{@link org.obeonetwork.m2doc.genconf.ModelDefinition#getValue <em>Value</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' reference.
     * @see #getValue()
     * @generated
     */
	void setValue(EObject value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' reference.
     * @see #setType(EClassifier)
     * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getModelDefinition_Type()
     * @model
     * @generated
     */
    EClassifier getType();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.genconf.ModelDefinition#getType <em>Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' reference.
     * @see #getType()
     * @generated
     */
    void setType(EClassifier value);

} // ModelDefinition
