package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.api.admin.KongService;
import com.i2bgod.kong.model.admin.base.Service;
import com.i2bgod.kong.model.admin.base.page.Page;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * @author: Lyn
 * @date: 04/08/2020
 */
@KongService(schemaName = "services")
@Headers("Content-Type: application/json")
public interface ServiceService {
    @RequestLine("POST /services")
    Service add(Service service);
    @RequestLine("POST /certificates/{certificatesNameOrId}/services")
    Service add(@Param("certificatesNameOrId") String certificatesNameOrId, Service service);

    @RequestLine("GET /services?size={size}&offset={offset}")
    Page<Service> list(@Param("size") Long size, @Param("offset") String offset);
    @RequestLine("GET /certificates/{certificatesNameOrId}/services?size={size}&offset={offset}")
    Page<Service> list(@Param("certificatesNameOrId") String certificatesNameOrId, @Param("size") Long size, @Param("offset") String offset);

    @RequestLine("GET /services/{nameOrId}")
    Service get(@Param("nameOrId") String nameOrId);
    @RequestLine("GET /routes/{routeNameOrId}/service")
    Service getByRoute(@Param("routeNameOrId") String routeNameOrId);
    @RequestLine("GET /plugins/{pluginId}/service")
    Service getByPlugin(@Param("pluginId") String pluginId);

    @RequestLine("PATCH /services/{nameOrId}")
    Service patch(@Param("nameOrId") String nameOrId, Service service);
    @RequestLine("PATCH /routes/{routeNameOrId}/service")
    Service patchByRoute(@Param("routeNameOrId") String routeNameOrId, Service service);
    @RequestLine("PATCH /plugins/{pluginId}/service")
    Service patchByPlugin(@Param("pluginId") String pluginId, Service service);

    @RequestLine("PUT /services/{nameOrId}")
    Service put(@Param("nameOrId") String nameOrId, Service service);
    @RequestLine("PUT /routes/{routeNameOrId}/service")
    Service putByRoute(@Param("routeNameOrId") String routeNameOrId, Service service);
    @RequestLine("PUT /plugins/{pluginId}/service")
    Service putByPlugin(@Param("pluginId") String pluginId, Service service);

    @RequestLine("DELETE /services/{nameOrId}")
    void delete(@Param("nameOrId") String nameOrId);
    @RequestLine("DELETE /routes/{routeNameOrId}/service")
    void deleteByRoute(@Param("routeNameOrId") String routeNameOrId);
}
