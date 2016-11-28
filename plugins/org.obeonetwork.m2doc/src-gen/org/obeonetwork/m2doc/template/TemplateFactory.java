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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.obeonetwork.m2doc.template.TemplatePackage
 * @generated
 */
public interface TemplateFactory extends EFactory {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	TemplateFactory eINSTANCE = org.obeonetwork.m2doc.template.impl.TemplateFactoryImpl.init();

	/**
     * Returns a new object of class '<em>Conditionnal</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Conditionnal</em>'.
     * @generated
     */
	Conditionnal createConditionnal();

	/**
     * Returns a new object of class '<em>Repetition</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Repetition</em>'.
     * @generated
     */
	Repetition createRepetition();

	/**
     * Returns a new object of class '<em>User Doc</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>User Doc</em>'.
     * @generated
     */
    UserDoc createUserDoc();

    /**
     * Returns a new object of class '<em>User Content</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>User Content</em>'.
     * @generated
     */
    UserContent createUserContent();

    /**
     * Returns a new object of class '<em>Query</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Query</em>'.
     * @generated
     */
	Query createQuery();

	/**
     * Returns a new object of class '<em>Table Merge</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Table Merge</em>'.
     * @generated
     */
	TableMerge createTableMerge();

	/**
     * Returns a new object of class '<em>Table</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Table</em>'.
     * @generated
     */
	Table createTable();

	/**
     * Returns a new object of class '<em>Row</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Row</em>'.
     * @generated
     */
	Row createRow();

	/**
     * Returns a new object of class '<em>Cell</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Cell</em>'.
     * @generated
     */
	Cell createCell();

	/**
     * Returns a new object of class '<em>Document Template</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Document Template</em>'.
     * @generated
     */
	DocumentTemplate createDocumentTemplate();

	/**
     * Returns a new object of class '<em>Bookmark</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Bookmark</em>'.
     * @generated
     */
    Bookmark createBookmark();

    /**
     * Returns a new object of class '<em>Link</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Link</em>'.
     * @generated
     */
    Link createLink();

    /**
     * Returns a new object of class '<em>Table Client</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Table Client</em>'.
     * @generated
     */
    TableClient createTableClient();

    /**
     * Returns a new object of class '<em>Image</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Image</em>'.
     * @generated
     */
	Image createImage();

	/**
     * Returns a new object of class '<em>Default</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Default</em>'.
     * @generated
     */
	Default createDefault();

	/**
     * Returns a new object of class '<em>Template</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Template</em>'.
     * @generated
     */
	Template createTemplate();

	/**
     * Returns a new object of class '<em>Representation</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Representation</em>'.
     * @generated
     */
	Representation createRepresentation();

	/**
     * Returns a new object of class '<em>Static Fragment</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Static Fragment</em>'.
     * @generated
     */
	StaticFragment createStaticFragment();

	/**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	TemplatePackage getTemplatePackage();

} //TemplateFactory
