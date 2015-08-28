package com.youama.nexus.core.system;

import com.youama.nexus.core.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author David Belicza
 * @since 2015.08.18.
 */
final class Configuration {

    private static final ThreadLocal<Configuration> localStorage = new ThreadLocal<Configuration>() {
        protected Configuration initialValue() {
            return new Configuration();
        }
    };

    static Configuration getInstance() {
        return localStorage.get();
    }

    static void resetInstance() {
        localStorage.remove();
    }

    private Configuration() {
        properties = new Properties();
        readConfig();
    }

    private String registeredPrimaryModule = SystemConstant.DEFAULT_APP;

    private String propertiesFileLocal;

    private String propertiesFileRemoteCIExample;

    private Properties properties;

    private boolean validProperties = false;

    private boolean redProperties = false;

    void setRegisteredPrimaryModuleArtifactId(String primaryModule) {
        registeredPrimaryModule = primaryModule;
    }

    String getRegisteredPrimaryModuleArtifactId() {
        return registeredPrimaryModule;
    }

    boolean isOk() {
        return (redProperties && validProperties);
    }

    boolean isDriverActive(String driver) {
        if ("active".equals(properties.getProperty("nexus.db." + driver))) {
            return true;
        } else {
            return false;
        }
    }

    String getProperty(String propertyKey) {
        return properties.getProperty(propertyKey);
    }

    private void readConfig() {
        propertiesFileLocal = FileSystemUtil.getBaseDirectory() + FileSystemUtil.DS + "config" + FileSystemUtil.DS +
                "nexus.properties";
        propertiesFileRemoteCIExample = FileSystemUtil.getBaseDirectory() + FileSystemUtil.DS + "config" +
                FileSystemUtil.DS + "nexus.example.properties";
        readProperties();
        validateProperties();
    }

    private void readProperties() {
        InputStream input = null;

        try {

            if (propertyExists(propertiesFileLocal)) {
                input = new FileInputStream(propertiesFileLocal);
                properties.load(input);
                redProperties = true;
            } else {
                input = new FileInputStream(propertiesFileRemoteCIExample);
                properties.load(input);
                redProperties = true;
            }

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

    private boolean propertyExists(String propertyPath) {
        File file;
        file = new File(propertyPath);

        if (file.exists() && !file.isDirectory()) {
            return true;
        }

        return false;
    }

    private void validateProperties() {
        validProperties = true;
    }
}
