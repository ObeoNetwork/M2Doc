package org.obeonetwork.m2doc.sirius.session;

import com.google.common.collect.Sets;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.command.DeleteRepresentationCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * A {@link CleaningAIRDJob} contains the necessary information to clean a representation after generation.
 * 
 * @author Romain Guider
 */
public class CleaningAIRDJob implements Runnable {

    /**
     * The semantic object associated with the representation.
     */
    private final EObject semantic;
    /**
     * The session where the representation has been created and must be deleted.
     */
    private final Session session;
    /**
     * The representation that must be cleaned.
     */
    private final DRepresentation representation;

    /**
     * Create a new {@link CleaningAIRDJob} instance given a semantic object a session and a representation.
     * 
     * @param semantic
     *            the semantic object
     * @param session
     *            the session.
     * @param representation
     *            the representation to delete.
     */
    public CleaningAIRDJob(EObject semantic, Session session, DRepresentation representation) {
        if (semantic == null || session == null || representation == null) {
            throw new IllegalArgumentException("a null argument has been passed to the CleaningAIRDJob constructor");
        }
        this.semantic = semantic;
        this.session = session;
        this.representation = representation;
    }

    /**
     * delete the representation.
     */
    @Override
    public void run() {
        session.getTransactionalEditingDomain().getCommandStack()
                .execute(new DeleteRepresentationCommand(session, Sets.newHashSet(representation)));
    }

}
