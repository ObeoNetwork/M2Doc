/**
 */
package org.eclipse.gendoc2.template.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gendoc2.template.AbstractConstruct;
import org.eclipse.gendoc2.template.Compound;
import org.eclipse.gendoc2.template.TemplatePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Compound</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gendoc2.template.impl.CompoundImpl#getSubConstructs <em>Sub Constructs</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class CompoundImpl extends AbstractConstructImpl implements Compound {
	/**
	 * The cached value of the '{@link #getSubConstructs() <em>Sub Constructs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubConstructs()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractConstruct> subConstructs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompoundImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TemplatePackage.Literals.COMPOUND;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractConstruct> getSubConstructs() {
		if (subConstructs == null) {
			subConstructs = new EObjectContainmentEList<AbstractConstruct>(AbstractConstruct.class, this, TemplatePackage.COMPOUND__SUB_CONSTRUCTS);
		}
		return subConstructs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TemplatePackage.COMPOUND__SUB_CONSTRUCTS:
				return ((InternalEList<?>)getSubConstructs()).basicRemove(otherEnd, msgs);
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
			case TemplatePackage.COMPOUND__SUB_CONSTRUCTS:
				return getSubConstructs();
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
			case TemplatePackage.COMPOUND__SUB_CONSTRUCTS:
				getSubConstructs().clear();
				getSubConstructs().addAll((Collection<? extends AbstractConstruct>)newValue);
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
			case TemplatePackage.COMPOUND__SUB_CONSTRUCTS:
				getSubConstructs().clear();
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
			case TemplatePackage.COMPOUND__SUB_CONSTRUCTS:
				return subConstructs != null && !subConstructs.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //CompoundImpl
