package com.emc.mystic.rest.controller.impl;

import com.emc.mystic.rest.controller.Cluster;
import com.emc.mystic.model.ClusterBean;
import com.emc.mystic.service.ClusterService;
import com.emc.mystic.util.webutil.RequestParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClusterController implements Cluster {
    private static final Logger logger = LogManager.getLogger(ClusterController.class);

    @Autowired
    private ClusterService clusterService;

    private static final String template = "Hello, %s!";

//    @Override
//    public HttpEntity<Node> getAvailableNodes(final RequestParameters params) {
////        logger.info("calling ClusterController.getAvailableNodes");
//        Node node = new Node(String.format(template, "World"), "Testing");
//        node.add(linkTo(methodOn(ClusterController.class).getAvailableNodes(params)).withSelfRel());
//        return new ResponseEntity<>(node, HttpStatus.OK);
//    }

    @Override
    public HttpEntity<ClusterBean> getCluster(@PathVariable("id") Long id, final RequestParameters params) {
        ClusterBean cluster = clusterService.getCluster(id);

        return new ResponseEntity<>(cluster, HttpStatus.OK);
    }
}
