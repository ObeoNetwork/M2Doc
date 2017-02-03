package org.obeonetwork.m2doc.ide.tests;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EPackage;
import org.junit.Before;
import org.junit.Test;
import org.obeonetwork.m2doc.ide.M2DocPlugin;
import org.obeonetwork.m2doc.util.M2DocUtils;
import org.osgi.framework.Bundle;

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
}
