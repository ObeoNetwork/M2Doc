package org.obeonetwork.m2doc.sirius.services;

import com.google.common.collect.Lists;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.gmf.runtime.diagram.ui.render.util.CopyToImageUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.business.api.query.DRepresentationDescriptorQuery;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.ui.internal.refresh.SiriusDiagramSessionEventBroker;
import org.eclipse.sirius.diagram.ui.internal.refresh.listeners.GMFDiagramUpdater;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.provider.ProviderException;
import org.obeonetwork.m2doc.sirius.commands.ExportRepresentationCommand;
import org.obeonetwork.m2doc.sirius.session.CleaningAIRDJob;
import org.obeonetwork.m2doc.sirius.session.CleaningJobRegistry;

/**
 * Utility class to be used by the various classes that access the Sirius session.
 * 
 * @author Romain Guider
 */
public final class SiriusDiagramUtils {

    /**
     * Constructor.
     */
    private SiriusDiagramUtils() {
        // nothing to do here
    }

    /**
     * Retrieve all representations whose target is the specified EObject and diagram description the given one.
     * 
     * @param targetRootObject
     *            Object which is the target of the representations.
     * @param diagramId
     *            the diagram description from which we want to retrieve representations.
     * @param session
     *            the Sirius session from which we want to find the representation with the given name.
     * @return all representations whose target is the specified EObject
     */
    public static List<DRepresentationDescriptor> getAssociatedRepresentationByDiagramDescriptionAndName(
            EObject targetRootObject, String diagramId, Session session) {
        return getAssociatedRepresentationByDiagramDescriptionAndName(null, targetRootObject, diagramId, session,
                false);
    }

