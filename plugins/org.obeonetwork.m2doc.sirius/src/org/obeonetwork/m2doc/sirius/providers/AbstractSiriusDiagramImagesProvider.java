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
package org.obeonetwork.m2doc.sirius.providers;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.io.Files;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.obeonetwork.m2doc.provider.AbstractDiagramProvider;

/**
 * All providers of this kind provide Sirius diagram images.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public abstract class AbstractSiriusDiagramImagesProvider extends AbstractDiagramProvider {

    /**
     * Boolean to know if diagram should be refresh before M2Doc generation.
     * True mean refresh, and default value is false.
     */
    protected boolean refreshRepresentations;

    /**
     * The {@link Set} of directory to cleanup.
     */
    private Set<File> directoryToCleanup = Sets.newLinkedHashSet();

    /**
     * return layers to activate.
     * 
     * @param diagram
     *            DDiagram
     * @param diagramActivatedLayers
     *            layers to activate
     * @return layers to activate
     */
    protected List<Layer> getLayers(DDiagram diagram, List<String> diagramActivatedLayers) {
        List<Layer> layers = Lists.newArrayList();
        List<Layer> allLayers = getAllLayers(diagram.getDescription());
        for (Object layerName : diagramActivatedLayers) {
            Layer layer = getLayer(allLayers, layerName);
            if (layer != null) {
                layers.add(layer);
            }
        }
        return layers;
    }

    /**
     * Get all the layers of a diagram description.
     * 
     * @param description
     *            the diagram description
     * @return all the layers
     */
    public EList<Layer> getAllLayers(final DiagramDescription description) {

        final Collection<Layer> layers = new ArrayList<>();
        if (description.getDefaultLayer() != null) {
            layers.add(description.getDefaultLayer());
        }
        layers.addAll(description.getAdditionalLayers());
        return new EcoreEList.UnmodifiableEList<>((InternalEObject) description,
                DescriptionPackage.eINSTANCE.getDiagramDescription_AllLayers(), layers.size(), layers.toArray());
    }

    /**
     * Return name layer.
     * 
     * @param layers
     *            List<Layer>
     * @param name
     *            layer name
     * @return name layer
     */
    protected Layer getLayer(List<Layer> layers, Object name) {
        for (final Layer layer : layers) {
            if (layer.getName().equals(name)) {
                return layer;
            }
        }
        return null;
    }

    /**
     * Creates a temporary directory.
     * 
     * @return the {@link File} corresponding to the created directory
     */
    protected String createTempDirectoryPath() {
        File tempFolder = Files.createTempDir();
        directoryToCleanup.add(tempFolder);
        return tempFolder.getAbsolutePath();
    }

    @Override
    public void clear() {
        super.clear();
        for (File file : directoryToCleanup) {
            deleteDirectory(file);
        }
    }

    /**
     * Recursively deletes the given folder.
     * 
     * @param folder
     *            the folder to delete
     */
    private void deleteDirectory(File folder) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null && files.length > 0) {
                for (File aFile : files) {
                    deleteDirectory(aFile);
                }
            }
            folder.delete();
        } else {
            folder.delete();
        }
    }

}
