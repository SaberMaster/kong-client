package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.api.admin.annoation.KongService;
import com.i2bgod.kong.model.admin.base.YamlConfig;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.apache.commons.collections.MapUtils;

import java.util.Map;

/**
 * @author: Lyn
 * @date: 19/08/2020
 */
@KongService(schemaName = "config")
@Headers("Content-Type: application/json")
public interface ConfigService {
    @RequestLine("POST /config?check_hash={checkHash}")
    Map<String, Map<String, Object>> addYaml(YamlConfig config, @Param("checkHash") Integer checkHash);

    @RequestLine("POST /config?check_hash={checkHash}")
    Map<String, Map<String, Object>> addJson(Map<String, Object> config, @Param("checkHash") Integer checkHash);

    default Map<String, Map<String, Object>> addJsonWithFormatVersion(Map<String, Object> config, Integer checkHash, String formatVersion) {
       if (MapUtils.isNotEmpty(config)) {
           config.put("_format_version", formatVersion);
       }
       return addJson(config, checkHash);
    }
}
