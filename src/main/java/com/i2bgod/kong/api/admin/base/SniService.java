package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.api.admin.KongService;
import com.i2bgod.kong.model.admin.base.Sni;
import com.i2bgod.kong.model.admin.base.page.Page;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * @author: Lyn
 * @date: 04/08/2020
 */
@KongService(schemaName = "snis")
@Headers("Content-Type: application/json")
public interface SniService {
    @RequestLine("POST /snis")
    Sni add(Sni sni);
    @RequestLine("POST /certificates/{certificatesNameOrId}/snis")
    Sni add(@Param("certificatesNameOrId") String certificatesNameOrId, Sni sni);

    @RequestLine("GET /snis?size={size}&offset={offset}")
    Page<Sni> list(@Param("size") Long size, @Param("offset") String offset);
    @RequestLine("GET /certificates/{certificatesNameOrId}/snis?size={size}&offset={offset}")
    Page<Sni> list(@Param("certificatesNameOrId") String certificatesNameOrId, @Param("size") Long size, @Param("offset") String offset);

    @RequestLine("GET /snis/{nameOrId}")
    Sni get(@Param("nameOrId") String nameOrId);

    @RequestLine("PATCH /snis/{nameOrId}")
    Sni patch(@Param("nameOrId") String nameOrId, Sni service);

    @RequestLine("PUT /snis/{nameOrId}")
    Sni put(@Param("nameOrId") String nameOrId, Sni service);

    @RequestLine("DELETE /snis/{nameOrId}")
    void delete(@Param("nameOrId") String nameOrId);
}
