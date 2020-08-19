package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.api.admin.KongService;
import com.i2bgod.kong.model.admin.base.Consumer;
import com.i2bgod.kong.model.admin.base.page.Page;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * @author: Lyn
 * @date: 19/08/2020
 */
@KongService(schemaName = "consumers")
@Headers("Content-Type: application/json")
public interface ConsumerService {
    @RequestLine("POST /consumers")
    Consumer add(Consumer consumer);

    @RequestLine("GET /consumers?size={size}&offset={offset}")
    Page<Consumer> list(@Param("size") Long size, @Param("offset") String offset);

    @RequestLine("GET /consumers/{usernameOrId}")
    Consumer get(@Param("usernameOrId") String usernameOrId);
    @RequestLine("GET /plugins/{pluginId}/consumer")
    Consumer getByPlugin(@Param("pluginId") String pluginId);

    @RequestLine("PATCH /consumers/{usernameOrId}")
    Consumer patch(@Param("usernameOrId") String usernameOrId, Consumer consumer);
    @RequestLine("PATCH /plugins/{pluginId}/consumer")
    Consumer patchByPlugin(@Param("pluginId") String pluginId, Consumer consumer);

    @RequestLine("PUT /consumers/{usernameOrId}")
    Consumer put(@Param("usernameOrId") String usernameOrId, Consumer consumer);
    @RequestLine("PUT /plugins/{pluginId}/consumer")
    Consumer putByPlugin(@Param("pluginId") String pluginId, Consumer consumer);

    @RequestLine("DELETE /consumers/{usernameOrId}")
    void delete(@Param("usernameOrId") String usernameOrId);
}
