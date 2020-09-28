package com.i2bgod.kong.model.admin.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.i2bgod.kong.model.admin.base.annotation.KongEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author: Lyn
 * @date: 18/08/2020
 */
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@KongEntity(dbEntityName = "upstreams")
public class Upstream extends TagBase{

    /**
     * id : 58c8ccbb-eafb-4566-991f-2ed4f678fa70
     * created_at : 1422386534
     * name : my-upstream
     * algorithm : round-robin
     * hash_on : none
     * hash_fallback : none
     * hash_on_cookie_path : /
     * slots : 10000
     * healthchecks : {"active":{"https_verify_certificate":true,"unhealthy":{"http_statuses":[429,404,500,501,502,503,504,505],"tcp_failures":0,"timeouts":0,"http_failures":0,"Integererval":0},"http_path":"/","timeout":1,"healthy":{"http_statuses":[200,302],"Integererval":0,"successes":0},"https_sni":"example.com","concurrency":10,"type":"http"},"passive":{"unhealthy":{"http_failures":0,"http_statuses":[429,500,503],"tcp_failures":0,"timeouts":0},"type":"http","healthy":{"successes":0,"http_statuses":[200,201,202,203,204,205,206,207,208,226,300,301,302,303,304,305,306,307,308]}}}
     * tags : ["user-level","low-priority"]
     */

    @JsonProperty("algorithm")
    private String algorithm;
    @JsonProperty("hash_on")
    private String hashOn;
    @JsonProperty("hash_fallback")
    private String hashFallback;
    @JsonProperty("hash_on_cookie_path")
    private String hashOnCookiePath;
    @JsonProperty("slots")
    private Integer slots;
    @JsonProperty("healthchecks")
    private HealthchecksBean healthchecks;

    @NoArgsConstructor
    @Data
    @ToString(callSuper = true)
    public static class HealthchecksBean {
        /**
         * active : {"https_verify_certificate":true,"unhealthy":{"http_statuses":[429,404,500,501,502,503,504,505],"tcp_failures":0,"timeouts":0,"http_failures":0,"Integererval":0},"http_path":"/","timeout":1,"healthy":{"http_statuses":[200,302],"Integererval":0,"successes":0},"https_sni":"example.com","concurrency":10,"type":"http"}
         * passive : {"unhealthy":{"http_failures":0,"http_statuses":[429,500,503],"tcp_failures":0,"timeouts":0},"type":"http","healthy":{"successes":0,"http_statuses":[200,201,202,203,204,205,206,207,208,226,300,301,302,303,304,305,306,307,308]}}
         */

        @JsonProperty("active")
        private ActiveBean active;
        @JsonProperty("passive")
        private PassiveBean passive;
        @JsonProperty("threshold")
        private Integer threshold;

        @NoArgsConstructor
        @Data
        @ToString(callSuper = true)
        public static class ActiveBean {
            /**
             * https_verify_certificate : true
             * unhealthy : {"http_statuses":[429,404,500,501,502,503,504,505],"tcp_failures":0,"timeouts":0,"http_failures":0,"Integererval":0}
             * http_path : /
             * timeout : 1
             * healthy : {"http_statuses":[200,302],"Integererval":0,"successes":0}
             * https_sni : example.com
             * concurrency : 10
             * type : http
             */

            @JsonProperty("https_verify_certificate")
            private Boolean httpsVerifyCertificate;
            @JsonProperty("unhealthy")
            private UnhealthyBean unhealthy;
            @JsonProperty("http_path")
            private String httpPath;
            @JsonProperty("timeout")
            private Integer timeout;
            @JsonProperty("healthy")
            private HealthyBean healthy;
            @JsonProperty("https_sni")
            private String httpsSni;
            @JsonProperty("concurrency")
            private Integer concurrency;
            @JsonProperty("type")
            private String type;

            @NoArgsConstructor
            @Data
            @ToString(callSuper = true)
            public static class UnhealthyBean {
                /**
                 * http_statuses : [429,404,500,501,502,503,504,505]
                 * tcp_failures : 0
                 * timeouts : 0
                 * http_failures : 0
                 * Integererval : 0
                 */

                @JsonProperty("tcp_failures")
                private Integer tcpFailures;
                @JsonProperty("timeouts")
                private Integer timeouts;
                @JsonProperty("http_failures")
                private Integer httpFailures;
                @JsonProperty("interval")
                private Integer interval;
                @JsonProperty("http_statuses")
                private List<Integer> httpStatuses;
            }

            @NoArgsConstructor
            @Data
            @ToString(callSuper = true)
            public static class HealthyBean {
                /**
                 * http_statuses : [200,302]
                 * Integererval : 0
                 * successes : 0
                 */

                @JsonProperty("interval")
                private Integer interval;
                @JsonProperty("successes")
                private Integer successes;
                @JsonProperty("http_statuses")
                private List<Integer> httpStatuses;
            }
        }

        @NoArgsConstructor
        @Data
        @ToString(callSuper = true)
        public static class PassiveBean {
            /**
             * unhealthy : {"http_failures":0,"http_statuses":[429,500,503],"tcp_failures":0,"timeouts":0}
             * type : http
             * healthy : {"successes":0,"http_statuses":[200,201,202,203,204,205,206,207,208,226,300,301,302,303,304,305,306,307,308]}
             */

            @JsonProperty("unhealthy")
            private UnhealthyBeanX unhealthy;
            @JsonProperty("type")
            private String type;
            @JsonProperty("healthy")
            private HealthyBeanX healthy;

            @NoArgsConstructor
            @Data
            @ToString(callSuper = true)
            public static class UnhealthyBeanX {
                /**
                 * http_failures : 0
                 * http_statuses : [429,500,503]
                 * tcp_failures : 0
                 * timeouts : 0
                 */

                @JsonProperty("http_failures")
                private Integer httpFailures;
                @JsonProperty("tcp_failures")
                private Integer tcpFailures;
                @JsonProperty("timeouts")
                private Integer timeouts;
                @JsonProperty("http_statuses")
                private List<Integer> httpStatuses;
            }

            @NoArgsConstructor
            @Data
            @ToString(callSuper = true)
            public static class HealthyBeanX {
                /**
                 * successes : 0
                 * http_statuses : [200,201,202,203,204,205,206,207,208,226,300,301,302,303,304,305,306,307,308]
                 */

                @JsonProperty("successes")
                private Integer successes;
                @JsonProperty("http_statuses")
                private List<Integer> httpStatuses;
            }
        }
    }
}
