package com.emc.mystic.rest.controller;

import com.emc.mystic.service.exception.ServiceException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Transformers {
    public static ExceptionTransformer<JsonNode> convertExceptionToJsonNode(ServiceException exception, Integer start) {
        return localizedMessage -> new ObjectMapper().createObjectNode()
                .put("errorCode", exception.getErrorCode() + start)
                .put("message", localizedMessage);
    }
}
