package org.obeonetwork.m2doc.sirius.session;

import org.eclipse.sirius.business.api.session.Session;

/**
 * This interface provides a common API to all the cleanup jobs that must be executed after a generation finished.
 * 
 * @author Romain Guider
 */
public interface ICleaningJob {
    /**
     * Triggers the cleaning job.
     */
    void doClean();

    /**
     * Gets the Sirius session in which the Job has been created.
     * 
     * @return the Sirius session in which the Job has been created
     */
    Session getSession();
}
