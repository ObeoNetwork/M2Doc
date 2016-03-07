/**
 */
package org.obeonetwork.wgen.template;

import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Row</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.wgen.template.Row#getCells <em>Cells</em>}</li>
 *   <li>{@link org.obeonetwork.wgen.template.Row#getTableRow <em>Table Row</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.wgen.template.TemplatePackage#getRow()
 * @model
 * @generated
 */
public interface Row extends EObject {
	/**
	 * Returns the value of the '<em><b>Cells</b></em>' containment reference list.
	 * The list contents are of type {@link org.obeonetwork.wgen.template.Cell}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cells</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cells</em>' containment reference list.
	 * @see org.obeonetwork.wgen.template.TemplatePackage#getRow_Cells()
	 * @model containment="true"
	 * @generated
	 */
	EList<Cell> getCells();

	/**
	 * Returns the value of the '<em><b>Table Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table Row</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table Row</em>' attribute.
	 * @see #setTableRow(XWPFTableRow)
	 * @see org.obeonetwork.wgen.template.TemplatePackage#getRow_TableRow()
	 * @model dataType="org.obeonetwork.wgen.template.WTableRow"
	 * @generated
	 */
	XWPFTableRow getTableRow();

	/**
	 * Sets the value of the '{@link org.obeonetwork.wgen.template.Row#getTableRow <em>Table Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table Row</em>' attribute.
	 * @see #getTableRow()
	 * @generated
	 */
	void setTableRow(XWPFTableRow value);

} // Row
