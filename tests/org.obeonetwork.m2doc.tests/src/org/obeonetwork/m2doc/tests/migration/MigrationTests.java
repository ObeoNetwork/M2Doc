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
package org.obeonetwork.m2doc.tests.migration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.obeonetwork.m2doc.generator.GenerationResult;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.tests.AbstractTemplatesTestSuite;
import org.obeonetwork.m2doc.tests.M2DocTestUtils;
import org.obeonetwork.m2doc.tests.TestMemoryURIHandler;
import org.obeonetwork.m2doc.util.M2DocUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
@RunWith(Parameterized.class)
public class MigrationTests {

    /**
     * Doesn't exist message.
     */
    private static final String DOESN_T_EXIST = " doesn't exist.";

    /**
     * The root folder.
     */
    private static final String ROOT = "resources-migration";

    /**
     * The {@link URIHandler} that check we don't have adherence to {@link File}.
     */
    private final TestMemoryURIHandler uriHandler = new TestMemoryURIHandler();

    /**
     * The {@link URIConverter}.
     */
    private final URIConverter uriConverter;

    /**
     * The test template {@link URI}.
     */
    private File testTemplateFile;

    /**
     * Constructor.
     * 
     * @param testTemplatePath
     *            the path to the test template.
     */
    public MigrationTests(String testTemplatePath) {
        this.testTemplateFile = new File(testTemplatePath);
        uriConverter = new ResourceSetImpl().getURIConverter();
        uriConverter.getURIHandlers().add(0, uriHandler);
    }

    @After
    public void after() {
        uriHandler.clear();
    }

    @Test
    public void migrate() throws IOException {
        final URI templateURI = getTemplateURI(testTemplateFile);
        final URI expectedMigratedURI = getExpectedMigratedURI(testTemplateFile);

        final List<TemplateValidationMessage> messages = new ArrayList<>();
        try {
            if (!uriConverter.exists(expectedMigratedURI, Collections.EMPTY_MAP)) {
                final URI actualMigratedURI = getActualMigratedURI(testTemplateFile);

                messages.addAll(M2DocUtils.migrate(uriConverter, templateURI, actualMigratedURI, new BasicMonitor()));

                fail(expectedMigratedURI + DOESN_T_EXIST);
            } else {
                final URI memoryMigratedURI = getMemoryMigratedURI(testTemplateFile);
                messages.addAll(M2DocUtils.migrate(uriConverter, templateURI, memoryMigratedURI, new BasicMonitor()));
                M2DocTestUtils.assertDocx(uriConverter, expectedMigratedURI, memoryMigratedURI);
            }
        } catch (Exception e) {
            messages.add(new TemplateValidationMessage(ValidationMessageLevel.ERROR, e.getMessage(), null));
        }

        // migration messages
        final URI expectedMigratedMessagesURI = getExpectedMigratedMessagesURI(testTemplateFile);
        final String actualMigrationMessages = getMigrationMessagesText(messages);
        if (!uriConverter.exists(expectedMigratedMessagesURI, Collections.EMPTY_MAP)) {
            final URI actualMigratedMessagesURI = getActualMigratedMessagesURI(testTemplateFile);

            try (OutputStream stream = uriConverter.createOutputStream(actualMigratedMessagesURI);) {
                AbstractTemplatesTestSuite.setContent(stream, "UTF-8", actualMigrationMessages);
            }

            fail(expectedMigratedMessagesURI + DOESN_T_EXIST);
        } else {
            try (InputStream stream = uriConverter.createInputStream(expectedMigratedMessagesURI)) {
                final String expectedMigrationMessages = AbstractTemplatesTestSuite.getContent(stream, "UTF-8");
                assertEquals(expectedMigrationMessages, actualMigrationMessages);
            }
        }
    }

