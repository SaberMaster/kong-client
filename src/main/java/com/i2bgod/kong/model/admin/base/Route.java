package com.i2bgod.kong.model.admin.base;

import com.google.gson.annotations.SerializedName;
import com.i2bgod.kong.model.admin.base.annotation.KongEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @author: Lyn
 * @date: 10/08/2020
 */
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@KongEntity(dbEntityName = "routes")
public class Route extends TagBase{
    /**
     * id : d35165e2-d03e-461a-bdeb-dad0a112abfe
     * created_at : 1422386534
     * updated_at : 1422386534
     * name : my-route
     * protocols : ["http","https"]
     * methods : ["GET","POST"]
     * hosts : ["example.com","foo.test"]
     * paths : ["/foo","/bar"]
     * headers : {"x-another-header":["bla"],"x-my-header":["foo","bar"]}
     * https_redirect_status_code : 426
     * regex_priority : 0
     * strip_path : true
     * path_handling : v0
     * preserve_host : false
     * tags : ["user-level","low-priority"]
     * service : {"id":"af8330d3-dbdc-48bd-b1be-55b98608834b"}
     */

    @SerializedName("headers")
    private Map<String, List<String>> headers;
    @SerializedName("https_redirect_status_code")
    private Integer httpsRedirectStatusCode;
    @SerializedName("regex_priority")
    private Integer regexPriority;
    @SerializedName("strip_path")
    private Boolean stripPath;
    @SerializedName("path_handling")
    private String pathHandling;
    @SerializedName("preserve_host")
    private Boolean preserveHost;
    @SerializedName("service")
    private Service service;
    @SerializedName("protocols")
    private List<String> protocols;
    @SerializedName("methods")
    private List<String> methods;
    @SerializedName("hosts")
    private List<String> hosts;
    @SerializedName("paths")
    private List<String> paths;
}
