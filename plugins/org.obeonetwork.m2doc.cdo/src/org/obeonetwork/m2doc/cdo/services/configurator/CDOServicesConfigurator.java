/*******************************************************************************
 *  Copyright (c) 2017 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.cdo.services.configurator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IService;
import org.eclipse.emf.common.util.Diagnostic;
import org.obeonetwork.m2doc.cdo.M2DocCDOUtils;
import org.obeonetwork.m2doc.services.configurator.IServicesConfigurator;

/**
 * Sirius {@link IServicesConfigurator}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class CDOServicesConfigurator implements IServicesConfigurator {

    /**
     * The {@link List} of options managed by this configurator.
     */
    public static final List<String> OPTIONS = initOptions();

    /**
     * Initializes options.
     * 
     * @return the {@link List} of options.
     */
    private static List<String> initOptions() {
        final List<String> res = new ArrayList<String>();

        res.add(M2DocCDOUtils.CDO_SERVER_OPTION);
        res.add(M2DocCDOUtils.CDO_REPOSITORY_OPTION);
        res.add(M2DocCDOUtils.CDO_BRANCH_OPTION);
        res.add(M2DocCDOUtils.CDO_LOGIN_OPTION);
        res.add(M2DocCDOUtils.CDO_PASSWORD_OPTION);

        return res;
    }

    @Override
    public List<String> getOptions() {
        return OPTIONS;
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
    public Set<IService> getServices(IReadOnlyQueryEnvironment queryEnvironment, Map<String, String> options) {
        return Collections.emptySet();
    }

    @Override
    public void cleanServices(IReadOnlyQueryEnvironment queryEnvironment) {
        // nothing to do here
    }

}
