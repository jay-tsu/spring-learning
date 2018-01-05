package com.emc.mystic.rest.controller.impl;

import com.emc.mystic.rest.controller.Cluster;
import com.emc.mystic.model.ClusterBean;
import com.emc.mystic.rest.controller.Transformers;
import com.emc.mystic.service.ClusterService;
import com.emc.mystic.service.exception.ClusterServiceException;
import com.emc.mystic.service.exception.ServiceException;
import com.emc.mystic.util.webutil.RequestParameters;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClusterController implements Cluster {
    private static final Logger logger = LogManager.getLogger(ClusterController.class);

    List<String> messages = Arrays.asList("", "没有找到对应的ID");


    @Autowired
    private ClusterService clusterService;

//    @Override
//    public HttpEntity<Node> getAvailableNodes(final RequestParameters params) {
////        logger.info("calling ClusterController.getAvailableNodes");
//        Node node = new Node(String.format(template, "World"), "Testing");
//        node.add(linkTo(methodOn(ClusterController.class).getAvailableNodes(params)).withSelfRel());
//        return new ResponseEntity<>(node, HttpStatus.OK);
//    }

//    @Override
//    public HttpEntity<ClusterBean> getCluster(@PathVariable("id") Long id, final RequestParameters params)
//            throws ClusterServiceException {
//        ClusterBean cluster = clusterService.getCluster(id);
//
//        return new ResponseEntity<>(cluster, HttpStatus.OK);
//    }

    @Override
    public ClusterBean getCluster(@PathVariable("id") Long id, final RequestParameters params)
            throws ClusterServiceException {
        return clusterService.getCluster(id);
    }

    @Override
    public HttpEntity<JsonNode> ClusterServiceExceptionHandler(final RequestParameters params, ServiceException exception) {
        // update exception message here and then build json object for response.
        return new ResponseEntity<>(Transformers.convertExceptionToJsonNode(exception, 0, params.getLocale()).apply(messages),
                HttpStatus.CREATED);
    }

//    @Override
//    public HttpEntity<JsonNode> PowerlinkAccountServiceExceptionHandler(ServiceException ex) {
//        return new ResponseEntity<>(Transformers.convertExceptionToJsonNode(ex, 10).get(),
//                HttpStatus.CREATED);
//    }
}
