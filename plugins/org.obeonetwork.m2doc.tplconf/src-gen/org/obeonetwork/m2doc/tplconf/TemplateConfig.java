/**
 */
package org.obeonetwork.m2doc.tplconf;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Template Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.tplconf.TemplateConfig#getMappings <em>Mappings</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.tplconf.TemplateConfig#getVariables <em>Variables</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.tplconf.TemplateConfig#getTypesByName <em>Types By Name</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.tplconf.TplconfPackage#getTemplateConfig()
 * @model
 * @generated
 */
public interface TemplateConfig extends EObject {
    /**
     * Returns the value of the '<em><b>Mappings</b></em>' containment reference list.
     * The list contents are of type {@link org.obeonetwork.m2doc.tplconf.EPackageMapping}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mappings</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Mappings</em>' containment reference list.
     * @see org.obeonetwork.m2doc.tplconf.TplconfPackage#getTemplateConfig_Mappings()
     * @model containment="true"
     * @generated
     */
    EList<EPackageMapping> getMappings();

    /**
     * Returns the value of the '<em><b>Variables</b></em>' containment reference list.
     * The list contents are of type {@link org.obeonetwork.m2doc.tplconf.TemplateVariable}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variables</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Variables</em>' containment reference list.
     * @see org.obeonetwork.m2doc.tplconf.TplconfPackage#getTemplateConfig_Variables()
     * @model containment="true"
     * @generated
     */
    EList<TemplateVariable> getVariables();

    /**
     * Returns the value of the '<em><b>Types By Name</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link org.obeonetwork.m2doc.tplconf.TemplateType},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Types By Name</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Types By Name</em>' map.
     * @see org.obeonetwork.m2doc.tplconf.TplconfPackage#getTemplateConfig_TypesByName()
     * @model mapType="org.obeonetwork.m2doc.tplconf.StringToTypeMapEntry<org.eclipse.emf.ecore.EString,
     *        org.obeonetwork.m2doc.tplconf.TemplateType>"
     * @generated
     */
    EMap<String, TemplateType> getTypesByName();

} // TemplateConfig
