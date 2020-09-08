package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.AdminClientConfig;
import com.i2bgod.kong.KongClient;
import com.i2bgod.kong.TestProperties;
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
class ServiceServiceTest {
    public static final String TMP_NAME = "test-service";
    private static ServiceService targetService;

    @BeforeAll
    static void setUp() throws FileNotFoundException {
        TestProperties testConfig = TestProperties.getTestConfig();
        KongClient kongClientUnderTest = new KongClient();
        AdminClientConfig adminClientConfig = new AdminClientConfig();
        adminClientConfig.setAdminUrl(testConfig.getAdminUrl());
        targetService = kongClientUnderTest.createAdminClient(adminClientConfig).getService(ServiceService.class);
    }

    @Test
    @Order(1)
    void testAdd() {
        Service service = new Service();
        service.setRetries(0);
        service.setUrl("http://127.0.0.1/abc");
        service.setConnectTimeout(1);
        service.setWriteTimeout(1);
        service.setReadTimeout(1);
        service.setName(TMP_NAME);

        Service result = targetService.add(service);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(2)
    void testList() {
        Page<Service> list = targetService.list(null, null);
        Assertions.assertNotNull(list);
    }

    @Test
    @Order(3)
    void testGet() {
        Service service = targetService.get(TMP_NAME);
        Assertions.assertNotNull(service);
    }


    @Test
    @Order(4)
    void testPatch() {
        Service service = new Service();
        service.setReadTimeout(2);
        Service result = targetService.patch(TMP_NAME, service);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.getReadTimeout());
    }

    @Test
    @Order(5)
    void testPut() {
        Service service = new Service();
        service.setRetries(0);
        service.setUrl("http://127.0.0.1/abc");
        service.setConnectTimeout(1);
        service.setWriteTimeout(1);
        service.setReadTimeout(1);
        Service result = targetService.put(TMP_NAME, service);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(6)
    void testDelete() {
        targetService.delete(TMP_NAME);
        Service service = targetService.get(TMP_NAME);
        Assertions.assertNull(service);
    }


    @AfterAll
    static void afterAll() {
        targetService.delete(TMP_NAME);
    }
}

