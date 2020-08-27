package com.i2bgod.kong.model.admin.base;

import com.google.gson.annotations.SerializedName;
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
    private Consumer consumer;
    @SerializedName("route")
    private Route route;
    @SerializedName("service")
    private Service service;
    @SerializedName("protocols")
    private List<String> protocols;
}
