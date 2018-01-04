package com.emc.mystic.rest.controller;

import com.emc.mystic.model.ClusterBean;
import com.emc.mystic.util.webutil.RequestParameters;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public interface Cluster {
//    @RequestMapping(value = "/clusters/available-nodes", method = RequestMethod.GET, produces = "application/json" )
//    HttpEntity<Node> getAvailableNodes(final RequestParameters params);

    @RequestMapping(value = "/clusters/{id}", method = RequestMethod.GET, produces = "application/json" )
    HttpEntity<ClusterBean> getCluster(@PathVariable("id") Long id, final RequestParameters params);
}
