package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.KongClient;
import com.i2bgod.kong.TestProperties;
import com.i2bgod.kong.model.admin.base.Target;
import com.i2bgod.kong.model.admin.base.Upstream;
import com.i2bgod.kong.model.admin.base.Upstream.HealthchecksBean.ActiveBean.HealthyBean;
import com.i2bgod.kong.model.admin.base.Upstream.HealthchecksBean.ActiveBean.UnhealthyBean;
import com.i2bgod.kong.model.admin.base.UpstreamHealth;
import com.i2bgod.kong.model.admin.base.page.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.FileNotFoundException;

/**
 * @author: Lyn
 * @date: 18/08/2020
 */
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TargetServiceTest {
    private static TargetService targetService;

    private static UpstreamService upstreamService;
    public static String TMP_UPSTREAM_NAME = "target-upstream-name";

    public static String TMP_ID = "be71ac8d-d0fd-4fa4-9603-d16d65926ccc";

    @BeforeAll
    static void setUp() throws FileNotFoundException {
        TestProperties testConfig = TestProperties.getTestConfig();
        KongClient kongClientUnderTest = new KongClient(testConfig.getAdminUrl());
        targetService = kongClientUnderTest.getAdminClient().getService(TargetService.class);

        upstreamService = kongClientUnderTest.getAdminClient().getService(UpstreamService.class);
        Upstream upstream = new Upstream();
        upstream.setName(TMP_UPSTREAM_NAME);
        upstream.setAlgorithm("round-robin");
        Upstream.HealthchecksBean.ActiveBean activeBean = new Upstream.HealthchecksBean.ActiveBean();
        activeBean.setHttpsVerifyCertificate(false);
        UnhealthyBean unhealthyBean = new UnhealthyBean();
        unhealthyBean.setTcpFailures(10);
        unhealthyBean.setTimeouts(10);
        unhealthyBean.setHttpFailures(10);
        unhealthyBean.setInterval(10);

        activeBean.setUnhealthy(unhealthyBean);
        activeBean.setHttpPath("/");
        activeBean.setTimeout(200);
        HealthyBean healthyBean = new HealthyBean();
        healthyBean.setInterval(2);
        healthyBean.setSuccesses(1);
        activeBean.setHealthy(healthyBean);
        activeBean.setConcurrency(1);
        activeBean.setType("http");

        Upstream.HealthchecksBean healthchecksBean = new Upstream.HealthchecksBean();
        healthchecksBean.setActive(activeBean);
        upstream.setHealthchecks(healthchecksBean);
        upstreamService.add(upstream);
    }

    @Test
    @Order(1)
    void testAdd() {
        Target target = new Target();
        target.setTarget("127.0.0.1:80");
        target.setId(TMP_ID);
        Target result = targetService.add(TMP_UPSTREAM_NAME, target);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(2)
    void testList() {
        Page<Target> list = targetService.list(TMP_UPSTREAM_NAME, null, null);
        Assertions.assertNotNull(list);
    }

    @Test
    @Order(3)
    void testSetAddressHealthy() {
        Assertions.assertDoesNotThrow(
                ()-> targetService.setAddressHealthy(TMP_UPSTREAM_NAME, TMP_ID, "127.0.0.1:80")
        );
    }

    @Test
    @Order(4)
    void testSetUnHealthy() {
        targetService.setUnHealthy(TMP_UPSTREAM_NAME, TMP_ID);
        UpstreamHealth health = upstreamService.getHealth(TMP_UPSTREAM_NAME);
        Assertions.assertEquals("UNHEALTHY",health.getData().get(0).getHealth());
    }

    @Test
    @Order(5)
    void testSetHealthy() {
        targetService.setHealthy(TMP_UPSTREAM_NAME, TMP_ID);
        UpstreamHealth health = upstreamService.getHealth(TMP_UPSTREAM_NAME);
        Assertions.assertEquals("HEALTHY",health.getData().get(0).getHealth());
    }

    @Test
    @Order(7)
    void testDelete() {
        Assertions.assertDoesNotThrow(()-> targetService.delete(TMP_UPSTREAM_NAME, TMP_ID));
    }

    @AfterAll
    static void afterAll() {
        upstreamService.delete(TMP_UPSTREAM_NAME);
    }
}

