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
 * A representation of the model object '<em><b>Query</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.m2doc.template.Query#getBehavior <em>Behavior</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.Query#getQuery <em>Query</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.template.TemplatePackage#getQuery()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore documentation='La balise est remplac\351e dans le document par la repr\351sentation en \r\nchaine de caract\350re du r\351sultat de l\'\351valuation de l\'expression dans le \r\ncontexte courant. La style du premier run contenant query est utilis\351 pour g\351n\351rer le document. Il n\'y a pas de sens \340 utiliser plusieurs styles diff\351rents dans une requ\352te si bien que seul le premier est utilis\351 si plusieurs sont pr\351sents.\r\n- Lorsque le modifier icon est pr\351cis\351, l\'icone de l\'\351l\351ment \351valu\351 telle que d\351finie dans le .edit correspondant  est ins\351r\351e \340 la place du run\r\n- Lorsque le modifier label est pr\351cis\351, le label de l\'\351l\351ment \351valu\351 tel que d\351finin dans le .edit correspondant est ins\351r\351 \340 la place du run.\r\n- Lorsque le modifier text ou qu\'aucun modifier n\'apparait, la repr\351sentation en chaine de caract\350re de l\'\351valuation du r\351sultat est ins\351r\351.' syntax='{aql:query [icon, label,text]}'"
 * @generated
 */
public interface Query extends Statement {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

    /**
     * Returns the value of the '<em><b>Behavior</b></em>' attribute.
     * The default value is <code>"TEXT"</code>.
     * The literals are from the enumeration {@link org.obeonetwork.m2doc.template.QueryBehavior}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Behavior</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Behavior</em>' attribute.
     * @see org.obeonetwork.m2doc.template.QueryBehavior
     * @see #setBehavior(QueryBehavior)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getQuery_Behavior()
     * @model default="TEXT"
     * @generated
     */
    QueryBehavior getBehavior();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.Query#getBehavior <em>Behavior</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Behavior</em>' attribute.
     * @see org.obeonetwork.m2doc.template.QueryBehavior
     * @see #getBehavior()
     * @generated
     */
    void setBehavior(QueryBehavior value);

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
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getQuery_Query()
     * @model dataType="org.obeonetwork.m2doc.template.AstResult"
     * @generated
     */
    AstResult getQuery();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.Query#getQuery <em>Query</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query</em>' attribute.
     * @see #getQuery()
     * @generated
     */
    void setQuery(AstResult value);

} // Query
