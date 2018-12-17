package com.emc.mystic.rest.controller.impl;

import com.emc.mystic.model.JobBean;
import com.emc.mystic.model.LogBundleBean;
import com.emc.mystic.rest.controller.LogBundleInterface;
import com.emc.mystic.rest.controller.transformer.Transformers;
import com.emc.mystic.rest.controller.message.LogBundleMessageSource;
import com.emc.mystic.service.LogBundleService;
import com.emc.mystic.service.exception.LogBundleServiceException;
import com.emc.mystic.service.exception.ServiceException;
import com.emc.mystic.util.webutil.RequestParameters;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
public class LogBundleController implements LogBundleInterface {
    private static final Logger logger = LogManager.getLogger(LogBundleController.class);

    @Autowired
    private LogBundleService logBundleService;

    @Override
    public LogBundleBean getLogBundle(@PathVariable("id") UUID id, final RequestParameters params)
            throws LogBundleServiceException {
        return logBundleService.getLogBundle(id);
    }

    @Override
    public List<LogBundleBean> getLogBundles(final RequestParameters params) throws LogBundleServiceException {
        return logBundleService.getLogBundles();
    }

    @Override
    public ResponseEntity<?> createLogBundle(@Valid @RequestBody LogBundleBean logBundle, final RequestParameters params)
            throws LogBundleServiceException {
        JobBean job = logBundleService.createLogBundleAsync(logBundle);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(String.format("/jobs/%s", job.getId()))
                .build()
                .toUri();
        return ResponseEntity.accepted()
                .location(location)
                .body(job);
    }

    @Override
    public HttpEntity<JsonNode> logBundleServiceExceptionHandler(final RequestParameters params,
                                                                 final ServiceException exception) {
        int start_index = LogBundleMessageSource.LOG_BUNDLE_SERVICE_START.getErrorCode();

        return LogBundleMessageSource.MESSAGES.stream()
                .filter(message -> message.getErrorCode() == start_index + exception.getErrorCode())
                .findFirst()
                .map(message -> {
                    String localizedMessage;
                    switch (message) {
                        case INVALID_LOG_BUNDLE_TYPE:
                            localizedMessage = message.getLocalizedMessage(params.getLocale(), exception.getExceptionArguments());
                            break;
                        default:
                            localizedMessage = message.getLocalizedMessage(params.getLocale());
                    }

                    return new ResponseEntity<>(
                            Transformers.convertExceptionToJsonNode(exception, start_index)
                                    .apply(localizedMessage),
                            message.getHttpStatus());
                })
                .orElse(null);
    }
}
