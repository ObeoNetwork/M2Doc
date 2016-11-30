package org.obeonetwork.m2doc.sirius.services;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;

/**
 * AQL Services for sirius representations.
 * 
 * @author Romain Guider
 */
public class M2DocSiriusServices {
    /**
     * Returns <code>true</code> if the arguments are not null, the eObject is in a session and there's a representation with the specified
     * name in it that is associated to the specified eObject. Returns <code>false</code> otherwise.
     * 
     * @param representationDescriptionName
     *            the name of the searched representation
     * @param eObject
     *            the eObject
     * @return <code>true</code> if there's a representation with the specified name in the eObject's associated session.
     */
    public boolean diagramExistsOnEObject(EObject eObject, String representationDescriptionName) {
        boolean result = false;
        if (representationDescriptionName != null || eObject != null) {
            Session session = SessionManager.INSTANCE.getSession(eObject);
            if (session != null) {
                result = new SiriusServices().getAssociatedRepresentationByDiagramDescriptionAndName(eObject,
                        representationDescriptionName, session).size() > 0;
            }
        }
        return result;
    }

    /**
     * * Returns <code>true</code> if the arguments are not null, the eObject is in a session and there's a representation with the
     * specified
     * title in it. Returns <code>false</code> otherwise.
     * 
     * @param representationName
     *            the name of the searched representation.
     * @param eObject
     *            any eObject that is in the session where to search
     * @return <code>true</code> if there's a representation with the specified name in the session associated to the specified eObject.
     */
    public boolean diagramWithNameExists(EObject eObject, String representationName) {
        boolean result = false;
        if (representationName != null && eObject != null) {
            Session session = SessionManager.INSTANCE.getSession(eObject);
            if (session != null) {
                result = new SiriusServices().getAssociatedRepresentationByName(representationName, session) != null;
            }
        }
        return result;
    }

}
