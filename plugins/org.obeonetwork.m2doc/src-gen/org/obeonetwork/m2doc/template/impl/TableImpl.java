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
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.template.Row;
import org.obeonetwork.m2doc.template.Table;
import org.obeonetwork.m2doc.template.TemplatePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.template.impl.TableImpl#getStyleRun <em>Style Run</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.TableImpl#getRuns <em>Runs</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.TableImpl#getClosingRuns <em>Closing Runs</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.TableImpl#getValidationMessages <em>Validation Messages</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.TableImpl#getRows <em>Rows</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.TableImpl#getTable <em>Table</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TableImpl extends MinimalEObjectImpl.Container implements Table {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v2.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v20.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

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
     * The cached value of the '{@link #getRows() <em>Rows</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getRows()
     * @generated
     * @ordered
     */
    protected EList<Row> rows;

    /**
     * The default value of the '{@link #getTable() <em>Table</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getTable()
     * @generated
     * @ordered
     */
    protected static final XWPFTable TABLE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTable() <em>Table</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getTable()
     * @generated
     * @ordered
     */
    protected XWPFTable table = TABLE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TableImpl() {
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
        return TemplatePackage.Literals.TABLE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public XWPFRun getStyleRun() {
        return styleRun;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setStyleRun(XWPFRun newStyleRun) {
        XWPFRun oldStyleRun = styleRun;
        styleRun = newStyleRun;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TABLE__STYLE_RUN, oldStyleRun,
                    styleRun));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<XWPFRun> getRuns() {
        if (runs == null) {
            runs = new EDataTypeUniqueEList<XWPFRun>(XWPFRun.class, this, TemplatePackage.TABLE__RUNS);
        }
        return runs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<XWPFRun> getClosingRuns() {
        if (closingRuns == null) {
            closingRuns = new EDataTypeUniqueEList<XWPFRun>(XWPFRun.class, this, TemplatePackage.TABLE__CLOSING_RUNS);
        }
        return closingRuns;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<TemplateValidationMessage> getValidationMessages() {
        if (validationMessages == null) {
            validationMessages = new EDataTypeUniqueEList<TemplateValidationMessage>(TemplateValidationMessage.class,
                    this, TemplatePackage.TABLE__VALIDATION_MESSAGES);
        }
        return validationMessages;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Row> getRows() {
        if (rows == null) {
            rows = new EObjectContainmentEList<Row>(Row.class, this, TemplatePackage.TABLE__ROWS);
        }
        return rows;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public XWPFTable getTable() {
        return table;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTable(XWPFTable newTable) {
        XWPFTable oldTable = table;
        table = newTable;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TABLE__TABLE, oldTable, table));
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
            case TemplatePackage.TABLE__ROWS:
                return ((InternalEList<?>) getRows()).basicRemove(otherEnd, msgs);
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
            case TemplatePackage.TABLE__STYLE_RUN:
                return getStyleRun();
            case TemplatePackage.TABLE__RUNS:
                return getRuns();
            case TemplatePackage.TABLE__CLOSING_RUNS:
                return getClosingRuns();
            case TemplatePackage.TABLE__VALIDATION_MESSAGES:
                return getValidationMessages();
            case TemplatePackage.TABLE__ROWS:
                return getRows();
            case TemplatePackage.TABLE__TABLE:
                return getTable();
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
            case TemplatePackage.TABLE__STYLE_RUN:
                setStyleRun((XWPFRun) newValue);
                return;
            case TemplatePackage.TABLE__RUNS:
                getRuns().clear();
                getRuns().addAll((Collection<? extends XWPFRun>) newValue);
                return;
            case TemplatePackage.TABLE__CLOSING_RUNS:
                getClosingRuns().clear();
                getClosingRuns().addAll((Collection<? extends XWPFRun>) newValue);
                return;
            case TemplatePackage.TABLE__VALIDATION_MESSAGES:
                getValidationMessages().clear();
                getValidationMessages().addAll((Collection<? extends TemplateValidationMessage>) newValue);
                return;
            case TemplatePackage.TABLE__ROWS:
                getRows().clear();
                getRows().addAll((Collection<? extends Row>) newValue);
                return;
            case TemplatePackage.TABLE__TABLE:
                setTable((XWPFTable) newValue);
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
            case TemplatePackage.TABLE__STYLE_RUN:
                setStyleRun(STYLE_RUN_EDEFAULT);
                return;
            case TemplatePackage.TABLE__RUNS:
                getRuns().clear();
                return;
            case TemplatePackage.TABLE__CLOSING_RUNS:
                getClosingRuns().clear();
                return;
            case TemplatePackage.TABLE__VALIDATION_MESSAGES:
                getValidationMessages().clear();
                return;
            case TemplatePackage.TABLE__ROWS:
                getRows().clear();
                return;
            case TemplatePackage.TABLE__TABLE:
                setTable(TABLE_EDEFAULT);
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
            case TemplatePackage.TABLE__STYLE_RUN:
                return STYLE_RUN_EDEFAULT == null ? styleRun != null : !STYLE_RUN_EDEFAULT.equals(styleRun);
            case TemplatePackage.TABLE__RUNS:
                return runs != null && !runs.isEmpty();
            case TemplatePackage.TABLE__CLOSING_RUNS:
                return closingRuns != null && !closingRuns.isEmpty();
            case TemplatePackage.TABLE__VALIDATION_MESSAGES:
                return validationMessages != null && !validationMessages.isEmpty();
            case TemplatePackage.TABLE__ROWS:
                return rows != null && !rows.isEmpty();
            case TemplatePackage.TABLE__TABLE:
                return TABLE_EDEFAULT == null ? table != null : !TABLE_EDEFAULT.equals(table);
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

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (styleRun: ");
        result.append(styleRun);
        result.append(", runs: ");
        result.append(runs);
        result.append(", closingRuns: ");
        result.append(closingRuns);
        result.append(", validationMessages: ");
        result.append(validationMessages);
        result.append(", table: ");
        result.append(table);
        result.append(')');
        return result.toString();
    }

} // TableImpl
