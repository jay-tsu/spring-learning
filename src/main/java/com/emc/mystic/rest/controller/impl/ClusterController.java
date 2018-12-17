package com.emc.mystic.rest.controller.impl;

import com.emc.mystic.model.ClusterBean;
import com.emc.mystic.model.NodeBean;
import com.emc.mystic.rest.controller.transformer.Transformers;
import com.emc.mystic.rest.controller.message.ClusterMessageSource;
import com.emc.mystic.service.ClusterService;
import com.emc.mystic.service.exception.ClusterServiceException;
import com.emc.mystic.service.exception.ServiceException;
import com.emc.mystic.util.webutil.RequestParameters;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClusterController implements com.emc.mystic.rest.controller.ClusterInterface {
    private static final Logger logger = LogManager.getLogger(ClusterController.class);

    @Autowired
    private ClusterService clusterService;

    @Override
    public ClusterBean getCluster(final RequestParameters params) throws ClusterServiceException {
        return clusterService.getCluster();
    }



    @Override
    public HttpEntity<JsonNode> clusterServiceExceptionHandler(final RequestParameters params,
                                                               final ServiceException exception) {
        int start_index = ClusterMessageSource.CLUSTER_SERVICE_START.getErrorCode();

        return ClusterMessageSource.MESSAGES.stream()
                .filter(message -> message.getErrorCode() == start_index + exception.getErrorCode())
                .findFirst()
                .map(message -> {
                    String localizedMessage;
                    switch (message) {
                        case NO_RELATED_ID:
                            localizedMessage = message.getLocalizedMessage(params.getLocale(), "Cluster");
                            break;
                        default:
                            localizedMessage = message.getLocalizedMessage(params.getLocale());
                    }

                    return new ResponseEntity<>(
                            Transformers.convertExceptionToJsonNode(exception, start_index).apply(localizedMessage),
                            message.getHttpStatus());
                })
                .orElse(null);
    }
}
