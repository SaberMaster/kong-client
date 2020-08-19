package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.api.admin.KongService;
import com.i2bgod.kong.model.admin.base.Upstream;
import com.i2bgod.kong.model.admin.base.UpstreamHealth;
import com.i2bgod.kong.model.admin.base.page.Page;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * @author: Lyn
 * @date: 19/08/2020
 */
@KongService(schemaName = "upstreams")
@Headers("Content-Type: application/json")
public interface UpstreamService {
    @RequestLine("POST /upstreams")
    Upstream add(Upstream upstream);

    @RequestLine("GET /upstreams?size={size}&offset={offset}")
    Page<Upstream> list(@Param("size") Long size, @Param("offset") String offset);

    @RequestLine("GET /upstreams/{nameOrId}")
    Upstream get(@Param("nameOrId") String nameOrId);
    @RequestLine("GET /targets/{targetHostPortOrId}/upstream")
    Upstream getByTarget(@Param("targetHostPortOrId") String targetHostPortOrId);

    @RequestLine("PATCH /upstreams/{nameOrId}")
    Upstream patch(@Param("nameOrId") String nameOrId, Upstream upstream);
    @RequestLine("PATCH /targets/{targetHostPortOrId}/upstream")
    Upstream patchByTarget(@Param("targetHostPortOrId") String targetHostPortOrId, Upstream upstream);


    @RequestLine("PUT /upstreams/{nameOrId}")
    Upstream put(@Param("nameOrId") String nameOrId, Upstream upstream);
    @RequestLine("PUT /targets/{targetHostPortOrId}/upstream")
    Upstream putByTarget(@Param("targetHostPortOrId") String targetHostPortOrId, Upstream upstream);

    @RequestLine("DELETE /upstreams/{nameOrId}")
    void delete(@Param("nameOrId") String nameOrId);
    @RequestLine("DELETE /targets/{targetHostPortOrId}/upstream")
    void deleteByTarget(@Param("targetHostPortOrId") String targetHostPortOrId);

    @RequestLine("GET /upstreams/{nameOrId}/health")
    UpstreamHealth getHealth();
}
