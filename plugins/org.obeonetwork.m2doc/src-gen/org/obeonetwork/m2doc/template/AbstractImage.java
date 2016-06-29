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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Image</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.m2doc.template.AbstractImage#getLegend <em>Legend</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.AbstractImage#getHeight <em>Height</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.AbstractImage#getWidth <em>Width</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.AbstractImage#getLegendPOS <em>Legend POS</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.template.TemplatePackage#getAbstractImage()
 * @model abstract="true"
 * @generated
 */
public interface AbstractImage extends AbstractProvider {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

    /**
     * Returns the value of the '<em><b>Legend</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Legend</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Legend</em>' attribute.
     * @see #setLegend(String)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getAbstractImage_Legend()
     * @model
     * @generated
     */
    String getLegend();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.AbstractImage#getLegend <em>Legend</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Legend</em>' attribute.
     * @see #getLegend()
     * @generated
     */
    void setLegend(String value);

    /**
     * Returns the value of the '<em><b>Height</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Height</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Height</em>' attribute.
     * @see #setHeight(int)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getAbstractImage_Height()
     * @model
     * @generated
     */
    int getHeight();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.AbstractImage#getHeight <em>Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Height</em>' attribute.
     * @see #getHeight()
     * @generated
     */
    void setHeight(int value);

    /**
     * Returns the value of the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Width</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Width</em>' attribute.
     * @see #setWidth(int)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getAbstractImage_Width()
     * @model
     * @generated
     */
    int getWidth();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.AbstractImage#getWidth <em>Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Width</em>' attribute.
     * @see #getWidth()
     * @generated
     */
    void setWidth(int value);

    /**
     * Returns the value of the '<em><b>Legend POS</b></em>' attribute.
     * The default value is <code>"BELOW"</code>.
     * The literals are from the enumeration {@link org.obeonetwork.m2doc.template.POSITION}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Legend POS</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Legend POS</em>' attribute.
     * @see org.obeonetwork.m2doc.template.POSITION
     * @see #setLegendPOS(POSITION)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getAbstractImage_LegendPOS()
     * @model default="BELOW"
     * @generated
     */
    POSITION getLegendPOS();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.AbstractImage#getLegendPOS <em>Legend POS</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Legend POS</em>' attribute.
     * @see org.obeonetwork.m2doc.template.POSITION
     * @see #getLegendPOS()
     * @generated
     */
    void setLegendPOS(POSITION value);

} // AbstractImage
