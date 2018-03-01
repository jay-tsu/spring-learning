package com.emc.mystic.task;

import com.emc.mystic.model.JobBean;
import com.emc.mystic.model.LogBundleBean;
import com.emc.mystic.repository.JobDao;
import com.emc.mystic.repository.LogBundleDao;
import com.emc.mystic.rest.controller.impl.ClusterController;
import com.emc.mystic.service.exception.LogBundleServiceException;
import com.emc.mystic.service.impl.LogBundleServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import static java.lang.Thread.sleep;

@Component
public class JobCleanupTask extends AbstractTask {
    private static final Logger logger = LogManager.getLogger(ClusterController.class);


    @Autowired
    private JobDao jobDao;

    @Override
    public void run() {
        while (true) {
            Instant now = Instant.now();

            List<JobBean> jobs = jobDao.findAll();

            jobs.stream()
                .filter(job -> {
                    Instant givenDate = Instant.parse(job.getEndtime());

//                    long secondsIn30Days = 30 * 24 * 60 * 60;
                    long seconds = 5;
                    return now.minusSeconds(seconds)
                              .compareTo(givenDate) > 0;
                })
                .forEach(job -> {
                    jobDao.delete(job);
                });
        }
    }


}
