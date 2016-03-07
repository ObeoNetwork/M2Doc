/**
 */
package org.obeonetwork.wgen.template.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.obeonetwork.wgen.template.Image;
import org.obeonetwork.wgen.template.POSITION;
import org.obeonetwork.wgen.template.TemplatePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Image</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.wgen.template.impl.ImageImpl#getLegend <em>Legend</em>}</li>
 *   <li>{@link org.obeonetwork.wgen.template.impl.ImageImpl#getFileName <em>File Name</em>}</li>
 *   <li>{@link org.obeonetwork.wgen.template.impl.ImageImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link org.obeonetwork.wgen.template.impl.ImageImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link org.obeonetwork.wgen.template.impl.ImageImpl#getLegendPOS <em>Legend POS</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ImageImpl extends AbstractConstructImpl implements Image {
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
	 * The default value of the '{@link #getFileName() <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileName()
	 * @generated
	 * @ordered
	 */
	protected static final String FILE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFileName() <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileName()
	 * @generated
	 * @ordered
	 */
	protected String fileName = FILE_NAME_EDEFAULT;

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
	protected static final POSITION LEGEND_POS_EDEFAULT = POSITION.ABOVE;

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
	protected ImageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TemplatePackage.Literals.IMAGE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.IMAGE__LEGEND, oldLegend, legend));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFileName(String newFileName) {
		String oldFileName = fileName;
		fileName = newFileName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.IMAGE__FILE_NAME, oldFileName, fileName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.IMAGE__HEIGHT, oldHeight, height));
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
			eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.IMAGE__WIDTH, oldWidth, width));
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
			eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.IMAGE__LEGEND_POS, oldLegendPOS, legendPOS));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TemplatePackage.IMAGE__LEGEND:
				return getLegend();
			case TemplatePackage.IMAGE__FILE_NAME:
				return getFileName();
			case TemplatePackage.IMAGE__HEIGHT:
				return getHeight();
			case TemplatePackage.IMAGE__WIDTH:
				return getWidth();
			case TemplatePackage.IMAGE__LEGEND_POS:
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
			case TemplatePackage.IMAGE__LEGEND:
				setLegend((String)newValue);
				return;
			case TemplatePackage.IMAGE__FILE_NAME:
				setFileName((String)newValue);
				return;
			case TemplatePackage.IMAGE__HEIGHT:
				setHeight((Integer)newValue);
				return;
			case TemplatePackage.IMAGE__WIDTH:
				setWidth((Integer)newValue);
				return;
			case TemplatePackage.IMAGE__LEGEND_POS:
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
			case TemplatePackage.IMAGE__LEGEND:
				setLegend(LEGEND_EDEFAULT);
				return;
			case TemplatePackage.IMAGE__FILE_NAME:
				setFileName(FILE_NAME_EDEFAULT);
				return;
			case TemplatePackage.IMAGE__HEIGHT:
				setHeight(HEIGHT_EDEFAULT);
				return;
			case TemplatePackage.IMAGE__WIDTH:
				setWidth(WIDTH_EDEFAULT);
				return;
			case TemplatePackage.IMAGE__LEGEND_POS:
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
			case TemplatePackage.IMAGE__LEGEND:
				return LEGEND_EDEFAULT == null ? legend != null : !LEGEND_EDEFAULT.equals(legend);
			case TemplatePackage.IMAGE__FILE_NAME:
				return FILE_NAME_EDEFAULT == null ? fileName != null : !FILE_NAME_EDEFAULT.equals(fileName);
			case TemplatePackage.IMAGE__HEIGHT:
				return height != HEIGHT_EDEFAULT;
			case TemplatePackage.IMAGE__WIDTH:
				return width != WIDTH_EDEFAULT;
			case TemplatePackage.IMAGE__LEGEND_POS:
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
		result.append(", fileName: ");
		result.append(fileName);
		result.append(", height: ");
		result.append(height);
		result.append(", width: ");
		result.append(width);
		result.append(", legendPOS: ");
		result.append(legendPOS);
		result.append(')');
		return result.toString();
	}

} //ImageImpl
