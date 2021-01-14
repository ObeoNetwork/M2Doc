/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.tests.userdoc;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.junit.Test;
import org.obeonetwork.m2doc.generator.UserContentManager;
import org.obeonetwork.m2doc.parser.DocumentParserException;

import static org.junit.Assert.assertNull;

/**
 * Tests the {@link UserContentManager} class.
 * 
 * @author ohaegi
 */
public class UserContentManagerTests {

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
        UserContentManager userContentManager = new UserContentManager(URIConverter.INSTANCE, null,
                URI.createFileURI("no_exist_file_path"));

        assertNull(userContentManager.consumeUserContent("noExistid1"));

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
        UserContentManager userContentManager = new UserContentManager(URIConverter.INSTANCE, null,
                URI.createFileURI("userContent/testUserContent2.docx"));
        // CHECKSTYLE:OFF
        assertNull(userContentManager.consumeUserContent("noExistid2"));
        // CHECKSTYLE:ON
        userContentManager.dispose();
    }

}
