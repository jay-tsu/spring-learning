package com.emc.mystic.service.impl;

import com.emc.mystic.model.JobBean;
import com.emc.mystic.model.LogBundleBean;
import com.emc.mystic.repository.LogBundleDao;
import com.emc.mystic.service.JobService;
import com.emc.mystic.service.LogBundleService;
import com.emc.mystic.service.exception.LogBundleServiceException;
import com.emc.mystic.task.LogBundleCreationTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class LogBundleServiceImpl implements LogBundleService {
    @Autowired
    private LogBundleDao logBundleDao;

    @Autowired
    private JobService jobService;

    @Autowired
    private LogBundleCreationTask logBundleCreationTask;

    public enum SupportedLogBundleType {
        iDRAC,
        VxManager
    }

    @Override
    public LogBundleBean getLogBundle(final UUID id) throws LogBundleServiceException {
        LogBundleBean logBundleBean = logBundleDao.findOne(id);
        if (logBundleBean == null) {
            throw new LogBundleServiceException(String.format("Can not find the log bundle by id %s", id), 1);
        } else {
            return logBundleBean;
        }
    }

    @Override
    public List<LogBundleBean> getLogBundles() throws LogBundleServiceException {
        return logBundleDao.findAll();
    }

    @Override
    public JobBean createLogBundleAsync(LogBundleBean logBundle)
            throws LogBundleServiceException {
        return jobService.execute(logBundleCreationTask.setLogBundle(logBundle));
    }
}