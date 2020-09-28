package com.i2bgod.kong.model.admin.base.page;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.i2bgod.kong.model.adapter.EmptyArrayJsonDeserializer;
import lombok.Data;
import okhttp3.HttpUrl;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author: Lyn
 * @date: 04/08/2020
 */
@Data
public class Page<T> {
    @JsonProperty("next")
    String next;
    @JsonProperty("offset")
    String offset;
    @JsonProperty("data")
    @JsonDeserialize(using = EmptyArrayJsonDeserializer.class)
    List<T> data;


    @Nullable
    public String getOffset() {
        if (StringUtils.isNotBlank(this.offset)) {
            return this.offset;
        }

        if (StringUtils.isBlank(getNext())) {
            return null;
        }

        HttpUrl url = HttpUrl.parse(getNext());
        if (null == url) {
            return null;
        }
        return url.queryParameter("offset");
    }
}
