package com.i2bgod.kong;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.i2bgod.kong.bean.ClientConfig;
import com.i2bgod.kong.exception.KongClientException;
import com.i2bgod.kong.gson.CustomGsonDecoder;
import com.i2bgod.kong.model.adapter.PluginJsonDeserializer;
import com.i2bgod.kong.model.admin.base.Plugin;
import com.i2bgod.kong.model.codec.KongAdminErrorDecoder;
import com.i2bgod.kong.util.PluginUtils;
import com.i2bgod.kong.util.SchemaUtils;
import feign.Feign;
import feign.gson.GsonEncoder;
import org.apache.commons.collections.MapUtils;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Lyn
 * @date: 04/08/2020
 */
public class AdminClient {
    private String url;

    private Map<Class<?>, Object> serviceMap;

    private ClientConfig config;

    private SchemaUtils schemaUtils;

    private PluginUtils pluginUtils;

    public AdminClient(AdminClientConfig config) {
        this.config = new ClientConfig(config.getExtraScanPackage());
        schemaUtils = new SchemaUtils(this.config.getKongEntityClassMap());
        pluginUtils = new PluginUtils(this.config.getPluginConfigClassMap());
        Feign.Builder feignBuilder = getFeignBuilder(config);
        this.url = config.getAdminUrl();
        createProxy(feignBuilder, url);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T getService(Class<T> clz) {
        return (T) serviceMap.get(clz);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T getService(String name) {
        Map<String, Class<?>> serviceClassMap = this.config.getServiceClassMap();
        if (MapUtils.isEmpty(serviceClassMap)) {
            throw new KongClientException("service not found");
        }

        Class<?> targetClz = serviceClassMap.get(name);
        if (null == targetClz) {
            throw new KongClientException("service not found");
        }
        return (T) serviceMap.get(targetClz);
    }

    public String getUrl() {
        return url;
    }

    public SchemaUtils getSchemaUtils() {
        return schemaUtils;
    }

    private void createProxy(Feign.Builder feignBuilder, String url) {
        Map<String, Class<?>> serviceClassMap = this.config.getServiceClassMap();
        serviceMap = new HashMap<>(serviceClassMap.size());
        serviceClassMap.forEach((k, v) -> serviceMap.put(v, feignBuilder.target(v, url)));
    }

    protected void release() {
        // no need
        serviceMap.clear();
    }

    private Feign.Builder getFeignBuilder(AdminClientConfig adminClientConfig) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Plugin.class, new PluginJsonDeserializer<>(this.pluginUtils));
        Gson gson = gsonBuilder.create();
        return Feign.builder()
                .decoder(new CustomGsonDecoder(gson))
                .encoder(new GsonEncoder(gson))
                .decode404()
                .errorDecoder(new KongAdminErrorDecoder())
                .retryer(adminClientConfig.getRetryer())
                .options(adminClientConfig.getOptions())
                .client(adminClientConfig.getClient())
                .logger(adminClientConfig.getLogger())
                .logLevel(adminClientConfig.getLevel());
    }
}
