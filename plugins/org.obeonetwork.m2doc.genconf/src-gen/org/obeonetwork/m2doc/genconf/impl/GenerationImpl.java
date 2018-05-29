/**
 */
package org.obeonetwork.m2doc.genconf.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.obeonetwork.m2doc.genconf.Definition;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.Option;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.genconf.impl.GenerationImpl#getName <em>Name</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.genconf.impl.GenerationImpl#getTemplateFileName <em>Template File Name</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.genconf.impl.GenerationImpl#getResultFileName <em>Result File Name</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.genconf.impl.GenerationImpl#getValidationFileName <em>Validation File Name</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.genconf.impl.GenerationImpl#getDefinitions <em>Definitions</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.genconf.impl.GenerationImpl#getOptions <em>Options</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GenerationImpl extends MinimalEObjectImpl.Container implements Generation {
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
     * The default value of the '{@link #getTemplateFileName() <em>Template File Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getTemplateFileName()
     * @generated
     * @ordered
     */
    protected static final String TEMPLATE_FILE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTemplateFileName() <em>Template File Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getTemplateFileName()
     * @generated
     * @ordered
     */
    protected String templateFileName = TEMPLATE_FILE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getResultFileName() <em>Result File Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getResultFileName()
     * @generated
     * @ordered
     */
    protected static final String RESULT_FILE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getResultFileName() <em>Result File Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getResultFileName()
     * @generated
     * @ordered
     */
    protected String resultFileName = RESULT_FILE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getValidationFileName() <em>Validation File Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getValidationFileName()
     * @generated
     * @ordered
     */
    protected static final String VALIDATION_FILE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getValidationFileName() <em>Validation File Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getValidationFileName()
     * @generated
     * @ordered
     */
    protected String validationFileName = VALIDATION_FILE_NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getDefinitions() <em>Definitions</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getDefinitions()
     * @generated
     * @ordered
     */
    protected EList<Definition> definitions;

    /**
     * The cached value of the '{@link #getOptions() <em>Options</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getOptions()
     * @generated
     * @ordered
     */
    protected EList<Option> options;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected GenerationImpl() {
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
        return GenconfPackage.Literals.GENERATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GenconfPackage.GENERATION__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getTemplateFileName() {
        return templateFileName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTemplateFileName(String newTemplateFileName) {
        String oldTemplateFileName = templateFileName;
        templateFileName = newTemplateFileName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GenconfPackage.GENERATION__TEMPLATE_FILE_NAME,
                    oldTemplateFileName, templateFileName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getResultFileName() {
        return resultFileName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setResultFileName(String newResultFileName) {
        String oldResultFileName = resultFileName;
        resultFileName = newResultFileName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GenconfPackage.GENERATION__RESULT_FILE_NAME,
                    oldResultFileName, resultFileName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getValidationFileName() {
        return validationFileName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setValidationFileName(String newValidationFileName) {
        String oldValidationFileName = validationFileName;
        validationFileName = newValidationFileName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GenconfPackage.GENERATION__VALIDATION_FILE_NAME,
                    oldValidationFileName, validationFileName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Definition> getDefinitions() {
        if (definitions == null) {
            definitions = new EObjectContainmentEList<Definition>(Definition.class, this,
                    GenconfPackage.GENERATION__DEFINITIONS);
        }
        return definitions;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Option> getOptions() {
        if (options == null) {
            options = new EObjectContainmentEList<Option>(Option.class, this, GenconfPackage.GENERATION__OPTIONS);
        }
        return options;
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
            case GenconfPackage.GENERATION__DEFINITIONS:
                return ((InternalEList<?>) getDefinitions()).basicRemove(otherEnd, msgs);
            case GenconfPackage.GENERATION__OPTIONS:
                return ((InternalEList<?>) getOptions()).basicRemove(otherEnd, msgs);
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
            case GenconfPackage.GENERATION__NAME:
                return getName();
            case GenconfPackage.GENERATION__TEMPLATE_FILE_NAME:
                return getTemplateFileName();
            case GenconfPackage.GENERATION__RESULT_FILE_NAME:
                return getResultFileName();
            case GenconfPackage.GENERATION__VALIDATION_FILE_NAME:
                return getValidationFileName();
            case GenconfPackage.GENERATION__DEFINITIONS:
                return getDefinitions();
            case GenconfPackage.GENERATION__OPTIONS:
                return getOptions();
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
            case GenconfPackage.GENERATION__NAME:
                setName((String) newValue);
                return;
            case GenconfPackage.GENERATION__TEMPLATE_FILE_NAME:
                setTemplateFileName((String) newValue);
                return;
            case GenconfPackage.GENERATION__RESULT_FILE_NAME:
                setResultFileName((String) newValue);
                return;
            case GenconfPackage.GENERATION__VALIDATION_FILE_NAME:
                setValidationFileName((String) newValue);
                return;
            case GenconfPackage.GENERATION__DEFINITIONS:
                getDefinitions().clear();
                getDefinitions().addAll((Collection<? extends Definition>) newValue);
                return;
            case GenconfPackage.GENERATION__OPTIONS:
                getOptions().clear();
                getOptions().addAll((Collection<? extends Option>) newValue);
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
            case GenconfPackage.GENERATION__NAME:
                setName(NAME_EDEFAULT);
                return;
            case GenconfPackage.GENERATION__TEMPLATE_FILE_NAME:
                setTemplateFileName(TEMPLATE_FILE_NAME_EDEFAULT);
                return;
            case GenconfPackage.GENERATION__RESULT_FILE_NAME:
                setResultFileName(RESULT_FILE_NAME_EDEFAULT);
                return;
            case GenconfPackage.GENERATION__VALIDATION_FILE_NAME:
                setValidationFileName(VALIDATION_FILE_NAME_EDEFAULT);
                return;
            case GenconfPackage.GENERATION__DEFINITIONS:
                getDefinitions().clear();
                return;
            case GenconfPackage.GENERATION__OPTIONS:
                getOptions().clear();
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
            case GenconfPackage.GENERATION__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case GenconfPackage.GENERATION__TEMPLATE_FILE_NAME:
                return TEMPLATE_FILE_NAME_EDEFAULT == null ? templateFileName != null
                        : !TEMPLATE_FILE_NAME_EDEFAULT.equals(templateFileName);
            case GenconfPackage.GENERATION__RESULT_FILE_NAME:
                return RESULT_FILE_NAME_EDEFAULT == null ? resultFileName != null
                        : !RESULT_FILE_NAME_EDEFAULT.equals(resultFileName);
            case GenconfPackage.GENERATION__VALIDATION_FILE_NAME:
                return VALIDATION_FILE_NAME_EDEFAULT == null ? validationFileName != null
                        : !VALIDATION_FILE_NAME_EDEFAULT.equals(validationFileName);
            case GenconfPackage.GENERATION__DEFINITIONS:
                return definitions != null && !definitions.isEmpty();
            case GenconfPackage.GENERATION__OPTIONS:
                return options != null && !options.isEmpty();
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
        result.append(" (name: ");
        result.append(name);
        result.append(", templateFileName: ");
        result.append(templateFileName);
        result.append(", resultFileName: ");
        result.append(resultFileName);
        result.append(", validationFileName: ");
        result.append(validationFileName);
        result.append(')');
        return result.toString();
    }

} // GenerationImpl
