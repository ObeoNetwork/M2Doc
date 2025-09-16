/*******************************************************************************
 *  Copyright (c) 2024, 2025 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.ide.ui.util;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.obeonetwork.m2doc.ide.ui.M2DocUIPlugin;

/**
 * Listener that registers class provider that are declared through an extension.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ClassPropertyUpdaterRegistryListener implements IRegistryEventListener {

    /**
     * The class constant.
     */
    public static final String CLASS = "class";

    /**
     * {@link IClassPropertyUpdater} extension point to parse for extensions.
     */
    public static final String CLASS_PROPERTY_UPDATER_EXTENSION_POINT = "org.obeonetwork.m2doc.ide.ui.classpropertyupdater";

    /**
     * {@link IClassPropertyUpdater} tag.
     */
    public static final String CLASS_PROPERTY_UPDATER_TAG_EXTENSION = "classpropertyupdater";

    /**
     * The {@link IClassPropertyUpdater} extension point class attribute.
     */
    public static final String CLASS_PROPERTY_UPDATER_ATTRIBUTE_CLASS = CLASS;

    @Override
    public void added(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            if (CLASS_PROPERTY_UPDATER_EXTENSION_POINT.equals(extension.getUniqueIdentifier())) {
                parseClassProviderExtension(extension);
            }
        }
    }

    @Override
    public void added(IExtensionPoint[] extensionPoints) {
        // no need to listen to this event
    }

    /**
     * Though this listener reacts to the extension point changes, there could have been contributions before
     * it's been registered. This will parse these initial contributions.
     */
    public void parseInitialContributions() {
        final IExtensionRegistry registry = Platform.getExtensionRegistry();

        for (IExtension extension : registry.getExtensionPoint(CLASS_PROPERTY_UPDATER_EXTENSION_POINT)
                .getExtensions()) {
            parseClassProviderExtension(extension);
        }
    }

    @Override
    public void removed(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            final IConfigurationElement[] configElements = extension.getConfigurationElements();
            for (IConfigurationElement element : configElements) {
                if (CLASS_PROPERTY_UPDATER_TAG_EXTENSION.equals(element.getName())) {
                    try {
                        final IClassPropertyUpdater updater = (IClassPropertyUpdater) element
                                .createExecutableExtension(CLASS_PROPERTY_UPDATER_ATTRIBUTE_CLASS);
                        if (M2DocUIPlugin.getClassPropertyUpdater().getClass() == updater.getClass()) {
                            M2DocUIPlugin.registerClassPropertyUpdater(null);
                        }
                    } catch (CoreException e) {
                        M2DocUIPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, getClass(), e.getMessage()));
                    }
                }
            }
        }
    }

    @Override
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
            if (CLASS_PROPERTY_UPDATER_TAG_EXTENSION.equals(element.getName())) {
                try {
                    final IClassPropertyUpdater updater = (IClassPropertyUpdater) element
                            .createExecutableExtension(CLASS_PROPERTY_UPDATER_ATTRIBUTE_CLASS);
                    M2DocUIPlugin.registerClassPropertyUpdater(updater);
                } catch (CoreException e) {
                    M2DocUIPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, getClass(), e.getMessage()));
                }
            }
        }
    }

}
