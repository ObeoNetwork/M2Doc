/*******************************************************************************
 *  Copyright (c) 2020 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.word.addin.tests;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.obeonetwork.m2doc.util.M2DocUtils;
import org.obeonetwork.m2doc.word.addin.CompletionServer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Bundle tests.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class BackEndTests {

    /**
     * The {@link CompletionServer}.
     */
    private static final CompletionServer SERVER = new CompletionServer();

    /**
     * The {@link HttpClient}.
     */
    private final HttpClient client = new HttpClient(new SslContextFactory.Client());

    private final String genconfURI = URI.createFileURI(new File("resources/nominal.genconf").getAbsolutePath())
            .toString().replace("/", "%2f").replace(":", "%3a");

    private final String genconfURIUML2 = URI.createFileURI(new File("resources/uml2.genconf").getAbsolutePath())
            .toString().replace("/", "%2f").replace(":", "%3a");

    @BeforeClass
    public static void beforeClass() throws Exception {
        SERVER.start("localhost", 12345);
        UMLPackage.eINSTANCE.getName(); // make sure UML2 is loaded
    }

    @AfterClass
    public static void afterCass() throws Exception {
        SERVER.stop();
    }

    @Before
    public void before() throws Exception {
        client.start();
    }

    @After
    public void after() throws Exception {
        client.stop();
    }

    @Test
    public void resourceExisting() throws InterruptedException, ExecutionException, TimeoutException {
        final ContentResponse response = client.GET("http://localhost:12345/assets/logo_M2Doc.png");

        assertEquals(200, response.getStatus());
        assertEquals("image/png", response.getMediaType());
    }

    @Test
    public void resourceNotExisting() throws InterruptedException, ExecutionException, TimeoutException {
        final ContentResponse response = client.GET("http://localhost:12345/NotExisting.txt");

        assertEquals(404, response.getStatus());
        assertEquals("text/html", response.getMediaType());
        assertEquals("<h1>404<h1>", response.getContentAsString());
    }

    @Test
    public void resourceManifest() throws InterruptedException, ExecutionException, TimeoutException {
        final ContentResponse response = client.GET("http://localhost:12345/assets/manifest.xml");

        assertEquals(200, response.getStatus());
        assertEquals("text/xml", response.getMediaType());
        assertTrue(response.getContentAsString().contains(M2DocUtils.VERSION));
        assertTrue(response.getContentAsString().contains("http://localhost:12345"));
    }

    @Test
    public void restNoGenconfURI() throws InterruptedException, ExecutionException, TimeoutException {
        final ContentResponse response = client.GET("http://localhost:12345/rest/");

        assertEquals(400, response.getStatus());
        assertEquals("text/plain", response.getMediaType());
        assertEquals("genconfURI parameter is mandatory", response.getContentAsString());
    }

    @Test
    public void restNotExistingGenconfURI() throws InterruptedException, ExecutionException, TimeoutException {
        final ContentResponse response = client.GET("http://localhost:12345/rest/?genconfURI=NotExisting.genconf");

        assertEquals(400, response.getStatus());
        assertEquals("text/plain", response.getMediaType());
        assertTrue(response.getContentAsString().startsWith("can't load .genconf file:"));
    }

    @Test
    public void restNoCommand() throws InterruptedException, ExecutionException, TimeoutException {
        final ContentResponse response = client.GET("http://localhost:12345/rest/?genconfURI=" + genconfURI);

        assertEquals(400, response.getStatus());
        assertEquals("text/plain", response.getMediaType());
        assertEquals("command parameter is mandatory", response.getContentAsString());
    }

    @Test
    public void restInvalidCommand() throws InterruptedException, ExecutionException, TimeoutException {
        final ContentResponse response = client
                .GET("http://localhost:12345/rest/?genconfURI=" + genconfURI + "&command=invalidCommand");

        assertEquals(400, response.getStatus());
        assertEquals("text/plain", response.getMediaType());
        assertEquals("command is invalid, valid commands: proposal, apply, validate, evaluate.",
                response.getContentAsString());
    }

    @Test
    public void restProposalNoExpression() throws InterruptedException, ExecutionException, TimeoutException {
        final ContentResponse response = client
                .GET("http://localhost:12345/rest/?genconfURI=" + genconfURI + "&command=proposal");

        assertEquals(400, response.getStatus());
        assertEquals("text/plain", response.getMediaType());
        assertEquals("expression parameter is mandatory", response.getContentAsString());
    }

    @Test
    public void restProposalNoOffset() throws InterruptedException, ExecutionException, TimeoutException {
        final ContentResponse response = client
                .GET("http://localhost:12345/rest/?genconfURI=" + genconfURI + "&command=proposal&expression=self.na");

        assertEquals(400, response.getStatus());
        assertEquals("text/plain", response.getMediaType());
        assertEquals("offset parameter is mandatory", response.getContentAsString());
    }

    @Test
    public void restProposal() throws InterruptedException, ExecutionException, TimeoutException {
        final ContentResponse response = client.GET("http://localhost:12345/rest/?genconfURI=" + genconfURI
            + "&command=proposal&expression=self.na&offset=7");

        assertEquals(200, response.getStatus());
        assertEquals("text/json", response.getMediaType());
        assertEquals(
                "[{\"documentation\":\"EAttribute named name in ENamedElement(http://www.eclipse.org/emf/2002/Ecore)\",\"cursorOffset\":9,\"label\":\"name\",\"type\":\"EAttribute\",\"value\":\"name\"}]",
                response.getContentAsString());
    }

    @Test
    public void restApplyNoExpression() throws InterruptedException, ExecutionException, TimeoutException {
        final ContentResponse response = client
                .GET("http://localhost:12345/rest/?genconfURI=" + genconfURI + "&command=apply");

        assertEquals(400, response.getStatus());
        assertEquals("text/plain", response.getMediaType());
        assertEquals("expression parameter is mandatory", response.getContentAsString());
    }

    @Test
    public void restApplyNoCompletion() throws InterruptedException, ExecutionException, TimeoutException {
        final ContentResponse response = client
                .GET("http://localhost:12345/rest/?genconfURI=" + genconfURI + "&command=apply&expression=self.na");

        assertEquals(400, response.getStatus());
        assertEquals("text/plain", response.getMediaType());
        assertEquals("completion parameter is mandatory", response.getContentAsString());
    }

    @Test
    public void restApplyNoOffset() throws InterruptedException, ExecutionException, TimeoutException {
        final ContentResponse response = client.GET("http://localhost:12345/rest/?genconfURI=" + genconfURI
            + "&command=apply&expression=self.na&completion=name");

        assertEquals(400, response.getStatus());
        assertEquals("text/plain", response.getMediaType());
        assertEquals("offset parameter is mandatory", response.getContentAsString());
    }

    @Test
    public void restApply() throws InterruptedException, ExecutionException, TimeoutException {
        final ContentResponse response = client.GET("http://localhost:12345/rest/?genconfURI=" + genconfURI
            + "&command=apply&expression=self.na&completion=name&offset=7");

        assertEquals(200, response.getStatus());
        assertEquals("text/plain", response.getMediaType());
        assertEquals("self.name", response.getContentAsString());
    }

    @Test
    public void restValidateNoExpression() throws InterruptedException, ExecutionException, TimeoutException {
        final ContentResponse response = client
                .GET("http://localhost:12345/rest/?genconfURI=" + genconfURI + "&command=validate");

        assertEquals(400, response.getStatus());
        assertEquals("text/plain", response.getMediaType());
        assertEquals("expression parameter is mandatory", response.getContentAsString());
    }

    @Test
    public void restValidate() throws InterruptedException, ExecutionException, TimeoutException {
        final ContentResponse response = client
                .GET("http://localhost:12345/rest/?genconfURI=" + genconfURI + "&command=validate&expression=self.na");

        assertEquals(200, response.getStatus());
        assertEquals("application/json", response.getMediaType());
        assertEquals(
                "[{\"level\":\"ERROR\",\"start\":4,\"end\":7,\"message\":\"Feature na not found in EClass EPackage\"}]",
                response.getContentAsString());
    }

    @Test
    public void restValidateOK() throws InterruptedException, ExecutionException, TimeoutException {
        final ContentResponse response = client.GET(
                "http://localhost:12345/rest/?genconfURI=" + genconfURI + "&command=validate&expression=self.name");

        assertEquals(200, response.getStatus());
        assertEquals("application/json", response.getMediaType());
        assertEquals("[]", response.getContentAsString());
    }

    @Test
    public void restValidateUML2() throws InterruptedException, ExecutionException, TimeoutException {
        final ContentResponse response = client.GET(
                "http://localhost:12345/rest/?genconfURI=" + genconfURIUML2 + "&command=validate&expression=self.na");

        assertEquals(200, response.getStatus());
        assertEquals("application/json", response.getMediaType());
        assertEquals(
                "[{\"level\":\"ERROR\",\"start\":4,\"end\":7,\"message\":\"Feature na not found in EClass Model\"}]",
                response.getContentAsString());
    }

    @Test
    public void restValidateUML2OK() throws InterruptedException, ExecutionException, TimeoutException {
        final ContentResponse response = client.GET(
                "http://localhost:12345/rest/?genconfURI=" + genconfURIUML2 + "&command=validate&expression=self.name");

        assertEquals(200, response.getStatus());
        assertEquals("application/json", response.getMediaType());
        assertEquals("[]", response.getContentAsString());
    }

    @Test
    public void restEvaluateNoExpression() throws InterruptedException, ExecutionException, TimeoutException {
        final ContentResponse response = client
                .GET("http://localhost:12345/rest/?genconfURI=" + genconfURI + "&command=evaluate");

        assertEquals(400, response.getStatus());
        assertEquals("text/plain", response.getMediaType());
        assertEquals("expression parameter is mandatory", response.getContentAsString());
    }

    @Test
    public void restEvaluateInvalidExpression() throws InterruptedException, ExecutionException, TimeoutException {
        final ContentResponse response = client
                .GET("http://localhost:12345/rest/?genconfURI=" + genconfURI + "&command=evaluate&expression=self.na");

        assertEquals(200, response.getStatus());
        assertEquals("text/html", response.getMediaType());
        assertEquals("<p>null</p>", response.getContentAsString());
    }

    @Test
    public void restEvaluate() throws InterruptedException, ExecutionException, TimeoutException {
        final ContentResponse response = client.GET(
                "http://localhost:12345/rest/?genconfURI=" + genconfURI + "&command=evaluate&expression=self.name");

        assertEquals(200, response.getStatus());
        assertEquals("text/html", response.getMediaType());
        assertEquals("<p>anydsl</p>", response.getContentAsString());
    }

    @Test
    public void restEvaluateUML2() throws InterruptedException, ExecutionException, TimeoutException {
        final ContentResponse response = client.GET(
                "http://localhost:12345/rest/?genconfURI=" + genconfURIUML2 + "&command=evaluate&expression=self.name");

        assertEquals(200, response.getStatus());
        assertEquals("text/html", response.getMediaType());
        assertEquals("<p>Some UML model</p>", response.getContentAsString());
    }

}
