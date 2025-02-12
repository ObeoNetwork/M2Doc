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
package org.obeonetwork.m2doc.migrator;

import java.util.List;

import org.apache.poi.xwpf.usermodel.IBody;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;

/**
 * Migrates a M2Doc template from one version to an other.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public interface IM2DocMigrator {

    /**
     * Migrates the given {@link IBody}.
     * 
     * @param body
     *            the {@link IBody} to migrate
     * @return the {@link List} of {@link TemplateValidationMessage}
     */
    List<TemplateValidationMessage> migrate(IBody body);

}
