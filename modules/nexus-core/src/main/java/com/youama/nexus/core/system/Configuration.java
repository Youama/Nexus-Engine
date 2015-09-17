package com.youama.nexus.core.system;

import com.youama.nexus.core.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
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
        if (internalPropertiesMap.size() <= 0) {
            properties = new Properties();
            readConfig();
        } else {
            redProperties = true;
            validateProperties();
        }
    }

    private String registeredPrimaryModuleArtifactId = SystemConstant.DEFAULT_APP;

    private Class<?> registeredMainClass = this.getClass();

    Map<String, String> internalPropertiesMap = new HashMap<String, String>();

    private Properties properties;

    private boolean validProperties = false;

    private boolean redProperties = false;

    void registerPrimaryModule(String primaryModuleArtifactId, Class<?> mainClass) {
        registeredPrimaryModuleArtifactId = primaryModuleArtifactId;
        registeredMainClass = mainClass;
    }

    void setInternalPropertiesMap( Map<String, String> internalPropertiesMap) {
        this.internalPropertiesMap = internalPropertiesMap;
    }

    String getRegisteredPrimaryModuleArtifactId() {
        return registeredPrimaryModuleArtifactId;
    }

    Class<?> getRegisteredMainClass() {
        return registeredMainClass;
    }

    boolean isOk() {
        return (redProperties && validProperties);
    }

    boolean isDriverActive(String driver) {
        return SystemConstant.DATABASE_FLAG_ACTIVE.equals(getProperty(SystemConstant.PROPERTY_DATABASE_PREFIX + driver));
    }

    String getProperty(String propertyKey) {
        if (internalPropertiesMap.size() <= 0) {
            return properties.getProperty(propertyKey);
        } else {
            return internalPropertiesMap.get(propertyKey);
        }
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
        String productionPropertiesPath = directoryPath.toString() + FileSystemUtil.DS + SystemConstant.DIRECTORY_CONFIG
                + FileSystemUtil.DS + SystemConstant.PROPERTIES_FILE_NAME_LOCAL;
        String ciTestPropertiesPath = directoryPath.toString() + FileSystemUtil.DS + SystemConstant.DIRECTORY_CONFIG
                + FileSystemUtil.DS + SystemConstant.PROPERTIES_FILE_NAME_TRAVIS;

        if (propertyExists(productionPropertiesPath)) {
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
        File file;
        file = new File(propertyPath);

        return file.exists() && !file.isDirectory();

    }

    private void validateProperties() {
    	// TODO validate
        validProperties = true;
    }
}
