package io.realword.backend.vertx.config;

import io.vertx.core.json.JsonObject;

/**
 * App config. <br>
 *
 * @author roman.kuvaldin@gmail.com
 */
public class AppConfig {

    public static enum ConfigKeyValues {
        APP_MONGODB_URL("APP_MONGODB_URL", "http://localhost:27017/"),
        APP_PORT("APP_PORT", "80");

        private final String key;
        private final String value;

        private ConfigKeyValues(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

    public static String mongoDbUrl(JsonObject config) {
        if (config == null) {
            return ConfigKeyValues.APP_MONGODB_URL.getValue();
        }
        return config.getString(ConfigKeyValues.APP_MONGODB_URL.getKey(), ConfigKeyValues.APP_MONGODB_URL.getValue());
    } // END mongoDbUrl

    public static String port(JsonObject config) {
        try {
            return config.getString(ConfigKeyValues.APP_PORT.getKey());
        } catch (Exception e) {
        }
        try {
            return config.getDouble(ConfigKeyValues.APP_PORT.getKey()).toString();
        } catch (Exception e) {
        }
        return ConfigKeyValues.APP_PORT.getValue();
    } // END mongoDbUrl
} // END AppConfig
