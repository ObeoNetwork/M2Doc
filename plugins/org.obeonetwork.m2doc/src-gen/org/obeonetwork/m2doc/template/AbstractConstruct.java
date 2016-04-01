/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
/**
 */
package org.obeonetwork.m2doc.template;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.obeonetwork.m2doc.parser.DocumentParsingError;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Construct</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.m2doc.template.AbstractConstruct#getStyleRun <em>Style Run</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.AbstractConstruct#getRuns <em>Runs</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.AbstractConstruct#getClosingRuns <em>Closing Runs</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.AbstractConstruct#getParsingErrors <em>Parsing Errors</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.template.TemplatePackage#getAbstractConstruct()
 * @model abstract="true"
 * @generated
 */
public interface AbstractConstruct extends EObject {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

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
	 * @see org.obeonetwork.m2doc.template.TemplatePackage#getAbstractConstruct_StyleRun()
	 * @model dataType="org.obeonetwork.m2doc.template.Run"
	 * @generated
	 */
	XWPFRun getStyleRun();

	/**
	 * Sets the value of the '{@link org.obeonetwork.m2doc.template.AbstractConstruct#getStyleRun <em>Style Run</em>}' attribute.
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
	 * @see org.obeonetwork.m2doc.template.TemplatePackage#getAbstractConstruct_Runs()
	 * @model dataType="org.obeonetwork.m2doc.template.Run"
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
	 * @see org.obeonetwork.m2doc.template.TemplatePackage#getAbstractConstruct_ClosingRuns()
	 * @model dataType="org.obeonetwork.m2doc.template.Run"
	 * @generated
	 */
	EList<XWPFRun> getClosingRuns();

	/**
	 * Returns the value of the '<em><b>Parsing Errors</b></em>' attribute list.
	 * The list contents are of type {@link org.obeonetwork.m2doc.parser.DocumentParsingError}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parsing Errors</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parsing Errors</em>' attribute list.
	 * @see org.obeonetwork.m2doc.template.TemplatePackage#getAbstractConstruct_ParsingErrors()
	 * @model dataType="org.obeonetwork.m2doc.template.DocumentParsingError"
	 * @generated
	 */
	EList<DocumentParsingError> getParsingErrors();
} // AbstractConstruct
