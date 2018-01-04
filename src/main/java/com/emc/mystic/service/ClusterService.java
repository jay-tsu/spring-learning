package com.emc.mystic.service;

import com.emc.mystic.model.ClusterBean;


public interface ClusterService {
//    List<Node> getAvailableNodes();

    ClusterBean getCluster(final Long id);
}
