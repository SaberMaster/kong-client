package com.i2bgod.kong.model.codec;

import com.i2bgod.kong.exception.KongClientException;
import com.i2bgod.kong.utils.ConfigUtils;
import feign.Response;
import feign.RetryableException;
import feign.Util;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author: Lyn
 * @date: 07/08/2020
 */
public class KongAdminErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        //todo: impl more detail in the future
        Exception defaultException = defaultErrorDecoder.decode(methodKey, response);
        if (defaultException instanceof RetryableException) {
            return defaultException;
        }
        try {
            return new KongClientException("kong response error",
                    response.status(), ConfigUtils.getGson().fromJson(Util.toString(response.body().asReader(Charset.defaultCharset())), Object.class)
                    );
        } catch (IOException ignored) {
        }
        return defaultException;
    }
}
