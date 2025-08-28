/*******************************************************************************
 *  Copyright (c) 2025 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.services.configurator;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.services.configurator.IServicesConfigurator;

/**
 * An {@link IServicesConfigurator} specific to M2Doc.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public interface IM2DocServicesConfigurator extends IServicesConfigurator {

    /**
     * Starts the generation for the given {@link IReadOnlyQueryEnvironment} and destination {@link XWPFDocument}.
     * 
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param destinationDocument
     *            the {@link XWPFDocument}
     */
    void startGeneration(IReadOnlyQueryEnvironment queryEnvironment, XWPFDocument destinationDocument);

}
