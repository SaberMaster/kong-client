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
public class Sni extends TagBase{

    /**
     * id : 91020192-062d-416f-a275-9addeeaffaf2
     * name : my-sni
     * created_at : 1422386534
     * tags : ["user-level","low-priority"]
     * certificate : {"id":"a2e013e8-7623-4494-a347-6d29108ff68b"}
     */

    @SerializedName("certificate")
    private Certificate certificate;
}
