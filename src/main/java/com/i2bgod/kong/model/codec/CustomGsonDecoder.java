package com.i2bgod.kong.model.codec;

import feign.Response;
import feign.gson.GsonDecoder;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author: Lyn
 * @date: 26/08/2020
 */
public class CustomGsonDecoder extends GsonDecoder {
    @Override
    public Object decode(Response response, Type type) throws IOException {
        if (404 == response.status()) {
            return null;
        }
        return super.decode(response,type);
    }
}

