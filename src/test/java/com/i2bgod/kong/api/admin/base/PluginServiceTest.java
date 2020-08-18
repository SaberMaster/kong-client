package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.KongClient;
import com.i2bgod.kong.model.admin.base.Plugin;
import com.i2bgod.kong.model.admin.base.page.Page;
import com.i2bgod.kong.model.admin.plugin.config.RateLimiting;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author: Lyn
 * @date: 18/08/2020
 */
@Slf4j
class PluginServiceTest {
    private PluginService pluginService;

    @BeforeEach
    void setUp() {
        KongClient kongClientUnderTest = new KongClient( "http://localhost:18001/", null,null , null);
        pluginService = kongClientUnderTest.getAdminClient().getService(PluginService.class);
    }

    @Test
    void testList() {
        Page<Plugin<Object>> list = pluginService.list(null, null);
        log.info("{}", list);
    }

    @Test
    void testGet() {
        try {
            Plugin<RateLimiting> obj = pluginService.get("c9588f0d-3159-4be2-9056-2594a150f7e0");
            log.info("{}", obj.getConfig().getRedisPort());
        } catch (Exception e) {
            log.error("test", e);
        }
    }
}

