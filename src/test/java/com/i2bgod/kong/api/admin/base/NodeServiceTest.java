package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.KongClient;
import com.i2bgod.kong.model.admin.base.Node;
import com.i2bgod.kong.model.admin.base.NodeStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author: Lyn
 * @date: 18/08/2020
 */

@Slf4j
class NodeServiceTest {
    private NodeService nodeService;

    @BeforeEach
    void setUp() {
        KongClient kongClientUnderTest = new KongClient( "http://localhost:18001/", null,null , null);
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

