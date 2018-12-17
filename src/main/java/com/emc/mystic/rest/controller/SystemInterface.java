package com.emc.mystic.rest.controller;

import com.emc.mystic.model.NodeBean;
import com.emc.mystic.service.exception.ClusterServiceException;
import com.emc.mystic.util.webutil.RequestParameters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "System", description = "System Operations")
@RestController
public interface SystemInterface {

    @ApiOperation(value = "Returns all hosts in VxRail cluster.", notes = "Returns all hosts in VxRail cluster.")
    @RequestMapping(value = "/v2/system/cluster-hosts", method = RequestMethod.GET, produces = "application/json")
    List<NodeBean> getAvailableNodes(final RequestParameters params) throws ClusterServiceException;
}