package com.i2bgod.kong.model.admin.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.i2bgod.kong.model.adapter.DateLongFormatDeserializer;
import com.i2bgod.kong.model.adapter.DateLongFormatSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author: Lyn
 * @date: 04/08/2020
 */
@Data
public class Base {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("created_at")
    @JsonSerialize(using = DateLongFormatSerializer.class)
    @JsonDeserialize(using = DateLongFormatDeserializer.class)
    private Date createAt;
    @JsonProperty("updated_at")
    @JsonSerialize(using = DateLongFormatSerializer.class)
    @JsonDeserialize(using = DateLongFormatDeserializer.class)
    private Date updateAt;
}
