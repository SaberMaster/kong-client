package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.KongClient;
import com.i2bgod.kong.TestProperties;
import com.i2bgod.kong.model.admin.base.Node;
import com.i2bgod.kong.model.admin.base.NodeStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

/**
 * @author: Lyn
 * @date: 18/08/2020
 */

@Slf4j
class NodeServiceTest {
    private static NodeService nodeService;

    @BeforeAll
    static void setUp() throws FileNotFoundException {
        TestProperties testConfig = TestProperties.getTestConfig();
        KongClient kongClientUnderTest = new KongClient();
        nodeService = kongClientUnderTest.getAdminClient(testConfig.getAdminUrl()).getService(NodeService.class);
    }


    @Test
    void testGet() {
        Node node = nodeService.get();
        Assertions.assertNotNull(node);
    }

    @Test
    void testGetStatus() {
        NodeStatus nodeStatus = nodeService.getStatus();
        Assertions.assertNotNull(nodeStatus);
    }
}

