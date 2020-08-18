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
    @RequestLine("GET /routes?size={size}&offset={offset}")
    Page<Route> list(@Param("size") Long size, @Param("offset") String offset);


    @RequestLine("POST /services/{serviceNameOrId}/routes")
    Route add(@Param("serviceNameOrId") String serviceNameOrId, Route route);

}
