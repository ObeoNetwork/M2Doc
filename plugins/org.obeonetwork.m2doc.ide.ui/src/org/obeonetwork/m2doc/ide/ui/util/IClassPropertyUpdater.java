/*******************************************************************************
 *  Copyright (c) 2024 Obeo. 
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

import org.obeonetwork.m2doc.properties.TemplateCustomProperties;

/**
 * Updates imported classes for the given {@link TemplateCustomProperties}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public interface IClassPropertyUpdater {

    /**
     * Updates the given {@link TemplateCustomProperties} imported classes.
     * 
     * @param customProperties
     *            the {@link TemplateCustomProperties} to update
     * @return <code>true</code> if the given {@link TemplateCustomProperties} has been updated, <code>false</code> otherwise
     */
    boolean updatePropertyClasses(TemplateCustomProperties customProperties);

}
