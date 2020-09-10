package com.i2bgod.kong.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class YamlUtilsTest {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    void testToYamlStr() throws Exception {
        assertEquals("---\n_format_version: \"1.1\"\nconsumers:\n- username: \"bobby\"\n", YamlUtils.toYamlStr("{\n\t\"_format_version\" : \"1.1\",\n\t\"consumers\": [ {\"username\":\"bobby\"}]}"));
    }
}
