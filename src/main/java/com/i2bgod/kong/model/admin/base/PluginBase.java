package com.i2bgod.kong.model.admin.base;

import com.google.gson.annotations.SerializedName;
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

    @SerializedName("run_on")
    private String runOn;
    @SerializedName("enabled")
    private Boolean enabled;
    @SerializedName("consumer")
    @KongFK
    private Consumer consumer;
    @SerializedName("route")
    @KongFK
    private Route route;
    @SerializedName("service")
    @KongFK
    private Service service;
    @SerializedName("protocols")
    private List<String> protocols;
}
