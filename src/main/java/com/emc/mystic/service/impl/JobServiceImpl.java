package com.emc.mystic.service.impl;

import com.emc.mystic.model.JobBean;
import com.emc.mystic.repository.JobDao;
import com.emc.mystic.service.JobService;
import com.emc.mystic.service.exception.JobServiceException;
import com.emc.mystic.task.AbstractTask;
import com.emc.mystic.task.JobCleanupTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private JobDao jobDao;

    @Autowired
    private JobCleanupTask jobCleanupTask;

    @Override
    public JobBean execute(AbstractTask task) {
        task.setJob(createNewJob());
        taskExecutor.submit(task);
        return task.getJob();
    }

    @Override
    public JobBean getJob(final UUID id) throws JobServiceException {
        JobBean jobBean = jobDao.findOne(id);
        if (jobBean == null) {
            throw new JobServiceException(String.format("Can not find the job by id %s", id), 1);
        }

        return jobBean;
    }

    @Override
    public List<JobBean> getAllJobs() {
        return jobDao.findAll();
    }

    private JobBean createNewJob() {
        JobBean job = new JobBean();
        job.setId(UUID.randomUUID());
        job.setStarttime(Timestamp.from(Instant.now()));
        job.setState(AbstractTask.JobStatus.STARTED.toString());
        job.setProgress(0);

        return job;
    }

//    @Async
//    public  void cleanupJob() {
//        taskExecutor.submit(jobCleanupTask);
//    }
}
