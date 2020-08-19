package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.api.admin.KongService;
import com.i2bgod.kong.model.admin.base.CaCertificate;
import com.i2bgod.kong.model.admin.base.page.Page;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * @author: Lyn
 * @date: 19/08/2020
 */
@KongService(schemaName = "ca_certificates")
@Headers("Content-Type: application/json")
public interface CaCertificateService {
    @RequestLine("POST /ca_certificates")
    CaCertificate add(CaCertificate caCertificate);

    @RequestLine("GET /ca_certificates?size={size}&offset={offset}")
    Page<CaCertificate> list(@Param("size") Long size, @Param("offset") String offset);

    @RequestLine("GET /ca_certificates/{id}")
    CaCertificate get(@Param("id") String id);

    @RequestLine("PATCH /ca_certificates/{id}")
    CaCertificate patch(@Param("id") String id, CaCertificate caCertificate);

    @RequestLine("PUT /ca_certificates/{id}")
    CaCertificate put(@Param("id") String id, CaCertificate caCertificate);

    @RequestLine("DELETE /ca_certificates/{id}")
    void delete(@Param("id") String id);
}
