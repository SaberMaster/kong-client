package com.i2bgod.kong.model.adapter;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.Type;
import java.util.Collection;

/**
 * @author: Lyn
 * @date: 05/08/2020
 */
public class EmptyArrayTypeAdapter<T extends Collection<?>> implements JsonDeserializer<T>, JsonSerializer<T> {

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        //fit lua return empty array return `{}`
        if (json.isJsonObject()) {
            return null;
        }
        return context.deserialize(json, typeOfT);
    }

    @Override
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonArray jsonArray = new JsonArray();
        if (CollectionUtils.isNotEmpty(src)) {
            for (Object obj : src) {
                jsonArray.add(context.serialize(obj));
            }
        }
        return jsonArray;
    }
}
