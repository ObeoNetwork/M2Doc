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

import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Repetition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.m2doc.template.Repetition#getIterationVar <em>Iteration Var</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.Repetition#getQuery <em>Query</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.template.TemplatePackage#getRepetition()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore documentation='pour chaque valeur v dans le r\351sultat de l\'\351valuation de query, \r\n- un nouveau contexte est cr\351e en ajoutant la d\351finition \'var =v\' au contexte courant\r\n- le r\351sultat du traitement du corps runs est ins\351r\351 dans le document \r\nles balises {m:for} et {m:endfor} sont supprim\351es.\r\nSi la balise {m:for} est imm\351diatement suivie d\'un retour chariot alors l\'ensemble du paragraphe la contenant est supprim\351e du r\351sultat sinon, le paragraphe duquel on a supprim\351 la balise est ins\351r\351 dans le r\351sultat. Le m\352me traitement est appliqu\351 \340 la balise {m:endfor}' syntax='{m:for var | query} body {m:endfor}'"
 * @generated
 */
public interface Repetition extends Compound {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

    /**
     * Returns the value of the '<em><b>Iteration Var</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Iteration Var</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Iteration Var</em>' attribute.
     * @see #setIterationVar(String)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getRepetition_IterationVar()
     * @model
     * @generated
     */
    String getIterationVar();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.Repetition#getIterationVar <em>Iteration Var</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Iteration Var</em>' attribute.
     * @see #getIterationVar()
     * @generated
     */
    void setIterationVar(String value);

    /**
     * Returns the value of the '<em><b>Query</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query</em>' attribute.
     * @see #setQuery(AstResult)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getRepetition_Query()
     * @model dataType="org.obeonetwork.m2doc.template.AstResult"
     * @generated
     */
    AstResult getQuery();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.Repetition#getQuery <em>Query</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query</em>' attribute.
     * @see #getQuery()
     * @generated
     */
    void setQuery(AstResult value);

} // Repetition
