package com.i2bgod.kong.model.admin.plugin.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.i2bgod.kong.model.admin.plugin.config.annoation.KongPluginConfig;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: Lyn
 * @date: 05/08/2020
 */
@NoArgsConstructor
@Data
@KongPluginConfig(schemaName = "ip-restriction")
public class IpRestriction {

    @JsonProperty("blacklist")
    private List<String> blacklist;
    @JsonProperty("whitelist")
    private List<String> whitelist;
}
