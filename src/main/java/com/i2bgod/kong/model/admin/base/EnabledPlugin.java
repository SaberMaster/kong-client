package com.i2bgod.kong.model.admin.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author: Lyn
 * @date: 27/08/2020
 */
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class EnabledPlugin {

    @JsonProperty("enabled_plugins")
    private List<String> enabledPlugins;
}
