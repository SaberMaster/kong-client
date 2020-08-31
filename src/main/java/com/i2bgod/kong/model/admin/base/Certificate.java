package com.i2bgod.kong.model.admin.base;

import com.google.gson.annotations.SerializedName;
import com.i2bgod.kong.model.admin.base.annotation.KongEntity;
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
@KongEntity(dbEntityName = "certificate")
public class Certificate extends TagBase{

    /**
     * id : 7fca84d6-7d37-4a74-a7b0-93e576089a41
     * created_at : 1422386534
     * cert : -----BEGIN CERTIFICATE-----...
     * key : -----BEGIN RSA PRIVATE KEY-----...
     * tags : ["user-level","low-priority"]
     */

    @SerializedName("cert")
    private String cert;
    @SerializedName("key")
    private String key;
}
