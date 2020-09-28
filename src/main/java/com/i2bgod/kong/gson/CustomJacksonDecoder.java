package com.i2bgod.kong.gson;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.jackson.JacksonDecoder;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author: Lyn
 * @date: 26/08/2020
 */
public class CustomJacksonDecoder extends JacksonDecoder {

    public CustomJacksonDecoder() {
        super();
    }

    public CustomJacksonDecoder(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    public Object decode(Response response, Type type) throws IOException {
        if (404 == response.status()) {
            return null;
        }
        return super.decode(response,type);
    }
}

