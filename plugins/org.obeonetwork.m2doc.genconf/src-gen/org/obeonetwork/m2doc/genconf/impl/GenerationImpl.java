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

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.obeonetwork.m2doc.genconf.Definition;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.Generation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.m2doc.genconf.impl.GenerationImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.genconf.impl.GenerationImpl#getTemplateFileName <em>Template File Name</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.genconf.impl.GenerationImpl#getResultFileName <em>Result File Name</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.genconf.impl.GenerationImpl#isTimeStamped <em>Time Stamped</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.genconf.impl.GenerationImpl#getDefinitions <em>Definitions</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.genconf.impl.GenerationImpl#getPackagesNSURI <em>Packages NSURI</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.genconf.impl.GenerationImpl#getServicesTokens <em>Services Tokens</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GenerationImpl extends MinimalEObjectImpl.Container implements Generation {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTemplateFileName() <em>Template File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemplateFileName()
	 * @generated
	 * @ordered
	 */
	protected static final String TEMPLATE_FILE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTemplateFileName() <em>Template File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemplateFileName()
	 * @generated
	 * @ordered
	 */
	protected String templateFileName = TEMPLATE_FILE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getResultFileName() <em>Result File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultFileName()
	 * @generated
	 * @ordered
	 */
	protected static final String RESULT_FILE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResultFileName() <em>Result File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultFileName()
	 * @generated
	 * @ordered
	 */
	protected String resultFileName = RESULT_FILE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isTimeStamped() <em>Time Stamped</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTimeStamped()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TIME_STAMPED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isTimeStamped() <em>Time Stamped</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTimeStamped()
	 * @generated
	 * @ordered
	 */
	protected boolean timeStamped = TIME_STAMPED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDefinitions() <em>Definitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinitions()
	 * @generated
	 * @ordered
	 */
	protected EList<Definition> definitions;

	/**
	 * The cached value of the '{@link #getPackagesNSURI() <em>Packages NSURI</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackagesNSURI()
	 * @generated
	 * @ordered
	 */
	protected EList<String> packagesNSURI;

	/**
	 * The cached value of the '{@link #getServicesTokens() <em>Services Tokens</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServicesTokens()
	 * @generated
	 * @ordered
	 */
	protected EList<String> servicesTokens;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenerationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GenconfPackage.Literals.GENERATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * @generated
	 */
	public String getTemplateFileName() {
		return templateFileName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTemplateFileName(String newTemplateFileName) {
		String oldTemplateFileName = templateFileName;
		templateFileName = newTemplateFileName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GenconfPackage.GENERATION__TEMPLATE_FILE_NAME, oldTemplateFileName, templateFileName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getResultFileName() {
		return resultFileName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResultFileName(String newResultFileName) {
		String oldResultFileName = resultFileName;
		resultFileName = newResultFileName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GenconfPackage.GENERATION__RESULT_FILE_NAME, oldResultFileName, resultFileName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTimeStamped() {
		return timeStamped;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeStamped(boolean newTimeStamped) {
		boolean oldTimeStamped = timeStamped;
		timeStamped = newTimeStamped;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GenconfPackage.GENERATION__TIME_STAMPED, oldTimeStamped, timeStamped));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Definition> getDefinitions() {
		if (definitions == null) {
			definitions = new EObjectContainmentEList<Definition>(Definition.class, this, GenconfPackage.GENERATION__DEFINITIONS);
		}
		return definitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getPackagesNSURI() {
		if (packagesNSURI == null) {
			packagesNSURI = new EDataTypeUniqueEList<String>(String.class, this, GenconfPackage.GENERATION__PACKAGES_NSURI);
		}
		return packagesNSURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getServicesTokens() {
		if (servicesTokens == null) {
			servicesTokens = new EDataTypeUniqueEList<String>(String.class, this, GenconfPackage.GENERATION__SERVICES_TOKENS);
		}
		return servicesTokens;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GenconfPackage.GENERATION__DEFINITIONS:
				return ((InternalEList<?>)getDefinitions()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
			case GenconfPackage.GENERATION__TIME_STAMPED:
				return isTimeStamped();
			case GenconfPackage.GENERATION__DEFINITIONS:
				return getDefinitions();
			case GenconfPackage.GENERATION__PACKAGES_NSURI:
				return getPackagesNSURI();
			case GenconfPackage.GENERATION__SERVICES_TOKENS:
				return getServicesTokens();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case GenconfPackage.GENERATION__NAME:
				setName((String)newValue);
				return;
			case GenconfPackage.GENERATION__TEMPLATE_FILE_NAME:
				setTemplateFileName((String)newValue);
				return;
			case GenconfPackage.GENERATION__RESULT_FILE_NAME:
				setResultFileName((String)newValue);
				return;
			case GenconfPackage.GENERATION__TIME_STAMPED:
				setTimeStamped((Boolean)newValue);
				return;
			case GenconfPackage.GENERATION__DEFINITIONS:
				getDefinitions().clear();
				getDefinitions().addAll((Collection<? extends Definition>)newValue);
				return;
			case GenconfPackage.GENERATION__PACKAGES_NSURI:
				getPackagesNSURI().clear();
				getPackagesNSURI().addAll((Collection<? extends String>)newValue);
				return;
			case GenconfPackage.GENERATION__SERVICES_TOKENS:
				getServicesTokens().clear();
				getServicesTokens().addAll((Collection<? extends String>)newValue);
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
			case GenconfPackage.GENERATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case GenconfPackage.GENERATION__TEMPLATE_FILE_NAME:
				setTemplateFileName(TEMPLATE_FILE_NAME_EDEFAULT);
				return;
			case GenconfPackage.GENERATION__RESULT_FILE_NAME:
				setResultFileName(RESULT_FILE_NAME_EDEFAULT);
				return;
			case GenconfPackage.GENERATION__TIME_STAMPED:
				setTimeStamped(TIME_STAMPED_EDEFAULT);
				return;
			case GenconfPackage.GENERATION__DEFINITIONS:
				getDefinitions().clear();
				return;
			case GenconfPackage.GENERATION__PACKAGES_NSURI:
				getPackagesNSURI().clear();
				return;
			case GenconfPackage.GENERATION__SERVICES_TOKENS:
				getServicesTokens().clear();
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
			case GenconfPackage.GENERATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case GenconfPackage.GENERATION__TEMPLATE_FILE_NAME:
				return TEMPLATE_FILE_NAME_EDEFAULT == null ? templateFileName != null : !TEMPLATE_FILE_NAME_EDEFAULT.equals(templateFileName);
			case GenconfPackage.GENERATION__RESULT_FILE_NAME:
				return RESULT_FILE_NAME_EDEFAULT == null ? resultFileName != null : !RESULT_FILE_NAME_EDEFAULT.equals(resultFileName);
			case GenconfPackage.GENERATION__TIME_STAMPED:
				return timeStamped != TIME_STAMPED_EDEFAULT;
			case GenconfPackage.GENERATION__DEFINITIONS:
				return definitions != null && !definitions.isEmpty();
			case GenconfPackage.GENERATION__PACKAGES_NSURI:
				return packagesNSURI != null && !packagesNSURI.isEmpty();
			case GenconfPackage.GENERATION__SERVICES_TOKENS:
				return servicesTokens != null && !servicesTokens.isEmpty();
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
		result.append(" (name: ");
		result.append(name);
		result.append(", templateFileName: ");
		result.append(templateFileName);
		result.append(", resultFileName: ");
		result.append(resultFileName);
		result.append(", timeStamped: ");
		result.append(timeStamped);
		result.append(", packagesNSURI: ");
		result.append(packagesNSURI);
		result.append(", servicesTokens: ");
		result.append(servicesTokens);
		result.append(')');
		return result.toString();
	}

} //GenerationImpl
