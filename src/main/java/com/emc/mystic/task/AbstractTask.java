package com.emc.mystic.task;

import com.emc.mystic.model.JobBean;
import com.emc.mystic.repository.JobDao;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractTask implements Runnable {
    JobBean job;

    @Autowired
    JobDao jobDao;

    public enum JobStatus {
        QUEUE,
        STARTED,
        IN_PROGRESS,
        SUSPENDED,
        COMPLETED,
        FAILED
    }

    public JobBean getJob() {
        return job;
    }

    public void setJob(JobBean job) {
        this.job = job;
    }

    void updateJob(JobBean job) {
        jobDao.saveAndFlush(job);
    }
}
