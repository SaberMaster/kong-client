package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.api.admin.KongService;
import com.i2bgod.kong.model.admin.base.Route;
import com.i2bgod.kong.model.admin.base.page.Page;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * @author: Lyn
 * @date: 04/08/2020
 */
@KongService(schemaName = "routes")
@Headers("Content-Type: application/json")
public interface RouteService {

    @RequestLine("POST /routes")
    Route add(Route route);
    @RequestLine("POST /services/{serviceNameOrId}/routes")
    Route add(@Param("serviceNameOrId") String serviceNameOrId, Route route);

    @RequestLine("GET /routes?size={size}&offset={offset}")
    Page<Route> list(@Param("size") Long size, @Param("offset") String offset);
    @RequestLine("GET /services/{serviceNameOrId}/routes?size={size}&offset={offset}")
    Page<Route> list(@Param("serviceNameOrId") String certificatesNameOrId, @Param("size") Long size, @Param("offset") String offset);

    @RequestLine("GET /routes/{nameOrId}")
    Route get(@Param("nameOrId") String nameOrId);
    @RequestLine("GET /plugins/{pluginId}/route")
    Route getByPlugin(@Param("pluginId") String pluginId);

    @RequestLine("PATCH /routes/{nameOrId}")
    Route patch(@Param("nameOrId") String nameOrId, Route route);
    @RequestLine("PATCH /plugins/{pluginId}/route")
    Route patchByPlugin(@Param("pluginId") String pluginId, Route route);

    @RequestLine("PUT /routes/{nameOrId}")
    Route put(@Param("nameOrId") String nameOrId, Route route);
    @RequestLine("PUT /plugins/{pluginId}/route")
    Route putByPlugin(@Param("pluginId") String pluginId, Route rotue);

    @RequestLine("DELETE /routes/{nameOrId}")
    void delete(@Param("nameOrId") String nameOrId);
}
