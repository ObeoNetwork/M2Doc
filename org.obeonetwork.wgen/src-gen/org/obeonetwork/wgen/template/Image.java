/**
 */
package org.obeonetwork.wgen.template;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Image</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.wgen.template.Image#getLegend <em>Legend</em>}</li>
 *   <li>{@link org.obeonetwork.wgen.template.Image#getFileName <em>File Name</em>}</li>
 *   <li>{@link org.obeonetwork.wgen.template.Image#getHeight <em>Height</em>}</li>
 *   <li>{@link org.obeonetwork.wgen.template.Image#getWidth <em>Width</em>}</li>
 *   <li>{@link org.obeonetwork.wgen.template.Image#getLegendPOS <em>Legend POS</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.wgen.template.TemplatePackage#getImage()
 * @model
 * @generated
 */
public interface Image extends AbstractConstruct {
	/**
	 * Returns the value of the '<em><b>Legend</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Legend</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Legend</em>' attribute.
	 * @see #setLegend(String)
	 * @see org.obeonetwork.wgen.template.TemplatePackage#getImage_Legend()
	 * @model
	 * @generated
	 */
	String getLegend();

	/**
	 * Sets the value of the '{@link org.obeonetwork.wgen.template.Image#getLegend <em>Legend</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Legend</em>' attribute.
	 * @see #getLegend()
	 * @generated
	 */
	void setLegend(String value);

	/**
	 * Returns the value of the '<em><b>File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File Name</em>' attribute.
	 * @see #setFileName(String)
	 * @see org.obeonetwork.wgen.template.TemplatePackage#getImage_FileName()
	 * @model
	 * @generated
	 */
	String getFileName();

	/**
	 * Sets the value of the '{@link org.obeonetwork.wgen.template.Image#getFileName <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Name</em>' attribute.
	 * @see #getFileName()
	 * @generated
	 */
	void setFileName(String value);

	/**
	 * Returns the value of the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Height</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Height</em>' attribute.
	 * @see #setHeight(int)
	 * @see org.obeonetwork.wgen.template.TemplatePackage#getImage_Height()
	 * @model
	 * @generated
	 */
	int getHeight();

	/**
	 * Sets the value of the '{@link org.obeonetwork.wgen.template.Image#getHeight <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Height</em>' attribute.
	 * @see #getHeight()
	 * @generated
	 */
	void setHeight(int value);

	/**
	 * Returns the value of the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Width</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Width</em>' attribute.
	 * @see #setWidth(int)
	 * @see org.obeonetwork.wgen.template.TemplatePackage#getImage_Width()
	 * @model
	 * @generated
	 */
	int getWidth();

	/**
	 * Sets the value of the '{@link org.obeonetwork.wgen.template.Image#getWidth <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Width</em>' attribute.
	 * @see #getWidth()
	 * @generated
	 */
	void setWidth(int value);

	/**
	 * Returns the value of the '<em><b>Legend POS</b></em>' attribute.
	 * The literals are from the enumeration {@link org.obeonetwork.wgen.template.POSITION}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Legend POS</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Legend POS</em>' attribute.
	 * @see org.obeonetwork.wgen.template.POSITION
	 * @see #setLegendPOS(POSITION)
	 * @see org.obeonetwork.wgen.template.TemplatePackage#getImage_LegendPOS()
	 * @model
	 * @generated
	 */
	POSITION getLegendPOS();

	/**
	 * Sets the value of the '{@link org.obeonetwork.wgen.template.Image#getLegendPOS <em>Legend POS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Legend POS</em>' attribute.
	 * @see org.obeonetwork.wgen.template.POSITION
	 * @see #getLegendPOS()
	 * @generated
	 */
	void setLegendPOS(POSITION value);

} // Image
