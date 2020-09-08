package com.i2bgod.kong;

import com.i2bgod.kong.misc.DefaultOkhttpClientCreator;
import feign.Client;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author: Lyn
 * @date: 04/08/2020
 */
@Getter
public class AdminClientConfig {
    @Setter
    private String adminUrl;
    @Nullable
    private Retryer retryer  = new Retryer.Default(100, SECONDS.toMillis(1), 3);
    @Nullable
    private Request.Options options = new Request.Options(10, TimeUnit.SECONDS, 60, TimeUnit.SECONDS, true);
    @Nullable
    private Client client = new OkHttpClient(getDefaultOkHttpClient());
    @Nullable
    private feign.Logger logger = new Slf4jLogger();
    @Nullable
    private Logger.Level level = Logger.Level.FULL;


    public void setRetryer(@Nullable Retryer retryer) {
        if (null == retryer) {
            return;
        }
        this.retryer = retryer;
    }

    public void setOptions(@Nullable Request.Options options) {
        if (null == options) {
            return;
        }
        this.options = options;
    }

    public void setClient(@Nullable Client client) {
        if (null == client) {
            return;
        }
        this.client = client;
    }

    public void setLogger(@Nullable Logger logger) {
        if (null == logger) {
            return;
        }
        this.logger = logger;
    }

    public void setLevel(@Nullable Logger.Level level) {
        if (null == level) {
            return;
        }
        this.level = level;
    }

    private static okhttp3.OkHttpClient defaultOkHttpClient;
    private static okhttp3.OkHttpClient getDefaultOkHttpClient() {
        if (null == defaultOkHttpClient) {
            DefaultOkhttpClientCreator defaultOkhttpClientCreator = new DefaultOkhttpClientCreator();
            defaultOkHttpClient = defaultOkhttpClientCreator.okHttpClient();
        }
        return defaultOkHttpClient;
    }
}
