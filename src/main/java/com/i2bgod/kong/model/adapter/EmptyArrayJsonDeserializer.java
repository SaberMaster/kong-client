package com.i2bgod.kong.model.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * @author: Lyn
 * @date: 05/08/2020
 */
public class EmptyArrayJsonDeserializer<T> implements JsonDeserializer<T> {

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        //fit lua return empty array return `{}`
        if (json.isJsonObject()) {
            return null;
        }
        return context.deserialize(json, typeOfT);
    }
}
