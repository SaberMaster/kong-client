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
@Headers("Content-Type: application/json")
public interface TargetService {
    @RequestLine("POST /upstreams/{upstreamHostPortOrId}/targets")
    Target add(@Param("upstreamHostPortOrId") String upstreamHostPortOrId, Target target);

    @RequestLine("GET /upstreams/{upstreamHostPortOrId}/targets?size={size}&offset={offset}")
    Page<Target> list(@Param("upstreamHostPortOrId") String upstreamHostPortOrId, @Param("size") Long size, @Param("offset") String offset);
    @RequestLine("GET /upstreams/{upstreamHostPortOrId}/targets/all")
    Page<Target> listAll(@Param("upstreamHostPortOrId") String upstreamHostPortOrId);

    @RequestLine("DELETE /upstreams/{upstreamHostPortOrId}/targets/{hostPortOrId}")
    void delete(@Param("upstreamHostPortOrId") String upstreamHostPortOrId, @Param("hostPortOrId") String hostPortOrId);

    @RequestLine("POST /upstreams/{upstreamHostPortOrId}/targets/{hostPortOrId}/{address}/healthy")
    Target setAddressHealthy(@Param("upstreamHostPortOrId") String upstreamHostPortOrId,@Param("hostPortOrId") String hostPortOrId, @Param("address") String address);

    @RequestLine("POST /upstreams/{upstreamHostPortOrId}/targets/{hostPortOrId}/unhealthy")
    Target setUnHealthy(@Param("upstreamHostPortOrId") String upstreamHostPortOrId,@Param("hostPortOrId") String hostPortOrId);
    @RequestLine("POST /upstreams/{upstreamHostPortOrId}/targets/{hostPortOrId}/healthy")
    Target setHealthy(@Param("upstreamHostPortOrId") String upstreamHostPortOrId,@Param("hostPortOrId") String hostPortOrId);
}
