package com.i2bgod.kong.model.codec;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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

    private static Gson gson = new Gson();

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
        try {
            return new KongClientException(
                    "kong response error",
                    response.status(),
                    gson.fromJson(body, Object.class)
                    );

        } catch (JsonSyntaxException jsonException) {
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
