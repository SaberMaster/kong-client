package com.i2bgod.kong;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.i2bgod.kong.bean.ClientConfig;
import com.i2bgod.kong.exception.KongClientException;
import com.i2bgod.kong.gson.CustomJacksonDecoder;
import com.i2bgod.kong.gson.CustomJacksonEncoder;
import com.i2bgod.kong.model.adapter.PluginJsonDeserializer;
import com.i2bgod.kong.model.admin.base.Plugin;
import com.i2bgod.kong.model.codec.KongAdminErrorDecoder;
import com.i2bgod.kong.util.PluginUtils;
import com.i2bgod.kong.util.SchemaUtils;
import feign.Feign;
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

    private ObjectMapper jsonUtil;

    public ObjectMapper getJsonUtil() {
        return jsonUtil;
    }

    public AdminClient(AdminClientConfig config) {
        this.config = new ClientConfig(config.getExtraScanPackage());
        pluginUtils = new PluginUtils(this.config.getPluginConfigClassMap());
        schemaUtils = new SchemaUtils(this.config.getKongEntityClassMap(), pluginUtils);
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
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(Plugin.class, new PluginJsonDeserializer<>(this.pluginUtils));
//        ObjectJsonSerializer objectJsonSerializer = new ObjectJsonSerializer();
//        config.getKongEntityClassMap().forEach((k, v) -> {
//           simpleModule.addSerializer(v, objectJsonSerializer);
//        });
        this.jsonUtil = objectMapper;
        return Feign.builder()
                .decoder(new CustomJacksonDecoder(jsonUtil))
                .encoder(new CustomJacksonEncoder(jsonUtil))
                .decode404()
                .errorDecoder(new KongAdminErrorDecoder())
                .retryer(adminClientConfig.getRetryer())
                .options(adminClientConfig.getOptions())
                .client(adminClientConfig.getClient())
                .logger(adminClientConfig.getLogger())
                .logLevel(adminClientConfig.getLevel());
    }
}
