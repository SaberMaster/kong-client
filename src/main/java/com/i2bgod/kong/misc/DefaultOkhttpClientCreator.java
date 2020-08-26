package com.i2bgod.kong.misc;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.CookieJar;
import okhttp3.Dns;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import org.apache.commons.collections.CollectionUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: Lyn
 * @date: 26/08/2020
 */
@Slf4j
public class DefaultOkhttpClientCreator {
    public OkHttpClient okHttpClient() {
        return this.okHttpClient(null, null, null, null);
    }

    public OkHttpClient okHttpClient(
            Cache cache,
            CookieJar cookieJar,
            Dns dns,
            List<Interceptor> interceptors
    ) {

        TrustManager[] trustManagers = getTrustManagers();
        OkHttpClient.Builder builder = getHttpBuilder(
                cache,
                cookieJar,
                dns,
                okHttp3ConnectionPool(),
                interceptors,
                getAllTrustedSslSocketFactory(trustManagers),
                trustManagers
        );
        return builder.build();
    }

    private OkHttpClient.Builder getHttpBuilder(
            Cache cache,
            CookieJar cookieJar,
            Dns dns,
            ConnectionPool connectionPool,
            List<Interceptor> interceptors,
            SSLSocketFactory ssf,
            TrustManager[] trustManagers) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

        HostnameVerifier notVerify = (s, sslSession) -> true;
        builder.connectTimeout(Duration.ofSeconds(10).toMillis(), TimeUnit.MILLISECONDS)
                .readTimeout(Duration.ofSeconds(30).toMillis(), TimeUnit.MILLISECONDS)
                .writeTimeout(Duration.ofSeconds(30).toMillis(), TimeUnit.MILLISECONDS)
                .pingInterval(Duration.ZERO.toMillis(), TimeUnit.MILLISECONDS)
                .connectionPool(connectionPool)
                .followRedirects(true)
                .followSslRedirects(true)
                .retryOnConnectionFailure(true)
                .sslSocketFactory(ssf,(X509TrustManager) trustManagers[0])
                .hostnameVerifier(notVerify)
        ;

        if (CollectionUtils.isNotEmpty(interceptors)) {
            interceptors.forEach(builder::addInterceptor);
        }
        if (null != cache) {
            builder.cache(cache);
        }
        if (null != cookieJar) {
            builder.cookieJar(cookieJar);
        }
        if (null != dns) {
            builder.dns(dns);
        }
        return builder;
    }

    private ConnectionPool okHttp3ConnectionPool() {
        return new ConnectionPool(10,
                Duration.ofMinutes(3).toNanos(),
                TimeUnit.NANOSECONDS);
    }

    private SSLSocketFactory getAllTrustedSslSocketFactory(TrustManager[] trustAllCerts) {
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            return sc.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            log.warn("get SSLContext err: {}", e.getMessage(), e);
            return null;
        }
    }

    private TrustManager[] getTrustManagers() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }
        };
    }

}
