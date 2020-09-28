package com.i2bgod.kong.model.admin.plugin.config;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("policy")
    private String policy;
    @JsonProperty("redis_timeout")
    private Integer redisTimeout;
    @JsonProperty("limit_by")
    private String limitBy;
    @JsonProperty("hide_client_headers")
    private Boolean hideClientHeaders;
    @JsonProperty("year")
    private Integer year;
    @JsonProperty("month")
    private Integer month;
    @JsonProperty("day")
    private Integer day;
    @JsonProperty("hour")
    private Integer hour;
    @JsonProperty("minute")
    private Integer minute;
    @JsonProperty("second")
    private Integer second;
    @JsonProperty("redis_password")
    private String redisPassword;
    @JsonProperty("redis_host")
    private String redisHost;
    @JsonProperty("redis_port")
    private Integer redisPort;
    @JsonProperty("redis_database")
    private Integer redisDatabase;
    @JsonProperty("fault_tolerant")
    private Boolean faultTolerant;
}
