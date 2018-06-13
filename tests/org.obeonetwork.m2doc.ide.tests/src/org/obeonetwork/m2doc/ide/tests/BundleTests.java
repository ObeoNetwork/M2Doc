package org.obeonetwork.m2doc.ide.tests;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.junit.Before;
import org.junit.Test;
import org.obeonetwork.m2doc.ide.M2DocPlugin;
import org.obeonetwork.m2doc.services.TemplateRegistry;
import org.obeonetwork.m2doc.services.TokenRegistry;
import org.obeonetwork.m2doc.util.M2DocUtils;
import org.osgi.framework.Bundle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Bundle tests.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class BundleTests {

    @Before
    public void setUp() {
        // Make sure the org.obeonetwork.m2doc.doc is loaded
        M2DocUtils.class.getName();
        // Make sure the org.obeonetwork.m2doc.doc.ide is loaded
        M2DocPlugin.getPlugin();
    }

    @Test
    public void notSingleton() {
        final Bundle bundle = Platform.getBundle("org.obeonetwork.m2doc");
        assertNotNull(bundle);
        assertNull(bundle.getHeaders().get("singleton"));
    }

    @Test
    public void isRegistredEcore() {
        assertNotNull(EPackage.Registry.INSTANCE.getEPackage("http://www.obeonetwork.org/m2doc/template/1.0"));
    }

    @Test
    public void serviceTokenExtensionPoint() {
        final Map<String, List<String>> map = TokenRegistry.INSTANCE.getServices("test token");

        assertNotNull(map);
        final List<String> list = map.get("org.obeonetwork.m2doc.ide.tests");

        assertNotNull(list);
        assertEquals(2, list.size());
        assertEquals("org.obeonetwork.m2doc.ide.tests.services.TestServiceClass1", list.get(0));
        assertEquals("org.obeonetwork.m2doc.ide.tests.services.TestServiceClass2", list.get(1));
    }

    @Test
    public void packageTokenExtensionPoint() {
        final List<String> list = TokenRegistry.INSTANCE.getPackages("test token");

        assertNotNull(list);
        assertEquals(2, list.size());
        assertEquals("http://www.obeonetwork.org/m2doc/template/1.0", list.get(0));
        assertEquals("http://www.eclipse.org/emf/2002/Ecore", list.get(1));
    }

    @Test
    public void templateExtensionPoint() {
        final Map<String, URI> map = TemplateRegistry.INSTANCE.getTemplates();

        assertNotNull(map);
        assertEquals(1, map.size());
        assertEquals("platform:/plugin/org.obeonetwork.m2doc.ide.tests/resources/test-template.docx",
                map.get("Test template").toString());
    }

}
