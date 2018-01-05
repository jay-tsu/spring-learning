package com.emc.mystic.service;

import com.emc.mystic.model.ClusterBean;
import com.emc.mystic.service.exception.ClusterServiceException;


public interface ClusterService {
    ClusterBean getCluster(final Long id) throws ClusterServiceException;
}
