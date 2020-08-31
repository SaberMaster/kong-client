package com.i2bgod.kong.gson;

import com.google.gson.Gson;
import feign.Response;
import feign.gson.GsonDecoder;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author: Lyn
 * @date: 26/08/2020
 */
public class CustomGsonDecoder extends GsonDecoder {

    public CustomGsonDecoder() {
        super();
    }

    public CustomGsonDecoder(Gson gson) {
        super(gson);
    }

    @Override
    public Object decode(Response response, Type type) throws IOException {
        if (404 == response.status()) {
            return null;
        }
        return super.decode(response,type);
    }
}

