package com.i2bgod.kong.model.admin.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author: Lyn
 * @date: 10/08/2020
 */
@NoArgsConstructor
@Data
public class NodeStatus {

    /**
     * database : {"reachable":true}
     * memory : {"workers_lua_vms":[{"http_allocated_gc":"0.03 MiB","pid":150}],"lua_shared_dicts":{"kong_locks":{"allocated_slabs":"0.06 MiB","capacity":"8.00 MiB"},"kong_cassandra":{"allocated_slabs":"0.04 MiB","capacity":"5.00 MiB"},"kong":{"allocated_slabs":"0.04 MiB","capacity":"5.00 MiB"},"kong_process_events":{"allocated_slabs":"0.04 MiB","capacity":"5.00 MiB"},"kong_db_cache":{"allocated_slabs":"1.51 MiB","capacity":"256.00 MiB"},"kong_cluster_events":{"allocated_slabs":"0.04 MiB","capacity":"5.00 MiB"},"kong_db_cache_miss":{"allocated_slabs":"0.08 MiB","capacity":"12.00 MiB"},"prometheus_metrics":{"allocated_slabs":"0.04 MiB","capacity":"5.00 MiB"},"kong_healthchecks":{"allocated_slabs":"0.04 MiB","capacity":"5.00 MiB"},"kong_rate_limiting_counters":{"allocated_slabs":"0.08 MiB","capacity":"12.00 MiB"}}}
     * server : {"connections_writing":1,"total_requests":15,"connections_handled":15,"connections_accepted":15,"connections_reading":0,"connections_active":1,"connections_waiting":0}
     */

    @JsonProperty("database")
    private DatabaseBean database;
    @JsonProperty("memory")
    private MemoryBean memory;
    @JsonProperty("server")
    private ServerBean server;

    @NoArgsConstructor
    @Data
    public static class DatabaseBean {
        /**
         * reachable : true
         */

        @JsonProperty("reachable")
        private Boolean reachable;
    }

    @NoArgsConstructor
    @Data
    public static class MemoryBean {
        /**
         * workers_lua_vms : [{"http_allocated_gc":"0.03 MiB","pid":150}]
         * lua_shared_dicts : {"kong_locks":{"allocated_slabs":"0.06 MiB","capacity":"8.00 MiB"},"kong_cassandra":{"allocated_slabs":"0.04 MiB","capacity":"5.00 MiB"},"kong":{"allocated_slabs":"0.04 MiB","capacity":"5.00 MiB"},"kong_process_events":{"allocated_slabs":"0.04 MiB","capacity":"5.00 MiB"},"kong_db_cache":{"allocated_slabs":"1.51 MiB","capacity":"256.00 MiB"},"kong_cluster_events":{"allocated_slabs":"0.04 MiB","capacity":"5.00 MiB"},"kong_db_cache_miss":{"allocated_slabs":"0.08 MiB","capacity":"12.00 MiB"},"prometheus_metrics":{"allocated_slabs":"0.04 MiB","capacity":"5.00 MiB"},"kong_healthchecks":{"allocated_slabs":"0.04 MiB","capacity":"5.00 MiB"},"kong_rate_limiting_counters":{"allocated_slabs":"0.08 MiB","capacity":"12.00 MiB"}}
         */

        @JsonProperty("lua_shared_dicts")
        private Map<String, LuaSharedDictBean> luaSharedDicts;
        @JsonProperty("workers_lua_vms")
        private List<WorkersLuaVmsBean> workersLuaVms;


        @NoArgsConstructor
        @Data
        public static class LuaSharedDictBean {
            /**
             * allocated_slabs : 0.06 MiB
             * capacity : 8.00 MiB
             */

            @JsonProperty("allocated_slabs")
            private String allocatedSlabs;
            @JsonProperty("capacity")
            private String capacity;
        }
        @NoArgsConstructor
        @Data
        public static class WorkersLuaVmsBean {
            /**
             * http_allocated_gc : 0.03 MiB
             * pid : 150
             */

            @JsonProperty("http_allocated_gc")
            private String httpAllocatedGc;
            @JsonProperty("pid")
            private Integer pid;
        }
    }

    @NoArgsConstructor
    @Data
    public static class ServerBean {
        /**
         * connections_writing : 1
         * total_requests : 15
         * connections_handled : 15
         * connections_accepted : 15
         * connections_reading : 0
         * connections_active : 1
         * connections_waiting : 0
         */

        @JsonProperty("connections_writing")
        private Integer connectionsWriting;
        @JsonProperty("total_requests")
        private Integer totalRequests;
        @JsonProperty("connections_handled")
        private Integer connectionsHandled;
        @JsonProperty("connections_accepted")
        private Integer connectionsAccepted;
        @JsonProperty("connections_reading")
        private Integer connectionsReading;
        @JsonProperty("connections_active")
        private Integer connectionsActive;
        @JsonProperty("connections_waiting")
        private Integer connectionsWaiting;
    }
}
