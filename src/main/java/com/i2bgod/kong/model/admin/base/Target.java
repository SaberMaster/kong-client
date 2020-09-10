package com.i2bgod.kong.model.admin.base;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.i2bgod.kong.model.adapter.DateDoubleFormatTypeAdapter;
import com.i2bgod.kong.model.admin.base.annotation.KongEntity;
import com.i2bgod.kong.model.admin.base.annotation.KongFK;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author: Lyn
 * @date: 19/08/2020
 */
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@KongEntity(dbEntityName = "targets")
public class Target {

    /**
     * id : a3395f66-2af6-4c79-bea2-1b6933764f80
     * created_at : 1422386534.123
     * upstream : {"id":"885a0392-ef1b-4de3-aacf-af3f1697ce2c"}
     * target : example.com:8000
     * weight : 100
     * tags : ["user-level","low-priority"]
     */

    @SerializedName("id")
    private String id;
    @SerializedName("upstream")
    @KongFK
    private Upstream upstream;
    @SerializedName("target")
    private String target;
    @SerializedName("weight")
    private Integer weight;
    @SerializedName("created_at")
    @JsonAdapter(DateDoubleFormatTypeAdapter.class)
    private Date createAt;
    @SerializedName("tags")
    private List<String> tags;
}
