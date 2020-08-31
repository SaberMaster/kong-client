package com.i2bgod.kong.model.admin.base;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.i2bgod.kong.model.adapter.DateLongFormatTypeAdapter;
import lombok.Data;

import java.util.Date;

/**
 * @author: Lyn
 * @date: 04/08/2020
 */
@Data
public class Base {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("created_at")
    @JsonAdapter(DateLongFormatTypeAdapter.class)
    private Date createAt;
    @SerializedName("updated_at")
    @JsonAdapter(DateLongFormatTypeAdapter.class)
    private Date updateAt;
}
