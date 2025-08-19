/*******************************************************************************
 *  Copyright (c) 2019, 2025 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.wikitext.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IService;
import org.eclipse.acceleo.query.runtime.ServiceUtils;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.obeonetwork.m2doc.services.configurator.IServicesConfigurator;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * HTML {@link IServicesConfigurator}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class WikiTextServicesConfigurator implements IServicesConfigurator {

    /**
     * Mapping from {@link IReadOnlyQueryEnvironment} to its instance of {@link M2DocWikiTextServices}.
     */
    private final Map<IReadOnlyQueryEnvironment, M2DocWikiTextServices> instancies = new HashMap<IReadOnlyQueryEnvironment, M2DocWikiTextServices>();

    @Override
    public List<String> getOptions() {
        return Collections.emptyList();
    }

    @Override
    public Map<String, String> getInitializedOptions(Map<String, String> options) {
        return Collections.emptyMap();
    }

    @Override
    public Map<String, String> getInitializedOptions(Map<String, String> options, EObject eObj) {
        return Collections.emptyMap();
    }

    @Override
    public Map<String, List<Diagnostic>> validate(IReadOnlyQueryEnvironment queryEnvironment,
            Map<String, String> options) {
        return Collections.emptyMap();
    }

    @Override
    public Set<IService<?>> getServices(IReadOnlyQueryEnvironment queryEnvironment, ResourceSet resourceSetForModels,
            Map<String, String> options) {
        M2DocWikiTextServices instance = new M2DocWikiTextServices(resourceSetForModels.getURIConverter(),
                URI.createURI(options.get(M2DocUtils.TEMPLATE_URI_OPTION)));
        instancies.put(queryEnvironment, instance);

        return ServiceUtils.getServices(queryEnvironment, instance);
    }

    @Override
    public void startGeneration(IReadOnlyQueryEnvironment queryEnvironment, XWPFDocument destinationDocument) {
        instancies.get(queryEnvironment).setDestinationDocument(destinationDocument);
    }

    @Override
    public void cleanServices(IReadOnlyQueryEnvironment queryEnvironment, ResourceSet resourceSetForModels) {
        instancies.remove(queryEnvironment);
    }

    @Override
    public ResourceSet createResourceSetForModels(Object context, Map<String, String> options) {
        // nothing to do here
        return null;
    }

    @Override
    public void cleanResourceSetForModels(Object context) {
        // nothing to do here
    }

}
