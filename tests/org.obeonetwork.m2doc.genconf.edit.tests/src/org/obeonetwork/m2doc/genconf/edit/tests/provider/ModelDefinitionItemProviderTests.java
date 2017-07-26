/*******************************************************************************
 *  Copyright (c) 2017 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.genconf.edit.tests.provider;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.ModelDefinition;
import org.obeonetwork.m2doc.genconf.provider.GenconfItemProviderAdapterFactory;
import org.obeonetwork.m2doc.genconf.provider.ModelDefinitionItemProvider;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.tests.MemoryURIHandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Tests {@link ModelDefinitionItemProvider}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ModelDefinitionItemProviderTests {

    /**
     * The variable name.
     */
    private static final String SELF = "self";

    /**
     * The {@link URIHandler} that check we don't have adherence to {@link File}.
     */
    private static MemoryURIHandler uriHandler = new MemoryURIHandler();

    /**
     * The provider to test.
     */
    private final ModelDefinitionItemProvider provider = (ModelDefinitionItemProvider) new GenconfItemProviderAdapterFactory()
            .createModelDefinitionAdapter();

    /**
     * The test {@link ModelDefinition}.
     */
    private ModelDefinition definition;

    /**
     * The test {@link TemplateCustomProperties}.
     */
    private TemplateCustomProperties properties;

    /**
     * The test {@link XWPFDocument}.
     */
    private XWPFDocument document;

    /**
     * Registers the {@link MemoryURIHandler}.
     */
    @BeforeClass
    public static void beforeClass() {
        URIConverter.INSTANCE.getURIHandlers().add(0, uriHandler);
    }

    /**
     * Initializes test resources.
     */
    @Before
    public void before() {
        final ResourceSet rs = new ResourceSetImpl();
        rs.getURIConverter().getURIHandlers().add(0, uriHandler);
        rs.getResourceFactoryRegistry().getContentTypeToFactoryMap().put("*", new XMIResourceFactoryImpl());
        final Resource res = rs.createResource(URI.createURI("m2doctests://resources/test.genconf"));

        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();
        generation.setTemplateFileName(URI.createURI("test.docx").toString());
        res.getContents().add(generation);

        definition = GenconfPackage.eINSTANCE.getGenconfFactory().createModelDefinition();
        definition.setKey("self");
        generation.getDefinitions().add(definition);

        final EPackage ePkg1 = EcorePackage.eINSTANCE.getEcoreFactory().createEPackage();
        ePkg1.setName("package1");
        final EPackage ePkg2 = EcorePackage.eINSTANCE.getEcoreFactory().createEPackage();
        ePkg2.setName("package2");
        final EPackage ePkg3 = EcorePackage.eINSTANCE.getEcoreFactory().createEPackage();
        ePkg3.setName("package3");
        final EClass eCls1 = EcorePackage.eINSTANCE.getEcoreFactory().createEClass();
        eCls1.setName("Class1");
        final EClass eCls2 = EcorePackage.eINSTANCE.getEcoreFactory().createEClass();
        eCls2.setName("Class2");
        final EClass eCls3 = EcorePackage.eINSTANCE.getEcoreFactory().createEClass();
        eCls3.setName("Class3");

        res.getContents().add(ePkg1);
        res.getContents().add(ePkg2);
        res.getContents().add(ePkg3);

        res.getContents().add(eCls1);
        res.getContents().add(eCls2);
        res.getContents().add(eCls3);

        try {
            res.save(null);
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        document = new XWPFDocument();

        properties = new TemplateCustomProperties(document);
        properties.getPackagesURIs().add(GenconfPackage.eINSTANCE.getNsURI());
    }

    /**
     * Saves the test {@link XWPFDocument}.
     */
    protected void saveDocument() {
        try (OutputStream stream = URIConverter.INSTANCE
                .createOutputStream(URI.createURI("m2doctests://resources/test.docx"))) {
            document.write(stream);
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    /**
     * Clears the {@link MemoryURIHandler}.
     */
    @After
    public void after() {
        uriHandler.clear();
    }

    /**
     * Unregisters the {@link MemoryURIHandler}.
     */
    @AfterClass
    public static void afterClass() {
        URIConverter.INSTANCE.getURIHandlers().remove(uriHandler);
    }

    @Test
    public void getChoiceOfValuesNotAVariable() {
        final IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(definition,
                GenconfPackage.Literals.MODEL_DEFINITION__VALUE);

        definition.setKey("NotAVariable");
        properties.save();
        saveDocument();

        final List<?> values = (List<?>) descriptor.getChoiceOfValues(definition);

        assertNotNull(values);
        assertEquals(1, values.size());
        assertEquals(null, values.get(0));
    }

    @Test
    public void getChoiceOfValuesNoType() {
        final IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(definition,
                GenconfPackage.Literals.MODEL_DEFINITION__VALUE);

        properties.getVariables().put(SELF, "");
        properties.save();
        saveDocument();

        final List<?> values = (List<?>) descriptor.getChoiceOfValues(definition);

        assertNotNull(values);
        assertEquals(1, values.size());
        assertEquals(null, values.get(0));
    }

    @Test
    public void getChoiceOfValuesInvalideType() {
        final IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(definition,
                GenconfPackage.Literals.MODEL_DEFINITION__VALUE);

        properties.getVariables().put(SELF, "InvalidType");
        properties.save();
        saveDocument();

        final List<?> values = (List<?>) descriptor.getChoiceOfValues(definition);

        assertNotNull(values);
        assertEquals(1, values.size());
        assertEquals(null, values.get(0));
    }

    @Test
    public void getChoiceOfValuesEPackage() {
        final IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(definition,
                GenconfPackage.Literals.MODEL_DEFINITION__VALUE);

        properties.getVariables().put(SELF, "ecore::EPackage");
        properties.save();
        saveDocument();

        @SuppressWarnings("unchecked")
        final List<EPackage> values = (List<EPackage>) descriptor.getChoiceOfValues(definition);

        assertNotNull(values);
        assertEquals(4, values.size());
        assertEquals("package1", values.get(0).getName());
        assertEquals("package2", values.get(1).getName());
        assertEquals("package3", values.get(2).getName());
        assertEquals(null, values.get(3));
    }

    @Test
    public void getChoiceOfValuesEClass() {
        final IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(definition,
                GenconfPackage.Literals.MODEL_DEFINITION__VALUE);

        properties.getVariables().put(SELF, "ecore::EClass");
        properties.save();
        saveDocument();

        @SuppressWarnings("unchecked")
        final List<EClass> values = (List<EClass>) descriptor.getChoiceOfValues(definition);

        assertNotNull(values);
        assertEquals(4, values.size());
        assertEquals("Class1", values.get(0).getName());
        assertEquals("Class2", values.get(1).getName());
        assertEquals("Class3", values.get(2).getName());
        assertEquals(null, values.get(3));
    }

    @Test
    public void getChoiceOfValuesGeneration() {
        final IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(definition,
                GenconfPackage.Literals.MODEL_DEFINITION__VALUE);

        properties.getVariables().put(SELF, "genconf::Generation");
        properties.save();
        saveDocument();

        @SuppressWarnings("unchecked")
        final List<Generation> values = (List<Generation>) descriptor.getChoiceOfValues(definition);

        assertNotNull(values);
        assertEquals(2, values.size());
        assertEquals(definition.eContainer(), values.get(0));
        assertEquals(null, values.get(1));
    }

}
