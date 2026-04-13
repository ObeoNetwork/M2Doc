/*******************************************************************************
 *  Copyright (c) 2026 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.ide.services.namespace;

import org.eclipse.acceleo.query.runtime.IService;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameResolver;
import org.eclipse.emf.common.util.Monitor;
import org.obeonetwork.m2doc.generator.M2DocEvaluationEnvironment;
import org.obeonetwork.m2doc.ide.M2DocPlugin;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.services.namespace.M2DocDocumentTemplateLoader;
import org.obeonetwork.m2doc.template.DocumentTemplate;

/**
 * A {@link M2DocDocumentTemplateLoader} that will try to {@link IQualifiedNameResolver#register(String, Object) register}
 * {@link TemplateCustomProperties#getImports() imported} {@link IService} using the bundle/project information. This allows to resolve
 * imports when the template isn't in a Java project.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class EclipseM2DocDocumentTemplateLoader extends M2DocDocumentTemplateLoader {

    /**
     * Constructor.
     * 
     * @param m2docEnv
     *            the {@link M2DocEvaluationEnvironment}
     * @param monitor
     *            the {@link Monitor}
     * @param qualifierSeparator
     *            the qualifier separator
     */
    public EclipseM2DocDocumentTemplateLoader(M2DocEvaluationEnvironment m2docEnv, Monitor monitor,
            String qualifierSeparator) {
        super(m2docEnv, monitor, qualifierSeparator);
    }

    @Override
    public DocumentTemplate load(IQualifiedNameResolver resolver, String qualifiedName) {
        final DocumentTemplate result = super.load(resolver, qualifiedName);

        if (result != null) {
            M2DocPlugin.getPlugin().loadServicesFromWorkspace(m2docEnv, result);
        }

        return result;
    }

}
