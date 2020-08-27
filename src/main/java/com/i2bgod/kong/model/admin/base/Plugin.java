package com.i2bgod.kong.model.admin.base;

import com.google.gson.annotations.JsonAdapter;
import com.i2bgod.kong.model.adapter.PluginJsonDeserializer;
import com.i2bgod.kong.model.admin.plugin.config.annoation.KongPluginConfig;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: Lyn
 * @date: 05/08/2020
 */
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@JsonAdapter(PluginJsonDeserializer.class)
public class Plugin<T> extends PluginBase {
    /**
     * created_at : 1596616298
     * config : {"minute":20,"policy":"cluster","month":null,"redis_timeout":2000,"limit_by":"all","hide_client_headers":false,"hour":500,"day":null,"second":null,"year":null,"redis_password":null,"redis_host":null,"redis_port":6379,"redis_database":0,"fault_tolerant":true}
     * name : rate-limiting
     * enabled : false
     * protocols : ["http","https"]
     * run_on : first
     * consumer : null
     * route : null
     * service : null
     */

    private T config;

    public Plugin(PluginBase pluginBase) {
        this.setEnabled(pluginBase.getEnabled());
        this.setRunOn(pluginBase.getRunOn());
        this.setConsumer(pluginBase.getConsumer());
        this.setRoute(pluginBase.getRoute());
        this.setService(pluginBase.getService());
        this.setProtocols(pluginBase.getProtocols());
        this.setTags(pluginBase.getTags());
        this.setId(pluginBase.getId());
        this.setName(pluginBase.getName());
        this.setCreateAt(pluginBase.getCreateAt());
        this.setUpdateAt(pluginBase.getUpdateAt());
    }

    public void setConfig(T config) {
        this.config = config;
        if (StringUtils.isBlank(this.getName())) {
            KongPluginConfig annotation = config.getClass().getAnnotation(KongPluginConfig.class);
            if (null == annotation) {
                return;
            }
            this.setName(annotation.schemaName());
        }
    }
}
