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
package org.obeonetwork.m2doc.template.impl;

import java.util.Collection;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.template.Link;
import org.obeonetwork.m2doc.template.TemplatePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.template.impl.LinkImpl#getStyleRun <em>Style Run</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.LinkImpl#getRuns <em>Runs</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.LinkImpl#getClosingRuns <em>Closing Runs</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.LinkImpl#getValidationMessages <em>Validation Messages</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.LinkImpl#getName <em>Name</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.LinkImpl#getText <em>Text</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LinkImpl extends MinimalEObjectImpl.Container implements Link {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = " Copyright (c) 2016, 2025 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v2.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v20.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

    /**
     * The default value of the '{@link #getStyleRun() <em>Style Run</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getStyleRun()
     * @generated
     * @ordered
     */
    protected static final XWPFRun STYLE_RUN_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getStyleRun() <em>Style Run</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getStyleRun()
     * @generated
     * @ordered
     */
    protected XWPFRun styleRun = STYLE_RUN_EDEFAULT;

    /**
     * The cached value of the '{@link #getRuns() <em>Runs</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getRuns()
     * @generated
     * @ordered
     */
    protected EList<XWPFRun> runs;

    /**
     * The cached value of the '{@link #getClosingRuns() <em>Closing Runs</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getClosingRuns()
     * @generated
     * @ordered
     */
    protected EList<XWPFRun> closingRuns;

    /**
     * The cached value of the '{@link #getValidationMessages() <em>Validation Messages</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getValidationMessages()
     * @generated
     * @ordered
     */
    protected EList<TemplateValidationMessage> validationMessages;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final AstResult NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected AstResult name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getText() <em>Text</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getText()
     * @generated
     * @ordered
     */
    protected static final AstResult TEXT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getText() <em>Text</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getText()
     * @generated
     * @ordered
     */
    protected AstResult text = TEXT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected LinkImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TemplatePackage.Literals.LINK;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public XWPFRun getStyleRun() {
        return styleRun;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setStyleRun(XWPFRun newStyleRun) {
        XWPFRun oldStyleRun = styleRun;
        styleRun = newStyleRun;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.LINK__STYLE_RUN, oldStyleRun,
                    styleRun));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<XWPFRun> getRuns() {
        if (runs == null) {
            runs = new EDataTypeUniqueEList<XWPFRun>(XWPFRun.class, this, TemplatePackage.LINK__RUNS);
        }
        return runs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<XWPFRun> getClosingRuns() {
        if (closingRuns == null) {
            closingRuns = new EDataTypeUniqueEList<XWPFRun>(XWPFRun.class, this, TemplatePackage.LINK__CLOSING_RUNS);
        }
        return closingRuns;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<TemplateValidationMessage> getValidationMessages() {
        if (validationMessages == null) {
            validationMessages = new EDataTypeUniqueEList<TemplateValidationMessage>(TemplateValidationMessage.class,
                    this, TemplatePackage.LINK__VALIDATION_MESSAGES);
        }
        return validationMessages;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public AstResult getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setName(AstResult newName) {
        AstResult oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.LINK__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public AstResult getText() {
        return text;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setText(AstResult newText) {
        AstResult oldText = text;
        text = newText;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.LINK__TEXT, oldText, text));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TemplatePackage.LINK__STYLE_RUN:
                return getStyleRun();
            case TemplatePackage.LINK__RUNS:
                return getRuns();
            case TemplatePackage.LINK__CLOSING_RUNS:
                return getClosingRuns();
            case TemplatePackage.LINK__VALIDATION_MESSAGES:
                return getValidationMessages();
            case TemplatePackage.LINK__NAME:
                return getName();
            case TemplatePackage.LINK__TEXT:
                return getText();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case TemplatePackage.LINK__STYLE_RUN:
                setStyleRun((XWPFRun) newValue);
                return;
            case TemplatePackage.LINK__RUNS:
                getRuns().clear();
                getRuns().addAll((Collection<? extends XWPFRun>) newValue);
                return;
            case TemplatePackage.LINK__CLOSING_RUNS:
                getClosingRuns().clear();
                getClosingRuns().addAll((Collection<? extends XWPFRun>) newValue);
                return;
            case TemplatePackage.LINK__VALIDATION_MESSAGES:
                getValidationMessages().clear();
                getValidationMessages().addAll((Collection<? extends TemplateValidationMessage>) newValue);
                return;
            case TemplatePackage.LINK__NAME:
                setName((AstResult) newValue);
                return;
            case TemplatePackage.LINK__TEXT:
                setText((AstResult) newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case TemplatePackage.LINK__STYLE_RUN:
                setStyleRun(STYLE_RUN_EDEFAULT);
                return;
            case TemplatePackage.LINK__RUNS:
                getRuns().clear();
                return;
            case TemplatePackage.LINK__CLOSING_RUNS:
                getClosingRuns().clear();
                return;
            case TemplatePackage.LINK__VALIDATION_MESSAGES:
                getValidationMessages().clear();
                return;
            case TemplatePackage.LINK__NAME:
                setName(NAME_EDEFAULT);
                return;
            case TemplatePackage.LINK__TEXT:
                setText(TEXT_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case TemplatePackage.LINK__STYLE_RUN:
                return STYLE_RUN_EDEFAULT == null ? styleRun != null : !STYLE_RUN_EDEFAULT.equals(styleRun);
            case TemplatePackage.LINK__RUNS:
                return runs != null && !runs.isEmpty();
            case TemplatePackage.LINK__CLOSING_RUNS:
                return closingRuns != null && !closingRuns.isEmpty();
            case TemplatePackage.LINK__VALIDATION_MESSAGES:
                return validationMessages != null && !validationMessages.isEmpty();
            case TemplatePackage.LINK__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case TemplatePackage.LINK__TEXT:
                return TEXT_EDEFAULT == null ? text != null : !TEXT_EDEFAULT.equals(text);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (styleRun: ");
        result.append(styleRun);
        result.append(", runs: ");
        result.append(runs);
        result.append(", closingRuns: ");
        result.append(closingRuns);
        result.append(", validationMessages: ");
        result.append(validationMessages);
        result.append(", name: ");
        result.append(name);
        result.append(", text: ");
        result.append(text);
        result.append(')');
        return result.toString();
    }

} // LinkImpl
