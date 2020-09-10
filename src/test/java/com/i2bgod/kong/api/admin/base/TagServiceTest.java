package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.AdminClientConfig;
import com.i2bgod.kong.KongClient;
import com.i2bgod.kong.TestProperties;
import com.i2bgod.kong.model.admin.base.TagEntity;
import com.i2bgod.kong.model.admin.base.page.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.FileNotFoundException;

/**
 * @author: Lyn
 * @date: 18/08/2020
 */

@Slf4j
@Execution(ExecutionMode.CONCURRENT)
class TagServiceTest {
    private static TagService targetService;

    @BeforeAll
    static void setUp() throws FileNotFoundException {
        TestProperties testConfig = TestProperties.getTestConfig();
        KongClient kongClientUnderTest = new KongClient();
        AdminClientConfig adminClientConfig = new AdminClientConfig();
        adminClientConfig.setAdminUrl(testConfig.getAdminUrl());
        targetService = kongClientUnderTest.createAdminClient(adminClientConfig).getService(TagService.class);
    }


    @Test
    void testList() {
        Page<TagEntity> list = targetService.list(null, null, null);
        Assertions.assertNotNull(list);
    }

    @Test
    void testList2() {
        Page<TagEntity> list = targetService.list("core", null, null);
        Assertions.assertNotNull(list);
    }
}

