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
package org.obeonetwork.m2doc.util.test;

import com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.poi.POIXMLProperties.CustomProperties;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.Test;
import org.obeonetwork.m2doc.properties.TemplateInfo;
import org.obeonetwork.m2doc.tplconf.EPackageMapping;
import org.obeonetwork.m2doc.tplconf.ScalarType;
import org.obeonetwork.m2doc.tplconf.StructuredType;
import org.obeonetwork.m2doc.tplconf.TemplateConfig;
import org.obeonetwork.m2doc.tplconf.TemplateVariable;
import org.obeonetwork.m2doc.tplconf.TplconfFactory;
import org.obeonetwork.m2doc.util.TemplateConfigUtil;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests of {@link TemplateConfigUtil}.
 * 
 * @author ldelaigue
 */
public class TemplateConfigUtilTest {

    @Test
    public void testTypeNameForClassifier() {
        assertEquals("ecore::EDataType", TemplateConfigUtil.typeName(EcorePackage.eINSTANCE.getEDataType()));
    }

    @Test
    public void testTypeName() {
        assertEquals("test::SomeClass", TemplateConfigUtil.typeName("test", "SomeClass"));
    }

    @Test
    public void testLoad() {
        TemplateInfo info = new TemplateInfo(new XWPFDocument()) {
            @Override
            public List<String> getPackagesURIs() {
                return Arrays.asList(EcorePackage.eNS_URI);
            }

            @Override
            public Map<String, String> getVariables() {
                return ImmutableMap.of("v1", "string", "v2", "ecore::EPackage");
            }
        };
        TemplateConfig config = TemplateConfigUtil.load(info);

        assertEquals(1, config.getMappings().size());
        EPackageMapping mappingEcore = config.getMappings().get(0);
        assertEquals("ecore", mappingEcore.getName());
        assertEquals(EcorePackage.eNS_URI, mappingEcore.getUri());
        assertEquals(EcorePackage.eINSTANCE, mappingEcore.getEPackage());

        assertEquals(2, config.getTypesByName().size());
        ScalarType typeString = (ScalarType) config.getTypesByName().get("string");
        StructuredType typeEPackage = (StructuredType) config.getTypesByName().get("ecore::EPackage");
        assertEquals("string", typeString.getName());
        assertEquals("EPackage", typeEPackage.getName());
        assertEquals(EcorePackage.eINSTANCE.getEPackage(), typeEPackage.getEClassifier());
        assertEquals("ecore", typeEPackage.getMappingName());
        assertEquals(mappingEcore, typeEPackage.getMapping());

        assertEquals(2, config.getVariables().size());
        TemplateVariable v1 = config.getVariables().get(0);
        assertEquals("v1", v1.getName());
        assertEquals("string", v1.getTypeName());
        assertEquals(typeString, v1.getType());

        TemplateVariable v2 = config.getVariables().get(1);
        assertEquals("v2", v2.getName());
        assertEquals("ecore::EPackage", v2.getTypeName());
        assertEquals(typeEPackage, v2.getType());
    }

    @Test
    public void testLoadWithUnknownVariableType() {
        TemplateInfo info = new TemplateInfo(new XWPFDocument()) {
            @Override
            public List<String> getPackagesURIs() {
                return Arrays.asList(EcorePackage.eNS_URI);
            }

            @Override
            public Map<String, String> getVariables() {
                return ImmutableMap.of("v1", "string", "v2", "test::SomeClass");
            }
        };
        TemplateConfig config = TemplateConfigUtil.load(info);

        assertEquals(1, config.getMappings().size());
        EPackageMapping mappingEcore = config.getMappings().get(0);
        assertEquals("ecore", mappingEcore.getName());
        assertEquals(EcorePackage.eNS_URI, mappingEcore.getUri());
        assertEquals(EcorePackage.eINSTANCE, mappingEcore.getEPackage());

        assertEquals(2, config.getTypesByName().size());
        ScalarType typeString = (ScalarType) config.getTypesByName().get("string");
        assertEquals("string", typeString.getName());
        StructuredType typeSomeClass = (StructuredType) config.getTypesByName().get("test::SomeClass");
        assertEquals("SomeClass", typeSomeClass.getName());
        assertNull(typeSomeClass.getEClassifier());
        assertNull(typeSomeClass.getMapping());
        assertEquals("test", typeSomeClass.getMappingName());

        assertEquals(2, config.getVariables().size());
        TemplateVariable v1 = config.getVariables().get(0);
        assertEquals("v1", v1.getName());
        assertEquals("string", v1.getTypeName());
        assertEquals(typeString, v1.getType());

        TemplateVariable v2 = config.getVariables().get(1);
        assertEquals("v2", v2.getName());
        assertEquals("test::SomeClass", v2.getTypeName());
        assertEquals(typeSomeClass, v2.getType());
    }

