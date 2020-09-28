package com.i2bgod.kong.model.adapter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.i2bgod.kong.model.admin.base.Plugin;
import com.i2bgod.kong.model.admin.base.PluginBase;
import com.i2bgod.kong.util.PluginUtils;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author: Lyn
 * @date: 05/08/2020
 */
public class PluginJsonDeserializer<T> extends JsonDeserializer<Plugin<T>> {

    private PluginUtils pluginUtils;

    public PluginJsonDeserializer(PluginUtils pluginUtils) {
       this.pluginUtils = pluginUtils;
    }

    private Type getPluginConfigType(String pluginName) {
        return resolvePluginConfigTypeByPluginName(pluginName);
    }

    private Type resolvePluginConfigTypeByPluginName(String name) {
        return pluginUtils.resolvePluginConfig(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Plugin<T> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        PluginBase pluginBase = deserializationContext.readValue(jsonParser, PluginBase.class);
        Plugin<T> result = new Plugin<>(pluginBase);
        Type pluginConfigType = getPluginConfigType(result.getName());
        JsonNode jsonNode = deserializationContext.readTree(jsonParser);
        JsonParser configJp = jsonNode.get("config").traverse();
        result.setConfig(deserializationContext.readValue(configJp ,(Class<T>) pluginConfigType));
        return result;
    }
}
