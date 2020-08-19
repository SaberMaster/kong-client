package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.api.admin.KongService;
import com.i2bgod.kong.model.admin.base.Certificate;
import com.i2bgod.kong.model.admin.base.page.Page;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * @author: Lyn
 * @date: 19/08/2020
 */
@KongService(schemaName = "certificates")
@Headers("Content-Type: application/json")
public interface CertificateService {
    @RequestLine("POST /certificates")
    Certificate add(Certificate certificate);

    @RequestLine("GET /certificates?size={size}&offset={offset}")
    Page<Certificate> list(@Param("size") Long size, @Param("offset") String offset);

    @RequestLine("GET /certificates/{id}")
    Certificate get(@Param("id") String id);

    @RequestLine("PATCH /certificates/{id}")
    Certificate patch(@Param("id") String id, Certificate certificate);

    @RequestLine("PUT /certificates/{id}")
    Certificate put(@Param("id") String id, Certificate certificate);

    @RequestLine("DELETE /certificates/{id}")
    void delete(@Param("id") String id);
}
