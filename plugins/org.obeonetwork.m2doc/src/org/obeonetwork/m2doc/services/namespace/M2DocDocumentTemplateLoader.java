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
package org.obeonetwork.m2doc.services.namespace;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.acceleo.query.runtime.IService;
import org.eclipse.acceleo.query.runtime.impl.namespace.AbstractLoader;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameLookupEngine;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameResolver;
import org.eclipse.acceleo.query.runtime.namespace.ISourceLocation;
import org.eclipse.acceleo.query.util.AqlResolverURIHandler;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.obeonetwork.m2doc.generator.M2DocEvaluationEnvironment;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.services.M2DocTemplateService;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Load M2Doc {@link DocumentTemplate}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class M2DocDocumentTemplateLoader extends AbstractLoader {

    /**
     * The {@link M2DocEvaluationEnvironment}.
     */
    protected final M2DocEvaluationEnvironment m2docEnv;

    /**
     * The {@link Monitor}.
     */
    protected final Monitor monitor;

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
    public M2DocDocumentTemplateLoader(M2DocEvaluationEnvironment m2docEnv, Monitor monitor,
            String qualifierSeparator) {
        super(qualifierSeparator, M2DocUtils.DOCX_EXTENSION_FILE, M2DocUtils.DOCX_EXTENSION_FILE);
        this.m2docEnv = m2docEnv;
        this.monitor = monitor;
    }

    @Override
    public DocumentTemplate load(IQualifiedNameResolver resolver, String qualifiedName) {
        DocumentTemplate res;

        try (InputStream is = resolver.getInputStream(resourceName(qualifiedName))) {
            if (is != null) {
                final URIConverter uriConverter = new ExtensibleURIConverterImpl();
                final URIHandler resolverHandler = new AqlResolverURIHandler(resolver);
                uriConverter.getURIHandlers().add(0, resolverHandler);
                final URI templateURI = AqlResolverURIHandler.createAqlResourceResolverURI(resourceName(qualifiedName));
                res = M2DocUtils.parse(uriConverter, templateURI, qualifiedName, new BasicMonitor());
            } else {
                res = null;
            }
        } catch (IOException | DocumentParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            res = null;
        }

        return res;
    }

    @Override
    public boolean canHandle(Object object) {
        return object instanceof DocumentTemplate;
    }

    @Override
    public Set<IService<?>> getServices(IQualifiedNameLookupEngine lookupEngine, Object object,
            String contextQualifiedName) {
        final Set<IService<?>> res = new LinkedHashSet<>();

        final DocumentTemplate documentTemplate = (DocumentTemplate) object;
        if (!documentTemplate.getTemplates().isEmpty()) {
            final byte[] serializedDocument = serializeDocument(documentTemplate);
            if (serializedDocument != null) {
                for (Template template : documentTemplate.getTemplates()) {
                    res.add(new M2DocTemplateService(template, serializedDocument, m2docEnv, lookupEngine,
                            contextQualifiedName, monitor));
                }
            }
        }

        return res;
    }

    /**
     * Serializes the given {@link DocumentTemplate}.
     * 
     * @param documentTemplate
     *            the {@link DocumentTemplate} to serialize
     * @return the byte array of the serialized {@link DocumentTemplate}
     */
    @SuppressWarnings("resource")
    protected byte[] serializeDocument(DocumentTemplate documentTemplate) {
        byte[] serializedDocument;
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            documentTemplate.getDocument().write(output);
            serializedDocument = output.toByteArray();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            serializedDocument = null;
        }
        return serializedDocument;
    }

    @Override
    public List<String> getImports(Object object) {
        return ((DocumentTemplate) object).getImports();
    }

    @Override
    public List<String> getNsURIImports(Object object) {
        return ((DocumentTemplate) object).getMetamodels();
    }

    @Override
    public String getExtends(Object object) {
        return ((DocumentTemplate) object).getExtend();
    }

    @Override
    public ISourceLocation getSourceLocation(IQualifiedNameResolver resolver, IService<?> service) {
        return null;
    }

    @Override
    public ISourceLocation getSourceLocation(IQualifiedNameResolver resolver, String qualifiedName) {
        return null;
    }

}
