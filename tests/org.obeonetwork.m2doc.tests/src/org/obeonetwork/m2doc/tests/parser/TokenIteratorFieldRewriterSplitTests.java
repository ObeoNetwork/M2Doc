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
package org.obeonetwork.m2doc.tests.parser;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.obeonetwork.m2doc.parser.TokenIterator;
import org.obeonetwork.m2doc.parser.TokenIteratorFieldRewriter;

import static org.junit.Assert.assertArrayEquals;

/**
 * Tests {@link TokenIteratorFieldRewriter} when splits are needed.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
@RunWith(Parameterized.class)
public class TokenIteratorFieldRewriterSplitTests {

    /**
     * Expected results {@link Map}.
     */
    private static final Map<String, List<String>> EXPECTEDS = initializeExpecteds();

    /**
     * The test document path.
     */
    private final String documentPath;

    /**
     * Constructor.
     * 
     * @param documentPath
     *            the document path
     */
    public TokenIteratorFieldRewriterSplitTests(String documentPath) {
        this.documentPath = documentPath;
    }

    /**
     * @return
     */
    private static Map<String, List<String>> initializeExpecteds() {
        final Map<String, List<String>> result = new HashMap<>();

        result.put("oneRun.docx", Arrays.asList("FIELD_START", "RUN: [m:self]", "FIELD_END"));
        result.put("oneRunWithPrefix.docx",
                Arrays.asList("RUN: [prefix]", "FIELD_START", "RUN: [m:self]", "FIELD_END"));
        result.put("oneRunWithPrefixAndSuffix.docx",
                Arrays.asList("RUN: [prefix]", "FIELD_START", "RUN: [m:self]", "FIELD_END", "RUN: [suffix]"));
        result.put("oneRunWithRunPrefix.docx",
                Arrays.asList("RUN: [prefix]", "FIELD_START", "RUN: [m:self]", "FIELD_END"));
        result.put("oneRunWithRunPrefixAndRunSuffix.docx",
                Arrays.asList("RUN: [prefix]", "FIELD_START", "RUN: [m:self]", "FIELD_END", "RUN: [suffix]"));
        result.put("oneRunWithRunSuffix.docx",
                Arrays.asList("FIELD_START", "RUN: [m:self]", "FIELD_END", "RUN: [suffix]"));
        result.put("oneRunWithSuffix.docx",
                Arrays.asList("FIELD_START", "RUN: [m:self]", "FIELD_END", "RUN: [suffix]"));
        result.put("oneRunWithTPrefix.docx",
                Arrays.asList("RUN: [prefix]", "FIELD_START", "RUN: [m:self]", "FIELD_END"));
        result.put("oneRunWithTPrefixAndTSuffix.docx",
                Arrays.asList("RUN: [prefix]", "FIELD_START", "RUN: [m:self]", "FIELD_END", "RUN: [suffix]"));
        result.put("oneRunWithTSuffix.docx",
                Arrays.asList("FIELD_START", "RUN: [m:self]", "FIELD_END", "RUN: [suffix]"));
        result.put("runSplit.docx", Arrays.asList("FIELD_START", "RUN: [m:se]", "RUN: [lf]", "FIELD_END"));
        result.put("runSplitEndFieldAlone.docx", Arrays.asList("FIELD_START", "RUN: [m:se]", "RUN: [lf]", "FIELD_END"));
        result.put("runSplitStartField.docx",
                Arrays.asList("FIELD_START", "RUN: [m]", "RUN: [:se]", "RUN: [lf]", "FIELD_END"));
        result.put("runSplitStartFieldAlone.docx",
                Arrays.asList("FIELD_START", "RUN: [m:se]", "RUN: [lf]", "FIELD_END"));
        result.put("runSplitWithPrefix.docx",
                Arrays.asList("RUN: [prefix]", "FIELD_START", "RUN: [m:se]", "RUN: [lf]", "FIELD_END"));
        result.put("runSplitWithRunPrefix.docx",
                Arrays.asList("RUN: [prefix]", "FIELD_START", "RUN: [m:se]", "RUN: [lf]", "FIELD_END"));
        result.put("runSplitWithRunPrefixAndRunSuffix.docx", Arrays.asList("RUN: [prefix]", "FIELD_START",
                "RUN: [m:se]", "RUN: [lf]", "FIELD_END", "RUN: [suffix]"));
        result.put("runSplitWithRunSuffix.docx",
                Arrays.asList("FIELD_START", "RUN: [m:se]", "RUN: [lf]", "FIELD_END", "RUN: [suffix]"));
        result.put("runSplitWithSuffix.docx",
                Arrays.asList("FIELD_START", "RUN: [m:se]", "RUN: [lf]", "FIELD_END", "RUN: [suffix]"));
        result.put("runSplitWithTPrefix.docx",
                Arrays.asList("RUN: [prefix]", "FIELD_START", "RUN: [m:se]", "RUN: [lf]", "FIELD_END"));
        result.put("runSplitWithTPrefixAndTSuffix.docx", Arrays.asList("RUN: [prefix]", "FIELD_START", "RUN: [m:se]",
                "RUN: [lf]", "FIELD_END", "RUN: [suffix]"));
        result.put("runSplitWithTSuffix.docx",
                Arrays.asList("FIELD_START", "RUN: [m:se]", "RUN: [lf]", "FIELD_END", "RUN: [suffix]"));
        result.put("tSplit.docx", Arrays.asList("FIELD_START", "RUN: [m:se, lf]", "FIELD_END"));
        result.put("tSplitEndFieldAlone.docx", Arrays.asList("FIELD_START", "RUN: [m:se, lf]", "FIELD_END"));
        result.put("tSplitStartField.docx", Arrays.asList("FIELD_START", "RUN: [m, :se, lf]", "FIELD_END"));
        result.put("tSplitStartFieldAlone.docx", Arrays.asList("FIELD_START", "RUN: [m:se, lf]", "FIELD_END"));
        result.put("tSplitWithPrefix.docx",
                Arrays.asList("RUN: [prefix]", "FIELD_START", "RUN: [m:se, lf]", "FIELD_END"));
        result.put("tSplitWithPrefixAndSuffix.docx",
                Arrays.asList("RUN: [prefix]", "FIELD_START", "RUN: [m:se, lf]", "FIELD_END", "RUN: [suffix]"));
        result.put("tSplitWithRunPrefix.docx",
                Arrays.asList("RUN: [prefix]", "FIELD_START", "RUN: [m:se, lf]", "FIELD_END"));
        result.put("tSplitWithRunPrefixAndRunSuffix.docx",
                Arrays.asList("RUN: [prefix]", "FIELD_START", "RUN: [m:se, lf]", "FIELD_END", "RUN: [suffix]"));
        result.put("tSplitWithRunSuffix.docx",
                Arrays.asList("FIELD_START", "RUN: [m:se, lf]", "FIELD_END", "RUN: [suffix]"));
        result.put("tSplitWithSuffix.docx",
                Arrays.asList("FIELD_START", "RUN: [m:se, lf]", "FIELD_END", "RUN: [suffix]"));
        result.put("tSplitWithTPrefix.docx",
                Arrays.asList("RUN: [prefix]", "FIELD_START", "RUN: [m:se, lf]", "FIELD_END"));
        result.put("tSplitWithTPrefixAndTSuffix.docx",
                Arrays.asList("RUN: [prefix]", "FIELD_START", "RUN: [m:se, lf]", "FIELD_END", "RUN: [suffix]"));
        result.put("tSplitWithTSuffix.docx",
                Arrays.asList("FIELD_START", "RUN: [m:se, lf]", "FIELD_END", "RUN: [suffix]"));

        return result;
    }

    /**
     * Gets test folders from resources/feature.
     * 
     * @return test folders from resources/feature
     */
    @Parameters(name = "{0}")
    public static Collection<Object[]> retrieveTestFolders() {
        final List<Object[]> result = new ArrayList<>();

        File folder = new File("resources/document/toSplit");
        final File[] children = folder.listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return pathname.isFile() && pathname.canRead() && pathname.getName().endsWith(".docx");
            }

        });
        Arrays.sort(children);
        for (File child : children) {
            result.add(new Object[] {child.getPath() });
        }

        return result;
    }

    @Test
    public void tokenize() throws IOException, InvalidFormatException {
        final File documentFile = new File(documentPath);
        try (FileInputStream is = new FileInputStream(documentFile);
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);) {
            final TokenIterator iterator = new TokenIteratorFieldRewriter(document);
            final List<String> actual = new ArrayList<>();
            while (iterator.hasNext()) {
                actual.add(iterator.next().toString());
            }

            final List<String> expected = EXPECTEDS.getOrDefault(documentFile.getName(), new ArrayList<>());
            assertArrayEquals(expected.toArray(new String[expected.size()]),
                    actual.toArray(new String[expected.size()]));
        }

    }

}
