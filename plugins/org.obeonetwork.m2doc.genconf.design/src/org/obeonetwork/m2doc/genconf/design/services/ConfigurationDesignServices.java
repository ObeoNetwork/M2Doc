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
package org.obeonetwork.m2doc.genconf.design.services;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.ModelDefinition;
import org.obeonetwork.m2doc.genconf.StringDefinition;
import org.obeonetwork.m2doc.genconf.util.ConfigurationServices;


/**
 * Services for configuration model.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class ConfigurationDesignServices {

    /**
     * Constants.
     */
    private static final String KEY = "key";

    /**
     * ConfigurationServices.
     */
    private ConfigurationServices configurationServices;

    /**
     * EMFServices.
     */
    private EMFServices emfServices;

    /**
     * return the configurationServices.
     * 
     * @return the configurationServices
     */
    public ConfigurationServices getConfigurationServices() {
        if (configurationServices == null) {
            configurationServices = new ConfigurationServices();
        }
        return configurationServices;
    }

    /**
     * return the emfServices.
     * 
     * @return the emfServices
     */
    public EMFServices getEMFServices() {
        if (emfServices == null) {
            emfServices = new EMFServices();
        }
        return emfServices;
    }

    /**
     * Create ModelDefinition.
     * 
     * @param generation
     *            Generation
     * @param valueElement
     *            EObject
     * @return ModelDefinition
     */
    public ModelDefinition createModelDefinition(Generation generation, EObject valueElement) {
        ModelDefinition modelDefinition = getConfigurationServices().createModelDefinition(generation);
        modelDefinition.setKey(KEY);
        modelDefinition.setValue(valueElement);
        return modelDefinition;
    }

    /**
     * Create ModelDefinition.
     * 
     * @param generation
     *            Generation
     * @param valueElement
     *            List<EObject>
     */
    public void createModelDefinition(Generation generation, List<EObject> valueElement) {
        for (EObject eObject : valueElement) {
            createModelDefinition(generation, eObject);
        }
    }

    /**
     * Create StringDefinition.
     * 
     * @param generation
     *            Generation
     * @return StringDefinition
     */
    public StringDefinition createStringDefinition(Generation generation) {
        StringDefinition stringDefinition = getConfigurationServices().createStringDefinition(generation);
        stringDefinition.setKey(KEY);
        return stringDefinition;
    }

    /**
     * return label from .edit itemProvider.
     * 
     * @param eObject
     *            EObject
     * @return label from .edit itemProvider.
     */
    public String getLabel(EObject eObject) {
        return getEMFServices().getLabel(eObject);
    }

    /**
     * return all session eobjects.
     * 
     * @param eObject
     *            EObject
     * @return all session eobjects
     */
    public Collection<EObject> getSessionEObjects(EObject eObject) {
        Session session = SessionManager.INSTANCE.getSession(eObject);
        List<EObject> result = Lists.newArrayList();
        if (session != null) {
            for (Resource res : session.getSemanticResources()) {
                if (res.getURI().isPlatformResource() || res.getURI().isPlatformPlugin()) {
                    Iterators.addAll(result, res.getAllContents());
                }
            }
        }
        return result;
    }

    /**
     * return all root eobjects.
     * 
     * @param eObject
     *            EObject
     * @return all root eobjects
     */
    public Collection<EObject> getRootEObjects(EObject eObject) {
        Session session = SessionManager.INSTANCE.getSession(eObject);
        List<EObject> result = Lists.newArrayList();
        if (session != null) {
            for (Resource res : session.getSemanticResources()) {
                if (res.getURI().isPlatformResource() || res.getURI().isPlatformPlugin()) {
                    result.addAll(res.getContents());
                }
            }
        }
        return result;
    }
}
