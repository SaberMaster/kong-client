package com.i2bgod.kong.model.admin.base;

import com.google.gson.annotations.SerializedName;
import com.i2bgod.kong.util.SchemaUtils;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author: Lyn
 * @date: 08/09/2020
 */
@NoArgsConstructor
@ToString(callSuper = true)
@Slf4j
public class YamlConfig {
    @SerializedName("config")
    private String config;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    /**
     * only support the entity which field's TypeAdapter return string or number
     * @param entities
     * @param formatVersion
     */
    public void setConfig(List<Object> entities, String formatVersion) {
        setConfig(SchemaUtils.generateDblessYamlStr(entities, formatVersion));
    }

    /**
     * only support the entity which field's TypeAdapter return string or number
     * @param config
     * @param formatVersion
     */
    public void setConfig(Map<String, Object> config, String formatVersion){
        setConfig(SchemaUtils.generateDblessYamlStr(config, formatVersion));
    }
}

