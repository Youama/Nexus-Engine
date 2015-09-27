package com.youama.nexus.core.system;


import java.util.Map;

/**
 * This static class is the <i>main</i> class of the Nexus engine. The NexusCoreUtil class provides access to other 
 * classes and objects what are provide global, resource and system/configuration specified features.
 *
 * @author David Belicza
 * @since 2015.08.09.
 */
final public class NexusCoreUtil {

    /**
     * When it is true then to set the Nexus properties from Java code is possible but to set from the .properties file
     * is not.
     */
    private static boolean allowInternalProperties = true;

    /**
     * It retrieves that system properties can be injected through Java instead of nexus.properties file.
     * 
     * @return True when it is allowed.
     */
    public static boolean isAllowInternalProperties() {
        return allowInternalProperties;
    }

    /**
     * It initiates the service layer by the Maven module's artifact id and by its primary class.
     * 
     * @param artifactId The Maven module's artifact id what initiates the Nexus.
     * @param mainClass The primary class what initiates the Nexus.
     */
    public static void initServices(String artifactId, Class<?> mainClass) {
        Configuration.getInstance().registerPrimaryModule(artifactId, mainClass);
        ServiceUtil.enableServiceDriver();
    }

    /**
     * It disables the Nexus' service layer and the main configuration.
     */
    public static void removeServices() {
        Configuration.resetInstance();
        ServiceUtil.disableServiceDriver();
    }

    /**
     * When <i>NexusCoreUtil.allowInternalProperties</i> is true then the settings of properties is possible by this
     * method. If this method would be used then the nexus.properties file will never be read in.
     * 
     * @param propertiesMap Map of properties. It should look like the .properties file's keys-values.
     */
    public static void setProperties(Map<String, String> propertiesMap) {
        if (allowInternalProperties) {
            Configuration.getInstance().setInternalPropertiesMap(propertiesMap);
        }
    }
}
