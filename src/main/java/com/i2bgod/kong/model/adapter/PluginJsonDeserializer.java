package com.i2bgod.kong.model.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.i2bgod.kong.model.admin.base.Plugin;
import com.i2bgod.kong.model.admin.base.PluginBase;
import com.i2bgod.kong.util.PluginUtils;

import java.lang.reflect.Type;

/**
 * @author: Lyn
 * @date: 05/08/2020
 */
public class PluginJsonDeserializer<T> implements JsonDeserializer<Plugin<T>> {

    private PluginUtils pluginUtils;

    public PluginJsonDeserializer(PluginUtils pluginUtils) {
       this.pluginUtils = pluginUtils;
    }

    @Override
    public Plugin<T> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        PluginBase pluginBase = context.deserialize(json, PluginBase.class);
        Plugin<T> result = new Plugin<>(pluginBase);
        Type pluginConfigType = getPluginConfigType(result.getName());
        result.setConfig(context.deserialize(jsonObject.getAsJsonObject("config"), pluginConfigType));
        return result;
    }

    private Type getPluginConfigType(String pluginName) {
        return resolvePluginConfigTypeByPluginName(pluginName);
    }

    private Type resolvePluginConfigTypeByPluginName(String name) {
        return pluginUtils.resolvePluginConfig(name);
    }
}
