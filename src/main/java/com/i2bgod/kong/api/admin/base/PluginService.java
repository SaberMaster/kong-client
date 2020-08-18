package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.api.admin.KongService;
import com.i2bgod.kong.model.admin.base.Plugin;
import com.i2bgod.kong.model.admin.base.page.Page;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * @author: Lyn
 * @date: 05/08/2020
 */
@KongService(schemaName = "plugins")
@Headers("Content-Type: application/json")
public interface PluginService {
    @RequestLine("GET /plugins/{id}")
    <T> Plugin<T> get(@Param("id") String id);

    @RequestLine("GET /plugins?size={size}&offset={offset}")
    Page<Plugin<Object>> list(@Param("size") Long size, @Param("offset") String offset);
}
