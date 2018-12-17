package com.emc.mystic.service.impl;

import com.emc.mystic.model.NodeBean;
import com.emc.mystic.repository.ClusterDao;
import com.emc.mystic.model.ClusterBean;
import com.emc.mystic.repository.NodeDao;
import com.emc.mystic.service.ClusterService;
import com.emc.mystic.service.exception.ClusterServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClusterServiceImpl implements ClusterService {
    @Autowired
    private ClusterDao clusterDao;

    @Autowired
    private NodeDao nodeDao;

    @Override
    public ClusterBean getCluster() throws ClusterServiceException {
        ClusterBean clusterBean = clusterDao.findAll().get(0);
        if (clusterBean == null) {
            throw new ClusterServiceException("Can not find the cluster.");
        }

        return clusterBean;
    }

    @Override
    public List<NodeBean> getAvailableNodes() throws ClusterServiceException {
        List<NodeBean> nodes = nodeDao.findAll();
        if (nodes == null) {
            throw new ClusterServiceException("Can not find any available nodes.", 2);
        }

        return nodes;
    }
}