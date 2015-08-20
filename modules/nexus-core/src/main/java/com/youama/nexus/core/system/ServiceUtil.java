package com.youama.nexus.core.system;

import java.util.HashMap;
import java.util.Map;

/**
 * @author David Belicza
 * @since 2015.08.20.
 */
final public class ServiceUtil {

    private static String[] supportedDrivers = {"mysql", "postgresql", "sqlite"};

    private static Map<String, ServiceManager> alternativeServiceManagers = new HashMap<String, ServiceManager>();

    private static ServiceManager defaultServiceManager;

    private static String driverConfigFlag;

    static void initServiceDriver() {
        Configuration configuration = ConfigurationLocalUtil.getConfiguration();
        ServiceManager serviceManager = new ServiceManager();

        String defaultDriver = driverConfigFlag = configuration.getDefaultDatabaseType();

        configuration.setDatabaseType(defaultDriver);
        serviceManager.setApplicationContext();
        defaultServiceManager = serviceManager;

        for (String driver : supportedDrivers) {
            if (!driver.equals(defaultDriver) && configuration.isDriverActive(driver)) {
                driverConfigFlag = driver;
                configuration.setDatabaseType(driver);
                serviceManager.setApplicationContext();
                alternativeServiceManagers.put(driver, serviceManager);
            }
        }

        driverConfigFlag = "";
    }

    /**
     * It retrieves the entity's service implementation by the service class name.
     *
     * @param classType Any class type what is a service class. Service classes should be defined as a Bean.
     * @return It is the service implementation. Return type is an Object what can be cast to any service class.
     */
    public static Object getService(Class classType) {
        return defaultServiceManager.getService(classType);
    }

    public static Object getAlternativeService(Class classType, String driver) {
        if (alternativeServiceManagers.get(driver) != null) {
            return alternativeServiceManagers.get(driver).getService(classType);
        } else {
            return getService(classType);
        }
    }

    public static String getDatasourceId() {
        if ("hsql".equals(driverConfigFlag)) {
            return "dataSourceClient";
        } else {
            return "dataSourceServer";
        }
    }

    public static String getDBDriver() {
        return ConfigurationLocalUtil.getProperty("nexus.db." + driverConfigFlag + ".driver");
    }

    public static String getDBUrl() {
        return ConfigurationLocalUtil.getProperty("nexus.db." + driverConfigFlag + ".url");
    }

    public static String getDBUser() {
        return ConfigurationLocalUtil.getProperty("nexus.db." + driverConfigFlag + ".user");
    }

    public static String getDBPassword() {
        return ConfigurationLocalUtil.getProperty("nexus.db." + driverConfigFlag + ".password");
    }

    public static String getDBDialect() {
        return ConfigurationLocalUtil.getProperty("nexus.hibernate." + driverConfigFlag + ".dialect");
    }

    public static String getDBCreation() {
        return ConfigurationLocalUtil.getProperty("nexus.hibernate." + driverConfigFlag + ".hbm2ddl_auto");
    }

    public static String getDBSessionContext() {
        return ConfigurationLocalUtil.getProperty("nexus.hibernate." + driverConfigFlag + ".current_session_context_class");
    }

    public static String getDBLog() {
        return ConfigurationLocalUtil.getProperty("nexus.hibernate." + driverConfigFlag + ".show_sql");
    }
}
