package com.i2bgod.kong.model.admin.base.page;

import com.google.gson.annotations.SerializedName;
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
    @SerializedName("next")
    String next;
    @SerializedName("offset")
    String offset;
    @SerializedName("data")
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
