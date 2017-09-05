package org.obeonetwork.m2doc.ide.util;

import java.util.HashMap;
import java.util.Map;

import org.obeonetwork.m2doc.util.ClassProvider;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;

/**
 * Provides {@link Class} from the running Eclipse.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class EclipseClassProvider extends ClassProvider {

    /**
     * The {@link BundleContext}.
     */
    private final BundleContext context;

    /**
     * The {@link Map} of {@link Bundle#getSymbolicName() bundle name} to {@link Bundle}.
     */
    // TODO add version ?
    private final Map<String, Bundle> bundles = new HashMap<String, Bundle>();

    /**
     * The {@link BundleListener} keeping track of {@link BundleEvent#INSTALLED installed} and {@link BundleEvent#UNINSTALLED uninstalled}
     * {@link Bundle}.
     */
    private final BundleListener listener;

    /**
     * Constructor.
     * 
     * @param context
     *            the {@link BundleContext}
     * @param classLoader
     *            the fall back {@link ClassLoader}
     */
    public EclipseClassProvider(BundleContext context, ClassLoader classLoader) {
        super(classLoader);
        this.context = context;
        for (Bundle bundle : context.getBundles()) {
            bundles.put(bundle.getSymbolicName(), bundle);
        }
        listener = new BundleListener() {

            @Override
            public void bundleChanged(BundleEvent event) {
                switch (event.getType()) {
                    case BundleEvent.INSTALLED:
                        bundles.put(event.getBundle().getSymbolicName(), event.getBundle());
                        break;

                    case BundleEvent.UNINSTALLED:
                        bundles.remove(event.getBundle().getSymbolicName());
                        break;

                    default:
                        // nothing to do here
                        break;
                }
            }
        };
        context.addBundleListener(listener);
    }

    @Override
    public Class<?> getClass(String className, String bundleName) throws ClassNotFoundException {
        final Class<?> res;

        final Bundle bundle = bundles.get(bundleName);
        if (bundle != null) {
            res = bundle.loadClass(className);
        } else {
            res = super.getClass(className, bundleName);
        }

        return res;
    }

    /**
     * Disposes the internal {@link BundleListener}.
     */
    public void dispose() {
        context.removeBundleListener(listener);
    }

}
