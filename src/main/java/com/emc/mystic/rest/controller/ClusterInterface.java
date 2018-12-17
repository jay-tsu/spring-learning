package com.emc.mystic.rest.controller;

import com.emc.mystic.model.ClusterBean;
import com.emc.mystic.model.NodeBean;
import com.emc.mystic.service.exception.ClusterServiceException;
import com.emc.mystic.service.exception.ServiceException;
import com.emc.mystic.util.webutil.RequestParameters;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Cluster", description = "Cluster Operations")
@RestController
public interface ClusterInterface {

    @ApiOperation(value = "Returns VxRail Cluster Details by Cluster ID", notes = "Returns VxRail Cluster Details Like Cluster's Name")
    @RequestMapping(value = "/v1/cluster", method = RequestMethod.GET, produces = "application/json")
    ClusterBean getCluster(final RequestParameters params) throws ClusterServiceException;



    @ExceptionHandler({ClusterServiceException.class,})
    HttpEntity<JsonNode> clusterServiceExceptionHandler(final RequestParameters params, ServiceException ex);

}
