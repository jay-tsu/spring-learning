package com.emc.mystic.service.impl;

import com.emc.mystic.repository.ClusterDao;
import com.emc.mystic.model.ClusterBean;
import com.emc.mystic.service.ClusterService;
import com.emc.mystic.service.exception.ClusterServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClusterServiceImpl implements ClusterService {
    @Autowired
    private ClusterDao clusterDao;

//    @Override
//    public List<Node> getAvailableNodes() {
//        return null;
//    }

    @Override
    public ClusterBean getCluster(final Long id) throws ClusterServiceException {
        ClusterBean clusterBean = clusterDao.findOne(id);
        if (clusterBean == null) {
            throw new ClusterServiceException("Can not find cluster id", 1);
        } else {
            return clusterBean;
        }
    }
}