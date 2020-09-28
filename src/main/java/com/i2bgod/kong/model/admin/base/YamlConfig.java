package com.i2bgod.kong.model.admin.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: Lyn
 * @date: 08/09/2020
 */
@NoArgsConstructor
@ToString(callSuper = true)
@Slf4j
public class YamlConfig {
    @JsonProperty("config")
    private String config;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}

