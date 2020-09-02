package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.KongClient;
import com.i2bgod.kong.TestProperties;
import com.i2bgod.kong.model.admin.base.Certificate;
import com.i2bgod.kong.model.admin.base.Sni;
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
class SniServiceTest {
    private static SniService targetService;

    private static String TMP_NAME = "test_sni";

    public static String ORIGIN_CERTIFICATE_ID = "be71ac8d-d0fd-4fa4-9603-d16d659268a1";
    public static String NEW_CERTIFICATE_ID = "be71ac8d-d0fd-4fa4-9603-d16d659268aa";

    private static CertificateService certificateService;

    @BeforeAll
    static void setUp() throws FileNotFoundException {
        TestProperties testConfig = TestProperties.getTestConfig();
        KongClient kongClientUnderTest = new KongClient();
        targetService = kongClientUnderTest.getAdminClient(testConfig.getAdminUrl()).getService(SniService.class);

        certificateService = kongClientUnderTest.getAdminClient(testConfig.getAdminUrl()).getService(CertificateService.class);
        Certificate certificate0 = new Certificate();
        certificate0.setCert("-----BEGIN CERTIFICATE-----\n" +
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
        certificate0.setKey("-----BEGIN PRIVATE KEY-----\n" +
                "MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQg5OaWPFTZHTA7PUp8\n" +
                "olBKXI895g8bJuhpolK+MxBluCuhRANCAARkFyaKXkPEfJhjUuN6LI1RBRLysuvp\n" +
                "g3FLLO9xpCBnxMPp0qhXRtNfCoMvUDkEsGe2xjx6a7PbTCISEdE0NDAp\n" +
                "-----END PRIVATE KEY-----");
        certificate0.setId(ORIGIN_CERTIFICATE_ID);
        certificateService.add(certificate0);

        certificateService = kongClientUnderTest.getAdminClient(testConfig.getAdminUrl()).getService(CertificateService.class);
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
        certificate.setId(NEW_CERTIFICATE_ID);
        certificateService.add(certificate);
    }

    @Test
    @Order(1)
    void testAdd() {
        Sni sni = new Sni();
        Certificate certificate = new Certificate();
        certificate.setId(ORIGIN_CERTIFICATE_ID);
        sni.setCertificate(certificate);
        sni.setName(TMP_NAME);

        Sni result = targetService.add(sni);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(2)
    void testList() {
        Page<Sni> list = targetService.list(null, null);
        Assertions.assertNotNull(list);
    }


    @Test
    @Order(3)
    void testGet() {
        Sni result = targetService.get(TMP_NAME);
        Assertions.assertNotNull(result);
    }


    @Test
    @Order(4)
    void testPatch() {
        Sni sni = new Sni();
        Certificate certificate = new Certificate();
        certificate.setId(NEW_CERTIFICATE_ID);
        sni.setCertificate(certificate);
        sni.setName(TMP_NAME);

        Sni result = targetService.patch(TMP_NAME, sni);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(certificate.getId(), result.getCertificate().getId());
    }

    @Test
    @Order(5)
    void testPut() {
        Sni sni = new Sni();
        Certificate certificate = new Certificate();
        certificate.setId(ORIGIN_CERTIFICATE_ID);
        sni.setCertificate(certificate);
        sni.setName(TMP_NAME);
        Sni result = targetService.put(TMP_NAME, sni);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(6)
    void testDelete() {
        targetService.delete(TMP_NAME);
        Sni result = targetService.get(TMP_NAME);
        Assertions.assertNull(result);
    }


    @AfterAll
    static void afterAll() {
        targetService.delete(TMP_NAME);
        certificateService.delete(ORIGIN_CERTIFICATE_ID);
        certificateService.delete(NEW_CERTIFICATE_ID);
    }
}

