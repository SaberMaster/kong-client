package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.KongClient;
import com.i2bgod.kong.TestProperties;
import com.i2bgod.kong.model.admin.base.EnabledPlugin;
import com.i2bgod.kong.model.admin.base.Plugin;
import com.i2bgod.kong.model.admin.base.page.Page;
import com.i2bgod.kong.model.admin.plugin.config.RateLimiting;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.FileNotFoundException;

/**
 * @author: Lyn
 * @date: 18/08/2020
 */
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PluginServiceTest {
    private static PluginService targetService;

    private String tmpPluginId = "be71ac8d-d0fd-4fa4-9603-d16d65926884";

    @BeforeAll
    static void setUp() throws FileNotFoundException {
        TestProperties testConfig = TestProperties.getTestConfig();
        KongClient kongClientUnderTest = new KongClient(testConfig.getAdminUrl());
        targetService = kongClientUnderTest.getAdminClient().getService(PluginService.class);
    }

    @Test
    @Order(1)
    void testAdd() {
        RateLimiting rateLimiting = new RateLimiting();
        rateLimiting.setPolicy("cluster");
        rateLimiting.setLimitBy("all");
        rateLimiting.setMinute(20);

        Plugin<RateLimiting> rateLimitingPlugin = new Plugin<>();
        rateLimitingPlugin.setId(tmpPluginId);
        rateLimitingPlugin.setConfig(rateLimiting);
        rateLimitingPlugin.setEnabled(true);

        Plugin<RateLimiting> result = targetService.add(rateLimitingPlugin);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(2)
    void testList() {
        Page<Plugin<Object>> list = targetService.list(null, null);
        Assertions.assertNotNull(list);
    }


    @Test
    @Order(3)
    void testGet() {
        Plugin<Object> result = targetService.get(tmpPluginId);
        Assertions.assertNotNull(result);
    }


    @Test
    @Order(4)
    void testPatch() {
        Plugin<Object> rateLimitingPlugin = new Plugin<>();
        rateLimitingPlugin.setEnabled(false);
        Plugin<Object> result = targetService.patch(tmpPluginId, rateLimitingPlugin);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(false, result.getEnabled());
    }

    @Test
    @Order(5)
    void testPut() {
        RateLimiting rateLimiting = new RateLimiting();
        rateLimiting.setPolicy("cluster");
        rateLimiting.setLimitBy("all");
        rateLimiting.setMinute(20);

        Plugin<RateLimiting> rateLimitingPlugin = new Plugin<>();
        rateLimitingPlugin.setConfig(rateLimiting);
        rateLimitingPlugin.setEnabled(true);
        Plugin<RateLimiting> result = targetService.put(tmpPluginId, rateLimitingPlugin);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(6)
    void testDelete() {
        targetService.delete(tmpPluginId);
        Plugin<Object> result = targetService.get(tmpPluginId);
        Assertions.assertNull(result);
    }


    @Test
    @Order(7)
    void testEnabled() {
        EnabledPlugin enabled = targetService.enabled();
        Assertions.assertNotNull(enabled);
    }
}

