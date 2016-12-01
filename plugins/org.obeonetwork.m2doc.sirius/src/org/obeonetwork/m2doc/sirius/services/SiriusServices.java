package org.obeonetwork.m2doc.sirius.services;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.provider.ProviderException;
import org.obeonetwork.m2doc.sirius.session.CleaningAIRDJob;
import org.obeonetwork.m2doc.sirius.session.CleaningJobRegistry;

/**
 * Services class to be used by the various classes that access the Sirius session.
 * 
 * @author Romain Guider
 */
public class SiriusServices {
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
    public List<DRepresentation> getAssociatedRepresentationByDiagramDescriptionAndName(EObject targetRootObject,
            String diagramId, Session session) {
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
    public List<DRepresentation> getAssociatedRepresentationByDiagramDescriptionAndName(Generation generation,
            EObject targetRootObject, String diagramId, Session session, boolean createIfAbsent) {
        List<DRepresentation> result = new ArrayList<DRepresentation>();
        if (diagramId != null && targetRootObject != null && session != null) {
            Collection<DRepresentation> representations = DialectManager.INSTANCE.getRepresentations(targetRootObject,
                    session);
            // Filter representations to keep only those in a selected viewpoint
            Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);

            for (DRepresentation representation : representations) {
                boolean isDangling = new DRepresentationQuery(representation).isDanglingRepresentation();
                if (!isDangling && representation instanceof DDiagram
                    && diagramId.equals(((DDiagram) representation).getDescription().getName())
                    && representation.eContainer() instanceof DView) {
                    DView dView = (DView) representation.eContainer();
                    Viewpoint vp = dView.getViewpoint();
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
            for (DRepresentation representation : DialectManager.INSTANCE.getRepresentations(targetRootObject,
                    session)) {
                if (representation instanceof DDiagram && ((DDiagram) representation).getDescription() == description) {
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
    public DRepresentation getAssociatedRepresentationByName(String representationName, Session session) {
        if (representationName != null) {
            Collection<DRepresentation> representations = DialectManager.INSTANCE.getAllRepresentations(session);

            // Filter representations to keep only those in a selected viewpoint
            Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);

            for (DRepresentation representation : representations) {
                boolean isDangling = new DRepresentationQuery(representation).isDanglingRepresentation();
                if (!isDangling && representationName.equals(representation.getName())
                    && representation.eContainer() instanceof DView) {
                    DView dView = (DView) representation.eContainer();
                    Viewpoint vp = dView.getViewpoint();
                    if (selectedViewpoints.contains(vp)) {
                        return representation;
                    }
                }
            }
        }

        return null;
    }

    /**
     * Returns the specified diagram representation.
     * 
     * @param session
     * @param diagramDescriptionName
     * @return
     * @throws ProviderException
     *             if the specified representation doesn't exist.
     */
    public RepresentationDescription findDiagramDescription(Session session, String diagramDescriptionName) {
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

}
