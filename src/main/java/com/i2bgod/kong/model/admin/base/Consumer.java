package com.i2bgod.kong.model.admin.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.i2bgod.kong.model.admin.base.annotation.KongEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: Lyn
 * @date: 19/08/2020
 */
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@KongEntity(dbEntityName = "consumers")
public class Consumer extends TagBase{

    /**
     * id : ec1a1f6f-2aa4-4e58-93ff-b56368f19b27
     * created_at : 1422386534
     * username : my-username
     * custom_id : my-custom-id
     * tags : ["user-level","low-priority"]
     */

    @JsonProperty("username")
    private String username;
    @JsonProperty("custom_id")
    private String customId;
}
