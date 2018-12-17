package com.emc.mystic.rest.controller;

import com.emc.mystic.model.LogBundleBean;
import com.emc.mystic.service.exception.LogBundleServiceException;
import com.emc.mystic.service.exception.ServiceException;
import com.emc.mystic.util.webutil.RequestParameters;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public interface LogBundleInterface {
    @RequestMapping(value = "/v1/support/logs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<LogBundleBean> getLogBundles(final RequestParameters params) throws LogBundleServiceException;

    @RequestMapping(value = "/v1/support/logs/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    LogBundleBean getLogBundle(@PathVariable("id") UUID id, final RequestParameters params)
            throws LogBundleServiceException;

    @RequestMapping(value = "/v1/support/logs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> createLogBundle(@Valid @RequestBody LogBundleBean logBundle, final RequestParameters params)
            throws LogBundleServiceException;

    @ExceptionHandler({LogBundleServiceException.class, })
    HttpEntity<JsonNode> logBundleServiceExceptionHandler(final RequestParameters params, ServiceException ex);
}
