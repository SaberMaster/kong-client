package com.i2bgod.kong.model.admin.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.i2bgod.kong.model.adapter.DateDoubleJsonDeserializer;
import com.i2bgod.kong.model.adapter.DateDoubleJsonSerializer;
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

    @JsonProperty("id")
    private String id;
    @JsonProperty("upstream")
    @KongFK
    private Upstream upstream;
    @JsonProperty("target")
    private String target;
    @JsonProperty("weight")
    private Integer weight;
    @JsonProperty("created_at")
    @JsonSerialize(using = DateDoubleJsonSerializer.class)
    @JsonDeserialize(using = DateDoubleJsonDeserializer.class)
    private Date createAt;
    @JsonProperty("tags")
    private List<String> tags;
}
