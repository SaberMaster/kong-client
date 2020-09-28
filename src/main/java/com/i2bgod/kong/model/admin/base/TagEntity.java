package com.i2bgod.kong.model.admin.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.i2bgod.kong.model.admin.base.annotation.KongEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: Lyn
 * @date: 18/08/2020
 */
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@KongEntity(dbEntityName = "tags")
public class TagEntity {

    /**
     * entity_name : services
     * entity_id : 2958ef00-ec1d-4a45-8607-cfe3acccf9f5
     * tag : core
     */

    @JsonProperty("entity_name")
    private String entityName;
    @JsonProperty("entity_id")
    private String entityId;
    @JsonProperty("tag")
    private String tag;
}
