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

import com.google.common.io.Files;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
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
     * Variable types.
     */
    private final Map<String, Set<IType>> types;

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
        this.testFolderPath = testFolder;
        final File genconfFile = getGenconfFile(new File(testFolderPath));
        if (genconfFile.exists()) {
            final ResourceSet rs = getResourceSet();
            generation = getGeneration(genconfFile, rs);
        } else {
            generation = GenconfFactory.eINSTANCE.createGeneration();
        }
        queryEnvironment = QueryServices.getInstance().initAcceleoEnvironment(generation);
        final URI templateURI = URI.createFileURI(getTemplateFile(new File(testFolderPath)).getAbsolutePath());
        documentTemplate = M2DocUtils.parse(templateURI, queryEnvironment);
        types = QueryServices.getInstance().getTypes(queryEnvironment, generation);
        ConfigurationServices configurationServices = new ConfigurationServices();
        variables = configurationServices.createDefinitions(generation);
    }

    /**
     * Registers the {@link TestDiagramProvider}.
     */
    @BeforeClass
    public static void beforeClass() {
        ProviderRegistry.INSTANCE.clear();
        ProviderRegistry.INSTANCE.registerProvider(new TestDiagramProvider());
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
    }

    /**
     * Gets the {@link Generation}.
     * 
     * @param genconfFile
     *            the {@link Generation} file
     * @param rs
     *            the {@link ResourceSet}
     * @return the {@link Generation}
     */
    protected Generation getGeneration(File genconfFile, ResourceSet rs) {
        return (Generation) rs.getResource(URI.createFileURI(genconfFile.getAbsolutePath()), true).getContents().get(0);
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
        final File expectedASTFile = getExpectedASTFile(new File(testFolderPath));
        final String actualAst = templateAstSerializer.serialize(documentTemplate);
        if (!expectedASTFile.exists()) {
            final File actualASTFile = getActualASTFile(new File(testFolderPath));
            if (!actualASTFile.exists()) {
                actualASTFile.createNewFile();
            }
            try (FileOutputStream stream = new FileOutputStream(actualASTFile);) {
                setContent(stream, "UTF-8", actualAst);
            }
            fail(expectedASTFile.getAbsolutePath() + " doesn't exists.");
        }
        try (FileInputStream stream = new FileInputStream(expectedASTFile)) {
            final String expectedAst = getContent(stream, "UTF-8");
            assertEquals(expectedAst, actualAst);
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
        final ValidationMessageLevel validationLevel = M2DocUtils.validate(documentTemplate, queryEnvironment, types);

        final File expectedValidationFile = getExpectedValidatedFile(new File(testFolderPath));
        final File tempFile;
        if (expectedValidationFile.exists()) {
            tempFile = File.createTempFile(expectedValidationFile.getAbsolutePath(), "validation-test.docx");
            if (validationLevel != ValidationMessageLevel.OK) {
                M2DocUtils.serializeValidatedDocumentTemplate(documentTemplate,
                        URI.createFileURI(tempFile.getAbsolutePath()));
            }
        } else {
            tempFile = getActualValidatedFile(new File(testFolderPath));
            tempFile.createNewFile();
            if (validationLevel != ValidationMessageLevel.OK) {
                M2DocUtils.serializeValidatedDocumentTemplate(documentTemplate,
                        URI.createFileURI(tempFile.getAbsolutePath()));
            }
            fail(expectedValidationFile.getAbsolutePath() + " doesn't exists.");
        }

        if (tempFile.length() != 0) {
            M2DocTestUtils.assertDocx(expectedValidationFile.getAbsolutePath(), tempFile.getAbsolutePath());
        } else {
            assertEquals(ValidationMessageLevel.OK, validationLevel);
            assertTrue(expectedValidationFile.exists());
            assertEquals(0, expectedValidationFile.length());
        }

        tempFile.delete();
    }

    /**
     * Tests the parsing by comparing the result of the generation.
     * 
     * @throws Exception
     *             if the generation fails
     */
    @Test
    public void generation() throws Exception {
        final File expectedGeneratedFile = getExpectedGeneratedFile(new File(testFolderPath));
        final File templateFile = getTemplateFile(new File(testFolderPath));
        final File userContentFile = getUserContentFile(new File(testFolderPath));

        File outputFile = null;
        if (expectedGeneratedFile.exists()) {
            outputFile = File.createTempFile(expectedGeneratedFile.getAbsolutePath(), "generated-test");
        } else {
            outputFile = getActualGeneratedFile(new File(testFolderPath));
            if (userContentFile.exists()) {
                Files.copy(userContentFile, outputFile);
            }
            generateTemplate(templateFile, outputFile);
            fail(expectedGeneratedFile.getAbsoluteFile() + " doesn't exists.");
        }

        try (FileInputStream is = new FileInputStream(templateFile)) {
            try (OPCPackage oPackage = OPCPackage.open(is)) {
                try (XWPFDocument document = new XWPFDocument(oPackage)) {
                    if (userContentFile.exists()) {
                        Files.copy(userContentFile, outputFile);
                    }
                    generateTemplate(templateFile, outputFile);
                    M2DocTestUtils.assertDocx(expectedGeneratedFile.getAbsolutePath(), outputFile.getAbsolutePath());
                }
            }
        } finally {
            if (outputFile != null) {
                outputFile.delete();
            }
        }
    }

    /**
     * Generates the given template to the given output file.
     * 
     * @param templateFile
     *            the template .docx
     * @param outputFile
     *            the output file .docx
     * @throws DocumentGenerationException
     *             if the document can't be generated
     * @throws IOException
     *             if the input ou output files can't be read/write
     * @throws DocumentParserException
     *             if the template can't be parsed
     */
    private void generateTemplate(final File templateFile, File outputFile)
            throws DocumentGenerationException, IOException, DocumentParserException {
        String outputPath = outputFile.getAbsolutePath();
        M2DocUtils.generate(documentTemplate, queryEnvironment, variables, URI.createFileURI(outputPath), generation);
    }

    /**
     * Gets the template file from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the template file from the test folder path
     */
    protected final File getTemplateFile(File testFolder) {
        return getTemplateFileInternal(testFolder);
    }

    /**
     * Gets the expected AST file from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the template file from the test folder path
     */
    protected File getExpectedASTFile(File testFolder) {
        return new File(testFolder + File.separator + testFolder.getName() + "-expected-ast.txt");
    }

    /**
     * Gets the actual AST file from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the actual file from the test folder path
     */
    protected File getActualASTFile(File testFolder) {
        return new File(testFolder + File.separator + testFolder.getName() + "-actual-ast.txt");
    }

    /**
     * Gets the expected validated template file from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the expected template file from the test folder path
     */
    protected File getExpectedValidatedFile(File testFolder) {
        return new File(testFolder + File.separator + testFolder.getName() + "-expected-validation.docx");
    }

    /**
     * Gets the actual validated template file from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the actual template file from the test folder path
     */
    protected File getActualValidatedFile(File testFolder) {
        return new File(testFolder + File.separator + testFolder.getName() + "-actual-validation.docx");
    }

    /**
     * Gets the expected generated file from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the expected template file from the test folder path
     */
    protected File getExpectedGeneratedFile(File testFolder) {
        return new File(testFolder + File.separator + testFolder.getName() + "-expected-generation.docx");
    }

    /**
     * Gets the actual generated file from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the actual generated file from the test folder path
     */
    protected File getActualGeneratedFile(File testFolder) {
        return new File(testFolder + File.separator + testFolder.getName() + "-actual-generation.docx");
    }

    /**
     * Gets the user content file from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the user content file from the test folder path
     */
    protected File getUserContentFile(File testFolder) {
        return new File(testFolder + File.separator + testFolder.getName() + "-userContent.docx");
    }

    /**
     * Gets the genconf file from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the genconf file from the test folder path
     */
    protected File getGenconfFile(File testFolder) {
        return new File(testFolder + File.separator + testFolder.getName() + ".genconf");
    }

    /**
     * Gets the template file from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the template file from the test folder path
     */
    private static File getTemplateFileInternal(File testFolder) {
        return new File(testFolder + File.separator + testFolder.getName() + "-template.docx");
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

}
