package com.emc.mystic.rest.controller;

import com.emc.mystic.model.ClusterBean;
import com.emc.mystic.rest.exception.RestApiException;
import com.emc.mystic.service.exception.ClusterServiceException;
import com.emc.mystic.service.exception.ServiceException;
import com.emc.mystic.util.webutil.RequestParameters;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public interface Cluster {
//    @RequestMapping(value = "/clusters/available-nodes", method = RequestMethod.GET, produces = "application/json" )
//    HttpEntity<Node> getAvailableNodes(final RequestParameters params);

//    @RequestMapping(value = "/clusters/{id}", method = RequestMethod.GET, produces = "application/json" )
//    HttpEntity<ClusterBean> getCluster(@PathVariable("id") Long id, final RequestParameters params)
//            throws ClusterServiceException;

    @RequestMapping(value = "/clusters/{id}", method = RequestMethod.GET, produces = "application/json" )
    ClusterBean getCluster(@PathVariable("id") Long id, final RequestParameters params) throws ClusterServiceException;

    @ExceptionHandler({ClusterServiceException.class, })
    HttpEntity<JsonNode> ClusterServiceExceptionHandler(final RequestParameters params, ServiceException ex);

//    @ExceptionHandler(PowerlinkAccountServiceException.class)
//    HttpEntity<JsonNode> PowerlinkAccountServiceExceptionHandler(ServiceException ex);
}
