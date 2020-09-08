package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.AdminClientConfig;
import com.i2bgod.kong.KongClient;
import com.i2bgod.kong.TestProperties;
import com.i2bgod.kong.model.admin.base.Service;
import lombok.extern.slf4j.Slf4j;
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
class SchemaServiceTest {
    private static SchemaService targetService;

    @BeforeAll
    static void setUp() throws FileNotFoundException {
        TestProperties testConfig = TestProperties.getTestConfig();
        KongClient kongClientUnderTest = new KongClient();
        AdminClientConfig adminClientConfig = new AdminClientConfig();
        adminClientConfig.setAdminUrl(testConfig.getAdminUrl());
        targetService = kongClientUnderTest.createAdminClient(adminClientConfig).getService(SchemaService.class);
    }

    @Test
    @Order(1)
    void testValidate() {
        Service service = new Service();
        service.setHost("www.baidu.com");
        service.setPort(8089);
        service.setPath("/abc");
        service.setProtocol("http");
        targetService.validate("services", service);
    }

    @Test
    @Order(1)
    void testValidate2() {
        Service service = new Service();
        service.setHost("www.baidu.com");
        service.setPort(8089);
        service.setPath("/abc");
        service.setProtocol("http");
        targetService.validate(service);
    }

    @Test
    @Order(2)
    void testGet() {
        Object service = targetService.get("services");
//        Assertions.assertNotNull(list);
        log.info("{}", service);
    }


    @Test
    @Order(3)
    void testGetPlugin() {
        Object plugin = targetService.getPlugin("rate-limiting");
//        Assertions.assertNotNull(route);
        log.info("{}", plugin);
    }
}

