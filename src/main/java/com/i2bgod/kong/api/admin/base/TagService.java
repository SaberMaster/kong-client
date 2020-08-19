package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.api.admin.KongService;
import com.i2bgod.kong.model.admin.base.TagEntity;
import com.i2bgod.kong.model.admin.base.page.Page;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * @author: Lyn
 * @date: 10/08/2020
 */
@KongService(schemaName = "tags")
@Headers("Content-Type: application/json")
public interface TagService {
    @RequestLine("GET /tags/{tags}?size={size}&offset={offset}")
    Page<TagEntity> list(@Param("tags") String tags, @Param("size") Long size, @Param("offset") String offset);
}
