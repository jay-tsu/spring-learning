package com.emc.mystic.rest.controller.transformer;

import com.emc.mystic.rest.controller.transformer.ExceptionTransformer;
import com.emc.mystic.service.exception.ServiceException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Transformers {
    public static ExceptionTransformer<JsonNode> convertExceptionToJsonNode(ServiceException exception, Integer start) {
        return localizedMessage -> new ObjectMapper().createObjectNode()
                .put("errorCode", start + exception.getErrorCode())
                .put("messages", localizedMessage);
    }
}
