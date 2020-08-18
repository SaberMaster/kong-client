package com.i2bgod.kong.model.admin.base;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
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

    @SerializedName("plugins")
    private PluginsBean plugins;
    @SerializedName("tagline")
    private String tagline;
    @SerializedName("configuration")
    private ConfigurationBean configuration;
    @SerializedName("version")
    private String version;
    @SerializedName("node_id")
    private String nodeId;
    @SerializedName("lua_version")
    private String luaVersion;
    @SerializedName("prng_seeds")
    private Map<String, Long> prngSeeds;
    @SerializedName("timers")
    private Map<String, Integer> timers;
    @SerializedName("hostname")
    private String hostname;

    @Data
    @NoArgsConstructor
    public static class PluginsBean {

        @SerializedName("available_on_server")
        private Map<String, Boolean> availableOnServer;
        @SerializedName("enabled_in_cluster")
        private List<String> enabledInCluster;
    }
    
    @Data
    @NoArgsConstructor
    public static class ConfigurationBean {
        @SerializedName("service_redis_timeout")
        private Integer serviceRedisTimeout;
        @SerializedName("proxy_access_log")
        private String proxyAccessLog;
        @SerializedName("prefix")
        private String prefix;
        @SerializedName("orchsym_cluster_poll_Integererval")
        private Integer orchsymClusterPollInterval;
        @SerializedName("loaded_plugins")
        private Map<String, Boolean> loadedPlugins;
        @SerializedName("cassandra_username")
        private String cassandraUsername;
        @SerializedName("ssl_cert_key")
        private String sslCertKey;
        @SerializedName("admin_ssl_cert_key")
        private String adminSslCertKey;
        @SerializedName("dns_resolver")
        @JsonAdapter(EmptyArrayJsonDeserializer.class)
        private List<String> dnsResolver;
        @SerializedName("pg_user")
        private String pgUser;
        @SerializedName("mem_cache_size")
        private String memCacheSize;
        //todo: check is ok
        @SerializedName("nginx_admin_directives")
        private Map<String, Object> nginxAdminDirectives;
        @SerializedName("ssl_ciphers")
        private String sslCiphers;
        @SerializedName("orchsym_body_filter_execution_allowed_payload_size")
        private Integer orchsymBodyFilterExecutionAllowedPayloadSize;
        @SerializedName("pg_host")
        private String pgHost;
        @SerializedName("nginx_acc_logs")
        private String nginxAccLogs;
        @SerializedName("service_redis_host")
        private String serviceRedisHost;
        @SerializedName("client_max_body_size")
        private String clientMaxBodySize;
        @SerializedName("nginx_kong_stream_conf")
        private String nginxKongStreamConf;
        @SerializedName("client_ssl_cert_default")
        private String clientSslCertDefault;
        //todo: check is ok
        @SerializedName("stream_listeners")
        @JsonAdapter(EmptyArrayJsonDeserializer.class)
        private List<Object> streamListeners;
        @SerializedName("pg_semaphore_timeout")
        private Integer pgSemaphoreTimeout;
        @SerializedName("dns_no_sync")
        private Boolean dnsNoSync;
        @SerializedName("db_update_propagation")
        private Integer dbUpdatePropagation;
        @SerializedName("lua_ssl_verify_depth")
        private Integer luaSslVerifyDepth;
        @SerializedName("nginx_err_logs")
        private String nginxErrLogs;
        @SerializedName("db_update_frequency")
        private Integer dbUpdateFrequency;
        @SerializedName("cassandra_port")
        private Integer cassandraPort;
        @SerializedName("dns_error_ttl")
        private Integer dnsErrorTtl;
        @SerializedName("nginx_conf")
        private String nginxConf;
        //todo: check is ok
        @SerializedName("nginx_stream_directives")
        private Map<String, Object> nginxStreamDirectives;
        @SerializedName("system_redis_host")
        private String systemRedisHost;
        @SerializedName("cassandra_lb_policy")
        private String cassandraLbPolicy;
        @SerializedName("nginx_optimizations")
        private Boolean nginxOptimizations;
        @SerializedName("nginx_http_upstream_keepalive_timeout")
        private String nginxHttpUpstreamKeepaliveTimeout;
        //todo: check is ok
        @SerializedName("nginx_sproxy_directives")
        private Map<String, Object> nginxSproxyDirectives;
        @SerializedName("origins")
        @JsonAdapter(EmptyArrayJsonDeserializer.class)
        private List<String> origins;
        @SerializedName("admin_ssl_cert")
        private String adminSslCert;
        @SerializedName("nginx_http_upstream_keepalive_requests")
        private String nginxHttpUpstreamKeepaliveRequests;
        @SerializedName("database")
        private String database;
        @SerializedName("anonymous_reports")
        private Boolean anonymousReports;
        @SerializedName("system_redis_database")
        private Integer systemRedisDatabase;
        @SerializedName("lua_socket_pool_size")
        private Integer luaSocketPoolSize;
        @SerializedName("pg_database")
        private String pgDatabase;
        @SerializedName("nginx_worker_processes")
        private String nginxWorkerProcesses;
        @SerializedName("ssl_cipher_suite")
        private String sslCipherSuite;
        @SerializedName("lua_package_cpath")
        private String luaPackageCpath;
        @SerializedName("proxy_error_log")
        private String proxyErrorLog;
        @SerializedName("data_consistency")
        private Boolean dataConsistency;
        @SerializedName("pg_ssl_verify")
        private Boolean pgSslVerify;
        @SerializedName("system_redis_timeout")
        private Integer systemRedisTimeout;
        @SerializedName("lua_package_path")
        private String luaPackagePath;
        @SerializedName("nginx_pid")
        private String nginxPid;
        @SerializedName("upstream_keepalive")
        private Integer upstreamKeepalive;
        @SerializedName("orchsym_body_filter_execution_timeout")
        private Integer orchsymBodyFilterExecutionTimeout;
        @SerializedName("data_url")
        private String dataUrl;
        @SerializedName("error_default_type")
        private String errorDefaultType;
        @SerializedName("orchsym_env_path")
        private Boolean orchsymEnvPath;
        @SerializedName("dns_stale_ttl")
        private Integer dnsStaleTtl;
        @SerializedName("proxy_ssl_enabled")
        private Boolean proxySslEnabled;
        @SerializedName("nginx_http_upstream_keepalive")
        private String nginxHttpUpstreamKeepalive;
        @SerializedName("enabled_headers")
        private Map<String, Boolean> enabledHeaders;
        @SerializedName("nginx_http_ssl_protocols")
        private String nginxHttpSslProtocols;
        @SerializedName("system_redis_port")
        private Integer systemRedisPort;
        @SerializedName("db_resurrect_ttl")
        private Integer dbResurrectTtl;
        @SerializedName("cassandra_timeout")
        private Integer cassandraTimeout;
        @SerializedName("cassandra_consistency")
        private String cassandraConsistency;
        @SerializedName("db_cache_ttl")
        private Integer dbCacheTtl;
        @SerializedName("admin_error_log")
        private String adminErrorLog;
        @SerializedName("admin_ssl_cert_default")
        private String adminSslCertDefault;
        @SerializedName("dns_not_found_ttl")
        private Integer dnsNotFoundTtl;
        @SerializedName("pg_ssl")
        private Boolean pgSsl;
        @SerializedName("cassandra_schema_consensus_timeout")
        private Integer cassandraSchemaConsensusTimeout;
        @SerializedName("pg_timeout")
        private Integer pgTimeout;
        @SerializedName("cassandra_repl_strategy")
        private String cassandraReplStrategy;
        @SerializedName("client_ssl")
        private Boolean clientSsl;
        @SerializedName("service_redis_database")
        private Integer serviceRedisDatabase;
        @SerializedName("log_level")
        private String logLevel;
        @SerializedName("kong_env")
        private String kongEnv;
        @SerializedName("nginx_kong_conf")
        private String nginxKongConf;
        @SerializedName("real_ip_header")
        private String realIpHeader;
        @SerializedName("dns_hostsfile")
        private String dnsHostsfile;
        @SerializedName("pg_max_concurrent_queries")
        private Integer pgMaxConcurrentQueries;
        @SerializedName("ssl_cert")
        private String sslCert;
        @SerializedName("env_id")
        private String envId;
        @SerializedName("admin_ssl_cert_key_default")
        private String adminSslCertKeyDefault;
        @SerializedName("cassandra_ssl_verify")
        private Boolean cassandraSslVerify;
        @SerializedName("service_redis_port")
        private Integer serviceRedisPort;
        @SerializedName("orchsym_access_log")
        private String orchsymAccessLog;
        @SerializedName("real_ip_recursive")
        private String realIpRecursive;
        @SerializedName("cassandra_repl_factor")
        private Integer cassandraReplFactor;
        @SerializedName("client_ssl_cert_key_default")
        private String clientSslCertKeyDefault;
        @SerializedName("nginx_daemon")
        private String nginxDaemon;
        @SerializedName("router_consistency")
        private String routerConsistency;
        //todo: check is ok
        @SerializedName("nginx_proxy_directives")
        @JsonAdapter(EmptyArrayJsonDeserializer.class)
        private List<Object> nginxProxyDirectives;
        @SerializedName("admin_access_log")
        private String adminAccessLog;
        @SerializedName("pg_port")
        private Integer pgPort;
        @SerializedName("ssl_cert_csr_default")
        private String sslCertCsrDefault;
        @SerializedName("client_body_buffer_size")
        private String clientBodyBufferSize;
        @SerializedName("ssl_preread_enabled")
        private Boolean sslPrereadEnabled;
        @SerializedName("ssl_cert_key_default")
        private String sslCertKeyDefault;
        @SerializedName("admin_acc_logs")
        private String adminAccLogs;
        @SerializedName("cassandra_keyspace")
        private String cassandraKeyspace;
        @SerializedName("ssl_cert_default")
        private String sslCertDefault;
        @SerializedName("cassandra_ssl")
        private Boolean cassandraSsl;
        @SerializedName("admin_ssl_enabled")
        private Boolean adminSslEnabled;
        @SerializedName("plugins")
        @JsonAdapter(EmptyArrayJsonDeserializer.class)
        private List<String> plugins;
        @SerializedName("admin_listen")
        @JsonAdapter(EmptyArrayJsonDeserializer.class)
        private List<String> adminListen;
        @SerializedName("trusted_ips")
        @JsonAdapter(EmptyArrayJsonDeserializer.class)
        private List<String> trustedIps;
        @SerializedName("cassandra_data_centers")
        @JsonAdapter(EmptyArrayJsonDeserializer.class)
        private List<String> cassandraDataCenters;
        @SerializedName("nginx_http_upstream_directives")
        @JsonAdapter(EmptyArrayJsonDeserializer.class)
        private List<NginxHttpUpstreamDirectivesBean> nginxHttpUpstreamDirectives;
        @SerializedName("proxy_listen")
        @JsonAdapter(EmptyArrayJsonDeserializer.class)
        private List<String> proxyListen;
        @SerializedName("stream_listen")
        @JsonAdapter(EmptyArrayJsonDeserializer.class)
        private List<String> streamListen;
        @SerializedName("dns_order")
        @JsonAdapter(EmptyArrayJsonDeserializer.class)
        private List<String> dnsOrder;
        @SerializedName("headers")
        @JsonAdapter(EmptyArrayJsonDeserializer.class)
        private List<String> headers;
        @SerializedName("nginx_http_directives")
        @JsonAdapter(EmptyArrayJsonDeserializer.class)
        private List<NginxHttpDirectivesBean> nginxHttpDirectives;
        @SerializedName("cassandra_contact_poIntegers")
        @JsonAdapter(EmptyArrayJsonDeserializer.class)
        private List<String> cassandraContactPoIntegers;
        @SerializedName("proxy_listeners")
        @JsonAdapter(EmptyArrayJsonDeserializer.class)
        private List<ProxyListenersBean> proxyListeners;
        @SerializedName("db_cache_warmup_entities")
        @JsonAdapter(EmptyArrayJsonDeserializer.class)
        private List<String> dbCacheWarmupEntities;
        @SerializedName("admin_listeners")
        @JsonAdapter(EmptyArrayJsonDeserializer.class)
        private List<AdminListenersBean> adminListeners;


        @Data
        @NoArgsConstructor
        public static class NginxHttpUpstreamDirectivesBean {
            @SerializedName("value")
            private String value;
            @SerializedName("name")
            private String name;
        }

        @NoArgsConstructor
        @Data
        public static class NginxHttpDirectivesBean {
            @SerializedName("value")
            private String value;
            @SerializedName("name")
            private String name;
        }

        @NoArgsConstructor
        @Data
        public static class ProxyListenersBean {
            @SerializedName("listener")
            private String listener;
            @SerializedName("proxy_protocol")
            private Boolean proxyProtocol;
            @SerializedName("reuseport")
            private Boolean reuseport;
            @SerializedName("transparent")
            private Boolean transparent;
            @SerializedName("ssl")
            private Boolean ssl;
            @SerializedName("ip")
            private String ip;
            @SerializedName("deferred")
            private Boolean deferred;
            @SerializedName("http2")
            private Boolean http2;
            @SerializedName("port")
            private Integer port;
            @SerializedName("bind")
            private Boolean bind;
        }

        @NoArgsConstructor
        @Data
        public static class AdminListenersBean {
            @SerializedName("listener")
            private String listener;
            @SerializedName("proxy_protocol")
            private Boolean proxyProtocol;
            @SerializedName("reuseport")
            private Boolean reuseport;
            @SerializedName("transparent")
            private Boolean transparent;
            @SerializedName("ssl")
            private Boolean ssl;
            @SerializedName("ip")
            private String ip;
            @SerializedName("deferred")
            private Boolean deferred;
            @SerializedName("http2")
            private Boolean http2;
            @SerializedName("port")
            private Integer port;
            @SerializedName("bind")
            private Boolean bind;
        }
    }

    @Data
    @NoArgsConstructor
    public static class TimersBean {
        @SerializedName("pending")
        private Integer pending;
        @SerializedName("running")
        private Integer running;
    }
}
