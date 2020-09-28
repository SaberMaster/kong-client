package com.i2bgod.kong.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.i2bgod.kong.exception.KongClientException;
import com.i2bgod.kong.model.adapter.DblessJsonSerializer;
import com.i2bgod.kong.model.admin.base.annotation.KongEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author: Lyn
 * @date: 08/09/2020
 */
@Slf4j
public class SchemaUtils {
    private Map<String, Class<?>> kongEntityClassMap;

    public Map<String, Class<?>> getKongEntityClassMap() {
        return kongEntityClassMap;
    }

    public void setKongEntityClassMap(Map<String, Class<?>> kongEntityClassMap) {
        this.kongEntityClassMap = kongEntityClassMap;
    }

    private PluginUtils pluginUtils;

    public PluginUtils getPluginUtils() {
        return pluginUtils;
    }

    public void setPluginUtils(PluginUtils pluginUtils) {
        this.pluginUtils = pluginUtils;
    }

    public SchemaUtils(Map<String, Class<?>> kongEntityClassMap, PluginUtils pluginUtils) {
        this.kongEntityClassMap = kongEntityClassMap;
        this.pluginUtils = pluginUtils;
    }

    @SuppressWarnings("unchecked")
    public String generateDblessJsonStr(List<Object> entities, String formatVersion) {
        Map<String, Object> maps = transEntityList2Map(entities);
        return generateDblessJsonStr(maps, formatVersion);
    }

    public String generateDblessJsonStr(Map<String, Object> config, String formatVersion) {
        config.put("_format_version", Optional.ofNullable(formatVersion).orElse("1.1"));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        SimpleModule simpleModule = new SimpleModule();
        kongEntityClassMap.values().forEach(entity ->
                simpleModule.addSerializer(entity, new DblessJsonSerializer<>(pluginUtils)));
        objectMapper.registerModule(simpleModule);
        try {
            return objectMapper.writeValueAsString(config);
        } catch (JsonProcessingException e) {
            log.warn("occur exception on serialize dbless json str", e);
            throw new RuntimeException(e);
        }
    }


    @SuppressWarnings("unchecked")
    public String generateDblessYamlStr(List<Object> entities, String formatVersion) {
        Map<String, Object> maps = transEntityList2Map(entities);
        return generateDblessYamlStr(maps, formatVersion);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> transEntityList2Map(List<Object> entities) {
        return entities.stream()
                .filter(Objects::nonNull)
                .collect(
                HashMap::new,
                (map, obj) -> {
                    KongEntity annotation = obj.getClass().getAnnotation(KongEntity.class);
                    map.putIfAbsent(annotation.dbEntityName(), new LinkedList<>());
                    List<Object> entityList = (List<Object>) map.get(annotation.dbEntityName());
                    entityList.add(obj);
                },
                Map::putAll
        );
    }

    public String generateDblessYamlStr(Map<String, Object> config, String formatVersion) {
        String jsonStr = generateDblessJsonStr(config, formatVersion);
        try {
            return YamlUtils.toYamlStr(jsonStr);
        } catch (JsonProcessingException e) {
            throw new KongClientException("parse json to yaml error", null, e);
        }
    }
}
