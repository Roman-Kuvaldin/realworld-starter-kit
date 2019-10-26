package io.realword.backend.vertx.config;

import io.realword.backend.vertx.config.AppConfig.ConfigKeyValues;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppConfigTest {

    public AppConfigTest() {
    }

    @Test
    public void testMongoDbHost() {
        String expResult = ConfigKeyValues.APP_MONGODB_URL.getValue();

        JsonObject config = null;
        assertEquals(expResult, AppConfig.mongoDbUrl(config), "Unexpected default value when passing null-reference");

        config = new JsonObject();
        assertEquals(expResult, AppConfig.mongoDbUrl(config), "Unexpected default value when passing empty config");

        expResult = "https://mongodb.org:27123/";
        config.put(ConfigKeyValues.APP_MONGODB_URL.getKey(), expResult);

        assertEquals(expResult, AppConfig.mongoDbUrl(config), "Unexpected value when passing test config");
    }

    @Test
    public void testAppPort() {
        String expResult = ConfigKeyValues.APP_PORT.getValue();

        JsonObject config = null;
        assertEquals(expResult, AppConfig.port(config), "Unexpected default value when passing null-reference");

        config = new JsonObject();
        assertEquals(expResult, AppConfig.port(config), "Unexpected default value when passing empty config");

        expResult = "443";
        config.put(ConfigKeyValues.APP_PORT.getKey(), expResult);

        assertEquals(expResult, AppConfig.port(config), "Unexpected value when passing test config");
    }
}
