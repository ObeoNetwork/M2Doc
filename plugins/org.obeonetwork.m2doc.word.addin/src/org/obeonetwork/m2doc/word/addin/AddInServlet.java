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
import java.util.HashMap;
import java.util.Iterator;
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
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IQueryValidationEngine;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IValidationMessage;
import org.eclipse.acceleo.query.runtime.IValidationResult;
import org.eclipse.acceleo.query.runtime.QueryCompletion;
import org.eclipse.acceleo.query.runtime.impl.QueryCompletionEngine;
import org.eclipse.acceleo.query.runtime.impl.QueryValidationEngine;
import org.eclipse.acceleo.query.runtime.impl.ValidationServices;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jetty.http.MimeTypes;
import org.eclipse.jetty.util.ajax.JSON;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.ide.M2DocPlugin;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.Let;
import org.obeonetwork.m2doc.template.Parameter;
import org.obeonetwork.m2doc.template.Repetition;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * The Add-in servlet.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
@SuppressWarnings("restriction")
public class AddInServlet extends HttpServlet {

    /**
     * The resources path.
     */
    private static final String RESOURCE_PATH = "/dist";

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

    /**
     * The parameter is mandatory message.
     */
    private static final String PARAMETER_IS_MANDATORY = "%s parameter is mandatory";

    /**
     * The can't load template message.
     */
    private static final String CAN_T_LOAD_TEMPLATE = "can't load template: %s";

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
     * GET for the rest API.
     * 
     * @param req
     *            the {@link HttpServletRequest}
     * @param resp
     *            the {@link HttpServletResponse}
     * @throws IOException
     *             if the responce can't e written
     */
    private void doRestAPI(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String genconfURI = req.getParameter(GENCONF_URI_PARAMETER);
        if (genconfURI != null && !genconfURI.isEmpty()) {
            Generation generation = null;
            try {
                generation = GenconfUtils.getGeneration(URI.createURI(genconfURI));
                // CHECKSTYLE:OFF
            } catch (Exception e) {
                // CHECKSTYLE:ON
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
                            getProposals(queryEnvironment, resourceSetForModels.getURIConverter(), templateURI, req,
                                    resp);
                            break;

                        case APPLY_COMMAND:
                            getAppliedProposal(queryEnvironment, resourceSetForModels.getURIConverter(), templateURI,
                                    req, resp);
                            break;

                        case VALIDATE_COMMAND:
                            getValidation(queryEnvironment, resourceSetForModels.getURIConverter(), templateURI, req,
                                    resp);
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
                    resp.getWriter().println(String.format(PARAMETER_IS_MANDATORY, COMMAND_PARAMETER));
                }
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
                resp.getWriter().println(genconfURI + " can't be loaded");
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
            resp.getWriter().println(String.format(PARAMETER_IS_MANDATORY, GENCONF_URI_PARAMETER));
        }
    }

    /**
     * Parses the template to retrieve internal variable (let, for, template, ...) types.
     * 
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param validator
     *            the {@link AstValidator}
     * @param variableTypes
     *            the declared variable types
     * @param uriConverter
     *            the {@link URIConverter}
     * @param templateURI
     *            the template URI
     * @return the mapping of internal variable (let, for, template, ...) types
     */
    private Map<String, Set<IType>> parseVariableTypes(IReadOnlyQueryEnvironment queryEnvironment,
            AstValidator validator, Map<String, Set<IType>> variableTypes, URIConverter uriConverter, URI templateURI) {
        final Map<String, Set<IType>> res = new HashMap<>();

        try (DocumentTemplate template = M2DocUtils.parse(uriConverter, templateURI,
                (IQueryEnvironment) queryEnvironment, M2DocPlugin.getClassProvider(), new BasicMonitor())) {
            final Iterator<EObject> it = template.eAllContents();
            while (it.hasNext()) {
                final EObject current = it.next();
                if (current instanceof Let) {
                    final Let let = (Let) current;
                    final IValidationResult validationResult = validator.validate(variableTypes, let.getValue());
                    final Set<IType> possibleTypes = validationResult.getPossibleTypes(let.getValue().getAst());
                    res.put(let.getName(), possibleTypes);
                } else if (current instanceof Repetition) {
                    final Repetition repetition = (Repetition) current;
                    final IValidationResult validationResult = validator.validate(variableTypes, repetition.getQuery());
                    final Set<IType> possibleTypes = validationResult.getPossibleTypes(repetition.getQuery().getAst());
                    res.put(repetition.getIterationVar(), possibleTypes);
                } else if (current instanceof Parameter) {
                    final Parameter parameter = (Parameter) current;
                    final IValidationResult validationResult = validator.validate(variableTypes, parameter.getType());
                    final Set<IType> possibleTypes = validationResult.getPossibleTypes(parameter.getType().getAst());
                    res.put(parameter.getName(), possibleTypes);
                }
            }

        } catch (IOException e) {
            // nothing to do here: if we can't parse it doesn't matter
        } catch (DocumentParserException e) {
            // nothing to do here: if we can't parse it doesn't matter
        }

        return res;
    }

