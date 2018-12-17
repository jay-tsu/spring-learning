package com.emc.mystic.rest.controller.impl;

import com.emc.mystic.model.NodeBean;
import com.emc.mystic.service.ClusterService;
import com.emc.mystic.service.SystemService;
import com.emc.mystic.service.exception.ClusterServiceException;
import com.emc.mystic.util.webutil.RequestParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SystemController implements com.emc.mystic.rest.controller.SystemInterface {
    private static final Logger logger = LogManager.getLogger(SystemController.class);

    @Autowired
    private ClusterService clusterService;

    @Override
    public List<NodeBean> getAvailableNodes(final RequestParameters params) throws ClusterServiceException {
        return clusterService.getAvailableNodes();
    }

}
