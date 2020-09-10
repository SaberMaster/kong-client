package com.i2bgod.kong.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.i2bgod.kong.bean.ClientConfig;
import com.i2bgod.kong.exception.KongClientException;
import com.i2bgod.kong.model.adapter.DblessJsonSerializer;
import com.i2bgod.kong.model.admin.base.annotation.KongEntity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author: Lyn
 * @date: 08/09/2020
 */
public class SchemaUtils {
    @SuppressWarnings("unchecked")
    public static String generateDblessJsonStr(List<Object> entities, String formatVersion) {
        Map<String, Object> maps = transEntityList2Map(entities);
        return generateDblessJsonStr(maps, formatVersion);
    }

    public static String generateDblessJsonStr(Map<String, Object> config, String formatVersion) {
        config.put("_format_version", Optional.ofNullable(formatVersion).orElse("1.1"));

        GsonBuilder gsonBuilder = new GsonBuilder();
        ClientConfig clientConfig = ConfigUtils.getClientConfig();
        clientConfig.getKongEntityClassMap().values().forEach(entity -> gsonBuilder.registerTypeAdapter(entity, new DblessJsonSerializer<>()));
        Gson gson = gsonBuilder.create();
        return gson.toJson(config);
    }


    @SuppressWarnings("unchecked")
    public static String generateDblessYamlStr(List<Object> entities, String formatVersion) {
        Map<String, Object> maps = transEntityList2Map(entities);
        return generateDblessYamlStr(maps, formatVersion);
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> transEntityList2Map(List<Object> entities) {
        return entities.stream().collect(
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

    public static String generateDblessYamlStr(Map<String, Object> config, String formatVersion) {
        String jsonStr = generateDblessJsonStr(config, formatVersion);
        try {
            return YamlUtils.toYamlStr(jsonStr);
        } catch (JsonProcessingException e) {
            throw new KongClientException("parse json to yaml error", null, e);
        }
    }
}
