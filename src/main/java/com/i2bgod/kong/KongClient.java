package com.i2bgod.kong;

import com.i2bgod.kong.gson.CustomGsonDecoder;
import com.i2bgod.kong.misc.DefaultOkhttpClientCreator;
import feign.Client;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author: Lyn
 * @date: 04/08/2020
 */
public class KongClient {
    private static okhttp3.OkHttpClient defaultOkHttpClient;

    final ConcurrentHashMap<String, AdminClient> adminClients;

    public KongClient() {
        this.adminClients = new ConcurrentHashMap<>(16);
    }

    public AdminClient getAdminClient(String adminUrl) {
        return this.getAdminClient(adminUrl,null,null,null,null,null);
    }

    public AdminClient getAdminClient(String adminUrl,
                                      @Nullable Retryer retryer,
                                      @Nullable Request.Options options,
                                      @Nullable Client client,
                                      @Nullable Logger logger,
                                      @Nullable Logger.Level level) {
        return this.adminClients.compute(adminUrl,
                (key, val) -> createAdminClient(adminUrl,retryer,options, client,logger, level));
    }

    private AdminClient createAdminClient(String adminUrl,
                                          @Nullable Retryer retryer,
                                          @Nullable Request.Options options,
                                          @Nullable Client client,
                                          @Nullable Logger logger,
                                          @Nullable Logger.Level level) {

        if (StringUtils.isBlank(adminUrl)) {
            throw new IllegalArgumentException("url is empty");
        }
        Feign.Builder builder = getFeignBuilder(retryer, options, client, logger, level);
        return new AdminClient(builder, adminUrl);
    }


    private Feign.Builder getFeignBuilder(Retryer retryer, Request.Options options, Client client, Logger logger, Logger.Level level) {
        return Feign.builder()
                .decoder(new CustomGsonDecoder())
                .encoder(new GsonEncoder())
                .decode404()
                .retryer(Optional
                        .ofNullable(retryer)
                        .orElse(new Retryer.Default(100, SECONDS.toMillis(1), 3)))
                .options(Optional
                        .ofNullable(options)
                        .orElse(new Request.Options(10, TimeUnit.SECONDS, 60, TimeUnit.SECONDS, true)))
                .client(Optional
                        .ofNullable(client)
                        .orElse(new OkHttpClient(getDefaultOkHttpClient())))
                .logger(Optional
                        .ofNullable(logger)
                        .orElse(new Slf4jLogger()))
                .logLevel(Optional
                        .ofNullable(level)
                        .orElse(Logger.Level.FULL));
    }

    private static okhttp3.OkHttpClient getDefaultOkHttpClient() {
        if (null == defaultOkHttpClient) {
            DefaultOkhttpClientCreator defaultOkhttpClientCreator = new DefaultOkhttpClientCreator();
            defaultOkHttpClient = defaultOkhttpClientCreator.okHttpClient();
        }
        return defaultOkHttpClient;
    }
}
