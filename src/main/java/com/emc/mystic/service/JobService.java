package com.emc.mystic.service;

import com.emc.mystic.model.JobBean;
import com.emc.mystic.task.AbstractTask;

import java.util.List;
import java.util.UUID;

public interface JobService {
    JobBean execute(AbstractTask task);

    JobBean getJob(final UUID id);
    List<JobBean> getAllJobs();
}
