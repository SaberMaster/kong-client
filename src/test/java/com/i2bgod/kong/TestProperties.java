package com.i2bgod.kong;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author: Lyn
 * @date: 26/08/2020
 */
@Data
public class TestProperties {
    private String adminUrl;
    private String dblessAdminUrl;

    public static TestProperties getTestConfig() throws IOException {
        InputStream inputStream = KongClient.class.getClassLoader().getResourceAsStream("test.json");
        if (null == inputStream) {
            throw new FileNotFoundException("test config not found!");
        }
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(inputStreamReader, TestProperties.class);
    }
}
