package com.i2bgod.kong.model.admin.plugin.config;

import com.google.gson.annotations.SerializedName;
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

    @SerializedName("blacklist")
    private List<String> blacklist;
    @SerializedName("whitelist")
    private List<String> whitelist;
}
