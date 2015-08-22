package com.youama.nexus.core.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author David Belicza
 * @since 2015.08.20.
 */
final public class ServiceUtil {

    static void initServiceDriver() {
        ServiceManager.getInstance().initServiceDriver();
    }

    public static List<String> getInstalledDrivers() {
        return ServiceManager.getInstance().getInstalledDrivers();
    }

    public static void switchDriver(String driverName) {
        ServiceManager.getInstance().switchDriver(driverName);
    }

    /**
     * It retrieves the entity's service implementation by the service class name.
     *
     * @param classType Any class type what is a service class. Service classes should be defined as a Bean.
     * @return It is the service implementation. Return type is an Object what can be cast to any service class.
     */
    public static Object getService(Class classType) {
        return ServiceManager.getInstance().getService(classType);
    }

    public static String getDatasourceId() {
        return ServiceManager.getInstance().getDatasourceId();
    }

    public static String getDBDriver() {
        return Configuration.getInstance()
                .getProperty("nexus.db." + ServiceManager.getInstance().getCurrentDriverName() + ".driver");
    }

    public static String getDBUrl() {
        return Configuration.getInstance()
                .getProperty("nexus.db." + ServiceManager.getInstance().getCurrentDriverName() + ".url");
    }

    public static String getDBUser() {
        return Configuration.getInstance()
                .getProperty("nexus.db." + ServiceManager.getInstance().getCurrentDriverName() + ".user");
    }

    public static String getDBPassword() {
        return Configuration.getInstance()
                .getProperty("nexus.db." + ServiceManager.getInstance().getCurrentDriverName() + ".password");
    }

    public static String getDBDialect() {
        return Configuration.getInstance()
                .getProperty("nexus.hibernate." + ServiceManager.getInstance().getCurrentDriverName() + ".dialect");
    }

    public static String getDBCreation() {
        return Configuration.getInstance()
                .getProperty("nexus.hibernate." +
                        ServiceManager.getInstance().getCurrentDriverName() + ".hbm2ddl_auto");
    }

    public static String getDBSessionContext() {
        return Configuration.getInstance()
                .getProperty("nexus.hibernate." +
                        ServiceManager.getInstance().getCurrentDriverName() + ".current_session_context_class");
    }

    public static String getDBLog() {
        return Configuration.getInstance()
                .getProperty("nexus.hibernate." + ServiceManager.getInstance().getCurrentDriverName() + ".show_sql");
    }
}
