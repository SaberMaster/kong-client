package com.i2bgod.kong;

import com.i2bgod.kong.exception.KongClientException;
import com.i2bgod.kong.model.codec.KongAdminErrorDecoder;
import com.i2bgod.kong.utils.ConfigUtils;
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
    private Feign.Builder feignBuilder;
    private String url;

    private Map<Class<?>, Object> serviceMap;

    public AdminClient(Feign.Builder feignBuilder, String url) {
        this.feignBuilder = feignBuilder;
        feignBuilder.errorDecoder(new KongAdminErrorDecoder());
        this.url = url;
        createProxy(feignBuilder, url);
    }

    public Feign.Builder getFeignBuilder() {
        return feignBuilder;
    }

    void setFeignBuilder(Feign.Builder feignBuilder) {
        this.feignBuilder = feignBuilder;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T getService(Class<T> clz) {
        return (T) serviceMap.get(clz);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T getService(String name) {
        Map<String, Class<?>> serviceClassMap = ConfigUtils.getClientConfig().getServiceClassMap();
        if (MapUtils.isEmpty(serviceClassMap)) {
            throw new KongClientException("no service impl found");
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

    void setUrl(String url) {
        this.url = url;
    }

    private void createProxy(Feign.Builder feignBuilder, String url) {
        Map<String, Class<?>> serviceClassMap = ConfigUtils.getClientConfig().getServiceClassMap();
        serviceMap = new HashMap<>(serviceClassMap.size());
        serviceClassMap.forEach((k, v) -> serviceMap.put(v, feignBuilder.target(v, url)));
    }
}
