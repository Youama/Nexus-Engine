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
 * This class has package level, it can not be used from outside the system package. This class reads and validates
 * the Nexus' .properties file.
 * 
 * @author David Belicza
 * @since 0.1.0
 */
final class Configuration {

    /**
     * Thread local singleton object for each threads.
     */
    private static final ThreadLocal<Configuration> localStorage = new ThreadLocal<Configuration>() {
        protected Configuration initialValue() {
            return new Configuration();
        }
    };

    /**
     * It retrieves the singleton instance from the current thread.
     * 
     * @return The Configuration thread local singleton object.
     */
    static Configuration getInstance() {
        return localStorage.get();
    }

    /**
     * It resets the singleton instance for the current thread.
     */
    static void resetInstance() {
        localStorage.remove();
    }

    /**
     * The properties can be pre-set by Java code. If it isn't the the nexus.properties file will be read.
     */
    private Configuration() {
        if (internalPropertiesMap.size() <= 0) {
            properties = new Properties();
            readConfig();
        } else {
            redProperties = true;
            validateProperties();
        }
    }

    /**
     * The artifact id of the Maven module what starts the whole engine.
     */
    private String registeredPrimaryModuleArtifactId = SystemConstant.DEFAULT_APP;

    /**
     * One of the classes of the Maven module what starts the whole engine.
     */
    private Class<?> registeredMainClass = this.getClass();

    /**
     * The properties what are set in the Java code.
     */
    private Map<String, String> internalPropertiesMap = new HashMap<String, String>();

    /**
     * The properties what are set in the .properties file.
     */
    private Properties properties;

    /**
     * Flag for properties validation.
     */
    private boolean validProperties = false;

    /**
     * Flag for properties reading.
     */
    private boolean redProperties = false;

    /**
     * It sets the primary module artifact ID and class.
     * 
     * @param primaryModuleArtifactId The artifact id of the Maven module what starts the whole engine.
     * @param mainClass One of the classes of the Maven module what starts the whole engine.
     */
    void registerPrimaryModule(String primaryModuleArtifactId, Class<?> mainClass) {
        registeredPrimaryModuleArtifactId = primaryModuleArtifactId;
        registeredMainClass = mainClass;
    }

    /**
     * It sets the properties from Java code. This and the regular properties can not be used in the same time.
     * 
     * @param internalPropertiesMap Map of properties. It should look like the .properties file's keys-values.
     */
    void setInternalPropertiesMap( Map<String, String> internalPropertiesMap) {
        this.internalPropertiesMap = internalPropertiesMap;
    }

    /**
     * It retrieves the artifact id of the Maven module what starts the whole engine.
     * 
     * @return The Maven artifact id.
     */
    String getRegisteredPrimaryModuleArtifactId() {
        return registeredPrimaryModuleArtifactId;
    }
    
    /**
     * It retrieves the primary class of the Maven module what starts the whole engine.
     * 
     * @return One of the classes of the Maven module what starts the whole engine.
     */
    Class<?> getRegisteredMainClass() {
        return registeredMainClass;
    }

    /**
     * It retrieves true when reading and validation were successfully.
     * 
     * @return True when it is OK.
     */
    boolean isOk() {
        return (redProperties && validProperties);
    }

    /**
     * It retrieves that the driver can be used or not.
     * 
     * @param driver The driver name.
     * @return True when driver is active.
     */
    boolean isDriverActive(String driver) {
        return SystemConstant.DATABASE_FLAG_ACTIVE.equals(getProperty(SystemConstant.PROPERTY_DATABASE_PREFIX + driver));
    }

    /**
     * It retrieves the property value by the property key.
     * 
     * @param propertyKey The key what is defined in the .properties file.
     * @return The value what is defined in the .properties file.
     */
    String getProperty(String propertyKey) {
        if (internalPropertiesMap.size() <= 0) {
            return properties.getProperty(propertyKey);
        } else {
            return internalPropertiesMap.get(propertyKey);
        }
    }

    /**
     * It finds, reads, sets and validates the correct .property file's values.
     */
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

    /**
     * It finds the .properties file. The .properties file can be in different directories. It can be inside the JAR 
     * file or it can be outside from the JAR and its name can be different. The .properties file's name can be
     * nexus.properties or nexus.example.properties. The nexus.example.properties file used in Continuous Integration's
     * process but nexus.properties used for local or production version. It always looks first for the nexus.properties
     * file but if it doesn't exist then the nexus.example.properties file will be searched in the current directory. 
     * These .properties files always have to be in the <i>config</i> directory. These config directory can be in any 
     * directory what contains this Configuration class. This method first looks for the .properties file in the class 
     * path and then in its parent and so on. So the .properties file can be defined by developer in the JAR file or it 
     * can be outside from the JAR and changed in run time by third party developers or users. The direction is from
     * inside to outside, so if the .properties file exits inside the JAR, then it can not be overridden from outside. 
     * 
     * @param directoryPath The first possible place of .properties file. This is the starting point for recursive 
     *           search.
     * @return The found .properties file's full path.
     */
    private String findPropertiesFile(Path directoryPath) {
        String productionPropertiesPath = directoryPath.toString() + FileSystemUtil.DS + SystemConstant.DIRECTORY_CONFIG
                + FileSystemUtil.DS + SystemConstant.PROPERTIES_FILE_NAME_LOCAL;
        String ciTestPropertiesPath = directoryPath.toString() + FileSystemUtil.DS + SystemConstant.DIRECTORY_CONFIG
                + FileSystemUtil.DS + SystemConstant.PROPERTIES_FILE_NAME_TRAVIS;

        if (propertyFileExists(productionPropertiesPath)) {
            return productionPropertiesPath;
        } else if (propertyFileExists(ciTestPropertiesPath)) {
            return ciTestPropertiesPath;
        } else if (!directoryPath.toString().equals(directoryPath.getRoot().toString())) {
            return findPropertiesFile(directoryPath.getParent());
        } else {
            return null;
        }
    }

    /**
     * It reads the .properties file by the given file path and sets it to the properties variable.
     * 
     * @param propertiesFilePath The full path of the .properties file.
     */
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

    /**
     * It checks the property file exists or doesn't.
     * 
     * @param propertyPath The full path to .properties file.
     * @return True when the file exists.
     */
    private boolean propertyFileExists(String propertyPath) {
        File file;
        file = new File(propertyPath);

        return file.exists() && !file.isDirectory();

    }

    /**
     * It validates the properties of Nexus.
     */
    private void validateProperties() {
        // TODO validate
        validProperties = true;
    }
}
