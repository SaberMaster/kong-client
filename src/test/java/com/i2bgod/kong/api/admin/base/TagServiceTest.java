package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.KongClient;
import com.i2bgod.kong.TestProperties;
import com.i2bgod.kong.model.admin.base.TagEntity;
import com.i2bgod.kong.model.admin.base.page.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

/**
 * @author: Lyn
 * @date: 18/08/2020
 */

@Slf4j
class TagServiceTest {
    private TagService tagService;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        TestProperties testConfig = TestProperties.getTestConfig();
        KongClient kongClientUnderTest = new KongClient(testConfig.getAdminUrl());
        tagService = kongClientUnderTest.getAdminClient().getService(TagService.class);
    }


    @Test
    void testList() {
        Page<TagEntity> list = tagService.list(null, null, null);
        log.info("{}", list);
    }

    @Test
    void testList2() {
        Page<TagEntity> list = tagService.list("core", null, null);
        log.info("{}", list);
    }
}

