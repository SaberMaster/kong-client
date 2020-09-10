package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.AdminClientConfig;
import com.i2bgod.kong.KongClient;
import com.i2bgod.kong.TestProperties;
import com.i2bgod.kong.model.admin.base.Route;
import com.i2bgod.kong.model.admin.base.Service;
import com.i2bgod.kong.model.admin.base.YamlConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author: Lyn
 * @date: 18/08/2020
 */

@Disabled
@Slf4j
@Execution(ExecutionMode.CONCURRENT)
class ConfigServiceTest {
    private static ConfigService targetService;

    @BeforeAll
    static void setUp() throws FileNotFoundException {
        TestProperties testConfig = TestProperties.getTestConfig();
        KongClient kongClientUnderTest = new KongClient();
        AdminClientConfig adminClientConfig = new AdminClientConfig();
        String dblessAdminUrl = testConfig.getDblessAdminUrl();
        adminClientConfig.setAdminUrl(dblessAdminUrl);
        targetService = kongClientUnderTest.createAdminClient(adminClientConfig).getService(ConfigService.class);
    }


    @Test
    void testAddYaml() {
        YamlConfig yamlConfig = new YamlConfig();
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

        objects.add(service);
        objects.add(route);
        yamlConfig.setConfig(objects,null);
        Map<String, Map<String, Object>> stringListMap = targetService.addYaml(yamlConfig, null);
        Assertions.assertNotNull(stringListMap);
    }

    @Test
    void testAddJson() {
        Map<String, Object> maps = new HashMap<>();
        Map<String, Object> service = new HashMap<>();
        service.put("retries", 0);
        service.put("url", "http://127.0.0.1/abc");
        service.put("name", "dbless-service-1");
        service.put("id", "be71ac8d-d0fd-4fa4-9603-aaad659268aa");

        Map<String, Object> route = new HashMap<>();
        route.put("hosts", Collections.singletonList("test_route.com"));
        route.put("paths", Collections.singletonList("/test_route"));
        route.put("name", "dbless-route-1");
        route.put("id", "be71ac8d-d0fd-4fa4-9603-aaad6592bcbc");
        route.put("service", service.get("id"));

        maps.put("services", Collections.singletonList(service));
        maps.put("routes", Collections.singletonList(route));
        Map<String, Map<String, Object>> stringListMap = targetService.addJsonWithFormatVersion(maps, null, "1.1");
        Assertions.assertNotNull(stringListMap);
    }
}

