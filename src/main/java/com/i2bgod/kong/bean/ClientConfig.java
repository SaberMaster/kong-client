package com.i2bgod.kong.bean;

import com.i2bgod.kong.api.admin.annoation.KongService;
import com.i2bgod.kong.model.admin.base.annotation.KongEntity;
import com.i2bgod.kong.model.admin.plugin.config.annoation.KongPluginConfig;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: Lyn
 * @date: 05/08/2020
 */
public class ClientConfig {

    public static final String BASE_PACKAGE = "com.i2bgod.kong";
    private Map<String, Class<?>> pluginConfigClassMap;
    private Map<String, Class<?>> serviceClassMap;
    private Map<String, Class<?>> kongEntityClassMap;
    private String extraPluginConfigScanPackage;
    private String extraServiceScanPackage;
    private String extraKongEntityScanPackage;


    public ClientConfig() {
        initConfig();
    }

    public String getExtraPluginConfigScanPackage() {
        return extraPluginConfigScanPackage;
    }

    public void setExtraPluginConfigScanPackage(String extraPluginConfigScanPackage) {
        this.extraPluginConfigScanPackage = extraPluginConfigScanPackage;
        // scan extra plugin config
        // extra plugin config will override the base plugin config
        pluginConfigClassMap.putAll(scanPluginConfig(extraPluginConfigScanPackage));
    }

    public String getExtraServiceScanPackage() {
        return extraServiceScanPackage;
    }

    public void setExtraServiceScanPackage(String extraServiceScanPackage) {
        this.extraServiceScanPackage = extraServiceScanPackage;
        // scan extra service
        // extra service will override the base service
        this.serviceClassMap.putAll(scanService(extraServiceScanPackage));
    }

    public String getExtraKongEntityScanPackage() {
        return extraKongEntityScanPackage;
    }

    public void setExtraKongEntityScanPackage(String extraKongEntityScanPackage) {
        this.extraKongEntityScanPackage = extraKongEntityScanPackage;
        // scan extra service
        // extra service will override the base service
        this.kongEntityClassMap.putAll(scanEntity(extraKongEntityScanPackage));
    }

    public Map<String, Class<?>> getPluginConfigClassMap() {
        return pluginConfigClassMap;
    }

    public Map<String, Class<?>> getServiceClassMap() {
        return serviceClassMap;
    }


    public Map<String, Class<?>> getKongEntityClassMap() {
        return kongEntityClassMap;
    }


    private void initConfig() {
        pluginConfigClassMap = scanPluginConfig(BASE_PACKAGE);
        serviceClassMap = scanService(BASE_PACKAGE);
        kongEntityClassMap = scanEntity(BASE_PACKAGE);
    }

    private Map<String, Class<?>> scanPluginConfig(String basePackage) {
        if (StringUtils.isBlank(basePackage)) {
            return new HashMap<>();
        }
        Reflections reflections = new Reflections(basePackage);
        Set<Class<?>> extraPluginConfigSet = reflections.getTypesAnnotatedWith(KongPluginConfig.class);
        return extraPluginConfigSet.stream()
                .collect(
                        HashMap::new,
                        (map, obj) -> {
                            KongPluginConfig annotation = obj.getAnnotation(KongPluginConfig.class);
                            map.putIfAbsent(annotation.schemaName(), obj);
                        },
                        Map::putAll
                );
    }

    private Map<String, Class<?>> scanService(String basePackage) {
        if (StringUtils.isBlank(basePackage)) {
            return new HashMap<>();
        }
        Reflections reflections = new Reflections(basePackage);
        Set<Class<?>> extraPluginConfigSet = reflections.getTypesAnnotatedWith(KongService.class);
        return extraPluginConfigSet.stream()
                .collect(
                        HashMap::new,
                        (map, obj) -> {
                            KongService annotation = obj.getAnnotation(KongService.class);
                            map.putIfAbsent(annotation.schemaName(), obj);
                        },
                        Map::putAll
                );
    }


    private Map<String, Class<?>> scanEntity(String basePackage) {
        if (StringUtils.isBlank(basePackage)) {
            return new HashMap<>();
        }
        Reflections reflections = new Reflections(basePackage);
        Set<Class<?>> extraPluginConfigSet = reflections.getTypesAnnotatedWith(KongEntity.class);
        return extraPluginConfigSet.stream()
                .collect(
                        HashMap::new,
                        (map, obj) -> {
                            KongEntity annotation = obj.getAnnotation(KongEntity.class);
                            map.putIfAbsent(annotation.dbEntityName(), obj);
                        },
                        Map::putAll
                );
    }
}
