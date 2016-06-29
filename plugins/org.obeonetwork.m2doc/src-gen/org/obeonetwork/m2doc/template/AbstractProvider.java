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

import org.eclipse.emf.common.util.EMap;

import org.obeonetwork.m2doc.provider.IProvider;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Provider</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.m2doc.template.AbstractProvider#getOptionValueMap <em>Option Value Map</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.AbstractProvider#getProvider <em>Provider</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.template.TemplatePackage#getAbstractProvider()
 * @model abstract="true"
 * @generated
 */
public interface AbstractProvider extends AbstractConstruct {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

    /**
     * Returns the value of the '<em><b>Option Value Map</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.Object},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Option Value Map</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Option Value Map</em>' map.
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getAbstractProvider_OptionValueMap()
     * @model mapType="org.obeonetwork.m2doc.template.OptionValueMap<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EJavaObject>"
     * @generated
     */
    EMap<String, Object> getOptionValueMap();

    /**
     * Returns the value of the '<em><b>Provider</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Provider</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Provider</em>' attribute.
     * @see #setProvider(IProvider)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getAbstractProvider_Provider()
     * @model dataType="org.obeonetwork.m2doc.template.Provider"
     * @generated
     */
    IProvider getProvider();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.AbstractProvider#getProvider <em>Provider</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Provider</em>' attribute.
     * @see #getProvider()
     * @generated
     */
    void setProvider(IProvider value);

} // AbstractProvider
