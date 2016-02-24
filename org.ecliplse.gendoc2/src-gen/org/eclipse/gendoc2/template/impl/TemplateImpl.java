/**
 */
package org.eclipse.gendoc2.template.impl;

import org.apache.poi.xwpf.usermodel.IBody;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gendoc2.template.Template;
import org.eclipse.gendoc2.template.TemplatePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Template</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gendoc2.template.impl.TemplateImpl#getTemplateName <em>Template Name</em>}</li>
 *   <li>{@link org.eclipse.gendoc2.template.impl.TemplateImpl#getDocument <em>Document</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TemplateImpl extends CompoundImpl implements Template {
	/**
	 * The default value of the '{@link #getTemplateName() <em>Template Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemplateName()
	 * @generated
	 * @ordered
	 */
	protected static final String TEMPLATE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTemplateName() <em>Template Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemplateName()
	 * @generated
	 * @ordered
	 */
	protected String templateName = TEMPLATE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDocument() <em>Document</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocument()
	 * @generated
	 * @ordered
	 */
	protected static final IBody DOCUMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDocument() <em>Document</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocument()
	 * @generated
	 * @ordered
	 */
	protected IBody document = DOCUMENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TemplateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TemplatePackage.Literals.TEMPLATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTemplateName(String newTemplateName) {
		String oldTemplateName = templateName;
		templateName = newTemplateName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE__TEMPLATE_NAME, oldTemplateName, templateName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IBody getDocument() {
		return document;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDocument(IBody newDocument) {
		IBody oldDocument = document;
		document = newDocument;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE__DOCUMENT, oldDocument, document));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TemplatePackage.TEMPLATE__TEMPLATE_NAME:
				return getTemplateName();
			case TemplatePackage.TEMPLATE__DOCUMENT:
				return getDocument();
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
			case TemplatePackage.TEMPLATE__TEMPLATE_NAME:
				setTemplateName((String)newValue);
				return;
			case TemplatePackage.TEMPLATE__DOCUMENT:
				setDocument((IBody)newValue);
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
			case TemplatePackage.TEMPLATE__TEMPLATE_NAME:
				setTemplateName(TEMPLATE_NAME_EDEFAULT);
				return;
			case TemplatePackage.TEMPLATE__DOCUMENT:
				setDocument(DOCUMENT_EDEFAULT);
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
			case TemplatePackage.TEMPLATE__TEMPLATE_NAME:
				return TEMPLATE_NAME_EDEFAULT == null ? templateName != null : !TEMPLATE_NAME_EDEFAULT.equals(templateName);
			case TemplatePackage.TEMPLATE__DOCUMENT:
				return DOCUMENT_EDEFAULT == null ? document != null : !DOCUMENT_EDEFAULT.equals(document);
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
		result.append(" (templateName: ");
		result.append(templateName);
		result.append(", document: ");
		result.append(document);
		result.append(')');
		return result.toString();
	}

} //TemplateImpl
