package com.emc.mystic.service;

import com.emc.mystic.model.JobBean;
import com.emc.mystic.model.LogBundleBean;
import com.emc.mystic.service.exception.LogBundleServiceException;

import java.util.List;
import java.util.UUID;

public interface LogBundleService {
    LogBundleBean getLogBundle(final UUID id) throws LogBundleServiceException;
    List<LogBundleBean> getLogBundles() throws LogBundleServiceException;
    JobBean createLogBundleAsync(LogBundleBean logBundle) throws LogBundleServiceException;
}
