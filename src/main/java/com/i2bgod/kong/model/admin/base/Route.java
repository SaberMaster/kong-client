package com.i2bgod.kong.model.admin.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.i2bgod.kong.model.admin.base.annotation.KongEntity;
import com.i2bgod.kong.model.admin.base.annotation.KongFK;
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

    @JsonProperty("headers")
    private Map<String, List<String>> headers;
    @JsonProperty("https_redirect_status_code")
    private Integer httpsRedirectStatusCode;
    @JsonProperty("regex_priority")
    private Integer regexPriority;
    @JsonProperty("strip_path")
    private Boolean stripPath;
    @JsonProperty("path_handling")
    private String pathHandling;
    @JsonProperty("preserve_host")
    private Boolean preserveHost;
    @JsonProperty("service")
    @KongFK
    private Service service;
    @JsonProperty("protocols")
    private List<String> protocols;
    @JsonProperty("methods")
    private List<String> methods;
    @JsonProperty("hosts")
    private List<String> hosts;
    @JsonProperty("paths")
    private List<String> paths;
}
