/**
 */
package org.eclipse.gendoc2.template;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Construct</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gendoc2.template.AbstractConstruct#getStyleRun <em>Style Run</em>}</li>
 *   <li>{@link org.eclipse.gendoc2.template.AbstractConstruct#getRuns <em>Runs</em>}</li>
 *   <li>{@link org.eclipse.gendoc2.template.AbstractConstruct#getClosingRuns <em>Closing Runs</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gendoc2.template.TemplatePackage#getAbstractConstruct()
 * @model abstract="true"
 * @generated
 */
public interface AbstractConstruct extends EObject {

	/**
	 * Returns the value of the '<em><b>Style Run</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Style Run</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Style Run</em>' attribute.
	 * @see #setStyleRun(XWPFRun)
	 * @see org.eclipse.gendoc2.template.TemplatePackage#getAbstractConstruct_StyleRun()
	 * @model dataType="org.eclipse.gendoc2.template.Run"
	 * @generated
	 */
	XWPFRun getStyleRun();

	/**
	 * Sets the value of the '{@link org.eclipse.gendoc2.template.AbstractConstruct#getStyleRun <em>Style Run</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Style Run</em>' attribute.
	 * @see #getStyleRun()
	 * @generated
	 */
	void setStyleRun(XWPFRun value);

	/**
	 * Returns the value of the '<em><b>Runs</b></em>' attribute list.
	 * The list contents are of type {@link org.apache.poi.xwpf.usermodel.XWPFRun}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Runs</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Runs</em>' attribute list.
	 * @see org.eclipse.gendoc2.template.TemplatePackage#getAbstractConstruct_Runs()
	 * @model dataType="org.eclipse.gendoc2.template.Run"
	 * @generated
	 */
	EList<XWPFRun> getRuns();

	/**
	 * Returns the value of the '<em><b>Closing Runs</b></em>' attribute list.
	 * The list contents are of type {@link org.apache.poi.xwpf.usermodel.XWPFRun}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Closing Runs</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Closing Runs</em>' attribute list.
	 * @see org.eclipse.gendoc2.template.TemplatePackage#getAbstractConstruct_ClosingRuns()
	 * @model dataType="org.eclipse.gendoc2.template.Run"
	 * @generated
	 */
	EList<XWPFRun> getClosingRuns();
} // AbstractConstruct
