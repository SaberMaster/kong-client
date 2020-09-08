package com.i2bgod.kong;

import com.i2bgod.kong.gson.CustomGsonDecoder;
import feign.Feign;
import feign.gson.GsonEncoder;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Lyn
 * @date: 04/08/2020
 */
public class KongClient {
    final ConcurrentHashMap<String, AdminClient> adminClients;

    public KongClient() {
        this.adminClients = new ConcurrentHashMap<>(16);
    }

    public AdminClient createAdminClient(AdminClientConfig adminClientConfig) {
        if (StringUtils.isBlank(adminClientConfig.getAdminUrl())) {
            throw new IllegalArgumentException("url is empty");
        }

        return this.adminClients.compute(adminClientConfig.getAdminUrl(),
                (key, val) -> {
                    Feign.Builder builder = getFeignBuilder(adminClientConfig);
                    return new AdminClient(builder, adminClientConfig.getAdminUrl());
                });
    }

    @Nullable
    public AdminClient getAdminClient(AdminClientConfig adminClientConfig) {
        return getAdminClient(adminClientConfig.getAdminUrl());
    }

    @Nullable
    public AdminClient getAdminClient(String adminUrl) {
        if (StringUtils.isBlank(adminUrl)) {
            throw new IllegalArgumentException("url is empty");
        }

        return adminClients.get(adminUrl);
    }

    public synchronized AdminClient removeAdminClient(String adminUrl) {
        AdminClient adminClient = this.adminClients.get(adminUrl);
        if (null != adminClient) {
            adminClient.release();
            return this.adminClients.remove(adminUrl);
        }
        return null;
    }


    private Feign.Builder getFeignBuilder(AdminClientConfig adminClientConfig) {
        return Feign.builder()
                .decoder(new CustomGsonDecoder())
                .encoder(new GsonEncoder())
                .decode404()
                .retryer(adminClientConfig.getRetryer())
                .options(adminClientConfig.getOptions())
                .client(adminClientConfig.getClient())
                .logger(adminClientConfig.getLogger())
                .logLevel(adminClientConfig.getLevel());
    }
}
