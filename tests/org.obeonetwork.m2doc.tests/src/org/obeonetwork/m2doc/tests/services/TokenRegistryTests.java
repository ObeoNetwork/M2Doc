/*******************************************************************************
 *  Copyright (c) 2018, 2025 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.tests.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.services.TokenRegistry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests the {@link TokenRegistry} class.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class TokenRegistryTests {

    @Test
    public void clearEmpty() {
        final TokenRegistry registry = new TokenRegistry();

        registry.clear();
        assertTrue(registry.getRegisteredTokens().isEmpty());
    }

    @Test
    public void clear() {
        final TokenRegistry registry = new TokenRegistry();

        final List<String> classes = new ArrayList<>();
        classes.add("someClass1");
        classes.add("someClass2");
        registry.registerServices("testToken", "someBundle", classes);

        final List<String> packages = new ArrayList<>();
        packages.add("package1");
        packages.add("package2");
        registry.registerPackages("testToken", packages);

        assertEquals(1, registry.getRegisteredTokens().size());

        registry.clear();
        assertTrue(registry.getRegisteredTokens().isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void deselectTokenNullNull() {
        final TokenRegistry registry = new TokenRegistry();

        registry.deselectToken(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void deselectTokenNullTokenName() {
        final TokenRegistry registry = new TokenRegistry();

        registry.deselectToken(null, "someToken");
    }

    @Test
    public void deselectTokenTemplateCustomPropertiesNull() throws IOException {
        final TokenRegistry registry = new TokenRegistry();

        try (XWPFDocument document = new XWPFDocument();) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);
            registry.deselectToken(properties, null);

            assertTrue(properties.getPackagesURIs().isEmpty());
            assertTrue(properties.getImports().isEmpty());
        }

    }

    @Test
    public void deselectToken() throws IOException {
        final TokenRegistry registry = new TokenRegistry();

        final List<String> classes = new ArrayList<>();
        classes.add("someClass1");
        classes.add("someClass2");
        registry.registerServices("testToken", "someBundle", classes);

        final List<String> packages = new ArrayList<>();
        packages.add("package1");
        packages.add("package2");
        registry.registerPackages("testToken", packages);

        try (XWPFDocument document = new XWPFDocument();) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);
            registry.deselectToken(properties, null);

            registry.selectToken(properties, "testToken");

            assertTrue(!properties.getPackagesURIs().isEmpty());
            assertTrue(!properties.getImports().isEmpty());

            registry.deselectToken(properties, "testToken");

            assertTrue(properties.getPackagesURIs().isEmpty());
            assertTrue(properties.getImports().isEmpty());
        }
    }

    @Test
    public void deselectTokenMultiTokenWithIntersection() throws IOException {
        final TokenRegistry registry = new TokenRegistry();

        final List<String> classes1 = new ArrayList<>();
        classes1.add("someClass1");
        classes1.add("someClass2");
        registry.registerServices("testToken1", "someBundle", classes1);

        final List<String> packages1 = new ArrayList<>();
        packages1.add("package1");
        packages1.add("package2");
        registry.registerPackages("testToken1", packages1);

        final List<String> classes2 = new ArrayList<>();
        classes2.add("someClass2");
        classes2.add("someClass3");
        registry.registerServices("testToken2", "someBundle", classes2);

        final List<String> packages2 = new ArrayList<>();
        packages2.add("package2");
        packages2.add("package3");
        registry.registerPackages("testToken2", packages2);

        try (XWPFDocument document = new XWPFDocument();) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);
            registry.deselectToken(properties, null);

            registry.selectToken(properties, "testToken1");
            registry.selectToken(properties, "testToken2");

            assertEquals(3, properties.getPackagesURIs().size());
            assertEquals(3, properties.getImports().size());

            registry.deselectToken(properties, "testToken1");

            assertEquals(2, properties.getPackagesURIs().size());
            assertEquals(2, properties.getImports().size());
            Iterator<String> it = properties.getPackagesURIs().iterator();
            assertEquals("package3", it.next());
            assertEquals("package2", it.next());
            it = properties.getImports().iterator();
            assertEquals("someClass3", it.next());
            assertEquals("someClass2", it.next());
        }

    }

    @Test
    public void getPackagesNull() {
        final TokenRegistry registry = new TokenRegistry();

        assertTrue(registry.getPackages(null).isEmpty());
    }

    @Test
    public void getPackagesEmpty() {
        final TokenRegistry registry = new TokenRegistry();

        assertTrue(registry.getPackages("").isEmpty());
    }

    @Test
    public void getPackages() {
        final TokenRegistry registry = new TokenRegistry();

        final List<String> classes = new ArrayList<>();
        classes.add("someClass1");
        classes.add("someClass2");
        registry.registerServices("testToken", "someBundle", classes);

        final List<String> packages = new ArrayList<>();
        packages.add("package1");
        packages.add("package2");
        registry.registerPackages("testToken", packages);

        assertEquals(2, registry.getPackages("testToken").size());
        assertEquals("package1", registry.getPackages("testToken").get(0));
        assertEquals("package2", registry.getPackages("testToken").get(1));
    }

    @Test
    public void getRegisteredTokensEmpty() {
        final TokenRegistry registry = new TokenRegistry();

        assertTrue(registry.getRegisteredTokens().isEmpty());
    }

    @Test
    public void getRegisteredTokens() {
        final TokenRegistry registry = new TokenRegistry();

        final List<String> classes = new ArrayList<>();
        classes.add("someClass1");
        classes.add("someClass2");
        registry.registerServices("testToken", "someBundle", classes);

        final List<String> packages = new ArrayList<>();
        packages.add("package1");
        packages.add("package2");
        registry.registerPackages("testToken", packages);

        assertEquals(1, registry.getRegisteredTokens().size());
    }

    @Test
    public void getSelectedTokenNull() {
        final TokenRegistry registry = new TokenRegistry();

        assertTrue(registry.getSelectedToken(null).isEmpty());
    }

    @Test
    public void getSelectedTokenEmpty() throws IOException {
        final TokenRegistry registry = new TokenRegistry();

        try (XWPFDocument document = new XWPFDocument();) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);

            assertTrue(registry.getSelectedToken(properties).isEmpty());
        }
    }

    @Test
    public void getSelectedTokenMultiTokenWithIntersection() throws IOException {
        final TokenRegistry registry = new TokenRegistry();

        final List<String> classes1 = new ArrayList<>();
        classes1.add("someClass1");
        classes1.add("someClass2");
        registry.registerServices("testToken1", "someBundle", classes1);

        final List<String> packages1 = new ArrayList<>();
        packages1.add("package1");
        packages1.add("package2");
        registry.registerPackages("testToken1", packages1);

        final List<String> classes2 = new ArrayList<>();
        classes2.add("someClass2");
        classes2.add("someClass3");
        registry.registerServices("testToken2", "someBundle", classes2);

        final List<String> packages2 = new ArrayList<>();
        packages2.add("package2");
        packages2.add("package3");
        registry.registerPackages("testToken2", packages2);

        try (XWPFDocument document = new XWPFDocument();) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);
            registry.deselectToken(properties, null);

            registry.selectToken(properties, "testToken1");
            registry.selectToken(properties, "testToken2");

            assertEquals(2, registry.getSelectedToken(properties).size());
            assertEquals("testToken1", registry.getSelectedToken(properties).get(0));
            assertEquals("testToken2", registry.getSelectedToken(properties).get(1));

            registry.deselectToken(properties, "testToken2");

            assertEquals(1, registry.getSelectedToken(properties).size());
            assertEquals("testToken1", registry.getSelectedToken(properties).get(0));
        }
    }

    @Test
    public void getServicesNull() {
        final TokenRegistry registry = new TokenRegistry();

        assertTrue(registry.getServices(null).isEmpty());
    }

    @Test
    public void getServicesEmpty() {
        final TokenRegistry registry = new TokenRegistry();

        assertTrue(registry.getServices("").isEmpty());
    }

    @Test
    public void getServices() {
        final TokenRegistry registry = new TokenRegistry();

        final List<String> classes = new ArrayList<>();
        classes.add("someClass1");
        classes.add("someClass2");
        registry.registerServices("testToken", "someBundle", classes);

        final List<String> packages = new ArrayList<>();
        packages.add("package1");
        packages.add("package2");
        registry.registerPackages("testToken", packages);

        assertEquals(1, registry.getServices("testToken").size());
        assertEquals(2, registry.getServices("testToken").get("someBundle").size());
        assertEquals("someClass1", registry.getServices("testToken").get("someBundle").get(0));
        assertEquals("someClass2", registry.getServices("testToken").get("someBundle").get(1));
    }

    @Test
    public void registerServicesTestSingleToken() {
        final TokenRegistry registry = new TokenRegistry();

        final List<String> services = new ArrayList<>();
        services.add(ServicePackage1.class.getCanonicalName());
        services.add(ServicePackage2.class.getCanonicalName());
        registry.registerServices("token1", "bundle", services);

        final Map<String, List<String>> map = registry.getServices("token1");
        assertEquals(1, map.size());
        final List<String> list = map.get("bundle");
        assertEquals(2, list.size());
        assertEquals(ServicePackage1.class.getCanonicalName(), list.get(0));
        assertEquals(ServicePackage2.class.getCanonicalName(), list.get(1));
    }

    @Test
    public void registerServicesTestTwoTokens() {
        final TokenRegistry registry = new TokenRegistry();

        final List<String> services1 = new ArrayList<>();
        services1.add(ServicePackage1.class.getCanonicalName());
        services1.add(ServicePackage2.class.getCanonicalName());
        final List<String> services2 = new ArrayList<>();
        services2.add(ServicePackage1.class.getCanonicalName());
        services2.add(ServicePackage2.class.getCanonicalName());
        services2.add(ServicePackage3.class.getCanonicalName());

        registry.registerServices("token1", "bundle", services1);
        registry.registerServices("token2", "bundle", services2);

        final Map<String, List<String>> map1 = registry.getServices("token1");
        assertEquals(1, map1.size());
        final List<String> list1 = map1.get("bundle");
        assertEquals(2, list1.size());
        assertEquals(ServicePackage1.class.getCanonicalName(), list1.get(0));
        assertEquals(ServicePackage2.class.getCanonicalName(), list1.get(1));
        final Map<String, List<String>> map2 = registry.getServices("token2");
        assertEquals(1, map2.size());
        final List<String> list2 = map2.get("bundle");
        assertEquals(3, list2.size());
        assertEquals(ServicePackage1.class.getCanonicalName(), list2.get(0));
        assertEquals(ServicePackage2.class.getCanonicalName(), list2.get(1));
        assertEquals(ServicePackage3.class.getCanonicalName(), list2.get(2));
    }

    @Test
    public void unregisterServices() {
        final TokenRegistry registry = new TokenRegistry();

        final List<String> services = new ArrayList<>();
        services.add(ServicePackage1.class.getCanonicalName());
        services.add(ServicePackage2.class.getCanonicalName());
        services.add(ServicePackage3.class.getCanonicalName());

        registry.registerServices("token", "bundle", services);

        final List<String> servicesToRemove = new ArrayList<>();
        servicesToRemove.add(ServicePackage1.class.getCanonicalName());
        servicesToRemove.add(ServicePackage2.class.getCanonicalName());

        registry.unregisterServices("token", "bundle", servicesToRemove);

        final Map<String, List<String>> map = registry.getServices("token");
        assertEquals(1, map.size());
        final List<String> list = map.get("bundle");
        assertEquals(1, list.size());
        assertEquals(ServicePackage3.class.getCanonicalName(), list.get(0));
    }

    @Test
    public void registerPackagesTestSingleToken() {
        final TokenRegistry registry = new TokenRegistry();

        final List<String> packages = new ArrayList<>();
        packages.add("someNsURI1");
        packages.add("someNsURI2");
        registry.registerPackages("token1", packages);

        final List<String> list = registry.getPackages("token1");
        assertEquals(2, list.size());
        assertEquals("someNsURI1", list.get(0));
        assertEquals("someNsURI2", list.get(1));
    }

    @Test
    public void registerPackagesTestTwoTokens() {
        final TokenRegistry registry = new TokenRegistry();

        final List<String> packages1 = new ArrayList<>();
        packages1.add("someNsURI1");
        packages1.add("someNsURI2");
        final List<String> packages2 = new ArrayList<>();
        packages2.add("someNsURI1");
        packages2.add("someNsURI2");
        packages2.add("someNsURI3");

        registry.registerPackages("token1", packages1);
        registry.registerPackages("token2", packages2);

        final List<String> list1 = registry.getPackages("token1");
        assertEquals(2, list1.size());
        assertEquals("someNsURI1", list1.get(0));
        assertEquals("someNsURI2", list1.get(1));
        final List<String> list2 = registry.getPackages("token2");
        assertEquals(3, list2.size());
        assertEquals("someNsURI1", list2.get(0));
        assertEquals("someNsURI2", list2.get(1));
        assertEquals("someNsURI3", list2.get(2));
    }

    @Test
    public void unregisterPackages() {
        final TokenRegistry registry = new TokenRegistry();

        final List<String> packages = new ArrayList<>();
        packages.add("someNsURI1");
        packages.add("someNsURI2");
        packages.add("someNsURI3");

        registry.registerPackages("token", packages);

        final List<String> packagesToRemove = new ArrayList<>();
        packagesToRemove.add("someNsURI1");
        packagesToRemove.add("someNsURI2");

        registry.unregisterPackages("token", packagesToRemove);

        final List<String> list = registry.getPackages("token");
        assertEquals(1, list.size());
        assertEquals("someNsURI3", list.get(0));
    }

}
