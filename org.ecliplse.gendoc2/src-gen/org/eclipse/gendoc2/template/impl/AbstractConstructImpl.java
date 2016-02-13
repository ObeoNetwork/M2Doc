/**
 */
package org.eclipse.gendoc2.template.impl;

import java.util.Collection;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.gendoc2.template.AbstractConstruct;
import org.eclipse.gendoc2.template.TemplatePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Construct</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gendoc2.template.impl.AbstractConstructImpl#getStyleRun <em>Style Run</em>}</li>
 *   <li>{@link org.eclipse.gendoc2.template.impl.AbstractConstructImpl#getRuns <em>Runs</em>}</li>
 *   <li>{@link org.eclipse.gendoc2.template.impl.AbstractConstructImpl#getClosingRuns <em>Closing Runs</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractConstructImpl extends MinimalEObjectImpl.Container implements AbstractConstruct {
	/**
	 * The default value of the '{@link #getStyleRun() <em>Style Run</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyleRun()
	 * @generated
	 * @ordered
	 */
	protected static final XWPFRun STYLE_RUN_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getStyleRun() <em>Style Run</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyleRun()
	 * @generated
	 * @ordered
	 */
	protected XWPFRun styleRun = STYLE_RUN_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRuns() <em>Runs</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRuns()
	 * @generated
	 * @ordered
	 */
	protected EList<XWPFRun> runs;

	/**
	 * The cached value of the '{@link #getClosingRuns() <em>Closing Runs</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClosingRuns()
	 * @generated
	 * @ordered
	 */
	protected EList<XWPFRun> closingRuns;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractConstructImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TemplatePackage.Literals.ABSTRACT_CONSTRUCT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XWPFRun getStyleRun() {
		return styleRun;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStyleRun(XWPFRun newStyleRun) {
		XWPFRun oldStyleRun = styleRun;
		styleRun = newStyleRun;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.ABSTRACT_CONSTRUCT__STYLE_RUN, oldStyleRun, styleRun));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<XWPFRun> getRuns() {
		if (runs == null) {
			runs = new EDataTypeUniqueEList<XWPFRun>(XWPFRun.class, this, TemplatePackage.ABSTRACT_CONSTRUCT__RUNS);
		}
		return runs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<XWPFRun> getClosingRuns() {
		if (closingRuns == null) {
			closingRuns = new EDataTypeUniqueEList<XWPFRun>(XWPFRun.class, this, TemplatePackage.ABSTRACT_CONSTRUCT__CLOSING_RUNS);
		}
		return closingRuns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TemplatePackage.ABSTRACT_CONSTRUCT__STYLE_RUN:
				return getStyleRun();
			case TemplatePackage.ABSTRACT_CONSTRUCT__RUNS:
				return getRuns();
			case TemplatePackage.ABSTRACT_CONSTRUCT__CLOSING_RUNS:
				return getClosingRuns();
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
			case TemplatePackage.ABSTRACT_CONSTRUCT__STYLE_RUN:
				setStyleRun((XWPFRun)newValue);
				return;
			case TemplatePackage.ABSTRACT_CONSTRUCT__RUNS:
				getRuns().clear();
				getRuns().addAll((Collection<? extends XWPFRun>)newValue);
				return;
			case TemplatePackage.ABSTRACT_CONSTRUCT__CLOSING_RUNS:
				getClosingRuns().clear();
				getClosingRuns().addAll((Collection<? extends XWPFRun>)newValue);
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
			case TemplatePackage.ABSTRACT_CONSTRUCT__STYLE_RUN:
				setStyleRun(STYLE_RUN_EDEFAULT);
				return;
			case TemplatePackage.ABSTRACT_CONSTRUCT__RUNS:
				getRuns().clear();
				return;
			case TemplatePackage.ABSTRACT_CONSTRUCT__CLOSING_RUNS:
				getClosingRuns().clear();
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
			case TemplatePackage.ABSTRACT_CONSTRUCT__STYLE_RUN:
				return STYLE_RUN_EDEFAULT == null ? styleRun != null : !STYLE_RUN_EDEFAULT.equals(styleRun);
			case TemplatePackage.ABSTRACT_CONSTRUCT__RUNS:
				return runs != null && !runs.isEmpty();
			case TemplatePackage.ABSTRACT_CONSTRUCT__CLOSING_RUNS:
				return closingRuns != null && !closingRuns.isEmpty();
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
		result.append(" (styleRun: ");
		result.append(styleRun);
		result.append(", runs: ");
		result.append(runs);
		result.append(", closingRuns: ");
		result.append(closingRuns);
		result.append(')');
		return result.toString();
	}

} //AbstractConstructImpl
