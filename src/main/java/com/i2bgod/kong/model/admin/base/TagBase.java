package com.i2bgod.kong.model.admin.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author: Lyn
 * @date: 04/08/2020
 */
@Data
@ToString(callSuper = true)
public class TagBase extends Base {
    @JsonProperty("tags")
    private List<String> tags;
}
