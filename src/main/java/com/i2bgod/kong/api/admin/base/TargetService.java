package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.api.admin.annoation.KongService;
import com.i2bgod.kong.model.admin.base.Target;
import com.i2bgod.kong.model.admin.base.page.Page;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * @author: Lyn
 * @date: 19/08/2020
 */
@KongService(schemaName = "targets")
    public interface TargetService {
    @RequestLine("POST /upstreams/{upstreamNameOrId}/targets")
    @Headers("Content-Type: application/json")
    Target add(@Param("upstreamNameOrId") String upstreamNameOrId, Target target);

    @RequestLine("GET /upstreams/{upstreamNameOrId}/targets?size={size}&offset={offset}")
    Page<Target> list(@Param("upstreamNameOrId") String upstreamNameOrId, @Param("size") Long size, @Param("offset") String offset);
    @RequestLine("GET /upstreams/{upstreamNameOrId}/targets/all")
    Page<Target> listAll(@Param("upstreamNameOrId") String upstreamNameOrId);

    @RequestLine("DELETE /upstreams/{upstreamNameOrId}/targets/{hostPortOrId}")
    void delete(@Param("upstreamNameOrId") String upstreamNameOrId, @Param("hostPortOrId") String hostPortOrId);

    @RequestLine("POST /upstreams/{upstreamNameOrId}/targets/{hostPortOrId}/{address}/healthy")
    void setAddressHealthy(@Param("upstreamNameOrId") String upstreamNameOrId,@Param("hostPortOrId") String hostPortOrId, @Param("address") String address);

    @RequestLine("POST /upstreams/{upstreamNameOrId}/targets/{hostPortOrId}/unhealthy")
    void setUnHealthy(@Param("upstreamNameOrId") String upstreamNameOrId,@Param("hostPortOrId") String hostPortOrId);
    @RequestLine("POST /upstreams/{upstreamNameOrId}/targets/{hostPortOrId}/healthy")
    void setHealthy(@Param("upstreamNameOrId") String upstreamNameOrId,@Param("hostPortOrId") String hostPortOrId);
}
