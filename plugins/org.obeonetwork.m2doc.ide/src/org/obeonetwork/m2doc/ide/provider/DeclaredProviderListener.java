/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.ide.provider;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.obeonetwork.m2doc.ide.M2DocPlugin;
import org.obeonetwork.m2doc.provider.AbstractDiagramProvider;
import org.obeonetwork.m2doc.provider.AbstractTableProvider;
import org.obeonetwork.m2doc.provider.ProviderRegistry;

/**
 * Listener that registers providers that are declared through an extension like diagram providers.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public class DeclaredProviderListener implements IRegistryEventListener {
    /**
     * Unique ID of the extension point.
     */
    private static final String PROVIDER_REGISTERY_ID = "org.obeonetwork.m2doc.ide.providers.register";
    /**
     * Name of the extension element describing a diagram provider.
     */
    private static final String DIAGRAM_PROVIDER_EXTENSION_ELEMENT = "diagramProvider";
    /**
     * Name of the attribute used to declare the diagram provider's class name.
     */
    private static final String DIAGRAM_PROVIDER_CLASS_ATTR_NAME = "diagramClass";

    /**
     * Name of the extension element describing a table provider.
     */
    private static final String TABLE_PROVIDER_EXTENSION_ELEMENT = "tableProvider";

    /**
     * Name of the attribute used to declare the table provider's class name.
     */
    private static final String TABLE_PROVIDER_CLASS_ATTR_NAME = "class";

    /**
     * Creates and initializes the service registry listener.
     */
    public DeclaredProviderListener() {
        // initializes the extensions
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = registry.getExtensionPoint(PROVIDER_REGISTERY_ID);
        for (IExtension extension : extensionPoint.getExtensions()) {
            add(extension);
        }
    }

    /**
     * adds an extension to this.
     * 
     * @param extension
     *            the extension
     */
    private void add(IExtension extension) {
        for (IConfigurationElement confElt : extension.getConfigurationElements()) {
            if (DIAGRAM_PROVIDER_EXTENSION_ELEMENT.equals(confElt.getName())) {
                try {
                    AbstractDiagramProvider newDiagramProvider = (AbstractDiagramProvider) confElt
                            .createExecutableExtension(DIAGRAM_PROVIDER_CLASS_ATTR_NAME);
                    if (!ProviderRegistry.INSTANCE.containsProvider(newDiagramProvider)) {
                        ProviderRegistry.INSTANCE.registerProvider(newDiagramProvider);
                    } else {
                        M2DocPlugin.INSTANCE.log(new Status(Status.ERROR, M2DocPlugin.PLUGIN_ID,
                                "Problem while registering M2Doc Providers : the provider \""
                                    + newDiagramProvider.getClass().getName()
                                    + "\" is already registered. The current implementation will not be used."));
                    }
                } catch (CoreException e) {
                    // CHECKSTYLE:OFF
                    M2DocPlugin.INSTANCE.log(new Status(Status.ERROR, M2DocPlugin.PLUGIN_ID, Status.ERROR,
                            "Problem while registering M2Doc Providers : " + e.getMessage(), e));
                    // CHECKSTYLE:ON
                } catch (InvalidRegistryObjectException e) {
                    M2DocPlugin.INSTANCE.log(new Status(Status.ERROR, M2DocPlugin.PLUGIN_ID, Status.ERROR,
                            "Problem while registering M2Doc Providers : " + e.getMessage(), e));
                } catch (ClassCastException e) {
                    M2DocPlugin.INSTANCE.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID,
                            "The registered table provider does not extend AbstractDiagramProvider.", e));
                }
            } else if (TABLE_PROVIDER_EXTENSION_ELEMENT.equals(confElt.getName())) {
                try {
                    AbstractTableProvider provider = (AbstractTableProvider) confElt
                            .createExecutableExtension(TABLE_PROVIDER_CLASS_ATTR_NAME);
                    if (!ProviderRegistry.INSTANCE.containsProvider(provider)) {
                        ProviderRegistry.INSTANCE.registerProvider(provider);
                    } else {
                        M2DocPlugin.INSTANCE.log(new Status(Status.ERROR, M2DocPlugin.PLUGIN_ID,
                                "Problem while registering M2Doc Providers : the provider \""
                                    + provider.getClass().getName()
                                    + "\" is already registered. The current implementation will not be used."));
                    }
                } catch (CoreException e) {
                    // CHECKSTYLE:OFF
                    M2DocPlugin.INSTANCE.log(new Status(Status.ERROR, M2DocPlugin.PLUGIN_ID,
                            "Problem while registering M2Doc Providers : " + e.getMessage(), e));
                    // CHECKSTYLE:ON
                } catch (InvalidRegistryObjectException e) {
                    M2DocPlugin.INSTANCE.log(new Status(Status.ERROR, M2DocPlugin.PLUGIN_ID,
                            "Problem while registering M2Doc Providers : " + e.getMessage(), e));
                } catch (ClassCastException e) {
                    M2DocPlugin.INSTANCE.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID,
                            "The registered table provider does not extend AbstractTableProvider.", e));
                }
            }
        }
    }

    @Override
    public void added(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            if (PROVIDER_REGISTERY_ID.equals(extension.getExtensionPointUniqueIdentifier())) {
                add(extension);
            }
        }
    }

    @Override
    public void removed(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            if (PROVIDER_REGISTERY_ID.equals(extension.getExtensionPointUniqueIdentifier())) {
                for (IConfigurationElement confElt : extension.getConfigurationElements()) {
                    if (DIAGRAM_PROVIDER_EXTENSION_ELEMENT.equals(confElt.getName())) {
                        try {
                            AbstractDiagramProvider newDiagramProvider = (AbstractDiagramProvider) confElt
                                    .createExecutableExtension(DIAGRAM_PROVIDER_CLASS_ATTR_NAME);
                            ProviderRegistry.INSTANCE.removeProvider(newDiagramProvider);
                        } catch (CoreException e) {
                            // CHECKSTYLE:OFF
                            M2DocPlugin.INSTANCE.log(new Status(Status.ERROR, M2DocPlugin.PLUGIN_ID, Status.ERROR,
                                    "Problem while unregistering M2Doc Providers : " + e.getMessage(), e));
                            // CHECKSTYLE:ON
                        } catch (InvalidRegistryObjectException e) {
                            M2DocPlugin.INSTANCE.log(new Status(Status.ERROR, M2DocPlugin.PLUGIN_ID, Status.ERROR,
                                    "Problem while unregistering M2Doc Providers : " + e.getMessage(), e));
                        }

                    }
                }
            }
        }
    }

    @Override
    public void added(IExtensionPoint[] extensionPoints) {
        // Do nothing.
    }

    @Override
    public void removed(IExtensionPoint[] extensionPoints) {
        // Do nothing.
    }

}
