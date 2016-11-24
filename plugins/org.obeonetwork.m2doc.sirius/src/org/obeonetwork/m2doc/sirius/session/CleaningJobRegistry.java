package org.obeonetwork.m2doc.sirius.session;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;

import org.obeonetwork.m2doc.genconf.Generation;

/**
 * Registry of cleaning jobs.
 * 
 * @author Romain Guider
 */
public class CleaningJobRegistry {
    /**
     * the singleton instance of {@link CleaningJobRegistry}.
     */
    public static final CleaningJobRegistry INSTANCE = new CleaningJobRegistry();
    /**
     * The multi map that contains the cleaning jobs executed post generation.
     */

    private Multimap<Generation, Runnable> jobs = ArrayListMultimap.create();

    /**
     * hidden constructor.
     */
    protected CleaningJobRegistry() {
    }

    /**
     * Registers a job on a generation.
     * 
     * @param generation
     *            the generation
     * @param job
     *            the job
     */
    public void registerJob(Generation generation, Runnable job) {
        jobs.put(generation, job);
    }

    /**
     * Clears all the jobs associated to a generation.
     * 
     * @param generation
     *            the generation.
     */
    public void clean(Generation generation) {
        try {
            Collection<Runnable> genJobs = jobs.get(generation);
            if (genJobs != null) {
                for (Runnable job : genJobs) {
                    job.run();
                }
            }
        } finally {
            jobs.removeAll(generation);
        }
    }

}
