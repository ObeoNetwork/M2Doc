/*******************************************************************************
 *  Copyright (c) 2019 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.ide.ui.services.configurator;

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
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.obeonetwork.m2doc.ide.ui.services.M2DocEObjectServices;
import org.obeonetwork.m2doc.ide.ui.services.SWTPromptServices;
import org.obeonetwork.m2doc.services.configurator.IServicesConfigurator;

/**
 * {@link SWTPromptServices} {@link IServicesConfigurator}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class M2DocEObjectServicesConfigurator implements IServicesConfigurator {

    /**
     * The mapping of instances.
     */
    private final Map<Object, M2DocEObjectServices> services = new HashMap<>();

    @Override
    public List<String> getOptions() {
        return Collections.emptyList();
    }

    @Override
    public Map<String, String> getInitializedOptions(Map<String, String> options) {
        return Collections.emptyMap();
    }

    @Override
    public Map<String, List<Diagnostic>> validate(IReadOnlyQueryEnvironment queryEnvironment,
            Map<String, String> options) {
        return Collections.emptyMap();
    }

    @Override
    public Set<IService> getServices(IReadOnlyQueryEnvironment queryEnvironment, ResourceSet resourceSetForModels,
            Map<String, String> options) {
        M2DocEObjectServices serviceInstance = new M2DocEObjectServices();
        services.put(queryEnvironment, serviceInstance);

        return ServiceUtils.getServices(queryEnvironment, serviceInstance);
    }

    @Override
    public void startGeneration(IReadOnlyQueryEnvironment queryEnvironment, XWPFDocument destinationDocument) {
        // nothing to do here
    }

    @Override
    public void cleanServices(IReadOnlyQueryEnvironment queryEnvironment, ResourceSet resourceSetForModels) {
        final M2DocEObjectServices serviceInstance = services.remove(queryEnvironment);
        if (serviceInstance != null) {
            serviceInstance.dispose();
        }
    }

    @Override
    public ResourceSet createResourceSetForModels(Object context, Map<String, String> options) {
        return null;
    }

    @Override
    public void cleanResourceSetForModels(Object context) {
        // nothing to do here
    }

}
