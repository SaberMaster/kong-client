package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.KongClient;
import com.i2bgod.kong.TestProperties;
import com.i2bgod.kong.model.admin.base.Certificate;
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
class CertificateServiceTest {
    private static CertificateService targetService;
    public static String TMP_ID = "be71ac8d-d0fd-4fa4-9603-d16d65926880";

    @BeforeAll
    static void setUp() throws FileNotFoundException {
        TestProperties testConfig = TestProperties.getTestConfig();
        KongClient kongClientUnderTest = new KongClient(testConfig.getAdminUrl());
        targetService = kongClientUnderTest.getAdminClient().getService(CertificateService.class);
    }

    @Test
    @Order(1)
    void testAdd() {
        Certificate certificate = new Certificate();
        certificate.setCert("-----BEGIN CERTIFICATE-----\n" +
                "MIICSTCCAe+gAwIBAgIQb27rkPyZVCY/6b5im+IOqTAKBggqhkjOPQQDAjB2MQsw\n" +
                "CQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNU2FuIEZy\n" +
                "YW5jaXNjbzEZMBcGA1UEChMQb3JnMi5leGFtcGxlLmNvbTEfMB0GA1UEAxMWdGxz\n" +
                "Y2Eub3JnMi5leGFtcGxlLmNvbTAeFw0xOTAxMjUwODUzMDBaFw0yOTAxMjIwODUz\n" +
                "MDBaMHYxCzAJBgNVBAYTAlVTMRMwEQYDVQQIEwpDYWxpZm9ybmlhMRYwFAYDVQQH\n" +
                "Ew1TYW4gRnJhbmNpc2NvMRkwFwYDVQQKExBvcmcyLmV4YW1wbGUuY29tMR8wHQYD\n" +
                "VQQDExZ0bHNjYS5vcmcyLmV4YW1wbGUuY29tMFkwEwYHKoZIzj0CAQYIKoZIzj0D\n" +
                "AQcDQgAEZBcmil5DxHyYY1LjeiyNUQUS8rLr6YNxSyzvcaQgZ8TD6dKoV0bTXwqD\n" +
                "L1A5BLBntsY8emuz20wiEhHRNDQwKaNfMF0wDgYDVR0PAQH/BAQDAgGmMA8GA1Ud\n" +
                "JQQIMAYGBFUdJQAwDwYDVR0TAQH/BAUwAwEB/zApBgNVHQ4EIgQguZ3Cfyc4CFRo\n" +
                "+aEk+NLMmYLkfOkSioz4gzNoVPwaeSswCgYIKoZIzj0EAwIDSAAwRQIhAPZ1tnvZ\n" +
                "Zexh53dOWNRiKJCYMeYfWXu51k+dWRaPTLKUAiAP/6bJJqnGCSaYEdUnB/nPoksx\n" +
                "o371IPLi9t9/VIdh9A==\n" +
                "-----END CERTIFICATE-----");
        certificate.setKey("-----BEGIN PRIVATE KEY-----\n" +
                "MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQg5OaWPFTZHTA7PUp8\n" +
                "olBKXI895g8bJuhpolK+MxBluCuhRANCAARkFyaKXkPEfJhjUuN6LI1RBRLysuvp\n" +
                "g3FLLO9xpCBnxMPp0qhXRtNfCoMvUDkEsGe2xjx6a7PbTCISEdE0NDAp\n" +
                "-----END PRIVATE KEY-----");
        certificate.setId(TMP_ID);
        Certificate result = targetService.add(certificate);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(2)
    void testList() {
        Page<Certificate> list = targetService.list(null, null);
        Assertions.assertNotNull(list);
    }


    @Test
    @Order(3)
    void testGet() {
        Certificate result = targetService.get(TMP_ID);
        Assertions.assertNotNull(result);
    }


    @Test
    @Order(4)
    void testPatch() {
        Certificate certificate = new Certificate();
        certificate.setKey("-----BEGIN PRIVATE KEY-----\n" +
                "MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQg7Rc8dM4+bJb8kpTc\n" +
                "kDZ1UrnxgWTez59zB38gGo0GVPihRANCAARn1NYEDW6RAMoEO6Ja61tAo2xg60E3\n" +
                "pjF7+3LfEg+U7C0zkeXFSjv0O8ndh1lY0pZIyRkAz1Cm2BfZfNoJSKB7\n" +
                "-----END PRIVATE KEY-----");
        certificate.setCert("-----BEGIN CERTIFICATE-----\n" +
                "MIICSDCCAe+gAwIBAgIQObllX1Z37SdIro1MSiSbjjAKBggqhkjOPQQDAjB2MQsw\n" +
                "CQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNU2FuIEZy\n" +
                "YW5jaXNjbzEZMBcGA1UEChMQb3JnMS5leGFtcGxlLmNvbTEfMB0GA1UEAxMWdGxz\n" +
                "Y2Eub3JnMS5leGFtcGxlLmNvbTAeFw0xOTAxMjUwODUzMDBaFw0yOTAxMjIwODUz\n" +
                "MDBaMHYxCzAJBgNVBAYTAlVTMRMwEQYDVQQIEwpDYWxpZm9ybmlhMRYwFAYDVQQH\n" +
                "Ew1TYW4gRnJhbmNpc2NvMRkwFwYDVQQKExBvcmcxLmV4YW1wbGUuY29tMR8wHQYD\n" +
                "VQQDExZ0bHNjYS5vcmcxLmV4YW1wbGUuY29tMFkwEwYHKoZIzj0CAQYIKoZIzj0D\n" +
                "AQcDQgAEZ9TWBA1ukQDKBDuiWutbQKNsYOtBN6Yxe/ty3xIPlOwtM5HlxUo79DvJ\n" +
                "3YdZWNKWSMkZAM9QptgX2XzaCUige6NfMF0wDgYDVR0PAQH/BAQDAgGmMA8GA1Ud\n" +
                "JQQIMAYGBFUdJQAwDwYDVR0TAQH/BAUwAwEB/zApBgNVHQ4EIgQgvd7HdR5xPrWM\n" +
                "KdsGhn3KLp+Qy9IzBTryAEbayx9TZyUwCgYIKoZIzj0EAwIDRwAwRAIgJUc8TYey\n" +
                "E2g35lKzeWdTaVJeauuKOH6L0Twkm00msS4CIHaiWLAfSJyNVg1XZOCvslBg7RC5\n" +
                "i+pF9B4EDa6c2VCk\n" +
                "-----END CERTIFICATE-----");
        Certificate result = targetService.patch(TMP_ID, certificate);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(certificate.getKey(), result.getKey());
    }

    @Test
    @Order(5)
    void testPut() {
        Certificate certificate = new Certificate();
        certificate.setCert("-----BEGIN CERTIFICATE-----\n" +
                "MIICSTCCAe+gAwIBAgIQb27rkPyZVCY/6b5im+IOqTAKBggqhkjOPQQDAjB2MQsw\n" +
                "CQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNU2FuIEZy\n" +
                "YW5jaXNjbzEZMBcGA1UEChMQb3JnMi5leGFtcGxlLmNvbTEfMB0GA1UEAxMWdGxz\n" +
                "Y2Eub3JnMi5leGFtcGxlLmNvbTAeFw0xOTAxMjUwODUzMDBaFw0yOTAxMjIwODUz\n" +
                "MDBaMHYxCzAJBgNVBAYTAlVTMRMwEQYDVQQIEwpDYWxpZm9ybmlhMRYwFAYDVQQH\n" +
                "Ew1TYW4gRnJhbmNpc2NvMRkwFwYDVQQKExBvcmcyLmV4YW1wbGUuY29tMR8wHQYD\n" +
                "VQQDExZ0bHNjYS5vcmcyLmV4YW1wbGUuY29tMFkwEwYHKoZIzj0CAQYIKoZIzj0D\n" +
                "AQcDQgAEZBcmil5DxHyYY1LjeiyNUQUS8rLr6YNxSyzvcaQgZ8TD6dKoV0bTXwqD\n" +
                "L1A5BLBntsY8emuz20wiEhHRNDQwKaNfMF0wDgYDVR0PAQH/BAQDAgGmMA8GA1Ud\n" +
                "JQQIMAYGBFUdJQAwDwYDVR0TAQH/BAUwAwEB/zApBgNVHQ4EIgQguZ3Cfyc4CFRo\n" +
                "+aEk+NLMmYLkfOkSioz4gzNoVPwaeSswCgYIKoZIzj0EAwIDSAAwRQIhAPZ1tnvZ\n" +
                "Zexh53dOWNRiKJCYMeYfWXu51k+dWRaPTLKUAiAP/6bJJqnGCSaYEdUnB/nPoksx\n" +
                "o371IPLi9t9/VIdh9A==\n" +
                "-----END CERTIFICATE-----");
        certificate.setKey("-----BEGIN PRIVATE KEY-----\n" +
                "MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQg5OaWPFTZHTA7PUp8\n" +
                "olBKXI895g8bJuhpolK+MxBluCuhRANCAARkFyaKXkPEfJhjUuN6LI1RBRLysuvp\n" +
                "g3FLLO9xpCBnxMPp0qhXRtNfCoMvUDkEsGe2xjx6a7PbTCISEdE0NDAp\n" +
                "-----END PRIVATE KEY-----");
        Certificate result = targetService.put(TMP_ID, certificate);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(6)
    void testDelete() {
        targetService.delete(TMP_ID);
        Certificate result = targetService.get(TMP_ID);
        Assertions.assertNull(result);
    }

    @AfterAll
    static void afterAll() {
        targetService.delete(TMP_ID);
    }
}

