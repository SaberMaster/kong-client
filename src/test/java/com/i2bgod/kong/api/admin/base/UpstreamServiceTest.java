package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.KongClient;
import com.i2bgod.kong.TestProperties;
import com.i2bgod.kong.model.admin.base.Upstream;
import com.i2bgod.kong.model.admin.base.UpstreamHealth;
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

/**
 * @author: Lyn
 * @date: 18/08/2020
 */
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UpstreamServiceTest {
    private static UpstreamService targetService;
    private static String TMP_NAME = "test-upstream";

    @BeforeAll
    static void setUp() throws FileNotFoundException {
        TestProperties testConfig = TestProperties.getTestConfig();
        KongClient kongClientUnderTest = new KongClient(testConfig.getAdminUrl());
        targetService = kongClientUnderTest.getAdminClient().getService(UpstreamService.class);
    }

    @Test
    @Order(1)
    void testAdd() {
        Upstream upstream = new Upstream();
        upstream.setName(TMP_NAME);
        upstream.setAlgorithm("round-robin");

        Upstream result = targetService.add(upstream);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(2)
    void testList() {
        Page<Upstream> list = targetService.list(null, null);
        Assertions.assertNotNull(list);
    }


    @Test
    @Order(3)
    void testGet() {
        Upstream result = targetService.get(TMP_NAME);
        Assertions.assertNotNull(result);
    }


    @Test
    @Order(4)
    void testPatch() {
        Upstream upstream = new Upstream();
        upstream.setAlgorithm("least-connections");
        Upstream result = targetService.patch(TMP_NAME, upstream);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(upstream.getAlgorithm(), result.getAlgorithm());
    }

    @Test
    @Order(5)
    void testPut() {
        Upstream upstream = new Upstream();
        upstream.setAlgorithm("round-robin");
        Upstream result = targetService.put(TMP_NAME, upstream);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(5)
    void testHealth() {
        UpstreamHealth result = targetService.getHealth(TMP_NAME);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(6)
    void testDelete() {
        targetService.delete(TMP_NAME);
        Upstream result = targetService.get(TMP_NAME);
        Assertions.assertNull(result);
    }

    @AfterAll
    static void afterAll() {
        targetService.delete(TMP_NAME);
    }
}

