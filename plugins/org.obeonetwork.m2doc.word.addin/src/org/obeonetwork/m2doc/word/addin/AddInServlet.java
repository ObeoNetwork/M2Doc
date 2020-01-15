/*******************************************************************************
 *  Copyright (c) 2020 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.word.addin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.parser.AstValidator;
import org.eclipse.acceleo.query.runtime.ICompletionProposal;
import org.eclipse.acceleo.query.runtime.ICompletionResult;
import org.eclipse.acceleo.query.runtime.IQueryValidationEngine;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IValidationResult;
import org.eclipse.acceleo.query.runtime.QueryCompletion;
import org.eclipse.acceleo.query.runtime.impl.QueryCompletionEngine;
import org.eclipse.acceleo.query.runtime.impl.QueryValidationEngine;
import org.eclipse.acceleo.query.runtime.impl.ValidationServices;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jetty.http.MetaData.Request;
import org.eclipse.jetty.http.MimeTypes;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.ajax.JSON;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * The Add-in servlet.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class AddInServlet extends HttpServlet {

    /**
     * The resources path.
     */
    private static final String RESOURCE_PATH = "/resources";

    /**
     * The manigest path.
     */
    private static final String MANIFEST_PATH = "/manifest.xml";

    /**
     * The rest api path.
     */
    private static final String API_PATH = "/rest";

    /**
     * The command parameter.
     */
    private static final String COMMAND_PARAMETER = "command";

    /**
     * The genconf uri parameter.
     */
    private static final String GENCONF_URI_PARAMETER = "genconfURI";

    /**
     * The expression parameter.
     */
    private static final String EXPRESSION_PARAMETER = "expression";

    /**
     * The offset parameter.
     */
    private static final String OFFSET_PARAMETER = "offset";

    /**
     * The completion parameter.
     */
    private static final String COMPLETION_PARAMETER = "completion";

    /**
     * The proposal command.
     */
    private static final String PROPOSAL_COMMAND = "proposal";

    /**
     * The apply command.
     */
    private static final String APPLY_COMMAND = "apply";

    /**
     * The validate command.
     */
    private static final String VALIDATE_COMMAND = "validate";

    /**
     * The evaluate command.
     */
    private static final String EVALUATE_COMMAND = "evaluate";

    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 7506740830524840300L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        if (!req.getPathInfo().startsWith(API_PATH)) {
            try (InputStream inputStream = AddInServlet.class.getResourceAsStream(RESOURCE_PATH + req.getPathInfo());) {
                if (inputStream != null) {
                    if (MANIFEST_PATH.equals(req.getPathInfo())) {
                        getManifest(M2DocUtils.VERSION, req.getServerName(), req.getServerPort(), inputStream, resp);
                    } else {
                        resp.setContentType(URLConnection.guessContentTypeFromName(req.getPathInfo()));
                        try (ServletOutputStream outputStream = resp.getOutputStream();) {
                            IOUtils.copy(inputStream, outputStream);
                        }
                        resp.setStatus(HttpServletResponse.SC_OK);
                    }
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.setContentType(MimeTypes.Type.TEXT_HTML.toString());
                    resp.getWriter().println("<h1>404<h1>");
                }
            }
        } else {
            doRestAPI(req, resp);
        }
    }

    /**
     * @param req
     * @param resp
     * @throws IOException
     */
    private void doRestAPI(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String genconfURI = req.getParameter(GENCONF_URI_PARAMETER);
        if (genconfURI != null && !genconfURI.isEmpty()) {
            Generation generation = null;
            try {
                generation = GenconfUtils.getGeneration(URI.createURI(genconfURI));
            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
                resp.getWriter().println("can't load .genconf file: " + e.getMessage());
            }
            if (generation != null) {
                final List<Exception> exceptions = new ArrayList<>();
                final ResourceSet resourceSetForModels = M2DocUtils.createResourceSetForModels(exceptions, generation,
                        new ResourceSetImpl(), GenconfUtils.getOptions(generation));
                final IReadOnlyQueryEnvironment queryEnvironment = GenconfUtils
                        .getQueryEnvironment(resourceSetForModels, generation);
                final URI templateURI = GenconfUtils.getResolvedURI(generation,
                        URI.createURI(generation.getTemplateFileName(), false));
                final String command = req.getParameter(COMMAND_PARAMETER);
                if (command != null && !command.isEmpty()) {
                    switch (command) {
                        case PROPOSAL_COMMAND:
                            try (XWPFDocument document = POIServices.getInstance()
                                    .getXWPFDocument(resourceSetForModels.getURIConverter(), templateURI)) {
                                final TemplateCustomProperties properties = new TemplateCustomProperties(document);
                                final AstValidator validator = new AstValidator(
                                        new ValidationServices(queryEnvironment));
                                final Map<String, Set<IType>> variableTypes = properties.getVariableTypes(validator,
                                        queryEnvironment);
                                getProposals(queryEnvironment, variableTypes, req, resp);
                            } catch (IOException e) {
                                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                                resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
                                resp.getWriter().println("can't load template: " + e.getMessage());
                            }
                            break;

                        case APPLY_COMMAND:
                            try (XWPFDocument document = POIServices.getInstance()
                                    .getXWPFDocument(resourceSetForModels.getURIConverter(), templateURI)) {
                                final TemplateCustomProperties properties = new TemplateCustomProperties(document);
                                final AstValidator validator = new AstValidator(
                                        new ValidationServices(queryEnvironment));
                                final Map<String, Set<IType>> variableTypes = properties.getVariableTypes(validator,
                                        queryEnvironment);
                                getAppliedProposal(queryEnvironment, variableTypes, req, resp);
                            } catch (IOException e) {
                                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                                resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
                                resp.getWriter().println("can't load template: " + e.getMessage());
                            }
                            break;

                        case VALIDATE_COMMAND:
                            try (XWPFDocument document = POIServices.getInstance()
                                    .getXWPFDocument(resourceSetForModels.getURIConverter(), templateURI)) {
                                final TemplateCustomProperties properties = new TemplateCustomProperties(document);
                                final AstValidator validator = new AstValidator(
                                        new ValidationServices(queryEnvironment));
                                final Map<String, Set<IType>> variableTypes = properties.getVariableTypes(validator,
                                        queryEnvironment);
                                getValidation(queryEnvironment, variableTypes, req, resp);
                            } catch (IOException e) {
                                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                                resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
                                resp.getWriter().println("can't load template: " + e.getMessage());
                            }
                            break;

                        case EVALUATE_COMMAND:
                            final Map<String, Object> variables = GenconfUtils.getVariables(generation,
                                    resourceSetForModels);
                            getEvaluation(queryEnvironment, variables, req, resp);
                            break;

                        default:
                            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                            resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
                            resp.getWriter().println("command is invalid");
                            break;
                    }
                } else {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
                    resp.getWriter().println(COMMAND_PARAMETER + " parameter is mandatory");
                }
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
                resp.getWriter().println(genconfURI + " can't be loaded");
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
            resp.getWriter().println(GENCONF_URI_PARAMETER + " parameter is mandatory");
        }
    }

    /**
     * Get an uptodate manifest file.
     * 
     * @param version
     *            the M2Doc version
     * @param serverName
     *            the server name
     * @param serverPort
     *            the server port
     * @param inputStream
     *            the manifest {@link InputStream}
     * @param resp
     *            the {@link Response}
     * @throws IOException
     *             if the manifext file can't be read
     */
    private void getManifest(String version, String serverName, int serverPort, InputStream inputStream,
            HttpServletResponse resp) throws IOException {
        String manifest = new String(IOUtils.toByteArray(inputStream));
        manifest = manifest.replace("VERSION", version);
        manifest = manifest.replace("HOST:PORT", serverName + ":" + serverPort);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType(MimeTypes.Type.TEXT_XML_UTF_8.toString());
        resp.getWriter().print(manifest);
    }

    /**
     * Gets the proposals.
     * 
     * @param queryEnvironment
     *            the {@link Generation}
     * @param variableTypes
     *            the variable types
     * @param req
     *            the {@link Request}
     * @param resp
     *            the {@link Response}
     * @throws IOException
     *             if the response can't be written
     */
    private void getProposals(IReadOnlyQueryEnvironment queryEnvironment, Map<String, Set<IType>> variableTypes,
            HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String expression = req.getParameter(EXPRESSION_PARAMETER);
        if (expression != null) {
            try {
                final Integer offset = Integer.valueOf(req.getParameter(OFFSET_PARAMETER));
                if (offset != null) {
                    final QueryCompletionEngine engine = new QueryCompletionEngine(queryEnvironment);

                    final ICompletionResult completionResult = engine.getCompletion(expression, offset, variableTypes);
                    final List<ICompletionProposal> proposals = completionResult
                            .getProposals(QueryCompletion.createBasicFilter(completionResult));

                    final String[] res = new String[proposals.size()];

                    int index = 0;
                    for (ICompletionProposal proposal : proposals) {
                        res[index++] = proposal.getProposal();
                    }

                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.setContentType(MimeTypes.Type.TEXT_JSON_UTF_8.toString());
                    resp.getWriter().print(JSON.toString(res));
                } else {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
                    resp.getWriter().println(OFFSET_PARAMETER + " parameter is mandatory");
                }
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
                resp.getWriter().println(EXPRESSION_PARAMETER + " must be an integer");
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
            resp.getWriter().println(EXPRESSION_PARAMETER + " parameter is mandatory");
        }
    }

    /**
     * Gets applied proposal.
     * 
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param variableTypes
     *            the variable types
     * @param req
     *            the {@link Request}
     * @param resp
     *            the {@link Response}
     * @throws IOException
     *             if the response can't be written
     */
    private void getAppliedProposal(IReadOnlyQueryEnvironment queryEnvironment, Map<String, Set<IType>> variableTypes,
            HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String expression = req.getParameter(EXPRESSION_PARAMETER);
        if (expression != null) {
            final String completion = req.getParameter(COMPLETION_PARAMETER);
            if (completion != null) {
                try {
                    final Integer offset = Integer.valueOf(req.getParameter(OFFSET_PARAMETER));
                    if (offset != null) {
                        final QueryCompletionEngine engine = new QueryCompletionEngine(queryEnvironment);
                        final ICompletionResult completionResult = engine.getCompletion(expression, offset,
                                variableTypes);
                        final List<ICompletionProposal> proposals = completionResult
                                .getProposals(QueryCompletion.createBasicFilter(completionResult));

                        ICompletionProposal proposal = null;
                        for (ICompletionProposal p : proposals) {
                            if (completion.equals(p.getProposal())) {
                                proposal = p;
                                break;
                            }
                        }
                        final String res;
                        if (proposal != null) {
                            final String prefix = expression.substring(0, completionResult.getReplacementOffset());
                            final String suffix = expression.substring(
                                    completionResult.getReplacementOffset() + completionResult.getReplacementLength());
                            res = prefix + proposal.getProposal() + suffix;
                        } else {
                            res = expression;
                        }

                        resp.setStatus(HttpServletResponse.SC_OK);
                        resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
                        resp.getWriter().print(res);
                    } else {
                        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
                        resp.getWriter().println(OFFSET_PARAMETER + " parameter is mandatory");
                    }
                } catch (NumberFormatException e) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
                    resp.getWriter().println(EXPRESSION_PARAMETER + " must be an integer");
                }
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
                resp.getWriter().println(COMPLETION_PARAMETER + " parameter is mandatory");
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
            resp.getWriter().println(EXPRESSION_PARAMETER + " parameter is mandatory");
        }
    }

    /**
     * @param queryEnvironment
     * @param req
     * @param resp
     * @throws IOException
     *             if the response can't be written
     */
    private void getValidation(IReadOnlyQueryEnvironment queryEnvironment, Map<String, Set<IType>> variableTypes,
            HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String expression = req.getParameter(EXPRESSION_PARAMETER);
        if (expression != null) {
            IQueryValidationEngine engine = new QueryValidationEngine(queryEnvironment);
            final IValidationResult result = engine.validate(expression, variableTypes);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType(MimeTypes.Type.APPLICATION_JSON_UTF_8.toString());
            resp.getWriter().print(JSON.toString(result.getMessages()));
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
            resp.getWriter().println(EXPRESSION_PARAMETER + " parameter is mandatory");
        }
    }

    /**
     * @param queryEnvironment
     * @param variables
     * @param req
     * @param resp
     */
    private void getEvaluation(IReadOnlyQueryEnvironment queryEnvironment, Map<String, Object> variables,
            HttpServletRequest req, HttpServletResponse resp) {
        // TODO Auto-generated method stub

    }

}
