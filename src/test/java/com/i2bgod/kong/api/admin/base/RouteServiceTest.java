package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.KongClient;
import com.i2bgod.kong.TestProperties;
import com.i2bgod.kong.model.admin.base.Route;
import com.i2bgod.kong.model.admin.base.Service;
import com.i2bgod.kong.model.admin.base.page.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.FileNotFoundException;
import java.util.Collections;

/**
 * @author: Lyn
 * @date: 18/08/2020
 */
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RouteServiceTest {
    public static final String TMP_NAME = "test_route";
    private static RouteService targetService;

    private static ServiceService serviceService;


    public static String TMP_SERVICE_NAME = "route_service_name";

    @BeforeAll
    static void setUp() throws FileNotFoundException {
        TestProperties testConfig = TestProperties.getTestConfig();
        KongClient kongClientUnderTest = new KongClient(testConfig.getAdminUrl());
        targetService = kongClientUnderTest.getAdminClient().getService(RouteService.class);

        serviceService = kongClientUnderTest.getAdminClient().getService(ServiceService.class);
        Service service = new Service();
        service.setRetries(0);
        service.setUrl("http://127.0.0.1/abc");
        service.setConnectTimeout(1);
        service.setWriteTimeout(1);
        service.setReadTimeout(1);
        service.setName(TMP_SERVICE_NAME);
        serviceService.add(service);

    }

    @Test
    @Order(1)
    void testAdd() {
        Route route = new Route();
        route.setProtocols(Collections.singletonList("http"));
        route.setMethods(Collections.singletonList("GET"));
        route.setHosts(Collections.singletonList("test_route.com"));
        route.setPaths(Collections.singletonList("/test_route"));
        route.setName(TMP_NAME);
        Route result = targetService.add(TMP_SERVICE_NAME, route);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(2)
    void testList() {
        Page<Route> list = targetService.list(null, null);
        Assertions.assertNotNull(list);
    }


    @Test
    @Order(3)
    void testGet() {
        Route route = targetService.get(TMP_NAME);
        Assertions.assertNotNull(route);
    }


    @Test
    @Order(4)
    void testPatch() {
        Route route = new Route();
        route.setProtocols(Collections.singletonList("https"));
        Route result = targetService.patch(TMP_NAME, route);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("https", result.getProtocols().get(0));
    }

    @Test
    @Order(5)
    void testPut() {
        Route route = new Route();
        route.setProtocols(Collections.singletonList("http"));
        route.setMethods(Collections.singletonList("GET"));
        route.setHosts(Collections.singletonList("test_route.com"));
        route.setPaths(Collections.singletonList("/test_route"));
        Route result = targetService.put(TMP_NAME, route);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(6)
    void testDelete() {
        targetService.delete(TMP_NAME);
        Route result = targetService.get(TMP_NAME);
        Assertions.assertNull(result);
    }


    @AfterAll
    static void afterAll() {
        targetService.delete(TMP_NAME);
        serviceService.delete(TMP_SERVICE_NAME);
    }
}

