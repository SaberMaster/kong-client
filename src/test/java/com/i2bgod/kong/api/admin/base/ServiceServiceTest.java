package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.KongClient;
import com.i2bgod.kong.model.admin.base.Service;
import com.i2bgod.kong.model.admin.base.page.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * @author: Lyn
 * @date: 18/08/2020
 */
@Slf4j
class ServiceServiceTest {
    private ServiceService serviceService;

    @BeforeEach
    void setUp() {
        KongClient kongClientUnderTest = new KongClient( "http://localhost:18001/", null,null , null);
        serviceService = kongClientUnderTest.getAdminClient().getService(ServiceService.class);
    }

    @Test
    void testService() {
        Page<Service> list = serviceService.list(null, null);
        log.info("{}", list);
    }

    @Test
    void testAddService() {
        Service service = new Service();
        service.setRetries(0);
        service.setUrl("http://127.0.0.1/abc");
        service.setConnectTimeout(1);
        service.setWriteTimeout(1);
        service.setReadTimeout(1);
        service.setName("test-service");
        service.setCreateAt(new Date());
        service.setUpdateAt(new Date());

        Service result = serviceService.add(service);
        log.info("{}", result);
    }
}

