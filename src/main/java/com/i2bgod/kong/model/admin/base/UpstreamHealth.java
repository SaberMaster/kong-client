package com.i2bgod.kong.model.admin.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.i2bgod.kong.model.adapter.DateDoubleJsonDeserializer;
import com.i2bgod.kong.model.adapter.DateDoubleJsonSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author: Lyn
 * @date: 19/08/2020
 */
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class UpstreamHealth {

    /**
     * total : 2
     * node_id : cbb297c0-14a9-46bc-ad91-1d0ef9b42df9
     * data : [{"created_at":1485524883980,"id":"18c0ad90-f942-4098-88db-bbee3e43b27f","health":"HEALTHY","target":"127.0.0.1:20000","upstream_id":"07131005-ba30-4204-a29f-0927d53257b4","weight":100},{"created_at":1485524914883,"id":"6c6f34eb-e6c3-4c1f-ac58-4060e5bca890","health":"UNHEALTHY","target":"127.0.0.1:20002","upstream_id":"07131005-ba30-4204-a29f-0927d53257b4","weight":200}]
     */

    @JsonProperty("total")
    private Integer total;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("data")
    private List<TargetHealthBean> data;

    @NoArgsConstructor
    @Data
    @ToString(callSuper = true)
    public static class TargetHealthBean {
        /**
         * created_at : 1485524883980
         * id : 18c0ad90-f942-4098-88db-bbee3e43b27f
         * health : HEALTHY
         * target : 127.0.0.1:20000.123
         * upstream_id : 07131005-ba30-4204-a29f-0927d53257b4
         * weight : 100
         */

        @JsonProperty("id")
        private String id;
        @JsonProperty("health")
        private String health;
        @JsonProperty("target")
        private String target;
        @JsonProperty("upstream_id")
        private String upstreamId;
        @JsonProperty("weight")
        private Integer weight;
        @JsonProperty("created_at")
        @JsonFormat(shape = JsonFormat.Shape.NUMBER)
        @JsonSerialize(using = DateDoubleJsonSerializer.class)
        @JsonDeserialize(using = DateDoubleJsonDeserializer.class)
        private Date createAt;
    }
}
