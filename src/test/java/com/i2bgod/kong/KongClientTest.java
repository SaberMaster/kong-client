package com.i2bgod.kong;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileNotFoundException;


@Slf4j
class KongClientTest {

    private KongClient kongClientUnderTest;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        TestProperties testConfig = TestProperties.getTestConfig();
        KongClient kongClientUnderTest = new KongClient();
    }
}
