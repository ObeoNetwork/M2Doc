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
package org.obeonetwork.m2doc.ide.services.configurator;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.Platform;
import org.obeonetwork.m2doc.ide.M2DocPlugin;
import org.obeonetwork.m2doc.services.configurator.IServicesConfigurator;
import org.obeonetwork.m2doc.services.configurator.IServicesConfiguratorDescriptor;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Listener that registers service configurators that are declared through an extension.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ServicesConfiguratorRegistryListener implements IRegistryEventListener {

    /**
     * The class constant.
     */
    public static final String CLASS = "class";

    /**
     * {@link IServicesConfigurator} extension point to parse for extensions.
     */
    public static final String SERVICES_CONFIGURATOR_EXTENSION_POINT = "org.obeonetwork.m2doc.ide.servicesConfigurator";

    /**
     * {@link IServicesConfigurator} tag.
     */
    public static final String SERVICES_CONFIGURATOR_TAG_EXTENSION = "servicesConfigurators";

    /**
     * The {@link IServicesConfigurator} extension point class attribute.
     */
    public static final String SERVICES_CONFIGURATOR_ATTRIBUTE_CLASS = CLASS;

    /**
     * The mapping from the class name to the {@link IServicesConfiguratorDescriptor}.
     */
    private final Map<String, IServicesConfiguratorDescriptor> descriptors = new HashMap<String, IServicesConfiguratorDescriptor>();

    /**
     * An {@link IServicesConfiguratorDescriptor} for an extension point.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    public static class ExtensionServicesConfiguratorDescriptor implements IServicesConfiguratorDescriptor {

        /**
         * The {@link IConfigurationElement}.
         */
        private final IConfigurationElement element;

        /**
         * The {@link IServicesConfigurator}.
         */
        private IServicesConfigurator configurator;

        /**
         * Constructor.
         * 
         * @param element
         *            the {@link IConfigurationElement}
         */
        public ExtensionServicesConfiguratorDescriptor(IConfigurationElement element) {
            this.element = element;
        }

        @Override
        public IServicesConfigurator getServicesConfigurator() {
            if (configurator == null) {
                try {
                    configurator = (IServicesConfigurator) element
                            .createExecutableExtension(SERVICES_CONFIGURATOR_ATTRIBUTE_CLASS);
                } catch (CoreException e) {
                    M2DocPlugin.log(e, false);
                }
            }

            return configurator;
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.IRegistryEventListener#added(org.eclipse.core.runtime.IExtension[])
     */
    public void added(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            if (SERVICES_CONFIGURATOR_EXTENSION_POINT.equals(extension.getUniqueIdentifier())) {
                parseServicesConfiguratorExtension(extension);
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.IRegistryEventListener#added(org.eclipse.core.runtime.IExtensionPoint[])
     */
    public void added(IExtensionPoint[] extensionPoints) {
        // no need to listen to this event
    }

    /**
     * Though this listener reacts to the extension point changes, there could have been contributions before
     * it's been registered. This will parse these initial contributions.
     */
    public void parseInitialContributions() {
        final IExtensionRegistry registry = Platform.getExtensionRegistry();

        for (IExtension extension : registry.getExtensionPoint(SERVICES_CONFIGURATOR_EXTENSION_POINT).getExtensions()) {
            parseServicesConfiguratorExtension(extension);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.IRegistryEventListener#removed(org.eclipse.core.runtime.IExtension[])
     */
    public void removed(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            final IConfigurationElement[] configElements = extension.getConfigurationElements();
            for (IConfigurationElement element : configElements) {
                if (SERVICES_CONFIGURATOR_TAG_EXTENSION.equals(element.getName())) {
                    final IServicesConfiguratorDescriptor descriptor = descriptors
                            .remove(element.getAttribute(SERVICES_CONFIGURATOR_ATTRIBUTE_CLASS));
                    if (descriptor != null) {
                        M2DocUtils.unregisterServicesConfigurator(descriptor);
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.IRegistryEventListener#removed(org.eclipse.core.runtime.IExtensionPoint[])
     */
    public void removed(IExtensionPoint[] extensionPoints) {
        // no need to listen to this event
    }

    /**
     * Parses a single {@link ILocation} extension contribution.
     * 
     * @param extension
     *            Parses the given extension and adds its contribution to the registry
     */
    private void parseServicesConfiguratorExtension(IExtension extension) {
        final IConfigurationElement[] configElements = extension.getConfigurationElements();
        for (IConfigurationElement element : configElements) {
            if (SERVICES_CONFIGURATOR_TAG_EXTENSION.equals(element.getName())) {
                final IServicesConfiguratorDescriptor descriptor = new ExtensionServicesConfiguratorDescriptor(element);
                descriptors.put(element.getAttribute(SERVICES_CONFIGURATOR_ATTRIBUTE_CLASS), descriptor);
                M2DocUtils.registerServicesConfigurator(descriptor);
            }
        }
    }

}
