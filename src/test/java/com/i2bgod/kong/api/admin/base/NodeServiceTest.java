package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.AdminClientConfig;
import com.i2bgod.kong.KongClient;
import com.i2bgod.kong.TestProperties;
import com.i2bgod.kong.model.admin.base.Node;
import com.i2bgod.kong.model.admin.base.NodeStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.IOException;

/**
 * @author: Lyn
 * @date: 18/08/2020
 */

@Slf4j
@Execution(ExecutionMode.CONCURRENT)
class NodeServiceTest {
    private static NodeService targetService;

    @BeforeAll
    static void setUp() throws IOException {
        TestProperties testConfig = TestProperties.getTestConfig();
        KongClient kongClientUnderTest = new KongClient();
        AdminClientConfig adminClientConfig = new AdminClientConfig();
        adminClientConfig.setAdminUrl(testConfig.getAdminUrl());
        targetService = kongClientUnderTest.createAdminClient(adminClientConfig).getService(NodeService.class);
    }


    @Test
    void testGet() {
        Node node = targetService.get();
        Assertions.assertNotNull(node);
    }

    @Test
    void testGetStatus() {
        NodeStatus nodeStatus = targetService.getStatus();
        Assertions.assertNotNull(nodeStatus);
    }
}

