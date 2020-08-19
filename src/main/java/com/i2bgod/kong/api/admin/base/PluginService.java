package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.api.admin.KongService;
import com.i2bgod.kong.model.admin.base.Plugin;
import com.i2bgod.kong.model.admin.base.page.Page;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;
import java.util.Map;


/**
 * @author: Lyn
 * @date: 05/08/2020
 */
@KongService(schemaName = "plugins")
@Headers("Content-Type: application/json")
public interface PluginService {

    @RequestLine("POST /routes")
    <T> Plugin<T> add(Plugin<T> route);
    @RequestLine("POST /routes/{routeNameOrId}/plugins")
    <T> Plugin<T> addByRoute(@Param("routeNameOrId") String routeNameOrId, Plugin<T> route);
    @RequestLine("POST /services/{serviceNameOrId}/plugins")
    <T> Plugin<T> addByService(@Param("serviceNameOrId") String serviceNameOrId, Plugin<T> route);
    @RequestLine("POST /consumers/{consumerNameOrId}/plugins")
    <T> Plugin<T> addByConsumer(@Param("consumerNameOrId") String consumerNameOrId, Plugin<T> route);

    @RequestLine("GET /plugins?size={size}&offset={offset}")
    Page<Plugin<Object>> list(@Param("size") Long size, @Param("offset") String offset);
    @RequestLine("GET /routes/{routeNameOrId}/plugins?size={size}&offset={offset}")
    <T> Page<Plugin<T>> listByRoute(@Param("routeNameOrId") String routeNameOrId, @Param("size") Long size, @Param("offset") String offset);
    @RequestLine("GET /services/{serviceNameOrId}/plugins?size={size}&offset={offset}")
    <T> Page<Plugin<T>> listByService(@Param("serviceNameOrId") String serviceNameOrId, @Param("size") Long size, @Param("offset") String offset);
    @RequestLine("GET /consumers/{consumerNameOrId}/plugins?size={size}&offset={offset}")
    <T> Page<Plugin<T>> listByConsumer(@Param("consumerNameOrId") String consumerNameOrId, @Param("size") Long size, @Param("offset") String offset);

    @RequestLine("GET /plugins/{id}")
    <T> Plugin<T> get(@Param("id") String id);

    @RequestLine("PATCH /plugins/{id}")
    <T> Plugin<T> patch(@Param("id") String id, Plugin<T> route);

    @RequestLine("PUT /plugins/{id}")
    <T> Plugin<T> put(@Param("id") String id, Plugin<T> route);

    @RequestLine("DELETE /plugins/{id}")
    void delete(@Param("id") String id);

    @RequestLine("DELETE /plugins/enabled")
    Map<String, List<String>> enabled();
}
