package com.i2bgod.kong.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

/**
 * @author: Lyn
 * @date: 08/09/2020
 */
public class YamlUtils {

    private static YAMLMapper yamlMapper = new YAMLMapper();

    public static String toYamlStr(String jsonStr) throws JsonProcessingException {
        // parse JSON
        JsonNode jsonNodeTree = new ObjectMapper().readTree(jsonStr);
        // save it as YAML
        return yamlMapper.writeValueAsString(jsonNodeTree);
    }
}
