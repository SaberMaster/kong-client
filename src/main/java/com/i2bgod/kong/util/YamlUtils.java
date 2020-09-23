package com.i2bgod.kong.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

/**
 * @author: Lyn
 * @date: 08/09/2020
 */
public class YamlUtils {

    private static YAMLMapper yamlMapper = new YAMLMapper();
    private static JsonMapper jsonMapper = new JsonMapper();

    public static String toYamlStr(String jsonStr) throws JsonProcessingException {
        // parse JSON
        JsonNode jsonNodeTree = new ObjectMapper().readTree(jsonStr);
        // save it as YAML
        return yamlMapper.writeValueAsString(jsonNodeTree);
    }

    public static String toJsonStr(String yamlStr) throws JsonProcessingException {
        // parse yaml
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        JsonNode jsonNodeTree = objectMapper.readTree(yamlStr);
        // save it as YAML
        return jsonMapper.writeValueAsString(jsonNodeTree);
    }
}
