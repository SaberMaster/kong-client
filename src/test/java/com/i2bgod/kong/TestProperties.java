package com.i2bgod.kong;

import com.google.gson.Gson;
import com.i2bgod.kong.util.ConfigUtils;
import lombok.Data;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author: Lyn
 * @date: 26/08/2020
 */
@Data
public class TestProperties {
    private String adminUrl;

    public static TestProperties getTestConfig() throws FileNotFoundException {
        InputStream inputStream = ConfigUtils.class.getClassLoader().getResourceAsStream("test.json");
        if (null == inputStream) {
            throw new FileNotFoundException("test config not found!");
        }
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        Gson gson = new Gson();
        return gson.fromJson(inputStreamReader, TestProperties.class);
    }
}
