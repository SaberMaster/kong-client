package com.i2bgod.kong.model.admin.base;

import com.google.gson.annotations.SerializedName;
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

    @SerializedName("algorithm")
    private String algorithm;
    @SerializedName("hash_on")
    private String hashOn;
    @SerializedName("hash_fallback")
    private String hashFallback;
    @SerializedName("hash_on_cookie_path")
    private String hashOnCookiePath;
    @SerializedName("slots")
    private Integer slots;
    @SerializedName("healthchecks")
    private HealthchecksBean healthchecks;

    @NoArgsConstructor
    @Data
    @ToString(callSuper = true)
    public static class HealthchecksBean {
        /**
         * active : {"https_verify_certificate":true,"unhealthy":{"http_statuses":[429,404,500,501,502,503,504,505],"tcp_failures":0,"timeouts":0,"http_failures":0,"Integererval":0},"http_path":"/","timeout":1,"healthy":{"http_statuses":[200,302],"Integererval":0,"successes":0},"https_sni":"example.com","concurrency":10,"type":"http"}
         * passive : {"unhealthy":{"http_failures":0,"http_statuses":[429,500,503],"tcp_failures":0,"timeouts":0},"type":"http","healthy":{"successes":0,"http_statuses":[200,201,202,203,204,205,206,207,208,226,300,301,302,303,304,305,306,307,308]}}
         */

        @SerializedName("active")
        private ActiveBean active;
        @SerializedName("passive")
        private PassiveBean passive;
        @SerializedName("threshold")
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

            @SerializedName("https_verify_certificate")
            private Boolean httpsVerifyCertificate;
            @SerializedName("unhealthy")
            private UnhealthyBean unhealthy;
            @SerializedName("http_path")
            private String httpPath;
            @SerializedName("timeout")
            private Integer timeout;
            @SerializedName("healthy")
            private HealthyBean healthy;
            @SerializedName("https_sni")
            private String httpsSni;
            @SerializedName("concurrency")
            private Integer concurrency;
            @SerializedName("type")
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

                @SerializedName("tcp_failures")
                private Integer tcpFailures;
                @SerializedName("timeouts")
                private Integer timeouts;
                @SerializedName("http_failures")
                private Integer httpFailures;
                @SerializedName("interval")
                private Integer interval;
                @SerializedName("http_statuses")
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

                @SerializedName("interval")
                private Integer interval;
                @SerializedName("successes")
                private Integer successes;
                @SerializedName("http_statuses")
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

            @SerializedName("unhealthy")
            private UnhealthyBeanX unhealthy;
            @SerializedName("type")
            private String type;
            @SerializedName("healthy")
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

                @SerializedName("http_failures")
                private Integer httpFailures;
                @SerializedName("tcp_failures")
                private Integer tcpFailures;
                @SerializedName("timeouts")
                private Integer timeouts;
                @SerializedName("http_statuses")
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

                @SerializedName("successes")
                private Integer successes;
                @SerializedName("http_statuses")
                private List<Integer> httpStatuses;
            }
        }
    }
}
