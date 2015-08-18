package com.youama.nexus.core.system;

import com.youama.nexus.core.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author David Belicza
 * @since 2015.08.18.
 */
final class Configuration {

    private String propertiesFile;

    private Properties properties;

    private boolean validProperties = false;

    private boolean redProperties = false;

    Configuration() {
        properties = new Properties();
    }

    boolean isOk() {
        return (redProperties && validProperties);
    }

    void readConfig(String version, String database) {
        propertiesFile = SystemUtil.getBaseDirectory() + SystemUtil.DS + "nexus_" + version + "_" + database
                + ".properties";
        readProperties();
        validateProperties();
    }

    private void readProperties() {
        InputStream input = null;

        try {
            input = new FileInputStream(propertiesFile);
            properties.load(input);
            redProperties = true;
        } catch (IOException e) {
            Log.warning(e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    Log.warning(e);
                }
            }
        }
    }

    private void validateProperties() {
        validProperties = true;
    }

    Properties getProperties() {
        return properties;
    }
}
