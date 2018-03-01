package com.emc.mystic.rest.controller.impl;

import com.emc.mystic.model.JobBean;
import com.emc.mystic.rest.controller.transformer.Transformers;
import com.emc.mystic.rest.controller.message.ClusterMessageSource;
import com.emc.mystic.service.JobService;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
public class JobController implements com.emc.mystic.rest.controller.JobInterface {
    private static final Logger logger = LogManager.getLogger(JobController.class);

    @Autowired
    private JobService jobService;

    @Override
    public ResponseEntity<?> getJob(@PathVariable("id") UUID id, final RequestParameters params)
            throws ClusterServiceException {
        JobBean job = jobService.getJob(id);

        if (job.getProgress() == 100) {
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                                                      .path(job.getTarget())
                                                      .build()
                                                      .toUri();

            return ResponseEntity.status(HttpStatus.SEE_OTHER)
                                 .location(location)
                                 .body(job);
        } else {
            return ResponseEntity.ok()
                                 .body(job);
        }
    }

    @Override
    public List<JobBean> getJobs(final RequestParameters params) throws ClusterServiceException{
        return jobService.getAllJobs();
    }

    @Override
    public HttpEntity<JsonNode> JobServiceExceptionHandler(final RequestParameters params,
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
