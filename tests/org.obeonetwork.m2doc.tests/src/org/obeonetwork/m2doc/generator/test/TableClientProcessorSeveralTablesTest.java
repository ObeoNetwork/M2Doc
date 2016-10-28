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
package org.obeonetwork.m2doc.generator.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.Test;
import org.obeonetwork.m2doc.generator.TableClientProcessor;
import org.obeonetwork.m2doc.provider.AbstractTableProvider.MTable;
import org.obeonetwork.m2doc.provider.ProviderException;

import static org.junit.Assert.assertEquals;

/**
 * Tests of {@link TableClientProcessor} when several tables are provided by sirius.
 * 
 * @author ldelaigue
 */
// CHECKSTYLE:OFF
public class TableClientProcessorSeveralTablesTest extends TableClientProcessorTest {

    @Override
    @Test
    public void testWithoutAnyParameter() throws ProviderException {
        Map<String, Object> arguments = new HashMap<String, Object>();
        processor = new TableClientProcessor(doc, provider, arguments);
        processor.generate(run);

        checkParagraph(paragraph, "Test Table");
        List<XWPFTable> tables = doc.getTables();
        assertEquals(2, tables.size());
        checkTable(tables.get(0));
        checkTable(tables.get(1));
    }

    @Override
    @Test
    public void testWithParamHideTitleFalse() throws ProviderException {
        Map<String, Object> arguments = new HashMap<String, Object>();
        arguments.put("hideTitle", "false");
        processor = new TableClientProcessor(doc, provider, arguments);
        processor.generate(run);

        checkParagraph(paragraph, "Test Table");
        List<XWPFTable> tables = doc.getTables();
        assertEquals(2, tables.size());
        checkTable(tables.get(0));
        checkTable(tables.get(1));
    }

    @Override
    @Test
    public void testWithParamHideTitleTrue() throws ProviderException {
        Map<String, Object> arguments = new HashMap<String, Object>();
        arguments.put("hideTitle", "true");
        processor = new TableClientProcessor(doc, provider, arguments);
        processor.generate(run);

        assertEquals("", paragraph.getText());
        List<XWPFTable> tables = doc.getTables();
        assertEquals(2, tables.size());
        checkTable(tables.get(0));
        checkTable(tables.get(1));
    }

    /**
     * Provide the list of {@link MTable}s to use in this test class.
     * 
     * @return The list of tables to use in this test class. Can be overridden.
     */
    @Override
    protected List<MTable> getTestTables() {
        List<MTable> result = new ArrayList<MTable>();
        result.add(getTestTable());
        result.add(getTestTable());
        return result;
    }
}
// CHECKSTYLE:ON
