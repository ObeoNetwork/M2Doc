package org.obeonetwork.m2doc.sirius.services;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.ProviderException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DRepresentationDescriptorQuery;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.LayerHelper;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.ui.internal.refresh.listeners.GMFDiagramUpdater;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.obeonetwork.m2doc.sirius.commands.PrepareDiagramCommand;

/**
 * Utility class to be used by the various classes that access the Sirius session.
 * 
 * @author Romain Guider
 */
@SuppressWarnings("restriction")
public final class SiriusRepresentationUtils {

    /**
     * The getAllLayer() method name.
     */
    private static final String GET_ALL_LAYERS = "getAllLayers";
    /**
     * "Sirius compatibility failed" message.
     */
    private static final String SIRIUS_COMPATIBILITY_FAILED = "Sirius compatibility failed.";

    /**
     * Constructor.
     */
    private SiriusRepresentationUtils() {
        // nothing to do here
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
    public static DDiagram getDDiagramToExport(final DDiagram diagram, boolean refreshRepresentations,
            final List<Layer> layers, final Session session, boolean isDiagramOpened) {
        // No refresh if no layers case and no boolean refresh at true
        if (layers.isEmpty() && !refreshRepresentations) {
            return diagram;
        }

        // Enable GMF notation model canonical refresh in pre-commit
        // called here to be notified before the DiagramEventBroker
        siriusDiagramSessionEventBrokerGetInstance(session);

        // create GMFDiagramUpdater
        GMFDiagramUpdater gmfDiagramUpdater = null;
        if (!isDiagramOpened) {
            gmfDiagramUpdater = new GMFDiagramUpdater(session, diagram);
        }

        PrepareDiagramCommand exportRepresentationCommand = new PrepareDiagramCommand(
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
     * Compatibility for changes between Sirius 6.1.1 and 6.1.2.
     * 
     * @param session
     *            the {@link Session}
     */
    private static void siriusDiagramSessionEventBrokerGetInstance(Session session) {
        Class<?> cls;
        try {
            cls = SiriusRepresentationUtils.class.getClassLoader()
                    .loadClass("org.eclipse.sirius.diagram.ui.internal.refresh.SiriusDiagramSessionEventBroker");
        } catch (ClassNotFoundException e) {
            try {
                cls = SiriusRepresentationUtils.class.getClassLoader().loadClass(
                        "org.eclipse.sirius.diagram.business.internal.refresh.SiriusDiagramSessionEventBroker");
            } catch (ClassNotFoundException e1) {
                throw new IllegalStateException(
                        "Sirius compatibility failed: can't find SiriusDiagramSessionEventBroker.");
            }
        }
        try {
            final Method method = cls.getMethod("getInstance", Session.class);
            method.invoke(null, session);

        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            throw new IllegalStateException(SIRIUS_COMPATIBILITY_FAILED, e);
        }
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
    public static DialectEditor getEditor(final Session session, final DRepresentation representation) {
        IEditingSession ui = SessionUIManager.INSTANCE.getUISession(session);
        if (ui != null) {
            return ui.getEditor(representation);
        }
        return null;
    }

    /**
     * Gets the {@link List} of {@link DRepresentation} with the given {@link RepresentationDescription#getName() description name} for the
     * given {@link EObject}.
     * 
     * @param session
     *            the {@link Session}
     * @param eObj
     *            the {@link EObject}
     * @param descriptionName
     *            the {@link RepresentationDescription#getName() description name}
     * @return the {@link List} of {@link DRepresentation} with the given description name for the given {@link EObject}
     */
    public static List<DRepresentation> getRepresentationByRepresentationDescriptionName(Session session, EObject eObj,
            String descriptionName) {
        final List<DRepresentation> res = new ArrayList<>();

        final Collection<DRepresentationDescriptor> repDescs = DialectManager.INSTANCE
                .getRepresentationDescriptors(eObj, session);
        // Filter representations to keep only those in a selected viewpoint
        final Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);

        for (DRepresentationDescriptor repDesc : repDescs) {
            boolean isDangling = new DRepresentationDescriptorQuery(repDesc).isDangling();
            if (!isDangling && repDesc.getDescription() instanceof DiagramDescription
                && descriptionName.equals(repDesc.getDescription().getName())
                && repDesc.getDescription().eContainer() instanceof Viewpoint) {
                Viewpoint vp = (Viewpoint) repDesc.getDescription().eContainer();
                if (selectedViewpoints.contains(vp)) {
                    res.add(repDesc.getRepresentation());
                }
            }
        }

        return res;
    }

    /**
     * Gets the {@link List} of all {@link Layer} for the given {@link DiagramDescription}. This survive API changes in Sirius 6.2.2.
     * 
     * @param description
     *            the {@link DiagramDescription}
     * @return the {@link List} of all {@link Layer} for the given {@link DiagramDescription}
     */
    @SuppressWarnings("unchecked")
    public static List<Layer> getAllLayer(DiagramDescription description) {
        final List<Layer> res = new ArrayList<>();

        try {
            final Method method = DiagramDescription.class.getMethod(GET_ALL_LAYERS);
            res.addAll((List<Layer>) method.invoke(description));
        } catch (NoSuchMethodException | SecurityException e) {
            try {
                final Method method = LayerHelper.class.getMethod(GET_ALL_LAYERS, DiagramDescription.class);
                res.addAll((List<Layer>) method.invoke(null, description));
            } catch (NoSuchMethodException | SecurityException e1) {
                // For Sirius 7.x.x
                try {
                    final Class<?> cls = SiriusRepresentationUtils.class.getClassLoader().loadClass(
                            "org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager");
                    final Object diagramComponentizationManager = cls.getConstructor(new Class<?>[] {}).newInstance();
                    final Method method = cls.getMethod(GET_ALL_LAYERS, Collection.class, DiagramDescription.class);
                    final Collection<Viewpoint> selectedViewpoints = new EObjectQuery(description).getSession()
                            .getSelectedViewpoints(false);
                    res.addAll((List<Layer>) method.invoke(diagramComponentizationManager, selectedViewpoints,
                            description));
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                        | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                        | SecurityException e2) {
                    throw new IllegalStateException(SIRIUS_COMPATIBILITY_FAILED, e2);
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
                throw new IllegalStateException(SIRIUS_COMPATIBILITY_FAILED, e1);
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new IllegalStateException(SIRIUS_COMPATIBILITY_FAILED, e);
        }

        return res;
    }

}
