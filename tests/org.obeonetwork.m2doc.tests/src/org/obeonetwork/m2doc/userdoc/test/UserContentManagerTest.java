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
package org.obeonetwork.m2doc.userdoc.test;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.obeonetwork.m2doc.generator.UserContentManager;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.template.UserContent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.obeonetwork.m2doc.generator.UserContentManager.TEMP_DEST_SUFFIX;

/**
 * Tests the {@link UserContentManager} class.
 * 
 * @author ohaegi
 */
public class UserContentManagerTest {

    /**
     * Test With No Exist Last Destination File.
     * 
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testWithNoExistLastDestinationFile() throws IOException, DocumentParserException {
        UserContentManager userContentManager = new UserContentManager("no_exist_file_path");

        assertNull(userContentManager.getUserContent("noExistid1"));

    }

    /**
     * Test With Last Destination File Contain No UserContent.
     * 
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testDeleteTempDestinationFile() throws IOException, DocumentParserException {
        String fileName = "testUserContent2.docx";
        String filePath = "userContent/" + fileName;
        File destFile = new File(filePath);

        // Before userContentManager creation temp file not exist
        File tempFile = findFirstTempFile(fileName, destFile);
        assertNull(tempFile);

        UserContentManager userContentManager = new UserContentManager(filePath);

        // After userContentManager creation temp file exist
        tempFile = findFirstTempFile(fileName, destFile);
        assertNotNull(tempFile);
        assertTrue(tempFile.exists());

        userContentManager.dispose();
        // After launch deleteTempGeneratedFile method temp file no exist
        assertFalse(tempFile.exists());

    }

    /**
     * Find First Temp File.
     * 
     * @param fileName
     *            fileName
     * @param destFile
     *            destFile
     * @return file with temp pattern in destFile folder
     */
    private File findFirstTempFile(String fileName, File destFile) {
        File tempFile = null;
        // Find first temp file
        for (File file : destFile.getParentFile().listFiles()) {
            if (file.getName().startsWith(fileName) && file.getName().endsWith(TEMP_DEST_SUFFIX)) {
                tempFile = file;
            }
        }
        return tempFile;
    }

    /**
     * Test With Last Destination File Contain No UserContent.
     * 
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testLastDestinationFileContainNoUserContent() throws IOException, DocumentParserException {
        UserContentManager userContentManager = new UserContentManager("userContent/testUserContent2.docx");
        // CHECKSTYLE:OFF
        assertNull(userContentManager.getUserContent("noExistid2"));
        // CHECKSTYLE:ON
        userContentManager.dispose();
    }

    /**
     * Test With Last Destination File Contain 1 UserContent.
     * 
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testLastDestinationFileContainOneUserContent() throws IOException, DocumentParserException {
        UserContentManager userContentManager = new UserContentManager("userContent/testUserContent1.docx");

        assertNull(userContentManager.getUserContent("noExistid2"));
        UserContent userContent = userContentManager.getUserContent("value1");
        assertNotNull(userContent);
        assertEquals(1, userContent.getSubConstructs().size());
        assertEquals("User document part Texte 1", userContent.getSubConstructs().get(0).getRuns().get(0).getText(0));
        userContentManager.dispose();
    }

    /**
     * Test With Last Destination File Contain 3 UserContent.
     * 
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testLastDestinationFileContain3UserContent() throws IOException, DocumentParserException {
        UserContentManager userContentManager = new UserContentManager("userContent/testUserContent4.docx");

        assertNull(userContentManager.getUserContent("noExistid2"));

        UserContent userContent = userContentManager.getUserContent("value1");
        assertNotNull(userContent);
        assertEquals(1, userContent.getSubConstructs().size());
        assertEquals("User document part Texte 1", userContent.getSubConstructs().get(0).getRuns().get(0).getText(0));

        userContent = userContentManager.getUserContent("value2");
        assertNotNull(userContent);
        assertEquals(1, userContent.getSubConstructs().size());
        assertEquals("User document part Texte 2", userContent.getSubConstructs().get(0).getRuns().get(0).getText(0));

        userContent = userContentManager.getUserContent("value3");
        assertNotNull(userContent);
        assertEquals(1, userContent.getSubConstructs().size());
        assertEquals("User document part Texte 3", userContent.getSubConstructs().get(0).getRuns().get(0).getText(0));

        userContentManager.dispose();
    }

}
