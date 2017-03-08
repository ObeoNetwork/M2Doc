/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.obeonetwork.m2doc.api.QueryServices;
import org.obeonetwork.m2doc.genconf.GenconfFactory;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.util.ConfigurationServices;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.generator.GenerationResult;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.provider.ProviderRegistry;
import org.obeonetwork.m2doc.provider.test.TestDiagramProvider;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.util.M2DocUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Run a folder with templates as a test suite JUnit.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
@RunWith(Parameterized.class)
public abstract class AbstractTemplatesTestSuite {

    /**
     * Copy buffer size.
     */
    private static final int BUFFER_SIZE = 8192;

    /**
     * Lost files suffix.
     */
    private static final String LOST_DOCX = "-lost.docx";

    /**
     * Doesn't exist message.
     */
    private static final String DOESN_T_EXISTS = " doesn't exists.";

    /**
     * User content tag in file name.
     */
    private static final String USER_CONTENT_TAG = "-userContent";

    /**
     * The {@link URIHandler} that check we don't have adherence to {@link File}.
     */
    private static MemoryURIHandler uriHandler = new MemoryURIHandler();

    /**
     * The {@link DocumentTemplate}.
     */
    private static DocumentTemplate documentTemplate;

    /**
     * The {@link TemplateAstSerializer}.
     */
    private final TemplateAstSerializer templateAstSerializer = new TemplateAstSerializer();

    /**
     * The test folder path.
     */
    private final String testFolderPath;

    /**
     * The {@link Generation}.
     */
    private final Generation generation;

    /**
     * The {@link IQueryEnvironment}.
     */
    private final IQueryEnvironment queryEnvironment;

    /**
     * Variables.
     */
    private final Map<String, Object> variables;

    /**
     * Constructor.
     * 
     * @param testFolder
     *            the test folder path
     * @throws IOException
     *             if the tested template can't be read
     * @throws DocumentParserException
     *             if the tested template can't be parsed
     */
    public AbstractTemplatesTestSuite(String testFolder) throws IOException, DocumentParserException {
        this.testFolderPath = testFolder.replaceAll("\\\\", "/");
        final URI genconfURI = getGenconfURI(new File(testFolderPath));
        if (URIConverter.INSTANCE.exists(genconfURI, Collections.EMPTY_MAP)) {
            final ResourceSet rs = getResourceSet();
            generation = getGeneration(genconfURI, rs);
        } else {
            generation = GenconfFactory.eINSTANCE.createGeneration();
        }
        final URI templateURI = getTemplateURI(new File(testFolderPath));
        setTemplateFileName(generation, templateURI.toFileString());
        queryEnvironment = QueryServices.getInstance().getEnvironment(templateURI);
        documentTemplate = M2DocUtils.parse(templateURI, queryEnvironment, this.getClass().getClassLoader());
        ConfigurationServices configurationServices = new ConfigurationServices();
        variables = configurationServices.createDefinitions(generation);
        // add providers variables
        variables.putAll(configurationServices.getProviderVariables(generation));
    }

    /**
     * Sets the {@link Generation#getTemplateFileName() template file name}.
     * 
     * @param gen
     *            the {@link Generation}
     * @param templateFileName
     *            the {@link Generation#getTemplateFileName() template file name}
     */
    protected void setTemplateFileName(Generation gen, final String templateFileName) {
        gen.setTemplateFileName(templateFileName);
    }

    /**
     * Registers the {@link TestDiagramProvider}.
     */
    @BeforeClass
    public static void beforeClass() {
        ProviderRegistry.INSTANCE.clear();
        ProviderRegistry.INSTANCE.registerProvider(new TestDiagramProvider());
        URIConverter.INSTANCE.getURIHandlers().add(0, uriHandler);
        uriHandler.clear();
    }

    /**
     * Closes the {@link DocumentTemplate}.
     * 
     * @throws IOException
     *             if the {@link DocumentTemplate} can't be closed
     */
    @AfterClass
    public static void afterClass() throws IOException {
        documentTemplate.close();
        ProviderRegistry.INSTANCE.clear();
        URIConverter.INSTANCE.getURIHandlers().remove(uriHandler);
    }

    /**
     * Gets the {@link Generation}.
     * 
     * @param genconfURI
     *            the {@link Generation} {@link URI}
     * @param rs
     *            the {@link ResourceSet}
     * @return the {@link Generation}
     */
    protected Generation getGeneration(URI genconfURI, ResourceSet rs) {
        return (Generation) rs.getResource(genconfURI, true).getContents().get(0);
    }

