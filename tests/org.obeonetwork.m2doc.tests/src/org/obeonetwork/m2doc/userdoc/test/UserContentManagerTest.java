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

import org.eclipse.emf.common.util.URI;
import org.junit.Test;
import org.obeonetwork.m2doc.generator.UserContentManager;
import org.obeonetwork.m2doc.parser.DocumentParserException;

import static org.junit.Assert.assertNull;
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
        UserContentManager userContentManager = new UserContentManager(URI.createFileURI("no_exist_file_path"));

        assertNull(userContentManager.getUserContent("noExistid1"));

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
        UserContentManager userContentManager = new UserContentManager(
                URI.createFileURI("userContent/testUserContent2.docx"));
        // CHECKSTYLE:OFF
        assertNull(userContentManager.getUserContent("noExistid2"));
        // CHECKSTYLE:ON
        userContentManager.dispose();
    }

}
