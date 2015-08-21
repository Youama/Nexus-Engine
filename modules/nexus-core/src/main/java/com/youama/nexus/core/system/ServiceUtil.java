package com.youama.nexus.core.system;

import com.sun.xml.internal.fastinfoset.util.StringArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author David Belicza
 * @since 2015.08.20.
 */
final public class ServiceUtil {

    private static String[] supportedDrivers = {"mysql", "postgresql", "hsql"};

    private static List<String> installedDrivers = new ArrayList<String>();

    private static Map<String, ServiceManager> serviceManagers = new HashMap<String, ServiceManager>();

    private static String currentDriverName;

    static void initServiceDriver() {
        Configuration configuration = ConfigurationLocalUtil.getConfiguration();

        for (String driver : supportedDrivers) {
            if (configuration.isDriverActive(driver)) {
                currentDriverName = driver;
                ServiceManager serviceManager = new ServiceManager();
                configuration.setDatabaseType(driver);
                serviceManager.setApplicationContext();
                serviceManagers.put(driver, serviceManager);
                installedDrivers.add(driver);
            }
        }
    }

    public static List<String> getInstalledDrivers() {
        return installedDrivers;
    }

    public static void switchDriver(String driver) {
        currentDriverName = driver;
    }

    /**
     * It retrieves the entity's service implementation by the service class name.
     *
     * @param classType Any class type what is a service class. Service classes should be defined as a Bean.
     * @return It is the service implementation. Return type is an Object what can be cast to any service class.
     */
    public static Object getService(Class classType) {
        return serviceManagers.get(currentDriverName).getService(classType);
    }

    public static String getDatasourceId() {
        if ("hsql".equals(currentDriverName)) {
            return "dataSourceClient";
        } else {
            return "dataSourceServer";
        }
    }

    public static String getDBDriver() {
        return ConfigurationLocalUtil.getProperty("nexus.db." + currentDriverName + ".driver");
    }

    public static String getDBUrl() {
        return ConfigurationLocalUtil.getProperty("nexus.db." + currentDriverName + ".url");
    }

    public static String getDBUser() {
        return ConfigurationLocalUtil.getProperty("nexus.db." + currentDriverName + ".user");
    }

    public static String getDBPassword() {
        return ConfigurationLocalUtil.getProperty("nexus.db." + currentDriverName + ".password");
    }

    public static String getDBDialect() {
        return ConfigurationLocalUtil.getProperty("nexus.hibernate." + currentDriverName + ".dialect");
    }

    public static String getDBCreation() {
        return ConfigurationLocalUtil.getProperty("nexus.hibernate." + currentDriverName + ".hbm2ddl_auto");
    }

    public static String getDBSessionContext() {
        return ConfigurationLocalUtil.getProperty("nexus.hibernate." + currentDriverName + ".current_session_context_class");
    }

    public static String getDBLog() {
        return ConfigurationLocalUtil.getProperty("nexus.hibernate." + currentDriverName + ".show_sql");
    }
}
