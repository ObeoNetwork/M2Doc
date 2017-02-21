package org.obeonetwork.m2doc.properties.test;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import org.obeonetwork.m2doc.genconf.Definition;
import org.obeonetwork.m2doc.genconf.GenconfFactory;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.ModelDefinition;
import org.obeonetwork.m2doc.genconf.TemplateConfigurationServices;
import org.obeonetwork.m2doc.properties.TemplateInfo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.obeonetwork.m2doc.properties.test.TemplateInfoTest.loadTemplateInfo;

public class TemplateConfigurationServicesTest {

    @Test
    public void test() throws InvalidFormatException, IOException {
        // Given
        TemplateConfigurationServices tcs = TemplateConfigurationServices.getInstance();
        Generation gen = GenconfFactory.eINSTANCE.createGeneration();
        TemplateInfo info = loadTemplateInfo("resources/document/properties/properties-template.docx");

        // When
        tcs.addProperties(gen, info);

        // Then
        assertTrue(gen.getPackagesNSURI().isEmpty());

        assertEquals(2, gen.getDefinitions().size());
        Definition def0 = gen.getDefinitions().get(0);
        Definition def1 = gen.getDefinitions().get(1);
        assertEquals("variable1", def0.getKey());
        assertTrue(def0 instanceof ModelDefinition);
        assertNull(((ModelDefinition) def0).getType());
        assertNull(((ModelDefinition) def0).getValue());
        assertEquals("variable2", def1.getKey());
        assertTrue(def1 instanceof ModelDefinition);
        assertNull(((ModelDefinition) def1).getType());
        assertNull(((ModelDefinition) def1).getValue());
    }
}
