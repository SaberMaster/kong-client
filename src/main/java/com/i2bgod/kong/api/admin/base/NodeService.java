package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.api.admin.annoation.KongService;
import com.i2bgod.kong.model.admin.base.Node;
import com.i2bgod.kong.model.admin.base.NodeStatus;
import feign.Headers;
import feign.RequestLine;

/**
 * @author: Lyn
 * @date: 10/08/2020
 */
@KongService(schemaName = "nodes")
@Headers("Content-Type: application/json")
public interface NodeService {
    @RequestLine("GET /")
    Node get();

    @RequestLine("GET /status")
    NodeStatus getStatus();
}
