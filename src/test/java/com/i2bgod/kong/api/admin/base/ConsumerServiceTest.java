package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.AdminClientConfig;
import com.i2bgod.kong.KongClient;
import com.i2bgod.kong.TestProperties;
import com.i2bgod.kong.model.admin.base.Consumer;
import com.i2bgod.kong.model.admin.base.page.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.FileNotFoundException;

/**
 * @author: Lyn
 * @date: 18/08/2020
 */
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Execution(ExecutionMode.CONCURRENT)
class ConsumerServiceTest {
    private static ConsumerService targetService;

    private static String TMP_NAME = "test-consumer";

    @BeforeAll
    static void setUp() throws FileNotFoundException {
        TestProperties testConfig = TestProperties.getTestConfig();
        KongClient kongClientUnderTest = new KongClient();
        AdminClientConfig adminClientConfig = new AdminClientConfig();
        adminClientConfig.setAdminUrl(testConfig.getAdminUrl());
        targetService = kongClientUnderTest.createAdminClient(adminClientConfig).getService(ConsumerService.class);
    }

    @Test
    @Order(1)
    void testAdd() {
        Consumer consumer = new Consumer();
        consumer.setUsername(TMP_NAME);
        consumer.setCustomId("custom_id");

        Consumer result = targetService.add(consumer);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(2)
    void testList() {
        Page<Consumer> list = targetService.list(null, null);
        Assertions.assertNotNull(list);
    }


    @Test
    @Order(3)
    void testGet() {
        Consumer consumer = targetService.get(TMP_NAME);
        Assertions.assertNotNull(consumer);
    }


    @Test
    @Order(4)
    void testPatch() {
        Consumer consumer = new Consumer();
        consumer.setCustomId("custom_id_2");

        Consumer result = targetService.patch(TMP_NAME, consumer);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(consumer.getCustomId(), result.getCustomId());
    }

    @Test
    @Order(5)
    void testPut() {
        Consumer consumer = new Consumer();
        consumer.setCustomId("custom_id");
        Consumer result = targetService.put(TMP_NAME, consumer);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(6)
    void testDelete() {
        targetService.delete(TMP_NAME);
        Consumer result = targetService.get(TMP_NAME);
        Assertions.assertNull(result);
    }

    @AfterAll
    static void afterAll() {
        targetService.delete(TMP_NAME);
    }
}

