package com.i2bgod.kong.model.codec;

import com.i2bgod.kong.AdminClientConfig;
import com.i2bgod.kong.KongClient;
import com.i2bgod.kong.TestProperties;
import com.i2bgod.kong.api.admin.base.ServiceService;
import com.i2bgod.kong.exception.KongClientException;
import com.i2bgod.kong.model.admin.base.Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.IOException;

@Execution(ExecutionMode.CONCURRENT)
class KongAdminErrorDecoderTest {

    private static ServiceService targetService;

    @BeforeAll
    static void setUp() throws IOException {
        TestProperties testConfig = TestProperties.getTestConfig();
        KongClient kongClientUnderTest = new KongClient();
        AdminClientConfig adminClientConfig = new AdminClientConfig();
        adminClientConfig.setAdminUrl(testConfig.getAdminUrl());
        targetService = kongClientUnderTest.createAdminClient(adminClientConfig).getService(ServiceService.class);
    }

    @Test
    void testAddServiceFail() {
        Assertions.assertThrows(KongClientException.class,()-> {
            Service service = new Service();
            // wrong scheme
            service.setUrl("testScheme://127.0.0.1/abc");
            service.setName("test-fail-service");
            targetService.add(service);
        });
    }
}
