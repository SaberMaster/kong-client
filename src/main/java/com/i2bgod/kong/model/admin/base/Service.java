package com.i2bgod.kong.model.admin.base;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: Lyn
 * @date: 04/08/2020
 */
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class Service extends TagBase {

    /**
     * id : 9748f662-7711-4a90-8186-dc02f10eb0f5
     * created_at : 1422386534
     * updated_at : 1422386534
     * name : my-service
     * retries : 5
     * protocol : http
     * host : example.com
     * port : 80
     * path : /some_api
     * connect_timeout : 60000
     * write_timeout : 60000
     * read_timeout : 60000
     * tags : ["user-level","low-priority"]
     * client_certificate : {"id":"4e3ad2e4-0bc4-4638-8e34-c84a417ba39b"}
     * tls_verify : true
     * tls_verify_depth : null
     * ca_certificates : ["4e3ad2e4-0bc4-4638-8e34-c84a417ba39b","51e77dc2-8f3e-4afa-9d0e-0e3bbbcfd515"]
     */

    @SerializedName("retries")
    private Integer retries;
    @SerializedName("protocol")
    private String protocol;
    @SerializedName("host")
    private String host;
    @SerializedName("port")
    private Integer port;
    @SerializedName("path")
    private String path;
    @SerializedName("url")
    private String url;
    @SerializedName("connect_timeout")
    private Integer connectTimeout;
    @SerializedName("write_timeout")
    private Integer writeTimeout;
    @SerializedName("read_timeout")
    private Integer readTimeout;
    @SerializedName("client_certificate")
    private ClientCertificateBean clientCertificate;

    @NoArgsConstructor
    @Data
    public static class ClientCertificateBean {
        /**
         * id : 4e3ad2e4-0bc4-4638-8e34-c84a417ba39b
         */

        private String id;
    }
}
