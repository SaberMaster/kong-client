package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.KongClient;
import com.i2bgod.kong.TestProperties;
import com.i2bgod.kong.model.admin.base.Service;
import com.i2bgod.kong.model.admin.base.page.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
class ServiceServiceTest {
    private ServiceService serviceService;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        TestProperties testConfig = TestProperties.getTestConfig();
        KongClient kongClientUnderTest = new KongClient(testConfig.getAdminUrl());
        serviceService = kongClientUnderTest.getAdminClient().getService(ServiceService.class);
    }

    @Test
    @Order(1)
    void testAddService() {
        Service service = new Service();
        service.setRetries(0);
        service.setUrl("http://127.0.0.1/abc");
        service.setConnectTimeout(1);
        service.setWriteTimeout(1);
        service.setReadTimeout(1);
        service.setName("test-service");

        Service result = serviceService.add(service);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(2)
    void testListService() {
        Page<Service> list = serviceService.list(null, null);
        Assertions.assertNotNull(list);
    }

    @Test
    @Order(3)
    void testGetService() {
        Service service = serviceService.get("test-service");
        Assertions.assertNotNull(service);
    }


    @Test
    @Order(4)
    void testPatchService() {
        Service service = new Service();
        service.setReadTimeout(2);
        Service result = serviceService.patch("test-service", service);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.getReadTimeout());
    }

    @Test
    @Order(5)
    void testPutService() {
        Service service = new Service();
        service.setRetries(0);
        service.setUrl("http://127.0.0.1/abc");
        service.setConnectTimeout(1);
        service.setWriteTimeout(1);
        service.setReadTimeout(1);
        Service result = serviceService.put("test-service", service);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(6)
    void testDeleteService() {
        serviceService.delete("test-service");
        Service service = serviceService.get("test-service");
        Assertions.assertNull(service);
    }
}

