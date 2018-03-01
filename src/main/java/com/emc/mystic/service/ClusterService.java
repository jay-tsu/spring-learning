package com.emc.mystic.service;

import com.emc.mystic.model.ClusterBean;
import com.emc.mystic.model.NodeBean;
import com.emc.mystic.service.exception.ClusterServiceException;

import java.util.List;

public interface ClusterService {
    ClusterBean getCluster(final Long id) throws ClusterServiceException;
    List<NodeBean> getAvailableNodes() throws ClusterServiceException;
}
