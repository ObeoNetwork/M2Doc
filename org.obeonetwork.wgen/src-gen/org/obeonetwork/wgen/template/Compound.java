/**
 */
package org.obeonetwork.wgen.template;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Compound</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.wgen.template.Compound#getSubConstructs <em>Sub Constructs</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.wgen.template.TemplatePackage#getCompound()
 * @model abstract="true"
 * @generated
 */
public interface Compound extends AbstractConstruct {
	/**
	 * Returns the value of the '<em><b>Sub Constructs</b></em>' containment reference list.
	 * The list contents are of type {@link org.obeonetwork.wgen.template.AbstractConstruct}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Constructs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Constructs</em>' containment reference list.
	 * @see org.obeonetwork.wgen.template.TemplatePackage#getCompound_SubConstructs()
	 * @model containment="true"
	 * @generated
	 */
	EList<AbstractConstruct> getSubConstructs();

} // Compound
