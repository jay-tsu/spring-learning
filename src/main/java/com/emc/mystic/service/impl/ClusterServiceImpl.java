package com.emc.mystic.service.impl;

import com.emc.mystic.repository.ClusterDao;
import com.emc.mystic.model.ClusterBean;
import com.emc.mystic.service.ClusterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public ClusterBean getCluster(final Long id) {
        return clusterDao.findOne(id);
    }
}