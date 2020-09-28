package com.i2bgod.kong.gson;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestTemplate;
import feign.jackson.JacksonEncoder;

import java.lang.reflect.Type;

/**
 * @author: Lyn
 * @date: 26/08/2020
 */
public class CustomJacksonEncoder extends JacksonEncoder {

    public CustomJacksonEncoder() {
        super();
    }

    public CustomJacksonEncoder(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) {
        super.encode(object, object.getClass(), template);
    }
}

