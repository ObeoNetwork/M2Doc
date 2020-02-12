package org.obeonetwork.m2doc.ide.jdt.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;
import org.obeonetwork.m2doc.ide.M2DocPlugin;
import org.obeonetwork.m2doc.ide.jdt.M2DocJDTPlugin;
import org.obeonetwork.m2doc.ide.util.EclipseClassProvider;

/**
 * Provides {@link Class} from the running Eclipse.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class EclipseJDTClassProvider extends EclipseClassProvider {

    /**
     * Mapping from project name to it's {@link ClassLoader}.
     */
    private Map<String, ClassLoader> classLoaders = new HashMap<String, ClassLoader>();

    /**
     * The {@link IResourceChangeListener} updating the cache according to workspace changes.
     */
    private final IResourceChangeListener listener = new IResourceChangeListener() {

        @Override
        public void resourceChanged(IResourceChangeEvent event) {
            if (event != null) {
                final IResourceDelta delta = event.getDelta();
                for (IResourceDelta child : delta.getAffectedChildren()) {
                    final IResource resource = child.getResource();
                    if (resource != null) {
                        final IProject project = resource.getProject();
                        if (project != null) {
                            classLoaders.remove(project.getName());
                        }
                    }
                }
            }
        }
    };

    /**
     * Constructor.
     */
    public EclipseJDTClassProvider() {
        super(M2DocPlugin.getBundlerContext(), M2DocPlugin.getPlugin().getClass().getClassLoader());
        ResourcesPlugin.getWorkspace().addResourceChangeListener(listener);
    }

    @Override
    public Class<?> getClass(String className, String bundleName) throws ClassNotFoundException {
        final Class<?> res;

        final ClassLoader classLoader = getClassLoaderForProject(bundleName);
        if (classLoader != null) {
            res = classLoader.loadClass(className);
        } else {
            res = super.getClass(className, bundleName);
        }

        return res;
    }

    /**
     * Gets the {@link ClassLoader} for the given project name.
     * 
     * @param name
     *            the project name
     * @return the {@link ClassLoader} for the given project name if nay, <code>null</code> otherwise
     */
    protected ClassLoader getClassLoaderForProject(String name) {
        ClassLoader res;

        final ClassLoader cachedClassLoader = classLoaders.get(name);
        if (cachedClassLoader != null) {
            res = cachedClassLoader;
        } else {
            final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(name);
            if (project.exists() && project.isOpen()) {
                final IJavaProject javaProject = JavaCore.create(project);
                if (javaProject != null) {
                    try {
                        final String[] classPathEntries = JavaRuntime.computeDefaultRuntimeClassPath(javaProject);
                        final List<URL> urlList = new ArrayList<URL>();
                        for (String entry : classPathEntries) {
                            final IPath path = new Path(entry);
                            final URL url = path.toFile().toURI().toURL();
                            urlList.add(url);
                        }
                        final URL[] urls = (URL[]) urlList.toArray(new URL[urlList.size()]);
                        res = new URLClassLoader(urls, getClassLoader());
                        classLoaders.put(name, res);
                    } catch (CoreException e) {
                        M2DocJDTPlugin.getPlugin().getLog().log(
                                new Status(IStatus.ERROR, M2DocJDTPlugin.PLUGIN_ID, "can't load from workspace.", e));
                        res = null;
                    } catch (MalformedURLException e) {
                        M2DocJDTPlugin.getPlugin().getLog().log(
                                new Status(IStatus.ERROR, M2DocJDTPlugin.PLUGIN_ID, "can't load from workspace.", e));
                        res = null;
                    }
                } else {
                    res = null;
                }
            } else {
                res = null;
            }
        }

        return res;
    }

    @Override
    public void dispose() {
        super.dispose();
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(listener);
        classLoaders.clear();
    }

}