    @Test
    public void testLoadWithUnknownUri() {
        TemplateInfo info = new TemplateInfo(new XWPFDocument()) {
            @Override
            public List<String> getPackagesURIs() {
                return Arrays.asList("http://www.test.com/some/test/uri");
            }

            @Override
            public Map<String, String> getVariables() {
                return ImmutableMap.of("v1", "string", "v2", "test::EPackage");
            }
        };
        TemplateConfig config = TemplateConfigUtil.load(info);

        assertEquals(1, config.getMappings().size());

        EPackageMapping mapping = config.getMappings().get(0);
        assertEquals("http://www.test.com/some/test/uri", mapping.getUri());
        assertNull(mapping.getName());

        assertEquals(2, config.getTypesByName().size());
        ScalarType typeString = (ScalarType) config.getTypesByName().get("string");
        assertEquals("string", typeString.getName());
        StructuredType typeUnknown = (StructuredType) config.getTypesByName().get("test::EPackage");
        assertEquals("EPackage", typeUnknown.getName());
        assertNull(typeUnknown.getEClassifier());
        assertNull(typeUnknown.getMapping());
        assertEquals("test", typeUnknown.getMappingName());

        assertEquals(2, config.getVariables().size());
        TemplateVariable v1 = config.getVariables().get(0);
        assertEquals("v1", v1.getName());
        assertEquals("string", v1.getTypeName());
        assertEquals(typeString, v1.getType());

        TemplateVariable v2 = config.getVariables().get(1);
        assertEquals("v2", v2.getName());
        assertEquals("test::EPackage", v2.getTypeName());
        assertEquals(typeUnknown, v2.getType());
    }

    /**
     * This test checks that even if the nsURI is not explicitly declared, a
     * variable type with a registered prefix will be recognized.
     */
    @Test
    public void testLoadVariableWithUndeclaredButRegisteredUri() {
        TemplateInfo info = new TemplateInfo(new XWPFDocument()) {
            @Override
            public List<String> getPackagesURIs() {
                return Collections.emptyList();
            }

            @Override
            public Map<String, String> getVariables() {
                return ImmutableMap.of("v1", "string", "v2", "ecore::EPackage");
            }
        };
        TemplateConfig config = TemplateConfigUtil.load(info);

        assertEquals(1, config.getMappings().size());
        EPackageMapping mappingEcore = config.getMappings().get(0);
        assertEquals("ecore", mappingEcore.getName());
        assertEquals(EcorePackage.eNS_URI, mappingEcore.getUri());
        assertEquals(EcorePackage.eINSTANCE, mappingEcore.getEPackage());

        assertEquals(2, config.getTypesByName().size());
        ScalarType typeString = (ScalarType) config.getTypesByName().get("string");
        StructuredType typeEPackage = (StructuredType) config.getTypesByName().get("ecore::EPackage");
        assertEquals("string", typeString.getName());
        assertEquals("EPackage", typeEPackage.getName());
        assertEquals(EcorePackage.eINSTANCE.getEPackage(), typeEPackage.getEClassifier());
        assertEquals("ecore", typeEPackage.getMappingName());
        assertEquals(mappingEcore, typeEPackage.getMapping());

        assertEquals(2, config.getVariables().size());
        TemplateVariable v1 = config.getVariables().get(0);
        assertEquals("v1", v1.getName());
        assertEquals("string", v1.getTypeName());
        assertEquals(typeString, v1.getType());

        TemplateVariable v2 = config.getVariables().get(1);
        assertEquals("v2", v2.getName());
        assertEquals("ecore::EPackage", v2.getTypeName());
        assertEquals(typeEPackage, v2.getType());
    }