    /**
     * Retrieve all representations whose target is the specified EObject and diagram description the given one. If no representation is
     * found and createIfAbsent is <code>true</code> the create the representation.
     * 
     * @param generation
     *            the generation configuration object this generation has been launched on.
     * @param targetRootObject
     *            Object which is the target of the representations.
     * @param diagramId
     *            the diagram description from which we want to retrieve representations.
     * @param session
     *            the Sirius session from which we want to find the representation with the given name.
     * @param createIfAbsent
     *            if <code>true</code> and the generation object is present, the representations are created on the fly and cleaned after
     *            doc generation.
     * @return all representations whose target is the specified EObject
     */
    public static List<DRepresentationDescriptor> getAssociatedRepresentationByDiagramDescriptionAndName(
            Generation generation, EObject targetRootObject, String diagramId, Session session,
            boolean createIfAbsent) {
        List<DRepresentationDescriptor> result = new ArrayList<>();
        if (diagramId != null && targetRootObject != null && session != null) {
            Collection<DRepresentationDescriptor> representations = DialectManager.INSTANCE
                    .getRepresentationDescriptors(targetRootObject, session);
            // Filter representations to keep only those in a selected viewpoint
            Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);

            for (DRepresentationDescriptor representation : representations) {
                boolean isDangling = new DRepresentationDescriptorQuery(representation).isDangling();
                if (!isDangling && representation.getDescription() instanceof DiagramDescription
                    && diagramId.equals(representation.getDescription().getName())
                    && representation.getDescription().eContainer() instanceof Viewpoint) {
                    Viewpoint vp = (Viewpoint) representation.getDescription().eContainer();
                    if (selectedViewpoints.contains(vp)) {
                        result.add(representation);
                    }
                }
            }
        }
        if (generation != null && result.isEmpty() && createIfAbsent) {
            RepresentationDescription description = findDiagramDescription(session, diagramId);
            session.getTransactionalEditingDomain().getCommandStack().execute(new CreateRepresentationCommand(session,
                    description, targetRootObject, diagramId, new NullProgressMonitor()));
            for (DRepresentationDescriptor representation : DialectManager.INSTANCE
                    .getRepresentationDescriptors(targetRootObject, session)) {
                if (representation != null && representation.getDescription() instanceof DiagramDescription
                    && representation.getDescription() == description) {
                    CleaningJobRegistry.INSTANCE.registerJob(generation,
                            new CleaningAIRDJob(targetRootObject, session, representation));
                    result = Lists.newArrayList(representation);
                }
            }

        }
        return result;
    }

    /**
     * Retrieve all representations whose target is the specified EObject.
     * 
     * @param representationName
     *            the name of the representation from which we want to create an image.
     * @param session
     *            the Sirius session from which we want to find the representation with the given name.
     * @return the corresponding representation.
     */
    public static DRepresentationDescriptor getAssociatedRepresentationByName(String representationName,
            Session session) {
        if (representationName != null) {
            Collection<DRepresentationDescriptor> representations = DialectManager.INSTANCE
                    .getAllRepresentationDescriptors(session);

            // Filter representations to keep only those in a selected viewpoint
            Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);

            for (DRepresentationDescriptor representation : representations) {
                boolean isDangling = new DRepresentationDescriptorQuery(representation).isDangling();
                if (!isDangling && representation != null && representationName.equals(representation.getName())
                    && representation.getDescription() instanceof DiagramDescription
                    && representation.getDescription().eContainer() instanceof Viewpoint) {
                    Viewpoint vp = (Viewpoint) representation.getDescription().eContainer();
                    if (selectedViewpoints.contains(vp)) {
                        return representation;
                    }
                }
            }
        }

        return null;
    }

    /**
     * Gets the {@link RepresentationDescription} with the given {@link RepresentationDescription#getName() diagram description name}.
     * 
     * @param session
     *            the {@link Session}
     * @param diagramDescriptionName
     *            the {@link RepresentationDescription#getName() diagram description name}
     * @return the {@link RepresentationDescription} with the given {@link RepresentationDescription#getName() diagram description name} if
     *         any found, <code>null</code> otherwise
     * @throws ProviderException
     *             if the specified representation doesn't exist.
     */
    public static RepresentationDescription findDiagramDescription(Session session, String diagramDescriptionName) {
        Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);
        for (Viewpoint viewpoint : selectedViewpoints) {
            EList<RepresentationDescription> ownedRepresentations = viewpoint.getOwnedRepresentations();
            for (RepresentationDescription representationDescription : ownedRepresentations) {
                if (diagramDescriptionName.equals(representationDescription.getName())) {
                    return representationDescription;
                }
            }
        }
        return null;
    }

    /**
     * Replace forbidden characters with "_" in a filename.
     * 
     * @param name
     *            the string to sanitized.
     * @return sanitized string.
     */
    private static String sanitizeFilename(String name) {
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
    private static String getDiagramImageFilename(DDiagram diagram, String rootPath) {
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
    private static Diagram getGmfDiagram(DDiagram semanticDiagram) {
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
     * @param refreshRepresentations
     *            should we refresh the representations
     * @param layers
     *            List<Layer>
     * @param session
     *            Session
     * @param isDiagramOpened
     *            boolean
     * @return DDiagram
     */
    private static DDiagram getDDiagramToExport(final DDiagram diagram, boolean refreshRepresentations,
            final List<Layer> layers, final Session session, boolean isDiagramOpened) {
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
     * @param imageUtility
     *            the {@link CopyToImageUtil}
     * @param refreshRepresentations
     *            should we refresh the representations
     * @param representations
     *            all the representations from which to generate the corresponding images.
     * @param layers
     *            layers activated on the representations.
     * @return all images paths corresponding to the given representations.
     * @throws ProviderException
     *             if the image generation fails.
     */
    public static List<String> generateAndReturnDiagramImages(String rootPath, final Session session,
            CopyToImageUtil imageUtility, boolean refreshRepresentations,
            List<DRepresentationDescriptor> representations, List<Layer> layers) throws ProviderException {
        List<String> resultList = new ArrayList<>();
        boolean isSessionDirtyBeforeExport = SessionStatus.DIRTY.equals(session.getStatus());
        for (DRepresentationDescriptor descriptor : representations) {
            DRepresentation dRepresentation = descriptor.getRepresentation();
            if (dRepresentation instanceof DDiagram) {
                final DDiagram dsd = (DDiagram) dRepresentation;
                DDiagram diagramtoExport = getDDiagramToExport(dsd, refreshRepresentations, layers, session,
                        getEditor(session, dsd) != null);
                String filePath = getDiagramImageFilename(diagramtoExport, rootPath);
                File file = new File(filePath);
                file.getParentFile().mkdirs();
                final IPath path = new Path(filePath);
                final Diagram gmfDiagram = getGmfDiagram(diagramtoExport);

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
    private static DialectEditor getEditor(final Session session, final DRepresentation representation) {
        IEditingSession ui = SessionUIManager.INSTANCE.getUISession(session);
        if (ui != null) {
            return ui.getEditor(representation);
        }
        return null;
    }

}
