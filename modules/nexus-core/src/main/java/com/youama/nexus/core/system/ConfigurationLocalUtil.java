package com.youama.nexus.core.system;

/**
 * @author David Belicza
 * @since 2015.08.20.
 */
final public class ConfigurationLocalUtil {

    private static Configuration configuration;

    static Configuration getConfiguration() {
        if (configuration == null) {
            configuration = new Configuration();
            configuration.readConfig();
        }

        return configuration;
    }

    static String getProperty(String property) {
        return getConfiguration().getProperty(property);
    }
}
