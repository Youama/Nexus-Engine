package com.youama.nexus.core.system;

import java.io.File;
import java.util.Properties;

/**
 * This static class is the main class of the Nexus engine. The SystemUtil class provides access to other classes and
 * objects what are provide global, resource and system/configuration specified features.
 *
 * @author David Belicza - 87.bdavid@gmail.com
 * @since 2015.08.09.
 */
final public class SystemUtil {

    public static String DS = File.separator;

    private static Properties propertyCache;

    /**
     * Initiate the Nexus Engine. This method has to be called from outside because of the application context and
     * other global settings and hooks. If a module calls this method that downgrades the access to the module's local
     * level instead application level.
     */
    public static void initiateSystem() {
        initiateSystemProcess("test", "mysql");
    }

    public static void initiateSystem(boolean production, String database) {
        String version = "test";
        if (production) {
            version = "production";
        }

        initiateSystemProcess(version, database);
    }

    private static void initiateSystemProcess(String version, String database) {
        System system = System.getInstance();
        system.initDirectoryMapping();
        system.initConfiguration(version, database);
        propertyCache = system.getConfiguration().getProperties();
        system.initServices();
    }

    public static void clearCache() {
        propertyCache = null;
    }

    /**
     * This method does not trigger any unit or story tests, it is for checking the current system/device before
     * initiateSystem boots the engine.
     */
    public static void testSystem() {

    }

    public static String getBaseDirectory() {
        return System.getInstance().getBaseDirectory();
    }

    /**
     * It retrieves the entity's service implementation by the service class name.
     *
     * @param classType Any class type what is a service class. Service classes should be defined as a Bean.
     * @return It is the service implementation. Return type is an Object what can be cast to any service class.
     */
    public static Object getService(Class classType) {
        return System.getInstance().getServiceProvider().getService(classType);
    }

    public static String getDBDriver() {
        return propertyCache.getProperty("nexus.db.driver");
    }

    public static String getDBUrl() {
        return propertyCache.getProperty("nexus.db.url");
    }

    public static String getDBUser() {
        return propertyCache.getProperty("nexus.db.user");
    }

    public static String getDBPassword() {
        return propertyCache.getProperty("nexus.db.password");
    }

    public static String getDBDialect() {
        return propertyCache.getProperty("nexus.hibernate.dialect");
    }

    public static String getDBCreation() {
        return propertyCache.getProperty("nexus.hibernate.hbm2ddl_auto");
    }

    public static String getDBSessionContext() {
        return propertyCache.getProperty("nexus.hibernate.current_session_context_class");
    }

    public static String getDBLog() {
        return propertyCache.getProperty("nexus.hibernate.show_sql");
    }
}
