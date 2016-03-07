/**
 */
package org.obeonetwork.wgen.template;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Template</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.wgen.template.DocumentTemplate#getHeaders <em>Headers</em>}</li>
 *   <li>{@link org.obeonetwork.wgen.template.DocumentTemplate#getFooters <em>Footers</em>}</li>
 *   <li>{@link org.obeonetwork.wgen.template.DocumentTemplate#getBody <em>Body</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.wgen.template.TemplatePackage#getDocumentTemplate()
 * @model
 * @generated
 */
public interface DocumentTemplate extends EObject {
	/**
	 * Returns the value of the '<em><b>Headers</b></em>' containment reference list.
	 * The list contents are of type {@link org.obeonetwork.wgen.template.Template}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Headers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Headers</em>' containment reference list.
	 * @see org.obeonetwork.wgen.template.TemplatePackage#getDocumentTemplate_Headers()
	 * @model containment="true"
	 * @generated
	 */
	EList<Template> getHeaders();

	/**
	 * Returns the value of the '<em><b>Footers</b></em>' containment reference list.
	 * The list contents are of type {@link org.obeonetwork.wgen.template.Template}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Footers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Footers</em>' containment reference list.
	 * @see org.obeonetwork.wgen.template.TemplatePackage#getDocumentTemplate_Footers()
	 * @model containment="true"
	 * @generated
	 */
	EList<Template> getFooters();

	/**
	 * Returns the value of the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Body</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body</em>' containment reference.
	 * @see #setBody(Template)
	 * @see org.obeonetwork.wgen.template.TemplatePackage#getDocumentTemplate_Body()
	 * @model containment="true"
	 * @generated
	 */
	Template getBody();

	/**
	 * Sets the value of the '{@link org.obeonetwork.wgen.template.DocumentTemplate#getBody <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body</em>' containment reference.
	 * @see #getBody()
	 * @generated
	 */
	void setBody(Template value);

} // DocumentTemplate
