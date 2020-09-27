package com.i2bgod.kong.util;

import com.i2bgod.kong.bean.ClientConfig;
import com.i2bgod.kong.model.admin.base.Plugin;
import com.i2bgod.kong.model.admin.base.Route;
import com.i2bgod.kong.model.admin.base.Service;
import com.i2bgod.kong.model.admin.plugin.config.RateLimiting;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

@Slf4j
@Execution(ExecutionMode.CONCURRENT)
class SchemaUtilsTest {

    private static SchemaUtils schemaUtils;

    @BeforeAll
    static void setUp() throws FileNotFoundException {
        ClientConfig clientConfig = new ClientConfig(Collections.emptyList());
        PluginUtils pluginUtils = new PluginUtils(clientConfig.getPluginConfigClassMap());
        schemaUtils = new SchemaUtils(clientConfig.getKongEntityClassMap(), pluginUtils);
    }


    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    void testToYamlStr() throws Exception {
        LinkedList<Object> objects = new LinkedList<>();
        Service service = new Service();
        service.setRetries(0);
        service.setUrl("http://127.0.0.1/abc");
        service.setConnectTimeout(1);
        service.setWriteTimeout(1);
        service.setReadTimeout(1);
        service.setName("dbless-service-1");
        service.setId("be71ac8d-d0fd-4fa4-9603-aaad659268aa");

        Route route = new Route();
        route.setProtocols(Collections.singletonList("http"));
        route.setMethods(Collections.singletonList("GET"));
        route.setHosts(Collections.singletonList("test_route.com"));
        route.setPaths(Collections.singletonList("/test_route"));
        route.setName("dbless-route-1");
        route.setId("be71ac8d-d0fd-4fa4-9603-aaad6592bcbc");
        Service routeService = new Service();
        routeService.setId(service.getId());
        route.setService(routeService);
        route.setCreateAt(new Date());


        RateLimiting rateLimiting = new RateLimiting();
        rateLimiting.setPolicy("cluster");
        rateLimiting.setLimitBy("all");
        rateLimiting.setMinute(20);

        Plugin<RateLimiting> rateLimitingPlugin = new Plugin<>();
        rateLimitingPlugin.setId("be71ac8d-d0fd-4fa4-9603-aaad6592bmmmm");
        rateLimitingPlugin.setConfig(rateLimiting);
        rateLimitingPlugin.setEnabled(true);
        rateLimitingPlugin.setRoute(route);
        rateLimitingPlugin.setService(service);

        objects.add(service);
        objects.add(route);
        objects.add(rateLimitingPlugin);
        String result = schemaUtils.generateDblessYamlStr(objects, null);
        Assertions.assertTrue(result.contains("limit_by"));
    }
}
