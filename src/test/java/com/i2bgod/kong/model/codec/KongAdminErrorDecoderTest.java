package com.i2bgod.kong.model.codec;

import com.i2bgod.kong.KongClient;
import com.i2bgod.kong.api.admin.base.ServiceService;
import com.i2bgod.kong.exception.KongClientException;
import com.i2bgod.kong.model.admin.base.Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class KongAdminErrorDecoderTest {

    private static ServiceService serviceService;

    @BeforeAll
    static void setUp() {
        KongClient kongClientUnderTest = new KongClient( "http://localhost:18001/");
        serviceService = kongClientUnderTest.getAdminClient().getService(ServiceService.class);
    }

    @Test
    void testAddServiceFail() {
        Assertions.assertThrows(KongClientException.class,()-> {
            Service service = new Service();
            // wrong scheme
            service.setUrl("testScheme://127.0.0.1/abc");
            service.setName("test-fail-service");
            serviceService.add(service);
        });
    }
}
