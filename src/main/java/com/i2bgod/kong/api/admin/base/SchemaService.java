package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.api.admin.annoation.KongService;
import com.i2bgod.kong.exception.KongClientException;
import com.i2bgod.kong.model.admin.base.Base;
import com.i2bgod.kong.model.admin.base.annotation.KongEntity;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: Lyn
 * @date: 18/08/2020
 */
@KongService(schemaName = "schemas")
@Headers("Content-Type: application/json")
public interface SchemaService {
    // todo: handle return value

    /**
     * error info will be throw by exception
     * @param entityName
     * @param entity
     */
    @RequestLine("POST /schemas/{entityName}/validate")
    <T extends Base> void validate(@Param("entityName") String entityName, T entity);

    default <T extends Base> void validate(T entity) {
        KongEntity annotation = entity.getClass().getAnnotation(KongEntity.class);
        if (null == annotation || StringUtils.isBlank(annotation.dbEntityName())) {
            throw new KongClientException("KongEntity annotation's db_entity_name is blank");
        }
        validate(annotation.dbEntityName(), entity);
    }

    @RequestLine("GET /schemas/{name}")
    Object get(@Param("name") String entityName);

    @RequestLine("GET /schemas/plugins/{pluginName}")
    Object getPlugin(@Param("pluginName") String pluginName);
}
