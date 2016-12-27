/**
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 */
package org.obeonetwork.m2doc.template;

import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Conditional</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.m2doc.template.Conditional#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.Conditional#getThen <em>Then</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.Conditional#getElse <em>Else</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.template.TemplatePackage#getConditional()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore documentation='If {@link Conditional#getCondition() condition} is evaluated to <code>true</code> the {@link Conditional#getThen() then} {@link Compound} is executed, otherwise the {@link Conditional#getElse() else} {@link Compound}' syntax='{m:if expr} runs1 [{m:elseif <expr>} runs_n]* [{m:else} run_else]{m:endif}'"
 * @generated
 */
public interface Conditional extends Statement {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

    /**
     * Returns the value of the '<em><b>Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Condition</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Condition</em>' attribute.
     * @see #setCondition(AstResult)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getConditional_Condition()
     * @model dataType="org.obeonetwork.m2doc.template.AstResult"
     *        annotation="http://www.eclipse.org/emf/2002/Ecore documentation='If evaluated to <code>true</code> the {@link Conditional#getThen() then} {@link Compound} is executed, otherwise the {@link Conditional#getElse() else} {@link Compound}'"
     * @generated
     */
    AstResult getCondition();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.Conditional#getCondition <em>Condition</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Condition</em>' attribute.
     * @see #getCondition()
     * @generated
     */
    void setCondition(AstResult value);

    /**
     * Returns the value of the '<em><b>Then</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Then</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Then</em>' containment reference.
     * @see #setThen(Block)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getConditional_Then()
     * @model containment="true" required="true"
     *        annotation="http://www.eclipse.org/emf/2002/Ecore documentation='The {@link Compound} is executed if {@link Conditional#getCondition() condition} is evaluated to <code>true</code>.'"
     * @generated
     */
    Block getThen();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.Conditional#getThen <em>Then</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Then</em>' containment reference.
     * @see #getThen()
     * @generated
     */
    void setThen(Block value);

    /**
     * Returns the value of the '<em><b>Else</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Else</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Else</em>' containment reference.
     * @see #setElse(Block)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getConditional_Else()
     * @model containment="true"
     *        annotation="http://www.eclipse.org/emf/2002/Ecore documentation='The {@link Compound} is executed if {@link Conditional#getCondition() condition} is evaluated to <code>false</code>.'"
     * @generated
     */
    Block getElse();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.Conditional#getElse <em>Else</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Else</em>' containment reference.
     * @see #getElse()
     * @generated
     */
    void setElse(Block value);

} // Conditional
