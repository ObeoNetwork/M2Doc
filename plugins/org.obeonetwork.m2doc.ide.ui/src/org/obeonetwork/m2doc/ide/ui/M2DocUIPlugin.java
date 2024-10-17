/*******************************************************************************
 *  Copyright (c) 2016, 2024 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.ide.ui;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.acceleo.query.parser.AstValidator;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.impl.ValidationServices;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.ide.services.configurator.ServicesConfiguratorRegistryListener;
import org.obeonetwork.m2doc.ide.ui.util.ClassPropertyUpdaterRegistryListener;
import org.obeonetwork.m2doc.ide.ui.util.IClassPropertyUpdater;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.util.M2DocUtils;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 */
public class M2DocUIPlugin extends AbstractUIPlugin {

    /**
     * The delete image key.
     */
    public static final String DELETE_IMG_KEY = "delete";

    /**
     * The add image key.
     */
    public static final String ADD_IMG_KEY = "add";

    /**
     * The plug-in ID.
     */
    public static final String PLUGIN_ID = "org.obeonetwork.m2doc.ide.ui"; //$NON-NLS-1$

    /**
     * Templates libraries preferences.
     */
    public static final String LIBRARIES_PREFERENCES = "librariesPrefrences";

    /**
     * The shared instance.
     */
    private static M2DocUIPlugin plugin;

    /**
     * The {@link ClassPropertyUpdaterRegistryListener}.
     */
    private static final ClassPropertyUpdaterRegistryListener CLASS_PROPERTY_UPDATER_REGISTERY_LISTENER = new ClassPropertyUpdaterRegistryListener();

    /**
     * The registered {@link IClassPropertyUpdater}.
     */
    private static IClassPropertyUpdater classPropertyUpdater;

