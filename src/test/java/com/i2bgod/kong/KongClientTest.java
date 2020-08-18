package com.i2bgod.kong;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;


@Slf4j
class KongClientTest {

    private KongClient kongClientUnderTest;

    @BeforeEach
    void setUp() {
        kongClientUnderTest = new KongClient( "http://localhost:18001/", null,null , null);
    }



}
