/**
 */
package org.eclipse.gendoc2.template;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cell</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gendoc2.template.Cell#getTemplate <em>Template</em>}</li>
 *   <li>{@link org.eclipse.gendoc2.template.Cell#getTableCell <em>Table Cell</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gendoc2.template.TemplatePackage#getCell()
 * @model
 * @generated
 */
public interface Cell extends EObject {
	/**
	 * Returns the value of the '<em><b>Template</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Template</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Template</em>' containment reference.
	 * @see #setTemplate(Template)
	 * @see org.eclipse.gendoc2.template.TemplatePackage#getCell_Template()
	 * @model containment="true"
	 * @generated
	 */
	Template getTemplate();

	/**
	 * Sets the value of the '{@link org.eclipse.gendoc2.template.Cell#getTemplate <em>Template</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Template</em>' containment reference.
	 * @see #getTemplate()
	 * @generated
	 */
	void setTemplate(Template value);

	/**
	 * Returns the value of the '<em><b>Table Cell</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table Cell</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table Cell</em>' attribute.
	 * @see #setTableCell(XWPFTableCell)
	 * @see org.eclipse.gendoc2.template.TemplatePackage#getCell_TableCell()
	 * @model dataType="org.eclipse.gendoc2.template.WTableCell"
	 * @generated
	 */
	XWPFTableCell getTableCell();

	/**
	 * Sets the value of the '{@link org.eclipse.gendoc2.template.Cell#getTableCell <em>Table Cell</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table Cell</em>' attribute.
	 * @see #getTableCell()
	 * @generated
	 */
	void setTableCell(XWPFTableCell value);

} // Cell
