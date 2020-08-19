package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.model.admin.base.Base;
import feign.Param;
import feign.RequestLine;

/**
 * @author: Lyn
 * @date: 18/08/2020
 */
public interface SchemaService {
    // todo: handle return value
    @RequestLine("POST /schemas/{entityName}/validate")
    void add(Base entity);

    @RequestLine("GET /schemas/{name}")
    Object get(@Param("name") String pluginName);

    @RequestLine("GET /schemas/plugins/{pluginName}")
    Object getPlugin(@Param("pluginName") String pluginName);
}
