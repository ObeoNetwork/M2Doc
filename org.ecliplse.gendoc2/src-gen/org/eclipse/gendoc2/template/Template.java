/**
 */
package org.eclipse.gendoc2.template;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Template</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gendoc2.template.Template#getTemplateName <em>Template Name</em>}</li>
 *   <li>{@link org.eclipse.gendoc2.template.Template#getDocument <em>Document</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gendoc2.template.TemplatePackage#getTemplate()
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
	 * @see org.eclipse.gendoc2.template.TemplatePackage#getTemplate_TemplateName()
	 * @model
	 * @generated
	 */
	String getTemplateName();

	/**
	 * Sets the value of the '{@link org.eclipse.gendoc2.template.Template#getTemplateName <em>Template Name</em>}' attribute.
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
	 * @see #setDocument(XWPFDocument)
	 * @see org.eclipse.gendoc2.template.TemplatePackage#getTemplate_Document()
	 * @model dataType="org.eclipse.gendoc2.template.Document"
	 * @generated
	 */
	XWPFDocument getDocument();

	/**
	 * Sets the value of the '{@link org.eclipse.gendoc2.template.Template#getDocument <em>Document</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Document</em>' attribute.
	 * @see #getDocument()
	 * @generated
	 */
	void setDocument(XWPFDocument value);

} // Template
