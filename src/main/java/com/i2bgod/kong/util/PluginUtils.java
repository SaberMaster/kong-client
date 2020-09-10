package com.i2bgod.kong.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author: Lyn
 * @date: 06/08/2020
 */
public class PluginUtils {
    private Map<String, Class<?>> pluginConfigClassMap;

    public PluginUtils(Map<String, Class<?>> pluginConfigClassMap) {
        this.pluginConfigClassMap = pluginConfigClassMap;
    }

    public Map<String, Class<?>> getPluginConfigClassMap() {
        return pluginConfigClassMap;
    }

    public void setPluginConfigClassMap(Map<String, Class<?>> pluginConfigClassMap) {
        this.pluginConfigClassMap = pluginConfigClassMap;
    }

    public Class<?> resolvePluginConfig(String pluginName) {
        if (StringUtils.isBlank(pluginName)) {
            return Object.class;
        }
        if (pluginConfigClassMap.containsKey(pluginName)) {
            return pluginConfigClassMap.get(pluginName);
        }
        return Object.class;
    }
}