    /**
     * The constructor.
     */
    public M2DocUIPlugin() {
    }

    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        final IExtensionRegistry registry = Platform.getExtensionRegistry();
        registry.addListener(CLASS_PROPERTY_UPDATER_REGISTERY_LISTENER,
                ServicesConfiguratorRegistryListener.SERVICES_CONFIGURATOR_EXTENSION_POINT);
        CLASS_PROPERTY_UPDATER_REGISTERY_LISTENER.parseInitialContributions();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
        final IExtensionRegistry registry = Platform.getExtensionRegistry();
        registry.removeListener(CLASS_PROPERTY_UPDATER_REGISTERY_LISTENER);
    }

    /**
     * Returns the shared instance.
     *
     * @return the shared instance
     */
    public static M2DocUIPlugin getDefault() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path.
     *
     * @param path
     *            the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

    @Override
    protected void initializeImageRegistry(ImageRegistry reg) {
        try {
            reg.put(ADD_IMG_KEY, ImageDescriptor
                    .createFromURL(new URL("platform:/plugin/org.obeonetwork.m2doc.ide.ui/icons/add.gif")));
            reg.put(DELETE_IMG_KEY, ImageDescriptor
                    .createFromURL(new URL("platform:/plugin/org.obeonetwork.m2doc.ide.ui/icons/delete.gif")));
        } catch (MalformedURLException e) {
            getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, e.getMessage(), e));
        }
    }

    /**
     * Gets the registered {@link IClassPropertyUpdater}.
     * 
     * @return the registered {@link IClassPropertyUpdater}
     */
    public static IClassPropertyUpdater getClassPropertyUpdater() {
        return classPropertyUpdater;
    }

    /**
     * Registers the given {@link IClassPropertyUpdater}.
     * 
     * @param updater
     *            the {@link IClassPropertyUpdater} to update
     */
    public static void registerClassPropertyUpdater(IClassPropertyUpdater updater) {
        classPropertyUpdater = updater;
    }

    /**
     * Gets the {@link Map} of template {@link File} to its {@link TemplateCustomProperties} that have only one variable compatible with
     * the
     * given {@link Set} of {@link IType}.
     * 
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param types
     *            the {@link Set} of {@link IType} to match
     * @return the {@link Map} of template {@link File} to its {@link TemplateCustomProperties} that have only one variable compatible
     *         with
     *         the given {@link Set} of {@link IType}
     */
    public Map<File, TemplateCustomProperties> getCompatibleTemplatesFromLibraries(
            IReadOnlyQueryEnvironment queryEnvironment, Set<IType> types) {
        final Map<File, TemplateCustomProperties> res = new LinkedHashMap<>();

        final List<File> templateFiles = getAllTemplatesFromLibraries();

        for (File templateFile : templateFiles) {
            try {
                final TemplateCustomProperties properties = POIServices.getInstance().getTemplateCustomProperties(
                        URIConverter.INSTANCE, URI.createFileURI(templateFile.getAbsolutePath()));
                final AstValidator validator = new AstValidator(new ValidationServices(queryEnvironment));
                final Map<String, Set<IType>> variableTypes = properties.getVariableTypes(validator, queryEnvironment);
                if (variableTypes.size() == 1
                    && isCompatible(types, variableTypes.entrySet().iterator().next().getValue())) {
                    res.put(templateFile, properties);
                }
                // CHECKSTYLE:OFF
            } catch (Exception e) {
                // CHECKSTYLE:ON
                M2DocUIPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, getClass(),
                        "can't load from library: " + templateFile.getAbsolutePath(), e));
            }
        }

        return res;
    }

    /**
     * Gets the {@link List} of all template {@link File} form libraries.
     * 
     * @return the {@link List} of all template {@link File} form libraries
     */
    public List<File> getAllTemplatesFromLibraries() {
        final List<File> res = new ArrayList<>();

        final IPreferenceStore preferenceStore = getPreferenceStore();
        final String librariesString = preferenceStore.getString(LIBRARIES_PREFERENCES);
        if (librariesString != null) {
            for (String libraryString : librariesString.split(":")) {
                final File library = new File(libraryString);
                res.addAll(getAllTemplates(library));
            }
        }

        return res;
    }

    /**
     * Tells if at least one combination in the cartetian product the variable {@link IType} can be {@link IType#isAssignableFrom(IType)
     * assigned} to the declaration {@link IType}.
     * 
     * @param variableTypes
     *            the {@link Set} of variable {@link IType}
     * @param declarationTypes
     *            the {@link Set} of declaration {@link IType}
     * @return <code>true</code> if at least one combination in the cartetian product the variable {@link IType} can be
     *         {@link IType#isAssignableFrom(IType)
     *         assigned} to the declaration {@link IType}, <code>false</code> otherwise
     */
    private boolean isCompatible(Set<IType> variableTypes, Set<IType> declarationTypes) {
        boolean res = false;

        compatible: for (IType variableType : variableTypes) {
            for (IType declarationType : declarationTypes) {
                if (declarationType.isAssignableFrom(variableType)) {
                    res = true;
                    break compatible;
                }
            }
        }

        return res;
    }

    /**
     * Gets the {@link List} of all the templates files recursively from the given library {@link File}.
     * 
     * @param library
     *            the library {@link File}
     * @return the {@link List} of all the templates files recursively from the given library {@link File}
     */
    private List<File> getAllTemplates(File library) {
        final List<File> res = new ArrayList<>();

        if (library.exists() && library.isDirectory()) {
            // filter for the folders
            FileFilter folderFilter = new FileFilter() {

                @Override
                public boolean accept(File pathname) {
                    if (pathname.isDirectory()) {
                        return true;
                    }
                    return false;
                }
            };

            // filter for the docx files
            FilenameFilter templateFiles = new FilenameFilter() {

                @Override
                public boolean accept(File dir, String name) {
                    String lowerCaseName = name.toLowerCase();
                    if (lowerCaseName.endsWith("." + M2DocUtils.DOCX_EXTENSION_FILE)) {
                        return true;
                    }
                    return false;
                }
            };

            // get templates of the folder
            for (File file : library.listFiles(templateFiles)) {
                res.add(file);
            }

            // get templates of the subFolders
            for (File subFolder : library.listFiles(folderFilter)) {
                res.addAll(getAllTemplates(subFolder));
            }
        }

        return res;
    }

}
