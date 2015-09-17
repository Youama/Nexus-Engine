package com.youama.nexus.core.system;


import java.util.Map;

/**
 * This static class is the main class of the Nexus engine. The NexusCoreUtil class provides access to other classes and
 * objects what are provide global, resource and system/configuration specified features.
 *
 * @author David Belicza
 * @since 2015.08.09.
 */
final public class NexusCoreUtil {

    private static boolean allowInternalProperties = true;

    public static boolean isAllowInternalProperties() {
        return allowInternalProperties;
    }

    public static void initServices(String artifactId, Class<?> mainClass) {
        Configuration.getInstance().registerPrimaryModule(artifactId, mainClass);
        ServiceUtil.enableServiceDriver();
    }

    public static void removeServices() {
        Configuration.resetInstance();
        ServiceUtil.disableServiceDriver();
    }

    public static void setProperties(Map<String, String> propertiesMap) {
        if (allowInternalProperties) {
            Configuration.getInstance().setInternalPropertiesMap(propertiesMap);
        }
    }
}
