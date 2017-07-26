package org.obeonetwork.m2doc.sirius.services;

import com.google.common.collect.Lists;

import java.io.File;
import java.security.ProviderException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.business.api.query.DRepresentationDescriptorQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.ui.internal.refresh.SiriusDiagramSessionEventBroker;
import org.eclipse.sirius.diagram.ui.internal.refresh.listeners.GMFDiagramUpdater;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ExportDocumentFormat;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.tools.api.actions.export.SizeTooLargeException;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Display;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.sirius.commands.ExportRepresentationCommand;
import org.obeonetwork.m2doc.sirius.session.CleaningAIRDJob;
import org.obeonetwork.m2doc.sirius.session.CleaningJobRegistry;

/**
 * Utility class to be used by the various classes that access the Sirius session.
 * 
 * @author Romain Guider
 */
@SuppressWarnings("restriction")
public final class SiriusRepresentationUtils {

    /**
     * Constructor.
     */
    private SiriusRepresentationUtils() {
        // nothing to do here
    }

    /**
     * Retrieve all representations whose target is the specified EObject and description the given one.
     * 
     * @param targetRootObject
     *            Object which is the target of the representations.
     * @param representationId
     *            the diagram description from which we want to retrieve representations.
     * @param session
     *            the Sirius session from which we want to find the representation with the given name.
     * @return all representations whose target is the specified EObject
     */
    public static List<DRepresentationDescriptor> getAssociatedRepresentationByDescriptionAndName(
            EObject targetRootObject, String representationId, Session session) {
        return getAssociatedRepresentationByDescriptionAndName(null, targetRootObject, representationId, session,
                false);
    }

    /**
     * Retrieve all representations whose target is the specified EObject and description the given one. If no representation is
     * found and createIfAbsent is <code>true</code> the create the representation.
     * 
     * @param generation
     *            the generation configuration object this generation has been launched on.
     * @param targetRootObject
     *            Object which is the target of the representations.
     * @param representationId
     *            the description from which we want to retrieve representations.
     * @param session
     *            the Sirius session from which we want to find the representation with the given name.
     * @param createIfAbsent
     *            if <code>true</code> and the generation object is present, the representations are created on the fly and cleaned after
     *            doc generation.
     * @return all representations whose target is the specified EObject
     */
    public static List<DRepresentationDescriptor> getAssociatedRepresentationByDescriptionAndName(Generation generation,
            EObject targetRootObject, String representationId, Session session, boolean createIfAbsent) {
        List<DRepresentationDescriptor> result = new ArrayList<>();
        if (representationId != null && targetRootObject != null && session != null) {
            Collection<DRepresentationDescriptor> representations = DialectManager.INSTANCE
                    .getRepresentationDescriptors(targetRootObject, session);
            // Filter representations to keep only those in a selected viewpoint
            Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);

            for (DRepresentationDescriptor representation : representations) {
                boolean isDangling = new DRepresentationDescriptorQuery(representation).isDangling();
                if (!isDangling && representationId.equals(representation.getDescription().getName())
                    && representation.getDescription().eContainer() instanceof Viewpoint) {
                    Viewpoint vp = (Viewpoint) representation.getDescription().eContainer();
                    if (selectedViewpoints.contains(vp)) {
                        result.add(representation);
                    }
                }
            }
        }
        if (generation != null && result.isEmpty() && createIfAbsent) {
            RepresentationDescription description = findDescription(session, representationId);
            session.getTransactionalEditingDomain().getCommandStack().execute(new CreateRepresentationCommand(session,
                    description, targetRootObject, representationId, new NullProgressMonitor()));
            for (DRepresentationDescriptor representation : DialectManager.INSTANCE
                    .getRepresentationDescriptors(targetRootObject, session)) {
                if (representation != null && representation.getDescription() == description) {
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
     * Gets the {@link RepresentationDescription} with the given {@link RepresentationDescription#getName() description name}.
     * 
     * @param session
     *            the {@link Session}
     * @param descriptionName
     *            the {@link RepresentationDescription#getName() description name}
     * @return the {@link RepresentationDescription} with the given {@link RepresentationDescription#getName() description name} if
     *         any found, <code>null</code> otherwise
     * @throws ProviderException
     *             if the specified representation doesn't exist.
     */
    public static RepresentationDescription findDescription(Session session, String descriptionName) {
        Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);
        for (Viewpoint viewpoint : selectedViewpoints) {
            EList<RepresentationDescription> ownedRepresentations = viewpoint.getOwnedRepresentations();
            for (RepresentationDescription representationDescription : ownedRepresentations) {
                if (descriptionName.equals(representationDescription.getName())) {
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
     * @param format
     *            the {@link ImageFileFormat}
     * @param rootPath
     *            the project root path from which document generation has been launched.
     * @return Full path starting from root folder
     */
    private static String getDiagramImageFileName(DDiagram diagram, ImageFileFormat format, String rootPath) {
        return rootPath + "/.generated/images/representations/diagram_"
            + sanitizeFilename(diagram.eResource().getURIFragment(diagram)) + "." + format.getName();
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
     * @param refreshRepresentations
     *            should we refresh the representations
     * @param representations
     *            all the representations from which to generate the corresponding images.
     * @param layers
     *            layers activated on the representations.
     * @return all images paths corresponding to the given representations.
     */
    public static List<String> generateAndReturnDiagramImages(String rootPath, final Session session,
            boolean refreshRepresentations, List<DRepresentationDescriptor> representations, List<Layer> layers) {
        List<String> resultList = new ArrayList<>();
        boolean isSessionDirtyBeforeExport = SessionStatus.DIRTY.equals(session.getStatus());
        for (DRepresentationDescriptor descriptor : representations) {
            DRepresentation dRepresentation = descriptor.getRepresentation();
            if (dRepresentation instanceof DDiagram) {
                final DDiagram dsd = (DDiagram) dRepresentation;
                final DDiagram diagramtoExport = getDDiagramToExport(dsd, refreshRepresentations, layers, session,
                        getEditor(session, dsd) != null);
                final ImageFileFormat format = ImageFileFormat.JPG;
                String filePath = getDiagramImageFileName(diagramtoExport, format, rootPath);
                File file = new File(filePath);
                file.getParentFile().mkdirs();
                final IPath path = new Path(filePath);
                try {
                    Runnable exportDiagUnitOfWork = new Runnable() {

                        @Override
                        public void run() {
                            try {
                                DialectUIManager.INSTANCE.export(diagramtoExport, session, path,
                                        new ExportFormat(ExportDocumentFormat.NONE,
                                                org.eclipse.sirius.common.tools.api.resource.ImageFileFormat.JPG),
                                        new NullProgressMonitor());
                            } catch (SizeTooLargeException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    };
                    if (Display.getDefault() != null) {
                        Display.getDefault().syncExec(exportDiagUnitOfWork);
                    } else {
                        exportDiagUnitOfWork.run();
                    }
                    resultList.add(filePath);

                    // remove representation copy if needed
                    if (!diagramtoExport.equals(dsd)) {
                        session.getTransactionalEditingDomain().getCommandStack().undo();
                    }
                    // CHECKSTYLE:OFF introducing a dedicated runtime exception just for this case seems overkill
                } catch (RuntimeException e) {
                    // CHECKSTYLE:ON
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
