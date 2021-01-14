/**
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 */
package org.obeonetwork.m2doc.template;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Content Control</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.template.ContentControl#getBlock <em>Block</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.template.TemplatePackage#getContentControl()
 * @model
 * @generated
 */
public interface ContentControl extends Statement {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v2.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v20.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

    /**
     * Returns the value of the '<em><b>Block</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Block</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Block</em>' attribute.
     * @see #setBlock(CTSdtBlock)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getContentControl_Block()
     * @model dataType="org.obeonetwork.m2doc.template.CTSdtBlock" required="true"
     * @generated
     */
    CTSdtBlock getBlock();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.ContentControl#getBlock <em>Block</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Block</em>' attribute.
     * @see #getBlock()
     * @generated
     */
    void setBlock(CTSdtBlock value);

} // ContentControl
