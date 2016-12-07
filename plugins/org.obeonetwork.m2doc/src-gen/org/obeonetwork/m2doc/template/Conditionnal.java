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
 * A representation of the model object '<em><b>Conditionnal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.m2doc.template.Conditionnal#getAlternative <em>Alternative</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.Conditionnal#getElse <em>Else</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.Conditionnal#getQuery <em>Query</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.template.TemplatePackage#getConditionnal()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='AlternativeExcludeElse' documentation='Si l\'expression expr est \351valu\351e \340 true dans le contexte courant alors l\'ensemble de la balise jusqu\'\340 la balise m:endif comprisee est remplac\351e par le traitement de runs1 dans le contexte courant. Sinon, l\'ensemble des balises est remplac\351e par le traitement du premier run_n tel que l\'expression expr de la balise m:elseif associ\351e est \351valu\351e \340 vrai. Si aucune telle balise n\'est pr\351sente ou si aucune d\'entre-elle porte une expression \351valu\351e \340 true, le r\351sultat du traitement de run_else, si la balise m:else est pr\351sente, dans le contexte courant est ins\351r\351 \340 la place de l\'ensemble des balises' syntax='{m:if expr} runs1 [{m:elseif <expr>} runs_n]* [{m:else} run_else]{m:endif}'"
 * @generated
 */
public interface Conditionnal extends Compound {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

    /**
     * Returns the value of the '<em><b>Alternative</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Alternative</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Alternative</em>' containment reference.
     * @see #setAlternative(Conditionnal)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getConditionnal_Alternative()
     * @model containment="true"
     * @generated
     */
    Conditionnal getAlternative();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.Conditionnal#getAlternative <em>Alternative</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Alternative</em>' containment reference.
     * @see #getAlternative()
     * @generated
     */
    void setAlternative(Conditionnal value);

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
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getConditionnal_Query()
     * @model dataType="org.obeonetwork.m2doc.template.AstResult"
     * @generated
     */
    AstResult getQuery();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.Conditionnal#getQuery <em>Query</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query</em>' attribute.
     * @see #getQuery()
     * @generated
     */
    void setQuery(AstResult value);

    /**
     * Returns the value of the '<em><b>Else</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Else</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Else</em>' containment reference.
     * @see #setElse(Default)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getConditionnal_Else()
     * @model containment="true"
     * @generated
     */
    Default getElse();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.Conditionnal#getElse <em>Else</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Else</em>' containment reference.
     * @see #getElse()
     * @generated
     */
    void setElse(Default value);

} // Conditionnal
