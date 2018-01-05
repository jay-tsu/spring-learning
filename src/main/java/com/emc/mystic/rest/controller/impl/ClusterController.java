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

    // The customized messages for service exception, including HttpStatus.
    public enum LocalizedMessages {
        // Todo: use the message key for i18N
        CLUSTER_SERVICE_START(0, "", HttpStatus.OK),
        NO_RELATED_ID(1, "没有找到对应的ID", HttpStatus.INTERNAL_SERVER_ERROR);

        private int errorCode;
        private String message;
        private HttpStatus httpStatus;

        LocalizedMessages(final int errorCode, final String message, final HttpStatus httpStatus) {
            this.errorCode = errorCode;
            this.message = message;
            this.httpStatus = httpStatus;
        }

        public int getErrorCode() {return this.errorCode;}

        public String getMessage() {return this.message;}

        public HttpStatus getHttpStatus() {return this.httpStatus;}
    }

    private List<LocalizedMessages> messages = Arrays.asList(LocalizedMessages.NO_RELATED_ID);

    @Autowired
    private ClusterService clusterService;

    @Override
    public ClusterBean getCluster(@PathVariable("id") Long id, final RequestParameters params)
            throws ClusterServiceException {
        return clusterService.getCluster(id);
    }

    @Override
    public HttpEntity<JsonNode> ClusterServiceExceptionHandler(final RequestParameters params,
                                                               final ServiceException exception) {
        int start_index = LocalizedMessages.CLUSTER_SERVICE_START.getErrorCode();

        return messages.stream()
                .filter(message -> message.getErrorCode() == start_index + exception.getErrorCode())
                .map(message -> new ResponseEntity<>(
                        Transformers.convertExceptionToJsonNode(exception, start_index)
                                .apply(message.getMessage()),
                        message.getHttpStatus()))
                .findFirst().get();
    }
}
