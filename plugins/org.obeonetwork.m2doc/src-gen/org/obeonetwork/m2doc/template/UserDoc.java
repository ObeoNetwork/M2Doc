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
 * A representation of the model object '<em><b>User Doc</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.m2doc.template.UserDoc#getId <em>Id</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.template.TemplatePackage#getUserDoc()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore documentation='UserDoc  tag\r\n\r\nTag template to keep user part modification in previous generated \r\nresult file.'"
 * @generated
 */
public interface UserDoc extends Compound {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(AstResult)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getUserDoc_Id()
     * @model dataType="org.obeonetwork.m2doc.template.AstResult"
     * @generated
     */
    AstResult getId();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.UserDoc#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(AstResult value);

} // UserDoc
