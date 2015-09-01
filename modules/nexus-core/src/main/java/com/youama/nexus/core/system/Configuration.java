package com.youama.nexus.core.system;

import com.youama.nexus.core.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    private String propertiesFileNameProduction = "nexus.properties";

    private String propertiesFileNameCITest = "nexus.example.properties";

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
        try {
            String propertiesFilePath = findPropertiesFile(
                    Paths.get(Configuration.class.getProtectionDomain().getCodeSource().getLocation().toURI()));
            readProperties(propertiesFilePath);
            validateProperties();
        } catch (URISyntaxException e) {
            Log.warning(e);
        }
    }

    private String findPropertiesFile(Path directoryPath) {
        String productionPropertiesPath = directoryPath.toString() + FileSystemUtil.DS + "config"  + FileSystemUtil.DS +
                propertiesFileNameProduction;
        String ciTestPropertiesPath = directoryPath.toString() + FileSystemUtil.DS + "config" + FileSystemUtil.DS +
                propertiesFileNameCITest;

        if  (propertyExists(productionPropertiesPath)) {
            return productionPropertiesPath;
        } else if (propertyExists(ciTestPropertiesPath)) {
            return ciTestPropertiesPath;
        } else if (!directoryPath.toString().equals(directoryPath.getRoot().toString())) {
            return findPropertiesFile(directoryPath.getParent());
        } else {
            return null;
        }
    }

    private void readProperties(String propertiesFilePath) {
        InputStream input = null;

        try {
            input = new FileInputStream(propertiesFilePath);
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

    private boolean propertyExists(String propertyPath) {
        System.out.println("in: " + propertyPath);
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
