/*******************************************************************************
 *  Copyright (c) 2020 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.ide.util;

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
import org.obeonetwork.m2doc.util.IClassProvider;

/**
 * Listener that registers class provider that are declared through an extension.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ClassProviderRegistryListener implements IRegistryEventListener {

    /**
     * The class constant.
     */
    public static final String CLASS = "class";

    /**
     * {@link IClassProvider} extension point to parse for extensions.
     */
    public static final String CLASS_PROVIDER_EXTENSION_POINT = "org.obeonetwork.m2doc.ide.classprovider";

    /**
     * {@link IClassProvider} tag.
     */
    public static final String CLASS_PROVIDER_TAG_EXTENSION = "classprovider";

    /**
     * The {@link IClassProvider} extension point class attribute.
     */
    public static final String CLASS_PROVIDER_ATTRIBUTE_CLASS = CLASS;

    /**
     * The mapping from the class name to the {@link IClassProviderDescriptor}.
     */
    private final Map<String, IClassProviderDescriptor> descriptors = new HashMap<String, IClassProviderDescriptor>();

    /**
     * An {@link IClassProviderDescriptor} for an extension point.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    public static class ExtensionClassProviderDescriptor implements IClassProviderDescriptor {

        /**
         * The {@link IConfigurationElement}.
         */
        private final IConfigurationElement element;

        /**
         * The {@link IClassProvider}.
         */
        private EclipseClassProvider provider;

        /**
         * Constructor.
         * 
         * @param element
         *            the {@link IConfigurationElement}
         */
        public ExtensionClassProviderDescriptor(IConfigurationElement element) {
            this.element = element;
        }

        @Override
        public EclipseClassProvider getClassProvider() {
            if (provider == null) {
                try {
                    provider = (EclipseClassProvider) element.createExecutableExtension(CLASS_PROVIDER_ATTRIBUTE_CLASS);
                } catch (CoreException e) {
                    M2DocPlugin.log(e, false);
                }
            }

            return provider;
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.IRegistryEventListener#added(org.eclipse.core.runtime.IExtension[])
     */
    public void added(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            if (CLASS_PROVIDER_EXTENSION_POINT.equals(extension.getUniqueIdentifier())) {
                parseClassProviderExtension(extension);
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

        for (IExtension extension : registry.getExtensionPoint(CLASS_PROVIDER_EXTENSION_POINT).getExtensions()) {
            parseClassProviderExtension(extension);
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
                if (CLASS_PROVIDER_TAG_EXTENSION.equals(element.getName())) {
                    final IClassProviderDescriptor descriptor = descriptors
                            .remove(element.getAttribute(CLASS_PROVIDER_ATTRIBUTE_CLASS));
                    if (descriptor != null) {
                        M2DocPlugin.unregisterClassProvider(descriptor);
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
    private void parseClassProviderExtension(IExtension extension) {
        final IConfigurationElement[] configElements = extension.getConfigurationElements();
        for (IConfigurationElement element : configElements) {
            if (CLASS_PROVIDER_TAG_EXTENSION.equals(element.getName())) {
                final IClassProviderDescriptor descriptor = new ExtensionClassProviderDescriptor(element);
                descriptors.put(element.getAttribute(CLASS_PROVIDER_ATTRIBUTE_CLASS), descriptor);
                M2DocPlugin.registerClassProvider(descriptor);
            }
        }
    }

}
