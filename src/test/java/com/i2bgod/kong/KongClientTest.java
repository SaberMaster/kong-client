package com.i2bgod.kong;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;


@Slf4j
class KongClientTest {

    private static KongClient kongClientUnderTest;

    @BeforeAll
    static void setUp() throws IOException {
        TestProperties testConfig = TestProperties.getTestConfig();
        KongClient kongClientUnderTest = new KongClient();
    }
}