    /**
     * Gets the migration messages text.
     * 
     * @param messages
     *            the {@link List} of {@link TemplateValidationMessage}
     *            the {@link GenerationResult}
     * @return the migration messages text
     */
    private String getMigrationMessagesText(List<TemplateValidationMessage> messages) {
        final StringBuilder builder = new StringBuilder();

        for (TemplateValidationMessage message : messages) {
            builder.append(message.getLevel());
            builder.append(" - ");
            builder.append(message.getMessage());
            builder.append("\n");
        }

        return M2DocTestUtils.getPortableString(builder.toString());
    }

    /**
     * Gets the expected migrated {@link URI} for the given template {@link File}.
     * 
     * @param file
     *            the template {@link File}
     * @return the expected migrated {@link URI} for the given template {@link File}
     */
    private URI getExpectedMigratedURI(File file) {
        final File parentFile = file.getParentFile();
        return URI.createFileURI(
                parentFile.getAbsolutePath() + File.separator + parentFile.getName() + "-migrated-expected.docx");
    }

    /**
     * Gets the actual migrated {@link URI} for the given template {@link File}.
     * 
     * @param file
     *            the template {@link File}
     * @return the actual migrated {@link URI} for the given template {@link File}
     */
    private URI getActualMigratedURI(File file) {
        final File parentFile = file.getParentFile();
        return URI.createFileURI(
                parentFile.getAbsolutePath() + File.separator + parentFile.getName() + "-migrated-actual.docx");
    }

    /**
     * Gets the in memory migrated {@link URI} for the given template {@link File}.
     * 
     * @param file
     *            the template {@link File}
     * @return the in memory migrated {@link URI} for the given template {@link File}
     */
    private URI getMemoryMigratedURI(File file) {
        return URI.createURI(TestMemoryURIHandler.PROTOCOL + "://" + file + "-migration-test.docx", false);
    }

    /**
     * Gets the expected migrated messages {@link URI} for the given template {@link File}.
     * 
     * @param file
     *            the template {@link File}
     * @return the expected migrated messages {@link URI} for the given template {@link File}
     */
    private URI getExpectedMigratedMessagesURI(File file) {
        final File parentFile = file.getParentFile();
        return URI.createFileURI(
                parentFile.getAbsolutePath() + File.separator + parentFile.getName() + "-messages-expected.txt");
    }

    /**
     * Gets the actual migrated messages {@link URI} for the given template {@link File}.
     * 
     * @param file
     *            the template {@link File}
     * @return the actual migrated messages {@link URI} for the given template {@link File}
     */
    private URI getActualMigratedMessagesURI(File file) {
        final File parentFile = file.getParentFile();
        return URI.createFileURI(
                parentFile.getAbsolutePath() + File.separator + parentFile.getName() + "-messages-actual.txt");
    }

    /**
     * Gets the template {@link URI} form the given template {@link File}.
     * 
     * @param file
     *            the template {@link File}
     * @return the template {@link URI} form the given template {@link File}
     */
    private URI getTemplateURI(File file) {
        return URI.createFileURI(file.getAbsolutePath());
    }

    /**
     * Gets the {@link List} of template path to migrate.
     * 
     * @return the {@link List} of module path to migrate
     * @throws IOException
     *             if the module can't be read
     * @throws FileNotFoundException
     *             if the module can't be found
     */
    @Parameters(name = "{0}")
    public static Collection<Object[]> retrieveTests() {
        final List<Object[]> res = new ArrayList<>();

        final List<String> modules = new ArrayList<>();
        final File root = new File(ROOT);
        modules.addAll(findTemplateFiles(root));

        final Path currentPath = new File(".").toPath().toAbsolutePath();
        for (String module : modules) {
            final Path relativePath = currentPath.relativize(new File(module).toPath().toAbsolutePath());
            res.add(new Object[] {relativePath.toString() });
        }

        return res;
    }

    private static List<String> findTemplateFiles(File root) {
        final List<String> res = new ArrayList<>();

        for (File child : root.listFiles()) {
            if (child.isFile()) {
                if (child.getName().endsWith("-template." + M2DocUtils.DOCX_EXTENSION_FILE)) {
                    res.add(child.getAbsolutePath());
                }
            } else {
                res.addAll(findTemplateFiles(child));
            }
        }

        return res;
    }

}
