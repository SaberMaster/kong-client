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
@KongEntity(dbEntityName = "ca_certificates")
public class CaCertificate extends TagBase{


    /**
     * id : 04fbeacf-a9f1-4a5d-ae4a-b0407445db3f
     * created_at : 1422386534
     * cert : -----BEGIN CERTIFICATE-----...
     * tags : ["user-level","low-priority"]
     */

    @SerializedName("cert")
    private String cert;
}
