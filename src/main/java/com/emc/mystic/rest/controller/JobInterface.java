package com.emc.mystic.rest.controller;

import com.emc.mystic.model.JobBean;
import com.emc.mystic.service.exception.JobServiceException;
import com.emc.mystic.service.exception.ServiceException;
import com.emc.mystic.util.webutil.RequestParameters;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public interface JobInterface {
    @RequestMapping(value = "/v1/jobs/{id}", method = RequestMethod.GET, produces = "application/json" )
    ResponseEntity<?> getJob(@PathVariable("id") UUID id, final RequestParameters params) throws JobServiceException;

    @RequestMapping(value = "/v1/jobs", method = RequestMethod.GET, produces = "application/json" )
    List<JobBean> getJobs(final RequestParameters params) throws JobServiceException;

    @ExceptionHandler({JobServiceException.class, })
    HttpEntity<JsonNode> JobServiceExceptionHandler(final RequestParameters params, ServiceException ex);

}
