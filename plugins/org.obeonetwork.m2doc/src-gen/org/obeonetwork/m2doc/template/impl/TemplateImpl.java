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
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.Parameter;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.TemplatePackage;
import org.obeonetwork.m2doc.template.Visibility;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Template</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.template.impl.TemplateImpl#getStyleRun <em>Style Run</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.TemplateImpl#getRuns <em>Runs</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.TemplateImpl#getClosingRuns <em>Closing Runs</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.TemplateImpl#getValidationMessages <em>Validation Messages</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.TemplateImpl#getVisibility <em>Visibility</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.TemplateImpl#getName <em>Name</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.TemplateImpl#getParameters <em>Parameters</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.TemplateImpl#getBody <em>Body</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.TemplateImpl#getDocumentTemplate <em>Document Template</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TemplateImpl extends MinimalEObjectImpl.Container implements Template {
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
     * The default value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getVisibility()
     * @generated
     * @ordered
     */
    protected static final Visibility VISIBILITY_EDEFAULT = Visibility.PRIVATE;

    /**
     * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getVisibility()
     * @generated
     * @ordered
     */
    protected Visibility visibility = VISIBILITY_EDEFAULT;

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
     * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getParameters()
     * @generated
     * @ordered
     */
    protected EList<Parameter> parameters;

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
    protected TemplateImpl() {
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
        return TemplatePackage.Literals.TEMPLATE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE__STYLE_RUN, oldStyleRun,
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
            runs = new EDataTypeUniqueEList<XWPFRun>(XWPFRun.class, this, TemplatePackage.TEMPLATE__RUNS);
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
            closingRuns = new EDataTypeUniqueEList<XWPFRun>(XWPFRun.class, this,
                    TemplatePackage.TEMPLATE__CLOSING_RUNS);
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
                    this, TemplatePackage.TEMPLATE__VALIDATION_MESSAGES);
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
    public Visibility getVisibility() {
        return visibility;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setVisibility(Visibility newVisibility) {
        Visibility oldVisibility = visibility;
        visibility = newVisibility == null ? VISIBILITY_EDEFAULT : newVisibility;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE__VISIBILITY, oldVisibility,
                    visibility));
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
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Parameter> getParameters() {
        if (parameters == null) {
            parameters = new EObjectContainmentEList<Parameter>(Parameter.class, this,
                    TemplatePackage.TEMPLATE__PARAMETERS);
        }
        return parameters;
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    TemplatePackage.TEMPLATE__BODY, oldBody, newBody);
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
                msgs = ((InternalEObject) body).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - TemplatePackage.TEMPLATE__BODY, null, msgs);
            if (newBody != null)
                msgs = ((InternalEObject) newBody).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - TemplatePackage.TEMPLATE__BODY, null, msgs);
            msgs = basicSetBody(newBody, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE__BODY, newBody, newBody));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DocumentTemplate getDocumentTemplate() {
        if (eContainerFeatureID() != TemplatePackage.TEMPLATE__DOCUMENT_TEMPLATE)
            return null;
        return (DocumentTemplate) eInternalContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetDocumentTemplate(DocumentTemplate newDocumentTemplate, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject) newDocumentTemplate, TemplatePackage.TEMPLATE__DOCUMENT_TEMPLATE,
                msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setDocumentTemplate(DocumentTemplate newDocumentTemplate) {
        if (newDocumentTemplate != eInternalContainer()
            || (eContainerFeatureID() != TemplatePackage.TEMPLATE__DOCUMENT_TEMPLATE && newDocumentTemplate != null)) {
            if (EcoreUtil.isAncestor(this, newDocumentTemplate))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newDocumentTemplate != null)
                msgs = ((InternalEObject) newDocumentTemplate).eInverseAdd(this,
                        TemplatePackage.DOCUMENT_TEMPLATE__TEMPLATES, DocumentTemplate.class, msgs);
            msgs = basicSetDocumentTemplate(newDocumentTemplate, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE__DOCUMENT_TEMPLATE,
                    newDocumentTemplate, newDocumentTemplate));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TemplatePackage.TEMPLATE__DOCUMENT_TEMPLATE:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetDocumentTemplate((DocumentTemplate) otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
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
            case TemplatePackage.TEMPLATE__PARAMETERS:
                return ((InternalEList<?>) getParameters()).basicRemove(otherEnd, msgs);
            case TemplatePackage.TEMPLATE__BODY:
                return basicSetBody(null, msgs);
            case TemplatePackage.TEMPLATE__DOCUMENT_TEMPLATE:
                return basicSetDocumentTemplate(null, msgs);
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
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID()) {
            case TemplatePackage.TEMPLATE__DOCUMENT_TEMPLATE:
                return eInternalContainer().eInverseRemove(this, TemplatePackage.DOCUMENT_TEMPLATE__TEMPLATES,
                        DocumentTemplate.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
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
            case TemplatePackage.TEMPLATE__STYLE_RUN:
                return getStyleRun();
            case TemplatePackage.TEMPLATE__RUNS:
                return getRuns();
            case TemplatePackage.TEMPLATE__CLOSING_RUNS:
                return getClosingRuns();
            case TemplatePackage.TEMPLATE__VALIDATION_MESSAGES:
                return getValidationMessages();
            case TemplatePackage.TEMPLATE__VISIBILITY:
                return getVisibility();
            case TemplatePackage.TEMPLATE__NAME:
                return getName();
            case TemplatePackage.TEMPLATE__PARAMETERS:
                return getParameters();
            case TemplatePackage.TEMPLATE__BODY:
                return getBody();
            case TemplatePackage.TEMPLATE__DOCUMENT_TEMPLATE:
                return getDocumentTemplate();
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
            case TemplatePackage.TEMPLATE__STYLE_RUN:
                setStyleRun((XWPFRun) newValue);
                return;
            case TemplatePackage.TEMPLATE__RUNS:
                getRuns().clear();
                getRuns().addAll((Collection<? extends XWPFRun>) newValue);
                return;
            case TemplatePackage.TEMPLATE__CLOSING_RUNS:
                getClosingRuns().clear();
                getClosingRuns().addAll((Collection<? extends XWPFRun>) newValue);
                return;
            case TemplatePackage.TEMPLATE__VALIDATION_MESSAGES:
                getValidationMessages().clear();
                getValidationMessages().addAll((Collection<? extends TemplateValidationMessage>) newValue);
                return;
            case TemplatePackage.TEMPLATE__VISIBILITY:
                setVisibility((Visibility) newValue);
                return;
            case TemplatePackage.TEMPLATE__NAME:
                setName((String) newValue);
                return;
            case TemplatePackage.TEMPLATE__PARAMETERS:
                getParameters().clear();
                getParameters().addAll((Collection<? extends Parameter>) newValue);
                return;
            case TemplatePackage.TEMPLATE__BODY:
                setBody((Block) newValue);
                return;
            case TemplatePackage.TEMPLATE__DOCUMENT_TEMPLATE:
                setDocumentTemplate((DocumentTemplate) newValue);
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
            case TemplatePackage.TEMPLATE__STYLE_RUN:
                setStyleRun(STYLE_RUN_EDEFAULT);
                return;
            case TemplatePackage.TEMPLATE__RUNS:
                getRuns().clear();
                return;
            case TemplatePackage.TEMPLATE__CLOSING_RUNS:
                getClosingRuns().clear();
                return;
            case TemplatePackage.TEMPLATE__VALIDATION_MESSAGES:
                getValidationMessages().clear();
                return;
            case TemplatePackage.TEMPLATE__VISIBILITY:
                setVisibility(VISIBILITY_EDEFAULT);
                return;
            case TemplatePackage.TEMPLATE__NAME:
                setName(NAME_EDEFAULT);
                return;
            case TemplatePackage.TEMPLATE__PARAMETERS:
                getParameters().clear();
                return;
            case TemplatePackage.TEMPLATE__BODY:
                setBody((Block) null);
                return;
            case TemplatePackage.TEMPLATE__DOCUMENT_TEMPLATE:
                setDocumentTemplate((DocumentTemplate) null);
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
            case TemplatePackage.TEMPLATE__STYLE_RUN:
                return STYLE_RUN_EDEFAULT == null ? styleRun != null : !STYLE_RUN_EDEFAULT.equals(styleRun);
            case TemplatePackage.TEMPLATE__RUNS:
                return runs != null && !runs.isEmpty();
            case TemplatePackage.TEMPLATE__CLOSING_RUNS:
                return closingRuns != null && !closingRuns.isEmpty();
            case TemplatePackage.TEMPLATE__VALIDATION_MESSAGES:
                return validationMessages != null && !validationMessages.isEmpty();
            case TemplatePackage.TEMPLATE__VISIBILITY:
                return visibility != VISIBILITY_EDEFAULT;
            case TemplatePackage.TEMPLATE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case TemplatePackage.TEMPLATE__PARAMETERS:
                return parameters != null && !parameters.isEmpty();
            case TemplatePackage.TEMPLATE__BODY:
                return body != null;
            case TemplatePackage.TEMPLATE__DOCUMENT_TEMPLATE:
                return getDocumentTemplate() != null;
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
        result.append(", visibility: ");
        result.append(visibility);
        result.append(", name: ");
        result.append(name);
        result.append(')');
        return result.toString();
    }

} // TemplateImpl
