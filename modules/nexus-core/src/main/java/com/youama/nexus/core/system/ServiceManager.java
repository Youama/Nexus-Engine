package com.youama.nexus.core.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author David Belicza
 * @since 2015.08.22.
 */
final class ServiceManager {

    private static ServiceManager instance = null;

    public static ServiceManager getInstance() {
        if (instance == null) {
            instance = new ServiceManager();
        }

        return instance;
    }

    private static String[] supportedDrivers = {"mysql", "postgresql", "hsql"};

    private static List<String> installedDrivers = new ArrayList<String>();

    private static Map<String, SingleService> services = new HashMap<String, SingleService>();

    private static String currentDriverName;

    String getDatasourceId() {
        if ("hsql".equals(currentDriverName)) {
            return "dataSourceClient";
        } else {
            return "dataSourceServer";
        }
    }

    String getCurrentDriverName() {
        return currentDriverName;
    }

    List<String> getInstalledDrivers() {
        return installedDrivers;
    }

    Object getService(Class classType) {
        return services.get(currentDriverName).getService(classType);
    }

    void switchDriver(String driverName) {
        currentDriverName = driverName;
    }

    void initServiceDriver() {
        Configuration configuration = Configuration.getInstance();

        for (String driver : supportedDrivers) {
            if (configuration.isDriverActive(driver)) {
                currentDriverName = driver;
                SingleService serviceManager = new SingleService();
                configuration.setDatabaseType(driver);
                serviceManager.setApplicationContext();
                services.put(driver, serviceManager);
                installedDrivers.add(driver);
            }
        }
    }
}
