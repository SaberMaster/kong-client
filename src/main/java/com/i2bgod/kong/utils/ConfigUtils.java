package com.i2bgod.kong.utils;

import com.google.gson.Gson;
import com.i2bgod.kong.bean.ClientConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author: Lyn
 * @date: 05/08/2020
 */
@Slf4j
public class ConfigUtils {

    private ConfigUtils() {}

    private static final String CONFIG_FILENAME = "kong-client.json";
    private static Gson gson = new Gson();
    private static ClientConfig clientConfig;

    static {
        try {
            loadConfig();
        } catch (IOException e) {
            log.error("load kong client config error", e);
        }
    }

    public static Gson getGson() {
        return gson;
    }

    public static void loadConfig() throws IOException {
        InputStream inputStream = ConfigUtils.class.getClassLoader().getResourceAsStream(CONFIG_FILENAME);
        if (null == inputStream) {
            throw new FileNotFoundException("kong client config not found!");
        }
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        clientConfig = gson.fromJson(inputStreamReader, ClientConfig.class);
    }

    public static ClientConfig getClientConfig() {
        return clientConfig;
    }
}
