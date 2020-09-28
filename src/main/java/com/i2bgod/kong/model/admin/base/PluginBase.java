package com.i2bgod.kong.model.admin.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.i2bgod.kong.model.admin.base.annotation.KongFK;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author: Lyn
 * @date: 05/08/2020
 */
@NoArgsConstructor
@ToString(callSuper = true)
@Data
public class PluginBase extends TagBase {

    @JsonProperty("run_on")
    private String runOn;
    @JsonProperty("enabled")
    private Boolean enabled;
    @JsonProperty("consumer")
    @KongFK
    private Consumer consumer;
    @JsonProperty("route")
    @KongFK
    private Route route;
    @JsonProperty("service")
    @KongFK
    private Service service;
    @JsonProperty("protocols")
    private List<String> protocols;
}
