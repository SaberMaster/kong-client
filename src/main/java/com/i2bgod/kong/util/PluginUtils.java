package com.i2bgod.kong.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author: Lyn
 * @date: 06/08/2020
 */
public class PluginUtils {
    private PluginUtils() {}

    public static Class<?> resolvePluginConfig(String pluginName) {
        if (StringUtils.isBlank(pluginName)) {
            return Object.class;
        }
        Map<String, Class<?>> pluginConfigClassMap = ConfigUtils.getClientConfig().getPluginConfigClassMap();
        if (pluginConfigClassMap.containsKey(pluginName)) {
            return pluginConfigClassMap.get(pluginName);
        }
        return Object.class;
    }
}
