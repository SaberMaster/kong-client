package com.i2bgod.kong.model.admin.base;

import com.google.gson.annotations.SerializedName;
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
public class TagEntity {

    /**
     * entity_name : services
     * entity_id : 2958ef00-ec1d-4a45-8607-cfe3acccf9f5
     * tag : core
     */

    @SerializedName("entity_name")
    private String entityName;
    @SerializedName("entity_id")
    private String entityId;
    @SerializedName("tag")
    private String tag;
}
