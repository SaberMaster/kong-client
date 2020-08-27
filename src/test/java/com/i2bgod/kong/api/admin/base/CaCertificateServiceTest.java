package com.i2bgod.kong.api.admin.base;

import com.i2bgod.kong.KongClient;
import com.i2bgod.kong.TestProperties;
import com.i2bgod.kong.model.admin.base.CaCertificate;
import com.i2bgod.kong.model.admin.base.page.Page;
import lombok.extern.slf4j.Slf4j;
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
class CaCertificateServiceTest {
    private static CaCertificateService targetService;
    private String tmpId = "be71ac8d-d0fd-4fa4-9603-d16d65926882";

    @BeforeAll
    static void setUp() throws FileNotFoundException {
        TestProperties testConfig = TestProperties.getTestConfig();
        KongClient kongClientUnderTest = new KongClient(testConfig.getAdminUrl());
        targetService = kongClientUnderTest.getAdminClient().getService(CaCertificateService.class);
    }

    @Test
    @Order(1)
    void testAdd() {
        CaCertificate caCertificate = new CaCertificate();
        caCertificate.setCert("-----BEGIN CERTIFICATE-----\n" +
                "MIICQzCCAeqgAwIBAgIRAKry6NeCMCu+A4K4nnTiW8EwCgYIKoZIzj0EAwIwczEL\n" +
                "MAkGA1UEBhMCVVMxEzARBgNVBAgTCkNhbGlmb3JuaWExFjAUBgNVBAcTDVNhbiBG\n" +
                "cmFuY2lzY28xGTAXBgNVBAoTEG9yZzEuZXhhbXBsZS5jb20xHDAaBgNVBAMTE2Nh\n" +
                "Lm9yZzEuZXhhbXBsZS5jb20wHhcNMTkwMTI1MDg1MzAwWhcNMjkwMTIyMDg1MzAw\n" +
                "WjBzMQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMN\n" +
                "U2FuIEZyYW5jaXNjbzEZMBcGA1UEChMQb3JnMS5leGFtcGxlLmNvbTEcMBoGA1UE\n" +
                "AxMTY2Eub3JnMS5leGFtcGxlLmNvbTBZMBMGByqGSM49AgEGCCqGSM49AwEHA0IA\n" +
                "BAUWAECiKHWDNBHm4Pm+Mvf1idCl8iNkgmc4dFfFU5z4zvLzeFAcOMp7PxB1fm7+\n" +
                "syryI07WDHWOyEIy9vgt8/ejXzBdMA4GA1UdDwEB/wQEAwIBpjAPBgNVHSUECDAG\n" +
                "BgRVHSUAMA8GA1UdEwEB/wQFMAMBAf8wKQYDVR0OBCIEINlTv6rjxiOoP6p9/IXv\n" +
                "ZmVMAD688Mu8XfCIABQEEMHsMAoGCCqGSM49BAMCA0cAMEQCIEj0LBLAO9hSJAEx\n" +
                "2oYqDOuJOk+vWVQmBVzrNorg9Qc7AiBynnEv0dbqm68/ExKGEWXCTafW/0j/AMUg\n" +
                "5OdOO9rXnQ==\n" +
                "-----END CERTIFICATE-----");
        caCertificate.setId(tmpId);

        CaCertificate result = targetService.add(caCertificate);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(2)
    void testList() {
        Page<CaCertificate> list = targetService.list(null, null);
        Assertions.assertNotNull(list);
    }


    @Test
    @Order(3)
    void testGet() {
        CaCertificate result = targetService.get(tmpId);
        Assertions.assertNotNull(result);
    }


    @Test
    @Order(4)
    void testPatch() {
        CaCertificate caCertificate = new CaCertificate();
        caCertificate.setCert("-----BEGIN CERTIFICATE-----\n" +
                "MIICQzCCAeqgAwIBAgIRAKry6NeCMCu+A4K4nnTiW8EwCgYIKoZIzj0EAwIwczEL\n" +
                "MAkGA1UEBhMCVVMxEzARBgNVBAgTCkNhbGlmb3JuaWExFjAUBgNVBAcTDVNhbiBG\n" +
                "cmFuY2lzY28xGTAXBgNVBAoTEG9yZzEuZXhhbXBsZS5jb20xHDAaBgNVBAMTE2Nh\n" +
                "Lm9yZzEuZXhhbXBsZS5jb20wHhcNMTkwMTI1MDg1MzAwWhcNMjkwMTIyMDg1MzAw\n" +
                "WjBzMQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMN\n" +
                "U2FuIEZyYW5jaXNjbzEZMBcGA1UEChMQb3JnMS5leGFtcGxlLmNvbTEcMBoGA1UE\n" +
                "AxMTY2Eub3JnMS5leGFtcGxlLmNvbTBZMBMGByqGSM49AgEGCCqGSM49AwEHA0IA\n" +
                "BAUWAECiKHWDNBHm4Pm+Mvf1idCl8iNkgmc4dFfFU5z4zvLzeFAcOMp7PxB1fm7+\n" +
                "syryI07WDHWOyEIy9vgt8/ejXzBdMA4GA1UdDwEB/wQEAwIBpjAPBgNVHSUECDAG\n" +
                "BgRVHSUAMA8GA1UdEwEB/wQFMAMBAf8wKQYDVR0OBCIEINlTv6rjxiOoP6p9/IXv\n" +
                "ZmVMAD688Mu8XfCIABQEEMHsMAoGCCqGSM49BAMCA0cAMEQCIEj0LBLAO9hSJAEx\n" +
                "2oYqDOuJOk+vWVQmBVzrNorg9Qc7AiBynnEv0dbqm68/ExKGEWXCTafW/0j/AMUg\n" +
                "5OdOO9rXnQ==\n" +
                "-----END CERTIFICATE-----");

        CaCertificate result = targetService.patch(tmpId, caCertificate);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(caCertificate.getCert(), result.getCert());
    }

    @Test
    @Order(5)
    void testPut() {
        CaCertificate caCertificate = new CaCertificate();
        caCertificate.setCert("-----BEGIN CERTIFICATE-----\n" +
                "MIICQzCCAeqgAwIBAgIRAKry6NeCMCu+A4K4nnTiW8EwCgYIKoZIzj0EAwIwczEL\n" +
                "MAkGA1UEBhMCVVMxEzARBgNVBAgTCkNhbGlmb3JuaWExFjAUBgNVBAcTDVNhbiBG\n" +
                "cmFuY2lzY28xGTAXBgNVBAoTEG9yZzEuZXhhbXBsZS5jb20xHDAaBgNVBAMTE2Nh\n" +
                "Lm9yZzEuZXhhbXBsZS5jb20wHhcNMTkwMTI1MDg1MzAwWhcNMjkwMTIyMDg1MzAw\n" +
                "WjBzMQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMN\n" +
                "U2FuIEZyYW5jaXNjbzEZMBcGA1UEChMQb3JnMS5leGFtcGxlLmNvbTEcMBoGA1UE\n" +
                "AxMTY2Eub3JnMS5leGFtcGxlLmNvbTBZMBMGByqGSM49AgEGCCqGSM49AwEHA0IA\n" +
                "BAUWAECiKHWDNBHm4Pm+Mvf1idCl8iNkgmc4dFfFU5z4zvLzeFAcOMp7PxB1fm7+\n" +
                "syryI07WDHWOyEIy9vgt8/ejXzBdMA4GA1UdDwEB/wQEAwIBpjAPBgNVHSUECDAG\n" +
                "BgRVHSUAMA8GA1UdEwEB/wQFMAMBAf8wKQYDVR0OBCIEINlTv6rjxiOoP6p9/IXv\n" +
                "ZmVMAD688Mu8XfCIABQEEMHsMAoGCCqGSM49BAMCA0cAMEQCIEj0LBLAO9hSJAEx\n" +
                "2oYqDOuJOk+vWVQmBVzrNorg9Qc7AiBynnEv0dbqm68/ExKGEWXCTafW/0j/AMUg\n" +
                "5OdOO9rXnQ==\n" +
                "-----END CERTIFICATE-----");
        caCertificate.setId(tmpId);
        CaCertificate result = targetService.put(tmpId, caCertificate);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(6)
    void testDelete() {
        targetService.delete(tmpId);
        CaCertificate result = targetService.get(tmpId);
        Assertions.assertNull(result);
    }

}