    /**
     * Get an updated manifest file.
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
     *            the {@link HttpServletResponse}
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
     * @param uriConverter
     *            the {@link URIConverter}
     * @param templateURI
     *            the template {@link URI}
     * @param req
     *            the {@link HttpServletRequest}
     * @param resp
     *            the {@link HttpServletResponse}
     * @throws IOException
     *             if the response can't be written
     */
    private void getProposals(IReadOnlyQueryEnvironment queryEnvironment, URIConverter uriConverter, URI templateURI,
            HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (XWPFDocument document = POIServices.getInstance().getXWPFDocument(uriConverter, templateURI)) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);
            final AstValidator validator = new AstValidator(new ValidationServices(queryEnvironment));
            final Map<String, Set<IType>> variableTypes = properties.getVariableTypes(validator, queryEnvironment);
            variableTypes
                    .putAll(parseVariableTypes(queryEnvironment, validator, variableTypes, uriConverter, templateURI));
            final String expression = req.getParameter(EXPRESSION_PARAMETER);
            if (expression != null) {
                try {
                    final Integer offset = Integer.valueOf(req.getParameter(OFFSET_PARAMETER));
                    if (offset != null) {
                        final QueryCompletionEngine engine = new QueryCompletionEngine(queryEnvironment);

                        final ICompletionResult completionResult = engine.getCompletion(expression, offset,
                                variableTypes);
                        final List<ICompletionProposal> proposals = completionResult
                                .getProposals(QueryCompletion.createBasicFilter(completionResult));

                        @SuppressWarnings("unchecked")
                        final Map<String, Object>[] res = new HashMap[proposals.size()];

                        int index = 0;
                        for (ICompletionProposal proposal : proposals) {
                            final Map<String, Object> map = new HashMap<>();
                            map.put("label", proposal.getProposal());
                            map.put("value", proposal.getProposal());
                            map.put("cursorOffset", proposal.getCursorOffset());
                            map.put("documentation", proposal.getDescription());
                            res[index++] = map;
                        }

                        resp.setStatus(HttpServletResponse.SC_OK);
                        resp.setContentType(MimeTypes.Type.TEXT_JSON_UTF_8.toString());
                        resp.getWriter().print(JSON.toString(res));
                    } else {
                        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
                        resp.getWriter().println(String.format(PARAMETER_IS_MANDATORY, OFFSET_PARAMETER));
                    }
                } catch (NumberFormatException e) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
                    resp.getWriter().println(EXPRESSION_PARAMETER + " must be an integer");
                }
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
                resp.getWriter().println(String.format(PARAMETER_IS_MANDATORY, EXPRESSION_PARAMETER));
            }
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
            resp.getWriter().println(String.format(CAN_T_LOAD_TEMPLATE, e.getMessage()));
        }
    }

    /**
     * Gets applied proposal.
     * 
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param uriConverter
     *            the {@link URIConverter}
     * @param templateURI
     *            the template {@link URI}
     * @param req
     *            the {@link HttpServletRequest}
     * @param resp
     *            the {@link HttpServletResponse}
     * @throws IOException
     *             if the response can't be written
     */
    private void getAppliedProposal(IReadOnlyQueryEnvironment queryEnvironment, URIConverter uriConverter,
            URI templateURI, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (XWPFDocument document = POIServices.getInstance().getXWPFDocument(uriConverter, templateURI)) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);
            final AstValidator validator = new AstValidator(new ValidationServices(queryEnvironment));
            final Map<String, Set<IType>> variableTypes = properties.getVariableTypes(validator, queryEnvironment);
            variableTypes
                    .putAll(parseVariableTypes(queryEnvironment, validator, variableTypes, uriConverter, templateURI));
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

                            final String res = getAppliedCompletion(expression, completion, completionResult);

                            resp.setStatus(HttpServletResponse.SC_OK);
                            resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
                            resp.getWriter().print(res);
                        } else {
                            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                            resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
                            resp.getWriter().println(String.format(PARAMETER_IS_MANDATORY, OFFSET_PARAMETER));
                        }
                    } catch (NumberFormatException e) {
                        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
                        resp.getWriter().println(EXPRESSION_PARAMETER + " must be an integer");
                    }
                } else {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
                    resp.getWriter().println(String.format(PARAMETER_IS_MANDATORY, COMPLETION_PARAMETER));
                }
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
                resp.getWriter().println(String.format(PARAMETER_IS_MANDATORY, EXPRESSION_PARAMETER));
            }
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
            resp.getWriter().println(String.format(CAN_T_LOAD_TEMPLATE, e.getMessage()));
        }
    }

    /**
     * Gets the applied completion.
     * 
     * @param expression
     *            the original expression
     * @param completion
     *            the completion to apply
     * @param completionResult
     *            the {@link ICompletionResult}
     * @return the new expression with the applied proposal
     */
    private String getAppliedCompletion(final String expression, final String completion,
            final ICompletionResult completionResult) {
        ICompletionProposal proposal = null;
        final List<ICompletionProposal> proposals = completionResult
                .getProposals(QueryCompletion.createBasicFilter(completionResult));
        for (ICompletionProposal p : proposals) {
            if (completion.equals(p.getProposal())) {
                proposal = p;
                break;
            }
        }
        final String res;
        if (proposal != null) {
            final String prefix = expression.substring(0, completionResult.getReplacementOffset());
            final String suffix = expression
                    .substring(completionResult.getReplacementOffset() + completionResult.getReplacementLength());
            res = prefix + proposal.getProposal() + suffix;
        } else {
            res = expression;
        }
        return res;
    }

    /**
     * Gets the validation.
     * 
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param uriConverter
     *            the {@link URIConverter}
     * @param templateURI
     *            the template {@link URI}
     * @param req
     *            the {@link HttpServletRequest}
     * @param resp
     *            the {@link HttpServletResponse}
     * @throws IOException
     *             if the response can't be written
     */
    private void getValidation(IReadOnlyQueryEnvironment queryEnvironment, URIConverter uriConverter, URI templateURI,
            HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (XWPFDocument document = POIServices.getInstance().getXWPFDocument(uriConverter, templateURI)) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);
            final AstValidator validator = new AstValidator(new ValidationServices(queryEnvironment));
            final Map<String, Set<IType>> variableTypes = properties.getVariableTypes(validator, queryEnvironment);
            variableTypes
                    .putAll(parseVariableTypes(queryEnvironment, validator, variableTypes, uriConverter, templateURI));
            final String expression = req.getParameter(EXPRESSION_PARAMETER);
            if (expression != null) {
                IQueryValidationEngine engine = new QueryValidationEngine(queryEnvironment);
                final IValidationResult result = engine.validate(expression, variableTypes);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType(MimeTypes.Type.APPLICATION_JSON_UTF_8.toString());
                final List<Map<String, Object>> messages = new ArrayList<>();
                for (IValidationMessage message : result.getMessages()) {
                    final Map<String, Object> map = new HashMap<>();
                    messages.add(map);
                    map.put("level", message.getLevel());
                    map.put("start", message.getStartPosition());
                    map.put("end", message.getEndPosition());
                    map.put("message", message.getMessage());
                }
                resp.getWriter().print(JSON.toString(messages));
                System.out.println(JSON.toString(messages));
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
                resp.getWriter().println(String.format(PARAMETER_IS_MANDATORY, EXPRESSION_PARAMETER));
            }
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType(MimeTypes.Type.TEXT_PLAIN_UTF_8.toString());
            resp.getWriter().println(String.format(CAN_T_LOAD_TEMPLATE, e.getMessage()));
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
