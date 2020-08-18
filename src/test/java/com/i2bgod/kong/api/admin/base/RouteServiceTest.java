package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.KongClient;
import com.i2bgod.kong.model.admin.base.Route;
import com.i2bgod.kong.model.admin.base.page.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

/**
 * @author: Lyn
 * @date: 18/08/2020
 */
@Slf4j
class RouteServiceTest {
    private RouteService routeService;

    @BeforeEach
    void setUp() {
        KongClient kongClientUnderTest = new KongClient( "http://localhost:18001/", null,null , null);
        routeService = kongClientUnderTest.getAdminClient().getService(RouteService.class);
    }

    @Test
    void testList() {
        try {
            Page<Route> list = routeService.list(null, null);
            log.info("{}", list);
        } catch (Exception e) {
            log.error("test", e);
        }
    }

    @Test
    void testAdd() {
        try {
            Route route = new Route();
            route.setProtocols(Collections.singletonList("http"));
            route.setMethods(Collections.singletonList("GET"));
            route.setHosts(Collections.singletonList("test_route.com"));
            route.setPaths(Collections.singletonList("/test_route"));
            route.setName("test_route");

            Route list = routeService.add("test-service", route);
            log.info("{}", list);
        } catch (Exception e) {
            log.error("test", e);
        }
    }
}

