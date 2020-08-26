package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.KongClient;
import com.i2bgod.kong.TestProperties;
import com.i2bgod.kong.model.admin.base.Node;
import com.i2bgod.kong.model.admin.base.NodeStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

/**
 * @author: Lyn
 * @date: 18/08/2020
 */

@Slf4j
class NodeServiceTest {
    private NodeService nodeService;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        TestProperties testConfig = TestProperties.getTestConfig();
        KongClient kongClientUnderTest = new KongClient(testConfig.getAdminUrl());
        nodeService = kongClientUnderTest.getAdminClient().getService(NodeService.class);
    }


    @Test
    void testGet() {
        Node node = nodeService.get();
        log.info("{}", node);
    }

    @Test
    void testGetStatus() {
        NodeStatus nodeStatus = nodeService.getStatus();
        log.info("{}", nodeStatus);
    }
}

