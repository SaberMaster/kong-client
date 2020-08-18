package com.i2bgod.kong;

import feign.Client;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author: Lyn
 * @date: 04/08/2020
 */
public class KongClient {
    private String adminUrl;
    private AdminClient adminClient;
    private static okhttp3.OkHttpClient okHttpClient;

    public AdminClient getAdminClient() {
        return adminClient;
    }

    public String getAdminUrl() {
        return adminUrl;
    }

    public KongClient(String adminUrl, @Nullable Retryer retryer, @Nullable Request.Options options, @Nullable Client client) {
        this.adminUrl = adminUrl;

        if (StringUtils.isBlank(adminUrl)) {
            throw new IllegalArgumentException("url is empty");
        }

        Feign.Builder builder = getFeignBuilder(retryer, options, client);
        this.adminClient = new AdminClient(builder, adminUrl);
    }

    private Feign.Builder getFeignBuilder(Retryer retryer, Request.Options options, Client client) {
        return Feign.builder()
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .retryer(Optional
                        .ofNullable(retryer)
                        .orElse(new Retryer.Default(100, SECONDS.toMillis(1), 3)))
                .options(Optional
                        .ofNullable(options)
                        .orElse(new Request.Options(10, TimeUnit.SECONDS, 60, TimeUnit.SECONDS, true)))
                .client(Optional
                        .ofNullable(client)
                        .orElse(new OkHttpClient(getDefaultOkHttpClient())))
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL);
    }

    private static okhttp3.OkHttpClient getDefaultOkHttpClient() {
        if (null == okHttpClient) {
            // todo: set init properties for ok http
           okHttpClient = new okhttp3.OkHttpClient();
        }
        return okHttpClient;
    }
}
