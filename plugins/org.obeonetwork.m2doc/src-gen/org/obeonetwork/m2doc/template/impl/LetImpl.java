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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.Let;
import org.obeonetwork.m2doc.template.TemplatePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Let</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.template.impl.LetImpl#getStyleRun <em>Style Run</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.LetImpl#getRuns <em>Runs</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.LetImpl#getClosingRuns <em>Closing Runs</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.LetImpl#getValidationMessages <em>Validation Messages</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.LetImpl#getName <em>Name</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.LetImpl#getValue <em>Value</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.LetImpl#getBody <em>Body</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LetImpl extends MinimalEObjectImpl.Container implements Let {
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
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected static final AstResult VALUE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected AstResult value = VALUE_EDEFAULT;

    /**
     * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getBody()
     * @generated
     * @ordered
     */
    protected Block body;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected LetImpl() {
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
        return TemplatePackage.Literals.LET;
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
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.LET__STYLE_RUN, oldStyleRun,
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
            runs = new EDataTypeUniqueEList<XWPFRun>(XWPFRun.class, this, TemplatePackage.LET__RUNS);
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
            closingRuns = new EDataTypeUniqueEList<XWPFRun>(XWPFRun.class, this, TemplatePackage.LET__CLOSING_RUNS);
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
                    this, TemplatePackage.LET__VALIDATION_MESSAGES);
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
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.LET__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public AstResult getValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setValue(AstResult newValue) {
        AstResult oldValue = value;
        value = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.LET__VALUE, oldValue, value));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Block getBody() {
        return body;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetBody(Block newBody, NotificationChain msgs) {
        Block oldBody = body;
        body = newBody;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TemplatePackage.LET__BODY,
                    oldBody, newBody);
            if (msgs == null)
                msgs = notification;
            else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setBody(Block newBody) {
        if (newBody != body) {
            NotificationChain msgs = null;
            if (body != null)
                msgs = ((InternalEObject) body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TemplatePackage.LET__BODY,
                        null, msgs);
            if (newBody != null)
                msgs = ((InternalEObject) newBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TemplatePackage.LET__BODY,
                        null, msgs);
            msgs = basicSetBody(newBody, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.LET__BODY, newBody, newBody));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TemplatePackage.LET__BODY:
                return basicSetBody(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
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
            case TemplatePackage.LET__STYLE_RUN:
                return getStyleRun();
            case TemplatePackage.LET__RUNS:
                return getRuns();
            case TemplatePackage.LET__CLOSING_RUNS:
                return getClosingRuns();
            case TemplatePackage.LET__VALIDATION_MESSAGES:
                return getValidationMessages();
            case TemplatePackage.LET__NAME:
                return getName();
            case TemplatePackage.LET__VALUE:
                return getValue();
            case TemplatePackage.LET__BODY:
                return getBody();
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
            case TemplatePackage.LET__STYLE_RUN:
                setStyleRun((XWPFRun) newValue);
                return;
            case TemplatePackage.LET__RUNS:
                getRuns().clear();
                getRuns().addAll((Collection<? extends XWPFRun>) newValue);
                return;
            case TemplatePackage.LET__CLOSING_RUNS:
                getClosingRuns().clear();
                getClosingRuns().addAll((Collection<? extends XWPFRun>) newValue);
                return;
            case TemplatePackage.LET__VALIDATION_MESSAGES:
                getValidationMessages().clear();
                getValidationMessages().addAll((Collection<? extends TemplateValidationMessage>) newValue);
                return;
            case TemplatePackage.LET__NAME:
                setName((String) newValue);
                return;
            case TemplatePackage.LET__VALUE:
                setValue((AstResult) newValue);
                return;
            case TemplatePackage.LET__BODY:
                setBody((Block) newValue);
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
            case TemplatePackage.LET__STYLE_RUN:
                setStyleRun(STYLE_RUN_EDEFAULT);
                return;
            case TemplatePackage.LET__RUNS:
                getRuns().clear();
                return;
            case TemplatePackage.LET__CLOSING_RUNS:
                getClosingRuns().clear();
                return;
            case TemplatePackage.LET__VALIDATION_MESSAGES:
                getValidationMessages().clear();
                return;
            case TemplatePackage.LET__NAME:
                setName(NAME_EDEFAULT);
                return;
            case TemplatePackage.LET__VALUE:
                setValue(VALUE_EDEFAULT);
                return;
            case TemplatePackage.LET__BODY:
                setBody((Block) null);
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
            case TemplatePackage.LET__STYLE_RUN:
                return STYLE_RUN_EDEFAULT == null ? styleRun != null : !STYLE_RUN_EDEFAULT.equals(styleRun);
            case TemplatePackage.LET__RUNS:
                return runs != null && !runs.isEmpty();
            case TemplatePackage.LET__CLOSING_RUNS:
                return closingRuns != null && !closingRuns.isEmpty();
            case TemplatePackage.LET__VALIDATION_MESSAGES:
                return validationMessages != null && !validationMessages.isEmpty();
            case TemplatePackage.LET__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case TemplatePackage.LET__VALUE:
                return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
            case TemplatePackage.LET__BODY:
                return body != null;
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
        result.append(", value: ");
        result.append(value);
        result.append(')');
        return result.toString();
    }

} // LetImpl
