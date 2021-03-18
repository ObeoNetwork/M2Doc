/*******************************************************************************
 *  Copyright (c) 2021 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.services;

import org.eclipse.acceleo.query.runtime.IService;
import org.eclipse.acceleo.query.runtime.IServiceCompletionProposal;

/**
 * Completion proposal for {@link M2DocTemplateService}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class M2DocTemplateServiceCompletionProposal implements IServiceCompletionProposal {

    /**
     * The {@link M2DocTemplateService}.
     */
    private final M2DocTemplateService service;

    /**
     * constructor.
     * 
     * @param service
     *            the {@link M2DocTemplateService}
     */
    public M2DocTemplateServiceCompletionProposal(M2DocTemplateService service) {
        this.service = service;
    }

    @Override
    public String getProposal() {
        return service.getName() + "()";
    }

    @Override
    public int getCursorOffset() {
        int namelength = service.getName().length();
        if (service.getNumberOfParameters() == 1) {
            /*
             * if we have only one parameter we return the offset: self.serviceCall()^
             */
            return namelength + 2;
        } else {
            /*
             * if we more than one parameter we return the offset: self.serviceCall(^)
             */
            return namelength + 1;
        }
    }

    @Override
    public String getDescription() {
        return "M2Doc template " + service.getShortSignature();
    }

    @Override
    public IService getObject() {
        return service;
    }

}
