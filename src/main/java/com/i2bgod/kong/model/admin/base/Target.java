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
public class Target extends TagBase{

    /**
     * id : a3395f66-2af6-4c79-bea2-1b6933764f80
     * created_at : 1422386534
     * upstream : {"id":"885a0392-ef1b-4de3-aacf-af3f1697ce2c"}
     * target : example.com:8000
     * weight : 100
     * tags : ["user-level","low-priority"]
     */

    @SerializedName("upstream")
    private Upstream upstream;
    @SerializedName("target")
    private String target;
    @SerializedName("weight")
    private Integer weight;
}
