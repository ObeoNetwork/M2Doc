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
package org.obeonetwork.m2doc.template.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.obeonetwork.m2doc.template.AbstractImage;
import org.obeonetwork.m2doc.template.POSITION;
import org.obeonetwork.m2doc.template.TemplatePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Image</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.AbstractImageImpl#getLegend <em>Legend</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.AbstractImageImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.AbstractImageImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.AbstractImageImpl#getLegendPOS <em>Legend POS</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractImageImpl extends AbstractProviderImpl implements AbstractImage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

    /**
     * The default value of the '{@link #getLegend() <em>Legend</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLegend()
     * @generated
     * @ordered
     */
    protected static final String LEGEND_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLegend() <em>Legend</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLegend()
     * @generated
     * @ordered
     */
    protected String legend = LEGEND_EDEFAULT;

    /**
     * The default value of the '{@link #getHeight() <em>Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHeight()
     * @generated
     * @ordered
     */
    protected static final int HEIGHT_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHeight()
     * @generated
     * @ordered
     */
    protected int height = HEIGHT_EDEFAULT;

    /**
     * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected static final int WIDTH_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected int width = WIDTH_EDEFAULT;

    /**
     * The default value of the '{@link #getLegendPOS() <em>Legend POS</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLegendPOS()
     * @generated
     * @ordered
     */
    protected static final POSITION LEGEND_POS_EDEFAULT = POSITION.BELOW;

    /**
     * The cached value of the '{@link #getLegendPOS() <em>Legend POS</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLegendPOS()
     * @generated
     * @ordered
     */
    protected POSITION legendPOS = LEGEND_POS_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbstractImageImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TemplatePackage.Literals.ABSTRACT_IMAGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLegend() {
        return legend;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLegend(String newLegend) {
        String oldLegend = legend;
        legend = newLegend;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.ABSTRACT_IMAGE__LEGEND, oldLegend, legend));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getHeight() {
        return height;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHeight(int newHeight) {
        int oldHeight = height;
        height = newHeight;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.ABSTRACT_IMAGE__HEIGHT, oldHeight, height));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getWidth() {
        return width;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWidth(int newWidth) {
        int oldWidth = width;
        width = newWidth;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.ABSTRACT_IMAGE__WIDTH, oldWidth, width));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public POSITION getLegendPOS() {
        return legendPOS;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLegendPOS(POSITION newLegendPOS) {
        POSITION oldLegendPOS = legendPOS;
        legendPOS = newLegendPOS == null ? LEGEND_POS_EDEFAULT : newLegendPOS;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.ABSTRACT_IMAGE__LEGEND_POS, oldLegendPOS, legendPOS));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TemplatePackage.ABSTRACT_IMAGE__LEGEND:
                return getLegend();
            case TemplatePackage.ABSTRACT_IMAGE__HEIGHT:
                return getHeight();
            case TemplatePackage.ABSTRACT_IMAGE__WIDTH:
                return getWidth();
            case TemplatePackage.ABSTRACT_IMAGE__LEGEND_POS:
                return getLegendPOS();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case TemplatePackage.ABSTRACT_IMAGE__LEGEND:
                setLegend((String)newValue);
                return;
            case TemplatePackage.ABSTRACT_IMAGE__HEIGHT:
                setHeight((Integer)newValue);
                return;
            case TemplatePackage.ABSTRACT_IMAGE__WIDTH:
                setWidth((Integer)newValue);
                return;
            case TemplatePackage.ABSTRACT_IMAGE__LEGEND_POS:
                setLegendPOS((POSITION)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case TemplatePackage.ABSTRACT_IMAGE__LEGEND:
                setLegend(LEGEND_EDEFAULT);
                return;
            case TemplatePackage.ABSTRACT_IMAGE__HEIGHT:
                setHeight(HEIGHT_EDEFAULT);
                return;
            case TemplatePackage.ABSTRACT_IMAGE__WIDTH:
                setWidth(WIDTH_EDEFAULT);
                return;
            case TemplatePackage.ABSTRACT_IMAGE__LEGEND_POS:
                setLegendPOS(LEGEND_POS_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case TemplatePackage.ABSTRACT_IMAGE__LEGEND:
                return LEGEND_EDEFAULT == null ? legend != null : !LEGEND_EDEFAULT.equals(legend);
            case TemplatePackage.ABSTRACT_IMAGE__HEIGHT:
                return height != HEIGHT_EDEFAULT;
            case TemplatePackage.ABSTRACT_IMAGE__WIDTH:
                return width != WIDTH_EDEFAULT;
            case TemplatePackage.ABSTRACT_IMAGE__LEGEND_POS:
                return legendPOS != LEGEND_POS_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (legend: ");
        result.append(legend);
        result.append(", height: ");
        result.append(height);
        result.append(", width: ");
        result.append(width);
        result.append(", legendPOS: ");
        result.append(legendPOS);
        result.append(')');
        return result.toString();
    }

} //AbstractImageImpl
