package com.emc.mystic.rest.controller;

import com.emc.mystic.model.ClusterBean;
import com.emc.mystic.service.exception.ClusterServiceException;
import com.emc.mystic.service.exception.ServiceException;
import com.emc.mystic.util.webutil.RequestParameters;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public interface Cluster {
    @RequestMapping(value = "/clusters/{id}", method = RequestMethod.GET, produces = "application/json" )
    ClusterBean getCluster(@PathVariable("id") Long id, final RequestParameters params) throws ClusterServiceException;

    @ExceptionHandler({ClusterServiceException.class, })
    HttpEntity<JsonNode> clusterServiceExceptionHandler(final RequestParameters params, ServiceException ex);

}
