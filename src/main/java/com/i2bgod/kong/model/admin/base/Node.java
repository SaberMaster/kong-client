package com.i2bgod.kong.model.admin.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.i2bgod.kong.model.adapter.EmptyArrayJsonDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author: Lyn
 * @date: 10/08/2020
 */
@Data
@NoArgsConstructor
public class Node {

    @JsonProperty("plugins")
    private PluginsBean plugins;
    @JsonProperty("tagline")
    private String tagline;
    @JsonProperty("configuration")
    private ConfigurationBean configuration;
    @JsonProperty("version")
    private String version;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("lua_version")
    private String luaVersion;
    @JsonProperty("prng_seeds")
    private Map<String, Long> prngSeeds;
    @JsonProperty("timers")
    private Map<String, Integer> timers;
    @JsonProperty("hostname")
    private String hostname;

    @Data
    @NoArgsConstructor
    public static class PluginsBean {

        @JsonProperty("available_on_server")
        private Map<String, Boolean> availableOnServer;
        @JsonProperty("enabled_in_cluster")
        private List<String> enabledInCluster;
    }
    
    @Data
    @NoArgsConstructor
    public static class ConfigurationBean {
        @JsonProperty("service_redis_timeout")
        private Integer serviceRedisTimeout;
        @JsonProperty("proxy_access_log")
        private String proxyAccessLog;
        @JsonProperty("prefix")
        private String prefix;
        @JsonProperty("orchsym_cluster_poll_Integererval")
        private Integer orchsymClusterPollInterval;
        @JsonProperty("loaded_plugins")
        private Map<String, Boolean> loadedPlugins;
        @JsonProperty("cassandra_username")
        private String cassandraUsername;
        @JsonProperty("ssl_cert_key")
        private String sslCertKey;
        @JsonProperty("admin_ssl_cert_key")
        private String adminSslCertKey;
        @JsonProperty("dns_resolver")
        @JsonDeserialize(using = EmptyArrayJsonDeserializer.class)
        private List<String> dnsResolver;
        @JsonProperty("pg_user")
        private String pgUser;
        @JsonProperty("mem_cache_size")
        private String memCacheSize;
        //todo: check is ok
        @JsonProperty("nginx_admin_directives")
        private Map<String, Object> nginxAdminDirectives;
        @JsonProperty("ssl_ciphers")
        private String sslCiphers;
        @JsonProperty("orchsym_body_filter_execution_allowed_payload_size")
        private Integer orchsymBodyFilterExecutionAllowedPayloadSize;
        @JsonProperty("pg_host")
        private String pgHost;
        @JsonProperty("nginx_acc_logs")
        private String nginxAccLogs;
        @JsonProperty("service_redis_host")
        private String serviceRedisHost;
        @JsonProperty("client_max_body_size")
        private String clientMaxBodySize;
        @JsonProperty("nginx_kong_stream_conf")
        private String nginxKongStreamConf;
        @JsonProperty("client_ssl_cert_default")
        private String clientSslCertDefault;
        //todo: check is ok
        @JsonProperty("stream_listeners")
        @JsonDeserialize(using = EmptyArrayJsonDeserializer.class)
        private List<Object> streamListeners;
        @JsonProperty("pg_semaphore_timeout")
        private Integer pgSemaphoreTimeout;
        @JsonProperty("dns_no_sync")
        private Boolean dnsNoSync;
        @JsonProperty("db_update_propagation")
        private Integer dbUpdatePropagation;
        @JsonProperty("lua_ssl_verify_depth")
        private Integer luaSslVerifyDepth;
        @JsonProperty("nginx_err_logs")
        private String nginxErrLogs;
        @JsonProperty("db_update_frequency")
        private Integer dbUpdateFrequency;
        @JsonProperty("cassandra_port")
        private Integer cassandraPort;
        @JsonProperty("dns_error_ttl")
        private Integer dnsErrorTtl;
        @JsonProperty("nginx_conf")
        private String nginxConf;
        //todo: check is ok
        @JsonProperty("nginx_stream_directives")
        private Map<String, Object> nginxStreamDirectives;
        @JsonProperty("system_redis_host")
        private String systemRedisHost;
        @JsonProperty("cassandra_lb_policy")
        private String cassandraLbPolicy;
        @JsonProperty("nginx_optimizations")
        private Boolean nginxOptimizations;
        @JsonProperty("nginx_http_upstream_keepalive_timeout")
        private String nginxHttpUpstreamKeepaliveTimeout;
        //todo: check is ok
        @JsonProperty("nginx_sproxy_directives")
        private Map<String, Object> nginxSproxyDirectives;
        @JsonProperty("origins")
        @JsonDeserialize(using = EmptyArrayJsonDeserializer.class)
        private List<String> origins;
        @JsonProperty("admin_ssl_cert")
        private String adminSslCert;
        @JsonProperty("nginx_http_upstream_keepalive_requests")
        private String nginxHttpUpstreamKeepaliveRequests;
        @JsonProperty("database")
        private String database;
        @JsonProperty("anonymous_reports")
        private Boolean anonymousReports;
        @JsonProperty("system_redis_database")
        private Integer systemRedisDatabase;
        @JsonProperty("lua_socket_pool_size")
        private Integer luaSocketPoolSize;
        @JsonProperty("pg_database")
        private String pgDatabase;
        @JsonProperty("nginx_worker_processes")
        private String nginxWorkerProcesses;
        @JsonProperty("ssl_cipher_suite")
        private String sslCipherSuite;
        @JsonProperty("lua_package_cpath")
        private String luaPackageCpath;
        @JsonProperty("proxy_error_log")
        private String proxyErrorLog;
        @JsonProperty("data_consistency")
        private Boolean dataConsistency;
        @JsonProperty("pg_ssl_verify")
        private Boolean pgSslVerify;
        @JsonProperty("system_redis_timeout")
        private Integer systemRedisTimeout;
        @JsonProperty("lua_package_path")
        private String luaPackagePath;
        @JsonProperty("nginx_pid")
        private String nginxPid;
        @JsonProperty("upstream_keepalive")
        private Integer upstreamKeepalive;
        @JsonProperty("orchsym_body_filter_execution_timeout")
        private Integer orchsymBodyFilterExecutionTimeout;
        @JsonProperty("data_url")
        private String dataUrl;
        @JsonProperty("error_default_type")
        private String errorDefaultType;
        @JsonProperty("orchsym_env_path")
        private Boolean orchsymEnvPath;
        @JsonProperty("dns_stale_ttl")
        private Integer dnsStaleTtl;
        @JsonProperty("proxy_ssl_enabled")
        private Boolean proxySslEnabled;
        @JsonProperty("nginx_http_upstream_keepalive")
        private String nginxHttpUpstreamKeepalive;
        @JsonProperty("enabled_headers")
        private Map<String, Boolean> enabledHeaders;
        @JsonProperty("nginx_http_ssl_protocols")
        private String nginxHttpSslProtocols;
        @JsonProperty("system_redis_port")
        private Integer systemRedisPort;
        @JsonProperty("db_resurrect_ttl")
        private Integer dbResurrectTtl;
        @JsonProperty("cassandra_timeout")
        private Integer cassandraTimeout;
        @JsonProperty("cassandra_consistency")
        private String cassandraConsistency;
        @JsonProperty("db_cache_ttl")
        private Integer dbCacheTtl;
        @JsonProperty("admin_error_log")
        private String adminErrorLog;
        @JsonProperty("admin_ssl_cert_default")
        private String adminSslCertDefault;
        @JsonProperty("dns_not_found_ttl")
        private Integer dnsNotFoundTtl;
        @JsonProperty("pg_ssl")
        private Boolean pgSsl;
        @JsonProperty("cassandra_schema_consensus_timeout")
        private Integer cassandraSchemaConsensusTimeout;
        @JsonProperty("pg_timeout")
        private Integer pgTimeout;
        @JsonProperty("cassandra_repl_strategy")
        private String cassandraReplStrategy;
        @JsonProperty("client_ssl")
        private Boolean clientSsl;
        @JsonProperty("service_redis_database")
        private Integer serviceRedisDatabase;
        @JsonProperty("log_level")
        private String logLevel;
        @JsonProperty("kong_env")
        private String kongEnv;
        @JsonProperty("nginx_kong_conf")
        private String nginxKongConf;
        @JsonProperty("real_ip_header")
        private String realIpHeader;
        @JsonProperty("dns_hostsfile")
        private String dnsHostsfile;
        @JsonProperty("pg_max_concurrent_queries")
        private Integer pgMaxConcurrentQueries;
        @JsonProperty("ssl_cert")
        private String sslCert;
        @JsonProperty("env_id")
        private String envId;
        @JsonProperty("admin_ssl_cert_key_default")
        private String adminSslCertKeyDefault;
        @JsonProperty("cassandra_ssl_verify")
        private Boolean cassandraSslVerify;
        @JsonProperty("service_redis_port")
        private Integer serviceRedisPort;
        @JsonProperty("orchsym_access_log")
        private String orchsymAccessLog;
        @JsonProperty("real_ip_recursive")
        private String realIpRecursive;
        @JsonProperty("cassandra_repl_factor")
        private Integer cassandraReplFactor;
        @JsonProperty("client_ssl_cert_key_default")
        private String clientSslCertKeyDefault;
        @JsonProperty("nginx_daemon")
        private String nginxDaemon;
        @JsonProperty("router_consistency")
        private String routerConsistency;
        //todo: check is ok
        @JsonProperty("nginx_proxy_directives")
        @JsonDeserialize(using = EmptyArrayJsonDeserializer.class)
        private List<Object> nginxProxyDirectives;
        @JsonProperty("admin_access_log")
        private String adminAccessLog;
        @JsonProperty("pg_port")
        private Integer pgPort;
        @JsonProperty("ssl_cert_csr_default")
        private String sslCertCsrDefault;
        @JsonProperty("client_body_buffer_size")
        private String clientBodyBufferSize;
        @JsonProperty("ssl_preread_enabled")
        private Boolean sslPrereadEnabled;
        @JsonProperty("ssl_cert_key_default")
        private String sslCertKeyDefault;
        @JsonProperty("admin_acc_logs")
        private String adminAccLogs;
        @JsonProperty("cassandra_keyspace")
        private String cassandraKeyspace;
        @JsonProperty("ssl_cert_default")
        private String sslCertDefault;
        @JsonProperty("cassandra_ssl")
        private Boolean cassandraSsl;
        @JsonProperty("admin_ssl_enabled")
        private Boolean adminSslEnabled;
        @JsonProperty("plugins")
        @JsonDeserialize(using = EmptyArrayJsonDeserializer.class)
        private List<String> plugins;
        @JsonProperty("admin_listen")
        @JsonDeserialize(using = EmptyArrayJsonDeserializer.class)
        private List<String> adminListen;
        @JsonProperty("trusted_ips")
        @JsonDeserialize(using = EmptyArrayJsonDeserializer.class)
        private List<String> trustedIps;
        @JsonProperty("cassandra_data_centers")
        @JsonDeserialize(using = EmptyArrayJsonDeserializer.class)
        private List<String> cassandraDataCenters;
        @JsonProperty("nginx_http_upstream_directives")
        @JsonDeserialize(using = EmptyArrayJsonDeserializer.class)
        private List<NginxHttpUpstreamDirectivesBean> nginxHttpUpstreamDirectives;
        @JsonProperty("proxy_listen")
        @JsonDeserialize(using = EmptyArrayJsonDeserializer.class)
        private List<String> proxyListen;
        @JsonProperty("stream_listen")
        @JsonDeserialize(using = EmptyArrayJsonDeserializer.class)
        private List<String> streamListen;
        @JsonProperty("dns_order")
        @JsonDeserialize(using = EmptyArrayJsonDeserializer.class)
        private List<String> dnsOrder;
        @JsonProperty("headers")
        @JsonDeserialize(using = EmptyArrayJsonDeserializer.class)
        private List<String> headers;
        @JsonProperty("nginx_http_directives")
        @JsonDeserialize(using = EmptyArrayJsonDeserializer.class)
        private List<NginxHttpDirectivesBean> nginxHttpDirectives;
        @JsonProperty("cassandra_contact_poIntegers")
        @JsonDeserialize(using = EmptyArrayJsonDeserializer.class)
        private List<String> cassandraContactPoIntegers;
        @JsonProperty("proxy_listeners")
        @JsonDeserialize(using = EmptyArrayJsonDeserializer.class)
        private List<ProxyListenersBean> proxyListeners;
        @JsonProperty("db_cache_warmup_entities")
        @JsonDeserialize(using = EmptyArrayJsonDeserializer.class)
        private List<String> dbCacheWarmupEntities;
        @JsonProperty("admin_listeners")
        @JsonDeserialize(using = EmptyArrayJsonDeserializer.class)
        private List<AdminListenersBean> adminListeners;


