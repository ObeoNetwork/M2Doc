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
package org.obeonetwork.m2doc.genconf.provider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.obeonetwork.m2doc.M2DocPlugin;

/**
 * Register declared org.obeonetwork.m2doc.configuration extension point.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public final class ConfigurationProviderService {
    /**
     * Name of the configuration element.
     */
    private static final String CONFIGURATION_ELEMENT_NAME = "configuration";
    /**
     * Name of the attribute used to declare the provider class name.
     */
    private static final String PROVIDER_CLASS_ATTR_NAME = "providerClass";

    /**
     * A singleton for the service.
     */
    private static ConfigurationProviderService instance = new ConfigurationProviderService();

    /**
     * The factory where the providers are registered.
     */
    private List<IConfigurationProvider> providers;

    /**
     * Default constructor.
     */
    private ConfigurationProviderService() {
        providers = new ArrayList<IConfigurationProvider>();
        configureService();
    }

    /**
     * Singleton constructor.
     * 
     * @return the Singleton instance
     */
    public static ConfigurationProviderService getInstance() {
        return instance;
    }

    /**
     * Return all the registered providers.
     * 
     * @return all the registered providers
     */
    public List<IConfigurationProvider> getProviders() {
        return providers;
    }

    /**
     * Register a provider.
     * 
     * @param provider
     *            IConfigurationProvider
     */
    public void register(IConfigurationProvider provider) {
        providers.add(provider);
    }

    /**
     * Load all the providers registered by extension point.
     */
    private void configureService() {
        IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(M2DocPlugin.PLUGIN_ID,
                CONFIGURATION_ELEMENT_NAME);

        if (extensionPoint != null) {
            IExtension[] extensions = extensionPoint.getExtensions();
            for (int extensionIndex = 0; extensionIndex < extensions.length; extensionIndex++) {
                IExtension extension = extensions[extensionIndex];
                IConfigurationElement[] configurationElements = extension.getConfigurationElements();
                for (int i = 0; i < configurationElements.length; i++) {
                    IConfigurationElement cfg = configurationElements[i];

                    if (CONFIGURATION_ELEMENT_NAME.equals(cfg.getName())) {
                        try {
                            register((IConfigurationProvider) cfg.createExecutableExtension(PROVIDER_CLASS_ATTR_NAME));
                        } catch (CoreException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }

    }

}
