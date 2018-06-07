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
package org.obeonetwork.m2doc.ide;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.obeonetwork.m2doc.services.TemplateRegistry;

/**
 * Listener that registers services that are declared through an extension.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class DeclaredTemplatesListener implements IRegistryEventListener {

    /**
     * Unique ID of the extension point.
     */
    public static final String TEMPLATE_REGISTERY_EXTENSION_POINT = "org.obeonetwork.m2doc.ide.templates.register";

    /**
     * Name of the service element.
     */
    private static final String TEMPLATE_ELEMENT_NAME = "template";

    /**
     * Name of the attribute used to declare the service's class name.
     */
    private static final String NAME_ATTR_NAME = "name";

    /**
     * Name of the attribute used to declare the service's class name.
     */
    private static final String URI_ATTR_NAME = "uri";

    /**
     * Parses initial contributions.
     */
    public void parseInitialContributions() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = registry.getExtensionPoint(TEMPLATE_REGISTERY_EXTENSION_POINT);
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
        for (IConfigurationElement element : extension.getConfigurationElements()) {
            if (TEMPLATE_ELEMENT_NAME.equals(element.getName())) {
                final String templateName = element.getAttribute(NAME_ATTR_NAME);
                final String templateURIString = element.getAttribute(URI_ATTR_NAME);
                TemplateRegistry.INSTANCE.registerTemplate(templateName, URI.createURI(templateURIString));
            }
        }
    }

    @Override
    public void added(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            if (TEMPLATE_REGISTERY_EXTENSION_POINT.equals(extension.getExtensionPointUniqueIdentifier())) {
                add(extension);
            }
        }
    }

    @Override
    public void removed(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            if (TEMPLATE_REGISTERY_EXTENSION_POINT.equals(extension.getExtensionPointUniqueIdentifier())) {
                for (IConfigurationElement element : extension.getConfigurationElements()) {
                    if (TEMPLATE_ELEMENT_NAME.equals(element.getName())) {
                        final String templateName = element.getAttribute(NAME_ATTR_NAME);
                        TemplateRegistry.INSTANCE.unregisterTemplate(templateName);
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