    @Test
    public void testStore() {
        // given
        XWPFDocument doc = new XWPFDocument();
        TemplateConfig config = TplconfFactory.eINSTANCE.createTemplateConfig();
        EPackageMapping ecoreMapping = TplconfFactory.eINSTANCE.createEPackageMapping();
        ecoreMapping.setName(EcorePackage.eNAME);
        ecoreMapping.setUri(EcorePackage.eNS_URI);
        ecoreMapping.setEPackage(EcorePackage.eINSTANCE);
        config.getMappings().add(ecoreMapping);

        EPackageMapping testMapping = TplconfFactory.eINSTANCE.createEPackageMapping();
        testMapping.setUri("http://www.test.com/some/test/uri");
        config.getMappings().add(testMapping);

        TemplateVariable v1 = TplconfFactory.eINSTANCE.createTemplateVariable();
        v1.setName("v1");
        v1.setTypeName("string");
        config.getVariables().add(v1);

        StructuredType typeEClassifier = TplconfFactory.eINSTANCE.createStructuredType();
        typeEClassifier.setName("EClassifier");
        typeEClassifier.setEClassifier(EcorePackage.eINSTANCE.getEClassifier());
        typeEClassifier.setMappingName(EcorePackage.eNAME);
        typeEClassifier.setMapping(ecoreMapping);
        config.getTypesByName().put("ecore::EClassifier", typeEClassifier);

        TemplateVariable v2 = TplconfFactory.eINSTANCE.createTemplateVariable();
        v2.setName("v2");
        v2.setTypeName("ecore::EClassifier");
        v2.setType(typeEClassifier);
        config.getVariables().add(v2);

        TemplateVariable v3 = TplconfFactory.eINSTANCE.createTemplateVariable();
        v3.setName("v3");
        v3.setTypeName("test::UnboundType");
        config.getVariables().add(v3);

        // when
        TemplateConfigUtil.store(config, doc);

        // then
        CustomProperties props = doc.getProperties().getCustomProperties();
        assertEquals(4, props.getUnderlyingProperties().sizeOfPropertyArray());

        CTProperty propUri = props.getProperty("m:uri");
        String propUriValue = propUri.getLpwstr();
        assertTrue(propUriValue.indexOf(EcorePackage.eNS_URI) >= 0);
        assertTrue(propUriValue.indexOf("http://www.test.com/some/test/uri") >= 0);
        assertTrue(propUriValue.indexOf(",") > 0);

        CTProperty propV1 = props.getProperty("m:var:v1");
        assertEquals("string", propV1.getLpwstr());

        CTProperty propV2 = props.getProperty("m:var:v2");
        assertEquals("ecore::EClassifier", propV2.getLpwstr());

        CTProperty propV3 = props.getProperty("m:var:v3");
        assertEquals("test::UnboundType", propV3.getLpwstr());
    }

    @Test
    public void testIsValidVariableName() {
        assertTrue(TemplateConfigUtil.isValidVariableName("v"));
        assertTrue(TemplateConfigUtil.isValidVariableName("_valid"));
        assertTrue(TemplateConfigUtil.isValidVariableName("valid_"));
        assertTrue(TemplateConfigUtil.isValidVariableName("valid_123"));
        assertTrue(TemplateConfigUtil.isValidVariableName("_VALID_1354"));

        assertFalse(TemplateConfigUtil.isValidVariableName(null));
        assertFalse(TemplateConfigUtil.isValidVariableName(""));
        assertFalse(TemplateConfigUtil.isValidVariableName("not valid"));
        assertFalse(TemplateConfigUtil.isValidVariableName("é"));
        assertFalse(TemplateConfigUtil.isValidVariableName("3invalid"));
        assertFalse(TemplateConfigUtil.isValidVariableName("-inv"));
        assertFalse(TemplateConfigUtil.isValidVariableName("not-valid"));
        assertFalse(TemplateConfigUtil.isValidVariableName("dfg$fsd"));
    }

    @Test
    public void testIsValidTypeName() {
        assertTrue(TemplateConfigUtil.isValidTypeName("string"));
        assertTrue(TemplateConfigUtil.isValidTypeName("a::v"));
        assertTrue(TemplateConfigUtil.isValidTypeName("_valid::Abc"));
        assertTrue(TemplateConfigUtil.isValidTypeName("valid_::abc"));
        assertTrue(TemplateConfigUtil.isValidTypeName("valid_123::abc"));
        assertTrue(TemplateConfigUtil.isValidTypeName("_VALID_1354::_Type_Name_with_àccénts"));
        assertTrue(TemplateConfigUtil.isValidTypeName("éàç_accented_chars_ok::_34"));

        assertFalse(TemplateConfigUtil.isValidTypeName(null));
        assertFalse(TemplateConfigUtil.isValidTypeName(""));
        assertFalse(TemplateConfigUtil.isValidTypeName("int"));
        assertFalse(TemplateConfigUtil.isValidTypeName("real"));
        assertFalse(TemplateConfigUtil.isValidTypeName("boolean"));
        assertFalse(TemplateConfigUtil.isValidTypeName("object"));
        assertFalse(TemplateConfigUtil.isValidTypeName("date"));
        assertFalse(TemplateConfigUtil.isValidTypeName("not valid::fds"));
        assertFalse(TemplateConfigUtil.isValidTypeName("3invalid::fds"));
        assertFalse(TemplateConfigUtil.isValidTypeName("-inv::fds"));
        assertFalse(TemplateConfigUtil.isValidTypeName("not-valid::fds"));
    }

}
