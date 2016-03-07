/**
 */
package org.obeonetwork.wgen.template;

import org.apache.poi.xwpf.usermodel.IBody;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Template</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.wgen.template.Template#getTemplateName <em>Template Name</em>}</li>
 *   <li>{@link org.obeonetwork.wgen.template.Template#getDocument <em>Document</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.wgen.template.TemplatePackage#getTemplate()
 * @model
 * @generated
 */
public interface Template extends Compound {
	/**
	 * Returns the value of the '<em><b>Template Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Template Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Template Name</em>' attribute.
	 * @see #setTemplateName(String)
	 * @see org.obeonetwork.wgen.template.TemplatePackage#getTemplate_TemplateName()
	 * @model
	 * @generated
	 */
	String getTemplateName();

	/**
	 * Sets the value of the '{@link org.obeonetwork.wgen.template.Template#getTemplateName <em>Template Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Template Name</em>' attribute.
	 * @see #getTemplateName()
	 * @generated
	 */
	void setTemplateName(String value);

	/**
	 * Returns the value of the '<em><b>Document</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Document</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Document</em>' attribute.
	 * @see #setDocument(IBody)
	 * @see org.obeonetwork.wgen.template.TemplatePackage#getTemplate_Document()
	 * @model dataType="org.obeonetwork.wgen.template.Body"
	 * @generated
	 */
	IBody getDocument();

	/**
	 * Sets the value of the '{@link org.obeonetwork.wgen.template.Template#getDocument <em>Document</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Document</em>' attribute.
	 * @see #getDocument()
	 * @generated
	 */
	void setDocument(IBody value);

} // Template
