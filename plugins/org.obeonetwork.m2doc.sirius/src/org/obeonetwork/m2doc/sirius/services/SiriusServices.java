package org.obeonetwork.m2doc.sirius.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

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
     * @param diagramDescriptionName
     *            the diagram description from which we want to retrieve representations.
     * @param session
     *            the Sirius session from which we want to find the representation with the given name.
     * @return all representations whose target is the specified EObject
     */
    public List<DRepresentation> getAssociatedRepresentationByDiagramDescriptionAndName(EObject targetRootObject,
            String diagramDescriptionName, Session session) {
        List<DRepresentation> result = new ArrayList<DRepresentation>();
        if (diagramDescriptionName != null) {
            Collection<DRepresentation> representations = DialectManager.INSTANCE.getRepresentations(targetRootObject,
                    session);

            // Filter representations to keep only those in a selected viewpoint
            Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);

            for (DRepresentation representation : representations) {
                if (representation instanceof DDiagram
                    && diagramDescriptionName.equals(((DDiagram) representation).getDescription().getName())
                    && representation.eContainer() instanceof DView) {
                    DView dView = (DView) representation.eContainer();
                    Viewpoint vp = dView.getViewpoint();
                    if (selectedViewpoints.contains(vp)) {
                        result.add(representation);
                    }
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
                if (representationName.equals(representation.getName())
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

}
