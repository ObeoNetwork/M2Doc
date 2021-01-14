/*******************************************************************************
 *  Copyright (c) 2018 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.genconf.editor;

import org.obeonetwork.m2doc.properties.TemplateCustomProperties;

/**
 * Provides {@link TemplateCustomProperties}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public interface ITemplateCustomPropertiesProvider {

    /**
     * Gets the {@link TemplateCustomProperties}.
     * 
     * @return the {@link TemplateCustomProperties} if any, <code>null</code> otherwise
     */
    TemplateCustomProperties getTemplateCustomProperties();

}