        @Data
        @NoArgsConstructor
        public static class NginxHttpUpstreamDirectivesBean {
            @JsonProperty("value")
            private String value;
            @JsonProperty("name")
            private String name;
        }

        @NoArgsConstructor
        @Data
        public static class NginxHttpDirectivesBean {
            @JsonProperty("value")
            private String value;
            @JsonProperty("name")
            private String name;
        }

        @NoArgsConstructor
        @Data
        public static class ProxyListenersBean {
            @JsonProperty("listener")
            private String listener;
            @JsonProperty("proxy_protocol")
            private Boolean proxyProtocol;
            @JsonProperty("reuseport")
            private Boolean reuseport;
            @JsonProperty("transparent")
            private Boolean transparent;
            @JsonProperty("ssl")
            private Boolean ssl;
            @JsonProperty("ip")
            private String ip;
            @JsonProperty("deferred")
            private Boolean deferred;
            @JsonProperty("http2")
            private Boolean http2;
            @JsonProperty("port")
            private Integer port;
            @JsonProperty("bind")
            private Boolean bind;
        }

        @NoArgsConstructor
        @Data
        public static class AdminListenersBean {
            @JsonProperty("listener")
            private String listener;
            @JsonProperty("proxy_protocol")
            private Boolean proxyProtocol;
            @JsonProperty("reuseport")
            private Boolean reuseport;
            @JsonProperty("transparent")
            private Boolean transparent;
            @JsonProperty("ssl")
            private Boolean ssl;
            @JsonProperty("ip")
            private String ip;
            @JsonProperty("deferred")
            private Boolean deferred;
            @JsonProperty("http2")
            private Boolean http2;
            @JsonProperty("port")
            private Integer port;
            @JsonProperty("bind")
            private Boolean bind;
        }
    }

    @Data
    @NoArgsConstructor
    public static class TimersBean {
        @JsonProperty("pending")
        private Integer pending;
        @JsonProperty("running")
        private Integer running;
    }
}
