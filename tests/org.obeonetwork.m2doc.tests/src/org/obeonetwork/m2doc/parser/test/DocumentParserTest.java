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
package org.obeonetwork.m2doc.parser.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.ast.StringLiteral;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.obeonetwork.m2doc.parser.BodyTemplateParser;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.provider.IProvider;
import org.obeonetwork.m2doc.provider.OptionType;
import org.obeonetwork.m2doc.provider.ProviderRegistry;
import org.obeonetwork.m2doc.provider.ProviderValidationMessage;
import org.obeonetwork.m2doc.provider.test.TestDiagramProvider;
import org.obeonetwork.m2doc.template.Conditional;
import org.obeonetwork.m2doc.template.StaticFragment;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.UserDoc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests the {@link BodyParser} class.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public class DocumentParserTest {

    /**
     * AQL query environment.
     */
    private IQueryEnvironment env = org.eclipse.acceleo.query.runtime.Query.newEnvironmentWithDefaultServices(null);
    /**
     * {@link ProviderRegistry} instance used during testing.
     */
    private ProviderRegistry registry = ProviderRegistry.INSTANCE;

    /**
     * Initialize provider registry.
     */
    @Before
    public void setUp() {
        registry.clear();
        registry.registerProvider(new TestDiagramProvider());
        registry.registerProvider(new NoDiagramProvider());
    }

    /**
     * Cleaning.
     */
    @After
    public void after() {
        registry.clear();
    }

    /**
     * General provider for testing.
     * 
     * @author pguilet<pierre.guilet@obeo.fr>
     */
    private class NoDiagramProvider implements IProvider {

        /*
         * (non-Javadoc)
         * 
         * @see org.obeonetwork.m2doc.provider.IProvider#getOptionTypes()
         */
        @Override
        public Map<String, OptionType> getOptionTypes() {
            return null;
        }

        @Override
        public List<ProviderValidationMessage> validate(Map<String, Object> options) {
            return Collections.emptyList();
        }

    }

    /**
     * Test parsing userDoc tag with default simple string.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testUserDocSimpleText() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testUserDoc1.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getXWPFBody());
        assertEquals(3, template.getBody().getStatements().size());
        assertTrue(template.getBody().getStatements().get(0) instanceof StaticFragment);
        assertTrue(template.getBody().getStatements().get(1) instanceof UserDoc);
        assertTrue(template.getBody().getStatements().get(2) instanceof StaticFragment);
        UserDoc userDoc = (UserDoc) template.getBody().getStatements().get(1);
        assertNotNull(userDoc.getId());
        assertTrue(userDoc.getId() instanceof AstResult);
        assertEquals(1, userDoc.getBody().getStatements().size());
        assertTrue(userDoc.getBody().getStatements().get(0) instanceof StaticFragment);
        // AQL parsing test
        assertTrue(userDoc.getId().getAst() instanceof StringLiteral);
        assertEquals("value1", ((StringLiteral) (userDoc.getId().getAst())).getValue());

    }

    /**
     * Test parsing userDoc tag with default one line string.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testUserDocOneLine() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testUserDoc2.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getXWPFBody());
        assertEquals(3, template.getBody().getStatements().size());
        assertTrue(template.getBody().getStatements().get(0) instanceof StaticFragment);
        assertTrue(template.getBody().getStatements().get(1) instanceof UserDoc);
        assertTrue(template.getBody().getStatements().get(2) instanceof StaticFragment);
        UserDoc userDoc = (UserDoc) template.getBody().getStatements().get(1);
        assertNotNull(userDoc.getId());
        assertTrue(userDoc.getId() instanceof AstResult);
        assertEquals(1, userDoc.getBody().getStatements().size());
        assertTrue(userDoc.getBody().getStatements().get(0) instanceof StaticFragment);
        // AQL parsing test
        assertTrue(userDoc.getId().getAst() instanceof StringLiteral);
        assertEquals("value1", ((StringLiteral) (userDoc.getId().getAst())).getValue());
    }

    /**
     * Test parsing userDoc tag with condition tag in userDoc content.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testUserDocWithConditionInContent()
            throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testUserDoc3.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getXWPFBody());
        assertEquals(3, template.getBody().getStatements().size());
        assertTrue(template.getBody().getStatements().get(0) instanceof StaticFragment);
        assertTrue(template.getBody().getStatements().get(1) instanceof UserDoc);
        assertTrue(template.getBody().getStatements().get(2) instanceof StaticFragment);
        UserDoc userDoc = (UserDoc) template.getBody().getStatements().get(1);
        assertNotNull(userDoc.getId());
        assertTrue(userDoc.getId() instanceof AstResult);
        assertEquals(3, userDoc.getBody().getStatements().size());
        assertTrue(userDoc.getBody().getStatements().get(0) instanceof StaticFragment);
        assertTrue(userDoc.getBody().getStatements().get(1) instanceof Conditional);
        assertTrue(userDoc.getBody().getStatements().get(2) instanceof StaticFragment);
        // Check ValidationMessage
        assertEquals(0, userDoc.getValidationMessages().size());
    }
}
