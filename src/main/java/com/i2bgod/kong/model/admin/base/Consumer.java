package com.i2bgod.kong.model.admin.base;

import com.google.gson.annotations.SerializedName;
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
public class Consumer extends TagBase{

    /**
     * id : ec1a1f6f-2aa4-4e58-93ff-b56368f19b27
     * created_at : 1422386534
     * username : my-username
     * custom_id : my-custom-id
     * tags : ["user-level","low-priority"]
     */

    @SerializedName("username")
    private String username;
    @SerializedName("custom_id")
    private String customId;
}
