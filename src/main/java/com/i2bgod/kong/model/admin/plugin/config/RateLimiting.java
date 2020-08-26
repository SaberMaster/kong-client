package com.i2bgod.kong.model.admin.plugin.config;

import com.google.gson.annotations.SerializedName;
import com.i2bgod.kong.model.admin.plugin.config.annoation.KongPluginConfig;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Lyn
 * @date: 05/08/2020
 */
@NoArgsConstructor
@Data
@KongPluginConfig(schemaName = "rate-limiting")
public class RateLimiting {

    /**
     * minute : 20
     * policy : cluster
     * month : null
     * redis_timeout : 2000
     * limit_by : all
     * hide_client_headers : false
     * hour : 500
     * day : null
     * second : null
     * year : null
     * redis_password : null
     * redis_host : null
     * redis_port : 6379
     * redis_database : 0
     * fault_tolerant : true
     */

    @SerializedName("policy")
    private String policy;
    @SerializedName("redis_timeout")
    private Integer redisTimeout;
    @SerializedName("limit_by")
    private String limitBy;
    @SerializedName("hide_client_headers")
    private Boolean hideClientHeaders;
    @SerializedName("year")
    private Integer year;
    @SerializedName("month")
    private Integer month;
    @SerializedName("day")
    private Integer day;
    @SerializedName("hour")
    private Integer hour;
    @SerializedName("minute")
    private Integer minute;
    @SerializedName("second")
    private Integer second;
    @SerializedName("redis_password")
    private String redisPassword;
    @SerializedName("redis_host")
    private String redisHost;
    @SerializedName("redis_port")
    private Integer redisPort;
    @SerializedName("redis_database")
    private Integer redisDatabase;
    @SerializedName("fault_tolerant")
    private Boolean faultTolerant;
}
