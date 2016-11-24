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

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.render.clipboard.DiagramGenerator;
import org.eclipse.gmf.runtime.diagram.ui.render.util.CopyToImageUtil;
import org.eclipse.gmf.runtime.diagram.ui.render.util.DiagramImageUtils;
import org.eclipse.gmf.runtime.diagram.ui.util.DiagramEditorUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.ui.internal.refresh.SiriusDiagramSessionEventBroker;
import org.eclipse.sirius.diagram.ui.internal.refresh.listeners.GMFDiagramUpdater;
import org.eclipse.sirius.diagram.ui.tools.api.part.DiagramEditPartService;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Shell;
import org.obeonetwork.m2doc.provider.AbstractDiagramProvider;
import org.obeonetwork.m2doc.provider.ProviderException;
import org.obeonetwork.m2doc.sirius.commands.ExportRepresentationCommand;

/**
 * All providers of this kind provide Sirius diagram images.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
@SuppressWarnings("restriction")
public abstract class AbstractSiriusDiagramImagesProvider extends AbstractDiagramProvider {

    /**
     * Boolean to know if diagram should be refresh before M2Doc generation.
     * True mean refresh, and default value is false.
     */
    protected boolean refreshRepresentations;

    /**
     * Replace forbidden characters with "_" in a filename.
     * 
     * @param name
     *            the string to sanitized.
     * @return sanitized string.
     */
    protected static String sanitizeFilename(String name) {
        return name.replaceAll("[:\\\\/*?|<>]", "_");
    }

    /**
     * Get the full path (starting from the root folder) of the generated image
     * for a diagram.
     * 
     * @param diagram
     *            the diagram from which we want to create an image.
     * @param rootPath
     *            the project root path from which document generation has been launched.
     * @return Full path starting from root folder
     */
    protected String getDiagramImageFilename(DDiagram diagram, String rootPath) {
        return rootPath + "/.generated/images/representations/diagram_"
            + sanitizeFilename(diagram.eResource().getURIFragment(diagram)) + "." + ImageFileFormat.JPEG.getName();
    }

    /**
     * Get the GMF Diagram instance corresponding to a viewpoint
     * DDiagram.
     * 
     * @param semanticDiagram
     *            the diagram object from which we want to create an image.
     * @return the GMF Diagram instance corresponding to a viewpoint
     *         DDiagram.
     */
    protected Diagram getGmfDiagram(DDiagram semanticDiagram) {
        for (final AnnotationEntry annotation : new DDiagramQuery(semanticDiagram)
                .getAnnotation(CustomDataConstants.GMF_DIAGRAMS)) {
            EObject eObject = annotation.getData();
            if (eObject instanceof Diagram) {
                final Diagram diagramInResource = (Diagram) eObject;
                final EObject semanticElement = ViewUtil.resolveSemanticElement(diagramInResource);
                if (semanticElement.equals(semanticDiagram)) {
                    return diagramInResource;
                }
            }
        }
        return null;
    }

    /**
     * If layers is empty return diagram else return diagram copy with applied layers.
     * 
     * @param diagram
     *            DDiagram
     * @param layers
     *            List<Layer>
     * @param session
     *            Session
     * @param isDiagramOpened
     *            boolean
     * @return DDiagram
     */
    protected DDiagram getDDiagramToExport(final DDiagram diagram, final List<Layer> layers, final Session session,
            boolean isDiagramOpened) {
        // No refresh if no layers case and no boolean refresh at true
        if (layers.isEmpty() && !refreshRepresentations) {
            return diagram;
        }

        // Enable GMF notation model canonical refresh in pre-commit
        // called here to be notified before the DiagramEventBroker
        SiriusDiagramSessionEventBroker.getInstance(session);

        // create GMFDiagramUpdater
        GMFDiagramUpdater gmfDiagramUpdater = null;
        if (!isDiagramOpened) {
            gmfDiagramUpdater = new GMFDiagramUpdater(session, diagram);
        }

        ExportRepresentationCommand exportRepresentationCommand = new ExportRepresentationCommand(
                session.getTransactionalEditingDomain(), layers, diagram, session, isDiagramOpened,
                refreshRepresentations);
        session.getTransactionalEditingDomain().getCommandStack().execute(exportRepresentationCommand);

        // remove GMFDiagramUpdater
        if (gmfDiagramUpdater != null) {
            gmfDiagramUpdater.dispose();
        }
        return exportRepresentationCommand.getExportedDiagram();
    }

    /**
     * Generates images corresponding to the given representation and returns their paths into a list.
     * 
     * @param rootPath
     *            the path of the project were to generate images.
     * @param session
     *            the Sirius session containing the representations from which we generate images.
     * @param representations
     *            all the representations from which to generate the corresponding images.
     * @param layers
     *            layers activated on teh representations.
     * @return all images paths corresponding to the given representations.
     * @throws ProviderException
     *             if the image generation fails.
     */
    protected List<String> generateAndReturnDiagramImages(String rootPath, final Session session,
            List<DRepresentation> representations, List<Layer> layers) throws ProviderException {
        List<String> resultList = new ArrayList<String>();
        boolean isSessionDirtyBeforeExport = SessionStatus.DIRTY.equals(session.getStatus());
        for (DRepresentation dRepresentation : representations) {
            if (dRepresentation instanceof DDiagram) {
                final DDiagram dsd = (DDiagram) dRepresentation;
                DDiagram diagramtoExport = getDDiagramToExport(dsd, layers, session, getEditor(session, dsd) != null);
                String filePath = getDiagramImageFilename(diagramtoExport, rootPath);
                File file = new File(filePath);
                file.getParentFile().mkdirs();
                final IPath path = new Path(filePath);
                final Diagram gmfDiagram = getGmfDiagram(diagramtoExport);

                // Use the DiagramEditPartService to use the figure validation
                // infinite loop safe ViewpointDiagramGraphicalViewer.
                // redefine sirius DiagramEditPartService to get image size.
                // CHECKSTYLE:OFF
                final CopyToImageUtil imageUtility = new DiagramEditPartService() {

                    @SuppressWarnings({"rawtypes", "unchecked" })
                    @Override
                    public List copyToImage(Diagram diagram, IPath destination, ImageFileFormat format,
                            IProgressMonitor monitor, PreferencesHint preferencesHint) throws CoreException {

                        List partInfo = Collections.EMPTY_LIST;

                        DiagramEditor openedDiagramEditor = DiagramEditorUtil
                                .findOpenedDiagramEditorForID(ViewUtil.getIdStr(diagram));
                        if (openedDiagramEditor != null) {
                            DiagramGenerator generator = copyToImage(openedDiagramEditor.getDiagramEditPart(),
                                    destination, format, monitor);
                            partInfo = generator.getDiagramPartInfo(openedDiagramEditor.getDiagramEditPart());
                            // begin added code
                            Rectangle rectangle = generator.calculateImageRectangle(
                                    openedDiagramEditor.getDiagramEditPart().getPrimaryEditParts());
                            setHeight(rectangle.height);
                            setWidth(rectangle.width);
                            // end added code
                        } else {

                            Shell shell = new Shell();
                            try {
                                DiagramEditPart diagramEditPart = createDiagramEditPart(diagram, shell,
                                        preferencesHint);
                                SiriusCanonicalLayoutHandler.launchSynchroneArrangeCommand(diagramEditPart);
                                diagramEditPart.getViewer().flush();
                                Assert.isNotNull(diagramEditPart);
                                DiagramGenerator generator = copyToImage(diagramEditPart, destination, format, monitor);
                                partInfo = generator.getDiagramPartInfo(diagramEditPart);

                                Dimension size = DiagramImageUtils.calculateImageRectangle(
                                        diagramEditPart.getPrimaryEditParts(), 0.0, new Dimension(100, 100)).getSize();
                                // begin added code
                                setHeight(size.height);
                                setWidth(size.width);
                                // end added code
                            } finally {
                                shell.dispose();
                            }
                        }

                        return partInfo;
                    }

                };
                // CHECKSTYLE:ON

                final EditingDomain editingDomain = session.getTransactionalEditingDomain();
                final Diagram realOne = (Diagram) editingDomain.getResourceSet()
                        .getEObject(EcoreUtil.getURI(gmfDiagram), true);
                try {
                    imageUtility.copyToImage(realOne, path, ImageFileFormat.JPEG, new NullProgressMonitor(),
                            PreferencesHint.USE_DEFAULTS);
                    resultList.add(filePath);

                    // remove representation copy if needed
                    if (!diagramtoExport.equals(dsd)) {
                        session.getTransactionalEditingDomain().getCommandStack().undo();
                    }
                } catch (CoreException e) {
                    throw new ProviderException("Image creation from diagram '" + dRepresentation.getName()
                        + "' to the file '" + filePath + "' failed.", e);
                }
            }
        }
        // save session if not dirty before diagram export
        if (!isSessionDirtyBeforeExport) {
            session.save(new NullProgressMonitor());
        }
        return resultList;
    }

    /**
     * Return opened representation.
     * 
     * @param session
     *            Session
     * @param representation
     *            DRepresentation
     * @return opened representation.
     */
    protected DialectEditor getEditor(final Session session, final DRepresentation representation) {
        IEditingSession ui = SessionUIManager.INSTANCE.getUISession(session);
        if (ui != null) {
            return ui.getEditor(representation);
        }
        return null;
    }

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

        final Collection<Layer> layers = new ArrayList<Layer>();
        if (description.getDefaultLayer() != null) {
            layers.add(description.getDefaultLayer());
        }
        layers.addAll(description.getAdditionalLayers());
        return new EcoreEList.UnmodifiableEList<Layer>((InternalEObject) description,
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

}