    /**
     * Gets the test folder path.
     * 
     * @return the test folder path
     */
    protected String getTestFolderPath() {
        return testFolderPath;
    }

    /**
     * Gets the {@link ResourceSet}.
     * 
     * @return the {@link ResourceSet}
     */
    protected ResourceSet getResourceSet() {
        ResourceSetImpl res = new ResourceSetImpl();

        res.getPackageRegistry().put(GenconfPackage.eNS_URI, GenconfPackage.eINSTANCE);
        res.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());

        return res;
    }

    /**
     * Tests the parsing by comparing a textual representation of the AST.
     * 
     * @throws IOException
     *             if the expected AST file can't be read
     * @throws FileNotFoundException
     *             if the expected AST file can't be found
     */
    @Test
    public void parsing() throws FileNotFoundException, IOException {
        final URI expectedASTURI = getExpectedASTURI(new File(testFolderPath));
        final String actualAst = templateAstSerializer.serialize(documentTemplate);
        if (!URIConverter.INSTANCE.exists(expectedASTURI, Collections.EMPTY_MAP)) {
            final URI actualASTURI = getActualASTURI(new File(testFolderPath));
            try (final OutputStream stream = URIConverter.INSTANCE.createOutputStream(actualASTURI);) {
                setContent(stream, "UTF-8", actualAst);
            }
            fail(expectedASTURI + DOESN_T_EXISTS);
        }
        try (final InputStream stream = URIConverter.INSTANCE.createInputStream(expectedASTURI)) {
            final String expectedAst = getContent(stream, "UTF-8");
            assertEquals(expectedAst.replaceAll("\r\n", "\n"), actualAst.replaceAll("\r\n", "\n"));
        }
    }

    /**
     * Tests the parsing by comparing the validated template.
     * 
     * @throws IOException
     *             if the validation template can't be generated
     * @throws DocumentGenerationException
     *             if the validation template can't be generated
     */
    @Test
    public void validation() throws IOException, DocumentGenerationException {
        final ValidationMessageLevel validationLevel = M2DocUtils.validate(documentTemplate, queryEnvironment);

        final URI expectedValidationURI = getExpectedValidatedURI(new File(testFolderPath));
        final URI tempURI;
        if (URIConverter.INSTANCE.exists(expectedValidationURI, Collections.EMPTY_MAP)) {
            tempURI = URI.createURI("m2doctests://" + testFolderPath + "-validation-test.docx");
            if (validationLevel != ValidationMessageLevel.OK) {
                M2DocUtils.serializeValidatedDocumentTemplate(documentTemplate, tempURI);
            }
        } else {
            tempURI = getActualValidatedURI(new File(testFolderPath));
            if (validationLevel != ValidationMessageLevel.OK) {
                M2DocUtils.serializeValidatedDocumentTemplate(documentTemplate, tempURI);
            } else {
                touch(tempURI);
            }
            fail(expectedValidationURI + DOESN_T_EXISTS);
        }

        if (!isEmpty(tempURI)) {
            M2DocTestUtils.assertDocx(expectedValidationURI, tempURI);
        } else {
            assertEquals(ValidationMessageLevel.OK, validationLevel);
            assertTrue(URIConverter.INSTANCE.exists(expectedValidationURI, Collections.EMPTY_MAP));
            assertTrue(isEmpty(expectedValidationURI));
        }

        URIConverter.INSTANCE.delete(tempURI, Collections.EMPTY_MAP);
    }

    /**
     * Tells if the given {@link URI} is empty.
     * 
     * @param uri
     *            the {@link URI} to check
     * @return <code>true</code> if the given {@link URI} is empty, <code>false</code> otherwise
     */
    private boolean isEmpty(URI uri) {
        try {
            try (InputStream inputStream = URIConverter.INSTANCE.createInputStream(uri);) {
                return URIConverter.INSTANCE.createInputStream(uri).read() == -1;
            }
        } catch (IOException e) {
            return true;
        }
    }

    /**
     * Tests the parsing by comparing the result of the generation.
     * 
     * @throws Exception
     *             if the generation fails
     */
    @Test
    public void generation() throws Exception {
        final URI expectedGeneratedURI = getExpectedGeneratedURI(new File(testFolderPath));
        final URI userContentURI = getUserContentURI(new File(testFolderPath));

        URI outputURI = null;
        if (URIConverter.INSTANCE.exists(expectedGeneratedURI, Collections.EMPTY_MAP)) {
            outputURI = URI.createURI("m2doctests://" + testFolderPath + "-generation-test.docx");
        } else {
            outputURI = getActualGeneratedURI(new File(testFolderPath));
            prepareoutputAndGenerate(userContentURI, outputURI);
            fail(expectedGeneratedURI + DOESN_T_EXISTS);
        }

        final GenerationResult generationResult = prepareoutputAndGenerate(userContentURI, outputURI);
        M2DocTestUtils.assertDocx(expectedGeneratedURI, outputURI);

        List<URI> expectedLostFiles = getExpectedLostURIs(testFolderPath);
        for (Entry<String, URI> entry : generationResult.getLostUserContents().entrySet()) {
            final URI actualLostURI = entry.getValue();
            final URI expectedLostURI = URI
                    .createURI(expectedGeneratedURI.toString() + "-" + entry.getKey() + LOST_DOCX);
            if (!URIConverter.INSTANCE.exists(expectedLostURI, Collections.emptyMap())) {
                copy(actualLostURI, URI.createURI(expectedLostURI.toString().replace("-expected-", "-actual-")));
                fail(expectedLostURI + DOESN_T_EXISTS);
            }
            M2DocTestUtils.assertDocx(expectedLostURI, actualLostURI);
            expectedLostFiles.remove(expectedLostURI);
        }
        // make sure we didn't miss an expected lost file
        assertTrue(Arrays.deepToString(expectedLostFiles.toArray()), expectedLostFiles.isEmpty());
    }

    /**
     * Prepare the output with the given user content and generate to the given output.
     * 
     * @param userContentURI
     *            existing user content {@link URI}
     * @param outputURI
     *            the output {@link URI}
     * @return the {@link GenerationResult}
     * @throws Exception
     *             if the generation fails
     */
    private GenerationResult prepareoutputAndGenerate(final URI userContentURI, URI outputURI) throws Exception {
        if (URIConverter.INSTANCE.exists(userContentURI, Collections.EMPTY_MAP)) {
            copy(userContentURI, outputURI);
        }
        for (URI userContentLostURI : getUserContentLostURI(new File(testFolderPath))) {
            final URI destURI = URI.createURI(outputURI.toString() + userContentLostURI.lastSegment()
                    .substring(userContentLostURI.lastSegment().indexOf(USER_CONTENT_TAG) + USER_CONTENT_TAG.length()));
            copy(userContentLostURI, destURI);
        }
        final GenerationResult generationResult = M2DocUtils.generate(documentTemplate, queryEnvironment, variables,
                outputURI);
        return generationResult;
    }

    /**
     * Gets all expected lost files from the given folder path.
     * 
     * @param folderPath
     *            the folder path
     * @return the {@link List} of all expected lost files from the given folder path
     */
    private List<URI> getExpectedLostURIs(String folderPath) {
        final List<URI> result = new ArrayList<URI>();

        File folder = new File(folderPath);
        final File[] children = folder.listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return pathname.isFile() && pathname.getName().contains("expected")
                    && pathname.getName().contains(LOST_DOCX);
            }

        });
        Arrays.sort(children);

        for (File file : children) {
            result.add(URI.createURI(file.toURI().toString()));
        }

        return result;
    }

    /**
     * Gets the template file from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the template file from the test folder path
     */
    protected final URI getTemplateURI(File testFolder) {
        return URI.createURI(getTemplateFileInternal(testFolder).toURI().toString());
    }

    /**
     * Gets the expected AST file from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the template file from the test folder path
     */
    protected URI getExpectedASTURI(File testFolder) {
        return URI.createURI(testFolder.toURI().toString() + testFolder.getName() + "-expected-ast.txt");
    }

    /**
     * Gets the actual AST file from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the actual file from the test folder path
     */
    protected URI getActualASTURI(File testFolder) {
        return URI.createURI(testFolder.toURI().toString() + testFolder.getName() + "-actual-ast.txt");
    }

    /**
     * Gets the expected validated template file from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the expected template file from the test folder path
     */
    protected URI getExpectedValidatedURI(File testFolder) {
        return URI.createURI(testFolder.toURI().toString() + testFolder.getName() + "-expected-validation.docx");
    }

    /**
     * Gets the actual validated template file from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the actual template file from the test folder path
     */
    protected URI getActualValidatedURI(File testFolder) {
        return URI.createURI(testFolder.toURI().toString() + testFolder.getName() + "-actual-validation.docx");
    }

    /**
     * Gets the expected generated file from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the expected template file from the test folder path
     */
    protected URI getExpectedGeneratedURI(File testFolder) {
        return URI.createURI(testFolder.toURI().toString() + testFolder.getName() + "-expected-generation.docx");
    }

    /**
     * Gets the actual generated file from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the actual generated file from the test folder path
     */
    protected URI getActualGeneratedURI(File testFolder) {
        return URI.createURI(testFolder.toURI().toString() + testFolder.getName() + "-actual-generation.docx");
    }

    /**
     * Gets the user content file from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the user content file from the test folder path
     */
    protected URI getUserContentURI(File testFolder) {
        return URI.createURI(testFolder.toURI().toString() + testFolder.getName() + "-userContent.docx");
    }

    /**
     * Gets the user content file from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the user content file from the test folder path
     */
    protected List<URI> getUserContentLostURI(File testFolder) {
        final List<URI> result = new ArrayList<URI>();

        final File[] children = testFolder.listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                final String name = pathname.getName();
                return name.contains("-userContent-") && name.contains(LOST_DOCX);
            }

        });
        Arrays.sort(children);
        for (File child : children) {
            result.add(URI.createURI(child.toURI().toString()));
        }

        return result;
    }

    /**
     * Gets the genconf file from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the genconf file from the test folder path
     */
    protected URI getGenconfURI(File testFolder) {
        return URI.createURI(testFolder.toURI().toString() + testFolder.getName() + ".genconf");
    }

    /**
     * Gets the template file from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the template file from the test folder path
     */
    private static File getTemplateFileInternal(File testFolder) {
        return new File(testFolder.getAbsolutePath() + File.separator + testFolder.getName() + "-template.docx");
    }

    /**
     * Gets the {@link Collection} of test folders from the given folder path.
     * 
     * @param folderPath
     *            the folder path
     * @return the {@link Collection} of test folders from the given folder path
     */
    public static Collection<Object[]> retrieveTestFolders(String folderPath) {
        Collection<Object[]> parameters = new ArrayList<Object[]>();

        File folder = new File(folderPath);
        final File[] children = folder.listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                final boolean res;

                if (pathname.isDirectory() && pathname.canRead()) {
                    res = getTemplateFileInternal(pathname).exists();
                } else {
                    res = false;
                }

                return res;
            }

        });
        Arrays.sort(children);
        for (File child : children) {
            parameters.add(new Object[] {child.getPath() });
        }

        return parameters;
    }

    /**
     * Gets the content of the given {@link InputStream}.
     * 
     * @param stream
     *            the {@link InputStream}
     * @param charsetName
     *            The name of a supported {@link java.nio.charset.Charset </code>charset<code>}
     * @return a {@link CharSequence} of the content of the given {@link InputStream}
     * @throws IOException
     *             if the {@link InputStream} can't be read
     */
    public static String getContent(InputStream stream, String charsetName) throws IOException {
        final int len = 8192;
        StringBuilder res = new StringBuilder(len);
        if (len != 0) {
            try (InputStreamReader input = new InputStreamReader(new BufferedInputStream(stream), charsetName)) {
                char[] buffer = new char[len];
                int length = input.read(buffer);
                while (length != -1) {
                    res.append(buffer, 0, length);
                    length = input.read(buffer);
                }
            }
        }
        return res.toString();
    }

    /**
     * Sets the given content to the given {@link OutputStream}.
     * 
     * @param stream
     *            the {@link OutputStream}
     * @param charsetName
     *            the charset name
     * @param content
     *            the content to write
     * @throws UnsupportedEncodingException
     *             if the given charset is not supported
     * @throws IOException
     *             if the given stream can't be written to
     */
    public static void setContent(OutputStream stream, String charsetName, String content)
            throws UnsupportedEncodingException, IOException {
        stream.write(content.getBytes(charsetName));
        stream.flush();
    }

    /**
     * Creates an empty content at the given {@link URI}.
     * 
     * @param uri
     *            the {@link URI}
     * @throws IOException
     *             if the content can't be written
     */
    private void touch(URI uri) throws IOException {
        try (OutputStream dest = URIConverter.INSTANCE.createOutputStream(uri);) {
            dest.write(new byte[] {});
        }
    }

    /**
     * Copies all bytes from a source {@link URI} to a destination {@link URI}.
     * 
     * @param sourceURI
     *            the source {@link URI}
     * @param destURI
     *            the destination {@link URI}
     * @return the number of copied bytes
     * @throws IOException
     *             if the copy can't be done
     */
    private static long copy(URI sourceURI, URI destURI) throws IOException {
        try (InputStream source = URIConverter.INSTANCE.createInputStream(sourceURI);
                OutputStream dest = URIConverter.INSTANCE.createOutputStream(destURI);) {
            long nread = 0L;
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = source.read(buf)) > 0) {
                dest.write(buf, 0, n);
                nread += n;
            }
            return nread;
        }
    }

}
