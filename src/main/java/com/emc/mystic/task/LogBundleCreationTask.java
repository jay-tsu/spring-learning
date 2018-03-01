package com.emc.mystic.task;

import com.emc.mystic.model.LogBundleBean;
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
import java.util.List;
import java.util.UUID;

import static java.lang.Thread.sleep;

@Component
public class LogBundleCreationTask extends AbstractTask {
    private static final Logger logger = LogManager.getLogger(ClusterController.class);

    @Autowired
    private LogBundleDao logBundleDao;

    private LogBundleBean logBundle;

    public LogBundleCreationTask setLogBundle(LogBundleBean logBundle) {
        this.logBundle = logBundle;
        return this;
    }

    @Override
    public void run() {
        LogBundleTypeValidation(logBundle.getTypes());

        logBundle.setId(UUID.randomUUID());

        if (logBundle.getName() == null) {
            logBundle.setName(generateLogBundleName(logBundle.getTypes()));
        }

        job.setDescription("A job to create a log bundle");
        job.setOwner("LogBundleService");

        job.setTarget(String.format("/support/logs/%s", logBundle.getId()));
        updateJob(job);

        try {
            sleep(100);
            logBundle.setSize(10000);
            logBundle.setCreationtime(Timestamp.from(Instant.now()));
            logBundleDao.saveAndFlush(logBundle);

            job.setState(JobStatus.COMPLETED.toString());
        } catch (InterruptedException e) {
            // Just simply ignore the exception from the function sleep().
            job.setState(JobStatus.FAILED.toString());
        }

        job.setEndtime(Timestamp.from(Instant.now()));
        job.setProgress(100);
        updateJob(job);
    }

    private void LogBundleTypeValidation(List<String> types) throws LogBundleServiceException {
        if (types == null) {
            throw new LogBundleServiceException("Log bundle type is missing.");
        }

        try {
            types.forEach(LogBundleServiceImpl.SupportedLogBundleType::valueOf);
        } catch (IllegalArgumentException ex) {
            int err_code = LogBundleServiceException.ErrorCode.INVLIAD_LOG_BUNDLE_TYPE.getValue();

            String message = ex.getMessage();
            String type = message.substring(message.lastIndexOf(".") + 1);

            throw new LogBundleServiceException(String.format("Invalid log bundle type %s.", type), err_code, type);
        }
    }

    private String generateLogBundleName(List<String> types) {
        String type = String.join("_", types);
        return String.join("_", "log_bundle", type, Instant.now()
                                                           .toString()) + ".zip";
    }
}
