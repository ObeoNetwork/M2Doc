package org.obeonetwork.m2doc.sirius.session;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

import org.obeonetwork.m2doc.genconf.Generation;

/**
 * Register of cleaning jobs.
 * 
 * @author Romain Guider
 */
public class CleaningJobRegistry {
    /**
     * the singleton instance of {@link CleaningJobRegistry}.
     */
    public static final CleaningJobRegistry INSTANCE = new CleaningJobRegistry();
    /**
     * The map where jobs are stored.
     */
    private Map<Generation, List<ICleaningJob>> jobs;

    /**
     * hiden constructor.
     */
    protected CleaningJobRegistry() {
        jobs = Maps.newHashMap();
    }

    /**
     * Registers a job on a generation.
     * 
     * @param generation
     *            the generation
     * @param job
     *            the job
     */
    public void registerJob(Generation generation, ICleaningJob job) {
        List<ICleaningJob> genJobs = jobs.get(generation);
        if (genJobs == null) {
            genJobs = Lists.newArrayList();
            jobs.put(generation, genJobs);
        }
        genJobs.add(job);
    }

    /**
     * Clears all the jobs associated to a generation.
     * 
     * @param generation
     *            the generation.
     */
    public void clean(Generation generation) {
        List<ICleaningJob> genJobs = jobs.get(generation);
        if (genJobs != null) {
            for (ICleaningJob job : genJobs) {
                job.doClean();
            }
        }
    }

}
