package com.emc.mystic.rest.controller;

import com.emc.mystic.service.exception.ServiceException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Locale;

public class Transformers {
    public static ExceptionTransformer<JsonNode> convertExceptionToJsonNode(
            ServiceException exception, Integer start, String locale) {
        return messages -> {
            int index = exception.getErrorCode() + start;
            JsonNode response =  new ObjectMapper().createObjectNode()
                    .put("errorCode", index)
                    .put("message", messages.get(index));

            // Localization staff
            return response;
        };
    }
}
