package com.i2bgod.kong.model.codec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.i2bgod.kong.exception.KongClientException;
import feign.Response;
import feign.RetryableException;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Lyn
 * @date: 07/08/2020
 */
@Slf4j
public class KongAdminErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        //todo: impl more detail in the future
        Exception defaultException = defaultErrorDecoder.decode(methodKey, response);
        if (defaultException instanceof RetryableException) {
            return defaultException;
        }
        String body = null;
        try {
            if (null != response.body()) {
                body = Util.toString(response.body().asReader(Charset.defaultCharset()));
            }
        } catch (IOException ioE) {
            log.warn("io exception", ioE);
            return new RuntimeException(ioE);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Object error;
            if (null != body) {
                error = objectMapper.readValue(body, Object.class);
            } else {
                error = null;
            }

            return new KongClientException(
                    "kong response error",
                    response.status(),
                    error
            );
        } catch (JsonProcessingException e) {
            Map<String, String> errInfo = new HashMap<>(2);
            errInfo.put("details", body);
            return new KongClientException(
                    "kong response error",
                    response.status(),
                    errInfo
            );
        }
    }
}
