/**
 */
package org.obeonetwork.m2doc.genconf.presentation;

import org.eclipse.emf.common.EMFPlugin;

import org.eclipse.emf.common.ui.EclipseUIPlugin;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;

/**
 * This is the central singleton for the M2docconf editor plugin.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public final class M2docconfEditorPlugin extends EMFPlugin {
	/**
     * Keep track of the singleton.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final M2docconfEditorPlugin INSTANCE = new M2docconfEditorPlugin();
	
	/**
     * Keep track of the singleton.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static Implementation plugin;

	/**
     * Create the instance.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public M2docconfEditorPlugin() {
        super
            (new ResourceLocator [] {
                EcoreEditPlugin.INSTANCE,
            });
    }

	/**
     * Returns the singleton instance of the Eclipse plugin.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the singleton instance.
     * @generated
     */
	@Override
	public ResourceLocator getPluginResourceLocator() {
        return plugin;
    }
	
	/**
     * Returns the singleton instance of the Eclipse plugin.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the singleton instance.
     * @generated
     */
	public static Implementation getPlugin() {
        return plugin;
    }
	
	/**
     * The actual implementation of the Eclipse <b>Plugin</b>.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static class Implementation extends EclipseUIPlugin {
		/**
         * Creates an instance.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		public Implementation() {
            super();
    
            // Remember the static instance.
            //
            plugin = this;
        }
	}

}
